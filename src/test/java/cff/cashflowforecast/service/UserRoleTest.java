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

import cff.cashflowforecast.entity.UserRole;
import cff.cashflowforecast.repository.UserRolesRepository;

@SpringBootTest
public class UserRoleTest {

	@InjectMocks
	UserRolesImp userRolesService;
	@Mock
	UserRolesRepository userRolesRepository;

	/*
	 * get all user roles testcase
	 */
	@Test
	public void getAllRolesTest() {
		List<UserRole> userRole = new ArrayList<UserRole>();
		UUID roleId = UUID.randomUUID();
		UserRole roleinfo1 = new UserRole(roleId, "manager");
		UUID roleId1 = UUID.randomUUID();
		UserRole roleinfo2 = new UserRole(roleId1, "techlead");
		userRole.add(roleinfo1);
		userRole.add(roleinfo2);
		Mockito.when(userRolesRepository.findAll()).thenReturn(userRole);
		List<UserRole> result = userRolesService.getAllUserRoles();
		assertEquals(userRole, result);

	}

	/*
	 * create user roles testcase
	 */
	@Test
	public void createUserRoleTest() {

		UUID userRoleId = UUID.randomUUID();
		UserRole userRole = new UserRole(userRoleId, "manager");
		userRolesService.createUserRole(userRole);
		Mockito.verify(userRolesRepository).save(userRole);

	}

	/*
	 * update user roles testcase
	 */
	@Test
	public void updateUserRoleTest() {
		UUID userRoleId = UUID.randomUUID();
		UserRole userRole = new UserRole(userRoleId, "manager");
		Mockito.when(userRolesRepository.findById(userRoleId)).thenReturn(Optional.of(userRole));
		userRolesService.updateUserRole(userRoleId, userRole);
		Mockito.verify(userRolesRepository).save(userRole);

	}

	/*
	 * delete user roles testcase
	 */
	@Test
	public void deleteUserRoleTest() {
		UUID userRoleId = UUID.randomUUID();
		UserRole userRole = new UserRole(userRoleId, "manager");
		Mockito.when(userRolesRepository.findById(userRoleId)).thenReturn(Optional.of(userRole));
		userRolesService.deleteUserRole(userRoleId);
		Mockito.verify(userRolesRepository).delete(userRole);

	}
}