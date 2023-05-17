package com.web.config;

import java.io.Serializable;

public class CmeResultVO implements Serializable{

    private static final long serialVersionUID = 7505549301689585084L;
    
    String resultMsg ="";
    String resultStrCode = "";
    private String resultDescMsg = "";
    String resultStrCode1 = "";
    String excelMappingVO = "";
    int resultCode = -1;
    
    
    
    public String getResultStrCode() {
        return resultStrCode;
    }
    public void setResultStrCode(String resultStrCode) {
        this.resultStrCode = resultStrCode;
    }
    public String getResultMsg() {
        return resultMsg;
    }
    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }
    public int getResultCode() {
        return resultCode;
    }
    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }
    public String getResultStrCode1() {
        return resultStrCode1;
    }
    public void setResultStrCode1(String resultStrCode1) {
        this.resultStrCode1 = resultStrCode1;
    }
    /**
     * @return the excelMappingVO
     */
    public String getExcelMappingVO() {
        return excelMappingVO;
    }
    /**
     * @param excelMappingVO the excelMappingVO to set
     */
    public void setExcelMappingVO(String excelMappingVO) {
        this.excelMappingVO = excelMappingVO;
    }
    /**
     * @return the resultDescMsg
     */
    public String getResultDescMsg() {
        return resultDescMsg;
    }
    /**
     * @param resultDescMsg the resultDescMsg to set
     */
    public void setResultDescMsg(String resultDescMsg) {
        this.resultDescMsg = resultDescMsg;
    }
    
    
    
}