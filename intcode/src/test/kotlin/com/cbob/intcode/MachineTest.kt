package com.cbob.intcode

import org.junit.Assert
import org.junit.Test
import java.lang.Exception

class CommandTest {
    @Test
    fun test_day2_part1_examples () {
        val program = "1,9,10,3,2,3,11,0,99,30,40,50"
        val expected1 = 3500L
        val runner = Machine(program)
        val finalState = runner.run()
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

        val runner = Machine(program)
        runner.state.inputs = mutableListOf(input)
        val finalState = runner.run()
        return finalState.output.toInt()
    }

    @Test
    fun test_day7_part1_examples () {
        val program =
            "3,15,3,16,1002,16,10,16,1,16,15,15,4,15,99,0,0"

        val expected = 43210

        var highestSignal = 0
        try {
            val components = intToComponents(43210)

            var power = 0
            while (components.isNotEmpty()) {
                val runner = Machine(program)
                val phaseSetting = components.removeAt(0)
                runner.state.inputs = mutableListOf(phaseSetting, power)
                val outputState = runner.run()
                power = outputState.output.toInt()
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

        var highestSignal = 0
        try {
            val components = intToComponents(1234)

            var power = 0
            while (components.isNotEmpty()) {
                val runner = Machine(program)
                val phaseSetting = components.removeAt(0)
                runner.state.inputs = mutableListOf(phaseSetting, power)
                val outputState = runner.run()
                power = outputState.output.toInt()
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

        var highestSignal = 0
        try {
            val components = intToComponents(10432)

            var power = 0
            while (components.isNotEmpty()) {
                val runner = Machine(program)
                val phaseSetting = components.removeAt(0)
                runner.state.inputs = mutableListOf(phaseSetting, power)
                val outputState = runner.run()
                power = outputState.output.toInt()
            }

            highestSignal = power
        } catch (ex: Exception) {
        }

        Assert.assertEquals(expected, highestSignal)
    }

    @Test
    fun test_day9_part1_examples1 () {
        val program =
            "109,1,204,-1,1001,100,1,100,1008,100,16,101,1006,101,0,99"

        val runner = Machine(program)
        val res = runner.run()

        Assert.assertEquals(program, res.outputs.toString().replace("[","").replace("]","").replace(" ", ""))
    }

    @Test
    fun test_day9_part1_examples2 () {
        val program =
            "1102,34915192,34915192,7,4,7,99,0"

        val runner = Machine(program)
        val res = runner.run()
        Assert.assertEquals(16, res.output.toString().count())

    }

    @Test
    fun test_day9_part1_examples3 () {
        val program =
            "104,1125899906842624,99"

        val runner = Machine(program)
        val res = runner.run()

        Assert.assertEquals(1125899906842624, res.output)

//        Assert.assertEquals(expected, highestSignal)
    }

}