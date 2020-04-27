package org.fluttercode.datafactory.impl

import org.fluttercode.datafactory.CimErtekek
import org.fluttercode.datafactory.TartalomErtekek
import org.fluttercode.datafactory.AdatErtekek
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
     * [AdatErtekek] interface which just returns the String arrays to use for the test data.
     *
     * @param nameDataValues Object holding the set of data values to use
     */
    var adatErtekek: AdatErtekek = AlapertelmezettAdatErtekek()
    private var cimErtekek: CimErtekek = AlapertelmezettCimErtekek()
    private var tartalomErtekek: TartalomErtekek = AlapertelmezettTartalomErtekek()
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
     * @param elemek List of items to choose from
     * @return Item from the list
    </T> */
    fun <T> getElem(elemek: List<T>?): T {
        return getElem(elemek, 100, null)
    }

    /**
     * Returns a random item from a list of items or the null depending on the probability parameter. The probability
     * determines the chance (in %) of returning an item off the list versus null.
     *
     * @param <T> Item type in the list and to return
     * @param elemek List of items to choose from
     * @param valoszinuseg chance (in %, 100 being guaranteed) of returning an item from the list
     * @return Item from the list or null if the probability test fails.
    </T> */
    fun <T> getElem(elemek: List<T>?, valoszinuseg: Int): T {
        return getElem(elemek, valoszinuseg, null)
    }

    /**
     * Returns a random item from a list of items or the defaultItem depending on the probability parameter. The
     * probability determines the chance (in %) of returning an item off the list versus the default value.
     *
     * @param <T> Item type in the list and to return
     * @param elemek List of items to choose from
     * @param valoszinuseg chance (in %, 100 being guaranteed) of returning an item from the list
     * @param alapertelmezettElem value to return if the probability test fails
     * @return Item from the list or the default value
    </T> */
    fun <T> getElem(elemek: List<T>?, valoszinuseg: Int, alapertelmezettElem: T?): T {
        requireNotNull(elemek) { "Az Elem lista nem lehet null" }
        require(!elemek.isEmpty()) { "Az Elem lista nem lehet ures" }
        return (if (esely(valoszinuseg)) elemek[random.nextInt(elemek.size)] else alapertelmezettElem) as T
    }

    /**
     * Returns a random item from an array of items
     *
     * @param <T> Array item type and the type to return
     * @param elemek Array of items to choose from
     * @return Item from the array
    </T> */
    fun <T> getElem(elemek: Array<T>?): T {
        return getElem(elemek, 100, null)
    }

    /**
     * Returns a random item from an array of items or null depending on the probability parameter. The probability
     * determines the chance (in %) of returning an item from the array versus null.
     *
     * @param <T> Array item type and the type to return
     * @param elemek Array of items to choose from
     * @param valoszinuseg chance (in %, 100 being guaranteed) of returning an item from the array
     * @return Item from the array or the default value
    </T> */
    fun <T> getElem(elemek: Array<T>?, valoszinuseg: Int): T {
        return getElem(elemek, valoszinuseg, null)
    }

    /**
     * Returns a random item from an array of items or the defaultItem depending on the probability parameter. The
     * probability determines the chance (in %) of returning an item from the array versus the default value.
     *
     * @param <T> Array item type and the type to return
     * @param elemek Array of items to choose from
     * @param valoszinuseg chance (in %, 100 being guaranteed) of returning an item from the array
     * @param alapertelmezettElem value to return if the probability test fails
     * @return Item from the array or the default value
    </T> */
    fun <T> getElem(elemek: Array<T>?, valoszinuseg: Int, alapertelmezettElem: T?): T {
        requireNotNull(elemek) { "Az Elem lista nem lehet null" }
        require(elemek.size != 0) { "Az Elem lista nem lehet ures" }
        return (if (esely(valoszinuseg)) elemek[random.nextInt(elemek.size)] else alapertelmezettElem) as T
    }

    /**
     * @return A random first name
     */
    val keresztNev: String
        get() = getElem(adatErtekek.getKeresztNevek())

    /**
     * Returns a combination of first and last name values in one string
     *
     * @return First and last name value
     */
    val nev: String
        get() = getElem(adatErtekek.getKeresztNevek()) + " " + getElem(adatErtekek.getVezetekNevek())

    /**
     * @return A random last name
     */
    val vezetekNev: String
        get() = getElem(adatErtekek.getVezetekNevek())

    /**
     * @return A random street name
     */
    val utcaNev: String
        get() = getElem(cimErtekek.getUtcanevek())

    /**
     * @return A random street suffix
     */
    val cimVegzodes: String
        get() = getElem(cimErtekek.getCimVegzodesek())

    /**
     * Generates a random city value
     *
     * @return City as a string
     */
    val varos: String
        get() = getElem(cimErtekek.getVarosok())

    /**
     * Generates an address value consisting of house number, street name and street suffix. i.e.
     * `543 Larkhill Road`
     *
     * @return Address as a string
     */
    val cim: String
        get() {
            val num = 404 + random.nextInt(1400)
            return "$num $utcaNev $cimVegzodes"
        }

    /**
     * Generates line 2 for a street address (usually an Apt. or Suite #). Returns null if the probabilty test fails.
     *
     * @param valoszinuseg Chance as % of have a value returned instead of null.
     * @return Street address line two or null if the probability test fails
     */
    fun getCimSor2(valoszinuseg: Int): String {
        return getCimSor2(valoszinuseg, null)
    }

    /**
     * Generates line 2 for a street address (usually an Apt. or Suite #). Returns default value if the probabilty test
     * fails.
     *
     * @param valoszinuseg Chance as % of have a value returned instead of null.
     * @param alapertelmezettErtek Value to return if the probability test fails.
     * @return Street address line two or null if the probability test fails
     */
    fun getCimSor2(valoszinuseg: Int, alapertelmezettErtek: String?): String {
        return (if (esely(valoszinuseg)) cimSor2 else alapertelmezettErtek)!!
    }

    /**
     * Generates line 2 for a street address (usually an Apt. or Suite #). Returns default value if the probabilty test
     * fails.
     *
     * @return Street address line 2
     */
    val cimSor2: String
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
    val szuletesiDatum: Date
        get() {
            val base = Date(0)
            return getDatum(base, -365 * 15, 365 * 15)
        }

    /**
     * Returns a random int value.
     *
     * @return random number
     */
    val szam: Int
        get() = random.nextInt()

    /**
     * Returns a random number between 0 and max
     *
     * @param max Maximum value of result
     * @return random number no more than max
     */
    fun getSzamMaximumig(max: Int): Int {
        return getSzamIntervallum(0, max)
    }

    /**
     * Returns a number betwen min and max
     *
     * @param min minimum value of result
     * @param max maximum value of result
     * @return Random number within range
     */
    fun getSzamIntervallum(min: Int, max: Int): Int {
        require(max >= min) { String.format("Minimumnak kisebbnek kell lennie mint a maximum (min=%d, max=%d)", min, max) }
        return if (max == min) {
            min
        } else min + random.nextInt(max - min)
    }

    /**
     * Builds a date from the year, month, day values passed in
     *
     * @param ev The year of the final [Date] result
     * @param honap The month of the final [Date] result (from 1-12)
     * @param nap The day of the final [Date] result
     * @return Date representing the passed in values.
     */
    fun getDatum(ev: Int, honap: Int, nap: Int): Date {
        val cal = Calendar.getInstance()
        cal.clear()
        cal[ev, honap - 1, nap, 0, 0] = 0
        return cal.time
    }

    /**
     * Returns a random date which is in the range `baseData` + `minDaysFromData` to
     * `baseData` + `maxDaysFromData`. This method does not alter the time component and the time is
     * set to the time value of the base date.
     *
     * @param alapDatum Date to start from
     * @param minNapokDatumtol minimum number of days from the baseDate the result can be
     * @param maxNapokDatumtol maximum number of days from the baseDate the result can be
     * @return A random date
     */
    fun getDatum(alapDatum: Date?, minNapokDatumtol: Int, maxNapokDatumtol: Int): Date {
        val cal = Calendar.getInstance()
        cal.time = alapDatum
        val diff = minNapokDatumtol + random.nextInt(maxNapokDatumtol - minNapokDatumtol)
        cal.add(Calendar.DATE, diff)
        return cal.time
    }

    /**
     * Returns a random date between two dates. This method will alter the time component of the dates
     *
     * @param minDatum Minimum date that can be returned
     * @param maxDatum Maximum date that can be returned
     * @return random date between these two dates.
     */
    fun getDatumIntervallum(minDatum: Date, maxDatum: Date): Date {
        // this can break if seconds is an int
        var mp = (maxDatum.time - minDatum.time) / 1000
        mp = (random.nextDouble() * mp).toLong()
        val eredmeny = Date()
        eredmeny.time = minDatum.time + mp * 1000
        return eredmeny
    }

    /**
     * Returns random text made up of english words of length `length`
     *
     * @param hossz length of returned string
     *
     * @return string made up of actual words with length `length`
     */
    fun getVeletlenSzoveg(hossz: Int): String {
        return getVeletlenSzoveg(hossz, hossz)
    }

    /**
     * Returns random text made up of english words
     *
     * @param minHossz minimum length of returned string
     * @param maxHossz maximum length of returned string
     * @return string of length between min and max length
     */
    fun getVeletlenSzoveg(minHossz: Int, maxHossz: Int): String {
        ellenorizMinMaxParameterek(minHossz, maxHossz)
        val sb = StringBuilder(maxHossz)
        var hossz = minHossz
        if (maxHossz != minHossz) {
            hossz = hossz + random.nextInt(maxHossz - minHossz)
        }
        while (hossz > 0) {
            if (sb.length != 0) {
                sb.append(" ")
                hossz--
            }
            val kivantSzoHosszNormalEloszlasa = 1.0 + Math.abs(random.nextGaussian()) * 6
            val hasznaltSzoHossz = Math.min(hossz.toDouble(), kivantSzoHosszNormalEloszlasa).toInt()
            val szo = getVeletlenSzo(hasznaltSzoHossz)
            sb.append(szo)
            hossz = hossz - szo.length
        }
        return sb.toString()
    }

    private fun ellenorizMinMaxParameterek(minHossz: Int, maxHossz: Int) {
        require(minHossz >= 0) { "A minimum hossznak egy nem-negatív számnak kell lennie" }
        require(maxHossz >= 0) { "A maximum hossznak egy nem-negatív számnak kell lennie" }
        require(maxHossz >= minHossz) { String.format("A minimum hossznak kisebbnek kell lennie mint a maximum hossz (min=%d, max=%d)", minHossz, maxHossz) }
    }

    /**
     * @return a random character
     */
    val veletlenKarakter: Char
        get() = (random.nextInt(26) + 'a'.toInt()).toChar()

    /**
     * Return a string containing `length` random characters
     *
     * @param hossz number of characters to use in the string
     * @return A string containing `length` random characters
     */
    fun getVeletlenKarakterek(hossz: Int): String {
        return getVeletlenKarakterek(hossz, hossz)
    }

    /**
     * Return a string containing between `length` random characters
     *
     * @param minHossz minimum number of characters to use in the string
     * @param maxHossz maximum number of characters to use in the string
     *
     * @return A string containing `length` random characters
     */
    fun getVeletlenKarakterek(minHossz: Int, maxHossz: Int): String {
        ellenorizMinMaxParameterek(minHossz, maxHossz)
        val sb = StringBuilder(maxHossz)
        var hossz = minHossz
        if (maxHossz != minHossz) {
            hossz = hossz + random.nextInt(maxHossz - minHossz)
        }
        while (hossz > 0) {
            sb.append(veletlenKarakter)
            hossz--
        }
        return sb.toString()
    }

    /**
     * Returns a word of a length between 1 and 10 characters.
     *
     * @return A work of max length 10
     */
    val veletlenSzo: String
        get() = getElem(tartalomErtekek.getSzavak())

    /**
     * Returns a valid word with a length of `length` characters.
     *
     * @param hossz maximum length of the word
     * @return a word of a length up to `length` characters
     */
    fun getVeletlenSzo(hossz: Int): String {
        return getVeletlenSzo(hossz, hossz)
    }

    /**
     * Returns a valid word with a length of up to `length` characters. If the `exactLength`
     * parameter is set, then the word will be exactly `length` characters in length.
     *
     * @param hossz maximum length of the returned string
     * @param pontosHossz indicates if the word should have a length of `length`
     * @return a string with a length that matches the specified parameters.
     */
    fun getVeletlenSzo(hossz: Int, pontosHossz: Boolean): String {
        return if (pontosHossz) {
            getVeletlenSzo(hossz, hossz)
        } else getVeletlenSzo(0, hossz)
    }

    /**
     * Returns a valid word based on the length range passed in. The length will always be between the min and max length
     * range inclusive.
     *
     * @param minHossz minimum length of the word
     * @param maxHossz maximum length of the word
     * @return a word of a length between min and max length
     */
    fun getVeletlenSzo(minHossz: Int, maxHossz: Int): String {
        ellenorizMinMaxParameterek(minHossz, maxHossz)

        // special case if we need a single char
        if (maxHossz == 1) {
            return if (esely(50)) {
                "a"
            } else "I"
        }


        // start from random pos and find the first word of the right size
        val szavak = tartalomErtekek.getSzavak()
        val pos = random.nextInt(szavak!!.size)
        for (i in szavak.indices) {
            val idx = (i + pos) % szavak.size
            val test = szavak[idx]
            if (test!!.length >= minHossz && test.length <= maxHossz) {
                return test
            }
        }
        // we haven't a word for this length so generate one
        return getVeletlenKarakterek(minHossz, maxHossz)
    }

    /**
     *
     * @param esely Chance of a suffix being returned
     * @return
     */
    fun getVegzodes(esely: Int): String? {
        return getElem(adatErtekek.getVegzodesek(), esely)
    }

    /**
     * Return a person prefix or null if the odds are too low.
     *
     * @param esely Odds of a prefix being returned
     * @return Prefix string
     */
    fun getElotag(esely: Int): String? {
        return getElem(adatErtekek.getElotagok(), esely)
    }

    /**
     * Returns a string containing a set of numbers with a fixed number of digits
     *
     * @param szamjegyek number of digits in the final number
     * @return Random number as a string with a fixed length
     */
    fun getSzamSorozat(szamjegyek: Int): String {
        var eredmeny = ""
        for (i in 0 until szamjegyek) {
            eredmeny = eredmeny + random.nextInt(10)
        }
        return eredmeny
    }

    /**
     * Generates a random business name by taking a city name and additing a business onto it.
     *
     * @return A random business name
     */
    val uzletNev: String
        get() = varos + " " + getElem(tartalomErtekek.getUzletTipusok())// 2 words// name and initial

    /**
     * Generates an email address
     *
     * @return an email address
     */
    val emailCim: String
        get() {
            val test = random.nextInt(100)
            var email = ""
            email = if (test < 50) {
                // name and initial
                keresztNev[0].toString() + vezetekNev
            } else {
                // 2 words
                getElem(tartalomErtekek.getSzavak()) + getElem(tartalomErtekek.getSzavak())
            }
            if (random.nextInt(100) > 80) {
                email = email + random.nextInt(100)
            }
            email = email + "@" + getElem(tartalomErtekek.getEmailHosztok()) + "." + getElem(tartalomErtekek.getTlds())
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
     * @param esely % chance of returning true
     * @return true or false value based on the random number generated and the chance value passed in
     */
    fun esely(esely: Int): Boolean {
        return random.nextInt(100) < esely
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
     * [CimErtekek] interface which just returns the String arrays to use for the test data.
     *
     * @param cimErtekek Object holding the set of data values to use
     */
    fun setCimErtekek(cimErtekek: CimErtekek) {
        this.cimErtekek = cimErtekek
    }

    /**
     * Set this to provide your own list of content data values by passing it a class that implements the
     * [TartalomErtekek] interface which just returns the String arrays to use for the test data.
     *
     * @param tartalomErtekek Object holding the set of data values to use
     */
    fun setTartalomErtekek(tartalomErtekek: TartalomErtekek) {
        this.tartalomErtekek = tartalomErtekek
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