package cff.cashflowforecast.repository;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import cff.cashflowforecast.entity.CFUser;
import cff.cashflowforecast.entity.Forecast;

@Repository
public interface ForecastRepository extends CrudRepository<Forecast, UUID> {
	List<Forecast> deleteByStatusAndForecastId(String status, UUID forecastId);
	List<Forecast> deleteByStatusAndForecastName(String status, String forecastName);
	List<Forecast> findByForecastName(String forecastName);
	List<Forecast> findByUser(CFUser user);
	@Query("SELECT f FROM Forecast f WHERE user in (select userId from CFUser where roles=(select roleId from UserRole where role = ?1))")
	List<Forecast> findForecastForRole(String role);

}
