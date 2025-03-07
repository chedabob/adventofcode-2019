package com.cbob.intcode.commands

import com.cbob.intcode.CommandParams
import com.cbob.intcode.State

interface Command {
    fun execute(state: State, params: CommandParams): Boolean

    fun getVal(state: State, idx: Int, paramMode : CommandParams.ParamMode) : Long {
        val currInstr = state.instructions[idx]
        return when (paramMode) {
            CommandParams.ParamMode.Position -> {
                state.instructions[currInstr.toInt()]
            }
            CommandParams.ParamMode.Immediate -> currInstr
            CommandParams.ParamMode.Relative -> {
                val newAddr = state.relBase + currInstr
                state.instructions[newAddr.toInt()]
            }
        }
    }

    fun getDstVal(state: State, idx: Int, paramMode : CommandParams.ParamMode) : Long {
        val currInstr = state.instructions[idx]
        return when (paramMode) {
            CommandParams.ParamMode.Position -> currInstr
            CommandParams.ParamMode.Immediate -> currInstr
            CommandParams.ParamMode.Relative -> state.relBase + currInstr
        }
    }
}