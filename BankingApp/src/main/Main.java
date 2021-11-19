package main;

import account.SavingsAccount;
import bank.*;
import account.*;
import transaction.*;
import loan.*;
import customer.*;
//Laura Solis

public class Main {

	public static void main(String[] args) throws TransactionException {
		// TODO Auto-generated method stub

		Bank bank=new Bank("UE");
		String accountNumber=bank.generateAccountNumber();
		System.out.println(accountNumber);

		Account olisa=new SavingsAccount(accountNumber,5000.0);
		olisa.creditAmount(700);
		olisa.creditAmount(800);


		String accountNumber2=bank.generateAccountNumber();
		System.out.println(accountNumber2);
		Account bryan=new SavingsAccount(accountNumber2,8000.0);

		olisa.transferAmount(bryan,700);

		System.out.println(bryan.getBalance());

		System.out.println();

		System.out.println(olisa.printBankStatement());



		Bank MainBank = new Bank("MainBank");
		Customer cust1 = new Customer("Bryan Morandi", "123 abc Street", "123-456-7891", "bryan@gmail.com");
		MainBank.addCustomer(cust1);
		MainBank.addAccount(bryan);
		cust1.applyForLoan(MainBank, 500000.00);
		cust1.createAccount(bryan);
		cust1.payLoan(1000.00, 0, bryan);
		for (Loan l: MainBank.Loans) {
			System.out.printf("Loan" + l.LoanID + " EMI: $%,.2f\n", l.getEMI());
			System.out.printf("Loan" + l.LoanID + " Prepayment penalty: $%,.2f\n", l.getPrepayment());

		}
		System.out.println("Bryan acct balance before: " + bryan.getBalance());
		cust1.payLoan(1000.00, 0, bryan);
		System.out.println("Bryan acct balance after: " + bryan.getBalance());
		for (Loan l: MainBank.Loans) {
			System.out.printf("Loan" + l.LoanID + " $%,.2f\n", l.Amount);
		}

		System.out.println("****New Checking Account****");
		String checkingAcctNumber = bank.generateAccountNumber();
		CheckingAccount checkingAcct = new CheckingAccount(checkingAcctNumber, 1500);
		System.out.println("Your new debit card number is: " + checkingAcct.generateDebitCardNumber());
		System.out.println("Your new debit card number is: " + checkingAcct.generateDebitCardPin());

		System.out.println("****New Savings Account****");
		String savingsAccountNumber = bank.generateAccountNumber();
		SavingsAccount savings = new SavingsAccount(savingsAccountNumber, 20000);
		System.out.println("Your annual interest earned will be: $" + savings.calculateAnnualInterestEarned());


		//Account  Account= new SavingsAccount(accountNumber, balance, transactions);

	}

}