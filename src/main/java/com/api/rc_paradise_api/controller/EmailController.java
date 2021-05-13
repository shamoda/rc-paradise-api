package com.api.rc_paradise_api.controller;

import com.api.rc_paradise_api.Emailservice.EmailService;
import com.api.rc_paradise_api.model.MailRequest;
import com.api.rc_paradise_api.model.MailResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*")
public class EmailController {

    @Autowired
    private EmailService service;

    @PostMapping("/sendingEmail")
    public MailResponse sendEmail(@RequestBody MailRequest request) {
        Map<String, Object> model = new HashMap<>();
        model.put("Name", request.getName());
        model.put("location", "Sri Lanka");
        return service.sendEmail(request, model);
    }
   
   

}
