package com.cbob.intcode.commands

import com.cbob.intcode.CommandParams
import com.cbob.intcode.State

class AdjustRelativeBaseCommand : Command {
    override fun execute(state: State, params: CommandParams): Boolean {
        val offset = state.instructions[state.instPtr + 1]
        state.relBase += offset

        state.instPtr += 2
        return true
    }

}