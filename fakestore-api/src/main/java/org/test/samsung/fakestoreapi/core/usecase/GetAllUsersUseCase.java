package org.test.samsung.fakestoreapi.core.usecase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.test.samsung.fakestoreapi.core.domain.User;
import org.test.samsung.fakestoreapi.core.gateway.IStoreClient;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
public class GetAllUsersUseCase implements IGetAllUsersUseCase {
    private final IStoreClient storeClient;

    @Override
    public List<User> execute() {

        return this.storeClient.getAllUsers();
    }

}