package com.nhong

import org.junit.jupiter.api.Test

class ShufflerTest {
    class Element: Shuffler.Shufflerable{
        var id = "0"
        var provider = "umbracity"
        override fun getBaseProperty(): String {
            return provider
        }

        override fun toString(): String {
            return id
        }
    }

    @Test
    fun testShuffler() {
        val list = mutableListOf<Element>()
        val categories = mapOf("um" to 5, "hi" to 15, "cam" to 10)

        for (cat in categories){
            val category = cat.key
            val quantity = cat.value
            for (i in 0 until quantity){
                val element = Element()
                element.id = "$category-$i"
                element.provider = category
                list.add(element)
            }
        }

        println(list)
        Shuffler.shuffle(list)
        println(list)

    }
}
