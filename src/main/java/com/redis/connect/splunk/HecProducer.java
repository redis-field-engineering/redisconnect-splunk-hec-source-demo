package com.redis.connect.splunk;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.time.Instant;

public class HecProducer implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(HecProducer.class.getName());
    private static final int iteration = Integer.parseInt(System.getProperty("iter", String.valueOf(1)));

    public static void main(String[] args) {
        new HecProducer().run();
    }

    @Override
    public void run() {

        for (int i = 1; i <= iteration; i++) {
            // Let's sleep for 1 sec between each iteration since HEC is dropping messages
            try {
                String jsonInfoMsg = String.format("{\"time\": %d, \"event\": \"%s\", \"source\": \"%s\", \"sourcetype\": \"%s\", \"host\": \"%s\", \"EventMsg\": \"This is test event %d for Java Logging with Logback and Splunk HEC\"}", Instant.now().toEpochMilli(), "infoLogs", "infoLogs", "logback", InetAddress.getLocalHost().getHostName(), i);
                logger.info("{}", jsonInfoMsg);

                String jsonDebugMsg = String.format("{\"time\": %d, \"event\": \"%s\", \"source\": \"%s\", \"sourcetype\": \"%s\", \"host\": \"%s\", \"SplunkURL\": \"%s\", \"SplunkToken\": \"%s\", \"SplunkIndex\": \"%s\"}", Instant.now().toEpochMilli(), "debugLogs", "debugLogs", "logback", InetAddress.getLocalHost().getHostName(), System.getProperty("splunk.url"), System.getProperty("splunk.token"), System.getProperty("splunk.index"));
                logger.debug("{}", jsonDebugMsg);

                Thread.sleep(1000);
            } catch (Exception e) {
                logger.error("Exception occurred..", e);
            }
        }
    }
}
