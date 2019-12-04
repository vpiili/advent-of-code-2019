def (min, max) = [265275, 781584]

assert getPassword(111111, 111111) == 1
assert getPassword(223450, 223450) == 0
assert getPassword(123789, 123789) == 0

assert getPassword(112233, 112233, false) == 1
assert getPassword(123444, 123444, false) == 0
assert getPassword(111122, 111122, false) == 1

println "Part 1 --- ${getPassword(min, max)}"
println "Part 2 --- ${getPassword(min, max, false)}"

def getPassword(min, max, partOne = true) {
    return (min..max).findAll { 
        def num = it.toString()
        return hasAtleastTwoAdjacent(num) && isNotDecreasing(num) && (uglyHasExactlyTwoAdjacent(num) || partOne)
    }.size()
}

def hasAtleastTwoAdjacent(String num) {
    return num.matches(".*([0-9])\\1+.*")
}

def isNotDecreasing(String num) {
    def asNumberList = num.split("").collect { it as int }
    return asNumberList.clone().sort() == asNumberList
}

def uglyHasExactlyTwoAdjacent(num) { // works because we already checked decreasing
    def chars = num.split("")
    return chars.any { searchable ->
        return chars.count { it == searchable } == 2
    }
}