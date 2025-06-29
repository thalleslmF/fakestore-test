package org.samsung.fakestoreapi.core.usecase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.samsung.fakestoreapi.core.domain.Order;
import org.samsung.fakestoreapi.core.domain.User;
import org.samsung.fakestoreapi.core.gateway.IStoreClient;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
public class GetAllUsersUseCase implements IGetAllUsersUseCase {
    private final IStoreClient storeClient;

    @Override
    public List<User> execute() {

        return this.storeClient.getAllUsers();
    }

}