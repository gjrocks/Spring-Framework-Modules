<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>User-service-ui</title>

    <!-- Bootstrap -->
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
   

    <link href="${pageContext.request.contextPath}/css/gj.css" rel="stylesheet">
     <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
      <link rel="stylesheet" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css">

  </head>
  <body>
   

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
     
    <script>
   
    $( function() {
    //  $( "#dob" ).datepicker();
  $('#getallusers').on('click', function(event) {
           
	  getUsers();
           });
    
    //  var dataSet=${data};
    
    	/*  $('#example').DataTable( {
    	        "data": dataSet,
    	        "columns": [
    	            { "data": "fname" },
    	            { "data": "lname" },
    	            { "data": "address" },
    	            { "data": "userid" },
    	            { "data": "mobileNumber" },
    	            { "data": "email" }
    	        ]
    	    } ); */
    } );
    
    function showTable(dataSet){
    	$('#example').DataTable( {
	        "data": dataSet,
	        "columns": [
	            { "data": "fname" },
	            { "data": "lname" },
	            { "data": "address" },
	            { "data": "userid" },
	            { "data": "mobileNumber" },
	            { "data": "email" }
	        ]
	    } );
    	
    }
    
    function getUsers() {
    	
    	
    	/* accountN=$.trim(accountN);
    	var fullName = $('#fullName').val();
        var cust=$('#cust').val();
        
        var dlnum=$('#dl').val(); */
        /* var client =    {

                "fullName" : fullName,

                "accountId" :accountN,

                "bankId" :"obp-bankx-n",

                "customerNumber" : cust,
                "dob" : "1980-05-05",
                "drivingLicence" :dlnum

    } */
       // alert(chatMessage.text);
        var request = $.ajax({
            url: 'getuser',
            type: 'POST',
           // data: JSON.stringify(client),
            contentType: "application/json; charset=utf-8",
            traditional: true,
            dataType: 'json'
          });

          request.done(function(msg) {
           
               console.log("msg :" +msg);
              // var obj = jQuery.parseJSON( msg );
             //  $('#openresult').show();
             //alert(obj);
            
               showTable(msg);
             
          });

          request.fail(function(jqXHR, textStatus) {
            alert( "Request failed: " + textStatus );
          });
        
       
    }
    </script>
    
<div class="container-fluid">
 
<div class="row" id="row1">
<!--   <div class="col-md-2" id="col1"><img src="logo..gif"></div> -->
  <div class="col-md-12" id="col2"><div id="menus">
 <nav>
  <ul>
    
    <li><a href="#home">Home</a></li>
    <li><a href="#register">Register</a></li>
    <li><a href="#messages">Messages</a></li>
    <li><a href="#contact">Contact-Us</a></li>

  </ul>
 </nav>

 </div></div>
  <!--  <div class="col-md-3" id="col3">.col-mid-3</div> -->
</div>
<hr>
 <label for="d">The get user button click will bring the user details from user details service.</label>
<hr>

<div class="row" id="row2">
  <div class="col-md-2" id="col1"></div>
  <div class="col-md-4" id="col2"><!-- Standard button -->

<form method="POST" action="/getuser">
  <!-- <div class="form-group">
    <label for="UserId">User id</label>
    <input type="text" class="form-control" id="userId" name="userId" placeholder="User id..">
  </div> -->
<!-- 
  <div class="form-group">
    <label for="exampleInputEmail1">Email address</label>
    <input type="email" class="form-control" id="exampleInputEmail1" name="exampleInputEmail1" placeholder="Email..">
  </div>

  <div class="form-group">
    <label for="mobileNumber">Mobile Number</label>
    <input type="text" class="form-control" id="mobileNumber" name="mobileNumber" placeholder="Mobile Number..">
  </div> -->

<!-- <div class="form-group">
    <label for="dob">DOB</label>
    <input type="text" class="form-control" name="dob" id="dob">
   
</div> -->

  



 <!--  <div class="form-group">
    <label for="dl">UK Driving Licence</label>
    <input type="text" class="form-control" id="dl" name="dl" placeholder="UK Driving Licence..">
  </div>
 -->
<!-- <div class="checkbox">
  <label>
    <input type="checkbox" value="">
    Option one is this and that&mdash;be sure to include why it's great
  </label>
</div>
<div class="checkbox disabled">
  <label>
    <input type="checkbox" value="" disabled>
    Option two is disabled
  </label>
</div>

<div class="radio">
  <label>
    <input type="radio" name="optionsRadios" id="optionsRadios1" value="option1" checked>
    Option one is this and that&mdash;be sure to include why it's great
  </label>
</div>
<div class="radio">
  <label>
    <input type="radio" name="optionsRadios" id="optionsRadios2" value="option2">
    Option two can be something else and selecting it will deselect option one
  </label>
</div>
<div class="radio disabled">
  <label>
    <input type="radio" name="optionsRadios" id="optionsRadios3" value="option3" disabled>
    Option three is disabled
  </label>
</div> -->
 <!--  <div class="form-group">
    <label for="exampleInputFile">Bank Statement (in PDF)</label>
    <input type="file" id="exampleInputFile" name="file">
   
  </div> -->
<button type="button" class="btn btn-prim" id="getallusers">Get All Users from user-service</button></div>
  </form>  
</div>

<hr>

<table id="example" class="display" width="100%"></table>

</div>
  </body>
</html>