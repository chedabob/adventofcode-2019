package com.cbob.intcode.commands

import com.cbob.intcode.CommandParams
import com.cbob.intcode.State

class AddCommand : Command {
    override fun execute(state: State, params: CommandParams) {
        val p1 = getVal(state, state.instPtr + 1, params.param1)
        val p2 = getVal(state, state.instPtr + 2, params.param2)
        val dst = state.instructions[state.instPtr + 3]

        state.instructions[dst] = p1 + p2

        state.instPtr += 4
    }

}