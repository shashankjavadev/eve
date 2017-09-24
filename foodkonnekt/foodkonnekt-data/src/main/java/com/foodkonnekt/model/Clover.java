package com.foodkonnekt.model;

public class Clover {

    private String authToken;
    private String instantUrl;
    private String url;
    // private String[] filters;
    private String merchantId;

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getInstantUrl() {
        return instantUrl;
    }

    public void setInstantUrl(String instantUrl) {
        this.instantUrl = instantUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }
}
