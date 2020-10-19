package com.morrisons.services.demo.transaction;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.morrisons.services.demo.model.account.Account;
import com.morrisons.services.demo.model.transaction.Transaction;
import com.morrisons.services.demo.model.transaction.Transactions;
import com.morrisons.services.demo.util.DemoServiceUtil;



@Component
public class TransactionService {

    @Value("${obp.api.versionedUrl}")
    private String apiUrl;

    private RestTemplate restTemplate = new RestTemplate();

    public List<Transaction> fetchTransactionList(String token, String bankId, String getId) {
        String allTransactionsUrl = String.format("%s/banks/%s/accounts/%s/owner/transactions", apiUrl, bankId, getId);
        HttpEntity<Void> req = DemoServiceUtil.prepareAuthRequest(token);
        Transactions transactions = restTemplate.exchange(allTransactionsUrl, HttpMethod.GET, req, Transactions.class).getBody();
        return transactions.getTransactions();
    }
	
}
