# app-gerenciador-tarefas
[![License](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)

Implementação/Estudo de um app web para gerenciamento de tarefas colaborativa concebido para a disciplina de Sistemas Web, do curso de Bacharelado em Ciências da Computação, na Instituição Universidade Estadual Paulista "Júlio de Mesquita Filho" - UNESP


## 🎯 Objetivo

O objetivo deste projeto é apresentar um **sistema de gerenciamento de tarefas**, uma
ferramenta popular para organização de projetos. Nosso sistema oferecerá uma plataforma
intuitiva e eficiente para que usuários possam criar, atribuir e monitorar tarefas em diferentes
estágios de conclusão.


## 💻 Pré-requisitos

- **Java SDK 17**: Este é o requisito principal do Spring Boot, que requer Java 17 ou superior. Você pode baixar e instalar o Java no site oficial: https://www.oracle.com/java/technologies/downloads/
- **Maven 3.6.3 ou posterior**: Maven é uma ferramenta de automação de construção usada para gerenciar dependências e construir seu projeto. Você pode baixar e instalar o Maven no site oficial do Apache: https://maven.apache.org/download.cgi
- **PostgreSQL Database**: será necessário instalá-lo e configurá-lo na máquina. Você pode baixar e instalar o PostgreSQL no site oficial: https://www.postgresql.org/download/

> Caso você tenha Docker instalado e configurado, não será preciso instalar manualmente os requisitos necessários. Para saber mais, leia a seção "**Como executar via Docker**".


## 📁 Estrutura Backend

```
..
├── api
│   ├── controller   # Controla as requisições HTTP
│   ├── dto          # Contém os objetos de transferência de dados (DTOs)
│   ├── exception    # Define tratamento de exceções específicas
│   ├── model        # Contém as classes de modelagem de banco de dados
│   ├── repository   # Contém as classes de acesso ao banco de dados
│   ├── security     # Contém as classes de segurança da API
│   ├── service      # Contém as classes de serviço da API
│   └── util         # Contém as classes de utilidades

```


## 🚀 Como Executar

No diretório raiz do projeto, execute o terminal linux e siga o passo-a-passo abaixo:
 1. No arquivo **application.properties**, modifique a propriedade *spring.datasource.url* para: `spring.datasource.url=jdbc:postgresql://localhost:5432/task_manager`
 2. Execute o comando `cd backend`  para ir para o diretório raiz do backend;
 3. Execute o comando `mvnw spring-boot:run` para inicializar o projeto;
 4. Enjoy!


## 🐋 Como Executar via Docker

No diretório raiz do projeto, execute o terminal e siga o passo-a-passo abaixo:
 1. No arquivo **application.properties**, modifique a propriedade *spring.datasource.url* para: `spring.datasource.url=jdbc:postgresql://db:5432/task_manager`
 2. Execute o comando `cd backend` para ir para o diretório raiz do backend;
 3. Inicie o Docker. Caso esteja usando WSL, habilite a integração no app Docker Desktop > Settings > Resources > "Enable integration with additional distros";
 4. Rode o comando `docker-compose up --build`  para construir e inicializar o projeto;
 5. Enjoy!

#### Como executar comandos SQL no DB Postgres via Docker:
 1. Execute o comando `cd backend` para ir para o diretório raiz do backend;
 2. Rode o comando `docker ps` para ver os containers que estão atualmente em execução;
 3. Copie o *CONTAINER ID* da imagem postgres;
 4. Execute o comando `docker exec -it containerid  bash`, substituindo '*containerid*' pelo id copiado no passo 3. Este comando executará o bash da imagem postgres;
 5. Execute o comando `psql task_manager` para acessar o banco de dados '*task_manager*' do projeto;
 6. Execute os comandos SQL que desejar, como por exemplo: `SELECT * FROM users;`;
 7. Para sair do termimal bash da imagem execute o comando `exit` 2 vezes seguidas.

<br/>

## 📨 Requisições e Respostas HTTP via POSTMAN
 - Utilize a **Collection Postman** disponível no diretório raiz do projeto para obter mais informações sobre os endpoints disponíveis, além de exemplos de requests e responses;
 - Ao realizar o login diretamente pela collection, o bearer-token será salvo automaticamente como variável global da collection, permitindo executar os demais endpoints facilmente;

> Você pode [pré-visualizar a collection aqui](https://github.com/guiIher-me/app-gerenciador-tarefas/blob/main/collection.md) gerada automaticamente via comando `postman-to-markdown Task\ Manager\ \[UNESP\].postman_collection.json` da lib postman-to-markdown.

## 📖 Diagrama de Classes UML
<br>
<p align="center">
<img src="https://github.com/guiIher-me/app-gerenciador-tarefas/blob/main/diagrams/mvp1.1/diagrama_classes_uml_mvp1.1.png" width="600">
</p>


## 📜  Licença
Esse projeto está sob licença MIT. Veja o arquivo [LICENSE](https://github.com/guiIher-me/app-gerenciador-tarefas/blob/main/LICENSE) para mais detalhes.
