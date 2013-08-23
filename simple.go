package main

import (
	"fmt"
	"time"
)

func longRun(response chan string) {
	time.Sleep(10e9)
	response <- "done"
}

func main() {
	response := make(chan string)
	go longRun(response)
	value := <-response
	fmt.Println(value)
}
