package com.netcracker.caterpillar.independent;

public class CleanSolution {

    // todo add module arithmetic
    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        int arraySize = parseInt(scanner.nextLine());
//        String arrayLine = scanner.nextLine();
//        String[] arrayStrings = arrayLine.split("\\s");
//        if(arrayStrings.length != arraySize){
//            throw new IllegalStateException();
//        }
//        int[] inputArray = new int[arraySize];
//        for(int i = 0; i < arraySize; i++){
//            inputArray[i] = parseInt(arrayStrings[i]);
//        }

//        int arraySize = 3;
//        int[] inputArray = {0, 10, 0};

        int arraySize = 9;
        int[] inputArray = {0, 3, 0, 1, 2, 1, 3, 1, 0};

//        int arraySize = 4;
//        int[] inputArray = {0, 0, 0, 0};

        int straightTotalDistance = getIndependentSetsAmount(inputArray);
        System.out.println(straightTotalDistance);
    }

    /**
     * state A - trunk node is included, leaf nodes are excluded (1 option)
     * state B - trunk node is excluded, leaf nodes are either included or excluded (2^k options)
     *
     * @param inputArray
     * @return
     */
    private static int getIndependentSetsAmount(int[] inputArray) {
        int arrayLength = inputArray.length;

        int current;
        int minusOne;
        int minusTwo;

        current = twoInPower(inputArray[0]);
        minusOne = 1;
        for (int i = 1; i < arrayLength; i++) {
            minusTwo = minusOne;
            minusOne = current;
            current = (minusOne + minusTwo) * twoInPower(inputArray[i]);
        }

        return current + minusOne - 1;
    }


    //todo replace with bit shift
    private static int pow(int number, int power) {
        int result = 1;
        for (int i = 0; i < power; i++) {
            result *= number;
        }
        return result;
    }

    private static int twoInPower(int power){
        return 1 << power;
    }
}
