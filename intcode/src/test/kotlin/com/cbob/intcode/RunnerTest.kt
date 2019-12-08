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
        val runner = Machine()
        val finalState = runner.run(test1)
        val output = finalState.instructions[0]
        Assert.assertEquals(expected1, output)
    }

}