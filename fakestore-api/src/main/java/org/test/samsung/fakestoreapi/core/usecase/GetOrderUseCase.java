package org.test.samsung.fakestoreapi.core.usecase;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.test.samsung.fakestoreapi.core.domain.Order;
import org.test.samsung.fakestoreapi.core.gateway.IStoreClient;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
public class GetOrderUseCase implements IGetOrderUseCase {
    private final IStoreClient storeClient;

    @Override
    public List<Order> execute(Integer userId, Instant orderStartDate, Instant orderEndDate, Integer orderId) {
        log.info("Executing GetOrderUseCase with userId={}, orderStartDate={}, orderEndDate={}, orderId={}",
                userId, orderStartDate, orderEndDate, orderId);

        List<Order> orders = getOrder(orderId);

        log.info("Filtering orders by userId={}", userId);
        orders = filterOrdersByUser(orders, userId);
        log.info("Filtered orders by userId. Remaining orders: {}", orders.size());

        log.info("Filtering orders by date range: {} to {}", orderStartDate, orderEndDate);
        orders = filterOrdersByDate(orders, orderStartDate, orderEndDate);
        log.info("Filtered orders by date range. Remaining orders: {}", orders.size());

        log.info("Enriching product information for the remaining orders");
        orders.forEach(this::enrichOrderProductsInfo);

        log.info("Finished executing GetOrderUseCase. Total orders returned: {}", orders.size());
        return orders;
    }

    private void enrichOrderProductsInfo(Order order) {
        log.debug("Enriching products for orderId={}", order.getId());
        order.setProducts(
                order.getProducts().stream().map(product -> {
                    var productWithAllData = storeClient.getProductById(product.getId());
                    product.setCategory(productWithAllData.getCategory());
                    product.setDescription(productWithAllData.getDescription());
                    product.setImage(productWithAllData.getImage());
                    product.setTitle(productWithAllData.getTitle());
                    product.setPrice(productWithAllData.getPrice());
                    return product;
                }).toList());
    }

    private List<Order> getOrder(Integer orderId) {
        if (orderId != null) {
            return Optional.ofNullable(storeClient.getOrderById(orderId))
                    .map(it -> {
                        it.setUser(storeClient.getUserById(it.getUser().getId()));
                        return List.of(it);
                    })
                    .orElse(List.of());
        } else {
            return storeClient.getAllOrders().stream().peek(it -> it.setUser(storeClient.getUserById(it.getUser().getId())))
                    .toList();
        }
    }

    private List<Order> filterOrdersByUser(List<Order> orders, Integer userId) {
        if (userId == null) {
            log.warn("User ID is null, skipping user filtering");
            return orders;
        }
        return orders.stream()
                .filter(order -> order.getUser().getId() == userId)
                .toList();
    }

    private List<Order> filterOrdersByDate(List<Order> orders, Instant startDate, Instant endDate) {
        if (startDate == null && endDate == null) {
            log.warn("Start date and end date are null, skipping date filtering");
            return orders;
        }

        return orders.stream()
                .filter(order -> {
                    boolean isAfterOrEqualStart = startDate == null || order.getDate().equals(startDate) || order.getDate().isAfter(startDate);
                    boolean isBeforeOrEqualEnd = endDate == null || order.getDate().equals(endDate) || order.getDate().isBefore(endDate);
                    return isAfterOrEqualStart && isBeforeOrEqualEnd;
                })
                .toList();
    }
}