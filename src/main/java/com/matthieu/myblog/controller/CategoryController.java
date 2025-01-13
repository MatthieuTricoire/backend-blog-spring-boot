package com.matthieu.myblog.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.matthieu.myblog.model.Category;
import com.matthieu.myblog.repository.CategoryRepository;

@RestController
@RequestMapping("/categories")

public class CategoryController {
  private final CategoryRepository categoryRepository;

  public CategoryController(CategoryRepository categoryRepository) {
    this.categoryRepository = categoryRepository;
  }

  @GetMapping
  public ResponseEntity<List<Category>> getAllCategories() {
    List<Category> categories = categoryRepository.findAll();

    if (categories.isEmpty()) {
      return ResponseEntity.noContent().build();
    }

    return ResponseEntity.ok(categories);
  }

  @PostMapping
  public ResponseEntity<Category> createCategory(@RequestBody Category category) {
    Category savedCategory = categoryRepository.save(category);

    return ResponseEntity.status(HttpStatus.CREATED).body(savedCategory);
  }
}
