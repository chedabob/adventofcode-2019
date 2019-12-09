package com.cbob.intcode.commands

import com.cbob.intcode.CommandParams
import com.cbob.intcode.State
import org.junit.Assert
import org.junit.Test

class AdjustRelativeBaseCommandTest {

    @Test
    fun test_Adjust () {
        val state = State()
        state.instructions = arrayOf(9,5)

        val cmd = AdjustRelativeBaseCommand()
        cmd.execute(state, CommandParams(109))

        Assert.assertEquals(2, state.instPtr)
        Assert.assertEquals(5, state.relBase)
    }

    @Test
    fun test_Adjust_Negative () {
        val state = State()
        state.instructions = arrayOf(9,-3)

        val cmd = AdjustRelativeBaseCommand()
        cmd.execute(state, CommandParams(109))

        Assert.assertEquals(2, state.instPtr)
        Assert.assertEquals(-3, state.relBase)
    }
}