package com.cbob.intcode

class State {
    var instructions : Array<Int> = arrayOf()
    var instPtr = 0
    var inputs : Array<Int> = arrayOf()
    var output = 0
}