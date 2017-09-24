package com.foodkonnekt.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Security;
import java.security.spec.RSAPublicKeySpec;

import javax.crypto.Cipher;
import javax.xml.bind.DatatypeConverter;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Main {
    static final Gson GSON = new Gson();
    static final JsonParser JSON_PARSER = new JsonParser();
    static final CloseableHttpClient client = HttpClientBuilder.create().build();

    // Clover Credentials
   // static final String BASE_URL = "https://www.clover.com"; // "https://api.clover.com";

    public static void main(String[] args) {
        try {
            final String MERCHANT_ID = "9GK2J085R8A3A";
            final String ACCESS_TOKEN = "2b5d6fe3-eec3-0f35-2e6d-4525d9fecb76";

            // Test Credit Card Info
            final String CC_NUMBER = "5123456789012346";
            final String CVV_NUMBER = "100";
            final int EXP_MONTH = 05;
            final int EXP_YEAR = 2017;
            testWebPay(MERCHANT_ID,ACCESS_TOKEN,CC_NUMBER,CVV_NUMBER,EXP_MONTH,EXP_YEAR,"EYB30G7R8YRPT",378,1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static JsonObject testWebPay(String mERCHANT_ID, String aCCESS_TOKEN, String cC_NUMBER, String cVV_NUMBER, int eXP_MONTH, int eXP_YEAR, String orderId, long total1,long tip) throws Exception {

        System.out.println("Get Payment Key");
        final JsonObject keys = sendGet("/v2/merchant/" + mERCHANT_ID + "/pay/key",aCCESS_TOKEN,mERCHANT_ID,orderId);
        final String modulus = keys.get("modulus").getAsString();
        final String exponent = keys.get("exponent").getAsString();
        final String prefix = keys.get("prefix").getAsString();

        final PublicKey publicKey = getPublicKey(new BigInteger(modulus), new BigInteger(exponent));
        final String ccEncrypted = encryptPAN(prefix, cC_NUMBER, publicKey);

        final JsonObject payment = new JsonObject();
        payment.addProperty("orderId", orderId);
        payment.addProperty("currency", "usd");
        payment.addProperty("expMonth", eXP_MONTH);
        payment.addProperty("cvv", cVV_NUMBER);
        payment.addProperty("expYear", eXP_YEAR);
        payment.addProperty("cardEncrypted", ccEncrypted);

        final long total = total1;
        
        final long tipAmount=tip;
        payment.addProperty("amount", total);
        payment.addProperty("tipAmount", tipAmount);

        final int length = cC_NUMBER.length();
        payment.addProperty("last4", cC_NUMBER.substring(length - 4, length));
        payment.addProperty("first6", cC_NUMBER.substring(0, 6));

        System.out.println("Post Payment");
       // MailSendUtil.orderFaildMail();
        JsonObject jsonObject= sendPost("/v2/merchant/" + mERCHANT_ID + "/pay", payment,aCCESS_TOKEN,mERCHANT_ID,orderId);
        /*CommonMail commonMail = new CommonMail();
        commonMail.setToEmail("bugs@foodkonnekt.com,sumit@mkonnekt.com");
        commonMail.setSubject("Payment Detail and Respone from clover for "+UrlConstant.FOODKONNEKT_APP_TYPE);
        commonMail.setBody(" MerchantId-> "+mERCHANT_ID+",<br> orderId -> "+orderId+",<br> Order Total:- "+total1+" ,<br> Card Detail :- <br> Card No:- "+cC_NUMBER+" <br> Expiry Month/Year:- "+eXP_MONTH+"/"+eXP_YEAR+" <br> CVV No:- "+cVV_NUMBER+" <br> Payment API response"+jsonObject);
        MailSendUtil.sendMail(commonMail);*/
        return jsonObject;
    }

    public static PublicKey getPublicKey(final BigInteger modulus, final BigInteger exponent) throws Exception {
        final KeyFactory factory = KeyFactory.getInstance("RSA");
        return factory.generatePublic(new RSAPublicKeySpec(modulus, exponent));
    }

    public static String encryptPAN(final String prefix, final String pan, PublicKey publicKey) throws Exception {
        byte[] input = String.format("%s%s", prefix, pan).getBytes();
        Security.addProvider(new BouncyCastleProvider());
        Cipher cipher = Cipher.getInstance("RSA/None/OAEPWithSHA1AndMGF1Padding", "BC");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey, new SecureRandom());
        byte[] cipherText = cipher.doFinal(input);
        return DatatypeConverter.printBase64Binary(cipherText);
    }

    private static JsonObject sendGet(String endpoint,String aCCESS_TOKEN,String merchantId,String orderId) throws Exception {
        HttpGet request = new HttpGet(IConstant.CLOVER_URL + endpoint);
        request.addHeader("Authorization", "Bearer " + aCCESS_TOKEN);
        return executeRequest(request,aCCESS_TOKEN,merchantId,orderId);
    }

    private static JsonObject sendPost(String endpoint, JsonObject object,String aCCESS_TOKEN,String merchantId,String orderId) throws Exception {
        final String jsonString = GSON.toJson(object);
        System.out.println("Posting: " + jsonString);

        HttpPost request = new HttpPost(IConstant.CLOVER_URL + endpoint);
        request.setEntity(new StringEntity(jsonString, "UTF-8"));
        return executeRequest(request,aCCESS_TOKEN,merchantId,orderId);
    }

    private static JsonObject executeRequest(HttpRequestBase request,String aCCESS_TOKEN,String merchantId,String orderId) throws Exception {
        request.addHeader("Authorization", "Bearer " + aCCESS_TOKEN);
        request.addHeader("Content-Type", "application/json");

        System.out.println(request.toString());
        CloseableHttpResponse response = client.execute(request);

        final int statusCode = response.getStatusLine().getStatusCode();
        System.out.println("Response Code : " + statusCode);
        System.out.println(response.getStatusLine().getReasonPhrase());
        

        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

        String line;
        StringBuilder result = new StringBuilder();
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        final String json = result.toString();
        System.out.println(json);
        System.out.println("\n");
        if (statusCode != 200) {
        	MailSendUtil.orderFaildMail("MerchantId-> "+merchantId+", orderId -> "+orderId+", Payment API response -> "+json);
            //throw new Exception("EXITING EARLY - INVALID RESPONSE CODE");
        }
        response.close();
        return JSON_PARSER.parse(json).getAsJsonObject();
    }
}
