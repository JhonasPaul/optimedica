-- 10 nuevos productos de práctica (con duplicados intencionales)
INSERT INTO products
(name, description, price, stock, brand, imageUrl,
 activeFlag, category_id, created_at)
VALUES
-- Variantes de “Aviador Classic” (Lentes de Sol, categoría 1)
('Aviador Classic', 'Versión con montura plata.',        155.90, 18, 'RayVision',
 'https://cdn.example.com/img/products/aviator-classic-silver.jpg', TRUE, 1, NOW()),
('Aviador Classic', 'Versión gunmetal espejado.',        162.50, 22, 'RayVision',
 'https://cdn.example.com/img/products/aviator-classic-gunmetal.jpg', TRUE, 1, NOW()),

-- Variantes de “Urban Wayfarer” (Lentes de Sol, categoría 1)
('Urban Wayfarer', 'Montura tortoise con lentes verdes.', 104.90, 35, 'OptiStyle',
 'https://cdn.example.com/img/products/urban-wayfarer-tortoise.jpg', TRUE, 1, NOW()),
('Urban Wayfarer', 'Montura negra mate, filtro luz azul.', 97.00, 28, 'OptiStyle',
 'https://cdn.example.com/img/products/urban-wayfarer-black.jpg',    TRUE, 1, NOW()),

-- Variantes de “Lectura +1.50 Floral” (Lectura, categoría 2)
('Lectura +1.50 Floral', 'Diseño floral blanco, graduación +1.50.', 30.90, 45, 'Lectio',
 'https://cdn.example.com/img/products/reading-floral-white.jpg', TRUE, 2, NOW()),
('Lectura +1.50 Floral', 'Diseño floral rojo, graduación +1.50.',   31.50, 40, 'Lectio',
 'https://cdn.example.com/img/products/reading-floral-red.jpg',   TRUE, 2, NOW()),

-- Variante de “Lectura +2.00 Ultralight” (Lectura, categoría 2)
('Lectura +2.00 Ultralight', 'Versión ultraligera en negro.',       46.00, 25, 'Lectio',
 'https://cdn.example.com/img/products/reading-ultralight-black.jpg', TRUE, 2, NOW()),

-- Variantes de accesorios (categoría 3)
('Estuche Rígido Premium', 'Versión XL con clip metálico.',         16.90,  90, 'SafeLens',
 'https://cdn.example.com/img/products/hard-case-xl.jpg',          TRUE, 3, NOW()),
('Paño Microfibra XL',     'Color gris oscuro, 25×25 cm.',           4.70, 200, 'ClearView',
 'https://cdn.example.com/img/products/microfiber-cloth-grey.jpg', TRUE, 3, NOW()),
('Spray Anti‑empañante 30 ml', 'Fórmula intensa, mayor duración.',   9.50, 150, 'FogFree',
 'https://cdn.example.com/img/products/antifog-spray-strong.jpg',  TRUE, 3, NOW());
