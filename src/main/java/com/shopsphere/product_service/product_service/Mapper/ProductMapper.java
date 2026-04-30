package com.shopsphere.product_service.product_service.Mapper;

import com.shopsphere.product_service.product_service.DTO.request.CreateProductRequestDTO;
import com.shopsphere.product_service.product_service.DTO.request.UpdateProductRequestDTO;
import com.shopsphere.product_service.product_service.DTO.response.ProductResponseDTO;
import com.shopsphere.product_service.product_service.Entity.Product;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductMapper {

    Product toEntity(CreateProductRequestDTO dto);

    ProductResponseDTO toResponseDTO(Product product);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateProductFromDto(UpdateProductRequestDTO dto , @MappingTarget Product product);

}
