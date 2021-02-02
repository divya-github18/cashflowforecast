package cff.cashflowforecast.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class ForecastTransaction {
	@Id
	@GeneratedValue
	@Column(name = "forecast_transcation_id")
	private UUID forecastTransactionId;
	@NotNull
	@ManyToOne
	@JoinColumn(name = "forecast_id")
	private Forecast forecast;
	@NotNull
	@ManyToOne
	@JoinColumn(name = "transcation_id")
	private Transaction transaction;
}
