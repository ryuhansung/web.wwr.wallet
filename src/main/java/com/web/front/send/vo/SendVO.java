package com.web.front.send.vo;

public class SendVO {

    private String userEmail = "";
    private String toAddr = "";
    private String fromAddr = "";
    private String amount = "";
    private String addr = "";
    private String adrs = "";
    private String pageAmt = "";
    private String pageNum = "";
    private String aceKey = "";
    private String sendPwd = "";
    private String coinNm = "";
    private String safeSendPwd = "";
    private String safeNewSendPwd = ""; // 2차 신규인증번호
    private String safeOldSendPwd = ""; // 2차 기존인증번호
    private String setPwdType = "";
    private String oldAceKey = "";
    private String safeAceKey = "";
    private String lmtYn = "";
    private String minSendLmt = "";
    private String maxSendLmt = "";
    private String lockMny = "";                // lockMny
    String cnKndCd = "";
    String coinType="";

    public String getCoinType() {
        return coinType;
    }

    public void setCoinType(String coinType) {
        this.coinType = coinType;
    }

    public String getCnKndCd() {
        return cnKndCd;
    }

    public void setCnKndCd(String cnKndCd) {
        this.cnKndCd = cnKndCd;
    }

    public String getLmtYn() {
        return lmtYn;
    }

    public void setLmtYn(String lmtYn) {
        this.lmtYn = lmtYn;
    }

    public String getMinSendLmt() {
        return minSendLmt;
    }

    public void setMinSendLmt(String minSendLmt) {
        this.minSendLmt = minSendLmt;
    }

    public String getMaxSendLmt() {
        return maxSendLmt;
    }

    public void setMaxSendLmt(String maxSendLmt) {
        this.maxSendLmt = maxSendLmt;
    }

    public String getSafeAceKey() {
        return safeAceKey;
    }

    public void setSafeAceKey(String safeAceKey) {
        this.safeAceKey = safeAceKey;
    }

    public String getOldAceKey() {
        return oldAceKey;
    }

    public void setOldAceKey(String oldAceKey) {
        this.oldAceKey = oldAceKey;
    }

    public String getSafeSendPwd() {
        return safeSendPwd;
    }

    public void setSafeSendPwd(String safeSendPwd) {
        this.safeSendPwd = safeSendPwd;
    }

    public String getSetPwdType() {
        return setPwdType;
    }

    public void setSetPwdType(String setPwdType) {
        this.setPwdType = setPwdType;
    }

    public String getSafeNewSendPwd() {
        return safeNewSendPwd;
    }

    public void setSafeNewSendPwd(String safeNewSendPwd) {
        this.safeNewSendPwd = safeNewSendPwd;
    }

    public String getSafeOldSendPwd() {
        return safeOldSendPwd;
    }

    public void setSafeOldSendPwd(String safeOldSendPwd) {
        this.safeOldSendPwd = safeOldSendPwd;
    }

    public String getAceKey() {
        return aceKey;
    }

    public void setAceKey(String aceKey) {
        this.aceKey = aceKey;
    }

    public String getSendPwd() {
        return sendPwd;
    }

    public void setSendPwd(String sendPwd) {
        this.sendPwd = sendPwd;
    }

    public String getCoinNm() {
        return coinNm;
    }

    public void setCoinNm(String coinNm) {
        this.coinNm = coinNm;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getToAddr() {
        return toAddr;
    }

    public void setToAddr(String toAddr) {
        this.toAddr = toAddr;
    }

    public String getFromAddr() {
        return fromAddr;
    }

    public void setFromAddr(String fromAddr) {
        this.fromAddr = fromAddr;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getAdrs() {
        return adrs;
    }

    public void setAdrs(String adrs) {
        this.adrs = adrs;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getPageAmt() {
        return pageAmt;
    }

    public void setPageAmt(String pageAmt) {
        this.pageAmt = pageAmt;
    }

    public String getPageNum() {
        return pageNum;
    }

    public void setPageNum(String pageNum) {
        this.pageNum = pageNum;
    }

    public String getLockMny() {
        return lockMny;
    }

    public void setLockMny(String lockMny) {
        this.lockMny = lockMny;
    }
}
