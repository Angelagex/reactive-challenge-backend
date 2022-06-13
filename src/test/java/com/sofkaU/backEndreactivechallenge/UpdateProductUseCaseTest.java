package com.sofkaU.backEndreactivechallenge;

import com.sofkaU.backEndreactivechallenge.model.ProductDTO;
import com.sofkaU.backEndreactivechallenge.repository.IProductRepository;
import com.sofkaU.backEndreactivechallenge.usecases.UpdateProductUseCase;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest
public class UpdateProductUseCaseTest {

    @MockBean
    private UpdateProductUseCase useCase;

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

        ProductDTO product2 = new ProductDTO();
        product.setId("123");
        product.setDescription("Product Description");
        product.setName("Hammer");
        product.setAmount(10);
        product.setMinAmount(5);
        product.setMaxAmount(100);
        product.setPrice("10000");
        product.setProvider("Marcos");

        StepVerifier.create(Mono.just(Mockito.when(useCase.saveProduct(product)).then(answer -> useCase.saveProduct(product2)).thenReturn(Mono.just(product2)))).expectNextCount(1).verifyComplete();
    }
}
