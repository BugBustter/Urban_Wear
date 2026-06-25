package com.urbanwear.backend.service;

import com.urbanwear.backend.dto.PageResponse;
import com.urbanwear.backend.dto.ProductDto;
import com.urbanwear.backend.model.Product;
import com.urbanwear.backend.model.ProductImage;
import com.urbanwear.backend.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public PageResponse<ProductDto> getProducts(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> products = productRepository.findAll(pageable);
        return mapToPageResponse(products);
    }

    public PageResponse<ProductDto> getProductsByCategory(Long categoryId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> products = productRepository.findByCategoryId(categoryId, pageable);
        return mapToPageResponse(products);
    }

    public PageResponse<ProductDto> searchProducts(String keyword, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> products = productRepository.searchProducts(keyword, pageable);
        return mapToPageResponse(products);
    }

    private PageResponse<ProductDto> mapToPageResponse(Page<Product> productsPage) {
        List<ProductDto> content = productsPage.getContent().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());

        return PageResponse.<ProductDto>builder()
                .content(content)
                .pageNumber(productsPage.getNumber())
                .pageSize(productsPage.getSize())
                .totalElements(productsPage.getTotalElements())
                .totalPages(productsPage.getTotalPages())
                .isLast(productsPage.isLast())
                .build();
    }

    private ProductDto mapToDto(Product product) {
        List<String> imageUrls = product.getImages() != null ?
                product.getImages().stream().map(ProductImage::getUrl).collect(Collectors.toList()) :
                List.of();

        return ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .brand(product.getBrand())
                .description(product.getDescription())
                .price(product.getPrice())
                .discountPercentage(product.getDiscountPercentage())
                .priceAfterDiscount(product.getPriceAfterDiscount())
                .rating(product.getRating())
                .numReviews(product.getNumReviews())
                .categoryName(product.getCategory() != null ? product.getCategory().getName() : null)
                .imageUrls(imageUrls)
                .build();
    }
}
