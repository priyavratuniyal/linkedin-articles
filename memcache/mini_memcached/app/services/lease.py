import uuid
import time
from threading import Lock

class LeaseService:
    def __init__(self):
        self.leases = {}
        self.lock = Lock()

    def issue_lease(self) -> str:
        with self.lock:
            token = str(uuid.uuid4())
            self.leases[token] = time.time() + 5  # 5-second validity
            return token

    def validate_lease(self, token: str) -> bool:
        with self.lock:
            if token not in self.leases:
                return False
                
            if time.time() > self.leases[token]:
                del self.leases[token]
                return False
                
            return True