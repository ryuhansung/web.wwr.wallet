package com.web.config.dbinfo;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import com.web.config.init.WebConfig;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;


@Configuration
@Component
public class DataSourceConfig {
           
    
    @Bean 
    public CmeRoutingDataSource multiDataSource() {
       CmeRoutingDataSource cliDataSource =  new CmeRoutingDataSource();
       
       Map<Object, Object> targetDataSource = new HashMap<Object, Object>();
       targetDataSource.put(DataSourceType.OPER, oper());
       targetDataSource.put(DataSourceType.BOARD, board());
       
       cliDataSource.setTargetDataSources(targetDataSource);
       
       cliDataSource.setDefaultTargetDataSource(oper());
       cliDataSource.afterPropertiesSet();
       return cliDataSource;
    }
    
    /**
	 * @Method Name  : 
	 * @작성일   : 2017. 11. 8. 
	 * @작성자   :  (주)씨엠이소프트 임승연
	 * @변경이력  :
	 * @Method 설명 :
	 * @return
	 */


    @Bean(name="oper",destroyMethod="close")
    public DataSource oper() {


        BasicDataSource dataSource = new BasicDataSource();

        dataSource.setDriverClassName("net.sf.log4jdbc.sql.jdbcapi.DriverSpy");
        dataSource.setUrl("jdbc:log4jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS_LIST=(ADDRESS=(PROTOCOL=TCP)(HOST="+ WebConfig.getInstance().DB_HOST +")(PORT="+ WebConfig.getInstance().DB_PORT +")))(CONNECT_DATA=(SERVICE_NAME="+WebConfig.getInstance().DB_SERVICE+")))");
        dataSource.setUsername(WebConfig.getInstance().DB_ID);
        dataSource.setPassword(WebConfig.getInstance().DB_PASS);
        dataSource.setValidationQuery("select 1 from dual");
        dataSource.setTestOnBorrow(true);
        dataSource.setTestOnReturn(true);
        dataSource.setTestWhileIdle(true);
        dataSource.setTimeBetweenEvictionRunsMillis(1800000);
        dataSource.setNumTestsPerEvictionRun(10);
        dataSource.setMinEvictableIdleTimeMillis(1800000);
        dataSource.setInitialSize(10);
        dataSource.setMaxActive(10);
        dataSource.setMaxIdle(10);
        dataSource.setMinIdle(10);
        return dataSource;
    }

    @Bean(name="board",destroyMethod="close")
    public DataSource board() {


        BasicDataSource dataSource = new BasicDataSource();

        dataSource.setDriverClassName("net.sf.log4jdbc.sql.jdbcapi.DriverSpy");
        dataSource.setUrl("jdbc:log4jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS_LIST=(ADDRESS=(PROTOCOL=TCP)(HOST="+ WebConfig.getInstance().DB_HOST_BOARD +")(PORT="+WebConfig.getInstance().DB_PORT+")))(CONNECT_DATA=(SERVICE_NAME="+WebConfig.getInstance().DB_SERVICE_BOARD+")))");
        dataSource.setUsername(WebConfig.getInstance().DB_ID_BOARD);
        dataSource.setPassword(WebConfig.getInstance().DB_PASS_BOARD);
        dataSource.setValidationQuery("select 1 from dual");
        dataSource.setTestOnBorrow(true);
        dataSource.setTestOnReturn(true);
        dataSource.setTestWhileIdle(true);
        dataSource.setTimeBetweenEvictionRunsMillis(1800000);
        dataSource.setNumTestsPerEvictionRun(10);
        dataSource.setMinEvictableIdleTimeMillis(1800000);
        dataSource.setInitialSize(10);
        dataSource.setMaxActive(10);
        dataSource.setMaxIdle(10);
        dataSource.setMinIdle(10);
        return dataSource;
    }


//	@Bean(name="oper",destroyMethod="close")
//    public DataSource oper() {
//
//
//        BasicDataSource dataSource = new BasicDataSource();
//
//        dataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//        dataSource.setUrl("jdbc:sqlserver://" + WebConfig.getInstance().DB_HOST + ":1433;DatabaseName=ADM");
//        dataSource.setUsername(WebConfig.getInstance().DB_ID);
//        dataSource.setPassword(WebConfig.getInstance().DB_PASS);
//        dataSource.setValidationQuery("select 'test'");
//        dataSource.setTestOnBorrow(true);
//        dataSource.setTestOnReturn(true);
//        dataSource.setTestWhileIdle(true);
//        dataSource.setTimeBetweenEvictionRunsMillis(1800000);
//        dataSource.setNumTestsPerEvictionRun(10);
//        dataSource.setMinEvictableIdleTimeMillis(1800000);
//        dataSource.setInitialSize(10);
//        dataSource.setMaxActive(10);
//        dataSource.setMaxIdle(10);
//        dataSource.setMinIdle(10);
//        return dataSource;
//    }
//
//
//
//	@Bean(name="board",destroyMethod="close")
//    public DataSource board() {
//
//        BasicDataSource dataSource = new BasicDataSource();
//
//        dataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//        dataSource.setUrl("jdbc:sqlserver://" + WebConfig.getInstance().DB_HOST_BOARD + ":1433;DatabaseName=ADM");
//        dataSource.setUsername(WebConfig.getInstance().DB_ID_BOARD);
//        dataSource.setPassword(WebConfig.getInstance().DB_PASS_BOARD);
//        dataSource.setValidationQuery("select 'test'");
//        dataSource.setTestOnBorrow(true);
//        dataSource.setTestOnReturn(true);
//        dataSource.setTestWhileIdle(true);
//        dataSource.setTimeBetweenEvictionRunsMillis(1800000);
//        dataSource.setNumTestsPerEvictionRun(10);
//        dataSource.setMinEvictableIdleTimeMillis(1800000);
//        dataSource.setInitialSize(10);
//        dataSource.setMaxActive(10);
//        dataSource.setMaxIdle(10);
//        dataSource.setMinIdle(10);
//        return dataSource;
//    }
   
}
