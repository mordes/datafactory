package org.fluttercode.datafactory.impl

import org.fluttercode.datafactory.TartalomErtekek

/*
 * Copyright 2011, Andrew M Gibson
 *
 * www.andygibson.net
 *
 * This file is part of DataFactory
 *
 * DataValve is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * DataFactory is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * 
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with DataFactory.  If not, see <http://www.gnu.org/licenses/>.
 *
 */   class AlapertelmezettTartalomErtekek : TartalomErtekek {
    override fun getSzavak(): Array<String> {
        return Companion.words
    }

    override fun getUzletTipusok(): Array<String> {
        return Companion.businessTypes
    }

    override fun getEmailHosztok(): Array<String> {
        return Companion.emailHosts
    }

    override fun getTlds(): Array<String> {
        return Companion.tlds
    }

    companion object {
        var words = arrayOf("throw", "ball", "hat", "red", "worn",
                "list", "words", "computer", "in", "out", "hot", "cold", "warp",
                "speed", "captain", "assert", "hold", "room", "ship", "lost", "is",
                "television", "show", "about", "plane", "crash", "island",
                "monster", "trees", "banging", "smoke", "where", "are", "we",
                "was", "asked", "no", "rescue", "came", "build", "fire", "waited",
                "days", "moved", "to", "caves", "found", "with", "ghost", "dad",
                "in", "white", "rabbit", "lock", "discovered", "hatch", "with",
                "boon", "secretly", "hid", "it", "while", "trying", "to", "open",
                "it", "until", "sidekick", "died", "as", "sacrifice", "island",
                "demanded", "many", "had", "dreams", "or", "visions", "others",
                "came", "took", "people", "who", "are", "they", "what", "do",
                "they", "want", "light", "came", "on", "through", "window",
                "leader", "is", "a", "good", "man", "numbers", "in", "room",
                "enter", "keys", "computer", "end", "of", "world", "wicket",
                "magnetic", "pull", "shepherd", "always", "wrong", "much",
                "suspense", "what", "to", "do", "when", "it", "ends", "I", "will",
                "have", "to", "find", "something", "else", "to", "pique", "my",
                "interest", "or maybe", "write", "lots", "of", "code", "probably",
                "should", "have", "generated", "this", "text", "automatically",
                "so", "will", "from", "the", "web", "ending", "badly", "library",
                "handled", "books", "constantly", "headphones", "of", "ill", "on",
                "it's", "sill", "sits", "sofa")
        private val businessTypes = arrayOf("Furnishings", "Bakery",
                "Accounting", "Textiles", "Manufacturing", "Industries",
                "Pro Services", "Landscaping", "Realty", "Travel",
                "Medical supplies", "Office supplies", "Insurance", "Software",
                "Motors", "Cafe", "Services", "Gymnasium", "Motor Services",
                "Signs", "Development", "Studios", "Engineering", "Development")
        private val emailHosts = arrayOf("gma1l", "hotma1l", "yah00",
                "somema1l", "everyma1l", "ma1lbox", "b1zmail", "ma1l2u")
        private val tlds = arrayOf("org", "net", "com", "biz", "us", "co.uk")
    }

}