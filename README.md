<img height="52" width="52" src="https://raw.githubusercontent.com/tandpfun/skill-icons/65dea6c4eaca7da319e552c09f4cf5a9a8dab2c8/icons/Java-Dark.svg" /> <img height="52" width="52" src="https://raw.githubusercontent.com/tandpfun/skill-icons/main/icons/Spring-Dark.svg" /> <img height="52" width="52" src="https://raw.githubusercontent.com/tandpfun/skill-icons/main/icons/PostgreSQL-Dark.svg" />  <img height="52" width="52" src="https://raw.githubusercontent.com/tandpfun/skill-icons/main/icons/Docker.svg" /> 

---

# FIFA 24 Match Tracker API

API desenvolvida para registrar resultados de partidas de FIFA 24 entre amigos. Com esta API, é possível cadastrar jogadores, criar partidas e associá-las aos jogadores, oferecendo controle sobre as partidas realizadas.

## Tecnologias Utilizadas

- **Java** e **Spring Boot** - Para desenvolvimento backend.
- **PostgreSQL** - Banco de dados relacional usado para armazenar os dados.
- **Docker** - Para o ambiente de banco de dados isolado.
- **Lombok** - Redução de código boilerplate.
- **Spring Data JPA** - Operações de consulta, gravação, leitura e exclusão de dados.
- **Spring Validation** - Validação de entrada de dados.
- **Spring Dev Tools** - Live reloading para um desenvolvimento mais fluido.

## Estrutura da Aplicação

A aplicação é estruturada em várias camadas para garantir modularidade e manutenibilidade:

1. **Models** - Definem as entidades `Player` e `Match`, com suas relações e restrições.
2. **DTOs** - Usados para validação inicial de dados antes de serem processados pela API.
3. **Services** - Implementam a lógica de negócio, incluindo regras como impedir que um jogador participe de uma partida contra si mesmo.
4. **Controllers** - Expõem os endpoints da API para cadastro e gerenciamento de jogadores e partidas.
5. **Exception Handling** - Tratamento de exceções para capturar erros do lado do cliente e servidor, garantindo respostas apropriadas.

## Endpoints da API

### 1. `/players`
- **POST** `/players` - Cadastrar um novo jogador.
- **GET** `/players` - Listar todos os jogadores.

### 2. `/match`
- **POST** `/match` - Criar uma nova partida entre dois jogadores (não é permitido que um jogador jogue contra si mesmo).
- **GET** `/match` - Listar todas as partidas.

## Pré-requisitos

- **Java 17** ou superior.
- **Maven** para gerenciamento de dependências.
- **Docker** para executar o container do PostgreSQL.

## Configuração e Execução da Aplicação

### 1. Clone o repositório

```bash
git clone https://github.com/fifa24-match-tracker/fifa24-match-tracker.git
cd fifa24-match-tracker
```

### 2. Configure o Banco de Dados

A aplicação utiliza o PostgreSQL, que é configurado para ser executado em um container Docker.

1. Execute o container do PostgreSQL com o seguinte comando:

   ```bash
   docker run --name postgres-fifa24 -e POSTGRES_USER=seu_usuario -e POSTGRES_PASSWORD=sua_senha -e POSTGRES_DB=seu_banco -p 5432:5432 -d postgres
   ```

2. No arquivo `application.properties`, configure as credenciais do PostgreSQL:

   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/seu_banco
   spring.datasource.username=seu_usuario
   spring.datasource.password=sua_senha
   ```

### 3. Compile o Projeto

No diretório raiz do projeto, execute:

```bash
mvn clean install
```

### 4. Execute a Aplicação

Para iniciar a aplicação, execute:

```bash
mvn spring-boot:run
```

A aplicação estará disponível em `http://localhost:8080`.

## Exemplos de Uso

### Criar um Novo Jogador

**POST** `/players`

```json
{
  "name": "Jogador 1",
  "nickname": "J1"
}
```

### Criar uma Nova Partida

**POST** `/match`

```json
{
  "player1Id": 1,
  "player2Id": 2,
  "scorePlayer1": 3,
  "scorePlayer2": 1
}
```

> **Nota**: A API não permite que um jogador jogue uma partida consigo mesmo. Se essa regra for violada, uma resposta de erro será retornada.

## Tratamento de Erros

A API trata as exceções comuns para garantir respostas adequadas aos usuários:

- **400 Bad Request** - Dados inválidos enviados pelo cliente.
- **404 Not Found** - Recurso solicitado não encontrado.
- **500 Internal Server Error** - Erro interno no servidor.

## Contribuições

Contribuições são bem-vindas! Sinta-se à vontade para abrir um *pull request* ou reportar *issues*.

## Contato

Desenvolvido por [Seu Nome](https://github.com/natanzeraa).
