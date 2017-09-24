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
    
    <!--CALLING CHECK ALL FUNCTIONALITY-->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
    <script src="resources/js/checkall/jquery.checkall.js"></script>
    <!--CALLING CHECK ALL FUNCTIONALITY-->
    
    <!--CALLING PRODUCTS TABS-->
    <link rel='stylesheet' type='text/css' href='resources/css/products-tabs/opentabby.css' />
    <!--CALLING PRODUCTS TABS-->

    <!--OPENS DIALOG BOX-->
    <link rel="stylesheet" type="text/css" href="resources/css/dialog-box/component.css" />
    <!--OPENS DIALOG BOX-->
    
    <!--ACCORDION FOR MENU-->
    <script src="resources/js/accordion/paccordion.js"></script>
    <!--ACCORDION FOR MENU-->
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
                                    
                                    <div class="left-nav-bar">
                                        <nav class="left-nav-bar-container">
                                            <div class="accordion-wrapper">
                                            <div class="ac-pane">
                                                <a href="adminHome">
                                                    <span>Dashboard</span>
                                                </a>
                                            </div>
                                            <div class="ac-pane">
                                                <a href="allOrders">
                                                    <span>Orders</span>
                                                </a>
                                            </div>
                                            <div class="ac-pane">
                                                <a href="inventory.html">
                                                    <span>Inventory</span>
                                                </a>
                                            </div>
                                            <div class="ac-pane">
                                                <a href="#" class="ac-title" data-accordion="true">
                                                    <span>Merchant</span>
                                                    <i class="fa"></i>
                                                </a>
                                                <div class="ac-content">
                                                    <ul>
                                                        <li><a href="merchants">Merchant</a></li>
                                                        <li><a href="#">Menu Item</a></li>
                                                        <li><a href="#">Menu Item</a></li>
                                                        <li><a href="#">Menu Item</a></li>
                                                        <li><a href="#">Menu Item</a></li>
                                                    </ul>
                                                </div>
                                            </div>
                                            </div>
                                        </nav><!--.left-nav-bar-container-->
                                        <div class="sidebar-logo">
                                            <img src="resources/img/logo.jpg">
                                        </div><!--.sidebar-logo-->
                                    </div><!--.left-nav-bar-->
                                
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
                                                            <main>
                                                                  <div class="within-page-horizontal-menu">
                                                                    <ul>
                                                                        <li><a href="inventory">Items</a></li>
                                                                        <li class="active-tab"><a href="category">Categories</a></li>
                                                                        <li><a href="modifierGroups">Modifier Groups</a></li>
                                                                        <li><a href="modifiers">Modifiers</a></li>
                                                                    </ul>
                                                                  </div><!--.within-page-horizontal-menu-->
                                                              
                                                              <section id="content2">
                                                                <div class="tab-content-container-outbound">
                                                                    <div class="tab-content-container">
                                                                        <div class="tab-content-container-inbound">
                                                                            <div class="inventory-page-data">
                                                                                <div class="inventory-tabs-outbound">
                                                                                    <div class="inventory-tabs">
                                                                                        <div class="inventory-tabs-inbound">
                                                                                            <div class="main-products-add-products">
                                                                                                <div class='openTabby' id='tabs1'>
                                                                                                    <div class='openTabby--slidesContainer'>
                                                                                                
                                                                                                      <article id='1' data-tab-name='Categories' class='openTabby--slide'>
                                                                                                        <div class="category-search-container">
                                                                                                            <div class="search-category category-33-percent">
                                                                                                                <input type="text" placeholder="Search Items" class="search">
                                                                                                            </div><!--.search-category-->
                                                                                                            <div class="status-category category-33-percent">
                                                                                                                <select>
                                                                                                                    <option>Status</option>
                                                                                                                    <option>Active</option>
                                                                                                                    <option>Inactive</option>
                                                                                                                </select>
                                                                                                            </div><!--.status-category-->
                                                                                                            <div class="location-category category-33-percent">
                                                                                                                <input type="text" placeholder="Location">
                                                                                                            </div><!--.location-category-->
                                                                                                        </div><!--.category-search-container-->
                                                                                                        <div class="table-category-list">
                                                                                                            <div class="table-category-list-inbound">
                                                                                                                <table width="100%" cellpadding="0" cellspacing="0" id="example">
                                                                                                                      <thead>
                                                                                                                        <tr>
                                                                                                                          <th><input type="checkbox" data-toggle="checkall" data-target="input[name=room]" /></th>
                                                                                                                          <th>Category Name</th>
                                                                                                                         <!--  <th>Products</th>
                                                                                                                          <th>Order</th> -->
                                                                                                                          <th>Status</th>
                                                                                                                          <th>Action</th>
                                                                                                                        </tr>
                                                                                                                      </thead>
                                                                                                                      <tbody>
                                                                                                                     <c:forEach items="${categories}" var="view" varStatus="status"> 
                                                                                                                        <tr>
                                                                                                                          <td><input type="checkbox" name="room" value="C" /></td>
                                                                                                                           <td>${view.name}</td>
                                                                                                                          <!-- <td>January</td> -->
                                                                                                                          <td>Active</td>
                                                                                                                         <!--  <td>January</td> -->
                                                                                                                          <td><a href="#" class="edit"><i class="fa fa-pencil" aria-hidden="true"></i></a> <a href="#" class="delete"><i class="fa fa-trash" aria-hidden="true"></i></a></td>
                                                                                                                        </tr>
                                                                                                                        </c:forEach>
                                                                                                                      </tbody>
                                                                                                                    </table>
                                                                                                            </div><!--.table-category-list-inbound-->
                                                                                                        </div><!--.table-category-list-->
                                                                                                      </article>
                                                                                                      
                                                                                                
                                                                                                      <article id='2' data-tab-name='Add Category' class='openTabby--slide'>
                                                                                                        <div class="adding-products">
                                                                                                            <div class="adding-products-form">
                                                                                                                <label>Select Location:</label>
                                                                                                                <select>
                                                                                                                    <option>Select Location</option>
                                                                                                                    <option>Select Location Two</option>
                                                                                                                </select>
                                                                                                                <br>
                                                                                                                <div class="clearfix"></div>
                                                                                                                <label>Category Name:</label>
                                                                                                                <textarea></textarea>
                                                                                                                <br>
                                                                                                                <div class="clearfix"></div>
                                                                                                                <label>Status:</label>
                                                                                                                <select>
                                                                                                                    <option>Choose</option>
                                                                                                                    <option>Active</option>
                                                                                                                    <option>Inactive</option>
                                                                                                                </select>
                                                                                                                <br>
                                                                                                                <div class="clearfix"></div>
                                                                                                                <div class="button left">
                                                                                                                    <a href="#">Save</a>
                                                                                                                </div><!--.button-->
                                                                                                            </div><!--.adding-products-form-->
                                                                                                        </div><!--.adding-products-->
                                                                                                      </article>
                                                                                                      
                                                                                                    <article id='3' data-tab-name='Update Category' class='openTabby--slide'>
                                                                                                        <div class="adding-products">
                                                                                                            <div class="adding-products-form">
                                                                                                                <label>Select Location:</label>
                                                                                                                <select>
                                                                                                                    <option>Select Location</option>
                                                                                                                    <option>Select Location Two</option>
                                                                                                                </select>
                                                                                                                <br>
                                                                                                                <div class="clearfix"></div>
                                                                                                                <label>Category Name:</label>
                                                                                                                <textarea></textarea>
                                                                                                                <br>
                                                                                                                <div class="clearfix"></div>
                                                                                                                <label>Status:</label>
                                                                                                                <select>
                                                                                                                    <option>Choose</option>
                                                                                                                    <option>Active</option>
                                                                                                                    <option>Inactive</option>
                                                                                                                </select>
                                                                                                                <br>
                                                                                                                <div class="clearfix"></div>
                                                                                                                <div class="button left">
                                                                                                                    <a href="#">Save</a>
                                                                                                                </div><!--.button-->
                                                                                                            </div><!--.adding-products-form-->
                                                                                                        </div><!--.adding-products-->
                                                                                                    </article>
                                                                                                    </div>
                                                                                                  </div>
                                                                                            </div><!--.main-products-add-products-->
                                                                                        </div><!--.inventory-tabs-inbound-->
                                                                                    </div><!--.inventory-tabs-->
                                                                                </div><!--.inventory-tabs-outbound-->
                                                                            </div><!--.inventory-page-data-->

                                                                        </div><!--.tab-content-container-inbound-->
                                                                    </div><!--.tab-content-container-->
                                                                </div><!--.tab-content-container-outbound-->
                                                              </section>
                                                                
                                                              
                                                                
                                                            </main>
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


    <div id="sd-dialog">
        <div class="md-modal md-effect-14" id="modal-14">
            <div class="md-content">
                <h3>Modal Dialog</h3>
                <div>
                    <form action="" method="get" class="home-pop-form">
                        <input name="name" type="text" placeholder="Your Name">
                        <input name="name" type="email" placeholder="Your Email">
                        <textarea name="query" cols="" rows="3" placeholder="State Your Query"></textarea>
                        <div class="button">
                            <input name="submit" type="button" value="Post Your Query" class="button left">
                        </div><!--.button-->
                    </form><!--.home-pop-form-->
                    <div class="clearfix"></div>
                    <div class="button">
                        <button class="white-button md-close">Close me!</button>
                    </div><!--.button-->
                </div>
            </div>
        </div>
        <div class="md-overlay"></div><!-- the overlay element -->
    </div><!--#sd-dialog--> 
    
    <!--OPENS DIALOG BOX-->
    <script src="resources/js/dialog-box/classie.js"></script>
    <script src="resources/js/dialog-box/modalEffects.js"></script>
    <script>
        var polyfilter_scriptpath = '/js/';
    </script>
    <!--OPENS DIALOG BOX-->
    
    <!--CALLING TABS-->
    <script src="https://code.jquery.com/jquery-2.1.4.min.js"></script>
    <script src='resources/js/products-tabs/opentabby.js'></script>
    <script>
        $(".openTabby").openTabby();
    </script>
    <!--CALLING TABS-->
     <script src="https://cdn.datatables.net/1.10.11/js/jquery.dataTables.min.js"></script>
    <script type="text/javascript">
     $(document).ready(function() {
        var table = $('#example').DataTable();
     });
     $(document).ready(function(){
       $('input[type=search]').each(function(){
         $(this).attr('placeholder', "Search Category");
       });
     });
 </script>
  </body>
</html>
