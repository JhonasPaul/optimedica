CREATE TABLE IF NOT EXISTS `categories` (
                                            `id`          INT          NOT NULL AUTO_INCREMENT,
                                            `name`        VARCHAR(50)  NOT NULL,
    `description` VARCHAR(500) NULL,
    `imageUrl`    VARCHAR(255) NULL,
    `active_flag`  BOOLEAN      NULL,
    `created_at`  DATETIME     NULL,
    PRIMARY KEY (`id`)
    );

-- Tabla: PRODUCTOS
CREATE TABLE IF NOT EXISTS `products` (
     `id`          INT          NOT NULL AUTO_INCREMENT,
    `name`        VARCHAR(45)  NOT NULL,
    `description` VARCHAR(500) NULL,
    `price`       DECIMAL(10,2) NULL,
    `stock`       INT          NULL,
    `brand`       VARCHAR(100) NULL,
    `imageUrl`    VARCHAR(255) NULL,
    `active_flag`  BOOLEAN      NULL,
    `category_id` INT          not NULL,
    `created_at`  DATETIME     NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `fk_category`
    FOREIGN KEY (`category_id`)
    REFERENCES `categories` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
    ) ;