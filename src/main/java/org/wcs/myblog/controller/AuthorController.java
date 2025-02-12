package org.wcs.myblog.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wcs.myblog.dto.AuthorDTO;
import org.wcs.myblog.model.Author;
import org.wcs.myblog.repository.AuthorRepository;
import org.wcs.myblog.service.AuthorService;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {
    private final AuthorRepository authorRepository;
    private final AuthorService authorService;

    public AuthorController(AuthorRepository authorRepository, AuthorService authorService) {
        this.authorRepository = authorRepository;
        this.authorService = authorService;
    }

    @GetMapping
    public ResponseEntity<List<AuthorDTO>> getAllAuthors() {
        List<AuthorDTO> authors = authorService.getAllAuthors();
        if (authors.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(authors);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorDTO> getAuthorById(@PathVariable Long id) {
        AuthorDTO author = authorService.getAuthorById(id);
        if (author == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(author);
    }

    @PostMapping
    public ResponseEntity<AuthorDTO> createAuthor(@RequestBody Author author) {
        AuthorDTO savedAuthor = authorService.createAuthor(author);
        return ResponseEntity.status(201).body(savedAuthor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuthorDTO> updateAuthor(@PathVariable Long id, @RequestBody Author authorDetails) {
        AuthorDTO updatedAuthor = authorService.updateAuthor(id, authorDetails);
        return ResponseEntity.ok(updatedAuthor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Long id) {
        boolean deleted = authorService.deleteAuthor(id);
        if (!deleted) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

}
