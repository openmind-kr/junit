package kr.openmind.junit.rule

import org.junit.Rule
import org.slf4j.Logger
import spock.lang.Specification
import spock.lang.Subject

class Slf4jGroovyTest extends Specification {

    @Subject
    Slf4jJava sut = new Slf4jJava()

    @Rule
    Slf4jGroovyRule logRule = new Slf4jGroovyRule(Slf4jJava, Mock(Logger))

    def logging() {
        when:
        boolean actual = sut.logging()

        then:
        actual == true
        1 * sut.log.info("message")
    }
}
