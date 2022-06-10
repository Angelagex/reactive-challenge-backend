package com.sofkaU.backEndreactivechallenge.repository;

import com.sofkaU.backEndreactivechallenge.collection.Bill;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBillInterface extends ReactiveMongoRepository<Bill, String> {
}
