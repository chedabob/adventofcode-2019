package com.cbob.intcode

import org.junit.Assert
import org.junit.Test

class CommandTest {
    @Test
    fun test_day2_part1_examples () {
        val test1 = "1,9,10,3,2,3,11,0,99,30,40,50"
        val expected1 = 3500
        val runner = Machine()
        val finalState = runner.run(test1)
        val output = finalState.instructions[0]
        Assert.assertEquals(expected1, output)
    }

}