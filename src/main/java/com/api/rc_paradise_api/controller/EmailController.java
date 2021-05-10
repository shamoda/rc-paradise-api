package com.api.rc_paradise_api.controller;

import com.api.rc_paradise_api.Emailservice.EmailService;
import com.api.rc_paradise_api.model.MailRequest;
import com.api.rc_paradise_api.model.MailResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/api/v1")
public class EmailController {

    @Autowired
    private EmailService service;

    @PostMapping("/sendingEmail")
    public MailResponse sendEmail(@RequestBody MailRequest request) {
        Map<String, Object> model = new HashMap<>();
        model.put("Name", request.getName());
        model.put("location", "Bangalore,India");
        return service.sendEmail(request, model);

    }
   
   

}
