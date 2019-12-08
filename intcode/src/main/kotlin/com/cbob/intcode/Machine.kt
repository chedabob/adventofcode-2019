package com.cbob.intcode

import com.cbob.intcode.commands.*
import java.lang.Exception

class Machine {
    fun run (instrStr : String, noun : Int? = null, verb : Int? = null, inputs: Array<Int> = arrayOf()) : State {
        val state = State()
        state.instructions = instrStr.split(",").map { it.toInt() }.toTypedArray()
        state.inputs = inputs

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
            3 -> StoreCommand()
            4 -> OutputCommand()
            5 -> JumpTrueCommand()
            6 -> JumpFalseCommand()
            7 -> LessThanCommand()
            8 -> EqualCommand()
            99 -> TerminateCommand()
            else -> throw Exception("Unknown instruction ${params.commandCode}")
        }

        return Pair(cmd, params)
    }
}