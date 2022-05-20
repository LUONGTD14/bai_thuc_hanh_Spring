package com.linh.hotelbk.dto.request;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AddRoomRequest {
    private String roomName;
    private MultipartFile roomImg;
    private Integer floor;
    private Integer price;
    private String availableFrom;
    private Long roomTypeId;
}
