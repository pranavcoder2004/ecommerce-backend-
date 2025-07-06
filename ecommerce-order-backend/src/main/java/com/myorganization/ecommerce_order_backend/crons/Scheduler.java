package com.myorganization.ecommerce_order_backend.crons;

import com.myorganization.ecommerce_order_backend.appCache.AppCacheForUserNameUnique;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Scheduler {
    @Autowired
    AppCacheForUserNameUnique appCacheForUserNameUnique;
    @Scheduled(cron = "0/5 * * * * ?")
    private void updatecache(){
        appCacheForUserNameUnique.appCache();
    }
}
