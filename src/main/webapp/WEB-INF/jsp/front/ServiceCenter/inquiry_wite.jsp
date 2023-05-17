<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<c:import url="/front.include.common_front_head.ds/proc.go"	charEncoding="utf-8" />
</head>
<body>
<c:import url="/front.include.inc_header.dp/proc.go" charEncoding="utf-8" />
<main class="cd-main-content inquiry-write">
    <div class="contents-wrap">
        <div class="tgroup">
            <h2>문의하기</h2>
            <ul>
                <li>고객센터</li>
                <li>문의하기</li>
            </ul>
        </div>
        <div class="check-list">
            <div class="cell">
                <ul class="dotStyle">
                    <li><strong>수집목적 :</strong>문의접수에 대한 처리 및 회신</li>
                    <li><strong>수집항목 :</strong>이름,이메일,연락처의 개인정보 및 그 외 문의제목,문의내용 등 개인이 직접 입력한 내용</li>
                    <li><strong>보유기간 :</strong>문의 처리 후 3년간 보관</li>
                </ul>
                <em class="noti">※ 수집한 개인정보는 서비스 이용기간동안 보관하고 있으며, 이외의 다른 목적으로는 사용되지 않습니다.</em>
            </div>
            <div class="check-wrap">
                <input type="checkbox" id="c1" class="iCheck" /><label for="c1">위에 내용에 동의합니다.</label>
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
                    <th>문의유형</th>
                    <td>
                        <select>
                            <option value="">선택</option>
                            <option value="">입금문의</option>
                            <option value="">출금문의</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th>이메일</th>
                    <td><input type="email" placeholder="이메일을 입력하세요." /></td>
                </tr>
                <tr>
                    <th>이름</th>
                    <td><input type="text" placeholder="실명을 입력하세요." /></td>
                </tr>
                <tr>
                    <th>제목</th>
                    <td><input type="text" placeholder="제목을 입력하세요." /></td>
                </tr>
                <tr>
                    <th>내용</th>
                    <td><textarea rows="10" style="width: 100%;" placeholder="문의하실 내용을 입력하세요."></textarea></td>
                </tr>
                <tr>
                    <th>연락처</th>
                    <td><input type="text" placeholder="휴대폰 번호를 입력하세요." /></td>
                </tr>
                <tr>
                    <th>비밀번호</th>
                    <td><input type="password" placeholder="비밀번호를 입력하세요." /> <p class="noti">※ 문의등록 후, 답변확인시 필요한 비밀번호 입니다.</p></td>
                </tr>
                </tbody>
            </table>
        </div>
        <ul class="btn-area">
            <li><a href="inquiry.html" class="btn-cancel">취소</a></li>
            <li><a href="inquiry.html" class="btn-submit">접수</a></li>
        </ul>
    </div>
</main>
<c:import url="/front.include.inc_footer.ds/proc.go" charEncoding="utf-8" />
</body>
</html>