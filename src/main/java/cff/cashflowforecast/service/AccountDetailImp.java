package cff.cashflowforecast.service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cff.cashflowforecast.entity.AccountDetail;
import cff.cashflowforecast.exception.ResourceNotFoundException;
import cff.cashflowforecast.interfaces.IAccountDetails;
import cff.cashflowforecast.repository.AccountDetailsRepository;

@Service
@Transactional(rollbackFor = Exception.class)
public class AccountDetailImp implements IAccountDetails{
	@Autowired
	private  AccountDetailsRepository accountDetailRepository;
	
	/**
	 * get all account details
	 */
	public List<AccountDetail> getAllAccountDetails() {
		return (List<AccountDetail>) accountDetailRepository.findAll();
	}

	/**
	 * create account
	 */
	public AccountDetail createAccount(AccountDetail accountDetails) {
		
		accountDetails.setAccountId(UUID.randomUUID());
		accountDetails.setCreatedDate(LocalDate.now());
		accountDetails.setUpdatedDate(LocalDate.now());
		return accountDetailRepository.save(accountDetails);
		
	}

	/**
	 * delete account based on account id
	 */
	public void deleteAccount(UUID accountId) throws ResourceNotFoundException {
		AccountDetail accountdetail = accountDetailRepository.findById(accountId)
				.orElseThrow(() -> new ResourceNotFoundException("Account not found for this id :: " + accountId));
		accountDetailRepository.delete(accountdetail);
	}

	/**
	 * update the account based on account id
	 */
	public AccountDetail updateAccount(UUID accountId, AccountDetail accountDetails) throws ResourceNotFoundException {
		AccountDetail account = accountDetailRepository.findById(accountId)
				.orElseThrow(() -> new ResourceNotFoundException("Account not found for this id :: " + accountId));
		account.setAccountNumber(accountDetails.getAccountNumber());
		account.setUser(accountDetails.getUser());
		account.setTypeOfAccount(accountDetails.getTypeOfAccount());
		account.setStatus(accountDetails.getStatus());
		account.setTypeOfProof(accountDetails.getTypeOfProof());
		account.setCreatedDate(account.getCreatedDate());
		account.setUpdatedDate(LocalDate.now());
		account.setCreatedBy(accountDetails.getCreatedBy());
		account.setUpdatedBy(accountDetails.getUpdatedBy());
		final AccountDetail updatedAccount = accountDetailRepository.save(account);
		return updatedAccount;

	}

}
