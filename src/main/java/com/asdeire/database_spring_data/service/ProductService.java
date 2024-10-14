package com.asdeire.database_spring_data.service;

import com.asdeire.database_spring_data.model.Product;
import com.asdeire.database_spring_data.repository.ProductRepository;
import com.asdeire.database_spring_data.specification.ProductSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Page<Product> getAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public List<Product> getProductsByCategory(Long categoryId) {
        return productRepository.findByCategoryId(categoryId);
    }

    public Page<Product> getSortedProducts(String sortBy, Pageable pageable) {
        return productRepository.findAll(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(sortBy)));
    }

    public Page<Product> searchProducts(Long categoryId, BigDecimal minPrice, BigDecimal maxPrice, String name, Pageable pageable) {
        Specification<Product> spec = Specification.where(null);

        if (categoryId != null) {
            spec = spec.and(ProductSpecification.hasCategory(categoryId));
        }

        if (minPrice != null && maxPrice != null) {
            spec = spec.and(ProductSpecification.priceBetween(minPrice, maxPrice));
        }

        if (name != null && !name.isEmpty()) {
            spec = spec.and(ProductSpecification.hasNameLike(name));
        }

        return productRepository.findAll(spec, pageable);
    }
}
