package ua.dev.webnauts.modularcleandesign

import org.junit.Test

import org.junit.Assert.*
import kotlin.system.measureTimeMillis

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    /**
     * Всякие нешьяки, которые могут улучшить производительность
     * */

    @Test
    fun addition_isCorrect() {
        /**
         * Прирост 30% с asSequence
         * [https://medium.com/@kamal.lakhani56/assequence-kotlin-3a71bc2f825b]
         * */
        val list = (1..1_000_000).toList()

        val executionTime = measureTimeMillis {
            list.map { it * 2 }.filter { it > 4 }
        }
        val executionTime2 = measureTimeMillis {
            val sequence = list.asSequence()
            sequence.map { it * 2 }.filter { it > 4 }.toList()
        }

        println("test v1 $executionTime test v2 $executionTime2")
    }
}