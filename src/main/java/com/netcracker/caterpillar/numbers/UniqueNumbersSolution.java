package com.netcracker.caterpillar.numbers;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import static java.lang.Integer.parseInt;

public class UniqueNumbersSolution {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String firstLine = scanner.nextLine();
        int n = parseInt(firstLine);

        int[] inputArray = new int[n];
        for (int i = 0; i < n; i++) {
            inputArray[i] = scanner.nextInt();
        }

//        System.out.println(n);
//        System.out.println(k);
//        System.out.println(Arrays.toString(inputArray));

//        int[] inputArray = {7, 10, 3, 2, 7, 4, 8, 5, 9, 10};
//        int[] inputArray = {5, 5, 5, 5, 5};
//        int[] inputArray = {3, 5, 4};

        BigInteger uniqueNumbersSum = getUniqueNumbersSum(inputArray);
        System.out.println(uniqueNumbersSum);
    }

    private static BigInteger getUniqueNumbersSum(int[] inputArray) {
        Set<Integer> set = new HashSet<Integer>();
        for (int number : inputArray) {
            set.add(number);
        }
        BigInteger result = BigInteger.valueOf(0);
        for (Integer uniqueNumber : set) {
            result = result.add(BigInteger.valueOf(uniqueNumber));
        }
        return result;
    }
}
