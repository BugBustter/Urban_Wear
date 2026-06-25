package com.urbanwear.backend.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
public class ProductDto {
    private Long id;
    private String name;
    private String brand;
    private String description;
    private BigDecimal price;
    private BigDecimal discountPercentage;
    private BigDecimal priceAfterDiscount;
    private Double rating;
    private Integer numReviews;
    private String categoryName;
    private List<String> imageUrls;
    // We can add variants later if needed
}
