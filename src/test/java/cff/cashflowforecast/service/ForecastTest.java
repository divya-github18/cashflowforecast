package cff.cashflowforecast.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;

import cff.cashflowforecast.entity.AccountDetail;
import cff.cashflowforecast.entity.CFUser;
import cff.cashflowforecast.entity.Forecast;
import cff.cashflowforecast.entity.ForecastTransaction;
import cff.cashflowforecast.entity.UserRole;
import cff.cashflowforecast.exception.ResourceNotFoundException;
import cff.cashflowforecast.repository.AccountDetailsRepository;
import cff.cashflowforecast.repository.ForecastRepository;
import cff.cashflowforecast.repository.ForecastTransactionRepository;
import cff.cashflowforecast.repository.UserRepository;

@SpringBootTest
public class ForecastTest {

	@InjectMocks
	CFUserImp cFUserService;
	@Mock
	UserRepository userRepository;
	@InjectMocks
	AccountDetailImp accountDetailService;
	@Mock
	AccountDetailsRepository accountDetailsRepository;
	@InjectMocks
	ForecastImp forecastService;
	@Mock
	ForecastRepository forecastRepository;
	@InjectMocks
	ForecastTransactionImp forecastTransactionService;
	@Mock
	ForecastTransactionRepository forecastTransactionRepository;
	@Autowired
	private KafkaTemplate<String, Object> kafkaTemplate;
	private static final String TOPIC = "test";

	@Test
	/*
	 * get all forecast test case
	 */
	public void getAllForecastTest() {
		List<UserRole> userRole = new ArrayList<UserRole>();
		UUID roleId = UUID.randomUUID();
		UserRole roleInfo = new UserRole(roleId, "manager");
		userRole.add(roleInfo);
		List<CFUser> cfuser = new ArrayList<CFUser>();
		UUID userId = UUID.randomUUID();
		CFUser userInfo = new CFUser(userId, "divya", "divya@gmail.com", roleInfo);
		cfuser.add(userInfo);
		List<AccountDetail> accountDetail = new ArrayList<AccountDetail>();
		UUID accountID = UUID.randomUUID();
		AccountDetail accountInfo = new AccountDetail(accountID, 26877854, userInfo, LocalDate.now(), "divya",
				LocalDate.now(), "divya", "active", "savings", "PAN");
		accountDetail.add(accountInfo);
		List<Forecast> forecast = new ArrayList<Forecast>();
		UUID forecastId = UUID.randomUUID();
		Forecast forecastInfo = new Forecast(forecastId, "loan", "houseloan", "public", LocalDate.now(),
				LocalDate.now(), LocalDate.now(), LocalDate.now(), "divya", "divya", "2w", userInfo, accountInfo);
		Forecast forecastInfo1 = new Forecast(forecastId, "new1", "new1", "public", LocalDate.now(), LocalDate.now(),
				LocalDate.now(), LocalDate.now(), "divya", "divya", "2w", userInfo, accountInfo);
		forecast.add(forecastInfo);
		forecast.add(forecastInfo1);
		Mockito.when(forecastRepository.findAll()).thenReturn(forecast);
		List<Forecast> result = forecastService.getAllForecasts();
		assertEquals(result, forecast);

	}

	/*
	 * create Forecast testcase
	 */
	@Test
	public void createForecastTest() {
		List<UserRole> userRole = new ArrayList<UserRole>();
		UUID roleId = UUID.randomUUID();
		UserRole roleInfo = new UserRole(roleId, "manager");
		userRole.add(roleInfo);
		List<CFUser> cfuser = new ArrayList<CFUser>();
		UUID userId = UUID.randomUUID();
		CFUser userInfo = new CFUser(userId, "divya", "divya@gmail.com", roleInfo);
		cfuser.add(userInfo);
		List<AccountDetail> accountDetail = new ArrayList<AccountDetail>();
		UUID accountID = UUID.randomUUID();
		AccountDetail accountInfo = new AccountDetail(accountID, 26877854, userInfo, LocalDate.now(), "divya",
				LocalDate.now(), "divya", "active", "savings", "PAN");
		accountDetail.add(accountInfo);
		List<Forecast> forecast = new ArrayList<Forecast>();
		UUID forecastId = UUID.randomUUID();
		Forecast forecastInfo = new Forecast(forecastId, "loan", "houseloan", "public", LocalDate.now(),
				LocalDate.now(), LocalDate.now(), LocalDate.now(), "divya", "divya", "2w", userInfo, accountInfo);
		forecast.add(forecastInfo);
		/// Mockito.when(kafkaTemplate.send(TOPIC, forecastInfo)).thenReturn(null);
		forecastService.createForecast(forecastInfo);
		Mockito.verify(forecastRepository).save(forecastInfo);

	}

	/*
	 * get forecasts based on role testcase
	 */
	@Test
	public void getForecastBasedOnRoleTest() throws ResourceNotFoundException {
		List<UserRole> userRole = new ArrayList<UserRole>();
		UUID roleId = UUID.randomUUID();
		UserRole roleInfo = new UserRole(roleId, "manager");
		userRole.add(roleInfo);
		List<CFUser> cfuser = new ArrayList<CFUser>();
		UUID userId = UUID.randomUUID();
		CFUser userInfo = new CFUser(userId, "divya", "divya@gmail.com", roleInfo);
		cfuser.add(userInfo);
		List<AccountDetail> accountDetail = new ArrayList<AccountDetail>();
		UUID accountID = UUID.randomUUID();
		AccountDetail accountInfo = new AccountDetail(accountID, 26877854, userInfo, LocalDate.now(), "divya",
				LocalDate.now(), "divya", "active", "savings", "PAN");
		accountDetail.add(accountInfo);
		List<Forecast> forecast = new ArrayList<Forecast>();
		UUID forecastId = UUID.randomUUID();
		Forecast forecastInfo = new Forecast(forecastId, "loan", "houseloan", "public", LocalDate.now(),
				LocalDate.now(), LocalDate.now(), LocalDate.now(), "divya", "divya", "2w", userInfo, accountInfo);
		forecast.add(forecastInfo);
		Mockito.when(forecastRepository.findForecastForRole("manager")).thenReturn(forecast);
		List<Forecast> result = forecastService.findForecastForRole("manager");
		assertEquals(result, forecast);

	}

	/*
	 * delete forecast based on id testcase
	 */
	@Test
	public void deleteForecastBasedOnIdTest() {
		List<UserRole> userRole = new ArrayList<UserRole>();
		UUID roleId = UUID.randomUUID();
		UserRole roleInfo = new UserRole(roleId, "manager");
		userRole.add(roleInfo);
		List<CFUser> cfuser = new ArrayList<CFUser>();
		UUID userId = UUID.randomUUID();
		CFUser userInfo = new CFUser(userId, "divya", "divya@gmail.com", roleInfo);
		cfuser.add(userInfo);
		List<AccountDetail> accountDetail = new ArrayList<AccountDetail>();
		UUID accountID = UUID.randomUUID();
		AccountDetail accountInfo = new AccountDetail(accountID, 26877854, userInfo, LocalDate.now(), "divya",
				LocalDate.now(), "divya", "active", "savings", "PAN");
		accountDetail.add(accountInfo);
		List<Forecast> forecast = new ArrayList<Forecast>();
		UUID forecastId = UUID.randomUUID();
		Forecast forecastInfo = new Forecast(forecastId, "loan", "houseloan", "public", LocalDate.now(),
				LocalDate.now(), LocalDate.now(), LocalDate.now(), "divya", "divya", "2w", userInfo, accountInfo);
		forecast.add(forecastInfo);
		List<ForecastTransaction> forecastTransaction = new ArrayList<ForecastTransaction>();
		Mockito.when(forecastRepository.findById(forecastId)).thenReturn(Optional.of(forecastInfo));
		Mockito.when(forecastTransactionRepository.findByForecast(forecastInfo)).thenReturn(forecastTransaction);
		forecastRepository.delete(forecastInfo);
		Mockito.verify(forecastRepository).delete(forecastInfo);

	}

	/*
	 * update forecast based on id testcase
	 */
	@Test
	public void updateForecastBasedOnId() {
		List<UserRole> userRole = new ArrayList<UserRole>();
		UUID roleId = UUID.randomUUID();
		UserRole roleInfo = new UserRole(roleId, "manager");
		userRole.add(roleInfo);
		List<CFUser> cfuser = new ArrayList<CFUser>();
		UUID userId = UUID.randomUUID();
		CFUser userInfo = new CFUser(userId, "divya", "divya@gmail.com", roleInfo);
		cfuser.add(userInfo);
		List<AccountDetail> accountDetail = new ArrayList<AccountDetail>();
		UUID accountID = UUID.randomUUID();
		AccountDetail accountInfo = new AccountDetail(accountID, 26877854, userInfo, LocalDate.now(), "divya",
				LocalDate.now(), "divya", "active", "savings", "PAN");
		accountDetail.add(accountInfo);
		List<Forecast> forecast = new ArrayList<Forecast>();
		UUID forecastId = UUID.randomUUID();
		Forecast forecastInfo = new Forecast(forecastId, "loan", "houseloan", "public", LocalDate.now(),
				LocalDate.now(), LocalDate.now(), LocalDate.now(), "divya", "divya", "2w", userInfo, accountInfo);
		forecast.add(forecastInfo);
		Forecast forecastInfo1 = new Forecast(forecastId, "loan", "houseloan", "private", LocalDate.now(),
				LocalDate.now(), LocalDate.now(), LocalDate.now(), "divya", "divya", "2w", userInfo, accountInfo);
		forecast.add(forecastInfo1);
		Mockito.when(forecastRepository.findById(forecastId)).thenReturn(Optional.of(forecastInfo1));
		forecastService.updateForecast(forecastId, forecastInfo1);
		Mockito.verify(forecastRepository).save(forecastInfo1);

	}

}
