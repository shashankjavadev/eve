function updateCustomer() {
  var emailRegex = /^[A-Za-z0-9._]*\@[A-Za-z]*\.[A-Za-z]{2,5}$/;
  var phoneRegex = /\(?([0-9]{3})\)?([ .-]?)([0-9]{3})\2([0-9]{4})/;
  var firstName = $("#first-name").val();
  var lastName = $("#second-name").val();
  var email = $("#email").val();
  var mobile = $("#mobile").val();
  var password = $("#password").val();
  var imgVal = $('#profile-photo').val();
  var oFile = $('#profile-photo')[0].files[0];
  var file_size;
  if(typeof oFile != 'undefined')
    {
    file_size = $('#profile-photo')[0].files[0].size;
    }
  var rfilter = /^(image\/jpeg|image\/png)$/i;
  var email = $("#email").val();
  if ($("#first-name").val() == "") {
    $("#first-name").focus();
    $("#errorBox").html("enter the First Name");
    return false;
  } /*else if ($("#second-name").val() == "") {
    $("#second-name").focus();
    $("#errorBox").html("enter the last Name");
    return false;
  }*/ else if (/^[a-zA-Z0-9- ]*$/.test(firstName) == false) {
    $("#first-name").focus();
    $("#errorBox").html("Your first name Contains illegal Characters");
    return false;
  } else if (/^[a-zA-Z0-9- ]*$/.test(lastName) == false) {
    $("#second-name").focus();
    $("#errorBox").html("Your last name Contains illegal Characters");
    return false;
  } else if (password.length < 8) {
    $("#password").focus();
    $("#errorBox").html("Password should be minimum 8 character");
    return false;
  } else if ($("#email").val() == "") {
    $("#email").focus();
    $("#errorBox").html("enter the email");
    return false;
  } else if (!emailRegex.test(email)) {
    $("#email").focus();
    $("#errorBox").html("Enter a valid email id");
    return false;
  } else if ($('#mobile').val() == "") {
    $("#mobile").focus();
    $("#errorBox").html("enter phone number");
    return false;
  } else if (mobile.length>12 || mobile.length<12) {
	$("#mobile").focus();
	$("#errorBox").html("enter valid phone number");
	return false;
 } /*else if (/^\d{10}$/.test(mobile)==false) {
    $("#mobile").focus();
    $("#errorBox").html("enter valid phone number");
    return false;*/
/*  } else if (imgVal == '') {
    $("#errorBox").css("display", "block");
    $("#errorBox").html("Please select image ");
    return false;*/
  /*}*/ else if ((imgVal != '')&&(!rfilter.test(oFile.type))) {
    $('#errorBox').html('Please select a valid image file (jpg and png are allowed)').show();
    return false;
  } else if (file_size > 2097152) {
    $("#errorBox").css("display", "block");
    $("#errorBox").html("Please select image upto 2 MB");
    return false;
  } else if ($(firstName != '' && lastName != '' && email != '' && mobile != '' && password != '' && email != '')) {
    var oldPass = $("#oldPassword").val();
    var newPass = $("#newPassword").val();
    var newPass1 = $("#newPassword1").val();
    if (oldPass != '' && newPass != '' && newPass1 != '') {
      if (newPass == newPass1) {
        $(document).ready(function() {
          $.ajax({
            url : "checkOldPassword",
            type : 'POST',
            dataType : 'json',
            data : "{\"password\":\"" + oldPass + "\"}",
            contentType : 'application/json',
            mimeType : 'application/json',
            success : function(data) {
              if (data.success == "samePassword") {
                $("#password").val(newPass);
                $("#errorBox").html("form submitted successfully")
                document.myform.action = "updateCustomerProfile", document.myform.submit()
              } else {
                jQuery(function(){
			         jQuery('#passwordIdBtn').click();
			     });
                return false;
              }
            },
            error : function(data, status, er) {
            }
          });
        });
      } else {
        $("#errorBox1").html("Password and confirm password should be same");
        return false;
      }

    } else {
      //$("#errorBox").html("form submitted successfully")
    	var firstAddress="";
    	var aprtNumber="";
    	var cityChk="";
    	var statChk="";
    	var zipChk="";
    	$(".addressCls").each(function() {
    	    firstAddress=firstAddress+","+$(this).val();
    	});
    	$(".aptCls").each(function() {
    		aprtNumber=aprtNumber+","+$(this).val();
    	});
    	$(".cityCls").each(function() {
    		cityChk=cityChk+","+$(this).val();
    	});
    	$(".stateCls").each(function() {
    		statChk=statChk+","+$(this).val();
    	});
    	$(".zipCls").each(function() {
    		zipChk=zipChk+","+$(this).val();
    	});
    	$.ajax({
            url : "checkAddressValidity?aptNumber=" + aprtNumber + "&firstAddress=" + firstAddress
                + "&city=" + cityChk+"&state="+statChk+"&zip="+zipChk,
            type : "GET",
            contentType : "application/json; charset=utf-8",
            success : function(statusValue) {
              if(statusValue.statusM==1){
            	  $("#delMessage").html(statusValue.message)
            	  jQuery(function(){
            	      jQuery('#deliveryFailurePopUp').click();
            	  });
              }else{
            	  document.myform.action = "updateCustomerProfile", document.myform.submit()
              }
            },
            error : function() {
            }
          })
      //document.myform.action = "updateCustomerProfile", document.myform.submit()
    }
  }
}

function encodeImageFileAsURL() {
  var filesSelected = document.getElementById("profile-photo").files;
  if (filesSelected.length > 0) {
    var fileToLoad = filesSelected[0];

    var fileReader = new FileReader();
    fileReader.onload = function(fileLoadedEvent) {
      var srcData = fileLoadedEvent.target.result; // <--- data: base64
      var newImage = document.createElement('img');
      newImage.src = srcData;
      document.getElementById("imgTest").innerHTML = newImage.outerHTML;
      document.getElementById("imageId").value = fileLoadedEvent.target.result;
      document.getElementById("headProfileId").innerHTML = newImage.outerHTML;
    }
    fileReader.readAsDataURL(fileToLoad);
  }
}
/*$(document).ready(function() {
$('#mobile', '#myform').keydown(function (e) {
	  var key = e.charCode || e.keyCode || 0;
	  $phone = $(this);

	  // Auto-format- do not expose the mask as the user begins to type
	  if (key !== 8 && key !== 9) {
	    if ($phone.val().length === 3) {
	        $phone.val($phone.val() + '-');
	    }
	    if ($phone.val().length === 7) {
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


});*/
function onlyNos(e, t) {
    try {
        if (window.event) {
            var charCode = window.event.keyCode;
        }
        else if (e) {
            var charCode = e.which;
        }
        else { return true; }
        if (charCode > 31 && (charCode < 48 || charCode > 57)) {
            return false;
        }
        return true;
    }
    catch (err) {
    }
}

$(document).ready( function (){
  $('#previousAddress').change(function(){
	 var address=$(this).val();
	 var addressArray=address.split("#");
	 var address1=addressArray[0];
	 var address2=addressArray[1];
	 var city=addressArray[2];
	 var state=addressArray[3];
	 var zip=addressArray[4];
	 var addressId=addressArray[5];
	 var addressPosId=addressArray[6];
	 $("#apt-no").val(address1);
	 $("#address").val(address2);
	 $("#city").val(city);
	 $("#state").val(state);
	 $("#zip").val(zip);
	 $("#addressId").val(addressId);
	 $("#addressPosId").val(addressPosId);
  });
});
