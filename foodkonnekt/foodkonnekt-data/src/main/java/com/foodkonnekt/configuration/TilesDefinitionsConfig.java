package com.foodkonnekt.configuration;

import java.util.HashMap;
import java.util.Map;

import org.apache.tiles.Attribute;
import org.apache.tiles.Definition;
import org.apache.tiles.definition.DefinitionsFactory;

import com.foodkonnekt.util.TilesIConstant;

public final class TilesDefinitionsConfig implements DefinitionsFactory {
    private static final Map<String, Definition> tilesDefinitions = new HashMap<String, Definition>();
    private static final Attribute BASE_TEMPLATE = new Attribute(TilesIConstant.DEFAULT_LAYOUT);

    public Definition getDefinition(String name, org.apache.tiles.request.Request tilesContext) {
        return tilesDefinitions.get(name);
    }

    /**
     * @param name
     *            <code>Name of the view</code>
     * 
     * @param title
     *            <code>Page title</code>
     * @param body
     *            <code>Body JSP file path</code>
     * 
     *            <code>Adds default layout definitions</code>
     */
    private static void addDefaultLayoutDef(String name, String body, String header, String footer) {
        Map<String, Attribute> attributes = new HashMap<String, Attribute>();

        attributes.put("title", new Attribute(TilesIConstant.TITLE));
        attributes.put("header", new Attribute(header));
        attributes.put("body", new Attribute(body));
        attributes.put("footer", new Attribute(footer));
        tilesDefinitions.put(name, new Definition(name, BASE_TEMPLATE, attributes));
    }

    public static void addDefinitions() {
        addDefaultLayoutDef("home", TilesIConstant.HOME_PAGE, TilesIConstant.FRONT_HEADER, TilesIConstant.FRONT_FOOTER);
        addDefaultLayoutDef("forgotPassword", TilesIConstant.FORGOT_PAGE, TilesIConstant.FRONT_HEADER,
                        TilesIConstant.FRONT_FOOTER);
        addDefaultLayoutDef("sessionTimeOut", TilesIConstant.WELCOME_PAGE, TilesIConstant.HEADER, TilesIConstant.FOOTER);
        addDefaultLayoutDef("categoryEmpty", TilesIConstant.CATEGORY_EMPTY, TilesIConstant.HEADER, TilesIConstant.FOOTER);
        addDefaultLayoutDef("order", TilesIConstant.ORDER_PAGE, "", "");
        addDefaultLayoutDef("myOrder", TilesIConstant.MY_ORDER_PAGE, TilesIConstant.HEADER, TilesIConstant.FOOTER);
        addDefaultLayoutDef("profile", TilesIConstant.PROFILE_PAGE, TilesIConstant.HEADER, TilesIConstant.FOOTER);
        addDefaultLayoutDef("exception", TilesIConstant.EXCEPTION_PAGE, TilesIConstant.FRONT_HEADER,
                        TilesIConstant.FRONT_FOOTER);
        
        addDefaultLayoutDef("PaymentForm", TilesIConstant.PAYMENT_FORM, "","");
        addDefaultLayoutDef("OrderReceipt", TilesIConstant.ORDER_RECEIPT, "","");
        addDefaultLayoutDef("feedbackForm", TilesIConstant.FEEDBACK, "","");
        addDefaultLayoutDef("feedbackFormWalkInCustomer", TilesIConstant.FEEDBACK_WALKIN_CUSTOMER, "","");
        addDefaultLayoutDef("feedbackFormWalkInCustomer5", TilesIConstant.FEEDBACK_WALKIN_CUSTOMER5, "","");
        addDefaultLayoutDef("feedbackFormWalkInCustomer6", TilesIConstant.FEEDBACK_WALKIN_CUSTOMER6, "","");
        addDefaultLayoutDef("feedbackFormWalkInCustomer7", TilesIConstant.FEEDBACK_WALKIN_CUSTOMER7, "","");
        addDefaultLayoutDef("feedbackForm1", TilesIConstant.FEEDBACK1, "","");
        
        addDefaultLayoutDef("feedbackResponse", TilesIConstant.FEEDBACK_RESPONSE, "","");
        
        addDefaultLayoutDef("feedbackshare", TilesIConstant.FEEDBACK_SHARE, "","");
        addDefaultLayoutDef("feedbackOrderId", TilesIConstant.FEEDBACK_ORDER_ID, "","");
        addDefaultLayoutDef("displayCustomerFeedback", TilesIConstant.DISPLAY_CUSTOMER_FEEDBACK, "","");
        addDefaultLayoutDef("feedbackOrderId5", TilesIConstant.FEEDBACK_ORDER_ID5, "","");
        addDefaultLayoutDef("feedbackOrderId6", TilesIConstant.FEEDBACK_ORDER_ID6, "","");
        addDefaultLayoutDef("feedbackOrderId7", TilesIConstant.FEEDBACK_ORDER_ID7, "","");
        
        
        
        addDefaultLayoutDef("createVouchar", TilesIConstant.CREATE_VOUCHAR, "", "");
        addDefaultLayoutDef("vouchars", TilesIConstant.VOUCHARS, "", "");
        addDefaultLayoutDef("adminHome", TilesIConstant.ADMIN_HOME, "", "");
        addDefaultLayoutDef("inventory", TilesIConstant.INVENTORY, "", "");
        addDefaultLayoutDef("addLineItem", TilesIConstant.ADD_LINE_ITEM, "", "");
        addDefaultLayoutDef("adminLogin", TilesIConstant.ADMIN_LOGIN, "", "");
        addDefaultLayoutDef("getAllMerchants", TilesIConstant.GET_ALL_MERCHANT, "", "");
        addDefaultLayoutDef("getAllMerchantsByVendor", TilesIConstant.GET_ALL_MERCHANT_BY_VENDOR, "", "");
        
        addDefaultLayoutDef("uploadInventory", TilesIConstant.UPLOAD_INVENTORY, "", "");
        addDefaultLayoutDef("categoryItems", TilesIConstant.CATEGORY_ITEMS, "", "");
        
        addDefaultLayoutDef("addCategory", TilesIConstant.ADD_CATEGORY, "", "");
        addDefaultLayoutDef("allOrders", TilesIConstant.ALL_ORDERS, "", "");
        addDefaultLayoutDef("customerOrders", TilesIConstant.CUSTOMER_ORDERS, "", "");
        addDefaultLayoutDef("merchants", TilesIConstant.MERCHANT, "", "");

        addDefaultLayoutDef("modifierGroups", TilesIConstant.MODIFIERS_GROUPS, "", "");
        addDefaultLayoutDef("modifiers", TilesIConstant.MODIFIERS, "", "");
        addDefaultLayoutDef("category", TilesIConstant.CATEGORY, "", "");
        addDefaultLayoutDef("logOut", TilesIConstant.LOG_OUT, "", "");

        // Configuration screens
        addDefaultLayoutDef("welcome", TilesIConstant.WELCOME, "", "");
        addDefaultLayoutDef("uploadLogo", TilesIConstant.UPLOAD_LOGO, "", "");
        addDefaultLayoutDef("setDeliveryZone", TilesIConstant.SET_DELIVERY_ZONE, "", "");
        addDefaultLayoutDef("setPickupTime", TilesIConstant.SET_PICKUP_TIME, "", "");
        addDefaultLayoutDef("setConvenienceFee", TilesIConstant.SET_CONVENIENCE_FEE, "", "");
        addDefaultLayoutDef("adminPanel", TilesIConstant.SET_ADMIN_PANEL, "", "");
        addDefaultLayoutDef("onLineOrderLink", TilesIConstant.ONLINE_ORDER_LINK, "", "");
     
        addDefaultLayoutDef("signup", TilesIConstant.MERCHANT_SIGN_UP, "", "");
        addDefaultLayoutDef("login", TilesIConstant.LOGIN, "", "");
        addDefaultLayoutDef("forgotpassword", TilesIConstant.FORGOT_PASSWORD, "", "");
        addDefaultLayoutDef("changepassword", TilesIConstant.CHANGE_PASSWORD, "", "");

        addDefaultLayoutDef("adminLogo", TilesIConstant.ADMIN_LOGO, "", "");
        addDefaultLayoutDef("addDeliveryZone", TilesIConstant.ADMIN_DELIVERY_ZONE, "", "");
        addDefaultLayoutDef("pickUpTime", TilesIConstant.ADMIN_PICKTME, "", "");
        addDefaultLayoutDef("convenienceFee", TilesIConstant.ADMIN_CONFENCE_FEE, "", "");
        addDefaultLayoutDef("customers", TilesIConstant.CUSTOMER, "", "");
        addDefaultLayoutDef("updateDeliveryZone", TilesIConstant.UPDATE_DELIVERY_ZONE, "", "");
        addDefaultLayoutDef("deliveryZones", TilesIConstant.DELIVERY_ZONE, "", "");
        addDefaultLayoutDef("updateCategory", TilesIConstant.UPDATE_CATEGORY, "", "");
        addDefaultLayoutDef("updateVouchar", TilesIConstant.UPDATE_VOUCHAR, "", "");
        addDefaultLayoutDef("setGuestPassword", TilesIConstant.GUEST_PASSWORD, TilesIConstant.FRONT_HEADER,
                        TilesIConstant.FRONT_FOOTER);
        addDefaultLayoutDef("resetGuestPassword", TilesIConstant.RESET_PASSWORD, TilesIConstant.FRONT_HEADER,
                TilesIConstant.FRONT_FOOTER);
        
        addDefaultLayoutDef("resetAdminPassword", TilesIConstant.RESET_ADMIN_PASSWORD, "","");
    }
}
