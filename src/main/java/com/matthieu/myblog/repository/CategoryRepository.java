package com.matthieu.myblog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.matthieu.myblog.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
