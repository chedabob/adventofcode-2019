package com.cbob.intcode.commands

import com.cbob.intcode.CommandParams
import com.cbob.intcode.State
import org.junit.Assert
import org.junit.Test

class JumpTrueCommandTest {
    @Test
    fun test_JumpTrue_NoJump () {
        val state = State()
        state.instructions = arrayOf(5,5,6,1,4,0,7,1)

        val cmd = JumpTrueCommand()
        cmd.execute(state, CommandParams(10005))

        Assert.assertEquals(3, state.instPtr)
    }

    @Test
    fun test_JumpTrue_Jump () {
        val state = State()
        state.instructions = arrayOf(5,5,6,1,4,1,7,1)

        val cmd = JumpTrueCommand()
        cmd.execute(state, CommandParams(10005))

        Assert.assertEquals(7, state.instPtr)
    }

    @Test
    fun test_JumpTrue_Jump_Immediate () {
        val state = State()
        state.instructions = arrayOf(5,1,3,1,4,1,7,1)

        val cmd = JumpTrueCommand()
        cmd.execute(state, CommandParams(1105))

        Assert.assertEquals(3, state.instPtr)
    }
}