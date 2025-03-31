# Descrição

Projeto Spring Boot de exemplo de interação com um banco de dados não relacional MongoDB. São utilizados neste projeto Spring Boot Starter MongoDB, Lombok, DevTools e documentação via Swagger através do Spring Doc da OpenAPI. São abordados neste projeto também conceitos como o de consultas nativas com NativeQuery, tratativas de exceções com mensagens personalizadas e Bean Validation.

# Rodando o container MongoDB com persistência

docker run --name mongodb-container -v /opt/mongodb:/data/db --restart=unless-stopped -p 27017:27017 -d mongo:4.4.6
