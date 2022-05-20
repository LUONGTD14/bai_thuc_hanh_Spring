package com.linh.hotelbk.dto.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Getter
@Setter
public class RoomDTO {
    private Long roomId;
    private String roomName;
    private String roomImg;
    private String price;
    private Boolean status;
    private String availableFrom;
}
