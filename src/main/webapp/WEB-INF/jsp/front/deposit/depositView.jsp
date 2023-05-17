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
/*        $(".cmtEthSecion").hide();*/

        function getBalance() {
            let coinCd = $("#selectAddr").val();

            <%-- 초기화 --%>
            list = new Object();
            list.number = new Array();
            count = 0;

            if(coinCd == "-1") {
                $("#balance").html(CME.numComma(0));
                $("#dataField").html("");
            } else {
                $("#cnKndNm").val($("#coinNm"+coinCd).val());
                $("#cnAddr").val($("#coinAddr"+coinCd).val());

                let data = {
                    "addr": $("#cnAddr").val(),
                    "coinNm": $("#coinNm"+coinCd).val(),
                    "coinType":$("#coinType"+coinCd).val(),
                    "cnKndCd" :coinCd
                }
                postLoadigAjax("/front.send.getBalance.dp/proc.go", data, getBalanceAfter, true, "");
            }
        }

        function getBalanceAfter(res) {
            $("#balanceVal").html(CME.fmt(res.balanceVal) + " " + $("#cnKndNm").val());
            //$("#ethBalance").html(CME.fmt(res.ethBalance) + " ETH");

            let html = "";
            let addr = $("#cnAddr").val();
            let pageNum = $("#pageNum").val();
<%--
            res.sendList.list.data.forEach(function(node, i) {
                if(node[2].toUpperCase() != addr.toUpperCase()) {
                    list.number[count]= node;
                    count++
                }
            }); --%>

            if(res.sendList.list == undefined || res.sendList.list =='undefined'){
                return false;
            }
            res.sendList.list.forEach(function(node, i){
                list.number[count]= node;
                count++
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
                    html += "<td>" + list.number[i].A + "</td>";
                    html += "<td>" + list.number[i].B + "</td>";
                    html += "<td>" + list.number[i].C + "</td>";
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
                    html += "<td>" + list.number[i].A + "</td>";
                    html += "<td>" + list.number[i].B + "</td>";
                    html += "<td>" + list.number[i].C + "</td>";
                    html += "</tr>";
                }
            }

            $("#dataField").html(html);
            $("#pageTxt").val("Page " + pageNum + " of " + pageSize);
        }

        function addrCopyUrl(){ //주소복사
            let _cnKndCd = $("#selectAddr").val();
            if(_cnKndCd == '-1'){
                swal('<spring:message code="TEXT_210_2"/>');
                return false;
            }
            let addr = $("#coinAddr" + _cnKndCd).val();
            var $temp = $("<input>");
            $("body").append($temp);
            $temp.val(addr).select();
            document.execCommand("copy");
            $temp.remove();
            swal('<spring:message code="TEXT_387"/>');
        }

        function getWallet() {
            <%--
            if($("#coinNm").val() == "WS") {
                $(".cmtEthSecion").show();
            } else {
                $(".cmtEthSecion").hide();
            }--%>
            let data = {
                coinNm : $("#coinNm").val()
            }

            //$("#balanceVal").html(CME.fmt("0") + " " + $("#coinNm").val());
            //$("#ethBalance").html(CME.fmt("0") + " ETH");
            $("#selectAddr").html('');
            $("#dataField").html('');

            postLoadigAjax("/front.send.getWalletList.dp/proc.go", data, getWalletAfter, true, "");
        }

        function getWalletAfter(res) {
            let html = "";
            html += '<option value="-1"><spring:message code="TEXT_210"/></option>';
            res.forEach(function(node) {
                html += '<option value="' + node.addr + '">' + node.addr + '</option>';
            });

            $("#balance").html(CME.fmt("0") + " " + $("#coinNm").val());
            $("#selectAddr").html(html);
        }
    </script>
</head>
<body>
    <input id="recordTotal" type="hidden" value=""/>
    <input id="pageNum" type="hidden" value="1"/>
    <input id="cnKndNm" type="hidden"/>
    <input id="cnAddr" type="hidden"/>

<c:import url="/front.include.inc_header.dp/proc.go" charEncoding="utf-8" />
<main class="cd-main-content deposit">
    <div class="contents-wrap">

        <!-- 타이틀 -->
        <div class="tgroup">
            <h2><spring:message code="TEXT_05"/></h2>
            <ul>
                <li>Wallet</li>
                <li><spring:message code="TEXT_05"/></li>
            </ul>
        </div>

        <!-- 코인 & 지갑 선택 -->
        <div class="wallet-wrap">
<%--            <div class="col">
                <label><spring:message code="02_36"/></label>
                <select id="coinNm" onChange="getWallet()">
                    <option value="-1"><spring:message code="TEXT_210_1"/></option>
                    <c:forEach var="item" items="${coinInfo}">
                        <option value="${item.coinNm}">${item.coinNm}</option>
                    </c:forEach>
                </select>
            </div>--%>
            <div class="col">
                <label>Wallet</label>
                <select id="selectAddr" onChange="getBalance()">
                    <option value="-1"><spring:message code="TEXT_210"/></option>
                    <c:forEach var="list" items="${walletList}">
                        <option value="${list.cnKndCd}">[${list.coinNm}]&nbsp;&nbsp;&nbsp;&nbsp;${list.addr}</option>
                    </c:forEach>
                </select>
                <c:forEach var="list" items="${walletList}">
                    <input type="hidden" id="coinType${list.cnKndCd}" value="${list.coinType}"/>
                    <input type="hidden" id="coinNm${list.cnKndCd}" value="${list.coinNm}"/>
                    <input type="hidden" id="coinAddr${list.cnKndCd}" value="${list.addr}"/>
                </c:forEach>
            </div>
            <div style="margin-top:10px;"><button class="btn" onclick="addrCopyUrl();">COPY ADDRESS</button></div>
            <dl>
                <dt>Balance</dt>
                <dd><span id="balanceVal"><spring:message code="TEXT_210_2"/></span></dd>
<%--                <br/>--%>
<%--                <dt class="cmtEthSecion">ETH Balance : </dt><dd class="cmtEthSecion"><span id="ethBalance">0.00000000 ETH</span></dd>--%>
            </dl>
        </div>

        <!-- 입금버튼 -->
        <h3 class="subTtile"><spring:message code="TEXT_132"/></h3>

        <!-- 입금내역 페이지네이션 -->
        <div class="t-controler">
            <a onClick="goPage('FIRST')" class="backward"><i class="xi-backward"></i></a>
            <a onClick="goPage('PREV')" class="prev"><i class="xi-play"></i></a>
            <input id="pageTxt" class="readonly" style="width:calc(100% - 182px); text-align: center; " type="text" readonly />
            <a onClick="goPage('NEXT')" class="next"><i class="xi-play"></i></a>
            <a onClick="goPage('LAST')" class="forward"><i class="xi-forward"></i></a>
        </div>

        <!-- 입금내역 테이블 -->
        <div class="table-wrap">
            <table class="normal-table">
                <colgroup>
                    <col width="60%" />
                    <col width="25%" />
                    <col width="15%" />
                </colgroup>
                <thead>
                <tr>
                    <th>TxHash</th>
                    <th>Age</th>
                    <th>Value</th>
                </tr>
                </thead>
                <tbody id="dataField">
                    <tr>
                        <td colspan="3"><spring:message code="TEXT_210_3"/></td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
</main>
<c:import url="/front.include.inc_footer.ds/proc.go" charEncoding="utf-8" />
</body>
</html>