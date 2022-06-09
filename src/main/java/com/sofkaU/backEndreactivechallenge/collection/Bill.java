package com.sofkaU.backEndreactivechallenge.collection;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
@Document(collection = "bills")
public class Bill {

    @Id
    private String id;

    @NotBlank(message = "ClientName is required")
    private String clientName;

    @NotBlank(message = "VendorName is required")
    private String vendorName;

    @NotNull(message = "Date cannot be null")
    Date date;

    List<Product> order;

}
