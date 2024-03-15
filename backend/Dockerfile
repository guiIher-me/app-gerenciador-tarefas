# Use a imagem base com o Maven
FROM maven:3.8.4-openjdk-17-slim AS build

# Define o diretório de trabalho dentro do contêiner
WORKDIR /app

# Copia o arquivo pom.xml para o diretório de trabalho
COPY pom.xml .

# Copia os arquivos de origem para o diretório de trabalho
COPY src ./src

# Compila o projeto com o Maven
RUN mvn clean package

# Segunda etapa - apenas a aplicação compilada é copiada para a imagem final
FROM openjdk:17-jdk-slim

# Define o diretório de trabalho dentro do contêiner
WORKDIR /app

# Copia o JAR da aplicação compilada da etapa anterior para o diretório de trabalho
COPY --from=build /app/target/api-task-manager.jar ./

# Expor a porta 8080
EXPOSE 8080

# Define o comando padrão para executar a aplicação quando o contêiner for iniciado
CMD ["java", "-jar", "api-task-manager.jar"]