<!-- <script src="https://code.jquery.com/jquery-2.1.4.min.js"></script> -->
<footer id="footer-container">
    <div class="footer-outer-container">
        <div class="footer-inner-container">
            <div class="row">
                <div class="sd-inner-footer">

                    <div class="footer-left">
                        <p>Powered by foodkonnekt | copyright@foodkonnekt.com</p>
                    </div>
                    <!--.footer-left-->
                    <div class="footer-right">
                        <img src="resources/img/foodkonnekt-logo.png" />
                    </div>
                    <!--.footer-right-->
                </div>
                <!--.sd-inner-footer-->
            </div>
            <!--.row-->
        </div>
        <!--.footer-inner-container-->
    </div>
    <!--.footer-outer-container-->
</footer>
<script>
$(document).ready(function() {
  var url=window.location.href;
   if((url.indexOf("adminHome"))>1){
      $(".adminHome").addClass("sd_menu"); 
   }
      
   if((url.indexOf("allOrders"))>1 ||(url.indexOf("customerOrders"))>1){
	      $(".allOrders").addClass("sd_menu"); 
	   }
      
   if((url.indexOf("inventory"))>1){
      $(".inventory").addClass("sd_menu"); 
   }
      
   if((url.indexOf("onLineOrderLink"))>1){
      $(".onLineOrderLink").addClass("sd_menu"); 
   }
   
   
   if((url.indexOf("deliveryZones"))>1){
      $(".onLineOrderLink").addClass("sd_menu"); 
   }
   if((url.indexOf("addDeliveryZone"))>1){
     $(".onLineOrderLink").addClass("sd_menu"); 
  }
   if((url.indexOf("vouchars"))>1){
      $(".onLineOrderLink").addClass("sd_menu"); 
   }
   if((url.indexOf("createVouchar"))>1){
      $(".onLineOrderLink").addClass("sd_menu"); 
   }
   
   if((url.indexOf("customers"))>1){
      $(".onLineOrderLink").addClass("sd_menu"); 
   }
   
   
   if((url.indexOf("category"))>1){
     $(".inventory").addClass("sd_menu"); 
  }
   if((url.indexOf("modifierGroups"))>1){
     $(".inventory").addClass("sd_menu"); 
  }
   if((url.indexOf("modifiers"))>1){
     $(".inventory").addClass("sd_menu"); 
  }
   if((url.indexOf("findItemsByCategoryId"))>1){
     $(".inventory").addClass("sd_menu"); 
  }
   if((url.indexOf("addLineItem"))>1){
     $(".inventory").addClass("sd_menu"); 
  }
   
});
</script>