package kr.openmind.junit.rule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Slf4jJava {

    private static final Logger log = LoggerFactory.getLogger(Slf4jJava.class);

    public boolean logging() {
        log.info("message");
        return true;
    }
}
