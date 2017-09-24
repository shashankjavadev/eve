<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="resources/js/shop/accordion/jquery-1.11.3.min.js"></script>
<script>
$(document).ready(function() {
	var tax="${order.tax}";
	var subtotal="${order.subTotal}";
	var discount="${order.orderDiscount}";
	   
	tax=Number(tax).toFixed(2);
	subtotal=Number(subtotal).toFixed(2);
	document.getElementById("tax").innerHTML = "$"+tax;
	document.getElementById("subtotal").innerHTML = "$"+subtotal;
	document.getElementById("discount").innerHTML = "$"+discount;
	var convFee="0.0";
	var conv="${order.convenienceFee}";
	if(conv==''){
		conv=convFee;
	}
	
	document.getElementById("convFee").innerHTML = "$"+conv;
});
</script>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<div style='width: 700px; height: auto; border: solid 1px #ddd; box-shadow: 0px 0px 6px #ddd; background-color: #f2f2f2; text-align: center; margin: 40px 0px 0px 35px; margin-left: 25%;'>
					<div style='width: 700px; height: 85px; padding: 20px 0px; text-align: center;'>&nbsp;
					<img src='${merchantLogo }' height='80' /></div>
					<div style='background-color: #fff; padding: 20px 0px 0px 0px; width: 90%; margin: 0 auto 65px auto;'>
					<!-- <p style='font-size: 20px; color: #e6880f;'>Dear " + name + ",</p>
					<p style='font-size: 14px;'>Thank you. We have received your order and are in the process of&nbsp;confirming it.&nbsp;</p> -->
					<div style='text-align: left; padding: 20px 20px;'>
					<table>
					<tbody>
					<tr>
					<td width='230'>Order type</td>
					<td>:</td><td>${order.orderType} </td></tr>
					<tr>
					<td>Order ID</td>
					<td>:</td>
					<td>${order.orderPosId} </td>
					</tr>
					<tr>
					<td>Payment</td><td>:</td><td> ${order.paymentMethod} </td></tr>
					</tbody>
					</table>
					<p><strong>Order Details:</strong><br /></p> <table>${orderdetails} </table>
					<p style='margin: 0;'>-------------------------------------------------------------------------------</p>
					<table><tbody><tr><td width='230'>Sub-total</td><td width='100px'; style="text-align:center"></td><td id="subtotal">
					</td></tr><tr><td>Convenience Fee</td><td>&nbsp;</td><td id="convFee">
					</td></tr>
			<!-- 		
			 if (orderType.toLowerCase().equals("delivery")) { 
				msg = msg + "<tr>" + "<td>Delivery Fee</td>" + "<td>&nbsp;</td>" + "<td>$" + deliverFee + "</td>"
						+ "</tr>";

			}
			
			if (tipAmount!=null && tipAmount>0) {
				msg = msg + "<tr>" + "<td>Tip</td>" + "<td>&nbsp;</td>" + "<td>$" + tipAmount + "</td>"
						+ "</tr>";

			} -->
			<tr><td>Discount</td><td>&nbsp;</td><td id="discount"></td></tr>

					<tr><td>Tax</td><td>&nbsp;</td><td id="tax"></td></tr></tbody>
					</table>
					<p style='margin: 0;'>-------------------------------------------------------------------------------</p>
					<table><tbody><tr><td width='230'>Total</td><td width="100px;" style="text-align:center">&nbsp;</td><td>
					$${order.orderPrice} </td></tr></tbody></table>
					<br /><br /> <strong style='text-decoration: underline;'>Special Instructions :</strong>  note
					 <br /><br /><br /> 
					 <a style='text-decoration: none; margin: 16px 0px; display: inline-block; background-color: #f8981d; color: #fff; padding: 10px 12px; border-radius: 10px; font-weight: 600; font-size: 15px;' href='${kritiqLink}' target='_blank'>Complete the Survey</a><br />
					 <!-- Thank you for your order! It'll be ready for "
					+ orderType + " within the next " + avgTime + " minutes. See you soon.  <br /><br /> Regards,<br />"
					+ merchantName + "</div>"
					+ "<p>Lookout for an update from us on your order confirmation.<br />We appreciate your business with us!</p>"
					+ "<div style='height: 70px; text-align: center;'>&nbsp;<img src='http://www.dev.foodkonnekt.com/app/foodnew.jpg' alt='' width='200' height='63' /></div>"
					+ "</div>"
					+ "<p style='color: #676767; font-size: 11px;'>Automated email. Please do not reply to this sender email.</p>" --> 
					<div style='height: 70px; text-align: center;'>&nbsp;<img src='http://www.dev.foodkonnekt.com/app/foodnew.jpg' alt='' width='200' height='63' /></div>
					</div>
					</div>
					</div>









</body>
</html>