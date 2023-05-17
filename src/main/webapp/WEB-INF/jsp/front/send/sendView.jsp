<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <c:import url="/front.include.common_front_head.dp/proc.go"	charEncoding="utf-8" />

    <script>

        <%-- 송금내역 페이징 전역변수 --%>
        let list = new Object();
        list.number = new Array();
        let count = 0;
        let pageSize;

        function getBalance() {
            $("#panBalance").html(CME.fmt("0") + " " + $("#coinNm").val());
            $("#ethBalance").html(CME.fmt("0") + " ETH");
            $("#dataField").html("");

            <%-- 초기화 --%>
            list = new Object();
            list.number = new Array();
            count = 0;


            let addr = $("#selectAddr").val();
            if(addr == "-1") {
                return false;
            }

            let data = {
                "addr": addr,
                "coinNm": $("#coinNm").val()
            }
            postLoadigAjax("/front.send.getBalance.dp/proc.go", data, getBalanceAfter, true, "");

        }

        function getBalanceAfter(res) {
            $("#panBalance").html(CME.fmt(res.panBalance) + " " + $("#coinNm").val());
            if($("#coinNm").val() == "WS") {
                $("#ethBalance").html(CME.fmt(res.ethBalance) + " ETH");
            }

            let html = "";
            let addr = $("#selectAddr").val();
            let pageNum = $("#pageNum").val();

            res.sendList.list.data.forEach(function(node, i) {
                if(node[2].toUpperCase() == addr.toUpperCase()) {
                    list.number[count]= node;
                    count++
                }
            });

            <%-- 페이지에서 첫번째 인덱스와 마지막 인덱스를 계산 --%>
            let firstIndex = (pageNum-1)*10;
            let lastIndex = firstIndex + 10;

            pageSize = (Math.ceil(parseFloat(count) /10) != 0) ? Math.ceil(parseFloat(count) /10) : 1;

            <%-- 반복문을 사용해서 페이지에서 첫번째 인덱스와 마지막 인덱스의 사이값을 html담는다 --%>
            for(var i = firstIndex; i < lastIndex; i++){
                <%-- data가 존재할때만 --%>
                if(typeof list.number[i] != 'undefined'){
                    html += "<tr>";
                    html += "<td>" + list.number[i][0] + "</td>";
                    html += "<td>" + list.number[i][5] + "</td>";
                    html += "<td>" + list.number[i][4] + "</td>";
                    if($("#coinNm").val() == "WS") {
                        html += "<td>" + list.number[i][6] + " ETH</td>";
                    } else {
                        html += "<td>" + list.number[i][6] + "</td>";
                    }
                    html += "</tr>";
                }
            }
            $("#dataField").html(html);
            $("#pageTxt").val("Page " + pageNum + " of " + pageSize);

        }

        function goPage(type) {
            let pageNum = $("#pageNum").val();
            let lastPage = pageSize;

            switch(type) {
                case "FIRST":
                    if(pageNum != 1) {
                        $("#pageNum").val("1");
                        pageNum = 1;
                    } else {
                        return false;
                    }
                    break;
                case "LAST":
                    if(pageNum != lastPage) {
                        $("#pageNum").val(lastPage);
                        pageNum = lastPage;
                    } else {
                        return false;
                    }
                    break;
                case "NEXT":
                    if(pageNum != lastPage) {
                        $("#pageNum").val(parseInt(pageNum) + 1);
                        pageNum++;
                    } else {
                        return false;
                    }
                    break;
                case "PREV":
                    if(pageNum != 1) {
                        $("#pageNum").val(pageNum - 1);
                        pageNum--;
                    } else {
                        return false;
                    }
                    break;
            }

            let html = "";
            let firstIndex = (pageNum-1)*10;
            let lastIndex = firstIndex + 10;

            for(var i = firstIndex; i < lastIndex; i++){
                if(typeof list.number[i] != 'undefined') {
                    html += "<tr>";
                    html += "<td>" + list.number[i][0] + "</td>";
                    html += "<td>" + list.number[i][5] + "</td>";
                    html += "<td>" + list.number[i][4] + "</td>";
                    if($("#coinNm").val() == "WS") {
                        html += "<td>" + list.number[i][6] + " ETH</td>";
                    } else {
                        html += "<td>" + list.number[i][6] + "</td>";
                    }
                    html += "</tr>";
                }
            }

            $("#dataField").html(html);
            $("#pageTxt").val("Page " + pageNum + " of " + pageSize);
        }

/*        function getPage() {
            let addr = $("#selectAddr").val();

            if(addr == "-1") {
                return false;
            } else {
                let data = {
                    "addr": addr,
                    "pageNum": $("#pageNum").val(),
                    "coinNm" : $("#coinNm").val()
                };

                postLoadigAjax("/front.send.getSendHistPage.dp/proc.go", data, getPageAfter, true, "");
            }

            let pageNum = $("#pageNum").val();
            let recordTotal = $("#recordTotal").val();
            //$("#pageTxt").val(pageNum + " / " + recordTotal);
        }

        function getPageAfter(res) {
            let addr = $("#selectAddr").val();

            let html = "";
            res.sendList.list.data.forEach(function(node, i) {
                if(node[2].toUpperCase() == addr.toUpperCase()) {
                    html += "<tr>";
                    html += "<td>" + node[0] + "</td>";
                    html += "<td>" + node[5] + "</td>";
                    html += "<td>" + node[4] + "</td>";
                    if($("#coinNm").val() == "WS") {
                        html += "<td>" + node[6] + " ETH</td>";
                    } else {
                        html += "<td>" + node[6] + "</td>";
                    }
                    html += "</tr>";
                }
            });

            let recordTotal = res.sendList.list.recordsTotal;
            let pageNum = $("#pageNum").val();

            $("#dataField").html(html);
            //$("#pageTxt").html(pageNum + " / " + recordTotal);
        }*/

        function goSendView() {
            let selected = $("#selectAddr").val();
            if(selected == "-1") {
                swal('<spring:message code="TEXT_388"/>');
                return false;
            }

            let balance = parseFloat($("#panBalance").html());
            let ethBalance = parseFloat($("#ethBalance").html());
            let coinNm = $("#coinNm").val();
            let cmt = parseFloat($("#coinNm option:selected").data("cmt"));

            if(coinNm == "WS") {
                if(ethBalance < cmt) {
                    swal('<spring:message code="02_37" arguments="' + cmt + '"/>');
                    return false;
                }
            } else {
                if(balance < cmt) {
                    swal('<spring:message code="02_38" arguments="' + cmt + ',' + coinNm + '"/>');
                    return false;
                }
            }

            if($("#selectAddr option:selected").data("haspwdyn") == "N") {
                swal('<spring:message code="TEXT_416"/>').then(function() {// 출금암호 설정후 송금 가능합니다. todo 영어없음!!
                    location.href="/front.wallet.walletManageView.dp/proc.go";
                });
                return false;
            }

            postLoadigAjax("/front.send.checkSendAuth.dp/proc.go", null, goSendViewAfter, true, "");
        }

        function goSendViewAfter(res) {
            if(res.resultCode == -1) {
                swal(res.resultMsg).then(function() {
                    location.href = "/front.mypage.myInfoView.dp/proc.go";
                });
            } else {
                CME.postSubmit("/front.send.sendingView.dp/proc.go", [
                    {name : "addr", value : $("#selectAddr").val() },
                    {name : "coinNm", value : $("#coinNm").val() },
                ]);
            }
        }

        function getWallet() {
            if($("#coinNm").val() == "WS") {
                $(".cmtEthSecion").show();
            } else {
                $(".cmtEthSecion").hide();
            }

            $("#panBalance").html(CME.fmt("0") + " " + $("#coinNm").val());
            $("#ethBalance").html(CME.fmt("0") + " ETH");
            $("#selectAddr").html('');
            $("#dataField").html('');

            let data = {
                coinNm : $("#coinNm").val()
            }
            postLoadigAjax("/front.send.getWalletList.dp/proc.go", data, getWalletAfter, true, "");
        }

        function getWalletAfter(res) {
            let html = "";
            html += '<option value="-1"><spring:message code="TEXT_210"/></option>';
            res.forEach(function(node) {
                html += '<option value="' + node.addr + '" data-haspwdyn="' + node.hasPwdYn + '">' + node.addr + '</option>';
            });

            $("#balance").html(CME.fmt("0") + " " + $("#coinNm").val());
            $("#selectAddr").html(html);
        }
    </script>

</head>
<body>
<input id="recordTotal" type="hidden" value=""/>
<input id="pageNum" type="hidden" value="1"/>

<c:import url="/front.include.inc_header.dp/proc.go" charEncoding="utf-8" />
<main class="cd-main-content remittance">
    <div class="contents-wrap">
        <!-- 서브메뉴 네비게이션 -->
        <%--<div class="subTab">
            <ul>
                <li><a href="/front.wallet.sendListView.dp/proc.go" class="current">송금</a></li>
                <li><a href="/front.wallet.mnemonicView.dp/proc.go">Mnemonic 송금</a></li>
            </ul>
        </div>--%>

        <!-- 타이틀 -->
        <div class="tgroup">
            <h2><spring:message code="TEXT_06"/></h2>
            <ul>
                <li>Wallet</li>
                <li><spring:message code="TEXT_06"/></li>
            </ul>
        </div>

        <!-- 코인 & 지갑 선택 -->
        <div class="wal-opt-con">
            <div class="wallet-wrap">
                <div class="col">
                    <label>Cryptocurrencies</label>
                    <select id="coinNm" onChange="getWallet()">
                        <c:forEach var="item" items="${coinInfo}">
                            <option value="${item.coinNm}" data-cmt="${item.sendCmt}">${item.coinNm}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="col">
                    <label>Wallet</label>
                    <select id="selectAddr" onChange="getBalance()">
                        <option value="-1"><spring:message code="TEXT_210"/></option>
                        <c:forEach var="list" items="${walletList}">
                            <option value="${list.addr}" data-haspwdyn="${list.hasPwdYn}">${list.addr}</option>
                        </c:forEach>
                    </select>
                </div>
                <dl>
                    <dt>Balance</dt>
                    <dd><span id="panBalance">0.00000000 WS</span></dd><br/>
                    <dt class="cmtEthSecion">ETH Balance : </dt><dd class="cmtEthSecion"><span id="ethBalance">0.0000</span></dd>
                </dl>
            </div>

            <!-- 송금버튼 -->
            <a class="btn-period" onClick="goSendView()"><spring:message code="TEXT_137"/></a>
        </div>

        <!-- 송금내역 페이지네이션 -->
        <h3 class="subTtile"><spring:message code="TEXT_133"/></h3>
        <div class="t-controler">
            <a onClick="goPage('FIRST')" class="backward"><i class="xi-backward"></i></a>
            <a onClick="goPage('PREV')" class="prev"><i class="xi-play"></i></a>
            <input id="pageTxt" class="readonly" readonly style="width:calc(100% - 182px); text-align: center;" type="text" />
            <a onClick="goPage('NEXT')" class="next"><i class="xi-play"></i></a>
            <a onClick="goPage('LAST')" class="forward"><i class="xi-forward"></i></a>
        </div>

        <!-- 송금내역 테이블 -->
        <div class="table-wrap">
            <table class="normal-table">
                <colgroup>
                    <col width="50%" />
                    <col width="20%" />
                    <col width="15%" />
                    <col width="15%" />
                </colgroup>
                <thead>
                <tr>
                    <th>TxHash</th>
                    <th>Age</th>
                    <th>Value</th>
                    <th><spring:message code="TEXT_412"/></th>
                </tr>
                </thead>
                <tbody id="dataField">

                </tbody>
            </table>
        </div>
    </div>
</main>
<c:import url="/front.include.inc_footer.ds/proc.go" charEncoding="utf-8" />
</body>
</html>