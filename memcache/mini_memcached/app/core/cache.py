from collections import OrderedDict
from threading import Lock
import time

class LRUCache:
    def __init__(self, capacity: int = 1000, default_ttl: int = 30):
        self.cache = OrderedDict()
        self.capacity = capacity
        self.default_ttl = default_ttl
        self.lock = Lock()

    def get(self, key: str) -> tuple[object, bool]:
        with self.lock:
            if key not in self.cache:
                return None, False
                
            value, expiry = self.cache[key]
            if time.time() > expiry:
                del self.cache[key]
                return None, False
                
            self.cache.move_to_end(key)
            return value, True

    def set(self, key: str, value: object, ttl: int = None):
        with self.lock:
            ttl = ttl or self.default_ttl
            if len(self.cache) >= self.capacity:
                self.cache.popitem(last=False)
            self.cache[key] = (value, time.time() + ttl)