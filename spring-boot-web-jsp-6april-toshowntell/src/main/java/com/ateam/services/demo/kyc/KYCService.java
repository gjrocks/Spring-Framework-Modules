package com.ateam.services.demo.kyc;


import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.ateam.services.demo.model.kyc.Check;
import com.ateam.services.demo.model.kyc.Document;
import com.ateam.services.demo.model.kyc.Documents;
import com.ateam.services.demo.model.kyc.KYCChecks;
import com.ateam.services.demo.util.DemoServiceUtil;

@Component
public class KYCService {
	
	@Value("${obp.api.versionedUrl}")
	private String apiUrl;

	private RestTemplate restTemplate = new RestTemplate();	

	/**
	 * Get customer KYC documents like passport , driving licence
	 * @param token
	 * @param customerNumber
	 * @return
	 */
	///customers/CUSTOMER_ID/kyc_documents
	public List<Document> getCustomerKYCDocuments(String token, String customerID)
	{
		String getKYCDocumentURL = String.format("%s/customers/%s/kyc_documents", apiUrl, customerID);
		HttpEntity<Void> req =  DemoServiceUtil.prepareAuthRequest(token);
		Documents documents = restTemplate.exchange(getKYCDocumentURL, HttpMethod.GET, req, Documents.class).getBody();		
		return documents.getDocuments();		
	}
	
	/*
	 *  Get KYC Checks for customer
	 */
	///customers/CUSTOMER_ID/kyc_checks
	public Check getCustomerKYCChecks(String token, String customerID)
	{
		String getKYCChecks = String.format("%s/customers/%s/kyc_checks", apiUrl, customerID);
		HttpEntity<Void> req =  DemoServiceUtil.prepareAuthRequest(token);
		KYCChecks kycChecks = restTemplate.exchange(getKYCChecks, HttpMethod.GET, req, KYCChecks.class).getBody();
		if(kycChecks != null && kycChecks.getChecks()!= null && !kycChecks.getChecks().isEmpty())
		{
			return kycChecks.getChecks().get(0);
		}
		return null;		
	}
	
	

}
