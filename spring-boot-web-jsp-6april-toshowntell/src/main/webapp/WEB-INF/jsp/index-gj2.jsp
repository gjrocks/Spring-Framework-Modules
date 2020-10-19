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
      $( "#myform" ).hide();
      
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
    
    var client1 =    {

            "fullName" : "Ganesh Jadhav",

            "accountId" :"47482060",

            "bankId" :"obp-bankx-n",

            "customerNumber" : "95329867344",
            "dob" : "1980-05-05",
            "dl" : "GJ090909",
            "sortCode" : "30-98-75",
            "mobile" : "07459855156",
            "caseNum" : "123-ABC-XYZ",
            "email" :" mail.ganesh.jadhav@gmail.com"

     };
    var client2 =    {

            "fullName" : "MORGAN SARAH MEREDYTH",

            "accountId" :"47482065",

            "bankId" :"obp-bankx-n",

            "customerNumber" : "9999911243435",
            "dob" : "1976-03-11",
            "dl" : "MORGA753116SM9IJ",
            "sortCode" : "30-98-75",
            "mobile" : "0879190279",
            "caseNum" : "124-ABC-XYZ",
            "email" :"mail.morgan.sarah@gmail.com"

     };
    var client3 =    {

            "fullName" : "Anthony Maxim Dsouza",

            "accountId" :"47482064",

            "bankId" :"obp-bankx-n",

            "customerNumber" : "9996511243435",
            "dob" : "1980-05-05",
            "dl" : "ANTHO753116SM9IJ",
            "sortCode" : "30-98-75",
            "mobile" : "+91 8879190279",
            "caseNum" : "125-ABC-XYZ",
            "email" :"mail.anthony.maxim@gmail.com"

     };
    function showDiv(obj){
    	$( "#myform" ).show();
    	var temp=client1;
    	if(obj==1){
    		temp=client1;
    	}
    	if(obj==2){
    		temp=client2;
    	}
		if(obj==3){
			temp=client3;
		 }
    	$("#fullName").val(temp.fullName);
    	 $("#dob").val(temp.dob);
         $("#dl").val(temp.dl);
         $("#sortCode").val(temp.sortCode);
         $("#exampleInputEmail1").val(temp.email);
         $("#acc").val(temp.accountId);
         $("#mobileNumber").val(temp.mobile);
         $("#cust").val(temp.customerNumber);
         
    }
    </script>
<nav class="navbar navbar-inverse" role="navigation">
    <div class="inner">
      <img alt="Open Bank" src="/images/new-back.png" width="100%">
    </div>
    </nav>
        <br />
        <br />
       
<div class="container-fluid">

<div class="row" id="row2">
  <div class="col-md-2" id="col1">
  
  
  </div>
  <div class="col-md-4" id="col2">
 <div id="apps">
 <div class="form-group">
    <label for="appDetails">Following Loan applications are queued for processing</label>
   
  </div>
   <table class="table table-bordered">
    <thead>
      <tr>
        <th>Sr No</th>
        <th>Full Name</th>
        <th>Loan Reference No</th>
        <th>View Details</th>
      </tr>
    </thead>
    <tbody>
      <tr>
        <td>1</td>
        <td>Ganesh Jadhav</td>
        <td>123-ABC-XYZ</td>
        <td><button type="button" class="btn btn-prim" id="viewApp" onclick="showDiv(1)">View</button></td>
      </tr>
       <tr>
       <td>2</td>
        <td>MORGAN SARAH MEREDYTH</td>
        <td>124-ABC-XYZ</td>
        <td><button type="button" class="btn btn-prim" id="viewApp" onclick="showDiv(2)">View</button></td>
      </tr>
      <tr>
       <td>3</td>
        <td>Anthony Maxim Dsouza</td>
        <td>125-ABC-XYZ</td>
        <td><button type="button" class="btn btn-prim" id="viewApp" onclick="showDiv(3)">View</button></td>
      </tr> 
    </tbody>
  </table>
 </div> 
 
<div id="myform">
<hr>
<form method="POST" action="/uploadNew">
  <div class="form-group">
    <label for="fullName">Full Name</label>
    <input type="text" class="form-control" id="fullName" name="fullName" placeholder="Full Name.." value="${fullName}">
  </div>

  <div class="form-group">
    <label for="exampleInputEmail1">Email address</label>
    <input type="email" class="form-control" id="exampleInputEmail1" name="exampleInputEmail1" placeholder="Email.." value="${exampleInputEmail}">
  </div>

  <div class="form-group">
    <label for="mobileNumber">Mobile Number</label>
    <input type="text" class="form-control" id="mobileNumber" name="mobileNumber" placeholder="Mobile Number.." value="${mobileNumber}">
  </div>

<div class="form-group">
    <label for="dob">DOB</label>
    <input type="text" class="form-control" name="dob" id="dob" value="${dob}">
   
</div>

  
<div class="form-group">
    <label for="sortCode">Sort-Code</label>
    <input type="text" class="form-control" name="sortCode" id="sortCode" value="${sortCode}">
   
</div>


<div class="form-group">
    <label for="acc">Account Number</label>
    <input type="text" class="form-control" name="acc" id="acc" value="${acc}">
   
</div>


  <div class="form-group">
    <label for="dl">UK Driving Licence</label>
    <input type="text" class="form-control" id="dl" name="dl" placeholder="UK Driving Licence.." value="${dl}">
  </div>

<div class="form-group">
   <!--  <label for="dl">UK Driving Licence</label> -->
    <input type="hidden" class="form-control" id="cust" name="cust" value="${cust}">
  </div>
<button type="submit" class="btn btn-prim">Submit</button></div>
  </form> 
  </div> 
</div>

</div>
 <br />
  </body>
</html>