<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<c:import url="/front.include.common_front_head.dp/proc.go"	charEncoding="utf-8" />
</head>
<body>
<c:import url="/front.include.inc_header.dp/proc.go" charEncoding="utf-8" />

<main class="cd-main-content sign_up">
    <div class="contents-wrap">
        <div class="short-wrap step03">
            <h2>회원가입</h2>
            <p class="desc">개인키(12가지 암호)를 참고하면서 아래의 단어들을 순서대로 입력해주세요.</p>
            <div class="security-wrap">
                <dl>
                    <dt><input type="text" placeholder="●●" /></dt>
                    <dd>9번 단어</dd>
                </dl>
                <dl>
                    <dt><input type="text" placeholder="●●" /></dt>
                    <dd>1번 단어</dd>
                </dl>
                <dl>
                    <dt><input type="text" placeholder="●●" /></dt>
                    <dd>13번 단어</dd>
                </dl>
                <dl>
                    <dt><input type="text" placeholder="●●" /></dt>
                    <dd>36번 단어</dd>
                </dl>
            </div>
            <div class="bottom-btnWrap">
                <a href="sign_up_step04.html" class="btn-primary">다음단계</a>
            </div>
        </div>
    </div>
</main>
<c:import url="/front.include.inc_footer.ds/proc.go" charEncoding="utf-8" />
</body>
</html>