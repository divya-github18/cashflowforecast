package cff.cashflowforecast.interfaces;

import java.util.List;
import java.util.UUID;

import cff.cashflowforecast.entity.AccountDetail;
import cff.cashflowforecast.exception.ResourceNotFoundException;

public interface IAccountDetails {
	public List<AccountDetail> getAllAccountDetails();
	public AccountDetail createAccount(AccountDetail accountDetails);
	public void deleteAccount(UUID accountId) throws ResourceNotFoundException;
	public AccountDetail updateAccount(UUID accountId, AccountDetail accountDetails) throws ResourceNotFoundException; 
	

}
