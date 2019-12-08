package com.cbob.intcode.commands

import com.cbob.intcode.CommandParams
import com.cbob.intcode.State
import org.junit.Assert
import org.junit.Test

class JumpFalseCommandTest {
    @Test
    fun test_JumpFalse_NoJump () {
        val state = State()
        state.instructions = arrayOf(6,5,6,1,4,1,7,1)

        val cmd = JumpFalseCommand()
        cmd.execute(state, CommandParams(10006))

        Assert.assertEquals(3, state.instPtr)
    }

    @Test
    fun test_JumpFalse_Jump () {
        val state = State()
        state.instructions = arrayOf(6,5,6,1,4,0,7,1)

        val cmd = JumpFalseCommand()
        cmd.execute(state, CommandParams(10006))

        Assert.assertEquals(7, state.instPtr)
    }

    @Test
    fun test_JumpFalse_Jump_Immediate () {
        val state = State()
        state.instructions = arrayOf(6,0,7,1,4,1,7,1)

        val cmd = JumpFalseCommand()
        cmd.execute(state, CommandParams(1106))

        Assert.assertEquals(7, state.instPtr)
    }
}