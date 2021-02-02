package cff.cashflowforecast.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cff.cashflowforecast.entity.Forecast;
import cff.cashflowforecast.entity.UserRole;
import cff.cashflowforecast.exception.ResourceNotFoundException;
import cff.cashflowforecast.interfaces.IUserRole;
import cff.cashflowforecast.service.UserRolesImp;

@RestController
@RequestMapping("/api/v1")
public class UserRolesController {

	// Autowiring is used to injects the object dependency implicitly
	@Autowired
	private IUserRole iUserRoles;

	/**
	 * Get all Userroles using GET mapping
	 * @return list of Userroles
	 */
	@GetMapping("userroles")
	public List<UserRole> getAllUserRoles() {
		return iUserRoles.getAllUserRoles();
	}
	/**
	 * Create UserRole using POST mapping
	 * @param userRole
	 * @return UserRole
	 */
	@PostMapping("/userroles")
	public UserRole createUserRole(@Validated @RequestBody UserRole userRole) {
		return iUserRoles.createUserRole(userRole);
	}
	/**
	 * Delete UserRole based on roleId using DELETE mapping
	 * @param roleId
	 * @return response in the form of map<String ,Boolean>
	 */
	@DeleteMapping("/userroles/{roleId}")
	public Map<String, Boolean> deleteUserRole(@PathVariable(value = "roleId") UUID roleId) {
		iUserRoles.deleteUserRole(roleId);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;

	}
	/**
	 * Update UserRole based on roleId using PUT mapping
	 * @param roleId
	 * @param userRole
	 * @return ResponseEntity
	 */
	@PutMapping("/userroles/{roleId}")
	public ResponseEntity<UserRole> updateUserRole(@PathVariable(value = "roleId") UUID roleId,
			@Validated @RequestBody UserRole userRole) {
		iUserRoles.updateUserRole(roleId, userRole);
		return ResponseEntity.ok(userRole);
	}

}
