package com.example.demo.starter.controller.product;

import com.example.demo.starter.controller.base.BaseController;
import com.example.demo.starter.dto.product.ProductDto;
import com.example.demo.starter.service.product.ProductService;
import com.example.demo.starter.util.response.NoContent;
import com.example.demo.starter.util.response.ServiceResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController extends BaseController {
    private final ProductService service;
    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<ServiceResponse<List<ProductDto>>> get(int page, int size) {
        return controllerResponse(service.get(page, size, null));
    }

    @PostMapping
    public ResponseEntity<ServiceResponse<ProductDto>> create(@RequestBody ProductDto model) {
        return controllerResponse(service.create(model));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServiceResponse<ProductDto>> update(@PathVariable UUID id, @RequestBody ProductDto model) {
        return controllerResponse(service.update(model, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ServiceResponse<NoContent>> delete(@PathVariable UUID id) {
        return controllerResponse(service.delete(id));
    }
}
