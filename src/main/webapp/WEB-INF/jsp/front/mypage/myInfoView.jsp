<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<c:import url="/front.include.common_front_head.dp/proc.go"	charEncoding="utf-8" />
</head>
<script>
    function resetPw(){
        swal('<spring:message code="TEXT_29"/>').then(pwMailSend);
    }

    function pwMailSend(){
        postLoadigAjax("/front.mail.pwMailSend.dp/proc.go", null, pwMailSendAfter);
    }

    function pwMailSendAfter(data){
        if(data.resultCode > 0) {
            swal('<spring:message code="TEXT_379"/>').then(logout);
        }else{
            swal(data.resultMsg);
        }
    }

    function setMobile() {
        $("#mobileSection").html('<input type="number" id="hphone" maxlength="12" class="iconInput" placeholder="<spring:message code="TEXT_295"/>" onkeydown="onlyNumber(this)"/><a onClick="phoneOverlap()" style="margin-left:10px;"><spring:message code="02_08"/></a>');
    }

    function phoneOverlap() {

        let isValid = isMobileNumber($("#hphone").val().trim());

        if(!isValid) {
            swal('<spring:message code="TEXT_422"/>');
            return false;
        }

        /*$('#hphone').attr("readonly", true);*/

        var userNm = "";
        if('${userInfo.userNm}' != ''){
            userNm = $("#userNm").text().trim();
        }
        let data = {
            "hphone" : $("#hphone").val().trim(),
            "userNm" : userNm,
            "modify" : "Y"
        };

        postAjax("/front.join.mobileAuth.dp/proc.go", data, phoneOverlapAfter);
    }

    function phoneOverlapAfter(res) {

        if(res.resultCode == -1) {
            $('#hphone').attr("readonly", false) ;
            $("#hphone").val("");
            swal(res.resultMsg);
            return false;
        }

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
    }

    <%-- 다날 성공시 --%>
    function receiveInfo(userMobile,userName) {
        $("#hphone").val(userMobile).attr("readonly", "readonly");
        location.reload();
        return true;
    }
    <%-- 다날 실패시 --%>
    function failInfo(){
        $("#hphone").attr("readonly", false);
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

    // type : new, reset
    function showSendPwdPop(type) {
        if(type == "new") {
            $("#sendPass0").hide()

        } else if(type == "reset"){
            $("#sendPass0").show()
        }

        $(".popupCon02").addClass("is-visible").data("type", type);
    }

    // type : new, reset
    function setSendPwd() {
        let type = $(".popupCon02").data("type");

        if(type == "reset" && $("#sendPass0").val() == "") {
            swal('<spring:message code="TEXT_118"/>');
            return false;
        }

        if(null == $("#sendPass1").val() || "" == $("#sendPass1").val()){
            swal('<spring:message code="TEXT_31"/>');
            return;
        }

        if(null == $("#sendPass2").val() || "" == $("#sendPass2").val()){
            swal('<spring:message code="TEXT_382"/>');
            return;
        }

        if (true != checkPasslevel($("#sendPass1").val())) { //비밀번호체크로직
            swal(checkPasslevel($("#newPwd").val()));
            return;
        }

        if ($("#sendPass1").val() != $("#sendPass2").val()) { //두개가 같지 않으면
            swal('<spring:message code="TEXT_382"/>');
            return;
        }

        postLoadigAjax("/front.include.getKey.dp/proc.go", null, getApiKeyAfter , true,"");
    }

    function getApiKeyAfter(res) {
        let RSAModulus = res.module;
        let RSAExponent = res.key;

        let newpw = $("#sendPass1").val();
        let randomKey = generateId(32);
        let new_rsa = EncryptRSA(RSAModulus,RSAExponent, randomKey);
        let enc_new_pwd = EncryptAES(newpw, randomKey);

        let data = {
            setPwdType : $(".popupCon02").data("type"),
            safeNewSendPwd : enc_new_pwd.trim(),
            aceKey : new_rsa,
        };

        if($(".popupCon02").data("type") == "reset") {
            let oldpw = $("#sendPass0").val();
            let oldRandomKey = generateId(32);
            let old_rsa = EncryptRSA(RSAModulus,RSAExponent, oldRandomKey);
            let enc_old_pwd = EncryptAES(oldpw, oldRandomKey);
            data.safeOldSendPwd = enc_old_pwd.trim();
            data.oldAceKey = old_rsa
        }


        postLoadigAjax("/front.mypage.setSendPwd.dp/proc.go", data, setSendPwdAfter);
    }

    function setSendPwdAfter(res) {
        if(res.resultCode < 0) {
            swal(res.resultMsg);
            return false;
        }
        swal(res.resultMsg).then(function() {
            location.reload();
        });


    }
</script>
<body>
<c:import url="/front.include.inc_header.dp/proc.go" charEncoding="utf-8" />

<main class="cd-main-content my_page">
    <div class="contents-wrap">
        <div class="tgroup">
            <h2><spring:message code="TEXT_10"/></h2>
            <ul>
                <li>Wallet</li>
                <li><spring:message code="TEXT_10"/></li>
            </ul>
        </div>
        <div class="table-wrap">
            <table class="normal-table">
                <colgroup>
                    <col width="20%" />
                    <col width="80%" />
                </colgroup>
                <tbody>
                <tr>
                    <th>ID</th>
                    <td>${userInfo.userEmail}</td>
                </tr>
                <c:if test="${userInfo.userNm ne ''}">
                <tr>
                    <th><spring:message code="02_09"/></th>
                    <td id="userNm">${userInfo.userNm}</td>
                </tr>
                </c:if>
                <tr>
                    <th><spring:message code="TEXT_295"/></th>
                    <c:choose>
                        <c:when test='${userAuthInfo.smsCertYn eq "N"}'>
                            <td id="mobileSection"><a onClick="setMobile()"><spring:message code="02_07"/></a></td>
                        </c:when>
                        <c:otherwise>
                            <td>${userInfo.hphone}</td>
                        </c:otherwise>
                    </c:choose>
                </tr>
                <%-- <tr>
                     <th>개인키 로그인 사용 설정</th>
                     <td>
                         <div class="switch">
                             <input name="keySet" type="radio" value="" id="keyLoginOn" checked>
                             <label for="keyLoginOn">ON</label>
                             <input name="keySet" type="radio" value="" id="keyLoginOff">
                             <label for="keyLoginOff" class="right">OFF</label>
                             <span aria-hidden="true"></span>
                         </div>
                         <script>
                             $('.switch label').on('click', function(){
                                 var indicator = $(this).parent('.switch').find('span');
                                 if ( $(this).hasClass('right') ){
                                     $(indicator).addClass('right');
                                 } else {
                                     $(indicator).removeClass('right');
                                 }
                             });
                         </script>
                         <dl class="listType">
                             <dt>개인키를 설정 했을 경우</dt>
                             <dd>로그인 시 아이디,비밀번호,개인키를 입력 한 후 로그인이 가능합니다.</dd>
                             <dt>개인키를 미설정 했을 경우</dt>
                             <dd>로그인 시 아이디,비밀번호를 입력 한 후 로그인이 가능합니다.</dd>
                         </dl>
                     </td>
                 </tr>--%>
                <tr>
                    <th><spring:message code="TEXT_246"/></th>
                    <td><a href="javascript:resetPw();"><spring:message code="TEXT_246"/></a></td>
                    <%--<td><a href="javascript:resetPw();" class="popup01">비밀번호 변경</a></td>--%>
                </tr>
                <tr>
                    <th><spring:message code="02_04"/></th>
                    <c:choose>
                        <c:when test='${sendPwdUse == "Y"}'>
                            <td><a href="javascript:showSendPwdPop('reset')"><spring:message code="02_05"/></a></td>
                        </c:when>
                        <c:otherwise>
                            <td><a href="javascript:showSendPwdPop('new')"><spring:message code="02_06"/></a></td>
                        </c:otherwise>
                    </c:choose>
                </tr>
                <%--<tr>
                    <th>PIN번호 변경</th>
                    <td><a href="#n" class="popup01">PIN번호 변경</a></td>
                </tr>--%>
                <%--<tr>--%>
                <%--<th>시간표시기준국가</th>--%>
                <%--<td>--%>
                <%--<select>--%>
                <%--<option value="">시간표시기준국가 선택</option>--%>
                <%--<option value="">한국</option>--%>
                <%--<option value="">미국</option>--%>
                <%--</select>--%>
                <%--</td>--%>
                <%--</tr>--%>
                </tbody>
            </table>
        </div>
    </div>
</main>

<!-- popup -->
<div class="cd-popup popupCon01" role="alert">
    <div class="cd-popup-container">
        <div class="cd-popup-mid">
            <h3>개인키</h3>
            <p class="desc">앞서 메모하신 개인키(12가지 암호)를 참고하면서 아래의 단어들을 순서대로 입력해주세요.</p>
            <div class="security-wrap">
                <dl>
                    <dt><input type="text" placeholder="●●" /></dt>
                    <dd>9번 단어</dd>
                </dl>
                <dl>
                    <dt><input type="text" placeholder="●●" /></dt>
                    <dd>1번 단어</dd>
                </dl>
                <dl>
                    <dt><input type="text" placeholder="●●" /></dt>
                    <dd>13번 단어</dd>
                </dl>
                <dl>
                    <dt><input type="text" placeholder="●●" /></dt>
                    <dd>36번 단어</dd>
                </dl>
            </div>
        </div>
        <ul class="cd-buttons">
            <li><a href="#0" class="btnPoint popup02">확인</a></li>
        </ul>
        <a href="#0" class="cd-popup-close img-replace"></a>
    </div>
</div>

<!-- popup -->
<div class="cd-popup popupCon02" role="alert">
    <div class="cd-popup-container">
        <div class="cd-popup-mid" >
            <h3 style="margin-bottom:2rem"><spring:message code="02_06"/></h3>
            <p class="desc" style="margin-bottom: 0;"><spring:message code="TEXT_80"/></p>
            <p class="desc"><spring:message code="TEXT_81"/></p>
            <div style="width:80%; height:80px; margin:0 auto;">
                <input type="password" id="sendPass0" placeholder="<spring:message code="02_17"/>" minlength="6" maxlength="12" class="find-insert desc" style="display:none; width:100%; margin-bottom:4px; height:30px;" /><br/>
                <input type="password" id="sendPass1" placeholder="<spring:message code="02_18"/>" minlength="6" maxlength="12" class="find-insert desc" style="width:100%; margin-bottom:4px; height:30px;" /><br/>
                <input type="password" id="sendPass2" placeholder="<spring:message code="02_19"/>" minlength="6" maxlength="12" class="find-insert desc" style="width:100%; height:30px;" />
            </div>
            <br>
            <p class="desc" style="text-align:left; margin-bottom:0; margin-top:12px; font-size:12px"><spring:message code="02_20"/></p>
            <p class="desc" style="text-align:left; font-size:12px"><spring:message code="02_21"/></p>
        </div>
        <ul class="cd-buttons" style="margin-top:0">
            <li><a class="btnPoint" onclick="setSendPwd()"><spring:message code="TEXT_246"/></a></li>
        </ul>
        <a class="cd-popup-close img-replace"></a>
    </div>
</div>

<c:import url="/front.include.inc_footer.ds/proc.go" charEncoding="utf-8" />
</body>
</html>