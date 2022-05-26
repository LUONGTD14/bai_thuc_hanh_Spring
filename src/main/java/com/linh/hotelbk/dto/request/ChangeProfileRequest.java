package com.linh.hotelbk.dto.request;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ChangeProfileRequest {
    private Long id;
    private String fullName;
    private String email;
    private String phone;
    private Long countryId;
    private Long cityId;
    private String address;
}
