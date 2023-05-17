/**
 * 씨엠이 소프트 자체 프로젝트 입니다. 허가 없이 복제 및 배포 할 수 없습니다.
 * 개발사 : (주)씨엠이소프트
 * CopyRight 씨엠이소프트 - 2017
 */
package com.web.front.wallet.control;


import com.bitkrx.core.util.SecurityUtil;
import com.web.config.CmeResultVO;
import com.web.config.CmeResultVO;
import com.web.config.control.CmeDefaultExtendController;
import com.web.config.init.WebConfig;
import com.web.config.util.ComUtil;
import com.web.config.util.StringUtils;
import com.web.front.login.util.LUtil;
import com.web.front.mypage.vo.CustVO;
import com.web.front.wallet.service.WalletService;
import com.web.front.wallet.vo.WalletVO;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.PrivateKey;
import java.util.*;

@Controller
@RequestMapping(value = "/front/wallet")
public class WalletController extends CmeDefaultExtendController {

    @Autowired
    WalletService walletService;

    LUtil lUtil = LUtil.getInstance();


    /**
     * @param vo
     * @param request
     * @param response
     * @param model
     * @return
     * @throws Exception
     * @Method Name  : walletManageView
     * @작성일 : 2019. 3. 13.
     * @작성자 :  (주)씨엠이소프트 이진우
     * @변경이력 :
     * @Method 설명 :지갑 관리 View
     */
    @RequestMapping(value = "/walletManageView.dm")
    public ModelAndView walletManageView(@RequestParam HashMap<String, String> param, @ModelAttribute CustVO vo, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {

        lUtil.getLoginId(request, response);

        WalletVO wvo = new WalletVO();
        List<WalletVO> coinList = walletService.selectUseCoinList(wvo);

        model.addAttribute("coinList", coinList);

        return new ModelAndView("/front/mypage/wallet_manage");

    }

    /**
     * @param vo
     * @param request
     * @param response
     * @param model
     * @return
     * @throws Exception
     * @Method Name  : activityListView
     * @작성일 : 2019. 3. 13.
     * @작성자 :  (주)씨엠이소프트 이진우
     * @변경이력 :
     * @Method 설명 :지갑 관리 View
     */
    @RequestMapping(value = "/activityListView.dm")
    public ModelAndView activityListView(@RequestParam HashMap<String, String> param, @ModelAttribute CustVO vo, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {

		/*JSONObject obj = ComUtil.apiJsonObj(WebConfig.getInstance().apiUrl + "/mail.commonCertMail.dp/proc.go", param);
		model.addAttribute("wList",obj);
		*/
        return new ModelAndView("/front/mypage/activity_list");

    }

    /**
     * @param vo
     * @param request
     * @param response
     * @param model
     * @return
     * @throws Exception
     * @Method Name  : provisionListView
     * @작성일 : 2019. 3. 13.
     * @작성자 :  (주)씨엠이소프트 이진우
     * @변경이력 :
     * @Method 설명 :지갑 관리 View
     */
    @RequestMapping(value = "/provisionListView.dm")
    public ModelAndView provisionListView(@RequestParam HashMap<String, String> param, @ModelAttribute CustVO vo, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {

		/*JSONObject obj = ComUtil.apiJsonObj(WebConfig.getInstance().apiUrl + "/bt.mail.commonCertMail.dp/proc.go", param);
		model.addAttribute("wList",obj);
		*/
        return new ModelAndView("/front/mypage/provision_list");
    }

    /**
     * @param vo
     * @param request
     * @param response
     * @param model
     * @return
     * @throws Exception
     * @Method Name  : usbManageView
     * @작성일 : 2019. 3. 13.
     * @작성자 :  (주)씨엠이소프트 이진우
     * @변경이력 :
     * @Method 설명 :지갑 관리 View
     */
    @RequestMapping(value = "/usbManageView.dm")
    public ModelAndView usbManageView(@RequestParam HashMap<String, String> param, @ModelAttribute CustVO vo, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {

        lUtil.getLoginId(request, response);
        return new ModelAndView("/front/mypage/usb_manage");

    }

    /**
     * @param vo
     * @param request
     * @param response
     * @param model
     * @return
     * @throws Exception
     * @Method Name  : walletManage
     * @작성일 : 2019. 3. 13.
     * @작성자 :  (주)씨엠이소프트 이진우
     * @변경이력 :
     * @Method 설명 :지갑 관리 페이지
     */
    @RequestMapping(value = "/getUserWallet.dm")
    public @ResponseBody
    WalletVO getUserWallet(@ModelAttribute WalletVO vo, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
        String userEmail = lUtil.getLoginId(request, response);
        vo.setUserEmail(userEmail);
        List<WalletVO> welletList = walletService.getWalletList(vo); //사용자 지갑주소 조회
        if (null != welletList && welletList.size() > 0) {
            for (int i = 0; i < welletList.size(); i++) {

                welletList.get(i).setUserEmail(userEmail);
                welletList.get(i).setCoinNm(welletList.get(i).getCoinNm());
                welletList.get(i).setCoinType(welletList.get(i).getCoinType());
                JSONObject obj = ComUtil.apiJsonObj("/block.getBalance.dp/proc.go", welletList.get(i));

                JSONParser parser = new JSONParser();
                if ("000".equals(obj.get("resultStrCode"))) {
                    JSONObject rtnBalance = (JSONObject) parser.parse(obj.get("balance").toString());
                    JSONObject ethBalance = null;
                    if("WS".equals(vo.getCoinNm())) {
                        ethBalance = (JSONObject) parser.parse(obj.get("ethBalance").toString());
                    }

                    String failCd = (String) obj.get("failCd");
                    if ("999".equals(failCd)) {

                    } else if ("998".equals(failCd)) {

                    } else if ("997".equals(failCd)) {

                    } else {
                        welletList.get(i).setBalanceVal(rtnBalance.get("result").toString());
/*                        welletList.get(i).setBalancePan(rtnBalance.get("result").toString());
                        if("WS".equals(vo.getCoinNm())) {
                            welletList.get(i).setBalanceEth(ethBalance.get("result").toString());
                        }*/
                    }
                }
            }
        }
        vo.setList(welletList);
        return vo;
    }

    @RequestMapping(value = "/createWallet.dm")
    public @ResponseBody
    CmeResultVO createWallet(@ModelAttribute WalletVO vo, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
        CmeResultVO res = new CmeResultVO();
        vo.setUserEmail(lUtil.getLoginId(request, response));
        WalletVO _vo = new WalletVO();
        _vo.setCoinType(vo.getCoinType());
        _vo.setUserEmail(vo.getUserEmail());
        List<WalletVO> walletList = walletService.getWalletList(_vo); //지갑주소 조회
        int insWallet = 0;

        if(walletList.size() > 0){ // 같은 타입의 주소가 이미 하나이상 존재하면 동일한 주소로 그냥 입력
            WalletVO wVo = new WalletVO();
            wVo = walletList.get(0);

            if("05".equals(vo.getCoinType())){
                vo.setPrivateKey(wVo.getPrivateKey());
                vo.setPublicKey(wVo.getPublicKey());
            }

            vo.setAddr(wVo.getAddr());
            vo.setUserDiv("G");//일반회원
            insWallet = walletService.insertWallet(vo); //지갑주소 DB insert
            if (insWallet > 0) {
                res.setResultCode(1);
                res.setResultMsg(getLocale(request, "TEXT_385"));
            } else {
                res.setResultCode(-1);
                res.setResultMsg(getLocale(request, "TEXT_386"));
            }
            return res;
        }

        JSONObject obj = ComUtil.apiJsonObj("/block.createWallet.dp/proc.go", vo);
        JSONParser parser = new JSONParser();
        if ("000".equals(obj.get("resultStrCode"))) {

            String failCd = (String) obj.get("failCd");
            if ("999".equals(failCd)) {
                res.setResultCode(-1);
                res.setResultMsg(getLocale(request, "TEXT_386"));
            } else if ("998".equals(failCd)) {
                res.setResultCode(-1);
                res.setResultMsg(getLocale(request, "TEXT_386"));
            } else if ("997".equals(failCd)) {
                res.setResultCode(-1);
                res.setResultMsg(getLocale(request, "TEXT_401"));
            } else {
                String address = "", privateKey = "", publicKey="";
                if("05".equals(vo.getCoinType())){
                    address = StringUtils.checkNull(obj.get("addr")+"");
                    privateKey = StringUtils.checkNull(obj.get("privateKey")+"");
                    publicKey = StringUtils.checkNull(obj.get("publicKey")+"");
                    vo.setPrivateKey(privateKey);
                    vo.setPublicKey(publicKey);
                }else{
                    JSONObject addr = (JSONObject) parser.parse((String) obj.get("addr"));
                    address = (String) addr.get("result"); //API에서 받아온 전자지갑 주소
                }

                WalletVO nvo = new WalletVO();
                nvo.setAddr(address);
                List<WalletVO> welletList = walletService.getWalletList(nvo); //지갑주소 조회

                if (null == welletList || welletList.size() == 0) { //해당주소로 select 값이 없으면
                    vo.setAddr(address);
                    vo.setUserDiv("G");//일반회원
                    System.out.println("##Addr:" + vo.getAddr());
                    System.out.println("##PrivateKey:" + vo.getPrivateKey());
                    System.out.println("##PublicKey:" + vo.getPublicKey());

                    insWallet = walletService.insertWallet(vo); //지갑주소 DB insert
                    //insWallet = 1;
                    if (insWallet > 0) {
                        res.setResultCode(1);
                        res.setResultMsg(getLocale(request, "TEXT_385"));
                    } else {
                        res.setResultCode(-1);
                        res.setResultMsg(getLocale(request, "TEXT_386"));
                    }
                } else {
                    res.setResultCode(-1);
                    res.setResultMsg("이미 사용중인 전자지갑 주소입니다."); // todo ...????
                }
            }
        }
        return res;
    }

    @RequestMapping(value = "/settingPwd.dm")
    public @ResponseBody
    CmeResultVO settingPwd(@ModelAttribute WalletVO vo, HttpServletRequest request, HttpServletResponse response) throws Exception {
        CmeResultVO res = new CmeResultVO();

        PrivateKey privateKey = (PrivateKey) request.getSession().getAttribute("_RSA_WEB_Key_");
        String aceKey = vo.getAceKey();
        aceKey = SecurityUtil.decryptRsa(privateKey, aceKey);
        String _userPw = SecurityUtil.Dec256Str(vo.getSendPwd(), aceKey);
        String _suserPw = SecurityUtil.Sha256Encode(_userPw);
        vo.setSendPwd(_suserPw);

        res.setResultCode(1);
        res.setResultMsg(getLocale(request, "TEXT_90"));
        vo.setUserEmail(lUtil.getLoginId(request, response));

        walletService.updateAddrPwd(vo);

        return res;

    }

    @RequestMapping(value = "/changePwd.dm")
    public @ResponseBody
    CmeResultVO changePwd(@ModelAttribute WalletVO vo, HttpServletRequest request, HttpServletResponse response) throws Exception {
        CmeResultVO res = new CmeResultVO();

        PrivateKey privateKey = (PrivateKey) request.getSession().getAttribute("_RSA_WEB_Key_");
        String aceKey = vo.getAceKey();
        aceKey = SecurityUtil.decryptRsa(privateKey, aceKey);
        String _userPw = SecurityUtil.Dec256Str(vo.getCurPwd(), aceKey);
        String _curPw = SecurityUtil.Sha256Encode(_userPw);
        vo.setCurPwd(_curPw);

        vo.setUserEmail(lUtil.getLoginId(request, response));
        int matchCnt = walletService.selectAddrPwdMatch(vo);

        if(matchCnt == 1) {
            String _sendPw = SecurityUtil.Dec256Str(vo.getSendPwd(), aceKey);
            String _newSendPwd = SecurityUtil.Sha256Encode(_sendPw);
            vo.setSendPwd(_newSendPwd);

            res.setResultCode(1);
            res.setResultMsg(getLocale(request, "TEXT_121"));
            walletService.updateAddrPwd(vo);
        } else {
            res.setResultCode(-1);
            res.setResultMsg(getLocale(request, "TEXT_162"));
        }

        return res;

    }
}