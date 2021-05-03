package com.api.rc_paradise_api.Registration;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Registrationrequest {

    private final String phone;
    private final String name;
    private final String password;
    private final boolean seller;
}
