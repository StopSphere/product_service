package com.shopsphere.product_service.product_service.Service.impl;

import com.shopsphere.product_service.product_service.DTO.request.CreateProductRequestDTO;
import com.shopsphere.product_service.product_service.DTO.request.UpdateProductRequestDTO;
import com.shopsphere.product_service.product_service.DTO.response.ProductResponseDTO;
import com.shopsphere.product_service.product_service.Entity.Product;
import com.shopsphere.product_service.product_service.Mapper.ProductMapper;
import com.shopsphere.product_service.product_service.Repository.ProductRepository;
import com.shopsphere.product_service.product_service.Service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public ProductResponseDTO createProduct(CreateProductRequestDTO dto) {
        Product product =productMapper.toEntity(dto);
            product.setAvailable(product.getQuantity()>0);
            productRepository.save(product);
            return productMapper.toResponseDTO(product);
    }

    @Override
    public ProductResponseDTO updateProduct(UUID id, UpdateProductRequestDTO dto) {
        Product product =productRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Product not found with id: "+id));
        productMapper.updateProductFromDto(dto,product);
        product.setAvailable(product.getQuantity()>0);
        productRepository.save(product);
        return productMapper.toResponseDTO(product);
    }

    @Override
    public List<ProductResponseDTO> getAllProducts() {
        return productRepository.findAll().stream()
                .map(productMapper::toResponseDTO)
                .toList();
    }

    @Override
    public ProductResponseDTO getProductById(UUID id) {
        return productRepository.findById(id)
                .map(productMapper::toResponseDTO)
                .orElseThrow(()-> new RuntimeException("Product not found with id: "+id));
    }

    @Override
    public Void deleteProduct(UUID id) {
        productRepository.deleteById(id);
        return null;
    }
}
