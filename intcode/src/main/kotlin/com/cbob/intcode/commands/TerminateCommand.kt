package com.cbob.intcode.commands

import com.cbob.intcode.State

class TerminateCommand : Command {
    override fun execute(state: State) {
        state.instPtr = state.instructions.size
    }

}