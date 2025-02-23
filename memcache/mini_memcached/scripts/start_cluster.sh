#!/bin/bash

# Start 3 nodes on ports 8000, 8001, and 8002
uvicorn app.main:create_app --host 0.0.0.0 --port 8000 &
uvicorn app.main:create_app --host 0.0.0.0 --port 8001 &
uvicorn app.main:create_app --host 0.0.0.0 --port 8002 &

echo "Cluster started on ports 8000, 8001, and 8002"