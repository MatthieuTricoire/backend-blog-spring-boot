package org.wcs.myblog.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.wcs.myblog.dto.ArticleDTO;
import org.wcs.myblog.model.Article;
import org.wcs.myblog.service.ArticleService;

@RequestMapping("/articles")
public class ArticleController {

  private final ArticleService articleService;

  public ArticleController(ArticleService articleService) {
    this.articleService = articleService;
  }

  @GetMapping
  public ResponseEntity<List<ArticleDTO>> getAllArticles() {
    List<ArticleDTO> articles = articleService.getAllArticles();
    if (articles.isEmpty()) {
      return ResponseEntity.noContent().build();
    }
    return ResponseEntity.ok(articles);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ArticleDTO> getArticleById(@PathVariable Long id) {
    ArticleDTO article = articleService.getArticleById(id);
    if (article == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(article);
  }

  @PostMapping
  public ResponseEntity<ArticleDTO> createArticle(@RequestBody Article article) {
    ArticleDTO savedArticle = articleService.createArticle(article);
    return ResponseEntity.status(HttpStatus.CREATED).body(savedArticle);
  }

  @PutMapping("/{id}")
  public ResponseEntity<ArticleDTO> updateArticle(@PathVariable Long id, @RequestBody Article articleDetails) {
    ArticleDTO updatedArticle = articleService.updateArticle(id, articleDetails);
    if (updatedArticle == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(updatedArticle);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteArticle(@PathVariable Long id) {
    if (articleService.deleteArticle(id)) {
      return ResponseEntity.noContent().build();
    } else {
      return ResponseEntity.notFound().build();
    }
  }
}
