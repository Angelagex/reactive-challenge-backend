package com.sofkaU.backEndreactivechallenge.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Data
@RequiredArgsConstructor
public class ProductDTO {

    private String id;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Description is required")
    private String description;

    @NotBlank(message = "Price is required")
    private String price;

    @NotBlank(message = "Provider is required")
    private String provider;

    @NotNull(message = "MinAmmount cannot be null")
    private Integer minAmount;

    @NotNull(message = "MaxAmmount cannot be null")
    private Integer maxAmount;

    @NotNull(message = "Ammount cannot be null")
    private Integer amount;
}
