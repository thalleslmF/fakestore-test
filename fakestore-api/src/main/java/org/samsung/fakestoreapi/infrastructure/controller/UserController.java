package org.samsung.fakestoreapi.infrastructure.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.samsung.fakestoreapi.core.domain.Order;
import org.samsung.fakestoreapi.core.domain.User;
import org.samsung.fakestoreapi.core.usecase.IGetAllUsersUseCase;
import org.samsung.fakestoreapi.core.usecase.IGetOrderUseCase;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@Slf4j
@RequiredArgsConstructor
public class UserController {
    private final IGetAllUsersUseCase getAllUsersUseCase;

    @GetMapping
    public List<User> getOrders() {


        return this.getAllUsersUseCase.execute();

    }
}