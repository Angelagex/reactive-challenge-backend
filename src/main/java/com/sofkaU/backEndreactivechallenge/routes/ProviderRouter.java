package com.sofkaU.backEndreactivechallenge.routes;


import com.sofkaU.backEndreactivechallenge.model.ProviderDTO;
import com.sofkaU.backEndreactivechallenge.usecases.DeleteProviderUseCase;
import com.sofkaU.backEndreactivechallenge.usecases.GetAllProvidersUseCase;
import com.sofkaU.backEndreactivechallenge.usecases.SaveProviderUseCase;
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
public class ProviderRouter {

    @Bean
    public RouterFunction<ServerResponse> getAllProvidersRoute(GetAllProvidersUseCase getAllUseCase){
        return route(GET("/provider/all"),
                request -> ServerResponse.status(HttpStatus.OK)
                        .body(BodyInserters.fromProducer(getAllUseCase.getAllProvider(), ProviderDTO.class))
        );
    }

    @Bean
    public RouterFunction<ServerResponse> saveProviderRoute(SaveProviderUseCase saveUseCase){
        return route(POST("/provider/save").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(ProviderDTO.class)
                        .flatMap(saveUseCase::saveProvider)
                        .flatMap( dto -> ServerResponse.status(HttpStatus.CREATED)
                                .contentType(MediaType.APPLICATION_JSON).bodyValue(dto))
        );
    }

    @Bean
    public RouterFunction<ServerResponse> deleteProviderRoute(DeleteProviderUseCase deleteUseCase){
        return route(DELETE("/provider/delete/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.status(HttpStatus.NO_CONTENT)
                        .body(BodyInserters.fromProducer(deleteUseCase.deleteById(request.pathVariable("id")), Void.class))

        );
    }

}
