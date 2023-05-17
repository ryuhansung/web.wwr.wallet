<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<c:import url="/front.include.common_front_head.dp/proc.go"	charEncoding="utf-8" />
</head>
<script>
    function updateView(){
        $('#qnaViewForm').attr({method:'post',action:"/front.board.create.dp/proc.go", target:"_self"}).submit();
    }

    function contentDelete(){
        var url = "/front.board.delete.dp/proc.go";
        var data = $("#qnaViewForm").serialize();
        postAjax(url, data, contentDeleteAfter);
    }

    function contentDeleteAfter(data){
        if(data.resultCode > 0){
            $("#cat_id").val('');
            swal(data.resultMsg).then(function(){
                goBoardList('BDMT_000000000005')
            });
        }else{
            swal(data.resultMsg);
        }
    }

    function pwPopup(id){
        if('${vo.user_email}' == '${result.reg_user}'){
            if(id == 'update'){
                updateView();
            }else if(id == 'delete'){
                contentDelete();
            }
        }else{
            $('#pwPopup').removeClass('update');
            $('#pwPopup').removeClass('delete');
            $('#pwPopup').addClass('is-visible');
            $('#pwPopup').addClass(id);
        }
    }

    function chkBoardPw(){
        if($("#board_pwd").val() == null ||$("#board_pwd").val() ==  ""){
            swal('<spring:message code="TEXT_31"/>');
            return;
        }

        if('${result.board_pwd}' != $("#board_pwd").val().trim()){
            swal('<spring:message code="TEXT_53"/>');
            $("#board_pwd").val("");
            return;
        }

        var classCk = $('#pwPopup').attr('class');
        var chekTest = classCk.toString();
        if (chekTest.indexOf("update") != -1) {
            updateView();
        }else if(chekTest.indexOf("delete") != -1){
            contentDelete();
        }
    }
</script>
<body>
<c:import url="/front.include.inc_header.dp/proc.go" charEncoding="utf-8" />
<main class="cd-main-content inquiry-view">
    <div class="contents-wrap">
        <form id="qnaViewForm" name="qnaViewForm" action="POST">
        <input type="hidden" name="board_id" id="board_id" value="${result.board_id}" />
        <input type="hidden" name="content_id" id="content_id" value="${result.content_id}"/>
        <input type="hidden" name="cat_name" id="cat_name" value="${result.cat_name}"/>
        <div class="tgroup">
            <h2><spring:message code="TEXT_15"/></h2>
            <ul>
                <li><spring:message code="TEXT_13"/></li>
                <li><spring:message code="TEXT_15"/></li>
            </ul>
        </div>
        <div class="inquiry-contents">
            <h3 class="iq-title"><em>[${result.cat_name}]</em>${result.board_title}</h3>
            <div class="author-info">
                <dl class="author">
                    <dt><spring:message code="TEXT_314"/> :</dt><dd>${result.user_name}(${result.reg_user})</dd>
                </dl>
                <dl class="date">
                    <dt><spring:message code="TEXT_315"/> :</dt><dd>${result.reg_dt}</dd>
                </dl>
                <c:if test="${result.user_phone ne ''}">
                <dl class="mobile">
                    <dt><spring:message code="TEXT_324"/> :</dt><dd>${result.user_phone}</dd>
                </dl>
                </c:if>
            </div>
            <div class="cell">
                <div class="inner">
                    <pre style="word-break: break-all; /*white-space: inherit;*/">${result.content_msg}</pre>
                </div>
                <ul class="btn-list">

                    <li><a href="javascript:pwPopup('update');"><i class="xi-pen"></i><spring:message code="TEXT_329"/></a></li>
                    <li><a href="javascript:pwPopup('delete');"><i class="xi-trash-o"></i><spring:message code="TEXT_330"/></a></li>
                </ul>
            </div>

            <c:if test="${result.reply_yn eq 'Y'}">
                <div class="answer-wrap">
                    <div class="author-info">
                        <dl class="author">
                            <dt><spring:message code="TEXT_314"/> :</dt><dd><spring:message code="TEXT_326"/></dd>
                        </dl>
                        <dl class="date">
                            <dt><spring:message code="TEXT_315"/> :</dt><dd>${result.reply_dt}</dd>
                        </dl>
                    </div>
                    <div class="inner">
                        <p>${result.reply_msg}</p>
                    </div>
                </div>
            </c:if>
        </div>
        <div class="btn-area">
            <a href="javascript:goBoardList('BDMT_000000000005');" class="btn-inquiry"><spring:message code="TEXT_331"/></a>
        </div>
        </form>
    </div>
</main>

<!-- popup -->
<div class="cd-popup popupCon01" role="alert" id="pwPopup">
    <div class="cd-popup-container">
        <div class="cd-popup-mid">
            <h3><spring:message code="TEXT_316"/></h3>
            <p class="desc"><spring:message code="TEXT_317"/></p>
            <input type="password" id="board_pwd" name="board_pwd" style="width: 100%;" class="insert-data" placeholder="<spring:message code="TEXT_25"/>" />
        </div>
        <ul class="cd-buttons">
            <li><a href="javascript:chkBoardPw();" class="btnPoint"><spring:message code="TEXT_318"/></a></li>
        </ul>
        <a href="#0" class="cd-popup-close img-replace"></a>
    </div>
</div>
<c:import url="/front.include.inc_footer.ds/proc.go" charEncoding="utf-8" />
</body>
</html>