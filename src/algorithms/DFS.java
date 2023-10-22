package algorithms;

import agent.Agent;
import agent.Node;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class DFS extends Agent {
    public DFS(Node startNode, Node goalNode) {
        super(startNode, goalNode);
    }

    @Override
    public void search() {
        Stack<Node> frontier = new Stack<>();
        Set<Node> visited = new HashSet<>();

        frontier.push(this.getStartNode());

        while (!frontier.isEmpty()) {
            Node currentNode = frontier.pop();

            if (!visited.contains(currentNode)) {
                visited.add(currentNode);

                if (isGoal(currentNode)) {
                    setSolution(constructSolution(currentNode));
                    return;
                }

                List<Node> successors = currentNode.expand();
                for (Node successor : successors) {
                    if (!visited.contains(successor)) {
                        frontier.push(successor);
                    }
                }
            }
        }
    }
}
