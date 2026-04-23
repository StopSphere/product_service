package com.shopsphere.product_service.product_service.DTO.response;

import lombok.*;

import java.util.UUID;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponseDTO {
    private UUID id;
    private String name;
    private String description;
    private double price;
    private int quantity;
    private String category;
    private String imageUrl;
    private boolean available;
}
