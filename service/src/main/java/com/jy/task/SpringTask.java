package com.jy.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SpringTask {

    //@Scheduled(cron = "*/3 * * * * ?")
    public void task(){
        System.out.println("所有失去的都会以另一种方式归来");
    }

}
