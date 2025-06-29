package org.test.samsung.fakestoreapi;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.test.samsung.fakestoreapi.core.domain.Name;
import org.test.samsung.fakestoreapi.core.domain.Order;
import org.test.samsung.fakestoreapi.core.domain.Product;
import org.test.samsung.fakestoreapi.core.domain.User;
import org.test.samsung.fakestoreapi.core.gateway.IStoreClient;
import org.test.samsung.fakestoreapi.core.usecase.GetOrderUseCase;

import java.time.Instant;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GetOrderUseCaseTest {

    @Mock
    private IStoreClient storeClient;

    @InjectMocks
    private GetOrderUseCase getOrderUseCase;

    @Test
    void testExecute_ReturnsFilteredOrders() {
        // Arrange
        var user = User.builder().id(1).name(new Name()).build();
        var product = Product.builder().id(1).quantity(1).build();
        var order = Order.builder().id(1).date( Instant.now())
        .user(user).products(List.of(product)).build();

        when(storeClient.getAllOrders()).thenReturn(List.of(order));
        when(storeClient.getUserById(1)).thenReturn(user);
        when(storeClient.getProductById(1)).thenReturn(product);

        List<Order> result = getOrderUseCase.execute(1, Instant.now().minusSeconds(3600), Instant.now(), null);

        assertThat(result).isNotNull();
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getUser().getId()).isEqualTo(1);
        assertThat(result.get(0).getProducts()).containsExactly(product);

        verify(storeClient, times(1)).getAllOrders();
        verify(storeClient, times(1)).getUserById(1);
        verify(storeClient, times(1)).getProductById(1);
    }
}