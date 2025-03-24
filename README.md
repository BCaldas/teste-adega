# Teste Adega
Projeto criado para o teste da empresa Digio, simulando um microserviço para uma adega.

## Principais Tecnologias Utilizadas

- Java 17
- Spring Boot 3.4
- Spring Cloud 2024.0.1
- Maven
- MariaDB
- Docker

## Utilização
- Clone o repositório dentro da pasta desejada.

### Rodar Local
- Abra o projeto e modifique o arquivo application-local.yml para colocar as credenciais corretas do banco de dados de acordo com o seu SGBD
- Abra um shell na raiz do projeto e execute o comando `mvn sprin-boot:run`
- O banco de dados será populado automaticamente após o startup da aplicação.

### Rodar Via Docker
- Clone o repositório dentro da pasta desejada.
- Abra um shell na raiz do projeto e execute o comando `docker-compose up -d --build`
- O banco de dados será populado automaticamente após o startup da aplicação.

## Requerimentos
- Postman - https://www.postman.com/ ou outro cliente HTTP. A collection para o postman se encontra na raiz do projeto.

### Local
- Java 17
- Maven
- MariaDB

### Rodar Via Docker
- Docker
