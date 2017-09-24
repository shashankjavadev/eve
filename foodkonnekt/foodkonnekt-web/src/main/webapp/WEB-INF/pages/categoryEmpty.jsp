<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
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
<link type="text/css" rel="stylesheet" href="resources/css/popModal.css">
</head>
<body>
<div class="exampleLive">
    <button style="display:none;" id="confirmModal_ex2" class="btn btn-primary" data-confirmmodal-bind="#confirm_content" data-topoffset="0" data-top="10%" >Example</button>
</div>
<div id="confirm_content" style="display:none">
    <div class="confirmModal_content sd-popup-content">
    <img src="resources/img/logo.png" class="sd-popup-logo">
    <h3>Please contact to your restaurant.</h3>
    </div>
    <div class="confirmModal_footer">
        <button type="button" class="btn btn-primary" id="buttonIdcls">Ok</button>
    </div>
</div>
</body>
<script src="https://cdn.jsdelivr.net/jquery/3.1.0/jquery.min.js"></script>
<script src="resources/js/popModal.js"></script>
<script>
jQuery(function(){
   jQuery('#confirmModal_ex2').click();
});
</script>
<script>
$(document).ready(function() {
$(".confirmModal_footer").on("click", function () {
    window.location.href = "https://www.foodkonnekt.com";
}); 
}); 
</script>
</html>