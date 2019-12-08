package com.cbob.intcode

import com.cbob.intcode.commands.*
import org.junit.Assert
import org.junit.Test

class CommandTest {
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
    fun test_Multiply () {
        val state = State()
        state.instructions = arrayOf(0,3,4,4,3)

        val cmd = MultiplyCommand()
        cmd.execute(state,CommandParams(2))

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

    @Test
    fun test_Terminate () {
        val state = State()
        state.instructions = arrayOf(0,1,2,3,4,5,6,7)

        val cmd = TerminateCommand()
        cmd.execute(state,CommandParams(99))

        Assert.assertEquals(8, state.instPtr)
    }

    @Test
    fun test_Store () {
        val state = State()
        state.inputs = arrayOf(7)
        state.instructions = arrayOf(3,1)

        val cmd = StoreCommand()
        cmd.execute(state,CommandParams(3))

        Assert.assertEquals(7, state.instructions[1])
        Assert.assertEquals(2, state.instPtr)

    }

    @Test
    fun test_Output () {
        val state = State()
        state.instructions = arrayOf(4,3,2,55,4,5)

        val cmd = OutputCommand()
        cmd.execute(state,CommandParams(4))

        Assert.assertEquals(55, state.output)
        Assert.assertEquals(2, state.instPtr)
    }

    @Test
    fun test_Output_Immediate () {
        val state = State()
        state.instructions = arrayOf(4,3,2,55,4,5)

        val cmd = OutputCommand()
        cmd.execute(state,CommandParams(104))

        Assert.assertEquals(3, state.output)
        Assert.assertEquals(2, state.instPtr)
    }

    @Test
    fun test_JumpTrue_NoJump () {
        val state = State()
        state.instructions = arrayOf(5,5,6,1,4,0,7,1)

        val cmd = JumpTrueCommand()
        cmd.execute(state,CommandParams(10005))

        Assert.assertEquals(3, state.instPtr)
    }

    @Test
    fun test_JumpTrue_Jump () {
        val state = State()
        state.instructions = arrayOf(5,5,6,1,4,1,7,1)

        val cmd = JumpTrueCommand()
        cmd.execute(state,CommandParams(10005))

        Assert.assertEquals(7, state.instPtr)
    }

    @Test
    fun test_JumpTrue_Jump_Immediate () {
        val state = State()
        state.instructions = arrayOf(5,1,3,1,4,1,7,1)

        val cmd = JumpTrueCommand()
        cmd.execute(state,CommandParams(1105))

        Assert.assertEquals(3, state.instPtr)
    }

    @Test
    fun test_JumpFalse_NoJump () {
        val state = State()
        state.instructions = arrayOf(6,5,6,1,4,1,7,1)

        val cmd = JumpFalseCommand()
        cmd.execute(state,CommandParams(10006))

        Assert.assertEquals(3, state.instPtr)
    }

    @Test
    fun test_JumpFalse_Jump () {
        val state = State()
        state.instructions = arrayOf(6,5,6,1,4,0,7,1)

        val cmd = JumpFalseCommand()
        cmd.execute(state,CommandParams(10006))

        Assert.assertEquals(7, state.instPtr)
    }

    @Test
    fun test_JumpFalse_Jump_Immediate () {
        val state = State()
        state.instructions = arrayOf(6,0,7,1,4,1,7,1)

        val cmd = JumpFalseCommand()
        cmd.execute(state,CommandParams(1106))

        Assert.assertEquals(7, state.instPtr)
    }



    @Test
    fun test_day2_part1_examples () {
        val test1 = "1,9,10,3,2,3,11,0,99,30,40,50"
        val expected1 = 3500
        val runner = Machine()
        val finalState = runner.run(test1)
        val output = finalState.instructions[0]
        Assert.assertEquals(expected1, output)
    }

}