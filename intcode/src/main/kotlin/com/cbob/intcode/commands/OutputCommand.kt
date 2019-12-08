package com.cbob.intcode.commands

import com.cbob.intcode.CommandParams
import com.cbob.intcode.State

class OutputCommand : Command {
    override fun execute(state: State, params: CommandParams): Boolean {
        val toWrite = getVal(state, state.instPtr + 1, params.param1)
        state.writeOutput(toWrite)

        state.instPtr += 2
        return true
    }
}