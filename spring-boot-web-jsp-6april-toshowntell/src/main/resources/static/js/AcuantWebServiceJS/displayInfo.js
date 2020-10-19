$(document).ready(function () {
	if (typeof(Storage) !== "undefined") {
	var driversLicense = sessionStorage.getItem("driversLicense");
	var passport = sessionStorage.getItem("passport");
	if(jQuery.isEmptyObject(driversLicense)!=true) {
		console.log(driversLicense);
$("#userInfo").val(driversLicense);
					        driversLicense =  jQuery.parseJSON(driversLicense);
		            //Checking if there are errors returned.
		            if (driversLicense.ResponseCodeAuthorization < 0) {
		                ui.ShowErrorDescription(driversLicense.ResponseMessageAuthorization);
		            }
		            else if (driversLicense.ResponseCodeAutoDetectState < 0) {
		                ui.ShowErrorDescription(driversLicense.ResponseCodeAutoDetectStateDesc);
		            }
		            else if (driversLicense.ResponseCodeProcState < 0) {
		                ui.ShowErrorDescription(driversLicense.ResponseCodeProcessStateDesc);
		            }
		            else if (driversLicense.WebResponseCode < 1) {
		                ui.ShowErrorDescription(driversLicense.WebResponseDescription);
		            }
		            else {
		                //Display data returned by the web service
		                var data = ui.DisplayField("First Name", driversLicense.NameFirst);
		                data += ui.DisplayField("Middle Name", driversLicense.NameMiddle);
		                data += ui.DisplayField("Last Name", driversLicense.NameLast);
		                data += ui.DisplayField("License Number", driversLicense.license);
		                data += ui.DisplayField("Address", driversLicense.Address);
		                data += ui.DisplayField("City", driversLicense.City);
		                data += ui.DisplayField("State", driversLicense.State);
		                data += ui.DisplayField("Zip", driversLicense.Zip);
		                data += ui.DisplayField("Country", driversLicense.IdCountry);
		                data += ui.DisplayField("Expiration Date", driversLicense.ExpirationDate4);
		                data += ui.DisplayField("Issue Date", driversLicense.IssueDate4);
		                data += ui.DisplayField("Date Of Birth", driversLicense.DateOfBirth4);
		                data += ui.DisplayField("Sex", driversLicense.Sex);
		                data += ui.DisplayField("Eyes Color", driversLicense.Eyes);
		                data += ui.DisplayField("Hair Color", driversLicense.Hair);
		                data += ui.DisplayField("Height", driversLicense.Height);
		                data += ui.DisplayField("Weight", driversLicense.Weight);
		                data += ui.DisplayField("Class", driversLicense.Class);
		                data += ui.DisplayField("Restriction", driversLicense.Restriction);
		                data += ui.DisplayField("Endorsements", driversLicense.Endorsements);

		                if (driversLicense.AuthenticationResult != null) {
		                    data += ui.DisplayField("Authentication Result", driversLicense.AuthenticationResult);
		                    if (driversLicense.AuthenticationResult == "Attention" || driversLicense.AuthenticationResult == "Unknown") // AuthenticationResultSummary is only populated if the AuthenticationResult is "Attention"
		                    {
		                        data += ui.DisplayField("Authentication Result Summary", driversLicense.AuthenticationResultSummary);
		                    }
		                }
		               
		                $(data).appendTo("#drivers-license-data");
		                $("#extractedData").show();

		                //Display face, sign and reformatted images on UI
		                var faceImage = driversLicense.FaceImage;
		                if (faceImage != null || faceImage != "") {
		                    var base64FaceImage = goog.crypt.base64.encodeByteArray(faceImage);

		                    $('#faceImage').show();
		                    $("#face-image").attr("src", "data:image/jpg;base64," + base64FaceImage);
		                }

		                var signImage = driversLicense.SignImage;
		                if (signImage != null || signImage != "") {
		                    var base64SignImage = goog.crypt.base64.encodeByteArray(signImage);

		                    $('#signImage').show();
		                    $("#signature-image").attr("src", "data:image/jpg;base64," + base64SignImage);
		                }

		                var reformattedImage = driversLicense.ReformattedImage;
		                if (reformattedImage != null || reformattedImage != "") {
		                    var base64ReformattedImage = goog.crypt.base64.encodeByteArray(reformattedImage);
		                    $("#image-thumbnail img:first-child").attr("src", "data:image/jpg;base64," + base64ReformattedImage);
		                }

					}

	} // end of drivering license
	if(jQuery.isEmptyObject(passport)!=true) {
		$("#userInfo").val(passport);
		passport = jQuery.parseJSON(passport);

            //Checking if there are errors returned.
            if (passport.ResponseCodeAuthorization < 0) {
                ui.ShowErrorDescription(passport.ResponseMessageAuthorization);
            }
            else if (passport.WebResponseCode < 1) {
                ui.ShowErrorDescription(passport.WebResponseDescription);
            }
            else {

                //Display data returned by the web service
                var data = ui.DisplayField("First Name", passport.NameFirst);
                data += ui.DisplayField("Middle Name", passport.NameMiddle);
                data += ui.DisplayField("Last Name", passport.NameLast);
                data += ui.DisplayField("First Name Non-MRZ", passport.NameFirst_NonMRZ);
                data += ui.DisplayField("Last Name Non-MRZ", passport.NameLast_NonMRZ);
                data += ui.DisplayField("Passport Number", passport.PassportNumber);
                data += ui.DisplayField("DOB", passport.DateOfBirth);
                data += ui.DisplayField("DOB Long", passport.DateOfBirth4);
                data += ui.DisplayField("Issue Date", passport.IssueDate);
                data += ui.DisplayField("Issue Date Long", passport.IssueDate4);
                data += ui.DisplayField("Expiration Date", passport.ExpirationDate);
                data += ui.DisplayField("Expiration Date Long", passport.ExpirationDate4);
                data += ui.DisplayField("Address", passport.Address2);
                data += ui.DisplayField("Address", passport.Address3);
                data += ui.DisplayField("Country Short", passport.Country);
                data += ui.DisplayField("Country Long", passport.CountryLong);
                data += ui.DisplayField("Personal Number", passport.PersonalNumber);
                data += ui.DisplayField("Nationality", passport.Nationality);
                data += ui.DisplayField("Nationality Long", passport.NationalityLong);
                data += ui.DisplayField("Sex", passport.Sex);
                data += ui.DisplayField("Place of Birth", passport.End_POB);

                if (passport.AuthenticationResult != null) {
                    data += ui.DisplayField("Authentication Result", passport.AuthenticationResult);
                    if (passport.AuthenticationResult == "Attention" || passport.AuthenticationResult == "Unknown") // AuthenticationResultSummary is only populated if the AuthenticationResult is "Attention"
                    {
                        data += ui.DisplayField("Authentication Result Summary", passport.AuthenticationResultSummary);
                    }
                }

                $(data).appendTo("#drivers-license-data");
                $("#extractedData").show();
            }

            //Display face, sign and reformatted images on UI
            var faceImage = passport.FaceImage;
            if (faceImage != null) {
                var base64FaceImage = goog.crypt.base64.encodeByteArray(faceImage);

                $('#faceImage').show();
                $("#face-image").attr("src", "data:image/jpg;base64," + base64FaceImage);
            }

            var signImage = passport.SignImage;
            if (signImage != null) {
                var base64SignImage = goog.crypt.base64.encodeByteArray(signImage);

                $('#signImage').show();
                $("#signature-image").attr("src", "data:image/jpg;base64," + base64SignImage);
            }

            var reformattedImage = passport.ReformattedImage;
            if (reformattedImage != null) {
                var base64ReformattedImage = goog.crypt.base64.encodeByteArray(reformattedImage);
                $("#image-thumbnail img:first-child").attr("src", "data:image/jpg;base64," + base64ReformattedImage);
            }
	}

}
else{

}
});