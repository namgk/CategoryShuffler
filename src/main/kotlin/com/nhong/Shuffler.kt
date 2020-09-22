package com.nhong
import java.util.*
import kotlin.math.ceil

class Shuffler {
    interface Shufflerable {
        fun getBaseProperty(): String
    }

    companion object {
        fun <T: Shufflerable> shuffle(list: MutableList<T>): List<T> {
            val listLength = list.size
            val settledPositions = mutableListOf<Int>()

            val categoryMap = mutableMapOf<String, MutableList<T>>()

            list.forEach {
                if (categoryMap[it.getBaseProperty()].isNullOrEmpty()) {
                    categoryMap[it.getBaseProperty()] = mutableListOf()
                }

                categoryMap[it.getBaseProperty()]!!.add(it)
            }

            val sortedCategorymap = categoryMap.toSortedMap(compareBy { categoryMap[it]!!.size })

            sortedCategorymap.forEach {
                val count = it.value.size
                val spacing = ceil(list.size.toDouble() / count.toDouble()).toInt()

                // spreading it.value to list
                for (i in 0 until count) {
                    var nextPosition = i * spacing
                    if (nextPosition >= listLength || settledPositions.contains(nextPosition)) {
                        // no more proper places to put, find any space left, starting from this nextPosition
                        for (j in 0..listLength) {
                            nextPosition = (nextPosition + 1) % listLength
                            if (!settledPositions.contains(nextPosition)) {
                                break
                            }
                        }
                    }

                    list[nextPosition] = it.value[i]
                    settledPositions.add(nextPosition)
                }
            }

            return list
        }
    }
}
