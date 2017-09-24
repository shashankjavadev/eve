<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
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
    <link href='https://fonts.googleapis.com/css?family=Open+Sans:400,300,300italic,400italic,600,600italic,700,700italic,800,800italic' rel='stylesheet' type='text/css'>
    <!--CALLING GOOGLE FONT OPEN SANS-->
    
    <!--CALLING FONT AWESOME-->
    <link rel="stylesheet" href="resources/css/font-awesome.css">
    <!--CALLING FONT AWESOME-->
    
    <!--CALLING FILTER OPTIONS-->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
    <script type="text/javascript" charset="utf8" src="https://ajax.aspnetcdn.com/ajax/jquery.dataTables/1.9.4/jquery.dataTables.min.js"></script>
    <script src="https://www.jqueryscript.net/demo/Powerful-jQuery-Data-Table-Column-Filter-Plugin-yadcf/jquery.dataTables.yadcf.js"></script>
    <!--OPENS DIALOG BOX-->
    <link rel="stylesheet" type="text/css" href="resources/css/dialog-box/component.css" />
    <!--OPENS DIALOG BOX-->
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
                                     <a href="adminHome" title="FoodKonnekt Dashboard" class="logo"><img src="resources/img/foodkonnekt-logo.png"></a>
                                </div><!--.logo-->
                                   <%@ include file="adminHeader.jsp"%>
                            </div><!--.row-->
                        </div><!--.inner-header-->
                    </header><!--#page-header-->
                    
                    <div id="page-content">
                        <div class="outer-container">
                            <div class="row">
                                <div class="content-inner-container">
                                         <%@ include file="leftMenu.jsp"%>                                
                                    <div class="right-content-container">
                                        <div class="right-content-inner-container">
                                        
                                            <div class="content-header">
                                                <div class="all-header-title">
                                                </div><!--.header-title-->
                                                <div class="content-header-dropdown">
                                                </div><!--.content-header-dropdown-->
                                            </div><!--.content-header-->
                                            
                                            <div class="merchant-page-data">
                                                <div class="merchant-actions-outbound">
                                                    <div class="merchat-coupons-container">
                                                        
                                                        <div class="coupons-navigation">
                                                            <ul>
                                                                <li><a href="onLineOrderLink">Location</a></li>
                                                                <li class="current-menu-item"><a href="deliveryZones">Delivery Zones</a></li>
                                                                <li><a href="vouchars">Coupons</a></li>
                                                                <li><a href="customers">Customers</a></li>
                                                            </ul>
                                                        </div><!--.coupons-navigation-->
                                                        <label id="errorBox" style="color: red;"></label>
                                                        <div class="delivery-zones-content-container">
                                                            <div class="clearfix"></div>
                                                            
                                                            <div class="add-coupon-container">
                                                                <div class="add-coupon-container-form">
                                                                     <form:form method="POST" action="saveDeliveryZone"  modelAttribute="Zone" id="setDeliveryZoneForm" autocomplete="off" name="formID">
                                                                       
                                                                        <label>Select Location:</label>
                                                                        <form:hidden path="id" id="zoneId"/>
                                                                        <form:hidden path="deliveryLineItemPosId" />
                                                                        <select>
                                                                            <option value="${id}">${address}</option>
                                                                        </select>
                                                                        <input type="hidden" name="admin" value="admin">
                                                                        <label>Zone Name:</label>
                                                                        <form:input path="zoneName"  maxlength="30" />
                                                                        
                                                                        <label>Zone Distance:</label>
                                                                        <form:input path="zoneDistance" maxlength="10" type='Number' onkeypress="return isNumberKey(event)"  onblur="currectNo(event)" min="0" />
                                                                        
                                                                        <label>Min Order Amount:</label>
                                                                        <div class="dollar-sign-left-coupon">
                                                                             <form:input path="minDollarAmount" maxlength="10" type='Number' onkeypress="return isNumberKey(event)"  onblur="currectNo(event)" min="0" />
                                                                        </div><!--.dollar-sign-left-->
                                                                        
                                                                        <label>Delivery Fee:</label>
                                                                        <div class="dollar-sign-left-coupon">
                                                                              <form:input path="deliveryFee" maxlength="10" type='Number' onkeypress="return isNumberKey(event)"  onblur="currectNo(event)" min="0" />
                                                                        </div><!--.dollar-sign-left-->
                                                                        
                                                                        <label>Avg Delivery Time:</label>
                                                                         <form:input path="avgDeliveryTime" maxlength="10" type='Number' onkeypress="return isNumberKey(event)"  onblur="currectNo(event)" min="1"/>
                                                                        <div class="clearfix"></div>
                                                                        
                                                                        <label>Status:</label>
                                                                                <form:select path="zoneStatus" id="zoneStatusId">
                                                                                   <form:option value="0" id='activeStatus'>Active</form:option>
                                                                                   <form:option value="1" id='inactiveStatus'>Inactive</form:option>
                                                                                </form:select><br>
                                                                        
                                                                        <label>Is Delivery Fee Taxable?:</label>
                                                                        
                                                                        <form:checkbox path="isDeliveryZoneTaxable" value="1" />
                                                                        
                                                                        <div class="clearfix"></div>
                                                                        
                                                                        <!-- <label>Status:</label>
                                                                        <select>
                                                                            <option>Choose</option>
                                                                            <option>Paid</option>
                                                                            <option>UnPaid</option>
                                                                            <option>Pending</option>
                                                                        </select>
                                                                        
                                                                        <div class="clearfix"></div> -->
                                                                        
                                                                        <div class="button left">
                                                                            <!-- <a href="#">Save</a> -->
                                                                             <input type="button" value="Update Zone" id="setZoneButton">&nbsp;&nbsp;<input type="button" value="Cancel" id="setZoneCancelButton">
                                                                        </div><!--.button-->
                                                                        
                                                                    </form:form>
                                                                </div><!--.add-coupon-container-form-->
                                                            </div><!--.add-coupon-container-->
                                                            
                                                        </div><!--.coupons-content-container-->
                                                    </div><!--.merchat-coupons-container-->
                                                </div><!--.merchant-actions-outbound-->
                                            </div><!--.merchant-page-data-->
                                            
                                        </div><!--.right-content-inner-container-->
                                    </div><!--.right-content-container-->
                                </div><!--.content-inner-container-->
                            </div><!--.row-->
                            
                        </div><!--.outer-container-->
                    </div><!--#page-content-->
                             <%@ include file="adminFooter.jsp"%>                    
                </div><!--.max-row-->
            </div><!--.inner-container-->
        </div><!--.foodkonnekt .dashboard-->
    </div><!--#page-container-->
    
    <script>
        var polyfilter_scriptpath = '/js/';
    </script>
    <script type="text/javascript">
          $(document).ready(function() {
            var table = $('#example').DataTable();
            
            var zoneStatus="${ZoneStatus}";
            
            if(zoneStatus==0){
              $("#activeStatus").attr('selected', 'selected');
            }else{
              $("#inactiveStatus").attr('selected', 'selected');
            }
          });
          $(document).ready(function() {
            $('input[type=search]').each(function() {
              $(this).attr('placeholder', "Search zones");
            });
            
            
          });
        </script>
    <script type="text/javascript">
    var status=0;
          $(document).ready(function() {
        	  
        	  
        	  $("#zoneName").bind('blur', function () {
                  var zoneName=$(this).val();
                  var zoneId = $("#zoneId").val();
                  
                  $.ajax({
                   url : "checkDeliveryZoneName?diliveryZoneName=" + zoneName + "&diliveryZoneId=" + zoneId,
                   type : "GET",
                   contentType : "application/json; charset=utf-8",
                   success : function(statusValue) {
                     if (statusValue=="true") {
                       
                       $("#zoneName").css('border-color', 'red');
                       $("#zoneName").focus();
                       $("#errorBox").html("Delivery zone name '"+zoneName+"' already exists");
                       status=1;
                     }else{
                       $("#errorBox").html("");
                       $("#zoneName").css('border-color', '');
                       status=0;
                     }
                   },
                   error : function() {
                     alert("error");
                   }
                 })            
               });
        	  
        	  $("#setZoneButton").click(function() {
        		  if(status==1){
              		$("#zoneName").css('border-color', 'red');
                      $("#zoneName").focus();
              		$("#errorBox").html("Delivery zone name already exists");
              		return false;
              	}else{
              		$("#errorBox").html("");
              		$("#zoneName").css('border-color', '');
              		
              	}
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
                    $("#errorBox").html("Enter the minimun order amount");
                    return false;
                  } else if (deliveryFee == "") {
                	  $("#minDollarAmount").css('border-color', '');
                	  $("#deliveryFee").css('border-color', 'red');
                    $("#deliveryFee").focus();
                    $("#errorBox").html("Enter the delivery fee");
                    return false;
                  } else if (avgDeliveryTime == "") {
                	  $("#deliveryFee").css('border-color', '');
                	  $("#avgDeliveryTime").css('border-color', 'red');
                    $("#avgDeliveryTime").focus();
                    $("#errorBox").html("Enter Average Delivery Time");
                    return false;
                  }else if (avgDeliveryTime <=0) {
                      $("#avgDeliveryTime").focus();
                      $("#deliveryFee").css('border-color', '');
                  	  $("#avgDeliveryTime").css('border-color', 'red');
                      $("#errorBox").html("Average Delivery Time Should be greater than 0");
                      return false;
                    }
                  else if ($(zoneName != '' && zoneDistance != '' && minDollarAmount != '' && deliveryFee != '' && avgDeliveryTime != '')) {
                	  $("#avgDeliveryTime").css('border-color', '');  
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
        
        <!--OPENS DIALOG BOX-->
    <script src="resources/js/dialog-box/classie.js"></script>
    <script src="resources/js/dialog-box/modalEffects.js"></script>
    <script>
        var polyfilter_scriptpath = '/js/';
    </script>
    <!--OPENS DIALOG BOX-->
	<!--CALLING TABS-->
  </body>
</html>
