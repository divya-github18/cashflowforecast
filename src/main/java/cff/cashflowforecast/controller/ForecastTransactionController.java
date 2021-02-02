package cff.cashflowforecast.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import cff.cashflowforecast.entity.ForecastTransaction;
import cff.cashflowforecast.interfaces.IForecastTranscation;
import cff.cashflowforecast.service.ForecastTransactionImp;

@RestController
@RequestMapping("/api/v1")
public class ForecastTransactionController {
	@Autowired
	private IForecastTranscation iForecastTranscation;
	/**
	 * get all forecast's transcations
	 * @return list of Forecast Transcations
	 */
	@GetMapping("forecasttranscation")
	public List<ForecastTransaction> getAll() {
		return iForecastTranscation.getAll();
	}
	/**
	 * Adding transcations to forecast
	 * @param forecastTransaction
	 * @return
	 */
	@PostMapping("/addtranscationtoforecast")
	public ForecastTransaction addTranscationToForecast(@RequestBody ForecastTransaction forecastTransaction) {
		
		return iForecastTranscation.addTranscationToForecast(forecastTransaction);
	}

}
