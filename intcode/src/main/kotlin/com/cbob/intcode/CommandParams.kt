package com.cbob.intcode

import kotlin.streams.toList

class CommandParams(raw: Long) {
    enum class ParamMode {
        Position,
        Immediate,
        Relative
    }
    val commandCode : Int
    var param1 = ParamMode.Position
    var param2 = ParamMode.Position
    var param3 = ParamMode.Position

    init {
        val chars = raw.toString().reversed()
        var rawCode = ""
        var idx = 0
        while (idx < chars.count()) {
            val component = "" + chars[idx]
            when (idx) {
                0 -> rawCode = component
                1 -> rawCode = component + rawCode
                2 -> param1 = paramMode(component.toInt())
                3 -> param2 = paramMode(component.toInt())
                4 -> param3 = paramMode(component.toInt())
            }
            idx++
        }
        commandCode = rawCode.toInt()
    }

    private fun paramMode(raw : Int): ParamMode {
        return when (raw) {
            1 -> ParamMode.Immediate
            2 -> ParamMode.Relative
            else -> ParamMode.Position
        }
    }
}