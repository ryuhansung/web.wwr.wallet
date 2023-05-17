package com.web.front.main.controller;

import com.web.config.control.CmeDefaultExtendController;
import com.web.config.control.CmeExtendsControl;
import com.web.config.util.StringUtils;
import com.web.front.board.service.BoardService;
import com.web.front.login.LoginConstant;
import com.web.front.login.interceptor.LoginInterceptor;
import com.web.front.login.service.LoginService;
import com.web.front.main.vo.PopupVO;
import com.web.front.mypage.vo.CustVO;
import kr.co.danal.jsinbi.http.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping(value = "/front/main")
public class MainController extends CmeDefaultExtendController {

    @Autowired
    LoginService loginService;

    @Autowired
    BoardService boardService;

    @RequestMapping(value = "/index.dm")
    public ModelAndView index(@ModelAttribute String lang, HttpSession httpSession, HttpServletRequest request,
                              HttpServletResponse response, ModelMap model) throws Exception {

        if(LoginInterceptor.isDevice(request)) {//모바일인경우
            String sessionId = request.getHeader("sid");

            System.out.println("in sid >> " + sessionId);
            if(sessionId != null && !"".equals(sessionId)) {//세션아이디가 있는경우
                CustVO vo = new CustVO();
                vo.setSessionIdMobile(sessionId);
                CustVO cvo = loginService.getCustInfo(vo);
                if(cvo != null) {
                    System.out.println("matched!");
                    httpSession.setAttribute(LoginConstant.LOGIN_SESSION, cvo);
                }else {
                    System.out.println("not matched!!!!!!!");
                    httpSession.removeAttribute(LoginConstant.LOGIN_SESSION);
                }
            }
        } else {
            Cookie loginCookie = WebUtils.getCookie(request, LoginConstant.LOGIN_COOKIE);

            if (loginCookie != null) {
                String sessionId = loginCookie.getValue();

                CustVO vo = new CustVO();
                vo.setSessionId(sessionId);
                CustVO cvo = loginService.getCustInfo(vo);
                if (cvo != null) {
                    httpSession.setAttribute(LoginConstant.LOGIN_SESSION, cvo);
                }
            }
        }


        //팝업
        PopupVO fvo = new PopupVO();
        fvo.setLang_cd(StringUtils.checkNull(getLocale(request),"en"));
        fvo.setSite_type("01");
        List<PopupVO> list = boardService.selectPopupList(fvo);// sysdate >= str_dt && sysdate <= end_dt && use_yn = 'Y'에 해당되는 list를 1~3개뽑아옴
        int listCnt = boardService.selectPopupListCnt(fvo);//  위 함수에서 뽑아오는 list 갯수 (1~3)사이

         List<PopupVO> fileList = new ArrayList<>();
        if(listCnt != 0){
            for( int i = 0; i < listCnt; i++){
                PopupVO pvo = new PopupVO();
                pvo.setSite_type(list.get(i).getSite_type());
                pvo.setPop_code(list.get(i).getPop_code());
                pvo = boardService.selectPopup(pvo); //for문으로 list에 담겨있는 pop_code로 해당 Popup을 한개씩 select함

                String file_sn = boardService.selectFileSn(pvo.getAtch_file_id());
                pvo.setFile_sn(file_sn);
                fileList.add(pvo);
            }
            model.addAttribute("fileList",fileList);
            model.addAttribute("popCnt",listCnt);
        }

        return new ModelAndView("/front/main/index");
    }
}
