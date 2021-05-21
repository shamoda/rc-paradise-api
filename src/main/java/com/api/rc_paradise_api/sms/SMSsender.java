package com.api.rc_paradise_api.sms;

import com.api.rc_paradise_api.model.SMSrequest;

public interface SMSsender {
    //Defining send SMS method to be implemented in Twilio service
    void sendSMS(SMSrequest smSrequest);
}
