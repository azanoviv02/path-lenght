package com.netcracker.caterpillar.colouring;

import java.util.Arrays;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class ColouringSolution {

    public static final int MODULO = 1000000007;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String firstLine = scanner.nextLine();
        String[] firstLineNumbers = firstLine.split("\\s");
        int n = parseInt(firstLineNumbers[0]);
        int k = parseInt(firstLineNumbers[1]);

        int[] inputArray = new int[n];
        for (int i = 0; i < n; i++) {
            inputArray[i] = scanner.nextInt();
        }

//        System.out.println(n);
//        System.out.println(k);
//        System.out.println(Arrays.toString(inputArray));

//        int n = 3;
//        int k = 3;
//        int[] inputArray = {0, 10, 0};

//        int n = 9;
//        int k = 2;
//        int[] inputArray = {0, 3, 0, 1, 2, 1, 3, 1, 0};

//        int n = 4;
//        int k = 4;
//        int[] inputArray = {0, 0, 0, 0};

        int colouringAmount = getColouringAmount(inputArray, k);
        System.out.println(colouringAmount);
    }

    private static int getColouringAmount(int[] inputArray, int k) {
        long result = 1;
        for (int i = 0; i < inputArray.length; i++) {
            if (i == 0) {
                result = (result * k) % MODULO;
            } else {
                result = (result * (k - 1)) % MODULO;
            }
            int leafAmount = inputArray[i];
            for (int j = 0; j < leafAmount; j++) {
                result = (result * (k - 1)) % MODULO;
            }
        }

        return Long.valueOf(result).intValue();
    }

    private static int pow(int number, int power) {
        long result = 1;
        for (int i = 0; i < power; i++) {
            result = (result * number) % MODULO;
        }
        return Long.valueOf(result).intValue();
    }
}
