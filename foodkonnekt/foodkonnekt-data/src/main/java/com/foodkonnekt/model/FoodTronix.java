package com.foodkonnekt.model;

public class FoodTronix {

    private String authToken;
    private String instantUrl;
    private String url;
    // private String[] filters;
    private String merchantId;
   
    private String storeId;
    
    private String companyId;

    public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

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
