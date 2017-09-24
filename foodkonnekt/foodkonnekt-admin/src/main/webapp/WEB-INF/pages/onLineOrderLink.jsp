<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<!doctype html>
<html class="no-js" lang="en">
<head>
<title>FoodKonnekt | Dashboard</title>
<!--CALLING STYLESHEET STYE.CSS-->
<link rel="stylesheet" href="resources/css/style.css">
<!--CALLING STYLESHEET STYLE.CSS-->

<!--CALLING GOOGLE FONT OPEN SANS-->
<link
	href='https://fonts.googleapis.com/css?family=Open+Sans:400,300,300italic,400italic,600,600italic,700,700italic,800,800italic'
	rel='stylesheet' type='text/css'>
<!--CALLING GOOGLE FONT OPEN SANS-->

<!--CALLING FONT AWESOME-->
<link rel="stylesheet" href="resources/css/font-awesome.css">
<!--CALLING FONT AWESOME-->

<!--CALLING FILTER OPTIONS-->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<!--  <script src="cdnjs.cloudflare.com/ajax/libs/jquery-timepicker/1.2.17/jquery.timepicker.min.js" type="text/javascript"></script>
     <script src="cdnjs.cloudflare.com/ajax/libs/jquery-timepicker/1.2.17/jquery.timepicker.min.js" type="text/javascript"></script> -->
<script type="text/javascript" charset="utf8"
	src="https://ajax.aspnetcdn.com/ajax/jquery.dataTables/1.9.4/jquery.dataTables.min.js"></script>
<script
	src="https://www.jqueryscript.net/demo/Powerful-jQuery-Data-Table-Column-Filter-Plugin-yadcf/jquery.dataTables.yadcf.js"></script>
<!--OPENS DIALOG BOX-->
<link rel="stylesheet" type="text/css"
	href="resources/css/dialog-box/component.css" />
<!-- <link rel="stylesheet" type="text/css" href="cdnjs.cloudflare.com/ajax/libs/jquery-timepicker/1.2.17/jquery.timepicker.min.css"/> -->


<script
	src="https://netdna.bootstrapcdn.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
<script
	type="http://netdna.bootstrapcdn.com/bootstrap/3.0.3/css/bootstrap.min.css"></script>
<!--OPENS DIALOG BOX-->
<style type="text/css">
.capDay {
	text-transform: capitalize;
}

.inactiveLink {
   pointer-events: none;
   cursor: default;
}
</style>
</head>
<body>
	<div id="page-container">
		<div class="foodkonnekt merchant">
			<div class="inner-container">
				<div class="max-row">

					<header id="page-header">
						<div class="inner-header">
							<div class="row">

								<div class="logo">
									<a href="adminHome" title="FoodKonnekt Dashboard" class="logo"><img
										src="resources/img/foodkonnekt-logo.png"></a>
								</div>
								<!--.logo-->
								<%@ include file="adminHeader.jsp"%>

							</div>
							<!--.row-->
						</div>
						<!--.inner-header-->
					</header>
					<!--#page-header-->

					<div id="page-content">
						<div class="outer-container">
							<div class="row">
								<div class="content-inner-container">
									<%@ include file="leftMenu.jsp"%>

									<div class="right-content-container">
										<div class="right-content-inner-container">

											<div class="content-header">
												<div class="all-header-title"></div>
												<!--.header-title-->
												<div class="content-header-dropdown"></div>
												<!--.content-header-dropdown-->
											</div>
											<!--.content-header-->

											<div class="merchant-page-data">
												<div class="merchant-actions-outbound">
													<div class="merchat-coupons-container">

														<div class="coupons-navigation">
															<ul>
																<li class="current-menu-item"><a
																	href="onLineOrderLink">Location</a></li>
																<li><a href="deliveryZones">Delivery Zones</a></li>
																<li><a href="vouchars">Coupons</a></li>
																<li><a href="customers">Customers</a></li>
															</ul>
														</div>
														<!--.coupons-navigation-->

														<div class="coupons-content-container">

															<div class="clearfix"></div>

															<div class="location-container">
																<div class="location-container-form">
																	<form:form action="saveBusinessLogo" method="POST"
																		modelAttribute="OpenHours" id="businessLogo"
																		enctype="multipart/form-data">
																		<h3>BUSINESS INFORMATION</h3>
																		<label>Business Name:</label>
																		<input type="text" value="${merchant.name}"
																			readonly="readonly">

																		<div class="clearfix"></div>
																		<div>
																			<label>Street Address1</label><input type="text"
																				value="${location.address1}" readonly="readonly">
																		</div>
																		<div>
																			<label>Street Address2</label><input type="text"
																				value="${location.address2}" readonly="readonly">
																		</div>
																		<div>
																			<label>Street Address3</label><input type="text"
																				value="${location.address3}" readonly="readonly">
																		</div>
																		<div>
																			<label>City</label><input type="text"
																				value="${location.city}" readonly="readonly">
																		</div>
																		<div>
																			<label>State</label><input type="text"
																				value="${location.state}" readonly="readonly">
																		</div>
																		<div>
																			<label>Zip</label><input type="text"
																				Placeholder="Postal/Zip Code"
																				value="${location.zip}" readonly="readonly">
																		</div>
																		<div>
																			<label>Phone No.:</label> <input type="text"
																				value="${merchant.phoneNumber}" readonly="readonly"
																				maxlength="10" size="10">
																		</div>
																		<div>
																			<label>Website:</label> <input type="text"
																				readonly="readonly">
																		</div>

																		<div>

																			<label>Payment Type:</label>
																			<div class="payment-order-tax">
																				<ul>
																					<li><form:checkbox id="cash"
																							path="allowPaymentModes" value="Cash"
																							onclick="checkCash()" />Cash</li>
																					<li><form:checkbox id="creditcard"
																							path="allowPaymentModes" value="Credit Card"
																							onclick="checkCreditCard()" />Credit Card</li>
																			</div>

																			<span id="paymentTypeErrorBox" style="color: red;"></span>
</div>

																			<!--.payment-order-tax-->

																			<!-- <div>
																				<label>Order Type:</label>
																				<div class="payment-order-tax">
																					<ul>
																						<li><input type="checkbox" name="paymenttype"
																							value="Delivery">Delivery</li>
																						<li><input type="checkbox" name="paymenttype"
																							value="Pick Up">Pick Up</li>
																				</div>
																				.payment-order-tax
																			</div> -->
																			<form:hidden path="locationId" value="${location.id}" />
																			<div>
																				<label>Pick Up Time:</label>
																				<form:input path="pickUpTiime"
																					value="${pickupTime.pickUpTime}"
																					type='Number' onkeypress="return isNumberKey(event)" 
																					 min="0"/> &nbsp;&nbsp;Minutes
																			</div>
																			<%-- <div>
																				<label>Allow Future Order:</label>
																				
																				
																				        <c:choose>
																										<c:when test="${allowFutureOrder ==1}">
																										<div class="payment-order-tax">
																				 						<ul>
																					                         
																				                           <li> <form:radiobutton path="allowFutureOrder" value="0" id="noRadio"/>No</li>
																				                           <li><form:radiobutton path="allowFutureOrder" value="1" checked="checked" id="yesRadio"/>Yes</li>
																				                           </ul>
																				                           </div>
																										</c:when>
																										<c:otherwise>
																											<div class="payment-order-tax">
																				 						<ul>
																				                           <li> <form:radiobutton path="allowFutureOrder" value="0" checked="checked" id="noRadio"/>No</li>
																				                            <li><form:radiobutton path="allowFutureOrder" value="1" id="yesRadio" />Yes</li>
																				                           </ul>
																				                           </div>
																										</c:otherwise>
																									</c:choose>
																				
																				
																			</div>
																			
																			<div id="daysAhead">
																				<label>Days Ahead:</label>
																				<form:input path="futureDaysAhead"
																				     value="${futureDaysAhead}"
																					type='Number' maxlength="2" onkeypress="return isNumberKey(event)" 
																					 min="0"
																					 oninput="javascript: if (this.value.length > this.maxLength) this.value = this.value.slice(0, this.maxLength);"/>
																			</div>
																			<div>
																				<label>Allow Multiple Koupon:</label>
																				
																				
																				        <c:choose>
																										<c:when test="${allowMultipleKoupon ==1}">
																										<div class="payment-order-tax">
																				 						<ul>
																					                         
																				                           <li> <form:radiobutton path="allowMultipleKoupon" value="0" id="noMultipleRadio"/>No</li>
																				                           <li><form:radiobutton path="allowMultipleKoupon" value="1" checked="checked" id="yesMultipleRadio"/>Yes</li>
																				                           </ul>
																				                           </div>
																										</c:when>
																										<c:otherwise>
																											<div class="payment-order-tax">
																				 						<ul>
																				                           <li> <form:radiobutton path="allowMultipleKoupon" value="0" checked="checked" id="noMultipleRadio"/>No</li>
																				                            <li><form:radiobutton path="allowMultipleKoupon" value="1" id="yesMultipleRadio" />Yes</li>
																				                           </ul>
																				                           </div>
																										</c:otherwise>
																									</c:choose>
																				
																				
																			</div> --%>
																			 <%-- <div>
																				<label>Active Feedback:</label>
																				
																				
																				        <c:choose>
																										<c:when test="${activeCustomerFeedback ==1}">
																										<div class="payment-order-tax">
																				 						<ul>
																					                         
																				                           <li> <form:radiobutton path="activeCustomerFeedback" value="0" />No</li>
																				                           <li><form:radiobutton path="activeCustomerFeedback" value="1" checked="checked" />Yes</li>
																				                           </ul>
																				                           </div>
																										</c:when>
																										<c:otherwise>
																											<div class="payment-order-tax">
																				 						<ul>
																				                           <li> <form:radiobutton path="activeCustomerFeedback" value="0" checked="checked" />No</li>
																				                            <li><form:radiobutton path="activeCustomerFeedback" value="1"  />Yes</li>
																				                           </ul>
																				                           </div>
																										</c:otherwise>
																									</c:choose>
																				
																				
																			</div> --%>
																			
																			<div>
																				<label>Convenience Fee:</label>
																				<%-- <form:input path="convenienceFee"
																					value="${convenienceFee.convenienceFee}"
																					type='Number' onkeypress="return isNumberKey(event)" 
																					 min="0" maxlength="5"
																					oninput="javascript: if (this.value.length > this.maxLength) this.value = this.value.slice(0, this.maxLength);"/> --%>
																			
																			<form:input path="convenienceFee" id = "convenienceFees"
																					value="${convenienceFee.convenienceFee}"
																					type="text"  
																					 min="0" maxlength="5"
																					onkeyup="return validatenumberOnKeyUp(event,this)" onkeypress="return validatenumber(event,this)"/>
																			
																			
																			</div>
																			<div>
																				<label>Taxable:</label>
																				<div class="payment-order-tax">
																					<ul>
																						<li><form:checkbox path="isTaxable" value="1"
																								id="isTaxable" />Yes</li>
																				</div>
																				<!--.payment-order-tax-->
																			</div>
																			
																			
																			 <div>

																			<!-- <label>Allow Customer Feedback:</label>
																			<div class="payment-order-tax">
																				<ul>
																					<li><input type="radio" name="allowfeedback" id="feedbackyes">Yes</li>
																					<li><input type="radio" name="allowfeedback" id="feedbackno">No</li>
																			</div>

																			<span id="paymentTypeErrorBox" style="color: red;"></span>
																			</div> 
																			
																			
																			<div class="gap-30"></div>
                                                  
																			 <h3>Customer Feedback Link</h3>
																			<div class="callout">
																			
																				<span id="onlineLink1">For Allow Customer Feedback Select Yes</span>
																				<div class="button right" id="onlineLinkDivCls">
																				
																				
																					
																				</div>
																			</div>  -->
																			
																			
																			
																			
																			
																			<%-- <h3>SOCIAL MEDIA LINKS</h3>
																			<div>
																			<form:hidden path="socialMediaLinks.id"/>
																				<label>Facebook:</label>
																				<form:input path="socialMediaLinks.faceBookLink"
																					value="${socialMediaLinks.faceBookLink}"
																					type='text' min="0" maxlength="500"
																					oninput="javascript: if (this.value.length > this.maxLength) this.value = this.value.slice(0, this.maxLength);"/>
																			</div>
																			<div>
																				<label>Yelp:</label>
																				<form:input path="socialMediaLinks.yelpLink"
																					value="${socialMediaLinks.yelpLink}"
																					type='text' min="0" maxlength="500"
																					oninput="javascript: if (this.value.length > this.maxLength) this.value = this.value.slice(0, this.maxLength);"/>
																			</div>
																			<div>
																				<label>Instagram:</label>
																				<form:input path="socialMediaLinks.instagramLink"
																					value="${socialMediaLinks.instagramLink}"
																					type='text' min="0" maxlength="500"
																					oninput="javascript: if (this.value.length > this.maxLength) this.value = this.value.slice(0, this.maxLength);"/>
																			</div> --%>
																			<div class="gap-30"></div>
                                                  
																			<h3>ONLINE ORDER LINK</h3>
																			<div class="callout">
																			<div class="pj-loader-3" style="display: none;margin-left: 25%;margin-top: -10%;">
                                                                           <img src="resources/img/load2.gif" style="width: 82px;margin-left: 117px;margin-top: 63px;">
                                                                           </div>
																			<span id="onlineLinkErrorBox" 
																					style="color: black;">We are still in the process of getting inventory data from Clover.</span>
																			<span id="onlineLink">${onlineOrderLink}</span>
																				<div class="button right" id="onlineLinkDivCls">
																				<a href="${onlineOrderLink}" target="_blank">Preview</a>
																				
																					<%-- <a href="#"
																						onClick="window.open('${onlineOrderLink}')">Preview</a> --%>
																				</div>
																				<div class="button right" id="enableOnlineOrderLink">
																				
																				<a 	>Yes</a>
																				
																				
																				</div>
																			</div>

																			<div class="gap-30"></div>

																			<h3>BUSINESS HOURS</h3>
																			<div class="callout" id="businessHours">
																				<span id="businessHourseErrorBox"
																					style="color: red;"></span>

																				<div class="button right">
																					<a href="#"
																						onClick="window.open('https://www.clover.com/setupapp/m/${merchant.posMerchantId}/businessinformation')">Go
																						To Clover</a>
																				</div>
																			</div>

																			<div id="businessHoursContainer3"></div>


																			<div class="business-hours">
																				<ul>
																					<c:forEach items="${businessHours}" var="bsh"
																						varStatus="status">
																						<li>
																							<div class="business-hours-li-left">
																								<label class="capDay"
																									style="color: black; line-height: 15px;">${bsh.day}</label>
																								<select class="opncls" name="selectedDay"
																									style="width: 90px;" attid="a_${bsh.id}">
																									<c:choose>
																										<c:when test="${bsh.isHoliday ==1}">
																											<option value="${bsh.id}:open">Open</option>
																											<option value="${bsh.id}:close"
																												selected="selected">Closed</option>
																										</c:when>
																										<c:otherwise>
																											<option value="${bsh.id}:open"
																												selected="selected">Open</option>
																											<option value="${bsh.id}:close">Closed</option>
																										</c:otherwise>
																									</c:choose>

																									<%--   <option value="onLineOrderLinkonLineOrderLinkopen:${bsh.id}">Open</option>
                                                                                        <option value="close:${bsh.id}">Close</option> --%>
																								</select>
																							</div> <!--.business-hours-li-left-->

																							<div class="business-hours-li-right teset"
																								style="width: 57%;">
																								<c:forEach items="${bsh.times}" var="time"
																									varStatus="status">
																									<div class="copyTime">
																										<select name="sTimeToSave" style="width: 38%;">
																											<c:forEach items="${times}" var="tm">
																												<c:choose>
																													<c:when test="${tm ==time.startTime}">
																														<option value="${bsh.id}_${tm}"
																															selected="selected">${tm}</option>
																													</c:when>
																													<c:otherwise>
																														<option value="${bsh.id}_${tm}">${tm}</option>
																													</c:otherwise>
																												</c:choose>
																											</c:forEach>
																										</select>

																										<to>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;to&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</to>

																										<select name="eTimeToSave" style="width: 38%;">
																											<c:forEach items="${times}" var="tm">
																												<c:choose>
																													<c:when test="${tm ==time.endTime}">
																														<option value="${tm}" selected="selected">${tm}</option>
																													</c:when>
																													<c:otherwise>
																														<option value="${tm}">${tm}</option>
																													</c:otherwise>
																												</c:choose>
																											</c:forEach>
																										</select>
																									</div>
																								</c:forEach>
																								<div class="copyToTime"
																									style="min-height: 47px;"></div>

																							</div> <!--.business-hours-li-right-->
																							<div class="business-hours-li-right"
																								style="width: 8%;">
																								<button id="0"
																									class="c-button-icon-primary optButton"
																									data-ember-action="1124" type="button">
																									<i class="fa fa-plus-circle hidden-xs"></i>
																								</button>

																								<button id="0"
																									class="e__button-icon-secondary optButtondel"
																									data-ember-action="1125" type="button">
																									<i class="fa fa-trash-o hidden-xs"></i>
																								</button>
																							</div>

																						</li>
																					</c:forEach>
																				</ul>
																			</div>

																			<div class="gap-30"></div>

																			<h3>BUSINESS LOGO</h3>
																			<div class="business-logo">
																				<!-- <img src="resources/img/230x100.png" /> -->
																				<img
																					src="data:image/jpeg;base64,${merchant.merchantLogo}"
																					onerror="this.src='resources/img/230x100.png'"
																					width="118" height="88" alt="image">
																				<p>(Max Upload Size 2 mb & Dimension 230x100 in
																					Pixels)</p>
																				<input type="file" name="file" id="image-file"
																					accept="image/*"> <label id="errorBox"
																					style="color: red;"></label>
																			</div>
																			<!--.business-logo-->
																			<c:choose>
																										<c:when test="${inventoryThread ==1}">
																										<div class="button">
																				<a href="#" id="businessLogoButton" >Save</a>
																			
																			
																			</div>
																										</c:when>
																										<c:otherwise>
																										<div class="button">
																				<a href="#" id="businessLogoButton" class="inactiveLink">Save</a>
																			</div>
																										</c:otherwise>
																									</c:choose>
																			
																			<!--.button-->
																	</form:form>
																</div>
																<!--.location-container-form-->
															</div>
															<!--.location-container-->

														</div>
														<!--.coupons-content-container-->
													</div>
													<!--.merchat-coupons-container-->
												</div>
												<!--.merchant-actions-outbound-->
											</div>
											<!--.merchant-page-data-->

										</div>
										<!--.right-content-inner-container-->
									</div>
									<!--.right-content-container-->
								</div>
								<!--.content-inner-container-->
							</div>
							<!--.row-->

						</div>
						<!--.outer-container-->
					</div>
					<!--#page-content-->
					<%@ include file="adminFooter.jsp"%>
					<!--#footer-container-->

				</div>
				<!--.max-row-->
			</div>
			<!--.inner-container-->
		</div>
		<!--.foodkonnekt .dashboard-->
	</div>
	<!--#page-container-->

	<div id="sd-dialog">
		<div class="md-modal md-effect-14" id="modal-14">
			<div class="md-content">
				<h3>Modal Dialog</h3>
				<div>
					<form action="" method="get" class="home-pop-form">
						<input name="name" type="text" placeholder="Your Name"> <input
							name="name" type="email" placeholder="Your Email">
						<textarea name="query" cols="" rows="3"
							placeholder="State Your Query"></textarea>
						<div class="button">
							<input name="submit" type="button" value="Post Your Query"
								class="button left">
						</div>
						<!--.button-->
					</form>
					<!--.home-pop-form-->
					<div class="clearfix"></div>
					<div class="button">
						<button class="white-button md-close">Close me!</button>
					</div>
					<!--.button-->
				</div>
			</div>
		</div>
		<div class="md-overlay"></div>
		<!-- the overlay element -->
	</div>
	<!--#sd-dialog-->

	<!--OPENS DIALOG BOX-->
	<script src="resources/js/dialog-box/classie.js"></script>
	<script src="resources/js/dialog-box/modalEffects.js"></script>
	<script>
		var polyfilter_scriptpath = '/js/';
	</script>
	<!--OPENS DIALOG BOX-->
	<script type="text/javascript">
	

	
	function testcheck()
	{
	    if (!jQuery("#cash").is(":checked") && !jQuery("#creditcard").is(":checked") ) {
	    	
	    	$("#paymentTypeErrorBox").html("At least one payment option should be selected");
	    	
	    	    	
	        return false;
	    }
	    $("#paymentTypeErrorBox").html("");
	    return true;
	}
	
	function checkCash()
	{
	    if (!jQuery("#cash").is(":checked") && !jQuery("#creditcard").is(":checked") ) {
	    	
	    	$("#paymentTypeErrorBox").html("At least one payment option should be selected");
	    	document.getElementById("cash").checked = true;
	    	    	
	        return false;
	    }
	    $("#paymentTypeErrorBox").html("");
	    return true;
	}
	
	function checkCreditCard()
	{
	    if (!jQuery("#cash").is(":checked") && !jQuery("#creditcard").is(":checked") ) {
	    	
	    	$("#paymentTypeErrorBox").html("At least one payment option should be selected");
	    	
	    	document.getElementById("creditcard").checked = true;  	
	        return false;
	    }
	    $("#paymentTypeErrorBox").html("");
	    return true;
	}
	
	  var mybutton_counter=0;
	 $(document).ready(function() {
		 
		 var inventoryThreadStatus="${inventoryThread}";
		 var isTaxAvailable="${isTaxAvailable}";
		
		 var allowfutureOrder="${allowFutureOrder}";
		 var allowMultipleKoupon = "${allowMultipleKoupon}";
		 var customerFeedback="";
		 customerFeedback= ${activeCustomerFeedback};
		 var linkActiveCustomerFeedback= "${linkActiveCustomerFeedback}";
				
		  if(customerFeedback==1){
			 $("#feedbackyes").prop('checked', true);
			 mybutton_counter = 1;
			 $("#onlineLink1").html(linkActiveCustomerFeedback);
			 $("#previewId").attr("href",linkActiveCustomerFeedback);
		 }else{
			 $("#feedbackno").prop('checked', true);
			 mybutton_counter = 0;
		 }	  
		 if(allowfutureOrder==1){
			 $("#daysAhead").show();
		 }else{
			 $("#daysAhead").hide();
		 }
		 
		 if(inventoryThreadStatus==1){
			
			   if(isTaxAvailable=='false'){
				   
				   $("#onlineLinkErrorBox").show();
					 $("#onlineLinkErrorBox").html("Your sales tax is set to zero. Click on Yes button to enable online ordering link or set your taxes in your PoS system.");
					 $("#onlineLink").hide();
					 $("#onlineLinkDivCls").hide();
					 $("#enableOnlineOrderLink").show();
					 
			   }else{
			 $("#onlineLinkErrorBox").hide();
			 $("#onlineLink").show();
			 $("#onlineLinkDivCls").show();
			 $("#enableOnlineOrderLink").hide();
			   }
			 
		 }else{
			 
			 $("#onlineLinkErrorBox").show();
			 $("#onlineLinkErrorBox").html("We are still in the process of getting inventory data from Clover.");
			 $("#onlineLink").hide();
			 $("#onlineLinkDivCls").hide();
			 $("#enableOnlineOrderLink").hide();
		 }
		 
		 var businessHoursSize ="${businessHours.size()}";
		if(businessHoursSize==0){
			
			$("#businessHourseErrorBox").html("Please add business hours on Clover to enable online ordering platform");
		}else{
			$("#businessHours").hide();
		}
		 var isTaxable="${convenienceFee.isTaxable}";
         if(isTaxable==1){
             $("#isTaxable").attr('checked', 'checked');
           }
         
         var cash="${cash}";
         if(cash==1){
             $("#cash").attr('checked', 'checked');
           }
         var creditcard="${creditcard}";
         
         if(creditcard==1){
             $("#creditcard").attr('checked', 'checked');
           }
           
	 });
		$(document)
				.ready(
						function() {
							$(".opncls").each(function() {
								if (($(this).val()).indexOf('close') > -1) {
									$(this).parent().next().next().hide();
								}
							});

							$(".opncls")
									.change(
											function() {
												var dayValue = $(this).val();
												if (dayValue.indexOf('open') > -1) {
													var timDivLngth = $(this)
															.parent().next()
															.find('.copyTime').length;

													if (timDivLngth == 0) {
														console.log("----"
																+ timDivLngth);
														var opclVal = $(this)
																.val();
														var opclArray = [];
														opclArray = opclVal
																.split(":");
														var dayId = opclArray[0];

														console
																.log($(this)
																		.parent()
																		.next()
																		.find(
																				'.copyTime').length);

														var timesVal = '${times}';
														var timeAyy = timesVal
																.split(",");
														var opt = '';
														var endTimeStr = '';
														for (var i = 1; i < (timeAyy.length - 1); i++) {
															opt += "<option value='"
																	+ dayId
																	+ '_'
																	+ (timeAyy[i])
																			.trim()
																	+ "'>"
																	+ timeAyy[i]
																	+ "</option>";
															endTimeStr += "<option value='"
																	+ (timeAyy[i])
																			.trim()
																	+ "'>"
																	+ timeAyy[i]
																	+ "</option>";
														}
														$(this)
																.parent()
																.next()
																.append(
																		"<div class='copyTime'><div>");
														aa = "<div class='copyTime'><select name='sTimeToSave' style='width: 38%;'>"
																+ opt
																+ "</select><to>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;to&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</to><select name='eTimeToSave' style='width: 38%;'>"
																+ endTimeStr
																+ "</select><div>";
														$(this)
																.parent()
																.next('.teset')
																.find(
																		'.copyToTime:first')
																.append(aa);
													}
													//$(this).parent().next().find('.copyToTime').show(); 
													$(this).parent().next()
															.find('.copyTime')
															.show();
													$(this).parent().next()
															.next().show();
												} else {
													// $(this).parent().next().find('.copyToTime').hide();
													$(this).parent().next()
															.find('.copyTime')
															.hide();
													$(this).parent().next()
															.next().hide();
												}
											});
							
							
							$("#yesRadio").click(
									function() {
										$("#daysAhead").show();
									});
							
							$("#noRadio").click(
									function() {
										$("#daysAhead").hide();
									});

							$(".optButton")
									.click(
											function() {
												var copCount = $(this)
														.parent()
														.prev('.teset')
														.find(
																".copyTime select").length;
												if (copCount == 0) {
													var selectval = $(this)
															.parent()
															.prev('.teset')
															.prev(
																	'.business-hours-li-left')
															.find('select')
															.val();
													var res = selectval
															.replace("close",
																	"open");
													$(this)
															.parent()
															.prev('.teset')
															.prev(
																	'.business-hours-li-left')
															.find('select')
															.val(res);

													var opclVal = selectval;
													var opclArray = [];
													opclArray = opclVal
															.split(":");
													var dayId = opclArray[0];

													var timesVal = '${times}';
													var timeAyy = timesVal
															.split(",");
													var opt = '';
													var endTimeStr = '';
													for (var i = 1; i < (timeAyy.length - 1); i++) {
														opt += "<option value='"
																+ dayId
																+ '_'
																+ (timeAyy[i])
																		.trim()
																+ "'>"
																+ timeAyy[i]
																+ "</option>";
														endTimeStr += "<option value='"
																+ (timeAyy[i])
																		.trim()
																+ "'>"
																+ timeAyy[i]
																+ "</option>";
													}
													$(this)
															.parent()
															.prev('.teset')
															.append(
																	"<div class='copyTime'><div>");
													aa = "<div class='copyTime'><select name='sTimeToSave' style='width: 38%;'>"
															+ opt
															+ "</select><to>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;to&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</to><select name='eTimeToSave' style='width: 38%;'>"
															+ endTimeStr
															+ "</select><div>";
													$(this)
															.parent()
															.prev('.teset')
															.find(
																	'.copyToTime:first')
															.append(aa);
												} else {
													$(this).parent().prev(
															'.teset')
													var cln = $(this)
															.parent()
															.prev('.teset')
															.find(
																	'.copyTime:first')
															.clone();
													var cpy = $(this)
															.parent()
															.prev('.teset')
															.find('.copyToTime');
													cln.appendTo(cpy);
												}
											});

							$(".optButtondel")
									.click(
											function() {
												$(this).parent().prev('.teset')
														.find('.copyTime:last')
														.remove();
												var copCount = $(this)
														.parent()
														.prev('.teset')
														.find(
																".copyTime select").length;
												if (copCount == 0) {
													var selectval = $(this)
															.parent()
															.prev('.teset')
															.prev(
																	'.business-hours-li-left')
															.find('select')
															.val();
													var res = selectval
															.replace("open",
																	"close");
													$(this)
															.parent()
															.prev('.teset')
															.prev(
																	'.business-hours-li-left')
															.find('select')
															.val(res);
													$(this).parent().hide();
												}
								
											});

							var imageSize;
							$('#image-file').on('change', function() {
							       //alert('This file size is: ' + (this.files[0].size/1024/1024).toFixed(2) + " MB");
								   imageSize=(this.files[0].size/1024/1024).toFixed(2);
								   if(imageSize > 2){
								   	   alert("Please select an image lesser than 2 MB");
								   	this.value="";
								   	imageSize=0;
								   	   }
							      });
							$("#businessLogoButton").click(function() {
								if(imageSize>2){
									$("#image-file").focus();
									return false;
								}
								if(!testcheck()){
									alert("At least one payment option should be selected");
									
									return false
								}
								$("#businessLogo").submit();
							});

						});
		
		
	</script>
	<script type="text/javascript">
	
	 function validatenumberOnKeyUp(event,el) {
	    	
	    	//var val = $('#tipAmount').val().replace(/^[0-9]*\.?[0-9]*$/,'');
	    	var val = $('#convenienceFees').val();
	        if(isNaN(val)){
	             val = val.replace(/[^0-9\.]/g,'');
	             if(val.split('.').length>2) 
	                 val =val.replace(/\.+$/,"");
	        }
	        $('#convenienceFees').val(val); 
	        }
			
	 function validatenumber(event,el) {
         return (/^[0-9]*\.?[0-9]*$/).test(el.value+event.key);
       }
	
	
	
	
	
	
	
	
	function isNumberKey(evt)
    {
  	  var target = evt.target || evt.srcElement; // IE
  	  var charCode = (evt.which) ? evt.which : event.keyCode
  	    var id = target.id;
  	    var data=document.getElementById(id).value;
  	  
  	  
  	  if(evt.which == 8 || evt.which == 0){
            return true;
        }
  	  
  	 if (charCode > 31 && (charCode < 48 || charCode > 57)){
         return false;
  		}
  	 
        if(evt.which < 46 || evt.which > 59) {
            return false;
            //event.preventDefault();
        } // prevent if not number/dot

        if(evt.which == 46 && data.indexOf('.') != -1) {
            return false;
            //event.preventDefault();
        }
    }
    
    function currectNo(evt){
  	  var target = evt.target || evt.srcElement; // IE

	    var id = target.id;
	    var data=document.getElementById(id).value;
	    
	    var res=data.split(".");
  	  
  	  if(res.length==2){
  		if(res[1]){
  			
  		}else{
  			
  			document.getElementById(id).value=res[0]+".0";
  		}
  	  }
    }
    
    
   // var mybutton_counter=0;
    $("#feedbackyes").on("click", function () {
    	
    	if (mybutton_counter==0){
    	 var merchantId=${merchant.id};
        var status = "yes";
        $.ajax({
            url : "updateMerchantKritiq?merchantId="+ merchantId +"&status="+ status,
            type : "GET",
            contentType : "application/json; charset=utf-8",
            success : function(result) {
            	
            	
            	if(result ==""){
            		$("#feedbackno").prop('checked', true);
           			$("#feedbackyes").prop('checked', false);
           			$("#onlineLink1").html("Please Subscribe Your Account");
            	}else if(result == "VALIDITY EXPAIRED"){
            		$("#feedbackno").prop('checked', true);
           			$("#feedbackyes").prop('checked', false);
           			$("#onlineLink1").html("VALIDITY EXPAIRED Please Subscribe Your Account");
            	}else{
            		$("#onlineLink1").html(result);
                	$("#previewId").attr("href",result);
                	mybutton_counter++;
            	}
            
            },
            error : function() {
              console.log("Error inside future date Ajax call");
            }
         })
    }
    });
    
    
    
$("#enableOnlineOrderLink").on("click", function () {
    	
    	
	$(".pj-loader-3").css("display","block");
	$("#onlineLinkErrorBox").hide();
	$("#enableOnlineOrderLink").hide();
    	var merchantId=${merchant.id};
    	
    	 $.ajax({
            url : "addDefaultTax?merchantId="+ merchantId,
            type : "GET",
            contentType : "application/json; charset=utf-8",
            success : function(result) {
            	if(result=='success'){
            	$("#onlineLinkErrorBox").hide();
   			 $("#onlineLink").show();
   			 $("#onlineLinkDivCls").show();
   			 $("#enableOnlineOrderLink").hide();
            	}else{
            		 $("#onlineLinkErrorBox").show();
        			 $("#onlineLink").hide();
        			 $("#onlineLinkDivCls").hide();
        			 $("#enableOnlineOrderLink").show();
            	}
   			$(".pj-loader-3").css("display","none");
            },
            error : function() {
              console.log("Error inside future date Ajax call");
              $(".pj-loader-3").css("display","none");
              $("#onlineLinkErrorBox").show();
    			 $("#onlineLink").hide();
    			 $("#onlineLinkDivCls").hide();
    			 $("#enableOnlineOrderLink").show();
            }
         }) 
    	
    	
    });
    
    $("#feedbackno").on("click", function () {
    	
    	$("#onlineLink1").html("To Allow Customer Feedback Select Yes");
    	mybutton_counter = 0;
    	var merchantId=${merchant.id};
    	var status = "no";
    	$.ajax({
            url : "updateMerchantKritiq?merchantId="+ merchantId +"&status="+ status,
            type : "GET",
            contentType : "application/json; charset=utf-8",
            success : function(result) {
            	
            	
            },
            error : function() {
              console.log("Error inside future date Ajax call");
            }
         })
    	
    	
    });
    
    </script>
</body>
</html>
