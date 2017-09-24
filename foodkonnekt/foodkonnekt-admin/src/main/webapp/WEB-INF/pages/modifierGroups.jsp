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
    
      input[type="search"]{
        display:none;
      
        max-width: 300px;
        width: 100%;
        outline: 0;
        border: 1px solid rgb(169, 169, 169);
        padding: 11px 10px;
        border-radius: 6px;
        margin-bottom: 7px;
        placeholder:Search Items;
    }
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
        
        var Cloverurl=currentUrl.replace("modifierGroups", "isInstalled");
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
                  // alert(inventoryThreadStatus);
                   inventoryThreadStatus=true;
                   
                   $("#LoadingImage").hide();
                   $("#content3").show();
                loadModifierGroups();
                  }else{
                   $("#LoadingImage").show();
                   $("#content3").hide();
                   inventoryThreadStatus=false;
                  }

           },
           error : function() {
               $("#LoadingImage").hide();
               alert("error");

           },
           complete: function() {
               // Schedule the next request when the current one's complete
               //alert(inventoryThreadStatus);
               if(inventoryThreadStatus==false){
                
               setTimeout(setUp, 5000);
               }
             }
       });

   });  
   

/* $(document).ready(function() {
    
    /* var inventoryThreadStatus="${inventoryThread}";
    if(inventoryThreadStatus==1){
        $("#content3").show();
        $("#LoadingImage").hide();
    }else{
        $("#LoadingImage").show();
        $("#content3").hide();
    } 
    
    
    
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
       "sAjaxSource": "modifiersGroupsDataTables",
       "aoColumns": [
           { "mData": "modifierGroupName" },
           { "mData": "modifiers" },
       ]
   });
}); */


function loadModifierGroups(){
    
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
           "sAjaxSource": "modifiersGroupsDataTables",
           "aoColumns": [
               { "mData": "modifierGroupName" },
               { "mData": "modifiers" },
           ]
       });
    
}


     /* $(document).ready(function() {
        var table = $('#example').DataTable();
        $(".searchq").keyup(function() {
             table.search( this.value).draw();
        } );
     }); */
     
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
                                                                        <li class="current-menu-item"><a href="modifierGroups">Modifier Groups</a></li>
                                                                        <li><a href="modifiers">Modifiers</a></li>
                                                                    </ul>
                                                        </div><!--.within-page-horizontal-menu-->
                                                             <div id="LoadingImage" style="display: none" align="middle">
                                                          <img src="resources/img/spinner.gif" align="middle" />
                                                             </div> 
                                                              <section id="content3">
                                                                <div class="tab-content-container-outbound">
                                                                    <div class="tab-content-container">
                                                                        <div class="tab-content-container-inbound">
                                                                            <div class="only-search-part">
                                                                                <div class="search-container">
                                                                                    <div class="only-search-elements">
                                                                                    <label>Search</label>
                                                                                     <input type="text" placeholder="Search Modifier Groups" id="search-inventory" class="searchq">
                                                                                     <input type="button" value="Search">
                                                                                    </div>
                                                                                    <!--   
                                                                                    <input type="text" placeholder="Search Items" id="search-inventory" class="searchq">
                                                                                    <input type="button" value="Search"> -->
                                                                                    </div>
                                                                                  <!--  <script type="text/javascript"> 
                                                                                        $(".searchq").keyup(function(){
                                                                                                var val = $(this).val();
                                                                                                $('input[type="search"]').val(val);
                                                                                                $('input[type="search"]').trigger( "keyup" );
                                                                                        });
                                                                                    </script> -->
                                                                              
                                                                                    </div>
                                                                                </div><!--.search-container-->
                                                                                <div class="inventory-items-list-table">
                                                                               <!--  <ul>
                                                                                    <li>50 results per page</li>
                                                                                    <li><a href="#"><i class="fa fa-arrow-circle-left" aria-hidden="true"></i> Back</a> | <a href="#"> Next <i class="fa fa-arrow-circle-right" aria-hidden="true"></i></a></li>
                                                                                </ul> -->
                                                                                    <table width="100%" cellpadding="0" cellspacing="0" id="example">
                                                                                      <thead>
                                                                                        <tr>
                                                                                          <th>Group Name</th>
                                                                                          <th>Modifiers</th>
                                                                                        </tr>
                                                                                      </thead>
                                                                                     <%--  <tbody>
                                                                                        <c:forEach items="${modifierGroups}" var="view" varStatus="status"> 
                                                                                        <tr>
                                                                                          <td>${view.name}</td>
                                                                                          <td>${view.modifierNames}</td>
                                                                                        </tr>
                                                                                        </c:forEach>
                                                                                      </tbody> --%>
                                                                                    </table>
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
                            
                        </div><!--.outer-container-->
                    </div><!--#page-content-->
                    
                      <%@ include file="adminFooter.jsp" %>
                    <!--#footer-container-->
                    
                </div><!--.max-row-->
            </div><!--.inner-container-->
        </div><!--.foodkonnekt .dashboard-->
    </div><!--#page-container-->
    
    <script>
        var polyfilter_scriptpath = '/js/';
    </script>
    <!--OPENS DIALOG BOX-->
    <script>
 $(function(){
    $('label').each(function(){
        if($(this).text()=='Search:'){
            $(this).text('');
            }
    })
    
    
      $("#search-inventory").keyup(function() {
             var searchTxt=$(this).val();
              if ( $.fn.DataTable.isDataTable('#example') ) {
                  $('#example').DataTable().destroy();
                }

                $('#example tbody').empty();
                var itemId=$('#itemId').val();
                $.ajax({
                  type: 'GET',
                  url: "searchModifierGroupByText?searchTxt="+searchTxt,
                  success:function(data){
                   var outer = [];
                   $.each(data, function( index, value ) {
                     var inner = [];
                     inner.push(value.modifierGroupName);
                     inner.push(value.modifiers);
                     outer.push(inner);
                   });
                   //console.log(outer);
                   $('#example').DataTable( {
                     data: outer,
                     columns: [
                         { title: "Group Name" },
                         { title: "Modifiers" },
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
