package tests;
import account.Account;
import account.CheckingAccount;
import account.SavingsAccount;
import bank.Bank;
import customer.Customer;
import transaction.TransactionException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Date;

//import static org.junit.Assert.assertEquals;

public class LauraTest {
    private Bank testBank;
    private SavingsAccount savings;
    private Account checking;
    private String openingDate;
    private CheckingAccount checking2;

    @BeforeEach
    public void setup() {
        testBank = new Bank("Test Bank");
        savings = new SavingsAccount(testBank.generateAccountNumber(), 10000.00);
        savings.setOpeningDate("11-19-2021");
        checking =  new CheckingAccount(testBank.generateAccountNumber(), 5000.00);
        checking2 = new CheckingAccount(testBank.generateAccountNumber(), 750);

    }

    @Test
    public void getInterestRateTest() {
        double interestExpected = 0.07;
        Assertions.assertEquals(interestExpected, savings.getInterestRate());
    }

    @Test
    public void testMinimumBalance() {
        double minBalance = 250;
        Assertions.assertEquals(minBalance, checking.getMinimumBalance());
    }

    @Test
    public void testMaxTransactionNumber() {
        int expectedNumber = 6;
        Assertions.assertEquals(expectedNumber, savings.getMaxTransactionNumber());
    }

   //Debit with sufficient funds
    @Test
    public void debitAmountTest1() throws TransactionException {
        checking.debitAmount(1000);
        double expected = 4000;
        Assertions.assertEquals(expected, checking.getBalance());
    }

    //Debit with insufficient funds
    @Test
    public void debitAmountTest2() {
        Exception exception = Assertions.assertThrows(TransactionException.class, () -> checking.debitAmount(5001));
        String expected = "Insufficient funds";
        String actual = exception.getMessage();
        Assertions.assertTrue(actual.contains(expected));
    }

    //Testing with sufficient funds
    @Test
    public void testTransferAmount1() throws TransactionException {
        savings.transferAmount(checking, 500);
        double expectedCheckingBalance = 5500;
        double expectedSavingsBalance = 9500;
        Assertions.assertEquals(expectedCheckingBalance, checking.getBalance());
        Assertions.assertEquals(expectedSavingsBalance, savings.getBalance());

    }

    //Testing with sufficient funds
    @Test
    public void testTransferAmount2() throws TransactionException {
        checking.transferAmount(savings, 500);
        double expectedCheckingBalance = 4500;
        double expectedSavingsBalance = 10500;
        Assertions.assertEquals(expectedCheckingBalance, checking.getBalance());
        Assertions.assertEquals(expectedSavingsBalance, savings.getBalance());

    }

    //Test without sufficient funds
    @Test
    public void testTransferAmount3() {
    	
        Exception exception = Assertions.assertThrows(TransactionException.class, () -> checking.transferAmount(savings, 2000500));
        String expected = "Insufficient funds";
        String actual = exception.getMessage();
        Assertions.assertTrue(actual.contains(expected));
    }

    //Test without sufficient funds
    @Test
    public void testTransferAmount4() {
        Exception exception = Assertions.assertThrows(TransactionException.class, () -> savings.transferAmount(checking, 2000500));
        String expected = "Insufficient funds";
        String actual = exception.getMessage();
        Assertions.assertTrue(actual.contains(expected));
    }

    @Test
    public void testOpeningDate() {
        String expected = "11-19-2021";
        Assertions.assertEquals(expected, savings.getOpeningDate());
    }

    @Test
    public void testCalculateInterestEarned1() {
        double expected = 10000*0.07*0.0833;
        Assertions.assertEquals(expected, savings.calculateInterestEarned());
    }
    @Test
    public void testCalculateInterestEarned2() {
        double expected = 5000*0.001*0.0833;
        Assertions.assertEquals(expected, checking.calculateInterestEarned());
    }

    @Test
    public void testAddInterest() {
        double expected = 5000+(5000*0.001*0.0833);
        double actual = checking.calculateInterestEarned() + checking.addInterest();
        Assertions.assertEquals(expected,actual);
    }


}
