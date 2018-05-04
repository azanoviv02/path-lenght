package com.netcracker.caterpillar.path;

public class CleanSolution {

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

//        int arraySize = 9;
//        int[] inputArray = {0, 3, 0, 1, 2, 1, 3, 1, 0};

        int arraySize = 4;
        int[] inputArray = {0, 0, 0, 0};

        int straightTotalDistance = getTotalDistance(inputArray);
        System.out.println(straightTotalDistance);
    }

    private static int getTotalDistance(int[] inputArray) {
        int totalDistance = 0;
        int fromCurrentTrunkNode = 0;
        int numberOfPaths = 0;
        for (int leafAmount : inputArray) {
            // paths from new trunk node to all previously existing
            fromCurrentTrunkNode += numberOfPaths;
            totalDistance += fromCurrentTrunkNode;

            // paths from new trunk node to new leaf nodes
            int toNewLeafNodes = leafAmount;
            totalDistance += toNewLeafNodes;

            // paths from new leaf nodes to all existing
            int fromNewLeafNodes = (fromCurrentTrunkNode + numberOfPaths) * leafAmount;
            totalDistance += fromNewLeafNodes;

            //paths from new leaf nodes to other new leaf nodes
            int interLeafNodes = leafAmount * (leafAmount - 1);
            totalDistance += interLeafNodes;

            // updating values for next iteration
            fromCurrentTrunkNode += leafAmount;
            numberOfPaths += leafAmount + 1;
        }
        return totalDistance;
    }
}
