<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<c:import url="/front.include.common_front_head.dp/proc.go"	charEncoding="utf-8" />
    <script>
        function complet(){
            var input = $("input[type=text]").length;
            for(var i=0;i<=input;i++){
                if($("input[type=text]").eq(i).val()== ""){
                    alert("입력사항을 다시한번 확인해주세요");
                    return true;
                }
            }
            if(!$(".gender-wrap div").hasClass("checked")){
                alert("성별을 선택해주세요");
                return true;
            }
            window.location.href="/front.mypage.usb_change_complet.ds/proc.go"
        }
    </script>
</head>
<body>
<c:import url="/front.include.inc_header.dp/proc.go" charEncoding="utf-8" />

<main class="cd-main-content usb_change">
    <div class="contents-wrap">
        <div class="short-wrap step02">
            <h2>USB 변경 등록</h2>
            <ul class="dotStyle">
                <li>USB 고장 또는 분실되었을 경우, USB 변경이 가능합니다.<br>변경 시, 기존 USB ID(고장 또는 분실)는 “사용안함“처리되므로, 사용안함처리된 USB ID를 다시 사용하실 경우에는 획득프로그램에서 USB등록 또는 기존 아이디에 등록해주시기 바랍니다.</li>
                <li>변경 후, USB 공유자의 USB ID도 모두 변경처리됩니다.</li>
            </ul>
            <div class="form-box">
                <ul>
                    <li class="idCheck read">
                        <span class="iconSet"><i class="xi-mail-o"></i></span>
                        <input type="text" class="iconInput readonly" placeholder="Email"  />
                    </li>
                </ul>
            </div>
            <div class="form-box">
                <ul>
                    <li>
                        <select>
                            <option value="">USB선택</option>
                            <option value="">USB1</option>
                            <option value="">USB2</option>
                        </select>
                    </li>
                    <li class="read">
                        <input type="text" name="read" class="readonly" placeholder="DDfsf512151"  />
                    </li>
                    <li class="half left">
                        <select>
                            <option value="">국가선택</option>
                            <option value="">한국</option>
                            <option value="">미국</option>
                        </select>
                    </li>
                    <li class="half right">
                        <select>
                            <option value="">지역선택</option>
                            <option value="">서울</option>
                            <option value="">경기</option>
                        </select>
                    </li>
                    <li class="nameCheck read">
                        <input type="text" name="name" class="readonly" placeholder="홍길동"  />
                        <span class="gender-wrap">
							<span class="g1"><input type="radio" id="gender-man" name="gender" class="iCheck"><label for="gender-man">남</label></span>
							<span class="g2"><input type="radio" id="gender-girl" name="gender" class="iCheck"><label for="gender-girl">여</label></span>
						</span>
                    </li>
                    <li class="half left read">
                        <input type="text" name="age" class="readonly" placeholder="20대"  />
                    </li>
                    <li class="half right read">
                        <input type="text" name="phone" class="readonly" placeholder="01022223333"  />
                    </li>
                </ul>
            </div>
            <div class="bottom-btnWrap">
                <a href="javascript:complet()" class="btn-primary">확인</a>
            </div>
        </div>
    </div>
</main>
<c:import url="/front.include.inc_footer.ds/proc.go" charEncoding="utf-8" />
</body>
</html>