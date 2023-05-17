<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<c:import url="/front.include.common_front_head.dp/proc.go"	charEncoding="utf-8" />
<script type="text/javascript" src="/js/simplePassChk.js"></script>
<script>
    $(document).ready(function () {
        getWallet();
    });

    function getWallet(){ //지갑주소 가져오기
        postAjax("/front.wallet.getUserWallet.dp/proc.go", null, getWalletAfter);
    }

    function getWalletAfter(data){ //지갑주소 가져오기 완료
        var html = "";

        data.list.forEach(function (node) {

            //let coinNm = node.coinNm + node.coinType;
            let coinCd = node.cnKndCd;
            console.log("###" + coinCd);
            if($("#stage" + coinCd).data("exist") == "true") {
                return false;
            }

            $("#outLink" + coinCd).html(node.addr);
            $("#outLink" + coinCd).off("click").removeAttr("onClick");
            $("#copy" + coinCd).append('<a href="javascript:copyUrl(\''+node.addr+'\');" class="txt-copy">COPY</a>');
            if(node.sendPwd == "") {
                $("#copy" + coinCd).append('<a class="pw-change" href="javascript:popSettingPwd(\'' + node.addr + '\', \''+ node.cnKndCd +'\')"><spring:message code="TEXT_103"/></a>');
            } else {
                $("#copy" + coinCd).append('<a class="pw-change" href="javascript:popChangePwd(\'' + node.addr + '\', \''+ node.cnKndCd +'\')"><spring:message code="TEXT_109"/></a>');
            }
            $("#balance" + coinCd).html(CME.fmt(node.balanceVal) + " " + node.coinNm);
            $("#stage" + coinCd).append("<div class='qr-code'><p id='qr_"+ node.addr +"_"+ coinCd +"' class='qrView'></p></div>");
            $("#stage" + coinCd).data("exist", "true");

            let qrTxt = "qr_"+ node.addr;
            qrTxt = qrTxt.replaceAll('qr_' , '');
            makeQrCode(qrTxt, coinCd);
        });

        $(".stage").show();
    }

    function insWallet(cnKndCd, coinNm, coinType, tokenYn){ //전자지갑생성
        let data = {
            "cnKndCd": cnKndCd,
            "coinNm" : coinNm,
            "userEmail" : $("#userEmail").val(),
            "coinType" : coinType,
            "tokenYn" : tokenYn
        }
        postAjax("/front.wallet.createWallet.dp/proc.go", data, insWalletAfter);
    }

    function insWalletAfter(data){ //전자지갑 생성완료
        if(data.resultCode > 0) {
            //swal('<spring:message code="TEXT_385"/>');
            swal('<spring:message code="TEXT_385"/>').then(getWallet);
        }else{
            swal('<spring:message code="TEXT_386"/>');
        }
    }

    function copyUrl(addr){ //주소복사
        var $temp = $("<input>");
        $("body").append($temp);
        $temp.val(addr).select();
        document.execCommand("copy");
        $temp.remove();
        swal('<spring:message code="TEXT_387"/>');
    }

    function makeQrCode(str, coinCd){
        let qrcode = new QRCode("qr_"+ str + "_" + coinCd, {
            width : 100,
            height : 100
        });
        if(str == null || str ==''){
            return;
        }
        qrcode.makeCode(str);
    }

    function popSettingPwd(addr, coinCd) {
        $("#hiddenCoinCd").val(coinCd);
        $("#popSettingPwd").addClass('is-visible');
        $("#settingAddr").html(addr);
        $("#hiddenSettingAddr").val(addr);
    }

    function popChangePwd(addr, coinCd) {
        $("#hiddenCoinCd").val(coinCd);
        $("#popChangePwd").addClass('is-visible');
        $("#changeAddr").html(addr);
        $("#hiddenChangeAddr").val(addr);
    }

    function getApiKey(type) {
        if(type == "set") {

            if ($("#settingPwd1").val() == "" || $("#settingPwd2").val() == null) {
                swal('<spring:message code="TEXT_87"/>');
                return ;
            }else if (true != simplePassChk($("#settingPwd2").val())) {
                swal(simplePassChk($("#settingPwd2").val()));
                $("#settingPwd1").val('');
                $("#settingPwd2").val('');
                return ;
            } else if($("#settingPwd1").val() != $("#settingPwd2").val()) {
                swal('<spring:message code="TEXT_88"/>');
                return ;
            }

            postLoadigAjax("/front.include.getKey.dp/proc.go", null, settingStep, true, "");
        } else if(type == "change") {

            if($("#currentPwd").val() == "" || $("#currentPwd").val() == null ) {
                swal('<spring:message code="TEXT_118"/>');
                return ;
            }

            if ($("#changePwd1").val() == "" || $("#changePwd1").val() == null) {
                swal('<spring:message code="TEXT_119"/>');
                return ;
            } else if ($("#changePwd2").val() == "" || $("#changePwd2").val() == null) {
                swal('<spring:message code="TEXT_120"/>');
                return ;
            } else if (true != simplePassChk($("#changePwd2").val())) {
                swal(simplePassChk($("#changePwd2").val()));
                $("#changePwd1").val('');
                $("#changePwd2").val('');
                return ;
            } else if($("#changePwd1").val() != $("#changePwd2").val()) {
                swal('<spring:message code="TEXT_88"/>');
                return ;
            }

            postLoadigAjax("/front.include.getKey.dp/proc.go", null, changeStep, true, "");
        }

    }

    function settingStep(res) {
        let pw2 = $("#settingPwd2").val();
        let addr = $("#hiddenSettingAddr").val();
        let randomKey = generateId(32);
        let RSAModulus = res.module;
        let RSAExponent = res.key;

        // rsa 암호화
        let rsa = EncryptRSA(RSAModulus,RSAExponent, randomKey);
        let newPwd = EncryptAES(pw2,randomKey);

        let data = {
            addr      : addr.trim(),
            cnKndCd    : $("#hiddenCoinCd").val(),
            sendPwd   : newPwd.trim(),
            aceKey	  :	rsa
        };

        postLoadigAjax("/front.wallet.settingPwd.dp/proc.go", data, settingPwdAfter, true, "");
    }

    function settingPwdAfter(res) {
        $("#popSettingPwd").removeClass('is-visible');
        swal(res.resultMsg).then(function(){location.reload()});
    }

    function changeStep(res) {
        let curPwd = $("#currentPwd").val();
        let pw2 = $("#changePwd2").val();
        let addr = $("#hiddenChangeAddr").val();
        let randomKey = generateId(32);
        let RSAModulus = res.module;
        let RSAExponent = res.key;

        // rsa 암호화
        let rsa = EncryptRSA(RSAModulus,RSAExponent, randomKey);
        let _curPwd = EncryptAES(curPwd,randomKey);
        let _newPwd = EncryptAES(pw2,randomKey);

        let data = {
            cnKndCd    : $("#hiddenCoinCd").val(),
            curPwd    : _curPwd.trim(),
            addr      : addr.trim(),
            sendPwd   : _newPwd.trim(),
            aceKey	  :	rsa
        };

        postLoadigAjax("/front.wallet.changePwd.dp/proc.go", data, changePwdAfter, true, "");
    }

    function changePwdAfter(res) {
        if(res.resultCode == "1") {
            $("#popChangePwd").removeClass('is-visible');
            swal(res.resultMsg).then(function(){location.reload()});
        } else {
            swal(res.resultMsg).then(function() {
                $("#currentPwd").val('');
                $("#changePwd1").val('');
                $("#changePwd2").val('');
            });
        }

    }

</script>
</head>
<body>
<input type="hidden" id="hiddenCoinCd"/>
<input type="hidden" id="userEmail" value="${sUserId}"/>
<c:import url="/front.include.inc_header.dp/proc.go" charEncoding="utf-8" />

<main class="cd-main-content wallet">
    <div class="contents-wrap">
        <div class="tgroup">
            <h2><spring:message code="TEXT_04"/></h2>
            <ul>
                <li>Wallet</li>
                <li><spring:message code="TEXT_04"/></li>
            </ul>
        </div>
        <div class="inner" id="walletMngBody">
            <c:forEach var="item" items="${coinList}">
                <div class='stage' id="stage${item.cnKndCd}" style="display:none">
                    <div class='info-wrap'>
                        <dl>
                            <dt>${item.coinNm} Address</dt>
                            <dd>
                                <a style="max-width: 35rem;" onClick="javascript:insWallet('${item.cnKndCd}','${item.coinNm}', '${item.coinType}','${item.tokenYn}')" class='out-link' id='outLink${item.cnKndCd}'><spring:message code="TEXT_417"/></a>
                                <a class='out-link' id=''></a>
                                <span id="copy${item.cnKndCd}"></span>
                            </dd>
                            <dt>Balance</dt>
                            <dd id="balance${item.cnKndCd}">0.00000000 ${item.coinNm}</dd>
                        </dl>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</main>

<!-- popup 출금암호 등록 -->
<div class="cd-popup" role="alert" id="popSettingPwd">
    <input type="hidden" id="hiddenSettingAddr"/>
    <div class="cd-popup-container">
        <div class="cd-popup-mid">
            <h3><spring:message code="TEXT_113"/></h3>
            <p class="desc"><spring:message code="TEXT_80"/><br><spring:message code="TEXT_419"/></p>
            <dl class="cage">
                <dt><spring:message code="TEXT_210"/></dt>
                <dd id="settingAddr"></dd>
            </dl>
            <div class="form-wrap">
                <h6><spring:message code="TEXT_84"/></h6>
                <input id="settingPwd1" type="password" maxlength="6"/>
                <h6><spring:message code="TEXT_85"/></h6>
                <input id="settingPwd2" type="password" maxlength="6"/>
            </div>
            <p class="caution"><spring:message code="TEXT_86"/></p>
            <ul class="cd-buttons">
                <li><a class="btnPoint" href="javascript:getApiKey('set')"><spring:message code="TEXT_318"/></a></li>
            </ul>
        </div>
        <a class="cd-popup-close img-replace"></a>
    </div>
</div>

<!-- popup 출금암호 변경 -->
<div class="cd-popup" role="alert" id="popChangePwd">
    <input type="hidden" id="hiddenChangeAddr"/>
    <div class="cd-popup-container">
        <div class="cd-popup-mid">
            <h3><spring:message code="TEXT_114"/></h3>
            <p class="desc"><spring:message code="TEXT_80"/><br><spring:message code="TEXT_419"/></p>
            <%--<dl class="cage">
                <dt>USB장치ID번호</dt>
                <dd>D121sdw12</dd>
            </dl>--%>
            <dl class="cage">
                <dt><spring:message code="TEXT_83"/></dt>
                <dd id="changeAddr"></dd>
            </dl>
            <div class="form-wrap">
                <h6><spring:message code="TEXT_115"/></h6>
                <input id="currentPwd" type="password" />
                <h6><spring:message code="TEXT_116"/></h6>
                <input id="changePwd1" type="password" maxlength="6"/>
                <h6><spring:message code="TEXT_117"/></h6>
                <input id="changePwd2" type="password" maxlength="6"/>
            </div>
            <p class="caution"><spring:message code="TEXT_86"/></p>
            <ul class="cd-buttons">
                <li><a class="btnPoint" href="javascript:getApiKey('change')"><spring:message code="TEXT_318"/></a></li>
            </ul>
        </div>
        <a class="cd-popup-close img-replace"></a>
    </div>
</div>

<c:import url="/front.include.inc_footer.ds/proc.go" charEncoding="utf-8" />
</body>
</html>