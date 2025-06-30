#  PrimeFaces Front

Projeto **Java EE (Jakarta)** com **PrimeFaces** e **Maven**, empacotado como **WAR**, pronto para ser implantado em um servidor de aplicações (WildFly, Payara, TomEE, etc).

---

## 🚀 Dependencias

- Java 17
- Jakarta EE 10 (CDI, Servlet, JSP, JSTL, Faces, REST)
- PrimeFaces 15 (jakarta)

---

## ⚙ Como compilar e gerar o WAR

Execute no terminal:
```bash
mvn clean package
```

## O arquivo gerado estará em:
```bash
target/primefaces-front.war
```

## Como executar
Implante o WAR gerado em um servidor de aplicações compatível (por exemplo, WildFly 26+, Payara 6, TomEE Jakarta).

Exemplo usando Docker + WildFly:

```bash
docker run -it --rm -p 8080:8080 \
  -v $(pwd)/target/primefaces-front.war:/opt/jboss/wildfly/standalone/deployments/primefaces-front.war \
  quay.io/wildfly/wildfly:36.1.0.Final

```
Para Linux é possivel executar o script  que empacota e faz deploy da aplicação:
```bash
./run.sh
```
A aplicação estará disponível em:
http://localhost:8080/primefaces-front



