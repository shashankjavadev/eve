<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<!doctype html>
<html class="no-js" lang="en">
  <head>
    <title>FoodKonnekt | Add Products</title>
    <!--CALLING STYLESHEET STYE.CSS-->
    <link rel="stylesheet" href="resources/css/style.css">
    <!--CALLING STYLESHEET STYLE.CSS-->
    
    <!--CALLING GOOGLE FONT OPEN SANS-->
    <link href='https://fonts.googleapis.com/css?family=Open+Sans:400,300,300italic,400italic,600,600italic,700,700italic,800,800italic' rel='stylesheet' type='text/css'>
    <!--CALLING GOOGLE FONT OPEN SANS-->
    
    <!--CALLING FONT AWESOME-->
    <link rel="stylesheet" href="resources/css/font-awesome.css">
    <!--CALLING FONT AWESOME-->
    
    <!--CALLING PRODUCTS TABS-->
    <link rel='stylesheet' type='text/css' href='resources/css/products-tabs/opentabby.css' />
    <!--CALLING PRODUCTS TABS-->
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
    
      input[type="search"]{
        max-width: 300px;
        width: 100%;
        outline: 0;
        border: 1px solid rgb(169, 169, 169);
        padding: 11px 10px;
        border-radius: 6px;
        margin-bottom: 7px;
        placeholder:Search Items;
    }
    input[type="text"], input[type="email"], input[type="password"], input[type="number"], input[type="date"] {
    height: 39px;
    }
    
    </style>
  </head>
  <body>
    <div id="page-container">
        <div class="foodkonnekt inventory">
            <div class="inner-container">
                <div class="max-row">
                    
                    <header id="page-header">
                        <div class="inner-header">
                            <div class="row">
                                
                                <div class="logo">
                                    <a href="adminHome" title="FoodKonnekt Dashboard" class="logo"><img src="resources/img/foodkonnekt-logo.png"></a>
                                </div><!--.logo-->
                               <%@ include file="adminHeader.jsp" %> 
                                
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
                                            </div><!--.content-header-->
                                            
                                            <div class="inventory-page-data">
                                                <div class="inventory-tabs-outbound">
                                                    <div class="inventory-tabs">
                                                        <div class="inventory-tabs-inbound">
                                                            <div class="main-products-add-products">
                                                                <div class='openTabby' id='tabs1'>
                                                                    <div class='openTabby--slidesContainer'>
                                                                    <div class="coupons-navigation">
                                                                                                                       <ul>
                                                                        <li ><a href="inventory">Items</a></li>
                                                                        <li class="current-menu-item"><a href="category">Categories</a></li>
                                                                        <li><a href="modifierGroups">Modifier Groups</a></li>
                                                                        <li><a href="modifiers">Modifiers</a></li>
                                                                    </ul>
                                                        </div><!--.within-page-horizontal-menu-->
                                                                        <form:form method="POST" action="updateCategoryStatus" modelAttribute="Category" id="updateCategoryForm" autocomplete="off" >
                                                                        <form:hidden path="id" value="${category.id}"/>  
                                                                        <div class="adding-products">
                                                                            <div class="adding-products-form">
                                                                                <label>Name:</label>
                                                                                <input type="text" placeholder="${category.name}" value="${category.name}" readonly="readonly" >
                                                                                <br>
                                                                                <div class="clearfix"></div>
                                                                                <label>Status:</label>
                                                                                <form:select path="itemStatus" id="categoryStatusId">
                                                                                   <form:option value="0">Active</form:option>
                                                                                   <form:option value="1">Inactive</form:option>
                                                                                </form:select>
                                                                                <br>
                                                                                <div class="clearfix"></div>
                                                                                <div class="button left">
                                                                                   <!--  <a href="#" id="updateItemButton">Save</a> -->
                                                                                    <input type="button" id="updateCategoryButton" value="Save">
                                                                                </div><!--.button-->
                                                                            </div><!--.adding-products-form-->
                                                                        </div><!--.adding-products-->
                                                                        </form:form>
                                                                    </div>
                                                                  </div>
                                                            </div><!--.main-products-add-products-->
                                                        </div><!--.inventory-tabs-inbound-->
                                                    </div><!--.inventory-tabs-->
                                                </div><!--.inventory-tabs-outbound-->
                                            </div><!--.inventory-page-data-->
                                            
                                        </div><!--.right-content-inner-container-->
                                    </div><!--.right-content-container-->
                                </div><!--.content-inner-container-->
                            </div><!--.row-->
                            
                        </div><!--.outer-container-->
                    </div><!--#page-content-->
                   <%@ include file="adminFooter.jsp" %>
                    <!--#footer-container-->
                    
                </div><!--.max-row-->
            </div><!--.inner-container-->
        </div><!--.foodkonnekt .dashboard-->
    </div><!--#page-container-->
    
    <!--CALLING TABS-->
    <script src="https://code.jquery.com/jquery-2.1.4.min.js"></script>
    <script src='resources/js/products-tabs/opentabby.js'></script>
    <script>
        $(".openTabby").openTabby();
    </script>

    <script src="https://cdn.datatables.net/1.10.11/js/jquery.dataTables.min.js"></script>
    <script type="text/javascript">
         $(document).ready(function() {
            var table = $('#example').DataTable();
         });
         $(document).ready(function(){
           $('input[type=search]').each(function(){
             $(this).attr('placeholder', "Search Items");
           });
         });
     </script>
     <script type="text/javascript">
      $(document).ready(function() {
          $("#updateCategoryButton").click(function () {
            $("#updateCategoryForm").submit();
          });
          
          var categoryStatus="${categoryStatus}";
          if(categoryStatus==0){
            $("#categoryStatusId").val(0);
          }
          if(categoryStatus==1){
            $("#categoryStatusId").val(1);
          }
      });
  </script>
    <!--CALLING TABS-->
  </body>
</html>
