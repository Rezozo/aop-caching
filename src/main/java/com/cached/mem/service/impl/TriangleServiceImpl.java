package com.cached.mem.service.impl;

import com.cached.mem.cache.annotations.LocalCacheClear;
import com.cached.mem.cache.annotations.LocalCacheable;
import com.cached.mem.cache.annotations.LocalCacheableAll;
import com.cached.mem.model.Triangle;
import com.cached.mem.repository.TriangleRepository;
import com.cached.mem.service.TriangleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TriangleServiceImpl implements TriangleService {

    private final TriangleRepository triangleRepository;

    @LocalCacheableAll
    @Override
    public List<Triangle> getAll() {
        return triangleRepository.findAll();
    }

    @LocalCacheable(key = "{id}")
    @Override
    public Triangle getById(Integer id) {
        return triangleRepository.findById(id).orElse(null);
    }

    @LocalCacheClear
    @Override
    public void save(Triangle triangle) {
        triangleRepository.save(triangle);
    }

    @LocalCacheClear(key = "{id}")
    @Override
    public void deleteById(Integer id) {
        triangleRepository.deleteById(id);
    }
}
