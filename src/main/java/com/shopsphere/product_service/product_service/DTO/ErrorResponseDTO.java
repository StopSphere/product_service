package com.shopsphere.product_service.product_service.DTO;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ErrorResponseDTO {

    private LocalDateTime timestamp;
    private int status;
    private String message;
    private String error;
    private String path;

}
