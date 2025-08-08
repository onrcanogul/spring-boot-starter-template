package com.example.demo.starter.controller.v1;

import com.example.demo.starter.controller.base.BaseController;
import com.example.demo.starter.dto.product.ProductDto;
import com.example.demo.starter.service.product.ProductService;
import com.example.demo.starter.util.response.NoContent;
import com.example.demo.starter.util.response.ServiceResponse;
import io.swagger.v3.oas.annotations.Operation;
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

    @GetMapping("/{pageNo}/{pageSize}")
    @Operation(summary = "Get All Products", description = "Get All Products")
    public ResponseEntity<ServiceResponse<List<ProductDto>>> get(@PathVariable int pageNo, @PathVariable int pageSize) {
        return controllerResponse(service.get(pageNo, pageSize));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Product By Id", description = "Get Product By Id")
    public ResponseEntity<ServiceResponse<ProductDto>> get(@PathVariable UUID id) {
        return controllerResponse(service.getSingle(id));
    }

    @PostMapping
    @Operation(summary = "Create a Product", description = "Create a Product")
    public ResponseEntity<ServiceResponse<ProductDto>> create(@RequestBody ProductDto model) {
        return controllerResponse(service.create(model));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a Product", description = "Update a Product")
    public ResponseEntity<ServiceResponse<ProductDto>> update(@PathVariable UUID id, @RequestBody ProductDto model) {
        return controllerResponse(service.update(model, id));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a Product", description = "Delete a Product")
    public ResponseEntity<ServiceResponse<NoContent>> delete(@PathVariable UUID id) {
        return controllerResponse(service.delete(id));
    }
}
