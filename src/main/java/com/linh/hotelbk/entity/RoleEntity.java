package com.linh.hotelbk.entity;

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
@Table(name = "tbl_role")
public class RoleEntity {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RoleId")
    private Long Id;

    @Column(name = "RoleName", nullable = false)
    private String roleName;

    @ManyToMany(mappedBy = "roles", cascade = CascadeType.ALL)
    private Set<UserEntity> users;

    @JsonSerialize(using = CustomDateSerializer.class)
    @Column(name = "CreateAt", nullable = false)
    private Date createAt;

    @JsonSerialize(using = CustomDateSerializer.class)
    @Column(name = "UpdateAt")
    private Date updateAt;
}
