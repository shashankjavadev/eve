<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en" class="no-js">
	<head>
	<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
		<meta charset="UTF-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge"> 
		<meta name="viewport" content="width=device-width, initial-scale=1"> 
		<title>Foodkonnekt Feedback Form</title>
		<meta name="description" content="Fullscreen Form Interface: A distraction-free form concept with fancy animations" />
		<meta name="keywords" content="fullscreen form, css animations, distraction-free, web design" />
		<meta name="author" content="Codrops" />
		<link rel="stylesheet" type="text/css" href="resources/kritiq/css/normalize.css" />
		<link rel="stylesheet" type="text/css" href="resources/kritiq/css/demo.css" />
		<link rel="stylesheet" type="text/css" href="resources/kritiq/css/component.css" />
		<link rel="stylesheet" type="text/css" href="resources/kritiq/css/cs-select.css" />
		<link rel="stylesheet" type="text/css" href="resources/kritiq/css/cs-skin-boxes.css" />
		<link rel="stylesheet" type="text/css" href="resources/kritiq/css/style2.css" />
		<script src="resources/kritiq/js/modernizr.custom.js"></script>
	</head>
	<body class="design4 design3 demo">
	
				<div class="fs-title text-center">
					<img src="/${merchantLogo}" onerror="this.src='resources/img/logo.png'" width="250" height="150" />
				</div>
				<c:choose>
			     <c:when test="${merchantKritiqExpiryStatus=='enabled'}">
				<form:form action="saveWalkInCustomerFeedback" id="myform" modelAttribute="CustomerFeedback" method="POST"  autocomplete="off">
				<div class="boxed-block text-center fs-fields orderBox">
					
					<input type="hidden" id="merchantId" name="merchantId" value="${merchantId}">
					<input type="hidden" id="merchantPosId" name="merchantPosId" value="${posMerchantId}">
					<input type="hidden" id="posId" name="posId" value="${posId}">
					<label class="fs-field-label fs-anim-upper" for="orderid">Enter your Order ID</label><br/><br/>
					<label class="fs-anim-lower" id="errorBox1" style="color: red;"></label>
					
					<form:input class="fs-anim-lower" type="text" path= "orderR.orderPosId" id="order-register" name="orderid" placeholder="Order Id" maxlength="20"  onkeyup="this.value = this.value.replace(/[$&+,:;-=?@#|'<>._^*()%!]/g, '')"></form:input>
					<!-- <form:input path= "orderR.orderPosId"  id="order-register" onblur="checkOrder()" ></form:input> -->
					<!-- <a href="feedbackFormWalkInCustomer.jsp" class="fs-continue fs-show">Continue</a> -->
					<a href="javascript:checkOrder();" class="fs-continue fs-show">Continue</a>
					<!-- <a href="javascript:checkOrder();">cont</a> -->
					<!-- <a href="#" onclick="myFunction()">LinkText</a> -->
					
				</div>
				</form:form>
				                                                                            </c:when>
				
				<c:when test="${merchantKritiqExpiryStatus=='disabled'}"> 
				<div class="boxed-block text-center fs-fields orderBox">
				<label class="fs-field-label fs-anim-upper" for="orderid">Please ask your restaurant to enable the feedback form !</label>
				</div>
				</c:when>
				<c:when test="${merchantKritiqExpiryStatus=='expired'}"> 
				<div class="boxed-block text-center fs-fields orderBox">
				<label class="fs-field-label fs-anim-upper" for="orderid">Please ask your restaurant to upgrade the feedback form !</label>
				</div>
				</c:when>
				</c:choose>
		<script src="resources/kritiq/js/jquery-2.1.1.min.js"></script>
		
		<script type="text/javascript">
		function checkOrder(){
			 var posMerchantId = $("#merchantPosId").val();
			var merchantId = $("#merchantId").val();
			var posId = $("#posId").val();
			var posOrderId = $("#order-register").val();
			if ($("#order-register").val() == "") {
			    $("#order-register").focus();
			    $("#errorBox1").html("Enter a valid order id");
			    return false;
			  }
			
			if(posId == 3){
				window.location='mfeedbackForm6?merchantId='+merchantId+'&orderId='+posOrderId;
			}else{
				// alert(posMerchantId+" "+posOrderId);
			       $.ajax({
			        url : "verifyOrderFromClover?merchantId=" + posMerchantId + "&orderId="+posOrderId,
			        type : 'GET',
			        dataType : 'json',
			        contentType : 'application/json',
			        mimeType : 'application/json',
			        success : function(data) {
			          var msg = data;
			          if (msg == true) {
			        	
			        	  window.location='mfeedbackForm6?merchantId='+merchantId+'&orderId='+posOrderId;       	  
			        	
			          } else {
			        	  
			        	  
			        	  $("#errorBox1").html("Enter a valid order id");
			        	  $('#submitButton').prop('disabled', true);
			           return false;
			            //document.myform.action = "customerSignUp", document.myform.submit()
			          }
			        },
			        /* error : function(data, status, er) {
			          alert("error: " + data + " status: " + status + " er:" + er);
			        } */
			      });
			}
		}
		
		</script>
	
	</body>
</html>
