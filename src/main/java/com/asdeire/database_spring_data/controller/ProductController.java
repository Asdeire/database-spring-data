package com.asdeire.database_spring_data.controller;

import com.asdeire.database_spring_data.model.Product;
import com.asdeire.database_spring_data.service.CategoryService;
import com.asdeire.database_spring_data.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String listProducts(Model model, @RequestParam(defaultValue = "0") int page) {
        Page<Product> products = productService.getAllProducts(PageRequest.of(page, 10));
        model.addAttribute("products", products);
        return "product-list";
    }

    @GetMapping("/by-category")
    public String getProductsByCategory(@RequestParam Long categoryId, Model model) {
        List<Product> products = productService.getProductsByCategory(categoryId);
        model.addAttribute("products", products);
        return "product-list";
    }

    @GetMapping("/new")
    public String showCreateProductForm(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("categories", categoryService.getAllCategories());
        return "product-form";
    }

    @PostMapping()
    public String createProduct(@ModelAttribute Product product, BindingResult result) {
        if (result.hasErrors()) {
            return "product-form";
        }
        productService.saveProduct(product);
        return "redirect:/products";
    }

    @GetMapping("/sorted")
    public String getSortedProducts(@RequestParam String sortBy, Model model, Pageable pageable) {
        Page<Product> products = productService.getSortedProducts(sortBy, pageable);
        model.addAttribute("products", products);
        return "sorted-product-list";
    }

    @GetMapping("/search")
    public String searchProducts(
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) BigDecimal minPrice,
            @RequestParam(required = false) BigDecimal maxPrice,
            @RequestParam(required = false) String name,
            @RequestParam(defaultValue = "0") int page,
            Model model) {

        Page<Product> products = productService.searchProducts(categoryId, minPrice, maxPrice, name, PageRequest.of(page, 10));
        model.addAttribute("products", products.getContent());
        return "product-list";
    }
}
