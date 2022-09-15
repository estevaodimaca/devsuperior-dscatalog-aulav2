package test.entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import entities.Account;
import test.factory.AccountFactory;

public class AccountTests {
	
	@Test
	public void depositShouldIncreaseBalanceWhenPositiveAmount() {
		//arrange
		double amount = 200.0;
		double expectedValue = 196;
		Account account = AccountFactory.createEmptyAccount();
		
		//action
		account.deposit(amount);
		
		//assert
		Assertions.assertEquals(expectedValue, account.getBalance());
		
		
	}
	
	
	@Test
	public void depositShouldDoNothingeWhenNegativeAmount() {
		//arrange
		double amount = -200.0;
		double expectedValue = 100;
		Account account = AccountFactory.createAccount(expectedValue);
		
		//action
		account.deposit(amount);
		
		//assert
		Assertions.assertEquals(expectedValue, account.getBalance());
		
		
	}

	
	@Test
	public void fullWithdrawShouldClearBalanceAndReturnBalance() {
		//arrange
		double expectedValue = 0.0;
		double initialBalance = 800.0;
		Account account = AccountFactory.createAccount(initialBalance);
		
		//action
		double result = account.fullWithdraw();
		
		//assert
		Assertions.assertTrue(expectedValue == account.getBalance());
		
		Assertions.assertTrue(result == initialBalance);
		
		
	}
	
	
	@Test
	public void withdrawShouldDecreaseBalanceWhenSufficientBalance() {
		//arrange
		double expectedValue = 0.0;
		double initialBalance = 800.0;
		Account account = AccountFactory.createAccount(initialBalance);
		
		//action
		double result = account.fullWithdraw();
		
		//assert
		Assertions.assertTrue(expectedValue == account.getBalance());
		
		Assertions.assertTrue(result == initialBalance);
		
		
	}
}
