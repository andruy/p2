# p2

## Introduction

p2 was created by Andrew Anderson, Sidi Gassama, Luis Marquez, and Elisa Pavesi. 

## Requirements:

* Each team must configure Prometheus to retrieve metrics from the deployed application
* Teams must create custom metrics through the micrometer API
* 3 member teams must create at least 1 custom metric
* 4 member teams must create at least 2 custom metrics
* These will be incorporated into the SLOs, so they should indicate something about the quality of the application
* Each team must define an SLO for their application.
* Must include error rates as well as latencies
* Must include their custom metrics
* Each team must define custom recording and alerting rules based on their SLOs
* These must follow the multi-window, multi-burn rate approach
* Each team must have a full DevOps pipeline built using Jenkins
* This pipeline must be configured through a Jenkinsfile and triggered in response to a webhook
* The project will be deployed with a canary deployment model
* Custom Dockerfiles must be designed and used
* No more usage of mvn spring-boot:build-image
* However, teams may design their Dockerfiles based on it
* Each team must create at least 1 Grafana dashboard to track all of the metrics associated with their SLO
* Teams with 4 members must also have additional panels
* Could be in a second dashboard
* Examples could be tracking relevant logs of the application or cpu/memory usage from Prometheus
* The more closely this additional dashboard indicates potential issue scenarios, the better
* The idea is to have visualizations that might indicate that a problem might occur soon, even if an alert has not fired yet


## Technologies Used
* Java
* Spring Boot
* Spring AOP
* Log4J
* Loki
* Grafana
* Prometheus
* Kubernetes
