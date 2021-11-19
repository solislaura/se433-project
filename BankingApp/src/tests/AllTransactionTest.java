package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import account.Account;
import account.CheckingAccount;
import account.SavingsAccount;
import bank.Bank;
import transaction.Transaction;
import transaction.TransactionException;
import transaction.TransactionType;

public class AllTransactionTest {
	 		@Test
	 		public void  deposit() throws TransactionException{
		 	Account account=new SavingsAccount("000",2000.0);
			
		 	account.creditAmount(1000.0);
	        Double balance=3000.0;
	        Assertions.assertEquals(balance, account.getBalance());
	        
	    }
	 		
	 	
	 	
	 	
	 	 	@Test
		    public void invalidAmount() throws TransactionException{
	 		 
	 		Account account=new SavingsAccount("000",2000.0);
		       Assertions.assertThrows(TransactionException.class, ()->{account.creditAmount(0);});
		       Assertions.assertThrows(TransactionException.class, ()->{account.creditAmount(5000);});
		       Assertions.assertThrows(TransactionException.class, ()->{account.creditAmount(-5);});
		       Assertions.assertThrows(TransactionException.class, ()->{account.creditAmount(5000);});
		       
		    }
		    
		    
		    


		    @Test
		    public void validAmountDeposit(){
		    	Account account=new SavingsAccount("000",2000.0);
		    	Assertions.assertDoesNotThrow(()->{account.creditAmount(1);});
		    	Assertions.assertDoesNotThrow(()->{account.creditAmount(2999);});
		    	
		    }
		    
		    
		    
		    
		    
		    @Test
		    public void PrintStatementDeposit() throws TransactionException {
		    	Account account=new SavingsAccount("000",2000.0);
		    	 Transaction transaction=account.creditAmount(1000.0);
		    	 Assertions.assertEquals(transaction.printTransaction(),"Transaction type: "+TransactionType.DEPOSIT+ " transactionId: "+transaction.getTransactionId()+" amount: "+1000.0+" balanceAfterTransaction: "+account.getBalance());
			      
		    }
		    
		    
		    
		    @Test
		    public void  recieve() throws TransactionException{
		    	Account olisa=new SavingsAccount("000",2000.0);
		        Account laura=new SavingsAccount("001",1000.0);
		        laura.Recieve(olisa, 1000);
		        
		        Double lauraBalance=2000.0;

		        Assertions.assertEquals(lauraBalance,laura.getBalance(),0);
		        
		        

		        

		    }
		    
		    
		    @Test
		    public void printTransationRecieve() {
		    Account olisa=new SavingsAccount("000",2000.0);
		    Account laura=new SavingsAccount("001",1000.0);
		    	
		   Transaction transaction=laura.Recieve(olisa, 1000);
		    	
		   String statement="Transaction type: "+TransactionType.RECIEVE+ " transactionId: "+transaction.getTransactionId()+" amount: "+1000.0+" sent from "+olisa.getAccountNumber()+" balance after transaction: "+laura.getBalance();
		   
		   Assertions.assertEquals(transaction.printTransaction(), statement);
		    	
		    }
		    
		    
		    @Test
		    public void  transfer() throws TransactionException{
		    	Bank bank=new Bank("BOA");
		    	Account olisa=new SavingsAccount(bank.generateAccountNumber(),2000.0);
		    	Account laura=new SavingsAccount(bank.generateAccountNumber(),1000.0);

		        olisa.transferAmount(laura, 1000.0);
		        Double olisaBalance=1000.0;
		        Double lauraBalance=2000.0;


		        Assertions.assertEquals(olisaBalance,olisa.getBalance(),0);
		        Assertions.assertEquals(lauraBalance,laura.getBalance(),0);
		    }


		    @Test
		    public void  invalidTransfer() throws TransactionException{
		    Bank bank=new Bank("BOA");
		    Account olisa=new SavingsAccount(bank.generateAccountNumber(),2000.0);
		    Account laura=new SavingsAccount(bank.generateAccountNumber(),1000.0);
		    Assertions.assertThrows(TransactionException.class, ()->{olisa.transferAmount(laura, 2050.0);});
		    Assertions.assertThrows(TransactionException.class, ()->{olisa.transferAmount(laura, 0);});
		    
		    }


		    @Test
		    public void  validTransfer() throws TransactionException{
		    	 Bank bank=new Bank("BOA");
				 Account olisa=new SavingsAccount(bank.generateAccountNumber(),2000.0);
				 Account laura=new SavingsAccount(bank.generateAccountNumber(),1000.0);
				 Assertions.assertDoesNotThrow(()->{olisa.transferAmount(laura, 2000.0);});
		    	
		    
		    }
		    
		    
		    @Test
		    public void invalidAmountWithdrawal() throws TransactionException{
		    	Bank bank=new Bank("BOA");
				Account account=new CheckingAccount(bank.generateAccountNumber(),5000.0);
				
		       Assertions.assertThrows(TransactionException.class, ()->{account.debitAmount(0);});
		       Assertions.assertThrows(TransactionException.class, ()->{account.debitAmount(5001);});
		       Assertions.assertThrows(TransactionException.class, ()->{account.debitAmount(5000);});
		    }



		    @Test
		    public void InsufficientBalanceBeforeWithdrawal() throws TransactionException{
		    	Bank bank=new Bank("BOA");
				Account account=new CheckingAccount(bank.generateAccountNumber(),2000.0);
		        Assertions.assertThrows(TransactionException.class, ()->{account.debitAmount(2500);});
		       
		       
		    }

		    @Test
		    public void validAmountWithdrawal(){
		    	
		    	Bank bank=new Bank("BOA");
				Account account=new CheckingAccount(bank.generateAccountNumber(),2000.0);
		    	Assertions.assertDoesNotThrow(()->{account.debitAmount(1);});
		    	Assertions.assertDoesNotThrow(()->{account.debitAmount(999);});
		    }
		    
		    
		    @Test
		    public void PrintStatementWithdrawal() throws TransactionException {
		    	Bank bank=new Bank("BOA");
				Account account=new CheckingAccount(bank.generateAccountNumber(),2000.0);
		    	 Transaction transaction=account.creditAmount(1000.0);
		    	 Assertions.assertEquals(transaction.printTransaction(),"Transaction type: "+TransactionType.DEPOSIT+ " transactionId: "+transaction.getTransactionId()+" amount: "+1000.0+" balanceAfterTransaction: "+account.getBalance());
			      
		    }
		    
		    
		    @Test
			 public void printStatements() throws TransactionException {
		    	Account olisa=new SavingsAccount("0",5000.0);
		    	Account laura=new SavingsAccount("1",5000.0);
				Transaction transaction1=olisa.debitAmount(1000.0);
				transaction1.setTransactionId(1000);
				Transaction transaction2=olisa.creditAmount(500);
				transaction2.setTransactionId(1001);
				Transaction transaction3=olisa.transferAmount(laura, 500);
				transaction3.setTransactionId(1002);
				 
				 String statement="Transaction type: WITHDRAW transactionId: 1000 amount: 1000.0 balanceAfterTransaction: 4000.0"+"\n"+
						"Transaction type: DEPOSIT transactionId: 1001 amount: 500.0 balanceAfterTransaction: 4500.0"+"\n"+
						"Transaction type: TRANSFER transactionId: 1002 amount: 500.0 sent to 1 balance after transaction: 4000.0"+"\n";
				 Assertions.assertEquals(statement, olisa.printBankStatement());
				 
			 }
			 
			 
			 
			 
		    
			 @Test
			 public void noPrintStatements() throws TransactionException {
				 
				 Account olisa=new SavingsAccount("0",5000.0);
				 Assertions.assertEquals("You have no previous Transactions", olisa.printBankStatement());
				 
			 }
	 
	 
	 

}
