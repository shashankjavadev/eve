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
     <script src="resources/js/checkall/jquery.checkall.js"></script>
    
      <!--OPENS DIALOG BOX-->
    <link rel="stylesheet" type="text/css" href="resources/css/dialog-box/component.css" />
    <!--OPENS DIALOG BOX-->
    
    <!--ACCORDION FOR MENU-->
    <script src="resources/js/accordion/paccordion.js"></script>
    <!--ACCORDION FOR MENU-->
     <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.11/css/jquery.dataTables.min.css">
     <script src="https://cdn.datatables.net/1.10.11/js/jquery.dataTables.min.js"></script>
     <script type="text/javascript">
          $(document).ready(function() {
            var table = $('#example').DataTable();
            $(".searchq").keyup(function() {
            	 table.search( this.value).draw();
           } );
          });
          $(document).ready(function() {
            $('input[type=search]').each(function() {
              $(this).attr('placeholder', "Search zones");
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
                                                                <li class="current-menu-item"><a href="deliveryZones">Delivery Zones</a></li>
                                                                <li><a href="vouchars">Coupons</a></li>
                                                                <li><a href="customers">Customers</a></li>
                                                            </ul>
                                                        </div><!--.coupons-navigation-->
                                                        
                                                        <div class="delivery-zones-content-container">
                                                            <div class="button left">
                                                                <a href="addDeliveryZone">Add Zone</a>
                                                            </div>
                                                            <div class="search-container">

																				<div class="only-search-elements">
																					<label>Search</label> <input type="text"
																						placeholder="Search Zones" id="search-inventory"
																						class="searchq"> <input type="button"
																						value="Search">
																				</div>

																			</div>
																			<script type="text/javascript">
																				$(".searchq").keyup(
																								function() {
																									var val = $(this).val();
																									$('input[type="search"]').val(val);
																									$('input[type="search"]').trigger("keyup");
																								});
																			</script>
                                                            <div class="clearfix"></div>
                                                            
                                                            <div class="delivery-zone-container">
                                                                <div class="delivery-zone-container-form">
                                                               <table width="100%" cellpadding="0" cellspacing="0" id="example">
                                                                                        <thead>
                                                                                            <tr>
                                                                                                <th>Location</th>
                                                                                                <th>Name</th>
                                                                                                <th>Avg. Time</th>
                                                                                                <th>Miles</th>
                                                                                                <th>Min Order Amt</th>
                                                                                                <th>Delivery Fee</th>
                                                                                                <th>Status</th>
                                                                                                <th>Action</th>
                                                                                            </tr>
                                                                                        </thead>
                                                                                        <tbody>
                                                                                            <c:forEach items="${zones}" var="z" varStatus="status">
                                                                                                <tr>
                                                                                                    <td>${z.address.address1}${z.address.address2}${z.address.city}${z.address.country}${z.address.zip}</td>
                                                                                                    <td>${z.zoneName}</td>
                                                                                                    <td>${z.avgDeliveryTime}</td>
                                                                                                    <td>${z.zoneDistance}</td>
                                                                                                    <td>${z.minDollarAmount}</td>
                                                                                                    <td>${z.deliveryFee}</td>
                                                                                                     <c:choose>
                                                                                                    <c:when test="${z.zoneStatus==0}">
                                                                                                      <td>Active</td>
                                                                                                    </c:when>    
                                                                                                    <c:otherwise>
                                                                                                     <td>InActive</td>
                                                                                                    </c:otherwise>
                                                                                                </c:choose>
                                                                                                    <td><a href="updateDelivery?deliveryZoneId=${z.id}" class="edit"><i class="fa fa-pencil" aria-hidden="true"></i></a>
                                                                                                    </td>
                                                                                                </tr>
                                                                                            </c:forEach>
                                                                                        </tbody>
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
})

</script>
  </body>
</html>
