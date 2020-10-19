package com.morrisons.services.demo.customer;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.morrisons.services.demo.AbstractTestSupport;
import com.morrisons.services.demo.model.customer.Customer;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerServiceTest extends AbstractTestSupport {
	
	@Autowired
	private CustomerService customerService; 

	@Test
    public void testGetCustomers()
    {
		List<Customer> customerList = customerService.getCustomers(authToken, "obp-bankx-n");
		System.out.println("Customer Size :" + customerList.size());
    }
	
	
}
