package org.samsung.fakestoreapi.core.usecase;

import org.samsung.fakestoreapi.core.domain.Order;
import org.samsung.fakestoreapi.core.domain.User;

import java.time.Instant;
import java.util.List;

public interface IGetAllUsersUseCase {


    /**
     * Retrieves an order based on the provided parameters.
     *
     * @param userId         The ID of the user who placed the order.
     * @param orderStartDate The start date for filtering orders.
     * @param orderEndDate   The end date for filtering orders.
     * @param orderId        The ID of the specific order to retrieve.
     * @return
     */
    List<User> execute();
}
