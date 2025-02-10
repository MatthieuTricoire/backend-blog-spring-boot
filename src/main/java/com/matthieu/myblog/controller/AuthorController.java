package com.matthieu.myblog.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.matthieu.myblog.dto.AuthorDTO;
import com.matthieu.myblog.model.Author;
import com.matthieu.myblog.repository.ArticleRepository;
import com.matthieu.myblog.repository.AuthorRepository;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/authors")
public class AuthorController {

  private final AuthorRepository authorRepository;
  private final ArticleRepository articleRepository;

  public AuthorController(
      AuthorRepository authorRepository,
      ArticleRepository articleRepository) {
    this.authorRepository = authorRepository;
    this.articleRepository = articleRepository;
  }

  @GetMapping
  public ResponseEntity<List<AuthorDTO>> getAllAuthors() {
    List<Author> authors = authorRepository.findAll();
    if (authors.isEmpty()) {
      return ResponseEntity.noContent().build();
    }

    List<AuthorDTO> authorDTOs = authors.stream().map(this::convertToDTO).collect(Collectors.toList());
    return ResponseEntity.ok(authorDTOs);
  }

  @GetMapping("/{id}")
  public ResponseEntity<AuthorDTO> getAuthorById(@PathVariable Long id) {
    Author author = authorRepository.findById(id).orElse(null);
    if (author == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(convertToDTO(author));
  }

  @PostMapping
  public ResponseEntity<AuthorDTO> createAuthor(@RequestBody Author author) {
    author.setFirstname(author.getFirstname());
    author.setLastname(author.getLastname());
    Author savedAuthor = authorRepository.save(author);
    return ResponseEntity.status(HttpStatus.CREATED).body(convertToDTO(savedAuthor));
  }

  @PutMapping("/{id}")
  public ResponseEntity<AuthorDTO> updateAuthor(@PathVariable Long id, @RequestBody Author authorDetails) {

    Author author = authorRepository.findById(id).orElse(null);
    if (author == null) {
      return ResponseEntity.notFound().build();
    }

    author.setFirstname(authorDetails.getFirstname());
    author.setLastname(authorDetails.getLastname());

    Author updatedAuthor = authorRepository.save(author);
    return ResponseEntity.ok(convertToDTO(updatedAuthor));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteAuthor(@PathVariable Long id) {

    Author author = authorRepository.findById(id).orElse(null);
    if (author == null) {
      return ResponseEntity.notFound().build();
    }

    authorRepository.delete(author);
    return ResponseEntity.noContent().build();
  }

  private AuthorDTO convertToDTO(Author author) {
    AuthorDTO authorDTO = new AuthorDTO();
    authorDTO.setId(author.getId());
    authorDTO.setFirstname(author.getFirstname());
    authorDTO.setLastname(author.getLastname());
    return authorDTO;
  }
}
