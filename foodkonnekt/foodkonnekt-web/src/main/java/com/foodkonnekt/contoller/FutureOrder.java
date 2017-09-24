/*package com.foodkonnekt.contoller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.foodkonnekt.model.Merchant;
import com.foodkonnekt.model.OrderItem;
import com.foodkonnekt.model.OrderItemModifier;
import com.foodkonnekt.model.OrderR;
import com.foodkonnekt.util.IConstant;
import com.google.gson.Gson;

public class FutureOrder implements Job {

    public void execute(JobExecutionContext context) throws JobExecutionException {

        List<Merchant> merchants = CronScheduler.memorymerchantRepository.findAll();
        for (Merchant merchant : merchants) {
            //System.out.println("Job A is runing");
            final long ONE_MINUTE_IN_MILLIS = 60000;
            Calendar date = Calendar.getInstance();
            long t = date.getTimeInMillis();
            Date afterAddingTenMins = new Date(t + (30 * ONE_MINUTE_IN_MILLIS));
            Date betWeenWithTime = new Date(t + (50 * ONE_MINUTE_IN_MILLIS));

       System.out.println("-------" + afterAddingTenMins+"------"+betWeenWithTime);
            List<OrderR> orderRs = CronScheduler.mermoryOrderRepository
                            .findByFulFilledDateAndIsFutureOrderAndMerchantId(afterAddingTenMins,
                                            IConstant.BOOLEAN_TRUE, merchant.getId(), betWeenWithTime);
            System.out.println(orderRs);
            for (OrderR orderR : orderRs) {
                String orderDetails = "";
                Map<String, Map<String, String>> notf = new HashMap<String, Map<String, String>>();
                Map<String, String> notification = new HashMap<String, String>();
                notification.put("appEvent", "notification");
                Map<String, String> order = new HashMap<String, String>();
                order.put("total", Double.toString(orderR.getOrderPrice()));
                order.put("tax", orderR.getTax());
                List<Map<String, String>> productList = new ArrayList<Map<String, String>>();
                List<OrderItem> orderItems = CronScheduler.memoryOrderItemRepository.findByOrderId(orderR.getId());
                for (OrderItem orderItem : orderItems) {
                    if (orderItem != null) {
                        if (orderItem.getItem() != null) {
                            String items = "<tr style='font-weight:600;text-transform:capitalize;font-family:arial'><td width='200px;'>"
                                            + orderItem.getItem().getName()
                                            + "</td><td width='100px;' style='text-align:center'>"
                                            + orderItem.getQuantity()
                                            + "</td><td width='100px;' style='text-align:center'>"
                                            + "$"
                                            + orderItem.getItem().getPrice() + "</td></tr>";
                            orderDetails = orderDetails + "" + "<b>" + items + "</b>";
                            List<Map<String, String>> extraList = new ArrayList<Map<String, String>>();
                            Map<String, String> poduct = new HashMap<String, String>();
                            poduct.put("product_id", orderItem.getItem().getPosItemId());
                            poduct.put("name", orderItem.getItem().getName());
                            poduct.put("price", Double.toString(orderItem.getItem().getPrice()));
                            poduct.put("qty", Integer.toString(orderItem.getQuantity()));
                            List<OrderItemModifier> itemModifiers = CronScheduler.memoryItemModifierRepository
                                            .findByOrderItemId(orderItem.getId());
                            for (OrderItemModifier itemModifier : itemModifiers) {
                                String modifiers = "<tr style='text-transform:capitalize;font-family:arial;margin-top:5px;font-size:12px'><td width='200px;'>"
                                                + itemModifier.getModifiers().getName()
                                                + "</td><td width='100px;' style='text-align:center'>"
                                                + itemModifier.getQuantity()
                                                + " </td><td width='100px;' style='text-align:center'> "
                                                + "$"
                                                + itemModifier.getModifiers().getPrice() + "</td></tr>";
                                orderDetails = orderDetails + "" + modifiers;
                                Map<String, String> exctra = new HashMap<String, String>();
                                exctra.put("id", itemModifier.getModifiers().getPosModifierId());
                                exctra.put("price", Double.toString(itemModifier.getModifiers().getPrice()));
                                exctra.put("name", itemModifier.getModifiers().getName());
                                exctra.put("qty", Integer.toString(orderItem.getQuantity()));
                                extraList.add(exctra);
                            }
                            Gson gson = new Gson();
                            String extraJson = gson.toJson(extraList);
                            poduct.put("extras", extraJson);
                            productList.add(poduct);
                        }
                    }
                }
                Gson gson = new Gson();
                String productJson = gson.toJson(productList);
                order.put("productItems", productJson);
                // order.put("productItems", productList.toString());
                String orderJson = gson.toJson(order);
                notification.put(
                                "payload",
                                orderR.getOrderPosId() + "@#You got a new order(" + orderR.getOrderPosId() + ") at "
                                                + orderR.getCreatedOn() + "@# $" + orderR.getOrderPrice() + "@# "
                                                + orderR.getOrderType() + "@#" + orderJson + "@#"
                                                + orderR.getMerchant().getPosMerchantId() + "@#"
                                                + orderR.getOrderNote() + " @#" + orderR.getPosPaymentId());

                // +"@#"+tip add this to notification payload if we add tip amount to show on popup on
                // notification app side
                notf.put("notification", notification);
                String notificationJson = gson.toJson(notf);
                notificationJson = notificationJson.trim().replaceAll("\\\\+\"", "'").replace("'[", "[")
                                .replace("]'", "]");
                // notificationJson=notificationJson.trim().replace("'[", "[").replace("]'", "]");
                // notificationJson=notificationJson.trim().replace("]'", "]");
                System.out.println(notificationJson);
                orderDetails = "<table width='300px;'><tbody>" + orderDetails + "</table></tbody>";
                sendNotification(notificationJson, orderR.getMerchant().getPosMerchantId(), orderR.getMerchant()
                                .getAccessToken());
            }
        }

    }

    private void sendNotification(String notificationJson, String merchantId, String accessToken) {
        HttpPost postRequest = new HttpPost(IConstant.CLOVER_V2_URL + merchantId + "/apps/" + IConstant.CLOVER_APP_ID
                        + "/notify?access_token=" + accessToken);
        System.out.println("=====" + convertToStringJson(postRequest, notificationJson));
    }

    public static String convertToStringJson(HttpPost postRequest, String customerJson) {
        StringBuilder responseBuilder = new StringBuilder();
        try {
            HttpClient httpClient = HttpClientBuilder.create().build();
            StringEntity input = new StringEntity(customerJson);
            input.setContentType("application/json");
            postRequest.setEntity(input);
            HttpResponse response = httpClient.execute(postRequest);
            System.out.println("Output from Server .... \n");
            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            String line = "";
            while ((line = rd.readLine()) != null) {
                responseBuilder.append(line);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseBuilder.toString();
    }
}
*/