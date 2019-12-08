package com.cbob.intcode.commands

import com.cbob.intcode.CommandParams
import com.cbob.intcode.State

class StoreCommand : Command {
    override fun execute(state: State, params: CommandParams): Boolean {
        val dst = state.instructions[state.instPtr + 1]
        val input = state.nextInput

        input?.let {
            state.instructions[dst] = input
            state.instPtr += 2
        }
        return input != null
    }

}