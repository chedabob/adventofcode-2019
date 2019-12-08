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

    @Test
    fun test_day5_part2_examples () {

        Assert.assertEquals(999, test_day5_part2(1))
        Assert.assertEquals(1000, test_day5_part2(8))
        Assert.assertEquals(1001, test_day5_part2(9))
    }

    private fun test_day5_part2 (input : Int) : Int {
        val program = "3,21,1008,21,8,20,1005,20,22,107,8,21,20,1006,20,31,1106,0,36,98,0,0,1002,21,125,20,4,20,1105,1,46,104,999,1105,1,46,1101,1000,1,20,4,20,1105,1,46,98,99"

        val runner = Machine()
        val finalState = runner.run(program, inputs = arrayOf(input))
        return finalState.output
    }

}