package com.cbob.intcode

import Runner
import com.cbob.intcode.commands.AddCommand
import com.cbob.intcode.commands.MultiplyCommand
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
}