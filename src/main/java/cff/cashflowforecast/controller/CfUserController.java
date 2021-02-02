package cff.cashflowforecast.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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

import cff.cashflowforecast.entity.CFUser;
import cff.cashflowforecast.exception.ResourceNotFoundException;
import cff.cashflowforecast.interfaces.Icfuser;
import cff.cashflowforecast.service.CFUserImp;

@RestController
@RequestMapping("/api/v1")
public class CfUserController {

	// Autowiring is used to injects the object dependency implicitly
	@Autowired
	private Icfuser icfuser;
	/**
	 * Get all CFUsers using GET mapping
	 * @return list of cfusers
	 */
	@GetMapping("/cfusers")
	public List<CFUser> getAllUsers() {
		return icfuser.getAllUsers();
	}

	/**
	 * Create CFuser using POST mapping
	 * @param cfuser
	 * @return CFUser
	 */
	@PostMapping("/cfusers")
	public CFUser createCfUser(@Validated @RequestBody CFUser cfuser) {
		return icfuser.createCfUser(cfuser);
	}

	/**
	 * Get the cfuser based on userid using Get mapping
	 * @param userId
	 * @return CFUser
	 */

	@GetMapping("/cfusers/{userId}")
	public CFUser getUserById(@PathVariable(value = "userId") UUID userId) {
		return icfuser.getUserById(userId);
	}

	/**
	 *  Delete the user details based on id using Delete Mapping
	 * @param userId
	 * @return response 
	 */
	 
	@DeleteMapping("/cfusers/{userId}")
	public Map<String, Boolean> deleteUser(@PathVariable(value = "userId") UUID userId) {
		icfuser.deleteUser(userId);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

	/**
	 *  update the user by using PUT mapping
	 * @param userId
	 * @param userDetails
	 * @return ResponseEntity
	 */
	@PutMapping("/cfusers/{userId}")
	public ResponseEntity<CFUser> updateUser(@PathVariable(value = "userId") UUID userId,
			@Validated @RequestBody CFUser userDetails) {
		icfuser.updateUser(userId, userDetails);
		return ResponseEntity.ok(userDetails);
	}

}
