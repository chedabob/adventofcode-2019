package com.cbob.intcode.commands

import com.cbob.intcode.CommandParams
import com.cbob.intcode.State
import org.junit.Assert
import org.junit.Test

class StoreCommandTest {
    @Test
    fun test_Store () {
        val state = State()
        state.inputs = arrayOf(7)
        state.instructions = arrayOf(3,1)

        val cmd = StoreCommand()
        cmd.execute(state, CommandParams(3))

        Assert.assertEquals(7, state.instructions[1])
        Assert.assertEquals(2, state.instPtr)

    }
}