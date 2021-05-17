package com.api.rc_paradise_api.SMSservice;

public class SMSrequest {

    private final String phoneNumber;
    private int otp;



    public SMSrequest(String phoneNumber, int otp) {
        this.phoneNumber = phoneNumber;
        this.otp = otp;
    }


    public String getPhoneNumber() {
        return phoneNumber;
    }


}
