package com.cbob.intcode

class State {
    var instructions : Array<Int> = arrayOf()
    var instPtr = 0
    var inputs = mutableListOf<Int>()
    var output = 0
    val currInstr : Int; get() = instructions[instPtr]

    private var inputPtr = 0
    val nextInput : Int?; get() {
        val input : Int? = inputs.getOrNull(inputPtr)

        input?.let {
            inputPtr++
        }
        return input
    }

    fun pushInput (newInput : Int) {
        inputs.add(newInput)
    }

    fun writeOutput (newOutput : Int) {
        output = newOutput
    }
    val finished : Boolean; get () { return instPtr >= instructions.size }
}