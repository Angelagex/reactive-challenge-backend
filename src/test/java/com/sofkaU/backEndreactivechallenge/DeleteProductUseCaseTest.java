package com.sofkaU.backEndreactivechallenge;

import com.sofkaU.backEndreactivechallenge.model.ProductDTO;
import com.sofkaU.backEndreactivechallenge.repository.IProductRepository;
import com.sofkaU.backEndreactivechallenge.usecases.DeleteProductUseCase;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest
public class DeleteProductUseCaseTest {

    @MockBean
    private DeleteProductUseCase useCase;

    @Mock
    IProductRepository repository;

    @Test
    void saveProvider() {
        ProductDTO product = new ProductDTO();
        product.setId("123");
        product.setDescription("Product Description");
        product.setName("Screw");
        product.setAmount(10);
        product.setMinAmount(5);
        product.setMaxAmount(100);
        product.setPrice("10000");
        product.setProvider("Marcos");

        StepVerifier.create(Mono.just(Mockito.when(useCase.deleteById(product.getId())).thenReturn(Mono.empty())))
                .expectNextCount(1).verifyComplete();
    }
}
