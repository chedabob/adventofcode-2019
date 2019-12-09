package com.cbob.intcode

import com.cbob.intcode.commands.*
import java.lang.Exception

class Machine(program: String) {
    val state = State()

    init {
        state.instructions = program.split(",").map { it.toLong() }.toTypedArray() + Array(10000) { 0L }
    }

    fun run (noun: Int? = null, verb: Int? = null) : State {

        noun?.let{
            state.instructions[1] = it.toLong()
        }

        verb?.let {
            state.instructions[2] = it.toLong()
        }

        while (true) {
            val cmd = mapToCommand(state.currInstr)
            val res = cmd.first.execute(state,cmd.second)
            if (!res || state.finished) {
                break
            }
        }
        return state
    }

    private fun mapToCommand(raw: Long) : Pair<Command, CommandParams> {
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
            9 -> AdjustRelativeBaseCommand()
            99 -> TerminateCommand()
            else -> throw Exception("Unknown instruction ${params.commandCode}")
        }

        return Pair(cmd, params)
    }


}