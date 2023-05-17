// 패스워드 체크(alert skip)	
function checkPasslevel(pw){

		pw = String(pw);

		var pwEtcRules = /^(?=.*[a-zA-Z])(?=.*[~!@\#$%<>^&*\()\-=+_\’])(?=.*[0-9]).{8,12}$/;
		var pwRules = /^[a-zA-Z0-9]{8,12}$/;
		var regEtc =/[~!@\#$%<>^&*\()\-=+_\’]/gi;
		var chkKor = /([가-힣ㄱ-ㅎㅏ-ㅣ\x20])/i; // 한글 정규식
		
		var chkEngOverlap = /(abc)|(bcd)|(cde)|(def)|(efg)|(fgh)|(ghi)|(hij)|(ijk)|(jkl)|(klm)|(lmn)|(mno)|(nop)|(opq)|(pqr)|(qrs)|(rst)|(stu)|(tuv)|(uvw)|(vwx)|(wxy) |(xyz)/; // 연속된 영문자 정규식
		var chkNumOverlap = /(012)|(123)|(234)|(345)|(456)|(567)|(678)|(789)|(890)|(098)|(987)|(876)|(765)|(654)|(543)|(432)|(321)|(210)/; // 연속된 숫자 정규식
		
		var ckNum = pw.search(/[0-9]/g);
 		var ckEng = pw.search(/[a-z]/ig);
 		var ckEtc = pw.search(regEtc);
 		
 		var str = "-1";
 		if(chkKor.test(pw)) {
 			str = MSG_JS.TEXT1;
	 		return str;
 		}
 		

		if(pw.length < 8 || pw.length > 12){
			str = MSG_JS.TEXT0;
	 		return str;
		}
		
		if (!regEtc.test(pw)) {
			if (!pwEtcRules.test(pw)) {
				str = MSG_JS.TEXT0;
		 		return str;
			}
			if (ckNum <0 || ckEng <0 && ckEtc <0 ) {
				str = MSG_JS.TEXT0;
				return str;
		 	}
		}
		
 		if(/(\w)\1\1/.test(pw)){
 			str = MSG_JS.TEXT2;
 			return str;
 		}
 		
 		if(chkEngOverlap.test(pw.toLowerCase())) {
 			str = MSG_JS.TEXT3;
 			return str;
 		}
	 	
		if(chkNumOverlap.test(pw)) {
			str = MSG_JS.TEXT4;
 			return str;
 		}
	 	return true;
	}