package algorithms;

import agent.Node;

public class Heuristics {
    public static int getHeuristic(HeuristicType heuristicType, Node currentNode, Node goalNode) {
        switch (heuristicType) {
            case EUCLIDEAN_DISTANCE -> {
                return getEuclideanDistance(currentNode, goalNode);
            }
            case MANHATTAN_DISTANCE -> {
                return getManhattanDistance(currentNode, goalNode);
            }
            default -> {
                return 0;
            }
        }
    }

    private static int getEuclideanDistance(Node currentNode, Node goalNode) {
        int total = 0;
        int[][] currentNodeTiles = currentNode.getTiles();
        for (int i = 0; i < currentNodeTiles.length; i++) {
            for (int j = 0; j < currentNodeTiles[0].length; j++) {
                int value = currentNodeTiles[i][j];
                if (value != 0) {
                    int rowIndex = goalNode.getIndexOfValue(value)[0];
                    int colIndex = goalNode.getIndexOfValue(value)[1];
                    total += calculateEuclideanDistance(i, j, rowIndex, colIndex);
                }
            }
        }
        return total;
    }

    private static int getManhattanDistance(Node currentNode, Node goalNode) {
        int total = 0;
        int[][] currentNodeTiles = currentNode.getTiles();
        for (int i = 0; i < currentNodeTiles.length; i++) {
            for (int j = 0; j < currentNodeTiles[0].length; j++) {
                int value = currentNodeTiles[i][j];
                if (value != 0) {
                    int rowIndex = goalNode.getIndexOfValue(value)[0];
                    int colIndex = goalNode.getIndexOfValue(value)[1];
                    total += calculateManhattanDistance(i, j, rowIndex, colIndex);
                }
            }
        }
        return total;
    }

    private static int calculateEuclideanDistance(int currentRow, int currentCol, int goalRow, int goalCol) {
        return (int) Math.sqrt(Math.pow((currentRow - goalRow), 2) + Math.pow((currentCol - goalCol), 2));
    }

    private static int calculateManhattanDistance(int currentRow, int currentCol, int goalRow, int goalCol) {
        return Math.abs(currentRow - goalRow) + Math.abs(currentCol - goalCol);
    }
}
