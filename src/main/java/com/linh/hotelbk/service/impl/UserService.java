package com.linh.hotelbk.service.impl;

import com.linh.hotelbk.dto.Mapper;
import com.linh.hotelbk.dto.request.RegistryRequest;
import com.linh.hotelbk.entity.AddressEntity;
import com.linh.hotelbk.entity.RoleEntity;
import com.linh.hotelbk.entity.UserEntity;
import com.linh.hotelbk.repository.IRoleRepository;
import com.linh.hotelbk.repository.IUserRepository;
import com.linh.hotelbk.service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class UserService implements IUserService {

    private final IUserRepository userRepository;
    private final IRoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserEntity registry(RegistryRequest req) {
        UserEntity user = Mapper.convertToUserEntity(req);

        //Tạo địa chỉ
        AddressEntity address = AddressEntity.builder()
                .fullAddress(req.getAddress())
                .countryId(req.getCountryId())
                .cityId(req.getCityId())
                .createAt(new Date())
                .updateAt(new Date())
                .user(user)
                .build();

        user.setAddress(address);
        user.setPassword(passwordEncoder.encode(req.getPassword()));

        //Tạo role cho user
        Set<RoleEntity> roles = new LinkedHashSet<>();
        roles.add(roleRepository.findByRoleName("ADMIN"));
        roles.add(roleRepository.findByRoleName("USER"));

        user.setRoles(roles);
        user = userRepository.saveAndFlush(user);
        return user;
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public UserEntity getCurrentLoginUser() {
        return ((UserPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
    }

    @Override
    public UserEntity findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public UserEntity findByToken(String token) {
        return userRepository.findByResetPassToken(token);
    }

    @Override
    public void update(UserEntity user) {
        userRepository.saveAndFlush(user);
    }

    @Override
    public UserEntity findById(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }
}
