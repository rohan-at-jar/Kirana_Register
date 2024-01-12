package com.kirana.Kirana_Register.config;

import com.giffing.bucket4j.spring.boot.starter.config.cache.SyncCacheResolver;
import com.giffing.bucket4j.spring.boot.starter.config.cache.jcache.JCacheCacheResolver;
import io.github.bucket4j.distributed.proxy.ProxyManager;
import io.github.bucket4j.grid.jcache.JCacheProxyManager;
import org.redisson.config.Config;
import org.redisson.jcache.configuration.RedissonConfiguration;
import org.redisson.spring.starter.RedissonAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.spi.CachingProvider;

/**
 * Configuration class for Redis integration in Kirana Register application.
 * Configures the Redisson client, creates a custom CacheManager using Redisson,
 * and provides a JCacheProxyManager for Bucket4j rate limiting.
 */
@Configuration
public class RedisConfig
{
    /**
     * Creates and configures a Redisson client.
     *
     * @return Configured Redisson client instance.
     */
    @Bean
    public Config config()
    {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://localhost:6379");
        return config;
    }

    /**
     * Creates a custom CacheManager using Redisson for caching.
     *
     * @param config Redisson configuration for cache management.
     * @return Configured CacheManager instance.
     */
    @Bean(name="my_cache_manager")
    public CacheManager cacheManager(Config config) {
        CacheManager manager = Caching.getCachingProvider().getCacheManager();
        manager.createCache("cache", RedissonConfiguration.fromConfig(config));
        return manager;
    }

    /**
     * Creates a JCacheProxyManager for Bucket4j rate limiting using the custom CacheManager.
     *
     * @param cacheManager Configured CacheManager instance.
     * @return JCacheProxyManager for Bucket4j rate limiting.
     */
    @Bean
    ProxyManager<String> proxyManager(CacheManager cacheManager) {
        return new JCacheProxyManager<>(cacheManager.getCache("cache"));
    }

    /**
     * reference:
     * https://github.com/MarcGiffing/bucket4j-spring-boot-starter/issues/73
     * */
    /**
     * Provides a SyncCacheResolver for Bucket4j rate limiting using the custom CacheManager.
     * This resolver is marked as Primary to be used as the primary cache resolver.
     *
     * @param cacheManager Configured CacheManager instance.
     * @return JCacheCacheResolver for Bucket4j rate limiting.
     */
    @Bean
    @Primary
    public SyncCacheResolver bucket4jCacheResolver(CacheManager cacheManager) {
        return new JCacheCacheResolver(cacheManager);
    }


}