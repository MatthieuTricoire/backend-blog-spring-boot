package org.wcs.myblog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.wcs.myblog.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
