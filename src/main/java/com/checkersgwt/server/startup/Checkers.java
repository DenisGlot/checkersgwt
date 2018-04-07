package com.checkersgwt.server.startup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Checkers {
    private static final Logger logger = LoggerFactory.getLogger(Checkers.class);
    private static final String CONTEXT_LOCATION = "/checkers-context.xml";

    @SuppressWarnings("resource")
    public static void main(String[] p_args) throws Exception {
        try {
            AbstractApplicationContext context = new ClassPathXmlApplicationContext(CONTEXT_LOCATION);
            context.start();
            context.registerShutdownHook();
        } catch (BeansException e) {
            logger.error("Scheduling configration error", e);
        }
    }
}

