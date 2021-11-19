package transaction;

public abstract class Transaction {
    protected int  transactionId ;
    protected  Double amount;
    protected  TransactionType transactionType;
    protected  static int ID=1000;
    protected  Double balanceAfterTransaction;
    



   public  String printTransaction(){

        return "Transaction type: "+transactionType+ " transactionId: "+transactionId+" amount: "+amount+" balanceAfterTransaction: "+balanceAfterTransaction;
       
   };

    public abstract Double apply(Double balance);


   


	public int getTransactionId() {
		return transactionId;
		
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	public TransactionType getTransactionType() {
		return transactionType;
	}

	

	
	
    
   



    
}
