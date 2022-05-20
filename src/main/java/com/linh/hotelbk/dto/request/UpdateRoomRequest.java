package com.linh.hotelbk.dto.request;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UpdateRoomRequest {
    private Long roomId;
    private String roomName;
    private Integer floor;
    private Integer price;
    private String availableFrom;
    private MultipartFile roomImg;
    private Long roomTypeId;
}
