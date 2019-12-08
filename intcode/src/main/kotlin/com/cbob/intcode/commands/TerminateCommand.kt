package com.cbob.intcode.commands

import com.cbob.intcode.CommandParams
import com.cbob.intcode.State

class TerminateCommand : Command {
    override fun execute(state: State, params: CommandParams): Boolean {
        state.instPtr = state.instructions.size
        return true
    }

}