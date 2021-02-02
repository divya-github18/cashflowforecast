package cff.cashflowforecast.interfaces;

import java.util.List;
import java.util.UUID;

import cff.cashflowforecast.entity.Forecast;
import cff.cashflowforecast.exception.ResourceNotFoundException;

public interface IForecast {
	public List<Forecast> getAllForecasts();
	public List<Forecast> findForecastForRole(String role) throws ResourceNotFoundException ;
	public void createForecast(Forecast forecast);
	public void deleteForecastBasedOnId( UUID forecastId);
	public void deleteForecastBasedOnVisibility(String forecastName) throws Exception;
	public Forecast updateForecast(UUID forecastId,Forecast forecastDetails);

}
