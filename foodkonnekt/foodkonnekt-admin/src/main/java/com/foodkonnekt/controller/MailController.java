package com.foodkonnekt.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.foodkonnekt.model.CommonMail;
import com.foodkonnekt.util.IConstant;
import com.foodkonnekt.util.MailSendUtil;

@Controller
public class MailController {

    @RequestMapping(value = "/sendMail", method = RequestMethod.POST)
    public @ResponseBody Map<Object, Object> save(@RequestBody CommonMail commonMail, HttpServletResponse response) {
       
    	 Map<Object, Object> mailResponse = new HashMap<Object, Object>();
    	 try{
        if (null != commonMail.getFromEmail() && null != commonMail.getToEmail()) {
            MailSendUtil.sendMail(commonMail);
            mailResponse.put(IConstant.RESPONSE, IConstant.RESPONSE_SUCCESS_MESSAGE);
            mailResponse.put(IConstant.MESSAGE, IConstant.MAIL_SUCCESS);
        } else {
            mailResponse.put(IConstant.RESPONSE, IConstant.RESPONSE_NO_DATA_MESSAGE);
            mailResponse.put(IConstant.MESSAGE, IConstant.MAIL_FAILURE);
        }
        } catch (Exception e) {
            if (e != null) {
                MailSendUtil.sendExceptionByMail(e);
            }
            e.printStackTrace();
        }
        return mailResponse;
    }
}
