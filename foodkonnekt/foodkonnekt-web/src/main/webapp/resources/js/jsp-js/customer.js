var addresses = [];
var deliv = 0;
var pick = 0;
$(function() {
  var curLesson = 1;
  $('div.paginationClass:not(:first)').hide();

});
function deliveryDec() {
  $('#lesson_1').hide();
  $('#lesson_2').show();
  $("#pickUpId").hide();
  $("#deliveryId").show();
  $("#orderType").val("Delivery");
  deliv = 1;
  pick = 0;
}
function pickup() {
  $('#lesson_1').hide();
  $('#lesson_2').show();
  $("#pickUpId").show();
  $("#deliveryId").hide();
  $("#orderType").val("Pickup");
  deliv = 0;
  pick = 1;
}
function checkDelivery() {
  var emailRegex = /^[A-Za-z0-9._]*\@[A-Za-z]*\.[A-Za-z]{2,5}$/;
  var phoneRegex = /\(?([0-9]{3})\)?([ .-]?)([0-9]{3})\2([0-9]{4})/;
  var email = $("#email-register").val();
  var name = $("#name-register").val();
  var phone = $("#phone-register").val();
  var password = $("#password-register").val();
  if ($("#name-register").val() == "") {
    $("#name-register").focus();
    $("#errorBox").html("enter the First Name");
    return false;
  } else if (/^[a-zA-Z0-9- ]*$/.test(name) == false) {
    $("#name-register").focus();
    $("#errorBox").html("Your name Contains illegal Characters");
    return false;
  } else if (name.length < 4) {
    $("#name-register").focus();
    $("#errorBox").html("Name should be a minimum of 4 characters");
    return false;
  } else if ($("#email-register").val() == "") {
    $("#email-register").focus();
    $("#errorBox").html("enter the email");
    return false;
  } else if (!emailRegex.test(email)) {
    $("#email-register").focus();
    $("#errorBox").html("Enter a valid email id");
    return false;
  } else if ($("#password-register").val() == "") {
    $("#password-register").focus();
    $("#errorBox").html("enter the password");
    return false;
  } else if (password.length < 8) {
    $("#password-register").focus();
    $("#errorBox").html("Password should be a minimum of 8 characters");
    return false;
  } else if ($('#phone-register').val() == "") {
    $("#phone-register").focus();
    $("#errorBox").html("enter phone number");
    return false;
  } else if ($(name != '' && email != '' && phone != '' && password != '')) {
    $(document).ready(
        function() {
          $.ajax({
            url : "checkDuplicateEmailId",
            type : 'POST',
            dataType : 'json',
            data : "{\"emailId\":\"" + email + "\"}",
            contentType : 'application/json',
            mimeType : 'application/json',
            success : function(data) {
              var msg = data.message;
              if (msg == "true") {
                $("#errorBox").html("EmailId allready exist , please login");
                return false;
              } else {
                $('#lesson_1').hide();
                $('#lesson_2').hide();
                $('#lesson_3').show();
                document.getElementById("firstName").value = document
                    .getElementById("name-register").value;
                document.getElementById("emailId").value = document
                    .getElementById("email-register").value;
                document.getElementById("phoneNumber").value = document
                    .getElementById("phone-register").value;
                document.getElementById("passwordSignUp").value = document
                    .getElementById("password-register").value;
              }
            },
            error : function(data, status, er) {
              alert("error: " + data + " status: " + status + " er:" + er);
            }
          });
        });
  }
}
function saveCustomer() {
  var emailRegex = /^[A-Za-z0-9._]*\@[A-Za-z]*\.[A-Za-z]{2,5}$/;
  var phoneRegex = /\(?([0-9]{3})\)?([ .-]?)([0-9]{3})\2([0-9]{4})/;
  var email = $("#email-register").val();
  var name = $("#name-register").val();
  var phone = $("#phone-register").val();
  var password = $("#password-register").val();
  if ($("#name-register").val() == "") {
    $("#name-register").focus();
    $("#errorBox").html("enter the First Name");
    return false;
  } else if (/^[a-zA-Z0-9- ]*$/.test(name) == false) {
    $("#name-register").focus();
    $("#errorBox").html("Your name Contains illegal Characters");
    return false;
  } else if (name.length < 4) {
    $("#name-register").focus();
    $("#errorBox").html("Name should be a minimum of 4 characters");
    return false;
  } else if ($("#email-register").val() == "") {
    $("#email-register").focus();
    $("#errorBox").html("Enter a valid email id");
    return false;
  } else if (!emailRegex.test(email)) {
    $("#email-register").focus();
    $("#errorBox").html("Enter a valid email id");
    return false;
  } else if ($("#password-register").val() == "") {
    $("#password-register").focus();
    $("#errorBox").html("enter the password");
    return false;
  } else if (password.length < 8) {
    $("#password-register").focus();
    $("#errorBox").html("Password should be a minimum of 8 characters");
    return false;
  } else if ($('#phone-register').val() == "") {
    $("#phone-register").focus();
    $("#errorBox").html("enter phone number");
    return false;
  } else if ($(name != '' && email != '' && phone != '' && password != '')) {
    $(document).ready(function() {
      $.ajax({
        url : "checkDuplicateEmailId",
        type : 'POST',
        dataType : 'json',
        data : "{\"emailId\":\"" + email + "\"}",
        contentType : 'application/json',
        mimeType : 'application/json',
        success : function(data) {
          var msg = data.message;
          if (msg == "true") {
            $("#errorBox").html("EmailId allready exist , please login");
            return false;
          } else {
            $("#errorBox").html("")
            document.myform.action = "customerSignUp", document.myform.submit()
          }
        },
        error : function(data, status, er) {
          alert("error: " + data + " status: " + status + " er:" + er);
        }
      });
    });
  }
}
function finalSave() {
  var address1 = $("#step-three-street-address").val();
  var address2 = $("#step-three-street-address-two").val();
  var city = $("#step-three-city").val();
  var state = $("#step-three-select-state").val();
  var zip = $("#step-three-zip").val();
  if ($("#step-three-street-address").val() == "") {
    $("#step-three-street-address").focus();
    $("#errorBox2").html("enter the First address");
    return false;
  } else if ($("#step-three-city").val() == "") {
    $("#step-three-city").focus();
    $("#errorBox2").html("enter the city");
    return false;
  } else if ($('#step-three-select-state').val() == "select") {
    $("#step-three-select-state").focus();
    $("#errorBox2").html("select state name");
    return false;
  } else if ($("#step-three-zip").val() == "") {
    $("#step-three-zip").focus();
    $("#errorBox2").html("enter zip code");
    return false;
  } else if ($(address1 != '' && city != '' && state != 'select' && zip != '')) {
    var checkAdd = $("#add-address").val();

    // var addressCheck=validateAddress();

    if (checkAdd == 0) {
      $(document).ready(
          function() {
            $.ajax({
              url : "checkDeliveryZone",
              type : 'POST',
              dataType : 'json',
              data : "{\"address1\":\"" + address1 + "\",\"address2\":\"" + address2
                  + "\",\"city\":\"" + city + "\",\"state\":\"" + state + "\",\"zip\":\"" + zip
                  + "\"}",
              contentType : 'application/json',
              mimeType : 'application/json',
              success : function(data) {
                if (data.message == "Your zone is in delivery zone") {
                  alert(data.message)
                  if ($("#customerId").val() != '' && $("#addressId").val() != '') {
                    document.finalForm.action = "signIn", document.finalForm.submit()
                  } else {
                    document.finalForm.action = "customerSignUp", document.finalForm.submit()
                  }
                } else {
                  alert(data.message)
                    $("#step-three-street-address").val("");
                    $("#step-three-street-address-two").val("");
                    $("#step-three-city").val("");
                    $('#step-three-select-state').val("select");
                    $("#step-three-zip").val("")
                  return false;
                }
              },
              error : function(data, status, er) {
                alert("error: " + data + " status: " + status + " er:" + er);
              }
            });
          });
    } else {
      if ($("#customerId").val() != '' && $("#addressId").val() != '') {
        document.finalForm.action = "signIn", document.finalForm.submit()
      } else {
        document.finalForm.action = "customerSignUp", document.finalForm.submit()
      }
    }
  }
}

function checkDuplicateEmail() {
  var email = $("#email-register").val();
  $.ajax({
    url : "checkDuplicateEmailId",
    type : 'POST',
    dataType : 'json',
    data : "{\"emailId\":\"" + email + "\"}",
    contentType : 'application/json',
    mimeType : 'application/json',
    success : function(data) {
      var msg = data.message;
      return msg;
    },
    error : function(data, status, er) {
      alert("error: " + data + " status: " + status + " er:" + er);
    }
  });
}

function customerSignIn() {
  var emailRegex = /^[A-Za-z0-9._]*\@[A-Za-z]*\.[A-Za-z]{2,5}$/;
  var email = $("#email").val();
  var paasword = $("#password").val();
  if ($("#email").val() == "") {
    $("#email").focus();
    $("#signInErrorBox").html("enter the email");
    return false;
  } else if (!emailRegex.test(email)) {
    $("#email").focus();
    $("#signInErrorBox").html("Enter a valid email id");
    return false;
  } else if ($('#password').val() == "") {
    $("#password").focus();
    $("#signInErrorBox").html("enter password");
    return false;
  } else if ($(email != '' && paasword != '')) {
    $(document).ready(
        function() {
          var emailId = $('#email').val();
          var password = $('#password').val();
          var orderType = $("#orderType").val()
          $.ajax({
            url : "customerSignIn",
            type : 'POST',
            dataType : 'json',
            data : "{\"emailId\":\"" + emailId + "\",\"vendorId\":\"" + $("#vendorId1").val()
                + "\",\"password\":\"" + password + "\",\"orderType\":\"" + orderType + "\"}",
            contentType : 'application/json',
            mimeType : 'application/json',
            success : function(data) {
              if (data.success == "Login successfully") {
                $("#customerId").val(data.custId);
                if (deliv == 1) {
                  $('#lesson_1').hide();
                  $('#lesson_2').hide();
                  $('#lesson_3').show();
                  addresses = data.address;
                  /* $('<option value=0>Add New Address</option>').appendTo('#add-address'); */
                  for ( var field in addresses) {
                    $(
                        '<option value="' + addresses[field].id + '">' + addresses[field].address1
                            + " " + addresses[field].address2 + '</option>').appendTo(
                        '#add-address');
                  }
                } else {
                  document.myform.action = "signIn", document.myform.submit()
                }
              } else {
                $("#signInErrorBox").html("Invalid login credential");
              }
            },
            error : function(data, status, er) {
              alert("error: " + data + " status: " + status + " er:" + er);
            }
          });
        });
  }
}
$(document).ready(function() {
  $('#add-address').on('change', function() {
    var addrId = this.value;
    for ( var field in addresses) {
      if (addresses[field].id == addrId) {
        $("#addressId").val(addresses[field].id)
        $("#step-three-street-address").val(addresses[field].address1);
        $("#step-three-street-address-two").val(addresses[field].address2);
        $("#step-three-city").val(addresses[field].city);
        $('#step-three-select-state').val(addresses[field].state);
        $("#step-three-zip").val(addresses[field].zip)
      }
    }
    if (addrId == 0) {
      $("#step-three-street-address").val("");
      $("#step-three-street-address-two").val("");
      $("#step-three-city").val("");
      $('#step-three-select-state').val("");
      $("#step-three-zip").val("")
    }
  });
});
$(document).ready(function() {
$('#phone-register', '#myform').keydown(function (e) {
  var key = e.charCode || e.keyCode || 0;
  $phone = $(this);

  // Auto-format- do not expose the mask as the user begins to type
  if (key !== 8 && key !== 9) {
    if ($phone.val().length === 3) {
      $phone.val($phone.val() + '-');
    }
    if ($phone.val().length === 4) {
      $phone.val($phone.val() + ' ');
    }     
    if ($phone.val().length === 8) {
      $phone.val($phone.val() + '-');
    }
  }

  // Allow numeric (and tab, backspace, delete) keys only
  return (key == 8 || 
      key == 9 ||
      key == 46 ||
      (key >= 48 && key <= 57) ||
      (key >= 96 && key <= 105)); 
})

.bind('focus click', function () {
  $phone = $(this);
  
  if ($phone.val().length === 0) {
    $phone.val('');
  }
  else {
    var val = $phone.val();
    $phone.val('').val(val); // Ensure cursor remains at the end
  }
})

.blur(function () {
  $phone = $(this);
  
  if ($phone.val() === '-') {
    $phone.val('');
  }
});
});