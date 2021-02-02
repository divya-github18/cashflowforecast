package cff.cashflowforecast.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import cff.cashflowforecast.entity.CFUser;
import cff.cashflowforecast.entity.UserRole;
import cff.cashflowforecast.exception.ResourceNotFoundException;
import cff.cashflowforecast.service.CFUserImp;


@SpringBootTest
public class CUserTest {

	@InjectMocks
	CfUserController cfuserController;
	@Mock
	CFUserImp cfuserService;
	@Test
	public void getAllRolesTest() {	
		List<UserRole> userRole=new ArrayList<UserRole>();
		UUID roleId=UUID.randomUUID();
		UserRole roleinfo1=new UserRole(roleId,"manager");
		userRole.add(roleinfo1);
		List<CFUser> users=new ArrayList<CFUser>();
		UUID userId=UUID.randomUUID();
		CFUser userInfo1=new CFUser(userId,"divya","divya@gmail.com",roleinfo1);
		UUID userId1=UUID.randomUUID();
		CFUser userInfo2=new CFUser(userId1,"navya","navya@gmail.com",roleinfo1);
		users.add(userInfo1);
		users.add(userInfo2);
		Mockito.when(cfuserService.getAllUsers()).thenReturn(users);
		List<CFUser> result=cfuserController.getAllUsers();
		assertEquals(users, result);


	}
	@Test
	public void createUserTest()
	{
		List<UserRole> userRole=new ArrayList<UserRole>();
		UUID roleId=UUID.randomUUID();
		UserRole roleinfo1=new UserRole(roleId,"manager");
		userRole.add(roleinfo1);
		UUID userId=UUID.randomUUID();
		CFUser userInfo=new CFUser(userId,"divya","divya@gmail.com",roleinfo1);
		 cfuserController.createCfUser(userInfo);
		 Mockito.verify(cfuserService).createCfUser(userInfo);
	}
	@Test
	public void getUserById() 
	{

		UUID roleId=UUID.randomUUID();
		UserRole roleinfo1=new UserRole(roleId,"manager");
		UUID userId=UUID.randomUUID();
		CFUser userInfo=new CFUser(userId,"divya","divya@gmail.com",roleinfo1);
		Mockito.when(cfuserService.getUserById(userId)).thenReturn(userInfo);
		CFUser result=cfuserController.getUserById(userId);
		assertEquals(userInfo, result);
	}
	@Test
	public void updateUserTest()
	{
		UUID roleId=UUID.randomUUID();
		UserRole roleinfo1=new UserRole(roleId,"manager");
		UUID userId=UUID.randomUUID();
		CFUser userInfo=new CFUser(userId,"divya","divya@gmail.com",roleinfo1);
		cfuserController.updateUser(userId, userInfo);
		 Mockito.verify(cfuserService).updateUser(userId, userInfo);
	}
	@Test
	public void deleteUserTest()
	{
		UUID roleId=UUID.randomUUID();
		UserRole roleinfo1=new UserRole(roleId,"manager");
		UUID userId=UUID.randomUUID();
		CFUser userInfo=new CFUser(userId,"divya","divya@gmail.com",roleinfo1);
		cfuserController.deleteUser(userId);
		 Mockito.verify(cfuserService).deleteUser(userId);
		
	}

}
