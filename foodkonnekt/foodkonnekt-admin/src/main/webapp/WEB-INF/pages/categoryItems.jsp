<%@page session="true"%>
<!doctype html>
<html class="no-js" lang="en">
    <!--CALLING STYLESHEET STYE.CSS-->
    <link rel="stylesheet" href="resources/css/style.css"><%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
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
      
      div#example1_filter {
         display: none;
      }
      
      example1_filter
    
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
      .pj-preloader {
    display: none;
    position: absolute;
    height: 383px;;
    width: 940px;
    background: url("resources/img/spinner.gif") no-repeat scroll center center rgba(153, 153, 153, 0.3);
    z-index: 9999;
    left: 260;
    position: absolute;
    top: 224px;
}
    </style>
    <div class="exampleLive">
    <button style="display:none;" id="categoryOrderPopUp" class="btn btn-primary" data-confirmmodal-bind="#confirm_content_categoryOrder" data-topoffset="0" data-top="30%" >Example</button>
</div>
<div id="confirm_content_categoryOrder" style="display:none">
    <div class="confirmModal_content sd-popup-content">
    <img src="resources/img/logo.png" class="sd-popup-logo">
    <h3>This order has been selected .Do you want to replace this ?</h3>
    </div>
    <div class="confirmModal_footer">
       <button type="button" id="yesBtn" class="btn btn-primary" onclick="yesButton()" >Yes</button> <button type="button" class="btn btn-primary" onclick="cancelButton()">Cancel</button>
    </div>
</div>
  <script type="text/javascript">
  var catId=""; 
  var sortOrder="";
  var itemcategoryId="${param.categoryId}";
	
  function cancelButton(){
	  $(".confirmModal").hide();
	}
  
  function yesButton(){
	   	   $(".confirmModal").hide();
	   	   
	   	 $('.pj-preloader').css('display','block');
         $.ajax({
              type: 'GET',
              url: "updateItemSortOrderById?categoryItemId="+catId+"&sortOrder="+sortOrder+"&action=update",
              success:function(data){
             	 
             	 $('.pj-preloader').css('display','none');
             	 if(data=='succuss'){
             		// alert(data);
             		 //$('.pj-preloader').css('display','none');
             		 $("#errorBox").html("");
             		window.location='findItemsByCategoryId?categoryId='+itemcategoryId;
             	 }else{
             		// alert(data);
             		/* $('#menuOrdr option').prop('selected', function() {
                       return this.defaultSelected;
                  }); */
             		
             		
             		
             		//$("#menuOrdr").css("border-color", "red")
             		jQuery('#categoryOrderPopUp').click();
             		
             		
             		
             		 //$("#errorBox").html("This order is already selected .Choose another one");
             	 }
             	
              }
           });
	   	   
   }</script>
    <div class="exampleLive">
    <button style="display:none;" id="uploadInventoryPopUp" class="btn btn-primary" data-confirmmodal-bind="#confirm_content_uploadinventory" data-topoffset="0" data-top="30%" >Example</button>
</div>
<div id="confirm_content_uploadinventory" style="display:none">
    <div class="confirmModal_content sd-popup-content">
    <img src="resources/img/logo.png" class="sd-popup-logo">
       
    </div>
    
    <form:form method="POST" action="uploadInventoryByExcel"
																			enctype="multipart/form-data"
																			autocomplete="off">
																			
																			<div class="uploadinventory-div" style=" margin-left: 90px;margin-top: -20px;">
																				
																				
																					<label>File to upload:</label> <input type="file" name="file" accept="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet, application/vnd.ms-excel"> <br>
																					<div class="clearfix"></div>
																					
																			</div>
																			
    
    <div class="confirmModal_footer">
       <button type="Submit" id="updateItemButton" class="btn btn-primary"  >Upload</button> <button type="button" class="btn btn-primary" onclick="cancelButton()">Cancel</button>
    </div>
    </form:form>
    
</div>

   <script type="text/javascript">

    //Plug-in to fetch page data 
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

$(document).ready(function() {
    $.fn.dataTableExt.sErrMode = 'throw';
});

function loadInveotry(){
	
	  if ( $.fn.DataTable.isDataTable('#example') ) {
          $('#example').DataTable().destroy();
        }

        $('#example tbody').empty();
	
    $("#example").dataTable( {
        "bProcessing": true,
        "bServerSide": true,
        "sort": "position",
        //bStateSave variable you can use to save state on client cookies: set value "true" 
        "bStateSave": false,
        //Default: Page display length
        "iDisplayLength": 10,
        //We will use below variable to track page number on server side(For more information visit: http://legacy.datatables.net/usage/options#iDisplayStart)
        "iDisplayStart": 0,
        "fnDrawCallback": function () {
            //Get page numer on client. Please note: number start from 0 So
            //for the first page you will see 0 second page 1 third page 2...
            //Un-comment below alert to see page number
            //alert("Current page number: "+this.fnPagingInfo().iPage);    
        },         
        "sAjaxSource": "inventoryDataUsingAjax",
        "aoColumns": [
            { "mData": "name" },
            { "mData": "price" },
            { "mData": "categoriesName" },
            { "mData": "modifierGroups" },
            { "mData": "status" },
            { "mData": "action" },
        ]
    } );
}

$(document).ready( function setUp() {
	
	   $("#example").on("change", ".category_order",function () {
          	//var prev = $(this).data('val');
             // var current = $(this).val();
              var menuOrder=$(this).val();
              var categoryId=$(this).attr('catid');
              catId= categoryId;
              
              sortOrder=menuOrder;
              $('.pj-preloader').css('display','block');
              $.ajax({
                   type: 'GET',
                   url: "updateItemSortOrderById?categoryItemId="+categoryId+"&sortOrder="+menuOrder+"&action=check",
                   success:function(data){
                  	 
                  	 $('.pj-preloader').css('display','none');
                  	 if(data=='succuss'){
                  		// alert(data);
                  		 //$('.pj-preloader').css('display','none');
                  		 $("#errorBox").html("");
                  		/* window.location='findItemsByCategoryId?categoryId='+itemcategoryId; */
                  		categoryItems()
                  	 }else{
                  		// alert(data);
                  		$('#menuOrdr_'+categoryId+' option').prop('selected', function() {
                            return this.defaultSelected;
                       });
                  		
                  		
                  		
                  		//$("#menuOrdr").css("border-color", "red")
                  		jQuery('#categoryOrderPopUp').click();
                  		
                  		
                  		
                  		// $("#errorBox").html("This order is already selected .Choose another one");
                  	 }
                  	
                   }
                }); 
              
              
              });
	
	
    var currentUrl = window.location.href;
    var Cloverurl=currentUrl.replace("inventory", "isInstalled");
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
               if (Cloverurl.indexOf("categoryId") >= 0){
                   minAmountData=true;
               }
               if(minAmountData==true){
                   inventoryThreadStatus=true;
                   $("#LoadingImage").hide();
                   $("#content1").show();
                   if (Cloverurl.indexOf("categoryId") ==-1){
                       loadInveotry();
                   }
                   if (Cloverurl.indexOf("categoryId") >= 0){
                       categoryItems();
                   }
               }else{
                   $("#LoadingImage").show();
                   $("#content1").hide();
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
                 //window.location = "inventory";
            }
          }
    });

});
</script>

 <script>
 function cancelButton(){
	  $(".confirmModal").hide();
	}
        $(document).ready(function() {
        	
        	$("#uploadInventory").on("click",  function(){
        		
        		jQuery('#uploadInventoryPopUp').click();
        	});
        	
        	
        //changes made by manish gupta for active/inactive functionality on 05/05/17	
        $("#example").on("click", ".nav-toggle", function(){
          var itemId=$(this).attr('itmId'); 
          var thishtml = jQuery(this).html();
        
          var categoryItemId = $(this).attr('categoryItemId'); 
          var selectId="menuOrdr_"+categoryItemId;
          var categoryId = $(this).attr('categoryid');
          var itemStatus;
          
         
         
          if(thishtml =='Active'){
        	  itemStatus=1;
            jQuery(this).html('InActive');
            $("#"+selectId).prop('disabled', 'disabled');
            $("#"+selectId).append("<option value='0' selected>0</option>");
            
          }
          if(thishtml =='InActive'){
        	  itemStatus=0;
            jQuery(this).html('Active');
            $("#"+selectId).prop('disabled', false);
          }
        
          $('.pj-preloader').css('display','block');
          $.ajax({
               type: 'GET',
               url: "updateItemStatusById?itemId="+itemId+"&itemStatus="+itemStatus+"&categoryItemId="+categoryItemId,
               success:function(data){
                   var response=jQuery.parseJSON( data );
       				   if(thishtml =='InActive'){
    				   if(response.result=='success' && response.categoryItemResult=='success'){
    				   $("#"+selectId).val(response.menuOrder);
    				   //window.location='findItemsByCategoryId?categoryId='+categoryId;
    				   categoryItems();
    				   }
    		   
    			   }
                    $('.pj-preloader').css('display','none');
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
                                                                          
                                                                        <li class="current-menu-item"><a href="inventory">Items</a></li>
                                                                        <li><a href="category">Categories</a></li>
                                                                        <li><a href="modifierGroups">Modifier Groups</a></li>
                                                                        <li><a href="modifiers">Modifiers</a></li>
                                                                    </ul>
                                                        </div>
                                                        
                                                        
                                                        <div id="LoadingImage" style="display: none" align="middle">
                                                          <img src="resources/img/spinner.gif" align="middle" />
                                                             </div>
                                                              <section id="content1">
                                                                <div class="tab-content-container-outbound">
                                                                    <div class="tab-content-container">
                                                                        <div class="tab-content-container-inbound">
                                                                            <div class="only-search-part">
                                                                              
                                                                                <div class="search-container">
                                                                                     <div class="only-search-elements">
                                                                                    <label>Search</label>
                                                                                    <input type="text" placeholder="Search Items" id="search-inventory" class="searchq">
                                                                                     <input type="button" value="Search"><br>
                                                                                    
                                                                                    </div>
                                                                                   
                                                                                    
                                                                                              <c:if test="${merchantType==3}"> 
                                                                                    <div class="upload-inventory">
                                                                                    <label id="errorMessage" style="color: red; float:right" >${param.excelResponse}</label><br>
                                                                                     <input type="button" id="uploadInventory" value="Upload Inventory"><br>
                                                                                     <input type="button"  value="Download Template" onclick="Download()" style="margin-right: 175px;">
                                                                                    </div>
 
                                                                                      </c:if>  
                                                                                    
                                                                                    <div class="only-filter-container">
                                                                                        <label>Filter By</label>
                                                                                        <select id="categoryId">
                                                                                            <!-- <option value="0">All Categories</option> -->
                                                                                            <c:forEach items="${categories}" var="view" varStatus="status">
                                                                                                                                                                                     
                                                                                            <c:choose>
                                                                                              <c:when test="${category.name==view.name}">  
                                                                                              <option value="${view.id}" selected="selected">${view.name}</option>
                                                                                              </c:when>
                                                                                              <c:otherwise>
                                                                                              <option value="${view.id}">${view.name}</option>
                                                                                              </c:otherwise>
                                                                                              </c:choose>
                                                                                        </c:forEach>
                                                                                        </select>
                                                                                    </div><!--.only-filter-container-->
                                                                                </div><!--.search-container-->
                                                                                <div class="inventory-items-list-table">
                                                                                 <div class="pj-preloader"></div>
                                                                                    <table width="100%" cellpadding="0" cellspacing="0" id="example" >
                                                                                      <thead>
                                                                                        <tr>
                                                                                          <th>Name</th>
                                                                                          <th>Price</th>
                                                                                          <th>Categories</th>
                                                                                          <th>Modifier Groups</th>
                                                                                          <th>Menu Order</th>
                                                                                          <th>Status</th>
                                                                                          <th>Actions</th>
                                                                                        </tr>
                                                                                      </thead>
                                                                                    </table>
                                                                                     <!-- <table width="100%" cellpadding="0" cellspacing="0" id="example1">
                                                                                     </table> -->
                                                                                </div><!--.inventory-items-list-table-->
                                                                            </div><!--.only-search-part-->
                                                                        </div><!--.tab-content-container-inbound-->
                                                                    </div><!--.tab-content-container-->
                                                                </div><!--.tab-content-container-outbound-->
                                                              </section>
                                                                
                                                                
                                                            </main>
                                                        </div><!--.inventory-tabs-inbound-->
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
    <script type="text/javascript">
   /*  $(document).ready(function() {
        //categoryItems();
  }); */
  function Download() {
	    window.location = "http://javadev.foodkonnekt.com:8080/foodkonnekt_merchat_logos/inv.xls";
	};
    function categoryItems(){
        if ( $.fn.DataTable.isDataTable('#example') ) {
            $('#example').DataTable().destroy();
          }
        
        

          $('#example tbody').empty();
          
          //var list = ${lineItemsJson};
          var category=$('#categoryId').val();
          
          
          /* $("#example").hide();
          $("#example_info").css("display", "none");
          $("#example_length").css("display", "none");
          $("#example_filter").css("display", "none");
          $("#example1_filter").css("display", "none");
          
          $("#example_paginate").css("display", "none"); */
          var outer = [];
          $.ajax({
              url : "filterByCategory?categoryId=" + category,
              type : "GET",
              contentType : "application/json; charset=utf-8",
              success : function(data) {
                var jsonOutpt = JSON.stringify(data);
                var menuItems = JSON.parse(jsonOutpt);
                $.each(menuItems, function( index, value ) {
                      var inner = [];
                      
                      inner.push(value.name);
                      inner.push(value.price);
                      inner.push(value.categoriesName);
                      inner.push(value.modifierGroups);
                      inner.push(value.menuOrder);
                      inner.push(value.status);
                      inner.push(value.action);
                      outer.push(inner);
                   });
                $('#example').DataTable( {
                    data: outer,
                    order: [[ 5, 'asc' ]],
                    columns: [
                        { title: "Name" },
                        { title: "Price" },
                        { title: "Categories" },
                        { title: "Modifier Groups" },
                        { title: "Menu Order" },
                        { title: "Status" },
                        { title: "Actions" }
                    ]
                } );
               var table =$('#example').DataTable();
               $(".searchq").keyup(function() {
                     table.search( this.value).draw();
               } );
              },
              error : function() {
                  console.log("Error in category wise inventory");
               }
            })
    }
   
 </script>
 <script type="text/javascript">
     $('#categoryId').change(function () {
    	 var catId= $('#categoryId').val();
    	if(catId>0){
    		categoryItems();
    		//window.location="findItemsByCategoryId?categoryId="+catId;
    	}else{
    		loadInveotry();
    	}
    	 
         
         $("#search-inventory").val("");
     });
 </script>
 <script src="resources/js/dialog-box/classie.js"></script>
<script src="resources/js/dialog-box/modalEffects.js"></script>
<script>
$(function(){
    $('label').each(function(){
        if($(this).text()=='Search:'){
            $(this).text('');
          }
    })
    
    
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
             url: "searchItemByText?searchTxt="+searchTxt,
             success:function(data){
              var outer = [];
              $.each(data, function( index, value ) {
                var inner = [];
                inner.push(value.name);
                inner.push(value.price);
                inner.push(value.categoriesName);
                inner.push(value.modifierGroups);
                inner.push(value.status);
                inner.push(value.action);
                outer.push(inner);
              });
              //console.log(outer);
              $('#example').DataTable( {
                data: outer,
                columns: [
                    { title: "Name" },
                    { title: "Price" },
                    { title: "Categories" },
                    { title: "Modifier Groups" },
                    { title: "Status" },
                    { title: "Actions" }
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
<script>
  var polyfilter_scriptpath = '/js/';
</script>
  </body>
</html>
  