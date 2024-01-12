package com.kirana.Kirana_Register.config;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.BucketConfiguration;
import io.github.bucket4j.Refill;
import io.github.bucket4j.distributed.proxy.ProxyManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;
import java.util.function.Supplier;

/**
 * Configuration class for rate limiting in Kirana Register application.
 * Configures rate limits using Bucket4j and provides a ProxyManager for distributed rate limiting.
 */
@Configuration
public class RateLimitConfig {
    //autowiring dependencies
    /**
     * Autowired dependency for ProxyManager.
     */
    @Autowired
    public ProxyManager buckets;

    /**
     * Resolves or creates a Bucket for rate limiting based on the provided key.
     * In a production environment, this function can use the key (e.g., authentication token)
     * to fetch user-specific rate limit details from the database and configure the Bucket accordingly.
     *
     * @param key The key used to identify the user or entity for rate limiting.
     * @return The resolved or created Bucket for rate limiting.
     */
    public Bucket resolveBucket(String key) {
        Supplier<BucketConfiguration> configSupplier = getConfigSupplierForUser(key);

        // Does not always create a new bucket, but instead returns the existing one if it exists.
        return buckets.builder().build(key, configSupplier);
    }

    /**
     * Provides a Supplier for BucketConfiguration based on the user's rate limit details.
     * In this example, a simple configuration with a refill of 2 tokens every 10 seconds and
     * a total limit of 5 tokens is used.
     *
     * @param key The key used to identify the user or entity for rate limiting.
     * @return Supplier for BucketConfiguration.
     */
    private Supplier<BucketConfiguration> getConfigSupplierForUser(String key) {
        Refill refill = Refill.intervally(2, Duration.ofSeconds(10));
        //create 10 token bandwidth
        Bandwidth limit = Bandwidth.classic(5, refill);
        // Bandwidth limit = Bandwidth.

        return () -> (BucketConfiguration.builder()
                .addLimit(limit)
                .build());
    }
}