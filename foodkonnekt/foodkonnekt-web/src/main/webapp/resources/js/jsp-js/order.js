$(document).ready(function() {
  
      $(".selMod").val("Credit Card");
      $(".selMod").change(function() {
        var paymentMod = this.value;
        if (paymentMod == 'Cash') {
          $(".comLi").hide();
        } else {
          $(".comLi").show();
        }
      });
      
      $(".chkAgree").click(function() {
            if ($("#terms-and-conditions").prop('checked') == true) {
              var ordId;
              $("#agreeMsg").html(" ");
              var paymentMethod = $(".selMod").val();
              
              if (paymentMethod == 'Credit Card') {
                var cardNumber = $("#payment-method-cc-number").val();
                var expirationDate = $("#expirationDate").val();
                var ccCode = $("#payment-method-cc-code").val();
                
                var cardType = $("#payment-method-cc-type").val();
                
                if (cardNumber == "") {
                  $("#payment-method-cc-number").focus();
                  $("#agreeMsg").html("Please enter card number");
                  return false;
                }else if (cardType=="American Express" && cardNumber.length!=15) {
                  $("#agreeMsg").html("Card number should be 15 characters");
                   return false
                }else if ((cardType=="Master Card" || cardType=="Visa") && (cardNumber.length!=16)) {
                  $("#agreeMsg").html("Card number should be 16 characters");
                   return false
                } else if (expirationDate == "") {
                  $("#expirationDate").focus();
                  $("#agreeMsg").html("Please enter expiration date");
                  return false;
                } else if (ccCode == "") {
                  $("#payment-method-cc-code").focus();
                  $("#agreeMsg").html("Please enter cc code");
                  return false;
                }else if (cardType=="American Express" && ccCode.length!=4) {
                $("#agreeMsg").html("CC code should be 4 characters");
                 return false
                }else if ((cardType=="Master Card" || cardType=="Visa") && (ccCode.length!=3)) {
                $("#agreeMsg").html("CC code should be 3 characters");
                 return false
                }
              }
              /* START call ajax for send order data to controller */
              var orderJson = $('#finalJson').val();
              var orderTotal = $('#total').val();
              $("#orderMasssage").html("Please wait your order is procssing...");
              $('.pj-loader-2').css('display','block');
              var instruction = document.getElementById("special-instructions").value;
              $.ajax({
                url : "placeOrder?orderJson=" + orderJson + "&orderTotal=" + orderTotal
                    + "&instruction=" + instruction,
                type : "GET",
                contentType : "application/json; charset=utf-8",
                success : function(statusValue) {
                  if (statusValue != null) {
                    ordId = statusValue;
                    $("#orderPosId").val(statusValue);
                    $("#ordPosId").val(statusValue);
                    callPayment();
                  }
                },
                error : function() {
                  alert("error");
                }
              })
              /* END */
            } else {
              $("#agreeMsg").html("You need to agree with terms and conditions.");
              return false;
            }
          });
      
     function callPayment() {
        var to = $("#total").val();
        var seds = $("#total").val();
        $("#ordrTotalAmount").val(seds);
        var sc = $("#ordrTotalAmount").val();
        document.myform.action = "orderPayment";
        document.myform.submit();
      }
      // called when key is pressed in textbox
      $(".quantity").keypress(function(e) {
        if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
          $("#errmsg").html("Digits Only").show().fadeOut("slow");
          return false;
        }
      });
      
      //attach keypress to input
      $('.numberInput').keydown(function(event) {
          // Allow special chars + arrows 
          if (event.keyCode == 46 || event.keyCode == 8 || event.keyCode == 9 
              || event.keyCode == 27 || event.keyCode == 13 
              || (event.keyCode == 65 && event.ctrlKey === true) 
              || (event.keyCode >= 35 && event.keyCode <= 39)){
                  return;
          }else {
              // If it's not a number stop the keypress
              if (event.shiftKey || (event.keyCode < 48 || event.keyCode > 57) && (event.keyCode < 96 || event.keyCode > 105 )) {
                  event.preventDefault(); 
              }   
          }
      });
      
      
    });

