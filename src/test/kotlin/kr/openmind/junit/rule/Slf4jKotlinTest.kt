package kr.openmind.junit.rule

import org.assertj.core.api.Assertions.assertThat
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.slf4j.Logger

class Slf4jKotlinTest {

    @get:Rule
    val logRule = Slf4jKotlinRule(Slf4jKotlin::class.java, Mockito.mock(Logger::class.java))

    @Test
    fun logging() {
        val sut = Slf4jKotlin()

        val actual = sut.logging()
        val logger = logRule.logger

        assertThat(actual).isTrue()
        verify(logger).info("message")
    }
}
