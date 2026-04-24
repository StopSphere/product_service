package com.shopsphere.product_service.product_service.Controller;

import com.shopsphere.product_service.product_service.DTO.request.CreateProductRequestDTO;
import com.shopsphere.product_service.product_service.DTO.request.UpdateProductRequestDTO;
import com.shopsphere.product_service.product_service.DTO.response.PagedResponse;
import com.shopsphere.product_service.product_service.DTO.response.ProductResponseDTO;
import com.shopsphere.product_service.product_service.Service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/api/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductResponseDTO> createProduct(@RequestBody @Valid CreateProductRequestDTO dto) {
        ProductResponseDTO createdProduct = productService.createProduct(dto);
        return ResponseEntity.ok(createdProduct);
    }
    @GetMapping
    public ResponseEntity<PagedResponse<ProductResponseDTO>> getAllProducts(
            @RequestParam(defaultValue = "0" ) int page ,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue= "id") String sortBy,
            @RequestParam(defaultValue="asc") String sortDirection
    ) {
        return  ResponseEntity.ok(productService.getAllProducts(page, size,sortBy,sortDirection));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> getProductById(@PathVariable UUID id) {
        ProductResponseDTO product = productService.getProductById(id);
        return ResponseEntity.ok(product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable UUID id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> updateProduct(@PathVariable UUID id , @RequestBody UpdateProductRequestDTO dto){
        ProductResponseDTO updatedProduct=productService.updateProduct(id,dto);
        return ResponseEntity.ok(updatedProduct);
    }
@GetMapping("/search")
    public ResponseEntity<PagedResponse<ProductResponseDTO>> searchProducts(
            @RequestParam String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ){
        return ResponseEntity.ok(productService.searchProductsByName(keyword,page,size));

    }


}

