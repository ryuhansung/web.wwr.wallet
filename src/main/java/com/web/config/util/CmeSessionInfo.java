package com.web.config.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.config.logging.CmeCommonLogger;
import com.web.config.util.CmeConstant.CmeSessionConst;
import com.web.front.mypage.vo.CustVO;

/**
 * @Class Name  : DmSessionInfo
 * @작성일   : 2012. 9. 26. 
 * @작성자   : Kim, YunKwan
 * @변경이력  :
 * @Method 설명 : 세션정보를 통합 관리
 */

public class CmeSessionInfo {
   CmeCommonLogger log = new CmeCommonLogger(this.getClass());
   SessionUtil sUtil;
   
   public CmeSessionInfo() {
      sUtil = SessionUtil.getinstance();
   }

   public void setUserInfo(HttpServletRequest request, HttpServletResponse response, CustVO loginInfo){

      if(loginInfo != null){
         sUtil.SetSessionValue(request, CmeSessionConst.IsLogin, "true");
         sUtil.SetSessionValue(request, CmeSessionConst.LoginId, loginInfo.getUserEmail());
         sUtil.SetSessionValue(request, CmeSessionConst.LoginNm, loginInfo.getUserNm());


      }else{
         log.ViewWarnLog("로그인 정보가 없습니다.");
      }
   }      
}   
