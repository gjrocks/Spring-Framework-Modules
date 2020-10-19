package com.ateam.services.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ateam.services.demo.account.AccountService;
import com.ateam.services.demo.authentication.DirectAuthenticationService;
import com.ateam.services.demo.customer.CustomerService;
import com.ateam.services.demo.kyc.KYCService;
import com.ateam.services.demo.model.Client;
import com.ateam.services.demo.model.OBPTransaction;
import com.ateam.services.demo.model.OBPTransactionRaw;
import com.ateam.services.demo.model.TranDetails;
import com.ateam.services.demo.model.account.Account;
import com.ateam.services.demo.model.customer.Customer;
import com.ateam.services.demo.model.kyc.Check;
import com.ateam.services.demo.model.kyc.Document;
import com.ateam.services.demo.model.transaction.Transaction;
import com.ateam.services.demo.transaction.TransactionService;

@RestController
public class OBPController {

	private static final Logger logger = LoggerFactory.getLogger(OBPController.class);

	@Autowired
	AccountService accountService;

	@Autowired
	CustomerService customerService;

	@Autowired
	KYCService kycService;

	@Autowired
	private DirectAuthenticationService directAuthenticationService;

	@Autowired
	TransactionService transactionService;

	@Value("${obp.username}")
	private String username;

	@Value("${obp.password}")
	private String password;

	
	@RequestMapping(value = "/obp/getcustomer", method = RequestMethod.POST)
	public ResponseEntity<Client> getClient(@RequestBody Client client) {

		logger.info("Start of getClient method");

		String authToken = directAuthenticationService.login(username, password);

		if(client.getBankId() != null && client.getFullName() != null)
		{
		List<Customer> customerList = customerService.getCustomers(authToken, client.getBankId());
		checkCustomerAvailableorNotBasedOnCustomerNum(customerList, client);				
		}
		return new ResponseEntity<Client>(client, HttpStatus.OK);

	}

	@RequestMapping(value = "/obp/validateClient", method = RequestMethod.POST)
	public ResponseEntity<Client> validateClient(@RequestBody Client client) {

		logger.info("Start of validateClient method");

		String authToken = directAuthenticationService.login(username, password);

		if (!client.isDetailedKYC()) {
			List<Account> accountList = accountService.fetchPrivateAccounts(authToken, false);

			if (client.getAccountId() != null) {
				checkInputAccountAvailableOrNot(accountList, client);
			}
			else
			{
				logger.error("Account Id is NULL");
			}

			if(client.getBankId() != null && client.getFullName() != null)
			{
			List<Customer> customerList = customerService.getCustomers(authToken, client.getBankId());
			checkCustomerAvailableorNot(customerList, client);				
			}
			else
			{
				logger.error("Bank Id or Full name is NULL");
			}
		} else {
			// call to check Get Customer KYC Documents
			// /customers/CUSTOMER_ID/kyc_documents
			if(client.getCustomerId() != null)
			{
				List<Document> documents = kycService.getCustomerKYCDocuments(authToken, client.getCustomerId());
				Check check = kycService.getCustomerKYCChecks(authToken, client.getCustomerId());
				verifyKYCDocuments(documents, client);
	
				if (check != null) {
					updateKYCCheckDetails(check, client);
				}
			}
			else
			{
				logger.error("CustomerId is NULL");
			}
		}

		return new ResponseEntity<Client>(client, HttpStatus.OK);

	}

	private void checkInputAccountAvailableOrNot(List<Account> accountList, Client client) {

		for (Account account : accountList) {
			if (account.getId().equalsIgnoreCase(client.getAccountId().trim())) {
				client.setAccountAvailable(true);
				break;
			}
		}

	}

	private void checkCustomerAvailableorNot(List<Customer> customerList, Client client) {

		for (Customer customer : customerList) {
			// customer.getCustomerNumber().equalsIgnoreCase(client.getCustomerNumber())
			if (customer.getLegalName().equalsIgnoreCase(client.getFullName())
					|| customer.getLegalName().contains(client.getFullName())) {
				client.setCustomerAvailable(true);
				client.setKYCDone(customer.getKycStatus());
				if (customer.getDateOfBirth() != null) {
					client.setDOBVerificationSuccessful(
							customer.getDateOfBirth().contains(client.getDob()) ? true : false);
				}
				client.setCustomerNumber(customer.getCustomerNumber());
				client.setCustomerId(customer.getCustomerId());
				client.setMobileNumber(customer.getMobilePhoneNumber());
				break;
			}
		}

	}

	private void checkCustomerAvailableorNotBasedOnCustomerNum(List<Customer> customerList, Client client) {

		for (Customer customer : customerList) {
			// customer.getCustomerNumber().equalsIgnoreCase(client.getCustomerNumber())
			if (customer.getCustomerNumber().equalsIgnoreCase(client.getCustomerNumber())
					|| customer.getLegalName().contains(client.getFullName())) {
				client.setCustomerAvailable(true);
				client.setKYCDone(customer.getKycStatus());
				if (customer.getDateOfBirth() != null) {
					client.setDOBVerificationSuccessful(
							customer.getDateOfBirth().contains(client.getDob()) ? true : false);
				}
				client.setCustomerNumber(customer.getCustomerNumber());
				client.setCustomerId(customer.getCustomerId());
				client.setMobileNumber(customer.getMobilePhoneNumber());
				break;
			}
		}

	}
	private void verifyKYCDocuments(List<Document> documents, Client client) {

		for (Document document : documents) {

			if (client.getPassportNumber() != null
					&& client.getPassportNumber().equalsIgnoreCase(document.getNumber())) {
				client.setPassportKYCSuccessful(true);
			} else if (client.getDrivingLicence() != null
					&& client.getDrivingLicence().equalsIgnoreCase(document.getNumber())) {
				client.setDrivingLicenceKYCSuccessful(true);
			}
		}

	}

	private void updateKYCCheckDetails(Check check, Client client) {
		client.setKycDate(check.getDate());
		client.setKycMode(check.getHow());
	}

	@RequestMapping(value = "/obp/transactions", method = RequestMethod.POST)
	public ResponseEntity<OBPTransaction> getTransactions(@RequestBody OBPTransaction obpTransaction) {

		logger.info("Start of getTransactions method");

		String authToken = directAuthenticationService.login(username, password);

		List<Transaction> transaction = transactionService.fetchTransactionList(authToken, obpTransaction.getBankId(),
				obpTransaction.getAccountId());

		List<TranDetails> tranDetails = new ArrayList<>();
 int cnt=1;
		for (Transaction trans : transaction) {
			TranDetails details = new TranDetails();
			details.setSrNo(cnt);
			details.setFromAccountId(trans.getThisAccount().getId());
			details.setToAccountId(trans.getOtherAccount().getId());
			details.setType(trans.getDetails().getType());
			details.setTransactionAmount(trans.getDetails().getValue().getAmount());
			details.setNewBalance(trans.getDetails().getNewBalance().getAmount());
			details.setCurrency(trans.getDetails().getNewBalance().getCurrency());
			details.setCdate(trans.getDetails().getCompleted());
			details.setDesc(trans.getDetails().getDescription());
			if(trans.getDetails().getValue().getAmount().startsWith("-")){
				details.setOutamt(trans.getDetails().getValue().getAmount().substring(1));
			     details.setInamt("0");
			}else{
				details.setOutamt("0");
			     details.setInamt(trans.getDetails().getValue().getAmount());
			}
			
			tranDetails.add(details);
			cnt++;
		}

		obpTransaction.setTranDetails(tranDetails);
		return new ResponseEntity<>(obpTransaction, HttpStatus.OK);
	}

	
	@RequestMapping(value = "/obp/transactionsraw", method = RequestMethod.POST)
	public ResponseEntity<OBPTransactionRaw> getTransactionsRaw(@RequestBody OBPTransactionRaw obpTransaction) {

		logger.info("Start of getTransactionsRaw method");

		String authToken = directAuthenticationService.login(username, password);

		List<Transaction> transaction = transactionService.fetchTransactionList(authToken, obpTransaction.getBankId(),
				obpTransaction.getAccountId());

		/*List<TranDetails> tranDetails = new ArrayList<>();
 int cnt=1;
		for (Transaction trans : transaction) {
			TranDetails details = new TranDetails();
			details.setSrNo(cnt);
			details.setFromAccountId(trans.getThisAccount().getId());
			details.setToAccountId(trans.getOtherAccount().getId());
			details.setType(trans.getDetails().getType());
			details.setTransactionAmount(trans.getDetails().getValue().getAmount());
			details.setNewBalance(trans.getDetails().getNewBalance().getAmount());
			details.setCurrency(trans.getDetails().getNewBalance().getCurrency());
			details.setCdate(trans.getDetails().getCompleted());
			details.setDesc(trans.getDetails().getDescription());
			if(trans.getDetails().getValue().getAmount().startsWith("-")){
				details.setOutamt(trans.getDetails().getValue().getAmount().substring(1));
			     details.setInamt("0");
			}else{
				details.setOutamt("0");
			     details.setInamt(trans.getDetails().getValue().getAmount());
			}
			
			tranDetails.add(details);
			cnt++;
		}*/

		obpTransaction.setTranDetails(transaction);
		return new ResponseEntity<>(obpTransaction, HttpStatus.OK);
	}
}
