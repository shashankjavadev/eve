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
   <link rel="stylesheet" href="resources/css/popupCSS.css">
   <script>
  $(document).ready(function() {
      var saveStatus="${saveStatus}";
      if(saveStatus!=""){ 
          jQuery(function(){
               jQuery('#confirmModal_ex2').click();
            });
        } 
      });
  function setConvennienceFee(){
    window.location.href = "setConvenienceFee";
  }
  </script>
  <body>
   <div class="exampleLive">
    <button id="confirmModal_ex2" style="display: none" class="btn btn-primary" data-confirmmodal-bind="#confirm_content" data-topoffset="0" data-top="10%">Example</button>
</div>
            
<div id="confirm_content" style="display:none">
    <div class="confirmModal_content sd-popup-content">
    <img src="resources/img/logo.png" class="sd-popup-logo">
   <h3>Your pickup time has been successfully saved</h3>
    </div>
    <div class="confirmModal_footer">
        <button type="button" class="btn btn-primary uploadedBtn" data-confirmmodal-but="ok" onclick="setConvennienceFee()">Continue</button>
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
                                    <a href="index.html" title="FoodKonnekt Dashboard" class="logo"><!-- <img src="resources/img/logo.jpg"> </a>-->
                             
                                
                     <img src="data:image/jpeg;base64,${merchant.merchantLogo}" onerror="this.src='resources/img/foodkonnekt-logo.png'" width="250" height="150"></a>
                    </div>
                            </div><!--.row-->
                        </div><!--.inner-header-->
                    </header><!--#page-header-->
                    
                    <div id="page-content clearfix">
                        <div class="outer-container">
                        <div style="margin:0 auto;padding-top: 52px;" class="clearfix clearfix1"><span style="margin:10px 0 0 0;">
                        <b><font size="4">SETUP PICKUP WAIT TIME</font></b></span>
                        <label id="errorBox" style="color: red;"></label>
                        </div>
                        
                            <div class="row">
                                <div class="content-inner-container">
                                <form:form method="POST" action="savePickupTime" modelAttribute="PickUpTime" id="setPickTimeForm" autocomplete="off" >                              
                                <div class="adding-products-form" style="width:800px; margin:0 auto;">
                                <p></p><p></p><p></p><p></p>
                                    <div class="clearfix"></div>                                    
                                    <label>Select Location:</label>
                                    <form:select path="locationId">
                                          <form:option value="${id}">${address}</form:option>
                                    </form:select>
                                    <br>                                    
                                    <div class="clearfix"></div>
                                    <label>Pick Up Time:</label>
                                    <form:input path="pickUpTime" maxlength="20" id="pickUpTime" style="vertical-align: middle;" type='Number' onkeypress="return isNumberKey(event)"  onblur="currectNo(event)"/>
                                    &nbsp;Minutes
                                    <br>
                                    <div class="clearfix"></div>
                                    
                                    <div style="width: 359px; padding-top: 15px;" class=" button clearfix"><span style="margin:10px 0 0 0;">
                                    <input type="button" value="Save" id="setPickupTimeButton" style="height: 43px;">
                                   <a href="setConvenienceFee" style="float: left;height: 43px;">Skip</a>
                                    </div>

                                    <div style="margin:0 auto;width: 434px;padding-top: 28px;" class="clearfix">
                                    You can change these setting later too from your admin console.
                                    </div>
                               </div><!--.adding-products-form-->
                               </form:form>
                               
                            </div><!--.adding-products-->   
                                    
                        </div><!--.content-inner-container-->
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
                                            <img src="resources/img/foodkonnekt-logo.png" />                                        </div><!--.footer-right-->
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
            $("#setPickupTimeButton").click(function() {
              var pickUpTime = $("#pickUpTime").val();
             
              if (pickUpTime == "") {
                $("#pickUpTime").focus();
                $("#errorBox").html("enter the pickup time");
                return false;
              } else if ($(pickUpTime != '')) {
                    $("#setPickTimeForm").submit();
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
    
    
    

  </body>
</html>