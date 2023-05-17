<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<c:import url="/front.include.common_front_head.dp/proc.go"	charEncoding="utf-8" />
<script>
$(document).ready(function(){
    if('${vo.user_email}' != null && '${vo.user_email}' != ""){
        $("#user_email").attr('class' , 'readonly');
        $("#user_email").val('${vo.user_email}');
        $('#user_email').attr("readonly", true) ;
    }
});
function getByteB(str) {
    var chkByte = 0;
    for (var i=0; i<str.length; ++i) {
        (str.charCodeAt(i) > 127) ? chkByte += 3 : chkByte++ ;
    }
    return chkByte;
}

function createAct(){

    if($('input:checkbox[id="c1"]').is(":checked") != true){
        swal('<spring:message code="TEXT_403"/>');
        return;
    }

    if($("#cat_id").val() == ""){
        swal('<spring:message code="TEXT_368"/>');
        return;
    }

    if($("#user_email").val() == ""){
        swal('<spring:message code="TEXT_363"/>');
        return;
    }

    if($("#user_name").val() == ""){
        swal('<spring:message code="TEXT_364"/>');
        return;
    }
    if($("#user_name").val().length <= 1){
        swal('<spring:message code="02_12"/>')
        return;
    }

    if($("#board_title").val() == ""){
        swal('<spring:message code="TEXT_365"/>');
        return;
    }

    if($("#content_msg").val() == ""){
        swal('<spring:message code="TEXT_366"/>');
        return;
    }

    <%--if($("#user_phone").val() == ""){--%>
        <%--swal('<spring:message code="TEXT_404"/>');--%>
        <%--return;--%>
    <%--}--%>

    if(parseInt(getByteB($("#board_title").val())) > 100){
        swal('<spring:message code="TEXT_405"/>');
        return;
    }

    if(parseInt(getByteB($("#content_msg").val())) > 1600){
        swal('<spring:message code="TEXT_406"/>');
        return;
    }

    if('${vo.user_email}' == '' && $("#board_pwd").val() == ''){
        swal('<spring:message code="TEXT_50"/>');
        return;
    }else{
     if('${vo.content_id}' == '' &&'${vo.user_email}' == '' && $("#board_pwd").val().length < 6){
           swal('<spring:message code="TEXT_407"/>');
           return;
       }
    }



    if($("#content_id").val() != "" && $("#content_id").val() != null){
        var url = "/front.board.updateAct.dp/proc.go";
    }else{
        var url = "/front.board.createAct.dp/proc.go";
    }
    var data = $("#qnaWriteForm").serialize();

    postAjax(url, data, createActAfter);

}

function createActAfter(data){
    if(data.resultCode > 0){
        $("#cat_id").val('');
        swal('<spring:message code="TEXT_250"/>').then(function(){
            goBoardList('BDMT_000000000005')
        });
    }else{
        swal('<spring:message code="TEXT_386"/>');
    }
}
</script>
</head>
<body>
<c:import url="/front.include.inc_header.dp/proc.go" charEncoding="utf-8" />
<main class="cd-main-content inquiry-write">
    <div class="contents-wrap">
    <form id="qnaWriteForm" name="qnaWriteForm" action="POST">
        <input type="hidden" name="board_id" id="board_id" value="${vo.board_id}" />
        <input type="hidden" name="is_notice" id="notice_yn" value="N" />
        <input type="hidden" name="content_id" id="content_id" value="${vo.content_id}"/>
        <div class="tgroup">
            <h2><spring:message code="TEXT_15"/></h2>
            <ul>
                <li><spring:message code="TEXT_13"/></li>
                <li><spring:message code="TEXT_15"/></li>
            </ul>
        </div>
        <div class="check-list">
            <div class="cell">
                <ul class="dotStyle">
                    <li><strong><spring:message code="TEXT_336"/></strong></li>
                    <li><strong><spring:message code="TEXT_337"/></strong></li>
                    <li><strong><spring:message code="TEXT_338"/></strong></li>
                </ul>
                <em class="noti">※ <spring:message code="TEXT_339"/></em>
            </div>
            <div class="check-wrap">
                <input type="checkbox" id="c1" class="iCheck" /><label for="c1"><spring:message code="TEXT_340"/></label>
            </div>
        </div>

        <div class="table-wrap">
            <table class="normal-table">
                <colgroup>
                    <col width="20%" />
                    <col width="80%" />
                </colgroup>
                <tbody>
                <tr>
                    <th><spring:message code="TEXT_341"/></th>
                    <td>
                        <select id="cat_id" name="cat_id">
                            <c:if test="${!empty optList}">
                                <c:forEach items="${optList}" var="optList">
                                    <option value="${optList.cat_id}">${optList.cat_name}</option>
                                </c:forEach>
                            </c:if>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th><spring:message code="TEXT_348"/></th>
                    <c:choose>
                        <c:when test="${vo.content_id eq '' or vo.user_email eq ''}">
                            <td><input type="email" placeholder="<spring:message code="TEXT_363"/>" id="user_email" name="user_email" maxlength="50" value="${result.reg_user}"/></td>
                        </c:when>
                        <c:otherwise>
                            <td>${vo.user_email}</td>
                            <input type="hidden" id="user_email" name="user_email" value="${vo.user_email}"/>
                        </c:otherwise>
                    </c:choose>
                </tr>
                <tr>
                    <th><spring:message code="TEXT_349"/></th>
                    <td><input type="text" placeholder="<spring:message code="TEXT_364"/>" id="user_name" name="user_name" maxlength="15" value="${result.user_name}" /></td>
                </tr>
                <tr>
                    <th><spring:message code="TEXT_350"/></th>
                    <td><input type="text" placeholder="<spring:message code="TEXT_365"/>" id="board_title" name="board_title" maxlength="100" value="${result.board_title}"/></td>
                </tr>
                <tr>
                    <th><spring:message code="TEXT_351"/></th>
                    <td><textarea rows="10" style="width: 100%;" placeholder="<spring:message code="TEXT_366"/>" id="content_msg" name="content_msg" maxlength="1000">${result.content_msg}</textarea></td>
                </tr>
                <tr>
                    <th><spring:message code="TEXT_352"/></th>
                    <td><input type="text" placeholder="<spring:message code="TEXT_358"/>" id="user_phone" name="user_phone" maxlength="15" value="${result.user_phone}" /></td>
                </tr>
                <c:if test="${vo.content_id eq '' and vo.user_email eq ''}">
                    <tr>
                        <th><spring:message code="TEXT_353"/></th>
                        <td>
                            <input type="password" placeholder="<spring:message code="TEXT_367"/>" id="board_pwd" name="board_pwd" value="${result.board_pwd}" maxlength="10"/>
                            <p class="noti">※ <spring:message code="TEXT_360"/></p>
                        </td>
                    </tr>
                </c:if>
                </tbody>
            </table>
        </div>
        <ul class="btn-area">
            <li><a href="javascript:goBoardList('BDMT_000000000005');" class="btn-cancel"><spring:message code="TEXT_361"/></a></li>
            <li><a href="javascript:createAct();" class="btn-submit"><spring:message code="TEXT_362"/></a></li>
        </ul>
    </form>
    </div>
</main>
<c:import url="/front.include.inc_footer.ds/proc.go" charEncoding="utf-8" />
</body>
</html>