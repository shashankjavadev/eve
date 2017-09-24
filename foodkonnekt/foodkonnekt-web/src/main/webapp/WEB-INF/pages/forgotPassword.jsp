<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title></title>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
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
<script src="resources/js/jsp-js/forgotPassword.js"></script>
<style type="text/css">
.stunning-heading h3:before {
    left: 2%;
}
.stunning-heading h3:after {
    right: 3%;
}
</style>
</head>
<body class="fk-1">
  <div class="sd-overlay-container">
    <div class="sd-main-site">
      <div class="max-row">

        <div class="sd-page-overlay">
          <div class="off-canvas-wrap docs-wrap" data-offcanvas>
            <div class="inner-wrap">
              <div class="main-useless-class">
                <div class="row">
                  <div class="page-color">
                    <nav class="tab-bar">
                    <div id="sd-logo">
                      <a href="#" class="site-logo"> <img src="resources/img/logo.png" />
                      </a>
                    </div>
                    <!---- #sd-logo -----> </nav>
                    <section class="main-section">
                    <div class="useless-inner-container">
                      <div class="sd-page-container">
                        <div id="sd-form-container">
                          <form:form method="POST" modelAttribute="Customer" id="myform" name="myform" action="sendPassword"
                            class="foodkonnekt-form" autocomplete="off" >

                            <div class="small-12 medium-12 large-12 columns paginationClass" id="lesson_2">
                              <div class="stunning-heading">
                                <h3>Forgot Password</h3>
                              </div>
                              <div class="sd-tabs-content tabs-content" data-tabs-content="example-tabs">
                                <div class="tabs-panel is-active" id="existing">
                                  <div class="small-12 medium-12 large-12 columns">
                                    <label id="signInErrorBox" class="error">${message}</label>
                                     <label id="errorBox" class="error"></label>
                                  </div>
                                  <input type="hidden" id="merctId" value="${merchant.id}">
                                  <div class="small-12 medium-12 large-12 columns">
                                    <label for="email">Email Address</label>
                                    <form:input path="emailId" placeholder="Enter email id" id="email" />
                                  </div>
                                  <div class="small-12 medium-12 large-12 columns sd-right">
                                    <a href="home?merchantId=${merchant.id}" class="success hollow button">Login</a>
                                    <input type="submit" class="success hollow button" value="Forgot Password" onclick="return checkEmail()">
                                  </div>
                                </div>
                              </div>
                            </div>
                          </form:form>
                          <!---- foodkonnekt-form ---->

                        </div>
                        <!-----#sd-form-container------>
                        <div id="footer">
                          <div class="footer-container-outbound">
                            <div class="footer-container-inbound">
                              <div class="small-12 medium-12 large-12 columns">

                                <div class="copyrights">
                                  <ul>
                                    <li>Powered By <a href="http://foodkonnekt.com/" target="_blank">FoodKonnekt</a></li>
                                    <li><a href="http://foodkonnekt.com/" target="_blank"><img
                                        src="resources/img/powered-by-foodkonnekt-logo.png"></a></li>
                                  </ul>
                                </div>
                                <!--.copyrights-->
                              </div>
                              <!--.grid-->
                            </div>
                            <!--.footer-container-inbound-->
                          </div>
                          <!--.footer-container-outbound-->
                        </div>
                        <!--#footer-->
                      </div>
                      <!--- .sd-page-container ---->
                    </div>
                    <!--.useless-inner-container--> </section>

                    <a class="exit-off-canvas"></a>
                  </div>
                  <!----- page-color ---->
                </div>
                <!---- row ---->
              </div>
              <!------ main-useless-class ----->
            </div>
            <!----inner wrap---->
          </div>
          <!-----off-canvas---->
        </div>
        <!---- sd-page-overlay ------>

      </div>
      <!---- max-row ----->
    </div>
    <!-- sd-main-site ---->
  </div>
  <!--.sd-overlay-container-->

  <script src="resources/js/vendor.js"></script>
  <script src="resources/js/foundation.js"></script>
  <script src="resources/js/docs.js"></script>
</body>
</html>