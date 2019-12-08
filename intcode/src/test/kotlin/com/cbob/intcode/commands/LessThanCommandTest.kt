package com.cbob.intcode.commands

import com.cbob.intcode.CommandParams
import com.cbob.intcode.State
import org.junit.Assert
import org.junit.Test

class LessThanCommandTest {
    @Test
    fun test_LessThan () {
        val state = State()
        state.instructions = arrayOf(7,3,5,4,3,6,7,8)

        val cmd = LessThanCommand()
        cmd.execute(state, CommandParams(7))

        Assert.assertEquals(1, state.instructions[4])
        Assert.assertEquals(4, state.instPtr)
    }

    @Test
    fun test_LessThan_Immediate () {
        val state = State()
        state.instructions = arrayOf(7,3,5,4,3,6,7,8)

        val cmd = LessThanCommand()
        cmd.execute(state, CommandParams(1107))

        Assert.assertEquals(1, state.instructions[4])
        Assert.assertEquals(4, state.instPtr)
    }

    @Test
    fun test_LessThan_Fail () {
        val state = State()
        state.instructions = arrayOf(7,3,5,4,3,3,7,8)

        val cmd = LessThanCommand()
        cmd.execute(state, CommandParams(7))

        Assert.assertEquals(0, state.instructions[4])
        Assert.assertEquals(4, state.instPtr)
    }
}