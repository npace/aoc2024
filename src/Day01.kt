import kotlin.math.absoluteValue

fun main() {
    fun part1(input: List<String>): Int {
        val (sortedLeft, sortedRight) = parseAndSortInput(input)

        var totalDistance = 0
        sortedLeft.forEachIndexed { index, left ->
            val right = sortedRight[index]
            val distance = (left - right).absoluteValue
            totalDistance += distance
        }
        println("Total distance: $totalDistance")
        return totalDistance
    }

    fun part2(input: List<String>): Int {
        val (sortedLeft, sortedRight) = parseAndSortInput(input)

        val countMap = mutableMapOf<Int, Int>()
        sortedRight.forEach { right ->
            val countForRight = countMap.getOrPut(right) { 0 }
            countMap[right] = countForRight + 1
        }

        val similarityScore = sortedLeft.sumOf { it * countMap.getOrDefault(it, 0) }
        return similarityScore
    }


    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day01_test")
    part1(testInput) shouldBe 11
    part2(testInput) shouldBe 31

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}

private fun parseAndSortInput(input: List<String>): Pair<List<Int>, List<Int>> {
    val (listLeft, listRight) = input.map {
        val first = it.substringBefore(" ").toInt()
        val second = it.substringAfterLast(" ").toInt()
        first to second
    }.unzip()
    return listLeft.sorted() to listRight.sorted()
}