package com.linh.hotelbk.service;

import com.linh.hotelbk.dto.request.RegistryRequest;
import com.linh.hotelbk.entity.UserEntity;

public interface IUserService {
    UserEntity registry(RegistryRequest req);
    boolean existsByEmail(String email);
    UserEntity getCurrentLoginUser();
}
