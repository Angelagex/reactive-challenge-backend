package com.sofkaU.backEndreactivechallenge.mapper;

import com.sofkaU.backEndreactivechallenge.collection.Provider;
import com.sofkaU.backEndreactivechallenge.model.ProviderDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class ProviderMapper {

    private ModelMapper modelMapper;

    public ProviderMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Function<Provider, ProviderDTO> convertEntityToDTO(){
        return provider ->
                modelMapper.map(provider, ProviderDTO.class);
    }

    public Function<ProviderDTO, Provider> convertDTOToEntity (){
        return providerDTO ->
                modelMapper.map(providerDTO,Provider.class);
    }
}
