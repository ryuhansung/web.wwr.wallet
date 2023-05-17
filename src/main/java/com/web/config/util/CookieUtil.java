package com.web.config.util;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.config.logging.CmeCommonLogger;


/**
 * @Class Name  : CookieUtil
 * @작성일   : 2012. 9. 12. 
 * @작성자   : Kim, YunKwan
 * @변경이력  :
 * @Method 설명 : 쿠키유틸
 */
public class CookieUtil {

	public Map<String,Cookie> cookieMap= new HashMap<String,Cookie>();
	 // 쿠키를 <쿠키이름, cookie 객체> 쌍으로 저장하는 맵
	 
	CmeCommonLogger log = new CmeCommonLogger(this.getClass());
	/**
	 * @param request
	 */
	@SuppressWarnings("deprecation")
	public CookieUtil(HttpServletRequest request)
	{
		// 생성자, cookie배열을 cookieMap에 저장한다.
		 Cookie[] cookies= request.getCookies();
		 if(cookies !=null){
			for(int i=0; i<cookies.length; i++){
				cookieMap.put(URLDecoder.decode(cookies[i].getName()), cookies[i]);				
			}
		}
	}
	
	public void viewAllCookie(HttpServletRequest request){
		 Cookie[] cookies= request.getCookies();
		 StringBuffer sb = new StringBuffer();
		 if(cookies !=null){
			for(int i=0; i<cookies.length; i++){
				sb.append(cookies[i].getName()+":"+cookies[i].getValue()+"|");
			}
			log.DebugLog(sb.toString());
		}
	}
	
	 /**
	 * @Method Name  : createCookie
	 * @작성일   : 2012. 9. 12. 
	 * @작성자   : Kim, YunKwan
	 * @변경이력  :
	 * @Method 설명 : 이름이 name,값이 value인 쿠키객체를 생성해서 리턴
	 * @param name
	 * @param value
	 * @return
	 * @throws IOException
	 */
	public Cookie createCookie(String name, String value)throws IOException 
	 {
		Cookie cookie = new Cookie(name, URLEncoder.encode(value, "utf-8"));
	    cookie.setPath("/");
	    return cookie;
	 }

	 /**
	 * @Method Name  : createCookie
	 * @작성일   : 2012. 9. 12. 
	 * @작성자   : Kim, YunKwan
	 * @변경이력  :
	 * @Method 설명 :이름이 name,값이 value,도메인이 domain, 경로가 path, 유효시간이 maxAge인 쿠키객체를 생성해서 리턴
	 * @param name
	 * @param value
	 * @param domain
	 * @param path
	 * @param maxAge
	 * @return
	 * @throws IOException
	 */
	public Cookie createCookie(String name, String value, String domain, String path, int maxAge)throws IOException 
	 {
		Cookie cookie = new Cookie(name, URLEncoder.encode(value, "utf-8"));
		cookie.setDomain(domain);
	    cookie.setPath(path);
	    cookie.setMaxAge(maxAge);
	     
	    return cookie;
	 }

	 /**
	 * @Method Name  : createCookie
	 * @작성일   : 2012. 9. 12. 
	 * @작성자   : Kim, YunKwan
	 * @변경이력  :
	 * @Method 설명 :이름이 name,값이 value, 경로가 path, 유효시간이 maxAge인 쿠키객체를 생성해서 리턴
	 * @param name
	 * @param value
	 * @param path
	 * @param maxAge
	 * @return
	 * @throws IOException
	 */
	public Cookie createCookie(String name, String value, String path, int maxAge)throws IOException
	 {
	    Cookie cookie= new Cookie(name,URLEncoder.encode(value,"utf-8"));
	    cookie.setPath(path);
	    cookie.setMaxAge(maxAge);
	    return cookie;
	 }
	 
	public void setSessionCookie(String name, String value, HttpServletResponse resp)throws IOException{
		Cookie ck = createCookie(name, value);
		resp.addCookie(ck);
	}
	
	
	 /**
	 * @Method Name  : removeCookie
	 * @작성일   : 2012. 9. 12. 
	 * @작성자   : Kim, YunKwan
	 * @변경이력  :
	 * @Method 설명 :이름이 name,값이 value, 경로가 path, 유효시간이 maxAge인 쿠키객체를 생성해서 리턴(삭제)
	 * @param name
	 * @return
	 * @throws IOException
	 */
	public void removeCookie(String name, HttpServletResponse resp)throws IOException
	 {
		  Cookie cookie=(Cookie)cookieMap.get(name);
		  cookie.setPath("/");
		  cookie.setMaxAge(0);
		  cookie.setValue("");
		  resp.addCookie(cookie);
	 }	 
	 
	
	/**
	 * @Method Name  : removeCookieAll
	 * @작성일   : 2013. 3. 5. 
	 * @작성자   : Kim, YunKwan
	 * @변경이력  :
	 * @Method 설명 : excepVal을 제외한 모든쿠기삭제 
	 * @param req
	 * @param resp
	 * @param excepVal
	 * @throws IOException
	 */
	public void removeCookieAll(HttpServletRequest req, HttpServletResponse resp ,String[] excepVal)throws IOException{
		Cookie[] _cookies = req.getCookies();
		if(_cookies != null){
			
			StringBuffer sb = new StringBuffer();			
	        for (int i = 0; i < _cookies.length; i++) {
	        	boolean chk = true;
	        	if(excepVal != null){
	        		for (int j = 0; j < excepVal.length; j++) {
	        			if(excepVal[j].equals(_cookies[i].getName())){
	        				chk = false;
	        			}
	        		}
	        	}
	        	if(chk){
		        	sb.append(_cookies[i].getName()+":"+_cookies[i].getValue()+"|");	        	
		            _cookies[i].setValue("");
		            _cookies[i].setPath("/");
		            _cookies[i].setMaxAge(0);
		            resp.addCookie(_cookies[i]);
	        	}
	        }
	        log.DebugLog(sb.toString());
		}
	}
	

	 /**
	 * @Method Name  : getCookie
	 * @작성일   : 2012. 9. 12. 
	 * @작성자   : Kim, YunKwan
	 * @변경이력  :
	 * @Method 설명 :쿠키 맵에 저장된 쿠키에서 지정한 이름의 쿠키객체를 구한다.
	 * @param name
	 * @return
	 */
	public Cookie getCookie(String name)
	 {
		 return (Cookie)cookieMap.get(name);
	 }

	 /**
	 * @Method Name  : getValue
	 * @작성일   : 2012. 9. 12. 
	 * @작성자   : Kim, YunKwan
	 * @변경이력  :
	 * @Method 설명 :쿠키 맵에 저장된 쿠키에서 지정한 이름의 쿠키객체 값을 구한다.
	 * @param name
	 * @return
	 * @throws IOException
	 */
	public String getValue(String name)throws IOException
	 {
		  Cookie cookie=(Cookie)cookieMap.get(name);
		  if(cookie==null) return "";
		  return URLDecoder.decode(cookie.getValue(),"utf-8");
	 }

	 /**
	 * @Method Name  : exists
	 * @작성일   : 2012. 9. 12. 
	 * @작성자   : Kim, YunKwan
	 * @변경이력  :
	 * @Method 설명 :지정한 이름의 쿠키가 존재할 경우 true, 아니면 false리턴
	 * @param name
	 * @return
	 */
	public boolean exists(String name)
	 {
		  return cookieMap.get(name) != null;
	 }		
	
}
