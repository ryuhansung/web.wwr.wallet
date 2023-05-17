/**
 * 씨엠이 소프트 자체 프로젝트 입니다. 허가 없이 복제 및 배포 할 수 없습니다.
 * 개발사 : (주)씨엠이소프트
 * CopyRight 씨엠이소프트 - 2017
 */
package com.web.front.board.vo;

import com.web.config.vo.DmExtendListVO;

import java.io.Serializable;

/**
 * @프로젝트명	: com.bitkrx.web
 * @패키지    	: com.bitkrx.web.front.board.vo
 * @클래스명  	: com.bitkrx.web
 * @작성자		:  (주)씨엠이소프트 임승연
 * @작성일		: 2017. 10. 31.
 */
public class BoardVO extends DmExtendListVO{

/*tb_board_mst 게시판 기본 정보 */
	
	private String board_id = "";					/*게시판 아이디*/
	private String board_name = "";					/*게시판 이름*/
	private String board_desc ="";					/*게시판 설명*/
	private String use_yn = "";						/*사용 여부*/
	private String reply_yn = "";					/*게시판 답변 사용 여부*/
	private String comment_yn="";					/*게시판 댓글 사용 여부 */
	private String reg_yn="";						/*등록버튼 여부*/
	private String writer_yn="";					/*작성자 노출 여부*/
	private String file_yn ="";						/*파일 업로드 사용 여부*/
	private String file_id ="";						/*게시판파일아이디*/
	private String notice_yn = "";					/*공지 게시판 여부*/
	private String notice_vw_yn ="";				/*공지 노출 여부*/	
	private String notice_cnt = "";					/*상위 공지 리스트 개수*/
	private String editor_yn = "";					/*에디터 사용 여부*/
	private String regdate_yn = "";					/*작성일자 출력 여부*/
	private String cat_view_yn = "";				/*카테고리 사용 여부*/
	private String newicon_yn="";					/*새글 아이콘 여부*/
	private String secret_use_yn ="";				/*비밀글 여부*/
	private String reg_email ="";					/*게시판 생성 email*/
	private String reg_dt ="";						/*게시판 생성일 */
	private String upt_email ="";					/*게시판 수정 eamil*/
	private String upt_dt="";						/*게시판 수정일*/
	private String reg_ip ="";						/*게시판 생성 IP*/
	private String upt_ip ="";						/*게시판 수정 IP */
	
	/*tb_board 게시판 기본 정보 */
	private String content_id = "";					/*게시글 번호*/
	private String board_title = "";				/*게시글 이름*/
	private String content_msg = "";				/*게시글 내용*/
	private Integer read_cnt = 0;					/*조회수*/
	private String board_pwd = "";					/*게시글 비밀번호*/

	private String secret_yn= "";					/*비밀글 여부*/
	private String comment_sn ="";					/*댓글 순서*/
	private String notice_from_dt ="";				/*공지 게시판 언제까지*/
	private String notice_to_dt ="";				/*공지 게시판 언제부터*/
	private String reg_user= "";					/*작성자 아이디*/
	private String user_phone="";					/*작성자 전화번호*/
	private String user_name="";					/*작성자 이름*/
	
	/*TB_BOARD_CAT_LIST & TB_BOARD_CAT_MST 게시판별 옵션 정보*/
	private String cat_mst_id = "";					/*옵션 부모 코드*/
	private String cat_mst_name = "";				/*옵션 부모 이름 */
	private String cat_id = "";						/*옵션 자식 코드*/
	private String cat_name = "";					/*옵션 이름*/
	private String cat_type ="";					/*옵션 타입 01:셀렉트 02:체크박스 03:라디오*/
	private String chile_cat_name="";				/*자식코드이름 array*/
	
	
	/*TB_BOARD_REPLY 테이블*/
	private String reply_id ="";					/*답변아이디*/
	private String reply_title ="";					/*답변내용*/
	private String reply_pwd ="";					/*답변 암호*/
	private String reply_msg="";

	/*TB_ATCH_FILE_MST , TB_ATCH_FILE_DETAIL 파일 테이블*/
	private String atch_file_id = "";				/*업로드파일아이디*/	
	private String up_file_mst_id ="";			/*파일 업로드 마스터 id*/
	
	private String file_sn = "";					/*파일 번호*/	
	private String file_stre_path ="";				/*파일 경로*/
	private String file_save_name="";				/*파일 저장 이름*/
	private String file_org_name ="";				/*파일 원본 이름*/
	private String mainYn ="";
	
	private String lang ="";
	private int cnt = 0;
	private String table_nm ="";
	private String user_email ="";
	private String reply_dt ="";

	public String getBoard_id() {
		return board_id;
	}

	public void setBoard_id(String board_id) {
		this.board_id = board_id;
	}

	public String getBoard_name() {
		return board_name;
	}

	public void setBoard_name(String board_name) {
		this.board_name = board_name;
	}

	public String getBoard_desc() {
		return board_desc;
	}

	public void setBoard_desc(String board_desc) {
		this.board_desc = board_desc;
	}

	@Override
	public String getUse_yn() {
		return use_yn;
	}

	@Override
	public void setUse_yn(String use_yn) {
		this.use_yn = use_yn;
	}

	public String getReply_yn() {
		return reply_yn;
	}

	public void setReply_yn(String reply_yn) {
		this.reply_yn = reply_yn;
	}

	public String getComment_yn() {
		return comment_yn;
	}

	public void setComment_yn(String comment_yn) {
		this.comment_yn = comment_yn;
	}

	public String getReg_yn() {
		return reg_yn;
	}

	public void setReg_yn(String reg_yn) {
		this.reg_yn = reg_yn;
	}

	public String getWriter_yn() {
		return writer_yn;
	}

	public void setWriter_yn(String writer_yn) {
		this.writer_yn = writer_yn;
	}

	public String getFile_yn() {
		return file_yn;
	}

	public void setFile_yn(String file_yn) {
		this.file_yn = file_yn;
	}

	public String getFile_id() {
		return file_id;
	}

	public void setFile_id(String file_id) {
		this.file_id = file_id;
	}

	public String getNotice_yn() {
		return notice_yn;
	}

	public void setNotice_yn(String notice_yn) {
		this.notice_yn = notice_yn;
	}

	public String getNotice_vw_yn() {
		return notice_vw_yn;
	}

	public void setNotice_vw_yn(String notice_vw_yn) {
		this.notice_vw_yn = notice_vw_yn;
	}

	public String getNotice_cnt() {
		return notice_cnt;
	}

	public void setNotice_cnt(String notice_cnt) {
		this.notice_cnt = notice_cnt;
	}

	public String getEditor_yn() {
		return editor_yn;
	}

	public void setEditor_yn(String editor_yn) {
		this.editor_yn = editor_yn;
	}

	public String getRegdate_yn() {
		return regdate_yn;
	}

	public void setRegdate_yn(String regdate_yn) {
		this.regdate_yn = regdate_yn;
	}

	public String getCat_view_yn() {
		return cat_view_yn;
	}

	public void setCat_view_yn(String cat_view_yn) {
		this.cat_view_yn = cat_view_yn;
	}

	public String getNewicon_yn() {
		return newicon_yn;
	}

	public void setNewicon_yn(String newicon_yn) {
		this.newicon_yn = newicon_yn;
	}

	public String getSecret_use_yn() {
		return secret_use_yn;
	}

	public void setSecret_use_yn(String secret_use_yn) {
		this.secret_use_yn = secret_use_yn;
	}

	public String getReg_email() {
		return reg_email;
	}

	public void setReg_email(String reg_email) {
		this.reg_email = reg_email;
	}

	public String getReg_dt() {
		return reg_dt;
	}

	public void setReg_dt(String reg_dt) {
		this.reg_dt = reg_dt;
	}

	public String getUpt_email() {
		return upt_email;
	}

	public void setUpt_email(String upt_email) {
		this.upt_email = upt_email;
	}

	public String getUpt_dt() {
		return upt_dt;
	}

	public void setUpt_dt(String upt_dt) {
		this.upt_dt = upt_dt;
	}

	public String getReg_ip() {
		return reg_ip;
	}

	public void setReg_ip(String reg_ip) {
		this.reg_ip = reg_ip;
	}

	public String getUpt_ip() {
		return upt_ip;
	}

	public void setUpt_ip(String upt_ip) {
		this.upt_ip = upt_ip;
	}

	public String getContent_id() {
		return content_id;
	}

	public void setContent_id(String content_id) {
		this.content_id = content_id;
	}

	public String getBoard_title() {
		return board_title;
	}

	public void setBoard_title(String board_title) {
		this.board_title = board_title;
	}

	public String getContent_msg() {
		return content_msg;
	}

	public void setContent_msg(String content_msg) {
		this.content_msg = content_msg;
	}

	public Integer getRead_cnt() {
		return read_cnt;
	}

	public void setRead_cnt(Integer read_cnt) {
		this.read_cnt = read_cnt;
	}

	public String getBoard_pwd() {
		return board_pwd;
	}

	public void setBoard_pwd(String board_pwd) {
		this.board_pwd = board_pwd;
	}

	public String getSecret_yn() {
		return secret_yn;
	}

	public void setSecret_yn(String secret_yn) {
		this.secret_yn = secret_yn;
	}

	public String getComment_sn() {
		return comment_sn;
	}

	public void setComment_sn(String comment_sn) {
		this.comment_sn = comment_sn;
	}

	public String getNotice_from_dt() {
		return notice_from_dt;
	}

	public void setNotice_from_dt(String notice_from_dt) {
		this.notice_from_dt = notice_from_dt;
	}

	public String getNotice_to_dt() {
		return notice_to_dt;
	}

	public void setNotice_to_dt(String notice_to_dt) {
		this.notice_to_dt = notice_to_dt;
	}

	@Override
	public String getReg_user() {
		return reg_user;
	}

	@Override
	public void setReg_user(String reg_user) {
		this.reg_user = reg_user;
	}

	public String getUser_phone() {
		return user_phone;
	}

	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getCat_mst_id() {
		return cat_mst_id;
	}

	public void setCat_mst_id(String cat_mst_id) {
		this.cat_mst_id = cat_mst_id;
	}

	public String getCat_mst_name() {
		return cat_mst_name;
	}

	public void setCat_mst_name(String cat_mst_name) {
		this.cat_mst_name = cat_mst_name;
	}

	public String getCat_id() {
		return cat_id;
	}

	public void setCat_id(String cat_id) {
		this.cat_id = cat_id;
	}

	public String getCat_name() {
		return cat_name;
	}

	public void setCat_name(String cat_name) {
		this.cat_name = cat_name;
	}

	public String getCat_type() {
		return cat_type;
	}

	public void setCat_type(String cat_type) {
		this.cat_type = cat_type;
	}

	public String getChile_cat_name() {
		return chile_cat_name;
	}

	public void setChile_cat_name(String chile_cat_name) {
		this.chile_cat_name = chile_cat_name;
	}

	public String getReply_id() {
		return reply_id;
	}

	public void setReply_id(String reply_id) {
		this.reply_id = reply_id;
	}

	public String getReply_title() {
		return reply_title;
	}

	public void setReply_title(String reply_title) {
		this.reply_title = reply_title;
	}

	public String getReply_pwd() {
		return reply_pwd;
	}

	public void setReply_pwd(String reply_pwd) {
		this.reply_pwd = reply_pwd;
	}

	public String getReply_msg() {
		return reply_msg;
	}

	public void setReply_msg(String reply_msg) {
		this.reply_msg = reply_msg;
	}

	public String getAtch_file_id() {
		return atch_file_id;
	}

	public void setAtch_file_id(String atch_file_id) {
		this.atch_file_id = atch_file_id;
	}

	public String getUp_file_mst_id() {
		return up_file_mst_id;
	}

	public void setUp_file_mst_id(String up_file_mst_id) {
		this.up_file_mst_id = up_file_mst_id;
	}

	public String getFile_sn() {
		return file_sn;
	}

	public void setFile_sn(String file_sn) {
		this.file_sn = file_sn;
	}

	public String getFile_stre_path() {
		return file_stre_path;
	}

	public void setFile_stre_path(String file_stre_path) {
		this.file_stre_path = file_stre_path;
	}

	public String getFile_save_name() {
		return file_save_name;
	}

	public void setFile_save_name(String file_save_name) {
		this.file_save_name = file_save_name;
	}

	public String getFile_org_name() {
		return file_org_name;
	}

	public void setFile_org_name(String file_org_name) {
		this.file_org_name = file_org_name;
	}

	public String getMainYn() {
		return mainYn;
	}

	public void setMainYn(String mainYn) {
		this.mainYn = mainYn;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public int getCnt() {
		return cnt;
	}

	public void setCnt(int cnt) {
		this.cnt = cnt;
	}

	public String getTable_nm() {
		return table_nm;
	}

	public void setTable_nm(String table_nm) {
		this.table_nm = table_nm;
	}

	public String getUser_email() {
		return user_email;
	}

	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}

	public String getReply_dt() {
		return reply_dt;
	}

	public void setReply_dt(String reply_dt) {
		this.reply_dt = reply_dt;
	}
}
