package com.redis.connect.splunk;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import picocli.CommandLine;

import java.util.Date;

@CommandLine.Command(name = "com.redis.connect.splunk.HecProducer", usageHelpAutoWidth = true, description = "HEC producer load generator.")
public class HecProducer implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(HecProducer.class.getName());
    @CommandLine.Option(names = {"-i", "--iter"}, description = "Iterations to run (default: ${DEFAULT-VALUE})", defaultValue = "1")
    private static int iteration;

    public HecProducer(int iter) {
        HecProducer.iteration = iter;
    }

    public static void main(String[] args) {
        new CommandLine(new HecProducer(iteration)).execute(args);
    }

    @Override
    public void run() {

        Date date = new Date();
        long now = System.currentTimeMillis();
        for (int i = 1; i <= iteration; i++) {
            String jsonMsg = String.format("{EventDate:'%s', EventTimeInMillis:'%d', EventMsg:'This is test event %d for Java Logging with Logback and Splunk HEC'}", date, now, i);
            logger.info("{}", jsonMsg);
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            logger.error("InterruptedException..", e);
        }
    }
}
