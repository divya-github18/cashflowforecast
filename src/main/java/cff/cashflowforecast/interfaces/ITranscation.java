package cff.cashflowforecast.interfaces;

import java.util.List;
import java.util.UUID;

import cff.cashflowforecast.entity.Transaction;

public interface ITranscation {
	public List<Transaction> getAllTransactions();
	public Transaction createTransaction(Transaction transaction);
	public void deleteTransaction(UUID transcationId);
	public void updateTransaction( UUID transcationId,Transaction transcationDetails);
	

}
