package com.shopsphere.product_service.product_service.Exception;

import com.shopsphere.product_service.product_service.DTO.ErrorResponseDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@Repository
public class GlobalExceptionHandler {
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleProductNotFoundException(ProductNotFoundException ex, HttpServletRequest request) {
        ErrorResponseDTO errorResponse = ErrorResponseDTO.builder()
                        .timestamp(LocalDateTime.now())
                        .status(404)
                        .error("Not Found")
                        .message(ex.getMessage())
                        .path(request.getRequestURI())
                                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleException(Exception ex, HttpServletRequest request) {
        ErrorResponseDTO error=ErrorResponseDTO.builder()
                .timestamp(LocalDateTime.now())
                .status(500)
                .error("Internal Server Error")
                .message(ex.getMessage())
                .path(request.getRequestURI())
                .build();
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
