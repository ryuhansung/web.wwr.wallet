package com.web.front.wallet.vo;

import java.util.List;

public class WalletVO {
    private String addr = "";                // 지갑주소
    private String cnKndCd = "";                // 코인CD (?)
    private String userEmail = "";              // 사용자 메일
    private String natnCode = "";               // 국가코드(?)
    private String brhCode = "";                // 추천인코드(?)
    private String usbId = "";                  // USB ID
    private String usbDiv = "";                 // USB 구분 (P : 획득 프로 / W :월렛)
    private String userDiv = "";                // 회원유형 (G : 일반 사용자 / U : 소유자 / S : 공유자)';
    private String regDt = "";                  // 생성일
    private String regEmail = "";               // 생성자
    private String uptDt = "";                  // 수정일
    private String uptEmail = "";               // 수정자
    private String balancePan = "";             // Ws 수량
    private String balanceEth = "";             // Eth 수량
    private String sendPwd = "";                // 출금암호
    private String aceKey = "";                 // 암호화용 aceKey
    private String curPwd = "";                 // 출금암호 변경때 쓰이는 현재암호
    private String hasPwdYn = "";               // 출금암호 셋팅여부
    private String coinNm = "";                 // 코인 이름
    private String sendCmt = "";                // 출금수수료
    String coinType = ""; //코인타입
    String privateKey = "";
    String publicKey = "";
    String balanceVal = "";
    String tokenYn = "";

    public String getTokenYn() {
        return tokenYn;
    }

    public void setTokenYn(String tokenYn) {
        this.tokenYn = tokenYn;
    }

    public String getBalanceVal() {
        return balanceVal;
    }

    public void setBalanceVal(String balanceVal) {
        this.balanceVal = balanceVal;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public String getCoinType() {
        return coinType;
    }

    public void setCoinType(String coinType) {
        this.coinType = coinType;
    }

    private List<WalletVO> list;                // 조회 리턴용 리스트

    public String getSendPwd() {
        return sendPwd;
    }

    public void setSendPwd(String sendPwd) {
        this.sendPwd = sendPwd;
    }

    public String getAceKey() {
        return aceKey;
    }

    public void setAceKey(String aceKey) {
        this.aceKey = aceKey;
    }

    public String getCurPwd() {
        return curPwd;
    }

    public void setCurPwd(String curPwd) {
        this.curPwd = curPwd;
    }

    public String getHasPwdYn() {
        return hasPwdYn;
    }

    public void setHasPwdYn(String hasPwdYn) {
        this.hasPwdYn = hasPwdYn;
    }

    public String getCoinNm() {
        return coinNm;
    }

    public void setCoinNm(String coinNm) {
        this.coinNm = coinNm;
    }

    public String getSendCmt() {
        return sendCmt;
    }

    public void setSendCmt(String sendCmt) {
        this.sendCmt = sendCmt;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getCnKndCd() {
        return cnKndCd;
    }

    public void setCnKndCd(String cnKndCd) {
        this.cnKndCd = cnKndCd;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getNatnCode() {
        return natnCode;
    }

    public void setNatnCode(String natnCode) {
        this.natnCode = natnCode;
    }

    public String getBrhCode() {
        return brhCode;
    }

    public void setBrhCode(String brhCode) {
        this.brhCode = brhCode;
    }

    public String getUsbId() {
        return usbId;
    }

    public void setUsbId(String usbId) {
        this.usbId = usbId;
    }

    public String getUsbDiv() {
        return usbDiv;
    }

    public void setUsbDiv(String usbDiv) {
        this.usbDiv = usbDiv;
    }

    public String getUserDiv() {
        return userDiv;
    }

    public void setUserDiv(String userDiv) {
        this.userDiv = userDiv;
    }

    public String getRegDt() {
        return regDt;
    }

    public void setRegDt(String regDt) {
        this.regDt = regDt;
    }

    public String getRegEmail() {
        return regEmail;
    }

    public void setRegEmail(String regEmail) {
        this.regEmail = regEmail;
    }

    public String getUptDt() {
        return uptDt;
    }

    public void setUptDt(String uptDt) {
        this.uptDt = uptDt;
    }

    public String getUptEmail() {
        return uptEmail;
    }

    public void setUptEmail(String uptEmail) {
        this.uptEmail = uptEmail;
    }

    public String getBalancePan() {
        return balancePan;
    }

    public void setBalancePan(String balancePan) {
        this.balancePan = balancePan;
    }

    public String getBalanceEth() {
        return balanceEth;
    }

    public void setBalanceEth(String balanceEth) {
        this.balanceEth = balanceEth;
    }

    public List<WalletVO> getList() {
        return list;
    }

    public void setList(List<WalletVO> list) {
        this.list = list;
    }
}
