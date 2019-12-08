package com.cbob.intcode.commands

import com.cbob.intcode.CommandParams
import com.cbob.intcode.State
import org.junit.Assert
import org.junit.Test

class EqualCommandTest {
    @Test
    fun test_Equal () {
        val state = State()
        state.instructions = arrayOf(7,3,5,4,3,4,7,8)

        val cmd = EqualCommand()
        cmd.execute(state, CommandParams(8))

        Assert.assertEquals(1, state.instructions[4])
        Assert.assertEquals(4, state.instPtr)
    }

    @Test
    fun test_Equal_Immediate () {
        val state = State()
        state.instructions = arrayOf(8,4,4,4,3,6,7,8)

        val cmd = EqualCommand()
        cmd.execute(state, CommandParams(1108))

        Assert.assertEquals(1, state.instructions[4])
        Assert.assertEquals(4, state.instPtr)
    }

    @Test
    fun test_Equal_Fail () {
        val state = State()
        state.instructions = arrayOf(8,3,5,4,3,3,7,8)

        val cmd = EqualCommand()
        cmd.execute(state, CommandParams(8))

        Assert.assertEquals(0, state.instructions[4])
        Assert.assertEquals(4, state.instPtr)
    }
}