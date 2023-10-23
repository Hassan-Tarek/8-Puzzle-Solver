package algorithms;

import agent.Agent;
import agent.Node;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class BFS extends Agent {
    public BFS(Node startNode, Node goalNode) {
        super(startNode, goalNode);
    }

    @Override
    public void search() {
        Queue<Node> frontier = new LinkedList<>();
        Set<Node> visited = new HashSet<>();

        frontier.add(this.getStartNode());

        while (!frontier.isEmpty()) {
            Node currentNode = frontier.poll();
            expandedNodes++;
            maxDepth = Math.max(maxDepth, currentNode.getDepth());

            if (!visited.contains(currentNode)) {
                visited.add(currentNode);

                if (isGoal(currentNode)) {
                    setGoalNode(currentNode);
                    setSolution(constructSolution(currentNode));
                    return;
                }

                List<Node> successors = currentNode.expand();
                for (Node successor : successors) {
                    if (!visited.contains(successor)) {
                        frontier.add(successor);
                    }
                }
            }
        }
    }
}
