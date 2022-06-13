package com.sofkaU.backEndreactivechallenge;

import com.sofkaU.backEndreactivechallenge.collection.Provider;
import com.sofkaU.backEndreactivechallenge.mapper.ProviderMapper;
import com.sofkaU.backEndreactivechallenge.repository.IProviderRepository;
import com.sofkaU.backEndreactivechallenge.usecases.GetAllProvidersUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@SpringBootTest
public class GetAllProvidersUseCaseTest {

    private GetAllProvidersUseCase useCase;

    @Autowired
    ProviderMapper mapper;

    @Mock
    IProviderRepository repository;

    @BeforeEach
    void setUp() { useCase = new GetAllProvidersUseCase(mapper, repository);}

    @Test
    void getProviders() {
        Provider provider = new Provider();
        provider.setId("123");
        provider.setProviderId("CC123");
        provider.setName("Marcos");

        Mockito.when(repository.findAll()).thenReturn(Flux.just(provider));

        StepVerifier.create(useCase.getAllProvider()).expectNextCount(1).verifyComplete();
        Mockito.verify(repository).findAll();

    }}



