docker container is a process running on the host machine
kubernetes is a container orchestration platform
pod is a group of containers
deployment kubernetes is a pod manager
service is a network endpoint

docker build -t auth-service:latest .
docker build -t user-service:latest .
docker build -t gateway:latest .

kubectl create namespace microservices

kubectl apply -f k8s/
kubectl apply -f user-service.yaml

kubectl get pods -n microservices
kubectl get svc -n microservices

kubectl logs -n microservices deployment/gateway

kubectl logs -n microservices user-6d6f9d6c8f-xyz34

kubectl logs -n microservices -f -l app=user --all-containers=true

kubectl exec -n microservices -it deployment/gateway -- sh

kubectl scale deployment user -n microservices --replicas=5

kubectl rollout restart deployment gateway -n microservices

kubectl rollout status deployment/user -n microservices

kubectl delete pod -n microservices -l app=gateway
kubectl scale deployment user-blue --replicas=0 -n microservices
kubectl delete deployment user-blue -n microservices

kubectl get endpoints -n microservices auth

docker tag user-service:latest <your-registry>/user-service:latest
docker push <your-registry>/user-service:latest
kubectl set image deployment/user -n microservices user=<your-registry>/user-service:latest

kubectl port-forward deployment/user-green 5000:4082 -n microservices
