# 📦 Samsung Fake Store API

Esta é uma aplicação que orquestra requisições para uma api de produtos e realiza filtros com base no id do pedido, data do pedido e usuário fornecido

---

## 🚀 Como executar o projeto

### Pré-requisitos
- Java 17 ou superior
- Maven ou Gradle
- (Opcional) Docker

### Compile e execute o projeto:
```bash
mvn spring-boot:run
# ou

./mvnw spring-boot:run
```

###  Acesso a aplicação

A aplicação ficará disponível em:
```
http://localhost:8081
```

### 📑 Documentação da API (Swagger)
Após iniciar a aplicação, acesse:
```bash
http://localhost:8081/swagger-ui/index.html
```

para visualizar e testar os endpoints disponíveis.

### ⚙ Configurações no application.properties
```
server.port=8081
```
