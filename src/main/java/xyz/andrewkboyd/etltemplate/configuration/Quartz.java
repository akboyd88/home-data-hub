package xyz.andrewkboyd.etltemplate.configuration;

import xyz.andrewkboyd.etltemplate.tasks.TestEmitterJob;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;


import org.quartz.SimpleScheduleBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Quartz {

    public static final String TEST_JOB_ID = "QUARTZ_TEST_EMITTER";

    @Bean
    public JobDetail testEmitterJobDetails() {
        return JobBuilder.newJob(TestEmitterJob.class)
                .withIdentity(TEST_JOB_ID)
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger fetchNVDCVERecentTrigger(@Qualifier("testEmitterJobDetails") JobDetail jobDetails) {
        return TriggerBuilder
                .newTrigger()
                .forJob(jobDetails)
                .withIdentity(TEST_JOB_ID)
                .withSchedule(SimpleScheduleBuilder.repeatSecondlyForever(5))
                .startNow()
                .build();
    }
}
