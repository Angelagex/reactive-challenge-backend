package com.sofkaU.backEndreactivechallenge.usecases;

import com.sofkaU.backEndreactivechallenge.repository.IReceiptRepository;
import reactor.core.publisher.Mono;

public class DeleteReceiptUseCase {

    private IReceiptRepository receiptRepository;


    public Mono<Void> deleteById(String id) {
        return receiptRepository.findById(id).flatMap( result -> receiptRepository.deleteById(result.getId()));
    }
}
