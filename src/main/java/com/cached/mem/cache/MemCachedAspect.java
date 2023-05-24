package com.cached.mem.cache;

import com.cached.mem.cache.annotations.LocalCacheClear;
import com.cached.mem.cache.annotations.LocalCacheable;
import com.cached.mem.cache.annotations.LocalCacheableAll;
import lombok.SneakyThrows;
import net.spy.memcached.MemcachedClient;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;

@Aspect
@Component
public class MemCachedAspect {

    private final MemcachedClient memcachedClient;

    @SneakyThrows
    public MemCachedAspect() {
        memcachedClient = new MemcachedClient(new InetSocketAddress("127.0.0.1", 11211));
    }

    @Pointcut("@annotation(target)")
    public void localCacheable(LocalCacheable target) { }

    @Pointcut("@annotation(target)")
    public void localCacheableAll(LocalCacheableAll target) { }

    @Pointcut("@annotation(target)")
    public void localCacheClear(LocalCacheClear target) { }

    @Around("@annotation(localCacheable) && args(id)")
    @SneakyThrows
    public Object checkCacheById(ProceedingJoinPoint joinPoint, LocalCacheable localCacheable, Integer id) {
        String key = "id_" + id;
        Object value = memcachedClient.get(key);

        if (value != null)
            return value;

        return setValue(joinPoint, key);
    }

    @Around("@annotation(localCacheableAll)")
    @SneakyThrows
    public Object checkAllCache(ProceedingJoinPoint joinPoint, LocalCacheableAll localCacheableAll) {
        String key = localCacheableAll.key();
        Object value = memcachedClient.get(key);

        if (value != null)
            return value;

        return setValue(joinPoint, key);
    }

    @SneakyThrows
    private Object setValue(ProceedingJoinPoint joinPoint, String key) {
        Object result = joinPoint.proceed();

        if (result != null)
            memcachedClient.set(key, 900, result);


        return result;
    }

    @AfterReturning("@annotation(localCacheClear) && args(id)")
    private void clearCache(LocalCacheClear localCacheClear, Integer id) {
        memcachedClient.delete("id_" + id);
        memcachedClient.delete("allTriangles");
    }
}
