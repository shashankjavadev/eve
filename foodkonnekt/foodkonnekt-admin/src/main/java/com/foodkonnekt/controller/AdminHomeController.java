package com.foodkonnekt.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.foodkonnekt.clover.vo.ItemModifiersVO;
import com.foodkonnekt.clover.vo.SearchVO;
import com.foodkonnekt.model.Category;
import com.foodkonnekt.model.CategoryItem;
import com.foodkonnekt.model.CategoryTiming;
import com.foodkonnekt.model.Clover;
import com.foodkonnekt.model.ConvenienceFee;
import com.foodkonnekt.model.Customer;
import com.foodkonnekt.model.Item;
import com.foodkonnekt.model.ItemModifierGroup;
import com.foodkonnekt.model.Merchant;
import com.foodkonnekt.model.Modifiers;
import com.foodkonnekt.model.OpenHours;
import com.foodkonnekt.model.PaymentMode;
import com.foodkonnekt.model.PickUpTime;
import com.foodkonnekt.model.Pos;
import com.foodkonnekt.model.SocialMediaLinks;
import com.foodkonnekt.model.TaxRates;
import com.foodkonnekt.model.Vendor;
import com.foodkonnekt.service.BusinessService;
import com.foodkonnekt.service.CategoryService;
import com.foodkonnekt.service.CloverService;
import com.foodkonnekt.service.CustomerService;
import com.foodkonnekt.service.ImportExcelService;
import com.foodkonnekt.service.ItemService;
import com.foodkonnekt.service.MerchantService;
import com.foodkonnekt.service.ModifierService;
import com.foodkonnekt.service.OrderService;
import com.foodkonnekt.serviceImpl.ExcelToJsonConverterConfig;
import com.foodkonnekt.util.CloverUrlUtil;
import com.foodkonnekt.util.DateUtil;
import com.foodkonnekt.util.EncryptionDecryptionUtil;
import com.foodkonnekt.util.IConstant;
import com.foodkonnekt.util.MailSendUtil;
import com.foodkonnekt.util.UrlConstant;
import com.google.gson.Gson;

@Controller
public class AdminHomeController {

	private static int EXPIRY_TIME = 30 * 60 * 1000;
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private MerchantService merchantService;
    
    @Autowired
    private CustomerService customerService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private ModifierService modifierService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private BusinessService businessService;

    @Autowired
    private CloverService cloverService;
    
    @Autowired
    private ImportExcelService importExcelService;
    
    
    
    

    /**
     * Show admin home page
     * 
     * @return String
     */
    @RequestMapping(value = "/adminHome", method = RequestMethod.GET)
    public String viewAdminHome(ModelMap model, HttpServletRequest request) {
        final HttpSession session = request.getSession(false);
        Merchant merchant1=null;
        try {
            if (session != null) {
            	Object object=session.getAttribute("merchant");
            	
            	if(object!=null){
                 merchant1 = (Merchant) session.getAttribute("merchant");
            	}else{
            		return "redirect:https://www.foodkonnekt.com";
            	}
                if(merchant1!=null && merchant1.getOwner()!=null && merchant1.getOwner().getPos()!=null && merchant1.getOwner().getPos().getPosId()!=null)
                	session.setAttribute("merchantType", merchant1.getOwner().getPos().getPosId());
                
                
                
                PickUpTime pickUpTime = businessService.findPickUpTimeByMerchantId(merchant1.getId());
                if (pickUpTime == null || pickUpTime.getPickUpTime() == null || pickUpTime.getPickUpTime().equals("0")) {
                    pickUpTime = new PickUpTime();
                    pickUpTime.setMerchantId(merchant1.getId());
                    pickUpTime.setPickUpTime("45");
                    merchantService.savePickupTime(pickUpTime);
                }

                final Merchant merchant = (Merchant) session.getAttribute("merchant");

                if (merchant != null) {
                    Integer isInstall = merchant.getIsInstall();
                    /* inventory Thread start */
                    if (isInstall == null || isInstall == 0) {
                        merchant1.setIsInstall(IConstant.BOOLEAN_TRUE); // 2
                        merchantService.save(merchant1);
                        final Clover clover = new Clover();
                        clover.setMerchantId(merchant.getPosMerchantId());
                        clover.setAuthToken(merchant.getAccessToken());
                        clover.setInstantUrl(IConstant.CLOVER_INSTANCE_URL);
                        clover.setUrl(IConstant.CLOVER_URL);

                        Runnable inventoryRunnable = new Runnable() {

                            public void run() {
                                // TODO Auto-generated method stub
                                session.setAttribute("inventoryThread", 0);
                                System.out.println("Clover inventoryThread Start");
                                cloverService.saveItem(clover, merchant);
                                System.out.println("Clover item done");
                                cloverService.saveCategory(clover, merchant);
                                System.out.println("Clover category done");
                                String modifierJson = CloverUrlUtil.getModifierAndModifierGroup(clover);
                                modifierService.saveModifierAndModifierGroup(modifierJson, merchant);
                                System.out.println("inventoryThread Done");
                                session.setAttribute("inventoryThread", 1);
                                /*
                                 * try{ if(session.getAttribute("isInstall")!=null){ int
                                 * isIntall=(Integer)session.getAttribute("isInstall"); merchant.setIsInstall(isIntall);
                                 * merchantService.save(merchant);} }catch(Exception e){
                                 * System.out.println("isInstall is not set ->"+e); }
                                 */
                            }
                        };
                        Thread inventoryThread = new Thread(inventoryRunnable);
                        inventoryThread.setName("inventoryThread");
                        inventoryThread.start();
                    } else if (isInstall == 1) {
                        session.setAttribute("inventoryThread", 1);
                    } else {
                        session.setAttribute("inventoryThread", 0);
                    }
                    /* inventory Thread ends */

                    /*
                     * if(session.getAttribute("inventoryThread")!=null){ int inventoryThreadStatus=
                     * (Integer)session.getAttribute("inventoryThread"); if(inventoryThreadStatus==1){
                     * merchant.setIsInstall(IConstant.BOOLEAN_TRUE); merchantService.save(merchant);} else{
                     * session.setAttribute("isInstall", IConstant.BOOLEAN_TRUE); } }
                     */
                    session.setAttribute("merchant", merchant1);
                    // Orders
                    model.addAttribute("noOfDeliveryOrder", orderService.findNoOfDeliveryOrder(merchant.getId()));
                    model.addAttribute("noOfPickUpOrder", orderService.findNoOfPickUpOrder(merchant.getId()));
                    model.addAttribute("avgOrderValue", orderService.findAverageOrderValue(merchant.getId()));
                    model.addAttribute("totalOrderValue", orderService.findtotalOrderValue(merchant.getId()));

                    // Customers
                    model.addAttribute("orderFrequency",
                                    orderService.findOrderFrequency(merchant.getId(), merchant.getOwner().getId()));
                    model.addAttribute("customerOrderAverage", orderService.findCustomerOrderAverage(merchant.getId(),
                                    merchant.getOwner().getId()));
                    model.addAttribute("totalNoOfCustomer",
                                    orderService.findTotalCustomer(merchant.getId(), merchant.getOwner().getId()));
                    // Item
                    model.addAttribute("trendingItem", orderService.findTrendingItem(merchant.getId()));
                    model.addAttribute("averageNumberOfItemPerOrder", orderService.findAverageNumberOfItemPerOrder(
                                    merchant.getId(), merchant.getOwner().getId()));
                } else {
                    return "redirect:https://www.foodkonnekt.com";
                }

            } else {
                return "redirect:https://www.foodkonnekt.com";
            }
        } catch (Exception e) {
            if (e != null) {
            	if(merchant1!=null)
                MailSendUtil.sendExceptionByMail(e,merchant1);
            	else
            		MailSendUtil.sendExceptionByMail(e);
                e.printStackTrace();
                return "redirect:https://www.foodkonnekt.com";
            }

        }
        return "adminHome";
    }
    
    @RequestMapping(value = "/uploadInventory", method = RequestMethod.GET)
    public String uploadLogo(ModelMap model) {
        return "uploadInventory";
    }

    @SuppressWarnings("unused")
    @RequestMapping(value = "/uploadInventoryByExcel", method = RequestMethod.POST)
    public String save(ModelMap model, HttpServletResponse response, HttpServletRequest request,
                    @RequestParam("file") MultipartFile file) {
        
            HttpSession session = request.getSession();
            Merchant merchant = (Merchant) session.getAttribute("merchant");
    	
    	try{
            ExcelToJsonConverterConfig config = new ExcelToJsonConverterConfig();
            
            File temp_file = new File(file.getOriginalFilename());
            try {
				file.transferTo(temp_file);
			} catch (IllegalStateException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
           
    		config.setSourceFile(file.getOriginalFilename());
    		String valid = config.valid();
    		if(valid!=null) {
    			System.out.println(valid);
    			//help(options);
    			
    		}
    		
    		
			try {
				String excelResponse =importExcelService.saveInventoryByExcel(config, merchant);
				
				
				if(excelResponse.contains("All the inventory imported successfully")){
					session.setAttribute("inventoryThread", 1);
                } else {
                    session.setAttribute("inventoryThread", 0);
                }
				
				model.addAttribute("excelResponse", excelResponse);
				
				
			} catch (InvalidFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
    	}catch(Exception e){
				MailSendUtil.sendExceptionByMail(e);
                e.printStackTrace();
                return "redirect:https://www.foodkonnekt.com";
			}
    	return "redirect:" + UrlConstant.BASE_URL + "/inventory";
            
    }

    @RequestMapping(value = "/adminSessionTimeOut", method = RequestMethod.GET)
    public String sessionTimeOut(ModelMap model) {
        return "adminSessionTimeOut";
    }

    /**
     * Show Inventory page
     * 
     * @return String
     */
    @RequestMapping(value = "/inventory", method = RequestMethod.GET)
    public String viewInventory(ModelMap model, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            Merchant merchant = (Merchant) session.getAttribute("merchant");
            if (merchant != null) {
                model.addAttribute("categories", categoryService.findAllCategory(merchant.getId()));
                if(merchant.getOwner()!=null && merchant.getOwner().getPos()!=null && merchant.getOwner().getPos().getPosId()!=null)
                model.addAttribute("merchantType", merchant.getOwner().getPos().getPosId());
            } else {
                return "redirect:https://www.foodkonnekt.com";
            }
        } else {
            return "redirect:https://www.foodkonnekt.com";
        }
        return "inventory";
    }
    
    @RequestMapping(value = "/support", method = RequestMethod.GET)
    public String support(@ModelAttribute("Customer") Customer customer, ModelMap model,
            HttpServletRequest request) {
        
        return "adminLogin";
    }
    
    
    
    @RequestMapping(value = "/LoginByAdmin", method = RequestMethod.POST)
    public String login(@ModelAttribute("Customer") Customer customer, ModelMap model,HttpServletResponse response,HttpServletRequest request) {

        Map<Object, Object> loginMap = new HashMap<Object, Object>();
        try {
        	HttpSession session = request.getSession();
            Customer result = customerService.findByEmailAndPassword(customer.getEmailId(),
                            EncryptionDecryptionUtil.encryptString(customer.getPassword()));
            if (result != null && result.getCustomerType()!=null && result.getCustomerType().equals("admin") ) {
            	if(result.getMerchantt()!=null && result.getMerchantt().getName().equals("FoodkonnektAdmin")&& result.getMerchantt().getOwner()!=null && result.getMerchantt().getOwner().getRole()!=null && result.getMerchantt().getOwner().getRole().getId()==IConstant.POS_CLOVER ){
            	
            	session.setAttribute("foodkonnektAdmin", result);
            	session.setAttribute("isFoodkonnektAdmin", true);
               // Customer customerResult = customerService.getCustomerProfile(result.getId());
                loginMap.put(IConstant.RESPONSE, IConstant.RESPONSE_SUCCESS_MESSAGE);
               // loginMap.put(IConstant.DATA, customerResult);
                return "redirect:" + UrlConstant.BASE_URL + "/getAllMerchants";
            	}else if(result.getMerchantt()!=null && result.getMerchantt().getOwner()!=null && result.getMerchantt().getOwner().getId()!=null){
            		session.setAttribute("foodkonnektAdmin", result);
            		session.setAttribute("isFoodkonnektAdmin", false);
            		return "redirect:" + UrlConstant.BASE_URL + "/getAllMerchants";
            	}
            	return "redirect:" + UrlConstant.BASE_URL + "/support";
            } else {
                loginMap.put(IConstant.RESPONSE, IConstant.RESPONSE_NO_DATA_MESSAGE);
                loginMap.put(IConstant.MESSAGE, IConstant.LOGIN_FAILURE);
                return "redirect:" + UrlConstant.BASE_URL + "/support";
            }
        } catch (Exception e) {
            if (e != null) {
                MailSendUtil.sendExceptionByMail(e);
            }
            e.printStackTrace();
            return "redirect:" + UrlConstant.BASE_URL + "/support";
        }
        
    }
    
    @RequestMapping(value = "/forgotAdminPassword", method = RequestMethod.POST)
    public String forgotPasswordAction( @RequestParam(value = "emailid") String emailid,
                    HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<Object, Object> forgotPasswordMap = new HashMap<Object, Object>();
        try {
            HttpSession session = request.getSession();
            if (emailid != null) {
                boolean status = customerService.findAdminByEmail(emailid);
                if (status) {
                    forgotPasswordMap.put("message",
                                    "Please check your inbox - we have sent you an email with instructions");
                } else {
                    forgotPasswordMap
                                    .put("message", "An account with the above email does not exist. Please try again");
                }
            } 
        } catch (Exception exception) {
            if (exception != null) {
                MailSendUtil.sendExceptionByMail(exception);
            }
            exception.printStackTrace();
        }
        return "redirect:" + UrlConstant.BASE_URL + "/support"; 
    }
    
    @RequestMapping(value = "/saveAdminPassword", method = RequestMethod.POST)
    public String saveAdminPassword(@ModelAttribute("Customer") Customer customer, ModelMap model,
                    HttpServletRequest request, HttpServletResponse response) {
        try {
            //HttpSession session = request.getSession();
        	
            if(customer!=null && customer.getId()!=null){
            	Customer sessionCustomer=customerService.findByCustomerId(customer.getId());
            sessionCustomer.setPassword(customer.getPassword());
            sessionCustomer = customerService.setGuestCustomerPassword(sessionCustomer);
            
            }else{
            	return "redirect:" + UrlConstant.BASE_URL + "/support";
            }
        } catch (Exception e) {
            if (e != null) {
                MailSendUtil.sendExceptionByMail(e);
            }
            e.printStackTrace();
        }
        return "redirect:" + UrlConstant.BASE_URL + "/support";
    }
    
    @RequestMapping(value = "/changeAdminpassword", params = { "email", "merchantId", "userId" }, method = RequestMethod.GET)
    public String changeAdminpassword(Map<String, Object> map, HttpServletRequest request,
                    @RequestParam(value = "email") String email, @RequestParam(value = "merchantId") String merchantId,
                    @RequestParam(value = "userId") String userId, @RequestParam(value = "tLog") String tLog) {

        try {
            Long time = Long.valueOf(EncryptionDecryptionUtil.decryption(tLog));
            Long currentTime = System.currentTimeMillis();
            int diff = (int) (currentTime - time);

            if (diff < EXPIRY_TIME) {

                if (merchantId != null) {
                    String id = merchantId;
                    Customer customer = customerService.findCustomerByEmailAndMerchantId(email, Integer.valueOf(id));
                    if (customer != null
                                    && userId.equalsIgnoreCase(EncryptionDecryptionUtil.encryptString(customer.getId()
                                                    + ""))) {
                        customer.setPassword("");
                        map.put("Customer", customer);
                        return "resetAdminPassword";
                    } else {
                        return "redirect:https://www.foodkonnekt.com";
                    }
                }
            } else {
                System.out.println("Link is expired");
                map.put("Customer", new Customer());
                return "forgotPassword";
            }
        } catch (Exception e) {
            if (e != null) {
                MailSendUtil.sendExceptionByMail(e);
            }
            e.printStackTrace();
        }
        return null;
    }
    
    
   

    /**
     * Show add line item page
     * 
     * @return String
     */
    @RequestMapping(value = "/addLineItem", method = RequestMethod.GET)
    public String viewAddLineItem(@ModelAttribute("Item") Item item, ModelMap model, HttpServletRequest request,
                    @RequestParam(required = false) int itemId) {
        try {
        	HttpSession session = request.getSession(false);
            if (session != null) {
                Merchant merchant = (Merchant) session.getAttribute("merchant");
                if (merchant != null){
            Item result = itemService.findItemByItemId(itemId);
            model.addAttribute("modifierLimit", result.getModifiersLimit());
            model.addAttribute("itemStatus", result.getItemStatus());
            model.addAttribute("item", result);
            model.addAttribute("times", DateUtil.findAllTime());
            model.addAttribute("times24", DateUtil.findAll24Time());
            if(merchant.getOwner()!=null && merchant.getOwner().getPos()!=null && merchant.getOwner().getPos().getPosId()!=null ){
            	model.addAttribute("merchantType",  merchant.getOwner().getPos().getPosId());
            }
                }}else{
                	return "redirect:https://www.foodkonnekt.com";
                }
        } catch (Exception e) {
            if (e != null) {
                MailSendUtil.sendExceptionByMail(e);
                e.printStackTrace();
                return "redirect:https://www.foodkonnekt.com";
            }
        }
        return "addLineItem";
    }
    
    
    
    @RequestMapping(value = "/getModifiersByModifierGroupIds", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody String filterByCategory(HttpServletRequest request,
                    @RequestParam(required = false) String modifierGroupIds,@RequestParam(required = false) Integer itemId)  {
    	List<List<ItemModifiersVO>> itemModifiers= new ArrayList();
        Map<Object, Object> itemModifierMap = new HashMap<Object, Object>();
        if(modifierGroupIds!=null && !modifierGroupIds.equals("null") && !modifierGroupIds.equals("undefined")){
        	String[] groupIds=modifierGroupIds.split(",");
        	for(String groupId:groupIds){
        		
        		int modifierGroupId=0;
        		try{
        		modifierGroupId=Integer.parseInt(groupId);
        		}catch(Exception e){
        			
        		}
        		if(modifierGroupId!=0){
        			
        			List<ItemModifiersVO> itemModifierList=modifierService.findModifiersbyModifierGroup(modifierGroupId,itemId);
        			if(itemModifierList!=null && itemModifierList.size()>0){
        			itemModifiers.add(itemModifierList);
        			}
        		}
        	}
        	itemModifierMap.put(IConstant.RESPONSE, IConstant.RESPONSE_SUCCESS_MESSAGE);
        	itemModifierMap.put(IConstant.DATA, itemModifiers);
        }else{
        	
        	itemModifierMap.put(IConstant.RESPONSE, IConstant.RESPONSE_NO_DATA_MESSAGE);
        	itemModifierMap.put(IConstant.MESSAGE, "ModifierId is null");
            
        }
        return new Gson().toJson(itemModifiers);
    }

    @RequestMapping(value = "/updateItem", method = RequestMethod.POST)
    public String updateItem(@ModelAttribute("Item") Item item, ModelMap model, HttpServletRequest request) {
        try {
            String description = request.getParameter("description");
            item.setDescription(description);
            itemService.updateLineItemValue(item);
        } catch (Exception e) {
            if (e != null) {
                MailSendUtil.sendExceptionByMail(e);
                e.printStackTrace();
                return "redirect:https://www.foodkonnekt.com";
            }
        }
        return "redirect:" + UrlConstant.BASE_URL + "/inventory";
    }

    /**
     * Show add category page
     * 
     * @return String
     */
    @RequestMapping(value = "/category", method = RequestMethod.GET)
    public String viewCategory(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
    	model.addAttribute("times", DateUtil.findAllTime());
    	model.addAttribute("weekDays", DateUtil.findAllWeekDays());
        return "category";
    }
    
    @RequestMapping(value = "/changeCategoryOrder", method = RequestMethod.GET)
    @ResponseBody
    public String changeCategoryOrder(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
     
    	return categoryService.changeCategoryOrder();
    }

    @RequestMapping(value = "/updateCategory", method = RequestMethod.GET)
    public String viewCategory(@ModelAttribute("Category") Category category, ModelMap model,
                    @RequestParam(required = false) int categoryId) {
        try {
            Category result = categoryService.findCategoryById(categoryId);
            model.addAttribute("categoryStatus", result.getItemStatus());
            model.addAttribute("category", result);
        } catch (Exception e) {
            if (e != null) {
                MailSendUtil.sendExceptionByMail(e);

                e.printStackTrace();
                return "redirect:https://www.foodkonnekt.com";
            }
        }
        return "updateCategory";
    }
    
    @RequestMapping(value = "/updateCategoryTiming", method = RequestMethod.GET)
    @ResponseBody
    public String updateCategoryTiming(@RequestParam int categoryId,@RequestParam String days,@RequestParam String startTime,@RequestParam String endTime,@RequestParam Integer allowCategoryTimings) {
        try {
            String result = categoryService.updateCategoryTiming(categoryId, days, startTime, endTime,allowCategoryTimings);
            return "success";
        } catch (Exception e) {
            if (e != null) {
                MailSendUtil.sendExceptionByMail(e);

               
            }
            return "fail";
        }
        
    }
    
    @RequestMapping(value = "/getCategoryTiming", method = RequestMethod.GET)
    @ResponseBody
    public List<CategoryTiming> getCategoryTiming(@RequestParam int categoryId) {
        try {
        	List<CategoryTiming> result = categoryService.getCategoryTiming(categoryId);
            return result;
        } catch (Exception e) {
            if (e != null) {
                MailSendUtil.sendExceptionByMail(e);

               
            }
            return null;
        }
        
    }

    @RequestMapping(value = "/updateCategoryStatus", method = RequestMethod.POST)
    public String updateCategoryStatus(@ModelAttribute("Category") Category category, ModelMap model,
                    HttpServletRequest request) {
        try {
            categoryService.updateCategoryStatusById(category);
        } catch (Exception e) {
            if (e != null) {
                MailSendUtil.sendExceptionByMail(e);

                e.printStackTrace();
                return "redirect:https://www.foodkonnekt.com";
            }
        }
        return "redirect:" + UrlConstant.BASE_URL + "/category";
    }

    @RequestMapping(value = "/addCategory", method = RequestMethod.GET)
    public String viewAddCategory(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
        try {
            HttpSession session = request.getSession(false);
            if (session != null) {
                Merchant merchant = (Merchant) session.getAttribute("merchant");
                if (merchant != null)
                    model.addAttribute("categories", categoryService.findAllCategory(merchant.getId()));
                else {
                    return "redirect:https://www.foodkonnekt.com";
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
        return "addCategory";
    }

    /**
     * Show allOrders page
     * 
     * @return String
     */
    @RequestMapping(value = "/allOrders", method = RequestMethod.GET)
    public String viewAllOrders(@ModelAttribute("SearchVO") SearchVO searchVO, ModelMap model,
                    HttpServletRequest request, HttpServletResponse response) {
        return "allOrders";
    }

    /**
     * Show merchant page
     * 
     * @return String
     */
    @RequestMapping(value = "/merchants", method = RequestMethod.GET)
    public String viewMerchants(ModelMap model, HttpServletRequest request) {
        try {
            HttpSession session = request.getSession(false);
            if (session != null) {
                Merchant merchant = (Merchant) session.getAttribute("merchant");
                if (merchant != null)
                    model.addAttribute("merchants", merchantService.findMerchantById(merchant.getId()));
                else
                    return "redirect:https://www.foodkonnekt.com";
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
        return "merchants";
    }

    /**
     * Show modifierGroup by merchantId
     * 
     * @param model
     * @return String
     */
    @RequestMapping(value = "/modifierGroups", method = RequestMethod.GET)
    public String viewModifierGroups(ModelMap model) {
        return "modifierGroups";
    }

    /**
     * Find Modifiers by merchantId
     * 
     * @param model
     * @return String
     */
    @RequestMapping(value = "/modifiers", method = RequestMethod.GET)
    public String viewModifiers(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
        try {
            HttpSession session = request.getSession(false);
            if (session != null) {
                Merchant merchant = (Merchant) session.getAttribute("merchant");
                if (merchant != null) {
                    model.addAttribute("categories", categoryService.findAllCategory(merchant.getId()));
                } else {
                    return "redirect:https://www.foodkonnekt.com";
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
        return "modifiers";
    }

    @RequestMapping(value = "/getAllModifiers", method = RequestMethod.GET)
    @ResponseBody
    public List<Modifiers> getModifiers(@RequestParam(required = true) Integer merchantId) {
        return modifierService.findModifierByMerchantById(merchantId);
    }

    /**
     * find by orderType , orderStatus and orderDate
     * 
     * @param searchVO
     * @param model
     * @param request
     * @param response
     * @return String
     */
    @RequestMapping(value = "/searchOrder", method = { RequestMethod.POST })
    public String searchOrders(@ModelAttribute("SearchVO") SearchVO searchVO, ModelMap model,
                    HttpServletRequest request, HttpServletResponse response) {
        String startDate = null;
        String endDate = null;
        try {
            if (!searchVO.getDateRange().isEmpty()) {
                JSONObject jObject = new JSONObject(searchVO.getDateRange());
                startDate = jObject.getString("start");
                endDate = jObject.getString("end");
            }
            System.out.println(startDate + endDate);
            if (searchVO.getOrderStatus().equals("Status") && searchVO.getDateRange() == "") {
                model.addAttribute("allOrders", orderService.findOrdersByOrderType(searchVO.getOrderType()));
            }
            if (searchVO.getOrderType().equals("Type") && searchVO.getDateRange() == "") {
                model.addAttribute("allOrders", orderService.findOrdersByStatus(searchVO.getOrderStatus()));
            }
            if (searchVO.getOrderStatus().equals("Status") && searchVO.getOrderType().equals("Type")) {
                model.addAttribute("allOrders", orderService.findByOrderDate(startDate, endDate));
            }
            if (!searchVO.getOrderStatus().equals("Status") && !searchVO.getOrderType().equals("Type")
                            && searchVO.getDateRange() != "") {
                model.addAttribute("allOrders", orderService.findOrdersByStatusAndOrderTypeAndDateRange(
                                searchVO.getOrderStatus(), searchVO.getOrderType(), startDate, endDate));
            }
            if (!searchVO.getOrderStatus().equals("Status") && !searchVO.getOrderType().equals("Type")
                            && searchVO.getDateRange() == "") {
                model.addAttribute(
                                "allOrders",
                                orderService.findOrdersByStatusAndOrderType(searchVO.getOrderStatus(),
                                                searchVO.getOrderType()));
            }
            if (searchVO.getOrderStatus().equals("Status") && !searchVO.getOrderType().equals("Type")
                            && searchVO.getDateRange() != "") {
                model.addAttribute("allOrders",
                                orderService.findByOrderTypeAndOrderDate(searchVO.getOrderType(), startDate, endDate));
            }
            if (!searchVO.getOrderStatus().equals("Status") && searchVO.getOrderType().equals("Type")
                            && searchVO.getDateRange() != "") {
                model.addAttribute("allOrders", orderService.findByOrderStatusAndOrderDate(searchVO.getOrderStatus(),
                                startDate, endDate));
            }
        } catch (Exception e) {
            if (e != null) {
                MailSendUtil.sendExceptionByMail(e);

                e.printStackTrace();
                return "redirect:https://www.foodkonnekt.com";
            }
        }
        return "allOrders";
    }

    @RequestMapping(value = "/onLineOrderLink", method = RequestMethod.GET)
    public String viewOnlineOrderLink(@ModelAttribute("OpenHours") OpenHours openHours, ModelMap model,
                    HttpServletRequest request) {
        try {
            HttpSession session = request.getSession(false);
            if (session != null) {
                Merchant merchant = (Merchant) session.getAttribute("merchant");
                if (merchant != null) {
                	
                	if(merchant.getOwner()!=null && merchant.getOwner().getPos()!=null && merchant.getOwner().getPos().getPosId()!=null)
                    	session.setAttribute("merchantType", merchant.getOwner().getPos().getPosId());
                	
                    String onlineOrderLink = (String) session.getAttribute("onlineOrderLink");
                    model.addAttribute("onlineOrderLink", onlineOrderLink);
                    // Find business hours by merchantId
                    model.addAttribute("businessHours", businessService.findBusinessHourByMerchantId(merchant.getId()));
                    // Find location by merchantId
                    model.addAttribute("location", businessService.findLocationByMerchantId(merchant.getId()));
                    model.addAttribute("convenienceFee",
                                    businessService.findConvenienceFeeByMerchantId(merchant.getId()));

                    model.addAttribute("pickupTime", businessService.findPickUpTimeByMerchantId(merchant.getId()));

                    model.addAttribute("times", DateUtil.findAllTime());
                    System.out.println("AllowFutureOrder --> " + merchant.getAllowFutureOrder());
                    model.addAttribute("allowFutureOrder", merchant.getAllowFutureOrder());
                    System.out.println("AllowMultipleKoupon --> " + merchant.getAllowMultipleKoupon());
                    model.addAttribute("allowMultipleKoupon", merchant.getAllowMultipleKoupon());
                    model.addAttribute("activeCustomerFeedback", merchant.getActiveCustomerFeedback());
                    
                    model.addAttribute("futureDaysAhead", merchant.getFutureDaysAhead());
                    
                   List<Category> categories= categoryService.findAllCategory(merchant.getId());
                   List<TaxRates> taxRates=merchantService.findAllTaxRatesByMerchantId(merchant.getId());
                   if(taxRates!=null && taxRates.size()>0){
                	   model.addAttribute("isTaxAvailable", true);
                   }else{
                	   model.addAttribute("isTaxAvailable", false);
                   }
                   
                  SocialMediaLinks socialMediaLinks= merchantService.getSocialMediaLinksByMerchantId(merchant.getId());
                  openHours.setSocialMediaLinks(socialMediaLinks);
                  String linkActiveCustomerFeedback = merchantService.generateLinkActiveCustomerFeedback(merchant.getId());
                  model.addAttribute("linkActiveCustomerFeedback", linkActiveCustomerFeedback);
                  
                  model.addAttribute("SocialMediaLinks", socialMediaLinks);
                   
                   if(categories!=null && categories.size()>0){
                	   session.setAttribute("inventoryThread", 1);
                   } else {
                       session.setAttribute("inventoryThread", 0);
                   }

                    PaymentMode cashMode = businessService.findByMerchantIdAndLabel(merchant.getId(), "Cash");
                    PaymentMode creditCardMode = businessService.findByMerchantIdAndLabel(merchant.getId(),
                                    "Credit Card");
                    if (cashMode != null)
                        model.addAttribute("cash", cashMode.getAllowPaymentMode());

                    if (creditCardMode != null)
                        model.addAttribute("creditcard", creditCardMode.getAllowPaymentMode());

                } else {
                    return "redirect:https://www.foodkonnekt.com";
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
        return "onLineOrderLink";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public void logOutRedirect(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        session.removeAttribute("merchant");
        session.invalidate();
        try {
            response.sendRedirect("https://www.foodkonnekt.com");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @RequestMapping(value = "/adminLogout", method = RequestMethod.GET)
    public void adminLogout(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        session.removeAttribute("foodkonnektAdmin");
        session.invalidate();
        try {
            response.sendRedirect("" + UrlConstant.BASE_URL + "/support");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * find customer orders
     * 
     * @param model
     * @param request
     * @param customerId
     * @return String
     */
    @RequestMapping(value = "/customerOrders", method = RequestMethod.GET)
    public String viewCustomerOrders(@ModelAttribute("SearchVO") SearchVO searchVO, ModelMap model,
                    HttpServletRequest request, @RequestParam(required = false) int customerId) {
        try {
            model.addAttribute("allOrders", orderService.findOrderByCustomerId(customerId));
        } catch (Exception e) {
            if (e != null) {
                MailSendUtil.sendExceptionByMail(e);

                e.printStackTrace();
                return "redirect:https://www.foodkonnekt.com";
            }
        }
        return "customerOrders";
    }
    
    @RequestMapping(value = "/getAllMerchants", method = RequestMethod.GET)
    public String getAllMerchants(@ModelAttribute("SearchVO") SearchVO searchVO, ModelMap model,
                    HttpServletRequest request) {
        try {
        	HttpSession session = request.getSession();
        	if(session!=null){
        	Customer customer=(Customer)session.getAttribute("foodkonnektAdmin");
        	Boolean isFoodkonnektAdmin=(Boolean)session.getAttribute("isFoodkonnektAdmin");
        	if(customer!=null){
        		if(isFoodkonnektAdmin!=null){
        			if(isFoodkonnektAdmin){
        	List<Merchant> merchants=merchantService.findAllMerchants();
            model.addAttribute("allMerchants",merchants );
            return "getAllMerchants";
            }else{
            	if(customer.getMerchantt()!=null && customer.getMerchantt().getOwner()!=null && customer.getMerchantt().getOwner().getId()!=null){
            		List<Merchant> merchants=merchantService.findAllMerchantsByVendorId(customer.getMerchantt().getOwner().getId());
            		       
                            model.addAttribute("allMerchants",merchants );
            	}
            	return "getAllMerchantsByVendor";
            }
        		}
            
        	 }
        	}
        	return "redirect:" + UrlConstant.BASE_URL + "/support";
        } catch (Exception e) {
            if (e != null) {
                MailSendUtil.sendExceptionByMail(e);

                e.printStackTrace();
            }
            return "redirect:" + UrlConstant.BASE_URL + "/support";
        }
       
    }

    @RequestMapping(value = "/saveBusinessLogo", method = RequestMethod.POST)
    public String save(@ModelAttribute("OpenHours") OpenHours openHours, ModelMap model, HttpServletResponse response,
                    HttpServletRequest request, @RequestParam("file") MultipartFile file) {

        try {
            HttpSession session = request.getSession(false);
            if (session != null) {
                Merchant merchant = (Merchant) session.getAttribute("merchant");
                if (merchant != null) {
                	//merchant.getId();
                	merchant=	merchantService.findById(merchant.getId());
                    if (!(file.getSize() == 0)) {
                        // images
                        Long size = file.getSize();
                        if (size >= 2097152) {
                            model.addAttribute("filesize", "Maximum Allowed File Size is 2 MB");
                            return "uploadLogo";
                        }

                        String orgName = file.getOriginalFilename();

                        String exts[] = orgName.split("\\.");

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

                        // String image = ImageUploadUtils.getImage(file);
                        merchant.setMerchantLogo(UrlConstant.ADMIN_LOGO_PATH_TO_SHOW + logoName);
                    }
                    
                   /* if (openHours.getActiveCustomerFeedback()!= null) {
                        merchant.setActiveCustomerFeedback(openHours.getActiveCustomerFeedback());
                    } else {
                        merchant.setActiveCustomerFeedback(IConstant.BOOLEAN_FALSE);
                    }*/

                    if (openHours.getAllowFutureOrder() != null) {
                    	
                        merchant.setAllowFutureOrder(openHours.getAllowFutureOrder());
                        
                        if(merchant.getOwner()!=null && merchant.getOwner().getPos()!=null && merchant.getOwner().getPos().getPosId()!=null && merchant.getOwner().getPos().getPosId()==IConstant.POS_CLOVER){
                        if(openHours.getAllowFutureOrder()==IConstant.BOOLEAN_TRUE){
                        	
                        	CloverUrlUtil.enableFuturOrderOnClover("Yes",merchant);
                        }else{
                        	CloverUrlUtil.enableFuturOrderOnClover("No",merchant);
                        }
                        }
                    } else {
                        merchant.setAllowFutureOrder(IConstant.BOOLEAN_FALSE);
                    }
                    
                    if (openHours.getFutureDaysAhead()!= null) {
                        merchant.setFutureDaysAhead(openHours.getFutureDaysAhead());
                    }
                    if (openHours.getAllowMultipleKoupon() != null) {
                    System.out.println("allow multiple koupon-->"+merchant.getAllowMultipleKoupon());
                    merchant.setAllowMultipleKoupon(openHours.getAllowMultipleKoupon());
                    }
                    merchant.setAllowFutureOrder(openHours.getAllowFutureOrder());
                    merchantService.save(merchant);
                    
                    if(openHours.getSocialMediaLinks()!=null){
                    	SocialMediaLinks oldSocialMediaLinks=merchantService.getSocialMediaLinksByMerchantId(merchant.getId());
                    	SocialMediaLinks socialMediaLinks=openHours.getSocialMediaLinks();
                    	if(oldSocialMediaLinks!=null){
                    		socialMediaLinks.setId(oldSocialMediaLinks.getId());
                    	}
                    	
                    	socialMediaLinks.setMerchant(merchant);
                    	
                    	socialMediaLinks =merchantService.saveSocialMediaLinks(socialMediaLinks);
                    	
                    	merchant.setSocialMediaLinks(socialMediaLinks);
                    }
                    
                    session.setAttribute("merchant", merchant);
                    PaymentMode cashMode = businessService.findByMerchantIdAndLabel(merchant.getId(), "Cash");
                    PaymentMode creditCardMode = businessService.findByMerchantIdAndLabel(merchant.getId(),
                                    "Credit Card");
                    if(cashMode!=null){
                    cashMode.setAllowPaymentMode(0);
                    businessService.savePaymentMode(cashMode);}
                    
                    if(creditCardMode!=null){
                    creditCardMode.setAllowPaymentMode(0);
                    
                    businessService.savePaymentMode(creditCardMode);
                    }
                    if (openHours.getAllowPaymentModes() != null) {
                        String[] paymentModes = openHours.getAllowPaymentModes().split(",");
                        for (String paymentMode : paymentModes) {
                            PaymentMode mode = businessService.findByMerchantIdAndLabel(merchant.getId(), paymentMode);
                           if(mode!=null){
                            mode.setAllowPaymentMode(1);
                            businessService.savePaymentMode(mode);}
                        }
                    }

                    ConvenienceFee convenienceFee = businessService.findConvenienceFeeByMerchantId(merchant.getId());
                    if (convenienceFee == null) {
                        convenienceFee = new ConvenienceFee();
                    }
                    convenienceFee.setLocationId(openHours.getLocationId());
                    convenienceFee.setConvenienceFee(openHours.getConvenienceFee());
                    convenienceFee.setIsTaxable(openHours.getIsTaxable());
                    convenienceFee.setMerchantId(merchant.getId());
                    merchantService.saveConvenienceFee(convenienceFee, merchant);

                    PickUpTime pickUpTime = businessService.findPickUpTimeByMerchantId(merchant.getId());
                    if (pickUpTime == null) {
                        pickUpTime = new PickUpTime();
                    }
                    pickUpTime.setLocationId(openHours.getLocationId());
                    pickUpTime.setMerchantId(merchant.getId());
                    pickUpTime.setPickUpTime(openHours.getPickUpTiime());
                    merchantService.savePickupTime(pickUpTime);

                    if (openHours.getsTimeToSave() != null && openHours.getSelectedDay() != null) {
                        businessService.updateBusinessHour(openHours.getsTimeToSave(), openHours.geteTimeToSave(),
                                        openHours.getSelectedDay());
                    }
                    System.out.println("---" + openHours.getsTimeToSave());
                    System.out.println("---" + openHours.geteTimeToSave());
                } else {
                    return "redirect:https://www.foodkonnekt.com";
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
        return "redirect:" + UrlConstant.BASE_URL + "/onLineOrderLink";
    }

    /**
     * Find by categoryId
     * 
     * @param categoryId
     * @return List<Item>
     */
    @RequestMapping(value = "/itemByCategoryId", method = RequestMethod.GET)
    @ResponseBody
    public List<Item> findItemByCategory(@RequestParam(required = true) Integer categoryId) {
        return itemService.findByCategoryId(categoryId);
    }

    /**
     * Find by itemId
     * 
     * @param itemId
     * @return List<Modifiers>
     */
    @RequestMapping(value = "/modifiersByItemId", method = RequestMethod.GET)
    @ResponseBody
    public List<Modifiers> findModifierByItem(@RequestParam(required = true) Integer itemId) {
        return categoryService.findByItemId(itemId);
    }

    /**
     * Find item by categoryId
     * 
     * @param model
     * @param request
     * @param response
     * @param categoryId
     * @return String
     */
    @RequestMapping(value = "/findItemsByCategoryId", method = RequestMethod.GET)
    public String viewMerchants(ModelMap model, HttpServletRequest request, HttpServletResponse response,
                    @RequestParam(required = true) Integer categoryId) {
        try {
            HttpSession session = request.getSession(false);
            if (session != null) {
                Merchant merchant = (Merchant) session.getAttribute("merchant");
                if (merchant != null) {
                    Category category = categoryService.findCategoryById(categoryId);
                  /* List <CategoryItem> category = itemService.findCategoryByInSortedOrder(categoryId);
                   for(CategoryItem c:category)
                  {
                	  System.out.println("name-"+c.getCategory().getName()+" sortorder-"+c.getSortOrder()+" getPrice- "+c.getItem().getPrice()+" "+c.getItem().getModifierGroups()+" "+c.getItem().getMenuOrder());
                	  
                  }*/
                  
                  //  System.out.println(category.getName());
                    model.addAttribute("category", category);
                    model.addAttribute("categories", categoryService.findAllCategory(merchant.getId()));
                } else {
                    return "redirect:https://www.foodkonnekt.com";
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
        return "categoryItems";
    }

    @RequestMapping(value = "/updateCategoryStatusById", method = RequestMethod.GET)
    @ResponseBody
    public String updateCatStatus(@RequestParam(required = true) Integer categoryId,
                    @RequestParam(required = true) Integer categoryStatus) {
        try {
            Category category = new Category();
            category.setId(categoryId);
            category.setItemStatus(categoryStatus);
            return  categoryService.updateCategoryStatusById(category);
        } catch (Exception e) {
            if (e != null) {
                MailSendUtil.sendExceptionByMail(e);
                e.printStackTrace();
                
            }
            return "redirect:https://www.foodkonnekt.com";
        }
        //return "succuss";
    }
    
    @RequestMapping(value = "/updateCategorySortOrderById", method = RequestMethod.GET)
    @ResponseBody
    public String updateCategorySortOrderById(@RequestParam(required = true) Integer categoryId,
                    @RequestParam(required = true) Integer sortOrder,@RequestParam(required = true)String action) {
        try {
            Category category = new Category();
            category.setId(categoryId);
            category.setSortOrder(sortOrder);
            boolean status=categoryService.updateCategorySortOrderById(category,action);
            if(status)
            	return "succuss";
            else
            	return "failed";
            
        } catch (Exception e) {
            if (e != null) {
                MailSendUtil.sendExceptionByMail(e);
                e.printStackTrace();
                return "redirect:https://www.foodkonnekt.com";
            }
            return "failed";
        }
        
    }
    
    @RequestMapping(value = "/updateItemSortOrderById", method = RequestMethod.GET)
    @ResponseBody
    public String updateItemSortOrderById(@RequestParam(required = true) Integer categoryItemId,
                    @RequestParam(required = true) Integer sortOrder,@RequestParam(required = true)String action) {
        try {
            CategoryItem categoryItem = new CategoryItem();
            categoryItem.setId(categoryItemId);
            categoryItem.setSortOrder(sortOrder);
            boolean status=itemService.updateItemSortOrderById(categoryItem,action);
            if(status)
            	return "succuss";
            else
            	return "failed";
            
        } catch (Exception e) {
            if (e != null) {
                MailSendUtil.sendExceptionByMail(e);
                e.printStackTrace();
                return "redirect:https://www.foodkonnekt.com";
            }
            return "failed";
        }
        
    }
    
    // Add extra parameter categoryItemId by manish gupta
    @RequestMapping(value = "/updateItemStatusById", method = RequestMethod.GET)
    @ResponseBody
    public String updateItemStatusById(@RequestParam(required = true) Integer itemId,
                    @RequestParam(required = true) Integer itemStatus,
                    @RequestParam(required=true) Integer categoryItemId) {
        try {
        	System.out.println(itemStatus+"-status, catItmId- "+categoryItemId);
            Item item = new Item();
            item.setId(itemId);
            item.setItemStatus(itemStatus);
            //change return type and add extra parameter categoryItemId
           return itemService.updateItemStatusById(item, categoryItemId);
        } catch (Exception e) {
            if (e != null) {
                MailSendUtil.sendExceptionByMail(e);
                e.printStackTrace();
                return "redirect:https://www.foodkonnekt.com";
            }
        }
        return "succuss";
    }

    @ExceptionHandler(value = NullPointerException.class)
    public String handleNullPointerException(Exception e) {
        return "exception";
    }

    /**
     * Find modifier count of modifierGroup
     * 
     * @param modiferCount
     * @param modifierGroupId
     * @return String
     */
    @RequestMapping(value = "/findModifierCountOfModifierGroup", method = RequestMethod.GET)
    @ResponseBody
    public String findMCount(@RequestParam(required = true) Integer modiferCount,
                    @RequestParam(required = true) Integer modifierGroupId) {
        return modifierService.findModifierCountOfModifierGroup(modifierGroupId, modiferCount);
    }

    @RequestMapping(value = "/isInstalled", method = RequestMethod.GET)
    @ResponseBody
    public boolean setup(ModelMap model, HttpServletResponse response, HttpServletRequest request) {
        try {
            HttpSession session = request.getSession();
            if(session!=null){
            Merchant merchant = (Merchant) session.getAttribute("merchant");
            
            if(merchant!=null){
            	Vendor vendor=merchant.getOwner();
            	if(vendor!=null){
            		Pos pos=vendor.getPos();
            		
            		if(pos!=null && pos.getPosId()==1){
            Integer inventoryThreadStatus = 0;
            if (session.getAttribute("inventoryThread") == null) {
                session.setAttribute("inventoryThread", 0);
                return false;
            } else if (session.getAttribute("inventoryThread") != null)
                inventoryThreadStatus = (Integer) session.getAttribute("inventoryThread");
            if (inventoryThreadStatus == 1) {
                return true;
            } else {
                return false;
            }}else{
            	return true;
            }}else{
            	return true;
            }}else{
            	return true;
            }}else{
            	return true;
            }
            	
            	
        } catch (Exception e) {
            if (e != null) {
                MailSendUtil.sendExceptionByMail(e);
                e.printStackTrace();
            }
            return false;
        }
    }
    
    @RequestMapping(value = "/updateItemModifierGroupSortOrderById", method = RequestMethod.GET)
    @ResponseBody
    public String updateItemModifierGroupSortOrderById(@RequestParam(required = true) Integer itemModifierGroupId,
    				@RequestParam(required = true) Integer itemId,
                    @RequestParam(required = true) Integer sortOrder,@RequestParam(required = true)String action,
                    @RequestParam(required = true) Integer itemModifierGroupOrder ) {
        try {
        	System.out.println("updateItemModifierGroupSortOrderById--"+itemModifierGroupOrder);
            ItemModifierGroup itemModifierGroup = new ItemModifierGroup();
            itemModifierGroup.setId(itemModifierGroupId);
            itemModifierGroup.setSortOrder(sortOrder);
            boolean status=itemService.updateItemModifierGroupSortOrderById(itemModifierGroup,action, itemModifierGroupOrder,itemId);
            if(status)
            	return "succuss";
            else
            	return "failed";
            
        } catch (Exception e) {
            if (e != null) {
                MailSendUtil.sendExceptionByMail(e);
                e.printStackTrace();
                return "redirect:https://www.foodkonnekt.com";
            }
            return "failed";
        }
        
    }
    
    @RequestMapping(value = "/updateInventoryItemStatusById", method = RequestMethod.GET)
    @ResponseBody
    public String updateInventoryItemStatusById(@RequestParam(required = true) Integer itemId,
                    @RequestParam(required = true) Integer itemStatus) {
        try {
            Item item = new Item();
            item.setId(itemId);
            item.setItemStatus(itemStatus);
            itemService.updateInventoryItemStatusById(item);
        } catch (Exception e) {
            if (e != null) {
                MailSendUtil.sendExceptionByMail(e);
                e.printStackTrace();
                return "redirect:https://www.foodkonnekt.com";
            }
        }
        return "succuss";
    }
       
    @RequestMapping(value = "/updateMerchantKritiq", method = RequestMethod.GET)
    @ResponseBody
    public String updateMerchantKritiqById(@RequestParam(value = "merchantId") Integer merchantId, @RequestParam(value = "status") String  status){
    	String result = null;
        try {
            
        	if(merchantId != null){
        		result = merchantService.getMerchantSubscription(merchantId , status);
        	}
        	
        } catch (Exception exception) {
            if (exception != null) {
                MailSendUtil.sendExceptionByMail(exception);
            }
            exception.printStackTrace();
        }
        return result;
    	
    	
    }
    
    @RequestMapping(value = "/addDefaultTax", method = RequestMethod.GET)
    @ResponseBody
    public String addDefaultTaxByMerchantId(@RequestParam(value = "merchantId") Integer merchantId){
		
    	String response=merchantService.addDefaultTaxByMerchantId(merchantId);
    	
    	
    	return response;
    }
    
    
    
}
