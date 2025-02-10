package com.matthieu.myblog.dto;

import java.util.List;

import com.matthieu.myblog.model.Article;
import com.matthieu.myblog.model.Author;

public class ArticleAuthorDTO {

  private Long id;
  private String contribution;
  private List<Article> articles;
  private List<Author> authors;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getContribution() {
    return contribution;
  }

  public void setContribution(String contribution) {
    this.contribution = contribution;
  }

  public List<Article> getArticles() {
    return articles;
  }

  public void setArticles(List<Article> articles) {
    this.articles = articles;
  }

  public List<Author> getAuthors() {
    return authors;
  }

  public void setAuthors(List<Author> authors) {
    this.authors = authors;
  }
}
