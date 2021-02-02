package cff.cashflowforecast;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
@EnableJpaRepositories
@SpringBootApplication
@EnableEurekaClient
public class CashflowforecastApplication {

	public static void main(String[] args) {
		SpringApplication.run(CashflowforecastApplication.class, args);
	}

}
