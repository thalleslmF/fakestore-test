## samsung-fakestore

Este projeto contém uma aplicação completa composta por:

* **Frontend JSF (PrimeFaces)** na porta `8080`
* **Backend Spring Boot (API REST)** na porta `8081`

O objetivo é simular consultas em uma loja (FakeStore), baseado em filtros como id do pedido, usuário e data do pedido.

---

## 🚀 Como rodar o projeto

### Backend (Spring Boot - porta 8081)

#### Requisitos:

* Java 17+
* Maven 3.6+

#### Execute:

```bash
cd samsung-fakestore-api
mvn spring-boot:run
```

ou

```bash
cd samsung-fakestore-api
./mvnw spring-boot:run
```

✅ A API estará disponível em:

```
http://localhost:8081
```

✅ A documentação Swagger estará em:

```
http://localhost:8081/swagger-ui/index.html
```

---

### 🖥 Frontend (JSF - porta 8080)

#### Requisitos:

* Java 17+
* Maven 3.6+
* Servidor de aplicação compatível (WildFly, Payara, TomEE etc.)

#### Build e deploy:

```bash
cd frontend
mvn clean package
```

➡ Gere o WAR e faça o deploy no seu servidor (ex.: WildFly).

✅ O frontend estará disponível em:

```
http://localhost:8080/samsung-fakestore-ui/index.xhtml
```

💡 O frontend se comunica com a API no `http://localhost:8081`.

---

## ⚙ Configurações

### Backend - `application.properties`

```properties
server.port=8081
springdoc.swagger-ui.path=/swagger-ui.html
```




## 🛠 Tecnologias utilizadas

* **Frontend:** JSF, PrimeFaces
* **Backend:** Spring Boot, Spring Web, Springdoc OpenAPI
* **Java 17**
* **Maven**

---

## 📄 Licença

Este projeto está licenciado sob a licença MIT.
