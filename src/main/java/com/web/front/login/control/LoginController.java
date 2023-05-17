package com.web.front.login.control;


import com.bitkrx.core.util.HttpComLib;
import com.bitkrx.core.util.SecurityUtil;
import com.web.config.CmeResultVO;
import com.web.config.control.CmeDefaultExtendController;
import com.web.config.init.WebConfig;
import com.web.config.mail.MailSenderImpl;
import com.web.config.service.CmeProperties;
import com.web.config.util.ComUtil;
import com.web.config.util.SessionUtil;
import com.web.config.util.StringUtils;
import com.web.front.lending.service.LendingService;
import com.web.front.login.LoginConstant;
import com.web.front.login.interceptor.LoginInterceptor;
import com.web.front.login.service.LoginService;
import com.web.front.login.vo.LoginHistoryVO;
import com.web.front.mypage.vo.CustVO;
import com.web.front.wallet.service.WalletService;
import com.web.front.wallet.vo.WalletVO;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.security.PrivateKey;
import java.util.HashMap;

@Controller
@RequestMapping(value="/front/login")
public class LoginController extends CmeDefaultExtendController {

	SessionUtil sUtil = SessionUtil.getinstance();

	@Autowired
	LoginService loginService;

	@Autowired
	MailSenderImpl mailSender;

	@Autowired
	ServletContext servletContext;

	@Autowired
	LendingService lendingService;

	@Autowired
	WalletService walletService;

	@RequestMapping(value = "/loginView.dm")
	public ModelAndView login(CustVO vo, HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		return new ModelAndView("/front/login/loginView");
	}

	/**
	 * @param vo
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 * @Method Name  : deleteBoard
	 * @작성일 : 2017. 11. 3.
	 * @작성자 :  (주)씨엠이소프트 임승연
	 * @변경이력 :
	 * @Method 설명 :로그인 체크 및 로그인
	 */
	@RequestMapping(value = "/loginAct.dm")
	public @ResponseBody
	CmeResultVO loginAct(@ModelAttribute("searchVO") CustVO vo, HttpSession httpSession, HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		CmeResultVO res = new CmeResultVO();// 결과 보낼곳

		CustVO cvo = loginService.getCustInfo(vo);
		LoginHistoryVO hisvo = new LoginHistoryVO();

		if (null == cvo) {
			res.setResultCode(-1);
			res.setResultMsg(getLocale(request, "TEXT_32"));
		} else {
			//===================== 로그인 기록 기본세팅   -최정현 추가 2019-12-02
			if (LoginInterceptor.isDevice(request)) {//모바일 인 경우 true
				hisvo.setBrws_cd("MOBILE");
				hisvo.setOs_cd("MOBILE");
			} else {// 웹인경우 false
				hisvo.setBrws_cd("WEB");
				hisvo.setOs_cd("WEB");
			}
			hisvo.setConn_ip(ComUtil.getRemoteIP(request));// 사실상 127.0.0.1밖에 기록안되는듯.
			hisvo.setUser_email(vo.getUserEmail());
			//================= 로그인 기록 기본세팅 끝

			PrivateKey privateKey = (PrivateKey) request.getSession().getAttribute("_RSA_WEB_Key_");
			String aceKey = vo.getAceKey();
			aceKey = SecurityUtil.decryptRsa(privateKey, aceKey);
			String _userPw = SecurityUtil.Dec256Str(vo.getUserPwd(), aceKey);
			String _suserPw = SecurityUtil.Sha256Encode(_userPw);
			vo.setUserPwd(_suserPw);

			if ("Y".equals(cvo.getChgPwd().trim()) || "".equals(cvo.getChgPwd().trim())) {//CHG_PWD가 N이던 Y던 여기로직은타지않음.
				res.setResultCode(-1);
				res.setResultMsg(getLocale(request, "TEXT_399"));
				hisvo.setFail_cd("CMMC00000000061");
				hisvo.setLogin_yn("N");
			} else if (!cvo.getUserPwd().equals(vo.getUserPwd())) {
				res.setResultCode(-1);
				res.setResultMsg(getLocale(request, "TEXT_34"));
				hisvo.setFail_cd("CMMC00000000041");
				hisvo.setLogin_yn("N");
			} else if ("N".equals(cvo.getUseYn())) {
				res.setResultCode(-1);
				res.setResultMsg(getLocale(request, "TEXT_400"));
				hisvo.setFail_cd("CMMC00000000062");
				hisvo.setLogin_yn("N");
			} else {
				res.setResultCode(1);//로그인 성공
				hisvo.setLogin_yn("Y");
				loginService.login(request, response, vo);
				res.setResultStrCode(httpSession.getId());
			}
			loginService.historyInsert(hisvo);//로그인이력 insert

			/*TRC20 계열 입금 체킹 시작*/
			WalletVO _vo = new WalletVO();
			_vo.setUserEmail(vo.getUserEmail());
			CmeResultVO resTrcAssetVo = walletService.getTrcDepositInfo(_vo);
		}

		return res;
	}

	/**
	 * @param :vo
	 * @param request
	 * @param response
	 * @param :model
	 * @return
	 * @throws Exception
	 * @Method Name  : logOut
	 * @작성일 :
	 * @작성자 :
	 * @변경이력 :
	 * @Method 설명 :로그아웃
	 */
	@RequestMapping(value = "/logout.dm")
	public @ResponseBody CmeResultVO logOut(HttpServletRequest request, HttpServletResponse response) throws Exception {

		CmeResultVO res = new CmeResultVO();

		loginService.logout(request);

		String lang = getLocale(request);
		if (null == lang || "".equals(lang)) {
			setLocale(request, response, "en");
			lang = getLocale(request);
		}

		setLocale(request, response, lang);
		res.setResultCode(1);
		res.setResultStrCode1("/front.main.index.dp/proc.go?lang=" + lang);
		return res;
	}

	/**
	 * @Method Name  : mailExsitCheck
	 * @작성일 : 2019. 12. 03.
	 * @작성자 :  (주)씨엠이소프트 윤원식
	 * @변경이력 :
	 * @Method 설명 : 등록된 이메일 체크
	 */
	@RequestMapping(value = "/mailExsitCheck.dm")
	public @ResponseBody
	CmeResultVO mailExsitCheck(CustVO vo, HttpServletRequest request, HttpServletResponse response) throws Exception {

		CmeResultVO res = new CmeResultVO();// 결과 보낼곳
		CustVO cvo = loginService.getCustInfo(vo);

		if (null == cvo) {
			res.setResultCode(-1);
			res.setResultMsg(getLocale(request, "TEXT_394"));
		} else {
			res.setResultCode(1);
		}

		return res;

	}

	@RequestMapping(value = "/rcvAcc.dm")
	public ModelAndView rcvAcc(CustVO vo, HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {

		String _userEmail = ComUtil.Dec256Str(vo.getEncUserEmail()); //복호화
		vo.setUserEmail(_userEmail);
		CustVO cvo = loginService.getCustInfo(vo);

		if (null == cvo) {
//			res.setResultCode(-1);
//			res.setResultMsg(getLocale(request, "01_134"));
		} else {
//			res.setResultCode(1);
			loginService.login(request, response, vo);
		}

		return new ModelAndView("redirect:/index.jsp");
	}

	@RequestMapping(value = "/sndAccToFound.dm")
	public @ResponseBody CmeResultVO sndAccToFound(CustVO vo, HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {

		CmeResultVO res = new CmeResultVO();// 결과 보낼곳

		Object obj = request.getSession().getAttribute(LoginConstant.LOGIN_SESSION);
		if (obj != null) {
			CustVO custVO = (CustVO) obj;
			String encUserEmail = ComUtil.Enc256Str(custVO.getUserEmail());
			vo.setUserEmail(encUserEmail);

			JSONParser parser = new JSONParser();
			JSONObject jsonObj = new JSONObject();
			try {
				String apiStr = HttpComLib.httpSendAPI(CmeProperties.getProperty("FOUND.URL") +"/front.login.rcvAccFromWallet.dp/proc.go", vo);
				jsonObj = (JSONObject) parser.parse(apiStr);
				int resultCode = Integer.parseInt(jsonObj.get("resultCode") + "");
				res.setResultCode(resultCode);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return res;
	}
}