package com.cached.mem.cache;

import com.cached.mem.cache.annotations.LocalCacheClear;
import com.cached.mem.cache.annotations.LocalCacheable;
import com.cached.mem.cache.annotations.LocalCacheableAll;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
public class Pointcuts {
    @Pointcut("@annotation(target)")
    public void localCacheable(LocalCacheable target) { }

    @Pointcut("@annotation(target)")
    public void localCacheableAll(LocalCacheableAll target) { }

    @Pointcut("@annotation(target)")
    public void localCacheClear(LocalCacheClear target) { }
}
