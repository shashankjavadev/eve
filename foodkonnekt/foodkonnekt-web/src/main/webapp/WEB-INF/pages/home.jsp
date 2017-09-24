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
<script src="resources/js/jsp-js/customer.js"></script>
<script type="text/javascript">
  $(document).ready(function() {
    var vendorId = "${vendorId}";
    $("#vendorId").val(vendorId);
    $("#vendorId1").val(vendorId);
    $("#checkId").val(1);
  });
</script>
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
                      <a href="javascript:void(0)" class="site-logo"> <img
                        src="${sessionScope.merchant.merchantLogo}"
                        onerror="this.onerror=null;this.src='resources/img/logo.png';">
                      </a>
                    </div>
                    <!---- #sd-logo -----> </nav>
                    <section class="main-section">
                    <div class="useless-inner-container">
                      <div class="sd-page-container">
                        <div id="sd-form-container">
                          <form:form method="POST" modelAttribute="Address" id="myform" name="myform"
                            class="foodkonnekt-form" autocomplete="off">

                            <div class="large-12 columns paginationClass" id="lesson_1">
                              <div class="stunning-heading">
                                <h3>Step One</h3>
                              </div>
                              <!--.stunning-heading-->
                              <div class="panel large-12 columns no-padding">
                                <div class="small-12 medium-12 large-12 columns">
                                  <label>Would You Like?</label>
                                  <div class="button-group" data-grouptype="OR">
                                    <button href="#" class="small button success radius"
                                      onClick="deliveryDec(); return false;">Delivery</button>
                                    <button href="#" class="small button success radius"
                                      onClick="pickup(); return false;">Pickup</button>
                                  </div>

                                </div>
                              </div>
                            </div>
                            <!--.grid-->

                            <div class="small-12 medium-12 large-12 columns paginationClass" id="lesson_2">
                              <div class="stunning-heading">
                                <h3>Step Two</h3>
                              </div>
                              <!--.stunning-heading-->
                              <ul class="button-group tabs sd-options small-12 medium-12 large-12 columns" data-tabs
                                id="example-tabs" data-grouptype="OR">
                                <li class="tabs-title is-active"><a href="#existing" aria-selected="true">Sign-in
                                    (Existing)</a></li>
                                <li class="tabs-title"><a href="#new">Register (New)</a></li>
                              </ul>
                              <div class="sd-tabs-content tabs-content" data-tabs-content="example-tabs">
                                <div class="tabs-panel is-active" id="existing">
                                  <div class="small-12 medium-12 large-12 columns">
                                    <label id="signInErrorBox" class="error"></label>
                                  </div>
                                  <div class="small-12 medium-12 large-12 columns">
                                    <form:hidden path="customer.merchantt.id" id="vendorId" />
                                    <form:hidden path="customer.orderType" id="orderType" />
                                    <label for="email">Email Address</label> <input type="email" placeholder="John Doe"
                                      id="email">
                                  </div>
                                  <div class="small-12 medium-12 large-12 columns">
                                    <label for="password">Password</label> <input type="password" placeholder="Password"
                                      id="password">
                                  </div>
                                  <div class="small-12 medium-12 large-12 columns sd-right">
                                    <input type="button" class="success hollow button" value="SignIn and Order"
                                      onclick="customerSignIn()">
                                  </div>
                                  
                                  <div class="small-12 medium-12 large-12 columns sd-right">
                                    <a href="forgotPassword">Forgot Password</a>
                                  </div>

                                </div>
                                <div class="tabs-panel" id="new">
                                  <div class="small-12 medium-12 large-12 columns">
                                    <label id="errorBox" class="error"></label>
                                  </div>
                                  <div class="small-12 medium-12 large-12 columns">
                                    <label for="name-register">Name</label>
                                    <form:input path="customer.firstName" placeholder="John Doe" id="name-register"
                                      minlength="4" maxlength="20" />
                                  </div>
                                  <div class="small-12 medium-12 large-12 columns">
                                    <label for="email-register">Email Address</label>
                                    <form:input path="customer.emailId" placeholder="example@email.com"
                                      id="email-register" maxlength="60" />
                                  </div>
                                  <div class="small-12 medium-12 large-12 columns">
                                    <label for="phone-register">Password</label>
                                    <form:password path="customer.password" placeholder="*********"
                                      id="password-register" maxlength="15" />
                                  </div>
                                  <div class="small-12 medium-12 large-12 columns">
                                    <label for="phone-register">Phone</label>
                                    <form:input path="customer.phoneNumber" placeholder="xxx-xxx-xxxx" id="phone-register"
                                      maxlength="13" />
                                  </div>
                                  <div id="list-for-buttons" class="small-12 medium-12 large-12 columns">
                                    <ul>
                                      <li><input type="button"
                                        class="success hollow button sd-new-customer-buttons" value="signUp and Order"
                                        id="pickUpId" onclick="saveCustomer()"></li>
                                      <li><input type="button"
                                        class="success hollow button sd-new-customer-buttons" value="Check For Delivery"
                                        onclick="checkDelivery()" id="deliveryId"></li>
                                    </ul>
                                  </div>
                                </div>
                              </div>
                            </div>
                          </form:form>
                          <!---- foodkonnekt-form ---->

                          <div class="small-12 medium-12 large-12 columns paginationClass" id="lesson_3">
                            <div class="stunning-heading">
                              <h3>Step Three</h3>
                            </div>
                            <!--.stunning-heading-->
                            <form:form method="POST" modelAttribute="Address" id="finalForm" name="finalForm"
                              class="foodkonnekt-form-address address-update panel large-12 columns no-padding"
                              autocomplete="off">
                              <div class="small-12 medium-12 large-12 columns">
                                <label id="errorBox2" class="error"></label>
                              </div>
                              <div id="address-updating-container">
                                <div class="small-12 medium-12 large-12 columns">
                                  <label for="add-address">Address</label>
                                </div>
                                <!--.grid-->
                                <div class="small-12 medium-12 large-12 columns">
                                  <select id="add-address">
                                    <option value=0>Add New Address</option>
                                  </select>
                                </div>
                                <!--.grid-->
                              </div>
                              <!--#address-updating-container-->
                              <div class="small-12 medium-12 large-12 columns">
                                <label for="step-three-street-address">Street Address</label>
                                <form:input path="address1" placeholder="Street Address" id="step-three-street-address"
                                  maxlength="100" />
                                <form:hidden path="customer.emailId" id="emailId" />
                                <form:hidden path="customer.id" id="customerId" />
                                <form:hidden path="customer.password" id="passwordSignUp" />
                                <form:hidden path="customer.phoneNumber" id="phoneNumber" />
                                <form:hidden path="customer.firstName" id="firstName" />
                                <form:hidden path="customer.merchantt.id" id="vendorId1" />
                                <form:hidden path="customer.checkId" id="checkId" />
                                <form:hidden path="id" id="addressId" />
                              </div>
                              <div class="clearfix"></div>

                              <div class="small-12 medium-12 large-6 columns">
                                <label for="step-three-street-address-two">Address Line 2</label>
                                <form:input path="address2" placeholder="Example: apt 201"
                                  id="step-three-street-address-two" maxlength="100" />
                              </div>

                              <div class="small-12 medium-12 large-6 columns">
                                <label for="step-three-city">City</label>
                                <form:input path="city" placeholder="City" id="step-three-city" maxlength="30" />
                              </div>

                              <div class="clearfix"></div>

                              <div class="small-12 medium-12 large-6 columns">
                                <label for="step-three-select-state">State</label>
                                <form:select path="state" id="step-three-select-state">
                                  <form:option value="select">Select</form:option>
                                   <form:option value="MP">MP</form:option>
                                  <form:option value="AL">Alabama</form:option>
                                  <form:option value="AK">Alaska</form:option>
                                  <form:option value="AZ">Arizona</form:option>
                                  <form:option value="AR">Arkansas</form:option>
                                  <form:option value="CA">California</form:option>
                                  <form:option value="CO">Colorado</form:option>
                                  <form:option value="CT">Connecticut</form:option>
                                  <form:option value="DE">Delaware</form:option>
                                  <form:option value="FL">Florida</form:option>
                                  <form:option value="GA">Georgia</form:option>
                                  <form:option value="HI">Hawaii</form:option>
                                  <form:option value="ID">Idaho</form:option>
                                  <form:option value="IL">Illinois</form:option>
                                  <form:option value="IN">Indiana</form:option>
                                  <form:option value="IA">Iowa</form:option>
                                  <form:option value="KS">Kansas</form:option>
                                  <form:option value="KY">Kentucky</form:option>
                                  <form:option value="LA">Louisiana</form:option>
                                  <form:option value="ME">Maine</form:option>
                                  <form:option value="MD">Maryland</form:option>
                                  <form:option value="MA">Massachusetts</form:option>
<form:option value="MI">Michigan</form:option>
<form:option value="MN">Minnesota</form:option>
<form:option value="MS">Mississippi</form:option>
<form:option value="MO">Missouri</form:option>
<form:option value="MT">Montana</form:option>
<form:option value="NE">Nebraska</form:option>
<form:option value="NV">Nevada</form:option>
<form:option value="NH">New Hampshire</form:option>
<form:option value="NJ">New Jersey</form:option>
<form:option value="NM">New Mexico</form:option>
<form:option value="NY">New York</form:option>
<form:option value="NC">North Carolina</form:option>
<form:option value="ND">North Dakota</form:option>
<form:option value="OH">Ohio</form:option>
<form:option value="OK">Oklahoma</form:option>
<form:option value="OR">Oregon</form:option>
<form:option value="PA">Pennsylvania</form:option>
<form:option value="RI">Rhode Island</form:option>
<form:option value="SC">South Carolina</form:option>
<form:option value="SD">South Dakota</form:option>
<form:option value="TN">Tennessee</form:option>
<form:option value="TX">Texas</form:option>
<form:option value="UT">Utah</form:option>
<form:option value="VT">Vermont</form:option>
<form:option value="VA">Virginia</form:option>
<form:option value="WA">Washington</form:option>
<form:option value="WV">West Virginia</form:option>
<form:option value="WI">Wisconsin</form:option>
<form:option value="WY">Wyoming</form:option>
 </form:select>
                              </div>

                              <div class="small-12 medium-12 large-6 columns">
                                <label for="step-three-zip">Zip</label>
                                <form:input path="zip" placeholder="Zip" id="step-three-zip" maxlength="5" />
                              </div>

                              <div class="clearfix"></div>
                              <div id="list-for-buttons" class="small-12 medium-12 large-12 columns">
                                <ul>
                                  <li></li>
                                  <li><input type="button" class="success hollow button sd-new-customer-buttons"
                                    value="Place and order" onclick="finalSave()"></li>
                                </ul>
                              </div>
                              <!--.grid-->
                            </form:form>
                          </div>
                          <!--.grid-->

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