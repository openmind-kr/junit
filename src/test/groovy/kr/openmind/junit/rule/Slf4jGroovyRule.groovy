package kr.openmind.junit.rule

import org.junit.rules.ExternalResource
import org.slf4j.Logger
import java.lang.reflect.Field
import java.lang.reflect.Modifier

class Slf4jGroovyRule extends ExternalResource {

    private Field logField
    private Logger logger
    private Logger originalLogger

    Slf4jGroovyRule(Class logClass, Logger logger) {
        this.logField = logClass.getDeclaredField("log")
        this.logger = logger
    }

    @Override
    protected void before() throws Throwable {
        logField.accessible = true

        Field modifiersField = Field.getDeclaredField("modifiers")
        modifiersField.accessible = true
        modifiersField.setInt(logField, logField.getModifiers() & ~Modifier.FINAL)

        originalLogger = (Logger) logField.get(null)
        logField.set(null, logger)
    }

    @Override
    protected void after() {
        logField.set(null, originalLogger)
    }
}
