package com.linh.hotelbk.dto.request;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ResetPasswordRequest {

    private String email;
}
