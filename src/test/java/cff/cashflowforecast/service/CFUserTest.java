package cff.cashflowforecast.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import cff.cashflowforecast.entity.CFUser;
import cff.cashflowforecast.entity.UserRole;
import cff.cashflowforecast.repository.UserRepository;

@SpringBootTest
public class CFUserTest {
	@InjectMocks
	CFUserImp cfUserService;
	@Mock
	UserRepository userRepository;

	/*
	 * get all users testcase
	 */
	@Test
	public void getAllCfUserTest() {
		List<UserRole> userRole = new ArrayList<UserRole>();
		UUID roleId = UUID.randomUUID();
		UserRole roleinfo1 = new UserRole(roleId, "manager");
		userRole.add(roleinfo1);
		List<CFUser> users = new ArrayList<CFUser>();
		UUID userId = UUID.randomUUID();
		CFUser userInfo1 = new CFUser(userId, "divya", "divya@gmail.com", roleinfo1);
		UUID userId1 = UUID.randomUUID();
		CFUser userInfo2 = new CFUser(userId1, "navya", "navya@gmail.com", roleinfo1);
		users.add(userInfo1);
		users.add(userInfo2);
		Mockito.when(userRepository.findAll()).thenReturn(users);
		List<CFUser> result = cfUserService.getAllUsers();
		assertEquals(users, result);

	}

	/**
	 * create users testcase
	 */
	@Test
	public void createUserTest() {
		List<UserRole> userRole = new ArrayList<UserRole>();
		UUID roleId = UUID.randomUUID();
		UserRole roleinfo1 = new UserRole(roleId, "manager");
		userRole.add(roleinfo1);
		UUID userId = UUID.randomUUID();
		CFUser userInfo = new CFUser(userId, "divya", "divya@gmail.com", roleinfo1);
		cfUserService.createCfUser(userInfo);
		Mockito.verify(userRepository).save(userInfo);
	}

	/*
	 * get user by id testcase
	 */
	@Test
	public void getUserById() {

		UUID roleId = UUID.randomUUID();
		UserRole roleinfo1 = new UserRole(roleId, "manager");
		UUID userId = UUID.randomUUID();
		CFUser userInfo = new CFUser(userId, "divya", "divya@gmail.com", roleinfo1);
		Mockito.when(userRepository.findById(userId)).thenReturn(Optional.of(userInfo));
		CFUser result = cfUserService.getUserById(userId);
		assertEquals(userInfo, result);
	}

	/*
	 * update users testcase
	 */
	@Test
	public void updateUserTest() {
		UUID roleId = UUID.randomUUID();
		UserRole roleinfo1 = new UserRole(roleId, "manager");
		UUID userId = UUID.randomUUID();
		CFUser userInfo = new CFUser(userId, "divya", "divya@gmail.com", roleinfo1);
		Mockito.when(userRepository.findById(userId)).thenReturn(Optional.of(userInfo));
		cfUserService.updateUser(userId, userInfo);
		Mockito.verify(userRepository).save(userInfo);
	}

	/*
	 * delete based on id users testcase
	 */
	@Test
	public void deleteUserTest() {
		UUID roleId = UUID.randomUUID();
		UserRole roleinfo1 = new UserRole(roleId, "manager");
		UUID userId = UUID.randomUUID();
		CFUser userInfo = new CFUser(userId, "divya", "divya@gmail.com", roleinfo1);
		Mockito.when(userRepository.findById(userId)).thenReturn(Optional.of(userInfo));
		cfUserService.deleteUser(userId);
		Mockito.verify(userRepository).delete(userInfo);

	}

}
