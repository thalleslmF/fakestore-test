package org.samsung.fakestoreapi.infrastructure.client.response;

import lombok.Builder;
import lombok.Data;
import org.samsung.fakestoreapi.core.domain.Address;
import org.samsung.fakestoreapi.core.domain.Name;
import org.samsung.fakestoreapi.core.domain.User;

@Builder
@Data
public class UserClientResponse {
    private int id;
    private String email;
    private String username;
    private String password;
    private Name name;
    private String phone;
    private int __v;
    private Address address;

    public User toUser() {
        return User.builder()
                .id(this.id)
                .email(this.email)
                .name(this.name)
                .address(this.address)
                .build();
    }
}