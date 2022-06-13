package com.sofkaU.backEndreactivechallenge;

import com.sofkaU.backEndreactivechallenge.model.BillDTO;
import com.sofkaU.backEndreactivechallenge.model.ProductDTO;
import com.sofkaU.backEndreactivechallenge.repository.IBillRepository;
import com.sofkaU.backEndreactivechallenge.usecases.DeleteBillUseCase;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@SpringBootTest
public class DeleteBillUseCaseTest {

    @MockBean
    private DeleteBillUseCase useCase;

    @Mock
    IBillRepository repository;

    @Test
    void saveBill() {
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

        BillDTO bill = new BillDTO();
        bill.setId("456");
        bill.setDate(localDate);
        bill.setClientName("Santiago");
        bill.setVendorName("Sher");
        bill.setOrder(List.of(product));

        StepVerifier.create(Mono.just(Mockito.when(useCase.deleteById(bill.getId())).thenReturn(Mono.empty())))
                .expectNextCount(1).verifyComplete();
    }
}
