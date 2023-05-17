package com.web.config.vo;

import java.io.Serializable;

public class FileAuditVO implements Serializable{


	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9027757278634283765L;
	String uploadfileName="";
	String uploadfileSize="";
	String auditfileSize="";
	String uploadfileExt="";
	String auditfileExt="";
	
	public String getUploadfileName() {
		return uploadfileName;
	}
	public void setUploadfileName(String uploadfileName) {
		this.uploadfileName = uploadfileName;
	}
	public String getUploadfileSize() {
		return uploadfileSize;
	}
	public void setUploadfileSize(String uploadfileSize) {
		this.uploadfileSize = uploadfileSize;
	}
	public String getAuditfileSize() {
		return auditfileSize;
	}
	public void setAuditfileSize(String auditfileSize) {
		this.auditfileSize = auditfileSize;
	}
	public String getUploadfileExt() {
		return uploadfileExt;
	}
	public void setUploadfileExt(String uploadfileExt) {
		this.uploadfileExt = uploadfileExt;
	}
	public String getAuditfileExt() {
		return auditfileExt;
	}
	public void setAuditfileExt(String auditfileExt) {
		this.auditfileExt = auditfileExt;
	}
	
	
}
