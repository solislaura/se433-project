package account;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import transaction.*;
import customer.*;
import bank.*;


public abstract class Account {
    private String accountNumber;
    private double balance;
    private double minimumBalance;
    private ArrayList<Transaction> transactions=new ArrayList<>();
    private double interestRate;
    private  double interestEarned;
    public static final double time = 0.0833;
    public static final int max = 5000;
    private  int countTransactions;
    private String openingDate;

    public double getBalance() {
        return balance;
    }

    public double getMinimumBalance() {
        return minimumBalance;
    }

    private ArrayList<Customer> customers;


    public Account(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;

       customers = new ArrayList<>();
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public Transaction debitAmount(double debit) throws TransactionException {
    	checkBalance(debit);
        validateAmount(debit);
        Transaction transaction =new WithdrawTransaction(debit);
        this.balance=transaction.apply(balance);
        transactions.add(transaction);
        
        
        return transaction;


    }
    public Transaction transferAmount(Account toAccount, double transferAmount) throws TransactionException {
        checkBalance(transferAmount);
        validateAmount(transferAmount);
        Transaction transaction=new TransferTransaction(transferAmount, toAccount,this);
        this.balance=transaction.apply(balance);
        transactions.add(transaction);
        countTransactions++;
        
        return transaction;
        
    }

    public Transaction Recieve(Account fromAccount,double debit){
        
        Transaction transaction =new RecieveTransaction(debit, fromAccount);
        this.balance=transaction.apply(balance);
        transactions.add(transaction);
        
        return transaction;
    }


    public Transaction creditAmount(double credit) throws TransactionException {
        validateAmount(credit);
        Transaction transaction =new DepositTransaction(credit);
        this.balance=transaction.apply(balance);
        transactions.add(transaction);
        countTransactions++;
        return transaction;

    }


    public double calculateInterestEarned() {
        interestEarned = balance * interestRate * time;
        return interestEarned;
    }

    public String getOpeningDate() {
        return openingDate;
    }

    public void setOpeningDate(String openingDate) {
        this.openingDate = openingDate;
    }

    public double addInterest() {
        balance += interestEarned;
        return balance;
    }

    public String printBankStatement(){
        String statement="";
        for(Transaction t:transactions){
            statement+=t.printTransaction()+"\n";

        }
        
        return statement.length()!=0 ?statement:"You have no previous Transactions";
          
    }
   
    public void validateAmount(Double amount) throws TransactionException{
        if(amount<=0){
            throw  new TransactionException("Transaction amount has to be greater than 0");
        }

        if(amount>=max){
            throw  new TransactionException("Transaction amount has to be less Than " + max);
        }
            

    }

    public void checkBalance(Double amount) throws TransactionException{
        if(amount>balance){
            throw  new TransactionException("Insufficient funds");
        }


    }
}
