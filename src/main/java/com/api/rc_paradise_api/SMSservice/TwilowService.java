package com.api.rc_paradise_api.SMSservice;

import com.api.rc_paradise_api.SMSservice.OTPservice.OTPservice;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
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
    public void sendSMS(SMSrequest smSrequest) {
        if(isPhoneValid(smSrequest.getPhoneNumber())){
            PhoneNumber to =  new PhoneNumber(smSrequest.getPhoneNumber());
            PhoneNumber from = new PhoneNumber(twilloConfig.getTrialNumber());
           MessageCreator messageCreator = Message.creator(
                    to,
                    from,
                    Integer.toString(smSrequest.getOtp())
            );
            messageCreator.create();
            LOGGER.info("SEND SMS");

        }else{

            throw  new IllegalArgumentException("Phone Number " +smSrequest.getPhoneNumber() + "is not valid");
        }

    }

    private boolean isPhoneValid(String phoneNumber) {
    //Implement Phone number validator
        return true;
    }
}
