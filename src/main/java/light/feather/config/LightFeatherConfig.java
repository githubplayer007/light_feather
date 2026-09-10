package light.feather.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class LightFeatherConfig {

	@Value("${supervisor.baseUrl}")
	String supervisorBaseUrl;
	
	@Bean
	public RestClient employeeRestClient(RestClient.Builder builder) {
		return builder
				.baseUrl(supervisorBaseUrl)
				.defaultHeader("Accept", "application/json")
				.build();
	}
}
