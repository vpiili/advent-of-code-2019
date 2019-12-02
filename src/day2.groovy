/**
 * https://adventofcode.com/2019/day/2
 * Ville Piili
 **/

assert compute([1,0,0,0,99]) == [2,0,0,0,99]
assert compute([2,3,0,3,99]) == [2,3,0,6,99]
assert compute([2,4,4,5,99,0]) == [2,4,4,5,99,9801]
assert compute([1,1,1,4,99,5,6,0,99]) == [30,1,1,4,2,5,6,0,99]

def input = new File('../input/day2.txt').text.split(",").collect { it as int }

def input1 = input.clone()

input1[1] = 12
input1[2] = 2

println "Part 1 --   ${compute(input1)[0]}"

(0..99).each { noun ->
    (0..99).each { verb -> 
        def input2 = input-clone()
        input2[1] = noun
        input2[2] = verb
        if (compute(input2)[0] == 19690720) {
            println "Part 2 --   ${100 * noun + verb}"
            return
        }
    }
}

def compute(List input) {
    (0..input.size()).step(4).find {
        if (input[it] == 99) return true 
        computeBlock(input[it..it+3], input)
        return false
    }
    return input
}

def computeBlock(List block, List input) {
    switch(block[0]) {
        case 1:
            input[block[3]] = input[block[1]] + input[block[2]] 
            break
        case 2:
            input[block[3]] = input[block[1]] * input[block[2]]
            break
    }
}