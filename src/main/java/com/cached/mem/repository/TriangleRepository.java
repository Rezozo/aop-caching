package com.cached.mem.repository;

import com.cached.mem.model.Triangle;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TriangleRepository extends MongoRepository<Triangle, Integer> {

}
