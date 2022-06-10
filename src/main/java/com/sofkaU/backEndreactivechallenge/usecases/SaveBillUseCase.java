package com.sofkaU.backEndreactivechallenge.usecases;

import com.sofkaU.backEndreactivechallenge.mapper.BillMapper;
import com.sofkaU.backEndreactivechallenge.model.BillDTO;
import com.sofkaU.backEndreactivechallenge.repository.IBillRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class SaveBillUseCase {

    private BillMapper billMapper;
    private IBillRepository billRepository;


    public Mono<BillDTO> saveBill(BillDTO dto) {
        return billRepository.save(billMapper.convertDTOToEntity().apply(dto))
                .map( bill -> billMapper.convertEntityToDTO().apply(bill));
    }
}
