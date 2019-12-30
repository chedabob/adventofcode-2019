package com.cbob.intcode

import kotlin.math.max

class GameState : State() {

    enum class Action {
        SetX,
        SetY,
        Paint
    }


    val width = 40
    var data = MutableList(width * width) { 0 }
    var score = 0

    private var currPosition = Point(width / 2, width / 2)

    private var currAction = Action.SetX

    override val nextInput: Int?
        get() {
            val ballLoc = findBall(data, width) ?: return 0
            val paddleLoc = findPaddle(data, width) ?: return 0

            return when {
                ballLoc.x < paddleLoc.x -> -1
                ballLoc.x > paddleLoc.x -> 1
                else -> 0
            }
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
                if (currPosition.x == -1 && currPosition.y == 0) {
                    println(newOutput.toInt())
                    score = max(score, newOutput.toInt())
                }
                else {
                    setArrayVal(currPosition, data, width, newOutput.toInt())
                }
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

    private fun findBall (data : List<Int>, width: Int) : Point? {
        val idx = data.indexOfFirst { it == 4 }
        if (idx == -1) {
            return null
        }

        return convert(idx, width)
    }

    private fun findPaddle (data : List<Int>, width: Int) : Point? {
        val idx = data.indexOfLast { it == 3 }
        if (idx == -1) {
            return null
        }

        return convert(idx, width)
    }

    private fun convert(idx: Int, width: Int): Point {
        val y = idx / width
        val x = idx % width
        return Point(x, y)
    }

}