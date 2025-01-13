package com.matthieu.myblog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.matthieu.myblog.model.Article;

public interface ArticleRepository extends JpaRepository<Article, Long> {

}
