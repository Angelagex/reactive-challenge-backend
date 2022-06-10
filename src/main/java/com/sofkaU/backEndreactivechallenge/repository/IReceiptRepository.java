package com.sofkaU.backEndreactivechallenge.repository;

import com.sofkaU.backEndreactivechallenge.collection.Receipt;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IReceiptRepository extends ReactiveMongoRepository<Receipt, String> {
}
