package cff.cashflowforecast.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cff.cashflowforecast.entity.ForecastTransaction;
import cff.cashflowforecast.interfaces.IForecastTranscation;
import cff.cashflowforecast.repository.ForecastTransactionRepository;

@Transactional(rollbackFor = Exception.class)
@Service
public class ForecastTransactionImp implements IForecastTranscation
{
	@Autowired
	private ForecastTransactionRepository forecastTransactionRepository;

	public List<ForecastTransaction> getAll() {
		
		return  (List<ForecastTransaction>) forecastTransactionRepository.findAll();
	}
	public ForecastTransaction addTranscationToForecast(ForecastTransaction forecastTransaction)
	{
		forecastTransaction.setForecastTransactionId(UUID.randomUUID());
		return forecastTransactionRepository.save(forecastTransaction);
	}
	
	
}
