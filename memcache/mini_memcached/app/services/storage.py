from app.core.cache import LRUCache
from app.services.lease import LeaseService

class CacheStorage:
    def __init__(self):
        self.cache = LRUCache()
        self.leases = LeaseService()