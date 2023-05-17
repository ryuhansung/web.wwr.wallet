package com.web.config.init;


import com.web.config.service.CmeProperties;
import com.web.config.util.ComUtil;
import org.springframework.web.WebApplicationInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Class Name : WebInitializer
 * @작성일 : 2018. 05. 08.
 * @작성자 : (주)씨엠이소프트 민병철
 * @변경이력 :
 * @Method 설명 : 서버 시작시 초기화가 필요한 로직 처리
 * @throws
 */
public class WebInitializer implements WebApplicationInitializer {

    /**
     * @throws
     * @Method Name : onStartUp
     * @작성일 : 2018. 05. 08.
     * @작성자 : (주)씨엠이소프트 민병철
     * @변경이력 :
     * @Method 설명 : 메세지 프로퍼티 검사
     */
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        boolean operYnChk = false;
        String Active = CmeProperties.getProperty("SERVER.MODE");
        if ("run".equals(Active)) {
            operYnChk = true;
        } else {
            operYnChk = false;
        }

        System.out.println("now oper mode : " + operYnChk);
        Active = Active.toUpperCase();
        String DB_IP = CmeProperties.getProperty("DB." + Active + ".IP"); // 기준 DB IP
        String DB_BOARD_IP = CmeProperties.getProperty("DB." + Active + ".BOARD.IP"); // 게시판 DB IP
        //String FTP_IP = CmeProperties.getProperty("FTP."+Active+".IP");; // FTP IP
        String DB_SVC_NM = CmeProperties.getProperty("DB." + Active + ".SVC");
        ;
        String DB_PORT = CmeProperties.getProperty("DB." + Active + ".PORT");
        String API_URL = CmeProperties.getProperty("API." + Active + ".URL");

        System.out.println("DB_IP:" + DB_IP);
        System.out.println("DB_BOARD_IP:" + DB_BOARD_IP);
        System.out.println("DB_SVC_NM:" + DB_SVC_NM);
        System.out.println("DB_PORT:" + DB_PORT);
        System.out.println("API_URL:" + API_URL);

        WebConfig.getInstance().API_URL = API_URL;

        WebConfig.getInstance().DB_HOST = DB_IP;
        WebConfig.getInstance().DB_SERVICE = DB_SVC_NM;
        WebConfig.getInstance().DB_PORT = DB_PORT;
        WebConfig.getInstance().DB_ID = "WSC";
        WebConfig.getInstance().DB_PASS = "WSC3838!";

        //190604 임승연 미정
        WebConfig.getInstance().DB_HOST_BOARD = DB_BOARD_IP;
        WebConfig.getInstance().DB_SERVICE_BOARD = DB_SVC_NM;
        WebConfig.getInstance().DB_ID_BOARD = "WSCBOARD";
        WebConfig.getInstance().DB_PASS_BOARD = "WSC3838!";

    }

    private String getLocalServerIp() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress() && !inetAddress.isLinkLocalAddress() && inetAddress.isSiteLocalAddress()) {
                        return inetAddress.getHostAddress().toString();
                    }
                }
            }
        } catch (SocketException ex) {
        }
        return null;
    }


    static int totalCnt = 0;

    public static void checkMessage(ServletContext servletContext) {
        System.out.println("Message Properties 검사 시작 =====================");

        ArrayList<String> res = new ArrayList<String>();

        _checkMessage(servletContext.getRealPath("/") + "WEB-INF/jsp", servletContext, res);

        System.out.println("검사한 메세지의 수 : " + totalCnt);
        System.out.println("오류가 확인된 메시지의 수 : " + res.size());
        for (int i = 0; i < res.size(); i++) {
            System.out.println(res.get(i));
        }

        System.out.println("Message Properties 검사 종료 =====================");
    }

    private static ArrayList<String> _checkMessage(String path, ServletContext servletContext, ArrayList<String> res) {

        File dir = new File(path);

        File[] fileList = dir.listFiles();

        try {

            for (int i = 0; i < fileList.length; i++) {

                File file = fileList[i];

                if (file.isFile()) {

                    if (file.getName().endsWith(".jsp")) {

                        //System.out.println("\t 검사 = " + file.getPath() + file.getName());

                        ArrayList<String> __res = __checkMessage(file, servletContext);

                        for (int j = 0; j < __res.size(); j++) {

                            res.add(__res.get(j));

                        }

                    }


                } else if (file.isDirectory()) {

                    _checkMessage(file.getCanonicalPath().toString(), servletContext, res);

                }

            }

        } catch (IOException e) {

        }

        return res;

    }

    private static ArrayList<String> __checkMessage(File file, ServletContext servletContext) {

        ArrayList<String> res = new ArrayList<String>();

        try {

            BufferedReader leftBf = new BufferedReader(new FileReader(file));

            String lLine = "";

            Pattern pattern = Pattern.compile("(code=\")[.a-zA-Z0-9_*]*(\")");

            while ((lLine = leftBf.readLine()) != null) {

                Matcher matcher = pattern.matcher(lLine);
                while (matcher.find()) {
                    String target = matcher.group(0);
                    totalCnt++;

                    target = target.substring(6, target.length() - 1);

                    //System.out.println("message code >> " + target);

                    File dir = new File(servletContext.getRealPath("/") + "WEB-INF/classes/message/com");

                    File[] fileList = dir.listFiles();

                    for (int i = 0; i < fileList.length; i++) {

                        if (fileList[i].getName().endsWith(".DS_Store")) {
                            continue;
                        }

                        BufferedReader bf = new BufferedReader(new FileReader(fileList[i]));
                        String rLine = "";
                        boolean isFind = false;
                        while ((rLine = bf.readLine()) != null) {

                            if (rLine.contains("=")) {

                                String[] tokens = rLine.split("=");
                                if (tokens[0].trim().equals(target.trim())) {
                                    isFind = true;
                                    break;
                                }

                            }

                        }

                        if (!isFind) {
                            //        System.out.println("Error! can't find message >> " + target + " in " + fileList[i].getName());
                            res.add(file.getName() + " || " + target + " >> " + fileList[i].getName());
                        }

                    }
                }

            }


        } catch (IOException e) {


        }

        return res;

    }

}
