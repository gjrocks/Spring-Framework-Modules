<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="Card Scanning Solutions Inc.">
    <title>User's Information</title>
    <link href="/css/bootstrap-select.css" rel="stylesheet" />
    <!-- for the dropdown list of regions -->
    <link href="/css/bootstrap-fileupload.css" rel="stylesheet" />
    <!-- Bootstrap core CSS -->
    <link href="/css/bootstrap.css" rel="stylesheet" />
    <link href="/css/bootstrap-switch.css" rel="stylesheet" />
    <link href="/css/bootstrap.icon-large.css" rel="stylesheet" />
    <!-- for the fonts of the web page -->
    <link href="/css/flat-ui-fonts.css" rel="stylesheet" />
    <!-- Custom styles for this template -->
    <link href="/css/custom.css" rel="stylesheet" />
</head>
<body>
    <form name="formSubmit" action="/submitpersonaldetails" method="post">
        <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
            <!-- Brand and toggle get grouped for better mobile display -->
            <br />
            <br />
        </nav>
        <br />
        <br />
        <br />
        <div class="container">           
            <div id="errorDiv" style="text-align: center; color: red;">
            </div>
            <div class="row">
                <div id="extractedData" class="col-xs-12 col-sm-12" style="display: none;">
                    <div class="panel panel-info">
                        <div class="panel-heading text-center">
                            <strong>Information</strong>
                        </div>
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-xs-12 col-sm-12 col-lg-12">
                                    <div id="drivers-license-data" class="form-horizontal">
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-xs-12 col-sm-12" id="faceImage" style="display: none;">
                    <div class="panel panel-info">
                        <div class="panel-heading text-center">
                            <strong>Face Image</strong>
                        </div>
                        <div class="panel-body text-center">
                            <img id="face-image" style="height: 180px; width: 140px;" class="img-responsive"
                                 src="" alt="FaceImage" />
                        </div>
                    </div>
                </div>
                <div class="col-xs-12 col-sm-12" id="signImage" style="display: none; margin: 0 auto !important;">
                    <div class="panel panel-info">
                        <div class="panel-heading text-center">
                            <strong>Signature</strong>
                        </div>
                        <div class="panel-body text-center">
                            <img id="signature-image" style="height: 50px; width: 200px;" class="img-responsive"
                                 src="" alt="SignatureImage" />
                        </div>
                    </div>
                </div>
                
            </div>
            <div class="starter-template">
            <br />
                <div class="row">
                <div id="div-controls" class="col-xs-12 col-sm-12 col-lg-12">
                <input type="hidden" name="userInfor" value="" id="userinfo">
                        <button id="btn-process-image" type="submit" class="btn btn-lg btn-primary" style="width: 120px;
                        height: 50px;">
                            Process
                        </button>
                </div>
                </div>
             </div>
        </div>
       

        <!-- Placed at the end of the document so the pages load faster -->
        <script src="/js/MiscJS/base.js"></script>
        <script>
            goog.require('goog.crypt.base64');
        </script>
        <script src="/js/MiscJS/jquery-2.0.3.js"></script>
        <script type="text/javascript">
            $(window).on('load', function () { $('.selectpicker').selectpicker({}); });
        </script>
        <script src="/js/MiscJS/bootstrap.js"></script>
        <script src="/js/MiscJS/bootstrap-fileupload.js"></script>
        <script src="/js/MiscJS/jquery.base64.js"></script>
        <script src="/js/MiscJS/bootstrap-switch.js"></script>
        <script src="/js/MiscJS/bootstrap-select.js"></script>
        <script src="/js/MiscJS/binaryajax.js"></script>
        <script src="/js/MiscJS/exif.js"></script>
        <script src="/js/MiscJS/canvasResize.js"></script>
        <script src="/js/MiscJS/ui.js"></script>
        <script src="/js/AcuantWebServiceJS/common.js"></script>
        <script src="/js/AcuantWebServiceJS/licensekey.js"></script>
        <script src="/js/AcuantWebServiceJS/displayInfo.js"></script>
    </form>
</body>
</html>
