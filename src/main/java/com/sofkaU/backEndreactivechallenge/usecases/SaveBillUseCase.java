package com.sofkaU.backEndreactivechallenge.usecases;

import com.sofkaU.backEndreactivechallenge.mapper.BillMapper;
import com.sofkaU.backEndreactivechallenge.mapper.ProductMapper;
import com.sofkaU.backEndreactivechallenge.model.BillDTO;
import com.sofkaU.backEndreactivechallenge.model.ProductDTO;
import com.sofkaU.backEndreactivechallenge.repository.IBillRepository;
import com.sofkaU.backEndreactivechallenge.repository.IProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@AllArgsConstructor
public class SaveBillUseCase {

    private BillMapper billMapper;
    private ProductMapper productMapper;
    private IBillRepository billRepository;
    private IProductRepository productRepository;


    public Mono<BillDTO> saveBill(BillDTO dto, List<ProductDTO> products) {

/*      products.stream().map( item -> productMapper.convertEntityToDTO().apply(productRepository.findById(item.getId())).map( item -> productRepository.save())

        productRepository.findById(item.getId()).map( product -> ))*/

        return billRepository.save(billMapper.convertDTOToEntity().apply(dto))
                .map( bill -> billMapper.convertEntityToDTO().apply(bill));
    }
}
