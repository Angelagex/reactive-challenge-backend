package com.sofkaU.backEndreactivechallenge;

import com.sofkaU.backEndreactivechallenge.model.ProviderDTO;
import com.sofkaU.backEndreactivechallenge.repository.IProviderRepository;
import com.sofkaU.backEndreactivechallenge.usecases.DeleteProviderUseCase;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest
public class DeleteProviderUseCaseTest {

    @MockBean
    private DeleteProviderUseCase useCase;

    @Mock
    IProviderRepository repository;

    @Test
    void deleteProvider() {
        ProviderDTO provider = new ProviderDTO();
        provider.setId("123");
        provider.setProviderId("CC123");
        provider.setName("Marcos");

        StepVerifier.create(Mono.just(Mockito.when(useCase.deleteById(provider.getId())).thenReturn(Mono.empty())))
                .expectNextCount(1).verifyComplete();
    }
}
