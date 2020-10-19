package com.morrisons.services.demo.customer;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.morrisons.services.demo.model.customer.Customer;
import com.morrisons.services.demo.model.customer.Customers;

@Component
public class CustomerService {

	@Value("${obp.api.versionedUrl}")
	private String apiUrl;

	private RestTemplate restTemplate = new RestTemplate();

	/**
	 * Get Customers for current user at Bank
	 * 
	 * @return
	 */
	public List<Customer> getCustomers(String token, String bankId) {
		String getCustomerUrl = String.format("%s/banks/%s/customers", apiUrl, bankId);
		HttpEntity<Void> req = prepareAuthRequest(token);
		Customers customers = restTemplate.exchange(getCustomerUrl, HttpMethod.GET, req, Customers.class).getBody();
		return customers.getCustomers();
	}

	private HttpEntity<Void> prepareAuthRequest(String token) {
		HttpHeaders authHeaders = new HttpHeaders();
		String dlHeader = String.format("DirectLogin token=%s", token);
		authHeaders.add(HttpHeaders.AUTHORIZATION, dlHeader);
		return new HttpEntity<>(null, authHeaders);
	}

}
