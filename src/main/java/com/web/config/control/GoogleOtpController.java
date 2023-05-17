/**
 * (주)크림스 의 시스템 웹어플리케이션 프레임웍 입니다. 허가 없이 복제 및 배포 할 수 없습니다.
 * 개발사 : (주)크림스
 * CopyRight Creams. inc. Since 2015 
 * 총괄 개발 책임자 : 주식회사 크림스 통합개발연구소 소장 김윤관
 */
package com.web.config.control;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base32;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.web.config.vo.GooglOtpVO;



/**
 * @프로젝트명	: cme.web.noryapi
 * @패키지    	: cme.web.googleotp.control
 * @클래스명  	: cme.web.noryapi
 * @작성자		: (주)씨엠이소프트 최종근
 * @작성일		: 2017. 7. 27.
 */
@Controller
@RequestMapping("/otp")
public class GoogleOtpController extends CmeDefaultExtendController{
	
	
	@RequestMapping (value="/newQrAKey.dm")
	@ResponseBody
	public GooglOtpVO newQrAKey(GooglOtpVO otpVO, HttpServletRequest request, HttpServletResponse response,  Model model) throws Exception{
		


        byte[] buffer = new byte[5 + 5 * 5];
        new Random().nextBytes(buffer);

//        Base32 codec = new Base32();
//
//        byte[] secretKey = Arrays.copyOf(buffer, 5);
//        byte[] bEncodedKey = codec.encode(secretKey);
         
        // 생성된 Key!
//        String encodedKey = new String(bEncodedKey);
        
//        otpVO.setEncodedKey(encodedKey);
//       
        String url = newQRBarcodeURL(otpVO.getUrlHost()); // 생성된 바코드 주소!
//        
        otpVO.setQrCodeUrl(url);
         
        
		return otpVO;
	}
	
	
	@RequestMapping (value="/getQrAKey.dm")
	@ResponseBody
	public GooglOtpVO pensionList(GooglOtpVO otpVO, 
			HttpServletRequest request, 
			HttpServletResponse response,  Model model)
			throws Exception{
		
		// Allocating the buffer
//      byte[] buffer = new byte[secretSize + numOfScratchCodes * scratchCodeSize];
        byte[] buffer = new byte[5 + 5 * 5];
         
        // Filling the buffer with random numbers.
        // Notice: you want to reuse the same random generator
        // while generating larger random number sequences.
        new Random().nextBytes(buffer);
 
        // Getting the key and converting it to Base32
        Base32 codec = new Base32();
//      byte[] secretKey = Arrays.copyOf(buffer, secretSize);
        byte[] secretKey = Arrays.copyOf(buffer, 5);
        byte[] bEncodedKey = codec.encode(secretKey);
         
        // 생성된 Key!
        String encodedKey = new String(bEncodedKey);
        
        otpVO.setEncodedKey(encodedKey);
       
        String url = getQRBarcodeURL(otpVO.getUrlHost(), encodedKey); // 생성된 바코드 주소!
        
        otpVO.setQrCodeUrl(url);
         
        
		return otpVO;
	}
	
	
	public static String newQRBarcodeURL(String user) {
        String format = "https://chart.googleapis.com/chart?chs=200x200&chld=M%%7C0&cht=qr&chl="+user;
         
        return String.format(format, user);
    }
	
	public static String getQRBarcodeURL(String user,  String secret) {
        String format = "https://chart.googleapis.com/chart?chs=200x200&chld=M%%7C0&cht=qr&chl=otpauth://totp/%s%%3Fsecret%%3D%s";
         
        return String.format(format, user, secret);
    }
	
	@RequestMapping (value="/chkAuthKey.dm")
	@ResponseBody
	public GooglOtpVO chkAuthKey(GooglOtpVO otpVO,
				HttpServletRequest request, 
				HttpServletResponse response,  Model model)
            throws ServletException, IOException ,Exception{
         
		String user_codeStr = otpVO.getAuthCode();
        long user_code = Integer.parseInt(user_codeStr);
        String encodedKey = otpVO.getEncodedKey();
//        long l = new Date().getTime();
//        long l = Long.parseLong(nowTime());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date nowDate = format.parse(nowTime());
        long l = nowDate.getTime();
        long ll =  l / 30000;
         
        boolean check_code = false;
        try {
            // 키, 코드, 시간으로 일회용 비밀번호가 맞는지 일치 여부 확인.
            check_code = check_code(encodedKey, user_code, ll);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        
        if(check_code) {
        	otpVO.setResultCode(1);
        }else {
        	otpVO.setResultCode(-1);
        }
        // 일치한다면 true.
        return otpVO;
         
    }
 
    private static boolean check_code(String secret, long code, long t) throws NoSuchAlgorithmException, InvalidKeyException {
        Base32 codec = new Base32();
        byte[] decodedKey = codec.decode(secret);
 
        // Window is used to check codes generated in the near past.
        // You can use this value to tune how far you're willing to go.
        int window = 3;
        for (int i = -window; i <= window; ++i) {
            long hash = verify_code(decodedKey, t + i);
 
            if (hash == code) {
                return true;
            }
        }
 
        // The validation code is invalid.
        return false;
    }
     
    private static int verify_code(byte[] key, long t)
            throws NoSuchAlgorithmException, InvalidKeyException {
        byte[] data = new byte[8];
        long value = t;
        for (int i = 8; i-- > 0; value >>>= 8) {
            data[i] = (byte) value;
        }
 
        SecretKeySpec signKey = new SecretKeySpec(key, "HmacSHA1");
        Mac mac = Mac.getInstance("HmacSHA1");
        mac.init(signKey);
        byte[] hash = mac.doFinal(data);
 
        int offset = hash[20 - 1] & 0xF;
 
        // We're using a long because Java hasn't got unsigned int.
        long truncatedHash = 0;
        for (int i = 0; i < 4; ++i) {
            truncatedHash <<= 8;
            // We are dealing with signed bytes:
            // we just keep the first byte.
            truncatedHash |= (hash[offset + i] & 0xFF);
        }
 
        truncatedHash &= 0x7FFFFFFF;
        truncatedHash %= 1000000;
 
        return (int) truncatedHash;
    }
}
