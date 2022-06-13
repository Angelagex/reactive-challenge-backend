package com.sofkaU.backEndreactivechallenge;

import com.sofkaU.backEndreactivechallenge.mapper.ProviderMapper;
import com.sofkaU.backEndreactivechallenge.model.ProviderDTO;
import com.sofkaU.backEndreactivechallenge.repository.IProviderRepository;
import com.sofkaU.backEndreactivechallenge.usecases.GetAllProvidersUseCase;
import com.sofkaU.backEndreactivechallenge.usecases.SaveProviderUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest
public class SaveProviderUseCaseTest {

    @MockBean
    private SaveProviderUseCase useCase;

    @Mock
    IProviderRepository repository;

    @Test
    void saveProvider() {
        ProviderDTO provider = new ProviderDTO();
        provider.setId("123");
        provider.setProviderId("CC123");
        provider.setName("Marcos");

        StepVerifier.create(Mono.just(Mockito.when(useCase.saveProvider(provider)).thenReturn(Mono.just(provider))))
                .expectNextCount(1).verifyComplete();
    }
}
