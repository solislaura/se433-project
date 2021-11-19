package transaction;

public class WithdrawTransaction  extends Transaction{
  

    
    public WithdrawTransaction(Double amount) {
       this.transactionType=TransactionType.WITHDRAW;
       this.amount=amount;
       this.transactionId=Transaction.ID;
        Transaction.ID++;
    }


    @Override
    public Double apply(Double balance) {
    
        Double newBalance=balance-amount;
        this.balanceAfterTransaction=newBalance;

        System.out.println(printTransaction());
        return newBalance;
    
    }
    
    


    
}
