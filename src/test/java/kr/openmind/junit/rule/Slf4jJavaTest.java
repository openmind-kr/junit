package kr.openmind.junit.rule;

import org.junit.Rule;
import org.junit.Test;
import org.slf4j.Logger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class Slf4jJavaTest {

    @Rule
    public Slf4jJavaRule logRule = new Slf4jJavaRule(Slf4jJava.class, mock(Logger.class));

    @Test
    public void logging() {
        Slf4jJava sut = new Slf4jJava();

        boolean actual = sut.logging();
        Logger logger = logRule.getLogger();

        assertThat(actual).isTrue();
        verify(logger).info("message");
    }
}
