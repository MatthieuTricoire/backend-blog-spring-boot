package com.matthieu.myblog.model;

import jakarta.persistence.*;

import java.time.LocalTime;

@Entity
public class Article {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, length = 50)
  private String title;

  @Column(columnDefinition = "TEXT")
  private String content;

  @Column(nullable = false, updatable = false)
  private LocalTime createdAt;

  @Column(nullable = false)
  private LocalTime updatedAt;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public LocalTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(LocalTime createdAt) {
    this.createdAt = createdAt;
  }

  public LocalTime getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(LocalTime updatedAt) {
    this.updatedAt = updatedAt;
  }

}
