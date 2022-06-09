package com.sofkaU.backEndreactivechallenge.collection;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;

@Data
@Document(collection = "providers")
public class Provider {

    @Id
    private String id;

    @NotBlank(message = "ProviderId is required")
    private String providerId;

    @NotBlank(message = "Name is required")
    private String name;

}
