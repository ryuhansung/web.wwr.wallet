package com.web.config.control;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.web.config.logging.CmeCommonLogger;
import com.web.config.service.FileService;
import com.web.config.vo.FileVO;



@Controller
@RequestMapping("/common/file")
public class FileMngController {
	
	@Autowired
	FileService fileService;
	
	CmeCommonLogger log = new CmeCommonLogger(this.getClass());

	/**
	 * @Method Name  : getImageInf
	 * @작성일   : 2012. 12. 15. 
	 * @작성자   : Kim, YunKwan
	 * @변경이력  :
	 * @Method 설명 : 이미지 미리 보기
	 * @param model
	 * @param atchFileId
	 * @param fileSn
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/getImageView.dm")
	public void getImageInf(
			Model model,
			@RequestParam(value = "atchFileId", required = false) String atchFileId,
			@RequestParam(value = "fileSn", required = false) String fileSn,
			HttpServletResponse response) throws Exception {

		FileVO vo = new FileVO();

		vo.setAtchFileId(atchFileId);
		vo.setFileSn(fileSn);

		FileVO fvo = fileService.selectFileInf(vo);

		// String fileLoaction = fvo.getFileStreCours() + fvo.getStreFileNm();

		File file = new File(fvo.getFileStreCours(), fvo.getStreFileNm());
		FileInputStream fis = null;
		new FileInputStream(file);

		BufferedInputStream in = null;
		ByteArrayOutputStream bStream = null;
		try {
			fis = new FileInputStream(file);
			in = new BufferedInputStream(fis);
			bStream = new ByteArrayOutputStream();
			int imgByte;
			while ((imgByte = in.read()) != -1) {
				bStream.write(imgByte);
			}

			String type = "";

			if (fvo.getFileExtsn() != null && !"".equals(fvo.getFileExtsn())) {
				if ("jpg".equals(fvo.getFileExtsn().toLowerCase())) {
					type = "image/jpeg";
				} else {
					type = "image/" + fvo.getFileExtsn().toLowerCase();
				}
				type = "image/" + fvo.getFileExtsn().toLowerCase();

			} else {
				log.ViewWarnLog("Image fileType is null.");
			}

			response.setHeader("Content-Type", type);
			response.setContentLength(bStream.size());

			bStream.writeTo(response.getOutputStream());

			response.getOutputStream().flush();
			response.getOutputStream().close();

		} catch (Exception e) {
			log.ViewErrorLog(e.getMessage());// e.printStackTrace();
		} finally {
			if (bStream != null) {
				try {
					bStream.close();
				} catch (Exception est) {
					log.ViewErrorLog("IGNORED: " + est.getMessage());
				}
			}
			if (in != null) {
				try {
					in.close();
				} catch (Exception ei) {
					log.ViewErrorLog("IGNORED: " + ei.getMessage());
				}
			}
			if (fis != null) {
				try {
					fis.close();
				} catch (Exception efis) {
					log.ViewErrorLog("IGNORED: " + efis.getMessage());
				}
			}
		}

	}
}
