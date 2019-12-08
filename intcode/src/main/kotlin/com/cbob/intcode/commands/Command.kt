package com.cbob.intcode.commands

import com.cbob.intcode.CommandParams
import com.cbob.intcode.State

interface Command {
    fun execute(state: State, params: CommandParams): Boolean

    fun getVal(state: State, idx: Int, paramMode : CommandParams.ParamMode) : Int {
        val currInstr = state.instructions[idx]
        return when (paramMode) {
            CommandParams.ParamMode.Position -> {
                state.instructions[currInstr]
            }
            CommandParams.ParamMode.Immediate -> currInstr
        }
    }
}