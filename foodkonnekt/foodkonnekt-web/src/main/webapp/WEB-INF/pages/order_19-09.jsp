<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!doctype html>
<html class="no-js" lang="en">
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet" href="resources/css/foundation.css" />
<link href="resources/css/docs.css" rel="stylesheet" />
<link href="resources/css/foundation-icons.css" rel="stylesheet" />
<link rel="stylesheet" href="resources/css/shop/shop-style.css" />
<link rel="stylesheet" href="resources/css/font-awesome.css">
<link
    href='https://fonts.googleapis.com/css?family=Open+Sans:400,300,300italic,400italic,600,600italic,700,700italic,800,800italic'
    rel='stylesheet' type='text/css'>

<!--accordion-->
<script src="resources/js/shop/accordion/jquery-1.11.3.min.js"></script>
<script src="resources/js/shop/accordion/woco.accordion.min.js"></script>

<!-- Hotjar Tracking Code for https://www.foodkonnekt.com/ -->
<script>
    (function(h,o,t,j,a,r){
        h.hj=h.hj||function(){(h.hj.q=h.hj.q||[]).push(arguments)};
        h._hjSettings={hjid:460451,hjsv:5};
        a=o.getElementsByTagName('head')[0];
        r=o.createElement('script');r.async=1;
        r.src=t+h._hjSettings.hjid+j+h._hjSettings.hjsv;
        a.appendChild(r);
    })(window,document,'//static.hotjar.com/c/hotjar-','.js?sv=');
</script>
<style>
* {
    font-family: 'Open Sans', sans-serif;
}

.btn {
    display: inline-block;
    cursor: pointer;
    background: #fff;
    border: 1px solid #bbb;
    height: 42px;
    padding: 11px;
    font-size: 14px;
    line-height: 18px;
    border-radius: 4px;
    text-transform: uppercase;
    font-weight: 600;
    text-align: center;
    max-width: 160px;
    width: 160px;
}

.btn.btn-default {
    
}

.btn.btn-default:hover {
    background: #eee;
    border-color: #bbb
}

.btn.btn-default:focus {
    background: #ddd;
    border-color: #bbb
}

.btn.btn-primary {
    background-color: #f8981d;
    border-color: #f8981d;
    color: #fff;
}

.btn.btn-primary:hover {
    background-color: #ffac41;
    border-color: #f8981d;
}

.btn.btn-primary:focus {
    background-color: #f8981d;
    border-color: #f8981d;
}

.btn.btn-default[disabled] {
    background: #fafafa !important;
    border-color: #ccc !important;
    color: #aaa
}

.btn.btn-primary[disabled] {
    background: #3F9DD0 !important;
    border-color: #537FA9 !important;
    color: #ACD3E8;
    box-shadow: none !important
}

.btn.btn-left {
    float: left;
    margin: 0 5px 0 0 !important
}

.sd-popup-content img {
    display: block;
    margin: 0 auto 10px;
}

.sd-popup-content h3 {
    text-align: center;
    font: bold;
}
</style>
<link type="text/css" rel="stylesheet" href="resources/css/popModal.css">
<script src="resources/js/popModal.js"></script>
<!--accordion-->
<script type="text/javascript">
var deliveryItemPosId="";
var deliveryItemPrice=0;
var deliveryTaxStatus=0;
var deliveryTaxPrice=0;
var avgDeliveryTime="0";
var merchantId="${merchantId}";
var bgColor='#f8981d';
var timingStatus="${openingClosingStatus}";
var closingDayStatus="${closingDayStatus}";
var openingClosingHours="${openingClosingHours}";
var setGuestPasswordV="${setPassword}";

var isConvenienceFeeStatus="${isConvenienceFeePrice}";
var convenienceFeePrice="${convenienceFeePrice}";
var convenienceFeePosId="${convenienceFeePosId}";
var convenienceFeeTax="${convenienceFeeTax}";
var sessionCutId='${sessionCustId}';
var allowFutureOrder= "${allowFutureOrder}";
var lengthOfMerchant = "${fn:length(merchantList)}";


var merchantTaxNameFromDB=${merchantTaxs};
var merchtTaxMap=new Map();
for (var m=0;m<merchantTaxNameFromDB.length;m++) {
    merchtTaxMap.set(merchantTaxNameFromDB[m].name,merchantTaxNameFromDB[m].rate);
}

var convenienceFeeTaxWithComma='${convenienceFeeTaxWithComma}';
var convenienceFeeTaxWithCommaArray=[];
convenienceFeeTaxWithCommaArray=convenienceFeeTaxWithComma.split(",");

var deliveryFeeTaxWithCommaArray=[];
var deliveryFeeCheckStatus=0;
var deliveryFreeCheckValue="";
var deliveryMfee=0;
</script>
<script src="resources/js/jsp-js/customerInfo.js"></script>
<style type="text/css">
.drawer {
    position: relative;
}

.pj-loader-2 {
    display: block;
    position: absolute;
    height: 130%;
    width: 100%;
    background: url("resources/img/now-loading.gif") no-repeat scroll center
        center rgba(255, 255, 2555, 1);
    z-index: 9999;
    left: 0;
    position: absolute;
    top: 120px;
}

.mnuLoading {
    display: none;
}
</style>
</head>
<body class="fk-1 shop-foodkonnekt shop page page-shop">

    <div id="signInNav" class="overlay" style="height: 0%;">
        <!-- Button to close the overlay navigation -->
        <a href="javascript:void(0)" class="closebtn"
            onClick="closeNavSignIn()">&times;</a>
        <!-- Overlay content -->
        <div class="overlay-content">
            <div class="overlay-contentscale">
                <sd-overlay>
                <div class="sd-accordion-container"
                    style="width: 78%; float: right;">
                    <div class="accordion-popup-header">
                        <div class="confirmModal_content sd-popup-content">
                            <img src="resources/img/logo.png" class="sd-popup-logo">
                        </div>
                    </div>
                    <!--.accordion-popup-header-->
                    <div class="accordion-popup-sd-container">
                          <div class="pj-loader-4"style="display:none">
                                       <img src="resources/img/load2.gif" style="width: 82px;margin-left: auto;margin-top: auto;">
                           </div>
                        <div class="accordion-popup-quantity">  
                            <label id="loginError" class="error"
                                style="font-size: 0.77778rem; font-weight: normal; line-height: 1.5;"></label>
                            <quantity> <label id="loginError" class="error"
                                style="font-size: 0.77778rem; font-weight: normal; line-height: 1.5;"></label>
                            <label
                                style="font-size: 0.77778rem; font-weight: normal; line-height: 1.5;">Email
                                ID</label> <input type="email" id="singInEmail"
                                placeholder="Enter email id"
                                style="float: left; max-width: 610px;"> <label
                                style="font-size: 0.77778rem; font-weight: normal; line-height: 1.5;">Password</label>
                            <input type="password" id="signInPassword" placeholder="Password"
                                style="float: left; max-width: 610px;"> </quantity>
                        </div>
                        <!--.accordion-popup-quantity-->

                        <div class="shop-checkout-popup-button" >
                            <div class="button right" id="forgotPassword" >
                                <a href="#" onclick="openNavforgot()">Forgot Password</a>
                            </div>
                            <div class="button right" style="margin-right: 10px" id="loginPopUp">
                                <a href="javascript:void(0)" id="singInButton">Login</a>
                            </div>
                            
                            <!--.button-->
                        </div>
                        <!--.shop-checkout-popup-button-->
                    </div>
                    <!--.accordion-popup-sd-container-->
                </div>
                <!--.sd-accordion-container--> </sd-overlay>
            </div>
        </div>
        <!--.overlay-content-->
    </div>
    <!--.content-within-overlay-->

    <div id="myNav" class="overlay" style="height: 0%;">
        <!-- Button to close the overlay navigation -->
        <a href="javascript:void(0)" class="closebtn"
            onClick="closeNavforgot()">&times;</a>
        <!-- Overlay content -->
        <div class="overlay-content">
            <div class="overlay-contentscale">
                <sd-overlay>
                <div class="sd-accordion-container"
                    style="width: 78%; float: right;">
                    <div class="accordion-popup-header">
                        <div class="confirmModal_content sd-popup-content">
                            <img src="resources/img/logo.png" class="sd-popup-logo">
                        </div>
                    </div>
                    <!--.accordion-popup-header-->
                    <div class="accordion-popup-sd-container">
                        <div class="accordion-popup-quantity">
                            <quantity> <label id="errorForgotPassword"
                                class="error"
                                style="font-size: 0.77778rem; font-weight: normal; line-height: 1.5;"></label>
                            <input type="text" id="emailForgot" placeholder="Enter email id"
                                style="float: left; max-width: 610px;"> </quantity>
                        </div>
                        <!--.accordion-popup-quantity-->

                        <div class="shop-checkout-popup-button">
                            <div class="button right" style="clear: both;" id="forgotButtonButton">
                                <a href="#" onclick="return checkEmail()" id="forgotButtonId">Forgot
                                    Password</a>
                            </div>
                            <!--.button-->
                        </div>
                        <!--.shop-checkout-popup-button-->
                    </div>
                    <!--.accordion-popup-sd-container-->
                </div>
                <!--.sd-accordion-container--> </sd-overlay>
            </div>
        </div>
        <!--.overlay-content-->
    </div>
    <!--.content-within-overlay-->


    <div class="pj-loader-2">
        <!--  <img src="resources/img/now-loading.gif"> -->
    </div>

    <div id="page">
        <div class="page-outbound">
            <div class="page">
                <div class="page-inbound">
<!-- <div class="sd-cart-fixed-for-mobile-container">
<div class="sd-cart-fixed-for-mobile-part" style="display: block;"><h4><span>TOTAL CART:</span><price>$3.00 </price></h4></div></div> -->
                <!--  .sd-cart-fixed-for-mobile-container -->
              <div class="sd-cart-fixed-for-mobile-container">
<div class="sd-cart-fixed-for-mobile-part" style="display: block;"><h4>TOTAL CART:<span id="totalSpanId1">$0.00</span></h4></div>
                </div>
                    <header id="master-header">
                        <div class="header">
                            <div class="shop-row">
                                <div class="logo-shop">
                                    <a href="index.html" title="FoodKonnekt Dashboard" class="logo">
                                        <!-- <img src="resources/img/logo.jpg"> </a> --> <img
                                        src="/${merchant.merchantLogo}"
                                        onerror="this.src='resources/img/logo.png'" width="250"
                                        height="150">
                                    </a>
                                    <div class="logINCust" style="display: block">
                                        &nbsp;&nbsp;&nbsp;<a href="#"
                                            style="float: right; padding-left: -10px"
                                            onclick="openNavSignIn()">Sign In</a>
                                    </div>
                                    <div class="loggedINCust" style="display: none">
                                        &nbsp;&nbsp;&nbsp;<a href="profile"
                                            style="float: right; padding-left: 10px">My Account</a>
                                        &nbsp;&nbsp;&nbsp;<a href="logout" style="float: right;"><i
                                            class="fa fa-power-off"></i> Log Out</a>
                                    </div>
                                    <div class="loggedINCust">
                                        <c:if
                                            test="${sessionScope.customer != null && (sessionScope.customer.password != null && sessionScope.customer.password != '')}">
                                     
                                     &nbsp;&nbsp;&nbsp;<a href="profile"
                                                style="float: right; padding-left: 10px">My Account</a>
                                     &nbsp;&nbsp;&nbsp;<a href="logout"
                                                style="float: right;"><i class="fa fa-power-off"></i>
                                                Log Out</a>

                                        </c:if>
                                    </div>

                                </div>
                                <!--.logo-shop-->

                            </div>
                            <!--.shop-row-->
                        </div>
                        <!--.header-->
                    </header>
                    <!--#master-header-->

                    <div class="content">
                        <div class="outer-content">
                            <div class="shop-row">
                                <span style="color: red;" id="orderMsgSpanId">${message}</span>
                                <div class="inner-content">
                                    <div class="small-12 medium-12 large-8 columns mnuLoading">
                                        <div id="left-container-accordion"
                                            class="accordion accordion2">
                                            
                                             <c:if test="${ merchantList.size() > 1}">
                                                    <h1 class="categchangeMerchant" id="changeLocation">Select Location</h1>
                                                   <div class="zeroDiv">
                                                    <select id="changeMerchantLocation">
                                                    <c:forEach items="${merchantList}" var="view" varStatus="status">
                                                    <c:choose>
                                                                                <c:when test="${merchantId ==view.id}">
                                                    <option value="${view.id}:${view.name}" selected>${view.name}</option>
                                                    </c:when>
                                                    <c:otherwise>
                                                    <option value="${view.id}:${view.name}">${view.name}</option>
                                                    </c:otherwise>
                                                    </c:choose>
                                                     </c:forEach>
                                                    </select>
                                                    </div>
                                                    </c:if>
                                                    
                                            
                                            <h1 style="display: none"></h1>
                                            <c:forEach items="${categories}" var="view"
                                                varStatus="status">
                                                <c:if test="${view.categoryStatus == 0}">
                                                <h1 class="categAjaxCall" idAttr="${view.id}">${view.categoryName}</h1>

                                                <div id="${view.id}"></div>
                                                </c:if>
                                            </c:forEach>
                                        </div>
                                    </div>

                                    <div class="small-12 medium-12 large-4 columns">
                                        <div class="your-cart-outbound">
                                            <div class="your-cart">
                                                <div class="your-cart-inbound">
                                                    <div class="accordion accordion-right">
                                                   
                                                  
                                                   
                                                    <%-- <c:if test="${ merchantList.size() > 1}">
                                                    <h1 class="disable" id="changeLocation">Change Location</h1>
                                                    <div class="zeroDiv">
                                                    <select id="changeMerchantLocation">
                                                    <c:forEach items="${merchantList}" var="view" varStatus="status">
                                                    <c:choose>
                                                                                <c:when test="${merchantId ==view.id}">
                                                    <option value="${view.id}:${view.name}" selected>${view.name}</option>
                                                    </c:when>
                                                    <c:otherwise>
                                                    <option value="${view.id}:${view.name}">${view.name}</option>
                                                    </c:otherwise>
                                                    </c:choose>
                                                     </c:forEach>
                                                    </select>
                                                    </div>
                                                    </c:if> --%>
                                                    
                                                    
                                                    
                                                    
                                                    
                                                        <h1 class="yourCartAmount" id="yourCartAmountId">Your
                                                            Cart</h1>
                                                        <div class="firstDiv">
                                                            <ul>
                                                                <li>
                                                                    <div class="cart">
                                                                        <div class="itemCart">
                                                                            <h3 id="your_cart">Your Cart</h3>
                                                                            <label id="couponError" class="error"></label> <label
                                                                                id="itemError" class="error"></label>
                                                                            <div class="table-header">
                                                                                <ul>
                                                                                    <li class="item">Item</li>
                                                                                    <li class="quantity">Qty</li>
                                                                                    <li class="price">Price</li>
                                                                                </ul>
                                                                            </div>
                                                                            <!--.table-header-->
                                                                            <div class="items-names-prices"></div>
                                                                            <!--.items-names-prices-->
                                                                            <div class="cart-footer">
                                                                                <ul>
                                                                                    <li>SUBTOTAL: <span id="subTotalSpanId">$0.00</span></li>

                                                                                    <c:if test="${isConvenienceFeePrice == '1'}">
                                                                                        <li id="conveniceLiId" style="display: none">CONVENIENCE
                                                                                            FEE: <span id="convenienceFeeSpanId">$${convenienceFeePrice}</span>
                                                                                        </li>
                                                                                    </c:if>
                                                                                    <li id="deliveryFeeLiId" style="display: none">DELIVERY
                                                                                        FEE: <span id="deliveryFeeSpanId"></span>
                                                                                    </li>
                                                                                    <li>TAX: <span id="taxSpanId">$0.00</span></li>
                                                                                    <li>DISCOUNT: <span id="discountSpanId">$0.00</span></li>
                                                                                    <li>TIP:<div class="tip-code"> <input type="text" placeholder="$0.00" class="couponCodeText" id="tipAmount" min="0" maxlength="5" onkeyup="return validatenumberOnKeyUp(event,this)" onkeypress="return validatenumber(event,this)"></div></li>
                                                                                    <li><b>TOTAL:</b> <span id="totalSpanId">$0.00</span></li>
                                                                                </ul>
                                                                            </div>
                                                                            <!--.cart-footer-->


                                                                            <div class="coupon-code">
                                                                               <textarea id="special-instructions" placeholder="Max 140 Character" maxlength="140" class=""></textarea>
                                                                                 <!-- <input type="text" placeholder="Coupon Code" class="couponCodeText1" maxlength="30" id="coupon">
                                                                            <input type="button" class="button couponButton" value="APPLY" id="couponbutton"> --> 
                                                                            </div>
                                                                            <!--.coupon-code-->
                                                                                <!--.pickup-delivery-selector-->
                                                                              <div class="pickup-delivery-selector">
                                                                               <input type="hidden" id="orderType">
                                                                                <ul>
                                                                                    <li>
                                                                                        <label for="pickup" tyle="line-height: 1.1;">
                                                                                        <input type="radio" name="pickupdelivery" id="pickup">
                                                                                        <span>Pick Up</span>
                                                                                        </label>
                                                                                     </li>
                                                                                    <c:if test="${zoneStatus=='Y'}">
                                                                                      <li>
                                                                                         <label for="delivery" style="line-height: 1.1;">
                                                                                         <input type="radio" name="pickupdelivery" id="delivery">
                                                                                         <span>Delivery</span>
                                                                                         </label>
                                                                                      </li>
                                                                                    </c:if>
                                                                                </ul>
                                                                            </div>
                                                                            
                                                                            
                                                                            
                                                                            <c:choose>
                                                                                <c:when test="${allowFutureOrder ==1}">
                                                                                    <div class="ordernow-futureorder-selector">
                                                                                        <ul>
                                                                                            <li>
                                                                                              <label for="ordernow"  style="line-height: 1.1;">
                                                                                              <input type="radio" name="isfuturOrder" id="ordernow"  > 
                                                                                              <span>Order Now</span>
                                                                                              </label>
                                                                                            </li>

                                                                                            <li>
                                                                                              <label for="orderlater" style="line-height: 1.1;">
                                                                                              <input type="radio" name="isfuturOrder" id="orderlater" > 
                                                                                              <span>Order Later</span>
                                                                                              </label>
                                                                                             </li>
                                                                                        </ul>
                                                                                    </div>
                                                                                </c:when>
                                                                            </c:choose>
                                                                            <label
                                                                                id="orderLaterError" class="error"></label>
                                                                            <div class="ordernow-futureorder-selector" id="futureDateCmbo" style="display: none">
                                                                                  <ul>
                                                                                     <li>
                                                                                       <select id="futureOrderDateCombo">
                                                                                        <option value="select">Select</option>
                                                                                         <%-- <c:forEach items="${futureOrderDates}" var="fDate" varStatus="status">
                                                                                            <option value="${fDate}">${fDate}</option>
                                                                                         </c:forEach> --%>
                                                                                       </select>
                                                                                     </li>
                                                                                     <li>
                                                                                        <select id="futureOpeingTime">
                                                                                          <option value="select">Select</option>
                                                                                        </select>
                                                                                     </li>
                                                                                  </ul>
                                                                              </div>
                                                                           
                                                                            
                                                                            
                                                                             
                                                                        </div>
                                                                    </div> <!--.cart-->
                                                                </li>
                                                            </ul>
                                                        </div>
                                                        <h1 class="disable" id="custH1">CUSTOMER INFORMATION</h1>
                                                        <div class="secondDiv">
                                                        <div class="pj-loader-3"style="display:none">
                                                             <img src="resources/img/load2.gif" style="width: 82px;margin-left: 117px;margin-top: 63px;">
                                                       </div>
                                                            <ul>
                                                                <li>
                                                                
                                                                    <div
                                                                        class="returning-guest-customer-selector customerInfo"
                                                                        style="display: none">
                                                                        <ul>
                                                                            <li><label for="returning-customer"> <input
                                                                                    type="radio" name="returningguest"
                                                                                    id="returning-customer"> <span>Returning
                                                                                        Customer</span></label></li>
                                                                            <li><label for="guest-checkout"> <input
                                                                                    type="radio" name="returningguest"
                                                                                    id="guest-checkout"> <span>Guest
                                                                                        Checkout</span></label></li>
                                                                        </ul>
                                                                    </div> <label id="errorBox" class="error"></label> <form:form
                                                                        method="POST" modelAttribute="PaymentVO"
                                                                        autocomplete="off" id="myform">
                                                                        <!--.returning-guest-customer-selector-->
                                                                        <div class="guestCustomer" style="display: none">
                                                                            <div class="returning-customer">
                                                                                <h4>Guest Login</h4>
                                                                                <input type="text" id="guest-name"
                                                                                    placeholder="Name" minlength="4" maxlength="40">
                                                                                <input type="email" id="guest-email"
                                                                                    placeholder="Email" maxlength="60">
                                                                                <!-- <input type="text"  id="guest-phone"placeholder="xxx-xxx-xxxx" maxlength="12"> -->
                                                                                <form:input path="instruction" type="text"
                                                                                    id="guest-phone" class="call_caller_phone"
                                                                                    onkeypress='return onlyNumberQuantity(event,this);'
                                                                                    placeholder="Phone" maxlength="12"
                                                                                    oninput="javascript: if (this.value.length > this.maxLength) this.value = this.value.slice(0, this.maxLength);" />

                                                                            </div>
                                                                            <div class="add-your-address gustAdd">
                                                                                <div class="address-line">
                                                                                    <input type="text" placeholder="Address Line 1"
                                                                                        id="step-three-street-address" maxlength="100">
                                                                                    <input type="text" placeholder="Address Line 2"
                                                                                        id="step-three-street-address-two" maxlength="100">
                                                                                        <input type="hidden" value=""
                                                                                        id="deliveryFeeFlag" maxlength="100">
                                                                                </div>
                                                                                <!--.address-line-->
                                                                                <div class="city-state-zip">
                                                                                    <input type="text" placeholder="City"
                                                                                        id="step-three-city" maxlength="30"> <select
                                                                                        id="step-three-select-state">
                                                                                        <option value="select">Select</option>
                                                                                        <option value="AL">Alabama</option>
                                                                                        <option value="AK">Alaska</option>
                                                                                        <option value="AZ">Arizona</option>
                                                                                        <option value="AR">Arkansas</option>
                                                                                        <option value="CA">California</option>
                                                                                        <option value="CO">Colorado</option>
                                                                                        <option value="CT">Connecticut</option>
                                                                                        <option value="DC">DC</option>
                                                                                        <option value="DE">Delaware</option>
                                                                                        <option value="FL">Florida</option>
                                                                                        <option value="GA">Georgia</option>
                                                                                        <option value="HI">Hawaii</option>
                                                                                        <option value="ID">Idaho</option>
                                                                                        <option value="IL">Illinois</option>
                                                                                        <option value="IN">Indiana</option>
                                                                                        <option value="IA">Iowa</option>
                                                                                        <option value="KS">Kansas</option>
                                                                                        <option value="KY">Kentucky</option>
                                                                                        <option value="LA">Louisiana</option>
                                                                                        <option value="ME">Maine</option>
                                                                                        <option value="MD">Maryland</option>
                                                                                        <option value="MA">Massachusetts</option>
                                                                                        <option value="MI">Michigan</option>
                                                                                        <option value="MN">Minnesota</option>
                                                                                        <option value="MS">Mississippi</option>
                                                                                        <option value="MO">Missouri</option>
                                                                                        <option value="MT">Montana</option>
                                                                                        <option value="NE">Nebraska</option>
                                                                                        <option value="NV">Nevada</option>
                                                                                        <option value="NH">New Hampshire</option>
                                                                                        <option value="NJ">New Jersey</option>
                                                                                        <option value="NM">New Mexico</option>
                                                                                        <option value="NY">New York</option>
                                                                                        <option value="NC">North Carolina</option>
                                                                                        <option value="ND">North Dakota</option>
                                                                                        <option value="OH">Ohio</option>
                                                                                        <option value="OK">Oklahoma</option>
                                                                                        <option value="OR">Oregon</option>
                                                                                        <option value="PA">Pennsylvania</option>
                                                                                        <option value="RI">Rhode Island</option>
                                                                                        <option value="SC">South Carolina</option>
                                                                                        <option value="SD">South Dakota</option>
                                                                                        <option value="TN">Tennessee</option>
                                                                                        <option value="TX">Texas</option>
                                                                                        <option value="UT">Utah</option>
                                                                                        <option value="VT">Vermont</option>
                                                                                        <option value="VA">Virginia</option>
                                                                                        <option value="WA">Washington</option>
                                                                                        <option value="WV">West Virginia</option>
                                                                                        <option value="WI">Wisconsin</option>
                                                                                        <option value="WY">Wyoming</option>
                                                                                    </select>
                                                                                </div>
                                                                                <div class="zip">
                                                                                    <input type="number" placeholder="Zip Code"
                                                                                        onkeypress='return onlyNumberQuantity(event,this);'
                                                                                        id="step-three-zip" min='1' maxlength="5"
                                                                                        class="logZipCode"
                                                                                        oninput="javascript: if (this.value.length > this.maxLength) this.value = this.value.slice(0, this.maxLength);">
                                                                                </div>
                                                                                <!--.city-state-zip-->
                                                                            </div>
                                                                            <!--.address-fields-->
                                                                            <div class="returning-customer">
                                                                                <h4>Create Password (Optional)</h4>
                                                                                <!--  <label for="password">Password:</label> -->
                                                                                <input type="password" id="password"
                                                                                    placeholder="Password" maxlength="15">
                                                                                <div class="checkout-button">
                                                                                    <a href="javascript:void(0)" id="registerButton">Continue
                                                                                        To Pay</a>
                                                                                </div>
                                                                            </div>
                                                                            <!--.create-account-optional-->
                                                                        </div>
                                                                    </form:form> <!--.guest-login-checkout-->
                                                                    <div class="returning-customer returningCustomer"
                                                                        style="display: none">
                                                                        <h4>Returning Customer</h4>
                                                                        <input type="email" placeholder="Email"
                                                                            id="loginEmail"> <input type="password"
                                                                            placeholder="Password" id="loginPassword" maxlength="16">
                                                                            <input type="hidden" value="" id="listOfAllDiscounts">
                                                                        <div class="checkout-button">
                                                                            <a href="javascript:void(0)" id="loginButton">Login</a>
                                                                        </div>
                                                                        <div class="checkout-button">
                                                                            <a href="#" onclick="openNavforgot()" id='forgotPassword_1'>Forgot
                                                                                Password</a>
                                                                        </div>
                                                                    </div> <!--.returning-customer--> <label id="deliveryError"
                                                                    class="error"></label>
                                                                    <div class="select-address" style="display: none">
                                                                        <select id="deliveryAddress">
                                                                            <!-- <option>Add Your Address</option> -->
                                                                        </select>
                                                                    </div> <!--.select-address-->
                                                                    <div class="add-your-address" style="display: none">
                                                                        <div class="address-line">
                                                                            <input type="text" placeholder="Address Line 1"
                                                                                id="deliveryAddresss1"> <input type="text"
                                                                                placeholder="Address Line 2" id="deliveryAddresss2">
                                                                        </div>
                                                                        <!--.address-line-->
                                                                        <div class="city-state-zip">
                                                                            <input type="text" placeholder="City"
                                                                                id="deliveryCity"> <select
                                                                                id="deliveryState">
                                                                                <option value="select">Select</option>
                                                                                <option value="AL">Alabama</option>
                                                                                <option value="AK">Alaska</option>
                                                                                <option value="AZ">Arizona</option>
                                                                                <option value="AR">Arkansas</option>
                                                                                <option value="CA">California</option>
                                                                                <option value="CO">Colorado</option>
                                                                                <option value="CT">Connecticut</option>
                                                                                <option value="DE">Delaware</option>
                                                                                <option value="FL">Florida</option>
                                                                                <option value="GA">Georgia</option>
                                                                                <option value="HI">Hawaii</option>
                                                                                <option value="ID">Idaho</option>
                                                                                <option value="IL">Illinois</option>
                                                                                <option value="IN">Indiana</option>
                                                                                <option value="IA">Iowa</option>
                                                                                <option value="KS">Kansas</option>
                                                                                <option value="KY">Kentucky</option>
                                                                                <option value="LA">Louisiana</option>
                                                                                <option value="ME">Maine</option>
                                                                                <option value="MD">Maryland</option>
                                                                                <option value="MA">Massachusetts</option>
                                                                                <option value="MI">Michigan</option>
                                                                                <option value="MN">Minnesota</option>
                                                                                <option value="MS">Mississippi</option>
                                                                                <option value="MO">Missouri</option>
                                                                                <option value="MT">Montana</option>
                                                                                <option value="NE">Nebraska</option>
                                                                                <option value="NV">Nevada</option>
                                                                                <option value="NH">New Hampshire</option>
                                                                                <option value="NJ">New Jersey</option>
                                                                                <option value="NM">New Mexico</option>
                                                                                <option value="NY">New York</option>
                                                                                <option value="NC">North Carolina</option>
                                                                                <option value="ND">North Dakota</option>
                                                                                <option value="OH">Ohio</option>
                                                                                <option value="OK">Oklahoma</option>
                                                                                <option value="OR">Oregon</option>
                                                                                <option value="PA">Pennsylvania</option>
                                                                                <option value="RI">Rhode Island</option>
                                                                                <option value="SC">South Carolina</option>
                                                                                <option value="SD">South Dakota</option>
                                                                                <option value="TN">Tennessee</option>
                                                                                <option value="TX">Texas</option>
                                                                                <option value="UT">Utah</option>
                                                                                <option value="VT">Vermont</option>
                                                                                <option value="VA">Virginia</option>
                                                                                <option value="WA">Washington</option>
                                                                                <option value="WV">West Virginia</option>
                                                                                <option value="WI">Wisconsin</option>
                                                                                <option value="WY">Wyoming</option>
                                                                            </select>
                                                                        </div>
                                                                        <div class="zip">
                                                                            <input type="number"
                                                                                onkeypress='return onlyNumberQuantity(event,this);'
                                                                                placeholder="Zip Code" id="deliveryZipCode" min='1'
                                                                                maxlength='5' class="beLZipCode"
                                                                                oninput="javascript: if (this.value.length > this.maxLength) this.value = this.value.slice(0, this.maxLength);">
                                                                        </div>
                                                                        <!--.city-state-zip-->
                                                                    </div> <!--.add-your-address-->
                                                                    <div class="checkout-button" style="display: none">
                                                                        <a href="javascript:void(0)" id="checkDeliveryButton">Check
                                                                            Delivery</a>
                                                                    </div> <!--.checkout-button-->
                                                                </li>
                                                            </ul>
                                                        </div>
                                                        <input type="hidden" id="customerId"
                                                            value="${sessionScope.customer.id}"> <input
                                                            type="hidden" id="customerPswd"
                                                            value="${sessionScope.customer.password}">

                                                        <h1 id="paymentMethodID">Select Payment Method</h1>
                                                        <div class="thirdDiv">
                                                            <form:form method="POST" modelAttribute="PaymentVO"
                                                                autocomplete="off" id="msform" name="myform">
                                                                 <form:hidden path="futureOrderType" id="futOrderType" />
                                                                        <form:hidden path="futureDate" id="futDate"/>
                                                                        <form:hidden path="futureTime" id="futtime"/>
                                                                        <form:hidden path="listOfALLDiscounts" id="listOfDiscounts"/>
                                                                <span id="agreeMsg" style="color: red"></span>
                                                                <ul>
                                                                    <li>
                                                                        <div class="select-payment-method"
                                                                            style="display: none">
                                                                            <label for="card-type">Payment:</label>
                                                                            <form:select path="paymentMethod"
                                                                                id="payment-method-credit-card" class="selMod">
                                                                                <%--  <form:option value="Cash">Cash</form:option>
                                                                            <form:option value="Credit Card">Credit Card</form:option> --%>
                                                                                <c:forEach items="${paymentModes}" var="paymentMode"
                                                                                    varStatus="paymentStatus">
                                                                                    <form:option value="${paymentMode}">${paymentMode}</form:option>
                                                                                </c:forEach>
                                                                            </form:select>
                                                                            <div class="comLi">
                                                                                <label for="card-type">Card Type:</label>
                                                                                <form:select path="ccType"
                                                                                    id="payment-method-cc-type">
                                                                                    <%--   <form:option value="">Select Card Type</form:option> --%>
                                                                                    <form:option value="American Express">American Express</form:option>
                                                                                    <form:option value="Master Card">Master Card</form:option>
                                                                                    <form:option value="Visa">Visa</form:option>
                                                                                </form:select>
                                                                                <label for="card-number">Card Number:</label> <input
                                                                                    type="number" id="payment-method-cc-number"
                                                                                    name="ccNumber" maxlength="15"
                                                                                    onkeypress='return onlyNumberQuantity(event,this);'
                                                                                    placeholder="CC Number" class="numberInput"
                                                                                    oninput="javascript: if (this.value.length > this.maxLength) this.value = this.value.slice(0, this.maxLength);">
                                                                                <label for="card-expiry">Card Expiry:</label>
                                                                                <%-- <form:input path="ccExpiration" id="expirationDate"
                                                                                    class="span2" placeholder="MM/YY" maxlength="5" /> --%>
                                                                                    <div class="cc_exp_month_year">
                                                                                    <form:select path="expMonth"
                                                                                    id="payment-method-expMonth" style="width= 35%">
                                                                                    <form:option value="">Month</form:option>
                                                                                    <form:option value="01">01</form:option>
                                                                                    <form:option value="02">02</form:option>
                                                                                    <form:option value="03">03</form:option>
                                                                                    <form:option value="04">04</form:option>
                                                                                    <form:option value="05">05</form:option>
                                                                                    <form:option value="06">06</form:option>
                                                                                    <form:option value="07">07</form:option>
                                                                                    <form:option value="08">08</form:option>
                                                                                    <form:option value="09">09</form:option>
                                                                                    <form:option value="10">10</form:option>
                                                                                    <form:option value="11">11</form:option>
                                                                                    <form:option value="12">12</form:option>
                                                                                </form:select>
                                                                                <form:select path="expYear"
                                                                                    id="payment-method-expYear">
                                                                                    <form:option value="">Year</form:option>
                                                                                    <form:option value="2017">2017</form:option>
                                                                                    <form:option value="2018">2018</form:option>
                                                                                    <form:option value="2019">2019</form:option>
                                                                                    <form:option value="2020">2020</form:option>
                                                                                    <form:option value="2021">2021</form:option>
                                                                                    <form:option value="2022">2022</form:option>
                                                                                    <form:option value="2023">2023</form:option>
                                                                                    <form:option value="2024">2024</form:option>
                                                                                    <form:option value="2025">2025</form:option>
                                                                                    <form:option value="2026">2026</form:option>
                                                                                    
                                                                                    
                                                                                </form:select>
                                                                                </div>
                                                                                <label for="security-code">CVV:</label>
                                                                                <form:input path="ccCode"
                                                                                    id="payment-method-cc-code"
                                                                                    onkeypress='return onlyNumberQuantity(event,this);'
                                                                                    placeholder="CC Code" maxlength="4"
                                                                                    class="numberInput" type="password"
                                                                                    oninput="javascript: if (this.value.length > this.maxLength) this.value = this.value.slice(0, this.maxLength);" />
                                                                            </div>
                                                                            <div class="agree-with-terms">
                                                                                 <input type="hidden" id="i-agree" checked="checked"><label
                                                                                    for="i-agreei">  <a
                                                                                    href="https://www.foodkonnekt.com/end-user-agreement"
                                                                                    target="_blank" style="color: black; padding-left: 0">By placing the order you agree to  <u> terms and conditions</u>
                                                                                </a></label>

                                                                                <form:hidden path="instruction" id="instructionId" />
                                                                                <form:hidden path="orderPosId" id="ordPosId" />
                                                                                <form:hidden path="total" id="ordrTotalAmount" />
                                                                                <form:hidden path="tip" id="ordrTipAmount" />
                                                                                <form:hidden path="subTotal" id="orderSubTotal" />
                                                                                <form:hidden path="tax" id="orderTax" />
                                                                                <div>
                                                                                    <!--.agree-with-terms-->
                                                                                </div>
                                                                                <!--.select-payment-method-->
                                                                                <div class="place-order-button">
                                                                                    <a href="javascript:void(0)" class="chkAgree">Place
                                                                                        Order</a>
                                                                                </div>
                                                                                <!--.place-order-button-->
                                                                            </div>
                                                                        </div>
                                                                    </li>
                                                                </ul>
                                                            </form:form>
                                                        </div>
                                                        <h1 hidden="true"></h1>
                                                    </div>
                                                    <!--.accordion-->
                                                </div>
                                                <!--.your-cart-inbound-->
                                            </div>
                                            <!--.your-cart-->
                                        </div>
                                        <!--.your-cart-outbound-->
                                    </div>
                                </div>
                                <!--.inner-content-->
                            </div>
                            <!--.shop-row-->
                        </div>
                        <!--.outer-content-->
                    </div>
                    <!--.content-->
                </div>
                <!--.page-inbound-->
            </div>
            <!--.page-->
        </div>
        <!--.page-outbound-->
    </div>
    <!--#page-->
    <!--accordion-->
    <!-- Pop up for guest customer -->
    <div class="exampleLive">
        <button style="display: none;" id="confirmModal_ex2"
            class="btn btn-primary" data-confirmmodal-bind="#confirm_content"
            data-topoffset="0" data-top="10%">Example</button>
    </div>
    <div id="confirm_content" style="display: none">
        <div class="confirmModal_content sd-popup-content">
            <img src="resources/img/logo.png" class="sd-popup-logo">
            <h3 style="font-size: 14px;">
                <b>Do you want to create an account so that you can keep track
                    of the order?</b>
            </h3>
        </div>
        <div class="confirmModal_footer">
            <button type="button" class="btn btn-primary guestPopUpCls"
                data-confirmmodal-but="ok" id='yesButton'>Yes</button>
            <button type="button" class="btn btn-default guestPopUpCls"
                data-confirmmodal-but="cancel">
                <font style="color: black;"><b>No</b></font>
            </button>
        </div>
    </div>

    <!-- Pop up for open and closing hour -->
    <div class="exampleLive">
        <button style="display: none;" id="openingClosingPopUp"
            class="btn btn-primary"
            data-confirmmodal-bind="#confirm_content_openingclosing"
            data-topoffset="0" data-top="10%">Example</button>
    </div>
    <div id="confirm_content_openingclosing" style="display: none">
        <div class="confirmModal_content sd-popup-content">
            <img src="resources/img/logo.png" class="sd-popup-logo">
            <h3 style="font-size: 14px;">
                <b>We are sorry. We are closed now. Please place your order
                    during our regular operating hours.Today's Operating Hours</br>"${openingClosingHours}"
                </b>
            </h3>
        </div>
        <div class="confirmModal_footer">
            <button type="button" class="btn btn-primary"
                data-confirmmodal-but="ok">Close</button>
        </div>
    </div>

    <div class="exampleLive">
        <button style="display: none;" id="openingClosingPopUp2"
            class="btn btn-primary"
            data-confirmmodal-bind="#confirm_content_openingclosing2"
            data-topoffset="0" data-top="10%">Example</button>
    </div>
    <div id="confirm_content_openingclosing2" style="display: none">
        <div class="confirmModal_content sd-popup-content">
            <img src="resources/img/logo.png" class="sd-popup-logo">
            <h3 style="font-size: 14px;">
                <b>We are sorry. We are closed today. Please place your order
                    during our regular operating day</b>
            </h3>
        </div>
        <div class="confirmModal_footer">
            <button type="button" class="btn btn-primary"
                data-confirmmodal-but="ok">Close</button>
        </div>
    </div>
    <!-- Delivery check success message -->
    <div class="exampleLive">
        <button style="display: none;" id="deliverySucccessPopUp"
            class="btn btn-primary"
            data-confirmmodal-bind="#confirm_content_deliverysuccess"
            data-topoffset="0" data-top="10%">Example</button>
    </div>
    <div id="confirm_content_deliverysuccess" style="display: none">
        <div class="confirmModal_content sd-popup-content">
            <img src="resources/img/logo.png" class="sd-popup-logo">
            <h3 style="font-size: 14px;">
                <b>Your zone is in delivery zone</b>
            </h3>
        </div>
        <div class="confirmModal_footer">
            <button type="button" class="btn btn-primary"
                data-confirmmodal-but="ok">Ok</button>
        </div>
    </div>

    <!-- Delivery check failure message -->
    <div class="exampleLive">
        <button style="display: none;" id="deliveryFailurePopUp"
            class="btn btn-primary"
            data-confirmmodal-bind="#confirm_content_deliveryfailure"
            data-topoffset="0" data-top="10%">Example</button>
    </div>
    <div id="confirm_content_deliveryfailure" style="display: none">
        <div class="confirmModal_content sd-popup-content">
            <img src="resources/img/logo.png" class="sd-popup-logo">
            <h3 style="font-size: 14px;">
                <b>Your address is not within delivery zone. Please input a new
                    address or select pick up option</b>
            </h3>
        </div>
        <div class="confirmModal_footer">
            <button type="button" class="btn btn-primary"
                data-confirmmodal-but="ok">Ok</button>
        </div>
    </div>


    <div class="exampleLive">
        <button style="display: none;" id="minimumDelivAmount"
            class="btn btn-primary"
            data-confirmmodal-bind="#confirm_content_minimumAmount"
            data-topoffset="0" data-top="10%">Example</button>
    </div>
    <div id="confirm_content_minimumAmount" style="display: none">
        <div class="confirmModal_content sd-popup-content">
            <img src="resources/img/logo.png" class="sd-popup-logo">
            <h3 style="font-size: 14px;" id="mToDelAmount"></h3>
        </div>
        <div class="confirmModal_footer">
            <button type="button" class="btn btn-primary"
                data-confirmmodal-but="ok">Ok</button>
        </div>
    </div>

    <!-- Session time out pop up after 15min -->
    <div class="exampleLive">
        <button style="display: none;" id="confirmModal_sessionTimeOut"
            class="btn btn-primary"
            data-confirmmodal-bind="#confirm_content_sessionTimeOut"
            data-topoffset="0" data-top="10%">Example</button>
    </div>
    <div id="confirm_content_sessionTimeOut" style="display: none">
        <div class="confirmModal_content sd-popup-content">
            <img src="resources/img/logo.png" class="sd-popup-logo">
            <h3 style="font-weight: 600; font-size: 15px;">Your session has
                been timed out. Please log back in</h3>
        </div>
        <div class="confirmModal_footer">
            <button type="button" class="btn btn-primary"
                onclick="customerSessionTimeOut()">Ok</button>
        </div>
    </div>

    <!-- Minimum delivery amount message -->
    <div class="exampleLive">
        <button style="display: none;" id="minimumDelAmountPopup"
            class="btn btn-primary"
            data-confirmmodal-bind="#confirm_content_minimumDelAmountSucc"
            data-topoffset="0" data-top="10%">Example</button>
    </div>
    <div id="confirm_content_minimumDelAmountSucc" style="display: none">
        <div class="confirmModal_content sd-popup-content">
            <img src="resources/img/logo.png" class="sd-popup-logo">
            <h3 style="font-size: 14px;" id="minDAmountPop"></h3>
        </div>
        <div class="confirmModal_footer">
            <button type="button" class="btn btn-primary"
                data-confirmmodal-but="ok">Ok</button>
        </div>
    </div>
    
    <div class="exampleLive">
        <button style="display: none;" id="orderError"
            class="btn btn-primary"
            data-confirmmodal-bind="#confirm_content_orderError"
            data-topoffset="0" data-top="10%">Example</button>
    </div>
    <div id="confirm_content_orderError" style="display: none">
        <div class="confirmModal_content sd-popup-content">
            <img src="resources/img/logo.png" class="sd-popup-logo">
            <h3 style="font-weight: 600; font-size: 15px;">Your order fail , please try again.</h3>
        </div>
        <div class="confirmModal_footer">
            <button type="button" class="btn btn-primary"
                data-confirmmodal-but="ok">Ok</button>
        </div>
    </div>
    <script>
var finalTotal=0;
var finalOrderTotal=0;
var finalTotalWithTip=0;
var tipAmt=0;
var subTotal=0;
var totalTax=0;
var timeOut=60000;
var delvryFeeStatus=0;
var addDeliveryAddressDropDownStatus =0;
var customerId="";
var customerPswd="";
var customeraddress="";
var timerVal;
var isDeliveryKoupon=false;
var demoArray = new Array();
var cartArray={};
var couponStatus=0;
var kouponCount=0;
var itemsForDiscount="";
var listOfALLDiscounts="";
document.getElementById("listOfAllDiscounts").value = listOfALLDiscounts;
var newTaxWithCoupon=0;
document.getElementById("deliveryFeeFlag").value = isDeliveryKoupon;

var taxNameAndItemPrice = new Map();
if(sessionCutId!=""){
/* timerVal=setTimeout(function(){ 
       jQuery(function(){
         jQuery('#confirmModal_sessionTimeOut').click();
     });
 }  , timeOut); */
}

$(document).on("keyup", ".call_caller_phone", function() {
    var val = this.value.replace(/\D/g,'');
    var newVal = '';
    while (val.length > 3) {
    if (val.length == 10) {
    newVal += val.substr(0, 4);
    return false;
    } else {
    newVal += val.substr(0, 3) + '-';
    val = val.substr(3);
    }
    }
    newVal += val;
    this.value = newVal;
  });	
  
  
  
  
function customerSessionTimeOut(){
     window.location.href = "cancelAccountRequest";
}
    $(document).ready(function() {
    	
    	if(merchantId==280){
    		document.body.style.backgroundImage = "url(resources/kritiq/img/sweet.jpg)";
    		document.body.style.backgroundSize = "100%";
    		document.body.style.backgroundAttachment = "fixed";
    		document.body.style.backgroundPosition = "center right";
    		/* $(".extra").css('background',"resources/kritiq/img/rest1.jpg");
    		$(".extra").css('background-size', "100%");
    		$(".extra").css('background-attachment', "fixed");
    		$(".extra").css('background-position', "center right"); */
    		
    	} 
    	
    	bgColor1='#68512D';
		 $(".categchangeMerchant").css('background-color', bgColor1);
    	
    	
    	
    	
    	if(merchantId==268){
    		 bgColor='#b8b308';
    		 
    	$(".place-order-button").find('a').css('background-color', bgColor);
    	$(".categAjaxCall").css('background-color', bgColor);
    	$(".yourCartAmount").css('background-color', bgColor);
    	$("#custH1").css('background-color', bgColor);
    	$("#your_cart").css('color', bgColor);
    	$(".logINCust").find('a').css({color: bgColor});
    	$(".loggedINCust").find('a').css({color: bgColor});
    	$(".btn.btn-primary").css('background-color', bgColor);
    	$(".btn.btn-primary").css('border-color', bgColor);
    	
    	$("#yesButton").css('background-color', bgColor);
    	
    	$("#loginPopUp").css('background-color', bgColor);
    	$("#forgotButtonButton").css('background-color', bgColor);
    	$("#loginButton").css('background-color', bgColor);
    	$("#forgotPassword").css('background-color', bgColor);
    	$("#forgotPassword_1").css('background-color', bgColor);
    	$("#couponbutton").css('background-color', bgColor);
    	$("#paymentMethodID").css('background-color', bgColor);
    	$("#menuItems").css('background-color', bgColor);
    	$("#registerButton").css('background-color', bgColor);
    	
    	$("#orderNowButton").css('background-color', bgColor);
    	$(".checkout-button").find('a').css('background-color', bgColor);
    	
    	
    	
    	
    	
    	}
    	if(lengthOfMerchant > 1){
    	 $(".zeroDiv").css("display", "none");
    	}
    	 
    	customerId= $("#customerId").val();
         customerPswd = $("#customerPswd").val();
         if(customerId!=""){
        	 $("#custH1").hide();
             $(".select-address").css("display", "none");
             $(".add-your-address").css("display", "none");
             $(".checkout-button").css("display", "none");
         
             $(".logINCust").css("display", "none");
             
         }
            $("#loginPassword").change(function() {
                 $("#errorBox").html("");
        });
            
         $("#custH1").click(function() {
             
            $("#secondDiv").css("display", "block");
            
            if(($('#delivery').is(':checked')) || ($('#pickup').is(':checked')) ){
                if($('#pickup').is(':checked')){
                    if(customerId!=""){
                        if(customerPswd=="" || customerPswd=="N"){
                            $("#custH1").show();
                            $(".customerInfo").css("display", "block");
                            $(".gustAdd").css("display", "none");
                        }else{
                         $("#custH1").hide();
                         $(".select-address").css("display", "none");
                         $(".add-your-address").css("display", "none");
                         $(".checkout-button").css("display", "none");
                         $(".customerInfo").css("display", "none");
                         $(".returning-guest-customer-selector").css("display", "none");
                        
                        }
                    }
                }
            }else{
                $(".firstDiv").css("display", "block");
                 $(".select-address").css("display", "none");
                 $(".thirdDiv").css("display", "none");
                 $(".guestCustomer").css("display", "none");
                 $(".returning-guest-customer-selector").css("display", "none");
                 $(".customerInfo").css("display", "none");
                 $(".returningCustomer").css("display", "none");
                $("#itemError").html("Please select order type");
            }
        });
         
      $("#paymentMethodID").click(function() {
            if(($('#delivery').is(':checked')) || ($('#pickup').is(':checked')) ){
                if($('#delivery').is(':checked')){
                    if($("#deliveryAddress").val()=='select' || $("#deliveryAddress").val()=='00'){
                        $(".secondDiv").css("display", "block");
                        $(".thirdDiv").css("display", "none");
                         $(".select-payment-method").css("display", "none");
                         $(".accordion-right").openSection(1);
                    }
                }
            }else{
                $(".firstDiv").css("display", "block");
                $(".select-address").css("display", "none");
                $(".thirdDiv").css("display", "none");
                $(".secondDiv").css("display", "none");
                $("#itemError").html("Please select order type");
            }
        });
        
        
        /* if(customerId!=""){
            $("#custH1").show();
            $(".secondDiv").css("display", "block");
            $(".customerInfo").css("display", "none");
        } */
        $( ".quantityCls" ).keyup(function() {
              $( "div" ).remove( ".disDiv" );
        });
        
        $(".accordion").accordion();
        
        $('.accordion-right .drawer:eq(1)').append('<div class="disDiv" style="position: absolute;top:0;left:0;width: 100%;height:100%;z-index:-1;opacity:0.4;filter: alpha(opacity = 50)"></div>');
        $('.accordion-right .drawer:eq(2)').append('<div class="disDiv" style="position: absolute;top:0;left:0;width: 100%;height:100%;z-index:-1;opacity:0.4;filter: alpha(opacity = 50)"></div>');
        
   function orderForPickup(){

   
       
   	$("#orderLaterError").html("");
       if(sessionCutId!=""){
           /* clearTimeout(timerVal);
           timerVal=setTimeout(function(){ 
                  jQuery(function(){
                    jQuery('#confirmModal_sessionTimeOut').click();
                });
            }  , timeOut); */
       }
       
       if(subTotal == 0.00){
       $("#itemError").html("Please select atleast one item.");
    	   $(".select-payment-method").css("display", "none");
       }else{
       
        $("#couponError").html("");
       if(demoArray.length == 0){
           $(this).prop('checked', false);
           $("#itemError").html("Please select atleast one item.");
       }else{
       	var status=true;
       	if($('#orderlater').is(':checked')){
          	 var futurdate=$("#futureOrderDateCombo").val();
          	var futureTime=$('#futureOpeingTime').val();
          	 if(futurdate=='' || futurdate=='select'){
          		 $("#futureOrderDateCombo").focus();
          		$("#orderLaterError").html("Please select date.");
          		$(this).prop('checked', false);
          		status=false;
          	 }else if(futureTime=='' || futureTime=='select'){
          		 $("#futureOpeingTime").focus();
          		$("#orderLaterError").html("Please select time.");
          		$(this).prop('checked', false);
          		status=false;
          	 }else{
          		 status=true; 
          	 }
               
          }
       	
       	if(status==true){
       		// $(".firstDiv").css("display", "block");
           $(".firstDiv").css("display", "none");
           if(customerId==""){
           $(".accordion-right").openSection(1);
               $(".add-your-address").css("display", "none");
               $(".customerInfo").css("display", "block");  
              }
               $("#orderType").val("Pickup");
               $( "div" ).remove( ".disDiv" );
               $("#couponError").html("");
               $("#itemError").html("");
               if(delvryFeeStatus==1 && deliveryItemPosId!=""){
                    if(deliveryTaxStatus==1){
                         /*  finalTotal=finalTotal-parseFloat(deliveryTaxPrice); 
                         totalTax=totalTax-parseFloat(deliveryTaxPrice);  */
                         for (var i=1;i<deliveryFeeTaxWithCommaArray.length;i++) {
                             if(taxNameAndItemPrice.has(deliveryFeeTaxWithCommaArray[i])){
                                    var itemCurrentPrice= taxNameAndItemPrice.get(deliveryFeeTaxWithCommaArray[i]);
                                    var currentSum=itemCurrentPrice+parseFloat(deliveryItemPrice);
                                    taxNameAndItemPrice.set(deliveryFeeTaxWithCommaArray[i],currentSum);
                                }else{
                                   taxNameAndItemPrice.set(deliveryFeeTaxWithCommaArray[i],parseFloat(deliveryItemPrice));
                                }
                             }
                         
                         finalTotal=parseFloat(finalTotal)-parseFloat(totalTax);
                         totalTax=0;
                         var taxSum=0;
                         for (var key of taxNameAndItemPrice.keys()) {
                              var taxVa=merchtTaxMap.get(key);
                              var subTax=0;
                              subTax= ((taxVa / 100)*taxNameAndItemPrice.get(key)).toFixed(2);
                              taxSum=parseFloat(taxSum)+parseFloat(subTax);
                          }
                         totalTax=taxSum;
                         finalTotal=finalTotal+parseFloat(totalTax);
                     
                    }
                    
                 finalTotal=finalTotal-parseFloat(deliveryItemPrice);
                 $("#taxSpanId").html("$"+totalTax.toFixed(2));
                 $("#totalSpanId").html("$"+finalTotal.toFixed(2));
                 $("#totalSpanId1").html("$"+finalTotal.toFixed(2));
                 $(".yourCartAmount").html("Your Cart &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; $"+finalTotal.toFixed(2))
                 $("#deliveryFeeLiId").css("display","none");
                 $("#deliveryFeeSpanId").html("$"+deliveryItemPrice);
                 delvryFeeStatus=0;
               }
           if(customerId!=""){
               if(customerPswd=="" ||customerPswd=="N"){
                   $("#custH1").show();
                   $(".customerInfo").css("display", "block");
                   $(".gustAdd").css("display", "none");
                   
               }else{
                $("#custH1").hide();
                $(".select-address").css("display", "none");
                $(".add-your-address").css("display", "none");
                $(".checkout-button").css("display", "none");
               
               }
                ///$(".customerInfo").css("display", "none");
                //$(".returningCustomer").css("display", "none");
                //$(".loggedIncustomerInfo").css("display", "block");
               $(".accordion-right").openSection(2);
               $(".select-payment-method").css("display", "block");
                var paymentModd=$(".selMod").val();
                if(paymentModd=='Cash'){
                    $(".comLi").hide();
                }
                if(paymentModd=='Credit Card'){
                    $(".comLi").show();
                }
                $(".select-payment-method").css("display", "block");
           }
       }
       }
   }
   }
   
   function orderForDelivery(){
	   

   	
       
       $("#couponError").html("");
       $("#orderLaterError").html("");
           if(sessionCutId!=""){
            /* clearTimeout(timerVal);
            timerVal=setTimeout(function(){ 
                   jQuery(function(){
                     jQuery('#confirmModal_sessionTimeOut').click();
                 });
             }  , timeOut); */
           }
        if(demoArray.length == 0){
                 $(this).prop('checked', false);
                 $("#itemError").html("Please select atleast one item.");
            }else{
            	$("#orderLaterError").html("");
            	var status=true;
            	if($('#orderlater').is(':checked')){
               	 var futurdate=$("#futureOrderDateCombo").val();
               	var futureTime=$('#futureOpeingTime').val();
               	 if(futurdate=='' || futurdate=='select'){
               		 $("#futureOrderDateCombo").focus();
               		$("#orderLaterError").html("Please select date.");
               		$(this).prop('checked', false);
               		status=false;
               	 }else if(futureTime=='' || futureTime=='select'){
               		 $("#futureOpeingTime").focus();
               		$("#orderLaterError").html("Please select time.");
               		$(this).prop('checked', false);
               		status=false;
               	 }else{
               		 status=true; 
               	 }
                    
               }
            	
            	if(status==true){
                
                 $.ajax({
                     url : "checkMinDeliveryAmount?subTotalAmount="+ subTotal,
                     type : "GET",
                     contentType : "application/json; charset=utf-8",
                     success : function(minAmountData) {
                       if(minAmountData==0){
                    	   $(".accordion-right").openSection(1);
                           $(".accordion-right").openSection(3);
                           $(".accordion-right").openSection(1);
                             
                             
                             $(".customerInfo").css("display", "block");
                             $("#orderType").val("Delivery");
                             $("div").remove( ".disDiv" );
                             
                             if($('#guest-checkout').is(':checked')){
                                 $(".gustAdd").css("display", "block");
                                 $("#errorBox").html("");
                             }
                             
                             if(delvryFeeStatus==0 && deliveryItemPosId!=""){
                                 if(deliveryTaxStatus==1){
                                     /* finalTotal=finalTotal+parseFloat(deliveryTaxPrice); 
                                     totalTax=totalTax+parseFloat(deliveryTaxPrice);  */
                                  for (var i=1;i<deliveryFeeTaxWithCommaArray.length;i++) {
                                      if(taxNameAndItemPrice.has(deliveryFeeTaxWithCommaArray[i])){
                                             var itemCurrentPrice= taxNameAndItemPrice.get(deliveryFeeTaxWithCommaArray[i]);
                                             var currentSum=itemCurrentPrice+parseFloat(deliveryItemPrice);
                                             taxNameAndItemPrice.set(deliveryFeeTaxWithCommaArray[i],currentSum);
                                         }else{
                                            taxNameAndItemPrice.set(deliveryFeeTaxWithCommaArray[i],parseFloat(deliveryItemPrice));
                                         }
                                      }
                                  
                                  finalTotal=parseFloat(finalTotal)-parseFloat(totalTax);
                                  totalTax=0;
                                  var taxSum=0;
                                  for (var key of taxNameAndItemPrice.keys()) {
                                       var taxVa=merchtTaxMap.get(key);
                                       var subTax=0;
                                       subTax= ((taxVa / 100)*taxNameAndItemPrice.get(key)).toFixed(2);
                                       taxSum=parseFloat(taxSum)+parseFloat(subTax);
                                   }
                                  totalTax=taxSum;
                                  finalTotal=finalTotal+parseFloat(totalTax);
                                 }
                              
                              finalTotal=finalTotal+parseFloat(deliveryItemPrice);
                              $("#taxSpanId").html("$"+totalTax.toFixed(2));
                              $("#totalSpanId").html("$"+finalTotal.toFixed(2));
                              $("#totalSpanId1").html("$"+finalTotal.toFixed(2));
                              $(".yourCartAmount").html("Your Cart &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; $"+finalTotal.toFixed(2))
                              $("#deliveryFeeLiId").css("display","block");
                              $("#deliveryFeeSpanId").html("$"+deliveryItemPrice);
                              delvryFeeStatus=1;
                            }
                             
                             $("#couponError").html("");
                             $("#itemError").html("");
                             var chkCustomerId='${address}';
                             if(customerId!="" && chkCustomerId!="NoData"){
                                 
                                $("#custH1").show();
                                $(".customerInfo").css("display", "none");
                                $(".returningCustomer").css("display", "none");
                                $(".select-address").css("display", "block");
                                
                                var addressArray='${address}';
                                 if(addDeliveryAddressDropDownStatus==0){
                                $('<option value=select >Select Delivery Address</option>').appendTo('#deliveryAddress'); 
                                $('<option value=00>Add A New Delivery Address</option>').appendTo('#deliveryAddress'); 
                                  for ( var i = 0, l = addressArray.length; i < l; i++ ) {
                                      $('<option value="' + addressArray[i].id + '" deliveItPosIdAttr="'+addressArray[i].deliveryPosId+'" deliveItFeeAttr="'+addressArray[i].deliveryFee+'">' + addressArray[i].address1+ " " + addressArray[i].address2 + '</option>').appendTo('#deliveryAddress');
                                  } 
                                   addDeliveryAddressDropDownStatus++;
                                } 
                            }
                             if(customerId!="" && chkCustomerId=="NoData"){
                                 $("#custH1").show();
                                 if(customerPswd=="" || customerPswd=="N"){
                                     $(".customerInfo").css("display", "block");
                                     $(".gustAdd").css("display", "block");
                                     
                                 }else{
                                 $(".customerInfo").css("display", "none");
                                 $(".returningCustomer").css("display", "none");
                                 $(".select-payment-method").css("display", "none");
                                 $(".secondDiv").css("display", "block")
                                 $(".select-address").css("display", "block");
                                 var addressArray=customeraddress;
                                  if(addDeliveryAddressDropDownStatus==0){ 
                                 $('<option value=select >Select Delivery Address</option>').appendTo('#deliveryAddress'); 
                                 $('<option value=00>Add A New Delivery Address</option>').appendTo('#deliveryAddress'); 
                                   for ( var i = 0, l = addressArray.length; i < l; i++ ) {
                                       $('<option value="' + addressArray[i].id + '" deliveItPosIdAttr="'+addressArray[i].deliveryPosId+'" deliveItFeeAttr="'+addressArray[i].deliveryFee+'">' + addressArray[i].address1+ " " + addressArray[i].address2 + '</option>').appendTo('#deliveryAddress');
                                   } 
                                    addDeliveryAddressDropDownStatus++;
                                  }
                                 }
                                 $(".accordion-right").openSection(2);
                                  var paymentModd=$(".selMod").val();
                                  if(paymentModd=='Cash'){
                                      $(".comLi").hide();
                                  }
                                  if(paymentModd=='Credit Card'){
                                      $(".comLi").show();
                                  }
                             }
                         }else{
                             $(".select-payment-method").css("display", "none");
                             $("#minDAmountPop").html("<b>Your order amount is less than the minimum delivery amount of $"+minAmountData+"</b>");
                             jQuery(function(){
                                 jQuery('#minimumDelAmountPopup').click();
                           })
                           $("#delivery").prop('checked', false);
                         }
                     },
                     error : function() {
                       alert("error");
                     }
                  })
               }
            }
   
   
   }
        if(allowFutureOrder==1){
    	
        	if(timingStatus == 'N')
    		{
    		 $('#ordernow').prop('disabled', true);
    		}
        	var orderType;
    	if($('#delivery').is(':checked')){
        	orderType="Delivery";
        }else{
        	orderType="Pickup";
        }
    	
    	$("#ordernow").on("click", function () {
    		 $("#futureOrderDateCombo").val("select"); 
    		$("#futureDateCmbo").css("display","none");
    		$('#futureOpeingTime').html('<option value="select">Select</option>');
    		 $("#orderLaterError").html("");
    	if(($('#delivery').is(':checked')) || ($('#pickup').is(':checked')) ){
    	
            if(orderType=="Pickup"){
            orderForPickup();
            }else if(orderType=="Delivery"){
            	 orderForDelivery();
            }
    	}else{
    		 $("#itemError").html("Please select Order Type");
    	
    	}
        });
    	
    	 $("#orderlater").on("click", function () {
    	        $("#futureDateCmbo").css("display","block");
    	        $("#orderLaterError").html("");
    	       /*  $("#delivery").prop('checked', false);
    	        $("#pickup").prop('checked', false); */
    	        $.ajax({
    	            url : "getFutureDates",
    	            type : "GET",
    	            contentType : "application/json; charset=utf-8",
    	            success : function(result) {
    	             $('#futureOrderDateCombo').html('<option value="select">Select</option>'); 
    	                for ( var i = 0, l = result.length; i < l; i++ ) {
    	                    $('<option value="' + result[i] +'">' + result[i] + '</option>').appendTo('#futureOrderDateCombo');
    	                }
    	            },
    	            error : function() {
    	              console.log("Error inside future date Ajax call");
    	            }
    	         })
    	        
    	       
    	    }); 
    	 $("#futureOpeingTime").on("change", function () {
    		 if(($('#delivery').is(':checked')) || ($('#pickup').is(':checked')) ){
    		 if(orderType=="Pickup"){
             orderForPickup();
             }else if(orderType=="Delivery"){
             	 orderForDelivery();
             }
    		 }else{
        		 $("#itemError").html("Please select Order Type");
        	
        	}
    	 }); 
   
    }else{ 
    	
    	$("#delivery").click(function() {
    		orderForDelivery();	
    	});
    	
        $("#pickup").click(function() {
        	orderForPickup();
        }); 
    }   
        $("#returning-customer").click(function() {
              $(".guestCustomer").css("display", "none");
              $(".returningCustomer").css("display", "block");
              if(customerId!=""){
                  if(customerPswd=="" || customerPswd=="N"){
                     $(".select-payment-method").css("display", "none");
                  }
              }
              $("#errorBox").html("");  
        });
        $("#guest-checkout").click(function() {
              $(".returningCustomer").css("display", "none");
              $(".guestCustomer").css("display", "block");
              if(customerId!=""){
                  if(customerPswd=="" || customerPswd=="N"){
                     $(".select-payment-method").css("display", "block");
                  }
              }
              if($('#delivery').is(':checked')){
                  $(".gustAdd").css("display", "block");
              }
              
              $("#errorBox").html("");
        });
        $("#deliveryAddress").change(function() {
          if(sessionCutId!=""){     
            /* clearTimeout(timerVal);
                timerVal=setTimeout(function(){ 
                       jQuery(function(){
                         jQuery('#confirmModal_sessionTimeOut').click();
                     });
                 }  , timeOut); */
          }
            var addNew=$(this).val();
            if(addNew=='00'){
               $(".add-your-address").css("display", "block");
               $(".checkout-button").css("display", "block");
            }else{
                 $.ajax({
                     url : "checkDeliveryAfterLogin?addressId=" + addNew+"&isDeliveryKoupon="+isDeliveryKoupon,
                     type : "GET",
                     contentType : "application/json; charset=utf-8",
                     success : function(data) {
                         if (data.message == "Your zone is in delivery zone") {
                             if(parseFloat(subTotal) >= parseFloat(data.minimumDeliveryAmount)){
                                 
                                  var sel = document.getElementById("deliveryAddress");
                                  var selected = sel.options[sel.selectedIndex];
                                  var posIId=selected.getAttribute("deliveitposidattr");
                                  if(isDeliveryKoupon == true){
                                	  var deliPrice=0;
                                  }
                                  
                                  else{
                                  var deliPrice=selected.getAttribute("deliveitfeeattr");
                                  }
                                  console.log("---------"+posIId+deliPrice);
                                  if (typeof posIId !== "undefined" && typeof deliPrice !== "undefined") {
                                      deliveryItemPosId= posIId;
                                      deliveryItemPrice= deliPrice;
                                      deliveryTaxStatus=data.deliveryTaxStatus;
                                      deliveryTaxPrice=data.deliveryTaxPrice;
                                      avgDeliveryTime=data.avgDeliveryTime;
                                      
                                      if(deliveryFeeCheckStatus==0 && deliveryFreeCheckValue==""){
                                          if(deliveryTaxStatus==1){
                                              /* finalTotal=finalTotal+parseFloat(deliveryTaxPrice); 
                                              totalTax=totalTax+parseFloat(deliveryTaxPrice); */ 
                                           var deliWCTax=data.deliveryTaxWithComma;
                                           console.log("--------"+deliWCTax);
                                           deliveryFeeTaxWithCommaArray= deliWCTax.split(",");
                                           for (var i=1;i<deliveryFeeTaxWithCommaArray.length;i++) {
                                               if(taxNameAndItemPrice.has(deliveryFeeTaxWithCommaArray[i])){
                                                      var itemCurrentPrice= taxNameAndItemPrice.get(deliveryFeeTaxWithCommaArray[i]);
                                                      var currentSum=itemCurrentPrice+parseFloat(deliveryItemPrice);
                                                      taxNameAndItemPrice.set(deliveryFeeTaxWithCommaArray[i],currentSum);
                                                  }else{
                                                     taxNameAndItemPrice.set(deliveryFeeTaxWithCommaArray[i],parseFloat(deliveryItemPrice));
                                                  }
                                               }
                                           
                                           finalTotal=parseFloat(finalTotal)-parseFloat(totalTax);
                                           totalTax=0;
                                           var taxSum=0;
                                           for (var key of taxNameAndItemPrice.keys()) {
                                                var taxVa=merchtTaxMap.get(key);
                                                var subTax=0;
                                                subTax= ((taxVa / 100)*taxNameAndItemPrice.get(key)).toFixed(2);
                                                taxSum=parseFloat(taxSum)+parseFloat(subTax);
                                            }
                                           totalTax=taxSum;
                                           finalTotal=finalTotal+parseFloat(totalTax);
                                           
                                           deliveryFreeCheckValue=deliWCTax;
                                          }
                                       finalTotal=finalTotal+parseFloat(deliveryItemPrice);
                                       
                                       deliveryFeeCheckStatus=1;
                                       deliveryMfee=deliveryItemPrice;
                                      }else{
                                        if(deliveryFreeCheckValue!=""){
                                          var taxMinusPrice=0;
                                          txItemNm= deliveryFreeCheckValue;
                                          if(txItemNm.indexOf(",")>-1){
                                              var txKeys=[];
                                              txKeys=txItemNm.split(",");
                                              for(var k=1;k<txKeys.length;k++){
                                                  taxTotalMinusPrice=taxNameAndItemPrice.get(txKeys[k]);
                                                  var rmingnTaxValue=parseFloat(taxTotalMinusPrice)-parseFloat(deliveryMfee);
                                                  taxNameAndItemPrice.set(txKeys[k],rmingnTaxValue);
                                              }
                                          }else{
                                              taxTotalMinusPrice=taxNameAndItemPrice.get(txItemNm);
                                              var rmingnTaxValue=parseFloat(taxTotalMinusPrice)-parseFloat(deliveryMfee);
                                              taxNameAndItemPrice.set(txItemNm,rmingnTaxValue);
                                          }
                                        }   
                                        finalTotal=parseFloat(finalTotal)-parseFloat(deliveryMfee);
                                          if(deliveryTaxStatus==1){
                                           var deliWCTax=data.deliveryTaxWithComma;
                                           console.log("--------"+deliWCTax);
                                           deliveryFeeTaxWithCommaArray= deliWCTax.split(",");
                                           for (var i=1;i<deliveryFeeTaxWithCommaArray.length;i++) {
                                               if(taxNameAndItemPrice.has(deliveryFeeTaxWithCommaArray[i])){
                                                      var itemCurrentPrice= taxNameAndItemPrice.get(deliveryFeeTaxWithCommaArray[i]);
                                                      var currentSum=itemCurrentPrice+parseFloat(deliveryItemPrice);
                                                      taxNameAndItemPrice.set(deliveryFeeTaxWithCommaArray[i],currentSum);
                                                  }else{
                                                     taxNameAndItemPrice.set(deliveryFeeTaxWithCommaArray[i],parseFloat(deliveryItemPrice));
                                                  }
                                               }
                                           
                                           finalTotal=parseFloat(finalTotal)-parseFloat(totalTax);
                                           totalTax=0;
                                           var taxSum=0;
                                           for (var key of taxNameAndItemPrice.keys()) {
                                                var taxVa=merchtTaxMap.get(key);
                                                var subTax=0;
                                                subTax= ((taxVa / 100)*taxNameAndItemPrice.get(key)).toFixed(2);
                                                taxSum=parseFloat(taxSum)+parseFloat(subTax);
                                            }
                                           totalTax=taxSum;
                                           finalTotal=finalTotal+parseFloat(totalTax);
                                           deliveryFreeCheckValue=deliWCTax;
                                          }
                                       finalTotal=finalTotal+parseFloat(deliveryItemPrice);
                                       deliveryFeeCheckStatus=1;
                                       deliveryMfee=deliveryItemPrice;
                                      }
                                      // Start- Add delivery fee and tax
                                      $("#taxSpanId").html("$"+totalTax.toFixed(2));
                                      $("#totalSpanId").html("$"+finalTotal.toFixed(2));
                                      $("#totalSpanId1").html("$"+finalTotal.toFixed(2));
                                      $(".yourCartAmount").html("Your Cart &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; $"+finalTotal.toFixed(2))
                                      $("#deliveryFeeLiId").css("display","block");
                                      $("#deliveryFeeSpanId").html("$"+deliveryItemPrice);
                                      delvryFeeStatus=1;
                                      //End
                                  }
                                   console.log(deliveryItemPosId+"--"+deliveryItemPrice);
                                   $(".add-your-address").css("display", "none");
                                   $(".checkout-button").css("display", "none");
                                   var paymentModd=$(".selMod").val();
                                   if(paymentModd=='Cash'){
                                       $(".comLi").hide();
                                   }
                                   if(paymentModd=='Credit Card'){
                                       $(".comLi").show();
                                   }
                                   $(".select-payment-method").css("display", "block");
                                   $(".accordion-right").openSection(2);
                             
                               }else{
                                   $(".select-payment-method").css("display", "none");
                                   $("#mToDelAmount").html("<b>Your order amount is less than the minimum delivery amount of $"+data.minimumDeliveryAmount+"</b>");
                                   jQuery(function(){
                                       jQuery('#minimumDelivAmount').click();
                               })
                             }
                         }else {
                           jQuery(function(){
                                jQuery('#deliveryFailurePopUp').click();
                           })
                        }
                     },
                     error : function() {
                         console.log("Error in check delivery");
                     }
                   })
            }
      });
        var count=0;
     var itemPosId;
     var itemPrice;
     var itemId;
     var itemName;
     var itemSum;
     var modifierSum=0;
     var itemTax;
     var finalItem="";
    
     var copItemPriceArray = new Array();
     var copTaxItemArray = new Array();
     var i=0;
     var nwI=0;
     var couponDiscount=0;
     var discountType="";
     
     var voucherCode="";
     var inventoryLevel ="";
     var itemPoSidsJson="";
     var modGroupLimitStatus=0;
     var modGroupLimit=0;
     
     
     var convenienceFeeTaxStatus=0;
     var txItemNm="";
     
     $(document).on('click', "li.itemLi", function() {
         itemPosId=$(this).attr("itmPosId");
         itemPrice=$(this).attr("itemPrice");
         itemId=$(this).attr("idItm");
         itemName=$(this).attr("itemNameAttr");
         itemTax=$(this).attr("taxV");
         modGroupLimitStatus=$(this).attr("modifierLimitStatusAttr");
         txItemNm=$(this).attr("itemTaxNames");
      }); 
     
     $(document).on('click', "a.orderButton", function() {

    	 if(lengthOfMerchant > 1){
    		 $(".zeroDiv").css("display", "none");
    		// $(".accordion-right").openSection(1);
    	// $(".firstDiv").css("display", "block");
    	 
    	 
    	 }
    	 
    	 couponDiscount=0;
    	 kouponCount=0; 
    	 voucherCode="";
    	 dicountType="";
    	 $("#coupon").attr("disabled", false);
		 $("#couponbutton").attr("disabled", false);
    	 
    	 $("#discountSpanId").html("$0.00");
         if(sessionCutId!=""){ 
         /* clearTimeout(timerVal);
          timerVal=setTimeout(function(){ 
            jQuery(function(){
               jQuery('#confirmModal_sessionTimeOut').click();
            });
           }  , timeOut); */
         }
         $("#itemError").html("");
         $(".modiGrpLimitError").html("");
         $("#couponError").html("");
         $(".couponCodeText").val("");
         var ordTypeForCheck=$("#orderType").val();
         if(ordTypeForCheck==""){
             $('.accordion-right .drawer:eq(1)').append('<div class="disDiv" style="position: absolute;top:0;left:0;width: 100%;height:100%;z-index:-1;opacity:0.4;filter: alpha(opacity = 50)"></div>');
             $('.accordion-right .drawer:eq(2)').append('<div class="disDiv" style="position: absolute;top:0;left:0;width: 100%;height:100%;z-index:-1;opacity:0.4;filter: alpha(opacity = 50)"></div>');
         }
         var itemQuantity=$("#qty_"+itemId).val();
         if(itemQuantity!="" && itemQuantity!=0 && itemQuantity.length <3 && itemQuantity.indexOf('.') == -1){
             
         var itemArray={};
         //var cartArray={};
         var modifierPosIdArray = [];
         var modifierNameArray = [];
         var modifierPriceArray = [];
         var modifierArray=[];
         $('.item-in-popup input:checked').each(function() {
             var modifierJson={};
             modifierPosIdArray.push($(this).attr('modifierPosIdAttr'));
             modifierNameArray.push($(this).attr('modifierNameAttr')+":&nbsp$"+$(this).attr('modifierPriceAttr'));
             modifierPriceArray.push($(this).val());
             
             modifierSum=parseFloat(modifierSum)+parseFloat($(this).val());
             modifierJson["id"]=$(this).attr('modifierPosIdAttr');
             modifierJson["price"]=$(this).val();
             modifierArray.push(modifierJson);
         });
         $('.item-in-popup input:checked').each(function() {
             $(this).prop('checked', false);
         });
         
         $("#conveniceLiId").css("display","block");
         var itemRemove=(parseFloat(itemPrice)+parseFloat(modifierSum))*itemQuantity
         var itemTaxRemove=(itemTax / 100)*itemRemove;
         
         if(txItemNm.indexOf(",")>-1){
              var txKeys=[];
              txKeys=txItemNm.split(",");
              
              for(var k=1;k<txKeys.length;k++){
                 if(taxNameAndItemPrice.has(txKeys[k])){
                     var itemCurrentPrice= taxNameAndItemPrice.get(txKeys[k]);
                     var currentSum=itemCurrentPrice+itemRemove;
                     taxNameAndItemPrice.set(txKeys[k],currentSum);
                 }else{
                     taxNameAndItemPrice.set(txKeys[k],itemRemove);
                 }
              }
          }else{
             if(taxNameAndItemPrice.has(txItemNm)){
                 var itemCurrentPrice= taxNameAndItemPrice.get(txItemNm);
                 var currentSum=itemCurrentPrice+itemRemove;
                 taxNameAndItemPrice.set(txItemNm,currentSum);
             }else{
                taxNameAndItemPrice.set(txItemNm,itemRemove);
             }
          }
         
         copItemPriceArray.push(itemRemove);
         copTaxItemArray.push(itemTax);
         console.log("----------i-------------"+i)
         var html ="<ul><li class='item-name'>"+itemName+"</li><li class='item-quantity'>"+itemQuantity+"</li><li class='item-price'>"+"$"+itemPrice+"</li> <small>"+modifierNameArray+"</small><div class='remove-from-cart'  removeItemtaxNames='"+txItemNm+"'  itemRemoveAtt="+itemRemove+" itemRemoveTax="+itemTaxRemove+" itemRemovePosId="+itemPosId+" arrayIndexAtrr="+nwI+"><span>x</span></div></ul>";
         $(".items-names-prices").append(html);
         
         
         var taxSum=0;
         for (var key of taxNameAndItemPrice.keys()) {
              var taxVa=merchtTaxMap.get(key);
              var subTax=0;
              subTax= ((taxVa / 100)*taxNameAndItemPrice.get(key)).toFixed(2);
              taxSum=parseFloat(taxSum)+parseFloat(subTax);
          }
         
         subTotal=parseFloat(subTotal)+((parseFloat(itemPrice)+parseFloat(modifierSum))*itemQuantity);
         modifierSum=0;
         console.log("itemTax----"+itemTax);
         
         totalTax=taxSum;
        // totalTax=parseFloat(totalTax)+((itemTax / 100)*itemRemove);
         console.log("totalTax--"+totalTax);
         
         $("#subTotalSpanId").html("$"+(subTotal).toFixed(2));
         if(isConvenienceFeeStatus>0){
             if(convenienceFeeTaxWithCommaArray.length >=2){
                 /* console.log("convenienceFeeTax----"+convenienceFeeTax);
                 totalTax=parseFloat(totalTax)+parseFloat(convenienceFeeTax); */
                 if(convenienceFeeTaxStatus==0){
                 for (var i=1;i<convenienceFeeTaxWithCommaArray.length;i++) {
                      if(taxNameAndItemPrice.has(convenienceFeeTaxWithCommaArray[i])){
                             var itemCurrentPrice= taxNameAndItemPrice.get(convenienceFeeTaxWithCommaArray[i]);
                             var currentSum=itemCurrentPrice+parseFloat(convenienceFeePrice);
                             taxNameAndItemPrice.set(convenienceFeeTaxWithCommaArray[i],currentSum);
                         }else{
                            taxNameAndItemPrice.set(convenienceFeeTaxWithCommaArray[i],parseFloat(convenienceFeePrice));
                         }
                      }
                   }
                      totalTax=0;
                      var taxSum=0;
                      for (var key of taxNameAndItemPrice.keys()) {
                           var taxVa=merchtTaxMap.get(key);
                           var subTax=0;
                           subTax= ((taxVa / 100)*taxNameAndItemPrice.get(key)).toFixed(2);
                           taxSum=parseFloat(taxSum)+parseFloat(subTax);
                       }
                      totalTax=taxSum;
                 convenienceFeeTaxStatus++;
             }
         }
         console.log("----------="+totalTax);
         $("#taxSpanId").html("$"+(totalTax).toFixed(2));
         finalTotal=subTotal+parseFloat(totalTax);
         if(isConvenienceFeeStatus>0){
             finalTotal=finalTotal+parseFloat(convenienceFeePrice);
         }
         
         $(".yourCartAmount").html("Your Cart &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; $"+finalTotal.toFixed(2))
         $("#totalSpanId").html("$"+finalTotal.toFixed(2));
         $("#totalSpanId1").html("$"+finalTotal.toFixed(2));
         itemArray["itemid"] = itemPosId;
         itemArray["amount"] = itemQuantity;
         itemArray["price"] = itemPrice;
         itemArray["modifier"]=modifierArray;
         cartArray["tax"]=(totalTax).toFixed(2);
         cartArray["subtotal"]=(subTotal).toFixed(2);
         cartArray["total"]=finalTotal.toFixed(2);
         cartArray["convFee"]=0;//parseFloat(convenienceFeePrice);
         cartArray["deliveryFee"]=deliveryItemPrice;
         tipAmt=$('#tipAmount').val();
         if(tipAmt!=""){
        	 cartArray["tipAmount"]=0;
         }else{
        	 cartArray["tipAmount"]=Number(tipAmt);
         }	 
        
         
         finalItem=finalItem+","+JSON.stringify(itemArray);
         
         demoArray.push(itemArray);
         console.log("+++++++++++++++"+JSON.stringify(demoArray));
         
         if(couponDiscount>0){
             console.log("=====copItemPriceArray===="+copItemPriceArray);    
             
             console.log("-----copTaxItemArray------"+copTaxItemArray);     
             var removeRecalculateTax=0;
             var removeRecalculatingSubtotal=0;
             for (i = 0; i < copItemPriceArray.length; i++) { 
                 removeRecalculatingSubtotal=removeRecalculatingSubtotal+copItemPriceArray[i];
             }
             console.log("444444444_"+removeRecalculatingSubtotal);
             subTotal=removeRecalculatingSubtotal;
             for (i = 0; i < copItemPriceArray.length; i++) { 
                 var nwTax=parseFloat(copItemPriceArray[i])-(parseFloat(couponDiscount)*parseFloat(copItemPriceArray[i])/subTotal);
                 console.log("-----copItemPriceArray[i]------"+parseFloat(copItemPriceArray[i]));   
                 console.log("-----copTaxItemArray[i]------"+parseFloat(copTaxItemArray[i]));   
                 console.log("----removeRecalculateTax------"+removeRecalculateTax);    
                 var newTaxValue=(parseFloat(copTaxItemArray[i]) / 100)*nwTax;
                 console.log("-----newTaxValue------"+newTaxValue.toFixed(2));  
                 removeRecalculateTax=parseFloat(removeRecalculateTax)+parseFloat((newTaxValue).toFixed(2));
                 console.log("-----newTaxWithCoupon------"+copItemPriceArray[i]+"-"+copTaxItemArray[i]+"-"+newTaxWithCoupon);   
              }  
             console.log("------removeRecalculateTax-----"+removeRecalculateTax);
             console.log("5555555555_"+subTotal);
             $("#subTotalSpanId").html("$"+subTotal.toFixed(2));
             newTaxWithCoupon=removeRecalculateTax;
             if(isConvenienceFeeStatus>0){
                 if(convenienceFeeTax>0 & convenienceFeeTaxStatus==0){
                     newTaxWithCoupon=parseFloat(newTaxWithCoupon)+parseFloat(convenienceFeeTax);
                 }
             }
             $("#taxSpanId").html("$"+(newTaxWithCoupon).toFixed(2));
             subTotal=subTotal-parseFloat(couponDiscount);
             
             totalTax=newTaxWithCoupon;
             
             finalTotal=parseFloat(subTotal)+parseFloat(totalTax);
             
             if(isConvenienceFeeStatus>0){
                 finalTotal=finalTotal+parseFloat(convenienceFeePrice);
             }
             console.log("+++----+++++___"+finalTotal);
             $(".yourCartAmount").html("Your Cart &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; $"+finalTotal.toFixed(2))
             $("#totalSpanId").html("$"+finalTotal.toFixed(2));
             $("#totalSpanId1").html("$"+finalTotal.toFixed(2));
         }
         
         nwI++;
         $("#qty_"+itemId).val("");
         
       }  
     }); 
     var modfrCheckCount=0;
    /*  $(".chkLimit").click(function() { */
    $(document).on("click",".chkLimit",function() {
         $(".modiGrpLimitError").html("");
         modGroupLimit=$(this).attr("modifierGroupLimitAttr");
     });
      /* $(".modifierChk").click(function() { */
      $(document).on("click",".modifierChk",function() {
         var clsAttr=$(this).attr("chkAttr");
         var modifierCount=0;
         if(modGroupLimitStatus==1){
             
             $('.ttt_'+clsAttr+' input:checked').each(function() {
                 modifierCount++;
             });
             if(modifierCount>modGroupLimit){
                 $(this).prop('checked', false);
                 $(".modiGrpLimitError").html("Modifier limit exceeded");
             }else{
                 if ($(this).prop('checked')==false){ 
                     $(".modiGrpLimitError").html("");
                  }
             }
         }
     }); 
     $('body').on('click', '.remove-from-cart', function () {
    	 kouponCount=0;    	 
    	 $('#coupon').prop('disabled', false);
		 $('#couponbutton').prop('disabled', false);
    	 couponDiscount=0;
         voucherCode="";
         dicountType="";
         $("#discountSpanId").html("$0.00");
    	 if(sessionCutId!=""){ 
            /*  clearTimeout(timerVal);
              timerVal=setTimeout(function(){ 
                jQuery(function(){
                   jQuery('#confirmModal_sessionTimeOut').click();
                });
               }  , timeOut); */
         }
         $(".select-payment-method").css("display", "none");
         $(".customerInfo").css("display", "none");
         $("#couponError").html("");
         $(".couponCodeText").val("");
         $("#delivery").prop('checked', false);
         $("#pickup").prop('checked', false);
             var removeItem=$(this).attr("itemRemoveAtt");
             txItemNm=$(this).attr("removeItemtaxNames");
             console.log("totalTax----"+totalTax)
             console.log("removeItem----"+txItemNm)
             console.log("-----subTotal----"+subTotal+"--removeItem--"+removeItem);
             var remainingSubtotal=(subTotal)-parseFloat(removeItem);
             console.log(removeItem+"--"+remainingSubtotal)
             
             //Tax calculation start
             var taxMinusPrice=0;
             if(txItemNm.indexOf(",")>-1){
                 var txKeys=[];
                 txKeys=txItemNm.split(",");
                 for(var k=1;k<txKeys.length;k++){
                     taxTotalMinusPrice=taxNameAndItemPrice.get(txKeys[k]);
                     console.log("-----taxTotalMinusPrice----"+taxTotalMinusPrice);
                     var rmingnTaxValue=parseFloat(taxTotalMinusPrice)-parseFloat(removeItem);
                     console.log("-----rmingnTaxValue----"+rmingnTaxValue);
                     taxNameAndItemPrice.set(txKeys[k],rmingnTaxValue);
                 }
             }else{
                 taxTotalMinusPrice=taxNameAndItemPrice.get(txItemNm);
                 console.log("-----taxTotalMinusPrice----"+taxTotalMinusPrice);
                 var rmingnTaxValue=parseFloat(taxTotalMinusPrice)-parseFloat(removeItem);
                 console.log("-----rmingnTaxValue----"+rmingnTaxValue);
                 taxNameAndItemPrice.set(txItemNm,rmingnTaxValue);
             }
             
             totalTax=0;
             var taxSum=0;
             for (var key of taxNameAndItemPrice.keys()) {
                  var taxVa=merchtTaxMap.get(key);
                  var subTax=0;
                  subTax= ((taxVa / 100)*taxNameAndItemPrice.get(key)).toFixed(2);
                  taxSum=parseFloat(taxSum)+parseFloat(subTax);
              }
            // totalTax=taxSum;
             //End
             
             //var removeTax=$(this).attr("itemRemoveTax");
             //var remainingTax=Math.abs(totalTax-parseFloat(removeTax));
             var remainingTax=taxSum;
             
             console.log("3333333333_"+taxSum+"--"+remainingTax)
             console.log("before remove copItemPriceArray-->"+JSON.stringify(copItemPriceArray));
             copItemPriceArray[$(this).attr("arrayIndexAtrr")]=0;
             // copItemPriceArray.removeByIndex($(this).attr("arrayIndexAtrr"));
             console.log("after remove copItemPriceArray-->"+JSON.stringify(copItemPriceArray));
             console.log("beforer remove copTaxItemArray-->"+JSON.stringify(copTaxItemArray));
             copTaxItemArray[$(this).attr("arrayIndexAtrr")]="0";
             //copTaxItemArray.removeByIndex($(this).attr("arrayIndexAtrr"));
             console.log("after remove copTaxItemArray-->"+JSON.stringify(copTaxItemArray));
            
             console.log("deleted item index -->"+$(this).attr("arrayIndexAtrr"));
             console.log("before remove item arrays"+JSON.stringify(demoArray));
             var fakeItemArray={};
             fakeItemArray["itemid"] = "ABCD";
             fakeItemArray["amount"] = "0";
             fakeItemArray["price"] = 0;
             fakeItemArray["modifier"]=[];
             //demoArray.removeByIndex($(this).attr("arrayIndexAtrr"));
             demoArray[$(this).attr("arrayIndexAtrr")]=fakeItemArray;
             console.log("after remove item arrays"+JSON.stringify(demoArray));
             if(remainingSubtotal==0 || copItemPriceArray.length==0){
                 $("#conveniceLiId").css("display","none");
                 $("#deliveryFeeLiId").css("display","none");
                 remainingTax=0;
                 couponStatus=0;
                 convenienceFeeTaxStatus=0;
                 newTaxWithCoupon=0;
                 copItemPriceArray=[];
                 copTaxItemArray=[];
                 i=0;
                 nwI=0;
                 taxNameAndItemPrice=new Map();
             }
             
             $("#subTotalSpanId").html("$"+remainingSubtotal.toFixed(2));
             $("#taxSpanId").html("$"+remainingTax.toFixed(2));
             subTotal=remainingSubtotal.toFixed(2);
             totalTax=remainingTax.toFixed(2);
             var conv=0;
             if(isConvenienceFeeStatus>0){
                 conv=parseFloat(convenienceFeePrice);
             }
             if(remainingSubtotal==0){
            	 conv=0.0;
             }
             finalTotal=remainingSubtotal+remainingTax+conv;
             $(".yourCartAmount").html("Your Cart &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; $"+finalTotal.toFixed(2))
             $("#totalSpanId").html("$"+finalTotal.toFixed(2));
             $("#totalSpanId1").html("$"+finalTotal.toFixed(2));
             console.log($(this).attr("arrayIndexAtrr"));
             $(this).parent().remove();
             
             console.log("-----demoArray[i]------"+demoArray.length);  
             console.log("-----copItemPriceArray[i]------"+copItemPriceArray);   
             console.log("-----copTaxItemArray[i]------"+copTaxItemArray);   
             if(couponDiscount>0){
                 var removeRecalculateTax=0;
                 var removeRecalculatingSubtotal=0;
                 for (i = 0; i < copItemPriceArray.length; i++) { 
                     removeRecalculatingSubtotal=removeRecalculatingSubtotal+copItemPriceArray[i];
                 }
                 console.log("444444444_"+removeRecalculatingSubtotal);
                 subTotal=removeRecalculatingSubtotal;
                 var conv=0;
                 for (i = 0; i < copItemPriceArray.length; i++) { 
                     var conv=0;
                     if(convenienceFeePrice>0)
                         conv= parseFloat(convenienceFeePrice)
                         
                     var nwTax=parseFloat(copItemPriceArray[i])-(parseFloat(couponDiscount)*parseFloat(copItemPriceArray[i])/(subTotal+conv));
                    /*  console.log("-----copItemPriceArray[i]------"+parseFloat(copItemPriceArray[i]));   
                     console.log("-----copTaxItemArray[i]------"+parseFloat(copTaxItemArray[i]));   
                     console.log("----removeRecalculateTax------"+removeRecalculateTax);    */ 
                     var newTaxValue=(parseFloat(copTaxItemArray[i]) / 100)*nwTax;
                     console.log("-----newTaxValue------"+newTaxValue.toFixed(2));  
                     removeRecalculateTax=parseFloat(removeRecalculateTax)+parseFloat((newTaxValue).toFixed(2));
                     console.log("-----newTaxWithCoupon------"+copItemPriceArray[i]+"-"+copTaxItemArray[i]+"-"+newTaxWithCoupon);   
                  }  
                // console.log("------removeRecalculateTax-----"+removeRecalculateTax);
                 //console.log("5555555555_"+subTotal);
                 /* console.log("copItemPriceArray------"+copItemPriceArray);
                 console.log("copTaxItemArray---------"+copTaxItemArray); */
                 
                 $("#subTotalSpanId").html("$"+subTotal.toFixed(2));
                 newTaxWithCoupon=removeRecalculateTax;
                 console.log("11111111_"+newTaxWithCoupon);
                 
                 if(isConvenienceFeeStatus>0){
                     if(convenienceFeeTax>0 & convenienceFeeTaxStatus==0){
                         newTaxWithCoupon=parseFloat(newTaxWithCoupon)+parseFloat(convenienceFeeTax);
                     }
                 }
                 if(subTotal==0){
                     removeRecalculateTax=0;
                     $("#conveniceLiId").css("display","none");
                     $("#deliveryFeeLiId").css("display","none");
                     newTaxWithCoupon=0;
                     totalTax=0;
                     newTaxValue=0;
                     convenienceFeeTaxStatus=0;
                     couponDiscount=0;
                     var discount =0;
                     $("#discountSpanId").html("$"+(discount).toFixed(2));
                     
                     $("#taxSpanId").html("$"+(newTaxWithCoupon).toFixed(2));
                     finalTotal=0;
                     $(".yourCartAmount").html("Your Cart &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; $"+finalTotal.toFixed(2))
                     $("#totalSpanId1").html("$"+finalTotal.toFixed(2));
                     $("#totalSpanId").html("$"+finalTotal.toFixed(2));
                 }else{
                 $("#taxSpanId").html("$"+(newTaxWithCoupon).toFixed(2));
                 subTotal=subTotal-parseFloat(couponDiscount);
                 totalTax=newTaxWithCoupon;
                 
                 finalTotal=parseFloat(subTotal)+parseFloat(totalTax);
                 if(isConvenienceFeeStatus>0){
                     finalTotal=finalTotal+parseFloat(convenienceFeePrice);
                 }
                 console.log("+++----+++++___"+finalTotal);
                 $(".yourCartAmount").html("Your Cart &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; $"+finalTotal.toFixed(2))
                 $("#totalSpanId1").html("$"+finalTotal.toFixed(2));
                 $("#totalSpanId").html("$"+finalTotal.toFixed(2)); }
             }
             checkR=$(this).attr("arrayIndexAtrr");
        });
      
        // $(".selMod").val("Credit Card");
         $(".selMod").change(function() {
           var paymentMod = this.value;
           if (paymentMod == 'Cash') {
             $(".comLi").hide();
           } else {
             $(".comLi").show();
           }
         });
  
        Array.prototype.removeByIndex = function(index) {
            this.splice(index, 1);
        }    
         $(".chkAgree").click(function() {
             if(sessionCutId!=""){ 
                 /* clearTimeout(timerVal);
                  timerVal=setTimeout(function(){ 
                    jQuery(function(){
                       jQuery('#confirmModal_sessionTimeOut').click();
                    });
                   }  , timeOut); */
                }      
               if ($("#i-agree").prop('checked') == true) {
                 var ordId;
                 $("#agreeMsg").html(" ");
                 var paymentMethod = $(".selMod").val();
                 if (paymentMethod == 'Credit Card') {
                   var cardNumber = $("#payment-method-cc-number").val();
                   var expirationDate = $("#expirationDate").val();
                   var ccCode = $("#payment-method-cc-code").val();
                   var cardType = $("#payment-method-cc-type").val();
                   var expMonth= $("#payment-method-expMonth").val();
                   var expYear=$("#payment-method-expYear").val();
                   if (cardType == "") {
                     $("#payment-method-cc-typer").focus();
                     $("#agreeMsg").html("Please select card type");
                     return false;
                  }else if (cardNumber == "") {
                     $("#payment-method-cc-number").focus();
                     $("#agreeMsg").html("Please enter card number");
                     return false;
                   }else if (cardType=="American Express" && cardNumber.length!=15) {
                     $("#payment-method-cc-number").val("")
                     $("#agreeMsg").html("Card number should be 15 characters");
                      return false
                   }else if ((cardType=="Master Card" || cardType=="Visa") && (cardNumber.length!=16)) {
                     $("#payment-method-cc-number").val("")
                     $("#agreeMsg").html("Card number should be 16 characters");
                      return false
                   }else if (cardNumber.indexOf('.') > -1) {
                     $("#payment-method-cc-number").val("")
                     $("#agreeMsg").html("Please enter valid card number");
                     return false
                   } else if (expMonth == "") {
                     $("#payment-method-expMonth").focus();
                     $("#agreeMsg").html("Please select card expiry month");
                     return false;
                   }else if (expYear == "") {
                       $("#payment-method-expYear").focus();
                       $("#agreeMsg").html("Please select card expiry year");
                       return false;
                     }  else if (ccCode == "") {
                     $("#payment-method-cc-code").focus();
                     $("#agreeMsg").html("Please enter cc code");
                     return false;
                   } else if (/[0-9]/.test(ccCode)==false) {
                       $("#payment-method-cc-code").focus();
                       $("#agreeMsg").html("Please enter valid cc code");
                       return false;
                   }else if (cardType=="American Express" && ccCode.length!=4) {
                     $("#payment-method-cc-code").val("");
                     $("#agreeMsg").html("CC code should be 4 characters");
                     return false
                   }else if ((cardType=="Master Card" || cardType=="Visa") && (ccCode.length!=3)) {
                     $("#payment-method-cc-code").val("");
                     $("#agreeMsg").html("CC code should be 3 characters");
                     return false
                  }else if (ccCode.indexOf('.') > -1) {
                     $("#payment-method-cc-number").val("")
                     $("#agreeMsg").html("Please enter valid cc code");
                     return false
                   }
                 }
                 /* START call ajax for send order data to controller */
                 if(deliveryItemPosId!="" && delvryFeeStatus==1){
                     var itemDeliveryArray={};
                     var deliveryModifierArray=[];
                     itemDeliveryArray["itemid"] = deliveryItemPosId;
                     itemDeliveryArray["amount"] = "1";
                     itemDeliveryArray["modifier"]=deliveryModifierArray;
                     demoArray.push(itemDeliveryArray);
                     console.log("deliveryItemPrice--"+deliveryItemPrice)
                     /* if(deliveryTaxStatus==1){
                         finalTotal=finalTotal+parseFloat(deliveryTaxPrice); 
                         totalTax=totalTax+parseFloat(deliveryTaxPrice); 
                     }
                     finalTotal=finalTotal+parseFloat(deliveryItemPrice); */
                 }
                 if(deliveryItemPosId!="" && delvryFeeStatus==0){
                     deliveryItemPrice=0;
                 }
                 if(isConvenienceFeeStatus>0){
                     var itemConvenienceFeeArray={};
                     var convenineceFeemodifierArray=[];
                     itemConvenienceFeeArray["itemid"] = convenienceFeePosId;
                     itemConvenienceFeeArray["amount"] = "1";
                     itemConvenienceFeeArray["modifier"]=convenineceFeemodifierArray;
                     console.log("itemConvenienceFeeArray--"+itemConvenienceFeeArray)
                     demoArray.push(itemConvenienceFeeArray);
                 }
                 var orderJson = (JSON.stringify(demoArray)).substring(1, (JSON.stringify(demoArray)).length-1);
                 console.log("1111111-----"+orderJson);
                 var orderTotal=0;
                 orderTotal = finalTotal.toFixed(2);
                 /* if(finalTotalWithTip>0){
                      orderTotal = finalTotalWithTip.toFixed(2);
                     }else{
                     orderTotal = finalTotal.toFixed(2);
                     } */
                     
                 var fututeOrderType=$("#orderlater").val();
                 var futurdate=$("#futureOrderDateCombo").val();
                 var futureTime=$('#futureOpeingTime').val();
                 $("#futOrderType").val(fututeOrderType);
                 $("#futDate").val(futurdate);
                 $("#futtime").val(futureTime);
                 console.log("Future order------"+fututeOrderType+"-"+futurdate+"-"+futureTime);
                 console.log("orderTotal--"+orderTotal)
                 var ordType=$("#orderType").val();
                 console.log("finalTotal----"+finalTotal+ordType);
                 var instruction = document.getElementById("special-instructions").value;
                 $(".pj-loader-2").css("display","block");
                 $(".mnuLoading").css("display","none");
                 listOfALLDiscounts= document.getElementById("listOfAllDiscounts").value ;
                 //alert(listOfALLDiscounts);
                 //alert(itemsForDiscount);
                  var orderPlaceUrl="placeOrder?orderJson=" + orderJson + "&orderTotal=" + orderTotal;
                     if(instruction!=''){
orderPlaceUrl=orderPlaceUrl+"&instruction=" + instruction;
                        } 
              if(couponDiscount!=''){
orderPlaceUrl=orderPlaceUrl+"&discount="+couponDiscount;
                        }
if(discountType!=''){
orderPlaceUrl=orderPlaceUrl+"&discountType="+discountType;
                        }

if(itemPoSidsJson!=''){
orderPlaceUrl=orderPlaceUrl+"&itemPosIds="+itemPoSidsJson;
                        }

if(inventoryLevel!=''){
orderPlaceUrl=orderPlaceUrl+"&inventoryLevel="+inventoryLevel;
                        }
if(voucherCode!=''){
orderPlaceUrl=orderPlaceUrl+"&voucherCode="+voucherCode;
                        }

if(ordType!=''){
orderPlaceUrl=orderPlaceUrl+"&orderType="+ordType;
                        }

if(convenienceFeePrice!=''){
orderPlaceUrl=orderPlaceUrl+"&convenienceFee="+convenienceFeePrice;
                        }

if(deliveryItemPrice!=''){
orderPlaceUrl=orderPlaceUrl+"&deliveryItemPrice="+deliveryItemPrice;
                        }else{
orderPlaceUrl=orderPlaceUrl+"&deliveryItemPrice=0";

}

if(avgDeliveryTime!=''){
orderPlaceUrl=orderPlaceUrl+"&avgDeliveryTime="+avgDeliveryTime;
                        }
                        
                        
if(customerId!=""){
	
	
	//placeOrder(orderPlaceUrl,instruction,couponDiscount,discountType,itemPoSidsJson,inventoryLevel,voucherCode,ordType,convenienceFeePrice,deliveryItemPrice,avgDeliveryTime,itemsForDiscount,listOfALLDiscounts);
   
	placeOrderOnAWS(orderPlaceUrl,instruction,couponDiscount,discountType,itemPoSidsJson,inventoryLevel,voucherCode,ordType,convenienceFeePrice,deliveryItemPrice,avgDeliveryTime,itemsForDiscount,listOfALLDiscounts);
	/*  $.ajax({
        url : orderPlaceUrl,
        type : "GET",
        contentType : "application/json; charset=utf-8",
        success : function(statusValue) {
         if (statusValue != null) {
         if(sessionCutId!=""){  
             clearTimeout(timerVal);
               timerVal=setTimeout(function(){ 
                 jQuery(function(){
                    jQuery('#confirmModal_sessionTimeOut').click();
                 });
                }  , timeOut);
        		 }   
            ordId = statusValue;
            console.log("----ordId---"+ordId);
            $("#orderPosId").val(statusValue);
            $("#ordPosId").val(statusValue);
            $("#listOfDiscounts").val(listOfALLDiscounts);
            $("#instructionId").val(instruction);
            $("#ordrTotalAmount").val(finalTotal.toFixed(2));
            $("#ordrTipAmount").val(Number(tipAmt).toFixed(2));
             if(finalTotalWithTip>0){
            $("#ordrTotalAmount").val(finalTotalWithTip.toFixed(2));
            }else{
              $("#ordrTotalAmount").val(finalTotal.toFixed(2));
            } 
            
            $("#orderSubTotal").val(subTotal);
            //$("#orderTax").val(totalTax.toFixed(2));
            $("#orderTax").val(totalTax);
            callPayment(); 
          }else{
         	 jQuery(function(){
                  jQuery('#orderError').click();
               }); 
           }
        },
        error : function() {
          alert("error");
          $(".pj-loader-2").css("display","none");
          $(".mnuLoading").css("display","block");
        }
      }) */ 
	
	
}else{

registerCustomer(orderPlaceUrl,instruction,couponDiscount,discountType,itemPoSidsJson,inventoryLevel,voucherCode,ordType,convenienceFeePrice,deliveryItemPrice,avgDeliveryTime,itemsForDiscount,listOfALLDiscounts);  
}                        


/*                  $.ajax({
                   url : orderPlaceUrl,
                   type : "GET",
                   contentType : "application/json; charset=utf-8",
                   success : function(statusValue) {
                    if (statusValue != null) {
                    if(sessionCutId!=""){  
                        clearTimeout(timerVal);
                          timerVal=setTimeout(function(){ 
                            jQuery(function(){
                               jQuery('#confirmModal_sessionTimeOut').click();
                            });
                           }  , timeOut);
                   		 }   
                       ordId = statusValue;
                       console.log("----ordId---"+ordId);
                       $("#orderPosId").val(statusValue);
                       $("#ordPosId").val(statusValue);
                       $("#listOfDiscounts").val(listOfALLDiscounts);
                       $("#instructionId").val(instruction);
                       $("#ordrTotalAmount").val(finalTotal.toFixed(2));
                       $("#ordrTipAmount").val(Number(tipAmt).toFixed(2));
                       /* if(finalTotalWithTip>0){
                       $("#ordrTotalAmount").val(finalTotalWithTip.toFixed(2));
                       }else{
                         $("#ordrTotalAmount").val(finalTotal.toFixed(2));
                       } 
                       
                       $("#orderSubTotal").val(subTotal);
                       //$("#orderTax").val(totalTax.toFixed(2));
                       $("#orderTax").val(totalTax);
                       callPayment(); 
                     }else{
                    	 jQuery(function(){
                             jQuery('#orderError').click();
                          }); 
                      }
                   },
                   error : function() {
                     alert("error");
                     $(".pj-loader-2").css("display","none");
                     $(".mnuLoading").css("display","block");
                   }
                 }) */
                 /* END */
               } else {
                 $("#agreeMsg").html("You need to agree with terms and conditions.");
                 return false;
               }
             });
        
        
         $('#tipAmount').blur(function (e) {
                //alert(finalTotal);
             if(demoArray.length == 0){
                 
                 $("#itemError").html("Please select atleast one item.");
                 $('#tipAmount').val("");
            }else{
             tipAmt=$('#tipAmount').val();
             
             if(tipAmt!=""){
            	 	
				
            	 finalTotalWithTip=finalTotal+Number(tipAmt);
                 
            	
                 
                 $("#totalSpanId").html("$"+finalTotalWithTip.toFixed(2));
                 $("#totalSpanId1").html("$"+finalTotalWithTip.toFixed(2));
                  $(".yourCartAmount").html("Your Cart &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; $"+finalTotalWithTip.toFixed(2))
             }else{
            	// $('#tipAmount').val("");
            	 finalTotalWithTip=finalTotal+Number(tipAmt);
            	 $("#totalSpanId").html("$"+finalTotalWithTip.toFixed(2));
            	 $("#totalSpanId1").html("$"+finalTotalWithTip.toFixed(2));
                 $(".yourCartAmount").html("Your Cart &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; $"+finalTotalWithTip.toFixed(2))
                
            	 
             }
                 }
             
            });
         
         $(".couponButton").click(function() {
             if(couponStatus==0){    
               if(demoArray.length == 0){
                 $(".couponCodeText1").val("");
                 $("#itemError").html("Please select atleast one item.");
               }else{
                 var couponC=$(".couponCodeText1").val();
                 var orderJson = JSON.stringify(demoArray);//(JSON.stringify(demoArray)).substring(1, (JSON.stringify(demoArray)).length-1);
                 var cartJson= JSON.stringify(cartArray)//(JSON.stringify(cartArray)).substring(1, (JSON.stringify(cartArray)).length-1);
                 
                 //orderJson = orderJson+"*x!"+cartJson+"*x!"+couponC;
                // orderJson = orderJson+"#@"+cartJson+"#@"+couponC;
                orderJson= "{\"orderJson\":" + orderJson + "}#@{\"cartJson\":" + cartJson + "}#@{\"couponCode\":\"" + couponC + "\"}#@{\"kouponCount\":\"" + kouponCount +"\"}#@{\"callingType\":\"first\"}"
         		 $.ajax({
                 url : "checkCouponValidity",
                 type : 'POST',
                 dataType : 'json',
                 data : orderJson,
                      contentType : 'application/json',
                      mimeType : 'application/json',
                      success : function(statusValue) {
                    	 if(statusValue.responsCode=='200'){
                    	  //alert(statusValue.DATA);
                    	  var voucherResponse=statusValue.DATA;
                    	  var voucherAllowMultipleKoupon=statusValue.allowMultipleKoupon;
                    	  //alert(JSON.stringify(voucherResponse.items));
                    	     itemsForDiscount=JSON.stringify(voucherResponse.items);
                    	      listOfALLDiscounts=JSON.stringify(voucherResponse.discountList);
                    	      document.getElementById("listOfAllDiscounts").value = listOfALLDiscounts;
                    	  //alert(statusValue.responsCode);
                         /* if(statusValue!=""){ */
                          
                        	 if(voucherAllowMultipleKoupon== 0){
                        		 $("#coupon").attr("disabled", true);
                        		 $("#couponbutton").attr("disabled", true);
                        	 }
                        	 
                        	 
                             $("#couponError").html("");
                             $(".couponCodeText1").val("");
                             /* if(subTotal>=(parseFloat(discount))){ */
                            // console.log("=====copItemPriceArray===="+copItemPriceArray);    
                            //console.log("-----copTaxItemArray------"+copTaxItemArray);  
                             discountType=voucherResponse.discountType;
                             
                             voucherCode=voucherResponse.voucherCode;
                             
                             inventoryLevel  = voucherResponse.inventoryLevel;
                             
                             itemPoSidsJson  = voucherResponse.itemList;
                            
                            // console.log("-----final coupon tax------"+newTaxWithCoupon);
                             $("#taxSpanId").html("$"+(voucherResponse.tax));
                            // couponDiscount=statusValue;
                             
                             $("#discountSpanId").html("$"+voucherResponse.discount);
                             kouponCount=voucherResponse.kouponCount;
                            // alert(kouponCount);
                             couponDiscount=voucherResponse.discount;
                            // alert(couponDiscount);
                             // subTotal=subTotal-parseFloat(voucherResponse.subtotal);
                             //$("#subTotalSpanId").html("$"+subTotal.toFixed(2));
                             console.log("++++++++++++++++"+totalTax+"--subTotal--"+subTotal);
                             
                             //totalTax=voucherResponse.tax;//newTaxWithCoupon;
                             //finalTotal=subTotal+totalTax;
                             finalOrderTotal=finalTotal;
                            finalTotal=voucherResponse.total;
                             //finalTotal=finalTotal-Number(tipAmt);
                            	 if(tipAmt!=""){
                            		 finalTotalWithTip=voucherResponse.total+Number(tipAmt);
                                 }else{
                                	 finalTotalWithTip=voucherResponse.total
                                 }	
                				
                            	// finalTotalWithTip=finalTotal+Number(tipAmt);
                            /*  if(isConvenienceFeeStatus>0){
                                 finalTotal=finalTotal+parseFloat(convenienceFeePrice);
                             } */
                             $(".yourCartAmount").html("Your Cart &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; $"+finalTotalWithTip)
                             $("#totalSpanId").html("$"+finalTotalWithTip);
                            // console.log("----vouchar discount----"+statusValue+"--"+finalTotal+"---"+couponDiscount)
                            /* }else{
                                $("#couponError").html("Please use other coupon or add more items");
                                $(".couponCodeText1").val("");
                            } */
                         /* }else{
                             $("#couponError").html("Invalid coupon code");
                             $(".couponCodeText1").val("");
                         } */
                    	 }else if(statusValue.responsCode=='400') {
                    		 $("#couponError").html(statusValue.responsMessage);
                             $(".couponCodeText1").val("");
                    	 }else if(statusValue.responsCode=='800') {
                    		 $("#couponError").html("Duplicate Coupon");
                             $(".couponCodeText1").val("");
                    	 }
                    	 else if(statusValue.responsCode=='300'){
                           	$("#delivery").prop('checked', true);
                           	 isDeliveryKoupon=statusValue.DATA;
                           	document.getElementById("deliveryFeeFlag").value = isDeliverykouponoupon;
                           	kouponCount=kouponCount+1;
                    	 }
                    	 else{
                    		 $("#couponError").html("Invalid coupon code");
                             $(".couponCodeText1").val("");
                    	 }
                     },
                     error : function() {
                          $("#couponError").html("Invalid coupon code");
                          $(".couponCodeText1").val("");
                     }
               }) ;
                
             }
             }else{
                 $("#couponError").html("Coupon already use , please use other one");
             }
            });
         });
      
      function callCouponAgain(couponCode){
    	  
    	  
              if(couponStatus==0){    
                if(demoArray.length == 0){
                  $(".couponCodeText1").val("");
                  $("#itemError").html("Please select atleast one item.");
                }else{
                  var couponC=couponCode;//$(".couponCodeText1").val();
                  var orderJson = JSON.stringify(demoArray);//(JSON.stringify(demoArray)).substring(1, (JSON.stringify(demoArray)).length-1);
                  var cartJson= JSON.stringify(cartArray)//(JSON.stringify(cartArray)).substring(1, (JSON.stringify(cartArray)).length-1);
                 
                  
                  //orderJson = orderJson+"*x!"+cartJson+"*x!"+couponC;
                 // orderJson = orderJson+"#@"+cartJson+"#@"+couponC;
                 orderJson= "{\"orderJson\":" + orderJson + "}#@{\"cartJson\":" + cartJson + "}#@{\"couponCode\":\"" + couponC + "\"}#@{\"kouponCount\":\"" + kouponCount +"\"}#@{\"callingType\":\"recall\"}"
                 
                 $.ajax({
                  url : "checkCouponValidity",
                  type : 'POST',
                  dataType : 'json',
                  data : orderJson,
                       contentType : 'application/json',
                       mimeType : 'application/json',
                       success : function(statusValue) {
                     	 if(statusValue.responsCode=='200'){
                     	  
                     	  var voucherResponse=statusValue.DATA;
                     	  var voucherAllowMultipleKoupon=statusValue.allowMultipleKoupon;
                     	  //alert(JSON.stringify(voucherResponse.items));
                     	     itemsForDiscount=JSON.stringify(voucherResponse.items);
                     	      listOfALLDiscounts=JSON.stringify(voucherResponse.discountList);
                     	     
                     	      document.getElementById("listOfAllDiscounts").value = listOfALLDiscounts;
                     	  //alert(statusValue.responsCode);
                          /* if(statusValue!=""){ */
                           
                         	 if(voucherAllowMultipleKoupon== 0){
                         		 $("#coupon").attr("disabled", true);
                         		 $("#couponbutton").attr("disabled", true);
                         	 }
                         	 
                         	 
                              $("#couponError").html("");
                              $(".couponCodeText1").val("");
                              /* if(subTotal>=(parseFloat(discount))){ */
                             // console.log("=====copItemPriceArray===="+copItemPriceArray);    
                             //console.log("-----copTaxItemArray------"+copTaxItemArray);  
                              discountType=voucherResponse.discountType;
                              
                              voucherCode=voucherResponse.voucherCode;
                              
                              inventoryLevel  = voucherResponse.inventoryLevel;
                              
                              itemPoSidsJson  = voucherResponse.itemList;
                             
                              console.log("-----final coupon tax------"+newTaxWithCoupon);
                              $("#taxSpanId").html("$"+(voucherResponse.tax));
                             // couponDiscount=statusValue;
                              
                              $("#discountSpanId").html("$"+voucherResponse.discount);
                              kouponCount=voucherResponse.kouponCount;
                              
                              couponDiscount=voucherResponse.discount;
                             // alert(couponDiscount);
                              // subTotal=subTotal-parseFloat(voucherResponse.subtotal);
                              //$("#subTotalSpanId").html("$"+subTotal.toFixed(2));
                              console.log("++++++++++++++++"+totalTax+"--subTotal--"+subTotal);
                              
                              totalTax=voucherResponse.tax;//newTaxWithCoupon;
                              //finalTotal=subTotal+totalTax;
                              finalOrderTotal=finalTotal;
                              finalTotal=voucherResponse.total;
                             
                              //finalTotal=finalTotal-Number(tipAmt);
                             	 if(tipAmt!=""){
                             		 finalTotalWithTip=voucherResponse.total+Number(tipAmt);
                                  }else{
                                 	 finalTotalWithTip=voucherResponse.total
                                  }	
                 				
                             	 //finalTotalWithTip=voucherResponse.total+Number(tipAmt);
                             /*  if(isConvenienceFeeStatus>0){
                                  finalTotal=finalTotal+parseFloat(convenienceFeePrice);
                              } */
                              $(".yourCartAmount").html("Your Cart &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; $"+finalTotalWithTip)
                              $("#totalSpanId").html("$"+finalTotalWithTip);
                             // console.log("----vouchar discount----"+statusValue+"--"+finalTotal+"---"+couponDiscount)
                             /* }else{
                                 $("#couponError").html("Please use other coupon or add more items");
                                 $(".couponCodeText1").val("");
                             } */
                          /* }else{
                              $("#couponError").html("Invalid coupon code");
                              $(".couponCodeText1").val("");
                          } */
                     	 }else if(statusValue.responsCode=='400') {
                     		 $("#couponError").html(statusValue.responsMessage);
                              $(".couponCodeText1").val("");
                              finalTotal=finalOrderTotal;
                              $("#taxSpanId").html("$"+totalTax.toFixed(2));
                              $("#totalSpanId").html("$"+finalTotal.toFixed(2));
                              listOfAllDiscounts="";
                              document.getElementById("listOfAllDiscounts").value = listOfALLDiscounts;
                             
                              $(".yourCartAmount").html("Your Cart &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; $"+finalTotal.toFixed(2))
                              
                     	 }else if(statusValue.responsCode=='800') {
                     		 $("#couponError").html("Duplicate Coupon");
                              $(".couponCodeText1").val("");
                              finalTotal=finalOrderTotal;
                              $("#taxSpanId").html("$"+totalTax.toFixed(2));
                              $("#totalSpanId").html("$"+finalTotal.toFixed(2));
                              
                     	 }
                     	 else if(statusValue.responsCode=='300'){
                            	$("#delivery").prop('checked', true);
                            	 isDeliveryKoupon=statusValue.DATA;
                            	document.getElementById("deliveryFeeFlag").value = isDeliverykouponoupon;
                            	kouponCount=kouponCount+1;
                     	 }
                     	 else{
                     		finalTotal=finalOrderTotal;
                     		 $("#taxSpanId").html("$"+totalTax.toFixed(2));
                             $("#totalSpanId").html("$"+finalTotal.toFixed(2));
                             listOfAllDiscounts="";
                             document.getElementById("listOfAllDiscounts").value = "";
                             
                             $(".yourCartAmount").html("Your Cart &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; $"+finalTotal.toFixed(2))
                             
                              $(".couponCodeText1").val("");
                     	 }
                      },
                      error : function() {
                    	  finalTotal=finalOrderTotal;
                    	  $("#taxSpanId").html("$"+totalTax.toFixed(2));
                          $("#totalSpanId").html("$"+finalTotal.toFixed(2));
                          listOfAllDiscounts="";
                          document.getElementById("listOfAllDiscounts").value = "";
                          
                          $(".yourCartAmount").html("Your Cart &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; $"+finalTotal.toFixed(2))
                           $(".couponCodeText1").val("");
                      }
                }) ;
                 
              }
              }else{
                  $("#couponError").html("Coupon already use , please use other one");
              }
             
    	  
    	  
      }
    
     /* $(".couponButton").click(function() {
     if(couponStatus==0){    
       if(demoArray.length == 0){
         $(".couponCodeText1").val("");
         $("#itemError").html("Please select atleast one item.");
       }else{
         var couponC=$(".couponCodeText1").val();
         var orderJson = JSON.stringify(demoArray);//(JSON.stringify(demoArray)).substring(1, (JSON.stringify(demoArray)).length-1);
         var cartJson= JSON.stringify(cartArray)//(JSON.stringify(cartArray)).substring(1, (JSON.stringify(cartArray)).length-1);
         //alert(cartJson);
         //orderJson = orderJson+"*x!"+cartJson+"*x!"+couponC;
        // orderJson = orderJson+"#@"+cartJson+"#@"+couponC;
        orderJson= "{\"orderJson\":" + orderJson + "}#@{\"cartJson\":" + cartJson + "}#@{\"couponCode\":\"" + couponC + "\"}"
 		 $.ajax({
         url : "checkCouponValidity",
         type : 'POST',
         dataType : 'json',
         data : orderJson,
              contentType : 'application/json',
              mimeType : 'application/json',
              success : function(statusValue) {
            	 
            	  alert(statusValue.DATA);
            	  var voucherResponse=statusValue.DATA;
            	  alert(voucherResponse.tax);
            	  alert(statusValue.responsCode);
                 if(statusValue!=""){
                 if(sessionCutId!=""){  
                     clearTimeout(timerVal);
                      timerVal=setTimeout(function(){ 
                        jQuery(function(){
                           jQuery('#confirmModal_sessionTimeOut').click();
                        });
                       }  , timeOut);
                   }  
                     $("#couponError").html("");
                     $(".couponCodeText1").val("");
                     if(subTotal>=(parseFloat(statusValue))){
                    // console.log("=====copItemPriceArray===="+copItemPriceArray);    
                    //console.log("-----copTaxItemArray------"+copTaxItemArray);  
                     couponStatus++;
                     
                     for (var key of taxNameAndItemPrice.keys()) {
                         var taxVa=merchtTaxMap.get(key);
                         var conv=0;
                         if(convenienceFeePrice>0)
                             conv= parseFloat(convenienceFeePrice)
                            
                             
                             
                         var nwTax=parseFloat(taxNameAndItemPrice.get(key))-(parseFloat(statusValue)*parseFloat(taxNameAndItemPrice.get(key))/(subTotal+conv));
                         console.log("-----copItemPriceArray[i]------"+parseFloat(taxNameAndItemPrice.get(key)));   
                         console.log("----newTaxWithCoupon------"+newTaxWithCoupon);  
                         console.log("----nwTax------"+nwTax+"---"+taxVa); 
                         
                         nwTax=nwTax.toFixed(2);
                         var newTaxValue=(parseFloat(taxVa) / 100)*nwTax;
                         
                         console.log("-----newTaxValue------"+newTaxValue.toFixed(2));  
                         newTaxWithCoupon=parseFloat(newTaxWithCoupon)+parseFloat((newTaxValue).toFixed(2));
                         console.log("-----newTaxWithCoupon------"+copItemPriceArray[i]+"-"+copTaxItemArray[i]+"-"+newTaxWithCoupon);   
                         
                     }
                     console.log("-----final coupon tax------"+newTaxWithCoupon);
                     $("#taxSpanId").html("$"+(newTaxWithCoupon).toFixed(2));
                     couponDiscount=statusValue;
                     $("#discountSpanId").html("$"+statusValue);
                      subTotal=subTotal-parseFloat(statusValue);
                     //$("#subTotalSpanId").html("$"+subTotal.toFixed(2));
                     console.log("++++++++++++++++"+totalTax+"--subTotal--"+subTotal);
                     
                     totalTax=voucherResponse.tax;//newTaxWithCoupon;
                     finalTotal=subTotal+totalTax;
                     
                     if(isConvenienceFeeStatus>0){
                         finalTotal=finalTotal+parseFloat(convenienceFeePrice);
                     }
                     $(".yourCartAmount").html("Your Cart &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; $"+finalTotal.toFixed(2))
                     $("#totalSpanId").html("$"+finalTotal.toFixed(2));
                     console.log("----vouchar discount----"+statusValue+"--"+finalTotal+"---"+couponDiscount)
                    }else{
                        $("#couponError").html("Please use other coupon or add more items");
                        $(".couponCodeText1").val("");
                    }
                 }else{
                     $("#couponError").html("Invalid coupon code");
                     $(".couponCodeText1").val("");
                 }
             },
             error : function() {
                  $("#couponError").html("Invalid coupon code");
                  $(".couponCodeText1").val("");
             }
       }) 
     }
     }else{
         $("#couponError").html("Coupon already use , please use other one");
     }
    });
 }); */
    
    function callPayment() {
       /*  document.myform.action = "orderPayment";
        document.myform.submit(); */
        
        var myform = document.getElementById("msform");
        var fd = new FormData(myform );
        
        $.ajax({
            url: "orderPaymentUsingAjax",
            data: fd,
            cache: false,
            processData: false,
            contentType: false,
            type: 'POST',
            success: function (responseBody) {
                // do something with the result
               
                if(responseBody=='APPROVED'){
                	window.location="order?message=Your order is placed successfully";
                }else if(responseBody=='DECLINED'){
                	$("#agreeMsg").html("Your card has been declined. Please retry or use another card");
                }else if(responseBody=='FAILED'){
                	window.location="order?message=We encountered an error processing your order. Please try again";
                }else{
                	window.location="order?message=We encountered an error processing your order. Please try again";
                }
                $(".pj-loader-2").css("display","none");
                $(".mnuLoading").css("display","block");
            }
        });
        
      }
    </script>
    <!--accordion-->
    <!--.full-page-over-lay-->
    <script>
        /* Open when someone clicks on the span element */
        function openNav(id,itemId,allowModifLimit) {
            var count=0;
        	if (typeof allowModifLimit === "undefined") {
                allowModifLimit=0;
            }
          if(closingDayStatus=='N'){
                jQuery(function(){
                    jQuery('#openingClosingPopUp2').click();
               })
            }else{
            if(timingStatus=='Y'){
            if(sessionCutId!=""){  
                /* clearTimeout(timerVal);
                timerVal=setTimeout(function(){ 
                       jQuery(function(){
                         jQuery('#confirmModal_sessionTimeOut').click();
                     });
                 }  , timeOut); */
            }
                    $.ajax({
                        url : "getItemModiferGroups?itemId="+ itemId +"&allowModifierLimit="+allowModifLimit,
                        type : "GET",
                        contentType : "application/json; charset=utf-8",
                        success : function(modifierGroupModifierStr) {
                          $("#"+id).empty();
                          var modifierGroupModifier = JSON.parse(modifierGroupModifierStr);
                          
                          $("<div class='accordion-popup-quantity'><label class='error r_"+id+"'></label><label class='error modiGrpLimitError'></label><quantity><label>Order Quantity: </label><input type='number' placeholder='Quantity' id='qty_"+itemId+"' onkeypress='return onlyNumberQuantity(event,this);' onkeyup='return onlyNumberQuantityOnKeyUp(event,this)'  class='quantityCls qtyVal_"+id+"' min='1' maxlength='2' oninput='javascript: if (this.value.length > this.maxLength) this.value = this.value.slice(0, this.maxLength);' > </quantity> </div>").appendTo('#'+id);
                          for (i = 0; i < modifierGroupModifier.length; i++) { 
                               if(modifierGroupModifier[i].modifierDtos.length!=0){
                                  if(modifierGroupModifier[i].modifiersLimit!=0){
                                      var modifierGroupName="";
                                     
                                      if (typeof modifierGroupModifier[i].modifiersLimit != 'undefined' && modifierGroupModifier[i].isMaxLimit==0){
                                           modifierGroupName=modifierGroupName+"<font style='text-transform: lowercase;'>(Options are limited to "+modifierGroupModifier[i].modifiersLimit+")</font>";
                                       }
                                      var grmpName;
                                      count++;
                                      if(modifierGroupName!=""){
                                          grmpName="<div class='sd-accordion-withinoverlay accordionButton chkLimit' id='open"+itemId+"-"+count+"' modifierGroupLimitAttr="+modifierGroupModifier[i].modifiersLimit+">"+modifierGroupModifier[i].modifierGroupName+"&nbsp;&nbsp;&nbsp;"+modifierGroupName;
                                      }else{
                                          grmpName="<div class='sd-accordion-withinoverlay accordionButton chkLimit' id='open"+itemId+"-"+count+"' modifierGroupLimitAttr="+modifierGroupModifier[i].modifiersLimit+">"+modifierGroupModifier[i].modifierGroupName+"&nbsp;&nbsp;&nbsp;";
                                      }
                                      
                                       
                                      $(grmpName+"</div>").appendTo('#'+id);
                                      var groupVal="<div class='accordionContent'  style='display: none;'><div class='end-products'><ul class='end-products-unordered-list'><li>";
                                      var modifig="";
                                      for (j = 0; j < modifierGroupModifier[i].modifierDtos.length; j++) { 
                                          modifig=modifig+"<div class='small-12 medium-12 large-4 columns'><div class='price-selecter-item'><div class='item-in-popup ttt_"+modifierGroupModifier[i].id+"'><input type='checkbox' value="+modifierGroupModifier[i].modifierDtos[j].price+" id="+modifierGroupModifier[i].modifierDtos[j].modifierPosId+" chkAttr="+modifierGroupModifier[i].id+"  class='modifierChk' modifierPosIdAttr="+modifierGroupModifier[i].modifierDtos[j].modifierPosId+" modifierNameAttr='"+modifierGroupModifier[i].modifierDtos[j].modifierName+"' modifierPriceAttr="+modifierGroupModifier[i].modifierDtos[j].price+"><label for="+modifierGroupModifier[i].modifierDtos[j].modifierPosId+">"+modifierGroupModifier[i].modifierDtos[j].modifierName+"</label></div><price><span>$"+modifierGroupModifier[i].modifierDtos[j].price+"</span></price> </div></div>";
                                      }
                                      groupVal=groupVal+modifig;
                                      $(groupVal+"</li></ul></div></div>").appendTo('#'+id);
                                      $("#open"+itemId+"-"+count+"").css('background-color', bgColor);
                                      
                                     } 
                                  }
                              }  
                          $("<div class='shop-checkout-popup-button'><div class='button right' style='clear: both; ' id='orderNowButton"+id+"'><a href='javascript:void(0)' onclick=closeNav('"+id+"') class='orderButton'>Order Now</a></div></div>").appendTo('#'+id);
                          $("#orderNowButton"+id+"").css('background-color', bgColor);
                          
                        },
                        error : function() {
                          alert("error");
                        }
                     })
                document.getElementById("myNav"+id).style.height = "100%";
            }else{
            	if(allowFutureOrder ==0){
            
                  jQuery(function(){
                     jQuery('#openingClosingPopUp').click();
                  
                }) }else{
                $("#ordernow").prop('checked', false);
                $("#orderlater").prop('checked', false);
                $('#ordernow').prop('disabled', true);
                if(sessionCutId!=""){  
                    /* clearTimeout(timerVal);
                    timerVal=setTimeout(function(){ 
                           jQuery(function(){
                             jQuery('#confirmModal_sessionTimeOut').click();
                         });
                     }  , timeOut); */
                }
                        $.ajax({
                        
                        	url : "getItemModiferGroups?itemId="+ itemId +"&allowModifierLimit="+allowModifLimit,
                            type : "GET",
                            contentType : "application/json; charset=utf-8",
                            success : function(modifierGroupModifierStr) {
                              $("#"+id).empty();
                              var modifierGroupModifier = JSON.parse(modifierGroupModifierStr);
                              
                              $("<div class='accordion-popup-quantity'><label class='error r_"+id+"'></label><label class='error modiGrpLimitError'></label><quantity><label>Order Quantity: </label><input type='number' placeholder='Quantity' id='qty_"+itemId+"' onkeypress='return onlyNumberQuantity(event,this);'  class='quantityCls qtyVal_"+id+"' min='1' maxlength='2' oninput='javascript: if (this.value.length > this.maxLength) this.value = this.value.slice(0, this.maxLength);' > </quantity> </div>").appendTo('#'+id);
                              for (i = 0; i < modifierGroupModifier.length; i++) { 
                                   if(modifierGroupModifier[i].modifierDtos.length!=0){
                                      if(modifierGroupModifier[i].modifiersLimit!=0){
                                          var modifierGroupName="";
                                          
                                          if (typeof modifierGroupModifier[i].modifiersLimit != 'undefined' && modifierGroupModifier[i].isMaxLimit==0 ){
                                               modifierGroupName=modifierGroupName+"<font style='text-transform: lowercase;'>(Options are limited to "+modifierGroupModifier[i].modifiersLimit+")</font>";
                                           }
                                          var grmpName;
                                          
                                          if(modifierGroupName!=""){
                                              grmpName="<div class='sd-accordion-withinoverlay accordionButton chkLimit' id='open"+itemId+"-"+count+"' modifierGroupLimitAttr="+modifierGroupModifier[i].modifiersLimit+">"+modifierGroupModifier[i].modifierGroupName+"&nbsp;&nbsp;&nbsp;"+modifierGroupName;
                                          }else{
                                              grmpName="<div class='sd-accordion-withinoverlay accordionButton chkLimit' id='open"+itemId+"-"+count+"' modifierGroupLimitAttr="+modifierGroupModifier[i].modifiersLimit+">"+modifierGroupModifier[i].modifierGroupName+"&nbsp;&nbsp;&nbsp;";
                                          }
                                          
                                          $(grmpName+"</div>").appendTo('#'+id);
                                          var groupVal="<div class='accordionContent'  style='display: none;'><div class='end-products'><ul class='end-products-unordered-list'><li>";
                                          var modifig="";
                                          for (j = 0; j < modifierGroupModifier[i].modifierDtos.length; j++) { 
                                              modifig=modifig+"<div class='small-12 medium-12 large-4 columns'><div class='price-selecter-item'><div class='item-in-popup ttt_"+modifierGroupModifier[i].id+"'><input type='checkbox' value="+modifierGroupModifier[i].modifierDtos[j].price+" id="+modifierGroupModifier[i].modifierDtos[j].modifierPosId+" chkAttr="+modifierGroupModifier[i].id+"  class='modifierChk' modifierPosIdAttr="+modifierGroupModifier[i].modifierDtos[j].modifierPosId+" modifierNameAttr='"+modifierGroupModifier[i].modifierDtos[j].modifierName+"' modifierPriceAttr="+modifierGroupModifier[i].modifierDtos[j].price+"><label for="+modifierGroupModifier[i].modifierDtos[j].modifierPosId+">"+modifierGroupModifier[i].modifierDtos[j].modifierName+"</label></div><price><span>$"+modifierGroupModifier[i].modifierDtos[j].price+"</span></price> </div></div>";
                                          }
                                          groupVal=groupVal+modifig;
                                          $(groupVal+"</li></ul></div></div>").appendTo('#'+id);
                                          $("open"+itemId+"-"+count+"").css('background-color', bgColor);
                                         }
                                      }
                                  }  
                              $("<div class='shop-checkout-popup-button'><div class='button right' style='clear: both;' id='orderNowButton"+id+"'><a href='javascript:void(0)' onclick=closeNav('"+id+"') class='orderButton'>Order Now</a></div></div>").appendTo('#'+id);
                              $("#orderNowButton"+id+"").css('background-color', bgColor);
                            },
                            error : function() {
                              alert("error");
                            }
                         })
                    document.getElementById("myNav"+id).style.height = "100%";
            }     
                        
            }
          } 
        }
        
        /* Close when someone clicks on the "x" symbol inside the overlay */
        function closeNav(id) {
        	
        	var itemQuantity=$(".qtyVal_"+id).val();
            if(itemQuantity!=""){
                if(itemQuantity.length <3){
                 if(itemQuantity==0){
                     $(".r_"+id).html("");
                     $(".r_"+id).html("Order Quantity should be greater than 0");
                  }else{
                     if(itemQuantity.indexOf('.') > -1){
                         $(".r_"+id).html("Quantity not allow dot");
                   }else{
                         $(".r_"+id).html("");
                         document.getElementById("myNav"+id).style.height = "0%";
                     }
                  }
                }else{
                    $(".qtyVal_"+id).val("");
                    $(".r_"+id).html("Please contact the store for placing bulk orders");
                }
            }else{
                 $(".r_"+id).html("Please enter quantity");
            }
        }
        function closeNav1(id) {
              $(".r_"+id).html("");
              document.getElementById("myNav"+id).style.height = "0%";
        }
    </script>
    <!--.full-page-over-lay-->

    <!--ACCORDION WITHIN OVERLAY-->
    <script>
    //ACCORDION BUTTON ACTION
    $('div.accordionButton').click(function() {
        var show = !$(this).next().is(':visible');    
        $(this).siblings('.accordionButton.active').removeClass('active');
        $(this).toggleClass('active', show);
        $('div.accordionContent').slideUp('normal');
        if(show)
            $(this).next().slideDown('normal');
    });
    //OPEN FIRST 
    $("#open").trigger('click');
    if(setGuestPasswordV=='Y'){
        jQuery(function(){
               jQuery('#confirmModal_ex2').click();
        })
    }
    $(document).ready(function() {
    $(".guestPopUpCls").on("click", function () {
        if(($(this).html()).indexOf('Yes') == -1){
            window.location.href = "cancelAccountRequest";
        }else{
            window.location.href = "setGuestPassword";
        }
    }); 
    });
    </script>
    <script>
    function onlyNumberQuantity(e, t) {     
        if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
            return false;
        }
    }
    
    function onlyNumberQuantityOnKeyUp(event,el) {
    	var val = el.value;
        val = val.replace(/\D/g,'');
        el.value=val;
        } 
    
function validatenumberOnKeyUp(event,el) {
    	
    	//var val = $('#tipAmount').val().replace(/^[0-9]*\.?[0-9]*$/,'');
    	var val = $('#tipAmount').val();
        if(isNaN(val)){
             val = val.replace(/[^0-9\.]/g,'');
             if(val.split('.').length>2) 
                 val =val.replace(/\.+$/,"");
        }
        $('#tipAmount').val(val); 
        }
    
    function validatenumber(event,el) {
          return (/^[0-9]*\.?[0-9]*$/).test(el.value+event.key);
        }
          
    
    
      $(document).ready(function() {
        $(".pj-loader-2").css("display","none");
        $(".mnuLoading").css("display","block");
        
        $('#payment-method-cc-type').on("change", function() { 
            var typeVal=$('#payment-method-cc-type').val();
            
            if(typeVal=='American Express'){
                 $("#payment-method-cc-number").attr('maxlength','15');
                 $("#payment-method-cc-code").attr('maxlength','4');
                 $("#payment-method-cc-number").val("");
                 $("#payment-method-cc-code").val("");
            }
            if(typeVal=='Master Card'){
                 $("#payment-method-cc-number").attr('maxlength','16');
                 $("#payment-method-cc-code").attr('maxlength','3');
                 $("#payment-method-cc-number").val("");
                 $("#payment-method-cc-code").val("");
            }
            if(typeVal=='Visa'){
                 $("#payment-method-cc-number").attr('maxlength','16');
                 $("#payment-method-cc-code").attr('maxlength','3');
                 $("#payment-method-cc-number").val("");
                 $("#payment-method-cc-code").val("");
            }
        });
      });
      
     function openNavforgot() {
         document.getElementById("myNav").style.height = "100%";
         document.getElementById("signInNav").style.height = "0%";
      }
     
     function openNavSignIn() {
         document.getElementById("signInNav").style.height = "100%";
         $("#loginError").html("");
      }
     
     function closeNavSignIn() {
    	  $('#singInEmail').val("");
          $('#signInPassword').val("");
    	 document.getElementById("signInNav").style.height = "0%";
      }
     
     function openNavforgot() {
         document.getElementById("myNav").style.height = "100%";
         document.getElementById("signInNav").style.height = "0%";
      }

    /* Close when someone clicks on the "x" symbol inside the overlay */
     function closeNavforgot() {
        $("#errorForgotPassword").html("");
        document.getElementById("myNav").style.height = "0%";
     }
      
      function checkEmail() {
         // var emailRegex = /^[A-Za-z0-9._]*\@[A-Za-z]*\.[A-Za-z]{2,5}$/;
          var emailRegex = /^([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
          var email = $("#emailForgot").val();
          if ($("#emailForgot").val() == "") {
            $("#emailForgot").focus();
            $("#errorForgotPassword").html("enter the email");
            return false;
          } else if (!emailRegex.test(email)) {
            $("#emailForgot").focus();
            $("#errorForgotPassword").html("Enter a valid email id");
            return false;
          }else{
              $("#errorForgotPassword").html("");
              $("#forgotButtonId").css("pointer-events","none");
                 $.ajax({
                      url : "forgotPasswordAction",
                      type : 'POST',
                      dataType : 'json',
                      data : "{\"emailId\":\"" + email + "\"}",
                      contentType : 'application/json',
                      mimeType : 'application/json',
                      success : function(statusValue) {
                          $("#errorForgotPassword").html(statusValue.message);
                          $("#emailForgot").val("");
                          $("#forgotButtonId").css("pointer-events","auto");
                     },
                     error : function() {
                         $("#errorForgotPassword").html("Some thing went wrong please try again");
                     }
               }) 
           }
        }
    var manageAccordinStates=0;
    $(document).ready(function() {
    	
    	
    	$(".categchangeMerchant").on("click", function () {
    		
    		$(".categchangeMerchant").css("display","block");
    		//$(".categAjaxCall").css("display","block");
    	});
    
      $(".categAjaxCall").on("click", function () {
        if(sessionCutId!=""){   
          /* clearTimeout(timerVal);
          timerVal=setTimeout(function(){ 
            jQuery(function(){
               jQuery('#confirmModal_sessionTimeOut').click();
            });
           }  , timeOut); */
        }  
          var categoryId=$(this).attr("idAttr");
          if(manageAccordinStates==categoryId){
              manageAccordinStates=0;
          }else{
              manageAccordinStates=categoryId;
          $("#"+categoryId).empty();
          //$("<div class='item_loading_"+categoryId+"' style='display: none;height: 383px;width: 709px; background: url(resources/img/spinner.gif) no-repeat scroll center center rgba(153, 153, 153, 0.3);z-index: 9999;left: 260;position: absolute;top: -1px;'></div>").appendTo('#'+categoryId);
          //$('.item_loading_'+categoryId).css('display','block');
          $.ajax({
              url : "getMenuItems?categoryId="+ categoryId,
              type : "GET",
              contentType : "application/json; charset=utf-8",
              success : function(menuItemsStr) {
                //$("#"+categoryId).empty();
                var dynamicVal="";
                var menuItems = JSON.parse(menuItemsStr);
                for (i = 0; i < menuItems.length; i++) { 
                    
                    $("<ul><li id='i_"+menuItems[i].id+"' taxV="+menuItems[i].itemTax+" itmPosId="+menuItems[i].itemPosId+" itemPrice="+menuItems[i].price+" itemNameAttr='"+menuItems[i].itemName+"' itemTaxNames='"+menuItems[i].itemTaxName+"' class='itemLi' idItm="+menuItems[i].id+" modifierLimitStatusAttr="+menuItems[i].allowModifierLimit+" ><a onclick=openNav('"+menuItems[i].itemPosId+"',"+menuItems[i].id+","+menuItems[i].allowModifierLimit+") style='color: black'><item>"+menuItems[i].itemName+"<br><font size='2'>"+menuItems[i].description+"</font></item> <price>$"+menuItems[i].price.toFixed(2)+"</price></a></li></ul>"+
                    "<div id='myNav"+menuItems[i].itemPosId+"' class='overlay' style='height: 0%;'><a href='javascript:void(0)' class='closebtn' onclick=closeNav1('"+menuItems[i].itemPosId+"')>&times;</a>"+
                    "<div class='overlay-content'><div class='overlay-contentscale'><sd-overlay><div class='sd-accordion-container'>"+
                    "<div class='accordion-popup-header'><div class='accordion-popup-header-content' id='menuItems"+menuItems[i].itemPosId+"'>"+
                    "<div class='small-12 medium-12 large-9 columns no-padding'><h2>"+menuItems[i].itemName+"</h2></div>"+
                    "<div class='small-12 medium-12 large-3 columns no-padding'><price>$"+menuItems[i].price.toFixed(2)+"</price></div>"+
                    "</div></div><div class='accordion-popup-sd-container' id="+menuItems[i].itemPosId+"> </div></div></sd-overlay></div></div></div>").appendTo('#'+categoryId);
                    $("#menuItems"+menuItems[i].itemPosId+"").css('background-color', bgColor);
                }
                
               // $('.item_loading_'+categoryId).css('display','none');
              },
              error : function() {
                alert("error");
              }
           })
          }
      }); 
     });
    $('body').on('click', '.sd-accordion-withinoverlay', function () {
        var status=$(this).hasClass('active');
         if($('.accordion-popup-sd-container').find('.active').length == 1){
             $('.accordion-popup-sd-container').find('.active').removeClass("active");
             $(".accordionContent").css("display","none");
         }
         
        if (!$(this).hasClass('active')) {
            $(this).next().css("display","block");
            $(this).addClass("active");
        }else{
            $(this).next().css("display","none");
            $(this).removeClass("active");
        }
        if(status){
             $(this).next().css("display","none");
             $(this).removeClass("active");
        }
    });
   
    
     $("#changeMerchantLocation").on("change", function () {
    	 var changeMerchant=$("#changeMerchantLocation").val();
    	 var merch=[];
    	 merch=changeMerchant.split(":");
    	 var merchId = merch[0];
    	 var merchName = merch[1].replace(" ","");
    	 
    	 $(".pj-loader-2").css("display","block");
    	 $.ajax({
             url : "sendLocation?merchantId="+ merchId +"&merchantName="+merchName,
             type : "GET",
             contentType : "application/json; charset=utf-8",
             success : function(result) {
            	 
            	 window.location=result;
            	 //$(".pj-loader-2").css("display","none");
             },
             error : function() {
               console.log("Error inside future date Ajax call");
             }
          })
    	
    	 
     }); 
    
    $("#futureOrderDateCombo").on("change", function () {
        var futuredate=$(this).val();
        var orderType;
       /*  $("#delivery").prop('checked', false);
        $("#pickup").prop('checked', false); */
        $("#orderLaterError").html("");
        if($('#delivery').is(':checked')){
        	orderType="Delivery";
        }else{
        	orderType="Pickup";
        }
        $.ajax({
            url : "getFutureDateOpeningTime?futureDate="+ futuredate +"&orderType="+orderType,
            type : "GET",
            contentType : "application/json; charset=utf-8",
            success : function(result) {
            $('#futureOpeingTime').html('<option value="select">Select</option>'); 
            
                for ( var i = 0, l = result.length; i < l; i++ ) {
                    $('<option value="' + result[i] +'">' + result[i] + '</option>').appendTo('#futureOpeingTime');
                }
            },
            error : function() {
              console.log("Error inside future date Ajax call");
            }
         })
    }); 
   </script>
    <!--ACCORDION WITHIN OVERLAY-->
    <!DOCTYPE html>
<script>
/* $(document).scroll(function () {
    var y = $(this).scrollTop();
    if (y > 1100) {
        $('.sd-cart-fixed-for-mobile-part').fadeOut();
    } else {
        $('.sd-cart-fixed-for-mobile-part').fadeIn();
    }
}); */
</script>

</body>
</html>