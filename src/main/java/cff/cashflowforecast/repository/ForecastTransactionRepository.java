package cff.cashflowforecast.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cff.cashflowforecast.entity.Forecast;
import cff.cashflowforecast.entity.ForecastTransaction;
import cff.cashflowforecast.entity.Transaction;

@Repository
public interface ForecastTransactionRepository extends CrudRepository<ForecastTransaction, UUID> {
	List<ForecastTransaction> findByForecast(Forecast forecast);
	List<ForecastTransaction> findByTransaction(Transaction transaction);
}
