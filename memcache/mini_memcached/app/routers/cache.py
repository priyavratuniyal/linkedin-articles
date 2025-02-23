from fastapi import APIRouter, HTTPException, Depends
from app.models.schema import CacheGetResponse, CacheSetRequest
from app.services.storage import CacheStorage

router = APIRouter()
storage = CacheStorage()

@router.get("/{key}", response_model=CacheGetResponse)
async def get(key: str, lease_token: str = None):
    value, is_valid = storage.cache.get(key)
    
    if value is not None:
        return {"value": value, "lease_required": False}
        
    if lease_token and storage.leases.validate_lease(lease_token):
        raise HTTPException(status_code=404, detail="Value not found")
        
    new_lease = storage.leases.issue_lease()
    return {
        "value": None,
        "lease_required": True,
        "lease_token": new_lease
    }

@router.post("/{key}")
async def set(key: str, data: CacheSetRequest):
    if data.lease_token and not storage.leases.validate_lease(data.lease_token):
        raise HTTPException(status_code=403, detail="Invalid lease token")
        
    storage.cache.set(key, data.value, data.ttl)
    return {"status": "success"}