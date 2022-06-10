package com.sofkaU.backEndreactivechallenge.repository;

import com.sofkaU.backEndreactivechallenge.collection.Provider;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProviderRepository extends ReactiveMongoRepository<Provider, String> {

}
