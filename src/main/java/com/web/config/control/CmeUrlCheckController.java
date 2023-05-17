package com.web.config.control;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.mobile.device.Device;
import org.springframework.mobile.device.DeviceUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.web.config.util.ComUtil;
import com.web.config.util.StringUtils;
import com.web.config.vo.CmeExceptionVO;


@Component
@Controller
public class CmeUrlCheckController extends CmeDefaultExtendController{

//    @Autowired 
//    private String strDmAdminIpList;
        
    
     @RequestMapping(value = "/{urlId}/dmparse.dm")
        public ModelAndView getUrlReturn(@PathVariable("urlId") String urlId, Model model, 
                                HttpServletRequest request, 
                                HttpServletResponse response)throws Exception {
            
         ModelAndView mv = null;
            
        Map<String,Object> param_map = ComUtil.setParam(request, null);
        
        if(!ComUtil.isDmUrl(urlId)){
            mv = new ModelAndView();
            Map<String,String> resultMap = new HashMap<String,String>();
            resultMap.put("code", "-1");
            resultMap.put("codeMsg", "비정상적인 접근");
            
            mv.addAllObjects(resultMap);
            mv.setViewName("jsonView");
            return mv;
            
        }
        if(urlId.indexOf(".") < 0){
            mv = new ModelAndView();
            Map<String,String> resultMap = new HashMap<String,String>();
            resultMap.put("code", "-1");
            resultMap.put("codeMsg", "비정상적인 접근");
            
            mv.addAllObjects(resultMap);
            mv.setViewName("jsonView");  
        }
        
        String[] urls = urlId.split("\\.");
        StringBuffer urlParse = new StringBuffer();

        for(int i=0; i < urls.length; i++){
            if(i < urls.length-1){
                urlParse.append(urls[i]);                   
            }
            if(i < urls.length-2){
                urlParse.append("/");
            }
        } 
        
        String urlHeader = "forward:/";
        
        if(urls[urls.length-1].equals("ds")){//jsp forward
            mv = new ModelAndView(urlParse.toString());
        }else if(urls[urls.length-1].equals("dp")){
            urlParse.append(".dm");
            debugLog("urlParse:"+urlHeader+urlParse.toString());
            mv = new ModelAndView(urlHeader+urlParse.toString(), param_map);
        }
        
        return mv;
    }       

    
    
    
     @RequestMapping(value = "/{urlId}/proc.go")
     public ModelAndView getSIASUrlReturn(@PathVariable("urlId") String urlId, Model model, 
                             HttpServletRequest request, 
                             HttpServletResponse response)throws Exception {
         
         ModelAndView mv = null;
 

         Map<String,Object> param_map = ComUtil.setParam(request, null);
         
         if(!ComUtil.isDmUrl(urlId)){
             mv = new ModelAndView();
             Map<String,String> resultMap = new HashMap<String,String>();
             resultMap.put("code", "-1");
             resultMap.put("codeMsg", "비정상적인 접근");
             
             mv.addAllObjects(resultMap);
             mv.setViewName("jsonView");
             return mv;
             
         }
         if(urlId.indexOf(".") < 0){
             mv = new ModelAndView();
             Map<String,String> resultMap = new HashMap<String,String>();
             resultMap.put("code", "-1");
             resultMap.put("codeMsg", "비정상적인 접근");
             
             mv.addAllObjects(resultMap);
             mv.setViewName("jsonView");  
         }
              
    
                                        
         String[] urls = urlId.split("\\.");
         StringBuffer urlParse = new StringBuffer();

         for(int i=0; i < urls.length; i++){
             if(i < urls.length-1){
                 urlParse.append(urls[i]);                   
             }
             if(i < urls.length-2){
                 urlParse.append("/");
             }
         } 
         
         sUtil.SetStrSession(request, "lang", getLocale(request));
       
         String urlHeader ="forward:/";
         
         
         if(urls[urls.length-1].equals("ds")){//jsp forward
             mv = new ModelAndView(urlParse.toString());
         }else if(urls[urls.length-1].equals("dp")){
             urlParse.append(".dm");
             debugLog("urlParse:"+urlHeader+urlParse.toString());
             mv = new ModelAndView(urlHeader+urlParse.toString(), param_map);
         }
         
         return mv;
     }
    
    @RequestMapping(value="/dm_none.dm")
    public ModelAndView rtnError(
                                    @ModelAttribute CmeExceptionVO vo,
                                    @RequestParam(value="errmsg", required=false) String exceptmsg,
                                    @RequestParam(value="title", required=false) String title,
                                    @RequestParam(value="msg", required=false) String message,
                                    ModelMap model,
                                    HttpServletRequest request, 
                                    HttpServletResponse response)throws Exception{
        ModelAndView mv = null;
        String _exceptmsg = "";
        String _title = "";
        String _message = "";
        
//        System.out.println("###/"+vo.getErrorStatusCode() + "####/"+ vo.getErrorMessageCode() + "###/"+vo.getErrorTitleCode());
        
        if(!"".equals(vo.getExmessage())){
            _exceptmsg = vo.getExmessage();
        }else{
            _exceptmsg = StringUtils.checkNull(exceptmsg); 
        }       
        
        if(!"".equals(vo.getErrorTitleCode())){
            _title = vo.getErrorTitleCode();
        }else{
            _title = StringUtils.checkNull(title);
        }
        
        if(!"".equals(vo.getErrorMessageCode())){
            _message = vo.getErrorMessageCode();
        }else{
            _message = StringUtils.checkNull(message);
        }
        
        debugLog(">>>>>>>>>>dm_none");
        
        mv = new ModelAndView();
        Map<String,String> resultMap = new HashMap<String,String>();
        resultMap.put("code", vo.getErrorStatusCode());
        resultMap.put("codeMsg", vo.getErrorMessageCode());
        
        mv.addAllObjects(resultMap);
        mv.setViewName("jsonView");  
        
        return mv;
    }
}
