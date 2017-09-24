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
    <script>
    $(document).ready(function(){
      $('#example').dataTable().yadcf([
            {column_number : 3, data: ["Inactive", "Active"], filter_default_label: "Select Status"}]);
    });
    </script>
    <!--CALLING FILTER OPTIONS-->
    
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
                                                                <li><a href="deliveryZones">Delivery Zones</a></li>
                                                                <li class="current-menu-item"><a href="vouchars">Coupons</a></li>
                                                                <li><a href="customers">Customers</a></li>
                                                            </ul>
                                                        </div><!--.coupons-navigation-->
                                                        <div class="coupons-content-container">
                                                             <label id="errorMessage" style="color: red;"></label>
                                                            <div class="clearfix"></div>
                                                            
                                                            <div class="add-coupon-container">
                                                                <div class="add-coupon-container-form">
                                                               
                                                                    <form:form method="POST" modelAttribute="Vouchar" action="addVouchar" autocomplete="off" id="saveVoucharForm">
                                                                        <label>Coupon Code:</label>
                                                                       <form:input path="voucharCode" id="voucharCode" maxlength="30" />
                                                                        
                                                                        <label>Type:</label>
                                                                        <form:select path="type">
                                                                            <form:option value="Amount">Amount</form:option>
                                                                        </form:select>
                                                                        
                                                                        <label>Discount:</label>
                                                                        <div class="dollar-sign-left-coupon">
                                                                            <form:input path="discount" id="discount" maxlength="5" type='Number' onkeypress="return isNumberKey(event)"  onblur="currectNo(event)" />
                                                                        </div><!--.dollar-sign-left-->
                                                                        
                                                                        <label>Valid:</label>
                                                                        <form:select path="validity" id="purpose">
                                                                            <form:option value="">Choose</form:option>
                                                                            <form:option value="0">Fixed</form:option>
                                                                            <form:option value="1">Period</form:option>
                                                                            <form:option value="2">Recurring</form:option>
                                                                        </form:select>
                                                                        <div id="fixed">
                                                                            <label>Date:</label>
                                                                           <form:input path="strDate" id="date" type="date" />
                                                                            
                                                                            <div class="clearfix"></div>
                                                                            
                                                                            <label>Time From:</label>
                                                                            <select class="fifty-percent hour" name="startTime" id="dStartHour"><option value="0">00</option><option value="1">01</option><option value="2">02</option><option value="3">03</option><option value="4">04</option><option value="5">05</option><option value="6">06</option><option value="7">07</option><option value="8">08</option><option value="9">09</option><option value="10">10</option><option value="11">11</option><option value="12">12</option><option value="13">13</option><option value="14">14</option><option value="15">15</option><option value="16">16</option><option value="17">17</option><option value="18">18</option><option value="19">19</option><option value="20">20</option><option value="21">21</option><option value="22">22</option><option value="23">23</option></select>
                                                                                <select class="fifty-percent minute " name="startTime" id="dStartMinute"><option value="0">00</option><option value="05">05</option><option value="10">10</option><option value="15">15</option><option value="20">20</option><option value="25">25</option><option value="30">30</option><option value="35">35</option><option value="40">40</option><option value="45">45</option><option value="50">50</option><option value="55">55</option>
                                                                                </select>
                                                                                <div class="clearfix"></div>
                                                                            
                                                                            <label>Time To:</label>
                                                                            <select class="fifty-percent hour" name="endTime" id="dEndHour"><option value="0">00</option><option value="1">01</option><option value="2">02</option><option value="3">03</option><option value="4">04</option><option value="5">05</option><option value="6">06</option><option value="7">07</option><option value="8">08</option><option value="9">09</option><option value="10">10</option><option value="11">11</option><option value="12">12</option><option value="13">13</option><option value="14">14</option><option value="15">15</option><option value="16">16</option><option value="17">17</option><option value="18">18</option><option value="19">19</option><option value="20">20</option><option value="21">21</option><option value="22">22</option><option value="23">23</option></select>
                                                                                <select class="fifty-percent minute " name="endTime" id="dEndMinute"><option value="0">00</option><option value="05">05</option><option value="10">10</option><option value="15">15</option><option value="20">20</option><option value="25">25</option><option value="30">30</option><option value="35">35</option><option value="40">40</option><option value="45">45</option><option value="50">50</option><option value="55">55</option></select>
                                                                                <div class="clearfix"></div>
                                                                            
                                                                        </div><!--#fixed-->
                                                                        <div id="period">
                                                                            <div class="from-date-time-add-coupon-period">
                                                                                <label>From Date/Time:</label>
                                                                                <input type="date" data-date-inline-picker="true" name ="fromD" id="startDate">
                                                                                <select class="hour" name="startTime" id="pStartHour"><option value="0">00</option><option value="1">01</option><option value="2">02</option><option value="3">03</option><option value="4">04</option><option value="5">05</option><option value="6">06</option><option value="7">07</option><option value="8">08</option><option value="9">09</option><option value="10">10</option><option value="11">11</option><option value="12">12</option><option value="13">13</option><option value="14">14</option><option value="15">15</option><option value="16">16</option><option value="17">17</option><option value="18">18</option><option value="19">19</option><option value="20">20</option><option value="21">21</option><option value="22">22</option><option value="23">23</option></select>
                                                                                
                                                                                
                                                                                <select class="minute " name="startTime" id="pStartMinute"><option value="0">00</option><option value="05">05</option><option value="10">10</option><option value="15">15</option><option value="20">20</option><option value="25">25</option><option value="30">30</option><option value="35">35</option><option value="40">40</option><option value="45">45</option><option value="50">50</option><option value="55">55</option></select>
                                                                            </div><!--.from-date-time-add-coupon-period-->
                                                                            <div class="from-date-time-add-coupon-period">
                                                                                <label>To Date/Time:</label>
                                                                                <input type="date" data-date-inline-picker="true" name ="endD" id="endDate">
                                                                                <select class="hour" name="endTime" id="pEndHour"><option value="0">00</option><option value="1">01</option><option value="2">02</option><option value="3">03</option><option value="4">04</option><option value="5">05</option><option value="6">06</option><option value="7">07</option><option value="8">08</option><option value="9">09</option><option value="10">10</option><option value="11">11</option><option value="12">12</option><option value="13">13</option><option value="14">14</option><option value="15">15</option><option value="16">16</option><option value="17">17</option><option value="18">18</option><option value="19">19</option><option value="20">20</option><option value="21">21</option><option value="22">22</option><option value="23">23</option></select>
                                                                                
                                                                                
                                                                                <select class="minute" name="endTime" id="pEndMinute"><option value="0">00</option><option value="05">05</option><option value="10">10</option><option value="15">15</option><option value="20">20</option><option value="25">25</option><option value="30">30</option><option value="35">35</option><option value="40">40</option><option value="45">45</option><option value="50">50</option><option value="55">55</option></select>                                                                     
                                                                            </div><!--.from-date-time-add-coupon-period-->
                                                                        </div><!--#period-->
                                                                        <div id="recurring">
                                                                            <label for="recurring">Every Day:</label>
                                                                            <form:select path="recurringR">
                                                                                    <form:option value="Monday">Monday</form:option>
                                                                                    <form:option value="Tuesday">Tuesday</form:option>
                                                                                    <form:option value="Wednesday">Wednesday</form:option>
                                                                                    <form:option value="Thursday">Thursday</form:option>
                                                                                    <form:option value="Friday">Friday</form:option>
                                                                                    <form:option value="Saturday">Saturday</form:option>
                                                                                    <form:option value="Sunday">Sunday</form:option>
                                                                           </form:select>
                                                                            
                                                                            <div class="clearfix"></div>
                                                                            
                                                                            <label>Start Time:</label>
                                                                            <select class="fifty-percent hour" name="startTime" id="rStartHour"><option value="0">00</option><option value="1">01</option><option value="2">02</option><option value="3">03</option><option value="4">04</option><option value="5">05</option><option value="6">06</option><option value="7">07</option><option value="8">08</option><option value="9">09</option><option value="10">10</option><option value="11">11</option><option value="12">12</option><option value="13">13</option><option value="14">14</option><option value="15">15</option><option value="16">16</option><option value="17">17</option><option value="18">18</option><option value="19">19</option><option value="20">20</option><option value="21">21</option><option value="22">22</option><option value="23">23</option></select>
                                                                                <select class="fifty-percent minute" name="startTime" id="rStartMinute"><option value="0">00</option><option value="05">05</option><option value="10">10</option><option value="15">15</option><option value="20">20</option><option value="25">25</option><option value="30">30</option><option value="35">35</option><option value="40">40</option><option value="45">45</option><option value="50">50</option><option value="55">55</option></select>
                                                                                <div class="clearfix"></div>
                                                                                
                                                                                <label>End Time:</label>
                                                                                <select class="fifty-percent hour" name="endTime" id="rEndHour"><option value="0">00</option><option value="1">01</option><option value="2">02</option><option value="3">03</option><option value="4">04</option><option value="5">05</option><option value="6">06</option><option value="7">07</option><option value="8">08</option><option value="9">09</option><option value="10">10</option><option value="11">11</option><option value="12">12</option><option value="13">13</option><option value="14">14</option><option value="15">15</option><option value="16">16</option><option value="17">17</option><option value="18">18</option><option value="19">19</option><option value="20">20</option><option value="21">21</option><option value="22">22</option><option value="23">23</option></select>
                                                                                <select class="fifty-percent minute" name="endTime" id="rEndMinute"><option value="0">00</option><option value="05">05</option><option value="10">10</option><option value="15">15</option><option value="20">20</option><option value="25">25</option><option value="30">30</option><option value="35">35</option><option value="40">40</option><option value="45">45</option><option value="50">50</option><option value="55">55</option></select>
                                                                                <div class="clearfix"></div>
                                                                        </div>
                                                                        
                                                                        <div class="button" style="text-align: left; margin: left;">
                                                                            <a href="vouchars">Cancel</a>
                                                                            <a href="#" id="addVoucharButton">Save</a>
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
                    <!--#footer-container-->
                    
                </div><!--.max-row-->
            </div><!--.inner-container-->
        </div><!--.foodkonnekt .dashboard-->
    </div><!--#page-container-->
    
    <script>
        var polyfilter_scriptpath = '/js/';
    </script>
    <!--OPENS DIALOG BOX-->

    <!--FOR HIDE AND DISPLAY ON DROPDOWN FORM FIELD SELECTION-->
    <style>
        #fixed {
          display:none;
        }
        #period {
          display:none;
        }
        #recurring {
          display:none;
        }
    </style>
    <script>
        $('#purpose').on('change', function () {
            $("#errorMessage").html("");
            $("#purpose").css('border-color', '');
            if(this.value === "0"){
                $("#fixed").show();
            } else {
                $("#fixed").hide();
            }
            if(this.value === "1"){
                $("#period").show();
            } else {
                $("#period").hide();
            }
            if(this.value === "2"){
                $("#recurring").show();
            } else {
                $("#recurring").hide();
            }
        });
    </script>
    <!-- <script>
        $('#purpose').on('change', function () {
            if(this.value === "1"){
                $("#period").show();
            } else {
                $("#period").hide();
            }
        });
    </script>
    <script>
        $('#purpose').on('change', function () {
            if(this.value === "2"){
                $("#recurring").show();
            } else {
                $("#recurring").hide();
            }
        });
    </script> -->
     <script type="text/javascript">
          $(document).ready(function() {
              
              $("#voucharCode").bind('blur', function () {
                  var voucharCode=$(this).val();
                  
                  $.ajax({
                   url : "checkCouponName?couponCode=" + voucharCode,
                   type : "GET",
                   contentType : "application/json; charset=utf-8",
                   success : function(statusValue) {
                     if (statusValue=="true") {
                       
                       $("#voucharCode").css('border-color', 'red');
                       $("#voucharCode").focus();
                       $("#errorMessage").html("Vouchar name '"+voucharCode+"' already exists");
                       status=1;
                     }else{
                       $("#errorMessage").html("");
                       $("#voucharCode").css('border-color', '');
                       status=0;
                     }
                   },
                   error : function() {
                     alert("error");
                   }
                 })            
               });
              
            $("#addVoucharButton").click(function() {
              var voucharCode = $("#voucharCode").val();
              var discount = $("#discount").val();
              var valid = $('#purpose').val();
              if (voucharCode == "") {
                  $("#voucharCode").css('border-color', 'red');
                $("#voucharCode").focus();
                $("#errorMessage").html("enter the vouchar code");
                return false;
              } else if (discount == "") {
                  $("#voucharCode").css('border-color', '');
                  $("#discount").css('border-color', 'red');
                $("#discount").focus();
                $("#errorMessage").html("enter the discount");
                return false;
              }else if(valid == ""){
                  $("#purpose").css('border-color', 'red');
                  $("#discount").css('border-color', '');
                 $("#purpose").focus();
                 $("#errorMessage").html("Choose voucher validity ");
                 return false;
             }
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
                  $("#date").css('border-color', '');
                  $("#dEndHour").css('border-color', '');
                  $("#dEndMinute").css('border-color', '');
                  if(selectedDate=='Invalid Date' ){
                      $("#date").css('border-color', 'red');
                    $("#errorMessage").html("Date can not be empty");
                    return false;
                  }
                  if(selectedDate!="Invalid Date"){
                      if (selectedDate < now) {
                          $("#date").css('border-color', 'red');
                       $("#errorMessage").html("Date must be in the future");
                       return false;
                      } else if(parseInt(dStartHour) > parseInt(dEndHour)){
                          $("#dEndHour").css('border-color', 'red');
                        $("#errorMessage").html("End time must be greater than start time.");
                        check=1;
                        return false;
                      }
                      
                      if((parseInt(dStartHour) ==parseInt(dEndHour)) && (parseInt(dStartMinute) >= parseInt(dEndMinute))){
                        $("#dEndMinute").css('border-color', 'red');
                       $("#errorMessage").html("End time must be greater than start time.");
                       check=0;
                       return false;
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
                
                $("#startDate").css('border-color', '');
                $("#endDate").css('border-color', '');
                $("#pEndHour").css('border-color', '');
                $("#pEndMinute").css('border-color', '');
                var check=0;
              if(startDate=="Invalid Date"){
                  $("#startDate").css('border-color', 'red');
                  $("#errorMessage").html("Start date can not be empty");
                  return false;
                }
              if(endDate=="Invalid Date"){
                  $("#endDate").css('border-color', 'red');
                  $("#errorMessage").html("End date can not be empty");
                  return false;
                }
               if(startDate!="Invalid Date"){
                   if (startDate < now) {
                       $("#startDate").css('border-color', 'red');
                         $("#errorMessage").html("Start date must be in the future");
                         return false;
                   }
               }
               if(endDate!="Invalid Date"){
                   if (endDateText < now) {
                       $("#endDate").css('border-color', 'red');
                        $("#errorMessage").html("End date must be in the future");
                        return false;
                   }
                   if(endDate < startDate){
                       $("#endDate").css('border-color', 'red');
                     $("#errorMessage").html("To date/time must be greater than From date/time.");
                     return false;
                   }
                   else if(parseInt(pStartHour) > parseInt(pEndHour)){
                       $("#pEndHour").css('border-color', 'red');
                     $("#errorMessage").html("End time must be greater than start time.");
                     check=1;
                     return false;
                   }else{
                     if(check!=0){
                     if(parseInt(pStartMinute) >= parseInt(pEndMinute)){
                         
                         $("#pEndMinute").css('border-color', 'red');
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
            
             $("#rEndHour").css('border-color', '');
            $("#rEndMinute").css('border-color', '');
            var check=0;
                if(parseInt(rStartHour) > parseInt(rEndHour)){
                     $("#rEndHour").css('border-color', 'red');
                  $("#errorMessage").html("End time must be greater than start time.");
                  check=1;
                  return false;
                }else{
                  if(check!=0){
                  if(parseInt(rStartMinute) >= parseInt(rEndMinute)){
                      $("#rEndMinute").css('border-color', 'red');
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
