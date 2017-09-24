package com.foodkonnekt.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.http.ParseException;
import org.json.JSONObject;

import com.foodkonnekt.model.Address;
import com.foodkonnekt.model.Merchant;
import com.foodkonnekt.model.ModifierGroup;
import com.foodkonnekt.model.Pos;
import com.foodkonnekt.model.Role;
import com.foodkonnekt.model.TaxRates;
import com.foodkonnekt.model.Vendor;

public class FoodTronixJsonUtil {

    public static Merchant setFoodTronixCompany(String comapnyDetails) {
        Merchant merchant = new Merchant();
        Vendor vendor = new Vendor();
        Role role = new Role();
        try {
            JSONObject jObject = new JSONObject(comapnyDetails);
            JSONObject owner = jObject.getJSONObject("1");
            vendor.setName(owner.getString("mCompany"));
            vendor.setEmail("info@mkonnekt.com");
            role.setRoleName("Admin");
            vendor.setRole(role);

            Pos pos = new Pos();
            pos.setPosId(IConstant.POS_FOODTRONIX);
            vendor.setPos(pos);
            merchant.setName(owner.getString("mCompany"));
            merchant.setPhoneNumber(owner.getString("mPhoneNumber"));
            merchant.setPosMerchantId(Integer.toString(owner.getInt("mRestaurantID")));
            merchant.setAllowFutureOrder(IConstant.BOOLEAN_FALSE);
            merchant.setOwner(vendor);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return merchant;
    }

    public static Address setAddress(String addressJson, Merchant merchant) {
        JSONObject Object = new JSONObject(addressJson);
        JSONObject jObject = Object.getJSONObject("1");

        Address address = new Address();
        if (addressJson.contains("mAddress"))
            address.setAddress1(jObject.getString("mAddress"));
        if (addressJson.contains("address2"))
            address.setAddress2(jObject.getString("address2"));
        if (addressJson.contains("address3"))
            address.setAddress3(jObject.getString("address3"));
        if (addressJson.contains("mCity"))
            address.setCity(jObject.getString("mCity"));
        if (addressJson.contains("mState"))
            address.setState(jObject.getString("mState"));
        if (addressJson.contains("country"))
            address.setCountry(jObject.getString("country"));
        if (addressJson.contains("mZipCode"))
            address.setZip(jObject.getString("mZipCode"));
        address.setMerchant(merchant);
        return address;
    }

    public static List<TaxRates> setTaxRates(String taxRates, Merchant merchant) {
        JSONObject jObject = new JSONObject(taxRates);
        JSONObject taxJsonObject = jObject.getJSONObject("1");

        List<TaxRates> rates = new ArrayList<TaxRates>();

        if (taxJsonObject.toString().contains("mToGoTaxRate")) {
            TaxRates rate = new TaxRates();
            rate.setName("mToGoTaxRate");
            rate.setIsDefault(1);
            rate.setRate(taxJsonObject.getDouble("mToGoTaxRate"));
            rate.setMerchant(merchant);

            rates.add(rate);
        }
        if (taxJsonObject.toString().contains("mDineInTaxRate")) {
            TaxRates rate = new TaxRates();
            rate.setName("mDineInTaxRate");
            rate.setIsDefault(0);
            rate.setRate(taxJsonObject.getDouble("mDineInTaxRate"));
            rate.setMerchant(merchant);

            rates.add(rate);
        }
        if (taxJsonObject.toString().contains("mWaitingTaxRate")) {
            TaxRates rate = new TaxRates();
            rate.setName("mWaitingTaxRate");
            rate.setIsDefault(0);
            rate.setRate(taxJsonObject.getDouble("mWaitingTaxRate"));
            rate.setMerchant(merchant);

            rates.add(rate);
        }
        if (taxJsonObject.toString().contains("mDeliveryTaxRate")) {
            TaxRates rate = new TaxRates();
            rate.setName("mDeliveryTaxRate");
            rate.setIsDefault(0);
            rate.setRate(taxJsonObject.getDouble("mDeliveryTaxRate"));
            rate.setMerchant(merchant);

            rates.add(rate);
        }
        if (taxJsonObject.toString().contains("mDriveThruTaxRate")) {
            TaxRates rate = new TaxRates();
            rate.setName("mDriveThruTaxRate");
            rate.setIsDefault(0);
            rate.setRate(taxJsonObject.getDouble("mDriveThruTaxRate"));
            rate.setMerchant(merchant);

            rates.add(rate);
        }

        return rates;

    }

    public static List<ModifierGroup> setItemPropertyGroup(String itemPropertyGroupJson, Merchant merchant) {
        JSONObject Object = new JSONObject(itemPropertyGroupJson);
        Iterator keys = Object.keys();
        List<ModifierGroup> modifierGroupList = new ArrayList<ModifierGroup>();
        while (keys.hasNext()) {
            // loop to get the dynamic key
            String currentDynamicKey = (String) keys.next();

            // get the value of the dynamic key
            JSONObject currentDynamicValue = Object.getJSONObject(currentDynamicKey);
            ModifierGroup modifierGroup = new ModifierGroup();
            if (currentDynamicValue.toString().contains("mActive")) {
                if (currentDynamicValue.getBoolean("mActive")) {
                    modifierGroup.setShowByDefault(IConstant.BOOLEAN_TRUE);
                } else {
                    modifierGroup.setShowByDefault(IConstant.BOOLEAN_FALSE);
                }
            }
            if (itemPropertyGroupJson.contains("mItemPropertyGroupID"))
                modifierGroup.setPosModifierGroupId(Integer.toString(currentDynamicValue.getInt("mItemPropertyGroupID")));
            if (itemPropertyGroupJson.contains("mDescription"))
                modifierGroup.setName(currentDynamicValue.getString("mDescription"));

            modifierGroup.setMerchant(merchant);
            modifierGroupList.add(modifierGroup);
        }

        return modifierGroupList;
    }

}
