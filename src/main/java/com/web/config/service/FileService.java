package com.web.config.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.web.config.CmeResultVO;
import com.web.config.vo.FileVO;

public interface FileService {
	/**
	 * 
	 * @Method Name  : getUploadFileInfo
	 * @작성일   : 2017. 12. 4. 
	 * @작성자   :  (주)씨엠이소프트 박상웅
	 * @변경이력  :
	 * @Method 설명 :
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	FileVO getUploadFileInfo(FileVO vo)throws Exception;
	
	/**
	 * 
	 * @Method Name  : DmFileUploadAction
	 * @작성일   : 2017. 12. 4. 
	 * @작성자   :  (주)씨엠이소프트 박상웅
	 * @변경이력  :
	 * @Method 설명 :
	 * @param vo
	 * @param multiRequest
	 * @param request
	 * @return
	 * @throws Exception
	 */
	CmeResultVO FileUploadAction(FileVO vo, final MultipartHttpServletRequest multiRequest, HttpServletRequest request)throws Exception;
	
	/**
	 * 
	 * @Method Name  : insertFileInfs
	 * @작성일   : 2017. 12. 4. 
	 * @작성자   :  (주)씨엠이소프트 박상웅
	 * @변경이력  :
	 * @Method 설명 :
	 * @param fvoList
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes" )
	public String insertFileInfs(List fvoList) throws Exception;
	
	/**
	 * 
	 * @Method Name  : getMaxFileSN
	 * @작성일   : 2017. 12. 4. 
	 * @작성자   :  (주)씨엠이소프트 박상웅
	 * @변경이력  :
	 * @Method 설명 :
	 * @param fvo
	 * @return
	 * @throws Exception
	 */
	public int getMaxFileSN(FileVO fvo) throws Exception;
	
	/**
	 * 
	 * @Method Name  : updateInsertFileInfs
	 * @작성일   : 2017. 12. 4. 
	 * @작성자   :  (주)씨엠이소프트 박상웅
	 * @변경이력  :
	 * @Method 설명 :
	 * @param fvoList
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes" )
    public int updateInsertFileInfs(List fvoList) throws Exception;
	
	/**
	 * 
	 * @Method Name  : updateFileInfs
	 * @작성일   : 2017. 12. 4. 
	 * @작성자   :  (주)씨엠이소프트 박상웅
	 * @변경이력  :
	 * @Method 설명 :
	 * @param fvoList
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes" )
    public int updateFileInfs(List fvoList) throws Exception;

	/**
	 * @Method Name  : selectFileInf
	 * @작성일   : 2017. 12. 4. 
	 * @작성자   :  (주)씨엠이소프트 박상웅
	 * @변경이력  :
	 * @Method 설명 :
	 * @param fvo
	 * @return
	 */
	FileVO selectFileInf(FileVO fvo) throws Exception;

	/**
	 * @Method Name  : getNextStringId
	 * @작성일   : 2017. 12. 28. 
	 * @작성자   :  (주)씨엠이소프트 박상웅
	 * @변경이력  :
	 * @Method 설명 :
	 * @return
	 */
	String getNextStringId(String string) throws Exception;

	/**
	 * @Method Name  : selectFileSn
	 * @작성일   : 2017. 12. 31. 
	 * @작성자   :  (주)씨엠이소프트 박상웅
	 * @변경이력  :
	 * @Method 설명 :
	 * @param atch_file_id
	 * @return
	 */
	String selectFileSn(String atch_file_id) throws Exception;
}
