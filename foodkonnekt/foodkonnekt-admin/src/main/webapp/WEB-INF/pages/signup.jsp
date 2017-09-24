<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
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
<link type="text/css" rel="stylesheet" href="resources/css/popModal.css">
<script src="resources/js/popModal.js"></script>
</head>
<style>
.logo {
	width: 1178px;
}

.clearfix:after {
	content: " ";
	visibility: hidden;
	display: block;
	height: 0;
	clear: both;
}

.clearfix1 {
	width: 30% ! important;
}

.min {
	width: 261px;
}
</style>
<link rel="stylesheet" href="resources/css/popupCSS.css">
<script>
  $(document).ready(function() {
      var saveStatus="${saveStatus}";
      if(saveStatus!=""){ 
          jQuery(function(){
               jQuery('#confirmModal_ex2').click();
            });
        }
      $('#email').blur(function () {
    	  var emailID=$('#email').val();
    	  
    	  $.ajax({
    	         url : "checkDuplicateMerchant?emailId="+emailID,
    	         type : "GET",
    	         contentType : "application/json; charset=utf-8",
    	         success : function(response) {
    	           
    	           if(response){
    	        	   $("#email").css('border-color', 'red');
                       $("#email").focus();
                       $("#errorBox").html("Email already exist ! Please try to login or singup with another email id");
    	             
    	             }else{
    	            	 $("#email").css('border-color', '');
                         $("#errorBox").html("");
    	             }
    	            	 
    	            
    	         },
    	         error : function(data, status, er) {
    	           alert("error: " + data + " status: " + status + " er:" + er);
    	         }
    	       });
  	
      });
      
      });
  function setPickUpTime(){
    window.location.href = "setPickupTime";
  }
  </script>
<body>
	<div class="exampleLive">
		<button id="confirmModal_ex2" style="display: none"
			class="btn btn-primary" data-confirmmodal-bind="#confirm_content"
			data-topoffset="0" data-top="10%">Example</button>
	</div>

	<div id="confirm_content" style="display: none">
		<div class="confirmModal_content sd-popup-content">
			<img src="resources/img/logo.png" class="sd-popup-logo">
			<h3>Your delivery zone has been successfully saved</h3>
			<h3>Do you want to add another Delivery zone?</h3>
		</div>
		<div class="confirmModal_footer">
			<button type="button" class="btn btn-primary uploadedBtn"
				data-confirmmodal-but="ok">Yes</button>
			<button type="button" class="btn btn-primary uploadedBtn"
				data-confirmmodal-but="no" onclick="setPickUpTime()">No</button>
		</div>
	</div>
	<div id="page-container">
		<div class="foodkonnekt merchant">
			<div class="inner-container">
				<div class="max-row">

					<header id="page-header">
					<div class="inner-header">
						<div class="row">

							<div class="logo">
								<a href="index.html" title="FoodKonnekt Dashboard" class="logo">
									<!-- <img src="resources/img/logo.jpg"> </a>--> <img
									src='resources/img/foodkonnekt-logo.png' width="250"
									height="150">
								</a>
							</div>
						</div>
						<!--.row-->
					</div>
					<!--.inner-header--> </header>
					<!--#page-header-->

					<div id="page-content clearfix">
						<div class="outer-container">
							<div style="margin: 0 auto; padding-top: 52px;"
								class="clearfix clearfix1">
								<span style="margin: 10px 0 0 0;"> <b><font size="4">Sign
											Up</font></b></span> <label id="errorBox" style="color: red;"></label>
							</div>

							<div class="row">
								<div class="content-inner-container">
									<form:form method="POST" action="saveMerchant"
										modelAttribute="Merchant" id="setDeliveryZoneForm"
										autocomplete="off" name="formID">
										<div class="adding-products-form"
											style="width: 800px; margin: 0 auto;">
											<p></p>
											<p></p>
											<p></p>
											<p></p>
											<div class="clearfix"></div>


											<div class="clearfix"></div>
											<label style="color: black">Name:</label>
											<form:input path="name" maxlength="30" id="merchantName"
												required='required' placeholder='Merchant/Organization Name' />
											<br>

											<div class="clearfix"></div>
											<label style="color: black">Phone No:</label>
											<form:input path="phoneNumber" maxlength="10"
												id="phoneNumber" placeholder='Phone no'
												style="vertical-align: middle;" type='Number'
												onkeypress="return isNumberKey(event)"
												onblur="currectNo(event)"
												oninput="javascript: if (this.value.length > this.maxLength) this.value = this.value.slice(0, this.maxLength);" />
											<br>

											<div class="clearfix"></div>
											<label style="color: black">Email id:</label>
											<form:input type="email" path="merchantLogin.emailId"
												maxlength="50" placeholder='Email id' id="email"
												style="vertical-align: middle;" />
											<br>

											<div class="clearfix"></div>
											<label style="color: black">Password:</label>
											<form:password path="merchantLogin.password" maxlength="16"
												placeholder='Password' id="pass"
												style="vertical-align: middle;" />
											<br>
											<div class="clearfix"></div>
											<label style="color: black">Confirm Password:</label> <input
												type="password" maxlength="16"
												placeholder='Confirm Password' id="confirmpass"
												style="vertical-align: middle;" /> <br>

											<div class="clearfix"></div>
											<label style="color: black">Address1:</label>
											<form:input path="addresses[0].address1" maxlength="50"
												placeholder='Address1' id="address1"
												style="vertical-align: middle;" />
											<br>

											<div class="clearfix"></div>
											<label style="color: black">Address2:</label>
											<form:input path="addresses[0].address2" maxlength="50"
												placeholder='Address2' id="address2"
												style="vertical-align: middle;" />
											<br>

											<div class="clearfix"></div>
											<label style="color: black">City:</label>
											<form:input path="addresses[0].city" maxlength="50"
												placeholder='City' id="city" style="vertical-align: middle;" />
											<br>

											<div class="clearfix"></div>
											<label style="color: black">State:</label>
											<%-- <form:input path="addresses[0].state" maxlength="50"
												placeholder='State' id="state"
												style="vertical-align: middle;" /> --%>
												<form:select id="state" path="addresses[0].state">
                                                                                        <option value="select">Select</option>
                                                                                        <option value="AL">Alabama</option>
                                                                                        <option value="AK">Alaska</option>
                                                                                        <option value="AZ">Arizona</option>
                                                                                        <option value="AR">Arkansas</option>
                                                                                        <option value="CA">California</option>
                                                                                        <option value="CO">Colorado</option>
                                                                                        <option value="CT">Connecticut</option>
                                                                                        <option value="DE">Delaware</option>
                                                                                        <option value="FL">Florida</option>
                                                                                        <option value="GA">Georgia</option>
                                                                                        <option value="HI">Hawaii</option>
                                                                                        <option value="ID">Idaho</option>
                                                                                        <option value="IL">Illinois</option>
                                                                                        <option value="IN">Indiana</option>
                                                                                        <option value="IA">Iowa</option>
                                                                                        <option value="KS">Kansas</option>
                                                                                        <option value="KY">Kentucky</option>
                                                                                        <option value="LA">Louisiana</option>
                                                                                        <option value="ME">Maine</option>
                                                                                        <option value="MD">Maryland</option>
                                                                                        <option value="MA">Massachusetts</option>
                                                                                        <option value="MI">Michigan</option>
                                                                                        <option value="MN">Minnesota</option>
                                                                                        <option value="MS">Mississippi</option>
                                                                                        <option value="MO">Missouri</option>
                                                                                        <option value="MT">Montana</option>
                                                                                        <option value="NE">Nebraska</option>
                                                                                        <option value="NV">Nevada</option>
                                                                                        <option value="NH">New Hampshire</option>
                                                                                        <option value="NJ">New Jersey</option>
                                                                                        <option value="NM">New Mexico</option>
                                                                                        <option value="NY">New York</option>
                                                                                        <option value="NC">North Carolina</option>
                                                                                        <option value="ND">North Dakota</option>
                                                                                        <option value="OH">Ohio</option>
                                                                                        <option value="OK">Oklahoma</option>
                                                                                        <option value="OR">Oregon</option>
                                                                                        <option value="PA">Pennsylvania</option>
                                                                                        <option value="RI">Rhode Island</option>
                                                                                        <option value="SC">South Carolina</option>
                                                                                        <option value="SD">South Dakota</option>
                                                                                        <option value="TN">Tennessee</option>
                                                                                        <option value="TX">Texas</option>
                                                                                        <option value="UT">Utah</option>
                                                                                        <option value="VT">Vermont</option>
                                                                                        <option value="VA">Virginia</option>
                                                                                        <option value="WA">Washington</option>
                                                                                        <option value="WV">West Virginia</option>
                                                                                        <option value="WI">Wisconsin</option>
                                                                                        <option value="WY">Wyoming</option>
                                                                                    </form:select>
											<br>

											<div class="clearfix"></div>
											<label style="color: black">Zip:</label>
											<form:input path="addresses[0].zip" maxlength="5"
												placeholder='Zip' id="zip" style="vertical-align: middle;"
												type='Number' onkeypress="return isNumberKey(event)"
												onblur="currectNo(event)"
												oninput="javascript: if (this.value.length > this.maxLength) this.value = this.value.slice(0, this.maxLength);" />
											<br>

											<div class="clearfix"></div>
											<label style="color: black">Country:</label>
											<form:input path="addresses[0].country" maxlength="50"
												placeholder='Country' id="country"
												style="vertical-align: middle;" value="USA" readonly="true" />
											<br>

											<div class="clearfix"></div>

											<div style="width: 359px; padding-top: 15px;"
												class=" button clearfix">
												<span style="margin: 10px 0 0 0;"> <input
													type="button" value="Sign Up" id="setZoneButton"
													style="height: 43px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
													<a href="www.foodkonnekt.com"
													style="float: left; height: 43px;">Cancel</a>
											</div>

											<div style="margin: 0 auto; width: 434px; padding-top: 28px;"
												class="clearfix">You can change these setting later
												too from your admin console.</div>

										</div>
										<!--.adding-products-form-->
									</form:form>

								</div>
								<!--.adding-products-->

							</div>
							<!--.content-inner-container-->
						</div>
						<!--.row-->
					</div>
					<!--.outer-container-->
				</div>
				<!--#page-content-->

				<footer id="footer-container">
				<div class="footer-outer-container">
					<div class="footer-inner-container">
						<div class="row">
							<div class="sd-inner-footer">

								<div class="footer-left">
									<p>Powered by foodkonnekt | copyright@foodkonnekt.com</p>
								</div>
								<!--.footer-left-->

								<div class="footer-right">
									<img src="resources/img/foodkonnekt-logo.png" />
								</div>
								<!--.footer-right-->
							</div>
							<!--.sd-inner-footer-->
						</div>
						<!--.row-->
					</div>
					<!--.footer-inner-container-->
				</div>
				<!--.footer-outer-container--> </footer>
				<!--#footer-container-->

			</div>
			<!--.max-row-->
		</div>
		<!--.inner-container-->
	</div>
	<!--.foodkonnekt .dashboard-->
	</div>
	<!--#page-container-->



	<!--OPENS DIALOG BOX-->
	<script type="text/javascript">
          $(document).ready(function() {
              $("#setZoneButton").click(function() {
                
            	  var emailRegex = /^([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
            	  var phoneRegex = /\(?([0-9]{3})\)?([ .-]?)([0-9]{3})\2([0-9]{4})/;
                  var merchantName = $("#merchantName").val();
                  var phoneNumber = $("#phoneNumber").val();
                  var email = $("#email").val();
                  var address1 = $("#address1").val();
                  var city = $("#city").val();
                  var zip = $("#zip").val();
                  var state = $("#state").val();
                  var country = $("#country").val();
                  var confrimPassword = $("#confirmpass").val();
                  
                  var pass = $("#pass").val();
                  var avgDeliveryTime = $("#avgDeliveryTime").val();
                  if (merchantName == "") {
                      $("#merchantName").css('border-color', 'red');
                    $("#merchantName").focus();
                    $("#errorBox").html("Enter the Merchant/Organization Name");
                    return false;
                  } else if (phoneNumber == "") {
                      $("#merchantName").css('border-color', '');
                      $("#phoneNumber").css('border-color', 'red');
                    $("#phoneNumber").focus();
                    $("#errorBox").html("Enter the phone no");
                    return false;
                  } else if(phoneNumber.length >10 || phoneNumber.length<10){
                	  $("#phoneNumber").focus();
                      $("#errorBox").html("Mobile no should be 10 digits");
                  }else if(phoneNumber < 0 ){
                	  $("#phoneNumber").focus();
                      $("#errorBox").html("Enter valid phone no");
                  }else if (email == "") {
                      $("#email").css('border-color', 'red');
                      $("#phoneNumber").css('border-color', '');
                    $("#email").focus();
                    $("#errorBox").html("Enter the emailid");
                    return false;
                  } else if (!emailRegex.test(email)) {
              	    $("#guest-email").focus();
            	    $("#errorBox").html("Enter a valid email id");
            	    return false;
            	  } else if (pass == "") {
                    $("#pass").focus();
                    $("#email").css('border-color', '');
                    $("#pass").css('border-color', 'red');
                    $("#errorBox").html("Enter the password");
                    return false;
                  } else if (pass.length<8) {
                	  $("#pass").css('border-color', 'red');
                      $("#errorBox").html("Password should be a minimum of 8 characters");
                    return false;
                  } else if ($("#confirmpass").val() == "") {
                			    $("#confirmpass").focus();
                			    $("#confirmpass").css('border-color', 'red');
                			    $("#errorBox").html("enter the confirm password");
                			    return false;
                			}else if (pass!=confrimPassword) {
                				$("#confirmpass").focus();
                			    $("#confirmpass").css('border-color', 'red');
                					$("#errorBox").html("Password and confirm password should be same");
                				    return false;
                			}else if (address1=="") {
                  
                	  $("#address1").focus();
                	  $("#pass").css('border-color', '');
                	  $("#confirmpass").css('border-color', '');
                	  $("#address1").css('border-color', 'red');
                      $("#errorBox").html("Enter the address1");
                    return false;
                  }else if (city=="") {
                	  $("#city").focus();
                	  $("#address1").css('border-color', '');
                	  $("#city").css('border-color', 'red');
                      $("#errorBox").html("Enter the city");
                    return false;
                  }else if (state=="select") {
                	  $("#state").focus();
                	  $("#city").css('border-color', '');
                	  $("#state").css('border-color', 'red');
                      $("#errorBox").html("Select the state");
                    return false;
                  }else if (zip=="") {
                	  $("#zip").focus();
                	  $("#state").css('border-color', '');
                	  $("#zip").css('border-color', 'red');
                      $("#errorBox").html("Enter the zip");
                    return false;
                  }else if (country=="") {
                	  $("#country").focus();
                	  $("#zip").css('border-color', '');
                	  $("#country").css('border-color', 'red');
                      $("#errorBox").html("Enter the country");
                    return false;
                  }else if ($(merchantName != '' && phoneNumber != '' && email != '' && pass != '' && avgDeliveryTime != '')) {
                      $("#avgDeliveryTime").css('border-color', '');
                      $("#errorBox").html("");
                      $("#setDeliveryZoneForm").submit();
                  } 
                });
            
            $("#setZoneCancelButton").click(function() {
                window.location="deliveryZones"
            });
            
          });
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
    </script>


	



</body>
</html>