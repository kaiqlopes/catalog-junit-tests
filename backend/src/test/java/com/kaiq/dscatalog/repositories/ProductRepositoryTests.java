package com.kaiq.dscatalog.repositories;

import com.kaiq.dscatalog.entities.Product;
import com.kaiq.dscatalog.factories.ProductFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
public class ProductRepositoryTests {

    private ProductRepository repository;

    private long existingId;
    private long nonExistentId;
    private int productActualCount;

    @Autowired
    public ProductRepositoryTests(ProductRepository repository) {
        this.repository = repository;
    }

    @BeforeEach
    void setUp() {
        existingId = 1L;
        nonExistentId = 1000L;
        productActualCount = 25;
    }

    @Test
    public void deleteShouldDeleteObjectWhenIdExists() {

        repository.deleteById(existingId);

        Optional<Product> result = repository.findById(existingId);

        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    public void findByIdShouldReturnNonEmptyOptionalWhenIdExists() {

        Optional<Product> result = repository.findById(existingId);

        Assertions.assertTrue(result.isPresent());
    }

    @Test
    public void findByIdShouldReturnEmptyOptionalWhenNonExistentId() {

        Optional<Product> result = repository.findById(nonExistentId);

        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    public void saveShouldPersistWithAutoIncrementWhenIdIsNull() {

        Product product = ProductFactory.createProduct();
        product.setId(null);

        product = repository.save(product);

        Assertions.assertNotNull(product.getId());
        Assertions.assertEquals(productActualCount + 1, product.getId());
    }

}
