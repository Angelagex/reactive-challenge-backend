package com.sofkaU.backEndreactivechallenge;

import com.sofkaU.backEndreactivechallenge.collection.Bill;
import com.sofkaU.backEndreactivechallenge.mapper.BillMapper;
import com.sofkaU.backEndreactivechallenge.model.ProductDTO;
import com.sofkaU.backEndreactivechallenge.repository.IBillRepository;
import com.sofkaU.backEndreactivechallenge.usecases.GetAllBillsUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@SpringBootTest
public class GetAllBillsUseCaseTest {

    private GetAllBillsUseCase useCase;

    @Autowired
    BillMapper mapper;

    @Mock
    IBillRepository repository;

    @BeforeEach
    void setUp() { useCase = new GetAllBillsUseCase(mapper, repository);}

    @Test
    void getProducts() {
        ProductDTO product = new ProductDTO();
        product.setId("123");
        product.setDescription("Product Description");
        product.setName("Screw");
        product.setAmount(10);
        product.setMinAmount(5);
        product.setMaxAmount(100);
        product.setPrice("10000");
        product.setProvider("Marcos");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
        String date = "2022-02-22";
        //convert String to LocalDate
        LocalDate localDate = LocalDate.parse(date, formatter);

        Bill receipt = new Bill();
        receipt.setId("456");
        receipt.setDate(localDate);
        receipt.setClientName("Santiago");
        receipt.setVendorName("Sher");
        receipt.setOrder(List.of(product));


        Mockito.when(repository.findAll()).thenReturn(Flux.just(receipt));

        StepVerifier.create(useCase.getAllBill()).expectNextCount(1).verifyComplete();
        Mockito.verify(repository).findAll();

    }
}
