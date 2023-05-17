package com.web.front.danal.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public interface DanalAuthService {

    public Map reqAuth(HttpServletRequest request, HttpServletResponse response, HashMap<String, String> param) throws Exception;

    public Map CPCGI(HttpServletRequest request, HttpServletResponse response) throws Exception;

}
