package com.salimatech.zazie.controller;


import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.stream.IntStream;

@RestController
public class FibonacciController {



    @GetMapping(value = "/fibonacci")
        public ArrayList<Long> fibonacci(@RequestParam("number") int number) {
        ArrayList<Long> fibs = new ArrayList();

        for (int i = 0; i <= number; i++) {
             fibs.add(computeRecursive(i));
        }
        return fibs;

        }

    /*
     *
     * The tail recursion algorithm's cost is
     * exponential as it does not store the result of previous
     * calculated numbers. eg F(n-3) is called 3 times


                                 F(n)
                                /     \
                            F(n-1)     F(n-2)
                            /   \      /     \
                        F(n-2)  F(n-3) F(n-3) F(n-4)
                        /    \
                     F(n-3) F(n-4)
     *
     * @param number input number
     * @return fibomacci of number
     */
     private long computeRecursive(int number){
         if (number == 0 || number == 1)
             return number;

         return computeRecursive(number - 1) + computeRecursive(number - 2);
    }

    /**
     * Iterative version of recursive ...
     * @param number
     * @return
     */
    private long computeIterative(int number) {

        if (number == 0 || number == 1)
            return number;

        int firstNumber = 0, secondNumber = 1;
        int fibNumber = 0;
        for (int series = 2; series <= number; series++) {
            fibNumber = firstNumber + secondNumber;
            firstNumber = secondNumber;
            secondNumber = fibNumber;
        }
        return fibNumber;

    }

}
