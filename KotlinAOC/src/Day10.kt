import kotlin.math.*

fun main() {
    val raw = "#...##.####.#.......#.##..##.#." +
            "#.##.#..#..#...##..##.##.#....." +
            "#..#####.#......#..#....#.###.#" +
            "...#.#.#...#..#.....#..#..#.#.." +
            ".#.....##..#...#..#.#...##....." +
            "##.....#..........##..#......##" +
            ".##..##.#.#....##..##.......#.." +
            "#.##.##....###..#...##...##...." +
            "##.#.#............##..#...##..#" +
            "###..##.###.....#.##...####...." +
            "...##..#...##...##..#.#..#...#." +
            "..#.#.##.#.#.#####.#....####.#." +
            "#......###.##....#...#...#...##" +
            ".....#...#.#.#.#....#...#......" +
            "#..#.#.#..#....#..#...#..#..##." +
            "#.....#..##.....#...###..#..#.#" +
            ".....####.#..#...##..#..#..#..#" +
            "..#.....#.#........#.#.##..####" +
            ".#.....##..#.##.....#...###...." +
            "###.###....#..#..#.....#####..." +
            "#..##.##..##.#.#....#.#......#." +
            ".#....#.##..#.#.#.......##....." +
            "##.##...#...#....###.#....#...." +
            ".....#.######.#.#..#..#.#.....#" +
            ".#..#.##.#....#.##..#.#...##..#" +
            ".##.###..#..#..#.###...#####.#." +
            "#...#...........#.....#.......#" +
            "#....##.#.#..##...#..####...#.." +
            "#.####......#####.....#.##..#.." +
            ".#...#....#...##..##.#.#......#" +
            "#..###.....##.#.......#.##...##"

    val width = sqrt(raw.length.toDouble()).toInt()
//    println("Width $width")
    val coords = (raw.indices).map { idxToCoord(it, width) }
//    println (coords)

    val pairs = raw.toCharArray().zip(coords)
//    println(pairs)

    val asteroids = pairs.filter { it.first == '#' }.map { it.second }

    val collisions = asteroids.map { asteroidRays(it, asteroids) }
    val max = collisions.maxBy { it.second.size }

    println("Day 10 Part 1 - Maximum : ${max?.second?.size}")


    vaporise(max!!.first, asteroids)

}
class Target(val location: Pair<Int, Int>, val distance: Double, val angle: Double)

fun asteroidRays(a : Pair<Int, Int>, others: List<Pair<Int, Int>>): Pair<Pair<Int,Int>,Set<Double>> {
    val notMe = others.filterNot { it == a }
    return Pair(a, notMe.map { arcTan(a, it) }.toSet())
}

fun asteroidRays2(a : Pair<Int, Int>, others: List<Pair<Int, Int>>): List<Target> {
    val angleDistance = others.map { Target(it, distance(a, it), arcTan2(a, it)) }
    return angleDistance.groupBy { q -> q.angle }.map { t -> t.value.minBy { it.distance }!! }.sortedBy { it.angle }.reversed()
}

fun distance (a : Pair<Int, Int>, b: Pair<Int,Int>) : Double {
    val x = (a.first - b.first).toDouble().pow(2.0)
    val y = (a.second - b.second).toDouble().pow(2.0)

    return sqrt(x + y)
}


fun arcTan (a : Pair<Int, Int>, b : Pair<Int, Int>) : Double {
    return atan2((b.first - a.first).toDouble(), (b.second - a.second).toDouble())
}


fun arcTan2 (a : Pair<Int, Int>, b : Pair<Int, Int>) : Double {
    val angle = atan2((a.first - b.first).toDouble(), (a.second - b.second).toDouble())
    return if (angle > 0) angle - (2 * PI) else angle
}

fun idxToCoord (idx : Int, width : Int) : Pair<Int, Int> {
    val columns = idx / width
    val rows = idx - (columns * width)

    return Pair(rows, columns)
}

fun vaporise (monitoringLocation : Pair<Int, Int>, asteroids: List<Pair<Int, Int>>) {
    var toCheck = asteroids.filterNot { it == monitoringLocation }

    var numVaporised = 1

    while (true) {
        var thisPass = asteroidRays2(monitoringLocation, toCheck)
        while (thisPass.isNotEmpty()) {
            val next = thisPass.first()
            if (numVaporised == 200) {
                println("Day 10 Part 2 ${calcFinal(next.location)}")
                return
            }
            else {
                thisPass = thisPass.takeLast(thisPass.size - 1)
                toCheck = toCheck.filterNot { it == next.location }
                numVaporised++
            }
        }

    }

}

fun calcFinal (input : Pair<Int, Int>): Int {
    return (input.first * 100) + input.second
}