package com.shopsphere.product_service.product_service.ServiceTest;

import com.shopsphere.product_service.product_service.Entity.Product;
import com.shopsphere.product_service.product_service.Exception.InsufficientStockException;
import com.shopsphere.product_service.product_service.Exception.ProductNotFoundException;
import com.shopsphere.product_service.product_service.Mapper.ProductMapper;
import com.shopsphere.product_service.product_service.Repository.ProductRepository;
import com.shopsphere.product_service.product_service.Service.impl.ProductServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    private ProductMapper productMapper;

    @Test
    void shouldReduceStockSuccessfully() {
        UUID productId = UUID.randomUUID();
        Product product = new Product();
        product.setId(productId);
        product.setQuantity(10);
        product.setAvailable(true);
        when(productRepository.findById(productId)).thenReturn(Optional.of(product));
        productService.reduceStock(productId, 5);
        assertEquals(5, product.getQuantity());
        assertTrue(product.isAvailable());
        verify(productRepository).save(product);
    }

    @Test
    void shouldThrowException() {
        UUID productId = UUID.randomUUID();
        Product product = new Product();
        product.setId(productId);
        product.setQuantity(3);
        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        assertThrows(InsufficientStockException.class, () -> productService.reduceStock(productId, 5));

        verify(productRepository, never()).save(any());
    }

    @Test
    void shouldThrowExceptionWhenProductNotFound() {
        UUID productId = UUID.randomUUID();
        when(productRepository.findById(productId)).thenReturn(Optional.empty());
        assertThrows(ProductNotFoundException.class, () -> productService.reduceStock(productId, 5));
    }

    @Test
    void shouldUpdateStockSuccessfully() {
        UUID productId = UUID.randomUUID();
        Product product = new Product();
        product.setId(productId);
        product.setQuantity(10);

        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        productService.updateStock(productId, 5);
        assertEquals(5, product.getQuantity());
        assertTrue(product.isAvailable());
        verify(productRepository).save(product);
    }


}

