package br.edu.utfpr.pb.pw44s.server.service;

import br.edu.utfpr.pb.pw44s.server.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IProductService {
    
    List<Product> findAll();
    Page<Product> findAll(Pageable pageable);
    Product findById(Long id);
    Product save(Product Product);
    void deleteById(Long id);
    boolean existsById(Long id);
    long count();
}
