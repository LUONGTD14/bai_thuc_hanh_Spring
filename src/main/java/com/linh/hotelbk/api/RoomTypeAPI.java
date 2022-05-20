package com.linh.hotelbk.api;

import com.linh.hotelbk.dto.request.AddRoomTypeRequest;
import com.linh.hotelbk.entity.RoomTypeEntity;
import com.linh.hotelbk.service.IRoomTypeService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@Api(tags = "API")
public class RoomTypeAPI {

    @Autowired
    private IRoomTypeService roomTypeService;

    @PostMapping(path = "/api/add-room-type")
    public String addRoomType(@RequestBody AddRoomTypeRequest request){
        try {
            RoomTypeEntity roomType = RoomTypeEntity.builder()
                    .roomTypeName(request.getRoomTypeName())
                    .createAt(new Date())
                    .updateAt(new Date())
                    .build();
            roomTypeService.save(roomType);
            return "Success";
        }catch(Exception e){
            return "Fail";
        }

    }
}
