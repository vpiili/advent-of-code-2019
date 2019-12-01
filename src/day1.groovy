/**
 * https://adventofcode.com/2019/day/1
 * Ville Piili
 **/

assert calculateFuel(12) == 2
assert calculateFuel(14) == 2
assert calculateFuel(1969)
assert calculateFuel(100756)

assert calculateFuel(14, true) == 2
assert calculateFuel(1969, true) == 966
assert calculateFuel(100756, true) == 50346


def input = new File('../input/day1.txt').readLines()
println "Part 1 --  ${fuelCounterUpper(input)}"
println "Part 2 --  ${fuelCounterUpper(input, true)}"

def fuelCounterUpper(List input, part2 = false) {
    return input.inject(0) { acc, line -> acc += calculateFuel(Integer.parseInt(line), part2) }
}

def calculateFuel(int input, part2 = false, int totalFuel = 0) {
    def fuel = ((input / 3) as int) - 2
    if (!part2) return fuel
    return fuel > 0 ? calculateFuel(fuel, part2, totalFuel + fuel) : totalFuel
}