<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<c:import url="/front.include.common_front_head.dp/proc.go"	charEncoding="utf-8" />
</head>
<body>
<c:import url="/front.include.inc_header.dp/proc.go" charEncoding="utf-8" />


<main class="cd-main-content my_usb">
    <div class="contents-wrap">
        <div class="tgroup">
            <h2>USB 관리</h2>
            <ul>
                <li>Wallet</li>
                <li>USB 관리</li>
            </ul>
        </div>
        <div class="usbId-wrap">
            <label for="">USB ID</label>
            <select>
                <option value="">USB ID 선택</option>
                <option value="">D121adfasfgt</option>
                <option value="">D122adfasfgt</option>
            </select>
            <a href="#n" class="btn-send popup01">회원가입 URL 발송</a>
        </div>
        <div class="searchTab-wrap">
            <div class="tab-menu">
                <a class="item active" data-tab="tab1">사용자리스트</a>
                <a class="item" data-tab="tab2">사용자활동내역</a>
            </div>
            <div id="tab1" class="search-container tab active" data-tab="first">
                <div class="cell">
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
                    </div>
                    <a href="" class="btn-period">검색</a>
                </div>
                <div class="cell">
                    <h3 class="subTtile">상세목록</h3>
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
                                <th>이름</th>
                                <th>이메일</th>
                                <th>USB 소유자  배분금액</th>
                                <th>가입일시</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td>홍*동</td>
                                <td>ab**@naver.com</td>
                                <td>1 ADM</td>
                                <td>18-06-28 00:00:00</td>
                            </tr>
                            <tr>
                                <td>박*수</td>
                                <td>bo**@naver.com</td>
                                <td>1 ADM</td>
                                <td>18-06-28 00:00:00</td>
                            </tr>
                            <tr>
                                <td>김*희</td>
                                <td>wk**@naver.com</td>
                                <td>1 ADM</td>
                                <td>18-06-28 00:00:00</td>
                            </tr>
                            <tr>
                                <td>김*동</td>
                                <td>op**@naver.com</td>
                                <td>1 ADM</td>
                                <td>18-06-28 00:00:00</td>
                            </tr>
                            <tr>
                                <td>박*동</td>
                                <td>vp**@naver.com</td>
                                <td>1 ADM</td>
                                <td>18-06-28 00:00:00</td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <div id="tab2" class="search-container tab" data-tab="second">
                <div class="cell">
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
                    </div>
                    <a href="" class="btn-period">검색</a>
                </div>
                <div class="cell">
                    <h3 class="subTtile">상세목록</h3>
                    <div class="t-controler">
                        <a href="" class="backward"><i class="xi-backward"></i></a>
                        <a href="" class="prev"><i class="xi-play"></i></a>
                        <input type="text" />
                        <a href="" class="next"><i class="xi-play"></i></a>
                        <a href="" class="forward"><i class="xi-forward"></i></a>
                    </div>
                    <div class="table-wrap">
                        <table id="dataTable02" class="display responsive nowrap" style="width: 100%;">
                            <thead>
                            <tr>
                                <th>이름</th>
                                <th>이메일</th>
                                <th>USB 소유자  배분금액</th>
                                <th>지급일시</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td>홍*동</td>
                                <td>ab**@naver.com</td>
                                <td>1 ADM</td>
                                <td>18-06-28 00:00:00</td>
                            </tr>
                            <tr>
                                <td>박*수</td>
                                <td>bo**@naver.com</td>
                                <td>1 ADM</td>
                                <td>18-06-28 00:00:00</td>
                            </tr>
                            <tr>
                                <td>김*희</td>
                                <td>wk**@naver.com</td>
                                <td>1 ADM</td>
                                <td>18-06-28 00:00:00</td>
                            </tr>
                            <tr>
                                <td>김*동</td>
                                <td>op**@naver.com</td>
                                <td>1 ADM</td>
                                <td>18-06-28 00:00:00</td>
                            </tr>
                            <tr>
                                <td>박*동</td>
                                <td>vp**@naver.com</td>
                                <td>1 ADM</td>
                                <td>18-06-28 00:00:00</td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
        <script>
            // tab menu
            $('.tab-menu a').click(function() {
                var activeTab = $(this).attr('data-tab');
                $('.tab-menu a').removeClass('active');
                $('.search-container').removeClass('active');
                $(this).addClass('active');
                $('#' + activeTab).addClass('active');
            });
            $(document).ready(function() {
                $('table.display').DataTable();
            });
        </script>
    </div>
</main>

<c:import url="/front.include.inc_footer.ds/proc.go" charEncoding="utf-8" />
</body>
</html>