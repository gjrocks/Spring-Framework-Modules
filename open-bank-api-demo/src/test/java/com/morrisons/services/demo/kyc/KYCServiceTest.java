package com.morrisons.services.demo.kyc;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.morrisons.services.demo.AbstractTestSupport;
import com.morrisons.services.demo.model.kyc.Check;
import com.morrisons.services.demo.model.kyc.Document;
import com.morrisons.services.demo.model.kyc.Documents;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KYCServiceTest extends AbstractTestSupport {

	@Autowired
	private KYCService kycService;

	@Test
	public void testGetCustomerKYCDocuments() {
		List<Document> documents = kycService.getCustomerKYCDocuments(authToken,
				"4ea21804-0078-4113-bb9a-9890b1b564fc");
		assertNotNull(documents);
	}

//	@Test
	public void testGetCustomerKYCChecks() {
		Check check = kycService.getCustomerKYCChecks(authToken, "4ea21804-0078-4113-bb9a-9890b1b564fc");
		assertNotNull(check);
	}

}
