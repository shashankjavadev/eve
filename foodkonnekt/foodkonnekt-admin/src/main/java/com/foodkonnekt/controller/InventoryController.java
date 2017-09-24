package com.foodkonnekt.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.foodkonnekt.model.Merchant;
import com.foodkonnekt.service.CategoryService;
import com.foodkonnekt.service.CustomerService;
import com.foodkonnekt.service.ItemService;
import com.foodkonnekt.service.ModifierService;
import com.foodkonnekt.service.OrderService;

@Controller
public class InventoryController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private ModifierService modifierService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/helloWorld", method = RequestMethod.GET)
    public String printWelcome(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
        return "helloWorld";

    }

    @RequestMapping(value = "/inventoryDataUsingAjax", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody String springPaginationDataTables(HttpServletRequest request) throws IOException {
        HttpSession session = request.getSession(false);
        Merchant merchant = (Merchant) session.getAttribute("merchant");
        Integer pageNumber = 0;
        if (null != request.getParameter("iDisplayStart"))
            pageNumber = (Integer.valueOf(request.getParameter("iDisplayStart")) / 10) + 1;
        // Fetch search parameter
        String searchParameter = request.getParameter("sSearch");
        // Fetch Page display length
        Integer pageDisplayLength = Integer.valueOf(request.getParameter("iDisplayLength"));
        // Create page list data
        String jsonOutput = "";
        if (merchant != null) {
            jsonOutput = itemService.findinventoryLineItemByMerchantId(merchant.getId(), pageDisplayLength, pageNumber,
                            searchParameter);
        }
        return jsonOutput;

    }

    @RequestMapping(value = "/filterByCategory", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody String filterByCategory(HttpServletRequest request,
                    @RequestParam(required = false) int categoryId) throws IOException {
        HttpSession session = request.getSession(false);
        Merchant merchant = (Merchant) session.getAttribute("merchant");
        String jsonOutput = "";
        if (merchant != null) {
            jsonOutput = itemService.filterInventoryByCategoryId(merchant.getId(), categoryId);
        }
        return jsonOutput;
    }

    @RequestMapping(value = "/modifiersDataTables", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody String findAllModifiers(HttpServletRequest request) throws IOException {
        HttpSession session = request.getSession(false);
        Merchant merchant = (Merchant) session.getAttribute("merchant");
        Integer pageNumber = 0;
        if (null != request.getParameter("iDisplayStart"))
            pageNumber = (Integer.valueOf(request.getParameter("iDisplayStart")) / 10) + 1;
        // Fetch search parameter
        String searchParameter = request.getParameter("sSearch");
        Integer pageDisplayLength = Integer.valueOf(request.getParameter("iDisplayLength"));
        String jsonOutput = "";
        if (merchant != null) {
            jsonOutput = modifierService.findModifierByMerchantById(merchant.getId(), pageDisplayLength, pageNumber,
                            searchParameter);
        }
        return jsonOutput;
    }

    @RequestMapping(value = "/modifiersGroupsDataTables", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody String findAllModifierGroups(HttpServletRequest request) throws IOException {
        HttpSession session = request.getSession(false);
        Merchant merchant = (Merchant) session.getAttribute("merchant");
        Integer pageNumber = 0;
        if (null != request.getParameter("iDisplayStart"))
            pageNumber = (Integer.valueOf(request.getParameter("iDisplayStart")) / 10) + 1;
        // Fetch search parameter
        String searchParameter = request.getParameter("sSearch");
        Integer pageDisplayLength = Integer.valueOf(request.getParameter("iDisplayLength"));
        String jsonOutput = "";
        if (merchant != null) {
            jsonOutput = modifierService.findModifierGroupsByMerchantById(merchant.getId(), pageDisplayLength,
                            pageNumber, searchParameter);
        }
        return jsonOutput;
    }

    @RequestMapping(value = "/customersDataTables", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody String findCustomerInventory(HttpServletRequest request) throws IOException {
        HttpSession session = request.getSession(false);
        Merchant merchant = (Merchant) session.getAttribute("merchant");
        Integer pageNumber = 0;
        if (null != request.getParameter("iDisplayStart"))
            pageNumber = (Integer.valueOf(request.getParameter("iDisplayStart")) / 10) + 1;
        // Fetch search parameter
        String searchParameter = request.getParameter("sSearch");
        Integer pageDisplayLength = Integer.valueOf(request.getParameter("iDisplayLength"));
        String jsonOutput = "";
        if (merchant != null) {
            jsonOutput = customerService.findCustomerInventory(merchant.getId(), pageDisplayLength, pageNumber,
                            searchParameter);
        }
        return jsonOutput;
    }

    @RequestMapping(value = "/searchModifiersByText", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody String searchModifiers(HttpServletRequest request,
                    @RequestParam(required = true) String searchTxt) throws IOException {
        HttpSession session = request.getSession(false);
        Merchant merchant = (Merchant) session.getAttribute("merchant");
        String searchResult = "";
        if (merchant != null) {
            searchResult = modifierService.searchModifiersByTxt(merchant.getId(), searchTxt);
        }
        return searchResult;
    }

    @RequestMapping(value = "/searchModifierGroupByText", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody String searchModifierGroup(HttpServletRequest request,
                    @RequestParam(required = true) String searchTxt) throws IOException {
        HttpSession session = request.getSession(false);
        Merchant merchant = (Merchant) session.getAttribute("merchant");
        String searchResult = "";
        if (merchant != null) {
            searchResult = modifierService.searchModifierGroupByTxt(merchant.getId(), searchTxt);
        }
        return searchResult;
    }

    @RequestMapping(value = "/searchItemByText", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody String searchItems(HttpServletRequest request, @RequestParam(required = true) String searchTxt)
                    throws IOException {
        HttpSession session = request.getSession(false);
        Merchant merchant = (Merchant) session.getAttribute("merchant");
        String searchResult = "";
        if (merchant != null) {
            searchResult = itemService.searchItemByTxt(merchant.getId(), searchTxt);
        }
        return searchResult;
    }

    @RequestMapping(value = "/categoryDataTables", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody String findCategoryInventory(HttpServletRequest request) throws IOException {
        HttpSession session = request.getSession(false);
        Merchant merchant = (Merchant) session.getAttribute("merchant");
        Integer pageNumber = 0;
        if (null != request.getParameter("iDisplayStart"))
            pageNumber = (Integer.valueOf(request.getParameter("iDisplayStart")) / 10) + 1;
        // Fetch search parameter
        String searchParameter = request.getParameter("sSearch");
        Integer pageDisplayLength = Integer.valueOf(request.getParameter("iDisplayLength"));
        String jsonOutput = "";
        if (merchant != null) {
            jsonOutput = categoryService.findCAtegoryInventory(merchant.getId(), pageDisplayLength, pageNumber,
                            searchParameter);
        }
        return jsonOutput;
    }

    @RequestMapping(value = "/searchCategoryByText", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody String searchCategoryByTxt(HttpServletRequest request,
                    @RequestParam(required = true) String searchTxt) throws IOException {
        HttpSession session = request.getSession(false);
        Merchant merchant = (Merchant) session.getAttribute("merchant");
        String searchResult = "";
        if (merchant != null) {
            searchResult = categoryService.searchCategoryByTxt(merchant.getId(), searchTxt);
        }
        return searchResult;
    }

    @RequestMapping(value = "/allOrdersDataTables", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody String findAllOrders(HttpServletRequest request) throws IOException {
        HttpSession session = request.getSession(false);
        Merchant merchant = (Merchant) session.getAttribute("merchant");
        Integer pageNumber = 0;
        if (null != request.getParameter("iDisplayStart"))
            pageNumber = (Integer.valueOf(request.getParameter("iDisplayStart")) / 10) + 1;
        // Fetch search parameter
        String searchParameter = request.getParameter("sSearch");
        Integer pageDisplayLength = Integer.valueOf(request.getParameter("iDisplayLength"));
        String jsonOutput = "";
        if (merchant != null) {
            jsonOutput = orderService.findAllOrderFromDataTable(merchant.getId(), pageDisplayLength, pageNumber,
                            searchParameter);
        }
        return jsonOutput;
    }

    @RequestMapping(value = "/findOrderDetailsById", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody String orderDetails(HttpServletRequest request, @RequestParam(required = true) Integer orderId)
                    throws IOException {
        return orderService.findOrderDetailsById(orderId);
    }

    @RequestMapping(value = "/searchOrderByText", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody String searchOrder(HttpServletRequest request, @RequestParam(required = true) String searchTxt)
                    throws IOException {
    	HttpSession session = request.getSession(false);
        Merchant merchant = (Merchant) session.getAttribute("merchant");
        Integer pageNumber = 0;
        if (null != request.getParameter("iDisplayStart"))
            pageNumber = (Integer.valueOf(request.getParameter("iDisplayStart")) / 10) + 1;
        // Fetch search parameter
        String searchParameter = request.getParameter("sSearch");
        Integer pageDisplayLength = Integer.valueOf(request.getParameter("iDisplayLength"));
        //String jsonOutput = "";
        String searchResult = "";
        if (merchant != null) {
            //jsonOutput = orderService.findAllOrderFromDataTable(merchant.getId(), pageDisplayLength, pageNumber,searchParameter);
            searchResult = orderService.searchOrderByText(merchant.getId(), searchTxt,pageDisplayLength,pageNumber);
        }
        /*HttpSession session = request.getSession(false);
        Merchant merchant = (Merchant) session.getAttribute("merchant");
        String searchResult = "";
        if (merchant != null) {
            searchResult = orderService.searchOrderByText(merchant.getId(), searchTxt);
        }*/
        return searchResult;
    }
}