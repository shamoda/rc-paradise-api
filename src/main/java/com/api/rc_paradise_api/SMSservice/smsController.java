package com.api.rc_paradise_api.SMSservice;

import com.api.rc_paradise_api.SMSservice.OTPservice.OTPservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.concurrent.ExecutionException;


@RestController
@RequestMapping("/api/v1")
@CrossOrigin
public class smsController {

    private final TwilowService twilowService;
    private final OTPservice otPservice;

    @Autowired
    public smsController(TwilowService twilowService, OTPservice otPservice) {
        this.twilowService = twilowService;
        this.otPservice = otPservice;
    }

    @PostMapping("/sendSMS") //sending SMS
    private int sendSMS( @RequestBody SMSrequest SMS){
        int OTP = otPservice.generateOTP(SMS.getPhoneNumber());  //Generating otp
        SMS.setOtp(OTP);  //setting OTP
        twilowService.sendSMS(SMS);//Sending OTP using twilio service Api
        return SMS.getOtp(); //return OTP
    }

   @PostMapping("/validateOTP") //Validating OTP
    public String validate(@RequestBody SMSrequest SMS) throws ExecutionException {
        //Getting OTP & phone No
        int otp = SMS.getOtp();
        String phone = SMS.getPhoneNumber();
        if(otp > 0){  //Validate the Otp
            int storedOtp = otPservice.getOtp(phone);  //Get OTP stored in cache
            if(storedOtp > 0){//Checking is it valid
                if(otp == storedOtp){//Validating
                    otPservice.invalidateOTP(phone); //Invalidating after use
                    return ("Entered Otp is valid");
                }
                else {
                    return "";
                }
            }else {                     //return empty String if invalid
                return  "";
            }
        }else {
            return  "";
        }
    }
}
