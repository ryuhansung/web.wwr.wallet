/**
 * (주)크림스 의 시스템 웹어플리케이션 프레임웍 입니다. 허가 없이 복제 및 배포 할 수 없습니다.
 * 개발사 : (주)크림스
 * CopyRight Creams. inc. Since 2015 
 * 총괄 개발 책임자 : 주식회사 크림스 통합개발연구소 소장 김윤관
 */
package com.web.config.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.StringTokenizer;

import kr.co.danal.jsinbi.HttpClient;

/**
 * @프로젝트명	: com.web.shop
 * @패키지    	: cme.com.web.common.util
 * @클래스명  	: com.web.shop
 * @작성자		: (주)씨엠이소프트 문영민
 * @작성일		: 2017. 4. 19.
 */
public class DanalAuthUtil {

	private static DanalAuthUtil util;
	private DanalAuthUtil(){}
	public static DanalAuthUtil getInstance() {
		synchronized(DanalAuthUtil.class){
			if(util == null) util = new DanalAuthUtil();
			return util;
		}
	}
	
	
	/********************************************************************************
	 *
	 * 다날 본인인증
	 *
	 * - Function Library
	 *      결제 연동에 필요한 Function 및 변수값 설정
	 *
	 * 결제 시스템 연동에 대한 문의사항이 있으시면 서비스개발팀으로 연락 주십시오.
	 * DANAL Commerce Division Technique supporting Team
	 * EMail : tech@danal.co.kr
	 *
	 ********************************************************************************/
	
	 /******************************************************
	 * 인증서버 URL 정의
	 ******************************************************/
	final String DN_SERVICE_URL = "https://uas.teledit.com/uas/";

	/******************************************************
	 * SET TIMEOUT
	 ******************************************************/
	final int DN_CONNECT_TIMEOUT = 5000;
	final int DN_TIMEOUT = 30000;

	/******************************************************
	 * ID		: 다날에서 제공해 드린 CPID
	 * PWD		: 다날에서 제공해 드린 CPPWD
	 * ORDERID	: CP 주문정보
	 ******************************************************/
	public final String ID  = "B010033470";
	public final String PWD = "5qGdnWqd0r";
	public final String ORDERID = "";

	/******************************************************
	 * CHARSET 지정 ( UTF-8:Default or EUC-KR )
	 ******************************************************/
	final String CHARSET = "EUC-KR";
//	final String CHARSET = "UTF-8";

	/******************************************************
	 * - CallTrans
	 *      다날 서버와 통신하는 함수입니다.
	 ******************************************************/
	public Map CallTrans( Map data ){
		
		String REQ_STR = data2str(data);
		String RES_STR = "";

		HttpClient hc = new HttpClient();

		hc.setConnectionTimeout( DN_CONNECT_TIMEOUT );
		hc.setTimeout( DN_TIMEOUT );

		try{
			int nStatus = hc.retrieve( "POST", DN_SERVICE_URL, REQ_STR, CHARSET, CHARSET );

			if( nStatus != 0) {
				RES_STR = "RETURNCODE=-1&RETURNMSG=NETWORK ERROR(" +nStatus+ ")";
			} else {
				RES_STR = hc.getResponseBody();
			}

		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return str2data( RES_STR );
	}

	public Map str2data(String str) {

		Map map = new HashMap();
		StringTokenizer st = new StringTokenizer(str,"&"); 

		while(st.hasMoreTokens()){

	    	String pair = st.nextToken();
	    	int index = pair.indexOf('=');

	    	if(index > 0){
	    		//map.put(pair.substring(0,index).trim(),urlDecode(pair.substring(index+1)));
				map.put(pair.substring(0,index).trim(), pair.substring(index+1));
			}
	 	}

		return map;
	}

	public String data2str(Map data) {

		Iterator i = data.keySet().iterator();
		StringBuffer sb = new StringBuffer();
	
		while(i.hasNext()){
	    		Object key = i.next();
	    		Object value = data.get(key);
	    		sb.append(key.toString()+"="+value+"&");
		}

		if(sb.length()>0) {
			return sb.substring(0,sb.length()-1);
		} else {
	    		return "";
		}
   }
	
	public String MakeFormInput(Map map,String[] arr) {

		StringBuffer ret = new StringBuffer();

		if( arr!=null )
		{
			for( int i=0;i<arr.length;i++ )
			{
				if( map.containsKey(arr[i]) )
				{
					map.remove( arr[i] );
				}
			}
		}

		Iterator i = map.keySet().iterator();

		while( i.hasNext() )
		{
			String key = (String)i.next();
			String value = (String)map.get(key);
			
			ret.append( "<input type=\"hidden\" name=\"" );
			ret.append( key );
			ret.append( "\" value=\"" );
			ret.append( value );
			ret.append( "\">" );
			ret.append( "\n" );
		}

		return ret.toString();
	}

	public String MakeFormInputHTTP(Map HTTPVAR,String arr) {

		StringBuffer ret = new StringBuffer();
		String key = "";
		String[] value = null;

		Iterator i = HTTPVAR.keySet().iterator();

		while( i.hasNext() )
		{
			key = (String)i.next();

			if( key.equals(arr) )
			{
				continue;
			}

			value = (String[])HTTPVAR.get(key);
			
			for( int j=0;j<value.length;j++ )
			{
				ret.append( "<input type=\"hidden\" name=\"" );
				ret.append( key );
				ret.append( "\" value=\"" );
				ret.append( value[j] );
				ret.append( "\">" );
				ret.append( "\n" );
			}
		}

		return ret.toString();
	}

	public String GetCIURL(String IsUseCI,String CIURL) {

		/*
		 * Default Danal CI
		 */
		String URL = "";

		if( IsUseCI.equals("Y") && CIURL != null )
		{
			CIURL = CIURL.replaceAll("<", "")
					.replaceAll(">", "")
					.replaceAll("(?i)javascript", "")
					.replaceAll("(?i)script", "");
			URL = CIURL;
		}

		return URL;
	}

	public String GetBgColor(String BgColor) {

		/*
		 * Default : Blue
		 */
		int Color = 0;
		int nBgColor = Integer.parseInt(BgColor);

		if( nBgColor > 0 && nBgColor < 11 )
		{
			Color = nBgColor;
		}

		if( Color >= 0 && Color <= 9 )
		{
			return "0" + Integer.toString(Color);
		}
		else
		{
			return Integer.toString(Color);
		}
	}
}
