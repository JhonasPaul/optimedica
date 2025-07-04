package com.optimedica.products.controller;

import com.optimedica.products.dto.ProductDto;
import com.optimedica.products.mapper.ProductMapper;
import com.optimedica.products.repository.ProductRepository;
import com.optimedica.products.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

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
        Map<String, String> map = new HashMap<>();
        Optional<ProductDto> productDtoOptional = productService.findById(id);
        if (productDtoOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(productService.findById(id));
        }
        map.put("mensaje ", "el id " + id + " no existe en la base de datos");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
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
}
