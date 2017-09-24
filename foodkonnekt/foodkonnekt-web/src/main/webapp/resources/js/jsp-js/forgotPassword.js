function checkEmail() {
  var emailRegex = /^[A-Za-z0-9._]*\@[A-Za-z]*\.[A-Za-z]{2,5}$/;
  var email = $("#email").val();
  if ($("#email").val() == "") {
    $("#email").focus();
    $("#errorBox").html("enter the email");
    return false;
  } else if (!emailRegex.test(email)) {
    $("#email").focus();
    $("#errorBox").html("Enter a valid email id");
    return false;
  }
}


function checkPassword() {
	var password = $("#password").val();
	var confrimPassword = $("#confirmPassword").val();
	if ($("#password").val() == "") {
	    $("#password").focus();
	    $("#errorBox").html("enter the Password");
	    return false;
	  }else if ($("#password").val() != "" && password.length < 8) {
		$("#password").focus();
		$("#errorBox").html("Password should be a minimum of 8 characters");
	    return false;
	  }
	if ($("#confirmPassword").val() == "") {
	    $("#confirmPassword").focus();
	    $("#errorBox").html("enter the confirm password");
	    return false;
	}else if (password!=confrimPassword) {
			$("#errorBox").html("Password and confirm password should be same");
		    return false;
	}
}