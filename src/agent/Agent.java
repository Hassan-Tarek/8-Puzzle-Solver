package agent;

import algorithms.HeuristicType;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public abstract class Agent {
    private Node startNode;
    private Node goalNode;
    private List<Node> solution;

    public Agent(Node startNode, Node goalNode) {
        this.startNode = startNode;
        this.goalNode = goalNode;
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

    public abstract void search();

    public boolean isGoal(Node node) {
        return node.equals(goalNode);
    }

    public int getDepth() {
        return goalNode.getDepth();
    }
}
