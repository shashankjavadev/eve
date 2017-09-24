<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<!doctype html>
<html class="no-js" lang="en">
  
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
    
    <!--CALENDAR MULTI-SELECT-->
    <link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/themes/smoothness/jquery-ui.css">
    <link href="resources/css/calendar/jquery.comiseo.daterangepicker.css" rel="stylesheet" type="text/css">
    <!--CALENDAR MULTI-SELECT-->
    
    <!--DONUT CHART-->
    <script src="https://code.jquery.com/jquery-1.12.4.js"   integrity="sha256-Qw82+bXyGq6MydymqBxNPYTaUXXq7c8v3CwiYwLLNXU="   crossorigin="anonymous"></script>
    <script src="resources/js/chart/script.js"></script>
    <link href="resources/css/chart/style.css" rel="stylesheet" type="text/css">
    <!--DONUT CHART-->  
    
    <!--OPENS DIALOG BOX-->
    <link rel="stylesheet" type="text/css" href="resources/css/dialog-box/component.css" />
    <!--OPENS DIALOG BOX-->
    <!--ACCORDION FOR MENU-->
    <script src="resources/js/accordion/paccordion.js"></script>
    
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
                                    <a href="adminHome" title="FoodKonnekt Dashboard" class="logo"><img src="resources/img/foodkonnekt-logo.png"></a>
                                </div><!--.logo-->
                                <div class="menu-and-dropdown">
                                <%@ include file="adminHeader.jsp" %> 
                                <div class="content-header">
                            </div><!--.content-header-->
                            </div><!--.menu-and-dropdown-->
                                
                            </div><!--.row-->
                        </div><!--.inner-header-->
                    </header><!--#page-header-->
                    
                    <div id="page-content">
                        <div class="outer-container">
                        
                            <div class="row">
                                <div class="content-inner-container">
                                     <%@ include file="leftMenu.jsp" %> 
                                
                                    <div class="right-content-container">
                                        <div class="right-content-inner-container">
                                        
                                            
                                            
                                            <div class="three-blocks-home">
                                                <div class="one-block-outbound">
                                                    <div class="one-block">
                                                        <div class="one-block-inboune">
                                                            <div class="one-block-header">
                                                                <h3>Orders</h3>
                                                            </div><!--.one-block-header-->
                                                            <div class="one-block-container">
                                                                <!-- <ul class="os-percentages horizontal-list">
                                                                    <li>
                                                                        <p class="ios os scnd-font-color">Total Orders</p>
                                                                        <p class="os-percentage">21<sup>%</sup></p>
                                                                    </li>
                                                                    <li>
                                                                        <p class="mac os scnd-font-color">Average Order Value</p>
                                                                        <p class="os-percentage">39<sup>%</sup></p>
                                                                    </li>
                                                                    <li>
                                                                        <p class="linux os scnd-font-color">Orders Per Day</p>
                                                                        <p class="os-percentage">9<sup>%</sup></p>
                                                                    </li>
                                                                </ul> -->
                                                                <div id="piechart" style="width: 300px;"></div>
                                                                <div class="chart-teller">
                                                                    <ul>
                                                                        <li class="chart-teller-one">Total Orders: ${totalOrderValue}</li>
                                                                          <li class="chart-teller-two">Avg Order Value: &nbsp; ${avgOrderValue}</li>
                                                                          <li class="chart-teller-four">Number of Delivery Orders: &nbsp; ${noOfDeliveryOrder}</li>
                                                                        <li class="chart-teller-three">Number of Take Out Orders : &nbsp; ${noOfPickUpOrder}</li>
                                                                    </ul>
                                                                </div><!--.chart-teller-->
                                                            </div><!--.one-block-container-->
                                                        </div><!--.one-block-inboune-->
                                                    </div><!--.one-block-->
                                                </div><!--.one-block-outbound-->
                                                
                                                <div class="one-block-outbound">
                                                    <div class="one-block">
                                                        <div class="one-block-inboune">
                                                            <div class="one-block-header">
                                                                <h3>Customers</h3>
                                                            </div><!--.one-block-header-->
                                                            <div class="one-block-container">
                                                               <!--  <div id="chart">
                                                                  <ul id="bars">
                                                                    <li class="customer-chart-teller-one"><div data-percentage="56" class="bar"></div><span></span></li>
                                                                    <li class="customer-chart-teller-two"><div data-percentage="33" class="bar"></div><span></span></li>
                                                                    <li class="customer-chart-teller-three"><div data-percentage="54" class="bar"></div><span></span></li>
                                                                  </ul>#barsr
                                                                </div>#chart -->
                                                                 <div id="customerChart" style="width: 300px;"></div>
                                                                <div class="chart-teller">
                                                                    <ul>
                                                                        <li class="chart-teller-one">Total Number of customer: &nbsp; ${totalNoOfCustomer}</li>
                                                                       <li class="chart-teller-two">Order Frequency: &nbsp; ${orderFrequency}</li>
                                                                        <li class="chart-teller-three">Customer Order Average: &nbsp; ${customerOrderAverage}</li>
                                                                    </ul>
                                                                </div><!--.chart-teller-->
                                                            </div><!--.one-block-container-->
                                                        </div><!--.one-block-inboune-->
                                                    </div><!--.one-block-->
                                                </div><!--.one-block-outbound-->
                                                
                                                <div class="one-block-outbound">
                                                    <div class="one-block">
                                                        <div class="one-block-inboune">
                                                            <div class="one-block-header">
                                                                <h3>Items</h3>
                                                            </div><!--.one-block-header-->
                                                            <div class="one-block-container">
                                                                <!-- <div id="chart">
                                                                  <ul id="bars">
                                                                    <li class="customer-chart-teller-one"><div data-percentage="88" class="bar"></div><span></span></li>
                                                                    <li class="customer-chart-teller-two"><div data-percentage="43" class="bar"></div><span></span></li>
                                                                    <li class="customer-chart-teller-three"><div data-percentage="22" class="bar"></div><span></span></li>
                                                                  </ul>#barsr
                                                                </div>#chart -->
                                                                <div id="itemChart" style="width: 300px;"></div>
                                                                <div class="chart-teller">
                                                                    <ul>
                                                                        <li class="chart-teller-two">Trending Item:&nbsp;<b>${trendingItem}</b></li>
                                                                        <li class="chart-teller-one">Avg Number of Items Per Order: &nbsp; ${averageNumberOfItemPerOrder}</li>
                                                                    </ul>
                                                                </div><!--.chart-teller-->
                                                            </div><!--.one-block-container-->
                                                        </div><!--.one-block-inboune-->
                                                    </div><!--.one-block-->
                                                </div><!--.one-block-outbound-->
                                                
                                                
                                            </div><!--.three-blocks-home-->
                                            
                                        </div><!--.right-content-inner-container-->
                                    </div><!--.right-content-container-->
                                </div><!--.content-inner-container-->
                            </div><!--.row-->
                            
                        </div><!--.outer-container-->
                    </div><!--#page-content-->
               <%@ include file="adminFooter.jsp" %>
                </div><!--.max-row-->
            </div><!--.inner-container-->
        </div><!--.foodkonnekt .dashboard-->
    </div><!--#page-container-->
    
    
    <div id="sd-dialog">
        <div class="md-modal md-effect-14" id="modal-14">
            <div class="md-content">
                <h3>Modal Dialog</h3>
                <div>
                    <p>This is a modal window. You can do the following things with it:</p>
                    <div class="button">
                        <button class="white-button md-close">Close me!</button>
                    </div><!--.button-->
                </div>
            </div>
        </div>
        <div class="md-overlay"></div><!-- the overlay element -->
    </div><!--#sd-dialog-->
    
    <!--CALENDAR MULTI-SELECT-->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.9.0/jquery.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/jquery-ui.min.js"></script>
    <script src="resources/js/calendar/jquery.comiseo.daterangepicker.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.8.3/moment.min.js"></script>
    <script src="resources/js/chart/loader.js"></script>
    <script>
    $(function() {
      $("#e1").daterangepicker();
    
       $("#e2").daterangepicker({
         datepickerOptions : {
             numberOfMonths : 2
         }
     });
    
     $("#e3").daterangepicker({
         presetRanges: [{
             text: 'Today',
             dateStart: function() { return moment() },
             dateEnd: function() { return moment() }
         }, {
             text: 'Tomorrow',
             dateStart: function() { return moment().add('days', 1) },
             dateEnd: function() { return moment().add('days', 1) }
         }, {
             text: 'Next 7 Days',
             dateStart: function() { return moment() },
             dateEnd: function() { return moment().add('days', 6) }
         }, {
             text: 'Next Week',
             dateStart: function() { return moment().add('weeks', 1).startOf('week') },
             dateEnd: function() { return moment().add('weeks', 1).endOf('week') }
         }],
         applyOnMenuSelect: false,
         datepickerOptions: {
             minDate: 0,
             maxDate: null
         }
     });
      $("#e4").daterangepicker();
     $("#e4_get").click(function () {
         alert("Selected range is: " + JSON.stringify($("#e4").daterangepicker("getRange")));
     });
     $("#e4_gets").click(function () { alert("Input value is: " + $("#e4").val());});
     $("#e4_set").click(function () {
         var yesterday = moment().subtract('days', 1).startOf('day').toDate();
         $("#e4").daterangepicker("setRange", {start: yesterday});
     });
     $("#e4_cl").click(function() { $("#e4").daterangepicker("clearRange"); });
     $("#e4_open").click(function () { $("#e4").daterangepicker("open"); });
     $("#e4_close").click(function () { $("#e4").daterangepicker("close"); });
      $("#e5").daterangepicker();
     $("#e5_init").click(function() {
         $("#e5").daterangepicker();
     });
     $("#e5_destroy").click(function() {
         $("#e5").daterangepicker("destroy");
     });
    });
    </script>
    <script type="text/javascript">
    
      var _gaq = _gaq || [];
      _gaq.push(['_setAccount', 'UA-36251023-1']);
      _gaq.push(['_setDomainName', 'jqueryscript.net']);
      _gaq.push(['_trackPageview']);
    
      (function() {
        var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
        ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
        var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
      })();
    
    </script>
    <!--CALENDAR MULTI-SELECT-->
    
    <!--OPENS DIALOG BOX-->
    <script src="resources/js/dialog-box/classie.js"></script>
    <script src="resources/js/dialog-box/modalEffects.js"></script>
    <script>
        var polyfilter_scriptpath = '/js/';
    </script>
    <!--OPENS DIALOG BOX-->
        <script type="text/javascript">
      google.charts.load('current', {'packages':['corechart']});
      google.charts.setOnLoadCallback(drawChart);
      function drawChart() {

        var data = google.visualization.arrayToDataTable([
          ['Orders', '$'],
          ['Total Orders',     ${totalOrderValue}],
          ['Avg Order Value',${avgOrderValue}],
          ['Number of Delivery Order',  ${noOfDeliveryOrder}],
          ['Number of Take Out Orders',  ${noOfPickUpOrder}]
        ]);

        var options = {
          pieHole: 0.4,
          title: '',
          pieSliceText: "none",
          legend: 'none',
          colors:['#fe8005','#cccccc','#318CE7','#484848']
        };
        var chart = new google.visualization.PieChart(document.getElementById('piechart'));

        chart.draw(data, options);
      }

      google.charts.load('current', {'packages':['bar']});
      google.charts.setOnLoadCallback(drawCustomerChart);
      function drawCustomerChart() {

        var data = google.visualization.arrayToDataTable([
          ['Orders', '$',{ role: 'style' }],
          ['Total Number of customer',  ${totalNoOfCustomer},   '#fe8005'],
          ['Order Frequency',${orderFrequency},'#cccccc'],
          ['Customer Order Average',  ${customerOrderAverage},'#484848']
        ]);

        var options = {
          title: '',
          hAxis: { 
            baselineColor: 'none',
              ticks: [],
              textPosition: 'none' 
            },
            vAxis: { 
            baselineColor: 'none',
              ticks: [],
              textPosition: 'none' 
            },
          legend: 'none'
        };
        var chart = new google.visualization.ColumnChart(document.getElementById('customerChart'));

        chart.draw(data, options);
      }

      google.charts.setOnLoadCallback(drawItemsChart);
      function drawItemsChart() {

        var data = google.visualization.arrayToDataTable([
          ['Orders', ' ',{ role: 'style' }],
          ['Avg Number of Items Per Order', ${averageNumberOfItemPerOrder},   '#fe8005']
          /* ['Average Order Value',10,'#cccccc'],
          ['Orders Per Day',  8,'#484848'] */
        ]);

        var options = {
          title: '',
          hAxis: { 
            baselineColor: 'none',
              ticks: [],
              textPosition: 'none' 
            },
            vAxis: { 
            baselineColor: 'none',
              ticks: [],
              textPosition: 'none' 
            },
          legend: 'none'
        };
        var chart = new google.visualization.ColumnChart(document.getElementById('itemChart'));

        chart.draw(data, options);
      }
    </script>
  </body>
</html>
