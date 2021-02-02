package cff.cashflowforecast.interfaces;

import java.util.List;
import java.util.UUID;

import cff.cashflowforecast.entity.UserRole;

public interface IUserRole {
	public List<UserRole> getAllUserRoles();
	public UserRole createUserRole(UserRole userRole);
	public void deleteUserRole(UUID roleId);
	public void updateUserRole( UUID roleId,UserRole userRole);  

}
