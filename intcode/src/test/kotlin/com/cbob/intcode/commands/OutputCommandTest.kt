package com.cbob.intcode.commands

import com.cbob.intcode.CommandParams
import com.cbob.intcode.State
import org.junit.Assert
import org.junit.Test

class OutputCommandTest {
    @Test
    fun test_Output () {
        val state = State()
        state.instructions = arrayOf(4,3,2,55,4,5)

        val cmd = OutputCommand()
        cmd.execute(state, CommandParams(4))

        Assert.assertEquals(55, state.output)
        Assert.assertEquals(2, state.instPtr)
    }

    @Test
    fun test_Output_Immediate () {
        val state = State()
        state.instructions = arrayOf(4,3,2,55,4,5)

        val cmd = OutputCommand()
        cmd.execute(state, CommandParams(104))

        Assert.assertEquals(3, state.output)
        Assert.assertEquals(2, state.instPtr)
    }

}