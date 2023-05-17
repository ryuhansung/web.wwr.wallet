package com.web.front.wallet.service.impl;

import com.bitkrx.core.util.HttpComLib;
import com.web.config.CmeResultVO;
import com.web.config.init.WebConfig;
import com.web.config.util.ComUtil;
import com.web.front.send.vo.SendVO;
import com.web.front.wallet.dao.WalletDAO;
import com.web.front.wallet.service.WalletService;
import com.web.front.wallet.vo.WalletVO;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WalletServiceImpl implements WalletService {
    @Autowired
    WalletDAO walletDAO;

    @Override
    public List<WalletVO> getWalletList(WalletVO vo) throws Exception {
        return walletDAO.getWalletList(vo);
    }

    @Override
    public int insertWallet(WalletVO vo) throws Exception {
        return walletDAO.insertWallet(vo);
    }

    @Override
    public void updateAddrPwd(WalletVO vo) throws Exception {
        walletDAO.updateAddrPwd(vo);
    }

    @Override
    public int selectAddrPwdMatch(WalletVO vo) throws Exception {
        return walletDAO.selectAddrPwdMatch(vo);
    }

    @Override
    public List<WalletVO> selectUseCoinList(WalletVO vo) throws Exception {
        return walletDAO.selectUseCoinList(vo);
    }

    @Override
    public String getLockMny(SendVO vo) throws Exception {
        return walletDAO.getLockMny(vo);
    }

    @Override
    public CmeResultVO getTrcDepositInfo(WalletVO vo) throws Exception {

        System.out.println("## TRC Asset LookUp Start >>>>>" + vo.getUserEmail());
        List<WalletVO> welletList = getWalletList(vo); //사용자 지갑주소 조회
        CmeResultVO cmeResultVO = new CmeResultVO();
        if (null != welletList && welletList.size() > 0) {
            for (WalletVO listVo : welletList) {
                if("05".equals(listVo.getCoinType())){ // 05 타입 TRC 계열만 입금확인을 진행한다.
                    vo.setCnKndCd(listVo.getCnKndCd());
                    JSONObject obj = ComUtil.apiJsonObj("/block.deposit.getTrcDepWithInfo.dp/proc.go", vo);
/*                    String rtnStr = HttpComLib.httpSendAPI(WebConfig.getInstance().API_URL + "/block.deposit.getTrcDepWithInfo.dp/proc.go", vo);
                    System.out.println("####>>>" + rtnStr);
                    JSONObject obj = new JSONObject();
                    JSONParser parser = new JSONParser();
                    obj = (JSONObject)parser.parse(rtnStr);*/

                    int resultCode = Integer.parseInt(obj.get("resultCode")+"");
                    String resultMsg = obj.get("resultMsg") + "";

                    System.out.println("##>>resultCode ::" + resultCode);
                    System.out.println("##>>resultMsg ::" + resultMsg);

                    cmeResultVO.setResultCode(resultCode);
                    cmeResultVO.setResultMsg(resultMsg);

                    if(resultCode > 0){
                        JSONArray trList = new JSONArray();
                        trList = (JSONArray)obj.get("data");
                        if(trList.size() > 0){
                            for(int i=0; i< trList.size(); i++){
                                JSONObject objVal = (JSONObject)trList.get(i);
                                System.out.println("##["+i+"]####BlockDate==>" + objVal.get("BlockDate") + "");
                                System.out.println("##["+i+"]####BlockTime==>" + objVal.get("BlockTime") + "");
                                System.out.println("##["+i+"]####fromAddr===>" + objVal.get("fromAddr") + "");
                                System.out.println("##["+i+"]####toAddr=====>" + objVal.get("toAddr") + "");
                                System.out.println("##["+i+"]####Value======>" + objVal.get("Value") + "");
                                System.out.println("##["+i+"]####txId=======>" + objVal.get("txId") + "");
                            }
                            System.out.println("##총 입금건수:" + trList.size());
                        }
                    }
                }
            }
        }

        return cmeResultVO;
    }

}
