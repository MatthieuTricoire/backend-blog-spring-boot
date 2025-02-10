package com.matthieu.myblog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.matthieu.myblog.model.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
