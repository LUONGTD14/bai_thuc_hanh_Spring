package com.linh.hotelbk.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AddServiceRequest {
    @JsonProperty(value = "serviceName")
    private String serviceName;

    @JsonProperty(value = "serviceTypeId")
    private Long serviceTypeId;

    @JsonProperty(value = "price")
    private Integer price;
}
