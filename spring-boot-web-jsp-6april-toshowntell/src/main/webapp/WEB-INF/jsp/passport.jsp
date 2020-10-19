﻿<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="Card Scanning Solutions Inc.">
    <title>Passport</title>
    <!-- Bootstrap core CSS -->
    <link href="/css/bootstrap-select.css" rel="stylesheet">
    <link href="/css/bootstrap-fileupload.css" rel="stylesheet">
    <link href="/css/bootstrap.css" rel="stylesheet">
    <link href="/css/bootstrap-switch.css" rel="stylesheet">
    <link href="/css/bootstrap.icon-large.css" rel="stylesheet">
    <!-- for the fonts of the web page -->
    <link href="/css/flat-ui-fonts.css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="/css/custom.css" rel="stylesheet">
</head>
<body>
    <form name="formSubmit" action="#">
            <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="inner">
      <a href="index.html">Home</a>
    </div>        
    </nav>
        <br />
        <br />
        <br />
        <div class="container">
            <div class="starter-template">
                <div class="row">
                    <div class="col-xs-12 col-sm-12 col-lg-12">
                    <p>Please upload passport copy:<br/> Note:- only .png .gif .jpg .jpeg formats are allowed</p>
                    </div>
                </div>
                <div class="row">
                    <div class="col-xs-12 col-sm-12 col-lg-12">
                        <div id="container-webcam" style="display: none">
                            <div class="col-xs-6 col-sm-6 col-lg-6">
                                <video id="webcam" width="640px" height="480px" class="thumbnail" autoplay></video>
                                <strong>Webcam View</strong>&nbsp;<i id="help-icon" class="icon-info" rel="tooltip"
                                                                     title="Click webcam area to capture."></i>
                            </div>
                            <div class="col-xs-6 col-sm-6 col-lg-6">
                                <canvas id="selected-canvas" width="640px" height="480px" class="thumbnail"></canvas>
                                <!--The blank canvas will store the captured image to keep the image resolution. -->
                                <canvas id="blank-canvas" width="640px" height="480px" style='display: none'></canvas>
                                <strong>Captured Image</strong>
                            </div>
                        </div>
                        <div id="container-camera">
                            <div id="fileupload-container" class="fileupload fileupload-new" data-provides="fileupload">
                                <div class="fileupload-new thumbnail">
                                    <img class="img-responsive" id="placehold-image" src="images/TapHere.gif" />
                                </div>
                                <div id="image-thumbnail" class="fileupload-preview fileupload-exists thumbnail">
                                </div>
                                <div>
                                    <span class="btn btn-file btn-link btn-sm">
                                        <input id="input-image" type="file" capture="camera" name="passportImage" />
                                    </span><a id="remove-button" href="#" class="btn btn-sm fileupload-exists" data-dismiss="fileupload"
                                              style="display: none">Remove</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-xs-12 col-sm-12 col-lg-12">
                        <table style="margin-left: auto; margin-right: auto; text-align: left;">
                            <tr id="option-source">
                                <td>
                                    Select Source
                                </td>
                                <td>
                                    <input type="checkbox" id="chkImageSource" checked class="switch-large" data-label-icon="icon-fullscreen"
                                           data-on-label="<i class='icon-camera'></i>&nbsp;<i class='icon-hard-drive'></i>"
                                           data-off-label="<i class='icon-webcam'></i>">
                                </td>
                            </tr>
                            <!--<tr>
                                <td>
                                    <medium>Select Image Source</medium>
                                    &nbsp;
                                </td>
                                <td>
                                    <select id="imageSource-select" class="selectpicker" data-width="170px">
                                        <option value="101">Mobile Cropped</option>
                                        <option value="102">ScanShell/Twain</option>
                                        <option value="103">SnapShell</option>
                                        <option value="105">Other-Resize Image</option>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <medium>Crop Image</medium>
                                    &nbsp;
                                </td>
                                <td>
                                    <input id="chkPreProcessing" type="checkbox" />
                                </td>
                            </tr>-->
                        </table>
                    </div>
                </div>
                <br />
                <div class="row">
                    <div id="div-controls" class="col-xs-12 col-sm-12 col-lg-12">
                        <button id="btn-process-image" type="button" class="btn btn-lg btn-primary" style="width: 120px;
                        height: 50px;">
                            Process
                        </button>
                    </div>
                </div>
                <div class="row">
                    <div class="col-xs-12">
                        <div id="loading">
                        </div>
                    </div>
                </div>
            </div>
            <div id="errorDiv" style="text-align: center; color: red;">
            </div>
            <div class="row">
                <div id="extractedData" class="col-xs-12 col-sm-12" style="display: none;">
                    <div class="panel panel-info">
                        <div class="panel-heading text-center">
                            <strong>Extracted Data</strong>
                        </div>
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-xs-12 col-sm-12 col-lg-12">
                                    <div id="passport-data" class="form-horizontal">
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
                            <strong>Signature Image</strong>
                        </div>
                        <div class="panel-body text-center">
                            <img id="signature-image" style="height: 50px; width: 200px;" class="img-responsive"
                                 src="" alt="SignatureImage" />
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Modal -->
        <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
             aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                            &times;
                        </button>
                        <h4 class="modal-title">
                            Image Captured
                        </h4>
                    </div>
                    <div class="modal-body">
                        <canvas id="captured-canvas" class="img-responsive"></canvas>
                    </div>
                    <div class="modal-footer">
                        <button id="btn-retake" class="btn btn-default" data-dismiss="modal">
                            Retake
                        </button>
                        <button id="btn-use-image" type="button" class="btn btn-primary">
                            Use Image
                        </button>
                    </div>
                </div>
            </div>
        </div>
        <!-- /.modal -->
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
        <script src="/js/AcuantWebServiceJS/passport.js"></script>
    </form>
</body>
</html>
