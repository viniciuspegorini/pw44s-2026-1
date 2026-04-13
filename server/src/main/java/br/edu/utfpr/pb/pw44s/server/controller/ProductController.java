package br.edu.utfpr.pb.pw44s.server.controller;

import br.edu.utfpr.pb.pw44s.server.dto.ProductDTO;
import br.edu.utfpr.pb.pw44s.server.mapper.ProductMapper;
import br.edu.utfpr.pb.pw44s.server.model.Product;
import br.edu.utfpr.pb.pw44s.server.service.IProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("products")
public class ProductController {

    private final IProductService ProductService;
    private final ProductMapper ProductMapper;

    public ProductController(IProductService ProductService,
                             ProductMapper ProductMapper) {
        this.ProductService = ProductService;
        this.ProductMapper = ProductMapper;
    }

    // GET -> http://localhost:8080/products
    @GetMapping
    public ResponseEntity<List<ProductDTO>> findAll() {
        return ResponseEntity.ok(
                this.ProductService.findAll()
                        .stream()
                        .map(ProductMapper::toDto)
                        .collect(Collectors.toList())
        );
    }

    // POST -> http://localhost:8080/products
    @PostMapping
    public ResponseEntity<ProductDTO> save(
            @RequestBody @Valid ProductDTO ProductDTO) {
        Product Product = this.ProductService.save(
                        ProductMapper.toEntity(ProductDTO)
        );
        return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(this.ProductMapper.toDto(Product));
    }

    // PUT -> http://localhost:8080/products
    @PutMapping
    public ResponseEntity<ProductDTO> update(
            @RequestBody @Valid ProductDTO ProductDTO) {
        Product Product = this.ProductService.save(
                ProductMapper.toEntity(ProductDTO)
        );
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.ProductMapper.toDto(Product));
    }

    // GET -> http://localhost:8080/products/:id onde :id é o código da categoria
    @GetMapping("{id}")
    public ResponseEntity<ProductDTO> findById(@PathVariable Long id) {
        Product Product = this.ProductService.findById(id);
        if (Product != null) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    this.ProductMapper.toDto(Product));
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        Product Product = this.ProductService.findById(id);
        if (Product != null) {
            this.ProductService.deleteById(id);
        }
    }

}
