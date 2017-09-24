package com.foodkonnekt.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.foodkonnekt.model.Address;
import com.foodkonnekt.model.CommonMail;
import com.foodkonnekt.model.Customer;
import com.foodkonnekt.model.CustomerFeedback;
import com.foodkonnekt.model.CustomerFeedbackAnswer;
import com.foodkonnekt.model.Merchant;
import com.foodkonnekt.model.MerchantLogin;
import com.foodkonnekt.model.MerchantSubscription;
import com.foodkonnekt.model.OrderR;
import com.foodkonnekt.model.Subscription;

public class MailSendUtil {

	public static boolean sendMail(CommonMail commonMail) {
		try {
			Message message = new MimeMessage(
					SendMailProperty.mailProperty(IConstant.FROM_EMAIL_ID, IConstant.FROM_PASSWORD));
			message.setFrom(new InternetAddress(IConstant.FROM_EMAIL_ID));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(commonMail.getToEmail()));
			message.setSubject(commonMail.getSubject());
			String msg = "Dear<br>";
			msg += commonMail.getBody();
			msg += " <br>";
			msg += " <br>";
			msg += "<b>Regards,</b><br>";
			msg += "FoodKonnekt";
			message.setContent(msg, "text/html");
			Transport.send(message);
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
		return true;
	}

	public static String forgotPasswordEmail(Customer customer) {
		try {
			String cusID = EncryptionDecryptionUtil.encryptString(String.valueOf(customer.getId()));
			String marchantId = String.valueOf(customer.getMerchantt().getId());
			String timeLog = String.valueOf(System.currentTimeMillis());
			Message message = new MimeMessage(
					SendMailProperty.mailProperty(IConstant.FROM_EMAIL_ID, IConstant.FROM_PASSWORD));
			message.setFrom(new InternetAddress(IConstant.FROM_EMAIL_ID));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(customer.getEmailId()));
			message.setSubject("Regards:Forgot password");
			/*String msg = "Dear " + customer.getFirstName() + "<br>";
			msg += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;We have received a request to reset your password .Please find your reset password link below::<br>";
			msg += " <br>";
			msg += " <br>";
			msg += "Click to : " + UrlConstant.WEB_BASE_URL + "/changepassword?email=" + customer.getEmailId()
					+ "&merchantId=" + marchantId + "&userId=" + cusID + "&tLog="
					+ EncryptionDecryptionUtil.encryption(timeLog) + "<br>";
			msg += " <br>";
			msg += " <br>";
			msg += " <br>";
			msg += "<b>Regards,</b><br>";
			msg += "FoodKonnekt";*/
			
			String merchantLogo="";
			if (customer.getMerchantt().getMerchantLogo() == null) {
                merchantLogo = "http://www.dev.foodkonnekt.com/app/foodnew.jpg";
            } else {
                merchantLogo = UrlConstant.BASE_PORT + customer.getMerchantt().getMerchantLogo();
            }
			
			String msg = "<div style='width: 700px; height: auto; border: solid 1px #ddd; box-shadow: 0px 0px 6px #ddd; background-color: #f2f2f2; text-align: center; margin: 40px 0px 0px 35px;'>"
					+ "<div style='width: 700px; height: 85px; padding: 20px 0px; text-align: center;'>&nbsp;<img src='"
					+ merchantLogo + "' height='80' /></div>"
					+ "<div style='background-color: #fff; padding: 20px 0px 0px 0px; width: 90%; margin: 0 auto 65px auto;'>"
					+ "<p style='font-size: 20px; color: #e6880f;'>Dear " + customer.getFirstName() + ",</p>"
					+ "<p style='font-size: 14px;text-align: left;padding-left:10px;'>We have received a request to reset your password .Please find your reset password link below: &nbsp;</p>";
			msg = msg+"<br /><br /> <a style='text-decoration: none; margin: 16px 0px; display: inline-block; background-color: #f8981d; color: #fff; padding: 10px 12px; border-radius: 10px; font-weight: 600; font-size: 15px;' href='" + UrlConstant.WEB_BASE_URL + "/changepassword?email=" + customer.getEmailId()
					+ "&merchantId=" + marchantId + "&userId=" + cusID + "&tLog="
					+ EncryptionDecryptionUtil.encryption(timeLog) + "' target='_blank'>Click here</a><br /> Note: Link will valid till next 30 min only .";

			msg = msg
					+ "<p><Order details: items, extras, price, fees, promo discount, total $><br>For any further questions, ";
			if(customer.getMerchantt().getPhoneNumber()!=null) { 
				msg = msg +"Please call us at " + customer.getMerchantt().getPhoneNumber() + "";}
			else{
				msg = msg +"Please call our store";
			}
					msg = msg +" <br><br>Regards,<br>" + customer.getMerchantt().getName()
					+ "<br><br>";
			msg = msg + "</div>";
			msg = msg + "<p>We appreciate your business with us!</p>"
					+ "<div style='height: 70px; text-align: center;'>&nbsp;<img src='http://www.dev.foodkonnekt.com/app/foodnew.jpg' alt='' width='200' height='63' /></div>"
					+ "</div>"
					+ "<p style='color: #676767; font-size: 11px;'>Automated email. Please do not reply to this sender email.</p>";
			
			
			message.setContent(msg, "text/html");
			Transport.send(message);
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
		return null;
	}
	
	public static String forgotAdminPasswordEmail(Customer customer) {
		try {
			String cusID = EncryptionDecryptionUtil.encryptString(String.valueOf(customer.getId()));
			String marchantId = String.valueOf(customer.getMerchantt().getId());
			String timeLog = String.valueOf(System.currentTimeMillis());
			Message message = new MimeMessage(
					SendMailProperty.mailProperty(IConstant.FROM_EMAIL_ID, IConstant.FROM_PASSWORD));
			message.setFrom(new InternetAddress(IConstant.FROM_EMAIL_ID));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(customer.getEmailId()));
			message.setSubject("Regards:Forgot password");
			/*String msg = "Dear " + customer.getFirstName() + "<br>";
			msg += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;We have received a request to reset your password .Please find your reset password link below::<br>";
			msg += " <br>";
			msg += " <br>";
			msg += "Click to : " + UrlConstant.WEB_BASE_URL + "/changepassword?email=" + customer.getEmailId()
					+ "&merchantId=" + marchantId + "&userId=" + cusID + "&tLog="
					+ EncryptionDecryptionUtil.encryption(timeLog) + "<br>";
			msg += " <br>";
			msg += " <br>";
			msg += " <br>";
			msg += "<b>Regards,</b><br>";
			msg += "FoodKonnekt";*/
			
			String merchantLogo="";
			if (customer.getMerchantt().getMerchantLogo() == null) {
                merchantLogo = "http://www.dev.foodkonnekt.com/app/foodnew.jpg";
            } else {
                merchantLogo = UrlConstant.BASE_PORT + customer.getMerchantt().getMerchantLogo();
            }
			
			String msg = "<div style='width: 700px; height: auto; border: solid 1px #ddd; box-shadow: 0px 0px 6px #ddd; background-color: #f2f2f2; text-align: center; margin: 40px 0px 0px 35px;'>"
					+ "<div style='width: 700px; height: 85px; padding: 20px 0px; text-align: center;'>&nbsp;<img src='"
					+ merchantLogo + "' height='80' /></div>"
					+ "<div style='background-color: #fff; padding: 20px 0px 0px 0px; width: 90%; margin: 0 auto 65px auto;'>"
					+ "<p style='font-size: 20px; color: #e6880f;'>Dear " + customer.getFirstName() + ",</p>"
					+ "<p style='font-size: 14px;text-align: left;padding-left:10px;'>We have received a request to reset your password .Please find your reset password link below: &nbsp;</p>";
			msg = msg+"<br /><br /> <a style='text-decoration: none; margin: 16px 0px; display: inline-block; background-color: #f8981d; color: #fff; padding: 10px 12px; border-radius: 10px; font-weight: 600; font-size: 15px;' href='" + UrlConstant.BASE_URL + "/changeAdminpassword?email=" + customer.getEmailId()
					+ "&merchantId=" + marchantId + "&userId=" + cusID + "&tLog="
					+ EncryptionDecryptionUtil.encryption(timeLog) + "' target='_blank'>Click here</a><br /> Note: Link will valid till next 30 min only .";

			msg = msg
					+ "<p><Order details: items, extras, price, fees, promo discount, total $><br>For any further questions, ";
			if(customer.getMerchantt().getPhoneNumber()!=null) { 
				msg = msg +"Please call us at " + customer.getMerchantt().getPhoneNumber() + "";}
			else{
				msg = msg +"Please call our store";
			}
					msg = msg +" <br><br>Regards,<br>Foodkonnekt<br><br>";
			msg = msg + "</div>";
			msg = msg + "<p>We appreciate your business with us!</p>"
					+ "<div style='height: 70px; text-align: center;'>&nbsp;<img src='http://www.dev.foodkonnekt.com/app/foodnew.jpg' alt='' width='200' height='63' /></div>"
					+ "</div>"
					+ "<p style='color: #676767; font-size: 11px;'>Automated email. Please do not reply to this sender email.</p>";
			
			
			message.setContent(msg, "text/html");
			Transport.send(message);
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
		return null;
	}

	public static String forgotMerchantPasswordEmail(MerchantLogin merchantLogin) {
		try {
			String marchantId = EncryptionDecryptionUtil.encryptString(String.valueOf(merchantLogin.getId()));
			//String marchantId = String.valueOf(merchantLogin.getMerchant().getId());
			String timeLog = String.valueOf(System.currentTimeMillis());
			Message message = new MimeMessage(
					SendMailProperty.mailProperty(IConstant.FROM_EMAIL_ID, IConstant.FROM_PASSWORD));
			message.setFrom(new InternetAddress(IConstant.FROM_EMAIL_ID));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(merchantLogin.getEmailId()));
			message.setSubject("Regards:Forgot password");
			/*String msg = "Dear " + customer.getFirstName() + "<br>";
			msg += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;We have received a request to reset your password .Please find your reset password link below::<br>";
			msg += " <br>";
			msg += " <br>";
			msg += "Click to : " + UrlConstant.WEB_BASE_URL + "/changepassword?email=" + customer.getEmailId()
					+ "&merchantId=" + marchantId + "&userId=" + cusID + "&tLog="
					+ EncryptionDecryptionUtil.encryption(timeLog) + "<br>";
			msg += " <br>";
			msg += " <br>";
			msg += " <br>";
			msg += "<b>Regards,</b><br>";
			msg += "FoodKonnekt";*/
			
			String merchantLogo="";
			//if (merchantLogin.getMerchant().getMerchantLogo() == null) {
                merchantLogo = "http://www.dev.foodkonnekt.com/app/foodnew.jpg";
            /*} else {
                merchantLogo = UrlConstant.BASE_PORT + customer.getMerchantt().getMerchantLogo();
            }*/
			
			String msg = "<div style='width: 700px; height: auto; border: solid 1px #ddd; box-shadow: 0px 0px 6px #ddd; background-color: #f2f2f2; text-align: center; margin: 40px 0px 0px 35px;'>"
					+ "<div style='width: 700px; height: 85px; padding: 20px 0px; text-align: center;'>&nbsp;<img src='"
					+ merchantLogo + "' height='80' /></div>"
					+ "<div style='background-color: #fff; padding: 20px 0px 0px 0px; width: 90%; margin: 0 auto 65px auto;'>"
					+ "<p style='font-size: 20px; color: #e6880f;'>Dear " + merchantLogin.getMerchant().getName() + ",</p>"
					+ "<p style='font-size: 14px;text-align: left;padding-left:10px;'>We have received a request to reset your password .Please find your reset password link below: &nbsp;</p>";
			msg = msg+"<br /><br /> <a style='text-decoration: none; margin: 16px 0px; display: inline-block; background-color: #f8981d; color: #fff; padding: 10px 12px; border-radius: 10px; font-weight: 600; font-size: 15px;' href='" + UrlConstant.BASE_URL + "/changepassword?email=" + merchantLogin.getEmailId()
					+ "&merchantId=" + marchantId + "&tLog="
					+ EncryptionDecryptionUtil.encryption(timeLog) + "' target='_blank'>Click here</a><br /> Note: Link will valid till next 30 min only .";

			msg = msg
					+ "<p><Order details: items, extras, price, fees, promo discount, total $><br>For any further questions, ";
			/*if(customer.getMerchantt().getPhoneNumber()!=null) { 
				msg = msg +"Please call us at " + customer.getMerchantt().getPhoneNumber() + "";}
			else{
				msg = msg +"Please call our store";
			}*/
					msg = msg +" <br><br>Regards, <br> FoodKonnekt <br><br>";
			msg = msg + "</div>";
			msg = msg + "<p>We appreciate your business with us!</p>"
					+ "<div style='height: 70px; text-align: center;'>&nbsp;<img src='http://www.dev.foodkonnekt.com/app/foodnew.jpg' alt='' width='200' height='63' /></div>"
					+ "</div>"
					+ "<p style='color: #676767; font-size: 11px;'>Automated email. Please do not reply to this sender email.</p>";
			
			
			message.setContent(msg, "text/html");
			Transport.send(message);
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
		return null;
	}
	
	public static String customerRegistartionConfirmation(Customer customer, String logoImage,String merchantName) {
		try {
			Message message = new MimeMessage(
					SendMailProperty.mailProperty(IConstant.FROM_EMAIL_ID, IConstant.FROM_PASSWORD));
			message.setFrom(new InternetAddress(IConstant.FROM_EMAIL_ID));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(customer.getEmailId()));
			message.setSubject("Thank you for registering");
			String msg = "<div style='width: 700px; height: auto; border: solid 1px #ddd; box-shadow: 0px 0px 6px #ddd; background-color: #f2f2f2; text-align: center; margin: 40px 0px 0px 35px;'><div style='width: 700px; height: 85px; padding: 20px 0px; text-align: center;'>&nbsp;<img src='"
					+ logoImage
					+ "' height='80' /></div><div style='background-color: #fff; padding: 20px 0px 0px 0px; width: 90%; margin: 0 auto 65px auto;'><p style='font-size: 20px; color: #e6880f;'>Dear "
					+ customer.getFirstName()
					+ ",</p><p style='font-size: 14px;'>Thank you for registering. Your account has been created.<br /> Please use the login details below to come back and order with us in future.</p><div style='text-align: left; padding: 5px 20px;'><table><tbody><tr><td width='90'>Name</td><td>:</td><td>"
					+ customer.getFirstName() + "</td></tr><tr><td>Email</td><td>:</td><td>" + customer.getEmailId()
					+ "</td></tr><tr><td>Phone</td><td>:</td><td>" + customer.getPhoneNumber()
					+ "</td></tr></tbody></table><br /><br /><p>Thank you. We appreciate your business with us!</p>Regards,<br />"+merchantName+"</div><div style='height: 70px; text-align: center;'>&nbsp;<img src='http://www.dev.foodkonnekt.com/app/foodnew.jpg' alt='' width='200' height='63' /></div></div><p style='color: #676767; font-size: 11px;'>Automated email. Please do not reply to this sender email.</p></div>'";
			message.setContent(msg, "text/html");
			Transport.send(message);
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
		return null;
	}
	
	public static String productInstallationMail(Merchant merchant,String posName,String status) {
		try {
			if(!UrlConstant.FOODKONNEKT_APP_TYPE.equals("Local")){
			if(merchant!=null){
				String merchantName="NA";
				String address="NA";
				String subscription="NA";
				String website="NA";
				String phoneNo="NA";
				String emailid="NA";
				if(merchant.getName()!=null && !merchant.getName().isEmpty()){
					merchantName=merchant.getName();
				}
				if(merchant.getPhoneNumber()!=null && !merchant.getPhoneNumber().isEmpty()){
					phoneNo=merchant.getPhoneNumber();
				}
				if(merchant.getOwner()!=null && merchant.getOwner().getEmail()!=null && !merchant.getOwner().getEmail().isEmpty()){
					emailid=merchant.getOwner().getEmail();
				}
				
				if(merchant.getMerchantSubscriptions()!=null &&  merchant.getMerchantSubscriptions().size()> 0){
					for(MerchantSubscription merchantSubscription :merchant.getMerchantSubscriptions()){
						
						Subscription subs = merchantSubscription.getSubscription();
						if(subs!=null){
							if(subs.getSubscriptionPlan()!=null)
								subscription=subs.getSubscriptionPlan();
						}
					}
				}
				
				
				if(merchant.getAddresses()!=null && merchant.getAddresses().size()> 0 ){
					for(Address address2:merchant.getAddresses()){
						if(address2.getAddress1()!=null && !address2.getAddress1().isEmpty())
							address=address2.getAddress1();
							
					   if(address2.getAddress2()!=null && !address2.getAddress2().isEmpty())	{
						   if(address.equals("NA"))
							   address="";
						   
						   address=address+" "+address2.getAddress2();
					   }
					   
						   if(address2.getAddress3()!=null && !address2.getAddress3().isEmpty()){
							   if(address.equals("NA"))
								   address="";
							   address=address+" "+address2.getAddress3();
						   }
						   
                          if(address2.getCity()!=null && !address2.getCity().isEmpty())
							   
							   address=address+" ,"+address2.getCity();
                          
                          if(address2.getState()!=null && !address2.getState().isEmpty())
							   
							   address=address+" ,"+address2.getState();
                          
                          if(address2.getZip()!=null && !address2.getZip().isEmpty())
							   
							   address=address+" ,"+address2.getZip();
                          
                          if(address2.getCountry()!=null && !address2.getCountry().isEmpty())
							   
							   address=address+" "+address2.getCountry();
                          
					}
					
				}
			
			Message message = new MimeMessage(
					SendMailProperty.mailProperty(IConstant.FROM_EMAIL_ID, IConstant.FROM_PASSWORD));
			message.setFrom(new InternetAddress(IConstant.FROM_EMAIL_ID));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("support@foodkonnekt.com"));
			message.setSubject("FoodKonnekt App "+status+" From "+posName);
			String msg = "<div style='width: 700px; height: auto; border: solid 1px #ddd; box-shadow: 0px 0px 6px #ddd; background-color: #f2f2f2; text-align: center; margin: 40px 0px 0px 35px;'><div style='width: 700px; height: 85px; padding: 20px 0px; text-align: center;'>&nbsp;<img src='http://www.dev.foodkonnekt.com/app/foodnew.jpg' height='80' /></div><div style='background-color: #fff; padding: 20px 0px 0px 0px; width: 90%; margin: 0 auto 65px auto;'><p style='font-size: 20px; color: #e6880f;'>Dear FoodKonnekt,</p><p style='font-size: 14px;'>You got a new installation.Merchant detail is as below :-</p><div style='text-align: left; padding: 5px 20px;'><table><tbody>"
					+ "<tr><td width='90'>Name</td><td>:</td>"
					+"<td>"+ merchantName + "</td>"
					+ "</tr><tr><td>Email</td><td>:</td><td>" + emailid+ "</td></tr>"
					+ "<tr><td>Phone</td><td>:</td><td>" + phoneNo+ "</td></tr>"
					+ "<tr><td>Subscription</td><td>:</td><td>" + subscription+ "</td></tr>"
					+ "<tr><td>Web Site</td><td>:</td><td>" + website+ "</td></tr>"
					+ "<tr><td>Address</td><td>:</td><td>" + address+ "</td></tr>"
					+ "</tbody></table><br /><br /><p>Thank you. We appreciate your business with us!</p>Regards,<br />FoodKonnekt</div><div style='height: 70px; text-align: center;'>&nbsp;<img src='http://www.dev.foodkonnekt.com/app/foodnew.jpg' alt='' width='200' height='63' /></div></div><p style='color: #676767; font-size: 11px;'>Automated email. Please do not reply to this sender email.</p></div>'";
			message.setContent(msg, "text/html");
			Transport.send(message);
			}}
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
		return null;
	}

	public static void placeOrderMail(String name, String paymentYtpe, String orderPosId, String subTotal, String tax,
			String orderDetails, String email, Double orderPrice, String note, String merchantName, String merchantLogo,
			String orderType, String convenienceFeeValue, String avgTime, String deliverFee, Double orderDiscount,Double tipAmount) {
		try {
			Message message = new MimeMessage(
					SendMailProperty.mailProperty(IConstant.FROM_EMAIL_ID, IConstant.FROM_PASSWORD));
			message.setFrom(new InternetAddress(IConstant.FROM_EMAIL_ID));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
			message.setSubject("Your order has been received");
			String msg = "<div style='width: 700px; height: auto; border: solid 1px #ddd; box-shadow: 0px 0px 6px #ddd; background-color: #f2f2f2; text-align: center; margin: 40px 0px 0px 35px;'>"
					+ "<div style='width: 700px; height: 85px; padding: 20px 0px; text-align: center;'>&nbsp;<img src='"
					+ merchantLogo + "' height='80' /></div>"
					+ "<div style='background-color: #fff; padding: 20px 0px 0px 0px; width: 90%; margin: 0 auto 65px auto;'>"
					+ "<p style='font-size: 20px; color: #e6880f;'>Dear " + name + ",</p>"
					+ "<p style='font-size: 14px;'>Thank you. We have received your order and are in the process of&nbsp;confirming it.&nbsp;</p>"
					+ "<div style='text-align: left; padding: 20px 20px;'>" + "<table>" + "<tbody>" + "<tr>"
					+ "<td width='230'>Order type</td>" + "<td>:</td>" + "<td>" + orderType + "</td>" + "</tr>" + "<tr>"
					+ "<td>Order ID</td>" + "<td>:</td>" + "<td>" + orderPosId + "</td>" + "</tr>" + "<tr>"
					+ "<td>Payment</td>" + "<td>:</td>" + "<td>" + paymentYtpe + "</td>" + "</tr>" + "</tbody>"
					+ "</table>" + "<p><strong>Order Details:</strong><br /></p>" + orderDetails + "</p>"
					+ "<p style='margin: 0;'>-------------------------------------------------------------------------------</p>"
					+ "<table>" + "<tbody>" + "<tr>" + "<td width='230'>Sub-total</td>" + "<td>&nbsp;</td>" + "<td>$"
					+ subTotal + "</td>" + "</tr>" + "<tr>" + "<td>Convenience Fee</td>" + "<td>&nbsp;</td>" + "<td>$"
					+ convenienceFeeValue + "</td>" + "</tr>";
			if (orderType.toLowerCase().equals("delivery")) {
				msg = msg + "<tr>" + "<td>Delivery Fee</td>" + "<td>&nbsp;</td>" + "<td>$" + deliverFee + "</td>"
						+ "</tr>";

			}
			
			if (tipAmount!=null && tipAmount>0) {
				msg = msg + "<tr>" + "<td>Tip</td>" + "<td>&nbsp;</td>" + "<td>$" + tipAmount + "</td>"
						+ "</tr>";

			}
			/*msg = msg + "<tr>" + "<td>Discount</td>" + "<td>&nbsp;</td>" + "<td>$" + orderDiscount + "</td>" + "</tr>"*/

        msg = msg		+ "<tr>" + "<td>Tax</td>" + "<td>&nbsp;</td>" + "<td>$" + tax + "</td>" + "</tr>" + "</tbody>"
					+ "</table>"
					+ "<p style='margin: 0;'>-------------------------------------------------------------------------------</p>"
					+ "<table>" + "<tbody>" + "<tr>" + "<td width='230'>Total</td>" + "<td>&nbsp;</td>" + "<td>$"
					+ orderPrice + "</td>" + "</tr>" + "</tbody>" + "</table>"
					+ "<br /><br /> <strong style='text-decoration: underline;'>Special Instructions :</strong> " + note
					+ " <br /><br /><br /> <a style='text-decoration: none; margin: 16px 0px; display: inline-block; background-color: #f8981d; color: #fff; padding: 10px 12px; border-radius: 10px; font-weight: 600; font-size: 15px;' href='https://www.clover.com/r/"
					+ orderPosId
					+ "' target='_blank'>View Online Receipt</a><br /> Thank you for your order! It'll be ready for "
					+ orderType + " within the next " + avgTime + " minutes. See you soon.  <br /><br /> Regards,<br />"
					+ merchantName + "</div>"
					+ "<p>Lookout for an update from us on your order confirmation.<br />We appreciate your business with us!</p>"
					+ "<div style='height: 70px; text-align: center;'>&nbsp;<img src='http://www.dev.foodkonnekt.com/app/foodnew.jpg' alt='' width='200' height='63' /></div>"
					+ "</div>"
					+ "<p style='color: #676767; font-size: 11px;'>Automated email. Please do not reply to this sender email.</p>"
					+ "</div>";
			message.setContent(msg, "text/html");
			Transport.send(message);
		} catch (MessagingException e) {

		}
	}

	public static void sendConfirmMail(String name, String paymentYtpe, String orderPosId, String subTotal, String tax,
			String orderDetails, String email, Double orderPrice, String note, String merchantName, String merchantLogo,
			String orderType, String convenienceFee, String pickUpTimeValue, String deliveryFee,Double orderDiscount,Double tipAmount) {
		try {
			Message message = new MimeMessage(
					SendMailProperty.mailProperty(IConstant.FROM_EMAIL_ID, IConstant.FROM_PASSWORD));
			message.setFrom(new InternetAddress(IConstant.FROM_EMAIL_ID));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
			message.setSubject("Your order has been confirmed");
			String msg = "<div style='width: 700px; height: auto; border: solid 1px #ddd; box-shadow: 0px 0px 6px #ddd; background-color: #f2f2f2; text-align: center; margin: 40px 0px 0px 35px;'>"
					+ "<div style='width: 700px; height: 85px; padding: 20px 0px; text-align: center;'>&nbsp;<img src='"
					+ merchantLogo + "' height='80' /></div>"
					+ "<div style='background-color: #fff; padding: 20px 0px 0px 0px; width: 90%; margin: 0 auto 65px auto;'>"
					+ "<p style='font-size: 20px; color: #e6880f;'>Dear " + name + ",</p>"
					+ "<p style='font-size: 14px;'>Your order has been confirmed and is getting ready! &nbsp;</p>"
					+ "<div style='text-align: left; padding: 20px 20px;'>" + "<table>" + "<tbody>" + "<tr>"
					+ "<td width='230'>Order type</td>" + "<td>:</td>" + "<td>" + orderType + "</td>" + "</tr>" + "<tr>"
					+ "<td>Order ID</td>" + "<td>:</td>" + "<td>" + orderPosId + "</td>" + "</tr>" + "<tr>"
					+ "<td>Payment</td>" + "<td>:</td>" + "<td>" + paymentYtpe + "</td>" + "</tr>" + "</tbody>"
					+ "</table>" + "<p><strong>Order Details:</strong><br /></p>" + orderDetails + "</p>"
					+ "<p style='margin: 0;'>-------------------------------------------------------------------------------</p>"
					+ "<table>" + "<tbody>" + "<tr>" + "<td width='230'>Sub-total</td>" + "<td>&nbsp;</td>" + "<td>$"
					+ subTotal + "</td>" + "</tr>" + "<tr>" + "<td>Convenience Fee</td>" + "<td>&nbsp;</td>" + "<td>$"
					+ convenienceFee + "</td>" + "</tr>";
			if (orderType.toLowerCase().equals("delivery")) {
				msg = msg + "<tr>" + "<td>Delivery Fee</td>" + "<td>&nbsp;</td>" + "<td>$" + deliveryFee + "</td>"
						+ "</tr>";

			}
			if (tipAmount!=null && tipAmount>0) {
				msg = msg + "<tr>" + "<td>Tip</td>" + "<td>&nbsp;</td>" + "<td>$" + tipAmount + "</td>"
						+ "</tr>";

			}
		/*	msg = msg + "<tr>" + "<td>Discount</td>" + "<td>&nbsp;</td>" + "<td>$"+orderDiscount+"</td>" + "</tr>"*/

msg = msg 	+ "<tr>" + "<td>Tax</td>" + "<td>&nbsp;</td>" + "<td>$" + tax + "</td>" + "</tr>" + "</tbody>"
					+ "</table>"
					+ "<p style='margin: 0;'>-------------------------------------------------------------------------------</p>"
					+ "<table>" + "<tbody>" + "<tr>" + "<td width='230'>Total</td>" + "<td>&nbsp;</td>" + "<td>$"
					+ orderPrice + "</td>" + "</tr>" + "</tbody>" + "</table>"
					+ "<br /><br /> <strong style='text-decoration: underline;'>Special Instructions :</strong> " + note
					+ " <br /><br /><br /> <a style='text-decoration: none; margin: 16px 0px; display: inline-block; background-color: #f8981d; color: #fff; padding: 10px 12px; border-radius: 10px; font-weight: 600; font-size: 15px;' href='https://www.clover.com/r/"
					+ orderPosId
					+ "' target='_blank'>View Online Receipt</a><br /> Thank you for your order! It'll be ready for "
					+ orderType + " within the next " + pickUpTimeValue
					+ " minutes. See you soon.  <br /><br /> Regards,<br />" + merchantName + "</div>"
					+ "<p>Lookout for an update from us on your order confirmation.<br />We appreciate your business with us!</p>"
					+ "<div style='height: 70px; text-align: center;'>&nbsp;<img src='http://www.dev.foodkonnekt.com/app/foodnew.jpg' alt='' width='200' height='63' /></div>"
					+ "</div>"
					+ "<p style='color: #676767; font-size: 11px;'>Automated email. Please do not reply to this sender email.</p>"
					+ "</div>";
			message.setContent(msg, "text/html");
			Transport.send(message);
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}

	}

	public static void sendOrderCancellationMail(String name, String orderPosId, String email, String merchantName,
			String merchantLogo, String merchantPhoneNo, String merchantEmail, String reason) {
		try {
			Message message = new MimeMessage(
					SendMailProperty.mailProperty(IConstant.FROM_EMAIL_ID, IConstant.FROM_PASSWORD));
			message.setFrom(new InternetAddress(IConstant.FROM_EMAIL_ID));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
			message.setSubject("We are sorry! Your recent order has been cancelled");
			String msg = "<div style='width: 700px; height: auto; border: solid 1px #ddd; box-shadow: 0px 0px 6px #ddd; background-color: #f2f2f2; text-align: center; margin: 40px 0px 0px 35px;'>"
					+ "<div style='width: 700px; height: 85px; padding: 20px 0px; text-align: center;'>&nbsp;<img src='"
					+ merchantLogo + "' height='80' /></div>"
					+ "<div style='background-color: #fff; padding: 20px 0px 0px 0px; width: 90%; margin: 0 auto 65px auto;'>"
					+ "<p style='font-size: 20px; color: #e6880f;'>Dear " + name + ",</p>"
					+ "<p style='font-size: 14px;'>We regret to inform you that your recent order " + orderPosId
					+ " , has been declined by " + merchantName + " &nbsp;</p>";
			msg = msg + "<div style='text-align: left; padding: 20px 20px;'>";
			if (reason != null && !reason.equals("")) {

				msg = msg + "<p><strong>The reason for rejecting the order is:</strong><br /></p>";
				msg = msg + "<ul style='font-size:12px'>";
				String reasons[] = reason.split(",");
				for (String r : reasons) {
					if(r!=null && (!r.isEmpty()|| !r.equals("")||!r.equals(" ")) )
					msg = msg + "<li>" + r + "</li>";
				}
				msg = msg + "</ul>";

			}

			msg = msg
					+ "<p><Order details: items, extras, price, fees, promo discount, total $><br>If you paid by credit card, your payment will be refunded to you at the earliest.<br>For any further questions,";
			if(merchantPhoneNo!=null) { 
				msg = msg +"Please call us at" + merchantPhoneNo + "";}
			else{
				msg = msg +"Please call our store";
			}
			
			msg = msg + " <br><br>Regards,<br>" + merchantName
					+ "<br><br>";
			msg = msg + "</div>";
			msg = msg + "<p>We appreciate your business with us!</p>"
					+ "<div style='height: 70px; text-align: center;'>&nbsp;<img src='http://www.dev.foodkonnekt.com/app/foodnew.jpg' alt='' width='200' height='63' /></div>"
					+ "</div>"
					+ "<p style='color: #676767; font-size: 11px;'>Automated email. Please do not reply to this sender email.</p>"
					+ "</div>";
			message.setContent(msg, "text/html");
			Transport.send(message);
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}

	}

	public static boolean webhookMail(String subject,String json) {
		try {
			if(!UrlConstant.FOODKONNEKT_APP_TYPE.equals("Local")){
			Message message = new MimeMessage(SendMailProperty.mailProperty("orders@foodkonnekt.com", "P@ssw0rd123"));
			message.setFrom(new InternetAddress("orders@foodkonnekt.com"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("sumit@mkonnekt.com"));
			message.setSubject(subject+UrlConstant.FOODKONNEKT_APP_TYPE);
			String msg = "Dear<br>";
			msg += json;
			msg += " <br>";
			msg += " <br>";
			msg += "<b>Regards,</b><br>";
			msg += "FoodKonnekt";
			message.setContent(msg, "text/html");
			Transport.send(message);
			}
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
		return true;
	}
	
	public static boolean orderFaildMail(String json) {
		try {
			if(!UrlConstant.FOODKONNEKT_APP_TYPE.equals("Local")){
			Message message = new MimeMessage(SendMailProperty.mailProperty("orders@foodkonnekt.com", "P@ssw0rd123"));
			message.setFrom(new InternetAddress("orders@foodkonnekt.com"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("bugs@foodkonnekt.com,sumit@mkonnekt.com"));
			message.setSubject("Order Failed from "+UrlConstant.FOODKONNEKT_APP_TYPE);
			String msg = "Dear<br>";
			msg += json;
			msg += " <br>";
			msg += " <br>";
			msg += "<b>Regards,</b><br>";
			msg += "FoodKonnekt";
			message.setContent(msg, "text/html");
			Transport.send(message);
			}
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
		return true;
	}

	public static boolean sendErrorMailToAdmin(String exception) {
		
		if(!UrlConstant.FOODKONNEKT_APP_TYPE.equals("Local")){
		  try { Message message = new
		  MimeMessage(SendMailProperty.mailProperty("orders@foodkonnekt.com",
		 "P@ssw0rd123")); message.setFrom(new
		  InternetAddress("orders@foodkonnekt.com"));
		  message.setRecipients(Message.RecipientType.TO,
		  InternetAddress.parse("bugs@foodkonnekt.com,sumit@mkonnekt.com,sumit.a.gangrade@gmail.com"));
		  message.setSubject("Exception from "+UrlConstant.FOODKONNEKT_APP_TYPE); String msg = ""; msg += exception;
		  msg += " <br>"; msg += " <br>"; msg += "<b>Regards,</b><br>"; msg +=
		  "FoodKonnekt"; message.setContent(msg, "text/html");
		  Transport.send(message); } catch (MessagingException e) { throw new
		  RuntimeException(e); }
		}
		 
		return true;
	}

	public static void sendExceptionByMail(Exception e) {
		StringWriter errors = new StringWriter();
		e.printStackTrace(new PrintWriter(errors));
		MailSendUtil.sendErrorMailToAdmin(errors.toString());
	}
	
	public static void sendExceptionByMail(Exception e,Merchant merchant) {
		StringWriter errors = new StringWriter();
		e.printStackTrace(new PrintWriter(errors));
		if(merchant!=null && merchant.getName()!=null)
		MailSendUtil.sendErrorMailToAdmin("MerchantName"+merchant.getName()+","+errors.toString());
		else
		MailSendUtil.sendErrorMailToAdmin(errors.toString());
	}

	public static String sendUpdatedTime(OrderR orderR, String reason, String time) {
		Customer customer = orderR.getCustomer();
		try {
			String cusID = EncryptionDecryptionUtil.encryptString(String.valueOf(customer.getId()));
			String marchantId = String.valueOf(customer.getMerchantt().getId());
			String timeLog = String.valueOf(System.currentTimeMillis());
			Message message = new MimeMessage(
					SendMailProperty.mailProperty(IConstant.FROM_EMAIL_ID, IConstant.FROM_PASSWORD));
			message.setFrom(new InternetAddress(IConstant.FROM_EMAIL_ID));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(customer.getEmailId()));
			message.setSubject("Order Updated");
			String merchantLogo="";
			if (customer.getMerchantt().getMerchantLogo() == null) {
                merchantLogo = "http://www.dev.foodkonnekt.com/app/foodnew.jpg";
            } else {
                merchantLogo = UrlConstant.BASE_PORT + customer.getMerchantt().getMerchantLogo();
            }
			/*String msg = "Dear " + customer.getFirstName() + "<br>";
			msg += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Thank you for placing the order with us but due to " + reason
					+ " your order would be ready to be \"picked up\"/delivered at<br>";
			msg += " <br>";
			msg += " <br>";
			msg += "New Time : " + time
					+ "We sincerely apologize about the inconvenience caused. Please call us at \"number\" if you have any questions.\r\n"
					+ "\r\n" + "We look forward to serving you soon" + "<br>";
			msg += " <br>";
			msg += " <br>";
			msg += " <br>";
			msg += "<b>Vendor Name,</b><br>";
			msg += "FoodKonnekt";*/
			String msg = "<div style='width: 700px; height: auto; border: solid 1px #ddd; box-shadow: 0px 0px 6px #ddd; background-color: #f2f2f2; text-align: center; margin: 40px 0px 0px 35px;'>"
					+ "<div style='width: 700px; height: 85px; padding: 20px 0px; text-align: center;'>&nbsp;<img src='"
					+ merchantLogo + "' height='80' /></div>"
					+ "<div style='background-color: #fff; padding: 20px 0px 0px 0px; width: 90%; margin: 0 auto 65px auto;'>"
					+ "<p style='font-size: 20px; color: #e6880f;'>Dear " + customer.getFirstName() + ",</p>"
					+ "<p style='font-size: 14px;text-align: left;padding-left:10px;'>Thank you for placing the order with us but due to \""+reason+"\" your order would be ready to be ";
			if (orderR.getOrderType().toLowerCase().equals("delivery")) {
				msg = msg+"delivered";	
			}else{
				msg = msg+"picked up";	
			}
			
			
			msg = msg  +" at "+time+" <br> <br><br>We sincerely apologize about the inconvenience caused. We look forward to serving you soon &nbsp;</p>";
			

			msg = msg
					+ "<p><Order details: items, extras, price, fees, promo discount, total $><br>For any further questions, ";
			if(customer.getMerchantt().getPhoneNumber()!=null) { 
				msg = msg +"Please call us at " + customer.getMerchantt().getPhoneNumber() + "";}
			else{
				msg = msg +"Please call our store";
			}
					msg = msg +" <br><br>Regards,<br>" + customer.getMerchantt().getName()
					+ "<br><br>";
			msg = msg + "</div>";
			msg = msg + "<p>We appreciate your business with us!</p>"
					+ "<div style='height: 70px; text-align: center;'>&nbsp;<img src='http://www.dev.foodkonnekt.com/app/foodnew.jpg' alt='' width='200' height='63' /></div>"
					+ "</div>"
					+ "<p style='color: #676767; font-size: 11px;'>Automated email. Please do not reply to this sender email.</p>";
			message.setContent(msg, "text/html");
			Transport.send(message);
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
		return null;
	}

	
	/*public static void sendOrderReceiptAndFeedbackEmail(OrderR order) {
		
	 try {
		String cusID = EncryptionDecryptionUtil.encryption(String.valueOf(order.getCustomer().getId()));
		String orderId = EncryptionDecryptionUtil.encryption(String.valueOf(order.getId()));
		
		
		Message message = new MimeMessage(
				SendMailProperty.mailProperty(IConstant.FROM_EMAIL_ID, IConstant.FROM_PASSWORD));
		message.setFrom(new InternetAddress(IConstant.FROM_EMAIL_ID));
		message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(order.getCustomer().getEmailId()));
		message.setSubject("Regards: Order Receipt and feedback");
		
		String merchantLogo="";
		if (order.getMerchant().getMerchantLogo() == null) {
            merchantLogo = "http://www.dev.foodkonnekt.com/app/foodnew.jpg";
        } else {
            merchantLogo = UrlConstant.BASE_PORT + order.getMerchant().getMerchantLogo();
        }
		
		String msg = "<div style='width: 700px; height: auto; border: solid 1px #ddd; box-shadow: 0px 0px 6px #ddd; background-color: #f2f2f2; text-align: center; margin: 40px 0px 0px 35px;'>"
				+ "<div style='width: 700px; height: 85px; padding: 20px 0px; text-align: center;'>&nbsp;<img src='"
				+ merchantLogo + "' height='80' /></div>";
		
		msg = msg+"<div style='background-color: #fff; padding: 20px 0px 0px 0px; width: 90%; margin: 0 auto 65px auto;'>"
				+ "<p style='font-size: 20px; color: #e6880f;'>Dear " + order.getCustomer().getFirstName() + ",</p>";
		
		msg = msg+"<br /> Thank You for your recent order and we hope that your experience has been pleasant."
						+ " In order for us to improve we are seeking feedback about your online ordering experience as well as about our food. Please find below a "
						+ "link for the survey (it would take around 2 min to complete this survey). As always we look forward to serving you soon. .";
		
			msg = msg+"<br/> <a 'font-size: 14px; none; text-align: left;padding-left:10px;' href ='" + UrlConstant.WEB_BASE_URL + "/feedbackForm?customerId=" + cusID
				+ "&orderId=" + orderId+ "' target='_blank'>Complete the Survey </a> ";
			msg = msg+" &nbsp;";
			msg = msg+" <a 'font-size: 14px; none; text-align: left;padding-left:10px;' href ='" + UrlConstant.WEB_BASE_URL + "/orderRec?orderid=" + orderId
					+ "&customerid=" + cusID+ "' target='_blank'>Order Receipt </a> ";

		msg = msg
				+ "<p><Order details: items, extras, price, fees, promo discount, total $><br>For any further questions, ";
		if(order.getMerchant().getPhoneNumber()!=null) { 
			msg = msg +"Please call us at " + order.getMerchant().getPhoneNumber() + "";}
		else{
			msg = msg +"Please call our store";
		}
				msg = msg +" <br><br>Regards,<br>" + order.getMerchant().getName()
				+ "<br><br>";
		msg = msg + "</div>";
		msg = msg + "<p>We appreciate your business with us!</p>"
				+ "<div style='height: 70px; text-align: center;'>&nbsp;<img src='http://www.dev.foodkonnekt.com/app/foodnew.jpg' alt='' width='200' height='63' /></div>"
				+ "</div>"
				+ "<p style='color: #676767; font-size: 11px;'>Automated email. Please do not reply to this sender email.</p>";
		
		
		message.setContent(msg, "text/html");
		
		Transport.send(message);

		System.out.println("mail sent successfully");

	} catch (MessagingException e) {
		throw new RuntimeException(e);
	}

	}
*/	
	/*public static void sendOrderReceiptAndFeedbackEmail(OrderR order, List<OrderR> orderList) {
		
		 try {
			String cusID = EncryptionDecryptionUtil.encryption(String.valueOf(order.getCustomer().getId()));
			String orderId = EncryptionDecryptionUtil.encryption(String.valueOf(order.getId()));
			
			
			Message message = new MimeMessage(
					SendMailProperty.mailProperty(IConstant.FROM_EMAIL_ID, IConstant.FROM_PASSWORD));
			message.setFrom(new InternetAddress(IConstant.FROM_EMAIL_ID));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(order.getCustomer().getEmailId()));
		
		message.setSubject("Regards: Order Receipt and feedback"); 
					
			String merchantLogo="";
			if (order.getMerchant().getMerchantLogo() == null) {
	            merchantLogo = "http://www.dev.foodkonnekt.com/app/foodnew.jpg";
	        } else {
	            merchantLogo = UrlConstant.BASE_PORT + order.getMerchant().getMerchantLogo();
	        }
			
			String msg = "<div style='width: 700px; height: auto; border: solid 1px #ddd; box-shadow: 0px 0px 6px #ddd; background-color: #f2f2f2; text-align: center; margin: 40px 0px 0px 35px;'>"
					+ "<div style='width: 700px; height: 85px; padding: 20px 0px; text-align: center;'>&nbsp;<img src='"
					+ merchantLogo + "' height='80' /></div>";
			
			msg = msg+"<div style='background-color: #fff; padding: 20px 0px 0px 0px; width: 90%; margin: 0 auto 65px auto;'>"
					+ "<p style='font-size: 20px; color: #e6880f;'>Dear " + order.getCustomer().getFirstName() + ",</p>";
			
			msg = msg+"<br /> Thank You for your recent order and we hope that your experience has been pleasant."
							+ " In order for us to improve we are seeking feedback about your online ordering experience as well as about our food. Please find below a "
							+ "link for the survey (it would take around 2 min to complete this survey). As always we look forward to serving you soon. .";
			
				msg = msg+"<br/> <a 'font-size: 14px; none; text-align: left;padding-left:5px;' href ='" + UrlConstant.WEB_BASE_URL + "/feedbackForm?customerId=" + cusID
					+ "&orderId=" + orderId+ "' target='_blank'>Complete the Survey </a> ";
				msg = msg+" &nbsp;";
				
				for(OrderR orderRe :orderList)
				{
					String orderIds = EncryptionDecryptionUtil.encryption(String.valueOf(orderRe.getId()));
				msg = msg+" <a 'font-size: 14px; none; text-align: left;padding-left:10px;' href ='" + UrlConstant.WEB_BASE_URL + "/orderRec?orderid=" + orderIds
						+ "&customerid=" + cusID+ "' target='_blank'>Order Receipt_"+orderRe.getOrderPosId() +"</a> ";
				msg = msg+" &nbsp;";
				}
			msg = msg
					+ "<p><Order details: items, extras, price, fees, promo discount, total $><br>For any further questions, ";
			if(order.getMerchant().getPhoneNumber()!=null) { 
				msg = msg +"Please call us at " + order.getMerchant().getPhoneNumber() + "";}
			else{
				msg = msg +"Please call our store";
			}
					msg = msg +" <br><br>Regards,<br>" + order.getMerchant().getName()
					+ "<br><br>";
			msg = msg + "</div>";
			msg = msg + "<p>We appreciate your business with us!</p>"
					+ "<div style='height: 70px; text-align: center;'>&nbsp;<img src='http://www.dev.foodkonnekt.com/app/foodnew.jpg' alt='' width='200' height='63' /></div>"
					+ "</div>"
					+ "<p style='color: #676767; font-size: 11px;'>Automated email. Please do not reply to this sender email.</p>";
			
			
			message.setContent(msg, "text/html");
			
			Transport.send(message);

			System.out.println("mail sent successfully");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}

		}
*/
	
	
	//Dummy method for testing
	public static void sendOrderReceiptAndFeedbackEmail(OrderR order, List<OrderR> orderList) {
		
		 /*try {
			String cusID = EncryptionDecryptionUtil.encryption(String.valueOf(order.getCustomer().getId()));
			String orderId = EncryptionDecryptionUtil.encryption(String.valueOf(order.getId()));
			
			
			Message message = new MimeMessage(
					SendMailProperty.mailProperty(IConstant.FROM_EMAIL_ID, IConstant.FROM_PASSWORD));
			message.setFrom(new InternetAddress(IConstant.FROM_EMAIL_ID));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(order.getCustomer().getEmailId()));
		*/
		
		
		
//		Properties props = new Properties();
//		props.put("mail.smtp.host", "smtp.gmail.com");
//		props.put("mail.smtp.socketFactory.port", "465");
//		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
//		props.put("mail.smtp.auth", "true");
//		props.put("mail.smtp.port", "465");

		/*Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("noreply.co.mail@gmail.com", "qwe@1234");
			}
		});*/
		String cusID = EncryptionDecryptionUtil.encryption(String.valueOf(order.getCustomer().getId()));
		String orderId = EncryptionDecryptionUtil.encryption(String.valueOf(order.getId()));
		
		
		try {
			/*MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress("noreply.co.mail@gmail.com"));*/
			Message message = new MimeMessage(
					SendMailProperty.mailProperty(IConstant.FROM_EMAIL_ID, IConstant.FROM_PASSWORD));
			message.setFrom(new InternetAddress(IConstant.FROM_EMAIL_ID));
			
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(order.getCustomer().getEmailId()));
		
		
		
		
		
		
		message.setSubject("Regards: Order Receipt and feedback"); 
		
		
		String customerName = "Customer";
		String mailHead ="Thank You for dining with us";
		
		
		if(order.getCustomer()!=null && order.getCustomer().getFirstName()!= null){
			customerName = order.getCustomer().getFirstName();
		}
		
					
			String merchantLogo="";
			if (order.getMerchant().getMerchantLogo() == null) {
	            merchantLogo = "https://gallery.mailchimp.com/63d57cb8e02236766b6ec6d06/images/e38be12c-9d09-4248-a809-b45181a5a0f5.jpg";
	        } else {
	            merchantLogo = UrlConstant.BASE_PORT + order.getMerchant().getMerchantLogo();
	        }
			
			
			String merchantName = "Merchant";
			if (order.getMerchant()!=null && order.getMerchant().getName() != null) {
				 merchantName = order.getMerchant().getName();
	        }
			
			
			/*String msg = "<div style='width: 700px; height: auto; border: solid 1px #ddd; box-shadow: 0px 0px 6px #ddd; background-color: #f2f2f2; text-align: center; margin: 40px 0px 0px 35px;'>"
					+ "<div style='width: 700px; height: 85px; padding: 20px 0px; text-align: center;'>&nbsp;<img src='"
					+ merchantLogo + "' height='80' /></div>";
			
			msg = msg+"<div style='background-color: #fff; padding: 20px 0px 0px 0px; width: 90%; margin: 0 auto 65px auto;'>"
					+ "<p style='font-size: 20px; color: #e6880f;'>Dear " + order.getCustomer().getFirstName() + ",</p>";
			
			msg = msg+"<br /> <p text-align: left;> Thank You for your recent order and we hope that your experience has been pleasant."
							+ " In order for us to improve we are seeking feedback about your online ordering experience as well as about our food. Please find below a "
							+ "link for the survey (it would take around 2 min to complete this survey). As always we look forward to serving you soon. </p>";
			
				msg = msg+"<br/> <a 'font-size: 14px; none; text-align: left;padding-left:5px;' href ='" + UrlConstant.WEB_BASE_URL + "/feedbackForm?customerId=" + cusID
					+ "&orderId=" + orderId+ "' target='_blank'>Complete the Survey </a> ";
				msg = msg+" &nbsp;";
				
				for(OrderR orderRe :orderList)
				{
					String orderIds = EncryptionDecryptionUtil.encryption(String.valueOf(orderRe.getId()));
				msg = msg+" <a 'font-size: 14px; none; text-align: left;padding-left:10px;' href ='" + UrlConstant.WEB_BASE_URL + "/orderRec?orderid=" + orderIds
						+ "&customerid=" + cusID+ "' target='_blank'>Order Receipt_"+orderRe.getOrderPosId() +"</a> ";
				msg = msg+" &nbsp;";
				}
			msg = msg
					+ "<p><Order details: items, extras, price, fees, promo discount, total $><br>For any further questions, ";
			if(order.getMerchant().getPhoneNumber()!=null) { 
				msg = msg +"Please call us at " + order.getMerchant().getPhoneNumber() + "";}
			else{
				msg = msg +"Please call our store";
			}
					msg = msg +" <br><br>Regards,<br>" + order.getMerchant().getName()
					+ "<br><br>";
			msg = msg + "</div>";
			msg = msg + "<p>We appreciate your business with us!</p>"
					+ "<div style='height: 70px; text-align: center;'>&nbsp;<img src='http://www.dev.foodkonnekt.com/app/foodnew.jpg' alt='' width='200' height='63' /></div>"
					+ "</div>"
					+ "<p style='color: #676767; font-size: 11px;'>Automated email. Please do not reply to this sender email.</p>";*/
			
			
			String mailBody = "We Thank You for your recent online order at our restaurant at "+merchantName+" .We value your business and your feedback helps us to get better. Please click the link here to begin your feedback process.";
		
			mailBody = mailBody+"<br/> <a 'font-size: 14px; none; text-align: left;padding-left:5px;' href ='" + UrlConstant.WEB_BASE_URL + "/feedbackForm?customerId=" + cusID
					+ "&orderId=" + orderId+ "' target='_blank'>Complete the Survey </a> ";
			mailBody = mailBody+" &nbsp;";
				
				for(OrderR orderRe :orderList)
				{
					String orderIds = EncryptionDecryptionUtil.encryption(String.valueOf(orderRe.getId()));
					mailBody = mailBody+" <a 'font-size: 14px; none; text-align: left;padding-left:10px;' href ='" + UrlConstant.WEB_BASE_URL + "/orderRec?orderid=" + orderIds
						+ "&customerid=" + cusID+ "' target='_blank'>Order Receipt_"+orderRe.getOrderPosId() +"</a> ";
					mailBody = mailBody+" &nbsp;";
				}
			
			
			
			
			/*String mailBody = "<br /> <p text-align: left;> Thank You for your recent order and we hope that your experience has been pleasant."
							+ " In order for us to improve we are seeking feedback about your online ordering experience as well as about our food. Please find below a "
							+ "link for the survey (it would take around 2 min to complete this survey). As always we look forward to serving you soon. </p>";
			
			mailBody = mailBody+"<br/> <a 'font-size: 14px; none; text-align: left;padding-left:5px;' href ='" + UrlConstant.WEB_BASE_URL + "/feedbackForm?customerId=" + cusID
					+ "&orderId=" + orderId+ "' target='_blank'>Complete the Survey </a> ";
			mailBody = mailBody+" &nbsp;";
				
				for(OrderR orderRe :orderList)
				{
					String orderIds = EncryptionDecryptionUtil.encryption(String.valueOf(orderRe.getId()));
					mailBody = mailBody+" <a 'font-size: 14px; none; text-align: left;padding-left:10px;' href ='" + UrlConstant.WEB_BASE_URL + "/orderRec?orderid=" + orderIds
						+ "&customerid=" + cusID+ "' target='_blank'>Order Receipt_"+orderRe.getOrderPosId() +"</a> ";
					mailBody = mailBody+" &nbsp;";
				}
				mailBody = mailBody
					+ "<p><Order details: items, extras, price, fees, promo discount, total $><br>For any further questions, ";
			if(order.getMerchant().getPhoneNumber()!=null) { 
				mailBody = mailBody +"Please call us at " + order.getMerchant().getPhoneNumber() + "";}
			else{
				mailBody = mailBody +"Please call our store";
			}*/
			
			
			
					
					String mailRegards = order.getMerchant().getName();
			
			String msg = kritiqMailFormate(customerName,mailHead,mailBody,merchantLogo,mailRegards);
			
			message.setContent(msg, "text/html");
			
			Transport.send(message);

			System.out.println("mail sent successfully");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}

		}

	
	
public static void thankyouMailForFeedback(CustomerFeedback customerFeedback, Customer customer, Boolean flag) {
		
		try {
			Message message = new MimeMessage(
					SendMailProperty.mailProperty(IConstant.FROM_EMAIL_ID, IConstant.FROM_PASSWORD));
			message.setFrom(new InternetAddress(IConstant.FROM_EMAIL_ID));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(customerFeedback.getCustomer().getEmailId()));
			message.setSubject("Regards: Thankyou for feedback");
			String customerName = "Customer";
			String mailHead ="Thank You for sharing your feedback";
			String mailBody = "We Thank You for your time and for your feedback. One of our staff members will review your feedback and reach out to you in case of any future questions. We look forward to seeing you soon again at our restaurant. ";
			String merchantLogo="";
			if (customer==null || customer.getMerchantt()==null || customer.getMerchantt().getMerchantLogo() == null) {
	            merchantLogo = "https://gallery.mailchimp.com/63d57cb8e02236766b6ec6d06/images/e38be12c-9d09-4248-a809-b45181a5a0f5.jpg";
	        } else {
	            merchantLogo = UrlConstant.BASE_PORT + customer.getMerchantt().getMerchantLogo();
	        }
			
			if(customer!=null && customer.getFirstName()!= null){
				customerName = customer.getFirstName();
			}
			
			
			
			
			
			/*String msg = "<%@ page language='java' contentType='text/html; charset=ISO-8859-1'pageEncoding='ISO-8859-1'%>"
					+ "<!DOCTYPE html PUBLIC '-//W3C//DTD HTML 4.01 Transitional//EN' 'http://www.w3.org/TR/html4/loose.dtd'>"
					+ "<html><head><meta charset='utf-8'>"
					+ "<meta http-equiv='X-UA-Compatible' content='IE=edge'><meta name='viewport' content='width=device-width, initial-scale=1'>"
					+ "<title>Email Template</title>"
					+ "<link type='text/css' rel='stylesheet' href='http://34.229.94.111:8080/web/resources/mailContent/css/font-awesome.min.css'/>"
     + "<link href='http://34.229.94.111:8080/web/resources/mailContent/css/bootstrap.min.css' rel='stylesheet'>"
		+ "<!-- Bootstrap -->"
	+ "<link href='http://34.229.94.111:8080/web/resources/mailContent/css/style.css' rel='stylesheet'>"
    + "</head>"

    + "<body>"
	   + "<main>"
          + "<div class='email-logo'>"
            + "<img src='img/logo.png' height='70' />"
          + "</div>"
		     + " <div class='email-block'>"
             + " <div class='email-header text-center'>"
             + "     Mkonnekt Social Report"
             + " </div>"
              + "<div class='email-body'>"
               + " <p>Hello John Doe,</p>"
               + " <p>We received a negative review yesterday for 'location name'. Let us know if you would like us to respond in any specific manner."
               + " <br/>"
               + " <p><b>Platform </b> : Facebook</p>"
               + " <p><b>Review Date </b> : 2017-09-01T23:10:27+0000</p>"
               + " <p><b>Customer Name </b> : Stephanie Karl Beaudry</p>"
               + " <p><b>Rating </b> : 1.0</p>"
                + "<p><b>Review Rating </b> : Called four times and got the answering machine each time.</p>"
               + " <br/><br/>"

                + "<p>Best,<br/>"
               + " mKonnekt Team</p>"
               + " <hr/>"
               + " <div class='text-center email-social'> <a href='#'><i class='fa fa-facebook-square'></i></a><a href='#'><i class='fa fa-twitter-square'></i></a><a href='#'><i class='fa fa-rss-square'></i></a></div></div></div></main> </body></html>";
			*/		
			
			
			/*String msg =  "<!DOCTYPE html PUBLIC '-//W3C//DTD HTML 4.01 Transitional//EN' 'http://www.w3.org/TR/html4/loose.dtd'>"
					+ "<html>"
					+ "<head>"
					+ "<title>Email Template</title>"
					+ "<style>.email-logo{max-width: 800px; margin: 20px auto; }</style>"
     + "<style> .email-block{max-width: 800px; background: #fff; margin: 20px auto; border-radius: 5px; border: 1px solid #ec902d; }</style>"
		+ "<!-- Bootstrap -->"
	+ "<style> .email-body{background:#fff; color: #8c8c8c; padding: 15px; }</style>"
		+"<style> .email-header{background:#ec902d; color: #fff; font-size: 22px; padding: 15px; text-transform: uppercase; letter-spacing: 5px; } </style>"
    + "</head>"

    + "<body>"
	   + "<main>"
          + "<div class='email-logo'>"
            + "<img src='http://34.229.94.111:8080/web/resources/mailContent/img/logo.png' height='70' />"
          + "</div>"
		     + " <div class='email-block'>"
             + " <div class='email-header text-center'>"
             + "     Mkonnekt Social Report"
             + " </div>"
              + "<div class='email-body'>"
               + " <p>Hello John Doe,</p>"
               + " <p>We received a negative review yesterday for 'location name'. Let us know if you would like us to respond in any specific manner."
               + " <br/>"
               + " <p><b>Platform </b> : Facebook</p>"
               + " <p><b>Review Date </b> : 2017-09-01T23:10:27+0000</p>"
               + " <p><b>Customer Name </b> : Stephanie Karl Beaudry</p>"
               + " <p><b>Rating </b> : 1.0</p>"
                + "<p><b>Review Rating </b> : Called four times and got the answering machine each time.</p>"
               + " <br/><br/>"

                + "<p>Best,<br/>"
               + " mKonnekt Team</p>"
               + " <hr/>"
               + " <div class='text-center email-social'> <a href='#'><i class='fa fa-facebook-square'></i></a><a href='#'><i class='fa fa-twitter-square'></i></a><a href='#'><i class='fa fa-rss-square'></i></a></div></div></div></main> </body></html>";
			*/
			
			/*String msg =  "<!DOCTYPE html PUBLIC '-//W3C//DTD HTML 4.01 Transitional//EN' 'http://www.w3.org/TR/html4/loose.dtd'>"
					+ "<html>"
					+ "<head>"
					+ "<title>Email Template</title>"
					+ "<style>.email-logo{max-width: 800px; margin: 20px auto; }</style>"
     + "<style> .email-block{max-width: 800px; background: #fff; margin: 20px auto; border-radius: 5px; border: 1px solid #ec902d; }</style>"
		+ "<!-- Bootstrap -->"
	+ "<style> .email-body{background:#fff; color: #8c8c8c; padding: 15px; }</style>"
		+"<style> .email-header{background:#ec902d; color: #fff; font-size: 22px; padding: 15px; text-transform: uppercase; letter-spacing: 5px; } </style>"
    + "</head>"

    + "<body>"
	   + "<main>"
          + "<div class='email-logo'>"
            + "<img src='" + merchantLogo + "' height='70' />"
          + "</div>"
		     + " <div class='email-block'>"
             + " <div class='email-header text-center'>"
             + " </div>"
              + "<div class='email-body'>"
              + "     Dear "
 			 		+ customerName
               + " <p>We Thank You for your time and for your feedback. We will review your feedback and we will get back to you shortly."
               + " <br/>"
               + " <p><b>Platform </b> : Facebook</p>"
               + " <p><b>Review Date </b> : 2017-09-01T23:10:27+0000</p>"
               + " <p><b>Customer Name </b> : Stephanie Karl Beaudry</p>"
               + " <p><b>Rating </b> : 1.0</p>"
                + "<p><b>Review Rating </b> : Called four times and got the answering machine each time.</p>"
               + " <br/><br/>"

                + "<p>Best,<br/>"
               + " mKonnekt Team</p>"
               + " <hr/>"
               + " <div class='text-center email-social'> <a href='#'><i class='fa fa-facebook-square'></i></a><a href='#'><i class='fa fa-twitter-square'></i></a><a href='#'><i class='fa fa-rss-square'></i></a></div></div></div></main> </body></html>";
			*/
			String mailRegards = "Kritiq" ;
			String msg = kritiqMailFormate(customerName,mailHead,mailBody,merchantLogo,mailRegards);
			
			message.setContent(msg, "text/html");	

			Transport.send(message);
		
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}

	}
	
public static  String kritiqMailFormate(String customerName,String mailHead,String mailBody,String merchantLogo,String mailRegards){
	
	String data = "<html xmlns='http://www.w3.org/1999/xhtml' xmlns:v='urn:schemas-microsoft-com:vml' xmlns:o='urn:schemas-microsoft-com:office:office'>"
			+"<head> "
			+"<meta charset='UTF-8'>"
			+"<meta http-equiv='X-UA-Compatible' content='IE=edge'>"
			+"<meta name='viewport' content='width=device-width, initial-scale=1'>"
			+" <style type='text/css'> "
					+" p{"
						+" margin:10px 0;"
						+" padding:0;"
					+" }"
					+" table{"
						+" border-collapse:collapse;"
					+" }"
					+" h1,h2,h3,h4,h5,h6{"
						+" display:block;"
						+" margin:0;"
						+" padding:0;"
					+" }"
					+" img,a img{"
						+" border:0;"
						+" height:auto;"
						+" outline:none;"
						+" text-decoration:none;"
					+" }"
					+" body,#bodyTable,#bodyCell{"
						+" height:100%;"
					+" 	margin:0;"
						+" padding:0;"
					+" 	width:100%;"
				+" 	}"
				+" 	.mcnPreviewText{"
				+" 		display:none !important;"
				+" 	}"
				+" 	#outlook a{"
				+" 		padding:0;"
				+" 	}"
				+" 	img{"
				+"		-ms-interpolation-mode:bicubic;"
				+" 	}"
				+" 	table{"
				+" 		mso-table-lspace:0pt;"
				+" 		mso-table-rspace:0pt;"
				+" 	}"
				+" 	.ReadMsgBody{"
				+" 		width:100%;"
				+" 	}"
				+" 	.ExternalClass{"
				+" 		width:100%;"
				+" 	}"
				+" 	p,a,li,td,blockquote{"
				+" 		mso-line-height-rule:exactly;"
				+" 	}"
				+" 	a[href^=tel],a[href^=sms]{"
				+" 		color:inherit;"
				+" 		cursor:default;"
				+" 		text-decoration:none;"
				+" 	}"
				+" 	p,a,li,td,body,table,blockquote{"
				+" 		-ms-text-size-adjust:100%;"
				+" 		-webkit-text-size-adjust:100%;"
				+" 	}"
				+" 	.ExternalClass,.ExternalClass p,.ExternalClass td,.ExternalClass div,.ExternalClass span,.ExternalClass font{"
				+" 		line-height:100%;"
				+" 	}"
				+" 	a[x-apple-data-detectors]{"
				+" 		color:inherit !important;"
				+" 		text-decoration:none !important;"
				+" 		font-size:inherit !important;"
				+" 		font-family:inherit !important;"
				+" 		font-weight:inherit !important;"
				+" 		line-height:inherit !important;"
				+" 	}"
				+" 	.templateContainer{"
				+" 		max-width:600px !important;"
				+" 	}"
				+" 	a.mcnButton{"
				+" 		display:block;"
				+" 	}"
				+" 	.mcnImage{"
				+" 		vertical-align:bottom;"
				+" 	}"
				+" 	.mcnTextContent{"
				+" 		word-break:break-word;"
				+" 	}"
				+" 	.mcnTextContent img{"
				+" 		height:auto !important;"
				+" 	}"
				+" 	.mcnDividerBlock{"
				+" 		table-layout:fixed !important;"
				+" 	}"
				
				+" 	h1{"
				+" 		color:#222222;"
				+" 		font-family:Helvetica;"
				+" 		font-size:40px;"
				+" 		font-style:normal;"
				+" 		font-weight:bold;"
				+" 		line-height:150%;"
				+" 		letter-spacing:normal;"
				+" 		text-align:center;"
				+" 	}"
				
				+" 	h2{"
				+" 		color:#222222;"
				+" 		font-family:Helvetica;"
				+" 		font-size:34px;"
				+" 		font-style:normal;"
				+" 		font-weight:bold;"
				+" 		line-height:150%;"
				+" 		letter-spacing:normal;"
				+" 		text-align:left;"
				+" 	}"
				
				+" 	h3{"
					+" 	color:#444444;"
					+" 	font-family:Helvetica;"
					+" 	font-size:22px;"
					+" 	font-style:normal;"
					+" 	font-weight:bold;"
					+" 	line-height:150%;"
					+" 	letter-spacing:normal;"
					+" 	text-align:left;"
				+" 	}"
				
				+" 	h4{"
				+" 		color:#999999;"
				+" 		font-family:Georgia;"
				+" 		font-size:20px;"
				+" 		font-style:italic;"
				+" 		font-weight:normal;"
				+" 		line-height:125%;"
				+" 		letter-spacing:normal;"
				+" 		text-align:center;"
				+" 	}"
				
				+" 	#templateHeader{"
				+" 		background-color:#f7f7f7;"
				+" 		background-image:none;"
				+" 		background-repeat:no-repeat;"
				+" 		background-position:center;"
				+" 		background-size:cover;"
				+" 		border-top:0;"
				+" 		border-bottom:0;"
				+" 		padding-top:0px;"
				+" 		padding-bottom:0px;"
				+" 	}"
				+" 	.headerContainer{"
				+" 		background-color:transparent;"
				+" 		background-image:none;"
				+" 		background-repeat:no-repeat;"
				+" 		background-position:center;"
				+" 		background-size:cover;"
				+" 		border-top:0;"
				+" 		border-bottom:0;"
				+" 		padding-top:0;"
				+" 		padding-bottom:0;"
				+" 	}"
				
				+" 	.headerContainer .mcnTextContent,.headerContainer .mcnTextContent p{"
				+" 		color:#808080;"
				+" 		font-family:Helvetica;"
				+" 		font-size:16px;"
				+" 		line-height:150%;"
				+" 		text-align:left;"
				+" 	}"
				
				+" 	.headerContainer .mcnTextContent a,.headerContainer .mcnTextContent p a{"
				+" 		color:#00ADD8;"
				+" 		font-weight:normal;"
				+"		text-decoration:underline;"
				+" 	}"
				
				+" 	#templateBody{"
				+" 		background-color:#FFFFFF;"
				+" 		background-image:none;"
				+" 		background-repeat:no-repeat;"
				+" 		background-position:center;"
				+" 		background-size:cover;"
				+" 		border-top:0;"
				+" 		border-bottom:0;"
				+" 		padding-top:0px;"
				+" 		padding-bottom:0px;"
				+" 	}"
				
				+" 	.bodyContainer{"
				+" 		background-color:transparent;"
				+" 		background-image:none;"
				+" 		background-repeat:no-repeat;"
				+" 		background-position:center;"
				+" 		background-size:cover;"
				+" 		border-top:0;"
				+" 		border-bottom:0;"
				+" 		padding-top:0;"
				+" 		padding-bottom:0;"
				+" 	}"
				
				+" 	.bodyContainer .mcnTextContent,.bodyContainer .mcnTextContent p{"
				+" 		color:#808080;"
				+" 		font-family:Helvetica;"
				+" 		font-size:16px;"
				+" 		line-height:150%;"
				+" 		text-align:left;"
				+" 	}"
				
				+" 	.bodyContainer .mcnTextContent a,.bodyContainer .mcnTextContent p a{"
				+" 		color:#00ADD8;"
				+" 		font-weight:normal;"
				+" 		text-decoration:underline;"
				+" 	}"
				
				+" 	#templateFooter{"
				+" 		background-color:#3f3717;   "
				+" 		background-image:none;   "
				+" 		background-repeat:no-repeat;   "
				+" 		background-position:center;   "
				+" 		background-size:cover;   "
				+" 		border-top:0;   "
				+" 		border-bottom:0;   "
				+" 		padding-top:0px;   "
				+" 		padding-bottom:0px;   "
				+" 	}"
				
				+" 	.footerContainer{"
				+" 		background-color:transparent;   "
				+" 		background-image:none;   "
				+" 		background-repeat:no-repeat;   "
				+" 		background-position:center;   "
				+" 		background-size:cover;   "
				+" 		border-top:0;   "
				+" 		border-bottom:0;   "
				+" 		padding-top:0;   "
				+" 		padding-bottom:0;   "
				+" 	}"
				
				+" 	.footerContainer .mcnTextContent,.footerContainer .mcnTextContent p{"
				+" 		font-family:Helvetica;   "
				+" 		font-size:12px;   "
				+" 		line-height:150%;   "
				+" 		text-align:center;   "
				+" 	}"
				
				+" 	.footerContainer .mcnTextContent a,.footerContainer .mcnTextContent p a{"
				+" 		color:#FFFFFF;   "
				+" 		font-weight:normal;   "
				+" 		text-decoration:underline;   "
				+" 	}"
			+" 	@media only screen and (min-width:768px){"
			+" 		.templateContainer{"
			+" 			width:600px !important;"
			+" 		}"

			+" }	@media only screen and (max-width: 480px){"
			+" 		body,table,td,p,a,li,blockquote{"
			+" 			-webkit-text-size-adjust:none !important;"
			+" 		}"
			
			+" }	@media only screen and (max-width: 480px){" 
			+"  		body{"
			+" 			width:100% !important;   "
			+" 			min-width:100% !important;   "
			+" 		}"

			+" }	@media only screen and (max-width: 480px){"
			+" 		.mcnImage{"
			+" 			width:100% !important;   "
			+" 		}"
			
			+" }	@media only screen and (max-width: 480px){"
			+" 		.mcnCartContainer,.mcnCaptionTopContent,.mcnRecContentContainer,.mcnCaptionBottomContent,.mcnTextContentContainer,.mcnBoxedTextContentContainer,.mcnImageGroupContentContainer,.mcnCaptionLeftTextContentContainer,.mcnCaptionRightTextContentContainer,.mcnCaptionLeftImageContentContainer,.mcnCaptionRightImageContentContainer,.mcnImageCardLeftTextContentContainer,.mcnImageCardRightTextContentContainer{"
			+" 			max-width:100% !important;   "
			+" 			width:100% !important;   "
			+" 		}"

			+" }	@media only screen and (max-width: 480px){"
			+" 		.mcnBoxedTextContentContainer{"
			+" 			min-width:100% !important;"
			+" 		}"

			+" }	@media only screen and (max-width: 480px){"
			+" 		.mcnImageGroupContent{"
			+" 			padding:9px !important;"
			+" 		}"

			+" }	@media only screen and (max-width: 480px){"
			+" 		.mcnCaptionLeftContentOuter .mcnTextContent,.mcnCaptionRightContentOuter .mcnTextContent{"
			+" 			padding-top:9px !important;"
			+" 		}"

			+" }	@media only screen and (max-width: 480px){"
			+" 		.mcnImageCardTopImageContent,.mcnCaptionBlockInner .mcnCaptionTopContent:last-child .mcnTextContent{"
			+" 			padding-top:18px !important;"
			+" 		}"

			+" }	@media only screen and (max-width: 480px){"
			+" 		.mcnImageCardBottomImageContent{"
			+" 			padding-bottom:9px !important;"
			+" 		}"

			+" }	@media only screen and (max-width: 480px){"
			+" 		.mcnImageGroupBlockInner{"
			+" 			padding-top:0 !important;"
			+" 			padding-bottom:0 !important;"
			+" 		}"

			+" }	@media only screen and (max-width: 480px){"
			+" 		.mcnImageGroupBlockOuter{"
			+" 			padding-top:9px !important;"
			+" 			padding-bottom:9px !important;"
			+" 		}"

			+" }	@media only screen and (max-width: 480px){"
			+" 		.mcnTextContent,.mcnBoxedTextContentColumn{"
			+" 			padding-right:18px !important;   "
			+" 			padding-left:18px !important;   "
			+" 		}"

			+" }	@media only screen and (max-width: 480px){"
			+" 		.mcnImageCardLeftImageContent,.mcnImageCardRightImageContent{"
			+" 			padding-right:18px !important;   "
			+" 			padding-bottom:0 !important;   "
			+" 			padding-left:18px !important;   "
			+" 		}"

			+" }	@media only screen and (max-width: 480px){"
			+" 		.mcpreview-image-uploader{"
			+" 			display:none !important;   "
			+" 			width:100% !important;   "
			+" 		}"

			+" }	@media only screen and (max-width: 480px){"
				
			+" 		h1{"
			+" 			font-size:30px !important;"
			+" 			line-height:125% !important;"
			+" 		}"

			+" }	@media only screen and (max-width: 480px){"
				
			+" 		h2{"
			+" 			font-size:26px !important;"
			+" 			line-height:125% !important;"
			+" 		}"

			+" }	@media only screen and (max-width: 480px){"
				
			+" 		h3{"
			+" 			font-size:20px !important;   "
			+" 			line-height:150% !important;   "
			+" 		}"

			+" }	@media only screen and (max-width: 480px){"
				
			+" 		h4{   "
			+" 			font-size:18px !important;   "
			+" 			line-height:150% !important;   "
			+" 		}"

			+" }	@media only screen and (max-width: 480px){   "
				
			+" 		.mcnBoxedTextContentContainer .mcnTextContent,.mcnBoxedTextContentContainer .mcnTextContent p{   "
			+" 			font-size:14px !important;   "
			+" 			line-height:150% !important;   "
			+" 		}"

			+" }	@media only screen and (max-width: 480px){   "
				
			+" 		.headerContainer .mcnTextContent,.headerContainer .mcnTextContent p{   "
			+" 			font-size:16px !important;   "
			+" 			line-height:150% !important;   "
			+" 		}"

			+" }	@media only screen and (max-width: 480px){   "
				
			+" 		.bodyContainer .mcnTextContent,.bodyContainer .mcnTextContent p{   "
			+" 			font-size:16px !important;   "
			+" 			line-height:150% !important;   "
			+" 		}"

			+" }	@media only screen and (max-width: 480px){"
				
			+" 		.footerContainer .mcnTextContent,.footerContainer .mcnTextContent p{"
			+" 			font-size:14px !important;   "
			+" 			line-height:150% !important;   "
			+" 		}"

			+" }</style></head>"
			+"  <body>"
					
					
					
			        +"   <center>"
			            +"   <table align='center' border='0' cellpadding='0' cellspacing='0' height='100%' width='100%' id='bodyTable'>   "
			                +"   <tr>"
			                    +"   <td align='center' valign='top' id='bodyCell'>   "
			                      
			                        +"   <table border='0' cellpadding='0' cellspacing='0' width='100%'>   "
										+"   <tr>   "
											+"   <td align='center' valign='top' id='templateHeader' data-template-container>   "
												
												+"   <table align='center' border='0' cellpadding='0' cellspacing='0' width='100%' class='templateContainer'>   "
													+"   <tr>   "
			                                			+"   <td valign='top' class='headerContainer'><table border='0' cellpadding='0' cellspacing='0' width='100%' class='mcnImageBlock' style='min-width:100%;'>   "
			    +"   <tbody class='mcnImageBlockOuter'>   "
			            +"   <tr>   "
			                +"   <td valign='top' style='padding:9px' class='mcnImageBlockInner'>   "
			                    +"   <table align='left' width='100%' border='0' cellpadding='0' cellspacing='0' class='mcnImageContentContainer' style='min-width:100%;'>   "
			                        +"   <tbody><tr>   "
			                            +"   <td class='mcnImageContent' valign='top' style='padding-right: 9px; padding-left: 9px; padding-top: 0; padding-bottom: 0; text-align:center;'>   "
			                                
			                                    
			                                        +"   <img align='center' alt='' src='"+merchantLogo+"' width='235' style='max-width:235px; padding-bottom: 0; display: inline !important; vertical-align: bottom;' class='mcnImage'>   "
			                                    
			                                
			                            +"   </td>   "
			                        +"   </tr>   "
			                    +"   </tbody>  </table>   "
			                +"   </td>   "
			            +"   </tr>   "
			    +"   </tbody>   "
			+"   </table>   </td>   "
													+"   </tr>   "
												+"   </table>   "
												
											+"   </td>   "
			                            +"   </tr>   "
										+"   <tr>   "
											+"   <td align='center' valign='top' id='templateBody' data-template-container>   "
												
												+"   <table align='center' border='0' cellpadding='0' cellspacing='0' width='100%' class='templateContainer'>   "
													+"   <tr>   "
			                                			+"   <td valign='top' class='bodyContainer'><table border='0' cellpadding='0' cellspacing='0' width='100%' class='mcnTextBlock' style='min-width:100%;'>   "
			    +"   <tbody class='mcnTextBlockOuter'>   "
			        +"   <tr>   "
			            +"   <td valign='top' class='mcnTextBlockInner' style='padding-top:9px;'>   "
			              	
						    
							
			                +"   <table align='left' border='0' cellpadding='0' cellspacing='0' style='max-width:100%; min-width:100%;' width='100%' class='mcnTextContentContainer'>   "
			                    +"   <tbody><tr>   "
			                        
			                        +"   <td valign='top' class='mcnTextContent' style='padding-top:0; padding-right:18px; padding-bottom:9px; padding-left:18px;'>   "
			                        
			                            +"   <h4>" + mailHead +"</h4>   "

			                        +"   </td>   "
			                    +"   </tr>   "
			                +"   </tbody></table>   "
							
			            +"   </td>   "
			        +"   </tr>   "
			    +"   </tbody>   "
			+"   </table><table border='0' cellpadding='0' cellspacing='0' width='100%' class='mcnDividerBlock' style='min-width:100%;'>   "
			    +"   <tbody class='mcnDividerBlockOuter'>   "
			        +"   <tr>   "
			            +"   <td class='mcnDividerBlockInner' style='min-width: 100%; padding: 27px 18px 0px;'>   "
			                +"   <table class='mcnDividerContent' border='0' cellpadding='0' cellspacing='0' width='100%' style='min-width:100%;'>   "
			                    +"   <tbody><tr>   "
			                        +"   <td>   "
			                            +"   <span></span>   "
			                        +"   </td>   "
			                    +"   </tr>   "
			                +"   </tbody></table>   "

			            +"   </td>   "
			        +"   </tr>   "
			    +"   </tbody>   "
			+"   </table><table border='0' cellpadding='0' cellspacing='0' width='100%' class='mcnDividerBlock' style='min-width:100%;'>   "
			    +"   <tbody class='mcnDividerBlockOuter'>   "
			        +"   <tr>   "
			            +"   <td class='mcnDividerBlockInner' style='min-width: 100%; padding: 9px 18px 0px;'>   "
			                +"   <table class='mcnDividerContent' border='0' cellpadding='0' cellspacing='0' width='100%' style='min-width:100%;'>   "
			                    +"   <tbody><tr>   "
			                        +"   <td>   "
			                            +"   <span></span>   "
			                        +"   </td>   "
			                    +"   </tr>   "
			                +"   </tbody></table>   "

			            +"   </td>   "
			        +"   </tr>   "
			    +"   </tbody>   "
			+"   </table><table border='0' cellpadding='0' cellspacing='0' width='100%' class='mcnTextBlock' style='min-width:100%;'>   "
			    +"   <tbody class='mcnTextBlockOuter'>   "
			        +"   <tr>   "
			            +"   <td valign='top' class='mcnTextBlockInner' style='padding-top:9px;'>   "
			            
							+"   <table align='left' border='0' cellspacing='0' cellpadding='0' width='100%' style='width:100%;'>   "
							+"   <tr>   "
							
							+"   <td valign='top' width='600' style='width:600px;'>   "
							+"   <![endif]-->   "
			                +"   <table align='left' border='0' cellpadding='0' cellspacing='0' style='max-width:100%; min-width:100%;' width='100%' class='mcnTextContentContainer'>   "
			                    +"   <tbody><tr>   "
			                        
			                        +"   <td valign='top' class='mcnTextContent' style='padding-top:0; padding-right:18px; padding-bottom:9px; padding-left:18px;'>   "
			                        
			                            +"   <p>Dear " + customerName +"</p>   "

			+"   <p>"+ mailBody +"&nbsp;</p>   "

			+"   <p>Best,</p>   "

			+"   <p>"+mailRegards+" team</p>   "

			                        +"   </td>   "
			                    +"   </tr>   "
			                +"   </tbody></table>   "
							
			                
							
			            +"   </td>   "
			        +"   </tr>   "
			    +"   </tbody>   "
			+"   </table><table border='0' cellpadding='0' cellspacing='0' width='100%' class='mcnDividerBlock' style='min-width:100%;'>   "
			    +"   <tbody class='mcnDividerBlockOuter'>   "
			        +"   <tr>   "
			            +"   <td class='mcnDividerBlockInner' style='min-width: 100%; padding: 9px 18px;'>   "
			                +"   <table class='mcnDividerContent' border='0' cellpadding='0' cellspacing='0' width='100%' style='min-width: 100%;border-top: 1px solid #E0E0E0;'>   "
			                    +"   <tbody><tr>   "
			                        +"   <td>   "
			                            +"   <span></span>   "
			                        +"   </td>   "
			                    +"   </tr>   "
			                +"   </tbody></table>   "

			            +"   </td>   "
			        +"   </tr>   "
			    +"   </tbody>   "
			+"   </table></td>   "
													+"   </tr>   "
												+"   </table>   "
												
											+"   </td>   "
			                            +"   </tr>   "
			                            +"   <tr>   "
											+"   <td align='center' valign='top' id='templateFooter' data-template-container>   "
												
												+"   <table align='center' border='0' cellpadding='0' cellspacing='0' width='100%' class='templateContainer'>   "
													+"   <tr>   "
			                                			+"   <td valign='top' class='footerContainer'><table border='0' cellpadding='0' cellspacing='0' width='100%' class='mcnFollowBlock' style='min-width:100%;'>   "
			    +"   <tbody class='mcnFollowBlockOuter'>   "
			        +"   <tr>   "
			            +"   <td align='center' valign='top' style='padding:9px' class='mcnFollowBlockInner'>   "
			                +"   <table border='0' cellpadding='0' cellspacing='0' width='100%' class='mcnFollowContentContainer' style='min-width:100%;'>   "
			    +"   <tbody><tr>   "
			        +"   <td align='center' style='padding-left:9px;padding-right:9px;'>   "
			            +"   <table border='0' cellpadding='0' cellspacing='0' width='100%' style='min-width:100%;' class='mcnFollowContent'>   "
			                +"   <tbody><tr>   "
			                    +"   <td align='center' valign='top' style='padding-top:9px; padding-right:9px; padding-left:9px;'>   "
			                        +"   <table align='center' border='0' cellpadding='0' cellspacing='0'>   "
			                            +"   <tbody><tr>   "
			                                +"   <td align='center' valign='top'>   "
			                                  
			                                        
			                                        
			                                            +"   <table align='left' border='0' cellpadding='0' cellspacing='0' style='display:inline;'>   "
			                                                +"   <tbody><tr>   "
			                                                    +"   <td valign='top' style='padding-right:10px; padding-bottom:9px;' class='mcnFollowContentItemContainer'>   "
			                                                        +"   <table border='0' cellpadding='0' cellspacing='0' width='100%' class='mcnFollowContentItem'>   "
			                                                            +"   <tbody><tr>   "
			                                                                +"   <td align='left' valign='middle' style='padding-top:5px; padding-right:10px; padding-bottom:5px; padding-left:9px;'>   "
			                                                                    +"   <table align='left' border='0' cellpadding='0' cellspacing='0' width=''>   "
			                                                                        +"   <tbody><tr>   "
			                                                                            
			                                                                                +"   <td align='center' valign='middle' width='24' class='mcnFollowIconContent'>   "
			                                                                                    +"   <a href='http://www.linkedin.com/company/13358770/' target='_blank'><img src='https://cdn-images.mailchimp.com/icons/social-block-v2/outline-light-linkedin-48.png' style='display:block;' height='24' width='24' class=''></a>   "
			                                                                                +"   </td>   "
			                                                                            
			                                                                            
			                                                                        +"   </tr>   "
			                                                                    +"   </tbody></table>   "
			                                                                +"   </td>   "
			                                                            +"   </tr>   "
			                                                        +"   </tbody></table>   "
			                                                    +"   </td>   "
			                                                +"   </tr>   "
			                                            +"   </tbody></table>   "
			                                        
			                                       
			                                        
			                                        
			                                            +"   <table align='left' border='0' cellpadding='0' cellspacing='0' style='display:inline;'>   "
			                                                +"   <tbody><tr>   "
			                                                    +"   <td valign='top' style='padding-right:10px; padding-bottom:9px;' class='mcnFollowContentItemContainer'>   "
			                                                        +"   <table border='0' cellpadding='0' cellspacing='0' width='100%' class='mcnFollowContentItem'>   "
			                                                            +"   <tbody><tr>   "
			                                                                +"   <td align='left' valign='middle' style='padding-top:5px; padding-right:10px; padding-bottom:5px; padding-left:9px;'>   "
			                                                                    +"   <table align='left' border='0' cellpadding='0' cellspacing='0' width=''>   "
			                                                                        +"   <tbody><tr>   "
			                                                                            
			                                                                                +"   <td align='center' valign='middle' width='24' class='mcnFollowIconContent'>   "
			                                                                                    +"   <a href='http://www.twitter.com/mKonnekt' target='_blank'><img src='https://cdn-images.mailchimp.com/icons/social-block-v2/outline-light-twitter-48.png' style='display:block;' height='24' width='24' class=''></a>   "
			                                                                                +"   </td>   "
			                                                                            
			                                                                            
			                                                                        +"   </tr>   "
			                                                                    +"   </tbody></table>   "
			                                                                +"   </td>   "
			                                                            +"   </tr>   "
			                                                        +"   </tbody></table>   "
			                                                    +"   </td>   "
			                                                +"   </tr>   "
			                                            +"   </tbody></table>   "
			                                        
			                                      
			                                    
			                                        
			                                        
			                                        
			                                            +"   <table align='left' border='0' cellpadding='0' cellspacing='0' style='display:inline;'>   "
			                                                +"   <tbody><tr>   "
			                                                    +"   <td valign='top' style='padding-right:10px; padding-bottom:9px;' class='mcnFollowContentItemContainer'>   "
			                                                        +"   <table border='0' cellpadding='0' cellspacing='0' width='100%' class='mcnFollowContentItem'>   "
			                                                            +"   <tbody><tr>   "
			                                                                +"   <td align='left' valign='middle' style='padding-top:5px; padding-right:10px; padding-bottom:5px; padding-left:9px;'>   "
			                                                                    +"  <table align='left' border='0' cellpadding='0' cellspacing='0' width=''>   "
			                                                                        +"   <tbody><tr>   "
			                                                                            
			                                                                                +"   <td align='center' valign='middle' width='24' class='mcnFollowIconContent'>   "
			                                                                                    +"   <a href='http://www.instagram.com/mKonnekt' target='_blank'><img src='https://cdn-images.mailchimp.com/icons/social-block-v2/outline-light-instagram-48.png' style='display:block;' height='24' width='24' class=''></a>   "
			                                                                                +"   </td>   "
			                                                                            
			                                                                            
			                                                                        +"   </tr>   "
			                                                                    +"   </tbody></table>   "
			                                                                +"   </td>   "
			                                                            +"   </tr>   "
			                                                        +"   </tbody></table>   "
			                                                    +"   </td>   "
			                                                +"   </tr>   "
			                                            +"   </tbody></table>   "
			                                        
			                                       
			                                    
			                                        
			                                        
			                                        
			                                            +"   <table align='left' border='0' cellpadding='0' cellspacing='0' style='display:inline;'>   "
			                                                +"   <tbody><tr>   "
			                                                    +"   <td valign='top' style='padding-right:0; padding-bottom:9px;' class='mcnFollowContentItemContainer'>   "
			                                                        +"   <table border='0' cellpadding='0' cellspacing='0' width='100%' class='mcnFollowContentItem'>   "
			                                                            +"   <tbody><tr>   "
			                                                                +"   <td align='left' valign='middle' style='padding-top:5px; padding-right:10px; padding-bottom:5px; padding-left:9px;'>   "
			                                                                    +"   <table align='left' border='0' cellpadding='0' cellspacing='0' width=''>   "
			                                                                        +"   <tbody><tr>   "
			                                                                            
			                                                                                +"   <td align='center' valign='middle' width='24' class='mcnFollowIconContent'>   "
			                                                                                    +"   <a href='http://www.mkonnekt.com' target='_blank'><img src='https://cdn-images.mailchimp.com/icons/social-block-v2/outline-light-link-48.png' style='display:block;' height='24' width='24' class=''></a>   "
			                                                                                +"   </td>   "
			                                                                            
			                                                                            
			                                                                        +"   </tr>   "
			                                                                    +"   </tbody></table>   "
			                                                                +"   </td>   "
			                                                            +"   </tr>   "
			                                                        +"   </tbody></table>   "
			                                                    +"   </td>   "
			                                                +"   </tr>   "
			                                            +"   </tbody></table>   "
			                                        
			                                        
			                                +"   </td>   "
			                            +"   </tr>   "
			                        +"   </tbody></table>   "
			                    +"   </td>   "
			                +"   </tr>   "
			            +"   </tbody></table>   "
			        +"   </td>   "
			    +"   </tr>   "
			+"   </tbody></table>   "

			            +"   </td>   "
			        +"   </tr>   "
			    +"   </tbody>   "
			+"   </table><table border='0' cellpadding='0' cellspacing='0' width='100%' class='mcnDividerBlock' style='min-width:100%;'>   "
			    +"   <tbody class='mcnDividerBlockOuter'>   "
			        +"   <tr>   "
			            +"   <td class='mcnDividerBlockInner' style='min-width:100%; padding:18px;'>   "
			                +"   <table class='mcnDividerContent' border='0' cellpadding='0' cellspacing='0' width='100%' style='min-width: 100%;border-top: 2px solid #505050;'>   "
			                    +"   <tbody><tr>   "
			                        +"   <td>   "
			                            +"   <span></span>   "
			                        +"   </td>   "
			                    +"   </tr>   "
			                +"   </tbody></table>   "

			            +"   </td>   "
			        +"   </tr>   "
			    +"   </tbody>   "
			+"   </table><table border='0' cellpadding='0' cellspacing='0' width='100%' class='mcnTextBlock' style='min-width:100%;'>   "
			    +"   <tbody class='mcnTextBlockOuter'>   "
			        +"   <tr>   "
			            +"   <td valign='top' class='mcnTextBlockInner' style='padding-top:9px;'>   "
			              	
						    
							
			                +"   <table align='left' border='0' cellpadding='0' cellspacing='0' style='max-width:100%; min-width:100%;' width='100%' class='mcnTextContentContainer'>   "
			                    +"   <tbody><tr>   "
			                        
			                        +"   <td valign='top' class='mcnTextContent' style='padding-top:0; padding-right:18px; padding-bottom:9px; padding-left:18px;'>   "
			                        
			                            +"   <em>Copyright © *|CURRENT_YEAR|* mKonnekt&nbsp;LLC, All rights reserved.</em>   "
			                        +"   </td>   "
			                    +"   </tr>   "
			                +"   </tbody></table>   "
							
			                
							
			            +"   </td>   "
			        +"   </tr>   "
			    +"   </tbody>   "
			+"   </table></td>   "
													+"   </tr>   "
												+"   </table>   "
												
											+"   </td>   "
			                            +"   </tr>   "
			                        +"   </table>   "
			                        
			                    +"   </td>   "
			                +"   </tr>   "
			            +"   </table>   "
			        +"   </center>   "
			    +"   </body>   "
			+"   </html>   ";

	
	
	return data;
}
	
//	public static void thankyouMailForFeedback(CustomerFeedback customerFeedback, Customer customer, Boolean flag) {
//		
//		try {
//			Message message = new MimeMessage(
//					SendMailProperty.mailProperty(IConstant.FROM_EMAIL_ID, IConstant.FROM_PASSWORD));
//			message.setFrom(new InternetAddress(IConstant.FROM_EMAIL_ID));
//			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(customerFeedback.getCustomer().getEmailId()));
//			message.setSubject("Regards: Thankyou for feedback");
//			String customerName = "Customer";
//			
//			
//			String merchantLogo="";
//			if (customer==null || customer.getMerchantt()==null || customer.getMerchantt().getMerchantLogo() == null) {
//	            merchantLogo = "http://www.dev.foodkonnekt.com/app/foodnew.jpg";
//	        } else {
//	            merchantLogo = UrlConstant.BASE_PORT + customer.getMerchantt().getMerchantLogo();
//	        }
//			
//			if(customer!=null && customer.getFirstName()!= null){
//				customerName = customer.getFirstName();
//			}
//			
//			String msg = "<div style='width: 700px; height: auto; border: solid 1px #ddd; box-shadow: 0px 0px 6px #ddd; background-color: #f2f2f2; text-align: center; margin: 40px 0px 0px 35px;'>"
//					+ "<div style='width: 700px; height: 85px; padding: 20px 0px; text-align: center;'>&nbsp;<img src='"
//					+ merchantLogo + "' height='80' /></div>"
//					+ "<div style='background-color: #fff; padding: 20px 0px 0px 0px; width: 90%; margin: 0 auto 65px auto;'>"
//					+ "<p style='font-size: 20px; color: #e6880f;'>Dear " + customerName  + ",</p>";
//			
//					/*msg = msg+"<br/> <a style='text-decoration: none; margin: 16px 0px; display: inline-block; background-color: #f8981d; color: #fff; padding: 10px 12px; border-radius: 10px; font-weight: 600; font-size: 15px;' "
//							    + "href= http://www.tacosymasdallas.com/ target='_blank'>Please find the link to the certificate</a><br /> this is our way of saying Thanks for providing us your valuable feedback. We will get back to you shortly.";*/
//
//			/*if(flag == true){
//				msg = msg+"<br/> We Thank You for providing your valuable feedback and for your time as well. We sincerely apologize about your experience. We take customer service very seriously and we are sorry about dropping the ball in this case. Can you please send us an email with your contact information so that one of our managers can reach out to you.<br/> We look forward to hearing from you soon.";	
//			}else{*/
//				msg = msg+"<br/><p> We Thank You for your time and for your feedback. We will review your feedback and we will get back to you shortly.</P>";	
//			//}
//					
//					
//					
//					
//					msg = msg +"<br>Best,<br>Kritiq Team<br>";
//			msg = msg + "</div>";
//			msg = msg+ "<div style='height: 70px; text-align: center;'>&nbsp;<img src='http://www.dev.foodkonnekt.com/app/foodnew.jpg' alt='' width='200' height='63' /></div>"
//					+ "</div>"
//					+ "<p style='color: #676767; font-size: 11px;'>Automated email. Please do not reply to this sender email.</p>";
//	
//			message.setContent(msg, "text/html");	
//
//			Transport.send(message);
//		
//		} catch (MessagingException e) {
//			throw new RuntimeException(e);
//		}
//
//	}
		




	
	public static void feedbackResultOfClientToMerchantMail( CustomerFeedback customerFeedback, Customer customer, int rating, List<CustomerFeedbackAnswer> custFedbackAnswer , String email) {
		System.out.println(customer.getMerchantt().getOwner().getEmail());
		try {
			Message message = new MimeMessage(
					SendMailProperty.mailProperty(IConstant.FROM_EMAIL_ID, IConstant.FROM_PASSWORD));
			message.setFrom(new InternetAddress(IConstant.FROM_EMAIL_ID));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
			message.setSubject("Regards: Negative Feedback");
		
			String customerName= "Customer";
		
		
		
		if(customer!=null && customer.getFirstName()!=null){
			customerName=customer.getFirstName();
		}
			/*String cusID = EncryptionDecryptionUtil.encryption(String.valueOf(customerFeedback.getCustomer().getId()));
			String orderId = EncryptionDecryptionUtil.encryption(String.valueOf(customerFeedback.getOrderR().getId()));*/
			
		   /*    String merchantLogo = "http://www.dev.foodkonnekt.com/app/foodnew.jpg";
	        
	     	
			String msg = "<div style='width: 700px; height: auto; border: solid 1px #ddd; box-shadow: 0px 0px 6px #ddd; background-color: #f2f2f2; text-align: center; margin: 40px 0px 0px 35px;'>"
					+ "<div style='width: 700px; height: 85px; padding: 20px 0px; text-align: center;'>&nbsp;<img src='"
					+ merchantLogo + "' height='80' /></div>";
					
			msg = msg+"<p style='font-size: 14px;text-align: left;padding-left:10px;'>Hello "+customer.getMerchantt().getName()+",<p>";
			msg = msg+"<p style='font-size: 14px;text-align: left;padding-left:10px;'>We just received a feedback provided to us by "+customerName+". We are sending you this email for you to be aware of. Please let us know if you would like to respond in any specific manner.,<p>";
			msg = msg+"<p style='font-size: 14px;text-align: left;padding-left:10px;'><a 'font-size: 14px; none; text-align: left;padding-left:10px;' href='" + UrlConstant.WEB_BASE_URL + "/customerfeedback?customerFeedbackId=" + EncryptionDecryptionUtil.encryption(customerFeedback.getId().toString())+ "' target='_blank'>Customer's Feedback</a><p>";*/
		String mailRegards = "Kritiq ";
		String merchantLogo = "https://gallery.mailchimp.com/63d57cb8e02236766b6ec6d06/images/e38be12c-9d09-4248-a809-b45181a5a0f5.jpg";
		String merchantName= customer.getMerchantt().getName();
		String mailHead ="Negative Feedback By Customer";
		String mailBody = "We have recently had a customer who reviewed us unfavorably on one or more criteria. Please find the link to the customer's detailed feedback. This is for your information so that necessary actions can be executed to alleviate the customer's conerns. <br>"
							+"<p style='font-size: 14px;text-align: left;padding-left:10px;'><a 'font-size: 14px; none; text-align: left;padding-left:10px;' href='" + UrlConstant.WEB_BASE_URL + "/customerfeedback?customerFeedbackId=" + EncryptionDecryptionUtil.encryption(customerFeedback.getId().toString())+ "' target='_blank'>Customer's Feedback</a><p>"
		+ "<style type='text/css'>table, th, td { border: 1px solid black; border-collapse: collapse; } <col width='1030'>"
		+ "</style>"
		+ "<table style='width:100%; border: 1px solid black;' >"
			+ "<tbody style=' border: 1px solid black;'>"
				+ "<tr>"
					+ "<th style='width:100px'>Platform</th>"
					+ "<td style='width:100px'>Facebook</td>"
				+ "</tr>"
				+ "<tr>"
					+ "<th>Review Date</th>"
					+ "<td style='width:100px'>"+customerFeedback.getCreateDate()+"/td>"
				+ "</tr>"
				+ "<tr>"
					+ "<th style='width:100px'>Customer Name</th>"
					+ "<th>"+customerName+"</th>"
				+ "</tr>"
				+ "<tr>"
					+ "<th>Rating</th>"
					+ "<td style='width:100px'>"+rating +" star</td>"
				+ "</tr>"
				+ "<tr>"
					+ "<th style='width:100px'>Review</th>"
					+ "<th>"+customerFeedback.getCustomerComments() +"</th>"
				+ "</tr>"
			+ "</tbody>"
		+ "</table> ";
			/*msg = msg+"<p style='font-size: 14px;text-align: left;padding-left:10px;'>This is the score for '"+customer.getFirstName()+"' for order ";
			msg = msg+"<a 'font-size: 14px; none; text-align: left;padding-left:10px;' href='" + UrlConstant.WEB_BASE_URL + "/orderRec?orderid=" + orderId
				+ "&customerid=" + cusID+ "' target='_blank'>"+customer.getMerchantt().getPosMerchantId()+"</a></p>";*/
			
			
			/*msg = msg+"<table><thead><tr><th>Question</th><th></th><th>Customer Rate</th></thead><tbody>";
			for(int j=0;j<merchantCount;j++)
			{
				msg = msg+"<tr><td width='230' align='left'>"+custFedbackAnswer.get(j).getFeedbackQuestion().getQuestion()+"</td><td>:</td><td>"+custFedbackAnswer.get(j).getAnswer()+"</td></tr>";
				
			}
			
			msg = msg+"</tbody></table>";*/
			
		/*	msg = msg + "</div>";
			msg = msg + "<p>We appreciate your business with us!</p>"
					+ "<div style='height: 70px; text-align: center;'>&nbsp;<img src='http://www.dev.foodkonnekt.com/app/foodnew.jpg' alt='' width='200' height='63' /></div>"
					+ "</div>"
					+ "<p style='color: #676767; font-size: 11px;'>Automated email. Please do not reply to this sender email.</p>";*/
		
		String msg = kritiqMailFormate(customerName,mailHead,mailBody,merchantLogo,mailRegards);
		//String msg = kritiqMailFormate(merchantName,mailHead,mailBody);
			message.setContent(msg, "text/html");	
			Transport.send(message);
		
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}

	}

	
	/*public static void feedbackResultOfClientToSupportMail( CustomerFeedback customerFeedback, Customer customer,
															int supportCount, List<CustomerFeedbackAnswer> custFedbackAnswer) {
		
		try {
			Message message = new MimeMessage(
					SendMailProperty.mailProperty(IConstant.FROM_EMAIL_ID, IConstant.FROM_PASSWORD));
			message.setFrom(new InternetAddress(IConstant.FROM_EMAIL_ID));
			//receipient will be foodkonnekt support
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("support@foodkonnekt.com"));
			message.setSubject("Regards: Feedback Result for Online Service");
		
			
			String cusID = EncryptionDecryptionUtil.encryption(String.valueOf(customerFeedback.getCustomer().getId()));
			String orderId = EncryptionDecryptionUtil.encryption(String.valueOf(customerFeedback.getOrderR().getId()));

		
			
			String merchantLogo = "http://www.dev.foodkonnekt.com/app/foodnew.jpg";
			
			
			String merchantName= customer.getMerchantt().getName();
			String mailHead ="Negative Feedback By Customer";
			String mailBody = "<p style='font-size: 14px;text-align: left;padding-left:10px;'>This is the score for '"+customer.getFirstName()+"' for order ";
			mailBody = mailBody+"<a 'font-size: 14px; none; text-align: left;padding-left:10px;' href='" + UrlConstant.WEB_BASE_URL + "/orderRec?orderid=" + orderId
					+ "&customerid=" + cusID+ "' target='_blank'>"+customer.getMerchantt().getPosMerchantId()+"</a> </p>";
				
				
			mailBody = mailBody+"<table><thead><tr><th>Question</th><th></th><th>Customer Rate</th></thead><tbody>";
				for(int j=0;j<supportCount;j++)
				{
					mailBody = mailBody+"<tr><td width='230' align='left'>"+custFedbackAnswer.get(j).getFeedbackQuestion().getQuestion()+"</td><td>:</td><td>"+custFedbackAnswer.get(j).getAnswer()+"</td></tr>";
					
				}
				mailBody = mailBody+"</tbody></table>";
				
				mailBody = mailBody+"<br><p style='font-size: 14px;text-align: left;padding-left:10px;'><a 'font-size: 14px; none; text-align: left;padding-left:10px;' href='" + UrlConstant.WEB_BASE_URL + "/customerfeedback?customerFeedbackId=" + EncryptionDecryptionUtil.encryption(customerFeedback.getId().toString())+ "' target='_blank'>Customer's Feedback</a><p>";
				
				
				mailBody = mailBody + "</div>";
				mailBody = mailBody + "<p>We appreciate your business with us!</p>"           
						+ "<div style='height: 70px; text-align: center;'>&nbsp;<img src='http://www.dev.foodkonnekt.com/app/foodnew.jpg' alt='' width='200' height='63' /></div>"
						+ "</div>"
						+ "<p style='color: #676767; font-size: 11px;'>Automated email. Please do not reply to this sender email.</p>";
	        
			String msg = "<div style='width: 700px; height: auto; border: solid 1px #ddd; box-shadow: 0px 0px 6px #ddd; background-color: #f2f2f2; text-align: center; margin: 40px 0px 0px 35px;'>"
					+ "<div style='width: 700px; height: 85px; padding: 20px 0px; text-align: center;'>&nbsp;<img src='"
					+ merchantLogo + "' height='80' /></div>";

			msg = msg+"<p style='font-size: 14px;text-align: left;padding-left:10px;'>This is the score for '"+customer.getFirstName()+"' for order ";
			msg = msg+"<a 'font-size: 14px; none; text-align: left;padding-left:10px;' href='" + UrlConstant.WEB_BASE_URL + "/orderRec?orderid=" + orderId
				+ "&customerid=" + cusID+ "' target='_blank'>"+customer.getMerchantt().getPosMerchantId()+"</a> </p>";
			
			
			msg = msg+"<table><thead><tr><th>Question</th><th></th><th>Customer Rate</th></thead><tbody>";
			for(int j=0;j<supportCount;j++)
			{
				msg = msg+"<tr><td width='230' align='left'>"+custFedbackAnswer.get(j).getFeedbackQuestion().getQuestion()+"</td><td>:</td><td>"+custFedbackAnswer.get(j).getAnswer()+"</td></tr>";
				
			}
			msg = msg+"</tbody></table>";
			
			msg = msg+"<br><p style='font-size: 14px;text-align: left;padding-left:10px;'><a 'font-size: 14px; none; text-align: left;padding-left:10px;' href='" + UrlConstant.WEB_BASE_URL + "/customerfeedback?customerFeedbackId=" + EncryptionDecryptionUtil.encryption(customerFeedback.getId().toString())+ "' target='_blank'>Customer's Feedback</a><p>";
			
			
			msg = msg + "</div>";
			msg = msg + "<p>We appreciate your business with us!</p>"           
					+ "<div style='height: 70px; text-align: center;'>&nbsp;<img src='http://www.dev.foodkonnekt.com/app/foodnew.jpg' alt='' width='200' height='63' /></div>"
					+ "</div>"
					+ "<p style='color: #676767; font-size: 11px;'>Automated email. Please do not reply to this sender email.</p>";
	
			
			
				String msg = kritiqMailFormate(merchantName,mailHead,mailBody);
			
			
			message.setContent(msg, "text/html");	
			Transport.send(message);
		
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}

	}*/

	public static void birthdayWishMail(String wishDate, Customer customer) {

		System.out.println(customer.getEmailId());

		
		
		try {
			Message message = new MimeMessage(
					SendMailProperty.mailProperty(IConstant.FROM_EMAIL_ID, IConstant.FROM_PASSWORD));
			message.setFrom(new InternetAddress(IConstant.FROM_EMAIL_ID));
			//receipient will be foodkonnekt support
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("support@foodkonnekt.com"));
			message.setSubject("Regards: Happy Birthday");

			
			
			String merchantLogo="";
			if (customer.getMerchantt().getMerchantLogo() == null) {
	            merchantLogo = "http://www.dev.foodkonnekt.com/app/foodnew.jpg";
	        } else {
	            merchantLogo = UrlConstant.BASE_PORT + customer.getMerchantt().getMerchantLogo();
	        }
			String msg = "<div style='width: 700px; height: auto; border: solid 1px #ddd; box-shadow: 0px 0px 6px #ddd; background-color: #f2f2f2; text-align: center; margin: 40px 0px 0px 35px;'>"
					+ "<div style='width: 700px; height: 85px; padding: 20px 0px; text-align: center;'>&nbsp;<img src='"
					+ merchantLogo + "' height='80' /></div>"
					+ "<div style='background-color: #fff; padding: 20px 0px 0px 0px; width: 90%; margin: 0 auto 65px auto;'>"
					+ "<p style='font-size: 20px; color: #e6880f;'>Dear " + customer.getFirstName() + ",</p>";
			
			msg = msg+"<br /> <p text-align: left;>On behalf of all our team, we would like to wish you a happy Birthday."
					+ "We wish you a spectacular life and committed behaviour towards your wishes and desires! </p>";
			
			
			msg = msg
					+ "<p><Order details: items, extras, price, fees, promo discount, total $><br>For any further questions, ";
			if(customer.getMerchantt().getPhoneNumber()!=null) { 
				msg = msg +"Please call us at " +customer.getMerchantt().getPhoneNumber() + "";}
			else{
				msg = msg +"Please call our store";
			}
					msg = msg +" <br><br>Regards,<br>" + customer.getMerchantt().getName()
					+ "<br><br>";
			msg = msg + "</div>";
			msg = msg + "<p>We appreciate your business with us!</p>"
					+ "<div style='height: 70px; text-align: center;'>&nbsp;<img src='http://www.dev.foodkonnekt.com/app/foodnew.jpg' alt='' width='200' height='63' /></div>"
					+ "</div>"
					+ "<p style='color: #676767; font-size: 11px;'>Automated email. Please do not reply to this sender email.</p>";	
			message.setContent(msg, "text/html");	
			Transport.send(message);
		
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}

		
	}

	public static void anniversaryWishMail(String wishDate, Customer customer) {
		System.out.println(customer.getEmailId());

		try {
			Message message = new MimeMessage(
					SendMailProperty.mailProperty(IConstant.FROM_EMAIL_ID, IConstant.FROM_PASSWORD));
			message.setFrom(new InternetAddress(IConstant.FROM_EMAIL_ID));
			//receipient will be foodkonnekt support
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("support@foodkonnekt.com"));
			message.setSubject("Regards: Happy Anniversary");

		
			String merchantLogo="";
			if (customer.getMerchantt().getMerchantLogo() == null) {
	            merchantLogo = "http://www.dev.foodkonnekt.com/app/foodnew.jpg";
	        } else {
	            merchantLogo = UrlConstant.BASE_PORT + customer.getMerchantt().getMerchantLogo();
	        }
			String msg = "<div style='width: 700px; height: auto; border: solid 1px #ddd; box-shadow: 0px 0px 6px #ddd; background-color: #f2f2f2; text-align: center; margin: 40px 0px 0px 35px;'>"
					+ "<div style='width: 700px; height: 85px; padding: 20px 0px; text-align: center;'>&nbsp;<img src='"
					+ merchantLogo + "' height='80' /></div>"
					+ "<div style='background-color: #fff; padding: 20px 0px 0px 0px; width: 90%; margin: 0 auto 65px auto;'>"
					+ "<p style='font-size: 20px; color: #e6880f;'>Dear " + customer.getFirstName() + ",</p>";
			
			msg = msg+"<br /> <p text-align: left;>On behalf of all our team, we would like to wish you a happy Anniversary."
					+ "We wish you a spectacular life and committed behaviour towards your wishes and desires! </p>";

			
			msg = msg
					+ "<p><Order details: items, extras, price, fees, promo discount, total $><br>For any further questions, ";
			if(customer.getMerchantt().getPhoneNumber()!=null) { 
				msg = msg +"Please call us at " +customer.getMerchantt().getPhoneNumber() + "";}
			else{
				msg = msg +"Please call our store";
			}
					msg = msg +" <br><br>Regards,<br>" + customer.getMerchantt().getName()
					+ "<br><br>";
			msg = msg + "</div>";
			msg = msg + "<p>We appreciate your business with us!</p>"
					+ "<div style='height: 70px; text-align: center;'>&nbsp;<img src='http://www.dev.foodkonnekt.com/app/foodnew.jpg' alt='' width='200' height='63' /></div>"
					+ "</div>"
					+ "<p style='color: #676767; font-size: 11px;'>Automated email. Please do not reply to this sender email.</p>";	
			message.setContent(msg, "text/html");	
			Transport.send(message);
		
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}

		

	}

	
	
}
