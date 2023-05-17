package com.web.config.init;


import com.web.config.service.CmeProperties;

public class WebConfig {

    private WebConfig() { }

    private static class holder {
        public static final WebConfig singleton = new WebConfig();
    }

    public static WebConfig getInstance() {
        return holder.singleton;
    }

    // API ROOT
    public String API_URL = "";

    // PC Page URL
    public String MOBILE_URL = "";

    public String DB_HOST;
    public String DB_ID;
    public String DB_PASS;
    public String DB_SERVICE;

    public String DB_HOST_BOARD;
    public String DB_ID_BOARD;
    public String DB_PASS_BOARD;
    public String DB_SERVICE_BOARD;

    public String DB_PORT;

    public String MAIL_FROM = CmeProperties.getProperty("MAIL.ID");
    public String MAIL_HOST = CmeProperties.getProperty("MAIL.HOST");
    public String MAIL_PASS = CmeProperties.getProperty("MAIl.PWD");


}
