package com.dstz.activiti.rest.editor.conf;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JacksonConfiguration {
	@Bean
	public ObjectMapper objectMapper() {
		return new ObjectMapper();
	}
}