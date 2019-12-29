package com.cbob.intcode

class GameState : State() {

    enum class Action {
        SetX,
        SetY,
        Paint
    }


    val width = 250
    var data = MutableList(width * width) { 0 }

    private var currPosition = Point(width / 2, width / 2)

    private var currAction = Action.SetX

    override val nextInput: Int?
        get() {
            return getArrayVal(currPosition, data, width)
        }

    override fun writeOutput(newOutput: Long) {
        when (currAction) {
            Action.SetX -> {
                currPosition.x = newOutput.toInt()
                currAction = Action.SetY
            }
            Action.SetY -> {
                currPosition.y = newOutput.toInt()
                currAction = Action.Paint
            }
            Action.Paint -> {
                setArrayVal(currPosition, data, width, newOutput.toInt())
                currAction = Action.SetX
            }
        }
    }


    private fun setArrayVal (p: Point, data: MutableList<Int>, width: Int, newVal: Int) {
        val location = convert(p, width)
        data[location] = newVal
    }
    private fun getArrayVal (p: Point, data : List<Int>, width : Int): Int {
        val location = convert(p, width)
        return data[location]
    }

    private fun convert (p: Point, width: Int) : Int {
        return (p.y * width) + p.x
    }

    data class Point (var x : Int, var y: Int)
}