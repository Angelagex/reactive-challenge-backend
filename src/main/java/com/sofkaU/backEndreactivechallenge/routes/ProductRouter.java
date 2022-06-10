package com.sofkaU.backEndreactivechallenge.routes;

import com.sofkaU.backEndreactivechallenge.model.ProductDTO;
import com.sofkaU.backEndreactivechallenge.usecases.DeleteProductUseCase;
import com.sofkaU.backEndreactivechallenge.usecases.GetAllProductsUseCase;
import com.sofkaU.backEndreactivechallenge.usecases.SaveProductUseCase;
import com.sofkaU.backEndreactivechallenge.usecases.UpdateProductUseCase;
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
    public RouterFunction<ServerResponse> getAllProductsRoute(GetAllProductsUseCase getAllUseCase){
        return route(GET("/product/all"),
                request -> ServerResponse.status(HttpStatus.OK)
                        .body(BodyInserters.fromProducer(getAllUseCase.getAllProduct(), ProductDTO.class))
        );
    }

    @Bean
    public RouterFunction<ServerResponse> saveProductRoute(SaveProductUseCase saveUseCase){
        return route(POST("/product/save").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(ProductDTO.class)
                        .flatMap(saveUseCase::saveProduct)
                        .flatMap( dto -> ServerResponse.status(HttpStatus.CREATED)
                                .contentType(MediaType.APPLICATION_JSON).bodyValue(dto))
        );
    }

    @Bean
    public RouterFunction<ServerResponse> deleteProductRoute(DeleteProductUseCase deleteUseCase){
        return route(DELETE("/product/delete/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.status(HttpStatus.NO_CONTENT)
                        .body(BodyInserters.fromProducer(deleteUseCase.deleteById(request.pathVariable("id")), Void.class))
        );
    }

    @Bean
    RouterFunction<ServerResponse> updateProductRouter(UpdateProductUseCase updateProductUseCase){

        return route(PUT("product/update").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(ProductDTO.class)
                        .flatMap(productDTO -> updateProductUseCase.saveProduct(productDTO))
                        .flatMap(result ->
                                 ServerResponse.status( result.getName()!=null ? HttpStatus.CREATED : HttpStatus.NOT_FOUND)
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(result)));
    }
}
