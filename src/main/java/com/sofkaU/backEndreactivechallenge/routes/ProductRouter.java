package com.sofkaU.backEndreactivechallenge.routes;

import com.sofkaU.backEndreactivechallenge.model.ProductDTO;
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
public class ProductRouter {

    @Bean
    @RouterOperation(path = "/product/all", beanClass = GetAllProductsUseCase.class, beanMethod = "get", operation = @Operation(operationId = "getAllProductsRoute", summary = "Get All Products",
            responses = {@ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(schema = @Schema(implementation = ProductDTO.class))),
                    @ApiResponse(responseCode = "404", description = "Not found")}))
    public RouterFunction<ServerResponse> getAllProductsRoute(GetAllProductsUseCase getAllUseCase){
        return route(GET("/product/all"),
                request -> ServerResponse.status(HttpStatus.OK)
                        .body(BodyInserters.fromProducer(getAllUseCase.getAllProduct(), ProductDTO.class))
        );
    }

    @Bean
    @RouterOperation(path = "/product/save", beanClass = SaveProductUseCase.class, beanMethod = "post", operation = @Operation(operationId = "saveProductRoute", summary = "Save A Product",
            responses = {@ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(schema = @Schema(implementation = ProductDTO.class))),
                    @ApiResponse(responseCode = "404", description = "Not found")}))
    public RouterFunction<ServerResponse> saveProductRoute(SaveProductUseCase saveUseCase){
        return route(POST("/product/save").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(ProductDTO.class)
                        .flatMap(saveUseCase::saveProduct)
                        .flatMap( dto -> ServerResponse.status(HttpStatus.CREATED)
                                .contentType(MediaType.APPLICATION_JSON).bodyValue(dto))
        );
    }

    @Bean
    @RouterOperation(path = "/product/delete/{id}", beanClass = DeleteProductUseCase.class, beanMethod = "delete", operation = @Operation(operationId = "deleteProductRoute", summary = "Delete Product By Id",
            parameters = {@Parameter(in = ParameterIn.PATH, name = "id", description = "Product Id")},
            responses = {@ApiResponse(responseCode = "204", description = "No content"),
                    @ApiResponse(responseCode = "404", description = "Not found")}))
    public RouterFunction<ServerResponse> deleteProductRoute(DeleteProductUseCase deleteUseCase){
        return route(DELETE("/product/delete/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.status(HttpStatus.NO_CONTENT)
                        .body(BodyInserters.fromProducer(deleteUseCase.deleteById(request.pathVariable("id")), Void.class))
        );
    }

    @Bean
    @RouterOperation(path = "/product/update", beanClass = UpdateProductUseCase.class, beanMethod = "post", operation = @Operation(operationId = "updateProductRoute", summary = "Update A Product",
            responses = {@ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(schema = @Schema(implementation = ProductDTO.class))),
                    @ApiResponse(responseCode = "404", description = "Not found")}))
    public RouterFunction<ServerResponse> updateProductRouter(UpdateProductUseCase updateProductUseCase){

        return route(PUT("product/update").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(ProductDTO.class)
                        .flatMap(productDTO -> updateProductUseCase.saveProduct(productDTO))
                        .flatMap(result ->
                                 ServerResponse.status( result.getName()!=null ? HttpStatus.CREATED : HttpStatus.NOT_FOUND)
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(result)));
    }
}
