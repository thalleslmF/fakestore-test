package org.samsung.fakestoreapi.infrastructure.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.samsung.fakestoreapi.core.domain.Order;
import org.samsung.fakestoreapi.core.usecase.IGetOrderUseCase;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@Slf4j
@RequiredArgsConstructor
public class OrderController {
    private final IGetOrderUseCase getOrderUseCase;

    @GetMapping
    public List<Order> getOrders(@RequestParam(required = false) Integer userId,
                                 @RequestParam(required = false) Instant orderStartDate,
                                 @RequestParam(required = false) Instant orderEndDate,
                                 @RequestParam(required = false) Integer orderId) {
        log.info("Starting to get order by params: userId={}, orderStartDate={}, orderEndDate={}, orderId={}",
                userId, orderStartDate, orderEndDate, orderId);

        return this.getOrderUseCase.execute(
                userId, orderStartDate, orderEndDate, orderId
        );

    }
}