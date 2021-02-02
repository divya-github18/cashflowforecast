package cff.cashflowforecast.service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.convert.Jsr310Converters.PeriodToStringConverter;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.JsonNodeDeserializer;
import com.fasterxml.jackson.databind.json.JsonMapper;

import cff.cashflowforecast.entity.CFUser;
import cff.cashflowforecast.entity.Forecast;
import cff.cashflowforecast.entity.ForecastTransaction;
import cff.cashflowforecast.entity.Transaction;
import cff.cashflowforecast.exception.PrivateStatusException;
import cff.cashflowforecast.exception.ResourceNotFoundException;
import cff.cashflowforecast.exception.UniqueForecastNameException;
import cff.cashflowforecast.interfaces.IForecast;
import cff.cashflowforecast.repository.ForecastRepository;
import cff.cashflowforecast.repository.ForecastTransactionRepository;

@Transactional(rollbackFor = Exception.class)
@Service
public class ForecastImp implements IForecast {
	@Autowired
	private ForecastRepository forecastRepository;
	@Autowired
	private ForecastTransactionRepository forecastTranscationRepository;
	@Autowired
	private KafkaTemplate<String, Object> kafkaTemplate;
	private static final String TOPIC = "test";
	/**
	 * get all the forecasts
	 */
	public List<Forecast> getAllForecasts() {

		return (List<Forecast>) forecastRepository.findAll();
	}
	/**
	 * get the forecastas based on user role
	 * @return 
	 */
	public List<Forecast> findForecastForRole(String role) throws ResourceNotFoundException {

		List<Forecast> forecasts = forecastRepository.findForecastForRole(role);
		if (forecasts.isEmpty()) {
			throw new ResourceNotFoundException("No forecasts available on the role of " + role);
		}
		return forecasts;
	}

	/**
	 * create a forecast and calculating horizon period
	 */
	public void createForecast(Forecast forecast) throws UniqueForecastNameException {
		CFUser cfuser = forecast.getUser();
		String forecastName = forecast.getForecastName();
		List<Forecast> forecasts = forecastRepository.findByUser(cfuser);
		forecasts.forEach(t -> {
			if (forecastName.equals(t.getForecastName())) {
				throw new UniqueForecastNameException(" UniqueForecastNameException:: " + t.getForecastName());
			}
		});
		String period = forecast.getHorizonPeriod();
		char ch1 = period.charAt(1);
		int days = 0;
		if (ch1 == 'w' || ch1 == 'W')
			days = Character.getNumericValue(period.charAt(0)) * 7;
		if (ch1 == 'm' || ch1 == 'M')
			days = Character.getNumericValue(period.charAt(0)) * 30;
		LocalDate startDate = LocalDate.now();
		LocalDate createdDate = LocalDate.now();
		LocalDate updatedDate = LocalDate.now();
		LocalDate endDate = startDate.plusDays(days);
		forecast.setForecastId(UUID.randomUUID());
		forecast.setStartDate(startDate);
		forecast.setCreatedDate(createdDate);
		forecast.setUpdatedDate(updatedDate);
		forecast.setEndDate(endDate);
		//kafkaTemplate.send(TOPIC, forecast);
		forecastRepository.save(forecast);
	}

	/**
	 * delete the public forecast based on forecastId
	 */
	public void deleteForecastBasedOnId( UUID forecastId) throws PrivateStatusException  {

		Forecast forecast = forecastRepository.findById(forecastId).orElseThrow();
		String status = forecast.getStatus();
		if (status.equals("public"))
		{
			List<ForecastTransaction> forecastTranscationInfo = forecastTranscationRepository
					.findByForecast(forecast);
			forecastTranscationRepository.deleteAll(forecastTranscationInfo);
			forecastRepository.deleteByStatusAndForecastId(status, forecastId);
		}else
		{
			throw new PrivateStatusException(" PrivateStatusException:: " + forecastId);
		}
	}

	/**
	 * delete the forecast based on status visibility
	 * @throws Exception 
	 */
	public void deleteForecastBasedOnVisibility(String forecastName) throws Exception {
		List<Forecast> forecasts = forecastRepository.findByForecastName(forecastName);
		if (forecasts.isEmpty()) {
			throw new Exception("Resources are not found on "+forecastName);
		}
		boolean privateExisit = false;
		for (Forecast forecast : forecasts) {
			String status = forecast.getStatus();
			if (status.equals("public")) {
				List<ForecastTransaction> forecastTranscationInfo = forecastTranscationRepository
						.findByForecast(forecast);
				forecastTranscationRepository.deleteAll(forecastTranscationInfo);
				forecastRepository.delete(forecast);
			} else {
				privateExisit = true;
			}
		}
		if (privateExisit)
			throw new PrivateStatusException(" PrivateStatusException:: " + forecastName);
	}

	/**
	 * update the forecast based on forecastId
	 * @return 
	 */
	public Forecast updateForecast(UUID forecastId,Forecast forecastDetails) {
		Forecast forecast = forecastRepository.findById(forecastId).orElseThrow();

		forecast.setDescription(forecastDetails.getDescription());
		forecast.setForecastName(forecastDetails.getForecastName());
		forecast.setHorizonPeriod(forecastDetails.getHorizonPeriod());
		forecast.setStatus(forecastDetails.getStatus());
		forecast.setCreatedDate(LocalDate.now());
		forecast.setStartDate(LocalDate.now());
		forecast.setUpdatedDate(LocalDate.now());
		forecast.setAccountDetail(forecastDetails.getAccountDetail());
		forecast.setUser(forecastDetails.getUser());
		forecast.setUpdatedBy(forecastDetails.getUpdatedBy());
		forecast.setCreatedBy(forecastDetails.getCreatedBy());
		forecastRepository.save(forecast);
		return forecast;
		
	}

}
