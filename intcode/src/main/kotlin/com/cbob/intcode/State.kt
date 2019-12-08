package com.cbob.intcode

class State {
    var instructions : Array<Int> = arrayOf()
    var instPtr = 0
    var inputs : Array<Int> = arrayOf()
    var output = 0
    val currInstr : Int; get() = instructions[instPtr]

    private var inputPtr = 0
    val nextInput : Int; get() {
        val input = inputs[inputPtr]
        inputPtr++
        return input
    }
}