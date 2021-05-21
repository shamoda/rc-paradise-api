package com.api.rc_paradise_api.sms;

import com.api.rc_paradise_api.sms.Config.TwilloConfig;
import com.twilio.Twilio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TwilioInitializer {
    //Defining inBuilt Logger factory
    private final static Logger LOGGER = LoggerFactory.getLogger(TwilioInitializer.class);
    private final TwilloConfig twilloConfig;

    @Autowired //Dependency injection
    public TwilioInitializer(TwilloConfig twilloConfig) {
        this.twilloConfig = twilloConfig;
        Twilio.init(
                //initializing twilio Config data
                twilloConfig.getAccountSid(),
                twilloConfig.getAuthToken()

        );//Console.log details
        LOGGER.info("Twillio initialialized... with account sid() ", twilloConfig.getAuthToken());
    }
}
