package com.foodkonnekt.service;

import com.foodkonnekt.model.Merchant;

public interface WebhookAppService {
	
	void appUnInstall(String merchantId);
	
	public void saveTaxRate(String taxRates, Merchant merchant);

}
