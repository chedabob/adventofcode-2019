package com.cbob.intcode.commands

import com.cbob.intcode.CommandParams
import com.cbob.intcode.State

interface Command {
    fun execute(state: State, params: CommandParams)
}