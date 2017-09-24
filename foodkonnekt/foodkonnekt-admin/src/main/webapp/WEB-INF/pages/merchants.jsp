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
    
    <!--ACCORDION FOR MENU-->
    <script src="resources/js/accordion/paccordion.js"></script>
    <!--ACCORDION FOR MENU-->

    
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
                                                <div class="content-header-dropdown">
                                                </div><!--.content-header-dropdown-->
                                            </div><!--.content-header-->
                                            
                                            <div class="breadcrumbs-table-fetcher">
                                                <ul>
                                                    <li>50 results per page</li>
                                                    <li><a href="#"><i class="fa fa-arrow-circle-left" aria-hidden="true"></i> Back</a> | <a href="#"> Next <i class="fa fa-arrow-circle-right" aria-hidden="true"></i></a></li>
                                                </ul>
                                            </div><!--.breadcrumbs-table-fetcher-->
                                            <div class="merchant-page-data">
                                                <table cellpadding="0" cellspacing="0" border="0" class="filter-table" id="example">
                                                    <thead>
                                                        <tr>
                                                            <th></th>
                                                            <th>Name</th>
                                                            <th>Address</th>
                                                            <th></th>
                                                            <th>Action</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                    <c:forEach items="${merchants}" var="view" varStatus="status">
                                                        <tr class="odd gradeX">
                                                            <td></td>
                                                            <td>${view.name}</td>
                                                            <td>${view.address}</td>
                                                            <td>Active</td>
                                                            <td><a href="#"><i class="fa fa-pencil" aria-hidden="true"></i></a> 
                                                            <!-- <a href="#"><i class="fa fa-trash" aria-hidden="true"></i></a> -->
                                                            </td>
                                                        </tr>
                                                        </c:forEach>
                                                    </tbody>
                                                </table>
                                            </div><!--.merchant-page-data-->
                                            
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
    <!--OPENS DIALOG BOX-->
    <script src="resources/js/dialog-box/classie.js"></script>
    <script src="resources/js/dialog-box/modalEffects.js"></script>
    <script>
        var polyfilter_scriptpath = '/js/';
    </script>
    <!--OPENS DIALOG BOX-->
    
  </body>
</html>
