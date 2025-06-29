#!/bin/bash

APP_NAME="primefaces-front"
PROJECT_DIR="/home/thallesf/Documentos/primefaces-front"
SRC_DIR="$PROJECT_DIR/src"
TARGET_DIR="$PROJECT_DIR/target/$APP_NAME"
TOMCAT_DIR="$PROJECT_DIR/apache-tomcat-9.0.78"
DEPLOY_DIR="$TOMCAT_DIR/webapps/$APP_NAME"

# Verifica se diretórios existem
for dir in "$SRC_DIR" "$TARGET_DIR" "$DEPLOY_DIR"; do
  if [ ! -d "$dir" ]; then
    echo "Diretório não encontrado: $dir"
    echo "Por favor, crie/copiei manualmente antes de rodar."
    exit 1
  fi
done

echo "Monitorando mudanças na pasta $SRC_DIR"

while true; do
  # Espera por qualquer modificação na pasta src (arquivos .java, .xhtml, resources etc)
  inotifywait -r -e modify,attrib,close_write,move,create,delete "$SRC_DIR"

  echo "Mudança detectada, esperando estabilizar os arquivos..."
  sleep 3  # debounce para evitar builds repetidos

  echo "Construindo o projeto com Maven..."
  mvn clean package

  if [ $? -ne 0 ]; then
    echo "Build Maven falhou, ignorando deploy."
    continue
  fi

  echo "Sincronizando pasta exploded para Tomcat..."
  rsync -av --delete "$TARGET_DIR/" "$DEPLOY_DIR/"
  echo "Forçando reload via Tomcat Manager API..."
  curl --silent --user admin:admin "http://localhost:8080/manager/text/reload?path=/primefaces_front_war_exploded"
  echo "Reload solicitado."

  echo "Deploy atualizado em Tomcat."
done
