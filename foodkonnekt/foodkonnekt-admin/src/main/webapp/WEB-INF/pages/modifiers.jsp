<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<!doctype html>
<html class="no-js" lang="en">
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
    
    <!--CALLING CHECK ALL FUNCTIONALITY-->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
    <script src="resources/js/checkall/jquery.checkall.js"></script>
    <!--CALLING CHECK ALL FUNCTIONALITY-->

    <!--OPENS DIALOG BOX-->
    <link rel="stylesheet" type="text/css" href="resources/css/dialog-box/component.css" />
    <!--OPENS DIALOG BOX-->
    
    <!--ACCORDION FOR MENU-->
    <script src="resources/js/accordion/paccordion.js"></script>
    <!--ACCORDION FOR MENU-->
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.11/css/jquery.dataTables.min.css">
    <script src="https://cdn.datatables.net/1.10.11/js/jquery.dataTables.min.js"></script>
    <style type="text/css">
      div#example_paginate {
        display: block;
       }
      div#example_filter {
         display: none;
      }
      div#example_length {
         display: block;
      }
    
      /* input[type="search"]{
     display:none;
        max-width: 300px;
        width: 100%;
        outline: 0;
        border: 1px solid rgb(169, 169, 169);
        padding: 11px 10px;
        border-radius: 6px;
        margin-bottom: 7px;
        placeholder:Search Items;
    } */
    </style>
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

$(document).ready( function setUp() {
    $.fn.dataTableExt.sErrMode = 'throw';
   
    var currentUrl = window.location.href;
    var Cloverurl=currentUrl.replace("modifiers", "isInstalled");
    $("#LoadingImage").show();
    
    /* var inventoryThreadStatus="${inventoryThread}";
    if(inventoryThreadStatus==1){
        $("#content1").show();
        $("#LoadingImage").hide();
    }else{
        $("#LoadingImage").show();
        $("#content1").hide();
    } */
   
     // Cloverurl=Cloverurl+ '?' + queryStrg;
         var inventoryThreadStatus;
    $.ajax({
        url : Cloverurl,
        type : "GET",
        contentType : "application/json; charset=utf-8",
        success : function(minAmountData) {
            
               if(minAmountData==true){
                   inventoryThreadStatus=true;
                   $("#LoadingImage").hide();
                   $("#content4").show();
                   loadModifiers();
                  // location.reload();
               }else{
                   $("#LoadingImage").show();
                   $("#content4").hide();
                   inventoryThreadStatus=false;
               }

        },
        error : function() {
            $("#LoadingImage").hide();
            alert("error");

        },
        complete: function() {
            if(inventoryThreadStatus==false){
            setTimeout(setUp, 5000);
            }
          }
    });

});

function loadModifiers(){
      $("#example").dataTable({
            "bProcessing": true,
            "bServerSide": true,
            "sort": "position",
            "bStateSave": false,
            "iDisplayLength": 10,
            "iDisplayStart": 0,
            "fnDrawCallback": function () {
                //Get page numer on client. Please note: number start from 0 So
                //for the first page you will see 0 second page 1 third page 2...
                //Un-comment below alert to see page number
                //alert("Current page number: "+this.fnPagingInfo().iPage);    
            },         
            "sAjaxSource": "modifiersDataTables",
            "aoColumns": [
                { "mData": "name" },
                { "mData": "price" },
                { "mData": "productUsed" },
            ]
        });
}
      
 $(document).ready(function(){
   $("#categoryId").change(function(){
       $.fn.dataTableExt.sErrMode = 'throw';
   var categoryId=$("#categoryId").val();
      if(categoryId==0){
          if ( $.fn.DataTable.isDataTable('#example') ) {
              $('#example').DataTable().destroy();
            }

          $('#example tbody').empty();
          
          $('#itemId').html('');
          $('<option value=0>Select Item</option>').appendTo('#itemId');
          $("#LoadingImage").show();
          $("#example").hide();
          
          $.ajax({
              type: 'GET',
              url: "getAllModifiers?merchantId=${merchant.id}",
              success:function(data){
                  $("#LoadingImage").hide();
                  $("#example").show();
               var outer = [];
               
               $.each(data, function( index, value ) {
                 var inner = [];
                 inner.push(value.name);
                 inner.push("$"+value.price);
                 inner.push(value.itemCount);
                 outer.push(inner);
               });
               console.log(outer);
               $('#example').DataTable({
                 data: outer,
                 columns: [
                     { title: "Name" },
                     { title: "Price" },
                     { title: "Products used" }
                 ]
               });
              }
           });
      }else{
      $.ajax({
            type: 'GET',
            url: "itemByCategoryId?categoryId="+categoryId,
            success:function(data){
              $('#itemId').html('');
              $('<option value=0>Select Item</option>').appendTo('#itemId'); 
             for ( var field in data) {
               $('<option value="' + data[field].id + '">' + data[field].name+'</option>').appendTo('#itemId');
             }
            }
         });
        }
    });
      
    $('#itemId').change(function () {
      if ( $.fn.DataTable.isDataTable('#example') ) {
         $('#example').DataTable().destroy();
    }

        $('#example tbody').empty();
        var itemId=$('#itemId').val();
        $.ajax({
          type: 'GET',
          url: "modifiersByItemId?itemId="+itemId,
          success:function(data){
           var outer = [];
           $.each(data, function( index, value ) {
             var inner = [];
             inner.push(value.name);
             inner.push("$"+value.price);
             inner.push(value.itemCount);
             outer.push(inner);
           });
           console.log(outer);
           $('#example').DataTable( {
             data: outer,
             columns: [
                 { title: "Name" },
                 { title: "Price" },
                 { title: "Products used" }
             ]
         } );
          }
       });
   });
});
</script>
  </head>
  <body>
    <div id="page-container">
        <div class="foodkonnekt inventory">
            <div class="inner-container">
                <div class="max-row">
                    
                    <header id="page-header">
                        <div class="inner-header">
                            <div class="row">
                                
                                <div class="logo">
                                    <a href="adminHome" title="FoodKonnekt Dashboard" class="logo"><img src="resources/img/foodkonnekt-logo.png"></a>
                                </div><!--.logo-->
                                 <%@ include file="adminHeader.jsp" %>
                                
                            </div><!--.row-->
                        </div><!--.inner-header-->
                    </header><!--#page-header-->
                    
                    <div id="page-content">
                        <div class="outer-container">
                        
                            <div class="row">
                                <div class="content-inner-container">
                                     <%@ include file="leftMenu.jsp"%>
                                
                                    <div class="right-content-container">
                                        <div class="right-content-inner-container">
                                        
                                            <div class="content-header">
                                                <div class="all-header-title">
                                                </div><!--.header-title-->
                                            </div><!--.content-header-->
                                            
                                            <div class="merchant-page-data">
                                                <div class="merchant-actions-outbound">
                                                    <div class="merchat-coupons-container">
                                                            <!-- <main> -->
                                                            <div class="coupons-navigation">
                                                                   <ul>
                                                                        <li ><a href="inventory">Items</a></li>
                                                                        <li><a href="category">Categories</a></li>
                                                                        <li><a href="modifierGroups">Modifier Groups</a></li>
                                                                        <li class="current-menu-item"><a href="modifiers">Modifiers</a></li>
                                                                    </ul>
                                                        </div><!--.within-page-horizontal-menu-->
                                                              <div id="LoadingImage" style="display: none" align="middle">
                                                          <img src="resources/img/spinner.gif" align="middle" />
                                                             </div>
                                                              <section id="content4">
                                                                <div class="tab-content-container-outbound">
                                                                    <div class="tab-content-container">
                                                                        <div class="tab-content-container-inbound">
                                                                            <div class="only-search-part">
                                                                             <div class="search-container">

                                                                                <div class="only-search-elements">
                                                                                    <label>Search</label> 
                                                                                    <input type="text"  placeholder="Search Modifiers" id="search-inventory" class="searchq" style="max-width: 793px;height: 48px !important;"> 
                                                                                    <input type="button" value="Search">
                                                                                </div>

                                                                         <div class="only-filter-container">
                                                                                        <label>Filter By</label>
                                                                                        <select id="categoryId">
                                                                                            <option value="0">All Categories</option>
                                                                                            <c:forEach items="${categories}" var="view" varStatus="status">
                                                                                            <option value="${view.id}">${view.name}</option>
                                                                                        </c:forEach>
                                                                                        </select>
                                                                                       <select id="itemId">
                                                                                        </select>
                                                                                    </div><!--.only-filter-container-->
                                                                                </div><!--.search-container-->
                                                                            </div>
                                                                            <div class="inventory-items-list-table">
                                                                                    <!-- Data table -->
                                                                                    <div id="LoadingImage" style="display: none">
                                                                                    <img src="resources/img/spinner.gif" />
                                                                                </div>
                                                                                    <table  width="100%" cellpadding="0" cellspacing="0" id="example" class="table">
                                                                                    <thead>
                                                                                    <tr>
                                                                                    <th data-type="string">Name</th>
                                                                                    <th data-type="string">Price</th>
                                                                                    <th>Products used</th>
                                                                                    </tr>
                                                                                    </thead>
                                                                                    </table>
                                                                                    <!-- /Data table -->
                                                                                </div><!--.inventory-items-list-table-->
                                                                            </div><!--.only-search-part-->
                                                                        </div><!--.tab-content-container-inbound-->
                                                                    </div><!--.tab-content-container-->
                                                                </div><!--.tab-content-container-outbound-->
                                                              </section>
                                                           <!--  </main>
                                                        </div> --><!--.inventory-tabs-inbound-->
                                                    </div><!--.inventory-tabs-->
                                                </div><!--.inventory-tabs-outbound-->
                                            </div><!--.inventory-page-data-->
                                            
                                        </div><!--.right-content-inner-container-->
                                    </div><!--.right-content-container-->
                                </div><!--.content-inner-container-->
                            </div><!--.row-->
                            <div id="example_filter" class="dataTables_filter">
                        </div><!--.outer-container-->
                    </div><!--#page-content-->
                    
                      
                      <%@ include file="adminFooter.jsp" %>
                    <!--#footer-container-->
                    
                </div><!--.max-row-->
            </div><!--.inner-container-->
        </div><!--.foodkonnekt .dashboard-->
    </div><!--#page-container-->
     <!--OPENS DIALOG BOX-->
    <script src="resources/js/dialog-box/classie.js"></script>
    <script src="resources/js/dialog-box/modalEffects.js"></script>
    <script>
        var polyfilter_scriptpath = '/js/';
    </script>
    <!--OPENS DIALOG BOX-->
 <script>
$(function(){
    $("#search-inventory").keyup(function() {
         $.fn.dataTableExt.sErrMode = 'throw';
         var searchTxt=$(this).val();
          if ( $.fn.DataTable.isDataTable('#example') ) {
              $('#example').DataTable().destroy();
            }

            $('#example tbody').empty();
            var itemId=$('#itemId').val();
            $.ajax({
              type: 'GET',
              url: "searchModifiersByText?searchTxt="+searchTxt,
              success:function(data){
               var outer = [];
               $.each(data, function( index, value ) {
                 var inner = [];
                 inner.push(value.name);
                 inner.push(value.price);
                 inner.push(value.productUsed);
                 outer.push(inner);
               });
               //console.log(outer);
               $('#example').DataTable( {
                 data: outer,
                 columns: [
                     { title: "Name" },
                     { title: "Price" },
                     { title: "Products used" }
                 ]
                });
               $('label').each(function(){
                   if($(this).text()=='Search:'){
                       $(this).text('');
                     }
               })
              }
           });
    });
})
</script>
  </body>
</html>
