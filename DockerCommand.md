# Docker Command 


🧩 One-Line Summary

### Docker commands = image lifecycle + container lifecycle + cleanup


| Category       | Command                        | Brief Description                       |
|----------------|--------------------------------|-----------------------------------------|
| General        | docker --version               | Show Docker version                     |
| General        | docker info                    | Display system-wide Docker information  |


### Docker Compose
| Category       | Command                        | Brief Description                       |
|----------------|--------------------------------|-----------------------------------------|
| Docker Compose | docker compose up              | Start services                          |
| Docker Compose | docker compose up -d           | Start services in background            |
| Docker Compose | docker compose down            | Stop & remove services                  |
| Docker Compose | docker compose ps              | List compose containers                 |
| Docker Compose | docker compose logs            | View compose logs                       |


### Containers
| Category       | Command                        | Brief Description                       |
|----------------|--------------------------------|-----------------------------------------|
| Containers     | docker ps                      | List running containers                 |
| Containers     | docker ps -a                   | List all containers (running + stopped) |
| Containers     | docker run <image>             | Create and start a container            |
| Containers     | docker run -d <image>          | Run container in detached mode          |
| Containers     | docker start <container>       | Start stopped container                 |
| Containers     | docker stop <container>        | Stop running container                  |
| Containers     | docker restart <container>     | Restart container                       |
| Containers     | docker rm <container>          | Remove container                        |
| Containers     | docker rm -f <container>       | Force remove running container          |

### Images

| Category       | Command                        | Brief Description                       |
|----------------|--------------------------------|-----------------------------------------|
| Images         | docker images                  | List all local Docker images            |
| Images         | docker pull <image>            | Download image from Docker Hub          |
| Images         | docker build -t <name> .       | Build image from Dockerfile             |
| Images         | docker rmi <image>             | Remove Docker image                     |
| Images         | docker image prune             | Remove unused images                    |

###  Logs & Debug
| Category       | Command                        | Brief Description                       |
|----------------|--------------------------------|-----------------------------------------|
| Logs & Debug   | docker logs <container>        | View container logs                     |
| Logs & Debug   | docker logs -f <container>     | Follow container logs                   |
| Logs & Debug   | docker exec -it <container> sh | Access container shell                  |
| Logs & Debug   | docker inspect <container>     | Detailed container info                 |

### Networking
| Category       | Command                        | Brief Description                       |
|----------------|--------------------------------|-----------------------------------------|
| Networking     | docker network ls              | List Docker networks                    |
| Networking     | docker network create <name>   | Create a network                        |
| Networking     | docker network rm <name>       | Remove a network                        |
| Networking     | docker port <container>        | Show container port mappings            |

### Volumes
| Category       | Command                        | Brief Description                       |
|----------------|--------------------------------|-----------------------------------------|
| Volumes        | docker volume ls               | List Docker volumes                     |
| Volumes        | docker volume create <name>    | Create a volume                         |
| Volumes        | docker volume rm <name>        | Remove a volume                         |
| Volumes        | docker volume prune            | Remove unused volumes                   |

### Cleanup
| Category       | Command                        | Brief Description                       |
|----------------|--------------------------------|-----------------------------------------|
| Cleanup        | docker system df               | Show Docker disk usage                  |
| Cleanup        | docker system prune            | Remove unused data                      |
| Cleanup        | docker container prune         | Remove stopped containers               |


Lists all currently RUNNING containers

If a container is not running, it will not appear here.

**docker ps**
🔍 Typical Output Explained
CONTAINER ID   IMAGE        COMMAND                  STATUS       PORTS                   NAMES
70d496abbf4a   mongo:7.0.1  "docker-entrypoint.s…"   Up 2 minutes  0.0.0.0:27017->27017/tcp mongodb

| Column       | Meaning                          |
|--------------|----------------------------------|
| CONTAINER ID | Unique container identifier      |
| IMAGE        | Image used to create container   |
| COMMAND      | Startup command inside container |
| STATUS       | Running time / current state     |
| PORTS        | Port mapping (host → container)  |
| NAMES        | Human-friendly container name    |



