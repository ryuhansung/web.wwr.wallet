package com.web.config.vo;

import java.io.Serializable;

public class DmExtendListVO implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = -3951784719336033096L;

	/** 검색시작일 */
    private String searchBgnDe = "";
    
    /** 검색조건 */
    private String searchCnd = "";
    
    /** 검색종료일 */
    private String searchEndDe = "";
    
    /** 검색단어 */
    private String searchWrd = "";
    
    /** 정렬순서(DESC,ASC) */
    private long sortOrdr = 0L;

    /** 검색사용여부 */
    private String searchUseYn = "";

    /** 현재페이지 */
    private int pageIndex = 1;

    /** 페이지갯수 */
    private int pageUnit = 10;

    /** 페이지사이즈 */
    private int pageSize = 10;

    /** 첫페이지 인덱스 */
    private int firstIndex = 1;

    /** 마지막페이지 인덱스 */
    private int lastIndex = 1;

    /** 페이지당 레코드 개수 */
    private int recordCountPerPage = 10;
    
    private int totalRecordCount = 1;

    /** 레코드 번호 */
    private int rowNo = 0;
    int totRow = 0;

	/**
	 * 최초등록자 아이디
	 */
	private String frstRegisterId = "";
	/**
	 * 최초등록시점
	 */
	private String frstRegisterPnttm = "";
	/**
	 * 최초등록자
	 */	
	private String frstRegisterNm="";	
	/**
	 * 최종수정자 아이디
	 */
	private String lastUpdusrId = "";
	/**
	 * 최종수정시점
	 */
	private String lastUpdusrPnttm = "";    

	String eleUserId		=   ""; //유저아이디의 elemnet Id 값
	String eleUserNm		=   "";	//유저이름의 elemnet Id 값
	String eleUserEmail		=	""; //이메일 elementId값
	String elePhone1		=	"";//폰1
	String elePhone2		=	"";//폰2
	String eleTeamName			=	"";
	String funcNm			=	""; //추가펑션을 실행한다.
	
	String regUser			=	"";
	String regUserName 		=	"";
	String regDate			=	"";
	String useYn			=	"Y";

	String reg_date			=	"";
	String reg_user			=	"";
	String update_date		=	"";
	String update_user		=	"";
	String use_yn			=	"";
	String tot_cnt			=	"0";
	
	public String getFrstRegisterNm() {
		return frstRegisterNm;
	}

	public void setFrstRegisterNm(String frstRegisterNm) {
		this.frstRegisterNm = frstRegisterNm;
	}

	public String getFrstRegisterId() {
		return frstRegisterId;
	}

	public void setFrstRegisterId(String frstRegisterId) {
		this.frstRegisterId = frstRegisterId;
	}

	public String getFrstRegisterPnttm() {
		return frstRegisterPnttm;
	}

	public void setFrstRegisterPnttm(String frstRegisterPnttm) {
		this.frstRegisterPnttm = frstRegisterPnttm;
	}

	public String getLastUpdusrId() {
		return lastUpdusrId;
	}

	public void setLastUpdusrId(String lastUpdusrId) {
		this.lastUpdusrId = lastUpdusrId;
	}

	public String getLastUpdusrPnttm() {
		return lastUpdusrPnttm;
	}

	public void setLastUpdusrPnttm(String lastUpdusrPnttm) {
		this.lastUpdusrPnttm = lastUpdusrPnttm;
	}

	public String getSearchBgnDe() {
		return searchBgnDe;
	}

	public void setSearchBgnDe(String searchBgnDe) {
		this.searchBgnDe = searchBgnDe;
	}

	public String getSearchCnd() {
		return searchCnd;
	}

	public void setSearchCnd(String searchCnd) {
		this.searchCnd = searchCnd;
	}

	public String getSearchEndDe() {
		return searchEndDe;
	}

	public void setSearchEndDe(String searchEndDe) {
		this.searchEndDe = searchEndDe;
	}

	public String getSearchWrd() {
		return searchWrd;
	}

	public void setSearchWrd(String searchWrd) {
		this.searchWrd = searchWrd;
	}

	public long getSortOrdr() {
		return sortOrdr;
	}

	public void setSortOrdr(long sortOrdr) {
		this.sortOrdr = sortOrdr;
	}

	public String getSearchUseYn() {
		return searchUseYn;
	}

	public void setSearchUseYn(String searchUseYn) {
		this.searchUseYn = searchUseYn;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPageUnit() {
		return pageUnit;
	}

	public void setPageUnit(int pageUnit) {
		this.pageUnit = pageUnit;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getFirstIndex() {
		return firstIndex;
	}

	public void setFirstIndex(int firstIndex) {
		this.firstIndex = firstIndex;
	}

	public int getLastIndex() {
		return lastIndex;
	}

	public void setLastIndex(int lastIndex) {
		this.lastIndex = lastIndex;
	}

	public int getRecordCountPerPage() {
		return recordCountPerPage;
	}

	public void setRecordCountPerPage(int recordCountPerPage) {
		this.recordCountPerPage = recordCountPerPage;
	}

	public int getRowNo() {
		return rowNo;
	}

	public void setRowNo(int rowNo) {
		this.rowNo = rowNo;
	}

	public String getEleUserId() {
		return eleUserId;
	}

	public void setEleUserId(String eleUserId) {
		this.eleUserId = eleUserId;
	}

	public String getEleUserNm() {
		return eleUserNm;
	}

	public void setEleUserNm(String eleUserNm) {
		this.eleUserNm = eleUserNm;
	}

	public String getFuncNm() {
		return funcNm;
	}

	public void setFuncNm(String funcNm) {
		this.funcNm = funcNm;
	}

	/**
	 * @return the regUser
	 */
	public String getRegUser() {
		return regUser;
	}

	/**
	 * @param regUser the regUser to set
	 */
	public void setRegUser(String regUser) {
		this.regUser = regUser;
	}

	/**
	 * @return the regUserName
	 */
	public String getRegUserName() {
		return regUserName;
	}

	/**
	 * @param regUserName the regUserName to set
	 */
	public void setRegUserName(String regUserName) {
		this.regUserName = regUserName;
	}

	/**
	 * @return the regDate
	 */
	public String getRegDate() {
		return regDate;
	}

	/**
	 * @param regDate the regDate to set
	 */
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	/**
	 * @return the useYn
	 */
	public String getUseYn() {
		return useYn;
	}

	/**
	 * @param useYn the useYn to set
	 */
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}


	public String getEleUserEmail() {
		return eleUserEmail;
	}

	public void setEleUserEmail(String eleUserEmail) {
		this.eleUserEmail = eleUserEmail;
	}

	public String getElePhone1() {
		return elePhone1;
	}

	public void setElePhone1(String elePhone1) {
		this.elePhone1 = elePhone1;
	}

	public String getElePhone2() {
		return elePhone2;
	}

	public void setElePhone2(String elePhone2) {
		this.elePhone2 = elePhone2;
	}

	public String getEleTeamName() {
		return eleTeamName;
	}

	public void setEleTeamName(String eleTeamName) {
		this.eleTeamName = eleTeamName;
	}

	/**
	 * @return the totalRecordCount
	 */
	public int getTotalRecordCount() {
		return totalRecordCount;
	}

	/**
	 * @param totalRecordCount the totalRecordCount to set
	 */
	public void setTotalRecordCount(int totalRecordCount) {
		this.totalRecordCount = totalRecordCount;
	}

	public int getTotRow() {
		return totRow;
	}

	public void setTotRow(int totRow) {
		this.totRow = totRow;
	}

	/**
	 * @return the reg_date
	 */
	public String getReg_date() {
		return reg_date;
	}

	/**
	 * @param reg_date the reg_date to set
	 */
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}

	/**
	 * @return the reg_user
	 */
	public String getReg_user() {
		return reg_user;
	}

	/**
	 * @param reg_user the reg_user to set
	 */
	public void setReg_user(String reg_user) {
		this.reg_user = reg_user;
	}

	/**
	 * @return the update_date
	 */
	public String getUpdate_date() {
		return update_date;
	}

	/**
	 * @param update_date the update_date to set
	 */
	public void setUpdate_date(String update_date) {
		this.update_date = update_date;
	}

	/**
	 * @return the update_user
	 */
	public String getUpdate_user() {
		return update_user;
	}

	/**
	 * @param update_user the update_user to set
	 */
	public void setUpdate_user(String update_user) {
		this.update_user = update_user;
	}

	/**
	 * @return the use_yn
	 */
	public String getUse_yn() {
		return use_yn;
	}

	/**
	 * @param use_yn the use_yn to set
	 */
	public void setUse_yn(String use_yn) {
		this.use_yn = use_yn;
	}

	/**
	 * @return the tot_cnt
	 */
	public String getTot_cnt() {
		return tot_cnt;
	}

	/**
	 * @param tot_cnt the tot_cnt to set
	 */
	public void setTot_cnt(String tot_cnt) {
		this.tot_cnt = tot_cnt;
	}


	
}
