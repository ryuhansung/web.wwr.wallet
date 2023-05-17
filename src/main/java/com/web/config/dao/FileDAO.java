/**
 * (주)크림스 의 시스템 웹어플리케이션 프레임웍 입니다. 허가 없이 복제 및 배포 할 수 없습니다.
 * 개발사 : (주)크림스
 * CopyRight Creams. inc. Since 2015 
 * 총괄 개발 책임자 : 주식회사 크림스 통합개발연구소 소장 김윤관
 */
package com.web.config.dao;

import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.web.config.logging.CmeCommonLogger;
import com.web.config.vo.FileVO;


/**
 * @프로젝트명	: cme.web.cube
 * @패키지    	: cme.com.web.common.dao
 * @클래스명  	: cme.web.cube
 * @작성자		:  (주)씨엠이소프트 박상웅
 * @작성일		: 2017. 12. 4.
 */
@Repository
public class FileDAO extends CmeComAbstractDAO{

	CmeCommonLogger log = new CmeCommonLogger(this.getClass());
	
	/**
	 * @Method Name  : selectFileInfs
	 * @작성일   : 2017. 12. 4. 
	 * @작성자   :  (주)씨엠이소프트 박상웅
	 * @변경이력  :
	 * @Method 설명 :
	 * @param fvo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<FileVO> selectFileInfs(FileVO fvo) throws Exception{
		return list("FileDAO.selectFileList", fvo);
	}

	/**
	 * @Method Name  : insertFileInf
	 * @작성일   : 2017. 12. 4. 
	 * @작성자   :  (주)씨엠이소프트 박상웅
	 * @변경이력  :
	 * @Method 설명 :
	 * @param fvo
	 */
	public void insertFileInf(FileVO fvo) throws Exception{
		insert("FileDAO.insertFileMaster", fvo);
		insert("FileDAO.insertFileDetail", fvo);
	}

	/**
	 * @Method Name  : insertFileInfs
	 * @작성일   : 2017. 12. 4. 
	 * @작성자   :  (주)씨엠이소프트 박상웅
	 * @변경이력  :
	 * @Method 설명 :
	 * @param fvoList
	 * @return
	 */
	@SuppressWarnings("rawtypes" )
	public String insertFileInfs(List fileList) throws Exception{
		FileVO vo = (FileVO) fileList.get(0);
		String atchFileId = vo.getAtchFileId();
		int res = (Integer) insert("FileDAO.insertFileMaster", vo);

		Iterator iter = fileList.iterator();
		while (iter.hasNext()) {
			vo = (FileVO) iter.next();

			insert("FileDAO.insertFileDetail", vo);
		}

		return atchFileId;
	}

	/**
	 * @Method Name  : updateFileInfs
	 * @작성일   : 2017. 12. 4. 
	 * @작성자   :  (주)씨엠이소프트 박상웅
	 * @변경이력  :
	 * @Method 설명 :
	 * @param fvoList
	 * @return
	 */
	@SuppressWarnings("rawtypes" )
	public int updateFileInfs(List fileList) throws Exception{
		FileVO vo;
		int res = 0;
		Iterator iter = fileList.iterator();
		
		while (iter.hasNext()) {
			vo = (FileVO) iter.next();
			res += update("FileDAO.updateFileDetail", vo);
		}
		return res;
	}

	/**
	 * @Method Name  : updateInsertFileInfs
	 * @작성일   : 2017. 12. 4. 
	 * @작성자   :  (주)씨엠이소프트 박상웅
	 * @변경이력  :
	 * @Method 설명 :
	 * @param fvoList
	 * @return
	 */
	@SuppressWarnings("rawtypes" )
	public int updateInsertFileInfs(List fileList) throws Exception{
		FileVO vo;
		int res = 0;
		Iterator iter = fileList.iterator();
		
		while (iter.hasNext()) {
			vo = (FileVO) iter.next();
			res += (Integer)insert("FileDAO.insertFileDetail", vo);
		}
		return res;
	}

	/**
	 * @Method Name  : deleteFileInfs
	 * @작성일   : 2017. 12. 4. 
	 * @작성자   :  (주)씨엠이소프트 박상웅
	 * @변경이력  :
	 * @Method 설명 :
	 * @param fvoList
	 * @return
	 */
	@SuppressWarnings("rawtypes" )
	public int deleteFileInfs(List fileList) throws Exception{
		Iterator iter = fileList.iterator();
		int rtnres = 0;
		FileVO vo;
		while (iter.hasNext()) {
			vo = (FileVO) iter.next();
			int res = delete("FileDAO.deleteFileDetail", vo);
			if (res > 0) {
				log.DebugLog("상세파일 삭제 성공:" + vo.getOrignlFileNm());
				int cnt = (Integer) getSqlSession().selectOne(
						"FileDAO.getDetailFileCnt", vo);
				if (cnt == 0) {
					log.DebugLog("부모파일 삭제 성공:" + vo.getOrignlFileNm());
					rtnres = delete("FileDAO.deleteFileId", vo);
				}
			}
		}
		return rtnres;
	}

	/**
	 * @Method Name  : deleteFileInf
	 * @작성일   : 2017. 12. 4. 
	 * @작성자   :  (주)씨엠이소프트 박상웅
	 * @변경이력  :
	 * @Method 설명 :
	 * @param fvo
	 * @return
	 */
	public int deleteFileInf(FileVO fvo) throws Exception{
		int res = 0;
		
		res = delete("FileDAO.deleteFileDetail", fvo);
		if (res > 0) {
			int cnt = (Integer) getSqlSession().selectOne(
					"FileDAO.getDetailFileCnt", fvo);
			if (cnt == 0) {
				res = delete("FileDAO.deleteFileId", fvo);
			}
		}

		return res;
	}

	/**
	 * @Method Name  : selectFileInf
	 * @작성일   : 2017. 12. 4. 
	 * @작성자   :  (주)씨엠이소프트 박상웅
	 * @변경이력  :
	 * @Method 설명 :
	 * @param fvo
	 * @return
	 */
	public FileVO selectFileInf(FileVO fvo) throws Exception{
		return (FileVO) selectByPk("FileDAO.selectFileInf", fvo);
	}

	/**
	 * @Method Name  : getMaxFileSN
	 * @작성일   : 2017. 12. 4. 
	 * @작성자   :  (주)씨엠이소프트 박상웅
	 * @변경이력  :
	 * @Method 설명 :
	 * @param fvo
	 * @return
	 */
	public int getMaxFileSN(FileVO fvo) throws Exception{
		return (Integer) getSqlSession().selectOne("FileDAO.getMaxFileSN",fvo);
	}

	/**
	 * @Method Name  : deleteAllFileInf
	 * @작성일   : 2017. 12. 4. 
	 * @작성자   :  (주)씨엠이소프트 박상웅
	 * @변경이력  :
	 * @Method 설명 :
	 * @param fvo
	 */
	public void deleteAllFileInf(FileVO fvo) throws Exception{
		update("FileDAO.deleteCOMTNFILE", fvo);
		
	}

	/**
	 * @Method Name  : deleteFileAll
	 * @작성일   : 2017. 12. 4. 
	 * @작성자   :  (주)씨엠이소프트 박상웅
	 * @변경이력  :
	 * @Method 설명 :
	 * @param fvo
	 */
	public void deleteFileAll(FileVO fvo) throws Exception{
		delete("FileDAO.deleteFileAll", fvo);
		
	}

	/**
	 * @Method Name  : selectFileListByFileNm
	 * @작성일   : 2017. 12. 4. 
	 * @작성자   :  (주)씨엠이소프트 박상웅
	 * @변경이력  :
	 * @Method 설명 :
	 * @param fvo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<FileVO> selectFileListByFileNm(FileVO fvo) throws Exception{
		return list("FileDAO.selectFileListByFileNm", fvo);
	}

	/**
	 * @Method Name  : selectFileListCntByFileNm
	 * @작성일   : 2017. 12. 4. 
	 * @작성자   :  (주)씨엠이소프트 박상웅
	 * @변경이력  :
	 * @Method 설명 :
	 * @param fvo
	 * @return
	 */
	public int selectFileListCntByFileNm(FileVO fvo) throws Exception{
		return (Integer) getSqlSession().selectOne("FileDAO.selectFileListCntByFileNm", fvo);
	}

	/**
	 * @Method Name  : selectImageFileList
	 * @작성일   : 2017. 12. 4. 
	 * @작성자   :  (주)씨엠이소프트 박상웅
	 * @변경이력  :
	 * @Method 설명 :
	 * @param vo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<FileVO> selectImageFileList(FileVO vo) throws Exception{
		return list("FileDAO.selectImageFileList", vo);
	}

	/**
	 * @Method Name  : getUploadFileInfo
	 * @작성일   : 2017. 12. 4. 
	 * @작성자   :  (주)씨엠이소프트 박상웅
	 * @변경이력  :
	 * @Method 설명 :
	 * @param vo
	 * @return
	 */
	public FileVO getUploadFileInfo(FileVO vo) throws Exception{
		return (FileVO)selectByPk("FileDAO.selectUploadFileInfo", vo);
	}

	/**
	 * @Method Name  : getUploadFileMstrList
	 * @작성일   : 2017. 12. 4. 
	 * @작성자   :  (주)씨엠이소프트 박상웅
	 * @변경이력  :
	 * @Method 설명 :
	 * @param vo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<FileVO> getUploadFileMstrList(FileVO vo) throws Exception{
		return list("FileDAO.selectUploadFileInfo", vo);
	}

	/**
	 * @Method Name  : getTmpAtchFile
	 * @작성일   : 2017. 12. 4. 
	 * @작성자   :  (주)씨엠이소프트 박상웅
	 * @변경이력  :
	 * @Method 설명 :
	 * @param vo
	 * @return
	 */
	public FileVO getTmpAtchFile(FileVO vo) throws Exception{
		return (FileVO)selectByPk("FileDAO.selectTmpFileIno", vo);
	}

	/**
	 * @Method Name  : insertUploadFileMstr
	 * @작성일   : 2017. 12. 4. 
	 * @작성자   :  (주)씨엠이소프트 박상웅
	 * @변경이력  :
	 * @Method 설명 :
	 * @param vo
	 * @return
	 */
	public int insertUploadFileMstr(FileVO vo) throws Exception{
		return (Integer)insert("FileDAO.insertUploadFileMstr",vo);
	}

	/**
	 * @Method Name  : insertTmpFile
	 * @작성일   : 2017. 12. 4. 
	 * @작성자   :  (주)씨엠이소프트 박상웅
	 * @변경이력  :
	 * @Method 설명 :
	 * @param vo
	 * @return
	 */
	public int insertTmpFile(FileVO vo) throws Exception{
		return (Integer)insert("FileDAO.inserTmpFile", vo);
	}

	/**
	 * @Method Name  : fileIdList
	 * @작성일   : 2017. 12. 4. 
	 * @작성자   :  (주)씨엠이소프트 박상웅
	 * @변경이력  :
	 * @Method 설명 :
	 * @param vo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<FileVO> fileIdList(FileVO vo) throws Exception{
		return list("FileDAO.fileIdList", vo);
	}

	/**
	 * @Method Name  : getTotCnt
	 * @작성일   : 2017. 12. 4. 
	 * @작성자   :  (주)씨엠이소프트 박상웅
	 * @변경이력  :
	 * @Method 설명 :
	 * @param vo
	 * @return
	 */
	public int getTotCnt(FileVO vo) throws Exception{
		return (Integer)getSqlSession().selectOne("FileDAO.getTotCnt", vo);
	}

	/**
	 * @Method Name  : fileIdDetail
	 * @작성일   : 2017. 12. 4. 
	 * @작성자   :  (주)씨엠이소프트 박상웅
	 * @변경이력  :
	 * @Method 설명 :
	 * @param vo
	 * @return
	 */
	public FileVO fileIdDetail(FileVO vo) throws Exception{
		return (FileVO)selectByPk("FileDAO.fileIdDetail", vo); 
	}

	/**
	 * @Method Name  : fileIdDelete
	 * @작성일   : 2017. 12. 4. 
	 * @작성자   :  (주)씨엠이소프트 박상웅
	 * @변경이력  :
	 * @Method 설명 :
	 * @param vo
	 */
	public void fileIdDelete(FileVO vo) throws Exception{
		delete("FileDAO.fileIdDelete", vo);
		
	}

	/**
	 * @Method Name  : fileIdUpdate
	 * @작성일   : 2017. 12. 4. 
	 * @작성자   :  (주)씨엠이소프트 박상웅
	 * @변경이력  :
	 * @Method 설명 :
	 * @param vo
	 * @return
	 */
	public int fileIdUpdate(FileVO vo) throws Exception{
		return (Integer)update("FileDAO.fileIdUpdate", vo);
	}

	/**
	 * @Method Name  : getNextStringId
	 * @작성일   : 2017. 12. 28. 
	 * @작성자   :  (주)씨엠이소프트 박상웅
	 * @변경이력  :
	 * @Method 설명 :
	 * @return
	 */
	public String getNextStringId(String string) throws Exception{
		return (String)getSqlSession().selectOne("FileDAO.getNextStringId", string);
	}

	/**
	 * @Method Name  : updateStringId
	 * @작성일   : 2017. 12. 28. 
	 * @작성자   :  (주)씨엠이소프트 박상웅
	 * @변경이력  :
	 * @Method 설명 :
	 * @param string
	 */
	public int updateStringId(String string) {
		return update("FileDAO.updateStringId", string);
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
	public String selectFileSn(String atch_file_id) throws Exception{
		return getSqlSession().selectOne("FileDAO.selectFileSn", atch_file_id);
	}

}
