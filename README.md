# Configuração do Docker no IntelliJ IDEA

Explicando como configurar e utilizar o Docker dentro do IntelliJ IDEA para iniciar o **pgAdmin** e o **Postgres** utilizando o arquivo `docker-compose.yml`.

---

## Pré-requisitos

Antes de começar, certifique-se de que:
1. O Docker está instalado e em execução no seu sistema.
    - Para baixar o Docker: [https://www.docker.com/get-started](https://www.docker.com/get-started)
2. O IntelliJ IDEA está instalado com o plugin do Docker habilitado.
    - Para habilitar o plugin, vá até `File > Settings > Plugins`, procure por "Docker" e instale-o.

---

## Passo a Passo

### 1. Abra o Docker
1. Certifique-se de que o Docker está aberto e em execução no seu sistema operacional.
2. Confirme que o Docker está funcionando corretamente:
    - No terminal, execute o comando `docker --version`. Isso deve retornar a versão instalada do Docker.

### 2. Localize o arquivo `docker-compose.yml`
1. Dentro do seu projeto, encontre a pasta que contém o arquivo `docker-compose.yml`.
2. Abra a pasta no IntelliJ IDEA.

### 3. Execute o `pgAdmin` e o `Postgres`
1. No arquivo `docker-compose.yml`, localize os serviços `pgadmin` e `postgres`.
2. Ao lado de cada serviço, clique no **ícone de play (▶)** para iniciar os contêineres.
    - O IntelliJ executará os serviços definidos no `docker-compose.yml`.

### 4. Verifique os contêineres
1. No IntelliJ IDEA, vá para a aba `Services` (no canto inferior direito da interface).
2. Expanda o menu **Docker > Containers** e verifique se os contêineres `pgadmin` e `postgres` estão em execução.

---

## Acessando o pgAdmin e o Postgres

### Acessar o pgAdmin
1. Abra um navegador e acesse: [http://localhost:15432] (ou a porta configurada no `docker-compose.yml`).
2. Faça login com o email e senha configurados no arquivo `docker-compose.yml`.

### Conectar ao Postgres via pgAdmin
1. No pgAdmin, adicione uma nova conexão (Server):
    - **Host**: `postgres` (ou o nome do serviço no `docker-compose.yml`).
    - **Maintenance Database**: `banco`
    - **Username/Password**: Utilize as credenciais configuradas no `docker-compose.yml`.

---

## Problemas Comuns

1. **Erro: Docker não está em execução**
    - Certifique-se de que o Docker Desktop está aberto antes de executar os serviços.
2. **Erro ao acessar o pgAdmin**
    - Verifique se o contêiner está em execução na aba `Services` do IntelliJ.
    - Verifique se a porta configurada no `docker-compose.yml` está disponível.

---

## Observações
- Qualquer modificação no `docker-compose.yml` requer que os contêineres sejam reiniciados para aplicar as alterações.
- Para parar os serviços, clique no botão **Stop (◼)** na aba `Services`.

Agora, você está pronto para usar o Docker no IntelliJ IDEA com o pgAdmin e o Postgres!
