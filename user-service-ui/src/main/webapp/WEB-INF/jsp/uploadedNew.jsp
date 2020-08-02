<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>Verify Page</title>

    <!-- Bootstrap -->
    <link href="/css/bootstrap.min.css" rel="stylesheet">

    <link href="/css/gj.css" rel="stylesheet">
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
    
    <script type="text/javascript">
    $(document).ready(function(){
    
        $('#verifybtn').on('click', function(event) {
             //alert('herer');
             openBankPlease();
            });
        
        $('#getKycDtl').on('click', function(event) {
            //alert('herer2');
            openBankPlease2();
           });
        
        $('#verifyperbtn').on('click', function(event) {
            //alert('herer2');
            openExperian();
           });
        
        $('#verificationDone').on('click', function(event) {
           alert("level 1 approval done, mail customer");
           });
        
        
        $('#openresult').hide();
        $('#openbankresult').hide();
        $('#openKYCresult').hide(); 
        $('#dlvalid').hide(); 
        $('#dlinvalid').hide(); 
    });
    
    function openBankPlease() {
    	var accountN = $('#accountNumber').val();
    	var fullName = $('#fullName').val();
        
        var client =    {

                "fullName" : fullName,

                "accountId" :accountN,

                "bankId" :"obp-bankx-n",

                "customerNumber" : "95329867344",
                "dob" : "1980-05-05"

    }
       // alert(chatMessage.text);
        var request = $.ajax({
            url: 'http://localhost:7000/obp/validateClient',
            type: 'POST',
            data: JSON.stringify(client),
            contentType: "application/json; charset=utf-8",
            traditional: true,
            dataType: 'json'
          });

          request.done(function(msg) {
            //$("#log").html( msg );
              // alert(msg);
             //  alert(msg.fullName);
               console.log(msg);
               $('#dum').val(JSON.stringify(msg));
               $('#openresult').show();
               $('#accountAvailable').val(msg.accountAvailable);
               $('#accountId').val(msg.accountId);
               $('#fullNameatbank').val(msg.fullName);
               $('#kycdone').val(msg.kycdone);
               $('#customerAvailable').val(msg.customerAvailable);
               $('#customerNumber').val(msg.customerNumber);
               $('#openbankresult').show();
             
          });

          request.fail(function(jqXHR, textStatus) {
            alert( "Request failed: " + textStatus );
          });
        
       
    }
    
    
    function openBankPlease2() {
      var payload= $('#dum').val();
      payload=JSON.parse(payload);
      payload.detailedKYC=true;
      console.log("payload:" +payload);
  /*       var client =    {

                "fullName" : fullName,

                "accountId" :accountN,

                "bankId" :"obp-bankx-n",

                "customerNumber" : "95329867344"

    } */
       // alert(chatMessage.text);
        var request = $.ajax({
            url: 'http://localhost:7000/obp/validateClient',
            type: 'POST',
            data: JSON.stringify(payload),
            contentType: "application/json; charset=utf-8",
            traditional: true,
            dataType: 'json'
          });

          request.done(function(msg) {
            //$("#log").html( msg );
              // alert(msg);
             //  alert(msg.fullName);
               console.log("msg2:" +msg);
             
             // $('#openresult').show();
               $('#kycDate').val(msg.kycDate);
               $('#kycMode').val(msg.kycMode);
               $('#dobverificationSuccessful').val(msg.dobverificationSuccessful);
               $('#passportKYCSuccessful').val(msg.passportKYCSuccessful);
               $('#drivingLicenceKYCSuccessful').val(msg.drivingLicenceKYCSuccessful);
               $('#detailedKYC').val(msg.detailedKYC);
               $('#openKYCresult').show(); 
             
          });

          request.fail(function(jqXHR, textStatus) {
            alert( "Request failed 2: " + textStatus );
          });
           
    }
    function openExperian(){
        var dl = $('#dl').val();

        var request = $.ajax({
            url: 'http://localhost:7000/validateDrivingLicense/'+dl,
            type: 'GET',
            contentType: "application/json; charset=utf-8",
            traditional: true,
            dataType: 'json'
          });

        request.done(function(msg) {
            //$("#log").html( msg );
              // alert(msg);
             //  alert(msg.fullName);
               console.log("msg3:" +msg);
             if(msg.result=="true"){
            	 $('#dlvalid').show(); 
            	 $('#dlinvalid').hide(); 
             }else{
            	 $('#dlvalid').hide(); 
            	 $('#dlinvalid').show(); 
             }
            	 
             // $('#openresult').show();
              /*  $('#kycDate').val(msg.kycDate);
               $('#kycMode').val(msg.kycMode);
               $('#dobverificationSuccessful').val(msg.dobverificationSuccessful);
               $('#passportKYCSuccessful').val(msg.passportKYCSuccessful);
               $('#drivingLicenceKYCSuccessful').val(msg.drivingLicenceKYCSuccessful);
               $('#detailedKYC').val(msg.detailedKYC);
               $('#openKYCresult').show();  */
             
          });

          request.fail(function(jqXHR, textStatus) {
            alert( "Request failed 2: " + textStatus );
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
<div class="row" id="row2">
<div class="col-md-2" id="col1"></div>
  <div class="col-md-3" id="col2">
  <!-- Standard button -->
  <div class="form-group">
    <label for="sortCode">Personal Details, please verify using  Experian service</label>
   
  </div>
 
<!-- <form method="POST" action="/upload" enctype="multipart/form-data"> -->
 <div class="form-group">
    <label for="fullName">Full Name</label>
    <input type="text" class="form-control" id="fullName" name="fullName" value="${ fullName}">
     <input type="hidden" class="form-control" id="dum" name="dum">
  </div>
  
   <div class="form-group">
    <label for="dl"> Driving Licence </label>
    <input type="text" class="form-control" id="dl" name="dl" value="${dl}">
  </div>
  
   <div class="form-group">
    <label for="dob"> DOB </label>
    <input type="text" class="form-control" id="dob" name="dob" value="${dob}">
  </div>
  <button type="button" class="btn btn-prim" id="verifyperbtn">Verify with Experian</button>
  </div>


</div>
<hr>
<hr>
<div class="row" id="row6">
  <div class="col-md-2" id="col1"></div>
  <div class="col-md-4" id="col2">
  <div class="form-group">
    <label for="Experian_Result">Experian Result  :</label>
      <div id="dlvalid" class="btn btn-success">Experian has found valid DL details</div>
      <div id="dlinvalid" class="btn btn-warning">Experian has not found valid DL details</div>
    </div>
  </div>
  </div>
  <hr>
<hr>
<div class="row" id="row3">
  <div class="col-md-2" id="col1"></div>
  <div class="col-md-4" id="col2">
  <!-- Standard button -->
  <div class="form-group">
    <label for="sortCode">Using the bank statement we have found following Bank Details, please verify</label>
   
  </div>
 
<!-- <form method="POST" action="/upload" enctype="multipart/form-data"> -->
<%--  <div class="form-group">
    <label for="fullName">Full Name</label>
    <input type="text" class="form-control" id="fullName" name="fullName" value="${ fullName}">
  </div> --%>
  
  <div class="form-group">
    <label for="sortCode">Sort code</label>
    <input type="text" class="form-control" id="sortCode" name="sortCode" value="${ sortCode}">
  </div>

  <div class="form-group">
    <label for="accountNumber">Account Number</label>
    <input type="text" class="form-control" id="accountNumber" name="accountNumber" value="${account }">
  </div>

  <div class="form-group">
    <label for="BIC">BIC</label>
    <input type="text" class="form-control" id="bic" name="bic" value="${bic }">
  </div>

  <div class="form-group">
    <label for="iban">IBAN</label>
    <input type="text" class="form-control" id="iban" name="iban" value="${ iban}" >
  </div>
<button type="button" class="btn btn-prim" id="verifybtn">Verify with Open Bank</button>
<button type="button" class="btn btn-prim" id="gettxnbtn">Get transactions</button>
</div>
</div>
<hr>

<div class="row" id="row4">
  <div class="col-md-2" id="col1"></div>
  <div class="col-md-4" id="col2">
   <div class="form-group">
    <label for="sortCode">Results from OpenBank</label>
  <!--  <button type="button" class="btn btn-success" disabled="disabled" id="openbankresult">Success</button> -->
  </div>
  <div id="openresult">
  <table class="table table-striped table-bordered table-hover table-condensed">
    <thead>
      <tr>
        <th>Result From OpenBank</th>
        <th></th>
      </tr>
    </thead>
    <tbody>
    <tr>
        <th>Account Active at Bank</th>
        <th><input type="text" class="form-control" id="accountAvailable" name="accountAvailable"></th>
      </tr>
      <tr>
        <td>Account Number</td>
        <td><input type="text" class="form-control" id="accountId" name="accountId"></td>
      </tr>
      <tr>
        <td>Customer Details Available</td>
        <td><input type="text" class="form-control" id="customerAvailable" name="customerAvailable"></td>
      </tr>
      <tr>
        <td>Customer Number</td>
        <td><input type="text" class="form-control" id="customerNumber" name="customerNumber"></td>
      </tr>
      <tr>
        <td>Full Name at Bank</td>
        <td><input type="text" class="form-control" id="fullNameatbank" name="fullNameatbank"></td>
      </tr>
      <tr>
        <td>KYC done at Bank</td>
        <td><input type="text" class="form-control" id="kycdone" name="kycdone"> <button type="button" class="btn btn-prim" id="getKycDtl">KYC details from Open Bank</button></td>
      </tr>
    </tbody>
  </table>
  </div>
  </div>
</div>
<hr>
<hr>
<div class="row" id="row5">
  <div class="col-md-2" id="col1"></div>
  <div class="col-md-4" id="col2">
   <div class="form-group">
    <label for="kycs">Results from OpenBank KYC</label>
   <!-- <button type="button" class="btn btn-success" disabled="disabled" id="openbankresult">Success</button> -->
  </div>
  <div id="openKYCresult">
  <table class="table table-striped table-bordered table-hover table-condensed">
    <thead>
      <tr>
        <th>Result From OpenBank KYC</th>
        <th></th>
      </tr>
    </thead>
    <tbody>
    <tr>
        <th>KYC Date at Bank</th>
        <th><input type="text" class="form-control" id="kycDate" name="kycDate"></th>
      </tr>
      <tr>
        <td>KYC Mode</td>
        <td><input type="text" class="form-control" id="kycMode" name="kycMode"></td>
      </tr>
      <tr>
        <td>Customer DOB Verification Successful</td>
        <td><input type="text" class="form-control" id="dobverificationSuccessful" name="dobverificationSuccessful"></td>
      </tr>
      <tr>
        <td>Passport KYC Successful</td>
        <td><input type="text" class="form-control" id="passportKYCSuccessful" name="passportKYCSuccessful"></td>
      </tr>
      <tr>
        <td>Driving Licence KYC Successful</td>
        <td><input type="text" class="form-control" id="drivingLicenceKYCSuccessful" name="drivingLicenceKYCSuccessful"></td>
      </tr>
      <tr>
        <td>Detailed KYC done at Bank</td>
        <td><input type="text" class="form-control" id="detailedKYC" name="detailedKYC"> </td>
      </tr>
    </tbody>
  </table>
  </div>
  </div>
</div>
<hr>
<hr>
<div class="row" id="row4">
  <div class="col-md-2" id="col1"></div>
  <div class="col-md-4" id="col2">
<button type="button" class="btn btn-prim" id="verificationDone">Verification Done- Level 1 approval</button>
</div>
</div>
</div>

<!-- </div> -->
  </body>
</html>