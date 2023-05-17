package com.web.front.send.controller;

import com.bitkrx.core.util.HttpComLib;
import com.bitkrx.core.util.SecurityUtil;
import com.web.config.CmeResultVO;
import com.web.config.control.CmeDefaultExtendController;
import com.web.config.init.WebConfig;
import com.web.config.util.ComUtil;
import com.web.front.join.service.JoinService;
import com.web.front.join.vo.JoinAuthVO;
import com.web.front.join.vo.JoinVO;
import com.web.front.login.service.LoginService;
import com.web.front.login.util.LUtil;
import com.web.front.mypage.service.MypageService;
import com.web.front.mypage.vo.CustVO;
import com.web.front.send.service.SendService;
import com.web.front.send.vo.SendVO;
import com.web.front.wallet.service.WalletService;
import com.web.front.wallet.vo.WalletVO;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/front/send")
public class SendController extends CmeDefaultExtendController {

    LUtil lUtil = LUtil.getInstance();

    @Autowired
    WalletService walletService;

    @Autowired
    JoinService joinService;

    @Autowired
    LoginService loginService;

    @Autowired
    SendService sendService;

    @Autowired
    MypageService mypageService;

    @RequestMapping(value = "/sendView.dm")
    public ModelAndView sendView(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {

        String loginId = lUtil.getLoginId(request, response);

        WalletVO wvo = new WalletVO();
        wvo.setUserEmail(loginId);
        wvo.setCoinNm("WS"); // 초기값
        List<WalletVO> walletList = walletService.getWalletList(wvo); //사용자 지갑주소 조회

        model.addAttribute("walletList", walletList);

        WalletVO wavo = new WalletVO();
        List<WalletVO> coinInfo = walletService.selectUseCoinList(wavo);
        model.addAttribute("coinInfo", coinInfo);

        return new ModelAndView("/front/send/sendView");

    }

    @RequestMapping(value = "/getWalletList.dm")
    public @ResponseBody List<WalletVO> getWalletList(@ModelAttribute SendVO vo, HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {

        String loginId = lUtil.getLoginId(request, response);

        WalletVO wvo = new WalletVO();
        wvo.setUserEmail(loginId);
        //wvo.setCoinNm(vo.getCoinNm());
        List<WalletVO> walletList = walletService.getWalletList(wvo); //사용자 지갑주소 조회

        return walletList;

    }

    @RequestMapping(value = "/getBalance.dm")
    public @ResponseBody JSONObject
    getBalance(@ModelAttribute SendVO vo, HttpServletRequest request, HttpServletResponse response) throws Exception {
        JSONObject res = new JSONObject();
        String loginId = lUtil.getLoginId(request, response); // 로그인검사 겸용

        String balanceVal = "";
        String ethBalance = "";

        vo.setUserEmail(loginId);
/*        JSONObject obj = ComUtil.apiJsonObj("/block.getBalance.dp/proc.go", vo);
        JSONParser parser = new JSONParser();
        JSONObject panBalanceObj = (JSONObject) parser.parse(obj.get("balance").toString());
        JSONObject ethBalanceObj = null;
        if("WS".equals(vo.getCoinNm())) {
            ethBalanceObj = (JSONObject) parser.parse(obj.get("ethBalance").toString());
        }*/
        System.out.println("### coinNm:" + vo.getCoinNm());
        System.out.println("### coinType:" + vo.getCoinType());
        System.out.println("### userEmail:" + vo.getUserEmail());
        System.out.println("### cnKndCd:" + vo.getCnKndCd());

/*        String rtnStr = HttpComLib.httpSendAPI(WebConfig.getInstance().API_URL + "/block.getBalance.dp/proc.go", vo);
        System.out.println("### rtnStr:" + rtnStr);*/

        JSONObject obj = ComUtil.apiJsonObj("/block.getBalance.dp/proc.go", vo);
        JSONParser parser = new JSONParser();
        JSONObject balObj = (JSONObject) parser.parse(obj.get("balance").toString());

        String failCd = (String)obj.get("failCd");
        if("999".equals(failCd)){

        }else if("998".equals(failCd)){

        }else if("997".equals(failCd)){

        }else{
            balanceVal = balObj.get("result").toString();
        }

/*        vo.setPageAmt("999999999");
        vo.setPageNum("1");
        //vo.setAdrs("0x15b1b82cc3f6a299ef8f20ab619847cc128243db");
        JSONObject addrObj = ComUtil.apiJsonObj("/block.addrInfo.dp/proc.go", vo);
        JSONObject sendList = (JSONObject)addrObj.get("data");*/

        List listMap = new ArrayList<Object>();
        JSONObject sendList = new JSONObject();

        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("userEmail", vo.getUserEmail());
        paramMap.put("cnKndCd", vo.getCnKndCd());
        paramMap.put("inOutType", "I");

        List<Map<String,Object>> rtnAssetInOutList = mypageService.getAssetInOutList(paramMap);

        if(rtnAssetInOutList.size() > 0){
            sendList.put("list", rtnAssetInOutList);
        }
/*        for(int i=0; i < 100; i++){
            Map<String,Object> resMap = new HashMap<String,Object>();
            resMap.put("A", i+"_123123asdfasdf2123123");
            resMap.put("B", i+"_201012123");
            resMap.put("C", i+"_121321.111");

            listMap.add(resMap);
        }*/

        res.put("balanceVal", balanceVal);
        res.put("sendList", sendList);
//        res.put("sendList", sendList);
/*        if("WS".equals(vo.getCoinNm())) {
            res.put("ethBalance", ethBalance);
        }*/

        return res;
    }

    @RequestMapping(value = "/getSendHistPage.dm")
    public @ResponseBody JSONObject
    getSendHistPage(@ModelAttribute SendVO vo, HttpServletRequest request, HttpServletResponse response) throws Exception {
        JSONParser parser = new JSONParser();

        vo.setAdrs(vo.getAddr());
        vo.setPageAmt("10");

        JSONObject addrObj = ComUtil.apiJsonObj("/block.addrInfo.dp/proc.go", vo);
        JSONObject sendList = (JSONObject)addrObj.get("data");
        JSONObject res = new JSONObject();
        res.put("sendList", sendList);

        return res;
    }

    /**
     * @Method Name  : sendingView
     * @작성일   : 2019. 6. 10.
     * @작성자   :  (주)씨엠이소프트 민병철
     * @변경이력  :
     * @Method 설명 :
     * @param vo
     * @param request
     * @param response
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/sendingView.dm")
    public ModelAndView sendingView(@ModelAttribute SendVO vo, HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {

        String loginId = lUtil.getLoginId(request, response);

        WalletVO wvo = new WalletVO();
        wvo.setCoinNm(vo.getCoinNm());
        wvo.setUserEmail(loginId);
        List<WalletVO> walletList = walletService.getWalletList(wvo); //사용자 지갑주소 조회

        List<WalletVO> coinInfo = walletService.selectUseCoinList(wvo);

        model.addAttribute("walletList", walletList);
        model.addAttribute("thisAddr", vo.getAddr());
        model.addAttribute("coinNm", vo.getCoinNm());
        model.addAttribute("coinInfo", coinInfo);

        CustVO cvo = new CustVO();
        cvo.setUserEmail(lUtil.getLoginId(request, response));
        SendVO svo = mypageService.selectSafeSendPwd(cvo);

        if(svo != null) {
            model.addAttribute("sendPwdUse", "Y");
        } else {
            model.addAttribute("sendPwdUse", "N");
        }

        return new ModelAndView("/front/send/sendingView");

    }

    @RequestMapping(value = "/reqSend.dm")
    public @ResponseBody
    CmeResultVO reqSend(@ModelAttribute SendVO vo, HttpServletRequest request, HttpServletResponse response) throws Exception {

        CmeResultVO resVo = new CmeResultVO();

        String loginId = lUtil.getLoginId(request, response);
        vo.setUserEmail(loginId);

        PrivateKey privateKey = (PrivateKey) request.getSession().getAttribute("_RSA_WEB_Key_");
        String aceKey = vo.getAceKey();
        aceKey = SecurityUtil.decryptRsa(privateKey, aceKey);
        String _userPw = SecurityUtil.Dec256Str(vo.getSendPwd(), aceKey);
        String _suserPw = SecurityUtil.Sha256Encode(_userPw);
        vo.setSendPwd(_suserPw);


        if("WS".equals(vo.getCoinNm())) {
            String lockMny = walletService.getLockMny(vo);
            System.out.println("lockMny >> " + lockMny);
            if(null!= lockMny && !"0".equals(lockMny)){
                WalletVO wwvo = new WalletVO();
                wwvo.setUserEmail(vo.getUserEmail());
                wwvo.setAddr(vo.getFromAddr());
                wwvo.setCoinNm(vo.getCoinNm());

                JSONObject obj = ComUtil.apiJsonObj("/block.getBalance.dp/proc.go", wwvo);
                JSONParser parser = new JSONParser();
                JSONObject panBalanceObj = (JSONObject) parser.parse(obj.get("balance").toString());
                String getResult = (String) panBalanceObj.get("result");

                BigDecimal getAmt = new BigDecimal(getResult);                  //잔액
                BigDecimal sendAmt = new BigDecimal(vo.getAmount());            //송금금액
                BigDecimal lockAmt = new BigDecimal(lockMny);                   //락갯수

                BigDecimal posAmt = getAmt.subtract(lockAmt);                   //송금 가능 자산

                if(sendAmt.compareTo(posAmt) > 0){
                    System.out.println("[불가] 송금신청금액 // " + sendAmt + ", lock 갯수 // "+lockAmt + ", 송금가능 갯수 //" + posAmt );
                    resVo.setResultCode(-1);
                    resVo.setResultMsg(getLocale(request,"02_02"));
                    return resVo;
                }else{
                    System.out.println("[가능] 송금신청금액 // " + sendAmt + ", lock 갯수 // "+ lockAmt + ", 송금가능 갯수 //" + posAmt );

                }
            }
        }


        WalletVO wvo = new WalletVO();
        wvo.setUserEmail(vo.getUserEmail());
        wvo.setAddr(vo.getFromAddr());
        wvo.setCurPwd(vo.getSendPwd());
        //wvo.setCoinNm(vo.getCoinNm());
        wvo.setCnKndCd(vo.getCnKndCd());
        int matchCnt = walletService.selectAddrPwdMatch(wvo);

        boolean limitAmt = true;

        if(matchCnt == 1) {

            if(!"".equals(vo.getSafeSendPwd())) {
                String safeAceKey = vo.getSafeAceKey();
                safeAceKey = SecurityUtil.decryptRsa(privateKey, safeAceKey);
                String safeSendPwd = SecurityUtil.Dec256Str(vo.getSafeSendPwd(), safeAceKey);
                String _safeSendPwd = SecurityUtil.Sha256Encode(safeSendPwd);
                vo.setSafeSendPwd(_safeSendPwd);
                int safeMatchCnt = mypageService.matchSafeSendPwd(vo);

                if(safeMatchCnt == 0) {
                    resVo.setResultCode(-1);
                    resVo.setResultMsg(getLocale(request,"02_29"));
                    return resVo;
                } else {
                    limitAmt = false;
                }
            }

            if(limitAmt) {
                SendVO lmtVo = sendService.selectSendLimitAmt(vo);
                if(lmtVo != null) {
                    BigDecimal max = new BigDecimal(lmtVo.getMaxSendLmt());
                    //BigDecimal min = new BigDecimal(lmtVo.getMinSendLmt());
                    BigDecimal amt = new BigDecimal(vo.getAmount());

                    Object[] args = {max.doubleValue(),vo.getCoinNm()};

                    if(amt.compareTo(max) == 1) {
                        resVo.setResultCode(-1);
                        resVo.setResultMsg(getLocale(request,"02_30",args));
                        return resVo;
                    }

                    /*if(amt.compareTo(min) == -1) {
                        resVo.setResultCode(-1);
                        resVo.setResultMsg("최소송금액 " + min.doubleValue() + vo.getCoinNm() + "보다 작을 수 없습니다.");
                        return resVo;
                    }*/
                }
            }

            JSONObject sendObj = ComUtil.apiJsonObj("/block.sendTransaction.dp/proc.go", vo);
            String failCd = (String)sendObj.get("failCd");

            resVo.setResultCode(-1);
            resVo.setResultMsg("");
            if ("".equals(failCd)) {
                resVo.setResultCode(1);
                resVo.setResultMsg(getLocale(request, "TEXT_152"));
                return resVo;
            } else if("998".equals(failCd)) {
                resVo.setResultCode(-1);
                resVo.setResultMsg("");
            } else if("993".equals(failCd)) {
                resVo.setResultCode(-1);
                resVo.setResultMsg(getLocale(request, "TEXT_150"));
            } else if("992".equals(failCd)) {
                resVo.setResultCode(-1);
                resVo.setResultMsg(getLocale(request, "TEXT_388"));
            } else if("777".equals(failCd)){
                resVo.setResultCode(-1);
                resVo.setResultMsg(getLocale(request, "02_01"));
            } else if("995".equals(failCd)){
                resVo.setResultCode(-1);
                String ethFee = (String)sendObj.get("message");
                resVo.setResultMsg(getLocale(request, "TEXT_388_1").replace("e0", ethFee));
            }else{
                resVo.setResultCode(-1);
                resVo.setResultMsg(getLocale(request, "02_01"));
            }
        } else {
            resVo.setResultCode(-1);
            resVo.setResultMsg(getLocale(request, "TEXT_162"));
        }

        return resVo;
    }

    @RequestMapping(value = "/checkSendAuth.dm")
    public @ResponseBody CmeResultVO
    checkSendAuth(@ModelAttribute SendVO vo, HttpServletRequest request, HttpServletResponse response) throws Exception {
        CmeResultVO res = new CmeResultVO();

        CustVO cvo = new CustVO();
        cvo.setUserEmail(lUtil.getLoginId(request, response));
        CustVO basic = loginService.getCustInfo(cvo);

        if("".equals(basic.getHphone())) {
            res.setResultCode(-1);
            res.setResultMsg(getLocale(request, "TEXT_423"));
            return res;
        }

        JoinVO jvo = new JoinVO();
        jvo.setUserEmail(lUtil.getLoginId(request, response));
        JoinAuthVO avo = joinService.selectUserJoinAuth(jvo);
        if("N".equals(avo.getSmsCertYn())) {
            res.setResultCode(-1);
            res.setResultMsg(getLocale(request, "TEXT_423"));
            return res;
        }

        res.setResultCode(1);

        return res;
    }

    @RequestMapping(value = "/findAddr.dm")
    public @ResponseBody JSONObject
    findAddr(@ModelAttribute CustVO vo, HttpServletRequest request, HttpServletResponse response) throws Exception {
        JSONObject res = new JSONObject();
        res.put("resultCode", -1);
        JoinVO jvo = new JoinVO();
        jvo.setHphone(vo.getHphone());
        int matchCnt = joinService.matchUserMobile(jvo);

        if(matchCnt == 0) {
            res.put("resultCode", -2);
            res.put("resultMsg", getLocale(request,"02_31"));
            return res;
        } else if(matchCnt > 1) {
            res.put("resultCode", -3);
            res.put("resultMsg", getLocale(request,"02_32"));
            return res;
        } else if(matchCnt == 1){
            List<WalletVO> list = sendService.findAddr(vo);
            Object args[] = {vo.getHphone(),vo.getCoinNm()};
            if(list.size() == 0) {
                res.put("resultCode", -4);
                res.put("resultMsg", getLocale(request,"02_33",args));
            } else if(list.size() > 1) {
                res.put("resultCode", -5);
                res.put("resultMsg", getLocale(request,"02_34"));
            } else if(list.size() == 1) {
                res.put("resultCode", 1);
            }

            res.put("wallet", list);
        }

        if((int)res.get("resultCode") == -1) {
            res.put("resultMsg", getLocale(request,"02_35"));
        }

        return res;
    }



}
