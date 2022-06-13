package com.sofkaU.backEndreactivechallenge;

import com.sofkaU.backEndreactivechallenge.collection.Product;
import com.sofkaU.backEndreactivechallenge.mapper.ProductMapper;
import com.sofkaU.backEndreactivechallenge.repository.IProductRepository;
import com.sofkaU.backEndreactivechallenge.usecases.GetAllProductsUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@SpringBootTest
public class GetAllProductsUseCaseTest {

    private GetAllProductsUseCase useCase;

    @Autowired
    ProductMapper mapper;

    @Mock
    IProductRepository repository;

    @BeforeEach
    void setUp() { useCase = new GetAllProductsUseCase(mapper, repository);}

    @Test
    void getProducts() {
        Product product = new Product();
        product.setId("123");
        product.setDescription("Product Description");
        product.setName("Screw");
        product.setAmount(10);
        product.setMinAmount(5);
        product.setMaxAmount(100);
        product.setPrice("10000");
        product.setProvider("Marcos");
        Mockito.when(repository.findAll()).thenReturn(Flux.just(product));

        StepVerifier.create(useCase.getAllProduct()).expectNextCount(1).verifyComplete();
        Mockito.verify(repository).findAll();

    }
}
