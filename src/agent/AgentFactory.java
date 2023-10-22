package agent;

import algorithms.AStar;
import algorithms.BFS;
import algorithms.DFS;
import algorithms.ISearch;

public class AgentFactory {
    public static ISearch createAgent(AgentType agentType) {
        return switch (agentType) {
            case DFS -> new DFS();
            case BFS -> new BFS();
            case A_STAR -> new AStar();
        };
    }
}
