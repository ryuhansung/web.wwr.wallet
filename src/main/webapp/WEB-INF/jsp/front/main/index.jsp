<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<c:import url="/front.include.common_front_head.dp/proc.go"	charEncoding="utf-8" />
<script>
    $(document).ready(function() {
        openMainPop();

    });

    function openMainPop(){

        var popCnt = '${popCnt}'; //띄울 팝업 갯수
        if(popCnt > 0){ //띄울 팝업이 1개 이상이라면
            var isPop = GetCookie('layer1');
            if(isPop != '' && typeof isPop != 'undefined' && isPop != null){//쿠키가 있다면

            }else{//쿠키가없다면 팝업을 보이게함

                $(".layerPop_wrap").show();
                $(".layerPop_inner").show();
                $("#layer1").show();
            }

            var isPop2 = GetCookie('layer2');
            if(isPop2 != ''  && typeof isPop2 != 'undefined'  && isPop2 != null){

            }else{
                $(".layerPop_wrap").show();
                $(".layerPop_inner").show();
                $("#layer2").show();
            }

            var isPop3 = GetCookie('layer3');
            if(isPop3 != ''  && typeof isPop3 != 'undefined'  && isPop3 != null){

            }else{
                $(".layerPop_wrap").show();
                $(".layerPop_inner").show();
                $("#layer3").show();
            }
            //처음페이지를 키면 display:none >>>> 쿠키를 저장하지 않는 이상 계속나옴.
            var show1 = $("#layer1").css("display");//상태를보는것. show인경우 table , hide인경우 none으로 상태가변경됨.
            var show2 = $("#layer2").css("display");
            var show3 = $("#layer3").css("display");

            if(show1 != 'table' && show2 != 'table' && show3 != 'table') {
                $(".layerPop_wrap").hide();
                $(".layerPop_inner").hide();
            }
        }
    }


    function closeMainPop(param) { //그냥 닫기 눌렀을때
        $("#layer" + param).hide();
        var show1 = $("#layer1").css("display");
        var show2 = $("#layer2").css("display");
        var show3 = $("#layer3").css("display");
        if('${popCnt}' == 3){
            if(show1== "none" && show2 == "none"  && show3 == "none") {
                $(".layerPop_wrap").hide();
                $(".layerPop_inner").hide();
            }
        }else if('${popCnt}' == 2){
            if(show1== "none" && show2 == "none") {
                $(".layerPop_wrap").hide();
                $(".layerPop_inner").hide();
            }
        }else if('${popCnt}' == 1){
            if(show1== "none") {
                $(".layerPop_wrap").hide();
                $(".layerPop_inner").hide();
            }
        }

    }

    function setCookie(id) {
        if(id == '1'){
            SetCookie("layer1","layer1",1);
            $("#layer1").hide();
        }else if(id =='2'){
            SetCookie("layer2","layer2",1);
            $("#layer2").hide();
        }else if(id =='3'){
            SetCookie("layer3","layer3",1);
            $("#layer3").hide();
        }
        var show1 = $("#layer1").css("display");
        var show2 = $("#layer2").css("display");
        var show3 = $("#layer3").css("display");

        if('${popCnt}' == 3){
            if(show1== "none" && show2 == "none"  && show3 == "none") {
                $(".layerPop_wrap").hide();
                $(".layerPop_inner").hide();
            }
        }else if('${popCnt}' == 2){
            if(show1== "none" && show2 == "none") {
                $(".layerPop_wrap").hide();
                $(".layerPop_inner").hide();
            }
        }else if('${popCnt}' == 1){
            if(show1== "none") {
                $(".layerPop_wrap").hide();
                $(".layerPop_inner").hide();
            }
        }
    }


</script>
</head>
<body>
<c:import url="/front.include.inc_header.dp/proc.go" charEncoding="utf-8" />
<main class="cd-main-content">
    <div class="cover">
        <div class="searchWrap">
            <%--<input type="search" placeholder="Search by Address / Txhash / Block / Ens" />--%>
            <%--<a href="#">GO</a>--%>
        </div>
        <em class="slogan"><spring:message code="TEXT_17"/></em>
        <p><spring:message code="TEXT_411"/></p>
        <a href="/front.wallet.walletManageView.dp/proc.go" class="btn-create-wallet"><spring:message code="TEXT_21"/></a>
    </div>
</main>
<c:import url="/front.include.inc_footer.dp/proc.go" charEncoding="utf-8" />

<div class="layerPop_wrap" style="display: none;">
    <div class="layerPop_inner" style="display:none;">
        <c:if test="${!empty fileList}">
            <c:forEach var="i" items="${fileList}" varStatus="idx">
                <div id="layer${idx.count}" class="popupBox" style="display:none">
                    <div class="layerInner">
                        <div class="txtBox">
                            <a href="${i.pop_url}">
                                <img src="/common.file.getImageView.dp/proc.go?atchFileId=${i.atch_file_id}&fileSn=${i.file_sn}" alt="" />
                            </a>
                        </div>
                        <c:if test="${i.day_open_yn eq 'Y'}">
                            <input type="checkbox" name="popCheck" id="pop0${idx.count}" onclick="setCookie(${idx.count})" />
                            <label for="pop0${idx.count}" javaScriptEscape="true"><spring:message code="01_01"/></label>
                        </c:if>
                        <a onclick="closeMainPop(${idx.count})"; style="cursor: pointer" class="popClose" javaScriptEscape="true">
                            [<spring:message code="01_02"/>]
                        </a>
                    </div>
                </div>
            </c:forEach>
        </c:if>
    </div>
</div>
</body>
</html>