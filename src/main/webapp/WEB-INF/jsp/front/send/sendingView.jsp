<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<c:import url="/front.include.common_front_head.dp/proc.go"	charEncoding="utf-8" />
    <script>
        function getBalance() {
            let addr = $("#selectAddr").val();

            if(addr == "-1") {
                $("#panBalance").html(CME.fmt("0") + " ${coinNm}");
                if("${coinNm}" == "WS") {
                    $("#ethBalance").html(CME.fmt(0));
                }
                $("#dataField").html("");
            } else {
                let data = {
                    "addr": addr,
                    "coinNm": '${coinNm}'
                }
                postLoadigAjax("/front.send.getBalance.dp/proc.go", data, getBalanceAfter, true, "");
            }
        }

        function getBalanceAfter(res) {
            $("#panBalance").html(CME.fmt(res.panBalance));
            $("#hiddenPanBalance").val(res.panBalance);
            if("${coinNm}" == "WS") {
                $("#ethBalance").html(CME.fmt(res.ethBalance));
                $("#hiddenEthBalance").val(res.ethBalance);
            }
        }

        function reqSend() {
            if($("#walletType").is(":checked")) {
                reqSendNext()
            } else if($("#phoneType").is(":checked")) {
                let isValid = isMobileNumber($("#toPhone").val().trim());

                if(!isValid) {
                    swal('<spring:message code="TEXT_422"/>');
                    return false;
                }

                let data = {
                    "hphone" : $("#toPhone").val().trim(),
                    "coinNm" : '${coinNm}'
                }

                postLoadigAjax("/front.send.findAddr.dp/proc.go", data, findAddr, true, "");
            }
        }

        function findAddr(res) {
            if(res.resultCode < -1) {
                swal(res.resultMsg);
                return false;
            } else if(res.resultCode == 1) {
                $("#toAddr").val(res.wallet[0].addr);
                reqSendNext()
            }
        }

        function isMobileNumber(phoneNum) {
            var regExp =/(01[016789])([1-9]{1}[0-9]{2,3})([0-9]{4})$/;
            var myArray;
            if(regExp.test(phoneNum)){
                return true;
            } else {
                return false;
            }
        }

        function reqSendNext() {
            let toAddr = $("#toAddr").val();
            let amount = $("#amount").val();
            let panBalance = $("#hiddenPanBalance").val();
            let ethBalance = $("#hiddenEthBalance").val();
            let sendPwd = $("#sendPwd").val();

            if(toAddr == "") {
                swal("<spring:message code='TEXT_148'/>");
                return false;
            }
            if(amount == "") {
                swal("<spring:message code='TEXT_149'/>")
                return false;
            }/* else if(parseFloat(amount) > parseFloat(panBalance)) {
                swal("<spring:message code='TEXT_150'/>")
                return false;
            }*/

            if(sendPwd == "") {
                swal("<spring:message code='TEXT_87'/>");
                return false;
            }

            swal({
                text : "<spring:message code='TEXT_151'/>",
                buttons: {
                    "<spring:message code='TEXT_318'/>": true,
                    cancel: "<spring:message code='TEXT_361'/>"
                }
            }).then(function(res) {
                if(res != null) {
                    postLoadigAjax("/front.include.getKey.dp/proc.go", null, sendStep, true, "");
                }
            });
        }

        function sendStep(res) {
            let amount = $("#amount").val();
            let toAddr = $("#toAddr").val();
            let sendPwd = $("#sendPwd").val();

            let randomKey = generateId(32);
            let RSAModulus = res.module;
            let RSAExponent = res.key;

            // rsa 암호화
            let rsa = EncryptRSA(RSAModulus,RSAExponent, randomKey);
            let _sendPwd = EncryptAES(sendPwd,randomKey);

            let data = {
                fromAddr    : $("#selectAddr").val(),
                toAddr      : toAddr,
                amount      : amount,
                aceKey	    : rsa,
                sendPwd     : _sendPwd.trim(),
                coinNm      : '${coinNm}'
            };

            let safeSendPwd = $("#safeSendPwd").val();

            if(safeSendPwd != undefined && safeSendPwd != "") {
                let safeRandomKey = generateId(32);
                let safeRsa = EncryptRSA(RSAModulus, RSAExponent, safeRandomKey);
                let _safeSendPwd = EncryptAES(safeSendPwd, safeRandomKey);

                data.safeSendPwd = _safeSendPwd;
                data.safeAceKey = safeRsa;
            }

            postLoadigAjax("/front.send.reqSend.dp/proc.go", data, reqSendAfter, true, "");
        }

        function reqSendAfter(res) {
            swal(res.resultMsg).then(function() {
                if(res.resultMsg == "1") {
                    location.reload();
                } else {
                    $("#sendPwd").val("");
                }
            });
        }

        function walletTypeClick() {
            $("#toAddr").show().val('');
            $("#toPhone").hide().val('');
        }

        function phoneTypeClick() {
            $("#toAddr").hide().val('');
            $("#toPhone").show().val('');
        }

        $(document).ready(function() {
            getBalance();
        })
    </script>
</head>
<body>
<input type="hidden" id="hiddenPanBalance"/>
<c:if test='${coinNm == "WS"}'>
<input type="hidden" id="hiddenEthBalance"/>
</c:if>

<c:import url="/front.include.inc_header.dp/proc.go" charEncoding="utf-8" />

<main class="cd-main-content remittance_write">
    <div class="contents-wrap">
        <div class="tgroup">
            <h2><spring:message code='TEXT_06'/></h2>
            <ul>
                <li>Wallet</li>
                <li><spring:message code='TEXT_06'/></li>
            </ul>
        </div>
        <div class="table-wrap">
            <table class="normal-table">
                <colgroup>
                    <col width="15%" />
                    <col width="85%" />
                </colgroup>
                <tbody>
                <tr>
                    <th>From</th>
                    <td>
                        <select id="selectAddr" onChange="getBalance()" style="width: 70%;">
                            <c:forEach var="list" items="${walletList}">
                                <option value="${list.addr}" <c:if test="${list.addr eq thisAddr}">selected="selected"</c:if>>${list.addr}</option>
                            </c:forEach>
                        </select>
                        <dl style="padding-bottom: 0px;">
                            <dt>${coinNm} Balance : </dt>
                            <dd><span id="panBalance">0.0000</span> ${coinNm}</dd>
                            <br/>
                            <c:if test='${coinNm == "WS"}'>
                            <dt>ETH Balance : </dt>
                            <dd><span id="ethBalance">0.0000</span> ETH</dd>
                            </c:if>
                        </dl>
                    </td>
                </tr>
                <tr>
                    <th>To</th>
                    <td>
                        <input onClick="walletTypeClick()" style="height:14px;" type="radio" id="walletType" name="toType" checked/><label for="walletType"><spring:message code="02_25"/></label>
                        <input onClick="phoneTypeClick()" style="height:14px;" type="radio" id="phoneType" name="toType"/><label for="phoneType"><spring:message code="TEXT_295"/></label>

                        <br/>

                        <input id="toAddr" type="text" style="width: 70%;" />
                        <input id="toPhone" type="number" style="width:70%;display:none" />
                    </td>
                </tr>
                <tr>
                    <th>Amount</th>
                    <td><input type="text" class="amount" id="amount" /> <strong>${coinNm}</strong></td>
                </tr>
                <tr>
                    <th>Commission</th>
                    <c:forEach var="item" items="${coinInfo}">
                        <c:if test='${item.coinNm eq coinNm}'>
                            <c:choose>
                                <c:when test='${coinNm == "WS"}'>
                                    <td id="commission">${item.sendCmt} ETH</td>
                                </c:when>
                                <c:otherwise>
                                    <td id="commission">${item.sendCmt} ${coinNm}</td>
                                </c:otherwise>
                            </c:choose>
                        </c:if>
                    </c:forEach>
                </tr>
                <tr>
                    <th><spring:message code="TEXT_145"/></th>
                    <td><input type="password" id="sendPwd" class="amount"></td>
                </tr>

                <tr>
                    <th><spring:message code="02_26"></spring:message></th>
                    <td>
                        <input type="password" id="safeSendPwd" class="amount" style="margin-bottom:5px;">
                        <br/>
                        <p><spring:message code="02_27"/></p>
                        <p><spring:message code="02_28"/></p>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
        <ul class="btn-wrap">
            <li><a class="btn-normal" href="/front.send.sendView.dp/proc.go">Cancel</a></li>
            <li><a class="btn-point popup01" onClick="reqSend()">Transfer</a></li>
        </ul>
    </div>
</main>
<c:import url="/front.include.inc_footer.ds/proc.go" charEncoding="utf-8" />
</body>
</html>