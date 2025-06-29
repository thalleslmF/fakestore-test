package org.test.samsung.fakestoreapi;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.test.samsung.fakestoreapi.core.domain.Name;
import org.test.samsung.fakestoreapi.core.domain.User;
import org.test.samsung.fakestoreapi.core.gateway.IStoreClient;
import org.test.samsung.fakestoreapi.core.usecase.GetAllUsersUseCase;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetAllUsersUseCaseTest {

    @Mock
    private IStoreClient storeClient;

    @InjectMocks
    private GetAllUsersUseCase getAllUsersUseCase;

    @Test
    void testExecute_ReturnsAllUsers() {
        // Arrange
        var user1 = User.builder().id(1).name(new Name()).build();
        when(storeClient.getAllUsers()).thenReturn(List.of(user1));

        // Act
        List<User> result = getAllUsersUseCase.execute();

        // Assert
        assertThat(result).isNotNull();
        assertThat(result).hasSize(1);
        assertThat(result).containsExactly(user1);
    }
}