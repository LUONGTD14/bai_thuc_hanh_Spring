package com.linh.hotelbk.dto.request;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ChangeCurrentPasswordRequest {
    private Long id;
    private String oldPassword;
    private String newPassword;
}
