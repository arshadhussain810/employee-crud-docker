#!/bin/bash

echo "Connecting to the app..."

# Forwarding the Service is simpler than finding a Pod name
kubectl port-forward svc/employee-service 8080:80
