package com.sofkaU.backEndreactivechallenge.usecases;

import com.sofkaU.backEndreactivechallenge.mapper.ReceiptMapper;
import com.sofkaU.backEndreactivechallenge.model.ReceiptDTO;
import com.sofkaU.backEndreactivechallenge.repository.IReceiptRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;


@Service
@AllArgsConstructor
public class GetAllReceiptsUseCase {

    private ReceiptMapper receiptMapper;
    private IReceiptRepository receiptRepository;



    public Flux<ReceiptDTO> getAllReceipt() {
        return receiptRepository.findAll().map(receipt -> receiptMapper.convertEntityToDTO().apply(receipt));
    }
}
