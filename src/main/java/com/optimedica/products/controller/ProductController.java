package com.optimedica.products.controller;

import com.optimedica.products.dto.ProductDto;

import com.optimedica.products.service.ProductService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api")
public class ProductController {

    private final ProductService productService;


    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("listar")
    public List<ProductDto> listar() {
        return productService.listProduct();
    }


    @GetMapping("/page/{page}")
    public ResponseEntity<?> obtenerProductosPaginados(@PathVariable int page) {
        Pageable pageable = PageRequest.of(page, 5);
        return ResponseEntity.status(HttpStatus.OK).body(productService.pageProduct(pageable));

    }


    @GetMapping("/{id}")
    public ResponseEntity<?> listarPorId(@PathVariable Integer id) {
        var optionalProductById = productService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(optionalProductById);
    }

    @PostMapping("guardar")
    public ResponseEntity<?> saveProduct(@RequestBody ProductDto productDto) {
        var proSave = productService.saveProduct(productDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(proSave);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Integer id, @RequestBody ProductDto productDto) {
        var proDtoSave = productService.updateProduct(id, productDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(proDtoSave);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
