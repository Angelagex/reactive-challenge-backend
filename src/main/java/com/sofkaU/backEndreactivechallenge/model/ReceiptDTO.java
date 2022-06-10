package com.sofkaU.backEndreactivechallenge.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Data
@RequiredArgsConstructor
public class ReceiptDTO {

    private String id;

    @NotBlank(message = "ProviderName is required")
    private String providerName;

    @NotBlank(message = "ProviderId is required")
    private String providerId;

    @NotNull(message = "Date cannot be null")
    LocalDate date;

    List<ProductDTO> order;

}

