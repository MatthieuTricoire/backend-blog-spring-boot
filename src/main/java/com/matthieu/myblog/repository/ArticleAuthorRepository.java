package com.matthieu.myblog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.matthieu.myblog.model.ArticleAuthor;

public interface ArticleAuthorRepository extends JpaRepository<ArticleAuthor, Long> {

}
