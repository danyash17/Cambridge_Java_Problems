package com.epam.sandbox;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static void main(String[] args) {
        /*
          1. The Fibonacci Series Problem
          Find the first term greater than 1000 in the sequence:
          1 1 2 3 5 8 13 . . .
          Also find the sum of all the values up to that term.
         */
        //System.out.println(fibonacci(1,1,1000));
        //System.out.println(fibonacciCalculateSum(1,1,1000));
        /*
          2. The Greenfly Problem
          Greenfly can reproduce asexually. After one week of life a lone female can produce eight
          offspring a day. Starting at the beginning of day 1 with a single mature female, how many
          greenfly could there be by the end of day 28? It may be assumed that:
          • There are no deaths
          • All offspring are females
          Note that at the end of day 1 there will be 9 greenfly (original + 8 offspring). At the end
          of day 7 there will be 57 greenfly (original + 8 × 7 offspring). At the end of day 8 there
          will be 129 greenfly (original + 8 × 8 offspring + 64 offspring from the daughters produced
          on day 1).
         */
        //System.out.println("There will be "+greenflyProblem(28)+" greenflies");
        /*
          3. All Prime Numbers less than 600
          Write a program to print a table of all prime numbers less than 600. Use the sieve method;
          take the first 600 integers and cross out all those that are multiples of 2, 3, 5, etc. until only
          primes remain, then print out the table
         */
        //System.out.println(primesUpTo(600));
        /*
          4. A Sort Problem
          Write a program which incorporates a method sort to ‘sort by exchange’ the elements of
          an array.
         */
        //System.out.println(Arrays.toString(bubbleSort(new int[]{1, 2, 6, 4, 89, 3, 7, 1, 3})));
        /*
          5. The Date of Easter Problem
          Write a method private static int easter(int y) which, when presented with a
          year y, returns the date of Easter.
          Incorporate this method into a complete test program. Verify that the method gives the
          correct date of Easter for the current year.
         */
        //System.out.println(getEaster(2029));
        /*
          6. The Friday 13th Problem
          Write a program to demonstrate that the 13th of a month is more likely to fall on a Friday
          than on any other day. Note that:
          • if (n % 4 == 0 && n % 100 != 0 || n % 400 == 0)...
          year n is a leap year.
          • the number of days in the leap year cycle of 400 years is an integral multiple of 7 (the
          program should verify this).
          • 1 January 1900 was a Monday.
          It is suggested that the program should look at all 4800 thirteenths that lie between
          1 January 1900 and 31 December 2299.
         */
        //System.out.println(friday13(2020, 11));
        /*
          7. The Forward and Backward Count Problem
          Write a program to sum the series
          P∞
          1/(1000n+π)
          n=0
          and print the results. The program
          should compute successive terms starting at n = 0 and continue adding terms until the
          sum ceases to increase. Write out the number of terms computed and the sum of those
          terms.
          The program should then recompute the answer by summing the same terms but in reverse
          order. This new sum should also be written out.
          • Why are the forward and backward sums different?
          • What answer would a mathematician give if asked the sum of the series?
         */
        //backForwardProblem();
        /*
          8. Accumulating Rounding Errors
          Write a program which evaluates 2
          n/100 for each n = 1, 2, . . . 16. Each value should
          be determined in two different ways. First evaluate (float)numerator/100.0f where
          numerator = 2
          n; this gives a good result. The second way is very na¨ıve: simply add
          (float)1/(float)100 to itself 2
          n times!
         */
        //roundingErrorsProblem();


    }

    public static int fibonacci(int prev, int next, int border) {
        int sum = prev + next;
        prev = next;
        return sum < border ? fibonacci(prev, sum, border) : sum;
    }

    public static int fibonacciCalculateSum(int prev, int next, int border) {
        int accumulator = 0;
        int last = fibonacci(prev, next, border);
        do {
            int sum = prev + next;
            prev = next;
            next = sum;
            accumulator += next;
        } while (prev + next != last);
        return accumulator;
    }

    public static long greenflyProblem(int day) {
        List<MutableInteger> youngGeneration = new LinkedList<>();
        int elderGeneration = 0;
        youngGeneration.add(new MutableInteger(7));
        for (int i = 1; i <= day; i++) {
            for (int j = 0; j < youngGeneration.size(); j++) {
                MutableInteger currentGreenfly = youngGeneration.get(j);
                if (currentGreenfly.get() >= 7) {
                    youngGeneration.remove(j);
                    elderGeneration++;
                    j--;
                } else youngGeneration.get(j).increment();
            }
            for (int k = 0; k < elderGeneration * 8; k++) {
                youngGeneration.add(new MutableInteger(1));
            }
        }
        return youngGeneration.size() + elderGeneration;
    }

    public static List<Integer> primesUpTo(int num) {
        List<Integer> primes = new LinkedList<>();
        boolean[] odds = new boolean[num];
        Arrays.fill(odds, true);
        int divider = 2;
        while (true) {
            for (int i = 2; i * divider < num; i++) {
                odds[i * divider] = false;
            }
            boolean trigger = true;
            for (int j = divider; j < num; j++) {
                if (odds[j]) {
                    divider = j;
                    primes.add(j);
                    odds[j] = false;
                    trigger = false;
                    break;
                }
            }
            if (trigger) break;
        }
        return primes;
    }

    public static int[] bubbleSort(int[] array) {
        int length = array.length;
        for (int i = 0; i < length; i++) {
            for (int j = length - 1; j > 0; j--) {
                if (array[j] < array[j - 1]) {
                    int temp = array[j - 1];
                    array[j - 1] = array[j];
                    array[j] = temp;
                }
            }
        }
        return array;
    }

    public static int getEaster(int year) {
        int a = year % 19, b = year / 100, c = year % 100, d = b / 4, e = b % 4, f = (b + 8) / 25, g = (b - f + 1) / 3, h = (19 * a + b - d - g + 15) % 30,
                i = c / 4, k = c % 4, l = (32 + 2 * e + 2 * i - h - k) % 7, m = (a + 11 * h + 22 * l) / 451, n = (h + l - 7 * m + 114) / 31, p = (h + l - 7 * m + 114) % 31;
        return 10 * (p + 1) + n;
    }

    public static String friday13(int year, int month) {
        int diff = year - 1900, leaps = diff / 4, daydiff = (diff - leaps) * 365 + leaps * 366, janday = daydiff % 7, jandaydiff = 0, nearestSunday = 7 - janday % 6;
        for (int i = 1; i < month; i++) {
            if (i == 2) {
                if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) jandaydiff += 29;
                else jandaydiff += 28;
                continue;
            }
            if (i % 2 != 0) {
                if (i <= 6) jandaydiff += 31;
                else jandaydiff += 30;
            } else if (i < 6) jandaydiff += 30;
            else jandaydiff += 31;
        }
        jandaydiff -= nearestSunday;
        String monthName = null;
        switch (month) {
            case 1: {
                monthName = new String("Январь");
                break;
            }
            case 2: {
                monthName = new String("Февраль");
                break;
            }
            case 3: {
                monthName = new String("Март");
                break;
            }
            case 4: {
                monthName = new String("Апрель");
                break;
            }
            case 5: {
                monthName = new String("Май");
                break;
            }
            case 6: {
                monthName = new String("Июнь");
                break;
            }
            case 7: {
                monthName = new String("Июль");
                break;
            }
            case 8: {
                monthName = new String("Август");
                break;
            }
            case 9: {
                monthName = new String("Сентябрь");
                break;
            }
            case 10: {
                monthName = new String("Октябрь");
                break;
            }
            case 11: {
                monthName = new String("Ноябрь");
                break;
            }
            case 12: {
                monthName = new String("Декабрь");
                break;
            }
        }
        return jandaydiff % 7 == 0 ? monthName + " " + year + " года - в этом месяце БЫЛА пятница 13е" : monthName + " " + year + "года - в этом месяце НЕ БЫЛО пятницы 13го";
    }

    public static void backForwardProblem() {
        int terms = 0;
        double forwardSum = 0, previous = 0;
        do {
            previous = forwardSum;
            forwardSum += 1 / ((1000 * terms) + Math.PI);
            terms++;
        }
        while (previous < forwardSum);
        int i = terms;
        double backwardSum = 0;
        while (i >= 0) {
            backwardSum += 1 / ((1000 * i) + Math.PI);
            i--;
        }
        double gaussSum = terms * (1 / Math.PI + 1 / ((1000 * terms) + Math.PI) / 2);
        System.out.println("Backward = " + backwardSum + "\nForward = " + forwardSum + "\nGauss sum = " + gaussSum + "\nTerms = " + terms);
    }

    public static void roundingErrorsProblem() {
        for (int i = 1; i <= 16; i++) {
            float badSum = 0;
            for (int j = 0; j < Math.pow(2, i); j++) {
                badSum += (float) 1 / (float) 100;
            }
            System.out.println((float) Math.pow(2, i) / 100.0f + " || " + badSum);
        }
    }
}
