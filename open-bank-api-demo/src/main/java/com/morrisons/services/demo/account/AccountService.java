package com.morrisons.services.demo.account;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.morrisons.services.demo.model.account.Account;
import com.morrisons.services.demo.model.account.Accounts;

@Component
public class AccountService {
    @Value("${obp.api.versionedUrl}/my/accounts")
    private String privateAccountUrl;

    @Value("${obp.api.versionedUrl}")
    private String apiUrl;

    private RestTemplate restTemplate = new RestTemplate();

    public List<Account> fetchPrivateAccounts(String token, boolean withDetails) {
        HttpEntity<Void> req = prepareAuthRequest(token);
        Accounts accounts = restTemplate.exchange(privateAccountUrl, HttpMethod.GET, req,  Accounts.class).getBody();

        if(!withDetails) {
            return accounts.getAccounts();
        }

        return accounts.getAccounts().stream().map(account -> {
            String acctDetailsUrl = String.format("%s/my/banks/%s/accounts/%s/account", apiUrl, account.getBankId(), account.getId());
            return restTemplate.exchange(acctDetailsUrl, HttpMethod.GET, req, Account.class).getBody();
        }).collect(Collectors.toList());
    }

    private HttpEntity<Void> prepareAuthRequest(String token) {
        HttpHeaders authHeaders = new HttpHeaders();
        String dlHeader = String.format("DirectLogin token=%s", token);
        authHeaders.add(HttpHeaders.AUTHORIZATION, dlHeader);
        return new HttpEntity<>(null, authHeaders);
    }
}
