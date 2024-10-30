<img height="52" width="52" src="https://raw.githubusercontent.com/tandpfun/skill-icons/65dea6c4eaca7da319e552c09f4cf5a9a8dab2c8/icons/Java-Dark.svg" /> <img height="52" width="52" src="https://raw.githubusercontent.com/tandpfun/skill-icons/main/icons/Spring-Dark.svg" /> <img height="52" width="52" src="https://raw.githubusercontent.com/tandpfun/skill-icons/main/icons/PostgreSQL-Dark.svg" />  <img height="52" width="52" src="https://raw.githubusercontent.com/tandpfun/skill-icons/main/icons/Docker.svg" /> 

---

# FC Match Tracker API

API desenvolvida para registrar resultados de partidas de FIFA 24/25 entre amigos. Com esta API, é possível cadastrar jogadores, criar partidas e associá-las aos jogadores, oferecendo controle sobre as partidas realizadas.

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

### 2. Execute a aplicação da seguinte maneira

A aplicação utiliza o PostgreSQL, que é configurado para ser executado em um container Docker usando o docker compose.

1. Execute o container do PostgreSQL com o seguinte comando dentro da pasta do projeto:

   ```bash
   docker compose up -d --build
   ```

   
> Ou você pode simplesmente clicar no botão indicado na imagem abaixo caso esteja usando o Eclipse IDE

<a href="https://drive.google.com/uc?export=view&id=1ygCqtEkEsFbD2lcr9pCvP_HMcpb-xPl3">
<img src="https://drive.google.com/uc?export=view&id=1ygCqtEkEsFbD2lcr9pCvP_HMcpb-xPl3" style="width: 650px; max-width: 100%; height: auto" title="Clique para ver a imagem em tela cheia" />
</a>


2. No arquivo `application.properties`, configure as credenciais do PostgreSQL:

   ```properties
   spring.datasource.url=jdbc:postgresql://fc-posgtfres:5432/admin
   spring.datasource.username=admin
   spring.datasource.password=admin
   spring.jpa.hibernate.ddl-auto=update
   ```

A API estará disponível em `http://localhost:8080`.
O painel administrativo do PG Admin estará disponível em `http://localhost:8082`.

## Exemplos de Uso

### Criar um Novo Jogador

**POST** `/players`

```json
{
	"name": "Felipe Albuquerque", 
	"email": "fe_albuquerque@email.com"
}

```
<a href="https://drive.google.com/uc?export=view&id=1XCo6lN1FWJVJHWjWU_3lIeTBXjlCTURf">
<img src="https://drive.google.com/uc?export=view&id=1XCo6lN1FWJVJHWjWU_3lIeTBXjlCTURf" style="width: 650px; max-width: 100%; height: auto" title="Clique para ver a imagem em tela cheia" />
</a>

### Criar uma Nova Partida

**POST** `/match`

```json
{
  "homePlayerGoals": 3,
  "awayPlayerGoals": 5,
  "homePlayer": "80c23433-979e-4480-b472-3fa217770381", 
  "awayPlayer": "08ab435c-07b7-4d4a-be37-6502d9c1ed83"
}
```

<a href="https://drive.google.com/uc?export=view&id=11j6bt9FAsINzLgbxu3r0XZlUpkOxHg7X">
<img src="https://drive.google.com/uc?export=view&id=11j6bt9FAsINzLgbxu3r0XZlUpkOxHg7X" style="width: 650px; max-width: 100%; height: auto" title="Clique para ver a imagem em tela cheia" />
</a>


Você pode baixar o arquivo de requests aqui [FC Match Tracker API Requests](https://drive.google.com/file/d/1qgGCGep3vdNngn7V5xXwdDdJl0l93awE/view?usp=drive_link)

> **Nota**: A API não permite que um jogador jogue uma partida consigo mesmo. Se essa regra for violada, uma resposta de erro será retornada.

## Tratamento de Erros

A API trata as exceções comuns para garantir respostas adequadas aos usuários:

- **400 Bad Request** - Dados inválidos enviados pelo cliente.
- **404 Not Found** - Recurso solicitado não encontrado.
- **500 Internal Server Error** - Erro interno no servidor.

## Contribuições

Contribuições são bem-vindas! Sinta-se à vontade para abrir um *pull request* ou reportar *issues*.

## Contato

Desenvolvido por [Natan Oliveira](https://www.linkedin.com/in/natan-oliveira-71023822b/).
