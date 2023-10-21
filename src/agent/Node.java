package agent;

import java.util.LinkedList;
import java.util.List;

public class Node {
    private int[][] tiles;
    private Node parent;
    private int depth;
    private int cost;
    private final int[] spaceIndex;

    public Node(int[][] tiles, Node parent, int depth, int cost) {
        this.tiles = tiles;
        this.parent = parent;
        this.depth = depth;
        this.cost = cost;
        this.spaceIndex = getIndexOfValue(0);
    }

    public int[][] getTiles() {
        return tiles;
    }

    public void setTiles(int[][] tiles) {
        this.tiles = tiles;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public List<Node> expand() {
        return getFilteredSuccessors();
    }

    public int[] getIndexOfValue(int value) {
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[0].length; j++) {
                if (tiles[i][j] == value) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{-1, -1};
    }

    private boolean isValidMove(Direction direction) {
        int row = spaceIndex[0];
        int col = spaceIndex[1];

        return switch (direction) {
            case UP -> row != 0;
            case DOWN -> row != tiles.length - 1;
            case RIGHT -> col % 3 != tiles[0].length - 1;
            case LEFT -> col % 3 != 0;
        };
    }

    private List<Node> getFilteredSuccessors() {
        List<Node> filteredSuccessors = new LinkedList<>();

        for (Direction direction : Direction.values()) {
            int rowIndexOfSpace = spaceIndex[0];
            int colIndexOfSpace = spaceIndex[1];

            if (isValidMove(direction)) {
                Node node = new Node(tiles, this, depth + 1, cost + 1);
                switch (direction) {
                    case UP -> {
                        node.tiles[rowIndexOfSpace][colIndexOfSpace] =  node.tiles[rowIndexOfSpace - 1][colIndexOfSpace];
                        node.tiles[rowIndexOfSpace - 1][colIndexOfSpace] = 0;
                    }
                    case DOWN -> {
                        node.tiles[rowIndexOfSpace][colIndexOfSpace] =  node.tiles[rowIndexOfSpace + 1][colIndexOfSpace];
                        node.tiles[rowIndexOfSpace + 1][colIndexOfSpace] = 0;
                    }
                    case RIGHT -> {
                        node.tiles[rowIndexOfSpace][colIndexOfSpace] =  node.tiles[rowIndexOfSpace][colIndexOfSpace + 1];
                        node.tiles[rowIndexOfSpace][colIndexOfSpace + 1] = 0;
                    }
                    case LEFT -> {
                        node.tiles[rowIndexOfSpace][colIndexOfSpace] =  node.tiles[rowIndexOfSpace][colIndexOfSpace - 1];
                        node.tiles[rowIndexOfSpace][colIndexOfSpace - 1] = 0;
                    }
                }
                filteredSuccessors.add(node);
            }
        }

        return filteredSuccessors;
    }
}
