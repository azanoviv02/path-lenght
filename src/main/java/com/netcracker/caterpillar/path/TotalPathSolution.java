package com.netcracker.caterpillar.path;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class TotalPathSolution {

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

        int arraySize = 3;
        int[] inputArray = {0, 0, 0, 0};

        int straightTotalDistance = getTotalDistance(inputArray);
        int totalNumberOfNodes = getNumberOfNodes(inputArray);
        int numberOfPairs = totalNumberOfNodes * (totalNumberOfNodes - 1) / 2;
        if (distanceMap.size() != numberOfPairs) {
            throw new IllegalStateException("Not all paths: " + numberOfPairs + ", " + distanceMap.size());
        }
        int mapTotalDistance = getMapTotalDistance();
        printMap();
        System.out.println(straightTotalDistance);
        System.out.println(mapTotalDistance);

    }

    private static void printMap() {
        for (Entry<Pair, Integer> entry : distanceMap.entrySet()) {
            System.out.println(entry);
        }
    }

    private static final Map<Pair, Integer> distanceMap = new HashMap<Pair, Integer>();

    private static int getTotalDistance(int[] inputArray) {
        int totalDistance = 0;
        int fromCurrentTrunkNode = 0;
        int numberOfPaths = 0;
        for (int i = 0; i < inputArray.length; i++) {
            int leafAmount = inputArray[i];

            // paths from new trunk node to all existing
            fromCurrentTrunkNode += numberOfPaths;
            if (i > 0) {
                addNewPairsBasedOn((i - 1) * 100, i * 100, 1);
                distanceMap.put(new Pair((i - 1) * 100, i * 100), 1);
            }
            totalDistance += fromCurrentTrunkNode;
            assertDistancesAreSame(totalDistance);

            // paths from new trunk node to new leaf nodes
            int toNewLeafNodes = leafAmount;
            for (int j = 0; j < leafAmount; j++) {
                distanceMap.put(new Pair(i * 100, i * 100 + 1 + j), 1);
            }
            totalDistance += toNewLeafNodes;
            assertDistancesAreSame(totalDistance);

            // paths from new leaf nodes to all existing
            int fromNewLeafNodes = (fromCurrentTrunkNode + numberOfPaths) * leafAmount;
            for (int j = 0; j < leafAmount; j++) {
                addNewPairsBasedOn(i * 100, i * 100 + 1 + j, 1);
            }
            totalDistance += fromNewLeafNodes;
//            assertDistancesAreSame(totalDistance);

            //paths from new leaf nodes to other new leaf nodes
            int interLeafNodes = leafAmount * (leafAmount - 1);
            for (int j = 0; j < leafAmount; j++) {
                for (int k = j + 1; k < leafAmount; k++) {
                    distanceMap.put(new Pair(i * 100 + 1 + j, i * 100 + 1 + k), 2);
                }
            }
            totalDistance += interLeafNodes;
            assertDistancesAreSame(totalDistance);

            fromCurrentTrunkNode += leafAmount;
            numberOfPaths += leafAmount+1;
//            if (numberOfPaths == 0 && i == 0) {
//                numberOfPaths++;
//            }
        }
        return totalDistance;
    }

    private static void assertDistancesAreSame(int straightTotalDistance){
        int mapTotalDistance = getMapTotalDistance();
        if(straightTotalDistance != mapTotalDistance){
            System.out.println("===================");
            printMap();
            System.out.println("Straight: "+straightTotalDistance);
            System.out.println("Map: "+mapTotalDistance);
            throw new IllegalStateException();
        }
    }
    private static int getMapTotalDistance() {
        int mapTotalDistance = 0;
        for(Entry<Pair, Integer> entry : distanceMap.entrySet()){
            mapTotalDistance += entry.getValue();
        }
        return mapTotalDistance;
    }

    private static void addNewPairsBasedOn(int baseNodeIndex,
                                           int newNodeIndex,
                                           int diff) {
        System.out.println("Basing new pairs for "+newNodeIndex+" on "+baseNodeIndex);
        Map<Pair, Integer> toAddMap = new HashMap<Pair, Integer>();
        for (Entry<Pair, Integer> entry : distanceMap.entrySet()) {
            Pair pair = entry.getKey();
            int distance = entry.getValue();
            if (pair.getRight() == baseNodeIndex) {
                int left = pair.getLeft();
                int newDistance = distance + diff;
                Pair newPair = new Pair(left, newNodeIndex);
                toAddMap.put(newPair, newDistance);
            }
            if(pair.getLeft() == baseNodeIndex && pair.getRight() < newNodeIndex){
                int right = pair.getRight();
                int newDistance = distance + diff;
                Pair newPair = new Pair(right, newNodeIndex);
                toAddMap.put(newPair, newDistance);
            }
        }
        distanceMap.putAll(toAddMap);
    }

    private static final int getNumberOfNodes(int[] inputArray) {
        int result = 0;
        for (int k : inputArray) {
            result += k + 1;
        }
        return result;
    }

    private static class Pair {
        private final int left;
        private final int right;

        public Pair(int left, int right) {
            if (left < 0 || right < 0) {
                throw new IllegalStateException();
            }
            if (left >= right) {
                throw new IllegalStateException();
            }
            this.left = left;
            this.right = right;
        }

        public int getLeft() {
            return left;
        }

        public int getRight() {
            return right;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Pair)) return false;

            Pair pair = (Pair) o;

            if (getLeft() != pair.getLeft()) return false;
            return getRight() == pair.getRight();
        }

        @Override
        public int hashCode() {
            int result = getLeft();
            result = 31 * result + getRight();
            return result;
        }

        @Override
        public String toString() {
            return "Pair{" +
                    "left=" + left +
                    ", right=" + right +
                    '}';
        }
    }
}
