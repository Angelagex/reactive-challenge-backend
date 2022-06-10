package com.sofkaU.backEndreactivechallenge.usecases;

import com.sofkaU.backEndreactivechallenge.mapper.ProductMapper;
import com.sofkaU.backEndreactivechallenge.model.ProductDTO;
import com.sofkaU.backEndreactivechallenge.repository.IProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class UpdateProductUseCase {

    private ProductMapper productMapper;
    private IProductRepository productRepository;


    public Mono<ProductDTO> saveProduct( ProductDTO dto) {
        return productRepository.findById(dto.getId()).flatMap( product ->
                productRepository.save(productMapper.convertDTOToEntity().apply(dto))
                .map(productRes -> productMapper.convertEntityToDTO().apply(productRes))
    ).switchIfEmpty(Mono.just(new ProductDTO()));
    }
}
