package org.wcs.myblog.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.util.List;

public class ArticleCreateDTO {
    @NotBlank(message = "Title cannot be empty")
    @Size(min = 2, max = 50, message = "Title must be between 2 and 50 characters")
    private String title;

    @NotBlank(message = "Content cannot be empty")
    @Size(min = 10, message = "Content must be at least 2 characters")
    private String content;

    @NotNull(message = "Category id cannot be null")
    @Positive(message = "Category id must be positive")
    private Long categoryId;

    @NotEmpty(message = "Images list cannot be empty")
    private List<@Valid ImageDTO> images;

    @NotEmpty(message = "Authors list cannot be empty")
    private List<@Valid ArticleAuthorDTO> authors;

    public List<ArticleAuthorDTO> getAuthors() {
        return authors;
    }

    public void setAuthors(List<ArticleAuthorDTO> authors) {
        this.authors = authors;
    }

    public List<ImageDTO> getImages() {
        return images;
    }

    public void setImages(List<ImageDTO> images) {
        this.images = images;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
