package com.kaiq.dscatalog.services;

import com.kaiq.dscatalog.dto.ProductDTO;
import com.kaiq.dscatalog.repositories.ProductRepository;
import com.kaiq.dscatalog.services.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class ProductServiceIT {

    @Autowired
    private ProductService service;

    @Autowired
    private ProductRepository repository;

    private Long existingId;
    private Long nonExistingId;
    private Long countTotalProducts;

    @BeforeEach
    void SetUp() throws Exception {

        existingId = 1L;
        nonExistingId = 1000L;
        countTotalProducts = 25L;

    }

    @Test
    public void deleteShouldDeleteWhenIdExists() {

        service.delete(existingId);

        Assertions.assertEquals(countTotalProducts -1, repository.count());
    }

    @Test
    public void deleteShouldThrowResourceNotFoundExceptionWhenIdDoesNotExist() {

        Assertions.assertThrowsExactly(ResourceNotFoundException.class ,() ->{
            service.delete(nonExistingId);
        });
    }

    @Test
    public void findAllPagedShouldReturnPage() {

        PageRequest page = PageRequest.of(0, 10);

        Page<ProductDTO> result = service.findAllPaged(page);

        Assertions.assertFalse(result.isEmpty());
        Assertions.assertEquals(0, result.getNumber());
        Assertions.assertEquals(10, result.getSize());
        Assertions.assertEquals(3, result.getTotalPages());
        Assertions.assertEquals(25, result.getTotalElements());
    }

    @Test
    public void findAllPagedShouldReturnEmptyPageWhenPageDoesNotExist() {

        PageRequest page = PageRequest.of(4, 10);

        Page<ProductDTO> result = service.findAllPaged(page);

        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    public void findAllPagedShouldReturnSortedPageWhenSortByName() {

        PageRequest page = PageRequest.of(0, 10, Sort.by("name"));

        Page<ProductDTO> result = service.findAllPaged(page);

        Assertions.assertEquals("Macbook Pro", result.getContent().get(0).getName());
        Assertions.assertEquals("PC Gamer", result.getContent().get(1).getName());
        Assertions.assertEquals("PC Gamer Alfa", result.getContent().get(2).getName());
    }

}
