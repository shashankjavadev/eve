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
   
   .min
   {
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
      });
  function setPickUpTime(){
    window.location.href = "setPickupTime";
  }
  </script>
  <body>
 <div class="exampleLive">
    <button id="confirmModal_ex2" style="display: none" class="btn btn-primary" data-confirmmodal-bind="#confirm_content" data-topoffset="0" data-top="10%">Example</button>
</div>
            
<div id="confirm_content" style="display:none">
    <div class="confirmModal_content sd-popup-content">
    <img src="resources/img/logo.png" class="sd-popup-logo">
    <h3>Your delivery zone has been successfully saved</h3>
    <h3>Do you want to add another Delivery zone?</h3>
    </div>
    <div class="confirmModal_footer">
        <button type="button" class="btn btn-primary uploadedBtn" data-confirmmodal-but="ok" >Yes</button>
        <button type="button" class="btn btn-primary uploadedBtn" data-confirmmodal-but="no" onclick="setPickUpTime()" >No</button>
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
                        <b><font size="4">SET UP DELIVERY ZONES</font></b></span>
                        <label id="errorBox" style="color: red;"></label>
                        </div>
                        
                            <div class="row">
                                <div class="content-inner-container">   
                       <form:form method="POST" action="saveDeliveryZone" modelAttribute="Zone" id="setDeliveryZoneForm" autocomplete="off" name="formID" >                             
                                <div class="adding-products-form" style="width:800px; margin:0 auto;">
                                <p></p><p></p><p></p><p></p>
                                    <div class="clearfix"></div>                                    
                                    <label style="color:black">Select Location:</label>
                                    <select>
                                       <option value="${id}">${address}</option>
                                    </select>
                                    <br>
                                    
                                    <div class="clearfix"></div>
                                    <label style="color:black">Zone Name:</label>
                                       <form:input path="zoneName" maxlength="30" id="zoneName"/>
                                    <br>
                                
                                    <div class="clearfix"></div>
                                    <label style="color:black">Zone Distance:</label>
                                    <form:input path="zoneDistance" maxlength="10"  id="zoneDistance" style="vertical-align: middle;" type='Number' onkeypress="return isNumberKey(event)"  onblur="currectNo(event)"/>&nbsp;miles
                                    <br>
                                    <div class="clearfix"></div>
                                    <label style="color:black">Min Dollar Amount:</label>
                                    <price>$</price><form:input path="minDollarAmount" maxlength="10"   id="minDollarAmount" type='Number' onkeypress="return isNumberKey(event)"  onblur="currectNo(event)" class="min" style="width:260px" />
                                    
                                    <br>
                                    <div class="clearfix"></div>
                                    <label style="color:black">Delivery fees:</label>
                                    <price>$</price><form:input path="deliveryFee" maxlength="10"  id="deliveryFee" type='Number' onkeypress="return isNumberKey(event)"  onblur="currectNo(event)" style="width:260px" />
                                    <br>
                                    <div class="clearfix"></div>                                    
                                    <label style="color:black">Is Delivery fees taxable?:</label>
                                    <form:checkbox path="isDeliveryZoneTaxable" value="1"/>
                                    <br>
                                    <div class="clearfix"></div>
                                    <label style="color:black">Avg Delivery Times:</label>
                                    <form:input path="avgDeliveryTime" maxlength="10" style="vertical-align: middle;"  id="avgDeliveryTime" type='Number' onkeypress="return isNumberKey(event)"  onblur="currectNo(event)" />
                                    <span >minutes</span>
                                    <br>
                                    <label style="color:black">Status:</label>
                                                                                <form:select path="zoneStatus" id="categoryStatusId">
                                                                                   <form:option value="0">Active</form:option>
                                                                                   <form:option value="1">Inactive</form:option>
                                                                                </form:select><br>
                                    <div class="clearfix"></div>
                                    
                                    <div style="width: 359px; padding-top: 15px;" class=" button clearfix"><span style="margin:10px 0 0 0;">
                                    <input type="button" value="Save" id="setZoneButton" style="height: 43px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                     <a href="setPickupTime" style="float: left;height: 43px;">Skip</a>
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
              $("#setZoneButton").click(function() {
                
                  var zoneName = $("#zoneName").val();
                  var zoneDistance = $("#zoneDistance").val();
                  var minDollarAmount = $("#minDollarAmount").val();
                  var deliveryFee = $("#deliveryFee").val();
                  var avgDeliveryTime = $("#avgDeliveryTime").val();
                  if (zoneName == "") {
                      $("#zoneName").css('border-color', 'red');
                    $("#zoneName").focus();
                    $("#errorBox").html("Enter the zone Name");
                    return false;
                  } else if (zoneDistance == "") {
                      $("#zoneName").css('border-color', '');
                      $("#zoneDistance").css('border-color', 'red');
                    $("#zoneDistance").focus();
                    $("#errorBox").html("Enter the zone distance");
                    return false;
                  } else if (minDollarAmount == "") {
                      $("#minDollarAmount").css('border-color', 'red');
                      $("#zoneDistance").css('border-color', '');
                    $("#minDollarAmount").focus();
                    $("#errorBox").html("Enter the minimun dollar amount");
                    return false;
                  } else if (deliveryFee == "") {
                    $("#deliveryFee").focus();
                    $("#minDollarAmount").css('border-color', '');
                  $("#deliveryFee").css('border-color', 'red');
                    $("#errorBox").html("Enter the delivery fee");
                    return false;
                  } else if (avgDeliveryTime == "") {
                    $("#avgDeliveryTime").focus();
                    $("#deliveryFee").css('border-color', '');
                      $("#avgDeliveryTime").css('border-color', 'red');
                    $("#errorBox").html("Enter Average Delivery Time");
                    return false;
                  } else if ($(zoneName != '' && zoneDistance != '' && minDollarAmount != '' && deliveryFee != '' && avgDeliveryTime != '')) {
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
    
    
    <!-- 
    
    ************ un usable code ********
    
    
    <script type="text/javascript">
          $(document).ready(function() {
            $("#addVoucharButton").click(function() {
              var voucharCode = $("#voucharCode").val();
              var discount = $("#delVaalid").val();
              if (voucharCode == "") {
                $("#voucharCode").focus();
                $("#errorMessage").html("enter the vouchar code");
                return false;
              } else if (discount == "") {
                $("#delVaalid").focus();
                $("#errorMessage").html("enter the discount");
                return false;
              }
             var valid = $('#purpose').val();
             $("#errorMessage").html("");
             if(valid!=""){
              if(valid==0){
                  var selectedText = document.getElementById('date').value;
                  var selectedDate = new Date(selectedText);
                  var now = new Date();
                  
                  var dStartHour = document.getElementById('dStartHour').value;
                  var dStartMinute = document.getElementById('dStartMinute').value;
                  var dEndHour = document.getElementById('dEndHour').value;
                  var dEndMinute = document.getElementById('dEndMinute').value;
                  var check=0;
                  if(selectedDate=='Invalid Date' ){
                    $("#errorMessage").html("Date cann't be empty");
                    return false;
                  }
                  if(selectedDate!="Invalid Date"){
                      if (selectedDate < now) {
                       $("#errorMessage").html("Date must be in the future");
                       return false;
                      } else if(parseInt(dStartHour) > parseInt(dEndHour)){
                        $("#errorMessage").html("End time must be greater than start time.");
                        check=1;
                        return false;
                      }else{
                        if(check!=0){
                        if(parseInt(dStartMinute) >= parseInt(dEndMinute)){
                        $("#errorMessage").html("End time must be greater than start time.");
                        check=0;
                        return false;
                      }
                     }
                    } 
                  }
              }
              if(valid==1){
                var startDateText = document.getElementById('startDate').value;
                var startDate = new Date(startDateText);
                
                var endDateText = document.getElementById('endDate').value;
                var endDate = new Date(endDateText);
                var now = new Date();
                
                var pStartHour = document.getElementById('pStartHour').value;
                var pStartMinute = document.getElementById('pStartMinute').value;
                var pEndHour = document.getElementById('pEndHour').value;
                var pEndMinute = document.getElementById('pEndMinute').value;
                var check=0;
              if(startDate=="Invalid Date"){
                  $("#errorMessage").html("Start date cann't be empty");
                  return false;
                }
              if(endDate=="Invalid Date"){
                  $("#errorMessage").html("End date cann't be empty");
                  return false;
                }
               if(startDate!="Invalid Date"){
                   if (startDate < now) {
                         $("#errorMessage").html("Start date must be in the future");
                         return false;
                   }
               }
               if(endDate!="Invalid Date"){
                   if (endDateText < now) {
                        $("#errorMessage").html("End date must be in the future");
                        return false;
                   }
                   if(endDate < startDate){
                     $("#errorMessage").html("To date/time must be greater than From date/time.");
                     return false;
                   }
                   else if(parseInt(pStartHour) > parseInt(pEndHour)){
                     $("#errorMessage").html("End time must be greater than start time.");
                     check=1;
                     return false;
                   }else{
                     if(check!=0){
                     if(parseInt(pStartMinute) >= parseInt(pEndMinute)){
                     $("#errorMessage").html("End time must be greater than start time.");
                     check=0;
                     return false;
                   }
                  }
                 } 
               }
            }
          if(valid==2){
            var rStartHour = document.getElementById('rStartHour').value;
            var rStartMinute = document.getElementById('rStartMinute').value;
            var rEndHour = document.getElementById('rEndHour').value;
            var rEndMinute = document.getElementById('rEndMinute').value;
            var check=0;
                if(parseInt(rStartHour) > parseInt(rEndHour)){
                  $("#errorMessage").html("End time must be greater than start time.");
                  check=1;
                  return false;
                }else{
                  if(check!=0){
                  if(parseInt(rStartMinute) >= parseInt(rEndMinute)){
                  $("#errorMessage").html("End time must be greater than start time.");
                  check=0;
                  return false;
                }
               }
              }    
            }
          }    
       $("#saveVoucharForm").submit();
     });
  });
          
    
    function isNumberKey(evt)
      {
        var charCode = (evt.which) ? evt.which : evt.keyCode;
        if (charCode != 46 && charCode > 31  && (charCode < 48 || charCode > 57))
        return false;

        return true;
    }
    
    
    
    
    </script>

 -->



  </body>
</html>