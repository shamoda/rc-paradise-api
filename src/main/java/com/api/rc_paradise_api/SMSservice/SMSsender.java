package com.api.rc_paradise_api.SMSservice;

public interface SMSsender {
    //Defining send SMS method to be implemented in Twilio service
    void sendSMS(SMSrequest smSrequest);
}
