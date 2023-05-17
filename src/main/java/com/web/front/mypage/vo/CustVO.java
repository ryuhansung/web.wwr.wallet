package com.web.front.mypage.vo;
import com.web.config.vo.DmExtendListVO;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @프로젝트명	: com.web
 * @패키지    	: com.web.front.login.vo
 * @클래스명  	: com.web
 * @작성자		:  (주)씨엠이소프트 임승연
 * @작성일		: 2017. 11. 3.
 */
public class CustVO {
    private String userEmail = "";      // 사용자 이메일
    private String encUserEmail = "";      // 사용자 이메일
    private String userNm = "";         // 사용자명
    private String userPwd = "";        // 비밀번호
    private String hphone = "";         // 핸드폰번호
    private String gender = "";         // 성별 M:남자 W:여자
    private String age = "";            // 나이대(10,20,30)
    private String chgPwd = "";         // 비밀번호 변경여부 Y:변경안함 N:변경
    private String testYn ="";
    private String fudYn = "";
    private String regDt= "";
    private String regEmail = "";
    private String uptDt = "";
    private String uptEmail = "";
    private String lang = "";
    private String aceKey = "";
    private String useYn = "";
    private String regIp = "";
    private String langCd ="";
    private String coinNm = "";
    private String sessionId = "";  // 세션아이디
    private String sessionIdMobile = ""; // 모바일 세션아이디
    private Date sessionTime; // 세션지속시간
    private Date sessionTimeMobile; // 모바일 세션 지속시간
    private String autoLogin = ""; //자동로그인 사용 여부
    private String token = "";
    private String subUserEmail =""; //보조이메일

    public String getEncUserEmail() {
        return encUserEmail;
    }

    public void setEncUserEmail(String encUserEmail) {
        this.encUserEmail = encUserEmail;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getCoinNm() {
        return coinNm;
    }

    public void setCoinNm(String coinNm) {
        this.coinNm = coinNm;
    }

    public Date getSessionTimeMobile() {
        return sessionTimeMobile;
    }

    public void setSessionTimeMobile(Date sessionTimeMobile) {
        this.sessionTimeMobile = sessionTimeMobile;
    }
    public String getSessionIdMobile() {
        return sessionIdMobile;
    }

    public void setSessionIdMobile(String sessionIdMobile) {
        this.sessionIdMobile = sessionIdMobile;
    }

    public Date getSessionTime() {
        return sessionTime;
    }

    public void setSessionTime(Date sessionTime) {
        this.sessionTime = sessionTime;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getAutoLogin() {
        return autoLogin;
    }

    public void setAutoLogin(String autoLogin) {
        this.autoLogin = autoLogin;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserNm() {
        return userNm;
    }

    public void setUserNm(String userNm) {
        this.userNm = userNm;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getHphone() {
        return hphone;
    }

    public void setHphone(String hphone) {
        this.hphone = hphone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getChgPwd() {
        return chgPwd;
    }

    public void setChgPwd(String chgPwd) {
        this.chgPwd = chgPwd;
    }

    public String getTestYn() {
        return testYn;
    }

    public void setTestYn(String testYn) {
        this.testYn = testYn;
    }

    public String getFudYn() {
        return fudYn;
    }

    public void setFudYn(String fudYn) {
        this.fudYn = fudYn;
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

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getAceKey() {
        return aceKey;
    }

    public void setAceKey(String aceKey) {
        this.aceKey = aceKey;
    }


    public String getUseYn() {
        return useYn;
    }

    public void setUseYn(String useYn) {
        this.useYn = useYn;
    }

    public String getRegIp() {
        return regIp;
    }

    public void setRegIp(String regIp) {
        this.regIp = regIp;
    }

    public String getLangCd() {
        return langCd;
    }

    public void setLangCd(String langCd) {
        this.langCd = langCd;
    }

    public String getSubUserEmail() {
        return subUserEmail;
    }

    public void setSubUserEmail(String subUserEmail) {
        this.subUserEmail = subUserEmail;
    }

}
