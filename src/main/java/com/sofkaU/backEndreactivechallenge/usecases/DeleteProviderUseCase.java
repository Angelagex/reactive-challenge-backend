package com.sofkaU.backEndreactivechallenge.usecases;
import com.sofkaU.backEndreactivechallenge.repository.IProviderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class DeleteProviderUseCase {

    private IProviderRepository providerRepository;


    public Mono<Void> deleteById(String id) {
        return providerRepository.findById(id).flatMap( result -> providerRepository.deleteById(result.getId()));
    }
}
