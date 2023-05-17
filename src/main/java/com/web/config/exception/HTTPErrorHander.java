package com.web.config.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.web.config.vo.CmeExceptionVO;

public class HTTPErrorHander {
	/* 404 error */
	@ExceptionHandler(NoHandlerFoundException.class)
    public ModelAndView NoHandlerFoundException(Exception e) {
		
		ModelAndView mnv = new ModelAndView("/error/error_main");
		CmeExceptionVO exceptionVO = new CmeExceptionVO();
		exceptionVO.setErrorStatusCode(String.valueOf(HttpStatus.NOT_FOUND));
		exceptionVO.setErrorMessage(e.getMessage());
		
		mnv.addObject("exceptionVO", exceptionVO);
         
        return mnv;
    }
	 
	@ExceptionHandler(value= {CmeApplicationException.class})	
    public ModelAndView handleRuntimeException(RuntimeException e) {
		ModelAndView mnv = new ModelAndView("/error/error_main");
		CmeExceptionVO exceptionVO = new CmeExceptionVO();
		exceptionVO.setErrorStatusCode(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR));
		exceptionVO.setErrorMessage(e.getMessage());
		
		mnv.addObject("exceptionVO", exceptionVO);
         
        return mnv;
    }
	
	@ExceptionHandler(Exception.class)
    public ModelAndView handleException(Exception e) {
		
		e.printStackTrace();
//		System.out.println(e.getMessage());
		ModelAndView mnv = new ModelAndView("/error/error_main");
		CmeExceptionVO exceptionVO = new CmeExceptionVO();
		exceptionVO.setErrorStatusCode(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR));
		exceptionVO.setErrorMessage(e.getMessage());
		
		mnv.addObject("exceptionVO", exceptionVO);
         
        return mnv;
    }
}
