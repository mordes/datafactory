package org.fluttercode.datafactory.impl

import org.junit.Assert
import org.junit.Before
import org.junit.Test

class DataFactoryTextTest {
    private var dataFactory: DataFactory? = null
    private val ITERATION_COUNT = 100000

    @Before
    fun initTest() {
        dataFactory = DataFactory.create()
        dataFactory!!.randomize(73438)
    }

    @Test
    fun shouldReturnRandomWordsOfVariedLength() {
        for (i in 0 until ITERATION_COUNT) {
            val maxLength = dataFactory!!.getSzamMaximumig(12)
            val word = dataFactory!!.getVeletlenSzo(maxLength, false)
            Assert.assertTrue("Rossz meretu szo", word.length <= maxLength)
        }
    }

    @Test
    fun shouldReturnRandomWordsOfSpecificLength() {
        for (i in 0 until ITERATION_COUNT) {
            val maxLength = dataFactory!!.getSzamMaximumig(12)
            val word = dataFactory!!.getVeletlenSzo(maxLength, true)
            Assert.assertTrue("Rossz meretu szo", word.length == maxLength)
        }
    }

    @Test
    fun shouldReturnRandomWordsOfSpecificLength2() {
        for (i in 0 until ITERATION_COUNT) {
            val maxLength = dataFactory!!.getSzamMaximumig(12)
            val word = dataFactory!!.getVeletlenSzo(maxLength, true)
            Assert.assertTrue("Rossz meretu szo", word.length == maxLength)
        }
    }

    @Test
    fun shouldReturnTextOfSpecificLength() {
        for (i in 0 until ITERATION_COUNT) {
            val len = dataFactory!!.getSzamMaximumig(40)
            val text = dataFactory!!.getVeletlenSzoveg(len)
            Assert.assertNotNull(text)
            Assert.assertTrue(String.format(
                    "Hossz nem egyezik (%d, elvart hossz %d) '%s' ",
                    text.length, len, text), len == text.length)
        }
    }

    @Test
    fun shouldReturnTextWithWords() {
        for (i in 0 until ITERATION_COUNT) {
            val len = 512 + dataFactory!!.getSzamMaximumig(128)
            val text = dataFactory!!.getVeletlenSzoveg(len)
            Assert.assertTrue(String.format(
                    "Hossz nem egyezik (%d, elvárt hossz %d) '%s' ",
                    text.length, len, text), len == text.length)
            val words = text.split(" ").toTypedArray()
            Assert.assertTrue("hosszu szovegeknek celszeru szokozt tartalmaznia", words.size > 32)
            Assert.assertFalse("szovegnek nem celszeru kettos szokozt tartalmaznia", text.contains("  "))
        }
    }

    @Test
    fun shouldReturnTextWithinBoundedLengths() {
        for (i in 0 until ITERATION_COUNT) {
            val minLen = 10 + dataFactory!!.getSzamMaximumig(20)
            val maxLen = minLen + dataFactory!!.getSzamMaximumig(10)
            val text = dataFactory!!.getVeletlenSzoveg(minLen, maxLen)
            Assert.assertNotNull(text)
            var msg = String.format("Hossz (%d) kisebb mint az elvart minimum (%d) az iteraciohoz %d - szoveg = '%s'",
                    text.length, minLen, i, text)
            Assert.assertTrue(msg, minLen <= text.length)
            msg = String.format("Hossz (%d) nagyobb mint az elvart maximum (%d) az iteraciohoz %d - text = %s",
                    text.length, maxLen, i, text)
            Assert.assertTrue(msg, maxLen >= text.length)
        }
    }

    @Test
    fun shouldReturnRandomWordsUpToLength() {
        for (i in 0 until ITERATION_COUNT) {
            val maxLength = dataFactory!!.getSzamMaximumig(30)
            val word = dataFactory!!.getVeletlenSzo(maxLength, false)
            Assert.assertTrue("Rossz meretu szo", word.length <= maxLength)
        }
    }

    @Test
    fun shouldReturnRandomNumber() {
        dataFactory!!.szam
    }

    @Test
    fun shouldReturnNegativeNumber() {
        val random = dataFactory!!.getSzamIntervallum(Int.MIN_VALUE, -1)
        Assert.assertTrue(random < 0)
    }

    @Test
    fun shouldReturnMinValue() {
        val random = dataFactory!!.getSzamIntervallum(Int.MIN_VALUE, Int.MIN_VALUE)
        Assert.assertEquals(Int.MIN_VALUE.toLong(), random.toLong())
    }

    @Test
    fun shouldReturnMaxValue() {
        val random = dataFactory!!.getSzamIntervallum(Int.MAX_VALUE, Int.MAX_VALUE)
        Assert.assertEquals(Int.MAX_VALUE.toLong(), random.toLong())
    }

    //Test param checking on randomWord()
    @Test(expected = IllegalArgumentException::class)
    fun shouldErrorOnNegativeLengthForRandomWord() {
        dataFactory!!.getVeletlenSzo(-1)
    }

    @Test(expected = IllegalArgumentException::class)
    fun shouldErrorOnNegativeMinLenForRandomWord() {
        dataFactory!!.getVeletlenSzo(-1, 10)
    }

    @Test(expected = IllegalArgumentException::class)
    fun shouldErrorOnNegativeMaxLenForRandomWord() {
        dataFactory!!.getVeletlenSzo(0, -10)
    }

    @Test(expected = IllegalArgumentException::class)
    fun shouldErrorOnInvalidSizeLenForRandomWord() {
        dataFactory!!.getVeletlenSzo(10, 2)
    }

    //Test param checking on randomText()
    @Test(expected = IllegalArgumentException::class)
    fun shouldErrorOnNegativeLengthForRandomText() {
        dataFactory!!.getVeletlenSzoveg(-1)
    }

    @Test(expected = IllegalArgumentException::class)
    fun shouldErrorOnNegativeMinLenForRandomText() {
        dataFactory!!.getVeletlenSzoveg(-1, 10)
    }

    @Test(expected = IllegalArgumentException::class)
    fun shouldErrorOnNegativeMaxLenForRandomText() {
        dataFactory!!.getVeletlenSzoveg(0, -10)
    }

    @Test(expected = IllegalArgumentException::class)
    fun shouldErrorOnInvalidSizeLenForRandomText() {
        dataFactory!!.getVeletlenSzoveg(10, 2)
    }

    //Test param checking on randomChars()
    @Test(expected = IllegalArgumentException::class)
    fun shouldErrorOnNegativeLengthForRandomChars() {
        dataFactory!!.getVeletlenKarakterek(-1)
    }

    @Test(expected = IllegalArgumentException::class)
    fun shouldErrorOnNegativeMinLenForRandomChars() {
        dataFactory!!.getVeletlenKarakterek(-1, 10)
    }

    @Test(expected = IllegalArgumentException::class)
    fun shouldErrorOnNegativeMaxLenForRandomChars() {
        dataFactory!!.getVeletlenKarakterek(0, -10)
    }

    @Test(expected = IllegalArgumentException::class)
    fun shouldErrorOnInvalidSizeLenForRandomChars() {
        dataFactory!!.getVeletlenKarakterek(10, 2)
    }
}