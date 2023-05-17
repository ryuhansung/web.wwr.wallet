<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<c:import url="/front.include.common_front_head.dp/proc.go"	charEncoding="utf-8" />
	</head>
	<script>
		$(document).ready(function() {
			if('${failMsg}'.length > 0){
				if(window.TeleditApp){
					window.TeleditApp.BestClose("<spring:message code='02_16'/> ${failMsg}");
				}else{
                    swal("<spring:message code='02_16'/> ${failMsg}").then(function () {
                        closePop();
                    });
				}
			}else if('${map.RETURNCODE}' == '998'){
                opener.parent.failInfo();
                if(window.TeleditApp){
                    window.TeleditApp.BestClose("<spring:message code='02_11'/>");
                }else{
                    swal("<spring:message code='02_11'/> ${failMsg}").then(function () {
                        closePop();
                    });
                }
			}else if('${map.RETURNCODE}' == '0000'){								//본인인증 성공
				if(opener.parent.receiveInfo)
				{
					var chk = opener.parent.receiveInfo('${map.IsDstAddr}','${map.NAME}');
					if(chk){
                        if(window.TeleditApp){
                            window.TeleditApp.BestClose("<spring:message code='02_15'/>");
                        }else{
                            swal("<spring:message code='02_15'/>").then(function (){
                                closePop();
                            });
                        }
                    }
				}
			}else{
				if(window.TeleditApp){
					window.TeleditApp.BestClose("<spring:message code='02_16'/>");
				}else{
                    swal("<spring:message code='02_16'/>").then(function () {
                        closePop();
                    });
                }
				opener.parent.failInfo();
			}
		});

		function closePop(){
            if (navigator.appVersion.indexOf("MSIE 6.0") >= 0)
            {
                window.close();
            }else{
                self.close();
            }
		}
</script>
</html>