package com.linh.hotelbk.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AddRoleRequest {

    @JsonProperty(value = "roleName")
    private String roleName;
}
