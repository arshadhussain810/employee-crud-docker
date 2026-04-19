#!/bin/bash

# 1. Build the image
docker build -t employee-backend:latest .

# 2. Load it into the cluster
kind load docker-image employee-backend:latest --name kind

# 3. Apply the Kubernetes files
kubectl apply -f k8s/

# 4. Refresh the pods
kubectl rollout restart deployment employee-deployment

echo "Done! App is deploying."