package com.foodkonnekt.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.foodkonnekt.model.Address;
import com.foodkonnekt.model.ConvenienceFee;
import com.foodkonnekt.model.Merchant;
import com.foodkonnekt.model.PickUpTime;
import com.foodkonnekt.model.Zone;
import com.foodkonnekt.service.CategoryService;
import com.foodkonnekt.service.ItemService;
import com.foodkonnekt.service.MerchantService;
import com.foodkonnekt.service.ModifierService;
import com.foodkonnekt.service.ZoneService;
import com.foodkonnekt.util.IConstant;
import com.foodkonnekt.util.MailSendUtil;
import com.foodkonnekt.util.UrlConstant;

@Controller
public class ConfigurationController {

    @Autowired
    private MerchantService merchantService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private ModifierService modifierService;

    @Autowired
    private ZoneService zoneService;

    /**
     * Get all inventory count from database
     * 
     * @param model
     * @param response
     * @param request
     * @return String
     */
    
    @RequestMapping(value = "/setup", method = RequestMethod.GET)
    @ResponseBody
    public Map<Object, Object> setup(ModelMap model, HttpServletResponse response, HttpServletRequest request) {
    	Map<Object, Object> inventoryCountMap = new HashMap<Object, Object>();
    	try {
            HttpSession session = request.getSession();
            String merchantId = (String) session.getAttribute("merchantId");
            System.out.println("----------Configuration controller-----merchanid--t" + merchantId);
            Merchant merchant = merchantService.findbyPosId(merchantId);
            System.out.println("---merchant---" + merchant);
            if (merchant != null) {
                
                Long categoryCount = categoryService.categoryCountByMerchantId(merchant.getId());
                Long itemCount = itemService.itemCountByMerchantId(merchant.getId());
                Long modifierGroupCount = modifierService.modifierGroupCountByMerchantId(merchant.getId());
                Long modifierCount = modifierService.modifierCountByMerchantId(merchant.getId());
               
                inventoryCountMap.put("categoryCount", categoryCount);
                inventoryCountMap.put("itemCount", itemCount);
                inventoryCountMap.put("modifierGroupCount", modifierGroupCount);
                inventoryCountMap.put("modifierCount", modifierCount);
                
                Integer inventoryThreadStatus= (Integer)session.getAttribute("inventoryThread");
                inventoryCountMap.put("inventoryThreadStatus", inventoryThreadStatus);
                session.setAttribute("merchant", merchant);
                // session.setMaxInactiveInterval(60);
            }
        } catch (Exception e) {
            if (e != null) {
                MailSendUtil.sendExceptionByMail(e);

                e.printStackTrace();
                return null;
            }
        }
        return inventoryCountMap;
    }
    
    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public String viewAdminHome(ModelMap model, HttpServletResponse response, HttpServletRequest request) {
    	Integer inventoryThreadStatus=0;
    	try {
            HttpSession session = request.getSession();
            String merchantId = (String) session.getAttribute("merchantId");
            System.out.println("----------Configuration controller-----merchanid--t" + merchantId);
            Merchant merchant = merchantService.findbyPosId(merchantId);
            System.out.println("---merchant---" + merchant);
            if (merchant != null) {
                List<Address> address = merchantService.findAddressByMerchantId(merchant.getId());
                String merchantLocation = address.get(0).getAddress1() + " " + address.get(0).getAddress2() + " "
                                + address.get(0).getAddress3() + " " + address.get(0).getCity() + " "
                                + address.get(0).getCountry() + "-" + address.get(0).getZip();
                
                /*merchant.setIsInstall(IConstant.BOOLEAN_FALSE);
                merchantService.save(merchant);*/
                model.addAttribute("phoneNumber", merchant.getPhoneNumber());
                model.addAttribute("email", merchant.getOwner().getEmail());
                model.addAttribute("address", merchantLocation);
                
                session.setAttribute("merchant", merchant);
                // session.setMaxInactiveInterval(60);
            }
            if(session.getAttribute("inventoryThread")!=null)
             inventoryThreadStatus= (Integer)session.getAttribute("inventoryThread");
            
        } catch (Exception e) {
            if (e != null) {
                MailSendUtil.sendExceptionByMail(e);

                e.printStackTrace();
                return "redirect:https://www.foodkonnekt.com";
            }
        }
        if(inventoryThreadStatus==1){
        	return "welcome";
        }else{
        	return "adminPanel";
        }
        
    }
    
    /*@RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public String viewAdminHome(ModelMap model, HttpServletResponse response, HttpServletRequest request) {
        try {
            HttpSession session = request.getSession();
            String merchantId = (String) session.getAttribute("merchantId");
            System.out.println("----------Configuration controller-----merchanid--t" + merchantId);
            Merchant merchant = merchantService.findbyPosId(merchantId);
            System.out.println("---merchant---" + merchant);
            if (merchant != null) {
                List<Address> address = merchantService.findAddressByMerchantId(merchant.getId());
                String merchantLocation = address.get(0).getAddress1() + " " + address.get(0).getAddress2() + " "
                                + address.get(0).getAddress3() + " " + address.get(0).getCity() + " "
                                + address.get(0).getCountry() + "-" + address.get(0).getZip();
                Long categoryCount = categoryService.categoryCountByMerchantId(merchant.getId());
                Long itemCount = itemService.itemCountByMerchantId(merchant.getId());
                Long modifierGroupCount = modifierService.modifierGroupCountByMerchantId(merchant.getId());
                Long modifierCount = modifierService.modifierCountByMerchantId(merchant.getId());
                merchant.setIsInstall(IConstant.BOOLEAN_FALSE);
                merchantService.save(merchant);
                model.addAttribute("phoneNumber", merchant.getPhoneNumber());
                model.addAttribute("email", merchant.getOwner().getEmail());
                model.addAttribute("address", merchantLocation);
                model.addAttribute("categoryCount", categoryCount);
                model.addAttribute("itemCount", itemCount);
                model.addAttribute("modifierGroupCount", modifierGroupCount);
                model.addAttribute("modifierCount", modifierCount);
                session.setAttribute("merchant", merchant);
                // session.setMaxInactiveInterval(60);
            }
        } catch (Exception e) {
            if (e != null) {
                MailSendUtil.sendExceptionByMail(e);

                e.printStackTrace();
                return "redirect:https://www.foodkonnekt.com";
            }
        }
        return "welcome";
    }*/

    /**
     * Upload Logo of merchant
     * 
     * @param model
     * @return String
     */
    @RequestMapping(value = "/uploadLogo", method = RequestMethod.GET)
    public String uploadLogo(ModelMap model) {
        return "uploadLogo";
    }

    @SuppressWarnings("unused")
    @RequestMapping(value = "/saveLogo", method = RequestMethod.POST)
    public String save(ModelMap model, HttpServletResponse response, HttpServletRequest request,
                    @RequestParam("file") MultipartFile file) {
        try {
            HttpSession session = request.getSession();
            // String image = ImageUploadUtils.getImage(file);
            Merchant merchant = (Merchant) session.getAttribute("merchant");
            Long size = file.getSize();
            String orgName = file.getOriginalFilename();
            if(orgName==null || orgName.isEmpty() || orgName.equals("")){
            	model.addAttribute("filesize", "Please select the logo");
                return "uploadLogo";
            }
            if (size >= 2097152) {
                model.addAttribute("filesize", "Maximum Allowed File Size is 2 MB");
                return "uploadLogo";
            }

           

            String exts[] = orgName.split("\\.");
                if(exts.length>0){
            String ext = exts[1];
                
            String logoName = merchant.getId() + "_" + merchant.getName().trim() + "." + ext;
            String filePath = UrlConstant.ADMIN_SERVER_LOGO_PATH + logoName;
            File dest = new File(filePath);
            try {
                file.transferTo(dest);
            } catch (IllegalStateException e) {
                e.printStackTrace();
                return "File uploaded failed:" + orgName;
            } catch (IOException e) {
                e.printStackTrace();
                return "File uploaded failed:" + orgName;
            }
                
            System.out.println("File uploaded:" + orgName);

            if (merchant != null) {
                merchant.setMerchantLogo(UrlConstant.ADMIN_LOGO_PATH_TO_SHOW + logoName);
                merchantService.save(merchant);
                session.setAttribute("merchant", merchant);
                model.addAttribute("imagePath", UrlConstant.BASE_PORT + merchant.getMerchantLogo());
            } else {
                return "redirect:https://www.foodkonnekt.com";
            }
                }
        } catch (Exception e) {
            if (e != null) {
                MailSendUtil.sendExceptionByMail(e);

                e.printStackTrace();
                return "redirect:https://www.foodkonnekt.com";
            }
        }
        String status = request.getParameter("admin");
        if ("admin".equals(status)) {
            return "redirect:"+UrlConstant.BASE_URL+"/adminLogo";
        } else {
            /* return "redirect:"+UrlConstant.BASE_URL+"/setDeliveryZone"; */
            return "uploadLogo";
        }
    }

    /**
     * Set delivery zone
     * 
     * @param model
     * @return String
     */
    @RequestMapping(value = "/setDeliveryZone", method = RequestMethod.GET)
    public String setDeliveryZone(@ModelAttribute("Zone") Zone zone, ModelMap model, HttpServletRequest request,
                    @RequestParam(required = false) String saveStatus) {
        try {
            HttpSession session = request.getSession(false);
            if (session != null) {
                Merchant merchant = (Merchant) session.getAttribute("merchant");
                if (merchant != null) {
                    List<Address> addresses = merchantService.findAddressByMerchantId(merchant.getId());
                    if (!addresses.isEmpty()) {
                        Address address = addresses.get(0);
                        String address1= "";
                        if(address.getAddress1()!=null){
                        	address1=address.getAddress1();
                        };
                        String address2= "";
                        if(address.getAddress2()!=null){
                        	address2=address.getAddress2();
                        };
                        String address3= "";
                        if(address.getAddress3()!=null){
                        	address3=address.getAddress3();
                        };
                        model.addAttribute("address",
                        		address1 + " " + address2 + " " + address3
                                                        + " " + address.getCity() + " " + address.getCountry() + "-"
                                                        + address.getZip());
                        model.addAttribute("id", address.getId());
                    }

                } else {
                    return "redirect:https://www.foodkonnekt.com";
                }
            }
        } catch (Exception e) {
            if (e != null) {
                MailSendUtil.sendExceptionByMail(e);

                e.printStackTrace();
                return "redirect:https://www.foodkonnekt.com";
            }
        }
        model.addAttribute("saveStatus", saveStatus);
        return "setDeliveryZone";
    }
    
   

    /**
     * Save delivery zone
     * 
     * @param zone
     * @param model
     * @param response
     * @param request
     * @return String
     */
    @RequestMapping(value = "/saveDeliveryZone", method = RequestMethod.POST)
    public String saveDeliveryZone(@ModelAttribute("Zone") Zone zone, ModelMap model, HttpServletResponse response,
                    HttpServletRequest request) {
        try {
            HttpSession session = request.getSession(false);
            Merchant merchant=null;
            if(session!=null)
             merchant = (Merchant) session.getAttribute("merchant");
            if (merchant != null) {
                zoneService.save(zone, merchant);
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
        String status = request.getParameter("admin");
        if ("admin".equals(status)) {
            return "redirect:"+UrlConstant.BASE_URL+"/deliveryZones";
        } else {
            // return "redirect:"+UrlConstant.BASE_URL+"/setPickupTime";
            model.addAttribute("saveStatus", "yes");
            return "redirect:"+UrlConstant.BASE_URL+"/setDeliveryZone";
        }
    }

    @RequestMapping(value = "/checkDeliveryZoneName", method = RequestMethod.GET)
    @ResponseBody
    public String checkDeliveryZoneName(@RequestParam(required = true) String diliveryZoneName,
                    @RequestParam(required = false) Integer diliveryZoneId, HttpServletRequest request) {

        try {
            HttpSession session = request.getSession(false);
            Merchant merchant=null;
            if(session!=null)
             merchant = (Merchant) session.getAttribute("merchant");
            if (merchant != null) {
                if (diliveryZoneId == null) {
                    return zoneService.findByMerchantIdAndDeliveryZoneName(merchant.getId(), diliveryZoneName);
                } else {
                    return zoneService.findByMerchantIdAndDeliveryZoneNameAndZoneId(merchant.getId(), diliveryZoneName,
                                    diliveryZoneId);
                }
            } else {
                return "redirect:https://www.foodkonnekt.com";
            }
        } catch (Exception e) {
            if (e != null) {
                MailSendUtil.sendExceptionByMail(e);

                e.printStackTrace();

            }
            return "redirect:https://www.foodkonnekt.com";
        }

    }

    /**
     * Show set pickup time page
     * 
     * @param pickUpTime
     * @param model
     * @param request
     * @return String
     */
    @RequestMapping(value = "/setPickupTime", method = RequestMethod.GET)
    public String setPickUpTime(@ModelAttribute("PickUpTime") PickUpTime pickUpTime, ModelMap model,
                    HttpServletRequest request) {
        try {
            HttpSession session = request.getSession(false);
            Merchant merchant=null;
            if(session!=null)
             merchant = (Merchant) session.getAttribute("merchant");
            if (merchant != null) {
                List<Address> addresses = merchantService.findAddressByMerchantId(merchant.getId());
                if (!addresses.isEmpty()) {
                    Address address = addresses.get(0);
                    String address1= "";
                    if(address.getAddress1()!=null){
                    	address1=address.getAddress1();
                    };
                    String address2= "";
                    if(address.getAddress2()!=null){
                    	address2=address.getAddress2();
                    };
                    String address3= "";
                    if(address.getAddress3()!=null){
                    	address3=address.getAddress3();
                    };
                    model.addAttribute("address",
                    		address1 + " " + address2 + " " + address3
                                                    + " " + address.getCity() + " " + address.getCountry() + "-"
                                                    + address.getZip());
                    model.addAttribute("id", address.getId());
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
        return "setPickupTime";
    }

    /**
     * Save pickup time
     * 
     * @param pickUpTime
     * @param model
     * @param request
     * @return String
     */
    @RequestMapping(value = "/savePickupTime", method = RequestMethod.POST)
    public String savePickUpTime(@ModelAttribute("PickUpTime") PickUpTime pickUpTime, ModelMap model,
                    HttpServletRequest request) {
        try {
            HttpSession session = request.getSession(false);
            Merchant merchant=null;
            if(session!=null)
             merchant = (Merchant) session.getAttribute("merchant");
            if (merchant != null) {
                pickUpTime.setMerchantId(merchant.getId());
                merchantService.savePickupTime(pickUpTime);
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
        String status = request.getParameter("admin");
        if ("admin".equals(status)) {
            return "redirect:"+UrlConstant.BASE_URL+"/pickUpTime";
        } else {
            model.addAttribute("saveStatus", "yes");
            // return "redirect:"+UrlConstant.BASE_URL+"/setConvenienceFee";
            return "setPickupTime";
        }
    }

    /**
     * Show set convenience fee page
     * 
     * @param model
     * @return String
     */
    @RequestMapping(value = "/setConvenienceFee", method = RequestMethod.GET)
    public String setConvenienceFee(@ModelAttribute("ConvenienceFee") ConvenienceFee convenienceFee, ModelMap model,
                    HttpServletRequest request) {
        try {
            HttpSession session = request.getSession(false);
            Merchant merchant=null;
            if(session!=null)
             merchant = (Merchant) session.getAttribute("merchant");
            if (merchant != null) {
                List<Address> addresses = merchantService.findAddressByMerchantId(merchant.getId());
                if (!addresses.isEmpty()) {
                    Address address = addresses.get(0);
                    
                    String address1= "";
                    if(address.getAddress1()!=null){
                    	address1=address.getAddress1();
                    };
                    String address2= "";
                    if(address.getAddress2()!=null){
                    	address2=address.getAddress2();
                    };
                    String address3= "";
                    if(address.getAddress3()!=null){
                    	address3=address.getAddress3();
                    };
                    model.addAttribute("address",
                    		address1 + " " + address2 + " " + address3
                                                    + " " + address.getCity() + " " + address.getCountry() + "-"
                                                    + address.getZip());
                    model.addAttribute("id", address.getId());
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
        return "setConvenienceFee";
    }

    /**
     * Save conveninece fee
     * 
     * @param convenienceFee
     * @param model
     * @param request
     * @return String
     */
    @RequestMapping(value = "/saveConvenienceFee", method = RequestMethod.POST)
    public String saveConvenienceFee(@ModelAttribute("ConvenienceFee") ConvenienceFee convenienceFee, ModelMap model,
                    HttpServletRequest request) {
        try {
            HttpSession session = request.getSession(false);
            Merchant merchant=null;
            if(session!=null)
             merchant = (Merchant) session.getAttribute("merchant");
            if (merchant != null) {
                convenienceFee.setMerchantId(merchant.getId());
                merchantService.saveConvenienceFee(convenienceFee, merchant);
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
        String status = request.getParameter("admin");
        if ("admin".equals(status)) {
            return "redirect:"+UrlConstant.BASE_URL+"/convenienceFee";
        } else {
            model.addAttribute("saveStatus", "yes");
            // return "redirect:"+UrlConstant.BASE_URL+"/adminPanel";
            return "setConvenienceFee";
        }
    }

    /**
     * Show admin panel page
     * 
     * @param model
     * @return String
     */
    @RequestMapping(value = "/adminPanel", method = RequestMethod.GET)
    public String setAdminPanel(ModelMap model,HttpServletRequest request) {
    	HttpSession session = request.getSession(false);
    	int inventoryThreadStatus=0;
    	if(session!=null){
    		if(session.getAttribute("inventoryThread")!=null)
    	 inventoryThreadStatus= (Integer)session.getAttribute("inventoryThread");
    	    	}
    	model.addAttribute("inventoryThreadStatus", inventoryThreadStatus);
        return "adminPanel";
    }

    @RequestMapping(value = "/logOut", method = RequestMethod.GET)
    public void logOutRedirect(ModelMap model, HttpServletResponse response) {
        try {
            response.sendRedirect("https://www.foodkonnekt.com");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/adminLogo", method = RequestMethod.GET)
    public String adminLogo(ModelMap model) {
        return "adminLogo";
    }

    @RequestMapping(value = "/addDeliveryZone", method = RequestMethod.GET)
    public String addDeliveryZone(@ModelAttribute("Zone") Zone zone, ModelMap model, HttpServletRequest request) {
        try {
            HttpSession session = request.getSession(false);
            Merchant merchant=null;
            if(session!=null)
             merchant = (Merchant) session.getAttribute("merchant");
            if (merchant != null) {
                List<Address> addresses = merchantService.findAddressByMerchantId(merchant.getId());
                if (!addresses.isEmpty()) {
                    Address address = addresses.get(0);
                    if(address!=null){
                    	String merchantAddress= "";
                    	if(address.getAddress1()!=null)
                    		merchantAddress=merchantAddress+address.getAddress1();
                    	if(address.getAddress2()!=null)
                    		merchantAddress=merchantAddress+" "+address.getAddress2();
                    	if(address.getAddress3()!=null)
                    		merchantAddress=merchantAddress+" "+address.getAddress3();
                    	if(address.getCity()!=null)
                    		merchantAddress=merchantAddress+" "+address.getCity();
                    	if(address.getCountry()!=null)
                    		merchantAddress=merchantAddress+" "+address.getCountry();
                    	if(address.getZip()!=null)
                    		merchantAddress=merchantAddress+"-"+address.getZip();
                    	
                    	 model.addAttribute("address",merchantAddress);
                    }
                   
                    model.addAttribute("id", address.getId());
                }
                model.addAttribute("zones", zoneService.findZoneByMerchantId(merchant.getId()));
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
        return "addDeliveryZone";
    }

    @RequestMapping(value = "/deliveryZones", method = RequestMethod.GET)
    public String showZones(@ModelAttribute("Zone") Zone zone, ModelMap model, HttpServletRequest request) {
        try {
            HttpSession session = request.getSession(false);
            Merchant merchant=null;
            if(session!=null)
             merchant = (Merchant) session.getAttribute("merchant");
            if (merchant != null) {
                model.addAttribute("zones", zoneService.findZoneByMerchantId(merchant.getId()));
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
        return "deliveryZones";
    }

    /**
     * Update delivery zone
     * 
     * @param zone
     * @param model
     * @param deliveryZoneId
     * @return String
     */
    @RequestMapping(value = "/updateDelivery", method = RequestMethod.GET)
    public String viewCustomerOrders(@ModelAttribute("Zone") Zone zone, ModelMap model, HttpServletRequest request,
                    @RequestParam(required = false) int deliveryZoneId) {
        try {
            Zone zoneDetail = zoneService.findById(deliveryZoneId);
            model.addAttribute("Zone", zoneDetail);
            model.addAttribute("ZoneStatus", zoneDetail.getZoneStatus());
            HttpSession session = request.getSession(false);
            Merchant merchant=null;
            if(session!=null)
             merchant = (Merchant) session.getAttribute("merchant");
            if (merchant != null) {
                List<Address> addresses = merchantService.findAddressByMerchantId(merchant.getId());
                if (!addresses.isEmpty()) {
                    Address address = addresses.get(0);
                    if(address!=null){
                    	String merchantAddress= "";
                    	if(address.getAddress1()!=null)
                    		merchantAddress=merchantAddress+address.getAddress1();
                    	if(address.getAddress2()!=null)
                    		merchantAddress=merchantAddress+" "+address.getAddress2();
                    	if(address.getAddress3()!=null)
                    		merchantAddress=merchantAddress+" "+address.getAddress3();
                    	if(address.getCity()!=null)
                    		merchantAddress=merchantAddress+" "+address.getCity();
                    	if(address.getCountry()!=null)
                    		merchantAddress=merchantAddress+" "+address.getCountry();
                    	if(address.getZip()!=null)
                    		merchantAddress=merchantAddress+"-"+address.getZip();
                    	
                    	 model.addAttribute("address",merchantAddress);
                    }
                    model.addAttribute("id", address.getId());
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
        return "updateDeliveryZone";
    }

    @RequestMapping(value = "/pickUpTime", method = RequestMethod.GET)
    public String pickUpTime(@ModelAttribute("PickUpTime") PickUpTime pickUpTime, ModelMap model,
                    HttpServletRequest request) {
        try {
            HttpSession session = request.getSession(false);
            Merchant merchant=null;
            if(session!=null)
             merchant = (Merchant) session.getAttribute("merchant");
            if (merchant != null) {
                List<Address> addresses = merchantService.findAddressByMerchantId(merchant.getId());
                if (!addresses.isEmpty()) {
                    Address address = addresses.get(0);
                    String address1= "";
                    if(address.getAddress1()!=null){
                    	address1=address.getAddress1();
                    };
                    String address2= "";
                    if(address.getAddress2()!=null){
                    	address2=address.getAddress2();
                    };
                    String address3= "";
                    if(address.getAddress3()!=null){
                    	address3=address.getAddress3();
                    };
                    model.addAttribute("address",
                    		address1 + " " + address2 + " " + address3
                                                    + " " + address.getCity() + " " + address.getCountry() + "-"
                                                    + address.getZip());
                    model.addAttribute("id", address.getId());
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
        return "pickUpTime";
    }

    @RequestMapping(value = "/convenienceFee", method = RequestMethod.GET)
    public String convenienceFee(@ModelAttribute("ConvenienceFee") ConvenienceFee convenienceFee, ModelMap model,
                    HttpServletRequest request) {
        try {
            HttpSession session = request.getSession(false);
            Merchant merchant=null;
            if(session!=null)
             merchant = (Merchant) session.getAttribute("merchant");
            if (merchant != null) {
                List<Address> addresses = merchantService.findAddressByMerchantId(merchant.getId());
                if (!addresses.isEmpty()) {
                    Address address = addresses.get(0);
                    String address1= "";
                    if(address.getAddress1()!=null){
                    	address1=address.getAddress1();
                    };
                    String address2= "";
                    if(address.getAddress2()!=null){
                    	address2=address.getAddress2();
                    };
                    String address3= "";
                    if(address.getAddress3()!=null){
                    	address3=address.getAddress3();
                    };
                    model.addAttribute("address",
                    		address1 + " " + address2 + " " + address3
                                                    + " " + address.getCity() + " " + address.getCountry() + "-"
                                                    + address.getZip());
                    model.addAttribute("id", address.getId());
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
        return "convenienceFee";
    }

    @RequestMapping(value = "/sessionOut", method = RequestMethod.GET)
    public String sessionOut(ModelMap model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        return "redirect:https://www.foodkonnekt.com";
    }
}
