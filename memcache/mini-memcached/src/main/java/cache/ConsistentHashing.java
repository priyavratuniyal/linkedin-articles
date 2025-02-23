package cache;

import java.util.*;

public class ConsistentHashing {
    private final TreeMap<Integer, String> circle = new TreeMap<>();
    private final int numReplicas;

    public ConsistentHashing(List<String> nodes, int numReplicas) {
        this.numReplicas = numReplicas;
        for (String node : nodes) addNode(node);
    }

    public void addNode(String node) {
        for (int i = 0; i < numReplicas; i++) {
            int hash = (node + i).hashCode();
            circle.put(hash, node);
        }
    }

    public void removeNode(String node) {
        for (int i = 0; i < numReplicas; i++) {
            circle.remove((node + i).hashCode());
        }
    }

    public String getNode(String key) {
        if (circle.isEmpty()) return null;
        int hash = key.hashCode();
        Integer targetHash = circle.ceilingKey(hash);
        if (targetHash == null) targetHash = circle.firstKey();
        return circle.get(targetHash);
    }
}
