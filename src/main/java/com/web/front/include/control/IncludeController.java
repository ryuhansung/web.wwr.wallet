package com.web.front.include.control;

import com.bitkrx.core.util.HttpComLib;
import com.bitkrx.core.util.SecurityUtil;
import com.web.config.CmeResultVO;
import com.web.config.control.CmeDefaultExtendController;
import com.web.config.init.WebConfig;
import com.web.config.util.ComUtil;
import com.web.front.lending.service.LendingService;
import com.web.front.lending.vo.LendingVO;
import com.web.front.login.LoginConstant;
import com.web.front.login.interceptor.LoginInterceptor;
import com.web.front.login.service.LoginService;
import com.web.front.login.util.LUtil;
import com.web.front.login.vo.EncVO;
import com.web.front.mypage.service.MypageService;
import com.web.front.mypage.vo.CustVO;
import com.web.front.push.service.PushService;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.security.PrivateKey;
import java.util.List;
import java.util.Map;

/**
 * @프로젝트명	: cme.web.bitkrx
 * @패키지    	: com.web.bitkrx.include.control
 * @클래스명  	: cme.web.bitkrx
 * @작성자		: (주)씨엠이소프트 임승연
 * @작성일		: 2017. 8. 28.
 */
@Controller
@RequestMapping(value="/front/include")
public class IncludeController extends CmeDefaultExtendController {

	@Autowired
	MypageService mypageService;

	@Autowired
	ServletContext servletContext;

	@Autowired
	LoginService loginService;

	@Autowired
	PushService pushService;

	@Autowired
	LendingService lendingService;

	LUtil lUtil = LUtil.getInstance();

	@RequestMapping(value="/common_front_head.dm")
	public ModelAndView commHeader(HttpServletRequest request, ModelMap model)throws Exception{
		model.addAttribute("lang" , getLocale(request));

		return new ModelAndView("/front/include/common_front_head");
	}

	/**
	 * @Method Name  : getGnbHead
	 * @작성일   :
	 * @작성자   :
	 * @변경이력  :
	 * @Method 설명 : 공통 header 정보
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/inc_header.dm")
	public ModelAndView getGnbHead(HttpServletRequest request, ModelMap model)throws Exception{
		String lang = getLocale(request);
		model.addAttribute("lang", lang);

		HttpSession httpSession = request.getSession();
		Object res = httpSession.getAttribute(LoginConstant.LOGIN_SESSION);

		if(res != null){
			CustVO vo = (CustVO)res;
			LendingVO lvo = lendingService.selectLending(vo.getUserEmail());

			model.addAttribute("lvo", lvo);
		}



    	return new ModelAndView("/front/include/inc_header");
	}

	/**
	 * @Method Name  : encgetKey
	 * @작성일   :
	 * @작성자   : (주)씨엠이소프트 김수연
	 * @변경이력  :
	 * @Method 설명 : getKey
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getKey.dm")
	public @ResponseBody
	EncVO encgetKey(@ModelAttribute EncVO vo, HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {

		EncVO res = new EncVO();
 		Map<String,Object> map = SecurityUtil.RsaKeyCreate();
		PrivateKey privateKey = (PrivateKey) map.get("privateKey");
		//개인키를 session 에 저장한다.
		request.getSession().setAttribute("_RSA_WEB_Key_", privateKey);

		res.setModule((String) map.get("pubKeyModule"));
		res.setKey((String) map.get("pubKeyExponent"));

		return res;
	}

	@RequestMapping(value = "/isIncLogin.dm")
	public @ResponseBody
	CmeResultVO isIncLogin(CustVO vo, HttpServletRequest request, HttpServletResponse response) throws Exception {
		CmeResultVO res = new CmeResultVO();

		Object isLogin = request.getSession().getAttribute(LoginConstant.LOGIN_SESSION);
		if(isLogin == null) {
			res.setResultCode(-1);
		} else {
			res.setResultCode(1);
		}

		return res;
	}

	@RequestMapping(value = "/updateToken.dm")
	public @ResponseBody
	CmeResultVO updateToken(CustVO vo, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Object isLogin = request.getSession().getAttribute(LoginConstant.LOGIN_SESSION);
		CmeResultVO res = new CmeResultVO();
		res.setResultCode(1);

		if(isLogin != null) {
			vo.setUserEmail(lUtil.getLoginId(request, response));
			pushService.updateToken(vo);
		}

		return res;
	}


}
