package com.sofkaU.backEndreactivechallenge.model;

import com.sofkaU.backEndreactivechallenge.collection.Product;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Data
@RequiredArgsConstructor
public class BillDTO {

    private String id;

    @NotBlank(message = "ClientName is required")
    private String clientName;

    @NotBlank(message = "VendorName is required")
    private String vendorName;

    @NotNull(message = "Date cannot be null")
    LocalDate date;

    List<Product> order;
}
