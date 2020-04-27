package org.fluttercode.datafactory.impl

import org.fluttercode.datafactory.AddressDataValues
import org.fluttercode.datafactory.ContentDataValues
import org.fluttercode.datafactory.NameDataValues
import java.util.*

/*
 * Copyright 2011, Andrew M Gibson
 *
 * www.andygibson.net
 *
 * This file is part of DataFactory.
 *
 * DataValve is free software: you can redistribute it and/or modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later
 * version.
 *
 * DataValve is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 *
 *
 * You should have received a copy of the GNU Lesser General Public License along with DataValve. If not, see
 * <http://www.gnu.org/licenses/>.
 *
 */
/**
 * Class that provides a number of methods for generating test data through helper components. These components
 * implement interfaces that provide an interface to accessing the test data. Components can be replaced with other
 * components to allow more suitable data to be used.
 *
 * @author Andy Gibson
 */
/**
 * @author GIBSOA01
 */
class DataFactory private constructor(random: Random) {
    /**
     * Set this to provide your own list of name data values by passing it a class that implements the
     * [NameDataValues] interface which just returns the String arrays to use for the test data.
     *
     * @param nameDataValues Object holding the set of data values to use
     */
    var nameDataValues: NameDataValues = DefaultNameDataValues()
    private var addressDataValues: AddressDataValues = DefaultAddressDataValues()
    private var contentDataValues: ContentDataValues = DefaultContentDataValues()
    private var random = Random()

    /**
     * Deprecated as of Aug 2015, use one of the static constructor methods:
     *
     * <pre>
     * dataFactory = DataFactory create();
     * or
     * dataFactory = DataFactory create(5765297);
     * or
     * dataFactory = DataFactory create(someRandom);
    </pre> *
     *
     *
     * If you want to use the same static fixed seed Random object that was implemented originally, you can just create
     * the factories using :
     *
     * <pre>
     * DataFactory createWithOriginalRandom();
    </pre> *
     *
     * Using this constructor will also create a datafactory that uses the same static fixed seed Random
     */
    @Deprecated("")
    constructor() : this(original_random) {
    }

    /**
     * Returns a random item from a list of items.
     *
     * @param <T> Item type in the list and to return
     * @param items List of items to choose from
     * @return Item from the list
    </T> */
    fun <T> getItem(items: List<T>?): T {
        return getItem(items, 100, null)
    }

    /**
     * Returns a random item from a list of items or the null depending on the probability parameter. The probability
     * determines the chance (in %) of returning an item off the list versus null.
     *
     * @param <T> Item type in the list and to return
     * @param items List of items to choose from
     * @param probability chance (in %, 100 being guaranteed) of returning an item from the list
     * @return Item from the list or null if the probability test fails.
    </T> */
    fun <T> getItem(items: List<T>?, probability: Int): T {
        return getItem(items, probability, null)
    }

    /**
     * Returns a random item from a list of items or the defaultItem depending on the probability parameter. The
     * probability determines the chance (in %) of returning an item off the list versus the default value.
     *
     * @param <T> Item type in the list and to return
     * @param items List of items to choose from
     * @param probability chance (in %, 100 being guaranteed) of returning an item from the list
     * @param defaultItem value to return if the probability test fails
     * @return Item from the list or the default value
    </T> */
    fun <T> getItem(items: List<T>?, probability: Int, defaultItem: T?): T {
        requireNotNull(items) { "Item list cannot be null" }
        require(!items.isEmpty()) { "Item list cannot be empty" }
        return (if (chance(probability)) items[random.nextInt(items.size)] else defaultItem) as T
    }

    /**
     * Returns a random item from an array of items
     *
     * @param <T> Array item type and the type to return
     * @param items Array of items to choose from
     * @return Item from the array
    </T> */
    fun <T> getItem(items: Array<T>?): T {
        return getItem(items, 100, null)
    }

    /**
     * Returns a random item from an array of items or null depending on the probability parameter. The probability
     * determines the chance (in %) of returning an item from the array versus null.
     *
     * @param <T> Array item type and the type to return
     * @param items Array of items to choose from
     * @param probability chance (in %, 100 being guaranteed) of returning an item from the array
     * @return Item from the array or the default value
    </T> */
    fun <T> getItem(items: Array<T>?, probability: Int): T {
        return getItem(items, probability, null)
    }

    /**
     * Returns a random item from an array of items or the defaultItem depending on the probability parameter. The
     * probability determines the chance (in %) of returning an item from the array versus the default value.
     *
     * @param <T> Array item type and the type to return
     * @param items Array of items to choose from
     * @param probability chance (in %, 100 being guaranteed) of returning an item from the array
     * @param defaultItem value to return if the probability test fails
     * @return Item from the array or the default value
    </T> */
    fun <T> getItem(items: Array<T>?, probability: Int, defaultItem: T?): T {
        requireNotNull(items) { "Item array cannot be null" }
        require(items.size != 0) { "Item array cannot be empty" }
        return (if (chance(probability)) items[random.nextInt(items.size)] else defaultItem) as T
    }

    /**
     * @return A random first name
     */
    val firstName: String
        get() = getItem(nameDataValues.getFirstNames())

    /**
     * Returns a combination of first and last name values in one string
     *
     * @return First and last name value
     */
    val name: String
        get() = getItem(nameDataValues.getFirstNames()) + " " + getItem(nameDataValues.getLastNames())

    /**
     * @return A random last name
     */
    val lastName: String
        get() = getItem(nameDataValues.getLastNames())

    /**
     * @return A random street name
     */
    val streetName: String
        get() = getItem(addressDataValues.getStreetNames())

    /**
     * @return A random street suffix
     */
    val streetSuffix: String
        get() = getItem(addressDataValues.getAddressSuffixes())

    /**
     * Generates a random city value
     *
     * @return City as a string
     */
    val city: String
        get() = getItem(addressDataValues.getCities())

    /**
     * Generates an address value consisting of house number, street name and street suffix. i.e.
     * `543 Larkhill Road`
     *
     * @return Address as a string
     */
    val address: String
        get() {
            val num = 404 + random.nextInt(1400)
            return "$num $streetName $streetSuffix"
        }

    /**
     * Generates line 2 for a street address (usually an Apt. or Suite #). Returns null if the probabilty test fails.
     *
     * @param probability Chance as % of have a value returned instead of null.
     * @return Street address line two or null if the probability test fails
     */
    fun getAddressLine2(probability: Int): String {
        return getAddressLine2(probability, null)
    }

    /**
     * Generates line 2 for a street address (usually an Apt. or Suite #). Returns default value if the probabilty test
     * fails.
     *
     * @param probability Chance as % of have a value returned instead of null.
     * @param defaultValue Value to return if the probability test fails.
     * @return Street address line two or null if the probability test fails
     */
    fun getAddressLine2(probability: Int, defaultValue: String?): String {
        return (if (chance(probability)) addressLine2 else defaultValue)!!
    }

    /**
     * Generates line 2 for a street address (usually an Apt. or Suite #). Returns default value if the probabilty test
     * fails.
     *
     * @return Street address line 2
     */
    val addressLine2: String
        get() {
            val test = random.nextInt(100)
            return if (test < 50) {
                "Apt #" + 100 + random.nextInt(1000)
            } else {
                "Suite #" + 100 + random.nextInt(1000)
            }
        }

    /**
     * Creates a random birthdate within the range of 1955 to 1985
     *
     * @return Date representing a birthdate
     */
    val birthDate: Date
        get() {
            val base = Date(0)
            return getDate(base, -365 * 15, 365 * 15)
        }

    /**
     * Returns a random int value.
     *
     * @return random number
     */
    val number: Int
        get() = random.nextInt()

    /**
     * Returns a random number between 0 and max
     *
     * @param max Maximum value of result
     * @return random number no more than max
     */
    fun getNumberUpTo(max: Int): Int {
        return getNumberBetween(0, max)
    }

    /**
     * Returns a number betwen min and max
     *
     * @param min minimum value of result
     * @param max maximum value of result
     * @return Random number within range
     */
    fun getNumberBetween(min: Int, max: Int): Int {
        require(max >= min) { String.format("Minimum must be less than minimum (min=%d, max=%d)", min, max) }
        return if (max == min) {
            min
        } else min + random.nextInt(max - min)
    }

    /**
     * Builds a date from the year, month, day values passed in
     *
     * @param year The year of the final [Date] result
     * @param month The month of the final [Date] result (from 1-12)
     * @param day The day of the final [Date] result
     * @return Date representing the passed in values.
     */
    fun getDate(year: Int, month: Int, day: Int): Date {
        val cal = Calendar.getInstance()
        cal.clear()
        cal[year, month - 1, day, 0, 0] = 0
        return cal.time
    }

    /**
     * Returns a random date which is in the range `baseData` + `minDaysFromData` to
     * `baseData` + `maxDaysFromData`. This method does not alter the time component and the time is
     * set to the time value of the base date.
     *
     * @param baseDate Date to start from
     * @param minDaysFromDate minimum number of days from the baseDate the result can be
     * @param maxDaysFromDate maximum number of days from the baseDate the result can be
     * @return A random date
     */
    fun getDate(baseDate: Date?, minDaysFromDate: Int, maxDaysFromDate: Int): Date {
        val cal = Calendar.getInstance()
        cal.time = baseDate
        val diff = minDaysFromDate + random.nextInt(maxDaysFromDate - minDaysFromDate)
        cal.add(Calendar.DATE, diff)
        return cal.time
    }

    /**
     * Returns a random date between two dates. This method will alter the time component of the dates
     *
     * @param minDate Minimum date that can be returned
     * @param maxDate Maximum date that can be returned
     * @return random date between these two dates.
     */
    fun getDateBetween(minDate: Date, maxDate: Date): Date {
        // this can break if seconds is an int
        var seconds = (maxDate.time - minDate.time) / 1000
        seconds = (random.nextDouble() * seconds).toLong()
        val result = Date()
        result.time = minDate.time + seconds * 1000
        return result
    }

    /**
     * Returns random text made up of english words of length `length`
     *
     * @param length length of returned string
     *
     * @return string made up of actual words with length `length`
     */
    fun getRandomText(length: Int): String {
        return getRandomText(length, length)
    }

    /**
     * Returns random text made up of english words
     *
     * @param minLength minimum length of returned string
     * @param maxLength maximum length of returned string
     * @return string of length between min and max length
     */
    fun getRandomText(minLength: Int, maxLength: Int): String {
        validateMinMaxParams(minLength, maxLength)
        val sb = StringBuilder(maxLength)
        var length = minLength
        if (maxLength != minLength) {
            length = length + random.nextInt(maxLength - minLength)
        }
        while (length > 0) {
            if (sb.length != 0) {
                sb.append(" ")
                length--
            }
            val desiredWordLengthNormalDistributed = 1.0 + Math.abs(random.nextGaussian()) * 6
            val usedWordLength = Math.min(length.toDouble(), desiredWordLengthNormalDistributed).toInt()
            val word = getRandomWord(usedWordLength)
            sb.append(word)
            length = length - word.length
        }
        return sb.toString()
    }

    private fun validateMinMaxParams(minLength: Int, maxLength: Int) {
        require(minLength >= 0) { "Minimum length must be a non-negative number" }
        require(maxLength >= 0) { "Maximum length must be a non-negative number" }
        require(maxLength >= minLength) { String.format("Minimum length must be less than maximum length (min=%d, max=%d)", minLength, maxLength) }
    }

    /**
     * @return a random character
     */
    val randomChar: Char
        get() = (random.nextInt(26) + 'a'.toInt()).toChar()

    /**
     * Return a string containing `length` random characters
     *
     * @param length number of characters to use in the string
     * @return A string containing `length` random characters
     */
    fun getRandomChars(length: Int): String {
        return getRandomChars(length, length)
    }

    /**
     * Return a string containing between `length` random characters
     *
     * @param minLength minimum number of characters to use in the string
     * @param maxLength maximum number of characters to use in the string
     *
     * @return A string containing `length` random characters
     */
    fun getRandomChars(minLength: Int, maxLength: Int): String {
        validateMinMaxParams(minLength, maxLength)
        val sb = StringBuilder(maxLength)
        var length = minLength
        if (maxLength != minLength) {
            length = length + random.nextInt(maxLength - minLength)
        }
        while (length > 0) {
            sb.append(randomChar)
            length--
        }
        return sb.toString()
    }

    /**
     * Returns a word of a length between 1 and 10 characters.
     *
     * @return A work of max length 10
     */
    val randomWord: String
        get() = getItem(contentDataValues.getWords())

    /**
     * Returns a valid word with a length of `length` characters.
     *
     * @param length maximum length of the word
     * @return a word of a length up to `length` characters
     */
    fun getRandomWord(length: Int): String {
        return getRandomWord(length, length)
    }

    /**
     * Returns a valid word with a length of up to `length` characters. If the `exactLength`
     * parameter is set, then the word will be exactly `length` characters in length.
     *
     * @param length maximum length of the returned string
     * @param exactLength indicates if the word should have a length of `length`
     * @return a string with a length that matches the specified parameters.
     */
    fun getRandomWord(length: Int, exactLength: Boolean): String {
        return if (exactLength) {
            getRandomWord(length, length)
        } else getRandomWord(0, length)
    }

    /**
     * Returns a valid word based on the length range passed in. The length will always be between the min and max length
     * range inclusive.
     *
     * @param minLength minimum length of the word
     * @param maxLength maximum length of the word
     * @return a word of a length between min and max length
     */
    fun getRandomWord(minLength: Int, maxLength: Int): String {
        validateMinMaxParams(minLength, maxLength)

        // special case if we need a single char
        if (maxLength == 1) {
            return if (chance(50)) {
                "a"
            } else "I"
        }


        // start from random pos and find the first word of the right size
        val words = contentDataValues.getWords()
        val pos = random.nextInt(words!!.size)
        for (i in words.indices) {
            val idx = (i + pos) % words.size
            val test = words[idx]
            if (test!!.length >= minLength && test.length <= maxLength) {
                return test
            }
        }
        // we haven't a word for this length so generate one
        return getRandomChars(minLength, maxLength)
    }

    /**
     *
     * @param chance Chance of a suffix being returned
     * @return
     */
    fun getSuffix(chance: Int): String? {
        return getItem(nameDataValues.getSuffixes(), chance)
    }

    /**
     * Return a person prefix or null if the odds are too low.
     *
     * @param chance Odds of a prefix being returned
     * @return Prefix string
     */
    fun getPrefix(chance: Int): String? {
        return getItem(nameDataValues.getPrefixes(), chance)
    }

    /**
     * Returns a string containing a set of numbers with a fixed number of digits
     *
     * @param digits number of digits in the final number
     * @return Random number as a string with a fixed length
     */
    fun getNumberText(digits: Int): String {
        var result = ""
        for (i in 0 until digits) {
            result = result + random.nextInt(10)
        }
        return result
    }

    /**
     * Generates a random business name by taking a city name and additing a business onto it.
     *
     * @return A random business name
     */
    val businessName: String
        get() = city + " " + getItem(contentDataValues.getBusinessTypes())// 2 words// name and initial

    /**
     * Generates an email address
     *
     * @return an email address
     */
    val emailAddress: String
        get() {
            val test = random.nextInt(100)
            var email = ""
            email = if (test < 50) {
                // name and initial
                firstName[0].toString() + lastName
            } else {
                // 2 words
                getItem(contentDataValues.getWords()) + getItem(contentDataValues.getWords())
            }
            if (random.nextInt(100) > 80) {
                email = email + random.nextInt(100)
            }
            email = email + "@" + getItem(contentDataValues.getEmailHosts()) + "." + getItem(contentDataValues.getTlds())
            return email.toLowerCase()
        }

    /**
     * Gives you a true/false based on a probability with a random number generator. Can be used to optionally add
     * elements.
     *
     * <pre>
     * if (DataFactory.chance(70)) {
     * // 70% chance of this code being executed
     * }
    </pre> *
     *
     * @param chance % chance of returning true
     * @return true or false value based on the random number generated and the chance value passed in
     */
    fun chance(chance: Int): Boolean {
        return random.nextInt(100) < chance
    }

    /**
     * Call randomize with a seed value to reset the random number generator. By using the same seed over different tests,
     * you will should get the same results out for the same data generation calls.
     *
     * @param seed Seed value to use to generate random numbers
     */
    fun randomize(seed: Int) {
        random = Random(seed.toLong())
    }

    /**
     * Set this to provide your own list of address data values by passing it a class that implements the
     * [AddressDataValues] interface which just returns the String arrays to use for the test data.
     *
     * @param addressDataValues Object holding the set of data values to use
     */
    fun setAddressDataValues(addressDataValues: AddressDataValues) {
        this.addressDataValues = addressDataValues
    }

    /**
     * Set this to provide your own list of content data values by passing it a class that implements the
     * [ContentDataValues] interface which just returns the String arrays to use for the test data.
     *
     * @param contentDataValues Object holding the set of data values to use
     */
    fun setContentDataValues(contentDataValues: ContentDataValues) {
        this.contentDataValues = contentDataValues
    }

    companion object {
        // used for backwards compatibility
        private val original_random = Random(93285)
        fun create(): DataFactory {
            return DataFactory()
        }

        fun create(seed: Long): DataFactory {
            return DataFactory(Random(seed))
        }

        /**
         * Backwards compatible constructor that creates a datafactory driven by the original instance of random.
         *
         * @return DataFactory instance with a shared random
         */
        fun createWithOriginalRandom(): DataFactory {
            return DataFactory(original_random)
        }
    }

    init {
        this.random = random
    }
}