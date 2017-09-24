<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@page session="true"%>
<!doctype html>
<html class="no-js" lang="en">
<head>
<title>FoodKonnekt | Add Products</title>
<!--CALLING STYLESHEET STYE.CSS-->
<link rel="stylesheet" href="resources/css/style.css">
<!--CALLING STYLESHEET STYLE.CSS-->

<!--CALLING GOOGLE FONT OPEN SANS-->
<link
	href='https://fonts.googleapis.com/css?family=Open+Sans:400,300,300italic,400italic,600,600italic,700,700italic,800,800italic'
	rel='stylesheet' type='text/css'>
<!--CALLING GOOGLE FONT OPEN SANS-->

<!--CALLING FONT AWESOME-->
<link rel="stylesheet"
	href="https://fontawesome.io/assets/font-awesome/css/font-awesome.css">
<!--CALLING FONT AWESOME-->

<!--OPENS DIALOG BOX-->
<link rel="stylesheet" type="text/css"
	href="resources/css/dialog-box/component.css" />
<!--OPENS DIALOG BOX-->

<!--CALLING PRODUCTS TABS-->
<link rel='stylesheet' type='text/css'
	href='resources/css/products-tabs/opentabby.css' />
<!--CALLING PRODUCTS TABS-->
<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/1.10.11/css/jquery.dataTables.min.css">
<style type="text/css">

.sd-modifiers-each-field-label label label {
    height: auto;
    display: inline-block;
    max-width: 20px;
    margin-left: 10px;
}
.sd-modifiers-each-field-label label span {
    float: left;
}

div#example_paginate {
	display: block;
}

div#example_filter {
	display: block;
}

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
	placeholder: Search Items;
}

input[type="text"], input[type="email"], input[type="password"], input[type="number"],
	input[type="date"] {
	height: 39px;
}
</style>
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

											<div class="inventory-page-data">
												<div class="inventory-tabs-outbound">
													<div class="inventory-tabs">
														<div class="inventory-tabs-inbound">
															<div class="main-products-add-products">
																<div class='openTabby' id='tabs1'>
																	<div class='openTabby--slidesContainer'>
																		<div class="coupons-navigation">
																			<ul>
																				<li class="current-menu-item"><a
																					href="inventory">Items</a></li>
																				<li><a href="category">Categories</a></li>
																				<li><a href="modifierGroups">Modifier
																						Groups</a></li>
																				<li><a href="modifiers">Modifiers</a></li>
																			</ul>
																		</div>
																		<!--.within-page-horizontal-menu-->
																		<form:form method="POST" action="uploadInventoryByExcel"
																			enctype="multipart/form-data"
																			autocomplete="off">
																			
																			<div class="adding-products">
																				<label id="errorMessage" style="color: red;">${param.excelResponse}</label><br>
																				<div class="adding-products-form">
																					<label>File to upload:</label> <input type="file" name="file" accept="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet, application/vnd.ms-excel"> <br>
																					<div class="clearfix"></div>
																					
																				<!--  <a href="#" id="updateItemButton">Save</a> -->
																				<input type="Submit" id="updateItemButton"
																					value="Save">&nbsp;&nbsp; <input
																					type="button" value="Cancel"
																					id="updateItemCancelButton">

																			</div>
																			<div id="example_filter" class="dataTables_filter"></div>

																			</div>
																			
																			<!--.sd-modifiers-limit-new-->
																			
																			<!--.button-->
																			</form:form>
																	</div>
																	<!--.adding-products-form-->
																</div>
																<!--.adding-products-->
																
															</div>
															<!--openTabby--slidesContainer-->
														</div>
														<!--openTabby-->
													</div>
													<!--.main-products-add-products-->
												</div>
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
					 <!-- page-content -->
		        </div>
	   <!--.max row-->
	         </div>
	    
			<!--inner-container-->

			<%@ include file="adminFooter.jsp"%>
			<!--#footer-container-->

		</div>
		<!--.foodkonnekt inventory-->
	</div>
	<!--.page-container-->
	

	<!--CALLING TABS-->
	<script src="https://code.jquery.com/jquery-2.1.4.min.js"></script>
	<script src='resources/js/products-tabs/opentabby.js'></script>
	<script>
        $(".openTabby").openTabby();
    </script>

	
	
	
  
  <!--OPENS DIALOG BOX-->
    <script src="resources/js/dialog-box/classie.js"></script>
    <script src="resources/js/dialog-box/modalEffects.js"></script>
    <script>
        var polyfilter_scriptpath = '/js/';
    </script>
    <!--OPENS DIALOG BOX-->
	<!--CALLING TABS-->
	<!--CALLING TABS-->
</body>
</html>
