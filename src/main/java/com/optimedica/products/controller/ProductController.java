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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        return productService.listarProductDto();
    }


    @GetMapping("/page/{page}")
    public ResponseEntity<?> obtenerProductosPaginados(@PathVariable int page) {
        Pageable pageable = PageRequest.of(page, 5);
        return ResponseEntity.status(HttpStatus.OK).body(productService.paginarProducto(pageable));

    }


    @GetMapping("/{id}")
    public ResponseEntity<?> listarPorId(@PathVariable Integer id) {
        Map<String, String> map = new HashMap<>();
        Optional<ProductDto> productDtoOptional = productService.listrPorId(id);
        if (productDtoOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(productService.listrPorId(id));
        }
        map.put("mensaje ", "el id " + id + " no existe en la base de datos");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
    }
}
