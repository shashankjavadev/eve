package com.foodkonnekt.util;

import org.apache.http.client.methods.HttpGet;

import com.foodkonnekt.model.Merchant;

public class InventoryUrlUtil {

    /**
     * Get lineItem with expand categories,modifierGroups and taxRates
     * 
     * @param merchant
     * @param lineItemId
     * @return String
     */
    public static String getLineItemWithExpands(Merchant merchant, String lineItemId) {
        HttpGet request = new HttpGet(IConstant.CLOVER_URL + IConstant.CLOVER_INSTANCE_URL
                        + merchant.getPosMerchantId() + "/items/" + lineItemId
                        + "?expand=categories,modifierGroups,taxRates&access_token=" + merchant.getAccessToken());
        return CloverUrlUtil.convertToStringJson(request);
    }

    /**
     * Get modifiers by modifierGroupsId
     * 
     * @param merchant
     * @param posModifierGroupId
     * @return String
     */
    public static String getModiferFromClover(Merchant merchant, String posModifierGroupId) {
        HttpGet request = new HttpGet(IConstant.CLOVER_URL + IConstant.CLOVER_INSTANCE_URL
                        + merchant.getPosMerchantId() + "/modifier_groups/" + posModifierGroupId
                        + "?expand=modifiers&access_token=" + merchant.getAccessToken());
        return CloverUrlUtil.convertToStringJson(request);
    }

    /**
     * check modiferGroup exist or not in clover
     * 
     * @param merchant
     * @param posId
     * @return String
     */
    public static String checkModiferGroup(Merchant merchant, String posModifierGroupId) {
        HttpGet request = new HttpGet(IConstant.CLOVER_URL + IConstant.CLOVER_INSTANCE_URL
                        + merchant.getPosMerchantId() + "/modifier_groups/" + posModifierGroupId + "?access_token="
                        + merchant.getAccessToken());
        return CloverUrlUtil.convertToStringJson(request);
    }
    
    public static String checkCategory(Merchant merchant, String posCategoryId) {
        HttpGet request = new HttpGet(IConstant.CLOVER_URL + IConstant.CLOVER_INSTANCE_URL
                        + merchant.getPosMerchantId() + "/categories/" + posCategoryId + "?access_token="
                        + merchant.getAccessToken());
        return CloverUrlUtil.convertToStringJson(request);
    }
}
