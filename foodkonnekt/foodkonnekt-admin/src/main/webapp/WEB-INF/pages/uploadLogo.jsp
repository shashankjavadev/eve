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
    <link type="text/css" rel="stylesheet" href="resources/css/popModal.css">
    <script src="resources/js/popModal.js"></script>
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
  <script type="text/javascript">
  $(document).ready(function() {
  var imgPath="${imagePath}";
  if(imgPath!=""){
      document.getElementById("imgTest").src=imgPath;
      jQuery(function(){
           jQuery('#confirmModal_ex2').click();
        });
  }
  $("#uploadLogo").click(function () {
    $("#logoForm").submit();
     });
  });

  function encodeImageFileAsURL() {
        var filesSelected = document.getElementById("inputFileToLoad").files;
        if (filesSelected.length > 0) {
          var fileToLoad = filesSelected[0];
          var fileReader = new FileReader();
          fileReader.onload = function(fileLoadedEvent) {
            var srcData = fileLoadedEvent.target.result; // <--- data: base64
            document.getElementById("imgTest").src=srcData;
          }
          fileReader.readAsDataURL(fileToLoad);
        }
      }
  function openDeliveryFeePage(){
       window.location.href = "setDeliveryZone";
      }
  </script>
  <body>
<div class="exampleLive">
    <button id="confirmModal_ex2" style="display: none" class="btn btn-primary" data-confirmmodal-bind="#confirm_content" data-topoffset="0" data-top="10%">Example</button>
</div>
            
<div id="confirm_content" style="display:none">
    <div class="confirmModal_content sd-popup-content">
    <img src="resources/img/logo.png" class="sd-popup-logo">
    <h3>Your logo has been successfully uploaded</h3>
    </div>
    <div class="confirmModal_footer">
        <button type="button" class="btn btn-primary uploadedBtn" data-confirmmodal-but="ok" onclick="openDeliveryFeePage()" >Continue</button>
    </div>
</div>
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
                    
                    <div id="page-content clearfix">
                        <div class="outer-container">
                        <div style="margin:0 auto;width: 217px;padding-top: 52px;" class="clearfix"><span style="margin:10px 0 0 0;">
                        <b><font size="4">SET UP BUSINESS LOGO</font></b></span>
                        
                        </div>
                        
                            <div class="row">
                                <div class="content-inner-container"> 
                                
                              <form action="saveLogo" method="POST" id="logoForm" enctype="multipart/form-data">                                
                                <div class="adding-products-form" style="width:800px; margin:0 auto;">
                                
                                    <div class="clearfix"></div>
                                    <p></p><p></p>
                                    <div style="margin:0 auto;width: 175px;padding-top: 14px;" class="clearfix">
                                    <img height="100" src="resources/img/rest_default_logo.jpg" id="imgTest">
                                    </div>
                                    <div style="margin:0 auto;width: 368px;padding-top: 14px;" class="clearfix">
                                    (Max Upload size 2 mb & Dimension 230*100 in pixels)
                                    </div>
                                    <div style="margin:0 auto;width: 199px;padding-top: -4px;" class="clearfix">
                                    (Image format:JPEG,PNG)
                                    </div>
                                    <div style="margin:0 auto;width: 330px;padding-top: 14px;" class="clearfix">
                                    <input type="file" name="file" id="inputFileToLoad" accept="image/*" onchange="encodeImageFileAsURL();"><span style="color: red">${filesize}</span> 
                                    </div>
                                    <br>
                                    <div style="margin:0 auto;width: 330px;padding-top: 28px;height: 43px;" class=" button clearfix">
                                    <span style="margin:10px 0 0 0;">
                                        <input type="button" value="Upload" id="uploadLogo" style="height: 43px;">
                                        <a style="float: left;height: 43px;" href="setDeliveryZone">Skip</a>
                                    </div>

                                    <div style="margin:0 auto;width: 434px;padding-top: 28px;" class="clearfix">
                                    You can change these setting later too from your admin console.
                                    </div>
                                    
                               </div><!--.adding-products-form-->
                               </form>
                               
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