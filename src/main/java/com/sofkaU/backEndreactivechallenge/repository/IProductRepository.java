package com.sofkaU.backEndreactivechallenge.repository;

import com.sofkaU.backEndreactivechallenge.collection.Product;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface IProductRepository extends ReactiveMongoRepository<Product, String> {
}
