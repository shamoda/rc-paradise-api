package com.api.rc_paradise_api.SMSservice;

import com.api.rc_paradise_api.SMSservice.Config.TwilloConfig;
import com.api.rc_paradise_api.SMSservice.OTPservice.OTPservice;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service //implementing SMSsender interface
public class TwilowService implements SMSsender{

    private static final Logger LOGGER = LoggerFactory.getLogger(TwilowService.class);
    private final TwilloConfig twilloConfig;
    private final OTPservice otPservice;

    @Autowired
    public TwilowService(TwilloConfig twilloConfig, OTPservice otPservice) {
        this.twilloConfig = twilloConfig;
        this.otPservice = otPservice;
    }

    @Override
    public void sendSMS(SMSrequest smsRequest) {
        if(isPhoneValid(smsRequest.getPhoneNumber())){  //Checking for phone number
            PhoneNumber to =  new PhoneNumber(smsRequest.getPhoneNumber());  //Setting basic SMS data
            PhoneNumber from = new PhoneNumber(twilloConfig.getTrialNumber());
            MessageCreator messageCreator = Message.creator(//Creating the message
                    to,
                    from,
                    Integer.toString(smsRequest.getOtp())
            );
            messageCreator.create();//Create
            LOGGER.info("SEND SMS");

        }else{
            //Throwing excepting ,phone no invalid
            throw  new IllegalArgumentException("Phone Number " +smsRequest.getPhoneNumber() + "is not valid");
        }

    }
    //Checking phone Number
    private boolean isPhoneValid(String phoneNumber) {

        String regex = "(0/91)?[7-9][0-9]{10}"; //Simple regex
        if(phoneNumber.matches(regex)){
            return true;
        }else{
            return false;
        }

    }
}
