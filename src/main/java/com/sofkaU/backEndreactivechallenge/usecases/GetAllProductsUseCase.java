package com.sofkaU.backEndreactivechallenge.usecases;

import com.sofkaU.backEndreactivechallenge.mapper.ProductMapper;
import com.sofkaU.backEndreactivechallenge.model.ProductDTO;
import com.sofkaU.backEndreactivechallenge.repository.IProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;


@Service
@AllArgsConstructor
public class GetAllProductsUseCase {

    private ProductMapper productMapper;
    private IProductRepository productRepository;



    public Flux<ProductDTO> getAllProduct() {
        return productRepository.findAll().map(product -> productMapper.convertEntityToDTO().apply(product));
    }
}
