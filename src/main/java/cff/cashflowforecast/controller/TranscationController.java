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

import cff.cashflowforecast.entity.Transaction;
import cff.cashflowforecast.exception.ResourceNotFoundException;
import cff.cashflowforecast.interfaces.ITranscation;
import cff.cashflowforecast.service.TransactionImp;

@RestController
@RequestMapping("/api/v1")
public class TranscationController {
	// Autowiring is used to injects the object dependency implicitly
	@Autowired
	private ITranscation iTranscation;

	/**
	 * Retrieving all transactions using get mapping
	 * @return list if transactions
	 */
	@GetMapping("/transactions")
	public List<Transaction> getAllTransactions() {
		return iTranscation.getAllTransactions();
	}

	/**
	 * create transactions using post mapping
	 * @param transaction
	 * @return transaction
	 */
	@PostMapping("/transactions")
	public Transaction createTransaction(@Validated @RequestBody Transaction transaction) {
		return iTranscation.createTransaction(transaction);
	}

	/**
	 * Delete transaction using transcation id
	 * @param transcationId
	 * @return response in the form of map<String ,Boolean>
	 */
	@DeleteMapping("/transaction/{transcationId}")
	public Map<String, Boolean> deleteTransaction(@PathVariable(value = "transcationId") UUID transcationId) {
		iTranscation.deleteTransaction(transcationId);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

	/**
	 * update transcation using transaction id
	 * @param transcationId
	 * @param transcationDetails
	 * @return response entity
	 * @throws ResourceNotFoundException
	 */
	@PutMapping("/transaction/{transcationId}")
	public ResponseEntity<Transaction> updateTransaction(@PathVariable(value = "transcationId") UUID transcationId,
			@Validated @RequestBody Transaction transcationDetails) throws ResourceNotFoundException {
		iTranscation.updateTransaction(transcationId, transcationDetails);
		return ResponseEntity.ok(transcationDetails);
	}

}
