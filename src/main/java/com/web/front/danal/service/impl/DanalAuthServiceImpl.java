package com.web.front.danal.service.impl;

import com.web.config.util.DanalAuthUtil;
import com.web.front.danal.DanalConstant;
import com.web.front.danal.service.DanalAuthService;
import com.web.front.join.JoinConstant;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

@Service
public class DanalAuthServiceImpl implements DanalAuthService {

    protected DanalAuthUtil danalAuthUtil = DanalAuthUtil.getInstance();

    @Override
    public Map reqAuth(HttpServletRequest request, HttpServletResponse response, HashMap<String, String> param) throws Exception {
        // TODO Auto-generated method stub
        response.setHeader("Pragma", "No-cache");
        request.setCharacterEncoding("euc-kr");

        String hphone = param.get("hphone");
        String userEmail = param.get("userEmail");
        String modify = param.get("modify");
        String userNm = URLEncoder.encode(param.get("userNm"),"euc-kr");

        /********************************************************************************
         *
         * 다날 본인인증
         *
         * - 인증 요청 페이지
         *	CP인증 및 기타 정보 전달
         *
         * 인증 시스템 연동에 대한 문의사항이 있으시면 서비스개발팀으로 연락 주십시오.
         * DANAL Commerce Division Technique supporting Team
         * EMail : tech@pay.co.kr
         *
         ********************************************************************************/

        /********************************************************************************
         *
         * XSS 취약점 방지를 위해
         * 모든 페이지에서 파라미터 값에 대해 검증하는 로직을 추가할 것을 권고 드립니다.
         * XSS 취약점이 존재할 경우 웹페이지를 열람하는 접속자의 권한으로 부적절한 스크립트가 수행될 수 있습니다.
         * 보안 대책
         *  - html tag를 허용하지 않아야 합니다.(html 태그 허용시 white list를 선정하여 해당 태그만 허용)
         *  - <, >, &, " 등의 문자를 replace등의 문자 변환함수를 사용하여 치환해야 합니다.
         *
         ********************************************************************************/
        /********************************************************************************
         *
         * [ 전문 요청 데이터 ] *********************************************************
         *
         ********************************************************************************/

        /***[ 필수 데이터 ]************************************/
        Map TransR = new HashMap();

        /******************************************************
         ** 아래의 데이터는 고정값입니다.( 변경하지 마세요 )
         * TXTYPE       : ITEMSEND
         * SERVICE		: UAS
         * AUTHTYPE		: 36
         ******************************************************/
        TransR.put( "TXTYPE", "ITEMSEND" );
        TransR.put( "SERVICE", "UAS" );
        TransR.put( "AUTHTYPE", "36" );

        /******************************************************
         *  CPID	: 다날에서 제공해 드린 ID( function 파일 참조 )
         *  CPPWD	: 다날에서 제공해 드린 PWD( function 파일 참조 )
         ******************************************************/
        //TransR.put( "CPID", "B010037240" );
        //TransR.put( "CPPWD", "mXtQ48iFCT" );
        TransR.put( "CPID", DanalConstant.CPID );
        TransR.put( "CPPWD", DanalConstant.CPPWD );


        /***[ 선택 사항 ]**************************************/
        /******************************************************
         * USERID       : 사용자 ID
         * ORDERID      : CP 주문번호
         * TARGETURL    : 인증 완료 시 이동 할 페이지의 FULL URL
         * AGELIMIT		: 서비스 사용 제한 나이 설정( 가맹점 필요 시 사용 )
         ******************************************************/

        String serverName = request.getServerName();
        Integer portNo = request.getServerPort();
        StringBuffer serverUrl = request.getRequestURL();
        System.out.println("request.getScheme()  >> " + request.getScheme());
        if(serverName.indexOf("localhost") > -1 || serverName.indexOf(DanalConstant.TEST_SERVER_DOMAIN) > -1 || serverName.indexOf("xlogic") > -1){
            serverName =  request.getScheme() + "://" + serverName + ":" + portNo;
        }else{
            System.out.println("");
            serverName = DanalConstant.RUN_PROTOCOL + serverName;
        }

        TransR.put( "USERID", "ADMIN" );
        TransR.put( "ORDERID", danalAuthUtil.ORDERID );
        TransR.put( "TARGETURL", serverName + "/front.join.authResult.dp/proc.go" );

        /********************************************************************************
         *
         * [ CPCGI에 HTTP POST로 전달되는 데이터 ] **************************************
         *
         ********************************************************************************/

        /***[ 필수 데이터 ]************************************/
        Map ByPassValue = new HashMap();

        /******************************************************
         * BgColor      : 인증 페이지 Background Color 설정
         * BackURL      : 에러 발생 및 취소 시 이동 할 페이지의 FULL URL
         * IsUseCI      : CP의 CI 사용 여부( Y or N )
         * CIURL        : CP의 CI FULL URL
         * IsCharSet	: charset 지정( EUC-KR:deault, UTF-8 )
         * IsDstAddr	: 고객전화번호(‘-‘구분됨, 010-1111-1111)(인증화면에 전달받은 전화번호로 고정시킴)
         ******************************************************/
        ByPassValue.put( "BgColor", "00" );
        ByPassValue.put( "BackURL", serverName + "/front.join.authResult.dp/proc.go" );
        ByPassValue.put( "IsUseCI", "N" );
        ByPassValue.put( "CIURL", "" );
        ByPassValue.put( "IsCharSet", "UTF-8" );
        ByPassValue.put( "IsDstAddr", hphone );

        /***[ 선택 사항 ]**************************************/
        /******************************************************
         ** CPCGI에 POST DATA로 전달 됩니다.
         **
         ******************************************************/
        ByPassValue.put("userEmail", userEmail);
        ByPassValue.put("userNm", userNm);
        ByPassValue.put("hphone", hphone);
        ByPassValue.put("modify", modify);

        Map Res = danalAuthUtil.CallTrans( TransR );

        if( Res.get("RETURNCODE").equals("0000") ) {

            String form1 = danalAuthUtil.MakeFormInput(Res,new String[]{"RETURNCODE","RETURNMSG"});
            String form2 = danalAuthUtil.MakeFormInput(ByPassValue, null);
            Res.put("form1", form1);
            Res.put("form2", form2);

        } else {
            /**************************************************************************
             *
             * 인증 실패에 대한 작업
             *
             **************************************************************************/
			/*
			String RETURNCODE		= (String)Res.get("RETURNCODE");
			String RETURNMSG		= (String)Res.get("RETURNMSG");
			boolean AbleBack	= false;
			String BackURL		= (String)ByPassValue.get("BackURL");
			String IsUseCI		= (String)ByPassValue.get("IsUseCI");
			String CIURL		= (String)ByPassValue.get("CIURL");
			String BgColor		= (String)ByPassValue.get("BgColor");
			*/
        }
        return Res;
    }

    @Override
    public Map CPCGI(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setHeader("Pragma", "No-cache");
        request.setCharacterEncoding("euc-kr");

        /********************************************************************************
         *
         * 다날 본인인증
         *
         * - 인증 확인 페이지
         *	인증 확인 및 기타 정보 수신
         *
         * 인증 시스템 연동에 대한 문의사항이 있으시면 서비스개발팀으로 연락 주십시오.
         * DANAL Commerce Division Technique supporting Team
         * EMail : tech@pay.co.kr
         *
         ********************************************************************************/

        /********************************************************************************
         *
         * XSS 취약점 방지를 위해
         * 모든 페이지에서 파라미터 값에 대해 검증하는 로직을 추가할 것을 권고 드립니다.
         * XSS 취약점이 존재할 경우 웹페이지를 열람하는 접속자의 권한으로 부적절한 스크립트가 수행될 수 있습니다.
         * 보안 대책
         *  - html tag를 허용하지 않아야 합니다.(html 태그 허용시 white list를 선정하여 해당 태그만 허용)
         *  - <, >, &, " 등의 문자를 replace등의 문자 변환함수를 사용하여 치환해야 합니다.
         *
         ********************************************************************************/
        boolean BillErr = false;

        Map TransR = new HashMap();
        Map Res = null;

        String TID = (String)request.getParameter("TID");
        String userEmail =(String)request.getParameter("userEmail");
        String modify = request.getParameter("modify");
        String userNm = URLDecoder.decode(request.getParameter("userNm"),"euc-kr");
        /*
         * - CONFIRMOPTION
         *		0 : NONE( default )
         *		1 : CPID 및 ORDERID 체크
         * - IDENOPTION
         * 0 : 생년월일(6자리) 및 성별 IDEN 필드로 Return (ex : 1401011)
         * 1 : 생년월일(8자리) 및 성별 별개 필드로 Return (연동 매뉴얼 참조. ex : DOB=20140101&SEX=1)
         */
        int nConfirmOption = 0;
        int nIdenOption = 1;
        TransR.put( "TXTYPE", "CONFIRM" );
        TransR.put( "TID", TID );
        TransR.put( "CONFIRMOPTION", nConfirmOption );
        TransR.put( "IDENOPTION", nIdenOption );
        TransR.put("CPTITLE" , request.getScheme() + "://" + request.getServerName());

        /*
         * nConfirmOption이 1이면 CPID, ORDERID 필수 전달
         */
        if( nConfirmOption == 1 )
        {
            TransR.put( "CPID", "" );
            TransR.put( "ORDERID", "" );
        }

        Res = danalAuthUtil.CallTrans( TransR );

        /******************************************************
         ** true일경우 웹브라우져에 debugging 메시지를 출력합니다.
         ******************************************************/
        if( false )
        {
            //out.print( "REQ[" + data2str(TransR) + "]<BR>" );
            //out.print( "RES[" + data2str(Res) + "]<BR>" );
        }

        if( Res.get("RETURNCODE").equals("0000") )
        {
            /**************************************************************************
             *
             * 인증성공에 대한 작업
             *
             **************************************************************************/

            /********************************************************************************
             *
             * 다날 본인인증
             *
             * - 인증 완료 페이지
             *
             * 인증 시스템 연동에 대한 문의사항이 있으시면 서비스개발팀으로 연락 주십시오.
             * DANAL Commerce Division Technique supporting Team
             * EMail : tech@pay.co.kr
             *
             ********************************************************************************/

            /********************************************************************************
             *
             * XSS 취약점 방지를 위해
             * 모든 페이지에서 파라미터 값에 대해 검증하는 로직을 추가할 것을 권고 드립니다.
             * XSS 취약점이 존재할 경우 웹페이지를 열람하는 접속자의 권한으로 부적절한 스크립트가 수행될 수 있습니다.
             * 보안 대책
             *  - html tag를 허용하지 않아야 합니다.(html 태그 허용시 white list를 선정하여 해당 태그만 허용)
             *  - <, >, &, " 등의 문자를 replace등의 문자 변환함수를 사용하여 치환해야 합니다.
             *
             ********************************************************************************/
            String IsUseCI	= (String)request.getParameter("IsUseCI");
            String CIURL	= (String)request.getParameter("CIURL");
            String BgColor	= (String)request.getParameter("BgColor");

            /*
             * Get CIURL
             */
            String URL = danalAuthUtil.GetCIURL( IsUseCI,CIURL );

            /*
             * Get BgColor
             */
            BgColor = danalAuthUtil.GetBgColor( BgColor );


            Res.put("IsUseCI", IsUseCI);
            Res.put("CIURL", CIURL);
            Res.put("BgColor", BgColor);
            Res.put("URL", URL);
            Res.put("NAME", Res.get("NAME"));

            String country_cd = (String) Res.get("FOREIGNER");
            String birth = (String) Res.get("DOB");
            String birth1 = birth.substring(0, 4);
            String birth2 = birth.substring(4, 6);
            String birth3 = birth.substring(6);

            //Res.put("country_cd", country_cd);
            //Res.put("birth", birth);
            //Res.put("birth1", birth1);
            //Res.put("birth2", birth2);
            //Res.put("birth3", birth3);
            //Res.put("param1", param1);
            //Res.put("insCountryCd", countryCd);
            //Res.put("temp_email", temp_email);
            //Res.put("insUserNm", insUserNm);
            //Res.put("insUserBirth", insUserBirth);
            //Res.put("insUserGender", insUserGender);
            //Res.put("lang", lang);
            Res.put("IsDstAddr", request.getParameter("IsDstAddr"));
            Res.put("userEmail", userEmail);
            Res.put("modify", modify);
            Res.put("userNm", userNm);



        } else{
        }

        return Res;
    }
}
