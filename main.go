package main

import (
	"math/rand"
	"os"
	"time"

	log "github.com/sirupsen/logrus"
)

func main() {
	results := []int{0, 0, 0, 0, 1}
	now := time.Now()
	rand.Seed(int64(now.Nanosecond()))
	n := results[rand.Intn(len(results))]
	log.Printf("Got exit code: %v", n)
	if n != 0 {
		log.Print("Exit will be regarded as failure!")
	}
	os.Exit(n)
}
