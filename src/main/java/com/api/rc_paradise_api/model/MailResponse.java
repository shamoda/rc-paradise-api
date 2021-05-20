package com.api.rc_paradise_api.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
//Mail Reply or response modeule
public class MailResponse {

    private String message;
    private boolean status;

}
