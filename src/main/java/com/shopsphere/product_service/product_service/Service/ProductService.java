package com.shopsphere.product_service.product_service.Service;

import com.shopsphere.product_service.product_service.DTO.request.CreateProductRequestDTO;
import com.shopsphere.product_service.product_service.DTO.request.UpdateProductRequestDTO;
import com.shopsphere.product_service.product_service.DTO.response.ProductResponseDTO;

import java.util.List;
import java.util.UUID;

public interface ProductService {

    ProductResponseDTO createProduct(CreateProductRequestDTO dto);

    ProductResponseDTO updateProduct(UUID id, UpdateProductRequestDTO dto);

    List<ProductResponseDTO> getAllProducts();

    ProductResponseDTO getProductById(UUID id);

    void deleteProduct(UUID id);
}
