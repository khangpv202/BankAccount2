package BankAccount2.bankaccount.qsoft;
public class BankAccountDTO {
    private String accountNumber;
    private double balance;
    private Long timestamp;

    public BankAccountDTO(String accountNumber) {
        this.accountNumber= accountNumber;
        balance = 0;
        this.timestamp = System.currentTimeMillis();
    }

    public double getBalance() {
        return balance;  //To change body of created methods use File | Settings | File Templates.
    }

    public String getAccountNumber() {
        return accountNumber;  //To change body of created methods use File | Settings | File Templates.
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
    
    public Long getTimestamp(){
    	return timestamp;
    }
}