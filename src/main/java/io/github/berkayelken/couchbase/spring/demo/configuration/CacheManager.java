package io.github.berkayelken.couchbase.spring.demo.configuration;

import io.github.berkayelken.couchbase.spring.demo.properties.CouchbaseConnectionProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.couchbase.cache.CouchbaseCacheConfiguration;
import org.springframework.data.couchbase.cache.CouchbaseCacheManager;
import org.springframework.data.couchbase.core.CouchbaseTemplate;

@Configuration
public class CacheManager {
	private final CouchbaseConnectionProperties connectionProperties;

	@Autowired
	public CacheManager(CouchbaseConnectionProperties connectionProperties) {
		this.connectionProperties = connectionProperties;
	}

	@Bean
	public CouchbaseCacheManager cacheManager() {
		CouchbaseTemplate template = connectionProperties.createCachingConnection();
		CouchbaseCacheManager.CouchbaseCacheManagerBuilder builder = CouchbaseCacheManager.CouchbaseCacheManagerBuilder.fromConnectionFactory(
				template.getCouchbaseClientFactory());
		builder.withCacheConfiguration("couchbaseSpringCache", CouchbaseCacheConfiguration.defaultCacheConfig());

		return builder.build();
	}
}
