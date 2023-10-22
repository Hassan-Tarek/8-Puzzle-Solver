package agent;

import algorithms.AStar;
import algorithms.BFS;
import algorithms.DFS;

import java.util.function.BiFunction;

public enum AgentType {
    DEPTH_FIRST_SEARCH(DFS::new),
    BREADTH_FIRST_SEARCH(BFS::new),
    A_STAR(AStar::new);

    private final BiFunction<Node, Node, Agent> agentSupplier;

    AgentType(BiFunction<Node, Node, Agent> agentSupplier) {
        this.agentSupplier = agentSupplier;
    }

    public BiFunction<Node, Node, Agent> getAgentSupplier() {
        return agentSupplier;
    }
}
