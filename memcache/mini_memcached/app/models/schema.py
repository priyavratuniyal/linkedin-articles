from pydantic import BaseModel

class CacheSetRequest(BaseModel):
    value: object
    ttl: int = None
    lease_token: str = None

class CacheGetResponse(BaseModel):
    value: object = None
    lease_required: bool = False
    lease_token: str = None