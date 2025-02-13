package com.matthieu.myblog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.matthieu.myblog.model.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
