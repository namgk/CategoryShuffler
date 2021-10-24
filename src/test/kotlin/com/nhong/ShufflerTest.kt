package com.nhong

import org.junit.jupiter.api.*

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
        // val categories = mapOf("hi" to 25, "cam" to 10, "um" to 5)
        val categories = mapOf("umbracity" to 4, "test" to 2)

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
