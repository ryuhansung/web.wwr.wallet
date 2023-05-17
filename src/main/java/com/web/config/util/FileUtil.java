package com.web.config.util;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.web.config.CmeResultVO;
import com.web.config.logging.CmeCommonLogger;
import com.web.config.service.FileService;
import com.web.config.util.CmeConstant.CmeSessionConst;
import com.web.config.vo.FileAuditVO;
import com.web.config.vo.FileVO;

@Component
public class FileUtil {

	public static final int BUFF_SIZE = 2048;

	@Autowired
	private FileService fileService;
    
	private String FileAccessExt = "GIF,JPG,PNG,BMP,TIF,JPE,ZIP,DOC,DOCX,XLS,XLSX,PPT,PDF,PPTX,HWP";
	
	private String ImgAcceessExt = "GIF,JPG,PNG,BMP,TIF,JPE";
	CmeCommonLogger log = new CmeCommonLogger(this.getClass());
	
	SessionUtil sUtil = SessionUtil.getinstance();
	/**
	 * 첨부파일에 대한 목록 정보를 취득한다.
	 * 
	 * @param files
	 * @return
	 * @throws Exception
	 */
	public List<FileVO> parseFileInf(Map<String, MultipartFile> files,
			int fileKeyParam, String atchFileId, FileVO fileinfo, HttpServletRequest request)
			throws Exception {
		int fileKey = fileKeyParam;
		String storePathString = "";
		String atchFileIdString = "";
		storePathString = fileinfo.getFile_path();
		if ("".equals(atchFileId) || atchFileId == null) {
			try {
				String table_name = "FILE_ID";
				atchFileIdString = fileService.getNextStringId(table_name);				
			} catch (Exception e) {
				// TODO: handle exception
				log.DebugLog(e.getMessage());
			}


		} else {
			atchFileIdString = atchFileId;
		}
		File saveFolder = new File(storePathString);

		if (!saveFolder.exists() || saveFolder.isFile()) {
			saveFolder.mkdirs();
		}
		Iterator<Entry<String, MultipartFile>> itr = files.entrySet()
				.iterator();
		MultipartFile file;
		String filePath = "";
		List<FileVO> result = new ArrayList<FileVO>();
		FileVO fvo;
		
		sUtil.RemoveSession(request, CmeSessionConst.AUDIT_FILE_INFOLIST);
		ArrayList<FileAuditVO> auditFileInfoList = new ArrayList<FileAuditVO>();
		while (itr.hasNext()) {
			FileAuditVO auditFileItem = null;
			Entry<String, MultipartFile> entry = itr.next();
			file = entry.getValue();
			String orginFileName = file.getOriginalFilename();
			// --------------------------------------
			// 원 파일명이 없는 경우 처리
			// (첨부가 되지 않은 input file type)
			// --------------------------------------
			if ("".equals(orginFileName)) {
				log.ViewWarnLog("parseFileInf : FileName is not Exist");
				continue;
			}
			int index = orginFileName.lastIndexOf(".");
			// String fileName = orginFileName.substring(0, index);
			String fileExt = orginFileName.substring(index + 1);
			String newName = fileinfo.getFile_prefix() + StringUtils.getTimeStamp() + fileKey;
			
			if("Y".equals(fileinfo.getFile_extend_name_yn())){
				newName += "." + fileExt;
			}
			
			long _size = file.getSize();
			File trFile = null;
			boolean upMax = false;
			boolean noExt = false;
			
			if(_size > Long.parseLong(fileinfo.getFile_size())){
				log.ViewWarnLog("parseFileInf : FileSize Over::::[CurrentFileSize:"+_size+"][AuditFileSize:"+fileinfo.getFile_size()+"]");
				auditFileItem = new FileAuditVO();
				auditFileItem.setAuditfileSize(fileinfo.getFile_size());
				auditFileItem.setUploadfileSize(String.valueOf(_size));
				auditFileItem.setUploadfileName(orginFileName);
				upMax = true;
			}
			
			if(!ChkAccFileExt(fileExt, fileinfo)){
				log.ViewWarnLog("parseFileInf : Is not Access File Ext:"+fileExt);
				if(auditFileItem == null){
					auditFileItem = new FileAuditVO();
				}
				auditFileItem.setUploadfileName(orginFileName);
				auditFileItem.setUploadfileExt(fileExt);
				if(fileinfo.getFile_type().equals("I")){
					auditFileItem.setAuditfileExt(ImgAcceessExt);
				}else{
					auditFileItem.setAuditfileExt(FileAccessExt);
				}
				noExt = true;
			}
			
			if(noExt || upMax){
				auditFileInfoList.add(auditFileItem);
				sUtil.SetObjSession(request, CmeSessionConst.AUDIT_FILE_INFOLIST, auditFileInfoList);				
				continue;
			}
			
			if (!"".equals(orginFileName)) {
				filePath = storePathString + File.separator + newName;
				trFile = new File(filePath);
				file.transferTo(trFile);
			}
			
			fvo = new FileVO();
			BeanUtils.copyProperties(fileinfo, fvo);
			fvo.setCodeName(file.getName());
			fvo.setFileExtsn(fileExt);
			fvo.setFileObj(trFile);
			fvo.setFileStreCours(storePathString);
			fvo.setFileMg(Long.toString(_size));
			fvo.setOrignlFileNm(orginFileName);
			fvo.setStreFileNm(newName);
			fvo.setAtchFileId(atchFileIdString);
			fvo.setFileSn(String.valueOf(fileKey));
			result.add(fvo);
			fileKey++;				
		}
		
		return result;
	}

	public List<FileVO> parseFileInf(MultipartFile file,
			int fileKeyParam, String atchFileId, FileVO fileinfo, HttpServletRequest request)
			throws Exception {
		
		int fileKey = fileKeyParam;

		String storePathString = "";
		String atchFileIdString = "";

		storePathString = fileinfo.getFile_path();

		sUtil.RemoveSession(request, CmeSessionConst.AUDIT_FILE_INFOLIST);
		ArrayList<FileAuditVO> auditFileInfoList = new ArrayList<FileAuditVO>();		
		
		boolean upMax = false;
		boolean noExt = false;
		if ("".equals(atchFileId) || atchFileId == null) {
			String table_name = "FILE_ID";
			atchFileIdString = fileService.getNextStringId(table_name);	
		} else {
			atchFileIdString = atchFileId;
		}

		File saveFolder = new File(storePathString);

		if (!saveFolder.exists() || saveFolder.isFile()) {
			saveFolder.mkdirs();
		}

		String filePath = "";
		List<FileVO> result = new ArrayList<FileVO>();
		FileVO fvo;

		String orginFileName = file.getOriginalFilename();

		// --------------------------------------
		// 원 파일명이 없는 경우 처리
		// (첨부가 되지 않은 input file type)
		// --------------------------------------
		if ("".equals(orginFileName)) {
			log.ViewWarnLog("parseFileInf Single : FileName is not Exist");			
			return result;
		}
		// //------------------------------------

		int index = orginFileName.lastIndexOf(".");
		String fileExt = orginFileName.substring(index + 1);
		String newName = fileinfo.getFile_prefix() + StringUtils.getTimeStamp() + fileKey;
		long _size = file.getSize();
		File trFile = null;
		FileAuditVO auditFileItem = null;
		
		if(_size > Long.parseLong(fileinfo.getFile_size())){
			upMax = true;
			
			log.ViewWarnLog("parseFileInf Single : FileSize Over::::[CurrentFileSize:"+_size+"][AuditFileSize:"+fileinfo.getFile_size()+"]");
			auditFileItem = new FileAuditVO();
			auditFileItem.setAuditfileSize(fileinfo.getFile_size());
			auditFileItem.setUploadfileSize(String.valueOf(_size));
			auditFileItem.setUploadfileName(orginFileName);			
		}

		if(!ChkAccFileExt(fileExt, fileinfo)){
			log.ViewWarnLog("parseFileInf Single : Is not Access File Ext:"+fileExt);
			if(auditFileItem == null){
				auditFileItem = new FileAuditVO();
			}
			auditFileItem.setUploadfileName(orginFileName);
			auditFileItem.setUploadfileExt(fileExt);
			if(fileinfo.getFile_type().equals("I")){
				auditFileItem.setAuditfileExt(ImgAcceessExt);
			}else{
				auditFileItem.setAuditfileExt(FileAccessExt);
			}			
			noExt = true;
		}
		
		if(noExt || upMax){
			auditFileInfoList.add(auditFileItem);
			sUtil.SetObjSession(request, CmeSessionConst.AUDIT_FILE_INFOLIST, auditFileInfoList);
			return result;
		}
		
		
		if (!"".equals(orginFileName)) {
			filePath = storePathString + File.separator + newName;
			trFile = new File(filePath);
			file.transferTo(trFile);
		}
		
		fvo = new FileVO();
		BeanUtils.copyProperties(fileinfo, fvo);
		fvo.setFileExtsn(fileExt);
		fvo.setFileObj(trFile);
		fvo.setFileStreCours(storePathString);
		fvo.setFileMg(Long.toString(_size));
		fvo.setOrignlFileNm(orginFileName);
		fvo.setStreFileNm(newName);
		fvo.setAtchFileId(atchFileIdString);
		fvo.setFileSn(String.valueOf(fileKey));
		
		result.add(fvo);
		return result;		
		
	}
	
	
	public List<FileVO> parseUpdateFileInf(Map<String, MultipartFile> files,
			String[] fileKeyParam, String atchFileId, FileVO fileinfo, HttpServletRequest request) throws Exception {
		int fileKey = 0;

		String storePathString = "";
		String atchFileIdString = "";

		storePathString = fileinfo.getFile_path();		
		boolean upMax = false;
		boolean noExt = false;
		if ("".equals(atchFileId) || atchFileId == null) {
			String table_name = "FILE_ID";
			atchFileIdString = fileService.getNextStringId(table_name);	
		} else {
			atchFileIdString = atchFileId;
		}

		File saveFolder = new File(storePathString);

		if (!saveFolder.exists() || saveFolder.isFile()) {
			saveFolder.mkdirs();
		}

		Iterator<Entry<String, MultipartFile>> itr = files.entrySet()
				.iterator();
		MultipartFile file;
		String filePath = "";
		List<FileVO> result = new ArrayList<FileVO>();
		FileVO fvo;
		int i = 0;
		boolean isUpdate = false;
		String fileSn = "";
		
		sUtil.RemoveSession(request, CmeSessionConst.AUDIT_FILE_INFOLIST);
		ArrayList<FileAuditVO> auditFileInfoList = new ArrayList<FileAuditVO>();
		
		while (itr.hasNext()) {
			Entry<String, MultipartFile> entry = itr.next();
			isUpdate = false;
			FileAuditVO auditFileItem = null;
			
			if(fileKeyParam.length > 0){
				for(int j=0; j < fileKeyParam.length; j++){
					if(j == i){
						isUpdate = true;
						fileSn = fileKeyParam[j];
						break;
					}
				}
			}

			if(!isUpdate){
				i++;
				continue;
			}
			fileKey = Integer.parseInt(fileSn);
			file = entry.getValue();
			String orginFileName = file.getOriginalFilename();			
			long _size = file.getSize();
			int index = orginFileName.lastIndexOf(".");
			String fileExt = orginFileName.substring(index + 1);
			
			if(_size > Long.parseLong(fileinfo.getFile_size())){
				log.ViewWarnLog("parseFileInf Single : FileSize Over::::[CurrentFileSize:"+_size+"][AuditFileSize:"+fileinfo.getFile_size()+"]");
				auditFileItem = new FileAuditVO();
				auditFileItem.setAuditfileSize(fileinfo.getFile_size());
				auditFileItem.setUploadfileSize(String.valueOf(_size));
				auditFileItem.setUploadfileName(orginFileName);	
				upMax = true;
				//continue;
			}			
			
			if(!ChkAccFileExt(fileExt, fileinfo)){
				log.ViewWarnLog("parseFileInf Single : Is not Access File Ext:"+fileExt);
				if(auditFileItem == null){
					auditFileItem = new FileAuditVO();
				}
				auditFileItem.setUploadfileName(orginFileName);
				auditFileItem.setUploadfileExt(fileExt);
				if(fileinfo.getFile_type().equals("I")){
					auditFileItem.setAuditfileExt(ImgAcceessExt);
				}else{
					auditFileItem.setAuditfileExt(FileAccessExt);
				}			
				noExt = true;
				//continue;
			}			
			
			if(noExt || upMax){
				auditFileInfoList.add(auditFileItem);
				sUtil.SetObjSession(request, CmeSessionConst.AUDIT_FILE_INFOLIST, auditFileInfoList);				
				continue;
			}			
			
			if(fileKey == 100){ //수정 중에 추가가 발생 할 수 있는 녀석들 처리
				fvo = new FileVO();
				fvo.setAtchFileId(atchFileId);
				int cnt = fileService.getMaxFileSN(fvo);
				
				List<FileVO> _result = parseFileInf(file,
						cnt, atchFileId, fileinfo, request);
				if(_result.size() > 0){
					int updateInsert = fileService.updateInsertFileInfs(_result);
					fileSn = String.valueOf(cnt);
					if(updateInsert > 0){
						log.ViewLog("파일 수정 리스트중에 추가 파일 업로드 성공");
					}					
				}
				continue;
			}

			fvo = new FileVO();
			fvo.setAtchFileId(atchFileIdString);
			fvo.setFileSn(fileSn);
			
			FileVO existFileInfo = fileService.selectFileInf(fvo);
			
			// --------------------------------------
			// 원 파일명이 없는 경우 처리
			// (첨부가 되지 않은 input file type)
			// --------------------------------------
			if ("".equals(orginFileName)) {
				log.ViewWarnLog("parseFileInf : FileName is not Exist");
				continue;
			}
			// //------------------------------------

			String newName = fileinfo.getFile_prefix() + StringUtils.getTimeStamp() + fileKey;

			File trFile = null;
			if (!"".equals(orginFileName)) {
				File existFile = new File(storePathString + existFileInfo.getFileStreCours());
				if(existFile.exists()){
					existFile.delete();
				}
				filePath = storePathString + File.separator + newName;
				trFile = new File(filePath);
				file.transferTo(trFile);
			}
			fvo = new FileVO();
			BeanUtils.copyProperties(fileinfo, fvo);			
			fvo.setFileExtsn(fileExt);
			fvo.setFileObj(trFile);			
			fvo.setFileStreCours(storePathString);
			fvo.setFileMg(Long.toString(_size));
			fvo.setOrignlFileNm(orginFileName);
			fvo.setStreFileNm(newName);
			fvo.setAtchFileId(atchFileIdString);
			fvo.setFileSn(String.valueOf(fileKey));

			if(upMax)
				fvo.setIsOverMaxlimit("Y");
			if(noExt)
				fvo.setIsExtFile("Y");			
			
			// writeFile(file, newName, storePathString);
			result.add(fvo);
			i++;
			// fileKey++;
		}
		return result;
	}

	// directory filter
/*	private FileFilter getDirectoryFilter() {
		FileFilter fileFilter = new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				// TODO Auto-generated method stub
				return false;
			}
		};
		return fileFilter;
	}*/
	
	String[] getDirList(File file){
		String[] res = null;
		res = file.list(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return new File(dir, name).isDirectory();
			}
		});

		return res;
	}
	
	// file filter
	private FilenameFilter getFileFilter() {
		FilenameFilter filter = new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return !name.startsWith(".");
			}
		};
		return filter;
	}

	/**
	 *  이미지 업로드
	 */
	public String writeImgFile(FileVO vo,
			final MultipartHttpServletRequest multiRequest,
			HttpServletRequest request,
			String fileUid
			) throws Exception {

		String resMsg = "";
		CmeResultVO resultVO = new CmeResultVO();
		vo.setFile_id(fileUid);
		resultVO = fileService.FileUploadAction(vo, multiRequest, request); // 입력,추가,삭제가 알아서 이루어집니다.
		resMsg = resultVO.getResultMsg();

		return resMsg;
	}	
	
	
	public ArrayList<String> getFilelist(String path) {
		File dir = new File(path);
		return conv_fileArry_to_list(dir.listFiles(getFileFilter()));
	}
	
	public ArrayList<String> getDirectories(String path){
		ArrayList<String> dirList = new ArrayList<String>();
		
		path = path.replaceAll("\\\\", "/");
		
		File _file = new File(path);
		String[] res = getDirList(_file);
		
		if(res == null){
			return null;
		}
		
		for(String s: res){
			dirList.add(s);
		}

		return dirList;
	}

	private ArrayList<String> conv_fileArry_to_list(File[] dirlist) {
		ArrayList<String> res = new ArrayList<String>();
		//this.list.clear();
		for (File flist : dirlist) {
			res.add(flist.getName());
		}
		return res;
	}

	ArrayList<String> conv_dirArray_to_list(String[] dirlist){
		ArrayList<String> res = new ArrayList<String>();
		log.DebugLog(">>>"+dirlist);
		for(String s : dirlist){
			res.add(s);
		}
		
		return res;
	}
	
	boolean ChkAccFileExt(String fileExt, FileVO fileinfo){
		String[] confext = null;
		if(fileinfo.getFile_type().equals("I")){
			confext = ImgAcceessExt.split("\\,");
		}else{
			confext = FileAccessExt.split("\\,");			
		}
		
		for(String s: confext){
			if(s.equals(fileExt.toUpperCase())){
				return true;
			}
		}
		return false;
	}

}
