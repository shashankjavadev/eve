  <%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
  <!DOCTYPE html>
  <html>
    <head>
	
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>mKonnekt Dashboard</title>
		<!--Import Google Icon Font-->
		<link href="http://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
		<!--Import materialize.css-->
		<link type="text/css" rel="stylesheet" href="resources/mkonnekt/css/font-awesome.min.css"/>
		<link type="text/css" rel="stylesheet" href="resources/mkonnekt/css/materialize.min.css"  media="screen,projection"/>
		<!-- Bootstrap -->
		<link href="resources/mkonnekt/css/bootstrap.min.css" rel="stylesheet">
		<!--Datepicker -->
		<link href="resources/mkonnekt/css/daterangepicker.css" rel="stylesheet">
		<link href="resources/mkonnekt/css/style.css" rel="stylesheet">
    </head>

    <body class="login-body">
    <main>
    	<div class="text-center login-logo">
    		<img src="resources/img/logo.png" height="70" />
    	</div>
    	<div class="login-form">
			<div class="avtar text-center">
				<h3>Change Password</h3>
				<span style="color: red;" id="errorBox"></span><br>
			</div>
					
					<form:form method="POST" action="resetMerchantPassword" modelAttribute="MerchantLogin" id="setAdminLoginForm" autocomplete="off" >
						<form:hidden path="emailId"/>
						<div class="input-field">
				          <!-- <input id="newpassword" type="password" class="validate" minlength="8" maxlength="50"  /> -->
				           <form:password  id="pass" placeholder="Password" path="password" class="validate" minlength="8" maxlength="16" required='required'/>
				          <label for="newpassword">Enter New Password</label>
				        </div>

            <div class="input-field">
                  <input id="confirmpass" type="password" class="validate" minlength="8" maxlength="16" required='required'>
                  <label for="confirmpass">Confirm Password</label>
                </div>
            <div class="row nomargin">
              <div class="signin text-center">
                <button type="submit" class="waves-effect waves-light btn white-text" onclick="return checkPassword()">Reset</button>
              </div>
            </div>
					</form:form>
		</div>
    </main>
    <footer>
    	<div class="row nomargin">

          <div class="footer-copyright">
            <div class="col-sm-6">
            Â© 2016 mKonnekt
            </div>
              <div class="text-right col-sm-6">
              		<a class="grey-text text-lighten-3" href="#!">About</a>
              		&nbsp;&nbsp;|&nbsp;&nbsp;
              		<a class="grey-text text-lighten-3" href="#!">Privacy Policy</a>
                    &nbsp;&nbsp;|&nbsp;&nbsp;
                    <a class="grey-text text-lighten-3" href="#!">Terms of Services</a>
                    &nbsp;&nbsp;|&nbsp;&nbsp;
                  	<a class="grey-text text-lighten-3" href="#!">Contact</a>
                </ul>
              </div>
            </div>
          </div>
    </footer>
	
	
    <!--Import jQuery before materialize.js-->
    <script type="text/javascript" src="resources/mkonnekt/js/jquery-2.1.1.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="resources/mkonnekt/js/bootstrap.min.js"></script>
	<!-- JS for Datepicker -->
	<script type="text/javascript" src="resources/mkonnekt/js/moment.min.js"></script>
	<script type="text/javascript" src="resources/mkonnekt/js/daterangepicker.js"></script>
	
    <script type="text/javascript" src="resources/mkonnekt/js/materialize.min.js"></script>
	<script>
	function checkPassword() {
		
		var password = $("#pass").val();
		var confrimPassword = $("#confirmpass").val();
		if ($("#pass").val() == "") {
		    $("#pass").focus();
		    $("#errorBox").html("enter the Password");
		    return false;
		  }else if ($("#pass").val() != "" && password.length < 8) {
			$("#pass").focus();
			$("#errorBox").html("Password should be a minimum of 8 characters");
		    return false;
		  }
		if ($("#confirmpass").val() == "") {
		    $("#confirmpass").focus();
		    $("#errorBox").html("enter the confirm password");
		    return false;
		}else if (password!=confrimPassword) {
				$("#errorBox").html("Password and confirm password should be same");
			    return false;
		}
	}
	$(document).ready(function() {
		
		
		$('select').material_select();
		$(function() {

var start = moment().subtract(29, 'days');
var end = moment();

function cb(start, end) {
    $('#reportrange span').html(start.format('MMMM D, YYYY') + ' - ' + end.format('MMMM D, YYYY'));
}

$('input[name="daterange"]').daterangepicker({
    startDate: start,
    endDate: end,
	applyClass: "btn-flat",
	cancelClass: "btn-flat",
    ranges: {
       'Today': [moment(), moment()],
       'Yesterday': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
       'Last 7 Days': [moment().subtract(6, 'days'), moment()],
       'Last 30 Days': [moment().subtract(29, 'days'), moment()],
       'This Month': [moment().startOf('month'), moment().endOf('month')],
       'Last Month': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]
    }
}, cb);

cb(start, end);

});
	  });
	</script>
    </body>
  </html>