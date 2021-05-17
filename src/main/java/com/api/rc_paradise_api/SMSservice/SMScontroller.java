package com.api.rc_paradise_api.SMSservice;

import com.api.rc_paradise_api.SMSservice.OTPservice.OTPservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@RestController
public class SMScontroller {

    private final TwilowService twilowService;
    private final OTPservice otPservice;

    @Autowired
    public SMScontroller(TwilowService twilowService, OTPservice otPservice) {
        this.twilowService = twilowService;
        this.otPservice = otPservice;
    }

    @PostMapping("/sendSMS")
    private int sendSMS(@Validated @RequestBody SMSrequest SMS){
   //sending the SMS
        int OTP = otPservice.generateOTP(SMS.getPhoneNumber());  //Generating otp
        SMS.setOtp(OTP);  //setting OTP
        twilowService.sendSMS(SMS);
        return SMS.getOtp();
    }

   @GetMapping("/ValidateOTP")
    public String validate(@RequestBody SMSrequest SMS) throws ExecutionException {
  //Validating the OTP

        int otp = SMS.getOtp();
        String phone = SMS.getPhoneNumber();

        //Validate the Otp
        if(otp > 0){

            int storedOtp = otPservice.getOtp(phone);

            if(storedOtp > 0){
                if(otp == storedOtp){

                    otPservice.invalidateOTP(phone);

                    return ("Entered Otp is valid");
                }
                else {
                    return "OTP is valid ,but Not validated" + storedOtp + "G "+SMS.getPhoneNumber();
                }
            }else {
                return  String.valueOf(storedOtp) ;
            }
        }else {
            return  "FAIL!";
        }
    }
}
