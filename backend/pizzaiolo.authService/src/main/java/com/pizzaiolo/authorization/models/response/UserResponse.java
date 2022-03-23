package com.pizzaiolo.authorization.models.response;

import com.pizzaiolo.authorization.models.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class UserResponse {
    private User data;
}
