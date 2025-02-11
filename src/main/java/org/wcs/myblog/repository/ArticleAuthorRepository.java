package org.wcs.myblog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.wcs.myblog.model.ArticleAuthor;

public interface ArticleAuthorRepository extends JpaRepository<ArticleAuthor, Long> {
}
