package com.foodkonnekt.model;

import com.foodkonnekt.clover.vo.MerchantVo;


public class CommonMail {

    private String fromEmail;
    private String toEmail;
    private String subject;
    private String body;
    private String password;
    private String verificationCode;
    private String appId;
    private MerchantVo merchants;

    public String getFromEmail() {
        return fromEmail;
    }

    public void setFromEmail(String fromEmail) {
        this.fromEmail = fromEmail;
    }

    public String getToEmail() {
        return toEmail;
    }

    public void setToEmail(String toEmail) {
        this.toEmail = toEmail;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public MerchantVo getMerchants() {
        return merchants;
    }

    public void setMerchants(MerchantVo merchants) {
        this.merchants = merchants;
    }
}
