package cff.cashflowforecast.service;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import cff.cashflowforecast.entity.Forecast;
import cff.cashflowforecast.entity.ForecastTransaction;
import cff.cashflowforecast.entity.Transaction;
import cff.cashflowforecast.interfaces.ITranscation;
import cff.cashflowforecast.repository.ForecastRepository;
import cff.cashflowforecast.repository.TransactionRepository;
import cff.cashflowforecast.repository.ForecastTransactionRepository;

@Transactional(rollbackFor = Exception.class)
@Service
public class TransactionImp implements ITranscation
{

	@Autowired
	private TransactionRepository transactionRepository;
	private ForecastTransactionRepository forecastTransactionRepository;

	/**
	 * get all the transactions
	 */
	public List<Transaction> getAllTransactions() 
	{
		return  transactionRepository.findAll();
	}
	/**
	 *create a transcation
	 */
	public Transaction createTransaction(Transaction transaction)
	{
		transaction.setTransactionDateAndTime(new Date(System.currentTimeMillis()));
		transaction.setTranscationId(UUID.randomUUID());
		return transactionRepository.save(transaction);
	}
	/**
	 * delete the transaction
	 */
	public void deleteTransaction(UUID transcationId)
	{
		Transaction transaction = transactionRepository.findById(transcationId).orElseThrow();
		List<ForecastTransaction> forecastTransactions=forecastTransactionRepository.findByTransaction(transaction);
		forecastTransactionRepository.deleteAll(forecastTransactions);
		transactionRepository.delete(transaction);
		
	}
	/**
	 *update the transcation
	 */
	public void updateTransaction( UUID transcationId,Transaction transcationDetails)  {
		Transaction transaction = transactionRepository.findById(transcationId).orElseThrow(); 
		transaction.setAmount(transcationDetails.getAmount());
		transaction.setTranscationType(transcationDetails.getTranscationType());
		transaction.setFromAccount(transcationDetails.getFromAccount());
		transaction.setToAccount(transcationDetails.getToAccount());
		transaction.setCurrency(transcationDetails.getCurrency());
		transaction.setCreatedBy(transcationDetails.getCreatedBy());
		transaction.setUpdatedBy(transcationDetails.getUpdatedBy());
		transaction.setTransactionDateAndTime(new Date(System.currentTimeMillis()));
		transactionRepository.save(transaction);
		
	}
}
