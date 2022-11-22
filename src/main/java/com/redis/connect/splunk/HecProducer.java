package com.redis.connect.splunk;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.time.Instant;

public class HecProducer implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(HecProducer.class.getName());
    private static final int iteration = Integer.parseInt(System.getProperty("iter", String.valueOf(1)));
    private String hostname = null;
    private final String splunkUrl = System.getProperty("splunk.url", "http://localhost:8088");
    private final String splunkToken = System.getProperty("splunk.token", "414b7f8e-cfbb-4a9a-b18a-303de41ccc76");
    private final String splunkIndex = System.getProperty("splunk.index", "hec");
    private final String splunkType = System.getProperty("splunk.type", "json");
    private final String splunkSourceType = System.getProperty("splunk.sourcetype", "logback");

    public static void main(String[] args) {
        new HecProducer().run();
    }

    @Override
    public void run() {

        for (int i = 1; i <= iteration; i++) {
            // Let's sleep for 1 sec between each iteration since HEC is dropping messages
            try {
                hostname = InetAddress.getLocalHost().getHostName();

                String jsonInfoMsg = String.format("{\"time\": %d, \"event\": \"%s\", \"source\": \"%s\", \"sourcetype\": \"%s\", \"host\": \"%s\", \"EventMsg\": \"This is test event %d for Java Logging with Logback and Splunk HEC\"}", Instant.now().toEpochMilli(), "infoLogs", "JavaLogging", splunkSourceType, hostname, i);
                logger.info("{}", jsonInfoMsg);

                String jsonDebugMsg = String.format("{\"time\": %d, \"event\": \"%s\", \"source\": \"%s\", \"sourcetype\": \"%s\", \"host\": \"%s\", \"SplunkURL\": \"%s\", \"SplunkToken\": \"%s\", \"SplunkIndex\": \"%s\", \"SplunkType\": \"%s\"}", Instant.now().toEpochMilli(), "debugLogs", "JavaLogging", splunkSourceType, hostname, splunkUrl, splunkToken, splunkIndex, splunkType);
                logger.debug("{}", jsonDebugMsg);

                Thread.sleep(1000);
            } catch (Exception e) {
                String jsonExceptionMsg = String.format("{\"time\": %d, \"event\": \"%s\", \"source\": \"%s\", \"sourcetype\": \"%s\", \"host\": \"%s\", \"SplunkURL\": \"%s\", \"SplunkToken\": \"%s\", \"SplunkIndex\": \"%s\", \"SplunkType\": \"%s\"}", Instant.now().toEpochMilli(), "errorLogs", "JavaLogging", splunkSourceType, hostname, splunkUrl, splunkToken, splunkIndex, splunkType);
                logger.error("{}", jsonExceptionMsg);
            }
        }
    }
}
