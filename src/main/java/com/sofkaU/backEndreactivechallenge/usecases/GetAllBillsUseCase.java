package com.sofkaU.backEndreactivechallenge.usecases;

import com.sofkaU.backEndreactivechallenge.mapper.BillMapper;
import com.sofkaU.backEndreactivechallenge.model.BillDTO;
import com.sofkaU.backEndreactivechallenge.repository.IBillRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;


@Service
@AllArgsConstructor
public class GetAllBillsUseCase {

    private BillMapper billMapper;
    private IBillRepository billRepository;



    public Flux<BillDTO> getAllBill() {
        return billRepository.findAll().map(bill -> billMapper.convertEntityToDTO().apply(bill));
    }
}
