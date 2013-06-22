package BankAccount2.bankaccount.qsoft;


/**
 * Created with IntelliJ IDEA.
 * User: khangpv
 * Date: 6/18/13
 * Time: 1:51 PM
 * To change this template use File | Settings | File Templates.
 */
public class BankAccount {
    private static  BankAccountDAO accountDAO ;

    public static BankAccountDTO open(String accountNumber) {
        BankAccountDTO account = new BankAccountDTO(accountNumber);
        accountDAO.save(account);
        return account;
    }

    public static void setBankAccountDAO(BankAccountDAO bankAccountDao) {
        accountDAO= bankAccountDao;
    }

    public static BankAccountDTO getAccount(String accountNumber) {
        return accountDAO.getAccount(accountNumber);  //To change body of created methods use File | Settings | File Templates.
    }

    public static TransactionDTO deposit(String accountNumber, double amount, String description) {
        return doTransaction(accountNumber, amount, description);
    }

	public static TransactionDTO withdraw(String accountNumber, int amount, String description) {
		// TODO Auto-generated method stub
		return doTransaction(accountNumber, amount, description);
	}
	private static TransactionDTO doTransaction(String accountNumber,double amount, String description){
		BankAccountDTO tmp = accountDAO.getAccount(accountNumber);
        double balance = tmp.getBalance()+amount;
        long timestamp = System.currentTimeMillis();
        tmp.setBalance(balance);
        accountDAO.save(tmp);
        TransactionDTO transaction = new TransactionDTO(accountNumber,amount,description);
        Transaction.save(transaction);
        return transaction;
	}

	public static void getTransactionOccurred(String accountNumber,
			long startTime, long stopTime) {
		// TODO Auto-generated method stub
		Transaction.getTransactionOccurred(accountNumber,startTime,stopTime);
	}

	public static void getNewestTransaction(String accountNumber, int numberOfTransaction) {
		Transaction.getNewestTransaction(accountNumber,numberOfTransaction);
		
	}
}