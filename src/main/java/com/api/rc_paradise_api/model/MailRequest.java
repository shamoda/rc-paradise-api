package com.api.rc_paradise_api.model;


import lombok.*;

@Data
public class MailRequest {

    private String name;
    private String to;
    private String from;
    private String subject;

}
