package algorithms;

import agent.Agent;
import agent.Node;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class AStar extends Agent {
    private final HeuristicType heuristicType;

    public AStar(HeuristicType heuristicType, Node startNode, Node goalNode) {
        super(startNode, goalNode);
        this.heuristicType = heuristicType;
    }

    @Override
    public void search() {
        Map<Node, Integer> nodeToCost = new HashMap<>();
        PriorityQueue<Node> frontier = new PriorityQueue<>(Comparator
                .comparingInt(nodeToCost::get)); // Cost f(n) = g(n) + h(n)
        Set<Node> visited = new HashSet<>();

        nodeToCost.put(getStartNode(),
                getStartNode().getCost() + Heuristics.getHeuristic(heuristicType, getStartNode(), getGoalNode()));
        frontier.add(getStartNode());

        while (!frontier.isEmpty()) {
            Node currentNode = frontier.poll();

            if (!visited.contains(currentNode)) {
                visited.add(currentNode);

                if (isGoal(currentNode)) {
                    setSolution(constructSolution(currentNode));
                    return;
                }

                List<Node> successors = currentNode.expand();
                for (Node successor : successors) {
                    if (!visited.contains(successor)) {
                        int newCost = successor.getCost() + Heuristics.getHeuristic(heuristicType, successor, getGoalNode());

                        if (!nodeToCost.containsKey(successor) || newCost < nodeToCost.get(successor)) {
                            nodeToCost.put(successor, newCost);
                            frontier.add(successor);
                        }
                    }
                }
            }
        }
    }
}
