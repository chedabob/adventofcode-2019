package com.cbob.intcode

import org.junit.Assert
import org.junit.Test

class CommandParamsTest {

    @Test
    fun test_single_command () {
        val input = 1L
        val params = CommandParams(input)

        Assert.assertEquals(input, params.commandCode)
    }

    @Test
    fun test_twodigit_command () {
        val input = 99L
        val params = CommandParams(input)

        Assert.assertEquals(input, params.commandCode)
    }

    @Test
    fun test_param1mode_default () {
        val input = 99L
        val params = CommandParams(input)

        Assert.assertEquals(CommandParams.ParamMode.Position, params.param1)
    }

    @Test
    fun test_param1mode_position () {
        val input = 1099L
        val params = CommandParams(input)

        Assert.assertEquals(CommandParams.ParamMode.Position, params.param1)
    }

    @Test
    fun test_param1mode_immediate () {
        val input = 199L
        val params = CommandParams(input)

        Assert.assertEquals(CommandParams.ParamMode.Immediate, params.param1)
    }

    @Test
    fun test_param2mode_default () {
        val input = 199L
        val params = CommandParams(input)

        Assert.assertEquals(CommandParams.ParamMode.Position, params.param2)
    }

    @Test
    fun test_param2mode_position () {
        val input = 10199L
        val params = CommandParams(input)

        Assert.assertEquals(CommandParams.ParamMode.Position, params.param2)
    }

    @Test
    fun test_param2mode_immediate () {
        val input = 1199L
        val params = CommandParams(input)

        Assert.assertEquals(CommandParams.ParamMode.Immediate, params.param2)
    }

    @Test
    fun test_param3mode_default () {
        val input = 199L
        val params = CommandParams(input)

        Assert.assertEquals(CommandParams.ParamMode.Position, params.param3)
    }

    @Test
    fun test_param3mode_position () {
        val input = 101199L
        val params = CommandParams(input)

        Assert.assertEquals(CommandParams.ParamMode.Position, params.param3)
    }

    @Test
    fun test_param3mode_immediate () {
        val input = 11199L
        val params = CommandParams(input)

        Assert.assertEquals(CommandParams.ParamMode.Immediate, params.param3)
    }
}