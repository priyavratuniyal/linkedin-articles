from pydantic import BaseSettings

class Settings(BaseSettings):
    # Cache settings
    cache_capacity: int = 1000  # Max items in cache
    cache_default_ttl: int = 30  # Default TTL in seconds

    # Lease settings
    lease_duration: int = 5  # Lease validity in seconds

    # Node settings
    node_id: str = "node1"  # Unique ID for this node
    cluster_nodes: list[str] = ["node1:8000", "node2:8001", "node3:8002"]  # All nodes in the cluster

    # FastAPI settings
    api_host: str = "0.0.0.0"
    api_port: int = 8000

    class Config:
        env_file = ".env"  # Load settings from .env file
        env_prefix = "MEMCACHE_"  # Environment variable prefix

# Singleton instance
settings = Settings()