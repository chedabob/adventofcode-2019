package com.cbob.intcode

import org.junit.Assert
import org.junit.Test
import java.lang.Exception

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

    @Test
    fun test_day7_part1_examples () {
        val program =
            "3,15,3,16,1002,16,10,16,1,16,15,15,4,15,99,0,0"

        val expected = 43210
        val runner = Machine()

        var highestSignal = 0
        try {
            val components = intToComponents(43210)

            var power = 0
            while (components.isNotEmpty()) {
                val phaseSetting = components.removeAt(0)
                val outputState = runner.run(program, inputs = arrayOf(phaseSetting, power))
                power = outputState.output
            }

            highestSignal = power
        } catch (ex: Exception) {
        }

        Assert.assertEquals(expected, highestSignal)
    }

    @Test
    fun test_day7_part1_examples2 () {
        val program =
            "3,23,3,24,1002,24,10,24,1002,23,-1,23,101,5,23,23,1,24,23,23,4,23,99,0,0"

        val expected = 54321
        val runner = Machine()

        var highestSignal = 0
        try {
            val components = intToComponents(1234)

            var power = 0
            while (components.isNotEmpty()) {
                val phaseSetting = components.removeAt(0)
                val outputState = runner.run(program, inputs = arrayOf(phaseSetting, power))
                power = outputState.output
            }

            highestSignal = power
        } catch (ex: Exception) {
        }

        Assert.assertEquals(expected, highestSignal)
    }

    @Test
    fun test_day7_part1_examples3 () {
        val program =
            "3,31,3,32,1002,32,10,32,1001,31,-2,31,1007,31,0,33,1002,33,7,33,1,33,31,31,1,32,31,31,4,31,99,0,0,0"

        val expected = 65210
        val runner = Machine()

        var highestSignal = 0
        try {
            val components = intToComponents(10432)

            var power = 0
            while (components.isNotEmpty()) {
                val phaseSetting = components.removeAt(0)
                val outputState = runner.run(program, inputs = arrayOf(phaseSetting, power))
                power = outputState.output
            }

            highestSignal = power
        } catch (ex: Exception) {
        }

        Assert.assertEquals(expected, highestSignal)
    }

}