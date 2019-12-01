/**
 * https://adventofcode.com/2019/day/1
 * Ville Piili
 **/

//assert calculateFuel('12') == 2
assert calculateFuel(14) == 2
assert calculateFuel(100756) == 50346
assert calculateFuel(1969) == 966

def file = new File('../input/day1.txt')
println fuelCounterUpper(file.readLines())

def fuelCounterUpper(List input) {
    return input.inject(0) { acc, line -> acc += calculateFuel(Integer.parseInt(line)) }
}

def calculateFuel(int input, int totalFuel = 0) {
    def fuel = ((input / 3) as int) - 2
    return fuel > 0 ? calculateFuel(fuel, totalFuel + fuel) : totalFuel
}