<%@ include file="header.jsp"%>
<script src="resources/js/jsp-js/profile.js"></script>
<!-- Hotjar Tracking Code for https://www.foodkonnekt.com/ -->
<script>
    (function(h,o,t,j,a,r){
        h.hj=h.hj||function(){(h.hj.q=h.hj.q||[]).push(arguments)};
        h._hjSettings={hjid:460451,hjsv:5};
        a=o.getElementsByTagName('head')[0];
        r=o.createElement('script');r.async=1;
        r.src=t+h._hjSettings.hjid+j+h._hjSettings.hjsv;
        a.appendChild(r);
    })(window,document,'//static.hotjar.com/c/hotjar-','.js?sv=');
</script>

<style type="text/css">
img.close {
    position: absolute;
    right: -8px;
    top: -5px;
    width: 32px;
}
.fieldset1 {
    margin-top: 10px;
    position: relative;
}
</style>
<script type="text/javascript">
$(document).ready(function() {
	
	var merchantId="${Customer.merchantt.id}";
	var bgColor='#f8981d';
	 if(merchantId==268){
		 bgColor='#b8b308';
	 $(".button.hollow.success").css('color', bgColor);
    	$(".button.hollow.success").css('border-color', bgColor);
    	$(".button.hollow.success.right").css('background-color', bgColor);
    	$(".button.hollow.success.right").css('color', 'white');
	 }
    	
    	
	
  $("#password").val("${Customer.password}");
  $("#checkId").val(0);
  var bDate="${Customer.birthDate}";
  var aDate="${Customer.anniversaryDate}";
  if(bDate!='' && aDate!='')
    {
    var b_array = bDate.split(',');
    var a_array = aDate.split(',');
    $("#birthday-month").val(b_array[0]);
    $("#birthday-date").val(b_array[1]);
    $("#anniversary-month").val(a_array[0]);
    $("#anniversary-date").val(a_array[1]);
    }
  $('#subscribe-check').attr('checked', true);
});


$(document).on("click",".close",function() {
  var count = $("#fieldset").children().length;
  if(count>1) {
  $(this).parent().remove();
  }
}); 

$(document).ready(function(){
$('.btn1').click(function(){
      $("#fieldset1").clone().appendTo("#fieldset");
      $('.btn2').css('visibility','visible');
});
var paddrss1="${Customer.addresses[0].address1}";
var paddrss2="${Customer.addresses[0].address2}";
var pcity="${Customer.addresses[0].city}";
var pstate="${Customer.addresses[0].state}";
var pzip="${Customer.addresses[0].zip}";
var pid="${Customer.addresses[0].id}";
var paddressPosId="${Customer.addresses[0].addressPosId}";
$('#previousAddress').val(paddrss1+"#"+paddrss2+"#"+pcity+"#"+pstate+"#"+pzip+"#"+pid+"#"+paddressPosId);
});
</script>
<div class="exampleLive">
    <button style="display:none;" id="deliveryFailurePopUp" class="btn btn-primary" data-confirmmodal-bind="#confirm_content_deliveryfailure" data-topoffset="0" data-top="10%" >Example</button>
</div>
<div id="confirm_content_deliveryfailure" style="display:none">
    <div class="confirmModal_content sd-popup-content">
    <img src="resources/img/logo.png" class="sd-popup-logo">
    <h3 style="font-size: 14px;"><b><span id="delMessage"></span></b></h3>
    </div>
    <div class="confirmModal_footer">
       <button type="button" class="btn btn-primary" data-confirmmodal-but="ok">Ok</button>
    </div>
</div>

<div class="exampleLive">
    <button style="display:none;" id="passwordIdBtn" class="btn btn-primary" data-confirmmodal-bind="#passwordId" data-topoffset="0" data-top="10%" >Example</button>
</div>
<div id="passwordId" style="display:none">
    <div class="confirmModal_content sd-popup-content">
    <img src="resources/img/logo.png" class="sd-popup-logo">
    <h3 style="font-size: 14px;">Incorrect old password , please try again.</h3>
    </div>
    <div class="confirmModal_footer">
        <button type="button" class="btn btn-primary" data-confirmmodal-but="ok">Ok</button>
    </div>
</div>

            <section class="main-section">
              <div class="useless-inner-container">
                <div class="sd-page-container">
                  <div id="sd-form-container">
                    <h3 class="sd-fancy-title page-title">My Profile</h3>
                    <hr>
                    <h3 class="sd-fancy-title">General Information</h3>
                    <hr>
                    <form:form method="POST" modelAttribute="Customer" name="myform" id="myform" autocomplete="off">
                      <form:hidden path="id" />
                      <form:hidden path="merchantt.id" />
                      <form:hidden path="checkId" id="checkId" />
                      <div class="small-12 medium-6 large-6 columns">
                        <label for="first-name">First Name</label>
                        <form:input path="firstName" id="first-name" placeholder="First Name" maxlength="15" />
                      </div>

                      <div class="small-12 medium-6 large-6 columns">
                        <label for="second-name">Last Name</label>
                        <form:input path="lastName" id="second-name" placeholder="Last Name" maxlength="15" />
                      </div>
                      <div class="clearfix"></div>
                      <hr>
                      
                      <div class="small-12 medium-6 large-6 columns">
                      <p><strong>Birthday</strong></p>
                            <div class="small-12 medium-6 large-6 columns no-padding">
                              <label for="birthday-month">Month</label>
                              <select name="birthDate" id="birthday-month" >
                                <option value="Jan">Jan</option>
                                <option value="Feb">Feb</option>
                                <option value="Mar">Mar</option>
                                <option value="Apr">Apr</option>
                                <option value="May">May</option>
                                <option value="Jun">Jun</option>
                                <option value="Jul">Jul</option>
                                <option value="Aug">Aug</option>
                                <option value="Sep">Sep</option>
                                <option value="Oct">Oct</option>
                                <option value="Nov">Nov</option>
                                <option value="Dec">Dec</option>
                              </select>
                            </div>
                            <div class="small-12 medium-6 large-6 columns no-padding">
                              <label for="birthday-date">Date</label>
                              <select name="birthDate" id="birthday-date">
                                <option value="01">01</option>
                                <option value="02">02</option>
                                <option value="03">03</option>
                                <option value="04">04</option>
                                <option value="05">05</option>
                                <option value="06">06</option>
                                <option value="07">07</option>
                                <option value="08">08</option>
                                <option value="09">09</option>
                                <option value="10">10</option>
                                <option value="11">11</option>
                                <option value="12">12</option>
                                <option value="13">13</option>
                                <option value="14">14</option>
                                <option value="15">15</option>
                                <option value="16">16</option>
                                <option value="17">17</option>
                                <option value="18">18</option>
                                <option value="19">19</option>
                                <option value="20">20</option>
                                <option value="21">21</option>
                                <option value="22">22</option>
                                <option value="23">23</option>
                                <option value="24">24</option>
                                <option value="25">25</option>
                                <option value="26">26</option>
                                <option value="27">27</option>
                                <option value="28">28</option>
                                <option value="29">29</option>
                                <option value="30">30</option>
                                <option value="31">31</option>
                              </select>
                            </div>
                           </div>
                         <div class="small-12 medium-6 large-6 columns">
                        <p><strong>Anniversary</strong></p>
                            <div class="small-12 medium-6 large-6 columns no-padding">
                              <label for="anniversary-month">Month</label>
                              <select name="anniversaryDate" id="anniversary-month">
                                <option value="Jan">Jan</option>
                                <option value="Feb">Feb</option>
                                <option value="Mar">Mar</option>
                                <option value="Apr">Apr</option>
                                <option value="May">May</option>
                                <option value="Jun">Jun</option>
                                <option value="Jul">Jul</option>
                                <option value="Aug">Aug</option>
                                <option value="Sep">Sep</option>
                                <option value="Oct">Oct</option>
                                <option value="Nov">Nov</option>
                                <option value="Dec">Dec</option>
                              </select>
                            </div>
                            <div class="small-12 medium-6 large-6 columns no-padding">
                              <label for="anniversary-date">Date</label>
                              <select name="anniversaryDate" id="anniversary-date">
                                <option value="01">01</option>
                                <option value="02">02</option>
                                <option value="03">03</option>
                                <option value="04">04</option>
                                <option value="05">05</option>
                                <option value="06">06</option>
                                <option value="07">07</option>
                                <option value="08">08</option>
                                <option value="09">09</option>
                                <option value="10">10</option>
                                <option value="11">11</option>
                                <option value="12">12</option>
                                <option value="13">13</option>
                                <option value="14">14</option>
                                <option value="15">15</option>
                                <option value="16">16</option>
                                <option value="17">17</option>
                                <option value="18">18</option>
                                <option value="19">19</option>
                                <option value="20">20</option>
                                <option value="21">21</option>
                                <option value="22">22</option>
                                <option value="23">23</option>
                                <option value="24">24</option>
                                <option value="25">25</option>
                                <option value="26">26</option>
                                <option value="27">27</option>
                                <option value="28">28</option>
                                <option value="29">29</option>
                                <option value="30">30</option>
                                <option value="31">31</option>
                              </select>
                            </div>
                         </div>
                      <div class="clearfix"></div>
                      <hr>
                      <div class="small-12 medium-6 large-6 columns">
                        <label for="email">Email Address</label> <!-- <span class="text-field"> --> <form:input
                            path="emailId" id="email" placeholder="Email Address" maxlength="60" />
                        <!-- </span> --><!--  <span class="add-more">  </span> -->
                      </div>
                      <div class="small-12 medium-6 large-6 columns">
                        <label for="mobile">Mobile</label> <!-- <span class="text-field"> --> <form:input
                            path="phoneNumber" type="text" id="mobile" placeholder="Mobile" class="call_caller_phone" maxlength="12" />
                       <!--  </span> --> <!-- <span class="add-more"> </span> -->
                      </div>
                      <div class="clearfix"></div>
                      <hr>
                      <div class="clearfix"></div>
                      
                      <c:if test="${ customerAddresses.size() > 1}">
                    <div class="small-12 medium-4 large-4 columns">
                        <label for="state">Addresses</label>
                     <select id="previousAddress">
                       <c:forEach items="${customerAddresses}" var="view" varStatus="status">
                         <option value="${view.address1}#${view.address2}#${view.city}#${view.state}#${view.zip}#${view.id}">${view.address1} ${view.address2}</option>
                       </c:forEach>
                      </select>
                    </div>
                    
                    </c:if>
                      <div class="clearfix"></div>
                      <label id="errorBox" class="error"></label>
                      <h3 class="sd-fancy-title">Delivery Address</h3>
                      <hr>
                      
                      <div class="col-md-4 fieldset" id="fieldset">
                      <div class="fieldset1" id="fieldset1">
                      <img class="close mith" src="resources/img/close.png" width="50"/>
                      
                       <div class="small-12 medium-3 large-3 columns">
                        <label for="apt-no">APT No.</label>
                        <form:input path="addresses[0].address1" id="apt-no" placeholder="APT No"  class="aptCls"/>
                        <form:hidden path="addresses[0].id" id="addressId" />
                        <form:hidden path="addresses[0].addressPosId" id="addressPosId" />
                      </div>
                      
                      <div class="small-12 medium-9 large-9 columns">
                        <label for="address">Address</label>
                        <form:input path="addresses[0].address2" id="address" placeholder="Address" maxlength="50" class="addressCls" />
                      </div>
                      <div class="clearfix"></div>
                      <hr>
                      <div class="small-12 medium-4 large-4 columns">
                        <label for="city">City</label>
                        <form:input path="addresses[0].city" id="city" placeholder="City" maxlength="20" class="cityCls"/>
                      </div>
                      <div class="small-12 medium-4 large-4 columns">
                        <label for="state">State</label>
                        <form:select path="addresses[0].state" id="state" class="stateCls">
                          <form:option value="select">Select</form:option>
                                  <form:option value="AL">Alabama</form:option>
                                  <form:option value="AK">Alaska</form:option>
                                  <form:option value="AZ">Arizona</form:option>
                                  <form:option value="AR">Arkansas</form:option>
                                  <form:option value="CA">California</form:option>
                                  <form:option value="CO">Colorado</form:option>
                                  <form:option value="CT">Connecticut</form:option>
                                  <form:option value="DE">Delaware</form:option>
                                  <form:option value="FL">Florida</form:option>
                                  <form:option value="GA">Georgia</form:option>
                                  <form:option value="HI">Hawaii</form:option>
                                  <form:option value="ID">Idaho</form:option>
                                  <form:option value="IL">Illinois</form:option>
                                  <form:option value="IN">Indiana</form:option>
                                  <form:option value="IA">Iowa</form:option>
                                  <form:option value="KS">Kansas</form:option>
                                  <form:option value="KY">Kentucky</form:option>
                                  <form:option value="LA">Louisiana</form:option>
                                  <form:option value="ME">Maine</form:option>
                                  <form:option value="MD">Maryland</form:option>
                                  <form:option value="MA">Massachusetts</form:option>
<form:option value="MI">Michigan</form:option>
<form:option value="MN">Minnesota</form:option>
<form:option value="MS">Mississippi</form:option>
<form:option value="MO">Missouri</form:option>
<form:option value="MT">Montana</form:option>
<form:option value="NE">Nebraska</form:option>
<form:option value="NV">Nevada</form:option>
<form:option value="NH">New Hampshire</form:option>
<form:option value="NJ">New Jersey</form:option>
<form:option value="NM">New Mexico</form:option>
<form:option value="NY">New York</form:option>
<form:option value="NC">North Carolina</form:option>
<form:option value="ND">North Dakota</form:option>
<form:option value="OH">Ohio</form:option>
<form:option value="OK">Oklahoma</form:option>
<form:option value="OR">Oregon</form:option>
<form:option value="PA">Pennsylvania</form:option>
<form:option value="RI">Rhode Island</form:option>
<form:option value="SC">South Carolina</form:option>
<form:option value="SD">South Dakota</form:option>
<form:option value="TN">Tennessee</form:option>
<form:option value="TX">Texas</form:option>
<form:option value="UT">Utah</form:option>
<form:option value="VT">Vermont</form:option>
<form:option value="VA">Virginia</form:option>
<form:option value="WA">Washington</form:option>
<form:option value="WV">West Virginia</form:option>
<form:option value="WI">Wisconsin</form:option>
<form:option value="WY">Wyoming</form:option>
</form:select>
                      </div>
                      <div class="small-12 medium-4 large-4 columns">
                        <label for="zip">Zip</label>
                        <form:input path="addresses[0].zip" id="zip" placeholder="Zip" maxlength="5"  class="zipCls" onkeypress="return onlyNos(event,this);" type="number" />
                      </div>
                      <div class="clearfix"></div>
                      <hr>
                      
                      
                     </div>
                     </div>
                      
                      <!-- <div class="small-12 medium-4 large-4 columns large-off-set-8 medium-offset-8">
                        <input type="button" id="addmore-address" value="Add Address"
                          class="success hollow button right btn1">
                      </div> -->
                      <hr>
                    <label id="errorBox1" class="error"></label>
                      <div class="small-12 medium-6 large-6 columns">
                        <label for="profile-photo">Profile Photo</label>
                        <div class="picture-uploader">
                          <span class="profile-upload"> <input type="file" id="profile-photo"
                            onchange="encodeImageFileAsURL();">
                          </span> <span class="profile-dp" id="imgTest"> <!-- <img src="resources/img/user-gravatar.jpg" /> -->
                          </span>
                          <form:hidden path="image" id="imageId" />
                        </div>
                        <!--.picture-uploader-->
                      </div>

                      <div class="small-12 medium-6 large-6 columns">
                        <label for="password">Password</label> <span class="password-field"> <form:password
                            path="password" id="password" maxlength="15" readonly="true" />
                        </span>
                        <div class="clearfix"></div>
                        <section class="ac-container">
                              <div>
                                <input id="ac-1" name="accordion-1" type="checkbox">
                                <label id="showhide"  for="ac-1" class="success hollow button right" style="float:right!important;margin:0!important;">Change Password</label>
                                <article class="ac-small">
                                  <input type="password" id="oldPassword" placeholder="Old Password">
                                  <input type="password" id="newPassword" placeholder="New Password">
                                  <input type="password" id="newPassword1" placeholder="confirm new Password">
                                  <input type="button" id="cancel-changes" value="Cancel" class="error hollow button right" style="margin-right:10px;">
                                </article>
                              </div>
                            </section>
                      </div>
                      <div class="clearfix"></div>
                     <div class="footer sd-footer sd-fake-footer">
                      <hr>
                      <div class="small-12 medium-6 large-6 columns">
                        <label class="subscribers" for="subscribe-check">Subscribe For Updates And Deals</label> 
                        <form:checkbox path="subscribe"  id="subscribe-check" value="true" checked="checked" />
                      </div>
                      <div class="small-12 medium-6 large-6 columns">
                        <input type="button" id="save-changes" value="Save" class="success hollow button right"
                          onclick="updateCustomer()">
                      </div>
                      <hr>
                      </div><!--.footer .sd-footer .sd-fake-footer-->
                    </form:form>
                  </div>
                  <!-----#sd-form-container------>
                  <div id="footer">
                    <div class="footer-container-outbound">
                      <div class="footer-container-inbound">
                        <div class="small-12 medium-12 large-12 columns">
                          <div class="copyrights">
                            <ul>
                              <li>Powered By <a href="http://foodkonnekt.com/" target="_blank">FoodKonnekt</a></li>
                              <li><a href="http://foodkonnekt.com/" target="_blank"><img
                                  src="resources/img/powered-by-foodkonnekt-logo.png"></a></li>
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
<!--HIDING CHANGE PASSWORD BUTTON STARTS-->
    <script type="text/javascript">
      $(document).ready(function(){
        $("#showhide").click(function(){
          $(".ac-small").show();
          $(".ac-small").css("height","210px");
          $("#showhide").hide(); 
      });
        $("#cancel-changes").click(function(){
            $(".ac-small").hide();
            $("#showhide").show();
        });
    });
      
      $(document).on("keyup", ".call_caller_phone", function() {
          var val = this.value.replace(/\D/g,'');
          var newVal = '';
          while (val.length > 3) {
          if (val.length == 10) {
          newVal += val.substr(0, 4);
          return false;
          } else {
          newVal += val.substr(0, 3) + '-';
          val = val.substr(3);
          }
          }
          newVal += val;
          this.value = newVal;
        });
      
    </script>
  <style>
*{    font-family: 'Open Sans', sans-serif;}
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
.btn.btn-default {}
.btn.btn-default:hover {background:#eee;border-color:#bbb}
.btn.btn-default:focus {background:#ddd;border-color:#bbb}
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
.btn.btn-default[disabled] {background:#fafafa!important;border-color:#ccc!important;color:#aaa}
.btn.btn-primary[disabled] {background:#3F9DD0!important;border-color:#537FA9!important;color:#ACD3E8;box-shadow:none!important}
.btn.btn-left {float:left;margin:0 5px 0 0!important}
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
  <!--HIDING CHANGE PASSWORD BUTTON ENDS-->
