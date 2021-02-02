package cff.cashflowforecast.service;

import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cff.cashflowforecast.entity.UserRole;
import cff.cashflowforecast.interfaces.IUserRole;
import cff.cashflowforecast.repository.UserRolesRepository;

@Transactional(rollbackFor = Exception.class)
@Service
public class UserRolesImp implements IUserRole {
	@Autowired
	private UserRolesRepository userRolesRepository;
	
	/**
	 * get all users
	 */
	public List<UserRole> getAllUserRoles() {
		return (List<UserRole>) userRolesRepository.findAll();
	}
	/**
	 * create userrole
	 */
	public UserRole createUserRole(UserRole userRole) {

		userRole.setRoleId(UUID.randomUUID());
		return userRolesRepository.save(userRole);
	}
	/**
	 * Delete userrole
	 */
	public void deleteUserRole(UUID roleId) {
		UserRole userroleinfo = userRolesRepository.findById(roleId).orElseThrow();
		userRolesRepository.delete(userroleinfo);
	}
	/**
	 * update userrole
	 */
	public void updateUserRole(UUID roleId, UserRole userRole) {
		UserRole userroleinfo = userRolesRepository.findById(roleId).orElseThrow();
		userroleinfo.setRole(userRole.getRole());
		userRolesRepository.save(userroleinfo);

	}

}
