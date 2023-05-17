package com.web.front.main.vo;

import com.web.config.vo.DmExtendListVO;

import java.io.Serializable;

public class PopupVO extends DmExtendListVO implements Serializable {

    private static final long serialVersionUID = -5632906708925320737L; //시리얼버전은 무엇
    private String pop_code = ""; //  primary key?
    private String pop_nm = ""; //팝업 이름
    private String str_dt = ""; // 시작일
    private String end_dt = ""; // 종료일
    private String pop_url = ""; // 팝업주소
    private String pop_desc = ""; // ?
    private String atch_file_id = ""; // 파일 테이블에 접근할 id
    private String file_sn = ""; //
    private String use_yn = ""; // 팝업 사용 여부
    private String reg_email = ""; // 등록자 이메일
    private String reg_ip = ""; //등록자 아이피
    private String reg_dt = ""; // 등록일
    private String upt_email  = ""; // 수정한 사람 아이디
    private String upt_ip = ""; // 수정 아이피
    private String upt_dt = ""; // 수정일
    private String day_open_yn = ""; // ?? 사용여부
    private String site_type = ""; //

    private String sdate = "";//시작날짜
    private String stime = ""; //종료날짜
    private String rdate = ""; //?
    private String rtime = ""; //?
    private String lang_cd = ""; //언어


    public String getSite_type() { return site_type; }

    public String getPop_code() {
        return pop_code;
    }

    public String getPop_nm() {
        return pop_nm;
    }

    public String getStr_dt() {
        return str_dt;
    }

    public String getEnd_dt() {
        return end_dt;
    }

    public String getPop_url() {
        return pop_url;
    }

    public String getPop_desc() {
        return pop_desc;
    }

    public String getAtch_file_id() {
        return atch_file_id;
    }

    public String getFile_sn() {
        return file_sn;
    }

    @Override
    public String getUse_yn() {
        return use_yn;
    }

    public String getReg_email() {
        return reg_email;
    }

    public String getReg_ip() {
        return reg_ip;
    }

    public String getReg_dt() {
        return reg_dt;
    }

    public String getUpt_email() {
        return upt_email;
    }

    public String getUpt_ip() {
        return upt_ip;
    }

    public String getUpt_dt() {
        return upt_dt;
    }

    public String getSdate() {
        return sdate;
    }

    public String getStime() {
        return stime;
    }

    public String getRdate() {
        return rdate;
    }

    public String getRtime() {
        return rtime;
    }

    public String getLang_cd() {
        return lang_cd;
    }

    public String getDay_open_yn() {
        return day_open_yn;
    }

    public void setSite_type(String site_type) { this.site_type = site_type; }

    public void setPop_code(String pop_code) {
        this.pop_code = pop_code;
    }

    public void setPop_nm(String pop_nm) {
        this.pop_nm = pop_nm;
    }

    public void setStr_dt(String str_dt) {
        this.str_dt = str_dt;
    }

    public void setEnd_dt(String end_dt) {
        this.end_dt = end_dt;
    }

    public void setPop_url(String pop_url) {
        this.pop_url = pop_url;
    }

    public void setPop_desc(String pop_desc) {
        this.pop_desc = pop_desc;
    }

    public void setAtch_file_id(String atch_file_id) {
        this.atch_file_id = atch_file_id;
    }

    public void setFile_sn(String file_sn) {
        this.file_sn = file_sn;
    }

    @Override
    public void setUse_yn(String use_yn) {
        this.use_yn = use_yn;
    }

    public void setReg_email(String reg_email) {
        this.reg_email = reg_email;
    }

    public void setReg_ip(String reg_ip) {
        this.reg_ip = reg_ip;
    }

    public void setReg_dt(String reg_dt) {
        this.reg_dt = reg_dt;
    }

    public void setUpt_email(String upt_email) {
        this.upt_email = upt_email;
    }

    public void setUpt_ip(String upt_ip) {
        this.upt_ip = upt_ip;
    }

    public void setUpt_dt(String upt_dt) {
        this.upt_dt = upt_dt;
    }

    public void setSdate(String sdate) {
        this.sdate = sdate;
    }

    public void setStime(String stime) {
        this.stime = stime;
    }

    public void setRdate(String rdate) {
        this.rdate = rdate;
    }

    public void setRtime(String rtime) {
        this.rtime = rtime;
    }

    public void setLang_cd(String lang_cd) {
        this.lang_cd = lang_cd;
    }

    public void setDay_open_yn(String day_open_yn) {
        this.day_open_yn = day_open_yn;
    }
}
