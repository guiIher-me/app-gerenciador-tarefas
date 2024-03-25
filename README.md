# app-gerenciador-tarefas
[![License](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)

Implementação/Estudo de um app web para gerenciamento de tarefas colaborativa concebido para a disciplina de Sistemas Web, do curso de Bacharelado em Ciências da Computação, na Instituição Universidade Estadual Paulista "Júlio de Mesquita Filho" - UNESP

## 🎯 Objetivo

O objetivo desta proposta é apresentar um **sistema de gerenciamento de tarefas**, uma
ferramenta popular para organização de projetos. Nosso sistema oferecerá uma plataforma
intuitiva e eficiente para que usuários possam criar, atribuir e monitorar tarefas em diferentes
estágios de conclusão.

## 💻 Pré-requisitos

- **Java SDK 17**: Este é o requisito principal do Spring Boot, que requer Java 17 ou superior. Você pode baixar e instalar o Java no site oficial: https://www.oracle.com/java/technologies/downloads/
- **Maven 3.6.3 ou posterior**: Maven é uma ferramenta de automação de construção usada para gerenciar dependências e construir seu projeto. Você pode baixar e instalar o Maven no site oficial do Apache: https://maven.apache.org/download.cgi
- **PostgreSQL Database**: será necessário instalá-lo e configurá-lo na máquina. Você pode baixar e instalar o PostgreSQL no site oficial: https://www.postgresql.org/download/

> Caso tenha Docker instalado e configurado, rode o comando `docker-compose up --build`  para construir e inicializar o projeto sem instalar manualmente os requisitos necessários.


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

 1. Execute o comando `cd backend`  para ir para o diretório raiz do backend;
 2. Execute o comando `mvnw spring-boot:run` para inicializar o projeto;
 
> Utilize a **collection postman** disponível no diretório raiz do projeto para obter mais informações sobre os endpoints disponíveis, além de exemplos de requests e responses.



## 📖 Diagrama de Classes UML
<br>
<p align="center">
<img src="https://github.com/guiIher-me/app-gerenciador-tarefas/blob/main/diagrams/mvp1.1/diagrama_classes_uml_mvp1.1.png" width="600">
</p>


## 📜  Licença
Esse projeto está sob licença MIT. Veja o arquivo [LICENSE](https://github.com/guiIher-me/app-gerenciador-tarefas/blob/main/LICENSE) para mais detalhes.
