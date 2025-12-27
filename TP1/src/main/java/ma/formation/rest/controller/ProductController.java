package ma.formation.rest.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ma.formation.rest.service.IProductService;
import ma.formation.rest.service.model.product;

@RestController
public class ProductController {

    @Autowired
    private IProductService service;

    @GetMapping(value = "/products")
    public List<product> getAll() {
        return service.getAll();
    }

    @GetMapping(value = "/products/{id}")
    public product getProductById(@PathVariable(value = "id") Long productId) {
        return service.getById(productId);
    }

    @PostMapping(value = "/products")
    public ResponseEntity<Object> createProduct(@Validated @RequestBody product product) {
        service.create(product);
        return new ResponseEntity<>("Product is created successfully", HttpStatus.CREATED);
    }

    @PutMapping(value = "/products/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable(name = "id") Long productId,
                                                @RequestBody product product) {
        product productFound = service.getById(productId);
        if (productFound == null)
            return ResponseEntity.notFound().build();
        service.update(productId, product);
        return new ResponseEntity<>("Product is updated successfully", HttpStatus.OK);
    }

    @DeleteMapping(value = "/products/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable(name = "id") Long productId) {
        product productFound = service.getById(productId);
        if (productFound == null)
            return ResponseEntity.notFound().build();
        service.delete(productId);
        return new ResponseEntity<>("Product is deleted successfully", HttpStatus.OK);
    }
}