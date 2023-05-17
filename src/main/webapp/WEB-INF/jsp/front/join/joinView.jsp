<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <c:import url="/front.include.common_front_head.dp/proc.go"	charEncoding="utf-8" />
</head>
<script type="text/javascript">

    /* 이메일 중복체크 */
    function mailOverlap(){
        var textCk = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;
        if($("#userEmail").val() == null || $("#userEmail").val() == ''){
            swal('<spring:message code="TEXT_239"/>');
            return;
        }

        if(textCk.test($("#userEmail").val())==false && $("#userEmail").val() !=''){
            swal('<spring:message code="TEXT_241"/>');
            return;
        }
        var data = {
            userEmail : $("#userEmail").val().trim()
        };
        postAjax("/front.join.mailOverlap.dp/proc.go", data, mailOverAfter);
    }

    /* 이메일 중복체크 완료 */
    function mailOverAfter(data){
        swal(data.resultMsg);

        if (data.resultCode > 0) {
            $('#userEmail').attr("readonly", true) ;
            $("#mailOverOk").val("Y");
            $("#mailArea").html('<a href="javascript:resetMail();" class="btn-idCheck chk_overlap"><spring:message code="TEXT_329"/></a>');
        } else {
            $("#userEmail").val("");
        }
    }

    /* 이메일 중복체크 재설정*/
    function resetMail(){
        $('#userEmail').attr("readonly", false).val("") ;
        $("#mailOverOk").val("N");
        $("#mailArea").html('<a href="javascript:mailOverlap();" class="btn-idCheck chk_overlap"><spring:message code="TEXT_42"/></a>');
    }

    /* 인증메일 발송 */
    function sendOkMail(){ //인증메일 발송
        if($("#userEmail").val() == null || $("#userEmail") == ''){
            swal('<spring:message code="TEXT_239"/>');
            return;
        }

        if($("#mailOverOk").val() != 'Y'){
            swal('<spring:message code="TEXT_380"/>');
            return;
        }

        var data = {
            userEmail : $("#userEmail").val().trim()
        };

        postLoadigAjax("/front.join.sendOkMail.dp/proc.go", data, mailFind);
    }


    /* 인증메일 발송 완료 */
    function mailFind(data){
        swal(data.resultMsg);

        if(data.resultCode  < 0){
            $("#mainSnd").val('N');
        } else{
            $("#mainSnd").val('S');
        }
    }

    /* 메일 에서 확인후 인증하기 버튼*/

    function mailEnd(){
        if($("#userEmail").val() == null || $("#userEmail") == ''){
            swal('<spring:message code="TEXT_30"/>');
            return;
        }

        if($("#mainSnd").val() == "" || $("#mainSnd").val() == "N"){
            swal('<spring:message code="TEXT_381"/>');
            return;
        }

        var data = {
            userEmail : $("#userEmail").val().trim()
        };

        postAjax("/front.join.mailAuthEnd.dp/proc.go", data, mailAuthFin);
    }


    /* 메일인증완료후 */
    function mailAuthFin(data){
        if(data.resultCode < 0){
            swal(data.resultMsg);
            return;
        }else{
            $("#mainSnd").val('Y');
            swal(data.resultMsg);
        }
    }


    /* RSA 키 가져오기 */
    function getApiKey() {
        postLoadigAjax("/front.include.getKey.dp/proc.go", null, oneStep , true,"");
    }

    /* 회원가입 */
    function oneStep(res){
        var textCk = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;

        var nameCk = /^[가-힣|a-z|A-Z]+$/;

        if(null == $("#userEmail").val() || "" == $("#userEmail").val()){
            swal('<spring:message code="TEXT_30"/>');
            return;
        }

        if(false == textCk.test($("#userEmail").val()) && "" != $("#userEmail").val()){
            swal('<spring:message code="TEXT_33"/>');
            return;
        }

        if(null == $("#newPwd").val() || "" == $("#newPwd").val()){
            swal('<spring:message code="TEXT_31"/>');
            return;
        }

        if(null == $("#chkPwd").val() || "" == $("#chkPwd").val()){
            swal('<spring:message code="TEXT_382"/>');
            return;
        }

        if (true != checkPasslevel($("#newPwd").val())) { //비밀번호체크로직
            swal(checkPasslevel($("#newPwd").val()));
            return;
        }

        if ($("#newPwd").val() != $("#chkPwd").val()) { //두개가 같지 않으면
            swal('<spring:message code="TEXT_382"/>');
            return;
        }

        if(null == $("#mainSnd").val() || 'Y' != $("#mainSnd").val()){
            swal('<spring:message code="TEXT_383"/>');
            return;
        }

        if('Y' != $("#mailOverOk").val()){
            swal('<spring:message code="TEXT_380"/>');
            return;
        }

        if(null == $("#userNm").val() || "" == $("#userNm").val()){
            swal('<spring:message code='TEXT_69'/>');
            return;
        }

        if(false == nameCk.test($("#userNm").val())){
            swal('<spring:message code='02_10'/>');
            return;
        }
        if(null == $("#hphone").val() || "" == $("#hphone").val()){
            swal('<spring:message code='TEXT_240'/>');
            return;
        }

        if(true != $('input:checkbox[id="eyongChk"]').is(":checked")){
            swal('<spring:message code="TEXT_52"/>');
            return;
        }

        var pw2 = $("#newPwd").val();
        var randomKey = generateId(32);
        var RSAModulus = res.module;
        var RSAExponent = res.key;

        // rsa 암호화
        let rsa = EncryptRSA(RSAModulus,RSAExponent, randomKey);
        let newPwd = EncryptAES(pw2,randomKey);

        var data = {
            userEmail   : $("#userEmail").val().trim(),
            userPwd     : newPwd.trim(),
            userNm      : $("#userNm").val().trim(),
            hphone      : $("#hphone").val().trim(),
            aceKey		: rsa
        };
        postAjax("/front.join.userInsert.dp/proc.go", data, infoAfter);
    }

    function infoAfter(data){ //회원가입 완료후
        if(data.resultCode < 0){
            swal(data.resultMsg);
            return;
        }else{
            $(".step01").hide();
            $(".step04").show();
        }
    }
    function phoneOverlap() {

        let isValid = isMobileNumber($("#hphone").val().trim());

        if(!isValid) {
            swal('<spring:message code="TEXT_422"/>');
            return false;
        }
        // 다날팝업창 출력 후 이름,전화번호 수정불가 처리
       /* $('#hphone').attr("readonly", true);
        $('#userNm').attr("readonly", true);*/

        let data = {
            "userEmail" : $("#userEmail").val().trim(),
            "userNm" : $("#userNm").val().trim(),
            "hphone" : $("#hphone").val().trim(),
            "modify" : "N"
        };

        postAjax("/front.join.mobileAuth.dp/proc.go", data, phoneOverlapAfter);
    }

    function phoneOverlapAfter(res) {

        if(res.resultCode == -1) {
            $('#hphone').removeAttr("readonly");
            $('#userNm').attr("readonly", false);
            $("#hphone").val("");
            swal(res.resultMsg);
            return false;
        }else{ <%-- 다날 패싱 --%>
            $("#hphone").attr("readonly", "readonly");
            $("#userNm").attr("readonly", "readonly");
            swal(res.resultMsg);
            return true;
        }


        <%--
        다날 인증 패싱
        if(CME.PAGE_DATA.POP) {
            CME.PAGE_DATA.POP.close();
            CME.PAGE_DATA.POP = null;
        }
        CME.PAGE_DATA.POP = window.open("", "Ready", "toolbar=no,top=10,left700,width=600,height=500,scrollbars=no,resizeable=no,menubar=no,location=no");
        var $form = $("<form></form>");
        $form.attr("action", "https://wauth.teledit.com/Danal/WebAuth/Web/Start.php");
        $form.attr("method", "POST");
        $form.attr("target", "Ready");
        $form.appendTo("body");
        $form.append(res.form1).append(res.form2).submit();
        --%>
    }

    function receiveInfo(userMobile,userName) {
        $("#hphone").val(userMobile).attr("readonly", "readonly");
        $("#userNm").val(userName).attr("readonly", "readonly");
        return true;
    }

    function failInfo(){
        $("#hphone").attr("readonly", false);
        $("#userNm").attr("readonly", false);
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



</script>
<body class="${lang}">
<c:import url="/front.include.inc_header.dp/proc.go" charEncoding="utf-8" />
<main class="cd-main-content sign_up">
    <div class="contents-wrap">
        <form action="POST" name="authForm" id="authForm">
            <input type="hidden" name="mailOverOk" id="mailOverOk"/>
            <input type="hidden" name="mainSnd" id="mainSnd"/>
            <input type="hidden" name="smsSnd" id="smsSnd"/>
            <!-- STEP01 일반사용자-->
            <div class="short-wrap step01 user">
                <h2><spring:message code="TEXT_308"/></h2>
                <div class="form-box">
                    <ul>
                        <li class="idCheck">
                            <span class="iconSet"><i class="xi-mail-o"></i></span>
                            <input type="email" id="userEmail" maxlength="50"  class="iconInput" placeholder="Email" />
                            <p id="mailArea">
                                <a href="javascript:mailOverlap();" class="btn-idCheck" ><spring:message code="TEXT_318"/></a>
                            </p>
                        </li>
                    </ul>
                </div>

                <ul class="dotStyle">
                    <li><spring:message code="TEXT_38"/></li>
                    <li><spring:message code="TEXT_39"/></li>
                    <li><spring:message code="TEXT_40"/></li>
                </ul>
                <ul class="btnWrap">
                    <li><a onclick="sendOkMail();"><span><spring:message code="TEXT_41"/></span></a></li>
                    <li><a onclick="mailEnd();" ><span><spring:message code="TEXT_42"/></span></a></li>
                </ul>

                <div class="form-box">
                    <ul>
                        <li>
                            <span class="iconSet"><i class="xi-lock-o"></i></span>
                            <input type="password" id="newPwd" maxlength="12" class="iconInput" placeholder='<spring:message code="TEXT_25"/>'/>
                        </li>
                        <li>
                            <span class="iconSet"><i class="xi-lock-o"></i></span>
                            <input type="password"  id="chkPwd" maxlength="12"  class="iconInput" placeholder='<spring:message code="TEXT_44"/>' />
                        </li>
                    </ul>
                </div>


                <ul class="dotStyle">
                    <li><spring:message code="TEXT_67"/></li>
                </ul>

                <div class="form-box">
                    <ul>
                        <li>
                            <span class="iconSet"><i class="xi-user-o"></i></span>
                            <input type="text" id="userNm" maxlength="30" class="iconInput" placeholder='<spring:message code="02_09"/>'/>
                        </li>

                    </ul>
                </div>

                <div class="form-box">
                    <ul>
                        <li class="idCheck">
                            <span class="iconSet"><i class="xi-mobile"></i></span>
                            <input type="text" id="hphone" maxlength="11" class="iconInput" placeholder="<spring:message code='TEXT_237'/>" onkeydown="onlyNumber(this)" />
                            <p id="">
                                <a href="javascript:phoneOverlap();" class="btn-idCheck" ><spring:message code="TEXT_318"/></a>
                            </p>
                        </li>
                    </ul>
                </div>

                <div class="agreement-wrap">
                    <textarea rows="3" readonly><spring:message code="02_13"/>
                    <spring:message code="02_14"/></textarea>
                    <input type="checkbox" id="eyongChk" class="iCheck" /><label for="eyongChk"><spring:message code="TEXT_46"/></label>
                </div>
                <div class="bottom-btnWrap">
                    <a href="#n" onclick="getApiKey();" class="btn-primary"><spring:message code="TEXT_308"/></a>
                </div>
            </div>
        </form>

        <div class="short-wrap complet step04" style="display:none">
            <h2><spring:message code="TEXT_308"/></h2>
            <img src="/images/front/common/img_sign_up.png" alt="" class="icon_completion" />
            <em class="completion"><strong>RNR Wallet</strong> <spring:message code="TEXT_78"/></em>
            <!--<p class="desc">회원가입을 진심으로 환영합니다.</p>-->
            <div class="bottom-btnWrap">
                <a href="/front.mypage.myInfoView.dp/proc.go" class="btn-primary"><spring:message code="TEXT_318"/></a>
            </div>
        </div>
    </div>
</main>
<c:import url="/front.include.inc_footer.ds/proc.go" charEncoding="utf-8" />
</body>
</html>