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

func calculateFuel(fuel int) int {
	return (fuel / 3) - 2
}

func test () {
	value := 100756
	expected := 33583

	fuel := calculateFuel(value)
	if fuel != expected {
		panic("ugh")
	}
}

func main() {

	test()

	data, err := ioutil.ReadFile("./d2p1.txt")
	check(err)
	input := string(data)
	// fmt.Println(input)

	modules := strings.Split(input, "\n")
	// fmt.Println(modules)

	sum := 0

	for _, mod := range modules {
		asInt, err := strconv.Atoi(strings.TrimSpace(mod))
		check(err)
		sum += calculateFuel(asInt)
	}

	fmt.Println(sum)
}
