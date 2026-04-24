package com.shopsphere.product_service.product_service.DTO.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PagedResponse<T> {
    private List<T> products;
    private int page;
    private int size;
    private long total;
    private int totalPages;
    private Boolean isLastPage;

}
