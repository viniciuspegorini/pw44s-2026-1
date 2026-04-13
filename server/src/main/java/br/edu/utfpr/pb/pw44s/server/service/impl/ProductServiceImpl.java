package br.edu.utfpr.pb.pw44s.server.service.impl;

import br.edu.utfpr.pb.pw44s.server.model.Product;
import br.edu.utfpr.pb.pw44s.server.repository.ProductRepository;
import br.edu.utfpr.pb.pw44s.server.service.IProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductServiceImpl
                        implements IProductService {

    private final ProductRepository ProductRepository;

    public ProductServiceImpl(ProductRepository ProductRepository) {
        this.ProductRepository = ProductRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> findAll() {
        return this.ProductRepository.findAll();
    }

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Product findById(Long id) {
        return this.ProductRepository.findById(id).orElse(null);
    }

    @Override
    public Product save(Product Product) {
        return this.ProductRepository.save(Product);
    }

    @Override
    public void deleteById(Long id) {
        this.ProductRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return false;
    }

    @Override
    public long count() {
        return 0;
    }
}
