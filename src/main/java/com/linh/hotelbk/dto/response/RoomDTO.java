package com.linh.hotelbk.dto.response;

import com.linh.hotelbk.utils.enums.RoomStatus;
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
    private String status;
    private String availableFrom;
}
