package com.web.config.control;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;


import com.web.config.exception.CmeApplicationException;
import com.web.config.logging.CmeCommonLogger;
import com.web.config.util.CmeConstant;
import com.web.config.util.ComUtil;
import com.web.config.util.SessionUtil;
import com.web.config.vo.CmeExceptionVO;

public abstract class CmeExtendsControl {
    protected CmeCommonLogger log = new CmeCommonLogger(this.getClass());
    protected SessionUtil sUtil = SessionUtil.getinstance();


    //@ExceptionHandler({DmApplicationException.class, NullPointerException.class})
    @ExceptionHandler(CmeApplicationException.class)
    public ModelAndView handleException(CmeApplicationException exception, HttpServletRequest req, HttpServletResponse response) throws Exception{
        ModelAndView mv = null;
        CmeExceptionVO vo = exception.getExceptVo();
        String strexp = "";
        
        if(!"".equals(vo.getExmessage())){
            strexp = vo.getExmessage();
        }else{
            //strexp = StringUtils.checkNull(exception.getMessage());         
        }
        
        if(strexp.length() > 3500){
            strexp = strexp.substring(0, 3400) + "...";
        }
        vo.setExmessage(strexp+"("+vo.getErrorStatusCode()+")");
        vo.setErrorMessage(vo.getErrorMessage() + "(" + vo.getErrorStatusCode() + ")");
        //vo.setErrorMessage(exception.getCustomMessage());
        vo.setTimestamp(exception.getCurDateTime());
        String hostName = req.getServerName();
        String hostPort = ""+req.getServerPort();
        boolean secure = req.isSecure();
        String hostSvr = "";
        debugLog(req.getScheme()+"://" + hostName + ":" + hostPort);
        
        if("80".equals(hostPort)){
            hostSvr = req.getScheme()+"://" + hostName;
        }else{
            if(secure){
                hostSvr = req.getScheme()+"://" + hostName;;                    
            }else{
                hostSvr = req.getScheme()+"://" + hostName + ":"+ hostPort;
            }
        }
        
        try {
            ComUtil.postRedirect(response, req, vo, "/dm_none.dp/proc.go", "");
        } catch (IOException e) {
            e.printStackTrace();
            String errorPg = hostSvr+ "/dm_none.dp/proc.go";
            response.sendRedirect(errorPg);
            return null;
/*          mv = new ModelAndView("redirect:/dm_none.dp/proc.go");
            return mv;*/
        }
        return mv;
    }   
    
    protected void debugLog(String msg){
        log.DebugLog(msg);
    }
    
    protected void infoLog(String msg){
        log.ViewLog(msg);
    }
    
    protected void errLog(String msg){
        log.ViewErrorLog(msg);
    }
    
    protected void warnLog(String msg){
        log.ViewWarnLog(msg);
    }   
    protected abstract String getLoginId();

	protected String nowTime() throws Exception{
		return ""; 
	}

    protected boolean isLoginYn(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return false;
    }
	
    /*protected abstract boolean isAuthenticate();
    protected abstract boolean loginYn(int chkauth);
    protected abstract boolean checkAuth(String level); 
    protected abstract void moveError();
    protected abstract boolean isAuth();
    protected abstract void dataError();*/
    
}

