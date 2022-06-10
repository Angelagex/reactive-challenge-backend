package com.sofkaU.backEndreactivechallenge.routes;

import com.sofkaU.backEndreactivechallenge.model.ReceiptDTO;
import com.sofkaU.backEndreactivechallenge.usecases.DeleteReceiptUseCase;
import com.sofkaU.backEndreactivechallenge.usecases.GetAllReceiptsUseCase;
import com.sofkaU.backEndreactivechallenge.usecases.SaveReceiptUseCase;
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
    public RouterFunction<ServerResponse> getAllReceiptsRoute(GetAllReceiptsUseCase getAllUseCase){
        return route(GET("/receipt/all"),
                request -> ServerResponse.status(HttpStatus.OK)
                        .body(BodyInserters.fromProducer(getAllUseCase.getAllReceipt(), ReceiptDTO.class))
        );
    }

    @Bean
    public RouterFunction<ServerResponse> saveReceiptRoute(SaveReceiptUseCase saveUseCase){
        return route(POST("/receipt/save").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(ReceiptDTO.class)
                        .flatMap((ReceiptDTO dto1) -> saveUseCase.saveReceipt(dto1, dto1.getOrder()))
                        .flatMap( dto -> ServerResponse.status(HttpStatus.CREATED)
                                .contentType(MediaType.APPLICATION_JSON).bodyValue(dto))
        );
    }

    @Bean
    public RouterFunction<ServerResponse> deleteReceiptRoute(DeleteReceiptUseCase deleteUseCase){
        return route(DELETE("/receipt/delete/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.status(HttpStatus.NO_CONTENT)
                        .body(BodyInserters.fromProducer(deleteUseCase.deleteById(request.pathVariable("id")), Void.class))
        );
    }
}
