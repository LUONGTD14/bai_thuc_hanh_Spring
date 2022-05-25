package com.linh.hotelbk.dto.request;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ChangPasswordRequest {
    private String newPassword;
    private String token;
}
