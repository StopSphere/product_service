package com.shopsphere.product_service.product_service.Service;

import com.shopsphere.product_service.product_service.DTO.request.CreateProductRequestDTO;
import com.shopsphere.product_service.product_service.DTO.request.UpdateProductRequestDTO;
import com.shopsphere.product_service.product_service.DTO.response.PagedResponse;
import com.shopsphere.product_service.product_service.DTO.response.ProductResponseDTO;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public interface ProductService {

    ProductResponseDTO createProduct(CreateProductRequestDTO dto);

    ProductResponseDTO updateProduct(UUID id, UpdateProductRequestDTO dto);

    PagedResponse<ProductResponseDTO> getAllProducts(int page, int size);

    ProductResponseDTO getProductById(UUID id);

    Void deleteProduct(UUID id);
}
