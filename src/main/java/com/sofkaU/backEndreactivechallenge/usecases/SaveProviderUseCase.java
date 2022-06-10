package com.sofkaU.backEndreactivechallenge.usecases;

import com.sofkaU.backEndreactivechallenge.mapper.ProviderMapper;
import com.sofkaU.backEndreactivechallenge.model.ProviderDTO;
import com.sofkaU.backEndreactivechallenge.repository.IProviderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;


@Service
@AllArgsConstructor
public class SaveProviderUseCase {


    private ProviderMapper providerMapper;
    private IProviderRepository providerRepository;

/*    boolean validate(ProviderDTO dto) {
        return dto.getProviderId().equals(dto.getProviderId().toString()) && dto.getName().equals(dto.getName().toString())
                ? true : false;
    }*/


    public Mono<ProviderDTO> saveProvider( ProviderDTO dto) {
        return providerRepository.save(providerMapper.convertDTOToEntity().apply(dto))
                .map( provider -> providerMapper.convertEntityToDTO().apply(provider));
    }


}
