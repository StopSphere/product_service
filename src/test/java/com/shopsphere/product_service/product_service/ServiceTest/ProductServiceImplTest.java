package com.shopsphere.product_service.product_service.ServiceTest;

import com.shopsphere.product_service.product_service.DTO.request.CreateProductRequestDTO;
import com.shopsphere.product_service.product_service.DTO.request.UpdateProductRequestDTO;
import com.shopsphere.product_service.product_service.DTO.response.PagedResponse;
import com.shopsphere.product_service.product_service.DTO.response.ProductResponseDTO;
import com.shopsphere.product_service.product_service.Entity.Product;
import com.shopsphere.product_service.product_service.Exception.ProductNotFoundException;
import com.shopsphere.product_service.product_service.Mapper.ProductMapper;
import com.shopsphere.product_service.product_service.Repository.ProductRepository;
import com.shopsphere.product_service.product_service.Service.impl.ProductServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductMapper productMapper;

    @InjectMocks
    private ProductServiceImpl productService;

    @Test
    void shouldCreateProductSuccessfully() {

        CreateProductRequestDTO request =
                new CreateProductRequestDTO();

        Product product = new Product();

        ProductResponseDTO response =
                new ProductResponseDTO();

        when(productMapper.toEntity(request))
                .thenReturn(product);

        when(productMapper.toResponseDTO(product))
                .thenReturn(response);

        ProductResponseDTO result =
                productService.createProduct(request);

        assertEquals(response, result);

        verify(productRepository)
                .save(product);
    }

    @Test
    void shouldUpdateProductSuccessfully() {

        UUID productId = UUID.randomUUID();

        Product product = new Product();

        UpdateProductRequestDTO request =
                new UpdateProductRequestDTO();

        ProductResponseDTO response =
                new ProductResponseDTO();

        when(productRepository.findById(productId))
                .thenReturn(Optional.of(product));

        when(productMapper.toResponseDTO(product))
                .thenReturn(response);

        ProductResponseDTO result =
                productService.updateProduct(
                        productId,
                        request
                );

        assertEquals(response, result);

        verify(productMapper)
                .updateProductFromDto(
                        request,
                        product
                );

        verify(productRepository)
                .save(product);
    }

    @Test
    void shouldThrowExceptionWhenUpdatingUnknownProduct() {

        UUID productId = UUID.randomUUID();

        when(productRepository.findById(productId))
                .thenReturn(Optional.empty());

        assertThrows(
                ProductNotFoundException.class,
                () -> productService.updateProduct(
                        productId,
                        new UpdateProductRequestDTO()
                )
        );
    }

    @Test
    void shouldGetProductByIdSuccessfully() {

        UUID productId = UUID.randomUUID();

        Product product = new Product();

        ProductResponseDTO response =
                new ProductResponseDTO();

        when(productRepository.findById(productId))
                .thenReturn(Optional.of(product));

        when(productMapper.toResponseDTO(product))
                .thenReturn(response);

        ProductResponseDTO result =
                productService.getProductById(productId);

        assertEquals(response, result);

        verify(productRepository)
                .findById(productId);
    }

    @Test
    void shouldThrowExceptionWhenProductNotFound() {

        UUID productId = UUID.randomUUID();

        when(productRepository.findById(productId))
                .thenReturn(Optional.empty());

        assertThrows(
                ProductNotFoundException.class,
                () -> productService.getProductById(productId)
        );
    }

    @Test
    void shouldDeleteProductSuccessfully() {

        UUID productId = UUID.randomUUID();

        productService.deleteProduct(productId);

        verify(productRepository)
                .deleteById(productId);
    }

    @Test
    void shouldSearchProductsByNameSuccessfully() {

        Product product = new Product();

        ProductResponseDTO response =
                new ProductResponseDTO();

        Page<Product> page =
                new PageImpl<>(List.of(product));

        when(
                productRepository
                        .findByNameContainingIgnoreCase(
                                eq("iphone"),
                                any(Pageable.class)
                        )
        ).thenReturn(page);

        when(productMapper.toResponseDTO(product))
                .thenReturn(response);

        PagedResponse<ProductResponseDTO> result =
                productService.searchProductsByName(
                        "iphone",
                        0,
                        10
                );

        assertEquals(
                1,
                result.getProducts().size()
        );
    }

    @Test
    void shouldGetAllProductsSuccessfully() {

        Product product = new Product();

        ProductResponseDTO response =
                new ProductResponseDTO();

        Page<Product> page =
                new PageImpl<>(List.of(product));

        when(
                productRepository.findAll(
                        any(Pageable.class)
                )
        ).thenReturn(page);

        when(productMapper.toResponseDTO(product))
                .thenReturn(response);

        PagedResponse<ProductResponseDTO> result =
                productService.getAllProducts(
                        0,
                        10,
                        "name",
                        "asc"
                );

        assertEquals(
                1,
                result.getProducts().size()
        );
    }
}