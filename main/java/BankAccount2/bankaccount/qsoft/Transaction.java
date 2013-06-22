package BankAccount2.bankaccount.qsoft;

import java.util.List;

public class Transaction {
	private static TransactionDAO transactionDAO;
	
	public static void save(TransactionDTO transaction) {
		// TODO Auto-generated method stub
		transactionDAO.save(transaction);
	}

	public static void setTransactionDAO(TransactionDAO transactionDao) {
		transactionDAO= transactionDao;
		
	}

	public static List getTransactionOccurred(String accountNumber) {
		// TODO Auto-generated method stub
		return transactionDAO.getTransactionsOccurred(accountNumber);
	}

	public static void getTransactionOccurred(String accountNumber,
			long startTime, long stopTime) {
		// TODO Auto-generated method stub
		transactionDAO.getTransactionsOccurred(accountNumber, startTime, stopTime);
	}

	public static void getNewestTransaction(String accountNumber,
			int numberOfTransaction) {
		transactionDAO.getNewestTransaction(accountNumber, numberOfTransaction);
		
	}

}
