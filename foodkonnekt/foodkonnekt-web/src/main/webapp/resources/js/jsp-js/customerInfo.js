$(document).ready(function() {
$("#registerButton").click(function() {
 if(sessionCutId!=""){	  
	/*clearTimeout(timerVal);
      timerVal=setTimeout(function(){ 
        jQuery(function(){
           jQuery('#confirmModal_sessionTimeOut').click();
        });
       }  , timeOut);*/
 }
 var listOfAllDiscounts = $("#listOfAllDiscounts").val();
	  var emailRegex = /^([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
	  var phoneRegex = /\(?([0-9]{3})\)?([ .-]?)([0-9]{3})\2([0-9]{4})/;
	  var email = $("#guest-email").val();
	  var name = $("#guest-name").val();
	  var phone = $("#guest-phone").val();
	  var password = $("#password").val();
	  var orderType=$("#orderType").val();
	  var isDeliveryKoupon = $("#deliveryFeeFlag").val();
	  var address1 = $("#step-three-street-address").val();
	  var address2 = $("#step-three-street-address-two").val();
	  var city = $("#step-three-city").val();
	  var state = $("#step-three-select-state").val();
	  var zip = $(".logZipCode").val();
	  if ($("#guest-name").val() == "") {
	    $("#guest-name").focus();
	    $("#errorBox").html("enter the First Name");
	    return false;
	  } else if (/^[a-zA-Z0-9- ]*$/.test(name) == false) {
	    $("#guest-name").focus();
	    $("#errorBox").html("Your name Contains illegal Characters");
	    return false;
	  } else if (name.length < 4) {
	    $("#guest-name").focus();
	    $("#errorBox").html("Name should be a minimum of 4 characters");
	    return false;
	  } else if ($("#guest-email").val() == "") {
	    $("#guest-email").focus();
	    $("#errorBox").html("enter the email");
	    return false;
	  } else if (!emailRegex.test(email)) {
	    $("#guest-email").focus();
	    $("#errorBox").html("Enter a valid email id");
	    return false;
	  } else if ($("#password").val() != "" && password.length < 8) {
		$("#password").focus();
		$("#errorBox").html("Password should be a minimum of 8 characters");
	    return false;
	  } else if ($('#guest-phone').val() == "") {
	    $("#guest-phone").focus();
	    $("#errorBox").html("enter phone number");
	    return false;
	  } else if (phone.length>12 || phone.length<12) {
		$("#guest-phone").focus();
		$("#errorBox").html("please enter valid phone number");
		return false;
	  } else if (/\D/g.test(phone)==false) {
		$("#guest-phone").focus();
		$("#errorBox").html("please enter valid phone number");
		return false;
	  } else if (orderType == "Delivery" && $("#step-three-street-address").val() == "") {
		$("#step-three-street-address").focus();
		$("#errorBox").html("enter the First address");
		return false;
	  } else if (orderType == "Delivery" && $("#step-three-city").val() == "") {
		$("#step-three-city").focus();
		$("#errorBox").html("enter the city");
		return false; 
	  } else if (orderType == "Delivery" && $('#step-three-select-state').val() == "select") {
		$("#step-three-select-state").focus();
		$("#errorBox").html("select state name");
		return false;  
	  } else if (orderType == "Delivery" && $(".logZipCode").val() == "") {
		$("#step-three-zip").focus();
		$("#errorBox").html("enter zip code");
		return false;
	  } else if (orderType == "Delivery" && zip.length != 5) {
		$("#step-three-zip").focus();
		$("#errorBox").html("enter valid zip code");
		return false;
	  } else if ($(name != '' && email != '' && phone != '')) {
		$("#errorBox").html("");
//		if(password!=""){  
	    $(document).ready(function() {
	    	$(".pj-loader-3").css("display","block");
	    	$(".customerInfo").css("display","none");
	    	$(".guestCustomer").css("display","none");
	    	
	    	
	          $.ajax({
	            url : "checkDuplicateEmailId",
	            type : 'POST',
	            dataType : 'json',
	            data : "{\"emailId\":\"" + email + "\"}",
	            contentType : 'application/json',
	            mimeType : 'application/json',
	            success : function(data) {
	              var msg = data.message;
	              if (msg == "true") {
	                $("#errorBox").html("Email already exists. Please login again");
	                $(".pj-loader-3").css("display","none");
	    	    	$(".customerInfo").css("display","block");
	    	    	$(".guestCustomer").css("display","block");
	                return false;
	              } else {
	            	 if(orderType == "Delivery"){
	            	            $.ajax({
	            	              url : "checkDeliveryZone",
	            	              type : 'POST',
	            	              dataType : 'json',
	            	              data : "{\"address1\":\"" + address1 + "\",\"address2\":\"" + address2
	            	                  + "\",\"city\":\"" + city + "\",\"state\":\"" + state + "\",\"zip\":\"" + zip
	            	                  + "\",\"isDeliveryKoupon\":\"" + isDeliveryKoupon+"\"}",
	            	              contentType : 'application/json',
	            	              mimeType : 'application/json',
	            	              success : function(data) {
	            	                if (data.message == "Your zone is in delivery zone") {
	            	                	
	            	                  if(parseFloat(subTotal) >= parseFloat(data.minimumDeliveryAmount)){
	            	                  deliveryItemPosId=data.itemPosId;
	            	                  deliveryItemPrice=data.itemPrice;
	            	                  deliveryTaxStatus=data.deliveryTaxStatus;
	            	                  deliveryTaxPrice=data.deliveryTaxPrice;
	            	                  avgDeliveryTime=data.avgDeliveryTime;
	            	                  
	            	                if(deliveryFeeCheckStatus==0 && deliveryFreeCheckValue==""){ 
	            	                  if(deliveryTaxStatus==1){
	            	                	  var deliWCTax=data.deliveryTaxWithComma;
	            	                	  console.log("--------"+deliWCTax);
	            	                	  deliveryFeeTaxWithCommaArray= deliWCTax.split(",");
	            	                	  for (var i=1;i<deliveryFeeTaxWithCommaArray.length;i++) {
	            	                          if(taxNameAndItemPrice.has(deliveryFeeTaxWithCommaArray[i])){
	            	                                 var itemCurrentPrice= taxNameAndItemPrice.get(deliveryFeeTaxWithCommaArray[i]);
	            	                                 var currentSum=itemCurrentPrice+parseFloat(deliveryItemPrice);
	            	                                 taxNameAndItemPrice.set(deliveryFeeTaxWithCommaArray[i],currentSum);
	            	                             }else{
	            	                                taxNameAndItemPrice.set(deliveryFeeTaxWithCommaArray[i],parseFloat(deliveryItemPrice));
	            	                             }
	            	                          }
	            	                	  
	            	                	  finalTotal=parseFloat(finalTotal)-parseFloat(totalTax);
	            	                	  totalTax=0;
	            	                      var taxSum=0;
	            	                      for (var key of taxNameAndItemPrice.keys()) {
	            	                           var taxVa=merchtTaxMap.get(key);
	            	                           var subTax=0;
	            	                           subTax= ((taxVa / 100)*taxNameAndItemPrice.get(key)).toFixed(2);
	            	                           taxSum=parseFloat(taxSum)+parseFloat(subTax);
	            	                       }
	            	                      totalTax=taxSum;
	            	                      finalTotal=finalTotal+parseFloat(totalTax);
	            	                      deliveryFreeCheckValue=deliWCTax;
	            	                  }
	            	                  finalTotal=finalTotal+parseFloat(deliveryItemPrice);
	            	                  deliveryFeeCheckStatus=1;
                                      deliveryMfee=deliveryItemPrice;
	            	                }else{
                                      if(deliveryFreeCheckValue!=""){
                                    	  var taxMinusPrice=0;
                                    	  txItemNm= deliveryFreeCheckValue;
                                          if(txItemNm.indexOf(",")>-1){
                                              var txKeys=[];
                                              txKeys=txItemNm.split(",");
                                              for(var k=1;k<txKeys.length;k++){
                                                  taxTotalMinusPrice=taxNameAndItemPrice.get(txKeys[k]);
                                                  var rmingnTaxValue=parseFloat(taxTotalMinusPrice)-parseFloat(deliveryMfee);
                                                  taxNameAndItemPrice.set(txKeys[k],rmingnTaxValue);
                                              }
                                          }else{
                                              taxTotalMinusPrice=taxNameAndItemPrice.get(txItemNm);
                                              var rmingnTaxValue=parseFloat(taxTotalMinusPrice)-parseFloat(deliveryMfee);
                                              taxNameAndItemPrice.set(txItemNm,rmingnTaxValue);
                                          }
                                    	}   
                                    	finalTotal=parseFloat(finalTotal)-parseFloat(deliveryMfee);
                                    	  if(deliveryTaxStatus==1){
                                           var deliWCTax=data.deliveryTaxWithComma;
                                           console.log("--------"+deliWCTax);
                                           deliveryFeeTaxWithCommaArray= deliWCTax.split(",");
                                           for (var i=1;i<deliveryFeeTaxWithCommaArray.length;i++) {
                                               if(taxNameAndItemPrice.has(deliveryFeeTaxWithCommaArray[i])){
                                                      var itemCurrentPrice= taxNameAndItemPrice.get(deliveryFeeTaxWithCommaArray[i]);
                                                      var currentSum=itemCurrentPrice+parseFloat(deliveryItemPrice);
                                                      taxNameAndItemPrice.set(deliveryFeeTaxWithCommaArray[i],currentSum);
                                                  }else{
                                                     taxNameAndItemPrice.set(deliveryFeeTaxWithCommaArray[i],parseFloat(deliveryItemPrice));
                                                  }
                                               }
                                           
                                           finalTotal=parseFloat(finalTotal)-parseFloat(totalTax);
                                           totalTax=0;
                                           var taxSum=0;
                                           for (var key of taxNameAndItemPrice.keys()) {
                                                var taxVa=merchtTaxMap.get(key);
                                                var subTax=0;
                                                subTax= ((taxVa / 100)*taxNameAndItemPrice.get(key)).toFixed(2);
                                                taxSum=parseFloat(taxSum)+parseFloat(subTax);
                                            }
                                           totalTax=taxSum;
                                           finalTotal=finalTotal+parseFloat(totalTax);
                                           deliveryFreeCheckValue=deliWCTax;
                                          }
                                       finalTotal=finalTotal+parseFloat(deliveryItemPrice);
                                       deliveryFeeCheckStatus=1;
                                       deliveryMfee=deliveryItemPrice;
                                      }
	            	                
	            	                  // Start- Add delivery fee and tax
	            	                  /*if(deliveryTaxStatus==1){
	            	                         finalTotal=finalTotal+parseFloat(deliveryTaxPrice); 
	            	                         totalTax=totalTax+parseFloat(deliveryTaxPrice); 
	            	                     }*/
	            	                  
	            	                  $("#taxSpanId").html("$"+totalTax.toFixed(2));
	            	                  $("#totalSpanId").html("$"+finalTotal.toFixed(2));
	            	                  $(".yourCartAmount").html("Your Cart &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; $"+finalTotal.toFixed(2))
	            	                  $("#deliveryFeeLiId").css("display","block");
	            	                  $("#deliveryFeeSpanId").html("$"+deliveryItemPrice);
	            	                  delvryFeeStatus=1;
	            	                  var customerUrl="";
	            	    	          if(listOfAllDiscounts!=''){
	            	    	        	  
	            	    	        	  customerUrl="{\"address1\":\"" + address1 + "\",\"address2\":\"" + address2
	          	  		                  + "\",\"city\":\"" + city + "\",\"state\":\"" + state + "\",\"zip\":\"" + zip+ "\",\"emailId\":\""+ email+ "\",\"firstName\":\""+ name+ "\",\"phoneNumber\":\""+ phone+ "\",\"password\":\""+ password + "\",\"id\":\""+ customerId
	          	  		                  + "\",\"listOfALLDiscounts\":" + listOfAllDiscounts+"}";
	            	    	        	  
	            	    	          }else{
	            	    	        	  customerUrl="{\"address1\":\"" + address1 + "\",\"address2\":\"" + address2
	          	  		                  + "\",\"city\":\"" + city + "\",\"state\":\"" + state + "\",\"zip\":\"" + zip+ "\",\"emailId\":\""+ email+ "\",\"firstName\":\""+ name+ "\",\"phoneNumber\":\""+ phone+ "\",\"password\":\""+ password + "\",\"id\":\""+ customerId
	          	  		                  + "\"}";
	            	    	          }
	            	                //End
	            	                  console.log("deliveryId--"+data.itemPosId+"status"+deliveryTaxStatus+"deTaxPrice"+deliveryTaxPrice);
	            	                /*  $.ajax({
	          	  	      	            url : "registerCustomer",
	          	  	      	            type : 'POST',
	          	  	      	            dataType : 'json',
	          	  		      	        data : customerUrl,
	          	  	      	            contentType : 'application/json',
	          	  	      	            mimeType : 'application/json',
	          	  	      	            success : function(data) {
	          	  	      	             console.log(data);
	          	  	      	             customerId=data.customerAfterSuccessId;
	          	  	      	             
	          	  	      	             sessionCutId=data.customerAfterSuccessId;
			          	  	      	       if(sessionCutId!=""){
			         	            		  timerVal=setTimeout(function(){ 
			         	            		         jQuery(function(){
			         	            		           jQuery('#confirmModal_sessionTimeOut').click();
			         	            		       });
			         	            		   }  , timeOut);
			         	            	   }
					          	  	      	     var paymentModd=$(".selMod").val();
			                          	 if(paymentModd=='Cash'){
			                          		 $(".comLi").hide();
			                          	 }
			                          	 if(paymentModd=='Credit Card'){
			                          		 $(".comLi").show();
			                          	 }
	          	  	      	             $(".accordion-right").openSection(2);
	          	  	                	 $(".select-payment-method").css("display", "block");
	          	  	                	$(".pj-loader-3").css("display","none");
	          	  	    	    	$(".customerInfo").css("display","block");
	          	  	    	    	$(".guestCustomer").css("display","block");
	          	  	      	            },
	          	  	      	            error : function(data, status, er) {
	          	  	      	              alert("error: " + data + " status: " + status + " er:" + er);
	          	  	      	            }
	          	  	      	          	});*/
	            	                  var paymentModd=$(".selMod").val();
	         	                	 if(paymentModd=='Cash'){
	         	                		 $(".comLi").hide();
	         	                	 }
	         	                	 if(paymentModd=='Credit Card'){
	         	                		 $(".comLi").show();
	         	                	 }
	         	                	 /*if(lengthOfMerchant > 1){
	         	                   	 
	         	             	    	 $(".accordion-right").openSection(3);
	         	             	    	 }else{*/
	         	             	   $(".accordion-right").openSection(2);
	         	             	    	 /*}*/
	          	  	                	 $(".select-payment-method").css("display", "block");
	          	  	                	$(".pj-loader-3").css("display","none");
	          	  	    	    	$(".customerInfo").css("display","block");
	          	  	    	    	$(".guestCustomer").css("display","block");
		            	              }else{
		            	            	  $("#mToDelAmount").html("<b>Your order amount is less than the minimum delivery amount of "+data.minimumDeliveryAmount+"</b>");
		            	            	  jQuery(function(){
		            	                        jQuery('#minimumDelivAmount').click();
		            	                   })
		            	            	  $("#step-three-street-address").val("");
		            	                  $("#step-three-street-address-two").val("");
		            	                  $("#step-three-city").val("");
		            	                  $('#step-three-select-state').val("select");
		            	                  $("#step-three-zip").val("")
		            	                  $(".pj-loader-3").css("display","none");
	            	    	    	$(".customerInfo").css("display","block");
	            	    	    	$(".guestCustomer").css("display","block");
		            	                  return false;
		            	               } 
	            	                } else {
	            	                  // alert(data.message);
	            	                   jQuery(function(){
	            	                        jQuery('#deliveryFailurePopUp').click();
	            	                   })
	            	                    $("#step-three-street-address").val("");
	            	                    $("#step-three-street-address-two").val("");
	            	                    $("#step-three-city").val("");
	            	                    $('#step-three-select-state').val("select");
	            	                    $("#step-three-zip").val("")
	            	                    $(".pj-loader-3").css("display","none");
	            	    	    	$(".customerInfo").css("display","block");
	            	    	    	$(".guestCustomer").css("display","block");
	            	                  return false;
	            	                }
	            	              },
	            	              error : function(data, status, er) {
	            	            	  var msg = data.message;
	    	  	      	              if (typeof msg === "undefined") {
	    	  	      	            	window.location.href = "sessionTimeOut";
	    	  	      	              }else{
	    	  	      	            	alert("error: " + data + " status: " + status + " er:" + er);
	    	  	      	              }
	            	              }
	            	            });
	            	  }else{/*
	            		  var customerUrl="";
    	    	          if(listOfAllDiscounts!=''){
    	    	        	  
    	    	        	  customerUrl="{\"address1\":\"" + address1 + "\",\"address2\":\"" + address2
	  		                  + "\",\"city\":\"" + city + "\",\"state\":\"" + state + "\",\"zip\":\"" + zip+ "\",\"emailId\":\""+ email+ "\",\"firstName\":\""+ name+ "\",\"phoneNumber\":\""+ phone+ "\",\"password\":\""+ password + "\",\"id\":\""+ customerId
		  		                + "\",\"listOfALLDiscounts\":" + listOfAllDiscounts+"}";
    	    	        	  
    	    	          }else{
    	    	        	  customerUrl="{\"address1\":\"" + address1 + "\",\"address2\":\"" + address2
	  		                  + "\",\"city\":\"" + city + "\",\"state\":\"" + state + "\",\"zip\":\"" + zip+ "\",\"emailId\":\""+ email+ "\",\"firstName\":\""+ name+ "\",\"phoneNumber\":\""+ phone+ "\",\"password\":\""+ password + "\",\"id\":\""+ customerId
		  		                + "\"}";
    	    	          }
	            		  $.ajax({
	  	      	            url : "registerCustomer",
	  	      	            type : 'POST',
	  	      	            dataType : 'json',
	  		      	        data : customerUrl,
	  	      	            contentType : 'application/json',
	  	      	            mimeType : 'application/json',
	  	      	            success : function(data) {
	  	      	             console.log(data);
	  	      	             customerId=data.customerAfterSuccessId;
	  	      	             sessionCutId=data.customerAfterSuccessId;
			  	      	         if(sessionCutId!=""){
				            		  timerVal=setTimeout(function(){ 
				            		         jQuery(function(){
				            		           jQuery('#confirmModal_sessionTimeOut').click();
				            		       });
				            		   }  , timeOut);
				            	}
	  	      	             $(".accordion-right").openSection(2);
	  	                	 $(".select-payment-method").css("display", "block");
	  	                	 var paymentModd=$(".selMod").val();
	  	                	 if(paymentModd=='Cash'){
	  	                		 $(".comLi").hide();
	  	                	 }
	  	                	 if(paymentModd=='Credit Card'){
	  	                		 $(".comLi").show();
	  	                	 }
	  	                	 
	  	                	$(".pj-loader-3").css("display","none");
        	    	    	$(".customerInfo").css("display","block");
        	    	    	$(".guestCustomer").css("display","block");
	  	      	            },
	  	      	            error : function(data, status, er) {
	  	      	              var msg = data.message;
	  	      	              if (typeof msg === "undefined") {
	  	      	            	window.location.href = "sessionTimeOut";
	  	      	              }else{
	  	      	            	alert("error: " + data + " status: " + status + " er:" + er);
	  	      	              }
	  	      	            }
	  	      	          });
	            	  */
	            		  var paymentModd=$(".selMod").val();
		                	 if(paymentModd=='Cash'){
		                		 $(".comLi").hide();
		                	 }
		                	 if(paymentModd=='Credit Card'){
		                		 $(".comLi").show();
		                	 }
		                	/* if(lengthOfMerchant > 1){
		                    	 
		              	    	 $(".accordion-right").openSection(3);
		              	    	 }else{*/
		              	   $(".accordion-right").openSection(2);
		              	    	/* }*/
	  	                	 $(".select-payment-method").css("display", "block");
	  	                	$(".pj-loader-3").css("display","none");
     	    	    	$(".customerInfo").css("display","block");
     	    	    	$(".guestCustomer").css("display","block");  
	            	  }
	              }
	            },
	            error : function(data, status, er) {
	              alert("error: " + data + " status: " + status + " er:" + er);
	            }
	          });
	        });
//		 }else{
//			 if(orderType == "Delivery"){
//				 $.ajax({
//   	              url : "checkDeliveryZone",
//   	              type : 'POST',
//   	              dataType : 'json',
//   	              data : "{\"address1\":\"" + address1 + "\",\"address2\":\"" + address2
//   	                  + "\",\"city\":\"" + city + "\",\"state\":\"" + state + "\",\"zip\":\"" + zip
//   	                  + "\"}",
//   	              contentType : 'application/json',
//   	              mimeType : 'application/json',
//   	              success : function(data) {
//   	                if (data.message == "Your zone is in delivery zone") {
//   	                  alert(data.message);
//   	                  deliveryItemPosId=data.itemPosId;
//   	                  deliveryItemPrice=data.itemPrice;
//		   	          	 $.ajax({
//		   	   	            url : "registerCustomer",
//		   	   	            type : 'POST',
//		   	   	            dataType : 'json',
//		   		      	        data : "{\"address1\":\"" + address1 + "\",\"address2\":\"" + address2
//		   		                  + "\",\"city\":\"" + city + "\",\"state\":\"" + state + "\",\"zip\":\"" + zip+ "\",\"emailId\":\""+ email+ "\",\"firstName\":\""+ name+ "\",\"phoneNumber\":\""+ phone+ "\",\"password\":\""+ password + "\"}",
//		   	   	            contentType : 'application/json',
//		   	   	            mimeType : 'application/json',
//		   	   	            success : function(data) {
//		   	   	             console.log(data);
//		   	   	       var paymentModd=$(".selMod").val();
//	                	 if(paymentModd=='Cash'){
//	                		 $(".comLi").hide();
//	                	 }
//	                	 if(paymentModd=='Credit Card'){
//	                		 $(".comLi").show();
//	                	 }
//		   	   	             $(".accordion-right").openSection(2);
//		   	             	 $(".select-payment-method").css("display", "block");
//		   	   	            },
//		   	   	            error : function(data, status, er) {
//			   	   	          var msg = data.message;
//	  	      	              if (typeof msg === "undefined") {
//	  	      	            	window.location.href = "sessionTimeOut";
//	  	      	              }else{
//	  	      	            	alert("error: " + data + " status: " + status + " er:" + er);
//	  	      	              }
//		   	   	            }
//		   	   	          });
//   	                  
//   	                } else {
//   	                  alert(data.message);
//   	                    $("#step-three-street-address").val("");
//   	                    $("#step-three-street-address-two").val("");
//   	                    $("#step-three-city").val("");
//   	                    $('#step-three-select-state').val("select");
//   	                    $("#step-three-zip").val("")
//   	                  return false;
//   	                }
//   	              },
//   	              error : function(data, status, er) {
//   	                alert("error: " + data + " status: " + status + " er:" + er);
//   	              }
//   	            });
//			 }else{
//				 $.ajax({
//		   	            url : "registerCustomer",
//		   	            type : 'POST',
//		   	            dataType : 'json',
//			      	        data : "{\"address1\":\"" + address1 + "\",\"address2\":\"" + address2
//			                  + "\",\"city\":\"" + city + "\",\"state\":\"" + state + "\",\"zip\":\"" + zip+ "\",\"emailId\":\""+ email+ "\",\"firstName\":\""+ name+ "\",\"phoneNumber\":\""+ phone+ "\",\"password\":\""+ password + "\"}",
//		   	            contentType : 'application/json',
//		   	            mimeType : 'application/json',
//		   	            success : function(data) {
//		   	             console.log(data);
//		   	            var paymentModd=$(".selMod").val();
//	                	 if(paymentModd=='Cash'){
//	                		 $(".comLi").hide();
//	                	 }
//	                	 if(paymentModd=='Credit Card'){
//	                		 $(".comLi").show();
//	                	 }
//		   	             $(".accordion-right").openSection(2);
//		             	 $(".select-payment-method").css("display", "block");
//		   	            },
//		   	            error : function(data, status, er) {
//			   	             var msg = data.message;
//	 	      	              if (typeof msg === "undefined") {
//	 	      	            	window.location.href = "sessionTimeOut";
//	 	      	              }else{
//	 	      	            	alert("error: " + data + " status: " + status + " er:" + er);
//	 	      	              }
//		   	            }
//		   	          });
//			 }
//		 }
	  }
});

$("#loginButton").click(function() {
if(sessionCutId!=""){	 
	/*clearTimeout(timerVal);
      timerVal=setTimeout(function(){ 
        jQuery(function(){
           jQuery('#confirmModal_sessionTimeOut').click();
        });
       }  , timeOut);*/
}
	  var emailRegex = /^([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
	  var email = $("#loginEmail").val();
	  var paasword = $("#loginPassword").val();
	 
	  console.log(merchantId);
	  if ($("#loginEmail").val() == "") {
	    $("#loginEmail").focus();
	    $("#errorBox").html("enter the email");
	    return false;
	  } else if (!emailRegex.test(email)) {
	    $("#loginEmail").focus();
	    $("#errorBox").html("Enter a valid email id");
	    return false;
	  } else if ($('#loginPassword').val() == "") {
	    $("#loginPassword").focus();
	    $("#errorBox").html("enter password");
	    return false;
	  } else if ($(email != '' && paasword != '')) {
		  $(".pj-loader-3").css("display","block");
	    	$(".customerInfo").css("display","none");
	    	$(".returningCustomer").css("display","none");
	    $(document).ready(
	        function() {
	          var emailId = $('#loginEmail').val();
	          var password = $('#loginPassword').val();
	          var orderType = $("#orderType").val();
	          var listOfAllDiscounts = $("#listOfAllDiscounts").val();
	          var customerUrl="";
	          if(listOfAllDiscounts!=''){
	        	  
	        	  customerUrl="{\"emailId\":\"" + emailId + "\",\"vendorId\":\"" + merchantId
	              + "\",\"password\":\"" + password + "\",\"orderType\":\"" + orderType
	              + "\",\"listOfALLDiscounts\":" + listOfAllDiscounts+"}";
	        	  
	          }else{
	        	  customerUrl="{\"emailId\":\"" + emailId + "\",\"vendorId\":\"" + merchantId
	              + "\",\"password\":\"" + password + "\",\"orderType\":\"" + orderType
	              + "\"}";
	          }
	          $.ajax({
	            url : "customerSignIn",
	            type : 'POST',
	            dataType : 'json',
	            data : customerUrl,
	            contentType : 'application/json',
	            mimeType : 'application/json',
	            success : function(data) {
	            	
	            	
	              if (data.success == "Login successfully") {
	            	  var duplicateCoupons= data.duplicateCouponMapList;
	            	  if(duplicateCoupons.length>0){
	            		  $("#discountSpanId").html("$0.00");
	            		  $("#taxSpanId").html("$"+totalTax);
	            		  
	            	  for (var i in duplicateCoupons){
	            		  var duplicateCoupon=duplicateCoupons[i];
	            		  
	            		  
	            		  
	            	  }
	            	  kouponCount=0;
		            	var obj = JSON.parse(listOfAllDiscounts);
		            	var vouchercode="";
		            	for (var x in obj){
		            		  if (obj.hasOwnProperty(x)){
		            			  var coupon=obj[x];
		            			  vouchercode=vouchercode+","+coupon.voucherCode;
		            			  //callCouponAgain(coupon.voucherCode);
		            		  }
		            		}
		            	
		            	 callCouponAgain(vouchercode);
		            	 
		            	 $("#couponError").html("This coupon may23 has been used on 27-05-2017. Please apply another coupon you may have");
		            	
	              }
	            	  customerId=data.custId;
	            	  customerPswd=data.customerPswd;
	            	  sessionCutId=data.custId;
	            	  customeraddress = data.address;
	               if(sessionCutId!=""){
	            		 /* timerVal=setTimeout(function(){ 
	            		         jQuery(function(){
	            		           jQuery('#confirmModal_sessionTimeOut').click();
	            		       });
	            		   }  , timeOut);*/
	            	}
	                if (orderType == "Delivery") {
	                	 $(".pj-loader-3").css("display","none");
	     	  	    	
	                	$(".customerInfo").css("display", "none");
	                	$(".returningCustomer").css("display", "none");
	                	$(".select-address").css("display", "block"); 
	                    addresses = data.address;
	                    $('<option value=select>Select Delivery Address</option>').appendTo('#deliveryAddress'); 
	                    $('<option value=00>Add A New Delivery Address</option>').appendTo('#deliveryAddress'); 
	                      for ( var i = 0, l = addresses.length; i < l; i++ ) {
	                         $('<option value="' + addresses[i].id + '" deliveItPosIdAttr="'+addresses[i].deliveryPosId+'" deliveItFeeAttr="'+addresses[i].deliveryFee+'">' + addresses[i].address1+ " " + addresses[i].address2 + '</option>').appendTo('#deliveryAddress');
	                      
	                  }
	                } else {
	                	   $("#custH1").hide();
	                        $(".select-address").css("display", "none");
	                        $(".add-your-address").css("display", "none");
	                        $(".checkout-button").css("display", "none");
	                	var paymentModd=$(".selMod").val();
	                	 if(paymentModd=='Cash'){
	                		 $(".comLi").hide();
	                	 }
	                	 if(paymentModd=='Credit Card'){
	                		 $(".comLi").show();
	                	 }
	                	 /*if(lengthOfMerchant > 1){
	                    	 
	              	    	 $(".accordion-right").openSection(3);
	              	    	 }else{*/
	              	   $(".accordion-right").openSection(2);
	              	    	 /*}*/
	                	$(".select-payment-method").css("display", "block");
	                }
	                $(".loggedINCust").css("display", "block");
	                $(".logINCust").css("display", "none");
	              } else {
	            	  $(".pj-loader-3").css("display","none");
	      	    	$(".customerInfo").css("display","block");
	      	    	$(".returningCustomer").css("display","block");
	                $("#errorBox").html("Invalid login credential");
	              }
	              
	             
	            },
	            error : function(data, status, er) {
	            	 var msg = data.success;
	      	              if (typeof msg === "undefined") {
	      	            	window.location.href = "sessionTimeOut";
	      	              }else{
	      	            	alert("error: " + data + " status: " + status + " er:" + er);
	      	       }
	            }
	          });
	        });
	  }
});

// changed by praveen 
$('#guest-phone11', '#myform').keydown(function (e) {
	  var key = e.charCode || e.keyCode || 0;
	  $phone = $(this);

	  // Auto-format- do not expose the mask as the user begins to type
	  if (key !== 8 && key !== 9) {
	    if ($phone.val().length === 3) {
	      $phone.val($phone.val() + '-');
	    }
	    if ($phone.val().length === 7) {
	      $phone.val($phone.val() + '-');
	    }
	  }

	  // Allow numeric (and tab, backspace, delete) keys only
	  return (key == 8 || 
	      key == 9 ||
	      key == 46 ||
	      (key >= 48 && key <= 57) ||
	      (key >= 96 && key <= 105)); 
	})

	.bind('focus click', function () {
	  $phone = $(this);
	  
	  if ($phone.val().length === 0) {
	    $phone.val('');
	  }
	  else {
	    var val = $phone.val();
	    $phone.val('').val(val); // Ensure cursor remains at the end
	  }
	})

	.blur(function () {
	  $phone = $(this);
	  
	  if ($phone.val() === '-') {
	    $phone.val('');
	  }
	});



/*$('#singInEmail').blur(function (e) {
	
	
});*/



$("#singInButton").click(function() {
	
	
	var custEmailID=$('#singInEmail').val();
	if ($("#singInEmail").val() == "") {
	    $("#singInEmail").focus();
	    $("#loginError").html("Enter the email id");
	    return false;}else if ($("#signInPassword").val() == "") {
		    $("#signInPassword").focus();
		    $("#loginError").html("Enter the password");
		    return false;}else{
	    	
	    	   $(".pj-loader-4").css("display","block");
		    	$(".accordion-popup-quantity").css("display","none");
		    	$(".shop-checkout-popup-button").css("display","none");
		    	 
	
	 $.ajax({
        url : "checkCustomerType?emailId="+custEmailID+"&merchantId="+merchantId,
        type : "GET",
        contentType : "application/json; charset=utf-8",
        success : function(customerResponse) {
          
          if(customerResponse=='php'){
       	    window.location.href = "resetPhpCustomerPassword?email="+custEmailID+"&merchantId="+merchantId;
            }else if(customerResponse=='java'){
            	if(sessionCutId!=""){	 
            		/*clearTimeout(timerVal);
            	      timerVal=setTimeout(function(){ 
            	        jQuery(function(){
            	           jQuery('#confirmModal_sessionTimeOut').click();
            	        });
            	       }  , timeOut);*/
            	}
            		  var emailRegex = /^([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
            		  var email = $("#singInEmail").val();
            		  var paasword = $("#signInPassword").val();
            		 
            		  
            		  console.log(merchantId);
            		  if ($("#singInEmail").val() == "") {
            		    $("#singInEmail").focus();
            		    $("#loginError").html("enter the email");
            		    return false;
            		  } else if (!emailRegex.test(email)) {
            		    $("#singInEmail").focus();
            		    $("#loginError").html("Enter a valid email id");
            		    return false;
            		  } else if ($('#signInPassword').val() == "") {
            		    $("#signInPassword").focus();
            		    $("#loginError").html("enter password");
            		    return false;
            		  } else if ($(email != '' && paasword != '')) {
            		    $(document).ready(
            		        function() {
            		          var emailId = $('#singInEmail').val();
            		          var password = $('#signInPassword').val();
            		          var orderType = $("#orderType").val();
            		          $.ajax({
            		            url : "customerSignIn",
            		            type : 'POST',
            		            dataType : 'json',
            		            data : "{\"emailId\":\"" + emailId + "\",\"vendorId\":\"" + merchantId
            		                + "\",\"password\":\"" + password + "\",\"orderType\":\"" + orderType + "\"}",
            		            contentType : 'application/json',
            		            mimeType : 'application/json',
            		            success : function(data) {
            		            	
            		              if (data.success == "Login successfully") {
            		            	  customerId=data.custId;
            		            	  customerPswd=data.customerPswd;
            		            	  sessionCutId=data.custId;
            		            	  customeraddress = data.address;
            		            	  $("#custH1").hide();
      		                        $(".select-address").css("display", "none");
      		                        $(".add-your-address").css("display", "none");
      		                        $(".checkout-button").css("display", "none");
            		            	  
            		               if(sessionCutId!=""){
            		            		  /*timerVal=setTimeout(function(){ 
            		            		         jQuery(function(){
            		            		           jQuery('#confirmModal_sessionTimeOut').click();
            		            		       });
            		            		   }  , timeOut);*/
            		            	}
            		                if (orderType == "Delivery") {
            		                	$(".customerInfo").css("display", "none");
            		                	$(".returningCustomer").css("display", "none");
            		                	$(".select-address").css("display", "block"); 
            		                    addresses = data.address;
            		                    $('<option value=select>Select Delivery Address</option>').appendTo('#deliveryAddress'); 
            		                    $('<option value=00>Add A New Delivery Address</option>').appendTo('#deliveryAddress'); 
            		                      for ( var i = 0, l = addresses.length; i < l; i++ ) {
            		                         $('<option value="' + addresses[i].id + '" deliveItPosIdAttr="'+addresses[i].deliveryPosId+'" deliveItFeeAttr="'+addresses[i].deliveryFee+'">' + addresses[i].address1+ " " + addresses[i].address2 + '</option>').appendTo('#deliveryAddress');
            		                      console.log("======"+addresses[i].id+addresses[i].address1)
            		                  }
            		                } else {
            		                	if(orderType=="Pickup"){
            		                		
            		                		 /*if(lengthOfMerchant > 1){
            		                        	 
            		                  	    	 $(".accordion-right").openSection(3);
            		                  	    	 }else{*/
            		                  	   $(".accordion-right").openSection(2);
            		                  	    	 /*}*/
            		                        $(".select-payment-method").css("display", "block");
            		                	}else{
            		                		$(".firstDiv").css("display", "block");
            		                	}
            		                	var paymentModd=$(".selMod").val();
            		                	 if(paymentModd=='Cash'){
            		                		 $(".comLi").hide();
            		                	 }
            		                	 if(paymentModd=='Credit Card'){
            		                		 $(".comLi").show();
            		                	 }
            		                	//$(".accordion-right").openSection(2);
            		                	//$(".select-payment-method").css("display", "block");
            		                }
            		                $(".loggedINCust").css("display", "block");
            		                $(".logINCust").css("display", "none");
            		                
            		                document.getElementById("signInNav").style.height = "0%";
            		              } else {
            		            	  $(".pj-loader-4").css("display","none");
                  		  	    	$(".accordion-popup-quantity").css("display","block");
                  		  	    	$(".shop-checkout-popup-button").css("display","block");
            		                $("#loginError").html("Invalid login credential");
            		              }
            		              
            		            
            		            },
            		            error : function(data, status, er) {
            		            	 var msg = data.success;
            		      	              if (typeof msg === "undefined") {
            		      	            	window.location.href = "sessionTimeOut";
            		      	              }else{
            		      	            	alert("error: " + data + " status: " + status + " er:" + er);
            		      	       }
            		            }
            		          });
            		        });
            		  }
            	
            	
            }else {
            	$(".pj-loader-4").css("display","none");
	  	    	$(".accordion-popup-quantity").css("display","block");
	  	    	$(".shop-checkout-popup-button").css("display","block");
           	 $("#singInEmail").focus();
        	    $("#loginError").html("Not Registered Email");
            }
          
           
           
        },
        error : function(data, status, er) {
          alert("error: " + data + " status: " + status + " er:" + er);
        }
      }); }
	
	
	});


$('#loginEmail').blur(function (e) {
	var custEmailID=$('#loginEmail').val();
	
	
	 $.ajax({
         url : "checkCustomerType?emailId="+custEmailID+"&merchantId="+merchantId,
         type : "GET",
         contentType : "application/json; charset=utf-8",
         success : function(customerResponse) {
           
           if(customerResponse=='php'){
        	    window.location.href = "resetPhpCustomerPassword?email="+custEmailID+"&merchantId="+merchantId;
             }else if(customerResponse=='java'){
             }else {
            	 $("#guest-email").focus();
         	    $("#errorBox").html("Not Registered Email");
             }
            
         },
         error : function(data, status, er) {
           alert("error: " + data + " status: " + status + " er:" + er);
         }
       });
	
});


//changed id  by praveen
$('#expirationDate12', '#msform').keydown(function (e) {
	  var key = e.charCode || e.keyCode || 0;
	  $phone = $(this);
	  // Auto-format- do not expose the mask as the user begins to type
	  if (key !== 8 && key !== 9) {
	    if ($phone.val().length === 2) {
	      $phone.val($phone.val() + '/');
	    }
	  }

	  // Allow numeric (and tab, backspace, delete) keys only
	  return (key == 8 || 
	      key == 9 ||
	      key == 46 ||
	      (key >= 48 && key <= 57) ||
	      (key >= 96 && key <= 105)); 
	}).bind('focus click', function () {
	  $phone = $(this);
	  
	  if ($phone.val().length === 0) {
	    $phone.val('');
	  }
	  else {
	    var val = $phone.val();
	    $phone.val('').val(val); // Ensure cursor remains at the end
	  }
	}).blur(function () {
	  $phone = $(this);
	  
	  if ($phone.val() === '-') {
	    $phone.val('');
	  }
	});



$("#checkDeliveryButton").click(function() {
	$(".pj-loader-3").css("display","block");
	$(".add-your-address").css("display","none");
	$(".select-address").css("display","none");
	$(".checkout-button").css("display","none");
	
	
if(sessionCutId!=""){	  
	/*clearTimeout(timerVal);
      timerVal=setTimeout(function(){ 
        jQuery(function(){
           jQuery('#confirmModal_sessionTimeOut').click();
        });
       }  , timeOut);*/
}
		
	  var address1 = $("#deliveryAddresss1").val();
	  var address2 = $("#deliveryAddresss2").val();
	  var city = $("#deliveryCity").val();
	  var state = $("#deliveryState").val();
	  var zip = $(".beLZipCode").val();
	  if ($("#deliveryAddresss1").val() == "") {
	    $("#deliveryAddresss1").focus();
	    $("#deliveryError").html("enter the First address");
	    return false;
	  } else if ($("#deliveryCity").val() == "") {
	    $("#deliveryCity").focus();
	    $("#deliveryError").html("enter the city");
	    return false;
	  } else if ($('#deliveryState').val() == "select") {
	    $("#deliveryState").focus();
	    $("#deliveryError").html("select state name");
	    return false;
	  } else if (zip == "") {
	    $("#deliveryZipCode").focus();
	    $("#deliveryError").html("enter zip code");
	    return false;
	  } else if (zip.length!=5) {
		$("#deliveryZipCode").focus();
		$("#deliveryError").html("enter valid zip code");
		return false;
	  } else if ($(address1 != '' && city != '' && state != 'select' && zip != '')) {
	    $(document).ready(function() {
	            $.ajax({
	              url : "checkDeliveryZone",
	              type : 'POST',
	              dataType : 'json',
	              data : "{\"address1\":\"" + address1 + "\",\"address2\":\"" + address2
	                  + "\",\"city\":\"" + city + "\",\"state\":\"" + state + "\",\"zip\":\"" + zip
	                  + "\"}",
	              contentType : 'application/json',
	              mimeType : 'application/json',
	              success : function(data) {
	                if (data.message == "Your zone is in delivery zone") {
	                  if(parseFloat(subTotal) >= parseFloat(data.minimumDeliveryAmount)){
	                	  
	                  deliveryItemPosId=data.itemPosId;
	                  deliveryItemPrice=data.itemPrice;
	                  deliveryTaxStatus=data.deliveryTaxStatus;
	                  deliveryTaxPrice=data.deliveryTaxPrice;
	                  avgDeliveryTime=data.avgDeliveryTime;
	                  
	                  
	                  // Start- Add delivery fee and tax
	                  if(deliveryFeeCheckStatus==0 && deliveryFreeCheckValue==""){
	                  if(deliveryTaxStatus==1){
	                	  var deliWCTax=data.deliveryTaxWithComma;
	                	  console.log("--------"+deliWCTax);
	                	  deliveryFeeTaxWithCommaArray= deliWCTax.split(",");
	                	  for (var i=1;i<deliveryFeeTaxWithCommaArray.length;i++) {
	                          if(taxNameAndItemPrice.has(deliveryFeeTaxWithCommaArray[i])){
	                                 var itemCurrentPrice= taxNameAndItemPrice.get(deliveryFeeTaxWithCommaArray[i]);
	                                 var currentSum=itemCurrentPrice+parseFloat(deliveryItemPrice);
	                                 taxNameAndItemPrice.set(deliveryFeeTaxWithCommaArray[i],currentSum);
	                             }else{
	                                taxNameAndItemPrice.set(deliveryFeeTaxWithCommaArray[i],parseFloat(deliveryItemPrice));
	                             }
	                          }
	                	  
	                	  finalTotal=parseFloat(finalTotal)-parseFloat(totalTax);
	                	  totalTax=0;
	                      var taxSum=0;
	                      for (var key of taxNameAndItemPrice.keys()) {
	                           var taxVa=merchtTaxMap.get(key);
	                           var subTax=0;
	                           subTax= ((taxVa / 100)*taxNameAndItemPrice.get(key)).toFixed(2);
	                           taxSum=parseFloat(taxSum)+parseFloat(subTax);
	                       }
	                      totalTax=taxSum;
	                      finalTotal=finalTotal+parseFloat(totalTax);
	                      deliveryFreeCheckValue=deliWCTax;
	                  }
	                  
	                  finalTotal=finalTotal+parseFloat(deliveryItemPrice);
	                  deliveryFeeCheckStatus=1;
                      deliveryMfee=deliveryItemPrice;
	                  }else{
	                	  if(deliveryFreeCheckValue!=""){
                        	  var taxMinusPrice=0;
                        	  txItemNm= deliveryFreeCheckValue;
                              if(txItemNm.indexOf(",")>-1){
                                  var txKeys=[];
                                  txKeys=txItemNm.split(",");
                                  for(var k=1;k<txKeys.length;k++){
                                      taxTotalMinusPrice=taxNameAndItemPrice.get(txKeys[k]);
                                      var rmingnTaxValue=parseFloat(taxTotalMinusPrice)-parseFloat(deliveryMfee);
                                      taxNameAndItemPrice.set(txKeys[k],rmingnTaxValue);
                                  }
                              }else{
                                  taxTotalMinusPrice=taxNameAndItemPrice.get(txItemNm);
                                  var rmingnTaxValue=parseFloat(taxTotalMinusPrice)-parseFloat(deliveryMfee);
                                  taxNameAndItemPrice.set(txItemNm,rmingnTaxValue);
                              }
                        	}   
                        	finalTotal=parseFloat(finalTotal)-parseFloat(deliveryMfee);
                        	  if(deliveryTaxStatus==1){
                               var deliWCTax=data.deliveryTaxWithComma;
                               console.log("--------"+deliWCTax);
                               deliveryFeeTaxWithCommaArray= deliWCTax.split(",");
                               for (var i=1;i<deliveryFeeTaxWithCommaArray.length;i++) {
                                   if(taxNameAndItemPrice.has(deliveryFeeTaxWithCommaArray[i])){
                                          var itemCurrentPrice= taxNameAndItemPrice.get(deliveryFeeTaxWithCommaArray[i]);
                                          var currentSum=itemCurrentPrice+parseFloat(deliveryItemPrice);
                                          taxNameAndItemPrice.set(deliveryFeeTaxWithCommaArray[i],currentSum);
                                      }else{
                                         taxNameAndItemPrice.set(deliveryFeeTaxWithCommaArray[i],parseFloat(deliveryItemPrice));
                                      }
                                   }
                               
                               finalTotal=parseFloat(finalTotal)-parseFloat(totalTax);
                               totalTax=0;
                               var taxSum=0;
                               for (var key of taxNameAndItemPrice.keys()) {
                                    var taxVa=merchtTaxMap.get(key);
                                    var subTax=0;
                                    subTax= ((taxVa / 100)*taxNameAndItemPrice.get(key)).toFixed(2);
                                    taxSum=parseFloat(taxSum)+parseFloat(subTax);
                                }
                               totalTax=taxSum;
                               finalTotal=finalTotal+parseFloat(totalTax);
                               deliveryFreeCheckValue=deliWCTax;
                              }
                           finalTotal=finalTotal+parseFloat(deliveryItemPrice);
                           deliveryFeeCheckStatus=1;
                           deliveryMfee=deliveryItemPrice;
	                  }
	                  $("#taxSpanId").html("$"+totalTax.toFixed(2));
	                  $("#totalSpanId").html("$"+finalTotal.toFixed(2));
	                  $(".yourCartAmount").html("Your Cart &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; $"+finalTotal.toFixed(2))
	                  $("#deliveryFeeLiId").css("display","block");
	            	  $("#deliveryFeeSpanId").html("$"+deliveryItemPrice);
	            	  delvryFeeStatus=1;
	                  //End
	                  
	                  console.log("deliveryId--"+data.itemPosId+"status"+deliveryTaxStatus+"deTaxPrice"+deliveryTaxPrice);
	                  var paymentModd=$(".selMod").val();
	                	 if(paymentModd=='Cash'){
	                		 $(".comLi").hide();
	                	 }
	                	 if(paymentModd=='Credit Card'){
	                		 $(".comLi").show();
	                	 }
	                  $(".select-payment-method").css("display", "block");
	                  /*if(lengthOfMerchant > 1){
	                 	 
	           	    	 $(".accordion-right").openSection(3);
	           	    	 }else{*/
	           	   $(".accordion-right").openSection(2);
	           	    	 /*}*/
	                  }else{
	                	  $("#mToDelAmount").html("<b>Your order amount is less than the minimum delivery amount of "+data.minimumDeliveryAmount+"</b>");
	                	    jQuery(function(){
	                			jQuery('#minimumDelivAmount').click();
	                		})
	                		$("#step-three-street-address").val("");
	                		$("#step-three-street-address-two").val("");
	                	    $("#step-three-city").val("");
	                		$('#step-three-select-state').val("select");
	                		$("#step-three-zip").val("")
	                		return false;
	                	}
	                  
	                } else {
	                	$(".pj-loader-3").css("display","none");
		              	$(".add-your-address").css("display","block");
		              	$(".select-address").css("display","block");
		              	$(".checkout-button").css("display","block");
	                	  jQuery(function(){
  	                        jQuery('#deliveryFailurePopUp').click();
  	                   })
	                    $("#deliveryAddresss1").val("");
	                    $("#deliveryAddresss2").val("");
	                    $("#deliveryCity").val("");
	                    $('#deliveryState').val("select");
	                    $("#deliveryZipCode").val("")
	                  return false;
	                }
	                
	                $(".pj-loader-3").css("display","none");
	              	$(".add-your-address").css("display","block");
	              	$(".select-address").css("display","block");
	              	$(".checkout-button").css("display","block");
	              },
	              error : function(data, status, er) {
	                alert("error: " + data + " status: " + status + " er:" + er);
	              }
	            });
	          });
	  }
});
});


function registerCustomer(orderPlaceUrl,instruction,couponDiscount,discountType,itemPoSidsJson,inventoryLevel,voucherCode,ordType,convenienceFeePrice,deliveryItemPrice,avgDeliveryTime,itemsForDiscount,listOfALLDiscounts) {
	 var listOfAllDiscounts = $("#listOfAllDiscounts").val();
	  var emailRegex = /^([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
	  var phoneRegex = /\(?([0-9]{3})\)?([ .-]?)([0-9]{3})\2([0-9]{4})/;
	  var email = $("#guest-email").val();
	  var name = $("#guest-name").val();
	  var phone = $("#guest-phone").val();
	  var password = $("#password").val();
	  var orderType=$("#orderType").val();
	  var isDeliveryKoupon = $("#deliveryFeeFlag").val();
	  var address1 = $("#step-three-street-address").val();
	  var address2 = $("#step-three-street-address-two").val();
	  var city = $("#step-three-city").val();
	  var state = $("#step-three-select-state").val();
	  var zip = $(".logZipCode").val();
	  var instruction = document.getElementById("special-instructions").value;
var customerUrl="";
if(listOfAllDiscounts!=''){
	  
	  customerUrl="{\"address1\":\"" + address1 + "\",\"address2\":\"" + address2
      + "\",\"city\":\"" + city + "\",\"state\":\"" + state + "\",\"zip\":\"" + zip+ "\",\"emailId\":\""+ email+ "\",\"firstName\":\""+ name+ "\",\"phoneNumber\":\""+ phone+ "\",\"password\":\""+ password + "\",\"id\":\""+ customerId
        + "\",\"listOfALLDiscounts\":" + listOfAllDiscounts+"}";
	  
}else{
	  customerUrl="{\"address1\":\"" + address1 + "\",\"address2\":\"" + address2
      + "\",\"city\":\"" + city + "\",\"state\":\"" + state + "\",\"zip\":\"" + zip+ "\",\"emailId\":\""+ email+ "\",\"firstName\":\""+ name+ "\",\"phoneNumber\":\""+ phone+ "\",\"password\":\""+ password + "\",\"id\":\""+ customerId
        + "\"}";
}



$.ajax({
      url : "registerCustomer",
      type : 'POST',
      dataType : 'json',
      data : customerUrl,
      contentType : 'application/json',
      mimeType : 'application/json',
      success : function(data) {
       console.log(data);
       customerId=data.customerAfterSuccessId;
       sessionCutId=data.customerAfterSuccessId;
	         if(sessionCutId!=""){
  		  /*timerVal=setTimeout(function(){ 
  		         jQuery(function(){
  		           jQuery('#confirmModal_sessionTimeOut').click();
  		       });
  		   }  , timeOut);*/
  	}
       //$(".accordion-right").openSection(2);
	         /*if(lengthOfMerchant > 1){
            	 
      	    	 $(".accordion-right").openSection(3);
      	    	 }else{*/
      	   $(".accordion-right").openSection(2);
      	    	 /*}*/
	 $(".select-payment-method").css("display", "block");
	 var paymentModd=$(".selMod").val();
	 if(paymentModd=='Cash'){
		 $(".comLi").hide();
	 }
	 if(paymentModd=='Credit Card'){
		 $(".comLi").show();
	 }
	 
	$(".pj-loader-3").css("display","none");
	$(".customerInfo").css("display","block");
	$(".guestCustomer").css("display","block");
	
    
	//placeOrder(orderPlaceUrl,instruction,couponDiscount,discountType,itemPoSidsJson,inventoryLevel,voucherCode,ordType,convenienceFeePrice,deliveryItemPrice,avgDeliveryTime,itemsForDiscount,listOfALLDiscounts);
	
	placeOrderOnAWS(orderPlaceUrl,instruction,couponDiscount,discountType,itemPoSidsJson,inventoryLevel,voucherCode,ordType,convenienceFeePrice,deliveryItemPrice,avgDeliveryTime,itemsForDiscount,listOfALLDiscounts);
	
      },
      error : function(data, status, er) {
        var msg = data.message;
        if (typeof msg === "undefined") {
      	window.location.href = "sessionTimeOut";
        }else{
      	alert("error: " + data + " status: " + status + " er:" + er);
        }
      }
    });
}

function placeOrder(orderPlaceUrl,instruction){
	
	
	
	$.ajax({
        url : orderPlaceUrl,
        type : "GET",
        contentType : "application/json; charset=utf-8",
        success : function(statusValue) {
         if (statusValue != null) {
         if(sessionCutId!=""){  
             /*clearTimeout(timerVal);
               timerVal=setTimeout(function(){ 
                 jQuery(function(){
                    jQuery('#confirmModal_sessionTimeOut').click();
                 });
                }  , timeOut);*/
        		 }   
            ordId = statusValue;
            console.log("----ordId---"+ordId);
            $("#orderPosId").val(statusValue);
            $("#ordPosId").val(statusValue);
            $("#listOfDiscounts").val(listOfALLDiscounts);
            $("#instructionId").val(instruction);
            $("#ordrTotalAmount").val(finalTotal.toFixed(2));
            $("#ordrTipAmount").val(Number(tipAmt).toFixed(2));
            /* if(finalTotalWithTip>0){
            $("#ordrTotalAmount").val(finalTotalWithTip.toFixed(2));
            }else{
              $("#ordrTotalAmount").val(finalTotal.toFixed(2));
            } */
            
            $("#orderSubTotal").val(subTotal);
            //$("#orderTax").val(totalTax.toFixed(2));
            $("#orderTax").val(totalTax);
            callPayment(); 
          }else{
         	 jQuery(function(){
                  jQuery('#orderError').click();
               }); 
           }
        },
        error : function() {
          alert("error");
          $(".pj-loader-2").css("display","none");
          $(".mnuLoading").css("display","block");
        }
      })
	
	
}


function placeOrderOnAWS(orderPlaceUrl,instruction,couponDiscount,discountType,itemPoSidsJson,inventoryLevel,voucherCode,ordType,convenienceFeePrice,deliveryItemPrice,avgDeliveryTime,itemsForDiscount,listOfALLDiscounts){
	
	var orderJson = (JSON.stringify(demoArray));
	var orderTotal=0;
    orderTotal = finalTotal.toFixed(2);
	var data1='{"orderJson" : '+orderJson+',"orderTotal" : "'+orderTotal+'","instruction" : "'+instruction+'", "discount" : "'+couponDiscount+'", "discountType" : "'+discountType+'","itemPosIds" : "'+itemPoSidsJson+'", "inventoryLevel" : "'+inventoryLevel+'", "voucherCode" : "'+voucherCode+'","orderType" : "'+ordType+'","convenienceFee" : "'+convenienceFeePrice+'", "deliveryItemPrice" : "'+deliveryItemPrice+'", "avgDeliveryTime" : "'+avgDeliveryTime+'","itemsForDiscount" : "'+itemsForDiscount+'","listOfALLDiscounts" : "'+listOfALLDiscounts+'"} ';
	
	$.ajax({
        url : "placeOrderOnAWS",
        type : 'POST',
        contentType : "application/json; charset=utf-8",
        data : data1,
       
        success : function(statusValue) {
        	
         if (statusValue != null) {
         /*if(sessionCutId!=""){  
             clearTimeout(timerVal);
               timerVal=setTimeout(function(){ 
                 jQuery(function(){
                    jQuery('#confirmModal_sessionTimeOut').click();
                 });
                }  , timeOut);
        		 }*/   
            ordId = statusValue;
            console.log("----ordId---"+ordId);
            $("#orderPosId").val(statusValue);
            $("#ordPosId").val(statusValue);
            $("#listOfDiscounts").val(listOfALLDiscounts);
            $("#instructionId").val(instruction);
            $("#ordrTotalAmount").val(finalTotal.toFixed(2));
            $("#ordrTipAmount").val(Number(tipAmt).toFixed(2));
            /* if(finalTotalWithTip>0){
            $("#ordrTotalAmount").val(finalTotalWithTip.toFixed(2));
            }else{
              $("#ordrTotalAmount").val(finalTotal.toFixed(2));
            } */
            
            $("#orderSubTotal").val(subTotal);
            //$("#orderTax").val(totalTax.toFixed(2));
            $("#orderTax").val(totalTax);
            callPayment(); 
          }else{
         	 jQuery(function(){
                  jQuery('#orderError').click();
               }); 
           }
        },
        error : function() {
          alert("orderFailed");
          
          $(".pj-loader-2").css("display","none");
          $(".mnuLoading").css("display","block");
        }
      })
}
