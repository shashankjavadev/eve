<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    <!--OPENS DIALOG BOX-->
    <link rel="stylesheet" type="text/css" href="resources/css/dialog-box/component.css" />
    <!--OPENS DIALOG BOX-->
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.11/css/jquery.dataTables.min.css">
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
                                                        </div>
                                                       <div class="comingsoon"><p align="center"><font size="8">Coming Soon...</font></p></div>
                                                        <!--.coupons-navigation-->
                                                        
                                                        <%--<div class="coupons-content-container">
                                                            <div class="button left">
                                                                <a href="createVouchar">Add Coupon</a>
                                                            </div><!--.button.left-->
                                                            <br>
                                                            <br>
                                                            
                                                            <div class="search-container" style="margin-top: 68px;">

                                                                                <div class="only-search-elements">
                                                                                    <label>Search</label> <input type="text"
                                                                                        placeholder="Search Coupons" id="search-inventory"
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
                                                            <table width="100%" cellpadding="0" cellspacing="0" id="example">
                                                                                    <thead>
                                                                                        <tr>
                                                                                            <th>Coupon Code</th>
                                                                                            <th>Discount</th>
                                                                                            <th>Date/Time Valid</th>
                                                                                            <th>Action</th>
                                                                                        </tr>
                                                                                    </thead>
                                                                                    <tbody>
                                                                                        <c:forEach items="${vouchars}" var="v" varStatus="status">
                                                                                            <tr>
                                                                                                <td>${v.voucharCode}</td>
                                                                                                <td>${v.discount}</td>
                                                                                                <td>${v.date}
                                                                                                <c:if test="${v.validity=='0'}">
                                                                                                  <fmt:formatDate value="${v.date}" pattern="dd-MM-yyyy"/>
                                                                                                From ${v.startTime} to ${v.endTime}
                                                                                                </c:if> 
                                                                                                
                                                                                                 <c:if test="${v.validity=='1'}">
                                                                                                   From  <fmt:formatDate value="${v.fromDate}" pattern="dd-MM-yyyy"/> 
                                                                                                    to  <fmt:formatDate value="${v.endDate}" pattern="dd-MM-yyyy"/>
                                                                                                </c:if> 
                                                                                                
                                                                                                  <c:if test="${v.validity=='2'}">
                                                                                                  
                                                                                                  Every ${v.recurringR} From ${v.startTime} to ${v.endTime}
                                                                                                </c:if> 
                                                                                                
                                                                                                </td>
                                                                                                <td><a href="editVouchar?voucharId=${v.id}" class="edit"><i class="fa fa-pencil" aria-hidden="true"></i></a>
                                                                                                </td>
                                                                                            </tr>
                                                                                        </c:forEach>
                                                                                    </tbody>
                                                            </table>
                                                        </div><!--.coupons-content-container--> --%>
                                                    </div><!--.merchat-coupons-container-->
                                                </div><!--.merchant-actions-outbound-->
                                            </div><!--.merchant-page-data-->
                                            
                                        </div><!--.right-content-inner-container-->
                                    </div><!--.right-content-container-->
                                </div><!--.content-inner-container-->
                            </div><!--.row-->
                            
                        </div><!--.outer-container-->
                    </div><!--#page-content-->
                    
                    <!--#footer-container-->
                    <div class="absolute"><%@ include file="adminFooter.jsp"%></div>
                    
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
      <script type="text/javascript">
          $(document).ready(function() {
            var table = $('#example').DataTable();
            $(".searchq").keyup(function() {
             table.search( this.value).draw();
          } );
          });
          $(document).ready(function() {
            $('input[type=search]').each(function() {
              $(this).attr('placeholder', "Search Vouchar");
            });
          });
        </script>
        <script>
$(function(){
    $('label').each(function(){
        if($(this).text()=='Search:'){
            $(this).text('');
            }
    })
})

</script>
<style>
div.absolute{
    position: absolute;
    bottom: 0;
    width: 100%;
}

div.comingsoon {
    position: absolute;
    top: 150px;
    right: 300px;
}
</style>
  </body>
</html>
