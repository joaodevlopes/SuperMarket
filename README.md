# 🛒 Supermarket API

API RESTful para gerenciamento de estoque de supermercado, com frontend simples em HTML, CSS e JavaScript.

**Objetivo principal:** aprender validações, DTOs e tratamento global de exceções em Spring Boot.

---

## 🚀 Tecnologias

### Backend
- Java 17+
- Spring Boot
- Spring Data JPA (com MySQL)
- Bean Validation (Jakarta Validation)
- MapStruct (Entity ↔ DTO)
- Lombok
- Exception Handling centralizado (@RestControllerAdvice)
- Configuração customizada de CORS

### Frontend
- HTML5
- CSS3
- JavaScript (Fetch API)

---

## ⚙️ Funcionalidades

- **Criar produto:** `POST /market`
- **Listar produtos:** `GET /market`
- **Atualizar produto:** `PUT /market/{id}`
- **Excluir produto:** `DELETE /market/{id}`
- **Buscar por nome:** `GET /market/search?name=...`

### Estrutura de um produto (JSON)
```json
{
  "id": 1,
  "name": "Arroz Integral",
  "description": "Pacote de 5kg",
  "price": 25.90,
  "quantity": 50,
  "date": "2025-09-13"
}
```
### Estrutura do projeto

- `src/main/java/com/supermarket/supermarket/`
  - `controller/`
    - `MarketController.java` – Endpoints REST
  - `service/`
    - `MarketService.java` – Lógica de negócio
  - `repository/`
    - `MarketRepository.java` – Interface JPA
  - `entity/`
    - `MarketEntity.java` – Entidade JPA
  - `dto/`
    - `MarketCreateDto.java` – Dados de entrada
    - `MarketResponseDto.java` – Dados de saída
    - `ApiError.java` – DTO de erro
  - `mapper/`
    - `MarketMapper.java` – MapStruct mapeamentos
  - `exception/`
    - `GlobalExceptionHandler.java` – Handler centralizado
    - `ResourceNotFoundException.java`
  - `CORS/`
    - `CorsConfig.java` – Configuração de CORS
    
### Front end
Interface em HTML + CSS + JS que consome os endpoints do backend:

Formulário para cadastrar novos produtos

Tabela dinâmica com listagem

Edição e exclusão de produtos

Busca em tempo real por nome

Notificações de sucesso/erro (toast)

### COMO RODAR LOCALMENTE:
## 1. Clonar o repositório
- **CLONAR O REPOSITTÓRIO :** `git clone https://github.com/seu-usuario/SuperMarket.git`
- **IR PARA O LOCAL DO ARQUIVO :** `cd supermarket-api`
## 2. Configurar o MySQL
Crie o banco de dados supermarket no MySQL e configure suas credenciais em src/main/resources/application.properties:
# Configuração do banco de dados
spring.datasource.url=jdbc:mysql://localhost:3306/supermarket
spring.datasource.username=SEU_USUARIO_AQUI
spring.datasource.password=SUA_SENHA_AQUI
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# Configuração JPA
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
Rodar o Backend

Usando Maven:

mvn spring-boot:run


Ou, se já tiver o .jar gerado:

java -jar target/supermarket-0.0.1-SNAPSHOT.jar


O backend ficará disponível em:
http://localhost:8080/market

### 4. Rodar o Frontend

Abrir index.html diretamente no navegador
ou

Usar o Live Server (VSCode) → acessa em: http://localhost:5500


### 📌 Aprendizados

DTOs: separar entrada (CreateDto) e saída (ResponseDto)

Bean Validation: validar dados antes de persistir

Tratamento centralizado de erros: respostas JSON padronizadas com mensagens claras

Padrão em camadas: Controller → Service → Repository

Integração Front + Back: fetch() e JSON

