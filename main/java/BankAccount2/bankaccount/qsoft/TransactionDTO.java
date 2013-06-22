package BankAccount2.bankaccount.qsoft;

public class TransactionDTO {
	private String accountNumber;
	private double amount ;
	private long timestamp;
	private String description;
	public TransactionDTO(String accountNumber, double amount,
			String description) {
		this.accountNumber = accountNumber;
		this.amount = amount;
		this.description = description;
		this.timestamp = System.currentTimeMillis();
	}
	public long getTimestamp(){
		return timestamp;
	}
	

}
