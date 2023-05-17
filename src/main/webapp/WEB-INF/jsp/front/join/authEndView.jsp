<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <c:import url="/front.include.common_front_head.dp/proc.go"	charEncoding="utf-8" />
</head>
<script type="text/javascript">
    $(document).ready(function() {
        swal('${res.resultMsg}').then(function() {
            window.close();
        });
    });
</script>
<body class="${lang}">
<main class="cd-main-content sign_up">
    <div class="contents-wrap">
        <div class="tgroup">
        </div>
    </div>
</main>
<c:import url="/front.include.inc_footer.ds/proc.go" charEncoding="utf-8" />
</body>
</html>