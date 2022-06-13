package com.sofkaU.backEndreactivechallenge.usecases;

import com.sofkaU.backEndreactivechallenge.mapper.ReceiptMapper;
import com.sofkaU.backEndreactivechallenge.model.ReceiptDTO;
import com.sofkaU.backEndreactivechallenge.repository.IReceiptRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class SaveReceiptUseCase {

    private ReceiptMapper receiptMapper;
    private IReceiptRepository receiptRepository;


    public Mono<ReceiptDTO> saveReceipt(ReceiptDTO dto) {


        return receiptRepository.save(receiptMapper.convertDTOToEntity().apply(dto))
                .map( receipt -> receiptMapper.convertEntityToDTO().apply(receipt));
    }
}
