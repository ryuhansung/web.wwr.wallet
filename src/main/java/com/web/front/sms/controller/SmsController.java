package com.web.front.sms.controller;

import com.web.config.CmeResultVO;
import com.web.config.util.ComUtil;
import com.web.front.sms.vo.SmsInfoVO;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value="/front/sms")
public class SmsController {

    @RequestMapping(value="/sendSms.dm")
    public @ResponseBody
    JSONObject sendSms(@ModelAttribute SmsInfoVO vo, HttpServletRequest request, HttpServletResponse response) throws Exception {
        JSONObject resObj = ComUtil.apiJsonObj("/sms.send.dp/proc.go", vo);

        return resObj;
    }
}
