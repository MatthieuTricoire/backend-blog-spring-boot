package org.wcs.myblog.mapper;

import org.springframework.stereotype.Component;
import org.wcs.myblog.dto.ArticleDTO;
import org.wcs.myblog.dto.CategoryCreateDTO;
import org.wcs.myblog.dto.CategoryDTO;
import org.wcs.myblog.model.Category;

import java.util.stream.Collectors;

@Component
public class CategoryMapper {

    public Category convertToEntity(CategoryCreateDTO categoryCreateDTO) {
        Category category = new Category();
        category.setName(categoryCreateDTO.getName());
        return category;
    }

    public CategoryDTO convertToDTO(Category category) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(category.getId());
        categoryDTO.setName(category.getName());
        if (category.getArticles() != null) {
            categoryDTO.setArticles(category.getArticles().stream().map(article -> {
                ArticleDTO articleDTO = new ArticleDTO();
                articleDTO.setId(article.getId());
                articleDTO.setTitle(article.getTitle());
                articleDTO.setContent(article.getContent());
                articleDTO.setUpdatedAt(article.getUpdatedAt());
                articleDTO.setCategoryName(article.getCategory().getName());
                return articleDTO;
            }).collect(Collectors.toList()));
        }
        return categoryDTO;
    }
}
