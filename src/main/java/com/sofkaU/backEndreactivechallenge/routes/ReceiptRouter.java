package com.sofkaU.backEndreactivechallenge.routes;

import com.sofkaU.backEndreactivechallenge.model.ReceiptDTO;
import com.sofkaU.backEndreactivechallenge.usecases.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springdoc.core.annotations.RouterOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;


@Configuration
public class ReceiptRouter {

    @Bean
    @RouterOperation(path = "/receipt/all", beanClass = GetAllReceiptsUseCase.class, beanMethod = "get", operation = @Operation(operationId = "getAllReceiptsRoute", summary = "Get All Receipts",
            responses = {@ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(schema = @Schema(implementation = ReceiptDTO.class))),
                    @ApiResponse(responseCode = "404", description = "Not found")}))
    public RouterFunction<ServerResponse> getAllReceiptsRoute(GetAllReceiptsUseCase getAllUseCase){
        return route(GET("/receipt/all"),
                request -> ServerResponse.status(HttpStatus.OK)
                        .body(BodyInserters.fromProducer(getAllUseCase.getAllReceipt(), ReceiptDTO.class))
        );
    }

    @Bean
    @RouterOperation(path = "/receipt/save", beanClass = SaveReceiptUseCase.class, beanMethod = "post", operation = @Operation(operationId = "saveReceiptRoute", summary = "Save A Receipt",
            responses = {@ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(schema = @Schema(implementation = ReceiptDTO.class))),
                    @ApiResponse(responseCode = "404", description = "Not found")}))
    public RouterFunction<ServerResponse> saveReceiptRoute(SaveReceiptUseCase saveUseCase){
        return route(POST("/receipt/save").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(ReceiptDTO.class)
                        .flatMap((ReceiptDTO dto1) -> saveUseCase.saveReceipt(dto1, dto1.getOrder()))
                        .flatMap( dto -> ServerResponse.status(HttpStatus.CREATED)
                                .contentType(MediaType.APPLICATION_JSON).bodyValue(dto))
        );
    }

    @Bean
    @RouterOperation(path = "/receipt/delete/{id}", beanClass = DeleteReceiptUseCase.class, beanMethod = "delete", operation = @Operation(operationId = "deleteReceiptRoute", summary = "Delete Receipt By Id",
            parameters = {@Parameter(in = ParameterIn.PATH, name = "id", description = "Receipt Id")},
            responses = {@ApiResponse(responseCode = "204", description = "No content"),
                    @ApiResponse(responseCode = "404", description = "Not found")}))
    public RouterFunction<ServerResponse> deleteReceiptRoute(DeleteReceiptUseCase deleteUseCase){
        return route(DELETE("/receipt/delete/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.status(HttpStatus.NO_CONTENT)
                        .body(BodyInserters.fromProducer(deleteUseCase.deleteById(request.pathVariable("id")), Void.class))
        );
    }
}
