<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <c:import url="/front.include.common_front_head.dp/proc.go"	charEncoding="utf-8" />

    <script type="text/javascript">
        $(document).ready(function() {
            $(".popupLoginCon").addClass('is-visible');
            $(".cd-popup").off("click");
            $(".cd-popup-close").on("click", function() {
                location.href = "/front.main.index.dp/proc.go?lang=" + $("#selectLang").val();
            })
        })

        function getApiKey() {
            postLoadigAjax("/front.include.getKey.dp/proc.go", null, chkLogin , true,"");
        }

        function chkLogin(data) {
            var RSAModulus = data.module;
            var RSAExponent = data.key;

            if ($("#userEmail").val() == "" || $("#userEmail").val() == null) {
                swal('<spring:message code="TEXT_363"/>');
                return;
            }

            var textCk = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;

            if (textCk.test($("#userEmail").val()) == false && $("#userEmail").val() != "") {
                swal('<spring:message code="TEXT_33"/>');
                return;
            }

            var res;
            var enc_pwd = "";

            if ($("#userPwd").val() == "" || $("#userPwd").val() == null) {
                swal('<spring:message code="TEXT_50"/>');
                return;
            }else{
                var pw = $("#userPwd").val();
                var randomKey = generateId(32);
                res = EncryptRSA(RSAModulus,RSAExponent, randomKey);
                enc_pwd = EncryptAES(pw, randomKey);
            }

            var data = {
                userEmail : $("#userEmail").val(),
                userPwd : enc_pwd.trim(),
                aceKey : res,
                autoLogin : $('#autoLogin:checked').length > 0 ? "Y" : "N"
            };
            postLoadigAjax("/front.login.loginAct.dp/proc.go", data, poploginAfter);
        }

        function poploginAfter(data) {
            if(data.resultCode < 0){
                swal(data.resultMsg);
            } else {
                if(window.infJs) {
                    window.infJs.saveSessionId(data.resultStrCode);
                }

                loginAfter();
            }
        }

        function loginAfter() { //로그인 완료후
            loadFrm('', 'loginFrm', 'post', '/front.mypage.myInfoView.dp/proc.go');
        }

        function resetPw(){ //비밀번호 찾기 버튼
            if ($("#userEmail").val() == "" || $("#userEmail").val() == null) {
                swal('<spring:message code="TEXT_239"/>');
                return;
            }

            var textCk = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;

            if (textCk.test($("#userEmail").val()) == false && $("#userEmail").val() != "") {
                swal('<spring:message code="TEXT_241"/>');
                return;
            }

            var data = {
                userEmail : $("#userEmail").val()
            }

            var mailExsitCheckAfter = function(res) {
                if(res.resultCode < 1){
                    swal(res.resultMsg);
                    return;
                }else {
                    swal('<spring:message code='TEXT_29'/>').then(pwMailSend);
                }
            };

            postLoadigAjax("/front.login.mailExsitCheck.dp/proc.go", data, mailExsitCheckAfter);
        }

        function pwMailSend(){ //비밀번호 변경 메일 전송
            var data = {
                userEmail : $("#userEmail").val()
            };
            postLoadigAjax("/front.mail.pwMailSend.dp/proc.go", data, pwMailSendAfter);
        }

        function pwMailSendAfter(data){
            if(data.resultCode > 0) {
                swal('<spring:message code="TEXT_379"/>');
            }else{
                swal(data.resultMsg);
            }
        }

        function enterLogin(){ //엔터 로그인
            if (event.keyCode == 13) {
                getApiKey();
            }
        }
    </script>
</head>
<body>
<c:import url="/front.include.inc_header.dp/proc.go" charEncoding="utf-8" />
<main class="cd-main-content">
    <div class="contents-wrap">
        <%--<form action="POST" name="loginFrm" id="loginFrm">
            <div class="short-wrap">
                <h2><spring:message code="TEXT_22"/></h2>
                <div class="form-box">
                    <ul>
                        <li>
                            <span class="iconSet"><i class="xi-mail-o"></i></span>
                            <input type="email" name="userEmail" id="userEmail" class="iconInput" placeholder="Email" />
                        </li>
                        <li>
                            <span class="iconSet"><i class="xi-lock-o"></i></span>
                            <input type="password" name="userPwd" id="userPwd"  class="iconInput" placeholder="Password" onkeydown="enterLogin();"/>
                        </li>
                    </ul>
                </div>
                <div class="cell">
                    <span class="left"><a href="/front.join.joinView.dp/proc.go"><i class="xi-user-plus-o"></i><spring:message code="TEXT_28"/></a></span>
                    &lt;%&ndash;<span class="right"><a onclick="" class="popup01"><i class="xi-info-o"></i>비밀번호찾기</a></span>&ndash;%&gt;
                    <span class="right"><a onclick="javascript:resetPw();"><i class="xi-info-o"></i><spring:message code="TEXT_26"/></a></span>
                </div>
               &lt;%&ndash; <div class="security-wrap">
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
                </div>&ndash;%&gt;
                <div class="bottom-btnWrap">
                    <a href="#n" class="btn-primary" onclick="getApiKey()"><spring:message code="TEXT_27"/></a>
                </div>
            </div>
        </form>--%>
    </div>
</main>

<form action="POST" name="loginFrm" id="loginFrm">
    <div class="cd-popup popupLoginCon" role="alert" style="display:block">
        <div class="cd-popup-container login">
            <div class="cd-popup-mid">
                <h3><spring:message code="TEXT_22"/></h3>
                <div class="form-box">
                    <ul>
                        <li>
                            <span class="iconSet"><i class="xi-mail-o"></i></span>
                            <input type="email" name="userEmail" id="userEmail" class="iconInput" placeholder="Email" />
                        </li>
                        <li>
                            <span class="iconSet"><i class="xi-lock-o"></i></span>
                            <input type="password" name="userPwd" id="userPwd"  class="iconInput" placeholder="Password" onkeydown="enterLogin();"/>
                        </li>
                    </ul>
                </div>
                <div class="cell">
                    <span class="left"><input id="autoLogin" type="checkbox"/><label for="autoLogin"><spring:message code="TEXT_434"/></label></span>
                    <%--주석 풀자 <span class="right"><a onclick="javascript:resetPw();"><i class="xi-info-o"></i><spring:message code="TEXT_26"/></a></span>--%>
                </div>
                <ul class="cd-buttons">
                    <li><a onClick="getApiKey()" class="btnPoint"><spring:message code="TEXT_22"/></a></li>
                    <li><a href="/front.join.joinView.dp/proc.go" class="btnDefault"><spring:message code="TEXT_28"/></a></li>
                    <%--<li><a href="javascript:alert('준비중입니다.');" class="btnDefault"><spring:message code="TEXT_28"/></a></li>--%>
                </ul>
            </div>
            <a class="cd-popup-close img-replace"></a>
        </div>
    </div>
</form>

<!-- popup -->
<div class="cd-popup popupCon01" role="alert">
    <div class="cd-popup-container">
        <div class="cd-popup-mid">
            <div class="tab-login">
                <a href="#n" class="item active" data-tab="first">아이디 찾기</a>
                <a href="#n" class="item" data-tab="second">비밀번호 찾기</a>
            </div>
            <div class="tabCon ui tab active"  data-tab="first">
                <p class="desc">개인키(12가지 암호)에 맞추어 아래의 단어들을 순서대로 입력해주세요.</p>
                <div class="security-wrap">
                    <dl>
                        <dt><input type="text" placeholder="●●" /></dt>
                        <dd>9번 단어 </dd>
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
                <div class="findId">
                    <dl>
                        <dt>아이디 : </dt>
                        <dd>cmesoft@cmesoft.co.kr</dd>
                    </dl>
                </div>
            </div>
            <div class="tabCon ui tab" data-tab="second">
                <div class="step01">
                    <p class="desc">비밀번호를 찾고자 하는 이메일 및 개인키(12가지 암호)에 맞추어 아래의 단어들을 순서대로 입력해주세요.</p>
                    <input type="email" placeholder="Email" class="find-insert" />
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
                <!--
                        <div class="step02">
                            <div class="tgroup">
                                <h5>비밀번호 재설정</h5>
                                <p class="desc">새로운 비밀번호를 입력해주세요.</p>
                            </div>
                            <input type="email" placeholder="비밀번호" class="find-insert" />
                            <input type="email" placeholder="비밀번호 재확인" class="find-insert" />
                        </div>
                -->

            </div>
        </div>
        <ul class="cd-buttons">
            <li><a href="#0" class="btnPoint" onclick="onClickSecurity()">로그인</a></li>

        </ul>
        <a href="#0" class="cd-popup-close img-replace"></a>
    </div>
</div>

<script>
    $('.tab-login .item').tab();
</script>
<c:import url="/front.include.inc_footer.ds/proc.go" charEncoding="utf-8" />
</body>
</html>