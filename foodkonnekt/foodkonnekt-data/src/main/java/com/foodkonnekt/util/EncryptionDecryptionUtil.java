package com.foodkonnekt.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Base64;

public class EncryptionDecryptionUtil {

    /**
     * Encrypt original string
     * 
     * @param original
     * @return Encrypt string
     */
    public static String encryption(String original) {
        byte[] encoded = Base64.encodeBase64(original.getBytes());
        return new String(encoded);
    }

    /**
     * Decrypt string to original
     * 
     * @param decrypt
     * @return original string
     */
    public static String decryption(String decrypt) {
        byte[] decoded = Base64.decodeBase64(decrypt);
        return new String(decoded);
    }
    
    public static String encryptString(String variable){
    	MessageDigest md;
    	StringBuffer sb = new StringBuffer();
		try {
			md = MessageDigest.getInstance("SHA-256");
			md.update(variable.getBytes());

	        byte byteData[] = md.digest();

	        //convert the byte to hex format method 1
	        
	        for (int i = 0; i < byteData.length; i++) {
	         sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
	        }
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
         return sb.toString();
        
    }
    
   /* public static void main(String[] args)throws Exception
    {
    	String password = "12";
    	
    	System.out.println("Hex format : " + encryptString(password));

    }*/
}
