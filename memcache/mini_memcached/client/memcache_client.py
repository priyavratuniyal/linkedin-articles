import httpx
from app.core.hashing import ConsistentHasher

class MemcacheClient:
    def __init__(self, servers: list[str]):
        self.hasher = ConsistentHasher(servers)
        self.client = httpx.AsyncClient()
        
    async def get(self, key: str):
        node = self.hasher.get_node(key)
        try:
            response = await self.client.get(
                f"http://{node}/{key}",
                params={"lease_token": self.current_lease}
            )
            return response.json()
        except httpx.HTTPError:
            # Handle retries and lease acquisition
            pass

    async def set(self, key: str, value: object, ttl: int = None):
        # Similar implementation
        pass