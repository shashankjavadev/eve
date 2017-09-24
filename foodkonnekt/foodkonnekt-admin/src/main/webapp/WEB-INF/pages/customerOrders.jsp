<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
                                <%@ include file="adminHeader.jsp"%>
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
                                    <%@ include file="leftMenu.jsp"%>
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
                                                                            placeholder="Search Orders"
                                                                            id="search-inventory" class="searchq">
                                                                        <input type="button" value="Search">
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <!--.inventory-items-list-table-->
                                                            <table id="checkit-table" class="table header-table-orders">
                                                                <thead>
                                                                    <tr>
                                                                        <th>Customer Name</th>
                                                                        <th>Date and Time</th>
                                                                        <th>Order Total</th>
                                                                        <th>Order Type</th>
                                                                        <th>Order Status</th>
                                                                        <th>Order Notes</th>
                                                                        <th>View</th>
                                                                    </tr>
                                                                </thead>
                                                                <tbody>
                                                                    <c:forEach items="${allOrders}" var="view"
                                                                        varStatus="status">
                                                                        <tr>
                                                                            <td align="center"><p>${view.customer.firstName}</p></td>
                                                                            <td align="center"><p>${view.createdOn}</p></td>
                                                                            <td align="center"><p>${view.orderPrice}</p></td>
                                                                            <td align="center"><p>${view.orderType}</p></td>
                                                                            <c:choose>
                                                                                <c:when test="${view.isDefaults=='1'}">
                                                                                    <td align="center"><p>Confirmed</p></td>
                                                                                </c:when>
                                                                                <c:otherwise>
                                                                                    <c:choose>
                                                                                        <c:when
                                                                                            test="${view.isDefaults=='2'}">
                                                                                            <td align="center"><p>Cancelled</p></td>
                                                                                        </c:when>
                                                                                        <c:otherwise>
                                                                                            <td align="center"><p>Pending</p></td>
                                                                                        </c:otherwise>
                                                                                    </c:choose>

                                                                                </c:otherwise>
                                                                            </c:choose>
                                                                            <td align="center"><p>${view.orderNote}</p></td>
                                                                            <td><p>
                                                                                    <button
                                                                                        class="md-trigger custom-sd-button-dialog"
                                                                                        data-modal="modal-15-${view.id}">View</button>
                                                                                </p></td>
                                                                        </tr>
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

    <div id="sd-dialog">
        <c:forEach items="${allOrders}" var="view" varStatus="status">
            <div class="md-modal md-effect-14" id="modal-15-${view.id}">
                <div class="md-content">
                    <h3>ORDER DETAILS</h3>
                    <div>
                        <div class="order-details-container">
                            <div class="order-details-header">
                                <ul>
                                    <li><span>Item</span></li>
                                    <li><span>Quantity</span></li>
                                    <li><span>Price</span></li>
                                </ul>
                            </div>
                            <!--.order-details-header-->
                            <c:forEach items="${view.orderItemViewVOs}" var="itemView" varStatus="status1">
                                <div class="order-details-items-quantity-price">
                                    <ul>
                                        <li>${itemView.item.name}</li>
                                        <li>${itemView.quantity}</li>
                                        <li>$ <fmt:formatNumber type="number" minFractionDigits="2"
                                                value="${itemView.item.price}" />
                                        </li>
                                    </ul>
                                    <c:forEach items="${itemView.itemModifierViewVOs}" var="modifierView"
                                        varStatus="status2">
                                        <ul>
                                            <li>${modifierView.modifiers.name}</li>
                                            <li>1</li>
                                            <li>$ <fmt:formatNumber type="number" minFractionDigits="2"
                                                    value="${modifierView.modifiers.price}" />
                                            </li>
                                        </ul>
                                    </c:forEach>
                                </div>
                                <!--.order-details-items-quantity-price-->
                            </c:forEach>
                            <div class="order-subtotal-tax-total">
                                <ul>
                                    <li>Subtotal</li>
                                    <li>$ <fmt:formatNumber type="number" minFractionDigits="2"
                                            value="${view.subTotal}" />
                                    </li>
                                </ul>
                                <c:if test="${not empty view.convenienceFee}">
                                    <ul>
                                        <li>Convenience Fee</li>
                                        <li></li>
                                        <li>$ <fmt:formatNumber type="number" minFractionDigits="2"
                                                value="${view.convenienceFee}" />
                                        </li>
                                    </ul>
                                </c:if>
                                <c:if test="${view.orderType!='pickup' && view.orderType!='Pickup'}">
                                    <ul>
                                        <li>Delivery Fee</li>
                                        <li></li>
                                        <li>$ <fmt:formatNumber type="number" minFractionDigits="2"
                                                value="${view.deliveryFee}" />
                                        </li>
                                    </ul>
                                </c:if>
                                
                                <ul>
                                    <li>Tax</li>
                                    <li>$ <fmt:formatNumber type="number" minFractionDigits="2" value="${view.tax}" />
                                    </li>
                                </ul>
                                <c:if test="${not empty view.tipAmount && view.tipAmount>0}">
                                    <ul>
                                        <li>Tip</li>
                                        <li></li>
                                        <li>$ <fmt:formatNumber type="number" minFractionDigits="2"
                                                value="${view.tipAmount}" />
                                        </li>
                                    </ul>
                                </c:if>
                                <ul>
                                    <li>Total</li>
                                    <li>$ <fmt:formatNumber type="number" minFractionDigits="2"
                                            value="${view.orderPrice}" />
                                    </li>
                                </ul>
                            </div>
                            <!--.order-subtotal-tax-total-->
                            <div class="order-details-order-notes">
                                <textarea name="order-notes" cols="" rows="4" readonly="readonly">${view.orderNote} </textarea>
                            </div>
                            <!--.order-details-order-notes-->
                        </div>
                        <!--.order-details-container-->
                        <div class="clearfix"></div>
                        <div class="button">
                            <button class="white-button md-close">Close</button>
                        </div>
                        <!--.button-->
                    </div>
                </div>
            </div>
        </c:forEach>

        <div class="md-overlay"></div>
        <!-- the overlay element -->
    </div>
    <!--#sd-dialog-->
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