package cff.cashflowforecast.interfaces;

import java.util.List;

import cff.cashflowforecast.entity.ForecastTransaction;

public interface IForecastTranscation {

	public List<ForecastTransaction> getAll();
	public ForecastTransaction addTranscationToForecast(ForecastTransaction forecastTransaction);
}
