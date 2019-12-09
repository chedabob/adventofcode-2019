package com.cbob.intcode.commands

import com.cbob.intcode.CommandParams
import com.cbob.intcode.State

class EqualCommand : Command {
    override fun execute(state: State, params: CommandParams): Boolean {
        val p1 = getVal(state, state.instPtr + 1, params.param1)
        val p2 = getVal(state, state.instPtr + 2, params.param2)
        val dst = getDstVal(state, state.instPtr + 3, params.param3)

        state.instructions[dst.toInt()] = when (p1 == p2) {
            true -> 1
            else -> 0
        }

        state.instPtr += 4
        return true
    }

}