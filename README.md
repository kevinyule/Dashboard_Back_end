# Dashboard-backend

Backend project for Dashboard

# Construir la imagen
docker build -t dashboard-app .

# Ejecutar el contenedor
docker run --name dashboard-app -p 8080:8080 dashboard-app
