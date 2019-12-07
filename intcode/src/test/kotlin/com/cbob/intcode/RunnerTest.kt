package com.cbob.intcode

import com.cbob.intcode.commands.AddCommand
import com.cbob.intcode.commands.MultiplyCommand
import com.cbob.intcode.commands.TerminateCommand
import org.junit.Assert
import org.junit.Test

class CommandTest {
    @Test
    fun test_Add () {
        val state = State()
        state.instructions = arrayOf(0,1,2,4,3)

        val cmd = AddCommand()
        cmd.execute(state)

        Assert.assertEquals(3, state.instructions[4])
        Assert.assertEquals(4, state.instPtr)
    }

    @Test
    fun test_Multiply () {
        val state = State()
        state.instructions = arrayOf(0,3,4,4,3)

        val cmd = MultiplyCommand()
        cmd.execute(state)

        Assert.assertEquals(12, state.instructions[4])
        Assert.assertEquals(4, state.instPtr)
    }

    @Test
    fun test_Terminate () {
        val state = State()
        state.instructions = arrayOf(0,1,2,3,4,5,6,7)

        val cmd = TerminateCommand()
        cmd.execute(state)

        Assert.assertEquals(8, state.instPtr)
    }

    @Test
    fun test_day2_part1_examples () {
        val test1 = "1,9,10,3,2,3,11,0,99,30,40,50"
        val expected1 = 3500
        val runner = Runner()
        val finalState = runner.run(test1)
        val output = finalState.instructions[0]
        Assert.assertEquals(expected1, output)
    }

    @Test
    fun test_day2_part1 () {
        val input = "1,0,0,3,1,1,2,3,1,3,4,3,1,5,0,3,2,1,9,19,1,13,19,23,2,23,9,27,1,6,27,31,2,10,31,35,1,6,35,39,2,9,39,43,1,5,43,47,2,47,13,51,2,51,10,55,1,55,5,59,1,59,9,63,1,63,9,67,2,6,67,71,1,5,71,75,1,75,6,79,1,6,79,83,1,83,9,87,2,87,10,91,2,91,10,95,1,95,5,99,1,99,13,103,2,103,9,107,1,6,107,111,1,111,5,115,1,115,2,119,1,5,119,0,99,2,0,14,0"
        val expected1 = 2894520
        val runner = Runner()
        val finalState = runner.run(input, true)
        val output = finalState.instructions[0]
        Assert.assertEquals(expected1, output)
    }

}