#!/bin/bash

WAR_NAME="primefaces-front"
PORT=8080
CONTAINER_NAME="tomcat-primefaces"

echo "🔁 Removendo container anterior (se existir)..."
if docker ps -a --format '{{.Names}}' | grep -Eq "^${CONTAINER_NAME}$"; then
  docker rm -f $CONTAINER_NAME
fi

echo "🔨 Gerando WAR com Maven..."
mvn clean package || { echo "❌ Erro no build"; exit 1; }

echo "🐳 Subindo Tomcat 9 via Docker..."
docker run -d \
  -p $PORT:8080 \
  --name $CONTAINER_NAME \
  tomcat:9.0
docker exec $CONTAINER_NAME rm -rf /usr/local/tomcat/webapps/primefaces-front
docker exec $CONTAINER_NAME rm -f /usr/local/tomcat/webapps/primefaces-front.war
echo "📦 Copiando WAR para o container..."
docker cp target/$WAR_NAME.war $CONTAINER_NAME:/usr/local/tomcat/webapps/

echo "⏳ Aguardando Tomcat subir..."
sleep 10

echo "🌐 Abrindo no navegador..."
xdg-open "http://localhost:$PORT/$WAR_NAME/index.xhtml" 2>/dev/null || open "http://localhost:$PORT/$WAR_NAME/index.xhtml"
