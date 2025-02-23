import hashlib
import bisect

class ConsistentHasher:
    def __init__(self, nodes: list[str], virtual_nodes: int = 200):
        self.ring = {}
        self.sorted_hashes = []
        
        for node in nodes:
            for i in range(virtual_nodes):
                virtual_key = f"{node}-{i}"
                hash_val = self._hash(virtual_key)
                self.ring[hash_val] = node
                self.sorted_hashes.append(hash_val)
                
        self.sorted_hashes.sort()

    def _hash(self, key: str) -> int:
        return int(hashlib.md5(key.encode()).hexdigest(), 16)

    def get_node(self, key: str) -> str:
        hash_val = self._hash(key)
        idx = bisect.bisect(self.sorted_hashes, hash_val) % len(self.sorted_hashes)
        return self.ring[self.sorted_hashes[idx]]