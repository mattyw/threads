package main

import (
	"fmt"
	"time"
)

var num = 1000000

func thread(response chan int) {
	time.Sleep(10e9)
	response <- 1
}

func main() {
	resp := make(chan int)
	for i := 0; i < num; i++ {
		go thread(resp)
	}

	count := 0
	for i := range resp {
		count += i
		if count == num {
			close(resp)
		}
		fmt.Println(count)
	}

}
