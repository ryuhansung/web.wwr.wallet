/**
 * (주)크림스 의 시스템 웹어플리케이션 프레임웍 입니다. 허가 없이 복제 및 배포 할 수 없습니다.
 * 개발사 : (주)크림스
 * CopyRight Creams. inc. Since 2015 
 * 총괄 개발 책임자 : 주식회사 크림스 통합개발연구소 소장 김윤관
 */
package com.web.config.service.impl;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.web.config.CmeResultVO;
import com.web.config.annotation.CommonDataSource;
import com.web.config.dao.FileDAO;
import com.web.config.dbinfo.DataSourceType;
import com.web.config.logging.CmeCommonLogger;
import com.web.config.service.FileService;
import com.web.config.util.ComUtil;
import com.web.config.util.FileUtil;
import com.web.config.util.StringUtils;
import com.web.config.vo.FileVO;


/**
 * 
 * @프로젝트명	: 
 * @패키지    	: 
 * @클래스명  	: 
 * @작성자		:  (주)씨엠이소프트 박상웅
 * @작성일		: 2017. 12. 28.
 */
@Service
public class FileServiceImpl implements FileService{

	@Autowired
	private FileUtil fileUtil; 
	
	@Autowired
	private FileDAO fileDAO;
	
	CmeCommonLogger log = new CmeCommonLogger(this.getClass());
	
	/**
	 * @오바라이딩클래스 : DmFileUploadAction
	 * @작성자		:  (주)씨엠이소프트 박상웅
	 * @작성일		: 2017. 12. 4.
	 * @Method 설명 :
	 * @param vo
	 * @param multiRequest
	 * @param request
	 * @return
	 * @throws Exception
	 * @see cme.com.web.common.service.DmFileService#DmFileUploadAction(cme.com.web.common.vo.DmFileVO, org.springframework.web.multipart.MultipartHttpServletRequest, javax.servlet.http.HttpServletRequest)
	 */
	@Override
	@CommonDataSource(DataSourceType.BOARD)
	public CmeResultVO FileUploadAction(FileVO vo, MultipartHttpServletRequest multiRequest,
			HttpServletRequest request) throws Exception {
		String resMsg = "업로드된 파일이 없습니다. 용량제한의 문제일 수 있습니다.";
		int fileWrite = 0;
		CmeResultVO resultVO = new CmeResultVO();
		List<FileVO> result = null;
		String atchFileId = StringUtils.checkNull(vo.getAtchFileId());
		String _FileSn = StringUtils.checkNull(vo.getFileSn());
		String[] arFileSn = new String[] {};

		if (!"".equals(_FileSn)) {
			arFileSn = _FileSn.split("\\,");
		}
		
		FileVO uploadFileMstr = getUploadFileInfo(vo);
		
		uploadFileMstr.setChkFileName(StringUtils.checkNull(vo.getChkFileName(),""));
		
		
		final Map<String, MultipartFile> files = multiRequest.getFileMap();
		// result = fileUtil.parseFileInf(files, 0, atchFileId, uploadFileMstr);
		try {
			if ("".equals(atchFileId)) {
				result = fileUtil.parseFileInf(files, 0, atchFileId, uploadFileMstr, request);
//				resMsg = getChkFailMessage(request);
				if (result.size() > 0) {
					atchFileId = insertFileInfs(result);
					vo.setAtchFileId(atchFileId);
					if (!"".equals(ComUtil.isNull(atchFileId))) {
						//resMsg = getChkFailMessage(request);
						resultVO.setResultCode(100);
						
						String fileIdSn = "";
						for( FileVO rFvo : result ) {
							String str = rFvo.getCodeName()+":"+rFvo.getFileSn();
							if ("".equals(fileIdSn)) {
								fileIdSn = str;
							} else {
								fileIdSn = fileIdSn+","+str;
							}
						}
						resultVO.setResultStrCode1(fileIdSn);
					}
				} else {
					log.ViewWarnLog("파일첨부가 실패되었습니다.");
				}

			} else { // 업데이트
				FileVO fvo = new FileVO();
				if ("".equals(_FileSn)) { // 추가 파일 업로드
					fvo.setAtchFileId(atchFileId);
					int cnt = getMaxFileSN(fvo);
					result = fileUtil.parseFileInf(files, cnt, atchFileId,
							uploadFileMstr, request);
//					resMsg = getChkFailMessage(request);					
					fileWrite = updateInsertFileInfs(result);
					
					if (fileWrite > 0) {
						resultVO.setResultCode(200);
						
						String fileIdSn = "";
						for( FileVO rFvo : result ) {
							String str = rFvo.getCodeName()+":"+rFvo.getFileSn();
							if ("".equals(fileIdSn)) {
								fileIdSn = str;
							} else {
								fileIdSn = fileIdSn+","+str;
							}
						}
						resultVO.setResultStrCode1(fileIdSn);
					}
				} else {
					result = fileUtil.parseUpdateFileInf(files, arFileSn,
							atchFileId, uploadFileMstr, request);
//					resMsg = getChkFailMessage(request);					
					fileWrite = updateFileInfs(result);
					
					if (fileWrite > 0) {
						
						String fileIdSn = "";
						for( FileVO rFvo : result ) {
							String str = rFvo.getCodeName()+":"+rFvo.getFileSn();
							if ("".equals(fileIdSn)) {
								fileIdSn = str;
							} else {
								fileIdSn = fileIdSn+","+str;
							}
						}
						resultVO.setResultStrCode1(fileIdSn);
						
						resultVO.setResultCode(200);
					}
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			resMsg = "파일업로드 실패";
			resultVO.setResultCode(-1);
		}

		resultVO.setResultMsg(resMsg);
		resultVO.setResultStrCode(atchFileId);

		return resultVO;
	}

	/**
	 * 
	 * @오바라이딩클래스 : getUploadFileInfo
	 * @작성자		:  (주)씨엠이소프트 박상웅
	 * @작성일		: 2017. 12. 4.
	 * @Method 설명 :
	 * @param vo
	 * @return
	 * @throws Exception
	 * @see cme.com.web.common.service.DmFileService#getUploadFileInfo(cme.com.web.common.vo.DmFileVO)
	 */
	@Override
	@CommonDataSource(DataSourceType.BOARD)
	public FileVO getUploadFileInfo(FileVO vo) throws Exception {
		// TODO Auto-generated method stub
		return fileDAO.getUploadFileInfo(vo);
	}

	/**
	 * 
	 * @오바라이딩클래스 : insertFileInfs
	 * @작성자		:  (주)씨엠이소프트 박상웅
	 * @작성일		: 2017. 12. 4.
	 * @Method 설명 :
	 * @param fvoList
	 * @return
	 * @throws Exception
	 * @see cme.com.web.common.service.DmFileService#insertFileInfs(java.util.List)
	 */
	@SuppressWarnings("rawtypes" )
	@CommonDataSource(DataSourceType.BOARD)
    public String insertFileInfs(List fvoList) throws Exception {
		String atchFileId = "";
		if (fvoList.size() != 0) {
		    atchFileId = fileDAO.insertFileInfs(fvoList);
		}
		if(atchFileId == ""){
			atchFileId = null;
		}
		return atchFileId;
    }
	
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
	@CommonDataSource(DataSourceType.BOARD)
    public int getMaxFileSN(FileVO fvo) throws Exception {
    	return fileDAO.getMaxFileSN(fvo);
    }
    
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
    @Override
    @CommonDataSource(DataSourceType.BOARD)
	public int updateInsertFileInfs(List fvoList) throws Exception {
		// TODO Auto-generated method stub
		return fileDAO.updateInsertFileInfs(fvoList);
	}
    
    @SuppressWarnings("rawtypes" )
    @CommonDataSource(DataSourceType.BOARD)
    public int updateFileInfs(List fvoList) throws Exception {
	//Delete & Insert
    	return fileDAO.updateFileInfs(fvoList);
    }

	/**
	 * @오바라이딩클래스 : selectFileInf
	 * @작성자		:  (주)씨엠이소프트 박상웅
	 * @작성일		: 2017. 12. 4.
	 * @Method 설명 :
	 * @param fvo
	 * @return
	 * @throws Exception
	 * @see cme.com.web.common.service.DmFileService#selectFileInf(cme.com.web.common.vo.DmFileVO)
	 */
    @CommonDataSource(DataSourceType.BOARD)
    public FileVO selectFileInf(FileVO fvo) throws Exception {
    	return fileDAO.selectFileInf(fvo);
    }

    /**
	 * @Method Name  : getNextStringId
	 * @작성일   : 2017. 12. 28. 
	 * @작성자   :  (주)씨엠이소프트 박상웅
	 * @변경이력  :
	 * @Method 설명 :
	 * @return
	 */
	@Override
	@CommonDataSource(DataSourceType.BOARD)
	public String getNextStringId(String string) throws Exception {
		String next_id = "";
		int update = fileDAO.updateStringId(string);
		if (update > 0) {
			next_id = fileDAO.getNextStringId(string);
		}
		return next_id;
	}

	/**
	 * @Method Name  : selectFileSn
	 * @작성일   : 2017. 12. 31. 
	 * @작성자   :  (주)씨엠이소프트 박상웅
	 * @변경이력  :
	 * @Method 설명 :
	 * @param atch_file_id
	 * @return
	 */
	@Override
	@CommonDataSource(DataSourceType.BOARD)
	public String selectFileSn(String atch_file_id) throws Exception {
		return fileDAO.selectFileSn(atch_file_id);
	}
}
