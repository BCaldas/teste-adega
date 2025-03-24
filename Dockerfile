# Usa uma imagem base do Maven para compilar o projeto
FROM maven:3.8.6-eclipse-temurin-17 AS build

# Define o diretório de trabalho
WORKDIR /app

# Copia os arquivos do projeto para dentro do contêiner
COPY pom.xml .
COPY src ./src

# Compila o projeto e gera o JAR
RUN mvn clean package -DskipTests

# Usa uma imagem base do JDK 17 para rodar a aplicação
FROM eclipse-temurin:17-jdk

# Define o diretório de trabalho
WORKDIR /app

# Copia o JAR gerado da fase anterior
COPY --from=build /app/target/*.jar app.jar

# Expondo a porta que a aplicação utiliza
EXPOSE 8080

# Comando para rodar a aplicação
CMD ["java", "-jar", "app.jar"]