package transaction;
import account.*;

public class RecieveTransaction extends Transaction{
    private Account fromAccount;


    public  RecieveTransaction(Double amount,Account fromAccount){
        this.transactionType=TransactionType.RECIEVE;
        this.amount=amount;
        this.transactionId=Transaction.ID;
        this.fromAccount=fromAccount;
        Transaction.ID++;
        
    }


    @Override
    public Double apply(Double balance) {
        // TODO Auto-generated method stub
        Double newBalance=balance+amount;
        this.balanceAfterTransaction=newBalance;
        return newBalance;
    }


    public  String printTransaction(){
    	

        return "Transaction type: "+transactionType+ " transactionId: "+transactionId+" amount: "+amount+" sent from "+fromAccount.getAccountNumber()+" balance after transaction: "+balanceAfterTransaction
                
        ;
       
   };
    
}
