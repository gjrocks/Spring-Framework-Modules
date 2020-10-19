package com.ateam.services.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ateam.services.demo.experian.DrivingLicense;


@RestController
public class ExperienController {
	
	

	@RequestMapping(value = "/validateDrivingLicense/{drivingLicenseNumber}", method = RequestMethod.GET)
	public ResponseEntity<DrivingLicense> validateDrivingLicense(@PathVariable(value="drivingLicenseNumber")String drivingLicenseNumber) {
		
		try{
			Thread.sleep(2000);
		}catch(Exception e){
			
		}
		List <DrivingLicense> driverList  = new ArrayList();
		driverList.add(new DrivingLicense("Snehal Nakhawa", "07-05-1988", "SN1234567", "Vashi"));
		driverList.add(new DrivingLicense("Saurabh Jakharde","09-08-1989","SJ1234890", "Pune"));
		driverList.add(new DrivingLicense("Ganesh Jadhav","05-05-1980","GJ090909" ,"Pune"));
		driverList.add(new DrivingLicense("MORGAN SARAH MEREDYTH","1976-03-11","MORGA753116SM9IJ" ,"London"));
		driverList.add(new DrivingLicense("Anthony Maxim Dsouza","05-05-1980","ANTHO753116SM9IJ" ,"London"));
		
		DrivingLicense driverDetails = new DrivingLicense(null, null, null, null);
		
		
		for(DrivingLicense list : driverList ) {
			
			
		if(list.getDriverLicenseNumber().equals(drivingLicenseNumber))
			
		{
			
			driverDetails.setDriverFullName(list.getDriverFullName());
			driverDetails.setDateOfBirth(list.getDateOfBirth());
			driverDetails.setDriverLicenseNumber(list.getDriverLicenseNumber());
			driverDetails.setAddressDetails(list.getAddressDetails());
			driverDetails.setResult("true");
			break;

		}else {
			driverDetails.setResult("Record Not Found");

			
		}
		}
		return new ResponseEntity<DrivingLicense>(driverDetails,HttpStatus.OK);
		
	}

}