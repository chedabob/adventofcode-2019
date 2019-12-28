package com.cbob.intcode

class ExtendedState : State() {

    enum class Direction (private val point: Point) {
        Up(Point(0, -1)),
        Down(Point(0, 1)),
        Left(Point(-1, 0)),
        Right(Point(1, 0));

        fun apply (p : Point) : Point{
            return Point(p.x + point.x, p.y + point.y)
        }

        fun rotate (direction : Int) : Direction {
            if (direction == 0) {
                return when (this) {
                    Up -> Left
                    Left -> Down
                    Down -> Right
                    Right -> Up
                }
            }
            else {
                return when (this) {
                    Up -> Right
                    Right -> Down
                    Down -> Left
                    Left -> Up
                }
            }
        }
    }


    private val width = 10000
    var data = MutableList(width * width) { 0 }

    private var currPosition = Point(width / 2, width / 2)
    private var direction = Direction.Up

    var painted = mutableSetOf<Point>()

    private var paint = true
    override val nextInput: Int?
        get() {
            return getArrayVal(currPosition, data, width)
        }

    override fun writeOutput(newOutput: Long) {
        if (paint) {
            setArrayVal(currPosition, data, width, newOutput.toInt())
            painted.add(currPosition)
        }
        else {
            direction = direction.rotate(newOutput.toInt())
            currPosition = direction.apply(currPosition)
        }
        paint = !paint
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

    data class Point (val x : Int, val y: Int)
}