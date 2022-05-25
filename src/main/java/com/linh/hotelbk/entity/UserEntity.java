package com.linh.hotelbk.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.linh.hotelbk.utils.CustomDateSerializer;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "tbl_user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UserId")
    private Long id;

    @Column(name = "Email", length = 40, nullable = false, unique = true)
    private String email;

    @Column(name = "Password")
    private String password;

    @Column(name = "FullName", nullable = false)
    private String fullName;

    @Column(name = "Avatar")
    private String gender;

    @Column(name = "Age")
    private Integer age;

    @Column(name = "PhoneNumber", nullable = false)
    private String phoneNumber;

    @Column(name = "IsActive")
    private Boolean isActive;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonIgnore
    @JoinTable(
            name = "tbl_user_role",
            joinColumns = @JoinColumn(name = "UserId", referencedColumnName = "UserId"),
            inverseJoinColumns = @JoinColumn(name = "RoleId", referencedColumnName = "RoleId")
    )
    private Set<RoleEntity> roles;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<BookingFormEntity> bookingForms;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private AddressEntity address;

    @Column(name = "ResetPassToken")
    private String resetPassToken;

    @JsonSerialize(using = CustomDateSerializer.class)
    @Column(name = "CreateAt", nullable = false)
    private Date createAt;

    @JsonSerialize(using = CustomDateSerializer.class)
    @Column(name = "UpdateAt")
    private Date updateAt;
}
