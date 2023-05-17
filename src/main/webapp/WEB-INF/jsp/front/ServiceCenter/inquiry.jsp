<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<c:import url="/front.include.common_front_head.ds/proc.go"	charEncoding="utf-8" />
</head>
<body>
<c:import url="/front.include.inc_header.dp/proc.go" charEncoding="utf-8" />
<main class="cd-main-content inquiry">
    <div class="contents-wrap">
        <div class="tgroup">
            <h2>문의하기</h2>
            <ul>
                <li>고객센터</li>
                <li>문의하기</li>
            </ul>
        </div>
        <div class="search-container">
            <div class="search-wrap">
                <div class="date-wrap">
                    <div class="date">
                        <label>조회</label>
                        <span><input type="text" /></span><strong>~</strong><span><input type="text" /></span>
                    </div>
                    <div class="btn-wrap">
                        <ul class="inner">
                            <li><a href="#n">오늘</a></li>
                            <li><a href="#n" class="current">7일</a></li>
                            <li><a href="#n">15일</a></li>
                            <li><a href="#n">1개월</a></li>
                            <li><a href="#n">3개월</a></li>
                            <li><a href="#n">전체</a></li>
                        </ul>
                    </div>
                </div>
                <div class="data-sort">
                    <div class="inquiry-type">
                        <label>문의유형</label>
                        <select>
                            <option value="">전체</option>
                            <option value="">입금문의</option>
                            <option value="">출금문의</option>
                        </select>
                    </div>
                    <div class="keyword-wrap">
                        <label>검색어</label>
                        <span class="boxing">
							<select>
								<option value="">제목</option>
								<option value="">글쓴이</option>
								<option value="">제목+글쓴이</option>
							</select>
							<input type="text" />
						</span>
                    </div>
                </div>
            </div>
            <a href="" class="btn-search">검색</a>
        </div>

        <div class="table-wrap">
            <table class="normal-table tb-text__center">
                <thead>
                <tr>
                    <th>번호</th>
                    <th>제목</th>
                    <th>작성자</th>
                    <th>작성일</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>10</td>
                    <td><a href="/front.ServiceCenter.inquiry_view.ds/proc.go"><p><em>[기타문의]</em>문의합니다</p></a></td>
                    <td>홍*동</td>
                    <td>2018-07-19 00:00:00</td>
                </tr>
                <tr class="answer">
                    <td></td>
                    <td><a href="#n" class="popup01"><p><em>[기타문의]</em>문의합니다</p></a></td>
                    <td><strong class="cs-center">관리자</strong></td>
                    <td></td>
                </tr>
                <tr>
                    <td>9</td>
                    <td><a href="/front.ServiceCenter.inquiry_view.ds/proc.go"><p><em>[기타문의]</em>문의합니다</p></a></td>
                    <td>홍*동</td>
                    <td>2018-07-19 00:00:00</td>
                </tr>
                <tr>
                    <td>8</td>
                    <td><a href="/front.ServiceCenter.inquiry_view.ds/proc.go"><p><em>[기타문의]</em>문의합니다</p></a></td>
                    <td>홍*동</td>
                    <td>2018-07-19 00:00:00</td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="btn-area">
            <a href="/front.ServiceCenter.inquiry_wite.ds/proc.go" class="btn-inquiry">문의하기</a>
        </div>
        <div class="pagination">
            <div class="pagination_wrap">
                <ul>
                    <li><a href="#" class="on">1</a></li>
                    <li><a href="#">2</a></li>
                </ul>
                <a href="#" class="prev xi-angle-left"><span>이전</span></a>
                <a href="#" class="next xi-angle-right"><span>다음</span></a>
            </div>
        </div>
    </div>
</main>
<c:import url="/front.include.inc_footer.ds/proc.go" charEncoding="utf-8" />
</body>
</html>