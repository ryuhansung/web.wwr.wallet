package com.web.config.control;


import java.util.Locale;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.config.util.ComUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.servlet.LocaleResolver;

import com.web.config.util.CmeConstant.CmeSessionConst;

public class CmeDefaultExtendController extends CmeExtendsControl{

	@Autowired
	private MessageSource messageSource;	
	
	@Autowired
	private LocaleResolver localeResolver;	

	protected String getLocale(HttpServletRequest request, String key) {
		
		String msg = "";
//		msg = messageSource.getMessage(key,null, "no surch", Locale.KOREA);
		msg = messageSource.getMessage(key,null, "", localeResolver.resolveLocale(request));
		
		
		return msg;
	}

	protected String getLocale(HttpServletRequest request, String key,Object[] args) {

		String msg = "";
//		msg = messageSource.getMessage(key,null, "no surch", Locale.KOREA);
		msg = messageSource.getMessage(key,args, "", localeResolver.resolveLocale(request));


		return msg;
	}
	
	protected String getLocale(HttpServletRequest request) {
		return localeResolver.resolveLocale(request).getLanguage();
	}
	
	protected void setLocale(HttpServletRequest request, HttpServletResponse response, String lang) {
		localeResolver.setLocale(request, response, new Locale(lang));
	}
	
	@Override
	protected String getLoginId() {
		// TODO Auto-generated method stub
		if("".equals(sUtil.getStrValue(CmeSessionConst.LoginId))){
			return "";
		}
		
		return sUtil.getStrValue(CmeSessionConst.LoginId);
	}


	protected boolean isLoginYn(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String lang = getLocale(request);
		if (!"true".equals(sUtil.getStrValue(CmeSessionConst.IsLogin))) { // 로그인 안돼있을때
			setLocale(request, response, lang);
			ComUtil.postRedirect(response, request, null, "/front.main.index.dp/proc.go?lang="+lang, "");
			return false;
		} else { // 로그인 OK
			return true;
		}
	}


	
}
