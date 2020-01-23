package com.finland.service.job;

import com.finland.dao.SubscriptionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
@PropertySource("classpath:scheduler.properties")
public class SubscriptionScheduler {

    @Autowired
    SubscriptionDao subscriptionDao;

    @Scheduled(cron = "${cron.expression}")
    public void subscriptionJob() {
        subscriptionDao.subscriptionJob();
    }
}
