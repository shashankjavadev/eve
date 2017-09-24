<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page session="true"%>
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

<!--CHECK ALL LIST TABLE-->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script src="resources/js/checkall/jquery.checkall.js"></script>
<!--CHECK ALL LIST TABLE-->

<!--OPENS DIALOG BOX-->
<link rel="stylesheet" type="text/css" href="resources/css/dialog-box/component.css" />
<!--OPENS DIALOG BOX-->

<!--ACCORDION FOR MENU-->
<script src="resources/js/accordion/paccordion.js"></script>
<!--ACCORDION FOR MENU-->
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.11/css/jquery.dataTables.min.css">
<script src="https://cdn.datatables.net/1.10.11/js/jquery.dataTables.min.js"></script>
<script type="text/javascript">
jQuery.fn.dataTableExt.oApi.fnPagingInfo = function ( oSettings )
    {
       return {
           "iStart":         oSettings._iDisplayStart,
           "iEnd":           oSettings.fnDisplayEnd(),
           "iLength":        oSettings._iDisplayLength,
           "iTotal":         oSettings.fnRecordsTotal(),
           "iFilteredTotal": oSettings.fnRecordsDisplay(),
           "iPage":          oSettings._iDisplayLength === -1 ?
               0 : Math.ceil( oSettings._iDisplayStart / oSettings._iDisplayLength ),
           "iTotalPages":    oSettings._iDisplayLength === -1 ?
               0 : Math.ceil( oSettings.fnRecordsDisplay() / oSettings._iDisplayLength )
       };
   };
   
   function format ( d ) {
	    alert(d.firstName);
	}
   
   $(document).ready( function setUp() {
	   var dt =$("#checkit-table").dataTable({
           "bProcessing": true,
           "bServerSide": true,
           "sort": "position",
           "bStateSave": false,
           "iDisplayLength": 10,
           "iDisplayStart": 0,
           "fnDrawCallback": function () {
               //Get page numer on client. Please note: number start from 0 So
           },         
           "sAjaxSource": "allOrdersDataTables",
           "aoColumns": [
               { "mData": "firstName" },
               { "mData": "createdOn" },
               { "mData": "orderPrice" },
               { "mData": "orderType" },
               { "mData": "status" },
               { "mData": "orderName" },
               
               {
                   "class":          "details-control",
                   "orderable":      false,
                   "mData":           "view",
                   "defaultContent": ""
               }
           ]
           
       });
	   var detailRows = [];
       $('#checkit-table tbody').on( 'click', 'tr td.details-control', function () {
    	   var tr = $(this).closest('tr');
    	   var rowID=tr.attr('id')
    	   var table = $('#checkit-table').DataTable();
var rowData = table.row('#'+rowID).data();

var deliveryFeeDiv="";

if(rowData.orderType!='pickup' &  rowData.orderType!='Pickup' ){
	deliveryFeeDiv=deliveryFeeDiv+"<ul><li>Delivery Fee</li><li></li> <li>$ "+rowData.deliveryFee+"</li> </ul>";
}
//alert(rowData.orderItemViewVOs);
var items="";
$.each( rowData.orderItemViewVOs, function ( i ) {
	
	var modifiers=""; 
	
	$.each( rowData.orderItemViewVOs[i].itemModifierViewVOs, function ( j ) {
		modifiers=modifiers+"<ul><li>"+rowData.orderItemViewVOs[i].itemModifierViewVOs[j].modifiers.name+"</li><li>"+rowData.orderItemViewVOs[i].itemModifierViewVOs[j].quantity+"</li><li>$"+rowData.orderItemViewVOs[i].itemModifierViewVOs[j].modifiers.price.toFixed(2)+" </li></ul>";
	} );
	
	items=items+"<div class='order-details-items-quantity-price'><ul><li>"+rowData.orderItemViewVOs[i].itemVO.name+"</li><li>"+rowData.orderItemViewVOs[i].quantity+"</li><li>$"+rowData.orderItemViewVOs[i].itemVO.price.toFixed(2)+"</li></ul>"+modifiers+"</div>";
	
} );
    	   var res = rowID.split("_");
    	   rowID = res[1];
    	   var divId="modal-15-"+rowID;
    	   var convenienceFeeDiv="";
    	  var tip="";
    	  
    	  if(rowData.tipAmount>0){
    		  tip=tip+"<ul> <li>Tip</li> <li></li><li>$ "+rowData.tipAmount.toFixed(2)+"</li></ul>";
    	  }
    	   
    	  if(rowData.discount>0){
    		  tip=tip+"<ul> <li>Discount</li> <li></li><li>$ "+rowData.discount.toFixed(2)+"</li></ul>";
    	  }
    	  if(rowData.convenienceFee==''){
    		  
    	  }else{
    		  convenienceFeeDiv=convenienceFeeDiv+"<ul> <li>Convenience Fee</li> <li></li><li>$ "+rowData.convenienceFee+"</li></ul>";
    	  }
    	   
    	   $("#sd-dialog").append("<div id='sd-dialog'><div class='md-modal md-effect-14' id='modal-15-"+rowID+"'><div class='md-content'><h3>ORDER DETAILS</h3><div><div class='order-details-container'><div class='order-details-header'><ul><li><span>Item</span></li><li><span>Quantity</span></li><li><span>Price</span></li></ul></div>"+items+"<div class='order-subtotal-tax-total'><ul><li>Subtotal</li><li>$"+Number(rowData.subTotal).toFixed(2)+"</li> </ul> "+convenienceFeeDiv+" "+deliveryFeeDiv+"<ul><li>Tax</li><li>$"+Number(rowData.tax).toFixed(2)+"</li> </ul>"+tip+" <ul> <li>Total</li><li>$"+rowData.orderPrice+" </li> </ul> </div><div class='order-details-order-notes'><textarea name='order-notes' cols='' rows='4' readonly='readonly'>"+rowData.orderName+" </textarea></div> </div><div class='clearfix'></div> <div class='button'><button class='white-button md-close' onClick='closeNavSignIn()'>Close</button></div> </div> </div> </div><div class='md-overlay'></div></div>");
    	   
    	   
    	   $("#modal-15-"+rowID).addClass('md-show');
       } );
      
       
       dt.on( 'draw', function () {
           $.each( detailRows, function ( i, id ) {
               $('#'+id+' td.details-control').trigger( 'click' );
           } );
       } );
       
       $(".white-button").click(function () {
    	  
    	   
          });
       
       
       
       
   });  
   
   function closeNavSignIn(){
	   $(".md-modal").removeClass('md-show');
   }
</script>

<style type="text/css">
div#example_paginate {
    display: block;
}

 div#checkit-table_filter {
    display: none;
} 

checkit-table_filter

div#example_length {
    display: block;
}

input[type="search"] {
    max-width: 300px;
    width: 100%;
    outline: 0;
    border: 1px solid rgb(169, 169, 169);
    padding: 11px 10px;
    border-radius: 6px;
    margin-bottom: 7px;
    display: none;
}
</style>
</head>
<body>
    <div id="page-container">
        <div class="foodkonnekt orders">
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
                                <%@ include file="adminHeader.jsp"%>
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
                                            <div class="content-header">
                                                <div class="all-header-title"></div>
                                                <!--.header-title-->
                                                <div class="content-header-dropdown"></div>
                                                <!--.content-header-dropdown-->
                                            </div>
                                            <!--.content-header-->
                                            <div class="orders-page-data">

                                                <div class="sd-orders-list-outbound">
                                                    <div class="sd-orders-list">
                                                        <div class="sd-orders-list-inbound">
                                                            <div class="orders-items-list-table">
                                                                <div class="search-container" style="margin-top: 10px">
                                                                    <div class="only-search-elements">
                                                                        <label>Search</label> 
                                                                        <input type="text" placeholder="Search Orders" id="search-inventory" class="searchq">
                                                                        <input type="button" value="Search">
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <!--.inventory-items-list-table-->
                                                            <table id="checkit-table" class="table header-table-orders">
                                                                <thead>
                                                                    <tr>
                                                                        <th>Customer Name</th>
                                                                        <th>Date and Time</th>
                                                                        <th>Order Total</th>
                                                                        <th>Order Type</th>
                                                                        <th>Order Status</th>
                                                                        <th>Order Notes</th>
                                                                        <th>View</th>
                                                                       
                                                                    </tr>
                                                                </thead>
                                                               
                                                            </table>
                                                        </div>
                                                        <!--.sd-orders-list-inbound-->
                                                    </div>
                                                    <!--.sd-orders-list-->
                                                </div>
                                                <!--.sd-orders-list-outbound-->

                                            </div>
                                            <!--.orders-page-data-->


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
                    <!--#footer-container-->

                </div>
                <!--.max-row-->
            </div>
            <!--.inner-container-->
        </div>
        <!--.foodkonnekt .dashboard-->
    </div>
    <!--#page-container-->


    <script type="text/javascript">
                    var _gaq = _gaq || [];
                    _gaq.push([ '_setAccount', 'UA-36251023-1' ]);
                    _gaq.push([ '_setDomainName', 'jqueryscript.net' ]);
                    _gaq.push([ '_trackPageview' ]);

                    (function() {
                        var ga = document.createElement('script');
                        ga.type = 'text/javascript';
                        ga.async = true;
                        ga.src = ('https:' == document.location.protocol ? 'https://ssl'
                                : 'http://www')
                                + '.google-analytics.com/ga.js';
                        var s = document.getElementsByTagName('script')[0];
                        s.parentNode.insertBefore(ga, s);
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

    <!-- <script src="https://cdn.datatables.net/1.10.11/js/jquery.dataTables.min.js"></script> -->
    <script type="text/javascript">
    /*  $(document).ready(function() {
        var table = $('#checkit-table').DataTable();
        $(".searchq").keyup(function() {
             table.search( this.value).draw();
         } );
     }); */
     $(document).ready(function(){
       $('input[type=search]').each(function(){
         $(this).attr('placeholder', "Search");
       });
     });
     $("#generateButton").click(function () {
       $("#searchForm").submit();
     });
     $("#generateButton").click(function () {
         $("#searchForm").submit();
       });
     
     
 </script>
    <script>

$(function () {
    $(".custom-sd-button-dialog").click(function () {
        alert($(this).attr("orderAttr"));
    });
    
    $("#search-inventory").keyup(function() {
       var searchTxt=$(this).val();
       if ($.fn.DataTable.isDataTable('#checkit-table') ) {
           $('#checkit-table').DataTable().destroy();
        }

      $('#checkit-table tbody').empty();
      
      var dt =$("#checkit-table").dataTable({
          "bProcessing": true,
          "bServerSide": true,
          "sort": "position",
          "bStateSave": false,
          "iDisplayLength": 10,
          "iDisplayStart": 0,
          "fnDrawCallback": function () {
              //Get page numer on client. Please note: number start from 0 So
          },         
          "sAjaxSource": "searchOrderByText?searchTxt="+searchTxt,
          "aoColumns": [
              { "mData": "firstName" },
              { "mData": "createdOn" },
              { "mData": "orderPrice" },
              { "mData": "orderType" },
              { "mData": "status" },
              { "mData": "orderName" },
              
              {
                  "class":          "details-control",
                  "orderable":      false,
                  "mData":           "view",
                  "defaultContent": ""
              }
          ]
          
      });
	   var detailRows = [];
      $('#checkit-table tbody').on( 'click', 'tr td.details-control', function () {
   	   var tr = $(this).closest('tr');
   	   var rowID=tr.attr('id')
   	   var table = $('#checkit-table').DataTable();
var rowData = table.row('#'+rowID).data();

var deliveryFeeDiv="";

if(rowData.orderType!='pickup' &  rowData.orderType!='Pickup' ){
	deliveryFeeDiv=deliveryFeeDiv+"<ul><li>Delivery Fee</li><li></li> <li>$ "+rowData.deliveryFee+"</li> </ul>";
}
//alert(rowData.orderItemViewVOs);
var items="";
$.each( rowData.orderItemViewVOs, function ( i ) {
	
	var modifiers=""; 
	
	$.each( rowData.orderItemViewVOs[i].itemModifierViewVOs, function ( j ) {
		modifiers=modifiers+"<ul><li>"+rowData.orderItemViewVOs[i].itemModifierViewVOs[j].modifiers.name+"</li><li>"+rowData.orderItemViewVOs[i].itemModifierViewVOs[j].quantity+"</li><li>$"+rowData.orderItemViewVOs[i].itemModifierViewVOs[j].modifiers.price.toFixed(2)+" </li></ul>";
	} );
	
	items=items+"<div class='order-details-items-quantity-price'><ul><li>"+rowData.orderItemViewVOs[i].itemVO.name+"</li><li>"+rowData.orderItemViewVOs[i].quantity+"</li><li>$"+rowData.orderItemViewVOs[i].itemVO.price.toFixed(2)+"</li></ul>"+modifiers+"</div>";
	
} );
   	   var res = rowID.split("_");
   	   rowID = res[1];
   	   var divId="modal-15-"+rowID;
   	   var convenienceFeeDiv="";
   	  var tip="";
   	  
   	  if(rowData.tipAmount>0){
   		  tip=tip+"<ul> <li>Tip</li> <li></li><li>$ "+rowData.tipAmount.toFixed(2)+"</li></ul>";
   	  }
   	   
   	  if(rowData.convenienceFee==''){
   		  
   	  }else{
   		  convenienceFeeDiv=convenienceFeeDiv+"<ul> <li>Convenience Fee</li> <li></li><li>$ "+rowData.convenienceFee+"</li></ul>";
   	  }
   	   
   	   $("#sd-dialog").append("<div id='sd-dialog'><div class='md-modal md-effect-14' id='modal-15-"+rowID+"'><div class='md-content'><h3>ORDER DETAILS</h3><div><div class='order-details-container'><div class='order-details-header'><ul><li><span>Item</span></li><li><span>Quantity</span></li><li><span>Price</span></li></ul></div>"+items+"<div class='order-subtotal-tax-total'><ul><li>Subtotal</li><li>$"+Number(rowData.subTotal).toFixed(2)+"</li> </ul> "+convenienceFeeDiv+" "+deliveryFeeDiv+"<ul><li>Tax</li><li>$"+Number(rowData.tax).toFixed(2)+"</li> </ul>"+tip+" <ul> <li>Total</li><li>$"+rowData.orderPrice+" </li> </ul> </div><div class='order-details-order-notes'><textarea name='order-notes' cols='' rows='4' readonly='readonly'>"+rowData.orderName+" </textarea></div> </div><div class='clearfix'></div> <div class='button'><button class='white-button md-close' onClick='closeNavSignIn()'>Close</button></div> </div> </div> </div><div class='md-overlay'></div></div>");
   	   
   	   
   	   $("#modal-15-"+rowID).addClass('md-show');
      } );
     
      
      dt.on( 'draw', function () {
          $.each( detailRows, function ( i, id ) {
              $('#'+id+' td.details-control').trigger( 'click' );
          } );
      } );
      
      
     
     });
});
</script>
</body>
</html>