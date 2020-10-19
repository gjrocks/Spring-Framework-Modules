package com.morrisons.services.demo.account;

		
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.morrisons.services.demo.*;
import com.morrisons.services.demo.model.account.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountServiceTest extends AbstractTestSupport {

    @Autowired private AccountService accountService;

    @Test
    public void fetchPrivateAccountsNoDetailsOk() {
        //fetch private accounts
        List<Account> privateAccounts = accountService.fetchPrivateAccounts(authToken, false);
        assertTrue(privateAccounts.size() > 0);
    }

   /* @Test
    public void fetchPrivateAccountsWithDetailsOk() {
        //fetch private accounts
        List<Account> privateAccounts = accountService.fetchPrivateAccounts(authToken, true);
        assertTrue(privateAccounts.size() > 0);
        privateAccounts.forEach(privateAccount -> assertNotNull(privateAccount.getBalance()));
    }*/
}