# School System

Um sistema de gerenciamento escolar backend baseado em microserviços, desenvolvido em Java com Spring Boot. Este projeto suporta operações de gerenciamento de alunos, turmas, matrículas e frequência, com arquitetura orientada a APIs REST. Atualmente contém o serviço `academic-service` e está preparado para receber novos microserviços no futuro.

---

## Visão Geral da Arquitetura

Este projeto segue o padrão de microserviços. Cada serviço é responsável por uma área específica da aplicação, o que facilita manutenção, escalabilidade e evolução.

- `academic-service` - serviço atual responsável por gerenciamento acadêmico
- `enrollment-service` - serviço planejado para matrículas
- `classroom-service` - serviço planejado para gestão de turmas
- outros serviços relacionados podem ser adicionados no futuro

---

## Tecnologias Utilizadas

- Java
- Spring Boot
- Maven
- APIs REST
- Swagger / OpenAPI (para documentação de API)

> Observação: Docker pode ser integrado futuramente para containerização dos microserviços.

---

## Estrutura do Projeto

Exemplo de árvore de diretórios do repositório:

```text
school-system/
├── academic-service/
│   ├── pom.xml
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   └── resources/
│   │   └── test/
│   └── README.md
├── enrollment-service/   # futuro microserviço
├── classroom-service/   # futuro microserviço
└── README.md
```

---

## Funcionalidades Atuais

O serviço `academic-service` já oferece as seguintes funcionalidades:

- Gerenciamento de alunos
- Gerenciamento de turmas
- Gerenciamento de matrículas
- Controle de frequência

---

## Como Executar o Projeto

Siga estes passos para executar o serviço localmente.

```bash
# clonar o repositório
git clone https://github.com/SEU_USUARIO/school-system.git

# navegar até o serviço
cd school-system/academic-service

# executar com Maven
mvn spring-boot:run
```

Ou gerar e executar o JAR:

```bash
cd school-system/academic-service
mvn clean package
java -jar target/academic-service-0.0.1-SNAPSHOT.jar
```

---

## Documentação da API

A documentação interativa da API está disponível via Swagger/OpenAPI.

- URL padrão: `http://localhost:8081/swagger-ui/index.html`

Essa interface permite visualizar endpoints, parâmetros e testar chamadas diretamente no navegador.

---

## Melhorias Futuras

Possíveis evoluções para o projeto:

- Adicionar novos microserviços para separar responsabilidades
- Implementar autenticação e autorização
- Integrar com Docker e Docker Compose
- Adicionar testes automatizados mais abrangentes
- Configurar CI/CD para deploy contínuo

---

## Autor

Projeto criado para fins de aprendizado, portfólio e demonstração.

- Nome: [Seu Nome]
- Repositório: `school-system`
- Contato: `seu-email@dominio.com`
