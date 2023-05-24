package com.cached.mem.service;

import com.cached.mem.model.Triangle;

import java.util.List;

public interface TriangleService {
    List<Triangle> getAll();

    Triangle getById(Integer id);

    void save(Triangle triangle);

    void deleteById(Integer id);
}
