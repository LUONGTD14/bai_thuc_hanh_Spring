package com.linh.hotelbk.dto.request;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class BookRoomRequest {
    private Long roomId;
    private String customerName;
    private String customerPhone;
    private String customerEmail;
    private String roomName;
    private String roomPrice;
    private Long roomTypeId;
    private String checkInDate;
    private String checkOutDate;
}
