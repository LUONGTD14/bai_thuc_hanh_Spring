package com.linh.hotelbk.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AddUsedServiceRequest {


    @JsonProperty(value = "quantity")
    private Integer quantity;

    @JsonProperty(value = "serviceId")
    private Long serviceId;

    @JsonProperty(value = "bookingFormId")
    private Long bookingFormId;
}
