package com.matthieu.myblog.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.matthieu.myblog.dto.ImageDTO;
import com.matthieu.myblog.model.Article;
import com.matthieu.myblog.model.Image;
import com.matthieu.myblog.repository.ArticleRepository;
import com.matthieu.myblog.repository.ImageRepository;

@RestController
@RequestMapping("/images")
public class ImageController {

  private final ImageRepository imageRepository;
  private final ArticleRepository articleRepository;

  public ImageController(ImageRepository imageRepository, ArticleRepository articleRepository) {
    this.imageRepository = imageRepository;
    this.articleRepository = articleRepository;
  }

  @GetMapping
  public ResponseEntity<List<ImageDTO>> getAllImages() {
    List<Image> images = imageRepository.findAll();

    if (images.isEmpty()) {
      return ResponseEntity.noContent().build();
    }

    List<ImageDTO> imageDTOs = images.stream()
        .map(this::convertToDTO)
        .collect(Collectors.toList());

    return ResponseEntity.ok(imageDTOs);
  }

  @GetMapping
  public ResponseEntity<ImageDTO> getImageById(@PathVariable Long id) {
    Image image = imageRepository.findById(id).orElse(null);

    if (image == null) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok(convertToDTO(image));
  }

  @PostMapping
  public ResponseEntity<ImageDTO> createImage(@RequestBody Image image) {
    Image savedImage = imageRepository.save(image);

    return ResponseEntity.status(201).body(convertToDTO(savedImage));
  }

  @PutMapping
  public ResponseEntity<ImageDTO> updateImage(@PathVariable Long id, @RequestBody Image imageDetails) {
    Image image = imageRepository.findById(id).orElse(null);

    if (image == null) {
      return ResponseEntity.notFound().build();
    }

    image.setUrl(image.getUrl());

    Image updatedImage = imageRepository.save(image);

    return ResponseEntity.ok(convertToDTO(updatedImage));
  }

  @DeleteMapping
  public ResponseEntity<Void> deleteImage(@PathVariable Long id) {
    Image image = imageRepository.findById(id).orElse(null);

    if (image == null) {
      return ResponseEntity.notFound().build();
    }

    imageRepository.delete(image);

    return ResponseEntity.noContent().build();

  }

  private ImageDTO convertToDTO(Image image) {
    ImageDTO imageDTO = new ImageDTO();

    image.setId(image.getId());
    image.setUrl(image.getUrl());

    if (image.getArticles() != null) {
      imageDTO.setArticleIds(image.getArticles().stream().map(Article::getId).collect(Collectors.toList()));
    }

    return imageDTO;
  }
}
