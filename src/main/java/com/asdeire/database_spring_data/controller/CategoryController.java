package com.asdeire.database_spring_data.controller;

import com.asdeire.database_spring_data.model.Category;
import com.asdeire.database_spring_data.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String getAllCategories(Model model) {
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        return "category-list";
    }

    @GetMapping("/{id}")
    public String getCategoryById(@PathVariable Long id, Model model) {
        Optional<Category> category = categoryService.getCategoryById(id);
        if (category.isPresent()) {
            model.addAttribute("category", category.get());
            return "category-detail";
        } else {
            return "error/404";
        }
    }

    @GetMapping("/new")
    public String showCreateCategoryForm(Model model) {
        model.addAttribute("category", new Category());
        return "category-form";
    }

    @PostMapping
    public String createCategory(@ModelAttribute Category category) {
        categoryService.saveCategory(category);
        return "redirect:/categories";
    }

    @DeleteMapping("/{id}")
    public String deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return "redirect:/categories";
    }
}
