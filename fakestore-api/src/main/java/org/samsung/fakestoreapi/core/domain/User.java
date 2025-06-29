package org.samsung.fakestoreapi.core.domain;

import lombok.Builder;
import lombok.Data;
@Data
@Builder
public class User {
    private int id;
    private String email;
    private Name name;
    private Address address;


}

