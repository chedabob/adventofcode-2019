package com.cbob.intcode.commands

import com.cbob.intcode.CommandParams
import com.cbob.intcode.State

class OutputCommand : Command {
    override fun execute(state: State, params: CommandParams) {
        val loc = state.instructions[state.instPtr + 1]
        val toWrite = state.instructions[loc]
        state.output = toWrite

        state.instPtr += 2
    }

}