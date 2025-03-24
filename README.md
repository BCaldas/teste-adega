# Teste Adega
Projeto criado para o teste da empresa Digio, simulando um microserviço para uma adega.

## Features
### Endpoints
- GET: `/compras` - Retornar uma lista das compras ordenadas de forma crescente por valor, deve conter o nome dos clientes, cpf dos clientes, dado dos produtos, quantidade das compras e valores totais de cada compra.
- GET: `/compras/maior-compra/ano` - (Exemplo: /maior_compra/2016) - Retornar a maior compra do ano informando os dados da compra disponibilizados, deve ter o nome do cliente, cpf do cliente, dado do produto, quantidade da compra e seu valor total.
- GET: `/clientes/clientes-fieis` - Retornar o Top 3 clientes mais fieis, clientes que possuem mais compras recorrentes com maiores valores.
- GET: `/produtos/recomendacao/cliente/tipo` - Retornar uma recomendação de vinho baseado nos tipos de vinho que o cliente mais compra.

### Automatização
- Popolução do Banco de Dados durante a inicialização da aplicação.

## Melhorias
- [ ] Mudar id para uuid
- [ ] Tratamento personalizado em caso de erro de negócio
- [ ] Health Check
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
