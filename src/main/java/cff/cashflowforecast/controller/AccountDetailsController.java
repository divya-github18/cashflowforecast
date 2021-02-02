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

import cff.cashflowforecast.entity.AccountDetail;
import cff.cashflowforecast.exception.ResourceNotFoundException;
import cff.cashflowforecast.interfaces.IAccountDetails;
import cff.cashflowforecast.service.AccountDetailImp;

@RestController
@RequestMapping("/api/v1")
public class AccountDetailsController {
	// Autowiring is used to injects the object dependency implicitly
	@Autowired
	private IAccountDetails iAccountDetails;

	/**
	 *  By using GetMapping get the account details
	 * @return list of Account Details
	 */
	@GetMapping("/accountdetails")
	public List<AccountDetail> getAllAccountDetails() {
		return iAccountDetails.getAllAccountDetails();
	}

	/**
	 * creating account details using Post Mapping
	 * @param accountDetails
	 * @return AccountDetail
	 */
	@PostMapping("/accountdetails")
	public AccountDetail createAccountDetails(@Validated @RequestBody AccountDetail accountDetails) {
		return iAccountDetails.createAccount(accountDetails);
	}

	/**
	 * delete account details using Delete Mapping
	 * @param accountId
	 * @return response in the form of map<String ,Boolean>
	 * @throws ResourceNotFoundException
	 */

	@DeleteMapping("/accountdetails/{accountId}")
	public Map<String, Boolean> deleteAccount(@PathVariable(value = "accountId") UUID accountId)
			throws ResourceNotFoundException {
		iAccountDetails.deleteAccount(accountId);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

	/**
	 * updating account details using PUT Mapping
	 * @param accountId
	 * @param accountDetails
	 * @return response entity
	 * @throws ResourceNotFoundException
	 */
	@PutMapping("/accountdetails/{accountId}")
	public ResponseEntity<AccountDetail> updateAccount(@PathVariable(value = "accountId") UUID accountId,
			@Validated @RequestBody AccountDetail accountDetails) throws ResourceNotFoundException {
		iAccountDetails.updateAccount(accountId, accountDetails);
		return ResponseEntity.ok(accountDetails);

	}

}
