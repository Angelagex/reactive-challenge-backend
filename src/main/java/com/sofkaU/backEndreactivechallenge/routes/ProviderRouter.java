package com.sofkaU.backEndreactivechallenge.routes;


import com.sofkaU.backEndreactivechallenge.model.ProviderDTO;
import com.sofkaU.backEndreactivechallenge.usecases.DeleteProviderUseCase;
import com.sofkaU.backEndreactivechallenge.usecases.GetAllProvidersUseCase;
import com.sofkaU.backEndreactivechallenge.usecases.SaveProviderUseCase;
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
public class ProviderRouter {

    @Bean
    @RouterOperation(path = "/provider/all", beanClass = GetAllProvidersUseCase.class, beanMethod = "get", operation = @Operation(operationId = "getAllProvidersRoute", summary = "Get All Providers",
            responses = {@ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(schema = @Schema(implementation = ProviderDTO.class))),
                    @ApiResponse(responseCode = "404", description = "Not found")}))
    public RouterFunction<ServerResponse> getAllProvidersRoute(GetAllProvidersUseCase getAllUseCase) {
        return route(GET("/provider/all"),
                request -> ServerResponse.status(HttpStatus.OK)
                        .body(BodyInserters.fromProducer(getAllUseCase.getAllProvider(), ProviderDTO.class))
        );
    }

    @Bean
    @RouterOperation(path = "/provider/save", beanClass = SaveProviderUseCase.class, beanMethod = "post", operation = @Operation(operationId = "saveProviderRoute", summary = "Save A Provider",
            responses = {@ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(schema = @Schema(implementation = ProviderDTO.class))),
                    @ApiResponse(responseCode = "404", description = "Not found")}))
    public RouterFunction<ServerResponse> saveProviderRoute(SaveProviderUseCase saveUseCase) {
        return route(POST("/provider/save").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(ProviderDTO.class)
                        .flatMap(saveUseCase::saveProvider)
                        .flatMap(dto -> ServerResponse.status(HttpStatus.CREATED)
                                .contentType(MediaType.APPLICATION_JSON).bodyValue(dto))
        );
    }

    @Bean
    @RouterOperation(path = "/provider/delete/{id}", beanClass = DeleteProviderUseCase.class, beanMethod = "delete", operation = @Operation(operationId = "deleteProviderRoute", summary = "Delete Provider By Id",
            parameters = {@Parameter(in = ParameterIn.PATH, name = "id", description = "Provider Id")},
            responses = {@ApiResponse(responseCode = "204", description = "No content"),
                    @ApiResponse(responseCode = "404", description = "Not found")}))
    public RouterFunction<ServerResponse> deleteProviderRoute(DeleteProviderUseCase deleteUseCase) {
        return route(DELETE("/provider/delete/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.status(HttpStatus.NO_CONTENT)
                        .body(BodyInserters.fromProducer(deleteUseCase.deleteById(request.pathVariable("id")), Void.class))

        );
    }

}
