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
     <script src="https://cdn.datatables.net/1.10.11/js/jquery.dataTables.min.js"></script>
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.11/css/jquery.dataTables.min.css">
    <script type="text/javascript">
          jQuery.fn.dataTableExt.oApi.fnPagingInfo = function ( oSettings )
          {
             return {
                 "iStart":         oSettings._iDisplayStart,
                 "iEnd":           oSettings.fnDisplayEnd(),
                 "iLength":        oSettings._iDisplayLength,
                 "iTotal":         oSettings.fnRecordsTotal(),
                 "iFilteredTotal": oSettings.fnRecordsDisplay(),
                 "iPage":          oSettings._iDisplayLength === -1 ?
                     0 : Math.ceil( oSettings._iDisplayStart / oSettings._iDisplayLength ),
                 "iTotalPages":    oSettings._iDisplayLength === -1 ?
                     0 : Math.ceil( oSettings.fnRecordsDisplay() / oSettings._iDisplayLength )
             };
         };

     $(document).ready(function() {
         $("#example").dataTable({
             "bProcessing": true,
             "bServerSide": true,
             "sort": "position",
             "bStateSave": false,
             "iDisplayLength": 10,
             "iDisplayStart": 0,
             "fnDrawCallback": function () {
                 //Get page numer on client. Please note: number start from 0 So
                 //for the first page you will see 0 second page 1 third page 2...
                 //Un-comment below alert to see page number
                 //alert("Current page number: "+this.fnPagingInfo().iPage);    
             },         
             "sAjaxSource": "customersDataTables",
             "aoColumns": [
                 { "mData": "firstName" },
                 { "mData": "emailId" },
                 { "mData": "phoneNumber" },
                 { "mData": "createdDate" },
                 { "mData": "orderCount" },
                 { "mData": "view" },
             ]
         });
     });
     
    /*  $(document).ready(function() {
         var table = $('#example').DataTable();
         $(".searchq").keyup(function() {
             table.search( this.value).draw();
          });
       }); */
     
          $(document).ready(function() {
            $('input[type=search]').each(function() {
              $(this).attr('placeholder', "Search customer");
            });
          });
        </script>
    <style type="text/css">
        div#example_paginate {
          display: block;
        }
        
        div#example_filter {
          display: block;
        }
        
        div#example_length {
          display: block;
        }
        
        input[type="search"] {
          max-width: 300px;
          width: 87%;
          outline: 0;
          border: 1px solid rgb(169, 169, 169);
          padding: 11px 10px;
          border-radius: 6px;
          margin-bottom: 7px;
          placeholder: Search Items;
          display:none;
        }
</style>
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
                                                                <li ><a href="deliveryZones">Delivery Zones</a></li>
                                                                <li><a href="vouchars">Coupons</a></li>
                                                                <li class="current-menu-item"><a href="customers">Customers</a></li>
                                                            </ul>
                                                        </div><!--.coupons-navigation-->
                                                        
                                                        <div class="delivery-zones-content-container">
                                                         <div class="search-container" style="margin-top: 10px">
                                                            <div class="only-search-elements">
                                                                <label>Search</label> 
                                                                <input type="text" id="searchTxt" class="searchq" style="max-width: 793px;height: 48px !important;"> <input type="button" value="Search">
                                                            </div>
                                                        </div>
                                                        <!-- <script type="text/javascript">
                                                            $(".searchq").keyup(function() {
                                                                var val = $(this).val();
                                                                ('input[type="search"]').val(val);
                                                                $('input[type="search"]').trigger("keyup");
                                                            });
                                                        </script> -->
                                                            <div class="clearfix"></div>
                                                            <div class="delivery-zone-container">
                                                                <div class="delivery-zone-container-form">
                                                                 <table width="100%" cellpadding="0" cellspacing="0" id="example">
                                                                                      <thead>
                                                                                        <tr>
                                                                                          <th>Name</th>
                                                                                          <th>Email</th>
                                                                                          <th>Phone Number</th>
                                                                                          <th>Customer Since</th>
                                                                                          <th>Order</th>
                                                                                          <th>Order Details</th>
                                                                                        </tr>
                                                                                      </thead>
                                                                                    <%--   <tbody>
                                                                                       <c:forEach items="${customers}" var="c" varStatus="status">
                                                                                        <tr>
                                                                                                <td>${c.firstName}${c.lastName}</td>
                                                                                                <td>${c.emailId}</td>
                                                                                                <td>${c.phoneNumber}</td>
                                                                                                <td>${c.createdDate}</td>
                                                                                                <td><a href="customerOrders?customerId=${c.id}" class="edit">${c.orderCount}</a></td>
                                                                                                <td>
                                                                                                <c:if test="${not empty c.orderCount}">
                                                                                                    <a href="customerOrders?customerId=${c.id}" class="edit" style="color: blue">view</a>
                                                                                                </c:if>
                                                                                                </td>
                                                                                        </tr>
                                                                                        </c:forEach>
                                                                                      </tbody> --%>
                                                                                    </table>
                                                                </div><!--.delivery-zone-container-form-->
                                                            </div><!--.delivery-zone-container-->
                                                            
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
    
    <!--OPENS DIALOG BOX-->
    <script src="resources/js/dialog-box/classie.js"></script>
    <script src="resources/js/dialog-box/modalEffects.js"></script>
    <script>
        var polyfilter_scriptpath = '/js/';
    </script>
    <!--OPENS DIALOG BOX-->
        <script>
            $(function(){
                $('label').each(function(){
                    if($(this).text()=='Search:'){
                        $(this).text('');
                      }
                })
                $("#searchTxt").keyup(function() {
                     var searchTxt=$(this).val();
                      if ( $.fn.DataTable.isDataTable('#example') ) {
                          $('#example').DataTable().destroy();
                        }

                        $('#example tbody').empty();
                        var itemId=$('#itemId').val();
                        $.ajax({
                          type: 'GET',
                          url: "searchCustomerByText?searchTxt="+searchTxt,
                          success:function(data){
                           var outer = [];
                           $.each(data, function( index, value ) {
                             var inner = [];
                             inner.push(value.firstName);
                             inner.push(value.emailId);
                             inner.push(value.phoneNumber);
                             inner.push(value.createdDate);
                             inner.push(value.orderCount);
                             inner.push(value.view);
                             outer.push(inner);
                           });
                           //console.log(outer);
                           $('#example').DataTable( {
                             data: outer,
                             columns: [
                                 { title: "Name" },
                                 { title: "Email" },
                                 { title: "Phone Number" },
                                 { title: "Customer since" },
                                 { title: "Order" },
                                 { title: "Order Details" }
                             ]
                            });
                           $('label').each(function(){
                               if($(this).text()=='Search:'){
                                   $(this).text('');
                                 }
                           })
                          }
                       });
                });
            })
</script>
  </body>
</html>
