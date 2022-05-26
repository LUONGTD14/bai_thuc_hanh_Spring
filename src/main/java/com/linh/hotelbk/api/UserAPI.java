package com.linh.hotelbk.api;

import com.linh.hotelbk.dto.request.ChangPasswordRequest;
import com.linh.hotelbk.dto.request.ChangeCurrentPasswordRequest;
import com.linh.hotelbk.dto.request.ChangeProfileRequest;
import com.linh.hotelbk.dto.request.ResetPasswordRequest;
import com.linh.hotelbk.dto.response.EmailBookingDTO;
import com.linh.hotelbk.entity.AddressEntity;
import com.linh.hotelbk.entity.UserEntity;
import com.linh.hotelbk.service.IUserService;
import com.linh.hotelbk.service.impl.SendMailService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@Api(tags = "API")
@Slf4j
public class UserAPI {

    @Value("${spring.mail.username}")
    private String mailFrom;
    private final IUserService userService;
    private final SendMailService mailService;
    private final PasswordEncoder passwordEncoder;

    public UserAPI(IUserService userService, SendMailService mailService, PasswordEncoder passwordEncoder){
        this.userService = userService;
        this.mailService = mailService;
        this.passwordEncoder = passwordEncoder;
    }

    @PutMapping(path = "/api/send-reset-mail")
    public Map<String, String> sendResetPassMail(@RequestBody ResetPasswordRequest request){
        System.out.println(request.getEmail());
        Map<String, String> result = new LinkedHashMap<>();
        if (!userService.existsByEmail(request.getEmail())){
            result.put("error", "error");
            return result;
        }
        UserEntity user = userService.findByEmail(request.getEmail());
        String resetPassToken = RandomString.make(20);
        user.setResetPassToken(resetPassToken);
        userService.update(user);
        //Send mail
        EmailBookingDTO emailDTO = EmailBookingDTO.builder()
                .from(mailFrom)
                .to(request.getEmail())
                .subject("Myhotel, Reset email !")
                .build();
        mailService.sendResetPassMail(resetPassToken, emailDTO);
        result.put("success", "success");
        return result;
    }

    @PutMapping(path = "/api/change-password")
    public Map<String, String> changePassword(@RequestBody ChangPasswordRequest request){
        Map<String, String> res = new LinkedHashMap<>();
        try{
            UserEntity user = userService.findByToken(request.getToken());
            String newPassword = passwordEncoder.encode(request.getNewPassword());
            user.setPassword(newPassword);
            userService.update(user);
            res.put("success", "success");
            return res;
        }catch (Exception e){
            log.error(e.getLocalizedMessage());
            res.put("error", "error");
            return res;
        }
    }

    @PutMapping(path = "/api/change-profile")
    public Map<String, String> changePròile(@RequestBody ChangeProfileRequest request){
        Map<String, String> res = new LinkedHashMap<>();
        try{
            UserEntity currentUser = userService.findById(request.getId());
            currentUser.setFullName(request.getFullName());
            currentUser.setEmail(request.getEmail());
            currentUser.setPhoneNumber(request.getPhone());

            AddressEntity oldAddress = currentUser.getAddress();
            oldAddress.setCityId(request.getCityId());
            oldAddress.setCountryId(request.getCountryId());
            oldAddress.setFullAddress(request.getAddress());

            currentUser.setAddress(oldAddress);
            userService.update(currentUser);

            res.put("success", "success");
            return res;
        }catch (Exception e){
            log.error(e.getLocalizedMessage());
            res.put("error", "error");
            return res;
        }
    }

    @PutMapping(path = "/api/change-current-password")
    public Map<String, String> changeCurrentPassword(@RequestBody ChangeCurrentPasswordRequest request){
        Map<String, String> res = new LinkedHashMap<>();
        try{
            UserEntity currentUser = userService.findById(request.getId());
            if (!passwordEncoder.matches(request.getOldPassword(), currentUser.getPassword())){
                res.put("error", "error");
                return res;
            }
            currentUser.setPassword(passwordEncoder.encode(request.getNewPassword()));
            userService.update(currentUser);
            res.put("success", "success");
            return res;
        }catch (Exception e){
            log.error(e.getLocalizedMessage());
            res.put("error", "error");
            return res;
        }
    }
}
