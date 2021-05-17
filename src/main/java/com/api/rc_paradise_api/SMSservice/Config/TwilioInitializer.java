package com.api.rc_paradise_api.SMSservice;

import com.twilio.Twilio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TwilioInitializer {

    private final static Logger LOGGER = LoggerFactory.getLogger(TwilioInitializer.class);
    private final TwilloConfig twilloConfig;

    @Autowired
    public TwilioInitializer(TwilloConfig twilloConfig) {
        this.twilloConfig = twilloConfig;
        Twilio.init(

                twilloConfig.getAccountSid(),
                twilloConfig.getAuthToken()

        );
        LOGGER.info("Twillio initialialized... with account sid() ", twilloConfig.getAuthToken());
    }
}
