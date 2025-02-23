import uvicorn
from app.config import settings
from app.routers.cache import router
from fastapi import FastAPI

def create_app():
    app = FastAPI(title="Mini-Memcached", version="0.1.0")
    app.include_router(router)
    return app

def main():
    app = create_app()
    uvicorn.run(
        app,
        host=settings.api_host,
        port=settings.api_port,
        reload=True,  # Auto-reload in development
    )

if __name__ == "__main__":
    main()