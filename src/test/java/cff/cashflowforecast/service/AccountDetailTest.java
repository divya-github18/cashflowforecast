package cff.cashflowforecast.service;

import java.time.LocalDate;
import java.util.ArrayList;
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
import cff.cashflowforecast.entity.UserRole;
import cff.cashflowforecast.exception.ResourceNotFoundException;
import cff.cashflowforecast.repository.AccountDetailsRepository;
import cff.cashflowforecast.repository.UserRepository;

@SpringBootTest
public class AccountDetailTest {

	@InjectMocks
	AccountDetailImp accountDetailService;
	@Mock
	AccountDetailsRepository accountDetailsRepository;

	/*
	 * get all account details testcase
	 */
	@Test
	public void getAllAccountDetailsTest() {

		UserRole roleInfo = new UserRole(UUID.randomUUID(), "manager");
		CFUser userInfo = new CFUser(UUID.randomUUID(), "divya", "divya@gmail.com", roleInfo);
		List<AccountDetail> accountDetails = new ArrayList<AccountDetail>();
		AccountDetail accountInfo = new AccountDetail(UUID.randomUUID(), 1244123, userInfo, LocalDate.now(), "divya",
				LocalDate.now(), "divya", "active", "savings", "PAN");
		AccountDetail accountInfo1 = new AccountDetail(UUID.randomUUID(), 26877854, userInfo, LocalDate.now(), "divya",
				LocalDate.now(), "divya", "active", "current", "PAN");
		accountDetails.add(accountInfo);
		accountDetails.add(accountInfo1);

	}

	/*
	 * create account details testcase
	 */
	@Test
	public void createAccountDetailTest() {
		UserRole roleInfo = new UserRole(UUID.randomUUID(), "manager");
		CFUser userInfo = new CFUser(UUID.randomUUID(), "divya", "divya@gmail.com", roleInfo);
		List<AccountDetail> accountDetails = new ArrayList<AccountDetail>();
		AccountDetail accountInfo = new AccountDetail(UUID.randomUUID(), 1244123, userInfo, LocalDate.now(), "divya",
				LocalDate.now(), "divya", "active", "savings", "PAN");
		accountDetails.add(accountInfo);
		accountDetailService.createAccount(accountInfo);
		Mockito.verify(accountDetailsRepository).save(accountInfo);

	}

	/*
	 * update account details testcase
	 */
	@Test
	public void updateAccountDetailTest() throws ResourceNotFoundException {
		UserRole roleInfo = new UserRole(UUID.randomUUID(), "manager");
		CFUser userInfo = new CFUser(UUID.randomUUID(), "divya", "divya@gmail.com", roleInfo);
		List<AccountDetail> accountDetails = new ArrayList<AccountDetail>();
		AccountDetail accountInfo = new AccountDetail(UUID.randomUUID(), 1244123, userInfo, LocalDate.now(), "divya",
				LocalDate.now(), "divya", "active", "savings", "PAN");
		accountDetails.add(accountInfo);
		Mockito.when(accountDetailsRepository.findById(accountInfo.getAccountId()))
				.thenReturn(Optional.of(accountInfo));
		accountDetailService.updateAccount(accountInfo.getAccountId(), accountInfo);
		Mockito.verify(accountDetailsRepository).save(accountInfo);

	}

	/*
	 * delete account details testcase
	 */
	public void deleteAccountDetailTest() throws ResourceNotFoundException {
		UserRole roleInfo = new UserRole(UUID.randomUUID(), "manager");
		CFUser userInfo = new CFUser(UUID.randomUUID(), "divya", "divya@gmail.com", roleInfo);
		List<AccountDetail> accountDetails = new ArrayList<AccountDetail>();
		AccountDetail accountInfo = new AccountDetail(UUID.randomUUID(), 1244123, userInfo, LocalDate.now(), "divya",
				LocalDate.now(), "divya", "active", "savings", "PAN");
		accountDetails.add(accountInfo);
		Mockito.when(accountDetailsRepository.findById(accountInfo.getAccountId()))
				.thenReturn(Optional.of(accountInfo));
		accountDetailService.deleteAccount(accountInfo.getAccountId());
		Mockito.verify(accountDetailsRepository).delete(accountInfo);

	}

}
