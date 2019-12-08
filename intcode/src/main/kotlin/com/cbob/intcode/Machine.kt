package com.cbob.intcode

import com.cbob.intcode.commands.AddCommand
import com.cbob.intcode.commands.Command
import com.cbob.intcode.commands.MultiplyCommand
import com.cbob.intcode.commands.TerminateCommand
import java.lang.Exception

class Machine {
    fun run (input : String, noun : Int? = null, verb : Int? = null) : State {
        val state = State()
        state.instructions = input.split(",").map { it.toInt() }.toTypedArray()

        noun?.let{
            state.instructions[1] = it
        }

        verb?.let {
            state.instructions[2] = it
        }

        val numInstr = state.instructions.size

        while (state.instPtr < numInstr) {
            val cmd = mapToCommand(state.currInstr)
            cmd.first.execute(state,cmd.second)
        }
        return state
    }

    private fun mapToCommand(raw: Int) : Pair<Command, CommandParams> {
        val params = CommandParams(raw)
        val cmd =  when (params.commandCode) {
            1 -> AddCommand()
            2 -> MultiplyCommand()
            99 -> TerminateCommand()
            else -> throw Exception("Unknown instruction")
        }

        return Pair(cmd, params)
    }

    private fun buildCommandParams (raw : Int) : CommandParams{
        return CommandParams(raw)
    }
}