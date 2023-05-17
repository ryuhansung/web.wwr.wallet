<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<c:import url="/front.include.common_front_head.ds/proc.go"	charEncoding="utf-8" />
</head>
<body>
<c:import url="/front.include.inc_header.dp/proc.go" charEncoding="utf-8" />
<main class="cd-main-content inquiry-view">
    <div class="contents-wrap">
        <div class="tgroup">
            <h2>문의하기</h2>
            <ul>
                <li>고객센터</li>
                <li>문의하기</li>
            </ul>
        </div>
        <div class="inquiry-contents">
            <h3 class="iq-title"><em>[기타문의]</em>문의합니다</h3>
            <div class="author-info">
                <dl class="author">
                    <dt>작성자 :</dt><dd>홍길동(aaa@gmail.com)</dd>
                </dl>
                <dl class="date">
                    <dt>작성일 :</dt><dd>2018-07-19</dd>
                </dl>
                <dl class="mobile">
                    <dt>연락처 :</dt><dd>01012345678</dd>
                </dl>
            </div>
            <div class="cell">
                <div class="inner">
                    <p>문의관련해서 문의할께요</p>
                </div>
                <ul class="btn-list">
                    <li><a href="#n" class="popup01"><i class="xi-pen"></i>수정</a></li>
                    <li><a href="#n" class="popup01"><i class="xi-trash-o"></i>삭제</a></li>
                </ul>
            </div>
            <div class="answer-wrap">
                <div class="author-info">
                    <dl class="author">
                        <dt>작성자 :</dt><dd>관리자</dd>
                    </dl>
                    <dl class="date">
                        <dt>작성일 :</dt><dd>2018-07-19</dd>
                    </dl>
                </div>
                <div class="inner">
                    <p>문의 주셔서 감사합니다.문의관련해서 안내드립니다.</p>
                </div>
            </div>
        </div>
        <div class="btn-area">
            <a href="inquiry.html" class="btn-inquiry">목록</a>
        </div>
    </div>
</main>

<!-- popup -->
<div class="cd-popup popupCon01" role="alert">
    <div class="cd-popup-container">
        <div class="cd-popup-mid">
            <h3>비밀번호입력</h3>
            <p class="desc">비밀번호를 입력해주세요.</p>
            <input type="password" style="width: 100%;" class="insert-data" placeholder="비밀번호" />
        </div>
        <ul class="cd-buttons">
            <li><a href="#0" class="btnPoint">확인</a></li>
        </ul>
        <a href="#0" class="cd-popup-close img-replace"></a>
    </div>
</div>
<c:import url="/front.include.inc_footer.ds/proc.go" charEncoding="utf-8" />
</body>
</html>