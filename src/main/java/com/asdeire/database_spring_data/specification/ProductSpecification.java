package com.asdeire.database_spring_data.specification;

import com.asdeire.database_spring_data.model.Product;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class ProductSpecification {

    public static Specification<Product> hasCategory(Long categoryId) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.join("category", JoinType.INNER).get("id"), categoryId);
    }

    public static Specification<Product> priceBetween(BigDecimal minPrice, BigDecimal maxPrice) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.between(root.get("price"), minPrice, maxPrice);
    }

    public static Specification<Product> hasNameLike(String name) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get("name"), "%" + name + "%");
    }
}
