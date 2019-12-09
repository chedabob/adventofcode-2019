package com.cbob.intcode.commands

import com.cbob.intcode.CommandParams
import com.cbob.intcode.State
import org.junit.Assert
import org.junit.Test

class AddCommandTest {
    @Test
    fun test_Add () {
        val state = State()
        state.instructions = arrayOf(0,1,2,4,3)

        val cmd = AddCommand()
        cmd.execute(state, CommandParams(1))

        Assert.assertEquals(3, state.instructions[4])
        Assert.assertEquals(4, state.instPtr)
    }

    @Test
    fun test_Add_Immediate () {
        val state = State()
        state.instructions = arrayOf(0,3,5,4,3,6,7,8)

        val cmd = AddCommand()
        cmd.execute(state, CommandParams(1111))

        Assert.assertEquals(8, state.instructions[4])
        Assert.assertEquals(4, state.instPtr)
    }

    @Test
    fun test_Add_Relative () {
        val state = State()
        state.relBase = 1
        state.instructions = arrayOf(2,3,4,4,3,6,7,8)

        val cmd = AddCommand()
        cmd.execute(state, CommandParams(2111))

        Assert.assertEquals(4, state.instPtr)
        Assert.assertEquals(9, state.instructions[4])
    }

    @Test
    fun test_Add_Relative_Negative () {
        val state = State()
        state.relBase = 5
        state.instructions = arrayOf(2,3,-2,4,3,6,7,8)

        val cmd = AddCommand()
        cmd.execute(state, CommandParams(2111))

        Assert.assertEquals(4, state.instPtr)
        Assert.assertEquals(7, state.instructions[4])
    }
}