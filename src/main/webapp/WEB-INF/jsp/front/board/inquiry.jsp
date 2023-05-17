<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<c:import url="/front.include.common_front_head.dp/proc.go"	charEncoding="utf-8" />
<script type="text/javascript" src="/js/jquery-ui.min.js"></script>
<script>
    $(document).ready(function(){
        if('${vo.searchBgnDe}' !='' && '${vo.searchEndDe}' !=''){
            var funcNm = '${vo.funcNm}';
            $(".btn-fun__grad:eq("+funcNm+")").click();
            $("#sdate").val('${vo.searchBgnDe}');
            $("#rdate").val('${vo.searchEndDe}');
        }else{
            $(".dabtn-fun__gradte:eq(3)").click();
            var today = new Date();
            var endDate = today.format('yyyy-MM-dd');
            today.setMonth(today.getMonth() - 1);
            today = getCalDate(today, -0);
            var staDate = today.format('yyyy-MM-dd');

            $("#sdate").val(staDate);
            $("#rdate").val(endDate);

        }
        var Close = "";
        var toDay = "";
        var su = "";
        var mo = "";
        var tu = "";
        var we = "";
        var th = "";
        var fr = "";
        var sa = "";
        var jan = "";
        var feb = "";
        var mar = "";
        var apr = "";
        var may = "";
        var jun = "";
        var jul = "";
        var aug = "";
        var sep = "";
        var oct = "";
        var nov = "";
        var dec = "";

        if ('${lang}' == 'ko') {
            Close = '닫기',
                toDay = '오늘',
                su = '일',
                mo = '월',
                tu = '화',
                we = '수',
                th = '목',
                fr = '금',
                sa = '토',
                jan = "1월",
                feb = "2월",
                mar = "3월",
                apr = "4월",
                may = "5월",
                jun = "6월",
                jul = "7월",
                aug = "8월",
                sep = "9월",
                oct = "10월",
                nov = "11월",
                dec = "12월"
        } else {
            Close = 'close',
                toDay = 'today',
                su = 'Su',
                mo = 'Mo',
                tu = 'Tu',
                we = 'We',
                th = 'Th',
                fr = 'Fr',
                sa = 'Sa',
                jan = "Jan",
                feb = "Feb",
                mar = "Mar",
                apr = "Apr",
                may = "May",
                jun = "Jun",
                jul = "Jul",
                aug = "Aug",
                sep = "Sep",
                oct = "Oct",
                nov = "Nov",
                dec = "Dec"
        }

        $("#sdate").datepicker({
            showOn : "button",
            changeYear : true,
            changeMonth : true,
            buttonImage : "/images/front/common/ico_calander.png",
            buttonImageOnly : true,
            dateFormat : "yy-mm-dd",
            numberOfMonths : 1,
            maxDate: "+0m +0w",
            showMonthAfterYear: true,        /* 년과 달의 위치 바꾸기 */
            /* 한글화 */
            monthNames : [jan, feb, mar, apr, may, jun, jul, aug, sep, oct, nov, dec],
            monthNamesShort : [jan, feb, mar, apr, may, jun, jul, aug, sep, oct, nov, dec],
            dayNames : [su, mo, tu, we, th, fr, sa],
            dayNamesShort : [su, mo, tu, we, th, fr, sa],
            dayNamesMin : [su, mo, tu, we, th, fr, sa],
            showAnim: 'slideDown',
            autoSize:true,
            onClose : function(selectedDate) {
                $("#rdate").datepicker("option", "minDate", selectedDate);
            }
        });

        $("#rdate").datepicker({
            showOn : "button",
            changeYear : true,
            changeMonth : true,
            buttonImage : "/images/front/common/ico_calander.png",
            buttonImageOnly : true,
            dateFormat : "yy-mm-dd",
            maxDate: "+0m +0w",
            numberOfMonths : 1,
            showMonthAfterYear: true,        /* 년과 달의 위치 바꾸기 */
            /* 한글화 */
            monthNames : [jan, feb, mar, apr, may, jun, jul, aug, sep, oct, nov, dec],
            monthNamesShort : [jan, feb, mar, apr, may, jun, jul, aug, sep, oct, nov, dec],
            dayNames : [su, mo, tu, we, th, fr, sa],
            dayNamesShort : [su, mo, tu, we, th, fr, sa],
            dayNamesMin : [su, mo, tu, we, th, fr, sa],
            showAnim: 'slideDown',
            autoSize:true,
            onClose : function(selectedDate) {
                $("#sdate").datepicker("option", "maxDate", selectedDate);
            }
        });

        if('${vo.cat_id}' != ''){
            $("#cat_id").val('${vo.cat_id}');
        }
        if('${vo.searchCnd}' != ''){
            $("#searchCnd").val('${vo.searchCnd}');
        }

        <%-- 엔터키 이벤트 --%>
        $("#searchWrd1").keydown(function() {
            if (event.keyCode === 13) {
                goSearch();
            }
        });
    });

    function fnsetDate(MM, dd, num){
        var today = new Date();
        if(MM == 'all'){
            var staDate = "2019.01.01";
            var endDate = today.format('yyyy-MM-dd');
        }else{
            var endDate = today.format('yyyy-MM-dd');
            today.setMonth(today.getMonth() - MM);
            today = getCalDate(today, -dd);
            var staDate = today.format('yyyy-MM-dd');
        }
        $("#sdate").val(staDate);
        $("#rdate").val(endDate);

        $(".btn-fun__grad").removeClass("current");
        $(".btn-fun__grad:eq("+num+")").addClass("current");
        $("#funcNm").val(num);
    }

    function createView(){
        $("#content_id").val('');
        $('#qnaForm').attr({method:'post',action:"/front.board.create.dp/proc.go", target:"_self"}).submit();

    }

    //페이징
    function linkPage(idx) {
        $("#content_id").val('');
        if($("#searchWrd1").val() != ""){
            $("#searchWrd").val(encodeURIComponent($("#searchWrd1").val()));
        }

        if(idx <= 0 || idx > '${pageSize }') {
            return;
        }
        if('${vo.pageIndex}' != idx) {
            $("#pageIndex").val(idx);
            loadFrm('', 'qnaForm', 'post', '/front.board.list.dp/proc.go');
        }

    }

    function goSearch(){
        $("#content_id").val('');
        if($("#searchWrd1").val() != null && $("#searchWrd1").val() != ""){
            $("#searchWrd").val(encodeURIComponent($("#searchWrd1").val())); //데이터 인코딩
        }
        $("#searchBgnDe").val($("#sdate").val());
        $("#searchEndDe").val($("#rdate").val());
        $("#pageIndex").val("1");
        loadFrm('', 'qnaForm', 'post', '/front.board.list.dp/proc.go');
    }

    function boardRead(id){
        $("#content_id").val(id);
        $('#qnaForm').attr({method:'post',action:"/front.board.read.dp/proc.go", target:"_self"}).submit();
    }


    function pwPopup(id){
        $("#content_id").val(id);
        $('#pwPopup').addClass('is-visible');
    }

    function chkBoardPw(){
        if($("#board_pwd").val() == null ||$("#board_pwd").val() ==  ""){
            swal("<spring:message code='TEXT_31'/>");
            return;
        }

        var data = {
            "board_pwd" : $("#board_pwd").val(),
            "content_id": $("#content_id").val(),
            "board_id": $("#board_id").val()
        };
        postLoadigAjax("/front.board.chkBoardPw.dp/proc.go", data, chkBoardPwAfter);
    }

    function chkBoardPwAfter(data){
        if(data.resultCode > 0){
            boardRead(data.resultMsg);
        }else{
            swal(data.resultMsg).then($("#board_pwd").val(''));
        }
    }

</script>
</head>
<body>
<c:import url="/front.include.inc_header.dp/proc.go" charEncoding="utf-8" />
<main class="cd-main-content inquiry">
    <div class="contents-wrap">
    <form id="qnaForm" name="qnaForm" action="POST">
        <div class="tgroup">
            <h2><spring:message code="TEXT_15"/></h2>
            <ul>
                <li><spring:message code="TEXT_13"/></li>
                <li><spring:message code="TEXT_15"/></li>
            </ul>
        </div>
        <div class="search-container">
            <div class="search-wrap">
                <div class="date-wrap">
                    <div class="date">
                        <label><spring:message code="TEXT_402"/></label>
                        <span>
                            <input type="text" id="sdate" name="sdate" readonly="readonly" value="${vo.searchBgnDe}" class="readonly"/>
                        </span>
                        <strong>~</strong>
                        <span>
                            <input type="text" id="rdate" name="rdate" readonly="readonlcat_idy" value="${vo.searchEndDe}" class="readonly"/>
                        </span>
                    </div>
                    <div class="btn-wrap">
                        <ul class="inner">
                            <li><a onclick="fnsetDate(0, 0, 0)" class="btn-fun__grad"><spring:message code="TEXT_165"/></a></li>
                            <li><a onclick="fnsetDate(0, 7, 1)" class="btn-fun__grad"><spring:message code="TEXT_166"/></a></li>
                            <li><a onclick="fnsetDate(0, 15, 2)" class="btn-fun__grad"><spring:message code="TEXT_167"/></a></li>
                            <li><a onclick="fnsetDate(1, 0, 3)" class="btn-fun__grad"><spring:message code="TEXT_168"/></a></li>
                            <li><a onclick="fnsetDate(3, 0, 4)" class="btn-fun__grad"><spring:message code="TEXT_169"/></a></li>
                            <li><a onclick="fnsetDate('all', 0, 5)" class="btn-fun__grad"><spring:message code="TEXT_170"/></a></li>
                        </ul>
                    </div>
                    <input type="hidden" id="searchBgnDe" name="searchBgnDe" value="${vo.searchBgnDe}"/>
                    <input type="hidden" id="searchEndDe" name="searchEndDe" value="${vo.searchEndDe}" />
                </div>
                <div class="data-sort">
                    <div class="inquiry-type">
                        <label><spring:message code="TEXT_312"/></label>
                        <select name="cat_id" id="cat_id">
                            <option value=""><spring:message code="TEXT_170"/></option>
                            <c:if test="${!empty optList}">
                                <c:forEach items="${optList}" var="optList">
                                    <option value="${optList.cat_id}">${optList.cat_name}</option>
                                </c:forEach>
                            </c:if>
                        </select>
                    </div>
                    <div class="keyword-wrap">
                        <label><spring:message code="TEXT_313"/></label>
                        <span class="boxing">
							<select name="searchCnd" id="searchCnd">
								<option value="01"><spring:message code="TEXT_304"/></option>
								<option value="02"><spring:message code="TEXT_314"/></option>
								<option value="03"><spring:message code="TEXT_304"/>+<spring:message code="TEXT_314"/></option>
							</select>
							<input type="hidden" id="searchWrd" name="searchWrd" value="${vo.searchWrd}">
                            <span>
                                <input type="text" id="searchWrd1" name="searchWrd1" value="${vo.searchWrd}" placeholder="<spring:message code="TEXT_313"/>"/>
                            </span>
						</span>
                    </div>
                </div>
            </div>
            <a onclick="goSearch();" class="btn-search"><spring:message code="TEXT_302"/></a>
        </div>

        <div class="table-wrap">
            <table class="normal-table tb-text__center">
                <thead>
                <tr>
                    <th><spring:message code="TEXT_303"/></th>
                    <th><spring:message code="TEXT_304"/></th>
                    <th><spring:message code="TEXT_314"/></th>
                    <th><spring:message code="TEXT_315"/></th>
                </tr>
                </thead>
                <tbody>
                <c:choose>
                    <c:when test="${!empty list}">
                        <c:forEach items="${list}" var="list" varStatus="idx">
                            <tr>
                                <td>${(vo.pageIndex - 1) * 10 + idx.count}</td>
                                <c:choose>
                                    <c:when test="${list.secret_yn eq 'Y'}">
                                        <c:choose>
                                            <c:when test="${sessionScope.LOGIN_SESSION.userEmail eq list.reg_user}">
                                                <td><a onclick="javascript:boardRead(${list.content_id});"><p><em>[${list.cat_name}]</em>${list.board_title}</p></a></td>
                                            </c:when>
                                            <c:otherwise>
                                                <td><a href="javascript:pwPopup('${list.content_id}');"><p><em>[${list.cat_name}]</em>${list.board_title}</p></a></td>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:when>
                                    <c:otherwise><%-- 수정 필요 --%>
                                        <td><a href="javascript:pwPopup('${list.content_id}');"><p><em>[${list.cat_name}]</em>${list.board_title}</p></a></td>
                                    </c:otherwise>
                                </c:choose>
                                <td>${list.user_name}</td>
                                <td>${list.reg_dt}</td>
                            </tr>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                       <tr>
                           <td colspan="4"><spring:message code="TEXT_206"/></td>
                       </tr>
                    </c:otherwise>
                </c:choose>
                </tbody>
            </table>
        </div>
        <div class="btn-area">
            <a href="javascript:createView();" class="btn-inquiry"><spring:message code="TEXT_15"/></a>
        </div>
        <fmt:parseNumber var="firstPage" value="${(vo.pageIndex -1) / vo.pageUnit}" integerOnly="true" />
        <fmt:parseNumber var="firstPage" value="${firstPage * vo.pageUnit + 1}" integerOnly="true" />
        <fmt:parseNumber var="lastPage" value="${firstPage + vo.pageUnit - 1}" integerOnly="true" />
        <c:if test="${lastPage > pageSize}">
            <c:set var="lastPage" value="${pageSize}"/>
        </c:if>

        <div class="pagination">
            <div class="pagination_wrap">
                <ul>
                    <c:forEach var="i" begin="${firstPage }" end="${lastPage}" varStatus="idx">
                        <li><a href="#n" onclick="linkPage(${idx.index});return false;" class="<c:if test='${idx.index eq vo.pageIndex}'>on</c:if>">${idx.index }</a></li>
                    </c:forEach>
                </ul>
                <a onclick="linkPage(${vo.pageIndex - 1});return false;" class="prev xi-angle-left"><span>이전</span></a>
                <a onclick="linkPage(${vo.pageIndex + 1});return false;" class="next xi-angle-right"><span>다음</span></a>
            </div>
        </div>

        <input type="hidden" id="board_id" name="board_id" value="${vo.board_id}">
        <input type="hidden" id="content_id" name="content_id">
        <input type="hidden" id="funcNm" name ="funcNm">
        <input type="hidden" name="pageIndex" id="pageIndex" value="${vo.pageIndex }">
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