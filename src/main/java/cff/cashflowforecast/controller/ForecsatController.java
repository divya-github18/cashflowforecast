package cff.cashflowforecast.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cff.cashflowforecast.entity.Forecast;
import cff.cashflowforecast.exception.PrivateStatusException;
import cff.cashflowforecast.exception.ResourceNotFoundException;
import cff.cashflowforecast.exception.UniqueForecastNameException;
import cff.cashflowforecast.interfaces.IForecast;
import cff.cashflowforecast.service.ForecastImp;

@RestController
@RequestMapping("/api/v1")
public class ForecsatController {

	// Autowiring is used to injects the object dependency implicitly
	@Autowired
	private IForecast iForecast;

	/**
	 * By using GETMapping get the forecasts using getAllForecasts method
	 * @return list of Forecasts
	 */
	
	@GetMapping("/forcasts")
	public List<Forecast> getAllForecasts() {
		return iForecast.getAllForecasts();
	}

	/**
	 *  Get the Forecasts based on role name
	 * @param role
	 * @return list of Forecasts
	 * @throws ResourceNotFoundException
	 */
	@GetMapping("/forcast/{role}")
	public List<Forecast> getAllForecastsBasedOnRole(@PathVariable(value = "role") String role)
			throws ResourceNotFoundException {
		return iForecast.findForecastForRole(role);

	}
	/**
	 * By using POST mapping create the Forecasts By using createForecast method
	 * @param forecast
	 * @return Response entity
	 * @throws UniqueForecastNameException
	 */
	@PostMapping("/forcasts")
	public ResponseEntity<Forecast> createForecast(@RequestBody Forecast forecast) throws UniqueForecastNameException {
		iForecast.createForecast(forecast);
		return ResponseEntity.ok(forecast);
	}

	/**
	 * By using DELETE mapping Delete the public Forecasts By using ForcastId
	 * @param forecastId
	 * @return response in the form of map<String ,Boolean>
	 */
	@DeleteMapping("/deleteForecastBasedOnId/{forecastId}")
	public Map<String, Boolean> deleteForecastBasedOnId(@PathVariable(value = "forecastId") UUID forecastId) {
		iForecast.deleteForecastBasedOnId(forecastId);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

	/**
	 * By using DELETE mapping Delete the public Forecasts By using ForcastName
	 * @param forecastName
	 * @return response in the form of map<String ,Boolean>
	 * @throws Exception
	 */
	@DeleteMapping("/deleteforecastbasedonvisibility/{forecastName}")
	public Map<String, Boolean> deleteForecastBasedOnVisibility(
			@PathVariable(value = "forecastName") String forecastName) throws Exception {
		iForecast.deleteForecastBasedOnVisibility(forecastName);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

	/**
	 * By using PUT mapping Update the Forecasts By using forecast Id
	 * @param forecastId
	 * @param forecastDetails
	 * @return response entity
	 */
	@PutMapping("/forcasts/{forecastId}")
	public ResponseEntity<Forecast> updateForecast(@PathVariable(value = "forecastId") UUID forecastId,
			@RequestBody Forecast forecastDetails) {
		iForecast.updateForecast(forecastId, forecastDetails);
		return ResponseEntity.ok(forecastDetails);
	}

}
