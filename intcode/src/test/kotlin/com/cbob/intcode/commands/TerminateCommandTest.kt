package com.cbob.intcode.commands

import com.cbob.intcode.CommandParams
import com.cbob.intcode.State
import org.junit.Assert
import org.junit.Test

class TerminateCommandTest {

    @Test
    fun test_Terminate () {
        val state = State()
        state.instructions = arrayOf(0,1,2,3,4,5,6,7)

        val cmd = TerminateCommand()
        cmd.execute(state, CommandParams(99))

        Assert.assertEquals(8, state.instPtr)
    }
}