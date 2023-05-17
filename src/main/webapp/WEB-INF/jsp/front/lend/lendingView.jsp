<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<c:import url="/front.include.common_front_head.dp/proc.go"	charEncoding="utf-8" />
</head>
<body>
<c:import url="/front.include.inc_header.dp/proc.go" charEncoding="utf-8" />

<main class="cd-main-content landing">
    <div class="contents-wrap">
        <div class="tgroup">
            <h2><spring:message code="TEXT_425"/></h2>
            <ul>
                <li><spring:message code="TEXT_02"/></li>
                <li><spring:message code="TEXT_425"/></li>
            </ul>
        </div>
        <h3 class="subTtile"><spring:message code="TEXT_426"/></h3>
        <div class="table-wrap">
            <table class="normal-table">
                <colgroup>
                    <col width="20%" />
                    <col width="80%" />
                </colgroup>
                <tbody>
                <tr>
                    <th><spring:message code="TEXT_427"/></th>
                    <td>${lvo.getUser_email()}</td>
                </tr>
                <tr>
                    <th><spring:message code="TEXT_428"/></th>
                    <td>${lvo.getReg_dt()}</td>
                </tr>
                <tr>
                    <th><spring:message code="TEXT_429"/></th>
                    <td>${lvo.getExpire_dt()}</td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="table-wrap">
            <table class="normal-table">
                <colgroup>
                    <col width="33.333%" />
                    <col width="33.333%" />
                    <col width="33.333%" />
                </colgroup>
                <thead>
                <tr>
                    <th><spring:message code="TEXT_430"/></th>
                    <th><spring:message code="TEXT_431"/></th>
                    <th><spring:message code="TEXT_432"/></th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td style="text-align: center">${lvo.getLend_prc()} <spring:message code="TEXT_433"/></td>
                    <td style="text-align: center">${lvo.getRcv_tot_prc()} <spring:message code="TEXT_433"/></td>
                    <td style="text-align: center">${lvo.getRam_prc()} <spring:message code="TEXT_433"/></td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</main>
<c:import url="/front.include.inc_footer.ds/proc.go" charEncoding="utf-8" />
</body>
</html>