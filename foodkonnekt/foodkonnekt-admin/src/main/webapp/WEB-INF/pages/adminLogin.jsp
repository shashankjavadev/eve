<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
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
    <link href='https://fonts.googleapis.com/css?family=Open+Sans:400,300,300italic,400italic,600,600italic,700,700italic,800,800italic' rel='stylesheet' type='text/css'>
    <!--CALLING GOOGLE FONT OPEN SANS-->
    
    <!--CALLING FONT AWESOME-->
    <link rel="stylesheet" href="resources/css/font-awesome.css">
    <!--CALLING FONT AWESOME-->
    
    <!--CALLING FILTER OPTIONS-->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
       <link type="text/css" rel="stylesheet" href="resources/css/popModal.css">
    <script src="resources/js/popModal.js"></script>

  </head>
  <style>
  .logo{
    width: 1178px;
   }
   
   footer#footer-container {
    position: absolute;
    bottom: 0;
    width: 100%;
}
   
   .clearfix:after {
   content: " ";
   visibility: hidden;
   display: block;
   height: 0;
   clear: both;
   }
   .clearfix1{
   width: 30%! important;
   }
  </style>
  <div class="exampleLive">
    <button style="display:none;" id="uploadInventoryPopUp" class="btn btn-primary" data-confirmmodal-bind="#confirm_content_uploadinventory" data-topoffset="0" data-top="30%" >Example</button>
</div>
<div id="confirm_content_uploadinventory" style="display:none">
    <div class="confirmModal_content sd-popup-content">
    <img src="resources/img/logo.png" class="sd-popup-logo">
       
    </div>
    
    <form method="POST" action="forgotAdminPassword" autocomplete="off">
																			
																			<div class="uploadinventory-div" style=" margin-left: 90px;margin-top: -20px;">
																				
																				
																					<label>Email Id:</label> <input type="email" name=emailid > <br>
																					<div class="clearfix"></div>
																					
																			</div>
																			
    
    <div class="confirmModal_footer">
       <button type="Submit" id="updateItemButton" class="btn btn-primary"  >Reset</button> <button type="button" class="btn btn-primary" onclick="cancelButton()">Cancel</button>
    </div>
    </form>
    
</div>
  
   <link rel="stylesheet" href="resources/css/popupCSS.css">
   <script>
   
   function cancelButton(){
		  $(".confirmModal").hide();
		}
   $(document).ready(function() {
	   $("#forgotButton").on("click",  function(){
   		
   		jQuery('#uploadInventoryPopUp').click();
   	});
	   
  $("#loginButton").click(function() {
	  
	  	  var emailRegex = /^([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
	  	  var email = $("#loginEmail").val();
	  	  var paasword = $("#loginPassword").val();
	  	 
	  	  var merchantId=0;
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
	  		$("#setAdminLoginForm").submit();
	  	  }
	  });
   });
  </script>
  <body>
   <div class="exampleLive">
    <button id="confirmModal_ex2" style="display: none" class="btn btn-primary" data-confirmmodal-bind="#confirm_content" data-topoffset="0" data-top="10%">Example</button>
</div>
            

    <div id="page-container">
		<div class="foodkonnekt merchant">
			<div class="inner-container">
				<div class="max-row">
					
					<header id="page-header">
						<div class="inner-header">
							<div class="row">
								
									
	                        <div class="logo">
									<a href="index.html" title="FoodKonnekt Dashboard" class="logo"><!-- <img src="resources/img/logo.jpg"> </a>-->
							 
								
                     <img src="data:image/jpeg;base64,${merchant.merchantLogo}" onerror="this.src='resources/img/foodkonnekt-logo.png'" width="250" height="150"></a>
                	</div>
							</div><!--.row-->
						</div><!--.inner-header-->
					</header><!--#page-header-->
					
					<div id="page-content clearfix">
						<div class="outer-container">
						<div style="margin:0 auto;padding-top: 52px;" class="clearfix clearfix1"><span style="margin:10px 0 0 0;">
						<b><font size="4">FoodKonnekt Admin Login</font></b></span>
						<label id="errorBox" style="color: red;"></label>
						</div>
						<%-- <form action="LoginByCustomer"> --%>
						<form:form method="POST" action="LoginByAdmin" modelAttribute="Customer" id="setAdminLoginForm" autocomplete="off" >
							<div class="row">
								<div class="content-inner-container">
                                                               								
								<div class="adding-products-form" style="width:800px; margin:0 auto;">
								<p></p><p></p><p></p><p></p>
									<div class="clearfix"></div>									
									<form:hidden  path="vendorId" value="0"/>
									<div class="clearfix"></div>
									<label>Email Id</label>
										<form:input type="text" id="loginEmail" placeholder="Email id" path="emailId"/>
									<br>
									<label>Password</label>
										<form:password  id="loginPassword" placeholder="Password" path="password"/>
									<br>
									
									<div class="clearfix"></div>
									<div style="width: 400px; padding-top: 15px;" class=" button clearfix"><span style="margin:10px 0 0 0;">
									<input type="button" value="Login" id="loginButton" style="height: 43px;">
									<input type="button" value="Forgot Password" id="forgotButton" style="height: 43px;width: 200px;">
                                    
									</div>
									

									
							   </div><!--.adding-products-form-->
                              
                               
							</div><!--.adding-products-->	
									
						</div><!--.content-inner-container-->
						</form:form>
						<%-- </form> --%>
					</div><!--.row-->
					
			</div><!--.outer-container-->
		</div><!--#page-content-->
					
					<footer id="footer-container">
						<div class="footer-outer-container">
							<div class="footer-inner-container">
								<div class="row">
									<div class="sd-inner-footer">
									
										<div class="footer-left">
											<p>Powered by foodkonnekt | copyright@foodkonnekt.com</p>
										</div><!--.footer-left-->
										
										<div class="footer-right">
											<img src="resources/img/foodkonnekt-logo.png" />										</div><!--.footer-right-->
									</div><!--.sd-inner-footer-->
								</div><!--.row-->
							</div><!--.footer-inner-container-->
						</div><!--.footer-outer-container-->
					</footer>
					<!--#footer-container-->
					
				</div><!--.max-row-->
			</div><!--.inner-container-->
		</div><!--.foodkonnekt .dashboard-->
	</div><!--#page-container-->
	
	<!--OPENS DIALOG BOX-->
      <script type="text/javascript">
          $(document).ready(function() {
            $("#setConvenienceFeeButton").click(function() {
              var convenienceFee = $("#convenienceFee").val();
             
              if (convenienceFee == "") {
                $("#convenienceFee").focus();
                $("#errorBox").html("enter the convenience fee");
                return false;
              } else if ($(convenienceFee!= '')) {
                    $("#setConvenienceFeeForm").submit();
              } 
            });
          });
          function isNumberKey(evt)
          {
        	  var target = evt.target || evt.srcElement; // IE

        	    var id = target.id;
        	    var data=document.getElementById(id).value;
        	  
        	  
        	  if(evt.which == 8 || evt.which == 0){
                  return true;
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
	
<!-- 	 <script type="text/javascript">
      $(document).ready(function() {
          $("#setConvenienceFeeButton").click(function () {
            $("#setConvenienceFeeForm").submit();
                    
          });
      });
  </script>
 -->
  </body>
</html>