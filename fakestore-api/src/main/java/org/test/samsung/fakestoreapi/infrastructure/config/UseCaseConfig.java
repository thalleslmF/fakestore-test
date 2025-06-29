package org.test.samsung.fakestoreapi.infrastructure.config;

import org.test.samsung.fakestoreapi.core.gateway.IStoreClient;
import org.test.samsung.fakestoreapi.core.usecase.GetAllUsersUseCase;
import org.test.samsung.fakestoreapi.core.usecase.GetOrderUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {


    @Bean
    public GetOrderUseCase getOrderUseCase(IStoreClient storeClient) {
        return new GetOrderUseCase(storeClient);
    }

    @Bean
    public GetAllUsersUseCase getAllUsersUseCase(IStoreClient storeClient) {
        return new GetAllUsersUseCase(storeClient);
    }
}
