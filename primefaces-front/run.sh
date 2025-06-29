#!/bin/bash

WILDFLY_VERSION=36.0.1.Final
WILDFLY_DIR=wildfly-$WILDFLY_VERSION
WILDFLY_FILE=wildfly-$WILDFLY_VERSION.tar.gz
WILDFLY_URL="https://github.com/wildfly/wildfly/releases/download/$WILDFLY_VERSION/$WILDFLY_FILE"
WILDFLY_HOME=$(pwd)/$WILDFLY_DIR
WAR_PATH=target/primefaces-front.war
APP_NAME=primefaces-front

if [ ! -d "$WILDFLY_DIR" ]; then
    echo "Baixando WildFly $WILDFLY_VERSION..."
    curl -LO "$WILDFLY_URL"
    echo "Descompactando WildFly..."
    tar xzf "$WILDFLY_FILE"
fi

echo "Rodando build Maven..."
mvn clean package

if [ ! -f "$WAR_PATH" ]; then
    echo "WAR não encontrado em $WAR_PATH. Abortando."
    exit 1
fi

echo "Iniciando WildFly..."
$WILDFLY_HOME/bin/standalone.sh &

echo "Aguardando WildFly iniciar..."
until $WILDFLY_HOME/bin/jboss-cli.sh --connect --command=":read-attribute(name=server-state)" >/dev/null 2>&1; do
    sleep 2
done

echo "WildFly iniciado."

echo "Fazendo deploy..."

$WILDFLY_HOME/bin/jboss-cli.sh --connect --command="undeploy $APP_NAME.war" >/dev/null 2>&1 || true
$WILDFLY_HOME/bin/jboss-cli.sh --connect --command="deploy $WAR_PATH --name=$APP_NAME.war --force"

echo "Aguardando aplicação estar UP..."

MAX_RETRIES=30
COUNT=0
STATUS=""

while [ $COUNT -lt $MAX_RETRIES ]; do
    STATUS=$($WILDFLY_HOME/bin/jboss-cli.sh --connect --command="deployment-info --name=$APP_NAME.war" | grep "status" | awk '{print $2}' | tr -d ,)
    if [ "$STATUS" == "OK" ]; then
        echo "Aplicação está UP!"
        break
    fi
    echo "Status do deploy: $STATUS. Aguardando..."
    sleep 2
    COUNT=$((COUNT + 1))
done

if [ "$STATUS" != "OK" ]; then
    echo "Erro: aplicação não subiu corretamente. Status final: $STATUS"
    echo "Parando WildFly..."

    # Tenta parar o WildFly
    $WILDFLY_HOME/bin/jboss-cli.sh --connect --command=":shutdown" >/dev/null 2>&1 || {
        echo "Falha ao parar WildFly via CLI, tentando matar o processo..."
        pkill -f standalone.sh || true
    }

    echo "Removendo WildFly baixado..."
    rm -rf "$WILDFLY_HOME"

    echo "Saindo com erro."
    exit 1
fi

APP_URL="http://localhost:8080/$APP_NAME/index.html"
echo "Testando resposta HTTP da aplicação em $APP_URL..."

HTTP_STATUS=$(curl -s -o /dev/null -w "%{http_code}" "$APP_URL")

if [ "$HTTP_STATUS" == "200" ]; then
    echo "Aplicação respondendo HTTP 200 OK!"
else
    echo "Aviso: aplicação respondeu com HTTP $HTTP_STATUS"
fi

echo "Deploy concluído com sucesso!"
