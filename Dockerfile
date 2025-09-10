FROM maven:3.9.6-eclipse-temurin-17 AS builder

LABEL authors="GabrielSalazarLozada"

# Crear directorio de trabajo
WORKDIR /app

# Copiar archivos de configuración y dependencias
COPY pom.xml .
COPY src ./src

# Compilar y empaquetar la aplicación
RUN mvn clean package -DskipTests

# Etapa 2: Imagen final más ligera
FROM eclipse-temurin:17-jdk-alpine

# Crear directorio de trabajo
WORKDIR /app

# Copiar el .jar generado desde la etapa anterior
COPY --from=builder /app/target/*.jar app.jar

# Exponer el puerto que usará Spring Boot
EXPOSE 8080

# Comando para ejecutar la app
ENTRYPOINT ["java","-jar","app.jar"]
