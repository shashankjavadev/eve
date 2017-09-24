<%@ page import="com.foodkonnekt.model.Merchant" %>
<style>
.md-content {
  color: black;
}
</style>
<style>
*{    font-family: 'Open Sans', sans-serif;}
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
.btn.btn-default {}
.btn.btn-default:hover {background:#eee;border-color:#bbb}
.btn.btn-default:focus {background:#ddd;border-color:#bbb}
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
.btn.btn-default[disabled] {background:#fafafa!important;border-color:#ccc!important;color:#aaa}
.btn.btn-primary[disabled] {background:#3F9DD0!important;border-color:#537FA9!important;color:#ACD3E8;box-shadow:none!important}
.btn.btn-left {float:left;margin:0 5px 0 0!important}
.sd-popup-content img {
    display: block;
    margin: 0 auto 10px;
}
.sd-popup-content h3 {
    text-align: center;
}
</style>
<div class="exampleLive">
    <button style="display:none;" id="confirmModal_ex22" class="btn btn-primary" data-confirmmodal-bind="#confirm_content" data-topoffset="0" data-top="10%" >Example</button>
</div>
<div id="confirm_content" style="display:none">
    <div class="confirmModal_content sd-popup-content">
    <img src="resources/img/logo.png" class="sd-popup-logo">
    <h3>Your session has been timed out. Please log back in</h3>
    </div>
    <div class="confirmModal_footer">
        <button type="button" class="btn btn-primary" onclick="sessionTimeOut()">Ok</button>
    </div>
</div>
<div class="header-nav">
    <nav class="header-nav-container">
        <ul>
            <li><button class="md-trigger custom-sd-button-dialog" data-modal="modal-18">
                    <i class="fa fa-user-plus" aria-hidden="true"></i> Support
                </button> |</li>
            <!-- <li><a href="#"><i class="fa fa-user" aria-hidden="true"></i> My Account</a> |</li> -->
            <li><a href="logout"><i class="fa fa-power-off" aria-hidden="true"></i> Log Out</a></li>
        </ul>
    </nav>
    <!--.header-nav-container-->
</div>
<!--.header-nav-->
<div id="sd-dialog">
    <div class="md-modal md-effect-14" id="modal-18">
        <div class="md-content">
            <h3>FoodKonnekt Support</h3>
            <div>
                <p>Phone Support: 619-566-6358 (10am CST to 10pm CST)</p>
                <p>Twitter: @FoodKonnektHelp</p>
                <p>Email us: support@foodkonnekt.com</p>
                <p>
                    <a href="https://foodkonnekt.freshdesk.com/support/home" target="_blank" style="color: blue;">Support FAQ</a>
                </p>
                <div class="button">
                    <button class="white-button md-close">Close</button>
                </div>
                <!--.button-->
            </div>
        </div>
    </div>
    <div class="md-overlay"></div>
    <!-- the overlay element -->
</div>
<!--#sd-dialog-->
 <!-- <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script> -->
<link type="text/css" rel="stylesheet" href="resources/css/popModal.css">
<script src="resources/js/popModal.js"></script>
<script type="text/javascript">
var timeOut=900000; 
setTimeout(function(){ 
    jQuery(function(){
        jQuery('#confirmModal_ex22').click();
      });
 }  , timeOut );
function sessionTimeOut(){
     window.location.href = "sessionOut";
}
</script>