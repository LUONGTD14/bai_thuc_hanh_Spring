package com.linh.hotelbk.dto.request;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class SaveFileRequest {
    private String fileName;
    private String fileContent;
}
