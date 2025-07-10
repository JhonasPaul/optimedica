package com.optimedica.products.controller;

import com.optimedica.products.dto.ProductDto;

import com.optimedica.products.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api")
public class ProductController {

    private final ProductService productService;



    // ✅ Obtiene todos los productos
    @Operation(summary = "Obtener todos los productos") // ✅ Describe brevemente qué hace el endpoint
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Productos obtenidos exitosamente"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping("listar")
    public List<ProductDto> listar() {
        return productService.listProduct();
    }


    // Indica que este método sirve para obtener productos paginados.
// El parámetro `page` representa el número de página solicitado.
    @Operation(
            summary = "Listar productos paginados", // Título breve que aparecerá en Swagger
            description = "Devuelve una lista paginada de productos, 5 por página." // Explicación más detallada
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", // Código HTTP que se devuelve cuando todo sale bien
                    description = "Lista de productos obtenida correctamente", // Descripción del éxito
                    content = @Content(mediaType = "application/json") // Tipo de respuesta
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Número de página inválido (por ejemplo, negativo)", // Lo que podría causar un 400
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Error interno del servidor",
                    content = @Content
            )
    })
    @GetMapping("/page/{page}")
    public ResponseEntity<?> obtenerProductosPaginados(@PathVariable int page) {
        Pageable pageable = PageRequest.of(page, 5);
        return ResponseEntity.status(HttpStatus.OK).body(productService.pageProduct(pageable));

    }



    // ✅ Obtiene un producto por su ID
    @Operation(summary = "Obtener producto por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto encontrado",
                    content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor"),

    })
    @GetMapping("/{id}")
    public ResponseEntity<?> listarPorId(@PathVariable Integer id) {
        var optionalProductById = productService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(optionalProductById);
    }


    // ✅ Crea un nuevo producto
    @Operation(summary = "Crear nuevo producto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Producto creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos para crear el producto"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PostMapping("guardar")
    public ResponseEntity<?> saveProduct(@RequestBody ProductDto productDto) {
        var proSave = productService.saveProduct(productDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(proSave);
    }


    // ✅ Actualiza un producto existente
    @Operation(summary = "Actualizar producto existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto actualizado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos para actualizar el producto"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Integer id, @RequestBody ProductDto productDto) {
        var proDtoSave = productService.updateProduct(id, productDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(proDtoSave);
    }


    // ✅ Elimina un producto por su ID
    @Operation(summary = "Eliminar producto por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Producto eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    public ProductController(ProductService productService) {
        this.productService = productService;
    }
}
