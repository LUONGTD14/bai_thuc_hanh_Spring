package com.linh.hotelbk.dto.request;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UpdateBookingFormRequest {
    private Long bookingId;
    private String checkInDate;
    private String checkOutDate;
}
