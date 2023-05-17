package com.web.config.vo;

import java.io.File;
import java.io.Serializable;

import com.web.config.service.CmeProperties;

public class FileVO implements Serializable{

    
	/**
	 * 
	 */
	private static final long serialVersionUID = -8999862820619746386L;
	/**
     * 첨부파일 아이디
     */
	private String atchFileId = "";
    /**
     * 생성일자
     */
	private String creatDt = "";
    /**
     * 파일내용
     */
	private String fileCn = "";
    /**
     * 파일확장자
     */
	private String fileExtsn = "";
    /**
     * 파일크기
     */
	private String fileMg = "";
    /**
     * 파일연번
     */
	private String fileSn = "";
    /**
     * 파일저장경로
     */
	private String fileStreCours = "";
    /**
     * 원파일명
     */
	private String orignlFileNm = "";
    /**
     * 저장파일명
     */
	private String streFileNm = "";
	
	private String chkFileName = "";

	private int tmpId;

	private String isOverMaxlimit = "N";
	private String isExtFile = "N";
    
    String file_id="", file_type="", file_desc="", create_date="", create_id="", file_size="", file_upload_count="";
    String file_path="", file_url="", file_prefix="", temp_id="", fileId="", imgId="";
    String returnUrl="";
    String file_web_desc = "";
    String file_extend_name_yn = "N";
    
    private String codeName = "";

    File fileObj;

	public void setDefaultFileWebDesc(){
		if("".equals(this.file_web_desc)){
			if("I".equals(this.file_type)){
				this.file_web_desc = CmeProperties.getProperty("File.Img.Desc");
			}else{
				this.file_web_desc = CmeProperties.getProperty("File.Etc.Desc");
			}
		}
    }
    
    
	public String getFile_web_desc() {
		return file_web_desc;
	}

	public void setFile_web_desc(String file_web_desc) {
		this.file_web_desc = file_web_desc;
	}

	public String getIsOverMaxlimit() {
		return isOverMaxlimit;
	}

	public void setIsOverMaxlimit(String isOverMaxlimit) {
		this.isOverMaxlimit = isOverMaxlimit;
	}

	public String getIsExtFile() {
		return isExtFile;
	}

	public void setIsExtFile(String isExtFile) {
		this.isExtFile = isExtFile;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getImgId() {
		return imgId;
	}

	public void setImgId(String imgId) {
		this.imgId = imgId;
	}

	public File getFileObj() {
		return fileObj;
	}

	public void setFileObj(File fileObj) {
		this.fileObj = fileObj;
	}

	public String getReturnUrl() {
		return returnUrl;
	}

	public void setReturnUrl(String returnUrl) {
		this.returnUrl = returnUrl;
	}

	public String getTemp_id() {
		return temp_id;
	}

	public void setTemp_id(String temp_id) {
		this.temp_id = temp_id;
	}

	public String getFile_path() {
		return file_path;
	}

	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}

	public String getFile_url() {
		return file_url;
	}

	public void setFile_url(String file_url) {
		this.file_url = file_url;
	}

	public String getFile_prefix() {
		return file_prefix;
	}

	public void setFile_prefix(String file_prefix) {
		this.file_prefix = file_prefix;
	}

	public String getFile_id() {
		return file_id;
	}

	public void setFile_id(String file_id) {
		this.file_id = file_id;
	}

	public String getFile_type() {
		return file_type;
	}

	public void setFile_type(String file_type) {
		this.file_type = file_type;
	}

	public String getFile_desc() {
		return file_desc;
	}

	public void setFile_desc(String file_desc) {
		this.file_desc = file_desc;
	}

	public String getCreate_date() {
		return create_date;
	}

	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}

	public String getCreate_id() {
		return create_id;
	}

	public void setCreate_id(String create_id) {
		this.create_id = create_id;
	}

	public String getFile_size() {
		return file_size;
	}

	public void setFile_size(String file_size) {
		this.file_size = file_size;
	}

	public String getFile_upload_count() {
		return file_upload_count;
	}

	public void setFile_upload_count(String file_upload_count) {
		this.file_upload_count = file_upload_count;
	}

	public String getAtchFileId() {
		return atchFileId;
	}

	public void setAtchFileId(String atchFileId) {
		this.atchFileId = atchFileId;
	}

	public String getCreatDt() {
		return creatDt;
	}

	public void setCreatDt(String creatDt) {
		this.creatDt = creatDt;
	}

	public String getFileCn() {
		return fileCn;
	}

	public void setFileCn(String fileCn) {
		this.fileCn = fileCn;
	}

	public String getFileExtsn() {
		return fileExtsn;
	}

	public void setFileExtsn(String fileExtsn) {
		this.fileExtsn = fileExtsn;
	}

	public String getFileMg() {
		return fileMg;
	}

	public void setFileMg(String fileMg) {
		this.fileMg = fileMg;
	}

	public String getFileSn() {
		return fileSn;
	}

	public void setFileSn(String fileSn) {
		this.fileSn = fileSn;
	}

	public String getFileStreCours() {
		return fileStreCours;
	}

	public void setFileStreCours(String fileStreCours) {
		this.fileStreCours = fileStreCours;
	}

	public String getOrignlFileNm() {
		return orignlFileNm;
	}

	public void setOrignlFileNm(String orignlFileNm) {
		this.orignlFileNm = orignlFileNm;
	}

	public String getStreFileNm() {
		return streFileNm;
	}

	public void setStreFileNm(String streFileNm) {
		this.streFileNm = streFileNm;
	}

	public int getTmpId() {
		return tmpId;
	}

	public void setTmpId(int tmpId) {
		this.tmpId = tmpId;
	}


	/**
	 * @return the file_extend_name_yn
	 */
	public String getFile_extend_name_yn() {
		return file_extend_name_yn;
	}


	/**
	 * @param file_extend_name_yn the file_extend_name_yn to set
	 */
	public void setFile_extend_name_yn(String file_extend_name_yn) {
		this.file_extend_name_yn = file_extend_name_yn;
	}


	/**
	 * @return the chkFileName
	 */
	public String getChkFileName() {
		return chkFileName;
	}


	/**
	 * @param chkFileName the chkFileName to set
	 */
	public void setChkFileName(String chkFileName) {
		this.chkFileName = chkFileName;
	}


	public String getCodeName() {
		return codeName;
	}


	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}


}
