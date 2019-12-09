package com.cbob.intcode.commands

import com.cbob.intcode.CommandParams
import com.cbob.intcode.State

class AdjustRelativeBaseCommand : Command {
    override fun execute(state: State, params: CommandParams): Boolean {
        val offset = getVal(state, state.instPtr + 1, params.param1)
        state.relBase += offset.toInt()

        state.instPtr += 2
        return true
    }

}