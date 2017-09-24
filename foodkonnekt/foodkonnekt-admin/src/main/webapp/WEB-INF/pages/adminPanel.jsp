<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
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

  </head>
  <style>
  .logo{
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
									<a href="index.html" title="FoodKonnekt Dashboard" class="logo"><!-- <img src="resources/img/logo.jpg"> </a>-->
							 
								
        <img src="data:image/jpeg;base64,${merchant.merchantLogo}" onerror="this.src='resources/img/foodkonnekt-logo.png'" width="250" height="150"></a>
	</div>
							</div><!--.row-->
						</div><!--.inner-header-->
					</header><!--#page-header-->
					
					<div id="page-content clearfix">
						<div class="outer-container">
						<div style="margin:0 auto;width: 127px;padding-top: 52px;" class="clearfix">
						<span style="margin:10px 0 0 0;"><b><font size="4">ALL DONE !</font></b></span></div>
						
							<div class="row">
								<div class="content-inner-container">
								
								<div class="adding-products-form" style="width:800px; margin:0 auto;">
								
									<div class="clearfix"></div>
									<c:choose>
																										<c:when test="${inventoryThreadStatus ==1}">
																										<p></p>
									You have successfully installed Foodkonnekt Online Ordering System.</br>
									<p></p>
																										</c:when>
																										<c:otherwise>
																											<p></p>
									We are still in the process of getting inventory data from Clover. We will email you once this process is done.</br>
									<p></p>
																										</c:otherwise>
																									</c:choose>
									
									You can get your online ordering link from your admin panel.Please add this link for online buttons</br>
									on your website ,Facebook,Twitter,Yelp,campaigns and any online platform accessed by your</br>
									Customers</br>
									<p></p>
									You are now ready to start accepting orders from anywhere online</br>
									<p></p>
									You can always make further changes at any point from admin portal.
									<div class="clearfix"></div>
									<br>
									<div class="clearfix"></div>
									<div class="button right">
										<a href="adminHome" style="max-width: 200px;width: 200px;" >Go to Admin Panel</a>
									</div><!--.button-->
									
									
							   </div><!--.adding-products-form-->
							</div><!--.adding-products-->	
									
						</div><!--.content-inner-container-->
					</div><!--.row-->
			</div><!--.outer-container-->
		</div><!--#page-content-->
					
					<footer id="footer-container">
						<div class="footer-outer-container">
							<div class="footer-inner-container">
								<div class="row">
									<div class="sd-inner-footer">
									
										<div class="footer-left">
											<p>Powered by foodkonnekt | copyright@foodkonnekt.com</p>
										</div><!--.footer-left-->
										
										<div class="footer-right">
											<img src="resources/img/foodkonnekt-logo.png" />										</div><!--.footer-right-->
									</div><!--.sd-inner-footer-->
								</div><!--.row-->
							</div><!--.footer-inner-container-->
						</div><!--.footer-outer-container-->
					</footer>
					<!--#footer-container-->
					
				</div><!--.max-row-->
			</div><!--.inner-container-->
		</div><!--.foodkonnekt .dashboard-->
	</div><!--#page-container-->

  </body>
</html>