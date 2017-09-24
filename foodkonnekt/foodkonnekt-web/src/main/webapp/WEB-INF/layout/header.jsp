<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title></title>
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet" href="resources/css/foundation.css" />
<link href="resources/css/docs.css" rel="stylesheet" />
<link href="resources/css/foundation-icons.css" rel="stylesheet" />
<link rel="stylesheet" href="resources/css/style.css" />
<link rel="stylesheet" href="resources/css/responsive.css" />
<link rel="stylesheet" href="resources/css/font-awesome.css">
<link
  href='https://fonts.googleapis.com/css?family=Open+Sans:400,300italic,300,400italic,600,600italic,700,700italic,800,800italic&subset=latin,greek,greek-ext,vietnamese,cyrillic-ext,cyrillic,latin-ext'
  rel='stylesheet' type='text/css'>
<link href='https://fonts.googleapis.com/css?family=Raleway:400,100,200,300,500,600,700,800,900' rel='stylesheet'
  type='text/css'>

<!--OFF CANVAS MENU JS/CSS ENDS-->
<script src="https://code.jquery.com/jquery-1.11.3.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.3/jquery.easing.min.js"></script>
<script src="resources/js/offcanvas/jquery.navside.js"></script>
<link href="resources/css/offcanvas/navside.css" rel="stylesheet" type="text/css">
<!--OFF CANVAS MENU JS/CSS STARTS-->

<!--ACCORDION JS/CSS STARTS-->
<link href="resources/css/accordion/smk-accordion.css" rel="stylesheet" type="text/css">
<!--ACCORDION JS/CSS ENDS-->

<!--PopUp with modal start-->
<link rel="stylesheet" type="text/css" href="resources/css/popup-pure-css/popup-pure-style.css">
<!--PopUp with modal ends-->

<!--NOTIFICATION POPUPS JS/CSS STARTS-->
<link href="resources/css/notifications/sticky.full.css" rel="stylesheet" media="screen">
<!--NOTIFICATION POPUPS JS/CSS ENDS-->
</head>
<body class="fk-1 shop-foodkonnekt shop page page-shop">
  <div class="sd-overlay-container">
    <div class="sd-main-site">
      <div class="max-row">
         <div class="offcanvas-nav-container">
              <nav id="navside">
                  <ul id="sidebar-menu">
                    <div class="sd-user-info">
                        <div class="sd-user-info-overlay">
                           <!--  <div class="sd-user-settings">
                                <a href="#" class="sd-settings-user"><i class="fa fa-cog"></i> Settings</a>
                            </div>.sd-user-settings -->
                            <div class="sd-user-name">
                                <h3><c:out value="${sessionScope.customer.firstName} ${sessionScope.customer.lastName}" /></h3>
                                <span>${sessionScope.merchant.name}</span>
                            </div><!--.sd-user-name-->
                            <div class="sd-user-gravatar">
                            <c:choose>
                                <c:when test="${sessionScope.customer.image!=null}">
                                <img src="${sessionScope.customer.image}" id="profileImgId">
                                </c:when>
                                   <c:otherwise>
                                   <span id="headProfileId"></span>
                                   </c:otherwise>
                                    </c:choose>
                            </div><!--.sd-user-gravatar-->
                         </div><!--.sd-user-info-overlay-->
                    </div><!--.sd-user-info-->
                    <li><a href="profile">My Profile</a></li>
                    <li><a href="myOrder">My Orders</a></li>
                    <li><a href="order">Order Now</a></li>
                    <li><a href="logout">Log Out</a></li>
                  </ul>
                </nav>
                <div id="top-bar-color" class="small-12 medium-12 large-12 columns">
                  <div class="sd-top-bar-offcanvas">
                      <div id="top-cart" class="small-6 medium-6 large-6 columns">
                          <price><i class="fa fa-shopping-cart"></i> Your Cart: <span id="cartPayment"></span></price>
                        </div><!--.grid-->
                  <div id="cart-top" class="small-6 medium-6 large-6 columns">
                  <c:if test="${sessionScope.customer != null}">
                  <cart><a href="logout"><i class="fa fa-power-off"></i> Log Out</a></cart>
                  </c:if>
                        </div><!--.grid-->
                    </div><!--.sd-top-bar-offcanvas-->
                </div><!--.grid-->
            </div><!--.offcanvas-nav-container-->
            <div class="sd-page-overlay">
  <div class="off-canvas-wrap docs-wrap" data-offcanvas>
    <div class="inner-wrap">
      <div class="main-useless-class">
        <div class="long-row">
          <div class="page-color">
            <nav class="tab-bar">
              <div id="sd-logo">
              <img src="${sessionScope.merchant.merchantLogo}" onerror="this.onerror=null;this.src='resources/img/logo.png';">
                </a>
              </div>
            </nav>