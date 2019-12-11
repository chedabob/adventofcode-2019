import kotlin.math.atan2
import kotlin.math.sqrt

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
    println("Width $width")
    val coords = (raw.indices).map { idxToCoord(it, width) }
//    println (coords)

    val pairs = raw.toCharArray().zip(coords)
//    println(pairs)

    val asteroids = pairs.filter { it.first == '#' }.map { it.second }

    val collisions = asteroids.map { asteroidRays(it, asteroids) }
    val max = collisions.maxBy { it.size }

    println("Maximum : ${max?.size}")
}

fun asteroidRays(a : Pair<Int, Int>, others: List<Pair<Int, Int>>): Set<Double> {
    val notMe = others.filterNot { it == a }
    return notMe.map { arcTan(a, it) }.toSet()
}

fun arcTan (a : Pair<Int, Int>, b : Pair<Int, Int>) : Double {
    return atan2((a.first - b.first).toDouble(), (a.second - b.second).toDouble())
}

fun idxToCoord (idx : Int, width : Int) : Pair<Int, Int> {
    val columns = idx / width
    val rows = idx - (columns * width)

    return Pair(rows, columns)
}