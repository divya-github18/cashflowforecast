package cff.cashflowforecast.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import cff.cashflowforecast.entity.AccountDetail;
import cff.cashflowforecast.entity.CFUser;
import cff.cashflowforecast.entity.ForecastTransaction;
import cff.cashflowforecast.entity.Transaction;
import cff.cashflowforecast.entity.UserRole;
import cff.cashflowforecast.repository.ForecastTransactionRepository;
import cff.cashflowforecast.repository.TransactionRepository;

@SpringBootTest
public class TranscationTest {

	@InjectMocks
	TransactionImp transactionService;
	@Mock
	TransactionRepository trnasactionRepository;
	@InjectMocks
	ForecastTransactionImp forecastTransactionService;
	@Mock
	ForecastTransactionRepository forecastTransactionRepository;

	/*
	 * get all utransactions testcase
	 */
	@Test
	public void getAllTransactionTest() {
		UserRole roleInfo = new UserRole(UUID.randomUUID(), "manager");
		CFUser userInfo = new CFUser(UUID.randomUUID(), "divya", "divya@gmail.com", roleInfo);
		List<AccountDetail> accountDetails = new ArrayList<AccountDetail>();
		AccountDetail accountInfo = new AccountDetail(UUID.randomUUID(), 1244123, userInfo, LocalDate.now(), "divya",
				LocalDate.now(), "divya", "active", "savings", "PAN");
		AccountDetail accountInfo1 = new AccountDetail(UUID.randomUUID(), 26877854, userInfo, LocalDate.now(), "divya",
				LocalDate.now(), "divya", "active", "current", "PAN");
		accountDetails.add(accountInfo);
		accountDetails.add(accountInfo1);
		List<Transaction> transactions = new ArrayList<Transaction>();
		UUID transactionId = UUID.randomUUID();
		Date date = new Date(System.currentTimeMillis());
		Transaction trnascationInfo1 = new Transaction(transactionId, "rupee", BigDecimal.valueOf(13234.5), "payable",
				date, "divya", "divya", accountInfo, accountInfo1);
		Transaction trnascationInfo2 = new Transaction(transactionId, "rupee", BigDecimal.valueOf(23234.5), "payable",
				date, "divya", "divya", accountInfo, accountInfo1);
		transactions.add(trnascationInfo1);
		transactions.add(trnascationInfo2);
		Mockito.when(trnasactionRepository.findAll()).thenReturn(transactions);
		List<Transaction> result = transactionService.getAllTransactions();
		assertEquals(transactions, result);

	}

	/*
	 * create transaction testcase
	 */
	@Test
	public void createTransactionTest() {
		UserRole roleInfo = new UserRole(UUID.randomUUID(), "manager");
		CFUser userInfo = new CFUser(UUID.randomUUID(), "divya", "divya@gmail.com", roleInfo);
		List<AccountDetail> accountDetails = new ArrayList<AccountDetail>();
		AccountDetail accountInfo = new AccountDetail(UUID.randomUUID(), 1244123, userInfo, LocalDate.now(), "divya",
				LocalDate.now(), "divya", "active", "savings", "PAN");
		AccountDetail accountInfo1 = new AccountDetail(UUID.randomUUID(), 26877854, userInfo, LocalDate.now(), "divya",
				LocalDate.now(), "divya", "active", "current", "PAN");
		accountDetails.add(accountInfo);
		accountDetails.add(accountInfo1);
		List<Transaction> transactions = new ArrayList<Transaction>();
		UUID transactionId = UUID.randomUUID();
		Date date = new Date(System.currentTimeMillis());
		Transaction trnascationInfo1 = new Transaction(transactionId, "rupee", BigDecimal.valueOf(13234.5), "payable",
				date, "divya", "divya", accountInfo, accountInfo1);
		transactions.add(trnascationInfo1);
		transactionService.createTransaction(trnascationInfo1);
		Mockito.verify(trnasactionRepository).save(trnascationInfo1);

	}

	/*
	 * update transaction testcase
	 */
	@Test
	public void updateTransactionTest() {
		UserRole roleInfo = new UserRole(UUID.randomUUID(), "manager");
		CFUser userInfo = new CFUser(UUID.randomUUID(), "divya", "divya@gmail.com", roleInfo);
		List<AccountDetail> accountDetails = new ArrayList<AccountDetail>();
		AccountDetail accountInfo = new AccountDetail(UUID.randomUUID(), 1244123, userInfo, LocalDate.now(), "divya",
				LocalDate.now(), "divya", "active", "savings", "PAN");
		AccountDetail accountInfo1 = new AccountDetail(UUID.randomUUID(), 26877854, userInfo, LocalDate.now(), "divya",
				LocalDate.now(), "divya", "active", "current", "PAN");
		accountDetails.add(accountInfo);
		accountDetails.add(accountInfo1);
		List<Transaction> transactions = new ArrayList<Transaction>();
		UUID transactionId = UUID.randomUUID();
		Date date = new Date(System.currentTimeMillis());
		Transaction trnascationInfo1 = new Transaction(transactionId, "rupee", BigDecimal.valueOf(13234.5), "payable",
				date, "divya", "divya", accountInfo, accountInfo1);
		transactions.add(trnascationInfo1);
		Mockito.when(trnasactionRepository.findById(transactionId)).thenReturn(Optional.of(trnascationInfo1));
		transactionService.updateTransaction(transactionId, trnascationInfo1);
		Mockito.verify(trnasactionRepository).save(trnascationInfo1);

	}

	/*
	 * delete transaction testcase
	 */
	@Test
	public void deleteTransactionTest() {
		UserRole roleInfo = new UserRole(UUID.randomUUID(), "manager");
		CFUser userInfo = new CFUser(UUID.randomUUID(), "divya", "divya@gmail.com", roleInfo);
		List<AccountDetail> accountDetails = new ArrayList<AccountDetail>();
		AccountDetail accountInfo = new AccountDetail(UUID.randomUUID(), 1244123, userInfo, LocalDate.now(), "divya",
				LocalDate.now(), "divya", "active", "savings", "PAN");
		AccountDetail accountInfo1 = new AccountDetail(UUID.randomUUID(), 26877854, userInfo, LocalDate.now(), "divya",
				LocalDate.now(), "divya", "active", "current", "PAN");
		accountDetails.add(accountInfo);
		accountDetails.add(accountInfo1);
		List<Transaction> transactions = new ArrayList<Transaction>();
		UUID transactionId = UUID.randomUUID();
		Date date = new Date(System.currentTimeMillis());
		Transaction trnascationInfo1 = new Transaction(transactionId, "rupee", BigDecimal.valueOf(13234.5), "payable",
				date, "divya", "divya", accountInfo, accountInfo1);
		transactions.add(trnascationInfo1);
		List<ForecastTransaction> forecastTransaction = new ArrayList<ForecastTransaction>();
		Mockito.when(trnasactionRepository.findById(transactionId)).thenReturn(Optional.of(trnascationInfo1));
		Mockito.when(forecastTransactionRepository.findByTransaction(trnascationInfo1)).thenReturn(forecastTransaction);
		transactionService.deleteTransaction(transactionId);
		Mockito.verify(trnasactionRepository).delete(trnascationInfo1);

	}

}
