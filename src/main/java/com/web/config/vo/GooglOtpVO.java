package com.web.config.vo;

import java.io.Serializable;

import com.web.config.CmeResultVO;

public class GooglOtpVO extends CmeResultVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9213944243788110891L;
	private String urlHost = "";
	private String encodedKey = "";
	private String qrCodeUrl = "";
	private String authCode = "";
	
	public String getUrlHost() {
		return urlHost;
	}
	public void setUrlHost(String urlHost) {
		this.urlHost = urlHost;
	}
	public String getEncodedKey() {
		return encodedKey;
	}
	public void setEncodedKey(String encodedKey) {
		this.encodedKey = encodedKey;
	}
	public String getQrCodeUrl() {
		return qrCodeUrl;
	}
	public void setQrCodeUrl(String qrCodeUrl) {
		this.qrCodeUrl = qrCodeUrl;
	}
	public String getAuthCode() {
		return authCode;
	}
	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}
	
	
}
