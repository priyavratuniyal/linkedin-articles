package cache;

import java.util.HashMap;
import java.util.Map;

public class CacheNode {
    private String nodeName;
    private LRUCache<String, String> localCache;

    public CacheNode(String nodeName, int capacity) {
        this.nodeName = nodeName;
        this.localCache = new LRUCache<>(capacity);
    }

    public String getNodeName() {
        return nodeName;
    }

    public String get(String key) {
        return localCache.get(key);
    }

    public void put(String key, String value) {
        localCache.put(key, value);
    }
}
