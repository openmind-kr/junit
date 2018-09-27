package kr.openmind.junit.rule

import org.slf4j.LoggerFactory

class Slf4jKotlin {

    companion object {
        private val logger = LoggerFactory.getLogger(this::class.java)
    }

    fun logging(): Boolean {
        logger.info("message")
        return true
    }
}
