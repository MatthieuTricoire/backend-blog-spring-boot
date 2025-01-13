package com.matthieu.myblog.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.matthieu.myblog.dto.ArticleDTO;
import com.matthieu.myblog.dto.CategoryDTO;
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
  public ResponseEntity<List<CategoryDTO>> getAllCategories() {
    List<Category> categories = categoryRepository.findAll();

    if (categories.isEmpty()) {
      return ResponseEntity.noContent().build();
    }

    List<CategoryDTO> categoryDTOs = categories.stream().map(this::convertToDTO).collect(Collectors.toList());
    return ResponseEntity.ok(categoryDTOs);
  }

  @GetMapping("/{id}")
  public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable Long id) {
    Category category = categoryRepository.findById(id).orElse(null);

    if (category == null) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok(convertToDTO(category));
  }

  @PostMapping
  public ResponseEntity<Category> createCategory(@RequestBody Category category) {
    Category savedCategory = categoryRepository.save(category);

    return ResponseEntity.status(HttpStatus.CREATED).body(savedCategory);
  }

  @PutMapping("/{id}")
  public ResponseEntity<CategoryDTO> updateCategory(@PathVariable Long id, @RequestBody Category categoryDetails) {
    Category category = categoryRepository.findById(id).orElse(null);
    if (category == null) {
      return ResponseEntity.notFound().build();
    }

    category.setName(categoryDetails.getName());
    // NOTE: Il ne faut pas update les éventuels articles que l'on souhaiterait
    // modifier ?

    Category categoryUpdated = categoryRepository.save(category);

    return ResponseEntity.ok(convertToDTO(categoryUpdated));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteCategory(@RequestBody Long id) {
    Category category = categoryRepository.findById(id).orElse(null);

    if (category == null) {
      return ResponseEntity.notFound().build();
    }

    categoryRepository.delete(category);
    return ResponseEntity.noContent().build();
  }

  private CategoryDTO convertToDTO(Category category) {
    CategoryDTO categoryDTO = new CategoryDTO();

    categoryDTO.setId(category.getId());
    categoryDTO.setName(category.getName());

    if (category.getArticles() != null) {
      categoryDTO.setArticles(category.getArticles().stream()
          .map(article -> {
            ArticleDTO articleDTO = new ArticleDTO();
            articleDTO.setId(article.getId());
            articleDTO.setTitle(article.getTitle());
            articleDTO.setContent(article.getContent());
            articleDTO.setUpdateAt(article.getUpdatedAt());
            articleDTO.setCategoryName(article.getCategory().getName());
            return articleDTO;
          }).collect(Collectors.toList()));
    }

    return categoryDTO;
  }

}
