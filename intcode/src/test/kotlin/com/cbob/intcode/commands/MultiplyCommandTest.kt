package com.cbob.intcode.commands

import com.cbob.intcode.CommandParams
import com.cbob.intcode.State
import org.junit.Assert
import org.junit.Test

class MultiplyCommandTest {
    @Test
    fun test_Multiply () {
        val state = State()
        state.instructions = arrayOf(0,3,4,4,3)

        val cmd = MultiplyCommand()
        cmd.execute(state, CommandParams(2))

        Assert.assertEquals(12, state.instructions[4])
        Assert.assertEquals(4, state.instPtr)
    }

    @Test
    fun test_Multiply_Immediate () {
        val state = State()
        state.instructions = arrayOf(0,3,5,4,3,6,7,8)

        val cmd = MultiplyCommand()
        cmd.execute(state, CommandParams(1112))

        Assert.assertEquals(15, state.instructions[4])
        Assert.assertEquals(4, state.instPtr)
    }
}