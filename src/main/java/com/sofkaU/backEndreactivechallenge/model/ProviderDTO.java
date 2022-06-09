package com.sofkaU.backEndreactivechallenge.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import javax.validation.constraints.NotBlank;

@Data
@RequiredArgsConstructor
public class ProviderDTO {

    private String id;

    @NotBlank(message = "ProviderId is required")
    private String providerId;

    @NotBlank(message = "Name is required")
    private String name;

}
