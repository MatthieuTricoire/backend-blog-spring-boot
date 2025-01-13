package com.matthieu.myblog.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.matthieu.myblog.model.Article;
import com.matthieu.myblog.repository.ArticleRepository;

@RestController
@RequestMapping("/articles")
public class ArticleController {
  private final ArticleRepository articleRepository;

  public ArticleController(ArticleRepository articleRepository) {
    this.articleRepository = articleRepository;
  }

  @GetMapping
  public ResponseEntity<List<Article>> getAllArticles() {
    List<Article> articles = articleRepository.findAll();
    if (articles.isEmpty()) {
      return ResponseEntity.noContent().build();
    }
    return ResponseEntity.ok(articles);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Article> getArticleById(@PathVariable Long id) {
    Article article = articleRepository.findById(id).orElse(null);

    if (article == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(article);
  }

  @PostMapping
  public ResponseEntity<Article> createArticle(@RequestBody Article article) {
    article.setCreatedAt(LocalDateTime.now());
    article.setUpdatedAt(LocalDateTime.now());

    Article savedArticle = articleRepository.save(article);

    return ResponseEntity.status(HttpStatus.CREATED).body(savedArticle);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Article> updateArticle(@PathVariable Long id, @RequestBody Article articleDetails) {
    Article article = articleRepository.findById(id).orElse(null);

    if (article == null) {
      return ResponseEntity.notFound().build();
    }

    article.setTitle(articleDetails.getTitle());
    article.setContent(articleDetails.getContent());
    article.setUpdatedAt(LocalDateTime.now());

    Article updatedArticle = articleRepository.save(article);
    return ResponseEntity.ok(updatedArticle);

  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteArticle(@PathVariable Long id) {
    Article article = articleRepository.findById(id).orElse(null);

    if (article == null) {
      return ResponseEntity.notFound().build();
    }

    articleRepository.delete(article);
    return ResponseEntity.noContent().build();
  }

  @GetMapping("/search-title")
  public ResponseEntity<List<Article>> getArticlesByTitle(@RequestParam String searchTerms) {
    List<Article> articles = articleRepository.findByTitle(searchTerms);
    if (articles.isEmpty()) {
      return ResponseEntity.noContent().build();
    }
    return ResponseEntity.ok(articles);
  }

  @GetMapping("/search-content")
  public ResponseEntity<List<Article>> getArticlesByContent(@RequestParam String searchTerms) {
    List<Article> articles = articleRepository.findByContentContaining(searchTerms);

    if (articles.isEmpty()) {
      return ResponseEntity.noContent().build();
    }

    return ResponseEntity.ok(articles);
  }

  @GetMapping("/search-after-date")
  public ResponseEntity<List<Article>> getArticlesAfterDate(@RequestParam String date) {
    LocalDate localDate = LocalDate.parse(date);
    LocalDateTime dateConverted = localDate.atStartOfDay();
    List<Article> articles = articleRepository.findByCreatedAtAfter(dateConverted);

    if (articles.isEmpty()) {
      return ResponseEntity.noContent().build();
    }

    return ResponseEntity.ok(articles);
  }

  @GetMapping("/search-last-five")
  public ResponseEntity<List<Article>> getLastFiveArticles() {

    Pageable pageable = PageRequest.of(0, 5);

    List<Article> articles = articleRepository.findAllByOrderByCreatedAtDesc(pageable);

    if (articles.isEmpty()) {
      return ResponseEntity.noContent().build();
    }

    return ResponseEntity.ok(articles);

  }
}
