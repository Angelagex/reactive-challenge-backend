package com.sofkaU.backEndreactivechallenge.usecases;

import com.sofkaU.backEndreactivechallenge.repository.IReceiptRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class DeleteReceiptUseCase {

    private IReceiptRepository receiptRepository;


    public Mono<Void> deleteById(String id) {
        return receiptRepository.findById(id).flatMap( result -> receiptRepository.deleteById(result.getId()));
    }
}
