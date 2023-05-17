<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<c:import url="/front.include.common_front_head.dp/proc.go"	charEncoding="utf-8" />
</head>
<body>
<c:import url="/front.include.inc_header.dp/proc.go" charEncoding="utf-8" />

<main class="cd-main-content activity">
    <div class="contents-wrap">
        <div class="tgroup">
            <h2>ADM활동현황</h2>
            <ul>
                <li>Wallet</li>
                <li>ADM활동현황</li>
            </ul>
        </div>
        <div class="search-container period">
            <div class="search-wrap">
                <div class="date-wrap">
                    <div class="date">
                        <label>기간</label>
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
                <span class="usbId">
					<select>
						<option value="">USB ID 선택</option>
						<option value="">D121adfasfgt</option>
						<option value="">D122adfasfgt</option>
					</select>
				</span>
            </div>
            <a href="" class="btn-period">검색</a>
        </div>
        <h3 class="subTtile">총 ADM활동횟수 <span class="num">12</span></h3>
        <div class="t-controler">
            <a href="" class="backward"><i class="xi-backward"></i></a>
            <a href="" class="prev"><i class="xi-play"></i></a>
            <input type="text" />
            <a href="" class="next"><i class="xi-play"></i></a>
            <a href="" class="forward"><i class="xi-forward"></i></a>
        </div>
        <div class="table-wrap">
            <table id="dataTable" class="display responsive nowrap" style="width: 100%;">
                <thead>
                <tr>
                    <th>번호</th>
                    <th>시작일시</th>
                    <th>종료일시</th>
                    <th>USB ID</th>
                    <th>구분</th>
                    <th>국가</th>
                    <th>지역</th>
                    <th>콘텐츠 구분1</th>
                    <th>콘텐츠 구분2</th>
                    <th>콘텐츠 제목</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>10</td>
                    <td>18-06-28<br/>00:00:00</td>
                    <td>18-06-28<br/>12:00:00</td>
                    <td>D121adfasfgt</td>
                    <td>PC</td>
                    <td>한국</td>
                    <td>서울</td>
                    <td>Game</td>
                    <td>Level 1</td>
                    <td>게임</td>
                </tr>
                <tr>
                    <td>9</td>
                    <td>18-06-28<br/>00:00:00</td>
                    <td>18-06-28<br/>12:00:00</td>
                    <td>D121adfasfgt</td>
                    <td>PC</td>
                    <td>한국</td>
                    <td>경기</td>
                    <td>Acting</td>
                    <td>Raffle</td>
                    <td>동영상</td>
                </tr>
                <tr>
                    <td>8</td>
                    <td>18-06-28<br/>00:00:00</td>
                    <td>18-06-28<br/>12:00:00</td>
                    <td>D121adfasfgt</td>
                    <td>APP</td>
                    <td>한국</td>
                    <td>서울</td>
                    <td>View</td>
                    <td>Video</td>
                    <td>동영상</td>
                </tr>
                </tbody>
            </table>
            <script>
                $(document).ready(function() {
                    $('#dataTable').DataTable();
                });
            </script>
        </div>
    </div>
</main>

<c:import url="/front.include.inc_footer.ds/proc.go" charEncoding="utf-8" />
</body>
</html>