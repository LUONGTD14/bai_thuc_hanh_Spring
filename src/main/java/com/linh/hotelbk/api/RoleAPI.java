package com.linh.hotelbk.api;

import com.linh.hotelbk.dto.request.AddRoleRequest;
import com.linh.hotelbk.entity.RoleEntity;
import com.linh.hotelbk.repository.IRoleRepository;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@Api(tags = "API")
public class RoleAPI {

    @Autowired
    private IRoleRepository roleRepository;

    @PostMapping(path = "api/addRole")
    public String addRole(@RequestBody AddRoleRequest req){
        RoleEntity role = RoleEntity.builder()
                .roleName(req.getRoleName())
                .createAt(new Date())
                .updateAt(new Date())
                .build();

        try{
            roleRepository.save(role);
            return "Success";
        }catch (Exception e){
            e.printStackTrace();
            return "Failed";
        }
    }
}
