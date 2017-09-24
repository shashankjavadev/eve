<!doctype html>
<html class="no-js" lang="en">
<head>

<title>FoodKonnekt | Dashboard</title>
<!--CALLING STYLESHEET STYE.CSS-->
<link rel="stylesheet" href="resources/css/style.css">
<!--CALLING STYLESHEET STYLE.CSS-->

<!--CALLING GOOGLE FONT OPEN SANS-->
<link
    href='https://fonts.googleapis.com/css?family=Open+Sans:400,300,300italic,400italic,600,600italic,700,700italic,800,800italic'
    rel='stylesheet' type='text/css'>
<!--CALLING GOOGLE FONT OPEN SANS-->

<!--CALLING FONT AWESOME-->
<link rel="stylesheet" href="resources/css/font-awesome.css">
<!--CALLING FONT AWESOME-->

<!--CALENDAR MULTI-SELECT-->
<link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/themes/smoothness/jquery-ui.css">
<link href="resources/css/calendar/jquery.comiseo.daterangepicker.css" rel="stylesheet" type="text/css">
<!--CALENDAR MULTI-SELECT-->

<!--DONUT CHART-->
<script src="https://code.jquery.com/jquery-1.12.4.js" integrity="sha256-Qw82+bXyGq6MydymqBxNPYTaUXXq7c8v3CwiYwLLNXU="
    crossorigin="anonymous"></script>
<script src="resources/js/chart/script.js"></script>
<link href="resources/css/chart/style.css" rel="stylesheet" type="text/css">
<!--DONUT CHART-->

<!--OPENS DIALOG BOX-->
<link rel="stylesheet" type="text/css" href="resources/css/dialog-box/component.css" />
<!--OPENS DIALOG BOX-->
<!--ACCORDION FOR MENU-->
<script src="resources/js/accordion/paccordion.js"></script>
<script type="text/javascript">
  $(document).ready(function() {
  $("#uploadLogo").click(function () {
    $("#logoForm").submit();
            
  });
  });
  </script>
</head>
<body>
    <div id="page-container">
        <div class="foodkonnekt dashboard">
            <div class="inner-container">
                <div class="max-row">

                    <header id="page-header">
                        <div class="inner-header">
                            <div class="row">

                                <div class="logo">
                                    <a href="adminHome" title="FoodKonnekt Dashboard" class="logo"><img
                                        src="resources/img/foodkonnekt-logo.png"></a>
                                </div>
                                <!--.logo-->
                                <div class="menu-and-dropdown">
                                    <%@ include file="adminHeader.jsp"%>

                                </div>
                                <!--.menu-and-dropdown-->

                            </div>
                            <!--.row-->
                        </div>
                        <!--.inner-header-->
                    </header>
                    <!--#page-header-->

                    <div id="page-content">
                        <div class="outer-container">

                            <div class="row">
                                <div class="content-inner-container">

                                    <%@ include file="leftMenu.jsp"%>

                                    <div class="right-content-container">
                                        <div class="right-content-inner-container">


                                            <form action="saveLogo" method="POST" id="logoForm"
                                                enctype="multipart/form-data">
                                                <div class="adding-products-form" style="width: 800px; margin: 0 auto;">
                                                  <input type="hidden" name="admin" value="admin">
                                                    <div class="clearfix"></div>
                                                    <p></p>
                                                    <p></p>
                                                    <div style="margin: 0 auto; width: 250px; padding-top: 14px;"
                                                        class="clearfix">
                                                        <img height="100" src="resources/img/rest_default_logo.jpg">
                                                    </div>
                                                    <div style="margin: 0 auto; width: 368px; padding-top: 14px;"
                                                        class="clearfix">(Max Upload size 2 mb & Dimension 230*100
                                                        in pixels)</div>
                                                    <div style="margin: 0 auto; width: 199px; padding-top: -4px;"
                                                        class="clearfix">(Image format:JPEG,PNG)</div>
                                                    <div style="margin: 0 auto; width: 330px; padding-top: 14px;"
                                                        class="clearfix">
                                                        <input type="file" name="file">
                                                    </div>
                                                    <div style="margin: 0 auto; width: 200px; padding-top: 28px;"
                                                        class=" button clearfix">
                                                        <span style="margin: 10px 0 0 0;"> <input type="button"
                                                            value="Upload" id="uploadLogo"> <a
                                                            href="setDeliveryZone">Skip</a>
                                                    </div>

                                                </div>
                                                <!--.adding-products-form-->
                                            </form>




                                        </div>
                                        <!--.right-content-inner-container-->
                                    </div>
                                    <!--.right-content-container-->


                                </div>
                                <!--.content-inner-container-->
                            </div>
                            <!--.row-->

                        </div>
                        <!--.outer-container-->
                    </div>
                    <!--#page-content-->
                    <%@ include file="adminFooter.jsp"%>
                </div>
                <!--.max-row-->
            </div>
            <!--.inner-container-->
        </div>
        <!--.foodkonnekt .dashboard-->
    </div>
    <!--#page-container-->

    <!--CALENDAR MULTI-SELECT-->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.9.0/jquery.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/jquery-ui.min.js"></script>

    <!--OPENS DIALOG BOX-->
</body>
</html>
