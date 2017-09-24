<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>FoodKonnekt | Welcome</title>
<!--CALLING STYLESHEET STYE.CSS-->
<link rel="stylesheet" href="resources/css/style.css">
<!--CALLING STYLESHEET STYLE.CSS-->

<!--CALLING GOOGLE FONT OPEN SANS-->
<link
    href='https://fonts.googleapis.com/css?family=Open+Sans:400,300,300italic,400italic,600,600italic,700,700italic,800,800italic'
    rel='stylesheet' type='text/css'>
<!--CALLING GOOGLE FONT OPEN SANS-->

<!--CALLING FONT AWESOME-->
<link rel="stylesheet"
    href="https://fontawesome.io/assets/font-awesome/css/font-awesome.css">
<!--CALLING FONT AWESOME-->

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script>
    $(document).ready( function() {
                var Cloverurl = window.location.href;

                var queryString = Cloverurl.split("?");
                var url=queryString[0];
                var qrsrng = queryString[1];
                var strng = qrsrng.split("#");
                var queryStrg = strng[0] + '&' + strng[1];
                //for Dev
                $("#LoadingImage").show();
                
               Cloverurl =  url+"/clover";
               
               
                  Cloverurl=Cloverurl+ '?' + queryStrg;

                $.ajax({
                    url : Cloverurl,
                    type : "GET",
                    contentType : "application/json; charset=utf-8",
                    success : function(minAmountData) {
                        window.location = minAmountData;
                    },
                    error : function() {
                        $("#LoadingImage").hide();
                        alert("error");

                    }
                });

            });
</script>

</head>
<style>
.logo {
    width: 1178px;
}

footer#footer-container {
    position: absolute;
    bottom: 0;
    width: 100%;
}

.clearfix:after {
    content: " ";
    visibility: hidden;
    display: block;
    height: 0;
    clear: both;
}
</style>
<body>
    <div id="page-container">
        <div class="foodkonnekt merchant">
            <div class="inner-container">
                <div class="max-row">

                    <header id="page-header">
                    <div class="inner-header">
                        <div class="row">

                            <div class="logo">
                                <a href="index.html" title="FoodKonnekt Dashboard" class="logo"><img
                                    src="resources/img/foodkonnekt-logo.png"></a>
                            </div>
                            <!--.logo-->
                        </div>
                        <!--.row-->
                    </div>
                    <!--.inner-header--> </header>
                    <!--#page-header-->

                    <div id="LoadingImage" style="display: none" align="middle">
                        <img src="resources/img/spinner.gif" align="middle" />
                    </div>

                    <footer id="footer-container">
                    <div class="footer-outer-container">
                        <div class="footer-inner-container">
                            <div class="row">
                                <div class="sd-inner-footer">

                                    <div class="footer-left">
                                        <p>Powered by foodkonnekt | copyright@foodkonnekt.com</p>
                                    </div>
                                    <!--.footer-left-->

                                    <div class="footer-right">
                                        <img src="resources/img/foodkonnekt-logo.png" />
                                    </div>
                                    <!--.footer-right-->
                                </div>
                                <!--.sd-inner-footer-->
                            </div>
                            <!--.row-->
                        </div>
                        <!--.footer-inner-container-->
                    </div>
                    <!--.footer-outer-container--> </footer>
                    <!--#footer-container-->

                </div>
                <!--.max-row-->
            </div>
            <!--.inner-container-->
        </div>
        <!--.foodkonnekt .dashboard-->
    </div>
    <!--#page-container-->
</body>
</html>
