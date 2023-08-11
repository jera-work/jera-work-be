package com.lawencon.candidate.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

@Configuration
public class GlobalConfig {

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

//	@Bean(name = "initTable")
//	public SpringLiquibase initTable(DataSource dataSource) {
//		final SpringLiquibase table = new SpringLiquibase();
//		table.setChangeLog("classpath:/db/migration/script/init_table_v001.sql");
//		table.setDataSource(dataSource);
//		return table;
//	}
//
//	@Bean(name = "initData")
//	@DependsOn("initTable")
//	public SpringLiquibase initData(DataSource dataSource) {
//		final SpringLiquibase table = new SpringLiquibase();
//		table.setChangeLog("classpath:/db/migration/script/init_data_v001.sql");
//		table.setDataSource(dataSource);
//		return table;
//	}

}
