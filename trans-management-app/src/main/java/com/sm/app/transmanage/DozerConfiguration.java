package com.sm.app.transmanage;

import java.util.Arrays;

import org.dozer.DozerBeanMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DozerConfiguration {

	@Bean
	public DozerBeanMapper dozerBeanMapper() {
		DozerBeanMapper dozerBean = new DozerBeanMapper();
		dozerBean.setMappingFiles(Arrays.asList("dozer-mapping.xml"));
		/*
		 * dozerBean.setMappingFiles(
		 * Arrays.asList("dozer-global-configuration.xml",
		 * "dozer-bean-mappings.xml", "dozer-mapping.xml"));
		 */
		return dozerBean;
	}
}
