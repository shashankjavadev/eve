<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
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
<link
	href='https://fonts.googleapis.com/css?family=Open+Sans:400,300,300italic,400italic,600,600italic,700,700italic,800,800italic'
	rel='stylesheet' type='text/css'>
<!--CALLING GOOGLE FONT OPEN SANS-->

<!--CALLING FONT AWESOME-->
<link rel="stylesheet" href="resources/css/font-awesome.css">
<!--CALLING FONT AWESOME-->

<!--CALLING CHECK ALL FUNCTIONALITY-->
<script type="text/javascript"
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
<script src="resources/js/checkall/jquery.checkall.js"></script>
<!--CALLING CHECK ALL FUNCTIONALITY-->

<!--OPENS DIALOG BOX-->
<link rel="stylesheet" type="text/css"
	href="resources/css/dialog-box/component.css" />
<link rel='stylesheet' type='text/css'
	href='resources/css/products-tabs/opentabby.css' />
<!--OPENS DIALOG BOX-->

<!--ACCORDION FOR MENU-->
<script src="resources/js/accordion/paccordion.js"></script>
<!--ACCORDION FOR MENU-->
<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/1.10.11/css/jquery.dataTables.min.css">
<script
	src="https://cdn.datatables.net/1.10.11/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="resources/js/bootstrap.min.js"></script>
<script src="resources/js/bootstrap-multiselect.js"
	type="text/javascript"></script>
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

input[type="search"] {
	display: none;
	max-width: 300px;
	width: 100%;
	outline: 0;
	border: 1px solid rgb(169, 169, 169);
	padding: 11px 10px;
	border-radius: 6px;
	margin-bottom: 7px;
	placeholder: Search Categories;
}

.pj-preloader {
	display: none;
	position: absolute;
	height: 383px;;
	width: 940px;
	background: url("resources/img/spinner.gif") no-repeat scroll center
		center rgba(153, 153, 153, 0.3);
	z-index: 9999;
	left: 260;
	position: absolute;
	top: 224px;
}
</style>
<style>
.md-content {
	color: black;
}
</style>
<style>
* {
	font-family: 'Open Sans', sans-serif;
}

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

.btn.btn-default {
	
}

.btn.btn-default:hover {
	background: #eee;
	border-color: #bbb
}

.btn.btn-default:focus {
	background: #ddd;
	border-color: #bbb
}

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

.btn.btn-default[disabled] {
	background: #fafafa !important;
	border-color: #ccc !important;
	color: #aaa
}

.btn.btn-primary[disabled] {
	background: #3F9DD0 !important;
	border-color: #537FA9 !important;
	color: #ACD3E8;
	box-shadow: none !important
}

.btn.btn-left {
	float: left;
	margin: 0 5px 0 0 !important
}

.sd-popup-content img {
	display: block;
	margin: 0 auto 10px;
}

.sd-popup-content h3 {
	text-align: center;
}
</style>
<%-- <div class="exampleLive">
	<button style="display: none;" id="categoryOrderPopUp"
		class="btn btn-primary"
		data-confirmmodal-bind="#confirm_content_categoryOrder"
		data-topoffset="0" data-top="30%">Example</button>
</div>
<div id="confirm_content_categoryOrder" style="display: none">
	<div class="confirmModal_content sd-popup-content">
		<img src="resources/img/logo.png" class="sd-popup-logo">
		<div class="accordion-popup-sd-container">
			<div class="pj-loader-4" style="display: none">
				<img src="resources/img/load2.gif"
					style="width: 82px; margin-left: auto; margin-top: auto;">
			</div>
			<div class="categoryTimingPopup">
				<div class="accordion-popup-quantity">

					<label
						style="font-size: 0.77778rem; font-weight: normal; line-height: 1.5;">Select Days</label>
						
					<div id="weekDays">
						<div class="innerdays">
						</br>
				    <input type="checkbox" value="Sunday" id="Sunday" name="days">Sunday</input>
					<input type="checkbox" value="Monday" id="Monday" name="days">Monday</input>
					<input type="checkbox" value="Tuesday" id="Tuesday" name="days">Tuesday</input>
					<input type="checkbox" value="Wednesday" id="Wednesday" name="days">Wednesday</input> 
					<input type="checkbox" value="Thursday" id="Thursday" name="days">Thursday</input> <br>
					<input type="checkbox" value="Friday" id="Friday" name="days">Friday</input>
					<input type="checkbox" value="Saturday" id="Saturday" name="days">Saturday</input><br>
					</div></div>
					<br> 
					<div id="popUpDiv"><label
						style="font-size: 0.77778rem; font-weight: normal; line-height: 1.5;">Select
						Timing</label> <select name="startTime" style="width: 38%;" id="startTime">
						<c:forEach items="${times}" var="tm">

							<option value="${tm}">${tm}</option>

						</c:forEach>
					</select></div>
					<to>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;to&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</to>
					<select path="endTime" style="width: 38%;" id="endTime">
						<c:forEach items="${times}" var="tm">

							<option value="${tm}">${tm}</option>

						</c:forEach>
					</select>
					</quantity>
				</div>
				<!--.accordion-popup-quantity-->


			</div>
		</div>
		<div class="confirmModal_footer">
			<button type="button" id="yesBtn" class="btn btn-primary"
				onclick="yesButton()">Save</button>
			<button type="button" class="btn btn-primary"
				onclick="cancelButton()">Cancel</button>
		</div>
	</div>
</div> --%>
<script type="text/javascript">
	$(function() {

		/*  $('#days').multiselect({
		     includeSelectAllOption: true
		 }); */

	});
	var catId = "";
	var sortOrder = "";
	var allowCategoryTimings=0;
	function cancelButton() {
		$("#categoryOrderPopUp").hide();
		$("#content2").show();
	}

	function yesButton() {
		

		
		var days = $('input[name="days"]:checked').map( function () {
    return $(this).val();
           }).get().join();
		var updateCatTime=true;
		 if(jQuery("#itemTimingsYes").is(":checked")){
			 if(days==''){
				 $("#errorMessage").html("Please select atleast one day.");
				 updateCatTime=false
			 }
		 }
	
		 var startTime = jQuery("#startTime option:selected").val();
		var endTime = jQuery("#endTime option:selected").val(); 
         if(updateCatTime){
        	 $("#errorMessage").html("");
        	 $('.pj-loader-4').css('display', 'block');
     		$('.categoryTimingPopup').css('display', 'none');
		$.ajax({
			type : 'GET',
			url : "updateCategoryTiming?categoryId=" + catId + "&days=" + days
					+ "&startTime=" + startTime + "&endTime=" + endTime+ "&allowCategoryTimings=" + allowCategoryTimings,
			success : function(data) {
				$("#categoryTiming_"+catId).attr('allowCategoryTimings',allowCategoryTimings);
				 $('.pj-loader-4').css('display', 'none');
				$('.categoryTimingPopup').css('display', 'block');
				$(".confirmModal").hide(); 
				$("#categoryOrderPopUp1").hide();
				$("#categoryOrderPopUp").hide();
				$("#content2").show();
			}

		}); 
         }

	}
	function testF(dd,count) {
		var ss=0;
		for(var j=0;j<dd.length;j++){
			if(dd[j]==count){
				ss++;
			}
		}
		return ss;
	}
	jQuery.fn.dataTableExt.oApi.fnPagingInfo = function(oSettings) {
		return {
			"iStart" : oSettings._iDisplayStart,
			"iEnd" : oSettings.fnDisplayEnd(),
			"iLength" : oSettings._iDisplayLength,
			"iTotal" : oSettings.fnRecordsTotal(),
			"iFilteredTotal" : oSettings.fnRecordsDisplay(),
			"iPage" : oSettings._iDisplayLength === -1 ? 0 : Math
					.ceil(oSettings._iDisplayStart / oSettings._iDisplayLength),
			"iTotalPages" : oSettings._iDisplayLength === -1 ? 0 : Math
					.ceil(oSettings.fnRecordsDisplay()
							/ oSettings._iDisplayLength)
		};
	};

	$(document).ready(function() {
		$.fn.dataTableExt.sErrMode = 'throw';
	});

	function loadCategory() {

		$("#example").dataTable({
			"bProcessing" : true,
			"bServerSide" : true,
			"sort" : "position",
			//bStateSave variable you can use to save state on client cookies: set value "true" 
			"bStateSave" : false,
			//Default: Page display length
			"iDisplayLength" : 10,
			//We will use below variable to track page number on server side(For more information visit: http://legacy.datatables.net/usage/options#iDisplayStart)
			"iDisplayStart" : 0,
			"fnDrawCallback" : function() {
				//Get page numer on client. Please note: number start from 0 So
				//for the first page you will see 0 second page 1 third page 2...
				//Un-comment below alert to see page number
				//alert("Current page number: "+this.fnPagingInfo().iPage);    
			},
			"sAjaxSource" : "categoryDataTables",
			"aoColumns" : [ {
				"mData" : "name"
			}, {
				"mData" : "itemCount"
			}, {
				"mData" : "menuOrder"
			}, {
				"mData" : "action"
			} , {
				"mData" : "timing"
			}  ]
		});
	}
	$(document).ready(function setUp() {
		var currentUrl = window.location.href;
		//alert(currentUrl);
		var Cloverurl = currentUrl.replace("category", "isInstalled");
		$("#LoadingImage").show();
		// Cloverurl=Cloverurl+ '?' + queryStrg;
		var inventoryThreadStatus;
		$.ajax({
			url : Cloverurl,
			type : "GET",
			contentType : "application/json; charset=utf-8",
			success : function(minAmountData) {
				if (minAmountData == true) {
					// alert(inventoryThreadStatus);
					inventoryThreadStatus = true;
					$("#LoadingImage").hide();
					$("#content2").show();
					loadCategory();
				} else {
					$("#LoadingImage").show();
					$("#content2").hide();
					inventoryThreadStatus = false;
				}
			},
			error : function() {
				$("#LoadingImage").hide();
				alert("error");

			},
			complete : function() {
				// Schedule the next request when the current one's complete
				//alert(inventoryThreadStatus);
				if (inventoryThreadStatus == false) {
					setTimeout(setUp, 5000);

				}
			}
		});
	});
</script>

<script>
	$(document).ready(function() {
		
		
		$("#itemTimingsNo").click(function(){
	    	$("#errorMessage").html("");
	        $(".categoryTimingPopup").hide();
	        allowCategoryTimings=0;
	    });
	    $("#itemTimingsYes").click(function(){
	    	allowCategoryTimings=1;
	        $(".categoryTimingPopup").show();
	    });

		
		



      
     

		
		
		
		
		
		
		
		
		$("#example").on("click",".nav-toggle-timing",function() {
											catId = $(this).attr('catid');
											allowCategoryTimings = $(this).attr('allowCategoryTimings');
											
											$('#days').prop('checked', true);
											$.ajax({
														type : 'GET',
														url : "getCategoryTiming?categoryId="
																+ catId,
														success : function(data) {
															if(data.length>0){
															console.log(data);
															
															 $("#errorMessage").html("");
															 if(allowCategoryTimings==0){
														          $("#itemTimingsNo").attr('checked', 'checked');
														          $("#errorMessage").html("");
														          $(".categoryTimingPopup").hide();
														        }
														        if(allowCategoryTimings==1){
														          $("#itemTimingsYes").attr('checked', 'checked');
														          $(".categoryTimingPopup").show();
														        }
															$( ".innerdays" ).remove();
															$("#weekDays").append("<div class='innerdays'>");
															var weekDays=[];
															var startTime="12:00 AM";
															var endTime="12:00 AM";
															for (var i = 0; i < data.length; i++) {
																if(i==5){
																	$(".innerdays").append("<br>");
																}
																if(data[i].holiday==false){
																	startTime=data[i].startTime;
																	endTime=data[i].endTime;
																	$(".innerdays").append("<input type='checkbox' class='rrr' value='"+data[i].day+"' id='"+data[i].day+"' name='days' checked='checked'>"+data[i].day+"</input>");
																}else{
																	$(".innerdays").append("<input type='checkbox' class='nnn' value='"+data[i].day+"' id='"+data[i].day+"' name='days' >"+data[i].day+"</input>");
																}
																$("#weekDays").append("</div>");
															}
															$("#startTime option[value='"+startTime+"']").attr("selected", "selected");
															$("#endTime option[value='"+endTime+"']").attr("selected", "selected");
														}else{
															$("#startTime option[value='12:00 AM']").attr("selected", "selected");
															$("#endTime option[value='12:00 AM']").attr("selected", "selected");
															$(".innerdays input[type=checkbox]").each(function () {
												                $(this).prop("checked", false);
												            });
														}
															  
															   
															console.log(weekDays);
															$('#categoryOrderPopUp').show();
															$("#content2").hide();
															

														}
													});

										});
						$("#example")
								.on(
										"click",
										".nav-toggle",
										function() {
											var categoryId = $(this).attr(
													'catid');
											
											var thishtml = jQuery(this).html();
											var categoryStatus;
											var selectId = "menuOrdr_"
													+ categoryId;
											if (thishtml == 'Active') {
												categoryStatus = 1;
												jQuery(this).html('Inactive');

												$("#" + selectId).prop(
														'disabled', 'disabled');
												$("#" + selectId)
														.append(
																"<option value='0' selected>0</option>");
											}
											if (thishtml == 'Inactive') {
												categoryStatus = 0;
												jQuery(this).html('Active');
												$("#" + selectId).prop(
														'disabled', false);
												//$("#"+selectId+" option[value='0']").remove();
												// window.location='category';

											}
											$('.pj-preloader').css('display',
													'block');
											$
													.ajax({
														type : 'GET',
														url : "updateCategoryStatusById?categoryId="
																+ categoryId
																+ "&categoryStatus="
																+ categoryStatus,
														success : function(data) {
															var response = jQuery
																	.parseJSON(data);
															if (thishtml == 'Inactive') {
																if (response.result == 'success') {
																	$(
																			"#"
																					+ selectId)
																			.val(
																					response.menuOrder);
																	window.location = 'category';
																}

															}
															//alert(response.result);
															$('.pj-preloader')
																	.css(
																			'display',
																			'none');

														}
													});

										});

						$("#example")
								.on(
										"change",
										".category_order",
										function() {
											//var prev = $(this).data('val');
											// var current = $(this).val();
											var menuOrder = $(this).val();
											var categoryId = $(this).attr(
													'catid');
											catId = categoryId;
											sortOrder = menuOrder;
											$('.pj-preloader').css('display',
													'block');
											$
													.ajax({
														type : 'GET',
														url : "updateCategorySortOrderById?categoryId="
																+ categoryId
																+ "&sortOrder="
																+ menuOrder
																+ "&action=update",
														success : function(data) {

															$('.pj-preloader')
																	.css(
																			'display',
																			'none');
															if (data == 'succuss') {
																// alert(data);
																//$('.pj-preloader').css('display','none');
																window.location='category';
																$("#errorBox")
																		.html(
																				"");
															} else {
																// alert(data);
																$(
																		'#menuOrdr_'
																				+ categoryId
																				+ ' option')
																		.prop(
																				'selected',
																				function() {
																					return this.defaultSelected;
																				});

																//$("#menuOrdr").css("border-color", "red")
																window.location='category';

																// $("#errorBox").html("This order is already selected .Choose another one");
															}

														}
													});

										});

						/*  $("#menuOrdr").change(function(){
						 	
						     
						     }); */
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
											</div>
											<!--.content-header-->

											<div class="merchant-page-data">
												<div class="merchant-actions-outbound">
													<div class="merchat-coupons-container">
														<!-- <main> -->
														<div class="coupons-navigation">
															<ul>
																<li><a href="inventory">Items</a></li>
																<li class="current-menu-item"><a href="category">Categories</a></li>
																<li><a href="modifierGroups">Modifier Groups</a></li>
																<li><a href="modifiers">Modifiers</a></li>
															</ul>
														</div>
														<!--.within-page-horizontal-menu-->
														<div id="LoadingImage" style="display: none"
															align="middle">
															<img src="resources/img/spinner.gif" align="middle" />
														</div>
														
<%-- <div >
																							<div class="sd-modifiers-limit-label">
																								<label>Display Category On Select </label>
																							</div>
																							<!--.sd-modifiers-limit-label-->

																							<div class="sd-modifiers-limit-fields">

																								<div class="sd-modifiers-limit-yes-no">
																								
																								<input type="radio" id="itemTimingsYes" name="itemTimings" value="Yes" > Yes
																								<input type="radio" id="itemTimingsNo" name="itemTimings" value="No" checked> No
																									Yes
																									<form:radiobutton 
																										id="itemTimingsYes" value="1" />
																									No
																									<form:radiobutton 
																										id="itemTimingsNo" value="0" />
																								</div></div>
																								
																								
																								 </div>	 --%>											
														
														
														
														
														
	<div id="categoryOrderPopUp" style="display: none;margin-top: 8%;margin-left: 1%;">
	
	<div class="confirmModal_content sd-popup-content">
		<!-- <img src="resources/img/logo.png" class="sd-popup-logo"> -->
		<div class="accordion-popup-sd-container">
			<div class="pj-loader-4" style="display: none">
				<img src="resources/img/load2.gif"
					style="width: 82px; margin-left: auto; margin-top: auto;">
			</div>
			<div id="allowCategoryTimeDiv" style="margin-top: 8%;margin-left: 1%;">
			<label id="errorMessage" style="color: red;"></label>
			<div class="sd-modifiers-limit-label">
																								<label>Display Items On Select Days : </label>
																							</div>
																							<!--.sd-modifiers-limit-label-->

																							<div class="sd-modifiers-limit-fields">

																								<div class="sd-modifiers-limit-yes-no">
																									Yes
																									<input type="radio" name="allowCategoryTimings"
																										id="itemTimingsYes" value="1" />
																									No
																									<input type="radio" name="allowCategoryTimings"
																										id="itemTimingsNo" value="0" />
																								</div></div></div><br>
																								<div class="clearfix"></div>
			<div class="categoryTimingPopup" style=" display: none;margin-top: 1%;margin-left: 1%;">
				<div class="accordion-popup-quantity">

					<label
						style="font-size: 0.77778rem; font-weight: normal; line-height: 1.5;">Select Days</label>
						
					<div id="weekDays">
						<div class="innerdays">
						</br>
				    <input type="checkbox" value="Sunday" id="Sunday" name="days">Sunday</input>
					<input type="checkbox" value="Monday" id="Monday" name="days">Monday</input>
					<input type="checkbox" value="Tuesday" id="Tuesday" name="days">Tuesday</input>
					<input type="checkbox" value="Wednesday" id="Wednesday" name="days">Wednesday</input> 
					<input type="checkbox" value="Thursday" id="Thursday" name="days">Thursday</input> <br>
					<input type="checkbox" value="Friday" id="Friday" name="days">Friday</input>
					<input type="checkbox" value="Saturday" id="Saturday" name="days">Saturday</input><br>
					</div></div>
					<br> 
					<label
						style="font-size: 0.77778rem; font-weight: normal; line-height: 1.5;">Select
						Timing</label> <select name="startTime" style="width: 38%;" id="startTime">
						<c:forEach items="${times}" var="tm">

							<option value="${tm}">${tm}</option>

						</c:forEach>
					</select>
					<to>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;to&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</to>
					<select path="endTime" style="width: 38%;" id="endTime">
						<c:forEach items="${times}" var="tm">

							<option value="${tm}">${tm}</option>

						</c:forEach>
					</select>
					</quantity>
				</div>
				<!--.accordion-popup-quantity-->


			</div>
		</div>
		 <div class="confirmModal_footer" style="margin-top: 2%;margin-left: 1%;" >
			<button type="button" id="yesBtn" class="btn btn-primary"
				onclick="yesButton()">Save</button>
			<button type="button" class="btn btn-primary"
				onclick="cancelButton()">Cancel</button>
		</div> 
	</div>
</div>

														<section id="content2" style="display: block">
															<div class="tab-content-container-outbound">
																<div class="tab-content-container">
																	<div class="tab-content-container-inbound">
																		<div class="only-search-part">
																			<div class="only-search-elements">
																			
																			

																				<div class="search-container"
																					style="margin-top: 59.5px">
																					<div class="only-search-elements">
																						<label>Search</label> <input type="text"
																							placeholder="Search Categories"
																							id="search-inventory" class="searchq"> <input
																							type="button" value="Search">
																					</div>
																				</div>

																				<div class="inventory-items-list-table">
																					<span id="errorBox" style="color: red;"></span>
																					<div class="pj-preloader"></div>
																					<table width="100%" cellpadding="0" cellspacing="0"
																						id="example">
																						<thead>
																							<tr>
																								<th>Name</th>
																								<th>Items</th>
																								<th>Menu Order</th>
																								<th>Actions</th>
																								<th>Select Timing</th>
																							</tr>
																						</thead>
																					</table>
																				</div>
																				<!--.inventory-items-list-table-->
																			</div>
																			<!--.only-search-part-->
																		</div>
																		<!--.tab-content-container-inbound-->
																	</div>
																	<!--.tab-content-container-->
																</div>
																<!--.tab-content-container-outbound-->
														</section>



														<!--   </main>
                                                        </div> -->
														<!--.inventory-tabs-inbound-->
													</div>
													<!--.inventory-tabs-->
												</div>
												<!--.inventory-tabs-outbound-->
											</div>
											<!--.inventory-page-data-->

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
	<!--OPENS DIALOG BOX-->
	<script>
		$(function() {
			$('label').each(function() {
				if ($(this).text() == 'Search:') {
					$(this).text('');
				}
			})

			$("#search-inventory").keyup(function() {
				$.fn.dataTableExt.sErrMode = 'throw';
				var searchTxt = $(this).val();
				if ($.fn.DataTable.isDataTable('#example')) {
					$('#example').DataTable().destroy();
				}
				$('#example tbody').empty();
				var itemId = $('#itemId').val();
				$.ajax({
					type : 'GET',
					url : "searchCategoryByText?searchTxt=" + searchTxt,
					success : function(data) {
						var outer = [];
						$.each(data, function(index, value) {
							var inner = [];
							inner.push(value.name);
							inner.push(value.itemCount);
							inner.push(value.menuOrder);
							inner.push(value.action);
							outer.push(inner);
						});
						//console.log(outer);
						$('#example').DataTable({
							data : outer,
							columns : [ {
								title : "Name"
							}, {
								title : "Items"
							}, {
								title : "Menu Order"
							}, {
								title : "Actions"
							} ]
						});
						$('label').each(function() {
							if ($(this).text() == 'Search:') {
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
