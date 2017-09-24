<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
 <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page session="true"%>
<!doctype html>
<html class="no-js" lang="en">
<head>
<title>FoodKonnekt | Dashboard</title>
<!--CALLING STYLESHEET STYE.CSS-->
<link rel="stylesheet" href="resources/css/style.css">
<!--CALLING STYLESHEET STYLE.CSS-->

<!--CALLING GOOGLE FONT OPEN SANS-->
<link
    href='https://fonts.googleapis.com/css?family=Open+Sans:400,300,300italic,400italic,600,600italic,700,700italic,800,800italic'
    rel='stylesheet' type='text/css'>
<!--CALLING GOOGLE FONT OPEN SANS-->

<!--CALLING FONT AWESOME-->
<link rel="stylesheet" href="resources/css/font-awesome.css">
<!--CALLING FONT AWESOME-->

<!--CALENDAR MULTI-SELECT-->
<link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/themes/smoothness/jquery-ui.css">
<link href="resources/css/calendar/jquery.comiseo.daterangepicker.css" rel="stylesheet" type="text/css">
<!--CALENDAR MULTI-SELECT-->

<!--CHECK ALL LIST TABLE-->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script src="resources/js/checkall/jquery.checkall.js"></script>
<!--CHECK ALL LIST TABLE-->

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

input[type="search"] {
    max-width: 300px;
    width: 100%;
    outline: 0;
    border: 1px solid rgb(169, 169, 169);
    padding: 11px 10px;
    border-radius: 6px;
    margin-bottom: 7px;
    display: none;
}
.right-content-container{
    width: 1100px;
    float: left;
    margin-left: 5%;
}
</style>
</head>
<body>
    <div id="page-container">
        <div class="foodkonnekt orders">
            <div class="inner-container">
                <div class="max-row">

                    <header id="page-header">
                        <div class="inner-header">
                            <div class="row">
                                <div class="logo">
                                    <a href="adminHome" title="FoodKonnekt Dashboard" class="logo"><img
                                        src="resources/img/foodkonnekt-logo.png"></a>
                                </div>
                                <!--.logo-->
                                <%-- <%@ include file="adminHeader.jsp"%> --%>
                                <div class="header-nav">
    <nav class="header-nav-container">
        <ul>
            
            <!-- <li><a href="#"><i class="fa fa-user" aria-hidden="true"></i> My Account</a> |</li> -->
            <li><a href="adminLogout"><i class="fa fa-power-off" aria-hidden="true"></i> Log Out</a></li>
        </ul>
    </nav>
    <!--.header-nav-container-->
</div>
                            </div>
                            <!--.row-->
                        </div>
                        <!--.inner-header-->
                    </header>
                    <!--#page-header-->

                    <div id="page-content">
                        <div class="outer-container">

                            <div class="row">
                                <div class="content-inner-container">
                                    <%-- <%@ include file="leftMenu.jsp"%> --%>
                                    <div class="right-content-container">
                                        <div class="right-content-inner-container">
                                            <div class="content-header">
                                                <div class="all-header-title"></div>
                                                <!--.header-title-->
                                                <div class="content-header-dropdown"></div>
                                                <!--.content-header-dropdown-->
                                            </div>
                                            <!--.content-header-->
                                            <div class="orders-page-data">

                                                <div class="sd-orders-list-outbound">
                                                    <div class="sd-orders-list">
                                                        <div class="sd-orders-list-inbound">
                                                            <div class="orders-items-list-table">
                                                                <div class="search-container" style="margin-top: 10px">
                                                                    <div class="only-search-elements">
                                                                        <label>Search</label> <input type="text"
                                                                            placeholder="Search Merchants"
                                                                            id="search-inventory" class="searchq">
                                                                        <input type="button" value="Search">
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <!--.inventory-items-list-table-->
                                                            <table id="checkit-table" class="table header-table-orders">
                                                                <thead>
                                                                    <tr>
                                                                        <th>Merchant Name</th>
                                                                        <th>Phone NO</th>
                                                                        <th>Email</th>
                                                                        <th>Total Orders</th>
                                                                        <th>POS Name</th>
                                                                        <th>Status</th>
                                                                        <th>Subscription</th>
                                                                        <th>View Admin</th>
                                                                        
                                                                    </tr>
                                                                </thead>
                                                                <tbody>
                                                                    <c:forEach items="${allMerchants}" var="view"
                                                                        varStatus="status">
                                                                        <c:if test="${view.name!='FoodkonnektAdmin'}">
                                                                        <tr>
                                                                            <td align="center"><p>${view.name}</p></td>
                                                                            <td align="center"><p>${view.phoneNumber}</p></td>
                                                                            <td align="center"><p>${view.owner.email}</p></td>
                                                                            <td align="center"><p>${view.totalOrders}</p></td>
                                                                            <td align="center"><p>${view.owner.pos.name}</p></td>
                                                                            <td align="center"><p>${view.status}</p></td>
                                                                                                                           <c:choose>
                                                                                <c:when test="${fn:length(view.merchantSubscriptions) gt 0}">
                                                                                    <c:forEach items="${view.merchantSubscriptions}" var="viewSubs"
                                                                        varStatus="status">
                                                                        <td align="center"><p>${viewSubs.subscription.subscriptionPlan}</p></td>
                                                                        </c:forEach>
                                                                                </c:when>
                                                                                <c:otherwise>
                                                                                    
                                                                                            <td align="center"><p>NA</p></td>
                                                                                        

                                                                                </c:otherwise>
                                                                            </c:choose>
                                                                            
                                                                            <c:choose>
                                                                                <c:when test="${view.owner.pos.name=='Clover' and view.isInstall==1}">
                                                                                    <td align="center"><a target="_blank" href="/foodkonnekt-admin/?merchant_id=${view.posMerchantId}&employee_id=T03TVG9M6C7PG&client_id=SMA6T1DNS8YAE#access_token=${view.accessToken}">View</a></td>
                                                                                </c:when>
                                                                                <c:otherwise>
                                                                                    
                                                                                            <td align="center"><p>NA</p></td>
                                                                                        

                                                                                </c:otherwise>
                                                                            </c:choose>
                                                                            
                                                                            
                                                                            
                                                                           
                                                                          
                                                                        </tr>
                                                                        </c:if>
                                                                    </c:forEach>
                                                                </tbody>
                                                            </table>
                                                        </div>
                                                        <!--.sd-orders-list-inbound-->
                                                    </div>
                                                    <!--.sd-orders-list-->
                                                </div>
                                                <!--.sd-orders-list-outbound-->

                                            </div>
                                            <!--.orders-page-data-->


                                        </div>
                                        <!--.right-content-inner-container-->
                                    </div>
                                    <!--.right-content-container-->
                                </div>
                                <!--.content-inner-container-->
                            </div>
                            <!--.row-->

                        </div>
                        <!--.outer-container-->
                    </div>
                    <!--#page-content-->

                    <%@ include file="adminFooter.jsp"%>
                    <!--#footer-container-->

                </div>
                <!--.max-row-->
            </div>
            <!--.inner-container-->
        </div>
        <!--.foodkonnekt .dashboard-->
    </div>
    <!--#page-container-->


    <script>
                    $(document).ready(function() {
                        $('#checkit-table').DataTable({
                            "order" : [ [ 1, "desc" ] ]
                        });
                    });
                </script>
    <script type="text/javascript">
                    var _gaq = _gaq || [];
                    _gaq.push([ '_setAccount', 'UA-36251023-1' ]);
                    _gaq.push([ '_setDomainName', 'jqueryscript.net' ]);
                    _gaq.push([ '_trackPageview' ]);

                    (function() {
                        var ga = document.createElement('script');
                        ga.type = 'text/javascript';
                        ga.async = true;
                        ga.src = ('https:' == document.location.protocol ? 'https://ssl'
                                : 'http://www')
                                + '.google-analytics.com/ga.js';
                        var s = document.getElementsByTagName('script')[0];
                        s.parentNode.insertBefore(ga, s);
                    })();
                </script>
    <!--CALENDAR MULTI-SELECT-->

    <!--OPENS DIALOG BOX-->
    <script src="resources/js/dialog-box/classie.js"></script>
    <script src="resources/js/dialog-box/modalEffects.js"></script>
    <script>
        var polyfilter_scriptpath = '/js/';
    </script>
    <!--OPENS DIALOG BOX-->

    <script src="https://cdn.datatables.net/1.10.11/js/jquery.dataTables.min.js"></script>
    <script type="text/javascript">
     $(document).ready(function() {
        var table = $('#checkit-table').DataTable();
        $(".searchq").keyup(function() {
             table.search( this.value).draw();
         } );
     });
     $(document).ready(function(){
       $('input[type=search]').each(function(){
         $(this).attr('placeholder', "Search");
       });
     });
     $("#generateButton").click(function () {
       $("#searchForm").submit();
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
</body>
</html>