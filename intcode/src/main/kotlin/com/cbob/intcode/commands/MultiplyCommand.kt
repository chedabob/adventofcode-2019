package com.cbob.intcode.commands

import com.cbob.intcode.State

class MultiplyCommand : Command {
    override fun execute(state: State) {
        val p1 = state.instructions[state.instPtr + 1]
        val p2 = state.instructions[state.instPtr + 2]
        val dst = state.instructions[state.instPtr + 3]

        val val1 = state.instructions[p1]
        val val2 = state.instructions[p2]

        state.instructions[dst] = val1 * val2

        state.instPtr += 4
    }

}