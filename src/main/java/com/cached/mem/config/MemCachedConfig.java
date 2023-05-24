package com.cached.mem.config;

import lombok.SneakyThrows;
import net.spy.memcached.MemcachedClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetSocketAddress;

@Configuration
public class MemCachedConfig {

    @Bean
    @SneakyThrows
    public MemcachedClient memcachedClient() {
        return new MemcachedClient(new InetSocketAddress("127.0.0.1", 11211));
    }

}

