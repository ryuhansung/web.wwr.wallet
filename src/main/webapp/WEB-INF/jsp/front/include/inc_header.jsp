<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<title>RNR Wallet</title>
<script type="text/javascript"></script>
<script>
    const appUrl = 'https://play.google.com/store/apps/details?id=cme.wswallet.app';

    function reloadPage(){
        location.href = "/front.main.index.dp/proc.go?lang=" + $("#selectLang").val();
    }

    function logout() {
        postLoadigAjax("/front.login.logout.dp/proc.go", null, logoutAfter, true, "");
    }

    function logoutAfter() {
        location.href = "/front.main.index.dp/proc.go?lang=" + $("#selectLang").val();
    }

    function goBoardList(id) {
        location.href = "/front.board.list.dp/proc.go?board_id=" + id;
    }

    function selectLang() {
        location.href = "/front.main.index.dp/proc.go?lang=" + $("#selectLang").val();
    }

    function copyUrl(addr){ //주소복사
        var $temp = $("<input>");
        $("body").append($temp);
        $temp.val(addr).select();
        document.execCommand("copy");
        $temp.remove();
        swal('<spring:message code="TEXT_387"/>');
    }

    function sendSmsAppUrl() {
        window.infJs.reqPhoneNumber();
    }

    function resSmsPhoneNumber(number) {
        swal({
            text : number + "로 앱 다운로드 URL을 발송하시겠습니까?",
            buttons: {
                "<spring:message code='TEXT_318'/>": true,
                cancel: "<spring:message code='TEXT_361'/>"
            }
        }).then(function(res) {
            if(res != null) {
                let data = {
                    type : "recommand",
                    m_mobile : number
                }
                postLoadigAjax("/front.sms.sendSms.dp/proc.go", data, resSmsPhoneNumberAfter, true, "");
            }
        });


    }

    function resSmsPhoneNumberAfter(res) {
        if(res.resultCode == "1") {
            swal("앱 추천 SMS 발송에 성공하였습니다.").then(function() {
                location.reload();
            });
        } else {

        }

    }

    $(document).ready(function() {
        postLoadigAjax("/front.include.isIncLogin.dp/proc.go", null, isIncLoginAfter, false, "");

        if(window.infJs) {
            if(window.infJs.reqVersion)
                window.infJs.reqToken();
        }

        $("#menuBtn").on("click", function() {
            postLoadigAjax("/front.include.isIncLogin.dp/proc.go", null, isIncLoginAfter, false, "");
        });

        function isIncLoginAfter(res) {
            if(res.resultCode == 1) {
                $("#tmLogin").remove();
            } else {
                $("#tmLogout").remove();
            }
        }

        if(window.infJs) {
            if(window.infJs.reqPhoneNumber) {
                $("#sendSmsApp").show();
            }
        }
    });

    function resToken(token) {
        let data = {
            token : token
        }
        postLoadigAjax("/front.include.updateToken.dp/proc.go", data, resTokenAfter, false, "");
    }

    function resTokenAfter(res) {

    }

    function serviceAlert(){
        swal("<spring:message code='02_01'/>");
        return;
    }
</script>

<header class="cd-main-header">
    <a class="cd-logo" href="javascript:reloadPage();"><img src="/images/front/common/logo.png" alt="Logo"></a>
    <ul class="cd-header-buttons">
        <%--<li><a class="cd-search-trigger" href="#cd-search">Search<span></span></a></li>--%>
        <li><a id="menuBtn" class="cd-nav-trigger" href="#cd-primary-nav">Menu<span></span></a></li>
    </ul> <!-- cd-header-buttons -->
</header>
<nav class="cd-nav">
    <ul class="top-menu" style="height: 43px">
        <!-- 일반 로그인 -->
        <li id="tmLogin" class="tm-login"><a href="/front.login.loginView.dp/proc.go"><i class="xi-power-off"></i><spring:message code="TEXT_22"/></a></li>
        <li id="tmLogout" class="tm-logout"><a onClick="logout()"><i class="xi-power-off"></i><spring:message code="TEXT_23"/></a></li>

        <!-- pin번호 로그인 -->
        <!--			<li class="tm-login"><a href="login.html" class="popupPin"><i class="xi-power-off"></i> 로그인</a></li>-->
        <li class="tm-lang">
            <select id="selectLang" onChange="selectLang()">
<%--                <option value="ko" <c:if test='${lang == "ko"}'>selected="selected"</c:if>>한국어</option>--%>
                <option value="en" <c:if test='${lang == "en"}'>selected="selected"</c:if>>English</option>
                <%--<option value="zh">中國語</option>--%>
                <%--<option value="ja">日本語</option>--%>
            </select>
        </li>
    </ul>
    <ul id="cd-primary-nav" class="cd-primary-nav is-fixed">
        <li>
            <a href="javascript:reloadPage();">HOME</a>
        </li>
        <li class="has-children">
            <a href="#">Wallet</a>
            <ul class="cd-nav-gallery is-hidden">
                <li class="go-back"><a>Menu</a></li>

                <%--<li><a class="cd-nav-item" href="javascript:serviceAlert();"><h3><spring:message code="TEXT_04"/></h3></a></li>--%>
                <%--<li><a class="cd-nav-item" href="javascript:serviceAlert();"><h3><spring:message code="TEXT_05"/></h3></a></li>--%>
                <%--<li><a class="cd-nav-item" href="javascript:serviceAlert();"><h3><spring:message code="TEXT_06"/></h3></a></li>--%>


                <li><a class="cd-nav-item" href="/front.wallet.walletManageView.dp/proc.go"><h3><spring:message code="TEXT_04"/></h3></a></li>
                <li><a class="cd-nav-item" href="/front.deposit.depositView.dp/proc.go"><h3><spring:message code="TEXT_05"/></h3></a></li>
                <li><a class="cd-nav-item" href="javascript:serviceAlert();"><h3><spring:message code="TEXT_06"/></h3></a></li>
                <%--<li><a class="cd-nav-item" href="/front.send.sendView.dp/proc.go"><h3><spring:message code="TEXT_06"/></h3></a></li>--%>
                <c:choose>
                    <c:when test="${!empty lvo}">
                        <li><a class="cd-nav-item" href="/front.lend.lendingView.dp/proc.go"><h3><spring:message code="TEXT_425"/></h3></a></li>
                    </c:when>

                </c:choose>
                <%--<li><a class="cd-nav-item" href="/front.wallet.activityListView.dp/proc.go"><h3><spring:message code="TEXT_07"/></h3></a></li>--%>
                <%--<li><a class="cd-nav-item" href="/front.wallet.provisionListView.dp/proc.go"><h3><spring:message code="TEXT_08"/></h3></a></li>--%>
                <%--<li><a class="cd-nav-item" href="/front.wallet.usbManageView.dp/proc.go"><h3><spring:message code="TEXT_09"/></h3></a></li>--%>
                <li><a class="cd-nav-item" href="/front.mypage.myInfoView.dp/proc.go"><h3><spring:message code="TEXT_10"/></h3></a></li>
            </ul>
        </li>
        <%--<li class="has-children">--%>
            <%--<a href="#">제품소개</a>--%>
            <%--<ul class="cd-nav-gallery is-hidden">--%>
                <%--<li class="go-back"><a href="#0">Menu</a></li>--%>
                <%--<li><a class="cd-nav-item" href="/front.product.product_info.ds/proc.go"><h3>제품소개</h3></a></li>--%>
                <%--<li><a class="cd-nav-item" href="/front.product.purchase_info.ds/proc.go"><h3>제품구매정보</h3></a></li>--%>
            <%--</ul>--%>
        <%--</li>--%>
        <li class="has-children">
            <a href="#"><spring:message code="TEXT_13"/></a>
            <ul class="cd-nav-gallery is-hidden">
                <li class="go-back"><a href="#0">Menu</a></li>
                <li><a class="cd-nav-item" href="javascript:goBoardList('BDMT_000000000007');"><h3><spring:message code="TEXT_424"/></h3></a></li>
                <li><a class="cd-nav-item" href="javascript:goBoardList('BDMT_000000000006');"><h3><spring:message code="TEXT_300"/></h3></a></li>
                <li><a class="cd-nav-item" href="javascript:goBoardList('BDMT_000000000005');"><h3><spring:message code="TEXT_310"/></h3></a></li>
            </ul>
        </li>

<%--        <li class="has-children">
            <a><spring:message code="TEXT_413"/></a>
            <ul class="cd-nav-gallery is-hidden">
                <li class="go-back"><a>Menu</a></li>
                <li><a class="cd-nav-item" onClick="window.open(appUrl)"><h3><spring:message code="TEXT_414"/></h3></a></li>
                <li><a class="cd-nav-item" onClick="copyUrl(appUrl)"><h3><spring:message code="TEXT_415"/></h3></a></li>
                <li id="sendSmsApp" style="display:none"><a class="cd-nav-item" onClick="sendSmsAppUrl()"><h3><spring:message code="02_39"/></h3></a></li>
            </ul>
        </li>--%>
    </ul> <!-- primary-nav -->
</nav> <!-- cd-nav -->

<%--<div id="cd-search" class="cd-search">--%>
    <%--<form>--%>
        <%--<input type="search" placeholder="Search...">--%>
        <%--<a href="" class="btn-search">GO</a>--%>
    <%--</form>--%>
<%--</div>--%>

<!-- popup -->
<div class="cd-popup popupPinCon" role="alert">
    <div class="cd-popup-container">
        <div class="cd-popup-mid">
            <h3>PIN 로그인</h3>
            <p class="desc">PIN 번호(6자리)를 입력해주세요.</p>
            <ul class="pinNum">
                <li><input type="password" /></li>
                <li><input type="password" /></li>
                <li><input type="password" /></li>
                <li><input type="password" /></li>
                <li><input type="password" /></li>
                <li><input type="password" /></li>
            </ul>
            <div class="btn-wrap">
                <a href="" class="otherId">다른 아이디로 로그인</a>
            </div>
        </div>
        <a href="#0" class="cd-popup-close img-replace"></a>
    </div>
</div>

