assert calculateClosestDistance(["R75","D30","R83","U83","L12","D49","R71","U7","L72"],["U62","R66","U55","R34","D71","R55","D58","R83"]) == 159
assert calculateClosestDistance(["R98","U47","R26","D63","R33","U87","L62","D20","R33","U53","R51"], ["U98","R91","D20","R16","D67","R40","U7","R15","U6","R7"]) == 135

assert calculateShortestWire(["R75","D30","R83","U83","L12","D49","R71","U7","L72"], ["U62","R66","U55","R34","D71","R55","D58","R83"]) == 610
assert calculateShortestWire(["R98","U47","R26","D63","R33","U87","L62","D20","R33","U53","R51"], ["U98","R91","D20","R16","D67","R40","U7","R15","U6","R7"]) == 410

def (input1, input2) = new File('../input/day3.txt').readLines().collect { it.split(",") as List }

//println "Part1 --   ${calculateClosestDistance(input1, input2)}"
//println "Part2 --   ${calculateShortestWire(input1, input2)}"


int calculateShortestWire(List one, List two) {
    def start = [x: 0, y: 0]
    def points1 = calculateRoute(start.clone(), one.clone())
    def points2 = calculateRoute(start.clone(), two.clone())
    def intersectingPoints = points1.intersect(points2)
    def wireLengths = intersectingPoints.collect { points1.indexOf(it) + points2.indexOf(it) + 2 }
    return wireLengths.min()
}


int calculateClosestDistance(List one, List two) {
    def start = [x: 0, y: 0]
    def points1 = calculateRoute(start.clone(), one.clone())
    def points2 = calculateRoute(start.clone(), two.clone())
    def intersectingPoints = points1.intersect(points2)
    println intersectingPoints
    def distances = intersectingPoints.collect { Math.abs(it['x']) + Math.abs(it['y']) }
    return distances.min()
}

List calculateRoute(Map startingPoint, List commands) {
    def initialCommand = commands.pop()
    def route = []
    route << calculateOneRoute(startingPoint, initialCommand)
    while (!commands.isEmpty()) route << calculateOneRoute(route.last().last().clone(), commands.pop())
    return route.flatten()
}

List calculateOneRoute(Map point, String command) {
    def direction = command[0]
    def distance = command.substring(1) as int
    return (1..distance).collect {
        switch(direction) {
            case "U":
                point['y']++
                return point.clone()
            case "R":
                point['x']++
                return point.clone()
            case "D":
                point['y']--
                return point.clone()
            case "L":
                point['x']--
                return point.clone()
        } 
    }
}



