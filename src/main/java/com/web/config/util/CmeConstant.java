package com.web.config.util;

import com.common.utils.AesUtil;
import com.web.config.service.CmeProperties;

public class CmeConstant {
	
	public static final String apiUrl = "http://oapi.planbit.io";
	public static final String encKey = AesUtil.key1 = CmeProperties.getSecretProperty("encrypt.key");
	public static final String enc256Key = AesUtil.key256 = CmeProperties.getSecretProperty("encrypt.256.key");
	public class CmeSessionConst {
		/*
		 * Session 관련상수
		 */
		public static final String LoginId = "LoginId"; // 관리자아이디 : empid
		public static final String LoginNm = "LoginNm"; // 관리자 이름 : empname
		public static final String IsLogin = "isLogin";
		
		// 파일 관련 상수
		public static final String AUDIT_FILE_INFOLIST = "AUDIT_FILE_INFO";

	}
	
	public class CookieConst{
		public static final String curMenuCode = "cur_menuId";
		public static final String subMenuTitle = "subMenuTitle";
		public static final String saveUserId = "saveUserId";
		public static final String saveid = "saveid";
		public static final String saveAdid = "saveAdid";	//admin id 쿠키 체크
		public static final String excepVal = "saveUserId,saveid,saveAdid,PassChk,layer1,layer2,layer3";
		public static final String twitChk = "twitChk";
		public static final String sno ="sno";
		public static final String memberName ="memberName";
		public static final String cyberId ="cyberId";
		public static final String JsessionId = "JSESSIONID";
		public static final String SessionUkey = "SessionUkey";

	}

}
