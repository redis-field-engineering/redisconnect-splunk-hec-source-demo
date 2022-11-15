package com.redis.connect.splunk;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

public class HecProducer implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(HecProducer.class.getName());
    private static final int iteration = Integer.parseInt(System.getProperty("iter", String.valueOf(1)));

    public static void main(String[] args) {
        new HecProducer().run();
    }

    @Override
    public void run() {

        for (int i = 1; i <= iteration; i++) {
            String jsonMsg = String.format("{EventId:'%s', EventDate:'%s', EventTimeInMillis:'%d', EventMsg:'This is test event %d for Java Logging with Logback and Splunk HEC'}", UUID.randomUUID(), new Date(), Instant.now().toEpochMilli(), i);
            logger.info("{}", jsonMsg);
            logger.debug("Splunk URL: {}, Splunk Token: {}, Splunk Index: {}", System.getProperty("splunk.url"), System.getProperty("splunk.token"), System.getProperty("splunk.index"));
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            logger.error("InterruptedException..", e);
        }
    }
}
