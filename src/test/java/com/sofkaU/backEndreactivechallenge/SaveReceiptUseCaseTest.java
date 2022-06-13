package com.sofkaU.backEndreactivechallenge;

import com.sofkaU.backEndreactivechallenge.model.ProductDTO;
import com.sofkaU.backEndreactivechallenge.model.ReceiptDTO;
import com.sofkaU.backEndreactivechallenge.repository.IReceiptRepository;
import com.sofkaU.backEndreactivechallenge.usecases.SaveReceiptUseCase;
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
public class SaveReceiptUseCaseTest {

    @MockBean
    private SaveReceiptUseCase useCase;

    @Mock
    IReceiptRepository repository;

    @Test
    void saveReceipt() {
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

        ReceiptDTO receipt = new ReceiptDTO();
        receipt.setId("456");
        receipt.setDate(localDate);
        receipt.setProviderId("CC123");
        receipt.setProviderName("Julian");
        receipt.setOrder(List.of(product));

        StepVerifier.create(Mono.just(Mockito.when(useCase.saveReceipt(receipt)).thenReturn(Mono.just(receipt))))
                .expectNextCount(1).verifyComplete();
    }
}
