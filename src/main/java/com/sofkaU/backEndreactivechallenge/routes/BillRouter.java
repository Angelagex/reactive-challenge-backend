package com.sofkaU.backEndreactivechallenge.routes;
import com.sofkaU.backEndreactivechallenge.model.BillDTO;
import com.sofkaU.backEndreactivechallenge.usecases.DeleteBillUseCase;
import com.sofkaU.backEndreactivechallenge.usecases.GetAllBillsUseCase;
import com.sofkaU.backEndreactivechallenge.usecases.SaveBillUseCase;
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
    public RouterFunction<ServerResponse> getAllBillsRoute(GetAllBillsUseCase getAllUseCase){
        return route(GET("/bill/all"),
                request -> ServerResponse.status(HttpStatus.OK)
                        .body(BodyInserters.fromProducer(getAllUseCase.getAllBill(), BillDTO.class))
        );
    }

    @Bean
    public RouterFunction<ServerResponse> saveBillRoute(SaveBillUseCase saveUseCase){
        return route(POST("/bill/save").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(BillDTO.class)
                        .flatMap((BillDTO dto1) -> saveUseCase.saveBill(dto1, dto1.getOrder()))
                        .flatMap( dto -> ServerResponse.status(HttpStatus.CREATED)
                                .contentType(MediaType.APPLICATION_JSON).bodyValue(dto))
        );
    }

    @Bean
    public RouterFunction<ServerResponse> deleteBillRoute(DeleteBillUseCase deleteUseCase){
        return route(DELETE("/bill/delete/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.status(HttpStatus.NO_CONTENT)
                        .body(BodyInserters.fromProducer(deleteUseCase.deleteById(request.pathVariable("id")), Void.class))
        );
    }

}
