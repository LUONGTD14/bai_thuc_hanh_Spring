package com.linh.hotelbk.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AddRoomTypeRequest {

    @JsonProperty(value = "roomTypeName")
    private String roomTypeName;
}
