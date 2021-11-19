package account;
import java.util.*;
import transaction.*;
import customer.*;


public class SavingsAccount extends Account{
    private static final double minimumBalance = 500.00;
    private static final double interestRate = 0.07;
    private double interestEarned;
    private double time = 0.0833;
    private double balance;
    private  static int maxTransactionNumber = 6;
    private static double annualInterestRate = 0.01;
    private double principleBalance;
    private double annualInterestEarned;



    public SavingsAccount(String accountNumber, double balance) {
        super(accountNumber, balance);
        this.balance = balance;
        setPrincipleBalance(balance);


    }

    public void setPrincipleBalance(double principleBalance) {
        this.principleBalance = principleBalance;
    }

    public double getPrincipleBalance() {
        return principleBalance;
    }

    public int getMaxTransactionNumber() {
        return maxTransactionNumber;
    }

    public double calculateAnnualInterestEarned(){
        annualInterestEarned=principleBalance*annualInterestRate*1;
        return annualInterestEarned;
    }


}
