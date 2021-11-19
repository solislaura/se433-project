package transaction;

public class DepositTransaction  extends Transaction{

    public  DepositTransaction(Double amount){
        this.transactionType=TransactionType.DEPOSIT;
        this.amount=amount;
        this.transactionId=Transaction.ID;
        Transaction.ID++;
        
    }

    @Override
    public Double apply(Double balance) {
    
        Double newBalance=balance+amount;
        this.balanceAfterTransaction=newBalance;
        System.out.println(printTransaction());
        return newBalance;
    }

   
    
}
