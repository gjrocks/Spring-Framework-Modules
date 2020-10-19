<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>Verification</title>

    <!-- Bootstrap -->
    <link href="/css/bootstrap.min.css" rel="stylesheet">

    <link href="/css/gj.css" rel="stylesheet">
     <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
     
 <!--    <link rel="stylesheet" type="text/css" href="css/file-upload.css" />
<script src="js/file-upload.js"></script> -->
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>
  <body>
   

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="/js/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="/js/bootstrap.min.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <script>
   
    $( function() {
      $( "#dob" ).datepicker();
      
     /*  if (typeof(Storage) !== "undefined") {
    	    var driversLicense = sessionStorage.getItem("driversLicense");
    	    var passport = sessionStorage.getItem("passport");
    	    var fullName = null;
    	    var drivingno  = null;
    	    var dob = null;
    	    
    	    if(jQuery.isEmptyObject(driversLicense)!=true) {
    	    	driversLicense =  jQuery.parseJSON(driversLicense);
    	    	fullName = driversLicense.NameFirst+" "+driversLicense.NameLast;
    	    	drivingno = driversLicense.license;  
    	    	var dateofbirth = driversLicense.DateOfBirth4.split("-");    	    	
    	    	dob = dateofbirth[0]+"/"+dateofbirth[1]+"/"+dateofbirth[2];    	
    	    	
    	    }
            if(jQuery.isEmptyObject(passport)!=true) {
                
            }
            $("#fullName").val(fullName);
            $("#dob").val(dob);
            $("#dl").val(drivingno);
       } */
      
      
    } );
    
    
    </script>
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="inner">
      <a href="index.html">Home</a>
    </div>        
    </nav>
        <br />
        <br />
        <br />
<div class="container-fluid">

<div class="row" id="row2">
  <div class="col-md-2" id="col1"></div>
  <div class="col-md-4" id="col2"><!-- Standard button -->

<form method="POST" action="/upload" enctype="multipart/form-data">
  <div class="form-group">
    <label for="fullName">Full Name</label>
    <input type="text" class="form-control" id="fullName" name="fullName" placeholder="Full Name..">
  </div>

  <div class="form-group">
    <label for="exampleInputEmail1">Email address</label>
    <input type="email" class="form-control" id="exampleInputEmail1" name="exampleInputEmail1" placeholder="Email..">
  </div>

  <div class="form-group">
    <label for="mobileNumber">Mobile Number</label>
    <input type="text" class="form-control" id="mobileNumber" name="mobileNumber" placeholder="Mobile Number..">
  </div>

<div class="form-group">
    <label for="dob">DOB</label>
    <input type="text" class="form-control" name="dob" id="dob">
   
</div>

  



  <div class="form-group">
    <label for="dl">UK Driving Licence</label>
    <input type="text" class="form-control" id="dl" name="dl" placeholder="UK Driving Licence..">
  </div>

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
  <div class="form-group">
    <label for="exampleInputFile">Bank Statement (in PDF)</label>
    <input type="file" id="exampleInputFile" name="file">
   
  </div>
<button type="submit" class="btn btn-prim">Submit</button></div>
  </form>  
</div>

</div>
  </body>
</html>