<script type="text/javascript" src="https://ecommerce.merchantware.net/v1/CayanCheckout.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js" type="text/javascript"></script>
<script type="text/javascript">
   CayanCheckout.setWebApiKey("5AJUMKNKI8Q0ZKEN");
   $(document).ready(function () {
    $('#submitPayment').click(function () {

        // Prevent the user from double-clicking
        //$(this).prop('disabled', true);

        // Create the payment tokencreate
        CayanCheckout.createPaymentToken({
            success: successCallback,
            error: failureCallback
        })

    });
	
	
});

function successCallback(tokenResponse) {
    // Populate a hidden field with the single-use token
	
	alert(tokenResponse.token);
    $("input[name='TokenHolder'").val(tokenResponse.token);
 
    // Submit the form
    $('#paymentForm').submit();
}

function failureCallback(tokenResponse) {
    // Populate a hidden field with the single-use token
	var errors=tokenResponse;
    var arrayLength = tokenResponse.length;
	alert(arrayLength);
	for (var i = 0; i < arrayLength; i++) {
    alert(tokenResponse[i].error_code +" , " + tokenResponse[i].reason);
    //Do something
}
	
}
</script>
<form method="post" id="paymentForm" action="cayanpayment">
    <div>
        <span id="error_message"></span>
    </div>
    <div>
        <label for="formcardnumber">Card Number</label>
        <input type="text" size="20" name="formcardnumber" data-cayan="cardnumber">
    </div>
    <div>
        <label for="formcvv">CVV</label>
        <input type="text" size="4" name="formcvv" data-cayan="cvv">
    </div>
    <div>
        <label for="formexpiremonth">Expiration Month</label>
        <input type="text" size="2" name="formexpiremonth" data-cayan="expirationmonth" title="MM">
    </div>
    <div>
        <label for="form-expireyear">Expiration Year</label>
        <input type="text" size="2" name="formexpireyear" data-cayan="expirationyear" title="YY">
    </div>
    <div>
        <input type="hidden" name="TokenHolder" value="">
        <button type="button" id="submitPayment">Submit Payment</button>
    </div>
</form>