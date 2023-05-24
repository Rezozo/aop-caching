package com.cached.mem.controller;

import com.cached.mem.model.Triangle;
import com.cached.mem.service.TriangleService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class TriangleController {

    private final TriangleService triangleService;

    @RequestMapping(value = "/triangles", method = RequestMethod.GET)
    public List<Triangle> allTriangles() {
        return triangleService.getAll();
    }

    @RequestMapping(value = "/triangle/{triangleId}", method = RequestMethod.GET)
    public Triangle oneTriangle(@PathVariable Integer triangleId) {
        return triangleService.getById(triangleId);
    }

    @RequestMapping(value = "/triangle", method = RequestMethod.POST)
    public void saveTriangle(@RequestBody Triangle triangle) {
        triangleService.save(triangle);
    }

    @RequestMapping(value = "/triangle/{triangleId}", method = RequestMethod.DELETE)
    public void deleteTriangle(@PathVariable Integer triangleId) {
        triangleService.deleteById(triangleId);
    }
}
