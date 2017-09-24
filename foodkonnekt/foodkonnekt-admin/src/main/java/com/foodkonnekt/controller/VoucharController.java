package com.foodkonnekt.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.foodkonnekt.model.Merchant;
import com.foodkonnekt.model.Vouchar;
import com.foodkonnekt.service.VoucharService;
import com.foodkonnekt.util.DateUtil;
import com.foodkonnekt.util.MailSendUtil;
import com.foodkonnekt.util.UrlConstant;

@Controller
public class VoucharController {

    @Autowired
    private VoucharService voucharService;

    /**
     * Open createVouchar page
     * 
     * @param vouchar
     * @param model
     * @return String
     */
    @RequestMapping(value = "/createVouchar", method = RequestMethod.GET)
    public String viewCreateVouchar(@ModelAttribute("Vouchar") Vouchar vouchar, ModelMap model) {
    	
        return "createVouchar";
    }

    /**
     * Save vouchars
     * 
     * @param vouchar
     * @param model
     * @return String
     */
    @RequestMapping(value = "/addVouchar", method = { RequestMethod.POST })
    public String saveVouchar(@ModelAttribute("Vouchar") Vouchar vouchar, ModelMap model, HttpServletRequest request) {
        try {
            HttpSession session = request.getSession(false);
            if (session != null) {
                Merchant merchant = (Merchant) session.getAttribute("merchant");
                if (merchant != null) {
                    String fromDate = request.getParameter("fromD");
                    String endDate = request.getParameter("endD");
                    if (fromDate != null) {
                        if (!fromDate.isEmpty()) {
                            vouchar.setFromDate(DateUtil.convertStringToDate(fromDate));
                        }
                    }
                    if (endDate != null) {
                        if (!endDate.isEmpty()) {
                            vouchar.setEndDate(DateUtil.convertStringToDate(endDate));
                        }
                    }
                    vouchar.setMerchant(merchant);
                    voucharService.save(vouchar);
                } else {
                	return  "redirect:https://www.foodkonnekt.com";
                }
            } else {
            	return "redirect:https://www.foodkonnekt.com";
            }
        } catch (Exception e) {
            if (e != null) {
                MailSendUtil.sendExceptionByMail(e);
            
            e.printStackTrace();
            return "redirect:https://www.foodkonnekt.com"; 
            }
        }
        return "redirect:"+UrlConstant.BASE_URL+"/vouchars";
    }
    
    @RequestMapping(value = "/checkCouponName", method = RequestMethod.GET)
    @ResponseBody
    public String checkCouponName(@RequestParam(required = true) String couponCode,@RequestParam(required = false) Integer couponId,HttpServletRequest request) {
    	
    	try {
            HttpSession session = request.getSession(false);
            Merchant merchant=null;
            if(session!=null)
             merchant = (Merchant) session.getAttribute("merchant");
            if(merchant!=null){
            	if(couponId==null){
            	return voucharService.findByMerchantIdAndCouponCode(merchant.getId(),couponCode);
            	}else{
            		return voucharService.findByMerchantIdAndCouponCodeAndCouponId(merchant.getId(),couponCode,couponId);
            	}
            }else{
            	return "redirect:https://www.foodkonnekt.com";	
            }
        } 
        catch (Exception e) {
            if (e != null) {
                MailSendUtil.sendExceptionByMail(e);
            
            e.printStackTrace();
            
            }
            return "redirect:https://www.foodkonnekt.com";
        }
  
       
    }

    @RequestMapping(value = "/editVouchar", method = RequestMethod.GET)
    public String viewCustomerOrders(@ModelAttribute("Vouchar") Vouchar vouchar, ModelMap model,
                    HttpServletRequest request, @RequestParam(required = false) int voucharId) {
    	try{
        Vouchar voucharDetail = voucharService.findById(voucharId);
        model.addAttribute("validity", voucharDetail.getValidity());
        model.addAttribute("Vouchar", voucharDetail);
    	 } catch (Exception e) {
             if (e != null) {
                 MailSendUtil.sendExceptionByMail(e);
             
             e.printStackTrace();
             return "redirect:https://www.foodkonnekt.com";
             }
         }
        return "updateVouchar";
    }

    /**
     * View all merchant mouchars
     * 
     * @param model
     * @return String
     */
    @RequestMapping(value = "/vouchars", method = RequestMethod.GET)
    public String viewVouchars(ModelMap model, HttpServletRequest request) {
        try {
            HttpSession session = request.getSession(false);
            if (session != null) {
                Merchant merchant = (Merchant) session.getAttribute("merchant");
                if (merchant != null) {
                    model.addAttribute("vouchars", voucharService.findByMerchantId(merchant.getId()));
                } else {
                	return  "redirect:https://www.foodkonnekt.com";
                }
            } else {
            	return "redirect:https://www.foodkonnekt.com";
            }
        } catch (Exception e) {
            if (e != null) {
                MailSendUtil.sendExceptionByMail(e);
            
            e.printStackTrace();
            return "redirect:https://www.foodkonnekt.com";
            }
        }
        return "vouchars";
    }
}
