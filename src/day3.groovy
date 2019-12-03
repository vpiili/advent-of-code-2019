assert calculateClosestDistance(["R75","D30","R83","U83","L12","D49","R71","U7","L72"],["U62","R66","U55","R34","D71","R55","D58","R83"]) == 159
assert calculateClosestDistance(["R98","U47","R26","D63","R33","U87","L62","D20","R33","U53","R51"], ["U98","R91","D20","R16","D67","R40","U7","R15","U6","R7"]) == 135

def (input1, input2) = new File('../input/day3.txt').readLines().collect { it.split(",") as List }

println "Part1 --   ${calculateClosestDistance(input1, input2)}"


int calculateClosestDistance(List one, List two) {
    def start = [0, 0]
    def points1 = calculateRoute(start.clone(), one)
    def points2 = calculateRoute(start.clone(), two)
    def intersectingPoints = points1.intersect(points2)
    def distances = intersectingPoints.collect { Math.abs(it[0]) + Math.abs(it[1]) }

    return distances.min()
}

List calculateRoute(List startingPoint, List commands) {
    def initialCommand = commands.pop()
    def route = []
    route << calculateOneRoute(startingPoint, initialCommand)
    while (!commands.isEmpty()) route << calculateOneRoute(route.last().last().clone(), commands.pop())
    return route.inject([]) { acc, item -> acc + item }
}

List calculateOneRoute(List point, String command) {
    def direction = command[0]
    def distance = command.substring(1) as int
    return (1..distance).collect {
        switch(direction) {
            case "U":
                point[0]++
                return point.clone()
            case "R":
                point[1]++
                return point.clone()
            case "D":
                point[0]--
                return point.clone()
            case "L":
                point[1]--
                return point.clone()
        } 
    }
}



