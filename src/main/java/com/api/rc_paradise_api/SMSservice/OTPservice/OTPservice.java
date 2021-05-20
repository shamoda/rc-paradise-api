package com.api.rc_paradise_api.SMSservice.OTPservice;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@Service
//OTP service clss
public class OTPservice {
    //Defining expiration time to a OTP
    private static final int EXPIRE_TIME =  4 ;
    //Defining LoadingCache of Guava dependency
    private LoadingCache<String ,Integer> otpCache;

    public OTPservice() {
        super();
        //Setting expiration time and using the LoadingCache
        otpCache = CacheBuilder.newBuilder().
                expireAfterWrite(EXPIRE_TIME, TimeUnit.MINUTES)
                .build(new CacheLoader<String, Integer>() {
                    @Override
                    public Integer load(String s) throws Exception {
                        return 0;
                    }
                });

    }
    //Generate a unique OTP
    public int generateOTP(String key){
        Random random = new Random(); //a 4 NUMBER otp generated
        int value = 1000 + random.nextInt(9000);
        otpCache.put(key, value); //Storing in cache
        return  value; //returning OTP
    }
    //getting the OTP stored in local storage BASED ON KEY
    public int getOtp(String key)  {

        try{
            return otpCache.get(key);
        }catch (Exception e){
            return 0;
        }
    }

    public void invalidateOTP(String Key){
        //invalidating the Unique OTP after use
        otpCache.invalidate(Key);
    }
}
