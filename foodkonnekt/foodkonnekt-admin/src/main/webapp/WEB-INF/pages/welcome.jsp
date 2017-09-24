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
  <script>
    $(document).ready( function setUp() {
                
                var currentUrl = window.location.href;
                var Cloverurl=currentUrl.replace("welcome", "setup");
                
                $("#LoadingImage").hide();
                var inventoryThreadStatus=0;
               
                 // Cloverurl=Cloverurl+ '?' + queryStrg;

                $.ajax({
                    url : Cloverurl,
                    type : "GET",
                    contentType : "application/json; charset=utf-8",
                    success : function(minAmountData) {
                           // alert(minAmountData.inventoryThreadStatus);
                            inventoryThreadStatus=minAmountData.inventoryThreadStatus;
                           $("#categorySpanId").html(minAmountData.categoryCount);
                           $("#itemSpanId").html(minAmountData.itemCount);
                           $("#modigrpSpanId").html(minAmountData.modifierGroupCount);
                           $("#modiSpanId").html(minAmountData.modifierCount);

                    },
                    error : function() {
                        $("#LoadingImage").hide();
                        alert("error");

                    },
                    complete: function() {
                        // Schedule the next request when the current one's complete
                        //alert(inventoryThreadStatus);
                        if(inventoryThreadStatus==0){
                        setTimeout(setUp, 20000);
                        }
                      }
                });

            });
</script>
<body>
    <div id="page-container">
        <div class="foodkonnekt merchant">
            <div class="inner-container">
                <div class="max-row">
                    
                    <header id="page-header">
                        <div class="inner-header">
                            <div class="row">
                                
                                <div class="logo">
                                    <a href="index.html" title="FoodKonnekt Dashboard" class="logo"><img src="resources/img/foodkonnekt-logo.png"></a>
                                </div><!--.logo-->  
                            </div><!--.row-->
                        </div><!--.inner-header-->
                    </header><!--#page-header-->
                     <div id="LoadingImage" style="display: none" align="middle">
                        <img src="resources/img/spinner.gif" align="middle" />
                    </div>
                    <div id="page-content clearfix">
                        <div class="outer-container">
                        <div style="margin:0 auto;width: 127px;padding-top: 52px;" class="clearfix"><span style="margin:10px 0 0 0;"><b><font size="4">WELCOME</font></b></span></div>
                        
                            <div class="row">
                                <div class="content-inner-container">
                                
                                <div class="adding-products-form" style="width:800px; margin:0 auto;">
                                
                                    <div class="clearfix"></div>
                                    
                                    <b>Welcome to FoodKonnekt!</b></br>
                                    <p></p>
                                    We have already setup the following using your data from clover POS:</br>
                                    <ul>
                                    <li>Default tax rate - 8.25%</br></li>
                                    <li>Location Address - ${address}</br></li>
                                    <li>Contact information - Email: ${email} ,Mobile: ${phoneNumber}</br></li>
                                    <li>Menu Inventory has been imported from Clover as follows:</br>
                                    <ul>
                                    <li>Categories - <span id="categorySpanId">0</span></br></li>
                                    <li>Item - <span id="itemSpanId">0</span></br></li>
                                    <li>Modifier Groups - <span id="modigrpSpanId">0</span></br></li>
                                    <li>Modifier - <span id="modiSpanId">0</span></br></li>
                                    </ul>
                                    </li>
                                    </ul>
                                    Please take few minutes to setup your delivery zones,vouchers and expected pick/delivery times in the following screens.
                                    You can also setup and change these from the FoodKonnect admin later , at any point of time.
                                    
                                    
                                    <div class="clearfix"></div>
                                    <br>
                                    <div class="clearfix"></div>
                                    <div class="button left">
                                        <a href="adminPanel" style="max-width: 200px;width: 200px;">Start configuring</a>
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
                                            <img src="resources/img/foodkonnekt-logo.png" />                                        </div><!--.footer-right-->
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