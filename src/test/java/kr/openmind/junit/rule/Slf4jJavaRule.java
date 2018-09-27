package kr.openmind.junit.rule;

import org.junit.rules.ExternalResource;
import org.slf4j.Logger;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class Slf4jJavaRule extends ExternalResource {

    private Field logField;
    private Logger logger;
    private Logger originalLogger;

    public Slf4jJavaRule(Class logClass, Logger logger) {
        try {
            this.logField = logClass.getDeclaredField("log");
            this.logger = logger;
        } catch (NoSuchFieldException e) {
            // nothing
        }
    }

    @Override
    protected void before() throws Throwable {
        logField.setAccessible(true);

        Field modifiersField = Field.class.getDeclaredField("modifiers");
        modifiersField.setAccessible(true);
        modifiersField.setInt(logField, logField.getModifiers() & ~Modifier.FINAL);

        originalLogger = (Logger) logField.get(null);
        logField.set(null, logger);
    }

    @Override
    protected void after() {
        try {
            logField.set(null, originalLogger);
        } catch (IllegalAccessException e) {
            // nothing
        }
    }

    public Logger getLogger() {
        return logger;
    }
}
