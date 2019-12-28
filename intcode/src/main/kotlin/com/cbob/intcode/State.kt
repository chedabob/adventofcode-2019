package com.cbob.intcode

open class State {
    var instructions : Array<Long> = arrayOf()
    var instPtr = 0
    var inputs = mutableListOf<Int>()
    var outputs = mutableListOf<Long>()
    val output : Long; get () { return outputs.last() }
    val currInstr : Long; get() = instructions[instPtr]
    var relBase = 0

    private var inputPtr = 0
    open val nextInput : Int?; get() {
        val input : Int? = inputs.getOrNull(inputPtr)

        input?.let {
            inputPtr++
        }
        return input
    }

    fun pushInput (newInput : Int) {
        inputs.add(newInput)
    }

    open fun writeOutput (newOutput : Long) {
        outputs.add(newOutput)
    }
    val finished : Boolean; get () { return instPtr >= instructions.size }
}