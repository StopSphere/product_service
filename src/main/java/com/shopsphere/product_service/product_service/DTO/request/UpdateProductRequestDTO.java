package com.shopsphere.product_service.product_service.DTO.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateProductRequestDTO {

    private String name;
    private String description;
    private double price;
    private int quantity;
    private String category;
    private String imageUrl;
}
