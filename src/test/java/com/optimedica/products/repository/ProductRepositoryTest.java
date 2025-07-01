package com.optimedica.products.repository;

import com.optimedica.common.entity.BaseId;
import com.optimedica.products.entity.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
public class ProductRepositoryTest extends BaseId {

    @Autowired
    private ProductRepository productRepository;

    // 🔧 Arrange: preparación de datos antes de cada prueba
    // guardas un producto de ejemplo para luego verificar si lo recuperas correctamente.
    @BeforeEach
    void setUp() {
        List<Product> productos = new ArrayList<>();
        for (int i = 1; i <= 15; i++) {
            Product producto = new Product();
            producto.setName("Producto " + i);
            producto.setDescription("Descripción " + i);
            producto.setPrice(new BigDecimal("100.00"));
            producto.setStock(5);
            producto.setBrand("Marca");
            producto.setImageURL("imagen.jpg");
            producto.setActiveFlag(1);
            producto.setCategoryID(1);
            producto.setCreatedAt(new Date());
            productos.add(producto);
        }
        productRepository.saveAll(productos);
    }

    @Test
    @DisplayName("Debería encontrar todos los productos guardados")
    void testFindAllProducts() {
        // 🚀 Act: ejecutar la acción a probar
        List<Product> products = productRepository.findAll();

        // ✅ Assert: verificar los resultados esperados
        assertThat(products).hasSize(15);
    }

    @Test
    @DisplayName("Debería encontrar los productos por página")
    void testPaginarProductos() {
        // 🚀 Act: ejecutar la acción a probar
        Pageable pageable = PageRequest.of(0, 10);
        Page<Product> page = productRepository.findAll(pageable);

        // ✅ Assert: verificar los resultados esperados
        assertThat(page.getContent()).hasSize(10); // total de productos por paginas
        assertThat(page.getTotalElements()).isEqualTo(15); // total de productos isnertados
        assertThat(page.getTotalPages()).isEqualTo(2); // 2  paginas en total, una con 10 y la otra con 5
    }

    @Test
    @DisplayName("Deberia encontrar el producto por id")
    void testProductoById(){


        // 🚀 Act: buscar el producto por su ID
        List<Product> products = productRepository.findAll();

        Integer idGuardado = products.getFirst().getId();
        Optional<Product> resultado = productRepository.findById(idGuardado);

        // ✅ Assert: verificar que el producto exista y sea correcto
        assertThat(resultado).isPresent();
        assertThat(resultado.get().getName()).isEqualTo("Producto 1");
    }
}