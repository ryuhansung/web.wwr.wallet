package com.web.front.login.util;

import com.web.config.util.ComUtil;
import com.web.config.util.SessionUtil;
import com.web.front.login.LoginConstant;
import com.web.front.mypage.vo.CustVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

/** *
 * @Class Name  : LUtil
 * @작성일 : 2019. 06. 09.
 * @작성자 :  (주)씨엠이소프트 민병철
 * @변경이력 :
 * @Method 설명 : 세션 처리 유틸
 */
public class LUtil {

    private final String LOGIN_SESSION = "LOGIN_SESSION";
    private final String IS_LOGIN = "IS_LOGIN";

    private LUtil() { }

    private static class holder {
        public static final LUtil singleton = new LUtil();
    }

    public static LUtil getInstance() {
        return holder.singleton;
    }

    public void login(HttpServletRequest request, String id) {
        HashMap<String, Object> loginSession = new HashMap<>();
        loginSession.put(IS_LOGIN, true);

        request.getSession().setAttribute(LoginConstant.LOGIN_ID, id);
        request.getSession().setAttribute(LOGIN_SESSION, loginSession);
    }

    public boolean setSession(String sessionName, Object obj, HttpServletRequest request) {
        Object loginObj = request.getSession().getAttribute(LOGIN_SESSION);
        if(loginObj == null) {
            return false;
        }

        ((HashMap<String, Object>)loginObj).put(sessionName, obj);
        return true;
    }

    public Object getSession(String sessionName, HttpServletRequest request) {
        Object loginObj = request.getSession().getAttribute(LOGIN_SESSION);
        if(loginObj == null) {
            return null;
        }

        return ((HashMap<String, Object>)loginObj).get(sessionName);
    }

    public void logout(HttpServletRequest request) {
        request.getSession().removeAttribute(LoginConstant.LOGIN_ID);
        request.getSession().removeAttribute(LOGIN_SESSION);
    }

    public String getLoginId(HttpServletRequest request, HttpServletResponse response) {

        String id = "";
        try {
            Object obj = request.getSession().getAttribute(LoginConstant.LOGIN_SESSION);

            if(obj != null) {
                CustVO vo = (CustVO)obj;
                id = vo.getUserEmail();
            } else {
                ComUtil.postRedirect(response, request, "/front.login.loginView.dp/proc.go");
                return null;
            }
        } catch(Exception e) {
            return "";
        }

        return id;
    }

}
