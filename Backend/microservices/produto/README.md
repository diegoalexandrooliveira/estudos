# Configurações

### Subir localstack

docker run -d --name aws -e SERVICES=sqs -p 4566:4566 -p 4571:4571 -p 8079:8080 localstack/localstack

### Criar uma fila

aws --endpoint-url=http://localhost:4566 sqs create-queue --queue-name novoProduto

### Receber mensagem da fila

aws --endpoint-url=http://localhost:4566 sqs receive-message --queue-url http://localhost:4566/000000000000/novoProduto