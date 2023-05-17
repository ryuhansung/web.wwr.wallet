package com.web.config.dbinfo;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.web.config.annotation.CommonDataSource;
import com.web.config.logging.CmeCommonLogger;

@Aspect
@Component
@Order(value=1)
public class CmeDbExcutionLoggingAspect implements InitializingBean{
    protected CmeCommonLogger log = new CmeCommonLogger(this.getClass());
    
    public Object doServiceProfiling(ProceedingJoinPoint joinPoint) throws Throwable{
        
        //log.DebugLog("=======================CmeDbExcutionLoggingAspect start============================");
        String strlog = "DataSource Binding Start|";
        final String methodName = joinPoint.getSignature().getName();
        final MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        
        if(method.getDeclaringClass().isInterface()){
            method = joinPoint.getTarget().getClass().getDeclaredMethod(methodName, method.getParameterTypes());
        }
        //Annotation을 가져온다.
        CommonDataSource dataSource = null;         
        try {
            dataSource = (CommonDataSource) method.getAnnotation(CommonDataSource.class);           
        } catch (Exception e) {
            //log.ViewErrorLog("Error:"+e.getMessage());
        }
        
        if(dataSource != null){
            //Method에 해당 dataSource관련 설정이 있을 경우 해당 dataSource의 value를 읽어 들인다.
            //log.DebugLog("====>dataSource ::"+dataSource.value());
            DataContextHolder.setDataSourceType(dataSource.value());
        }else{
//            DataSource 기본값세팅
            DataContextHolder.setDataSourceType(DataSourceType.OPER);

        }
        strlog += "Bind DataSource #####===>" + DataContextHolder.getDataSourceType();
        //log.DebugLog(strlog);
        Object returnValue = joinPoint.proceed();
        //log.DebugLog("returnValue:::"+ returnValue);
        DataContextHolder.clearDataSourceType();
        
        return returnValue;     
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        // TODO Auto-generated method stub
    }

}
