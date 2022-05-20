package com.linh.hotelbk.dto.request;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class RegistryRequest {

    @NotNull(message = "FullName must not be empty")
    private String fullname;

    @NotEmpty(message = "Email must not be empty")
    @Email(message = "Invalid email")
    private String email;

    @NotEmpty(message = "Password must not be empty")
    @Length(min = 6, message = "Password must contain at least 6 character")
    private String password;

    @NotEmpty(message = "Phone must not be empty")
    @Length(min = 10, max = 11, message = "Invalid phone number")
    private String phone;

    private String fax;

    private String company;

    @NotNull(message = "Country must not be null")
    private Long countryId;

    @NotNull(message = "City must not be null")
    private Long cityId;

    @NotEmpty(message = "Address must not be empty")
    private String address;
}
