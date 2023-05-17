<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<c:import url="/front.include.common_front_head.dp/proc.go"	charEncoding="utf-8" />
</head>
<script>
    $(document).ready(function(){
        // tab menu
        $('input[type="text"]').keydown(function() {
            if (event.keyCode === 13) {
                goSearch();
            }
        });

    });
    //페이징
    function linkPage(idx) {
        if($("#searchWrd").val() != ""){
            $("#searchWrd").val(encodeURIComponent($("#searchWrd1").val()));
        }

        if(idx <= 0 || idx > '${pageSize }') {
            return;
        }
        if('${vo.pageIndex}' != idx) {
            $("#pageIndex").val(idx);
            var url = "/front.board.list.dp/proc.go";
            var frm = "noticeForm";
            var methodtype = "post";
            $('#'+frm).attr({method:methodtype,action:url, target:"_self"}).submit();
        }
    }

    function goSearch(){
        $("#searchWrd").val(encodeURIComponent($("#searchWrd1").val())); //데이터 인코딩
        $("#pageIndex").val("1");
        loadFrm('', 'noticeForm', 'post', '/front.board.list.dp/proc.go');
    }

</script>
<body>
<c:import url="/front.include.inc_header.dp/proc.go" charEncoding="utf-8" />
<main class="cd-main-content faq">
    <div class="contents-wrap">
        <div class="tgroup">
            <h2><spring:message code="TEXT_424"/></h2>
            <ul>
                <li><spring:message code="TEXT_13"/></li>
                <li><spring:message code="TEXT_424"/></li>
            </ul>
        </div>
        <div class="searchTab-wrap">
            <form id="noticeForm" name="noticeForm" action="POST">
            <div id="tab1" class="search-container ui tab active">
                <div class="search-wrap">
                    <input type="hidden" id="searchWrd" name="searchWrd" value="${vo.searchWrd}">
                    <span>
                        <input type="text" id="searchWrd1" name="searchWrd1" value="${vo.searchWrd}"/>
                    </span>
                    <a href="javascript:goSearch();" class="btn-search"><spring:message code="TEXT_173"/></a>
                </div>
                <div class="tabDown col-2">
                    <div class="tabDownHead">
                        <ul>
                            <li><spring:message code="TEXT_303"/></li>
                            <li><spring:message code="TEXT_304"/></li>
                        </ul>
                    </div>
                    <dl class="tabDownBody">
                        <c:choose>
                            <c:when test="${!empty list}">
                                <c:forEach items="${list}" var="list" varStatus="idx">
                                    <dt>
                                        <ul>
                                            <li>${(vo.pageIndex - 1) * 10 + idx.count}</li>
                                            <li>${list.board_title }</li>
                                        </ul>
                                    </dt>
                                    <dd>
                                        <div class="tab-cover">
                                            <p>${list.content_msg }</p>
                                        </div>
                                    </dd>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <dt>
                                    <ul>
                                        <li><spring:message code="TEXT_206"/></li>
                                    </ul>
                                </dt>
                            </c:otherwise>
                        </c:choose>
                    </dl>
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
            </div>
            <input type="hidden" id="board_id" name="board_id" value="${vo.board_id}">
            <input type="hidden" name="pageIndex" id="pageIndex" value="${vo.pageIndex }">
            <input type="hidden" name="cat_id" id="cat_id" value="${vo.cat_id}">
            <script type="text/javascript" src="/js/swiper.min.js"></script>
            <script>
                // 슬라이드 메뉴
                $(function() {
                    var swiper = new Swiper('.slide_mainMenu', {
                        slidesPerView: 6,
                        spaceBetween: 0,
                        // init: false,
                        pagination: {
                            el: '.swiper-pagination',
                            clickable: true,
                        },
                        breakpoints: {
                            1024: {
                                slidesPerView: 6,
                                spaceBetween: 00,
                            },
                            768: {
                                slidesPerView: 3,
                                spaceBetween: 0,
                            },
                            640: {
                                slidesPerView: 3,
                                spaceBetween: 0,
                            },
                            320: {
                                slidesPerView: 3,
                                spaceBetween: 0,
                            }
                        }
                    });
                });
            </script>
        </form>
        </div>
    </div>
</main>
<c:import url="/front.include.inc_footer.ds/proc.go" charEncoding="utf-8" />
</body>
</html>