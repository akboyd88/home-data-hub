package xyz.andrewkboyd.etltemplate.tasks;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.quartz.QuartzJobBean;

import org.springframework.transaction.annotation.Transactional;

import java.util.Random;


@DisallowConcurrentExecution
public class TestEmitterJob extends QuartzJobBean {
    private static final Logger LOG = LoggerFactory.getLogger(TestEmitterJob.class);
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final Random random = new Random();

    /**
     * Create task runner for fetching CVE information from NVD JSON list
     * @param template kafka template for publishing NVD data
     */
    public TestEmitterJob(KafkaTemplate<String, String> template) {
        kafkaTemplate = template;
    }

    /**
     * Execute the actual job. The job data map will already have been
     * applied as bean property values by execute. The contract is
     * exactly the same as for the standard Quartz execute method.
     *
     * @param context job context information
     * @see #execute
     */
    @Transactional
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        LOG.trace("Starting test emitter task");
        var randomIntString = Integer.toString(random.nextInt(250));
        kafkaTemplate.send("test.number", randomIntString)
            .addCallback(result -> {
                if(result == null) {
                    LOG.warn("sent {} to test.number, but result is null", randomIntString);
                    return;
                }
                LOG.info("sent {} to test.number, with meta {}", randomIntString, result.getRecordMetadata());
            },error -> LOG.error("Error while sending", error));
        LOG.trace("Finished test emitter task");
    }
}
