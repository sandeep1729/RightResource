package com.r2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;

@EnableCaching
@SpringBootApplication
public class RrApplication {

	public static void main(String[] args) {
		SpringApplication.run(RrApplication.class, args);
	}

	@Bean
	public CacheManager getEhCacheManager() {
		return new EhCacheCacheManager(getEhCacheFactory().getObject());
	}

	@Bean
	public EhCacheManagerFactoryBean getEhCacheFactory() {
		EhCacheManagerFactoryBean factoryBean = new EhCacheManagerFactoryBean();
		factoryBean.setConfigLocation(new ClassPathResource("ehcache.xml"));
		factoryBean.setShared(true);
		return factoryBean;
	}
}
