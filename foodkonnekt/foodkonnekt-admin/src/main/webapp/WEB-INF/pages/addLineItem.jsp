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
 <link href="resources/css/bootstrap.min.css"
        rel="stylesheet" type="text/css" />
        <link href="resources/css/bootstrap-multiselect.css" rel="stylesheet" type="text/css" />
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
	href="resources/css/font-awesome.css">
<!--CALLING FONT AWESOME-->

<!--OPENS DIALOG BOX-->
<link rel="stylesheet" type="text/css"
	href="resources/css/dialog-box/component.css" />
<!--OPENS DIALOG BOX-->

<!--CALLING PRODUCTS TABS-->
<link rel='stylesheet' type='text/css'
	href='resources/css/products-tabs/opentabby.css' />
<!--CALLING PRODUCTS TABS-->
<!-- <link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/1.10.11/css/jquery.dataTables.min.css"> -->
	
<style type="text/css">
.body{
overflow: scroll !important;
}
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
  var itemModifierGroupId=""; 
  var sortOrder="";
  function cancelButton(){
	  $(".confirmModal").hide();
	}
  
  function yesButton(){
	   	   $(".confirmModal").hide();
	   	   
	   	 $('.pj-preloader').css('display','block');
         $.ajax({
              type: 'GET',
              url: "updateItemModifierGroupSortOrderById?itemModifierGroupId="+itemModifierGroupId+"&sortOrder="+sortOrder+"&action=update",
              success:function(data){
             	 
             	 $('.pj-preloader').css('display','none');
             	 if(data=='succuss'){
             		// alert(data);
             		 //$('.pj-preloader').css('display','none');
             		 $("#errorBox").html("");
             		window.location='category';
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
	   	   
   }
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

											<div class="inventory-page-data">
												<div class="inventory-tabs-outbound">
													<div class="inventory-tabs">
														<div class="inventory-tabs-inbound">
															<div class="main-products-add-products">
																<div class='openTabby' id='tabs1'>
																	<div class='openTabby--slidesContainer'>
																	<div class="pj-preloader"></div>
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
																		<form:form method="POST" action="updateItem"
																			modelAttribute="Item" id="updateItemForm"
																			autocomplete="off">
																			<form:hidden path="id" value="${item.id}" id="item_id" />
																			<div class="adding-products">
																				<label id="errorMessage" style="color: red;"></label>
																				<div class="adding-products-form">
																					<label>Name:</label> 
																					<c:choose>
                                                                                              <c:when test="${merchantType==1}">  
                                                                                              <input type="text"
																						placeholder="${item.name}" value="${item.name}"
																						readonly="readonly">
                                                                                              </c:when>
                                                                                              <c:otherwise>
                                                                                              <form:input path="name"
																						placeholder="name" value="${item.name}" />
                                                                                              </c:otherwise>
                                                                                              </c:choose>
																					 <br>
																					<div class="clearfix"></div>
																					<label>Description:</label>
																					<textarea maxlength="140"  id="description" name="description">${item.description }</textarea>
																					<%-- <form:textarea path="description" maxlength="140"  id="description" value="Testing"/> --%>
																					
																					<br>
																					<div class="clearfix"></div>
																					<label>Category:</label> <select>
																						<c:forEach items="${item.categoryList}" var="view"
																							varStatus="status">
																							<option>${view}</option>
																						</c:forEach>
																					</select> <br>
																					<div class="clearfix"></div>
																					<c:if test="${fn:length(item.extras) gt 0}">
																					<label>Modifier Groups:</label> <form:select id="md_grps" class="multiselect" multiple="multiple" path="itemModifierGroupsStatusIds" >
																						
																						<c:forEach items="${item.itemModifierGroups}"
																							var="modigrp" varStatus="status">
																											<c:choose>
                                                                                              <c:when test="${modigrp.modifierGroupStatus==1}">  
                                                                                              <option value="${modigrp.id}" selected>${modigrp.modifierGroup.name}</option>
                                                                                              </c:when>
                                                                                              <c:otherwise>
                                                                                              <option value="${modigrp.id}" >${modigrp.modifierGroup.name}</option>
                                                                                              </c:otherwise>
                                                                                              </c:choose>																		
																								
																							
																						</c:forEach>

																					</form:select> <br>
																					</c:if>
																					<c:if test="${fn:length(item.extras) gt 0}">
																					<label>Modifiers:</label> <form:select id="modis" multiple="multiple" path="itemModifiersStatusIds" >
																						
																						<%-- <c:forEach items="${item.itemModifierGroups}"
																							var="modigrp" varStatus="status">
																							<c:forEach
																								items="${modigrp.modifierGroup.modifiers}"
																								var="view" varStatus="status">
																								<option>${view.name}</option>
																							</c:forEach>
																						</c:forEach> --%>

																					</form:select> <br>
																					</c:if>
																					<div class="clearfix"></div>
																					<c:if test="${fn:length(item.extras) gt 0}">
																						<div class="sd-modifiers-limit-new">
																							<div class="sd-modifiers-limit-label">
																								<label>Modifier Group Limit: </label>
																							</div>
																							<!--.sd-modifiers-limit-label-->

																							<div class="sd-modifiers-limit-fields">

																								<div class="sd-modifiers-limit-yes-no">
																									Yes
																									<form:radiobutton path="allowModifierLimit"
																										id="allowModifierLimitYes" value="1" />
																									No
																									<form:radiobutton path="allowModifierLimit"
																										id="allowModifierLimitNo" value="0" />
																								</div>
																								<!--.sd-modifiers-limit-yes-no-->
																					<div id="modifier_allow">
																					<c:forEach items="${item.itemModifierGroups}"
																						var="modigrp" varStatus="status">
                                                                                       <c:if test="${fn:length(modigrp.modifierGroup.modifiers) gt 0}">
																						<form:hidden path="itemModifierGroupsIds"
																							value="${modigrp.id}" />
																						<div class="sd-modifiers-each-field-label">
																							<label><span>${modigrp.modifierGroup.name}&nbsp;&nbsp;(Max:</span><label id="modigrp_id_${modigrp.id}">${modigrp.modifierGroup.modifiers.size()}</label>) </label>
																						</div>
																						<!--.sd-modifiers-each-field-label-->
																						<div class="sd-modifiers-each-field-input">
																							<form:input path="modifierLimits"
																								value="${modigrp.modifiersLimit}"
																								class="modiferGrpCls"
																								modigrpAttr="${modigrp.modifierGroup.id}"
																								type='Number' onkeypress="return isNumberKey(event)"  maxlength="2" id="modigrp_modi_id_${modigrp.id}" onblur="checkModifierLimit(${modigrp.id})" />
																						</div>
																						<label id="errorMessage_${modigrp.id}" style="color: red;"></label>
																						<!--.sd-modifiers-each-field-input-->
																						
																						
																						
																						</c:if>
																					</c:forEach>
																					</div>
																					
																				</div>
																				<!--.sd-modifiers-limit-fields-->
																				</c:if>
																				
																				<c:if test="${fn:length(item.extras) gt 0}">
																						<div class="sd-modifiers-limit-new">
																							<div class="sd-modifiers-limit-label">
																								<label>Modifier Group Order: </label>
																							</div>
																							<!--.sd-modifiers-limit-label-->

																							<div class="sd-modifiers-limit-fields">

																								<div class="sd-modifiers-limit-yes-no">
																									Yes
																									<form:radiobutton path="allowModifierGroupOrder"
																										id="itemModifierGroupOrderYes" value="1" />
																									No
																									<form:radiobutton path="allowModifierGroupOrder"
																										id="itemModifierGroupOrderNo" value="0" />
																								</div></div>
																								<!--.sd-modifiers-limit-yes-no-->
																					<div id="item_modifier_order">
																					<c:forEach items="${item.itemModifierGroups}"
																						var="modigrp" varStatus="status">
                                                                                       <c:if test="${fn:length(modigrp.modifierGroup.modifiers) gt 0}">
																						<%-- <form:hidden path="itemModifierGroupsIds"
																							value="${modigrp.id}" /> --%>
																						<div class="sd-modifiers-each-field-label">
																							<label><span>${modigrp.modifierGroup.name}
																						</div>
																						<!--.sd-modifiers-each-field-label-->
																						<div class="sd-modifiers-each-field-input">
																						
																							<select <%-- path="modifierLimits" --%> class="modigrpClass" id="modiGrpOrder_${modigrp.id}" modifierGrpId="${modigrp.id}">
																							   
																							    <c:forEach items="${item.itemModifierGroups}"
																						var="modigrpOrder" varStatus="status">
																						
																						<c:choose>
                                                                           <c:when test="${status.index+1 == modigrp.sortOrder}">  
                                                                            <option value="${status.index+1}" selected="selected">${status.index+1}</option>  
                                                                            </c:when>
                                                                            <c:otherwise>
                                                                            <option value="${status.index+1}">${status.index+1}</option> 
                                                                            </c:otherwise>
                                                                            </c:choose>
																							    
																							    </c:forEach><select>
																						</div>
																						<label id="errorMessage_${modigrp.id}" style="color: red;"></label>
																						<!--.sd-modifiers-each-field-input-->
																						
																						
																						
																						</c:if>
																					</c:forEach>
																					</div>
																					
																				</div>
																				<!--.sd-modifiers-limit-fields-->
																				</c:if>
																				
																				<div class="sd-modifiers-limit-new">
																							<div class="sd-modifiers-limit-label">
																								<label>Display Items On Select Days : </label>
																							</div>
																							<!--.sd-modifiers-limit-label-->

																							<div class="sd-modifiers-limit-fields">

																								<div class="sd-modifiers-limit-yes-no">
																									Yes
																									<form:radiobutton path="allowItemTimings"
																										id="itemTimingsYes" value="1" />
																									No
																									<form:radiobutton path="allowItemTimings"
																										id="itemTimingsNo" value="0" />
																								</div></div></div>
																								<div id="itemTimingDiv">
																								<label>Select Days:</label> <form:select id="days" class="multiselect" multiple="multiple" path="days" >
																						<c:forEach items="${item.itemTimings}"
																							var="itemTime" varStatus="status">
																						
                                                                                              <c:choose>
                                                                                              <c:when test="${itemTime.holiday==false}">  
                                                                                              <option value="${itemTime.day}" selected>${itemTime.day}</option>
                                                                                              </c:when>
                                                                                              <c:otherwise>
                                                                                              <option value="${itemTime.day}" >${itemTime.day}</option>
                                                                                              </c:otherwise>
                                                                                              </c:choose>
                                                                                              																		
																								
																						</c:forEach>	
																						

																					</form:select> <br>
																								<label>Select Timing:</label>
																					<div class="business-hours-li-right teset" style="width: 35%;">
																								
																									<div class="copyTime">
																										<form:select path="startTime" style="width: 38%;" >
																										<c:forEach items="${times}" var="tm">
																												<c:choose>
																													<c:when test="${tm ==item.startTime}">
																														<option value="${tm}"
																															selected="selected">${tm}</option>
																													</c:when>
																													<c:otherwise>
																														<option value="${tm}">${tm}</option>
																													</c:otherwise>
																												</c:choose>
																											</c:forEach>
																										</form:select>

																										<to>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;to&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</to>

																										<form:select path="endTime" style="width: 38%;" >
																											<c:forEach items="${times}" var="tm">
																												<c:choose>
																													<c:when test="${tm ==item.endTime}">
																														<option value="${tm}"
																															selected="selected">${tm}</option>
																													</c:when>
																													<c:otherwise>
																														<option value="${tm}">${tm}</option>
																													</c:otherwise>
																												</c:choose>
																											</c:forEach>
																												</form:select>
																									</div>
																								
																								<div class="copyToTime"
																									style="min-height: 47px;"></div>

																							</div> </div> <br><br>
																				<div class="clearfix"></div>
																			<label>Price:</label>
																		   <price>$</price>
																		   <c:choose>
                                                                           <c:when test="${merchantType==1}">  
                                                                            <input  class="dollar" value="${item.price }" readonly="readonly" style="height: 45px;" > 
                                                                            </c:when>
                                                                            <c:otherwise>
                                                                            <form:input path="price" class="dollar" value="${item.price }" type='Number' onkeypress="return isNumberKey(event)" 
																					onblur="currectNo(event)" min="0" maxlength="5"
																					oninput="javascript: if (this.value.length > this.maxLength) this.value = this.value.slice(0, this.maxLength);" style="height: 45px;"/>
																		    </c:otherwise>
                                                                            </c:choose>
	
																			<br>
																			<div class="clearfix"></div>
																			<label>Status:</label>
																			<form:radiobutton path="itemStatus" name="item"
																				value="0" id="activeStatus" /> Active<br>
																			<label></label><form:radiobutton path="itemStatus" name="item"
																				value="1" id="inactiveStatus" /> InActive<br>
																				
																				
																					
																							<div class="clearfix"></div>
																			<div class="button left">
																				<!--  <a href="#" id="updateItemButton">Save</a> -->
																				<input type="button" id="updateItemButton"
																					value="Save">&nbsp;&nbsp; <input
																					type="button" value="Cancel"
																					id="updateItemCancelButton">

																			</div>
																			<!-- <div id="example_filter" class="dataTables_filter"></div> -->

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
	<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
	<script src="https://code.jquery.com/jquery-2.1.4.min.js"></script>
	<script src='resources/js/products-tabs/opentabby.js'></script>
	<script src="resources/js/popModal.js"></script>
	<script type="text/javascript" src="resources/js/bootstrap.min.js"></script>
    <script src="resources/js/bootstrap-multiselect.js" type="text/javascript"></script>
	<script>
        $(".openTabby").openTabby();
        function isNumberKey(evt)
        {
      	  var target = evt.target || evt.srcElement; // IE
      	  var charCode = (evt.which) ? evt.which : event.keyCode
      	    var id = target.id;
      	    var data=document.getElementById(id).value;
      	  
      	  
      	  if(evt.which == 8 || evt.which == 0){
                return true;
            }
      	  
      	 if (charCode > 31 && (charCode < 48 || charCode > 57)){
             return false;
      		}
      	 
            if(evt.which < 46 || evt.which > 59) {
                return false;
                //event.preventDefault();
            } // prevent if not number/dot

            if(evt.which == 46 && data.indexOf('.') != -1) {
                return false;
                //event.preventDefault();
            }
        }
    </script>

	<!-- <script
		src="https://cdn.datatables.net/1.10.11/js/jquery.dataTables.min.js"></script> -->
		
		<!-- select with checkboxes -->
	
	
   
    
    <script type="text/javascript">
    function loadModifiers(selected){
    	
   	 //$('#modis').multiselect('rebuild');
   	
   	
    	var itemId="${item.id}";
    	
    	 $.ajax({
    		 
             url : "getModifiersByModifierGroupIds?modifierGroupIds=" + selected+"&itemId="+itemId,
             type : "GET",
             contentType : "application/json; charset=utf-8",
             success : function(data) {
            	 //$("#modis").multiselect();
            	 
             	$('#modis').find('option').remove();
             	$('#modis').find('optgroup').remove();
               var itemModiifers = data;
               
               for (i = 0; i < itemModiifers.length; i++) {
             	  var itemModifier=itemModiifers[i];
             	 var modifierGroupName=itemModifier[0].modifierGroupName;
                 
             	$( "#modis" ).append("<optgroup label='"+modifierGroupName+"'>");
             	 for (k = 0; k < itemModifier.length; k++) {
             		var itemModis=itemModifier[k];
                    
             	  if(itemModis.modifierStatus==1){
             	  $( "#modis" ).append("<option value='"+itemModis.id+"' selected>"+itemModis.modifierNmae+"</option>");
             	  }else{
             		 $( "#modis" ).append("<option value='"+itemModis.id+"'>"+itemModis.modifierNmae+"</option>");
             	  }
               }
             	$( "#modis" ).append("</optgroup>");
               }
               
              	$('#modis').multiselect('rebuild');
              	$('#modis').multiselect('refresh'); 
              	
               	
             },
             error : function() {
                 console.log("Error in category wise inventory");
              }
           })
    	
    }
        $(function () {
            $('#md_grps').multiselect({
                includeSelectAllOption: true
            });
            
            $('#days').multiselect({
                includeSelectAllOption: true
            });
            
            $('#modis').multiselect({
            	includeSelectAllOption: true
            }); 
            
            //$("#modis").multiselect();
            
            
            
            $('#md_grps').change(function(e) {
                var selected = $(e.target).val();
                
                loadModifiers(selected);
            });

            
            	$('.modigrpClass').change(function(e) {
            		sortOrder = $(e.target).val();
            		 itemModifierGroupId=$(this).attr('modifierGrpId');
            		
            		var itemId="${param.itemId}";
            		var itemModifierGroupOrder = $("#itemModifierGroupOrderYes").val();
            		
            		$('.pj-preloader').css('display','block');
            		
            		$.ajax({
                        type: 'GET',
                        url: "updateItemModifierGroupSortOrderById?itemModifierGroupId="+itemModifierGroupId+"&sortOrder="+sortOrder+"&itemId="+itemId+"&itemModifierGroupOrder="+itemModifierGroupOrder+"&action=check",
                        success:function(data){
                       	 
                       	 $('.pj-preloader').css('display','none');
                       	 if(data=='succuss'){
                       		// alert(data);
                       		 //$('.pj-preloader').css('display','none');
                       		 $("#errorBox").html("");
                       		window.location='addLineItem?itemId='+itemId;
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


            /* $('#md_grps').load(function(e) {
                var selected = $(e.target).val();
                
                loadModifiers(selected);
            }); */
            
           
            
            
            
           /*  $('#modis').multiselect({
                includeSelectAllOption: true
            }); */
            
            
            
            /* $('#md_grps').click(function () {
                var selected = $("#md_grps option:selected");
                var message = "";
                selected.each(function () {
                    message += $(this).text() + " " + $(this).val() + "\n";
                });
                alert(message);
            }); */
        });
    </script>
	
	<!-- select with checkboxes -->
	<script type="text/javascript">
	var status=0;
	var modifier_Status=[];
	  function checkModifierLimit(modi_grp_id){
		  
		  
		  
		  var modigrp_id="modigrp_id_"+modi_grp_id
		  var modigrp_modi_id= "modigrp_modi_id_"+modi_grp_id
		  
		  var modifierMaxLimit=document.getElementById(modigrp_id).textContent;
		  var modifierLimit=document.getElementById(modigrp_modi_id).value;
		   
		  if(Number(modifierMaxLimit)< Number(modifierLimit)){
			  var index = modifier_Status.indexOf(modi_grp_id);
			  if (index == -1) {
			  modifier_Status.push(modi_grp_id);
			  }
		  $("#"+modigrp_modi_id).css('border-color', 'red');
		  $("#errorMessage_"+modi_grp_id).html("Modifier limit exceeded");
		  }else{
			  
			  var index = modifier_Status.indexOf(modi_grp_id);
			 
			  if (index > -1) {
				  modifier_Status.splice(index, 1);
				}
			  $("#"+modigrp_modi_id).css('border-color', '');
			  $("#errorMessage_"+modi_grp_id).html("");
		  }
		
		 
		 
		  
	  }
	  
         $(document).ready(function() {
        	 
        	
                 var selected = $('#md_grps').val();
                 loadModifiers(selected); 
             
        	
            /* var table = $('#example').DataTable(); */
         });
         /* $(document).ready(function(){
           $('input[type=search]').each(function(){
             $(this).attr('placeholder', "Search Items");
           });
         }); */
     </script>
	<script type="text/javascript">
     
      $(document).ready(function() {
          $("#updateItemButton").click(function () {
        	  
            if(jQuery("#allowModifierLimitYes").is(":checked")){
            	
            	var modifier_Status_length=modifier_Status.length;
            	//alert(modifier_Status_length);
            	if(modifier_Status_length==0){
            	 $("#updateItemForm").submit();
            }
            }else if(jQuery("#itemTimingsYes").is(":checked")){
      		  if($("#days").val()==null || $("#days").val()==''){
    			  $("#errorMessage").html("Please select atleast one day.");
    		  }else{
    			  $("#updateItemForm").submit();
    		  }
    	  
    	 
    	  }else{
            	$("#updateItemForm").submit();
            }
          });
          
          var mLimit="${modifierLimit}";
          $("#modifierLimit").val(mLimit);
          var itemStatus="${itemStatus}";
          if(itemStatus==0){
            $("#activeStatus").attr('checked', 'checked');
          }
          if(itemStatus==1){
            $("#inactiveStatus").attr('checked', 'checked');
          }
          
          var allowModifierLimit="${item.allowModifierLimit}";
          var allowModifierGroupOrder="${item.allowModifierGroupOrder}";
          var allowItemTimings="${item.allowItemTimings}";
          
          if(allowItemTimings==0){
              $("#itemTimingsNo").attr('checked', 'checked');
              $("#errorMessage").html("");
              $("#itemTimingDiv").hide();
            }
            if(allowItemTimings==1){
              $("#itemTimingsYes").attr('checked', 'checked');
              $("#itemTimingDiv").show();
            }
          
          if(allowModifierLimit==0){
              $("#allowModifierLimitNo").attr('checked', 'checked');
              $("#errorMessage").html("");
              $("#modifier_allow").hide();
            }
            if(allowModifierLimit==1){
              $("#allowModifierLimitYes").attr('checked', 'checked');
              $("#modifier_allow").show();
            }
            
            if(allowModifierGroupOrder==0){
                $("#itemModifierGroupOrderNo").attr('checked', 'checked');
                $("#errorMessage").html("");
                $("#item_modifier_order").hide();
              }
              if(allowModifierGroupOrder==1){
                $("#itemModifierGroupOrderYes").attr('checked', 'checked');
                $("#item_modifier_order").show();
              }
          
          $("#updateItemCancelButton").click(function() {
            window.location="inventory"
          });
          
         /*  $(".modiferGrpCls").bind('blur', function () {
             var modiferCount=$(this).val();
             var modifierGroupId=$(this).attr("modigrpAttr");
             
             $.ajax({
              url : "findModifierCountOfModifierGroup?modiferCount=" + modiferCount + "&modifierGroupId=" + modifierGroupId,
              type : "GET",
              contentType : "application/json; charset=utf-8",
              success : function(statusValue) {
                if (statusValue=="true") {
                  $(this).focus(); 
                  $("#errorMessage").html("Modifier limit exceeded");
                  status=1;
                }else{
                  $("#errorMessage").html("");
                  status=0;
                }
              },
              error : function() {
                alert("error");
              }
            })            
          }); */
      });
      
     /*  function isNumberKey(evt)
      {
    	  var target = evt.target || evt.srcElement; // IE

    	    var id = target.id;
    	    var data=document.getElementById(id).value;
    	  
    	  
    	  if(evt.which == 8 || evt.which == 0){
              return true;
          }
          if(evt.which < 46 || evt.which > 59) {
              return false;
              //event.preventDefault();
          } // prevent if not number/dot

          if(evt.which == 46) {
              return false;
              //event.preventDefault();
          }
      } */
      
      function currectNo(evt){
    	  var target = evt.target || evt.srcElement; // IE

  	    var id = target.id;
  	    var data=document.getElementById(id).value;
  	    
  	    var res=data.split(".");
    	  
    	  if(res.length==2){
    		if(res[1]){
    			
    		}else{
    			
    			document.getElementById(id).value=res[0]+".0";
    		}
    	  }
      }
      
      $(document).ready(function(){
    	    $("#allowModifierLimitNo").click(function(){
    	    	$("#errorMessage").html("");
    	        $("#modifier_allow").hide();
    	    });
    	    $("#allowModifierLimitYes").click(function(){
    	        $("#modifier_allow").show();
    	    });
    	    
    	    $("#itemTimingsNo").click(function(){
    	    	$("#errorMessage").html("");
    	        $("#itemTimingDiv").hide();
    	    });
    	    $("#itemTimingsYes").click(function(){
    	        $("#itemTimingDiv").show();
    	    });
    	    
    	    
    	    $("#itemModifierGroupOrderNo").click(function(){
    	    	$("#errorMessage").html("");
    	        $("#item_modifier_order").hide();
    	    });
    	    $("#itemModifierGroupOrderYes").click(function(){
    	        $("#item_modifier_order").show();
    	    });
    	    
    	    
    	});
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
