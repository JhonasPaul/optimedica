package com.optimedica.products.controller;

import com.optimedica.products.dto.ProductDto;
import com.optimedica.products.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.optimedica.common.exception.ErrorResponse;

import java.util.List;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Slf4j
// Agrupa todos los endpoints de este controlador bajo la etiqueta "Productos"
@Tag(name = "Productos", description = "Operaciones relacionadas con productos")
@RestController
@RequestMapping("api")
public class ProductController {

    private final ProductService productService;


    // ✅ Obtiene todos los productos
    @Operation(summary = "Obtener todos los productos") // ✅ Describe brevemente qué hace el endpoint
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Productos obtenidos exitosamente",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = ProductDto.class)))),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @GetMapping("listar")
    public List<ProductDto> listar() {
        return productService.listProduct();
    }


    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    // 🔹 Anotación que describe qué hace este endpoint para Swagger UI
    @Operation(summary = "Listar producto paginados") //  Breve resumen visible en la UI de Swagger
// 🔹 Indica que si todo va bien, se devuelve un JSON con un producto
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", // ✅ Código de respuesta HTTP esperada
                    description = "Lista paginada de productos",
                    content = @Content(mediaType = "application/json", //  Tipo de contenido de la respuesta
                            array = @ArraySchema(schema = @Schema(implementation = ProductDto.class)))), //  Esquema que representa el producto devuelto
            @ApiResponse(responseCode = "404", description = "Producto no encontrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/page/{page}")
    public ResponseEntity<?> obtenerProductosPaginados(@PathVariable int page) {
        Pageable pageable = PageRequest.of(page, 5);
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        log.info("📥 En el controlador, autenticado como: {}", auth != null ? auth.getName() : "SIN AUTENTICAR");

//        log.info("🔍 Entrando al método pageProduct() con pageable: {}", page);
        return ResponseEntity.status(HttpStatus.OK).body(productService.pageProduct(pageable));
    }

    @PreAuthorize("hasRole('ADMIN')")
    // 🔹 Anotación que describe qué hace este endpoint para Swagger UI
    @Operation(summary = "Obtener producto por ID") //  Breve resumen visible en la UI de Swagger
// 🔹 Indica que si todo va bien, se devuelve un JSON con un producto
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", //  Código de respuesta HTTP esperada
                    description = "Producto encontrado correctamente",
                    content = @Content(mediaType = "application/json", //  Tipo de contenido de la respuesta
                            schema = @Schema(implementation = ProductDto.class))), //  Esquema que representa el producto devuelto
            @ApiResponse(responseCode = "404", description = "Producto no encontrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> listarPorId(@PathVariable Integer id) {
        var optionalProductById = productService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(optionalProductById);
    }


    @Operation(summary = "Agregar producto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto agregado exitosamente",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProductDto.class))),
            @ApiResponse(responseCode = "400", description = "Datos inválidos para agregar el producto",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping("guardar")
    public ResponseEntity<?> saveProduct(@RequestBody ProductDto productDto) {
        var proSave = productService.saveProduct(productDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(proSave);
    }


    @Operation(summary = "Actualizar producto existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto actualizado exitosamente",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProductDto.class))),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "400", description = "Datos inválidos para actualizar el producto",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Integer id, @RequestBody ProductDto productDto) {
        var proDtoSave = productService.updateProduct(id, productDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(proDtoSave);
    }


    @Operation(summary = "Elimina un producto por el id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Producto actualizado"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
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
