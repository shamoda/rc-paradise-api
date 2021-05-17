package com.api.rc_paradise_api.SMSservice.OTPservice;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@Service
public class OTPservice {

    private static final int EXPIRE_TIME =  4 ;
    private LoadingCache<String ,Integer> otpCache;


    public OTPservice() {

        super();
        otpCache = CacheBuilder.newBuilder().
                expireAfterWrite(EXPIRE_TIME, TimeUnit.MINUTES)
                .build(new CacheLoader<String, Integer>() {
                    @Override
                    public Integer load(String s) throws Exception {
                        return 0;
                    }
                });

    }


    public int generateOTP(String key){
        //GENERATING otp
        Random random = new Random();
        int value = 1000 + random.nextInt(9000);
        otpCache.put(key, value);

        return  value;
    }

    public int getOtp(String key)  {

        //getting the OTP stored in local storage

            try{
                return otpCache.get(key);
            }catch (Exception e){
                return 0;
            }
        }

    public void invalidateOTP(String Key){
        //invalidating the Unique OTP

        otpCache.invalidate(Key);


    }
}
