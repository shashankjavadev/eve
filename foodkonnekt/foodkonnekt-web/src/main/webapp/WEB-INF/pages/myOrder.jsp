<%@ include file="header.jsp"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
  <!--OFF PopUP JS/CSS STARTS-->
  <script type="text/javascript" src="resources/js/ping-nose-popup/pignose.popup.min.js"></script>
  <script type="text/javascript">
  var orderStatus="${orderStatus}";
    $(function() {
      $('.btn.pg-orange').bind('click', function(event) {
        var ordPrice = $(this).attr('ordPrice');
           event.preventDefault();
        $('#pg-orange'+ordPrice).pignosePopup({
          theme: 'orange'
        });
      });
    });
   function orderFail(){
        window.location.href = "order";
   }
  </script>
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.11/css/jquery.dataTables.min.css">
<script src="https://cdn.datatables.net/1.10.11/js/jquery.dataTables.min.js"></script>
 <script type="text/javascript">
 $(document).ready(function() {
    var table = $('#example').DataTable();
  /*   $('#example tbody').on('click', 'tr', function () {
        var data = table.row( this ).data();
        alert( 'You clicked on '+data[0]+'\'s row' );
    } ); */
} );
 </script>
  <!--OFF PopUP JS/CSS ENDS-->
  <div class="exampleLive">
    <button style="display:none;" id="orderFailPopUp" class="btn1 btn-primary" data-confirmmodal-bind="#orderFailPopUp_content" data-topoffset="0" data-top="10%" >Example</button>
</div>
<div id="orderFailPopUp_content" style="display:none">
    <div class="confirmModal_content sd-popup-content">
    <img src="resources/img/logo.png" class="sd-popup-logo">
    <h3 style="font-size: 14px;">Your order was not placed. Please try again or call us</h3>
    </div>
    <div class="confirmModal_footer">
       <button type="button" class="btn1 btn-primary" onclick="orderFail()">Ok</button>
    </div>
</div>
              <section class="main-section">
                <div class="useless-inner-container">
                  <div class="sd-page-container">
                    <div id="sd-form-container">
                      <h3 class="sd-fancy-title page-title">My Orders</h3>
                      <hr>
                      <div class="order-container">
                        <div class="orders-outbound">
                          <div class="orders-inbound">
                            <div class="orders-inner-container">
                              <table width="100%" cellpadding="0" cellspacing="0" id="example">
                                  <thead>
                                <tr class="header-table-orders">
                                  <td>SN</td>
                                  <td>Date</td>
                                  <td>Restaurant</td>
                                  <td>Order</td>
                                </tr>
                              </thead>
                                <tbody>
                                <c:forEach items="${orderList}" var="order" varStatus="status">
                                  <tr>
                                    <td>${status.index+ 1 }</td>
                                    <td><fmt:formatDate pattern="MM/dd/yyyy" value="${order.createdOn}" /> </td>
                                    <td>${order.merchant.name}</td>
                                    <td>
                                      <div class="total-count-sd-popup-outbound">
                                        <div class="total-count-sd-popup-inbound">
                                          <div class="total-count-sd-popup">
                                            <div class="">
                                              <a href="#" ordPrice="${order.id}" class="no-button-style button btn btn-default btn-lg pg-orange">$<fmt:formatNumber  value="${order.orderPrice}" maxFractionDigits="2"/></a>
                                            </div>
                                            
                                            <div id="pg-orange${order.id}" class="pignose-popup" style="left: 262.5px !important;max-width: 608px;">
                                              <div class="item_header">
                                                 <span class="txt_title">Order Details</span>
                                              </div>
                                              <div class="item_content">
                                                                                    <div class="scroll-bar-your-cart">
                                                                                        <div class="your-cart">
                                                                                            <div class="shopping-details">
                                                                                                <div class="item-qty-price">
                                                                                                    <div class="item-qty-price-ul heading-item-qty-price">
                                                                                                        <ul>
                                                                                                            <li class="heading-item"><span>Item</span></li>
                                                                                                            <li class="heading-qty"><span>Quantity</span></li>
                                                                                                            <li class="heading-price"><span>Price</span></li>
                                                                                                        </ul>
                                                                                                    </div><!--.item-qty-price-ul-->
                                                                                                 <div class="foodkonnekt-order-detail" style="width: 583px;height: 140px;overflow-y: scroll;">
                                                                                                 <c:forEach items="${order.orderItemViewVOs}" var="itemView" varStatus="status1">   
                                                                                                    <div class="item-qty-price-ul">
                                                                                                            <ul>
                                                                                                                <li>${itemView.item.name}</li>
                                                                                                                <li>${itemView.quantity}</li>
                                                                                                                <li>$ 
                                                                                                                <fmt:formatNumber type="number" minFractionDigits="2" value="${itemView.item.price}" />
                                                                                                                </li>
                                                                                                            </ul>
                                                                                                         <c:forEach items="${itemView.itemModifierViewVOs}" var="modifierView" varStatus="status2">   
                                                                                                            <ul>
                                                                                                                <li>${modifierView.modifiers.name}</li>
                                                                                                                <li>${modifierView.quantity}</li>
                                                                                                                <li>$ 
                                                                                                                <fmt:formatNumber type="number" minFractionDigits="2" value="${modifierView.modifiers.price}" />
                                                                                                                </li>
                                                                                                            </ul>
                                                                                                         </c:forEach> 
                                                                                                    </div><!--.item-qty-price-ul-->
                                                                                                  </c:forEach>     
                                                                                               </div>     
                                                                                                </div><!--.item-qty-price-->
                                                                                                <hr>
                                                                                                <div class="subtotal-tax-total">
                                                                                                    <ul>
                                                                                                        <li>SubTotal</li>
                                                                                                        <li></li>
                                                                                                        <li>$ 
                                                                                                        <fmt:formatNumber type="number" minFractionDigits="2" value="${order.subTotal}" />
                                                                                                       </li>
                                                                                                    </ul>
                                                                                                    <c:if test="${not empty order.convenienceFee}">
                                                                                                    <ul>
                                                                                                        <li>Convenience Fee</li>
                                                                                                        <li></li>
                                                                                                        <li>$
                                                                                                        <fmt:formatNumber type="number" minFractionDigits="2" value="${order.convenienceFee}" />
                                                                                                        </li>
                                                                                                    </ul>
                                                                                                    </c:if>
                                                                                                     <%-- <c:if test="${order.deliveryFee!=0}"> --%>
                                                                                                    <c:if test="${order.orderType!='pickup' && order.orderType!='Pickup'}">
                                                                                                    <ul>
                                                                                                        <li>Delivery Fee</li>
                                                                                                        <li></li>
                                                                                                        <li>$
                                                                                                        <fmt:formatNumber type="number" minFractionDigits="2" value="${order.deliveryFee}" />
                                                                                                        </li>
                                                                                                    </ul>
                                                                                                    </c:if>
                                                                                                    <ul>
                                                                                                        <li>Tax</li>
                                                                                                        <li></li>
                                                                                                        <li>$
                                                                                                        <fmt:formatNumber type="number" minFractionDigits="2" value="${order.tax}" />
                                                                                                        </li>
                                                                                                    </ul>
                                                                                                    <c:if test="${not empty order.tipAmount && order.tipAmount>0}">
                                                                                                    <ul>
                                                                                                        <li>Tip</li>
                                                                                                        <li></li>
                                                                                                        <li>$
                                                                                                        <fmt:formatNumber type="number" minFractionDigits="2" value="${order.tipAmount}" />
                                                                                                        </li>
                                                                                                    </ul>
                                                                                                    </c:if>
                                                                                                    <ul>
                                                                                                        <li>Total</li>
                                                                                                        <li></li>
                                                                                                        <li>$
                                                                                                         <fmt:formatNumber type="number" minFractionDigits="2" value="${order.orderPrice}" />
                                                                                                        </li>
                                                                                                    </ul>
                                                                                                </div><!--.subtotal-tax-total-->
                                                                                                <div class="sd-comment-box">
                                                                                                    <textarea name="sd-comment-box" cols="" rows="4" readonly="readonly">${order.orderNote}</textarea>
                                                                                                </div><!--.sd-comment-box-->
                                                                                                <div class="large-12 columns no-padding">
                                                                                                    <a href="#" class="btn_close right sd-new-button">Close</a>
                                                                                                </div>
                                                                                            </div><!---shopping-details---->
                                                                                        </div><!----your-cart---->
                                                                                    </div><!--.scroll-bar-your-cart-->
                                                                                </div><!--.item_content-->
                                              </div>
                                              
                                              
                                            </div>
                                            <!--.total-count-sd-popup-->
                                          </div>
                                          <!--.total-count-sd-popup-inbound-->
                                        </div>
                                        <!--.total-count-sd-popup-outbound-->
                                    </td>
                                  </tr>
                                    </c:forEach>
                                </tbody>
                              </table>
                              <hr>
                              <div class="clearfix"></div>
                              
                              </div>
                              <!--.orders-inner-container-->
                            </div>
                            <!--.orders-inbound-->
                          </div>
                          <!--.orders-outbound-->
                        </div>
                        <!--.order-container-->
                      </div>
                      <!-----#sd-form-container------>
                      <div id="footer">
                        <div class="footer-container-outbound">
                          <div class="footer-container-inbound">
                            <div class="small-12 medium-12 large-12 columns">
                              <div class="copyrights">
                                <ul>
                                  <li>Powered By
                                    <a href="http://foodkonnekt.com/" target="_blank">FoodKonnekt</a>
                                  </li>
                                  <li>
                                    <a href="http://foodkonnekt.com/" target="_blank">
                                      <img src="resources/img/powered-by-foodkonnekt-logo.png">
                                    </a>
                                  </li>
                                </ul>
                              </div>
                              <!--.copyrights-->
                            </div>
                            <!--.grid-->
                          </div>
                          <!--.footer-container-inbound-->
                        </div>
                        <!--.footer-container-outbound-->
                      </div>
                      <!--#footer-->
                    </div>
                    <!--- sd-page-container ---->
                  </div>
                  <!--.useless-inner-container-->
              </section>
              <a class="exit-off-canvas"></a>
              </div>
              <!----- page-color ---->
            </div>
            <!---- row ---->
          </div>
          <!------ main-useless-class ----->
        </div>
        <!----inner wrap---->
      </div>
      <!-----off-canvas---->
    </div>
    <!---- sd-page-overlay ------>
    <!--OFF CANVAS MENU JS/CSS STARTS-->
      <style>
*{    font-family: 'Open Sans', sans-serif;}
.btn1 {
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
.btn1.btn-default {}
.btn1.btn-default:hover {background:#eee;border-color:#bbb}
.btn1.btn-default:focus {background:#ddd;border-color:#bbb}
.btn1.btn-primary {
    background-color: #f8981d;
    border-color: #f8981d;
    color: #fff;
}
.btn1.btn-primary:hover {
    background-color: #ffac41;
    border-color: #f8981d;
}
.btn1.btn-primary:focus {
    background-color: #f8981d;
    border-color: #f8981d;
}
.btn1.btn-default[disabled] {background:#fafafa!important;border-color:#ccc!important;color:#aaa}
.btn1.btn-primary[disabled] {background:#3F9DD0!important;border-color:#537FA9!important;color:#ACD3E8;box-shadow:none!important}
.btn1.btn-left {float:left;margin:0 5px 0 0!important}
.sd-popup-content img {
    display: block;
    margin: 0 auto 10px;
}
.sd-popup-content h3 {
    text-align: center;
    font:bold;
}
</style>
    <link type="text/css" rel="stylesheet" href="resources/css/popModal.css">
   <script src="resources/js/popModal.js"></script>  
   <script>
   if(orderStatus=='Y'){
   jQuery(function(){
          jQuery('#orderFailPopUp').click();
      });
   }
   </script>