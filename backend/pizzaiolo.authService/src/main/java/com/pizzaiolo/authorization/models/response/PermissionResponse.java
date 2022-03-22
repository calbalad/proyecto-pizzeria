package com.pizzaiolo.authorization.models.response;

import com.pizzaiolo.authorization.models.entities.Permission;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class PermissionResponse {
    private Permission data;
}
