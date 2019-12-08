package com.cbob.intcode.commands

import com.cbob.intcode.CommandParams
import com.cbob.intcode.State

class JumpFalseCommand : Command {
    override fun execute(state: State, params: CommandParams) {
        val p1 = getVal(state, state.instPtr + 1, params.param1)
        val dst = getVal(state, state.instPtr + 2, params.param2)

        if (p1 == 0) {
            state.instPtr = dst
        }
        else {
            state.instPtr += 3
        }
    }

}