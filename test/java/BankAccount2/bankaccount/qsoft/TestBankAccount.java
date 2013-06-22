package BankAccount2.bankaccount.qsoft;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

/**
 * Created with IntelliJ IDEA.
 * User: khangpv
 * Date: 6/18/13
 * Time: 1:47 PM
 * To change this template use File | Settings | File Templates.
 */
public class TestBankAccount {

    BankAccountDAO mockAccountDao = mock(BankAccountDAO.class);
    String accountNumber ="0123456789";
    TransactionDAO mockTransactionDao = mock(TransactionDAO.class);
    @Before
    public void initial(){
        reset(mockAccountDao);
        BankAccount.setBankAccountDAO(mockAccountDao);
        Transaction.setTransactionDAO(mockTransactionDao);
    }

    @Test
    public void testOpenNewAccount(){
    	BankAccountDTO initialAccount =BankAccount.open(accountNumber);
        ArgumentCaptor<BankAccountDTO> savedAccounts = ArgumentCaptor.forClass(BankAccountDTO.class);
        verify(mockAccountDao,times(1)).save(savedAccounts.capture());
        assertEquals(savedAccounts.getValue().getBalance(),0,0.001);
        assertEquals(savedAccounts.getValue().getAccountNumber(),initialAccount.getAccountNumber());
        assertEquals(savedAccounts.getValue().getTimestamp(), initialAccount.getTimestamp());
    }
    @Test
    public void testGetAccount(){
        BankAccountDTO initialAccount =BankAccount.open(accountNumber);
        when(mockAccountDao.getAccount(accountNumber)).thenReturn(initialAccount);
        BankAccountDTO account = BankAccount.getAccount(accountNumber);
        assertEquals(initialAccount,account);
    }
    public void initialAccountForTest(){
    	BankAccountDTO initialAccount = BankAccount.open(accountNumber);
        when(mockAccountDao.getAccount(accountNumber)).thenReturn(initialAccount);
        BankAccount.deposit(accountNumber, 10, "first deposit");
        BankAccount.deposit(accountNumber, 10, "second deposit");
    }
    @Test
    public void testDeposit(){
        initialAccountForTest();
        ArgumentCaptor<BankAccountDTO> savedAccounts = ArgumentCaptor.forClass(BankAccountDTO.class);
        verify(mockAccountDao,times(3)).save(savedAccounts.capture());
        assertEquals(savedAccounts.getValue().getBalance(),20,0.001);
    }
    @Test
    public void testDepositHasTimeStampIntoDB(){
        initialAccountForTest();
        TransactionDTO finalTransaction = BankAccount.deposit(accountNumber, 10, "second deposit");
        when(mockTransactionDao.getTransaction(finalTransaction)).thenReturn(finalTransaction);
        ArgumentCaptor<TransactionDTO> savedAccounts = ArgumentCaptor.forClass(TransactionDTO.class);        
        verify(mockTransactionDao,times(3)).save(savedAccounts.capture());
        assertTrue(0!=savedAccounts.getValue().getTimestamp());
    }
    @Test
    public void testWithdraw(){
    	initialAccountForTest();
        BankAccount.withdraw(accountNumber, -10, "second deposit");
        ArgumentCaptor<BankAccountDTO> savedAccounts = ArgumentCaptor.forClass(BankAccountDTO.class);
        verify(mockAccountDao,times(4)).save(savedAccounts.capture());
        assertEquals(savedAccounts.getValue().getBalance(),10,0.001); 
    }
    @Test
    public void testWithdrawWithTimestamp(){
    	initialAccountForTest();
        TransactionDTO finalTransaction = BankAccount.deposit(accountNumber, 10, "second deposit");
        when(mockTransactionDao.getTransaction(finalTransaction)).thenReturn(finalTransaction);
        ArgumentCaptor<TransactionDTO> savedAccounts = ArgumentCaptor.forClass(TransactionDTO.class);        
        verify(mockTransactionDao,times(3)).save(savedAccounts.capture());
        assertTrue(0!=savedAccounts.getValue().getTimestamp());
    }
    @Test
    public void testgetTransactionsOccurred(){
    	initialAccountForTest();
        List listTransaction = new ArrayList<TransactionDTO>();
        ArgumentCaptor<TransactionDTO> savedAccounts = ArgumentCaptor.forClass(TransactionDTO.class);
        ///verify(mockTransactionDao,times(2)).save(savedAccounts.capture());
        listTransaction = savedAccounts.getAllValues();
        when(mockTransactionDao.getTransactionsOccurred(accountNumber)).thenReturn(listTransaction);        
        List transactionOccurred = Transaction.getTransactionOccurred(accountNumber);        
        assertEquals(transactionOccurred, listTransaction);
    }
    @Test
    public void testGetTransactionInIntervalTime(){
    	initialAccountForTest();
        long startTime = 0l, stopTime = 1000l;        
        BankAccount.getTransactionOccurred(accountNumber,startTime,stopTime);              
        verify(mockTransactionDao).getTransactionsOccurred(accountNumber,startTime,stopTime); 
    }
    @Test
    public void testGetNewestTransaction(){
    	initialAccountForTest();
        BankAccount.getNewestTransaction(accountNumber,5);
        verify(mockTransactionDao).getNewestTransaction(accountNumber,5);
    }

}