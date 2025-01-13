# Reserva Microservice

Este proyecto es un microservicio para la gestión de reservas. Utiliza Spring Boot y MySQL.

## Requisitos

- Docker
- Docker Compose

## Instrucciones

### 1. Clonar el repositorio

```bash
git clone https://github.com/ErickRamirezO/reserva
```

2. Construir la imagen de Docker
```bash
docker build -t reserva-microservice .
```

3. Levantar el contenedor
```bash
docker-compose up -d
```

4. Acceder a la aplicación
La aplicación estará disponible en http://localhost:8082.