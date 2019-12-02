package main

import (
	"fmt"
	"io/ioutil"
	"strings"
	"strconv"
)

func check (e error) {
	if e != nil { 
		panic(e)
	}
}


func startProgram (input string, noun int, verb int) int {
	defer func() {
		if err := recover(); err != nil {
			//fmt.Println(err)
		}
	}()
	var instructions = parse(input)

	instructions[1] = noun
	instructions[2] = verb

	afterRun := adjustState(instructions)

	//fmt.Println(afterRun)

	return afterRun[0]

}

func parse (input string) []int {
	rawInstructions := strings.Split(input, ",")
	var instructions = convertToInts(rawInstructions) 
	return instructions
}


func adjustState(instructions []int) []int {
	
	i := 0
	for i<len(instructions) {
		val := instructions[i]
		switch val {
		case 99:
			return instructions
		case 1:
			slice := instructions[i+1:i+4]
			valA := instructions[slice[0]]
			valB := instructions[slice[1]]
			out := valA + valB
			instructions[slice[2]] = out
			i += 4
		case 2:
			slice := instructions[i+1:i+4]
			valA := instructions[slice[0]]
			valB := instructions[slice[1]]
			out := valA * valB
			instructions[slice[2]] = out
			i += 4
		}
	}
	return instructions
}

func convertToInts (input []string) []int {
	var t2 = []int{}
	for _, val := range input {
		parsed, err := strconv.Atoi(val)
		check(err)
		t2 = append(t2, parsed)
	}
	return t2
}

func findOutput (input string) {
	out := 19690720

	for noun := 0; noun < 128; noun++ {
		for verb := 0; verb < 128; verb++ {
			calced := startProgram(input, noun, verb)
			if calced == out {
				fmt.Println(100 * noun + verb)
				return
			}
		}
	}
}

func test () {
	input := "1,1,1,4,99,5,6,0,99"
	parsed := parse(input)
	fmt.Println(parsed)

	adjusted := adjustState(parsed)
	// expected := []int { 2,0,0,0,99 }

	// state := startProgram(input)
	
	fmt.Println(adjusted)
}

func main() {

	// test()

	data, err := ioutil.ReadFile("./d2p1.txt")
	check(err)
	input := string(data)
	fmt.Println(input)

	findOutput(input)
}
