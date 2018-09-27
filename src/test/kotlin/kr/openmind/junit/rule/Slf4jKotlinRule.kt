package kr.openmind.junit.rule

import org.junit.rules.ExternalResource
import org.slf4j.Logger
import java.lang.reflect.Field
import java.lang.reflect.Modifier

class Slf4jKotlinRule(logClass: Class<*>, logger: Logger): ExternalResource() {

    private var logField: Field = logClass.getDeclaredField("logger")
    var logger: Logger = logger
    private set
            private var originalLogger: Logger? = null

    override fun before() {
        logField!!.isAccessible = true

        val modifiersField = Field::class.java.getDeclaredField("modifiers")
        modifiersField.isAccessible = true
        modifiersField.setInt(logField, logField!!.modifiers and Modifier.FINAL.inv())

        originalLogger = logField!!.get(null) as Logger
        logField!!.set(null, logger)
    }

    override fun after() {
        logField!!.set(null, originalLogger)
    }
}
