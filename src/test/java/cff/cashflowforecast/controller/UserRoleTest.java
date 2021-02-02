package cff.cashflowforecast.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;


import cff.cashflowforecast.entity.UserRole;
import cff.cashflowforecast.service.UserRolesImp;
@SpringBootTest
public class UserRoleTest {
	                          
	@InjectMocks
	private UserRolesController userRolesController;

	@Mock                        
	private UserRolesImp userRoleService; 
	   
	@Test
	public void getAllUserRoles()
	{
		List<UserRole> userRole=new ArrayList<UserRole>();
		 UUID roleId=UUID.randomUUID();
		UserRole roleinfo1=new UserRole(roleId,"manager");
		 UUID roleId1=UUID.randomUUID();
		UserRole roleinfo2=new UserRole(roleId1,"techlead");
		userRole.add(roleinfo1);
		userRole.add(roleinfo2);
		Mockito.when(userRoleService.getAllUserRoles()).thenReturn(userRole);
		userRolesController.getAllUserRoles();
		Mockito.verify(userRoleService).getAllUserRoles();		
	}
	@Test
	public void createUserRole() {
		List<UserRole> userRole=new ArrayList<UserRole>();
		 UUID roleId=UUID.randomUUID();
		UserRole roleinfo1=new UserRole(roleId,"manager");
		userRole.add(roleinfo1);
		userRolesController.createUserRole(roleinfo1);
		Mockito.verify(userRoleService).createUserRole(roleinfo1);	
		
	}
	@Test
	public void updateUserRoleTest() {
		List<UserRole> userRole=new ArrayList<UserRole>();
		 UUID roleId=UUID.randomUUID();
		UserRole roleinfo1=new UserRole(roleId,"manager");
		userRole.add(roleinfo1);
		userRolesController.updateUserRole(roleId, roleinfo1);
		Mockito.verify(userRoleService).updateUserRole(roleId, roleinfo1);
		
	}
	@Test
	public void deleteUserRoleTest() {
		List<UserRole> userRole=new ArrayList<UserRole>();
		 UUID roleId=UUID.randomUUID();
		UserRole roleinfo1=new UserRole(roleId,"manager");
		userRole.add(roleinfo1);
		userRolesController.deleteUserRole(roleId);
		Mockito.verify(userRoleService).deleteUserRole(roleId);
		
	}
}
