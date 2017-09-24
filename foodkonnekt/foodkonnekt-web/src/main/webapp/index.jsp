

<!--CALLING FONT AWESOME-->

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>

<script>
var qrsrng;
    $(document).ready( function() {
                var Cloverurl = window.location.href;

                var queryString = Cloverurl.split("?");
                var url=queryString[0];
                 qrsrng = queryString[1];
               

            });
    
    function onclickfn(){
    	//var url ='http://localhost:8080/foodkonnekt-web/cfeedbackForm?'+qrsrng;
    	var url='http://34.229.94.111:8080/web/cfeedbackForm?'+qrsrng;
    	//var url='https://www.foodkonnekt.com/foodkonnekt-web/cfeedbackForm?'+qrsrng;


    	window.open(url, "_blank");
    	}
    
</script>
<style>
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

.btn.btn-primary {
    background-color: #f8981d;
    border-color: #f8981d;
    color: #fff;
}
</style>
</head>
<div ><input type='button' name='Share Feedback' class="btn btn-primary" value='Share Feedback' id='shareButton' onclick="onclickfn()" style="margin-left: 18%;"/></div>
<div style="margin-left: 13%;margin: 7%;"><img src='http://www.dev.foodkonnekt.com/app/foodnew.jpg' alt='' width='200' height='63' /></div>

