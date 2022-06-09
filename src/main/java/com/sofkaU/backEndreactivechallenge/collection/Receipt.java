package com.sofkaU.backEndreactivechallenge.collection;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
@Document(collection = "receipts")
public class Receipt {


    @Id
    private String id;

    @NotBlank(message = "ProviderName is required")
    private String providerName;

    @NotBlank(message = "ProviderId is required")
    private String providerId;

    @NotNull(message = "Date cannot be null")
    Date date;

    List<Product> order;

}
