package com.web.front.mypage.vo;

import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.List;

public class AuthVO implements Serializable {


	private static final long serialVersionUID = 1825852716317603924L;
	
	private String firstYn = "";
	private String userEmail = "";
	private String emailCertYn =""; //발송전 : N  발송후: M 메일에서 확인 S 인증확인 클릭시:Y
	private String emailCertDt ="";
	private String smsCertYn ="";
	private String smsCeryDt ="";
	private String otpSerial ="";
	private String otpCertYn = "";
	private List RESULT ;
	private String lvl ="";
	private String isUptYn ="";
	private String bankAccNo ="";
	private String kycCertYn ="";
	private String kycUptEmail="";
	private String kycUptDt="";
	private String kycRsn = "";
	private String userMobile = "";
	private String code = "";
	private String okuserMobile = "";			/* 20180803 김수현 추가 */
	private String authType ="";
	private String langCd = "";
	private String otpYn = "";
	private String telCertYn ="";
	private String tetCertDt = "";

	private Integer outInt = 0;

    public Integer getOutInt() {
        return outInt;
    }

    public void setOutInt(Integer outInt) {
        this.outInt = outInt;
    }

    public String getTetCertDt() {
		return tetCertDt;
	}

	public void setTetCertDt(String tetCertDt) {
		this.tetCertDt = tetCertDt;
	}

	public String getTelCertYn() {
		return telCertYn;
	}

	public void setTelCertYn(String telCertYn) {
		this.telCertYn = telCertYn;
	}

	public String getOkuserMobile() {
		return okuserMobile;
	}

	public void setOkuserMobile(String okuserMobile) {
		this.okuserMobile = okuserMobile;
	}

	public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getOtpCertYn() {
		return otpCertYn;
	}

	public void setOtpCertYn(String otpCertYn) {
		this.otpCertYn = otpCertYn;
	}

	public String getKycRsn() {
		return kycRsn;
	}

	public void setKycRsn(String kycRsn) {
		this.kycRsn = kycRsn;
	}

	private MultipartFile file1;
	private MultipartFile file2;

	public MultipartFile getFile1() {
		return file1;
	}

	public void setFile1(MultipartFile file1) {
		this.file1 = file1;
	}

	public MultipartFile getFile2() {
		return file2;
	}

	public void setFile2(MultipartFile file2) {
		this.file2 = file2;
	}

	public String getKycCertYn() {
		return kycCertYn;
	}

	public void setKycCertYn(String kycCertYn) {
		this.kycCertYn = kycCertYn;
	}

	public String getKycUptEmail() {
		return kycUptEmail;
	}

	public void setKycUptEmail(String kycUptEmail) {
		this.kycUptEmail = kycUptEmail;
	}

	public String getKycUptDt() {
		return kycUptDt;
	}

	public void setKycUptDt(String kycUptDt) {
		this.kycUptDt = kycUptDt;
	}

	public String getFirstYn() {
		return firstYn;
	}
	public void setFirstYn(String firstYn) {
		this.firstYn = firstYn;
	}
	public String getIsUptYn() {
		return isUptYn;
	}
	public void setIsUptYn(String isUptYn) {
		this.isUptYn = isUptYn;
	}
	public String getEmailCertYn() {
		return emailCertYn;
	}
	public void setEmailCertYn(String emailCertYn) {
		this.emailCertYn = emailCertYn;
	}
	public String getEmailCertDt() {
		return emailCertDt;
	}
	public void setEmailCertDt(String emailCertDt) {
		this.emailCertDt = emailCertDt;
	}
	public String getSmsCertYn() {
		return smsCertYn;
	}
	public void setSmsCertYn(String smsCertYn) {
		this.smsCertYn = smsCertYn;
	}
	public String getSmsCeryDt() {
		return smsCeryDt;
	}
	public void setSmsCeryDt(String smsCeryDt) {
		this.smsCeryDt = smsCeryDt;
	}
	public String getOtpSerial() {
		return otpSerial;
	}
	public void setOtpSerial(String otpSerial) {
		this.otpSerial = otpSerial;
	}
	
	public List getRESULT() {
		return RESULT;
	}
	public void setRESULT(List RESULT) {
		this.RESULT = RESULT;
	}
	public String getLvl() {
		return lvl;
	}
	public void setLvl(String lvl) {
		this.lvl = lvl;
	}
	public String getBankAccNo() {
		return bankAccNo;
	}
	public void setBankAccNo(String bankAccNo) {
		this.bankAccNo = bankAccNo;
	}
	public String getAuthType() {
		return authType;
	}
	public void setAuthType(String authType) {
		this.authType = authType;
	}
    public String getLangCd() {
        return langCd;
    }
    public void setLangCd(String langCd) {
        this.langCd = langCd;
    }
	public String getOtpYn() {return otpYn;}
	public void setOtpYn(String otpYn) {this.otpYn = otpYn;}

}