package br.edu.utfpr.pb.pw44s.server.controller;

import br.edu.utfpr.pb.pw44s.server.dto.CategoryDTO;
import br.edu.utfpr.pb.pw44s.server.mapper.CategoryMapper;
import br.edu.utfpr.pb.pw44s.server.model.Category;
import br.edu.utfpr.pb.pw44s.server.service.ICategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("categories")
public class CategoryController {

    private final ICategoryService categoryService;
    private final CategoryMapper categoryMapper;

    public CategoryController(ICategoryService categoryService,
                              CategoryMapper categoryMapper) {
        this.categoryService = categoryService;
        this.categoryMapper = categoryMapper;
    }

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> findAll() {
        return ResponseEntity.ok(
                this.categoryService.findAll()
                        .stream()
                        .map(categoryMapper::toDto)
                        .collect(Collectors.toList())
        );
    }

    @PostMapping
    public ResponseEntity<CategoryDTO> save(
            @RequestBody @Valid CategoryDTO categoryDTO) {
        Category category = this.categoryService.save(
                        categoryMapper.toEntity(categoryDTO)
        );
        return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(this.categoryMapper.toDto(category));
    }

}
