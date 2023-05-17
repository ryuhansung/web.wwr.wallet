<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<c:import url="/front.include.common_front_head.dp/proc.go"	charEncoding="utf-8" />
</head>
<body>
<c:import url="/front.include.inc_header.dp/proc.go" charEncoding="utf-8" />

<main class="cd-main-content mnemonic">
    <div class="contents-wrap">
        <div class="subTab">
            <ul>
                <li><a href="remittance.html">송금</a></li>
                <li><a href="mnemonic.html" class="current">Mnemonic 송금</a></li>
            </ul>
        </div>
        <div class="tgroup">
            <h2>Mnemonic 송금</h2>
            <ul>
                <li>Wallet</li>
                <li>송금</li>
                <li>Mnemonic 송금</li>
            </ul>
        </div>
        <div class="short-wrap">
            <h2>Mnemonic 송금 확인</h2>
            <p class="desc">빈칸에 ADM ColdWallet PC프로그램에서 지갑생성시 보관하고 계신복구단어 및 출금암호를 입력해주세요.<br>(출금암호는 ADM ColdWallet에서 설정한 회원만 입력해주세요)</p>
            <h3>복구단어</h3>
            <ul class="myKeywords">
                <li><input type="text" placeholder="단어1" /></li>
                <li><input type="text" placeholder="단어2" /></li>
                <li><input type="text" placeholder="단어3" /></li>
                <li><input type="text" placeholder="단어4" /></li>

                <li><input type="text" placeholder="단어5" /></li>
                <li><input type="text" placeholder="단어6" /></li>
                <li><input type="text" placeholder="단어7" /></li>
                <li><input type="text" placeholder="단어8" /></li>

                <li><input type="text" placeholder="단어9" /></li>
                <li><input type="text" placeholder="단어10" /></li>
                <li><input type="text" placeholder="단어11" /></li>
                <li><input type="text" placeholder="단어12" /></li>
            </ul>
            <h3>출금암호</h3>
            <span class="withdrawal-pw"><input type="text" placeholder="출금암호 입력(필수)" /></span>
            <div class="bottom-btnWrap">
                <a href="sign_up_step03.html" class="btn-primary">확인</a>
            </div>
        </div>
    </div>
</main>

<c:import url="/front.include.inc_footer.ds/proc.go" charEncoding="utf-8" />
</body>
</html>