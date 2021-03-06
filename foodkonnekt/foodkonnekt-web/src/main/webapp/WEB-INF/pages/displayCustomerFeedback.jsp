<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="resources/kritiq/css/style.css">
<link rel="stylesheet" href="resources/kritiq/css/font-awesome.css">
 <link rel="stylesheet" type="text/css" href="resources/kritiq/css/dialog-box/component.css" />
 <link type="text/css" rel="stylesheet" href="resources/kritiq/css/popModal.css">
<script src="resources/js/shop/accordion/jquery-1.11.3.min.js"></script>
<script src="resources/js/shop/accordion/woco.accordion.min.js"></script>
<script src="resources/js/popModal.js"></script>
<title>Insert title here</title>
<style>
* {
    font-family: 'Open Sans', sans-serif;
}

.btn {
    display: inline-block;
    cursor: pointer;
    background: #fff;
    border: 1px solid #bbb;
    height: 42px;
    padding: 11px;
    font-size: 14px;
    line-height: 18px;
    border-radius: 4px;
    text-transform: uppercase;
    font-weight: 600;
    text-align: center;
    max-width: 160px;
    width: 160px;
}

.btn.btn-default {
    
}

.btn.btn-default:hover {
    background: #eee;
    border-color: #bbb
}

.btn.btn-default:focus {
    background: #ddd;
    border-color: #bbb
}

.btn.btn-primary {
    background-color: #f8981d;
    border-color: #f8981d;
    color: #fff;
}

.btn.btn-primary:hover {
    background-color: #ffac41;
    border-color: #f8981d;
}

.btn.btn-primary:focus {
    background-color: #f8981d;
    border-color: #f8981d;
}

.btn.btn-default[disabled] {
    background: #fafafa !important;
    border-color: #ccc !important;
    color: #aaa
}

.btn.btn-primary[disabled] {
    background: #3F9DD0 !important;
    border-color: #537FA9 !important;
    color: #ACD3E8;
    box-shadow: none !important
}

.btn.btn-left {
    float: left;
    margin: 0 5px 0 0 !important
}

.sd-popup-content img {
    display: block;
    margin: 0 auto 10px;
}

.sd-popup-content h3 {
    text-align: center;
    font: bold;
}
</style>
</head>

<body>

<div class="exampleLive">
        <button style="display: none;" id="openingClosingPopUp"
            class="btn btn-primary"
            data-confirmmodal-bind="#confirm_content_openingclosing"
            data-topoffset="0" data-top="10%">Example</button>
    </div>
    <div id="confirm_content_openingclosing" style="display: none">
        <div class="confirmModal_content sd-popup-content">
            <img src="resources/img/logo.png" class="sd-popup-logo">
            <h3 style="font-size: 14px;">
                <b>You already had completed servey for this order. Thank you</br>
                </b>
            </h3>
        </div>
        <div class="confirmModal_footer">
            <a href="https://www.foodkonnekt.com"  ><button type="button" class="btn btn-primary" 
               data-confirmmodal-but="ok">Ok </button></a>
        </div>
    </div>



	<div
		style='width: 700px; height: auto; border: solid 1px #ddd; box-shadow: 0px 0px 6px #ddd; background-color: #f2f2f2; text-align: center; margin: 40px 0px 0px 35px; margin-left: 25%'>
		<div
			style='width: 700px; height: 85px; padding: 20px 0px; text-align: center;'>
			&nbsp; <img src='resources/img/logo.png' height='80' />
		</div>
		<form:form action="saveWalkInCustomerFeedback" id="feedback_walk_in"
			modelAttribute="CustomerFeedback"  method="POST">
			
			<form:hidden path="customer.id" value="${customer.id}" />
			<form:hidden path="customer.id" value="${orderR.customer.id}" />
			<form:hidden path="orderR.id" value="${orderR.id}" />
			<div
				style='background-color: #fff; padding: 20px 0px 0px 0px; width: 90%; margin: 0 auto 65px auto;'>
				<p style='font-size: 20px; color: #e6880f;'>Dear
					${orderR.customer.firstName},</p>
				<p style='font-size: 14px;'>Thank you for your time to fill
					feedback form.&nbsp;</p>
					
					
					
					<div style='text-align: left; padding: 20px 20px;'>
					<!-- <label id="errorBox" style="color: red;"></label> -->
					<!-- <p>
						<strong>Merchant</strong><br />
					</p> -->
											<div class="payment-order-tax">
							
							<input type="hidden" id="merchantPosId" name="merchantPosId" value="${posMerchantId}">
							<div class="inventory-items-list-table">
								<form:input path="customer.emailId" ></form:input>
								<select path="name" id="merchantPosId" name="merchantPosId" onchange="blankOrderId()">
								 <c:forEach items="${merchantList}" var="view" varStatus="status">
								 
                                 <option value="${view.posMerchantId}" selected="selected" >${view.name}</option>
                                
								 </c:forEach>
								</select>

				
							</div>
						</div>

				</div>
					
						<div style='text-align: left; padding: 20px 20px;'>
					<label id="errorBox1" style="color: red;"></label>
					<!-- <p>
						<strong>OrderId</strong><br />
					</p> -->
											<div class="payment-order-tax">

							<div class="inventory-items-list-table">
								<form:input path="customer.emailId" ></form:input>
								<form:input path= "orderR.orderPosId"  id="order-register" onblur="checkOrder()" ></form:input>
								<form:hidden id="posMerchantId" path="orderR.orderPosId" value="${posOrderId}" />

							</div>
						</div>
					



				</div>
					
				<c:set var="count" value="0" scope="page" />
				<c:forEach items="${FeedbackQuestionCategories}" var="view"
					varStatus="status">
					<div style='text-align: left; padding: 20px 20px;'>

						<p>
							<strong>${view.feedbackQuestionCategory }:</strong><br />
						</p>
						<div>





							<div class="payment-order-tax">

								<div class="inventory-items-list-table">
									<table width="100%" cellpadding="0" cellspacing="0"
										id="example">
										<thead>
											<tr>
												<th></th>
												<th>Excellent</th>
												<th>Good</th>
												<th>Average</th>
												<th>Below Average</th>
												<th>Poor</th>

											</tr>

										</thead>
										<c:forEach items="${view.feedbackQuestions}"
											var="feedbackQuestions" varStatus="status">
											<tr>
												<td>${feedbackQuestions.question }</td>
												<form:hidden
													path="customerFeedbackAnswers[${count}].feedbackQuestion.id"
													value="${feedbackQuestions.id}" />
													<form:hidden
													path="customerFeedbackAnswers[${count}].feedbackQuestion.question"
													value="${feedbackQuestions.question}" />
													<form:hidden
													path="customerFeedbackAnswers[${count}].feedbackQuestion.feedbackQuestionCategory.id"
													value="${view.id }" />
												<td><form:radiobutton
														path="customerFeedbackAnswers[${count}].answer" value="5" /></td>
												<td><form:radiobutton
														path="customerFeedbackAnswers[${count}].answer" value="4" /></td>
												<td><form:radiobutton
														path="customerFeedbackAnswers[${count}].answer" value="3" /></td>
												<td><form:radiobutton
														path="customerFeedbackAnswers[${count}].answer" value="2" /></td>
												<td><form:radiobutton
														path="customerFeedbackAnswers[${count}].answer" value="1" /></td>

											</tr>
											<c:set var="count" value="${count+1}" />
										</c:forEach>


									</table>
									<!-- <table width="100%" cellpadding="0" cellspacing="0" id="example1">
                                                                                     </table> -->
								</div>
							</div>



						</div>



					</div>
				</c:forEach>



				<div style='text-align: left; padding: 20px 20px;'>
					<label id="errorBox" style="color: red;"></label>
					<p>
						<strong>Name</strong><br />
					</p>
											<div class="payment-order-tax">

							<div class="inventory-items-list-table">
								<form:input path="customer.emailId" ></form:input>
								<form:input path= "customer.firstName"  id="name-register" ></form:input>

							</div>
						</div>
					



				</div>
			
				<div style='text-align: left; padding: 20px 20px;'>
					<label id="errorBox" style="color: red;"></label>
					<p>
						<strong>Email</strong><br />
					</p>
											<div class="payment-order-tax">

							<div class="inventory-items-list-table">
								<form:input path="customer.emailId" ></form:input>
								<form:input path= "customer.emailId" placeholder="Email" id="email-register" ></form:input>

							</div>
						</div>
					



				</div>
				<div style='text-align: left; padding: 20px 20px;'>
				<label id="errorBox" style="color: red;"></label>
					<p>
						<strong>Phone No</strong><br />
					</p>
											<div class="payment-order-tax">

							<div class="inventory-items-list-table">
							
								<form:input path="customer.phoneNumber" ></form:input>
								<form:input path="customer.phoneNumber" type="number"
								 placeholder="Phone" maxlength="10" id="phone-register"></form:input>

							</div>
						</div>
					



				</div>
				<div style='text-align: left; padding: 20px 20px;'>

					
						 <p><strong>Anniversary</strong></p>
                              <label for="anniversary-month">Month</label>
                              <form:select path="anniversaryMonth" id="anniversary-month">
                                <option value="">Select</option>
                                <option value="Jan">Jan</option>
                                <option value="Feb">Feb</option>
                                <option value="Mar">Mar</option>
                                <option value="Apr">Apr</option>
                                <option value="May">May</option>
                                <option value="Jun">Jun</option>
                                <option value="Jul">Jul</option>
                                <option value="Aug">Aug</option>
                                <option value="Sep">Sep</option>
                                <option value="Oct">Oct</option>
                                <option value="Nov">Nov</option>
                                <option value="Dec">Dec</option>
                              </form:select>
                              <label for="anniversary-date">Date</label>
                               <form:select path="anniversaryDate" id="anniversary-date">
                                <option value="">Select</option>
                                <option value="01">01</option>
                                <option value="02">02</option>
                                <option value="03">03</option>
                                <option value="04">04</option>
                                <option value="05">05</option>
                                <option value="06">06</option>
                                <option value="07">07</option>
                                <option value="08">08</option>
                                <option value="09">09</option>
                                <option value="10">10</option>
                                <option value="11">11</option>
                                <option value="12">12</option>
                                <option value="13">13</option>
                                <option value="14">14</option>
                                <option value="15">15</option>
                                <option value="16">16</option>
                                <option value="17">17</option>
                                <option value="18">18</option>
                                <option value="19">19</option>
                                <option value="20">20</option>
                                <option value="21">21</option>
                                <option value="22">22</option>
                                <option value="23">23</option>
                                <option value="24">24</option>
                                <option value="25">25</option>
                                <option value="26">26</option>
                                <option value="27">27</option>
                                <option value="28">28</option>
                                <option value="29">29</option>
                                <option value="30">30</option>
                                <option value="31">31</option>
                             </form:select>
					



				</div>
				<div style='text-align: left; padding: 20px 20px;'>

					
						 <p><strong>Birth Day</strong></p>
                              <label for="birthday-month">Month</label>
                              <form:select path="bdayMonth" id="birthday-month">
                                <option value="">Select</option>
                                <option value="Jan">Jan</option>
                                <option value="Feb">Feb</option>
                                <option value="Mar">Mar</option>
                                <option value="Apr">Apr</option>
                                <option value="May">May</option>
                                <option value="Jun">Jun</option>
                                <option value="Jul">Jul</option>
                                <option value="Aug">Aug</option>
                                <option value="Sep">Sep</option>
                                <option value="Oct">Oct</option>
                                <option value="Nov">Nov</option>
                                <option value="Dec">Dec</option>
                              </form:select>
                              <label for="birthday-date">Date</label>
                               <form:select path="bdayDate" id="birthday-date">
                               <option value="">Select</option>
                                <option value="01">01</option>
                                <option value="02">02</option>
                                <option value="03">03</option>
                                <option value="04">04</option>
                                <option value="05">05</option>
                                <option value="06">06</option>
                                <option value="07">07</option>
                                <option value="08">08</option>
                                <option value="09">09</option>
                                <option value="10">10</option>
                                <option value="11">11</option>
                                <option value="12">12</option>
                                <option value="13">13</option>
                                <option value="14">14</option>
                                <option value="15">15</option>
                                <option value="16">16</option>
                                <option value="17">17</option>
                                <option value="18">18</option>
                                <option value="19">19</option>
                                <option value="20">20</option>
                                <option value="21">21</option>
                                <option value="22">22</option>
                                <option value="23">23</option>
                                <option value="24">24</option>
                                <option value="25">25</option>
                                <option value="26">26</option>
                                <option value="27">27</option>
                                <option value="28">28</option>
                                <option value="29">29</option>
                                <option value="30">30</option>
                                <option value="31">31</option>
                             </form:select>
					



				</div>
								<div style='text-align: left; padding: 20px 20px;'>

					<p>
						<strong>Add some additional commnets:</strong><br />
					</p>
					<div>





						<div class="payment-order-tax">

							<div class="inventory-items-list-table">
								<form:textarea path="customerComments"></form:textarea>
								<!-- <table width="100%" cellpadding="0" cellspacing="0" id="example1">
                                                                                     </table> -->
							</div>
						</div>



					</div>



				</div>
				
				<!-- <input type="submit" value="Save" onclick="saveCustomer()"></input> -->
				<input type="button"  class="btn btn-primary" value="Save" id="submitButton"  onclick="saveCustomer()">
		</form:form>
		
		<script>
		function blankOrderId()
		 {
			 $("#order-register").val("");
			 $('#submitButton').prop('disabled', true);
			 
		 }
		
		
		 
		function saveCustomer() {
			
			  var emailRegex = /^[A-Za-z0-9._]*\@[A-Za-z]*\.[A-Za-z]{2,5}$/;
			  var phoneRegex = /\(?([0-9]{3})\)?([ .-]?)([0-9]{3})\2([0-9]{4})/;
			  var email = $("#email-register").val();
			  var name = $("#name-register").val();
			  var order = $("#order-register").val();
			  var phone = $("#phone-register").val();
			  if ($("#name-register").val() == "") {
			    $("#name-register").focus();
			    $("#errorBox").html("enter your Name");
			    return false;
			  } else if (/^[a-zA-Z0-9- ]*$/.test(name) == false) {
				  alert("in name");
			    $("#name-register").focus();
			    $("#errorBox").html("Your name Contains illegal Characters");
			    return false;
			  } else if (name.length < 4) {
			    $("#name-register").focus();
			    $("#errorBox").html("Name should be a minimum of 4 characters");
			    return false;
			  } 
			  else if ($("#order-register").val() == "") {
				    $("#order-register").focus();
				    $("#errorBox").html("Enter a valid order id");
				    return false;
				  }
			  
			  else if ($("#email-register").val() == "") {
			    $("#email-register").focus();
			    $("#errorBox").html("Enter a valid email id");
			    return false;
			  } else if (!emailRegex.test(email)) {
			    $("#email-register").focus();
			    $("#errorBox").html("Enter a valid email id");
			    return false;
			  } else if ($('#phone-register').val() == "") {
			    $("#phone-register").focus();
			    $("#errorBox").html("Enter phone number");
			    return false;
			  } else if(phone.length >10 || phone.length<10){
				  $("#phone-register").focus();
                  $("#errorBox").html("Phone no. should be 10 digits");
              }else if ($(name != '' && email != '' && phone != '' && order != '')) {
				 $("#feedback_walk_in").submit();
			  } 
			   
			}
		
		function checkOrder(){
			
			var posMerchantId = $("#merchantPosId").val();
			var posOrderId = $("#order-register").val();
		 	
			if ($("#order-register").val() == "") {
			    $("#order-register").focus();
			    $("#errorBox").html("Enter a valid order id");
			    return false;
			  }
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
			        	  $("#errorBox1").html("");
			        	  $('#submitButton').prop('disabled', false);
			        	
			            return true;
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
		

	
		$(document).ready(function() {
			
			var checkFeedbackStatus = '${feedBackStatus}';
				
				if(checkFeedbackStatus=='true')
					{
			jQuery('#openingClosingPopUp').click();
					}
		}); 



 
</script>
		
</body>
</html> --%>





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
		<link rel="stylesheet" type="text/css" href="resources/kritiq/css/font-awesome.min.css" />
		<link rel="stylesheet" type="text/css" href="resources/kritiq/css/cs-select.css" />
		<link rel="stylesheet" type="text/css" href="resources/kritiq/css/cs-skin-boxes.css" />
		<link rel="stylesheet" type="text/css" href="resources/kritiq/css/style4.css" />
		<script src="resources/kritiq/js/modernizr.custom.js"></script>
	</head>
	<body class="design4 design3">
		<div class="container feedbackTwo">
		 
			<div class="fs-form-wrap" id="fs-form-wrap">
			
			<div class="fs-form-full text-right socialIcons">
					<a href="javascript:void(0);"><i  aria-hidden="true"></i></a>
					<a href="javascript:void(0);"><i  aria-hidden="true"></i></a>
					<a href="javascript:void(0);"><i  aria-hidden="true"></i></a>
					<a href="javascript:void(0);"><i  aria-hidden="true"></i></a>
				</div>
			
			
			
			
			
				<div class="fs-title text-center">
					<!-- <img src="resources/kritiq/img/logo.png" /> -->
					<img src="/${merchantLogo}" onerror="this.src='resources/img/logo.png'" width="250" height="150" />
				</div>
				
					
				
				
    
				
				<form:form action="saveWalkInCustomerFeedback" id="myform" modelAttribute="CustomerFeedback" method="POST"  class="fs-form fs-form-full" autocomplete="off">
				
			<form:hidden path="customer.id" value="${orderR.customer.id}" />
			<form:hidden path="orderR.id" value="${orderR.id}" />
				<%-- <p style='font-size: 20px; color: #e6880f;'>Dear
					${orderR.customer.firstName},</p>
				<p style='font-size: 14px;'>Thank you for your time to fill
					feedback form.&nbsp;</p> --%>
				
				
				<div >
					<!-- <label id="errorBox" style="color: red;"></label> -->
					<!-- <p>
						<strong>Merchant</strong><br />
					</p> -->
											<div class="payment-order-tax">
							
							<input type="hidden" id="merchantPosId" name="merchantPosId" value="${posMerchantId}">
							<%-- <div class="inventory-items-list-table">
								<form:input path="customer.emailId" ></form:input>
								<select path="name" id="merchantPosId" name="merchantPosId" onchange="blankOrderId()">
								 <c:forEach items="${merchantList}" var="view" varStatus="status">
								 
                                 <option value="${view.posMerchantId}" selected="selected" >${view.name}</option>
                                
								 </c:forEach>
								</select>

				
							</div> --%>
						</div>

				</div>
					
						<div >
					<label id="errorBox1" style="color: red;"></label>
					<!-- <p>
						<strong>OrderId</strong><br />
					</p> -->
											<div class="payment-order-tax">

							<div class="inventory-items-list-table">
								<%-- <form:input path="customer.emailId" ></form:input> --%>
								<form:hidden path= "orderR.orderPosId"  id="order-register" value="${orderId}" ></form:hidden>
								<%-- <form:input path= "orderR.orderPosId"  id="order-register" value="${orderId}" onblur="checkOrder()" ></form:input> --%>
								<%-- <form:hidden id="posMerchantId" path="orderR.orderPosId" value="${posOrderId}" /> --%>

							</div>
						</div>
					



				</div>
				
				<c:set var="count" value="0" scope="page" />
				
				
					<ol class="fs-fields">
				<c:forEach items="${FeedbackQuestionCategories}" var="view"
					varStatus="status">
						<!-- <li data-input-trigger> -->
						<li >
						<div class="option-detail-block">
								<div class="option-detail">
									<!-- <span class="iconDetails brokenHeart">Heart Broken</span>
									<span class="iconDetails hurt">Hurt</span>
									<span class="iconDetails heartLike">Like</span>
									<span class="iconDetails love">Love</span>
									<span class="iconDetails inLove">Madly in Love</span> -->
									<div class="fs-radio-group fs-radio-custom clearfix fs-anim-lower text-center">
										<span><label class="radio-exp brokenHeart">Heart Broken</label></span>
										<span><label class="radio-exp rad-line hurt">Hurt</label></span>
										<span><label class="radio-exp heartLike">Like</label></span>
										<span><label class="radio-exp love">Love</label></span>
										<span><label class="radio-exp inLove">Madly in Love</label></span>
									</div>
								</div>
							</div>
						<c:if test="${count==0}">
							<div class="firstget">
								<p>We want our customers to have unique dining experience and your feedback will enable us to serve you better next time.</p>
								<p>We Thank You for your time and for your insights.</p>
							</div>
						</c:if>
						
						
							<div class="header-title">${view.feedbackQuestionCategory }</div>
							<div class="boxed-block">
								<c:forEach items="${view.feedbackQuestions}"
											var="feedbackQuestions" varStatus="status">
								<label class="fs-field-label fs-anim-upper" for="q1">${feedbackQuestions.question }</label>
								<div class="fs-radio-group fs-radio-custom clearfix fs-anim-lower" >
								<!-- <table width="100%" cellpadding="0" cellspacing="0"
										id="example" > -->
										
										<%-- <c:if test="${count != 0}">
										<script>
										alert("wwwwwwww");
									//firstmsg.style.visibility  = "hidden";
									
										</script>
										</c:if> --%>
											
								<%-- <td><label class="fs-field-label fs-anim-upper" for="q1">${feedbackQuestions.question }</label></td> --%>
											
											<form:hidden path="customerFeedbackAnswers[${count}].feedbackQuestion.id" value="${feedbackQuestions.id}" />
													<form:hidden path="customerFeedbackAnswers[${count}].feedbackQuestion.question" value="${feedbackQuestions.question}" />
													<form:hidden path="customerFeedbackAnswers[${count}].feedbackQuestion.feedbackQuestionCategory.id" value="${view.id }" />
											
											
											
											
                                 <!--  <td > --><span><form:radiobutton id="q3e[${count}]" name="q3" readonly="true" path="customerFeedbackAnswers[${count}].answer" value="1"/><label for="q3e[${count}]" class="radio-exp brokenHeart"></label></span><!-- </td> -->
								 <!--  <td > --><span><form:radiobutton id="q3d[${count}]" name="q3" readonly="true" path="customerFeedbackAnswers[${count}].answer" value="2"/><label for="q3d[${count}]" class="radio-exp rad-line hurt"></label></span><!-- </td> -->
								 <!--  <td > --><span><form:radiobutton id="q3a[${count}]" name="q3" readonly="true" path="customerFeedbackAnswers[${count}].answer" value="3"/><label for="q3a[${count}]" class="radio-exp heartLike"></label></span><!-- </td> -->
								 <!--  <td > --><span><form:radiobutton id="q3c[${count}]" name="q3" readonly="true" path="customerFeedbackAnswers[${count}].answer" value="4"/><label for="q3c[${count}]" class="radio-exp love"></label></span><!-- </td> -->
								 <!--  <td > --><span><form:radiobutton id="q3b[${count}]" name="q3" readonly="true" path="customerFeedbackAnswers[${count}].answer" value="5"/><label for="q3b[${count}]" class="radio-exp inLove"></label></span><!-- </td> -->
								
                                                
                                               
                                                
                                 
                                                
										
								</tr>	
								
								<c:set var="count" value="${count+1}" />

									<!-- </table> -->
									</div>
								</c:forEach>
							</div>
						</li>
						</c:forEach>
				
						<li>
							<div class="boxed-block boxed-top" >
							
								<label class="fs-field-label fs-anim-upper" for="q9" data-info="We won't send you spam, we promise...">What's your email address?</label>
								<!-- <input class="fs-anim-lower" id="q9" name="q9" placeholder="mailus@mkonnekt.com" required /> -->
								<form:input path= "customer.emailId" type="email" placeholder="Email" id="email-register" readonly="true"  required="required" maxlength="60" oninput='javascript: if (this.value.length > this.maxLength) this.value = this.value.slice(0, this.maxLength);'></form:input>
								<label class="fs-field-label fs-anim-upper" for="q9">What's your Phone Number?</label>
								<!-- <input class="fs-anim-lower" id="q9" name="q9" placeholder="+91-123456789" required /> -->
								<form:input path="customer.phoneNumber" type="text" class="call_caller_phone" readonly="true" placeholder="Phone" maxlength="12" id="phone-register" required="required" oninput='javascript: if (this.value.length > this.maxLength) this.value = this.value.slice(0, this.maxLength);'></form:input>
							<div class="row">
								<label class="fs-field-label fs-anim-upper" for="q9" data-info="We would like to send a special gift.">When is your Anniversary?</label>
								<!-- <select  class="cs-select cs-skin-boxes fs-anim-lower" id="MonthSelect"> -->
								 <form:select class="cs-select cs-skin-boxes fs-anim-lower" name="anniversarySelect" path="anniversaryMonth" id="anniversarySelect">
									<option value="" data-class="month-select" selected>${CustomerFeedback.anniversaryMonth}</option>
									<option value="Jan" data-class="month-January">January</option>
									<option value="Feb" data-class="month-February">February</option>
									<option value="Mar" data-class="month-March">March</option>
									<option value="April" data-class="month-April">April</option>
									<option value="May" data-class="month-May">May</option>
									<option value="June" data-class="month-June">June</option>
									<option value="July" data-class="month-July">July</option>
									<option value="Aug" data-class="month-August">August</option>
									<option value="Sept" data-class="month-September">September</option>
									<option value="Oct" data-class="month-October">October</option>
									<option value="Nov" data-class="month-November">November</option>
									<option value="Dec" data-class="month-December">December</option>
								</form:select>
								<!-- <select class="cs-select cs-skin-boxes fs-anim-lower" id="DaySelect"> -->
								<form:select class="cs-select cs-skin-boxes fs-anim-lower" name="anniversarySelect" path="anniversaryDate" id="anniversarySelectDay">
									<option value="" data-class="day-select" selected>${CustomerFeedback.anniversaryDate}</option>
									<option value="1" data-class="day-1">1</option>
									<option value="2" data-class="day-2">2</option>
									<option value="3" data-class="day-3">3</option>
									<option value="4" data-class="day-4">4</option>
									<option value="5" data-class="day-5">5</option>
									<option value="6" data-class="day-6">6</option>
									<option value="7" data-class="day-7">7</option>
									<option value="8" data-class="day-8">8</option>
									<option value="9" data-class="day-9">9</option>
									<option value="10" data-class="day-10">10</option>
									<option value="11" data-class="day-11">11</option>
									<option value="12" data-class="day-12">12</option>
									<option value="13" data-class="day-January">13</option>
									<option value="14" data-class="day-February">14</option>
									<option value="15" data-class="day-March">15</option>
									<option value="16" data-class="day-April">16</option>
									<option value="17" data-class="day-May">17</option>
									<option value="18" data-class="day-June">18</option>
									<option value="19" data-class="day-July">19</option>
									<option value="20" data-class="day-August">20</option>
									<option value="21" data-class="day-September">21</option>
									<option value="22" data-class="day-October">22</option>
									<option value="23" data-class="day-November">23</option>
									<option value="24" data-class="day-December">24</option>
									<option value="25" data-class="day-August">25</option>
									<option value="26" data-class="day-September">26</option>
									<option value="27" data-class="day-October">27</option>
									<option value="28" data-class="day-November">28</option>
									<option value="29" data-class="day-December">29</option>
									<option value="30" data-class="day-November">30</option>
									<option value="31" data-class="day-December">31</option>
								</form:select>
							</div>
							<div class="row">
								<label class="fs-field-label fs-anim-upper" for="q9" data-info="We would like to send a special gift.">When is your Birthday?</label>
								<!-- <select  class="cs-select cs-skin-boxes fs-anim-lower"  id="MonthSelectbDay"> -->
								 <form:select  class="cs-select cs-skin-boxes fs-anim-lower" name="birthdaySelect"  path="bdayMonth" id="birthdaySelect">
									<option value="" data-class="month-select" selected>${CustomerFeedback.bdayMonth}</option>
									<option value="Jan" data-class="month-January">January</option>
									<option value="Feb" data-class="month-February">February</option>
									<option value="Mar" data-class="month-March">March</option>
									<option value="April" data-class="month-April">April</option>
									<option value="May" data-class="month-May">May</option>
									<option value="June" data-class="month-June">June</option>
									<option value="July" data-class="month-July">July</option>
									<option value="Aug" data-class="month-August">August</option>
									<option value="Sept" data-class="month-September">September</option>
									<option value="Oct" data-class="month-October">October</option>
									<option value="Nov" data-class="month-November">November</option>
									<option value="Dec" data-class="month-December">December</option>
								</form:select>
								<!-- <select  class="cs-select cs-skin-boxes fs-anim-lower" id="DaySelectbDay"> -->
								<form:select class="cs-select cs-skin-boxes fs-anim-lower" name="birthdaySelect" path="bdayDate" id="birthdaySelectDay">
									<option value="" data-class="day-select" selected>${CustomerFeedback.bdayDate}</option>
									<option value="1" data-class="day-1">1</option>
									<option value="2" data-class="day-2">2</option>
									<option value="3" data-class="day-3">3</option>
									<option value="4" data-class="day-4">4</option>
									<option value="5" data-class="day-5">5</option>
									<option value="6" data-class="day-6">6</option>
									<option value="7" data-class="day-7">7</option>
									<option value="8" data-class="day-8">8</option>
									<option value="9" data-class="day-9">9</option>
									<option value="10" data-class="day-10">10</option>
									<option value="11" data-class="day-11">11</option>
									<option value="12" data-class="day-12">12</option>
									<option value="13" data-class="day-January">13</option>
									<option value="14" data-class="day-February">14</option>
									<option value="15" data-class="day-March">15</option>
									<option value="16" data-class="day-April">16</option>
									<option value="17" data-class="day-May">17</option>
									<option value="18" data-class="day-June">18</option>
									<option value="19" data-class="day-July">19</option>
									<option value="20" data-class="day-August">20</option>
									<option value="21" data-class="day-September">21</option>
									<option value="22" data-class="day-October">22</option>
									<option value="23" data-class="day-November">23</option>
									<option value="24" data-class="day-December">24</option>
									<option value="25" data-class="day-August">25</option>
									<option value="26" data-class="day-September">26</option>
									<option value="27" data-class="day-October">27</option>
									<option value="28" data-class="day-November">28</option>
									<option value="29" data-class="day-December">29</option>
									<option value="30" data-class="day-November">30</option>
									<option value="31" data-class="day-December">31</option>
								</form:select>
							</div>
						</div>
						</li>
						
					<%-- 	<div style='text-align: left; padding: 20px 20px;'>
					<p>
						<strong>Add some additional commnets:</strong><br />
					</p>
					<div>
						<div class="payment-order-tax">

							<div class="inventory-items-list-table">
								<form:textarea path="customerComments"></form:textarea>
								<!-- <table width="100%" cellpadding="0" cellspacing="0" id="example1">
                                                                                     </table> -->
							</div>
						</div>
					</div>



				</div> --%>
						
						<li data-input-trigger>
						
						<div   class="boxed-top paddingBoxed" id="sudmitButtonDiv">
							<label class="fs-field-label fs-anim-upper" for="q4">Additional Comments</label>
							<form:textarea class="fs-anim-lower" id="q4" name="q4" path="customerComments" placeholder="Describe here" maxlength="200" oninput='javascript: if (this.value.length > this.maxLength) this.value = this.value.slice(0, this.maxLength);' onkeyup="this.value = this.value.replace(/[&*<>^]/g, '')"></form:textarea>
						
					</div>
						
						<%-- <label class="fs-field-label fs-anim-upper" for="q4">Additional Comments</label>
							<form:textarea class="fs-anim-lower" id="q4" name="q4" path="customerComments" placeholder="Describe here"></form:textarea>
							<!-- <textarea class="fs-anim-lower" id="q4" name="q4" placeholder="Describe here"></textarea> --> --%>
						</li>
					</ol><!-- /fs-fields -->
					<!-- <div class="hideForm" id="sudmitButtonDiv">
						<p>Thank you for your time!</p>
						 <p><button class="fs-submit" type="button" id="submitButton" >Submit Your Feedback</button></p>
						 <p><button class="fs-submit" type="submit" id="submitButton" onclick="window.location='feedbackshare.html';">Submit Your Feedback</button></p> 
						<p><input type="button" class="fs-submit"  value="Save" id="submitButton" onclick="window.location='feedbackshare.html';" >Submit Your Feedback</p>
						 onclick="saveCustomer()"
					</div> -->
				</form:form><!-- /fs-form -->
				
                                                                                              
                                                                                              
                                                                                              
                                                                                              
                                                                                              
                                                                                               
				<div class="fs-form-overview hideForm" style="display: none;text-align: center;" id="afterFeedback">
						<p>Thank you for your feedback !</p>
						
						<!-- <p><button class="fs-submit" type="submit" id="submitButton" onclick="window.location='feedbackshare.html';">Submit Your Feedback</button></p> -->
						<!-- <p><input type="button" class="fs-submit"  value="Save" id="submitButton" onclick="window.location='feedbackshare.html';" >Submit Your Feedback</p> -->
						 <!-- onclick="saveCustomer()" -->
				</div>
				
				
                                                                                              
                                                                                             
                                                                                              
			</div><!-- /fs-form-wrap -->


		</div><!-- /container -->
		<script src="resources/kritiq/js/classie.js"></script>
		<script src="resources/kritiq/js/selectFx.js"></script>
		<script src="resources/kritiq/js/fullscreenForm2.js"></script>
		<script src="resources/kritiq/js/jquery-2.1.1.min.js"></script>
		<script>
			(function() {
				var formWrap = document.getElementById( 'fs-form-wrap' );

				[].slice.call( document.querySelectorAll( 'select.cs-select' ) ).forEach( function(el) {	
					new SelectFx( el, {
						stickyPlaceholder: false,
						onChange: function(val){
							document.querySelector('span.cs-placeholder').style.backgroundColor = val;
						}
					});
				} );

				new FForm( formWrap, {
					onReview : function() {
						classie.add( document.body, 'overview' ); // for demo purposes only
					}
				} );
			})();
			$(".cs-placeholder").on("click", function(){
				if($(this).parent().hasClass("cs-active") && $(this).parent().has("#anniversarySelect")){
					$("#anniversarySelectDay").parent().find(".cs-placeholder").css("display","none");
					$("#birthdaySelect").parent().find(".cs-placeholder").css("display","none");
				}
				if($(this).parent().hasClass("cs-active") && $(this).parent().has("#birthdaySelect")){
					$("#birthdaySelectDay").parent().find(".cs-placeholder").css("display","none");
				}
				
			});
			$(".cs-options li").on("click", function(){
					$("#anniversarySelectDay").parent().find(".cs-placeholder").css("display","block");
					$("#birthdaySelectDay").parent().find(".cs-placeholder").css("display","block");
					$("#birthdaySelect").parent().find(".cs-placeholder").css("display","block");
			});
			
			
			
			
			function blankOrderId()
			 {
				 $("#order-register").val("");
				 $('#submitButton').prop('disabled', true);
				 
			 }
			
			$(document).on("keyup", ".call_caller_phone", function() {
			      var val = this.value.replace(/\D/g,'');
			      var newVal = '';
			      while (val.length > 3) {
			      if (val.length == 10) {
			      newVal += val.substr(0, 4);
			      return false;
			      } else {
			      newVal += val.substr(0, 3) + '-';
			      val = val.substr(3);
			      }
			      }
			      newVal += val;
			      this.value = newVal;
			    });			
			 
			function saveCustomer() {
				
				  var emailRegex = /^[A-Za-z0-9._]*\@[A-Za-z]*\.[A-Za-z]{2,5}$/;
				  var phoneRegex = /\(?([0-9]{3})\)?([ .-]?)([0-9]{3})\2([0-9]{4})/;
				  var email = $("#email-register").val();
				  var name = $("#name-register").val();
				  var order = $("#order-register").val();
				  var phone = $("#phone-register").val();
				  if ($("#name-register").val() == "") {
				    $("#name-register").focus();
				    $("#errorBox").html("enter your Name");
				    return false;
				  } else if (/^[a-zA-Z0-9- ]*$/.test(name) == false) {
					  alert("in name");
				    $("#name-register").focus();
				    $("#errorBox").html("Your name Contains illegal Characters");
				    return false;
				  } else if (name.length < 4) {
				    $("#name-register").focus();
				    $("#errorBox").html("Name should be a minimum of 4 characters");
				    return false;
				  } 
				  else if ($("#order-register").val() == "") {
					    $("#order-register").focus();
					    $("#errorBox").html("Enter a valid order id");
					    return false;
					  }
				  
				  else if ($("#email-register").val() == "") {
				    $("#email-register").focus();
				    $("#errorBox").html("Enter a valid email id");
				    return false;
				  } else if (!emailRegex.test(email)) {
				    $("#email-register").focus();
				    $("#errorBox").html("Enter a valid email id");
				    return false;
				  } else if ($('#phone-register').val() == "") {
				    $("#phone-register").focus();
				    $("#errorBox").html("Enter phone number");
				    return false;
				  } else if(phone.length >10 || phone.length<10){
					  $("#phone-register").focus();
	                  $("#errorBox").html("Phone no. should be 10 digits");
	              }else if ($(name != '' && email != '' && phone != '' && order != '')) {
					 $("#feedback_walk_in").submit();
				  } 
				   
				}
			function checkOrder(){
				var posMerchantId = $("#merchantPosId").val();
				var posOrderId = $("#order-register").val();
				if ($("#order-register").val() == "") {
				    $("#order-register").focus();
				    $("#errorBox").html("Enter a valid order id");
				    return false;
				  }
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
				        	  $("#errorBox1").html("");
				        	  $('#submitButton').prop('disabled', false);
				        	
				            return true;
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
			

		
			$(document).ready(function() {
				
				var checkFeedbackStatus = '${feedBackStatus}';
					
					
			}); 
			
		 	/*  $(".fs-back").on("click", function(){
				$(".fs-back").css("display","block");
				$(".fs-continue").css("display","block");
				 var submitBtn = document.getElementsByClassName("fs-submit-btm");
			submitBtn[0].classList.value = "fs-submit-btm disable-continue-btn";
		});  
			 */
			
				var saveStatus = false;
			  $('#submitButton').on('click',  function () {
				 
				 var myform = document.getElementById("myform");
			        var fd = new FormData(myform );
			        
			        
			        $.ajax({
			            url: "saveWalkInCustomerFeedbackByAjax",
			            data: fd,
			            cache: false,
			            processData: false,
			            contentType: false,
			            type: 'POST',
			            success: function (responseBody) {
			             
			               
			              if(responseBody=='200'){
			            	  
			            	 
			            	  $("#afterFeedback").css("display","block");
			            	  $("#sudmitButtonDiv").css("display","none");
			            	  
			            	  setTimeout(function () {
			            		   window.location.href = "https://www.foodkonnekt.com"; 
			            		}, 3000);
			              }
			            }
			        });
			        
				 
			 }); 
			
			  $(document).on("click",".fs-continue, .fs-nav-dots button", function(){
	  				$("html, body").animate({ scrollTop: 0 }, "slow");
				});
		</script>
	</body>
</html>