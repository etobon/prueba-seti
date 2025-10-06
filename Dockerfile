# Usa una imagen base con Java 22
FROM eclipse-temurin:22-jdk

# Crea el directorio de trabajo
WORKDIR /app

# Copia el JAR generado (asegúrate de haber hecho mvn clean package antes)
COPY target/*.jar app.jar

# Expone el puerto de la app
EXPOSE 8080

# Comando de arranque
ENTRYPOINT ["java", "-jar", "app.jar"]
