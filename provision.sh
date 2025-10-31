#!/bin/bash
set -e

# 1. Update and install dependencies
sudo apt-get update -y
sudo apt-get install -y curl wget apt-transport-https ca-certificates conntrack

# 2. Install Docker
sudo apt-get install -y docker.io
sudo systemctl enable docker
sudo systemctl start docker

# 3. Install Minikube and kubectl
curl -LO https://storage.googleapis.com/minikube/releases/latest/minikube_latest_amd64.deb
sudo dpkg -i minikube_latest_amd64.deb
sudo apt-get install -y kubectl

# 4. Start Minikube using Docker driver
sudo minikube start --driver=docker

# 5. Wait for cluster to be ready
sleep 60

# 6. Clone the GitHub repo
cd /home/ubuntu
git clone https://github.com/saifahmed2003/library-java17-mysql-app.git
cd library-java17-mysql-app/k8s

# 7. Apply Kubernetes YAMLs
kubectl apply -f mysql-deployment.yaml
kubectl apply -f mysql-service.yaml
kubectl apply -f app-deployment.yaml
kubectl apply -f app-service.yaml

# 8. Print out service info
kubectl get all
echo "Deployment complete! Access the app with: minikube service library-app-service --url"
