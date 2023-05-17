<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<c:import url="/front.include.common_front_head.dp/proc.go"	charEncoding="utf-8" />
</head>
<body>
<c:import url="/front.include.inc_header.dp/proc.go" charEncoding="utf-8" />
<main class="cd-main-content purchase-info">
    <div class="contents-wrap">
        <div class="tgroup">
            <h2>제품 구매정보</h2>
            <ul>
                <li>제품소개</li>
                <li>제품 구매정보</li>
            </ul>
        </div>
        <div class="container">
            <div class="cell">
                <div class="pd-imgWrap">
                    <!-- Swiper -->
                    <div class="swiper-container gallery-top" style="height: 280px">
                        <div class="swiper-wrapper">
                            <div class="swiper-slide"><img src="/images/front/sub/img_usb.jpg" alt="" /></div>
                            <div class="swiper-slide"><img src="/images/front/sub/img_usb02.jpg" alt="" /></div>
                            <div class="swiper-slide"><img src="/images/front/sub/img_usb03.jpg" alt="" /></div>
                        </div>
                        <!-- Add Arrows -->
                        <div class="swiper-button-next swiper-button-white"></div>
                        <div class="swiper-button-prev swiper-button-white"></div>
                    </div>
                    <div class="swiper-container gallery-thumbs" style="height: 88px">
                        <div class="swiper-wrapper">
                            <div class="swiper-slide"><img src="/images/front/sub/img_usb.jpg" alt="" /></div>
                            <div class="swiper-slide"><img src="/images/front/sub/img_usb02.jpg" alt="" /></div>
                            <div class="swiper-slide"><img src="/images/front/sub/img_usb03.jpg" alt="" /></div>
                        </div>
                    </div>
                    <script type="text/javascript" src="/js/swiper.min.js"></script>
                    <script>
                        var galleryThumbs = new Swiper('.gallery-thumbs', {
                            spaceBetween: 10,
                            slidesPerView: 3,
                            freeMode: true,
                            watchSlidesVisibility: true,
                            watchSlidesProgress: true,
                        });
                        var galleryTop = new Swiper('.gallery-top', {
                            spaceBetween: 10,
                            navigation: {
                                nextEl: '.swiper-button-next',
                                prevEl: '.swiper-button-prev',
                            },
                            thumbs: {
                                swiper: galleryThumbs
                            }
                        });
                    </script>
                </div>
                <div class="pd-info">
                    <h3>ADM Mining USB</h3>
                    <ul class="detail">
                        <li>
                            <dl>
                                <dt>모델명</dt>
                                <dd>ADM Mining USB</dd>
                            </dl>
                        </li>
                        <li>
                            <dl>
                                <dt>크기, 무게</dt>
                                <dd>26x45</dd>
                            </dl>
                        </li>
                        <li>
                            <dl>
                                <dt>용량</dt>
                                <dd>32GB</dd>
                            </dl>
                        </li>
                        <li>
                            <dl>
                                <dt>제조국</dt>
                                <dd>Korea</dd>
                            </dl>
                        </li>
                        <li>
                            <dl>
                                <dt>판매가</dt>
                                <dd>12,000원</dd>
                            </dl>
                        </li>
                        <li>
                            <dl>
                                <dt>USB기능</dt>
                                <dd>ADM획득장치/Cold Wallet/일반 USB</dd>
                            </dl>
                        </li>
                    </ul>
                </div>
            </div>
            <div class="cell">
                <div class="tab_container">
                    <ul class="tabs">
                        <li data-tab="tab1" class="active"><a href="#n">오프라인정보</a></li>
                        <li data-tab="tab2"><a href="#n">온라인정보</a></li>
                    </ul>
                    <div class="tab_contents">
                        <div id="tab1" class="tab_content active">
                            <table class="basic-table">
                                <colgroup>
                                    <col width="25%" />
                                    <col width="50%" />
                                    <col width="25%" />
                                </colgroup>
                                <thead>
                                <tr>
                                    <th>오프라인 지점명</th>
                                    <th>정보</th>
                                    <th>오시는길</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <th>지점1</th>
                                    <td>
                                        <ul class="dotStyle">
                                            <li>주소 : 서울시 역삼로 128 건영빌딩 3층</li>
                                            <li>070-4166-1996</li>
                                        </ul>
                                    </td>
                                    <td><a href="#n" class="popup01">오시는길</a></td>
                                </tr>
                                <tr>
                                    <th>지점2</th>
                                    <td>
                                        <ul class="dotStyle">
                                            <li>주소 : 서울시 역삼로 128 건영빌딩 3층</li>
                                            <li>070-4166-1996</li>
                                        </ul>
                                    </td>
                                    <td><a href="#n" class="popup01">오시는길</a></td>
                                </tr>
                                <tr>
                                    <th>지점3</th>
                                    <td>
                                        <ul class="dotStyle">
                                            <li>주소 : 서울시 역삼로 128 건영빌딩 3층</li>
                                            <li>070-4166-1996</li>
                                        </ul>
                                    </td>
                                    <td><a href="#n" class="popup01">오시는길</a></td>
                                </tr>
                                <tr>
                                    <th>지점4</th>
                                    <td>
                                        <ul class="dotStyle">
                                            <li>주소 : 서울시 역삼로 128 건영빌딩 3층</li>
                                            <li>070-4166-1996</li>
                                        </ul>
                                    </td>
                                    <td><a href="#n" class="popup01">오시는길</a></td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                        <div id="tab2" class="tab_content">
                            <table class="basic-table">
                                <colgroup>
                                    <col width="50%" />
                                    <col width="50%" />
                                </colgroup>
                                <thead>
                                <tr>
                                    <th>판매처</th>
                                    <th>정보</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <th>사이트명1</th>
                                    <td><a href="#n">wwww.cmesoft.co.kr</a></td>
                                </tr>
                                <tr>
                                    <th>사이트명1</th>
                                    <td><a href="#n">wwww.cmesoft.co.kr</a></td>
                                </tr>
                                <tr>
                                    <th>사이트명1</th>
                                    <td><a href="#n">wwww.cmesoft.co.kr</a></td>
                                </tr>
                                <tr>
                                    <th>사이트명1</th>
                                    <td><a href="#n">wwww.cmesoft.co.kr</a></td>
                                </tr>
                                <tr>
                                    <th>사이트명1</th>
                                    <td><a href="#n">wwww.cmesoft.co.kr</a></td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <script>
                        // tab menu
                        $('ul.tabs li').click(function() {
                            var activeTab = $(this).attr('data-tab');
                            $('ul.tabs li').removeClass('active');
                            $('.tab_content').removeClass('active');
                            $(this).addClass('active');
                            $('#' + activeTab).addClass('active');
                        });
                    </script>
                </div>
            </div>
        </div>
    </div>
</main>
<!-- popup -->
<div class="cd-popup popupCon01" role="alert">
    <div class="cd-popup-container wide-contents">
        <div class="cd-popup-mid">
            <h3>오시는길</h3>
            <div class="map-area">
                <img src="/images/front/sub/map.jpg" alt="" />
            </div>
        </div>
        <a href="#0" class="cd-popup-close img-replace"></a>
    </div>
</div>

<c:import url="/front.include.inc_footer.ds/proc.go" charEncoding="utf-8" />
</body>
</html>