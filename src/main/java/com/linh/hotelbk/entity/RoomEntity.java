package com.linh.hotelbk.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.linh.hotelbk.utils.CustomDateSerializer;
import com.linh.hotelbk.utils.enums.RoomStatus;
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
@Table(name = "tbl_room")
public class RoomEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RoomId")
    private Long id;

    @Column(name = "RoomName", nullable = false)
    private String roomName;

    @Column(name = "Floor", nullable = false)
    private Integer floor;

    @Column(name = "Price", nullable = false)
    private Integer price;

    @Column(name = "Status", nullable = false)
    private Boolean status;

//    @Enumerated(EnumType.STRING)
//    @Column(name = "Status", nullable = false)
//    private RoomStatus status;

    @Column(name = "AvailableFrom", nullable = false)
    private Date availableFrom;

    @Column(name = "Image", nullable = false)
    private String image;

    @ManyToOne
    @JoinColumn(name = "RoomTypeId", referencedColumnName = "RoomTypeId")
    private RoomTypeEntity roomType;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<BookingFormEntity> bookingForms;

    @JsonSerialize(using = CustomDateSerializer.class)
    @Column(name = "CreateAt", nullable = false)
    private Date createAt;

    @JsonSerialize(using = CustomDateSerializer.class)
    @Column(name = "UpdateAt")
    private Date updateAt;

    @Transient
    public String getImagePath(){
        return "/hotel-demo/image/room/"+this.roomType.getRoomTypeName()+"/"+this.image;
    }

    @Transient
    public String getCurrency(){
        return this.price+" $";
    }
}
