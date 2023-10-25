package gui;

import agent.Agent;
import agent.AgentType;
import agent.AgentFactory;
import agent.Node;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Controller {
    private Agent agent;
    private final GameView view;
    private int currentStateIndex;

    public Controller() {
        this.view = GameView.getInstance();
        this.agent = AgentFactory.createAgent(AgentType.BREADTH_FIRST_SEARCH, null, null);
        this.currentStateIndex = 0;
    }

    public EventHandler<ActionEvent> solve() {
        return event -> {
            if (!isValidPuzzle(view.getPuzzleTextField().getText())) {
                AlterBox.display("ERROR!!!", "Enter a valid puzzle!");
                reset().handle(event);
                return;
            }

            currentStateIndex = 0;
            int[][] startNodeTiles = parsePuzzleString(view.getPuzzleTextField().getText());
            Node startNode = new Node(startNodeTiles, null, 0, 0);
            int[][] goalNodeTiles = new int[][]{{0, 1, 2}, {3, 4, 5}, {6, 7, 8}};
            Node goalNode = new Node(goalNodeTiles, null, 0, 0);
            String algorithm = view.getSearchAlgorithmsBox().getSelectionModel().getSelectedItem();
            AgentType agentType = parseAgentType(algorithm);
            agent.setStartNode(startNode);
            agent = AgentFactory.createAgent(agentType, startNode, goalNode);
            long start = System.nanoTime();
            agent.search();
            List<Node> solution = agent.getSolution();
            long end = System.nanoTime();

            if (!isSolvable(startNode, goalNode, solution)) {
                AlterBox.display("ERROR!!!", "Enter a solvable puzzle!");
                reset().handle(event);
            }
            else {
                updateDisplay(String.valueOf(agent.getGoalNode().getCost()),
                        String.valueOf(agent.getDepth()), String.valueOf(agent.getMaxDepth()),
                        String.valueOf(agent.getExpandedNodes()), String.valueOf((end - start) / 1_000_000),
                        startNode.getTiles());
                updateButtonStates(currentStateIndex, solution.size());
            }
        };
    }

    public EventHandler<ActionEvent> reset() {
        return event -> {
            view.getPuzzleTextField().setText("");
            view.getSearchAlgorithmsBox().setValue("BFS");
            currentStateIndex = 0;
            clearDisplay();
            updateButtonStates(currentStateIndex, 0);
        };
    }

    public EventHandler<ActionEvent> getPrevState() {
        return event -> {
            List<Node> solution = agent.getSolution();
            currentStateIndex = Math.max(0, currentStateIndex - 1);

            updateGridDisplay(solution.get(currentStateIndex).getTiles());
            updateButtonStates(currentStateIndex, solution.size());
        };
    }

    public EventHandler<ActionEvent> getNextState() {
        return event -> {
            List<Node> solution = agent.getSolution();
            currentStateIndex = Math.min(solution.size() - 1, currentStateIndex + 1);

            updateGridDisplay(solution.get(currentStateIndex).getTiles());
            updateButtonStates(currentStateIndex, solution.size());
        };
    }

    public EventHandler<ActionEvent> viewPath() {
        return event -> {
            PathView pathView = PathView.getInstance();
            pathView.createGrid(agent.getSolution());
            pathView.getStage().show();
        };
    }

    private boolean isValidPuzzle(String puzzle) {
        Set<Character> set = new HashSet<>();

        for (int i = 0; i < puzzle.length(); i++) {
            char c = puzzle.charAt(i);
            set.add(c);
            if (!Character.isDigit(c) || (c - '0') == 9) {
                return false;
            }
        }

        return set.size() == 9;
    }

    private boolean isSolvable(Node startNode, Node goalNode, List<Node> solution) {
        return startNode.equals(goalNode) && solution.size() == 0 ||
                !startNode.equals(goalNode) && solution.size() > 0;
    }

    private int[][] parsePuzzleString(String puzzle) {
        int[][] tiles = new int[3][3];
        int index = 0;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tiles[i][j] = Character.getNumericValue(puzzle.charAt(index++));
            }
        }

        return tiles;
    }

    private AgentType parseAgentType(String algorithm) {
        return switch (algorithm) {
            case "BFS" -> AgentType.BREADTH_FIRST_SEARCH;
            case "A* using Euclidean" -> AgentType.A_STAR_EUCLIDEAN;
            case "A* using Manhattan" -> AgentType.A_STAR_MANHATTAN;
            default -> AgentType.DEPTH_FIRST_SEARCH;
        };
    }

    private void updateDisplay(String cost, String depth, String maxDepth,
                                String expandedNodes, String time, int[][] tiles) {
        updateTextDisplay(cost, depth, maxDepth, expandedNodes, time);
        updateGridDisplay(tiles);
    }


    private void updateTextDisplay(String cost, String depth, String maxDepth,
                                   String expandedNodes, String time) {
        view.getCostOfPathLabel().setText("Cost of Path = " + cost);
        view.getSearchDepthLabel().setText("Search Depth = " + depth);
        view.getMaxDepthLabel().setText("Max Depth = " + maxDepth);
        view.getExpandedNodesLabel().setText("No. of Expanded Nodes = " + expandedNodes);
        String t = time.equals("") ? "" : " ms";
        view.getTimeLabel().setText("Time = " + time + t);
    }

    private void updateGridDisplay(int[][] tiles) {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                int value = tiles[row][col];
                view.getGridLabel()[row][col].setText(value == 0 ? "" : String.valueOf(value));
            }
        }
    }

    private void clearDisplay() {
        view.getCostOfPathLabel().setText("Cost of Path = ");
        view.getSearchDepthLabel().setText("Search Depth = ");
        view.getMaxDepthLabel().setText("Max Depth = ");
        view.getExpandedNodesLabel().setText("No. of Expanded Nodes = ");
        view.getTimeLabel().setText("Time = ");
        updateGridDisplay(new int[][]{{0, 0, 0}, {0, 0, 0}, {0, 0, 0}});
    }

    private void updateButtonStates(int currentIndex, int solutionSize) {
        view.getNextButton().setDisable(currentIndex >= solutionSize);
        view.getPrevButton().setDisable(currentIndex <= 0);
    }
}
