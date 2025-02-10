package com.matthieu.myblog.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.matthieu.myblog.model.Article;

public interface ArticleRepository extends JpaRepository<Article, Long> {

  List<Article> findByTitle(String title);

  List<Article> findByContentContaining(String content);

  List<Article> findByCreatedAtAfter(LocalDateTime date);

  List<Article> findTop5ByCreatedAtByDesc();

}
