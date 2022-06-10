package com.sofkaU.backEndreactivechallenge.usecases;

import com.sofkaU.backEndreactivechallenge.mapper.ProviderMapper;
import com.sofkaU.backEndreactivechallenge.model.ProviderDTO;
import com.sofkaU.backEndreactivechallenge.repository.IProviderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@AllArgsConstructor
public class GetAllProvidersUseCase {

    private ProviderMapper providerMapper;
    private IProviderRepository providerRepository;



    public Flux<ProviderDTO> getAllProvider() {
        return providerRepository.findAll().map(provider -> providerMapper.convertEntityToDTO().apply(provider));
    }
}
