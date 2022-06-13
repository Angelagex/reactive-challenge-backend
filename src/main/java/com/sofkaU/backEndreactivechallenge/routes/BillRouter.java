package com.sofkaU.backEndreactivechallenge.routes;

import com.sofkaU.backEndreactivechallenge.model.BillDTO;
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
public class BillRouter {

    @Bean
    @RouterOperation(path = "/bill/all", beanClass = GetAllBillsUseCase.class, beanMethod = "get", operation = @Operation(operationId = "getAllBillsRoute", summary = "Get All Bills",
            responses = {@ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(schema = @Schema(implementation = BillDTO.class))),
                    @ApiResponse(responseCode = "404", description = "Not found")}))
    public RouterFunction<ServerResponse> getAllBillsRoute(GetAllBillsUseCase getAllUseCase){
        return route(GET("/bill/all"),
                request -> ServerResponse.status(HttpStatus.OK)
                        .body(BodyInserters.fromProducer(getAllUseCase.getAllBill(), BillDTO.class))
        );
    }

    @Bean
    @RouterOperation(path = "/bill/save", beanClass = SaveBillUseCase.class, beanMethod = "post", operation = @Operation(operationId = "saveBillRoute", summary = "Save A Bill",
            responses = {@ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(schema = @Schema(implementation = BillDTO.class))),
                    @ApiResponse(responseCode = "404", description = "Not found")}))
    public RouterFunction<ServerResponse> saveBillRoute(SaveBillUseCase saveUseCase){
        return route(POST("/bill/save").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(BillDTO.class)
                        .flatMap((BillDTO dto1) -> saveUseCase.saveBill(dto1))
                        .flatMap( dto -> ServerResponse.status(HttpStatus.CREATED)
                                .contentType(MediaType.APPLICATION_JSON).bodyValue(dto))
        );
    }

    @Bean
    @RouterOperation(path = "/bill/delete/{id}", beanClass = DeleteBillUseCase.class, beanMethod = "delete", operation = @Operation(operationId = "deleteBillRoute", summary = "Delete Bill By Id",
            parameters = {@Parameter(in = ParameterIn.PATH, name = "id", description = "Bill Id")},
            responses = {@ApiResponse(responseCode = "204", description = "No content"),
                    @ApiResponse(responseCode = "404", description = "Not found")}))
    public RouterFunction<ServerResponse> deleteBillRoute(DeleteBillUseCase deleteUseCase){
        return route(DELETE("/bill/delete/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.status(HttpStatus.NO_CONTENT)
                        .body(BodyInserters.fromProducer(deleteUseCase.deleteById(request.pathVariable("id")), Void.class))
        );
    }

}
