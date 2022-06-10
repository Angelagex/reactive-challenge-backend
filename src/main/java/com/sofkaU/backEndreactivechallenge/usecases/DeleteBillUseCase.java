package com.sofkaU.backEndreactivechallenge.usecases;

import com.sofkaU.backEndreactivechallenge.repository.IBillRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class DeleteBillUseCase {

    private IBillRepository billRepository;


    public Mono<Void> deleteById(String id) {
        return billRepository.findById(id).flatMap( result -> billRepository.deleteById(result.getId()));
    }
}
