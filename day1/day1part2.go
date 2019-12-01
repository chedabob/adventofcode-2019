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

func calculateFuel(mass int, currFuel int) int {
	fuel := (mass / 3) - 2
	if fuel <= 0 {
		return currFuel
	} else {
		return calculateFuel(fuel, currFuel + fuel)
	}
}

func test () {
	value := 100756
	expected := 50346

	fuel := calculateFuel(value, 0)
	if fuel != expected {
		panic("ugh")
	}
}

func main() {

	test()

	data, err := ioutil.ReadFile("./d1p1.txt")
	check(err)
	input := string(data)
	// fmt.Println(input)

	modules := strings.Split(input, "\n")
	// fmt.Println(modules)

	sum := 0

	for _, mod := range modules {
		asInt, err := strconv.Atoi(strings.TrimSpace(mod))
		check(err)
		sum += calculateFuel(asInt, 0)
	}

	fmt.Println(sum)
}
