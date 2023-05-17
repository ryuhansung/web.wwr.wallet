<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<c:import url="/front.include.common_front_head.dp/proc.go"	charEncoding="utf-8" />
<script>
    $(document).ready(function () {
        mailCheck();
    });

    function mailCheck(){ // 메일 유효성 체크

        if ($("#userEmail").val() == "" || $("#userEmail").val() == null) {
            closeSite();
        }

        if ($("#sndCode").val() == "" || $("#sndCode").val() == null) {
            closeSite();
        }

        var data = {
            userEmail: $("#userEmail").val(),
            sndCode: $("#sndCode").val()
        }
        postAjax("/front.mail.mailCheck.dp/proc.go", data, mailCheckAfter);
    }

    function mailCheckAfter(data){
        if(data.resultCode > 0) {
        // 정상로직
        }else{
            swal(data.resultMsg).then(closeSite);
        }
    }
    function getApiKey(){
        postLoadigAjax("/front.include.getKey.dp/proc.go", null, changePwd , true,"");
    }

    function changePwd(data) { //비밀번호변경
        var RSAModulus = data.module;
        var RSAExponent = data.key;
        if ($("#userEmail").val() == "" || $("#userEmail").val() == null) {
            closeSite();
        }

        if ($("#new_password").val() == "" || $("#new_password").val() == null) {
            swal('<spring:message code="TEXT_320"/>');
            return;
        }

        if ($("#chk_password").val() == "" || $("#chk_password").val() == null) {
            swal('<spring:message code="TEXT_382"/>');
            return;
        }

        if (checkPasslevel($("#new_password").val()) != true) { //비밀번호체크로직
            swal(checkPasslevel($("#new_password").val()));
            return;
        }

        if ($("#new_password").val() != $("#chk_password").val()) {
            swal('<spring:message code="TEXT_382"/>');
            return;
        }


        var pw = $("#new_password").val();
        var randomKey = generateId(32);

        // rsa 암호화
        var res = EncryptRSA(RSAModulus,RSAExponent, randomKey);

        var enc_pwd = EncryptAES(pw,randomKey);

        var data = {
            userEmail   : $("#userEmail").val().trim(),
            userPwd     : enc_pwd.trim(),
            aceKey		:	res
        };

        postAjax("/front.mail.changePwd.dp/proc.go", data, changePwdAfter);
    }

    function changePwdAfter(data){ //비밀번호 변경완료
        swal(data.resultMsg).then(function() {
            closeSite();
        });
    }

    function closeSite(){ //브라우저 닫기
        if (navigator.appVersion.indexOf("MSIE 6.0") >= 0) {
            window.close();
        } else {
            window.open('about:blank', '_self').close();
        }
    }
</script>
</head>
<body>
<main class="cd-main-content sign_up">
    <div class="contents-wrap">
        <div class="short-wrap">
            <h2><spring:message code="TEXT_246"/></h2>
            <div class="form-box">
                <ul>
                    <li>
                        <span class="iconSet"><i class="xi-lock-o"></i></span>
                        <input type="password" id="new_password" class="iconInput" placeholder="<spring:message code="TEXT_43"/>" />
                    </li>
                    <li>
                        <span class="iconSet"><i class="xi-lock-o"></i></span>
                        <input type="password" id="chk_password" class="iconInput" placeholder="<spring:message code="TEXT_44"/>" />
                    </li>
                </ul>
            </div>
            <p class="desc-l"></p>
            <div class="bottom-btnWrap">
                <a href="javascript:getApiKey();" class="btn-primary"><spring:message code="TEXT_318"/></a>
            </div>
        </div>
    </div>
</main>
<input type="hidden" value="${param.sndCode}" name="sndCode" id="sndCode">
<input type="hidden" value="${param.userEmail}" name="userEmail" id="userEmail">
<input type="hidden" id="ace_key" name="ace_key">

</body>
</html>