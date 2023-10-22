package agent;

public class AgentFactory {
    public static Agent createAgent(AgentType agentType, Node startNode, Node goalNode) {
        return agentType.getAgentSupplier().apply(startNode, goalNode);
    }
}
