package com.foodkonnekt.serviceImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foodkonnekt.model.Clover;
import com.foodkonnekt.model.Customer;
import com.foodkonnekt.model.Item;
import com.foodkonnekt.model.Merchant;
import com.foodkonnekt.model.Modifiers;
import com.foodkonnekt.model.OrderItem;
import com.foodkonnekt.model.OrderItemModifier;
import com.foodkonnekt.model.OrderR;
import com.foodkonnekt.repository.CustomerrRepository;
import com.foodkonnekt.repository.ItemmRepository;
import com.foodkonnekt.repository.MerchantRepository;
import com.foodkonnekt.repository.ModifiersRepository;
import com.foodkonnekt.repository.OrderItemRepository;
import com.foodkonnekt.repository.OrderRepository;
import com.foodkonnekt.service.MigrationService;
import com.foodkonnekt.util.CloverUrlUtil;
import com.foodkonnekt.util.DateUtil;
import com.foodkonnekt.util.IConstant;
import com.foodkonnekt.util.MailSendUtil;

@Service
public class MigrationServiceImpl implements MigrationService {

    @Autowired
    private MerchantRepository merchantRepository;

    @Autowired
    private CustomerrRepository customerrRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ItemmRepository itemmRepository;

    @Autowired
    private ModifiersRepository modifiersRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    public String migrateCustomer(String merchantPOSId,String startDate, String endDate) {

        List<PHPMerchant> phpMerchantList = getPHPMerchants(merchantPOSId);
        String response = "";

        for (PHPMerchant phpMerchant : phpMerchantList) {

            if (phpMerchant.getMerchantPosId() != null && phpMerchant.getAccessToken() != null
                            && phpMerchant.getId() != 0) {
                int merchantId = phpMerchant.getId();
                Clover clover = new Clover();

                clover.setMerchantId(phpMerchant.getMerchantPosId());
                clover.setAuthToken(phpMerchant.getAccessToken());
                clover.setInstantUrl(IConstant.CLOVER_INSTANCE_URL);
                clover.setUrl(IConstant.CLOVER_URL);

                Merchant merchant = merchantRepository.findByPosMerchantId(phpMerchant.getMerchantPosId());
                if (merchant != null) {
                    Statement stmt = getStatement();
                    Statement stmtOrder = getStatement();
                    Statement stmtOrderItem = getStatement();
                    ResultSet rs;
                    ResultSet rsOrder;
                    ResultSet rsOrderItem;
                    try {
                    	String sqlQuery="select  ccm.clover_customer_id ,c.id ,c.c_email  from foodkonnektfood_delivery_clients c "
                                + "left join foodkonnektfood_delivery_customer_client_map ccm on ccm.customer_id=c.id "
                                + "where c.user_id=" + merchantId+" and c.created BETWEEN '"+startDate+"' AND '"+endDate+"' ";
                    	System.out.println(sqlQuery);
                    	
                        rs = stmt.executeQuery(sqlQuery);
                        rs.last();
                        int total = rs.getRow();
                        int migrated = 0;
                        int updated=0;
                        rs.beforeFirst();
                        while (rs.next()) {
                            System.out.println("Customer Id " + rs.getString(1));
                            String customerCloverID= rs.getString(1);
                            String customerEmailId= rs.getString(3);
                            
                            
                            System.out.println("Customer DB Id " + rs.getInt(2));
                            String customerId = rs.getString(1);
                            
                            List<Customer> customers=customerrRepository.findByEmailIdAndMerchantId(customerEmailId,merchant.getId());
                            if(customers.size()>0){
                            	for(Customer customer:customers){
                            		customer.setCustomerPosId(customerCloverID);
                            		customerrRepository.save(customer);
                            		
                            		System.out.println("customer updated-->"+ ++updated);
                            	}
                            }else{
                            if (customerId != null && !customerId.isEmpty()) {

                                String customerJson = CloverUrlUtil.getCustomer(clover, customerId);
                                if (customerJson.contains("id")) {
                                    Customer customer = setCustomer(customerJson, merchant);
                                    if (customer != null && customer.getEmailId() != null) {

                                        customer.setCustomerType("php");
                                        customerrRepository.save(customer);

                                        rsOrder = stmtOrder
                                                        .executeQuery("select o.id , o.type , o.status ,o.payment_method, o.price ,"
                                                                        + "o.price_delivery ,o.convenience_fee ,"
                                                                        + "o.discount ,o.subtotal , o.tax , o.total ,o.clover_order_id ,o.created ,o.order_instruction "
                                                                        + "from foodkonnektfood_delivery_orders o where o.client_id="
                                                                        + rs.getInt(2));
                                        while (rsOrder.next()) {
                                            String cloverOrderId = rsOrder.getString("o.clover_order_id");
                                            OrderR orderR = new OrderR();
                                            orderR.setConvenienceFee(rsOrder.getString("o.convenience_fee"));
                                            orderR.setCustomer(customer);
                                            orderR.setDeliveryFee(rsOrder.getString("o.price_delivery"));
                                            orderR.setMerchant(merchant);
                                            orderR.setOrderPosId(rsOrder.getString("o.clover_order_id"));
                                            orderR.setOrderPrice(rsOrder.getDouble("o.total"));
                                            orderR.setOrderType(rsOrder.getString("o.type"));
                                            orderR.setPaymentMethod(rsOrder.getString("o.payment_method"));
                                            orderR.setSubTotal(rsOrder.getString("o.subtotal"));
                                            orderR.setTax(rsOrder.getString("o.tax"));
                                            orderR.setCreatedOn(rsOrder.getDate("o.created"));
                                            orderR.setOrderNote(rsOrder.getString("o.order_instruction"));
                                            String orderStatus = rsOrder.getString("o.status");
                                            if (orderStatus.equals("confirmed"))
                                                orderR.setIsDefaults(1);
                                            else if (orderStatus.equals("cancelled")) {
                                                orderR.setIsDefaults(2);
                                            } else {
                                                orderR.setIsDefaults(0);
                                            }

                                            orderRepository.save(orderR);

                                            String orderItemsJson = CloverUrlUtil.getOrderItems(clover, cloverOrderId);
                                            Map<String, String> orderItemsMap = setOrderItems(orderItemsJson);
                                            int phpOrderId = rsOrder.getInt("o.id");
                                            rsOrderItem = stmtOrderItem
                                                            .executeQuery("select oi.order_id ,oi.foreign_id , oi.cnt ,p.id ,pm.clover_lineitem_id from foodkonnektfood_delivery_orders_items oi "
                                                                            + "left join foodkonnektfood_delivery_products p on p.id = oi.foreign_id "
                                                                            + "left join foodkonnektfood_delivery_lineitem_product_map pm on pm.product_id=p.id "
                                                                            + "where oi.order_id= "
                                                                            + phpOrderId
                                                                            + " and oi.type='product'");

                                            while (rsOrderItem.next()) {
                                                OrderItem orderItem = new OrderItem();

                                                orderItem.setOrder(orderR);
                                                orderItem.setQuantity(rsOrderItem.getInt("oi.cnt"));
                                                String itemPosId = rsOrderItem.getString("pm.clover_lineitem_id");
                                                if (itemPosId != null && !itemPosId.isEmpty()) {
                                                    Item item = itemmRepository.findByPosItemId(itemPosId);
                                                    if (item != null) {
                                                        if (cloverOrderId != null && !cloverOrderId.isEmpty()) {
                                                            String orderItemId = orderItemsMap.get(itemPosId);
                                                            if (orderItemId != null && !orderItemId.isEmpty()) {
                                                                String OrderItemModifierJson = CloverUrlUtil
                                                                                .getOrderItemsWithModifier(clover,
                                                                                                cloverOrderId,
                                                                                                orderItemId);
                                                                if (OrderItemModifierJson.contains("id")) {
                                                                    List<OrderItemModifier> itemModifiers = setItemModifiers(
                                                                                    OrderItemModifierJson, orderItem);
                                                                    orderItem.setOrderItemModifiers(itemModifiers);
                                                                }
                                                            }
                                                        }
                                                        orderItem.setItem(item);
                                                        orderItemRepository.save(orderItem);
                                                    }
                                                }
                                            }

                                            migrated++;

                                        }
                                    }
                                }
                            } }

                        }
                        response = "Got " + total + "Customers and Migrated " + migrated;

                    } catch (Exception e) {
                        response = e.getMessage();
                        e.printStackTrace();
                    }
                }
            }
        }
        return response;

    }

    Statement getStatement() {
        Connection con = null;
        Statement stmt = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");

            String DB_URL = "jdbc:mysql://localhost:3306/foodkonn_FoodKonnekt";
            String USER_NAME = "foodkonn_user";
            String PASSWORD = "XC3hT#MC?!.L";
            
            /*String USER_NAME = "root";
            String PASSWORD = "root";*/
            
            con = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
            stmt = con.createStatement();
        } catch (Exception e) {
            if (e != null) {
                MailSendUtil.sendExceptionByMail(e);
            }
            e.printStackTrace();
        }
        return stmt;
    }

    Customer setCustomer(String customerJson, Merchant merchant) {
        JSONObject customerObject = new JSONObject(customerJson);

        
        try {
        	Customer customer = new Customer();
        	JSONObject emailJson = customerObject.getJSONObject("emailAddresses");
            JSONArray emailArray = emailJson.getJSONArray("elements");
            for (Object jObj : emailArray) {
                JSONObject emailObject = (JSONObject) jObj;
                customer.setEmailId(emailObject.getString("emailAddress"));
                List<Customer> customers = customerrRepository.findByEmailIdAndMerchantId(customer.getEmailId(),
                                merchant.getId());
                if (customers != null && customers.size() > 0) {
                    return null;
                }
            }
            
            customer.setCustomerPosId(customerObject.getString("id"));
            
            customer.setFirstName(customerObject.getString("firstName"));
            customer.setLastName(customerObject.getString("lastName"));
            Long millis = customerObject.getLong("customerSince");
            Date date = new Date(millis);

            customer.setCreatedDate(DateUtil.getDDMMYYYY(date));
            

            JSONObject phoneJson = customerObject.getJSONObject("phoneNumbers");
            JSONArray phoneArray = phoneJson.getJSONArray("elements");
            for (Object jObj : phoneArray) {
                JSONObject phoneObject = (JSONObject) jObj;
                customer.setPhoneNumber(phoneObject.getString("phoneNumber"));
            }
            customer.setMerchantt(merchant);
            return customer;
        } catch (Exception e) {
            if (e != null) {
                MailSendUtil.sendExceptionByMail(e);
            }
            e.printStackTrace();
            return null;
        }
       
    }

    List<OrderItemModifier> setItemModifiers(String OrderItemModifierJson, OrderItem orderItem) {
        List<OrderItemModifier> itemModifiers = new ArrayList<OrderItemModifier>();
        try {
            if (OrderItemModifierJson.contains("modifications")) {
                JSONObject orderItemModifierObject = new JSONObject(OrderItemModifierJson);
                JSONObject modifierJson = orderItemModifierObject.getJSONObject("modifications");
                JSONArray modiferArray = modifierJson.getJSONArray("elements");
                for (Object jObj : modiferArray) {
                    OrderItemModifier orderItemModifier = new OrderItemModifier();
                    JSONObject modifierObject = (JSONObject) jObj;
                    JSONObject JsonObject = modifierObject.getJSONObject("modifier");
                    String modifierCloverId = JsonObject.getString("id");
                    Modifiers modifiers = modifiersRepository.findByPosModifierId(modifierCloverId);
                    orderItemModifier.setModifiers(modifiers);
                    orderItemModifier.setOrderItem(orderItem);
                    orderItemModifier.setQuantity(orderItem.getQuantity());
                    itemModifiers.add(orderItemModifier);
                }
            }
        } catch (Exception e) {
            if (e != null) {
                MailSendUtil.sendExceptionByMail(e);
            }
            e.printStackTrace();
        }
        return itemModifiers;
    }

    Map<String, String> setOrderItems(String OrderItemsJson) {
        Map<String, String> orderItemsMap = new HashMap<String, String>();
        try {
            if (OrderItemsJson.contains("elements")) {
                JSONObject jObject = new JSONObject(OrderItemsJson);
                JSONArray orderItemsArray = jObject.getJSONArray("elements");
                for (Object jObj : orderItemsArray) {
                    JSONObject orderItemObject = (JSONObject) jObj;
                    String itemRefId = orderItemObject.getString("id");
                    JSONObject itemObject = orderItemObject.getJSONObject("item");
                    String itemId = itemObject.getString("id");
                    orderItemsMap.put(itemId, itemRefId);
                }
            }
        } catch (Exception e) {
            if (e != null) {
                MailSendUtil.sendExceptionByMail(e);
            }
            e.printStackTrace();
        }
        return orderItemsMap;
    }

    class PHPMerchant {
        private int id;
        private String merchantPosId;
        private String accessToken;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getMerchantPosId() {
            return merchantPosId;
        }

        public void setMerchantPosId(String merchantPosId) {
            this.merchantPosId = merchantPosId;
        }

        public String getAccessToken() {
            return accessToken;
        }

        public void setAccessToken(String accessToken) {
            this.accessToken = accessToken;
        }
    }

    List<PHPMerchant> getPHPMerchants(String merchantPOSId) {
        String query = "select u.id ,  lm.merchant_id , lm.access_token from foodkonnektfood_delivery_users u left join foodkonnektfood_delivery_locations_map lm on lm.merchant_id=u.merchant_id where u.email!='mobile@mkonnekt.com' and u.merchant_id='"
                        + merchantPOSId + "'";
        Statement stmt = getStatement();
        ResultSet rs;
        List<PHPMerchant> phpMerchantList = new ArrayList<PHPMerchant>();
        try {
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                PHPMerchant phpMerchant = new PHPMerchant();
                phpMerchant.setId(rs.getInt(1));
                phpMerchant.setMerchantPosId(rs.getString(2));
                phpMerchant.setAccessToken(rs.getString(3));
                phpMerchantList.add(phpMerchant);
            }
        } catch (Exception e) {
            if (e != null) {
                MailSendUtil.sendExceptionByMail(e);
            }
            e.printStackTrace();
        }
        return phpMerchantList;
    }
}
