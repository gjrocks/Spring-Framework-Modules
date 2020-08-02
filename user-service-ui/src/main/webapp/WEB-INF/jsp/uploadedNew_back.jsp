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
             alert('herer');
             openBankPlease();
            });
        $('#openresult').hide();
    });
    
    function openBankPlease() {
    	var accountN = $('#accountNumber').val();
    	var fullName = $('#fullName').val();
        
        var client =    {

                "fullName" : fullName,

                "accountId" :accountN,

                "bankId" :"obp-bankx-n",

                "customerNumber" : "95329867344"

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
               alert(msg);
               alert(msg.fullName);
               console.log(msg);
               $('#openresult').show();
               $('#accountAvailable').val(msg.accountAvailable);
               $('#accountId').val(msg.accountId);
              // $('#fullName').val(msg.fullName);
               $('#kycdone').val(msg.kycdone);
             //  $('#accountAvailable').val(msg.accountAvailable);
               
             
          });

          request.fail(function(jqXHR, textStatus) {
            alert( "Request failed: " + textStatus );
          });
        
       
    }
    </script>
<div class="container-fluid">
   <!-- <div class="row">
  <div class="col-md-1">.col-md-1</div>
  <div class="col-md-1">.col-md-1</div>
  <div class="col-md-1">.col-md-1</div>
  <div class="col-md-1">.col-md-1</div>
  <div class="col-md-1">.col-md-1</div>
  <div class="col-md-1">.col-md-1</div>
  <div class="col-md-1">.col-md-1</div>
  <div class="col-md-1">.col-md-1</div>
  <div class="col-md-1">.col-md-1</div>
  <div class="col-md-1">.col-md-1</div>
  <div class="col-md-1">.col-md-1</div>
  <div class="col-md-1">.col-md-1</div>
</div>
ganesh
Stack the columns on mobile by making one full-width and the other half-width
<div class="row">
  <div class="col-xs-12 col-md-8">.col-xs-12 .col-md-8</div>
  <div class="col-xs-6 col-md-4">.col-xs-6 .col-md-4</div>
</div>


<div class="row">
  <div class="col-xs-6 col-md-4">.col-xs-6 .col-md-4</div>
  <div class="col-xs-6 col-md-4">.col-xs-6 .col-md-4</div>
  <div class="col-xs-6 col-md-4">.col-xs-6 .col-md-4</div>
</div>


<div class="row">
  <div class="col-xs-6">.col-xs-6</div>
  <div class="col-xs-6">.col-xs-6</div>
</div>
-->
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
  <div class="col-md-4" id="col2">
  <!-- Standard button -->
  <div class="form-group">
    <label for="sortCode">Personal Details, please verify using  Experian service</label>
   
  </div
 
<!-- <form method="POST" action="/upload" enctype="multipart/form-data"> -->
 <div class="form-group">
    <label for="fullName">Full Name</label>
    <input type="text" class="form-control" id="fullName" name="fullName" value="${ fullName}">
  </div>
  
   <div class="form-group">
    <label for="dl"> Driving Licence </label>
    <input type="text" class="form-control" id="dl" name="dl" value="${dl}">
  </div>
  
  <button type="button" class="btn btn-prim" id="verifyperbtn">Verify with Experian</button>
  </div>


</div>
<hr>
<div class="row" id="row3">
  <div class="col-md-2" id="col1"></div>
  <div class="col-md-4" id="col2">
  <!-- Standard button -->
  <div class="form-group">
    <label for="sortCode">Using the bank statement we have found following Bank Details, please verify</label>
   
  </div
 
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
<button type="button" class="btn btn-prim" id="verifybtn">Verify with Open Bank</button></div>

<hr>
<div class="row" id="row4">
  <div class="col-md-2" id="col1"></div>
  <div class="col-md-4" id="col2">
  <div id="openresult">
  <table class="table table-striped table-bordered table-hover table-condensed">
    <thead>
      <tr>
        <th>Account Available</th>
        <th><input type="text" class="form-control" id="accountAvailable" name="accountAvailable"></th>
      </tr>
    </thead>
    <tbody>
      <tr>
        <td>Account Number</td>
        <td><input type="text" class="form-control" id="accountId" name="accountId"></td>
      </tr>
      <tr>
        <td>Customer Details Available</td>
        <td><input type="text" class="form-control" id="customerAvailable" name="customerAvailable"></td>
      </tr>
      <tr>
        <td>Full Name at Bank</td>
        <td><input type="text" class="form-control" id="fullNameatbank" name="fullNameatbank"></td>
      </tr>
      <tr>
        <td>KYC done at Bank</td>
        <td><input type="text" class="form-control" id="kycdone" name="kycdone"></td>
      </tr>
    </tbody>
  </table>
  </div>
  </div>
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
  <!-- <div class="form-group">
    <label for="exampleInputFile">Bank Statement (in PDF)</label>
    <input type="file" id="exampleInputFile" name="file">
   
  </div>

  </form>   -->
</div>

</div>
  </body>
</html>