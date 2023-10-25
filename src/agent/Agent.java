package agent;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public abstract class Agent {
    private Node startNode;
    private Node goalNode;
    protected int expandedNodes;
    protected int maxDepth;
    private List<Node> solution;

    public Agent(Node startNode, Node goalNode) {
        this.startNode = startNode;
        this.goalNode = goalNode;
        this.expandedNodes = 0;
    }

    public Node getStartNode() {
        return startNode;
    }

    public void setStartNode(Node startNode) {
        this.startNode = startNode;
    }

    public Node getGoalNode() {
        return goalNode;
    }

    public void setGoalNode(Node goalNode) {
        this.goalNode = goalNode;
    }

    public List<Node> getSolution() {
        return solution;
    }

    public void setSolution(List<Node> solution) {
        this.solution = solution;
    }

    public int getExpandedNodes() {
        return expandedNodes;
    }

    public void setExpandedNodes(int expandedNodes) {
        this.expandedNodes = expandedNodes;
    }

    public int getMaxDepth() {
        return maxDepth;
    }

    public void setMaxDepth(int maxDepth) {
        this.maxDepth = maxDepth;
    }

    public abstract void search();

    public boolean isGoal(Node node) {
        return node.equals(goalNode);
    }

    public List<Node> constructSolution(Node goal) {
        List<Node> solution = new LinkedList<>();
        Node currentNode = goal;

        while (currentNode != null) {
            solution.add(currentNode);
            currentNode = currentNode.getParent();
        }

        Collections.reverse(solution);
        return solution;
    }

    public int getDepth() {
        return goalNode.getDepth();
    }
}
