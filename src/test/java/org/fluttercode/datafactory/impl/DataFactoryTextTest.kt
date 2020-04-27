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
            val maxLength = dataFactory!!.getNumberUpTo(12)
            val word = dataFactory!!.getRandomWord(maxLength, false)
            Assert.assertTrue("Wrong size word", word.length <= maxLength)
        }
    }

    @Test
    fun shouldReturnRandomWordsOfSpecificLength() {
        for (i in 0 until ITERATION_COUNT) {
            val maxLength = dataFactory!!.getNumberUpTo(12)
            val word = dataFactory!!.getRandomWord(maxLength, true)
            Assert.assertTrue("Wrong size word", word.length == maxLength)
        }
    }

    @Test
    fun shouldReturnRandomWordsOfSpecificLength2() {
        for (i in 0 until ITERATION_COUNT) {
            val maxLength = dataFactory!!.getNumberUpTo(12)
            val word = dataFactory!!.getRandomWord(maxLength, true)
            Assert.assertTrue("Wrong size word", word.length == maxLength)
        }
    }

    @Test
    fun shouldReturnTextOfSpecificLength() {
        for (i in 0 until ITERATION_COUNT) {
            val len = dataFactory!!.getNumberUpTo(40)
            val text = dataFactory!!.getRandomText(len)
            Assert.assertNotNull(text)
            Assert.assertTrue(String.format(
                    "Length does not match (%d, expected %d) '%s' ",
                    text.length, len, text), len == text.length)
        }
    }

    @Test
    fun shouldReturnTextWithWords() {
        for (i in 0 until ITERATION_COUNT) {
            val len = 512 + dataFactory!!.getNumberUpTo(128)
            val text = dataFactory!!.getRandomText(len)
            Assert.assertTrue(String.format(
                    "Length does not match (%d, expected %d) '%s' ",
                    text.length, len, text), len == text.length)
            val words = text.split(" ").toTypedArray()
            Assert.assertTrue("long texts should contain spaces", words.size > 32)
            Assert.assertFalse("text should not contain double spaces", text.contains("  "))
        }
    }

    @Test
    fun shouldReturnTextWithinBoundedLengths() {
        for (i in 0 until ITERATION_COUNT) {
            val minLen = 10 + dataFactory!!.getNumberUpTo(20)
            val maxLen = minLen + dataFactory!!.getNumberUpTo(10)
            val text = dataFactory!!.getRandomText(minLen, maxLen)
            Assert.assertNotNull(text)
            var msg = String.format("Length (%d) is less than expected minimum (%d) for iteration %d - text = '%s'",
                    text.length, minLen, i, text)
            Assert.assertTrue(msg, minLen <= text.length)
            msg = String.format("Length (%d) is more than expected (%d) for iteration %d - text = %s",
                    text.length, maxLen, i, text)
            Assert.assertTrue(msg, maxLen >= text.length)
        }
    }

    @Test
    fun shouldReturnRandomWordsUpToLength() {
        for (i in 0 until ITERATION_COUNT) {
            val maxLength = dataFactory!!.getNumberUpTo(30)
            val word = dataFactory!!.getRandomWord(maxLength, false)
            Assert.assertTrue("Wrong size word", word.length <= maxLength)
        }
    }

    @Test
    fun shouldReturnRandomNumber() {
        dataFactory!!.number
    }

    @Test
    fun shouldReturnNegativeNumber() {
        val random = dataFactory!!.getNumberBetween(Int.MIN_VALUE, -1)
        Assert.assertTrue(random < 0)
    }

    @Test
    fun shouldReturnMinValue() {
        val random = dataFactory!!.getNumberBetween(Int.MIN_VALUE, Int.MIN_VALUE)
        Assert.assertEquals(Int.MIN_VALUE.toLong(), random.toLong())
    }

    @Test
    fun shouldReturnMaxValue() {
        val random = dataFactory!!.getNumberBetween(Int.MAX_VALUE, Int.MAX_VALUE)
        Assert.assertEquals(Int.MAX_VALUE.toLong(), random.toLong())
    }

    //Test param checking on randomWord()
    @Test(expected = IllegalArgumentException::class)
    fun shouldErrorOnNegativeLengthForRandomWord() {
        dataFactory!!.getRandomWord(-1)
    }

    @Test(expected = IllegalArgumentException::class)
    fun shouldErrorOnNegativeMinLenForRandomWord() {
        dataFactory!!.getRandomWord(-1, 10)
    }

    @Test(expected = IllegalArgumentException::class)
    fun shouldErrorOnNegativeMaxLenForRandomWord() {
        dataFactory!!.getRandomWord(0, -10)
    }

    @Test(expected = IllegalArgumentException::class)
    fun shouldErrorOnInvalidSizeLenForRandomWord() {
        dataFactory!!.getRandomWord(10, 2)
    }

    //Test param checking on randomText()
    @Test(expected = IllegalArgumentException::class)
    fun shouldErrorOnNegativeLengthForRandomText() {
        dataFactory!!.getRandomText(-1)
    }

    @Test(expected = IllegalArgumentException::class)
    fun shouldErrorOnNegativeMinLenForRandomText() {
        dataFactory!!.getRandomText(-1, 10)
    }

    @Test(expected = IllegalArgumentException::class)
    fun shouldErrorOnNegativeMaxLenForRandomText() {
        dataFactory!!.getRandomText(0, -10)
    }

    @Test(expected = IllegalArgumentException::class)
    fun shouldErrorOnInvalidSizeLenForRandomText() {
        dataFactory!!.getRandomText(10, 2)
    }

    //Test param checking on randomChars()
    @Test(expected = IllegalArgumentException::class)
    fun shouldErrorOnNegativeLengthForRandomChars() {
        dataFactory!!.getRandomChars(-1)
    }

    @Test(expected = IllegalArgumentException::class)
    fun shouldErrorOnNegativeMinLenForRandomChars() {
        dataFactory!!.getRandomChars(-1, 10)
    }

    @Test(expected = IllegalArgumentException::class)
    fun shouldErrorOnNegativeMaxLenForRandomChars() {
        dataFactory!!.getRandomChars(0, -10)
    }

    @Test(expected = IllegalArgumentException::class)
    fun shouldErrorOnInvalidSizeLenForRandomChars() {
        dataFactory!!.getRandomChars(10, 2)
    }
}