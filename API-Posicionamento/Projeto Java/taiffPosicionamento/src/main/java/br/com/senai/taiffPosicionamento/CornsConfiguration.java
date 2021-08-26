package br.com.senai.taiffPosicionamento;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CornsConfiguration {
	
	@Bean
	@Primary
	public CorsFilter corsFilter() {
		final UrlBasedCorsConfigurationSource UrlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
		final CorsConfiguration corsConfig = new CorsConfiguration();
		
		List<String> originVetor = new ArrayList<String>();
		originVetor.add("*");
		
		corsConfig.setAllowCredentials(true);
		corsConfig.setAllowedOriginPatterns(originVetor);
		corsConfig.addAllowedHeader("*");
		corsConfig.addAllowedMethod("GET");
		corsConfig.addAllowedMethod("PUT");
		corsConfig.addAllowedMethod("POST");
		corsConfig.addAllowedMethod("DELETE");
		
		UrlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfig);
		
		return new CorsFilter(UrlBasedCorsConfigurationSource);
	}
}