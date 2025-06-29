package org.test.samsung.fakestoreapi.core.usecase;

import org.test.samsung.fakestoreapi.core.domain.Order;

import java.time.Instant;
import java.util.List;

public interface IGetOrderUseCase {


    /**
     * Retrieves an order based on the provided parameters.
     *
     * @param userId         The ID of the user who placed the order.
     * @param orderStartDate The start date for filtering orders.
     * @param orderEndDate   The end date for filtering orders.
     * @param orderId        The ID of the specific order to retrieve.
     * @return
     */
    List<Order> execute(Integer userId, Instant orderStartDate, Instant orderEndDate, Integer orderId);
}
