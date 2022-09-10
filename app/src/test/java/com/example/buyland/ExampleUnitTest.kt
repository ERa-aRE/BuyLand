package com.example.buyland

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    fun testList(user: String ,usernames:List<String>): Boolean {
        return user in usernames
        /*if (user in usernames ) {
            true } else { false }*/
    }
}