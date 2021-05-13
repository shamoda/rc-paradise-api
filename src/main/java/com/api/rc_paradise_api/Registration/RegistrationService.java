package com.api.rc_paradise_api.Registration;

import com.api.rc_paradise_api.model.AppUserRole;
import com.api.rc_paradise_api.model.User;
import com.api.rc_paradise_api.service.UserService;



public class RegistrationService {

    private  final UserService userService;

    public RegistrationService(UserService userService) {
        this.userService = userService;
    }

    public String register(User user){
        return userService.registerUser(
            new User(
                    user.getPhone(),
                    user.getName(),
                    user.getPassword(),
                    user.getRole()
            )
        );
    }

}
