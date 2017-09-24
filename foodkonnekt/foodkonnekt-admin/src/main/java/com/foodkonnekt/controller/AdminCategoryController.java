package com.foodkonnekt.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.foodkonnekt.model.Category;
import com.foodkonnekt.service.CategoryService;
import com.foodkonnekt.util.IConstant;
import com.google.gson.Gson;

@Controller
public class AdminCategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * Find all category name
     * 
     * @return
     */
    @RequestMapping(value = "/AllCategory", method = RequestMethod.GET)
    public @ResponseBody Map<Object, Object> findCaegory(@RequestParam Integer merchantId) {
        Map<Object, Object> categoryMap = new HashMap<Object, Object>();
        List<Category> categories = categoryService.findAllCategory(merchantId);
        if (categories != null) {
            categoryMap.put(IConstant.RESPONSE, IConstant.RESPONSE_SUCCESS_MESSAGE);
            categoryMap.put(IConstant.DATA, categories);
        } else {
            categoryMap.put(IConstant.RESPONSE, IConstant.RESPONSE_NO_DATA_MESSAGE);
            categoryMap.put(IConstant.MESSAGE, IConstant.CATEGORY_FAILURE);
        }
        return categoryMap;
    }
    
    
    
    /**
     * Get all categories     * 
     * @return Map<Object, Object>
     */
    @RequestMapping(value = "getAllCategories", method = RequestMethod.GET)
    public @ResponseBody Map<Object, Object> getAllCategoriesByMerchantUId(@RequestParam("merchantUId") String merchantUId) {
    	System.out.println("getAllCategories");
        Map<Object, Object> allCategoriesMap = new HashMap<Object, Object>();
        List<Category> allCategories = categoryService.getAllCategoriesByMerchantUId(merchantUId);
        Gson gson = new Gson();
        String allCategoriesJson = gson.toJson(allCategories);
        if (!allCategories.isEmpty()) {
        	allCategoriesMap.put(IConstant.RESPONSE, IConstant.RESPONSE_SUCCESS_MESSAGE);
        	allCategoriesMap.put(IConstant.DATA, allCategoriesJson);
        } else {
        	allCategoriesMap.put(IConstant.RESPONSE, IConstant.RESPONSE_NO_DATA_MESSAGE);
        	allCategoriesMap.put(IConstant.DATA, allCategoriesJson);
        }
        return allCategoriesMap;
    }   
    
    
    /**
     * Get all categories     * 
     * @return Map<Object, Object>
     */
    @RequestMapping(value = "getAllCategoriesByVendorUId", method = RequestMethod.GET)
    public @ResponseBody Map<Object, Object> getAllCategoriesByVendorUId(@RequestParam("vendorUId") String vendorUId,HttpServletResponse response) {
        Map<Object, Object> allCategoriesMap = new HashMap<Object, Object>();
        List<Category> allCategories = categoryService.getAllCategoriesByVendorUId(vendorUId);
        Gson gson = new Gson();
        String eventTypesJson = gson.toJson(allCategories);
        if (!allCategories.isEmpty()) {
        	allCategoriesMap.put(IConstant.RESPONSE, IConstant.RESPONSE_SUCCESS_MESSAGE);
        	allCategoriesMap.put(IConstant.DATA, eventTypesJson);
        } else {
        	allCategoriesMap.put(IConstant.RESPONSE, IConstant.RESPONSE_NO_DATA_MESSAGE);
        	allCategoriesMap.put(IConstant.DATA, eventTypesJson);
        }
        return allCategoriesMap;
    }   
    
}
