# Sistema de Login e Cadastro com Spring Boot

Este é um projeto prático que demonstra como construir um sistema de login e cadastro do zero usando **Java, Spring Boot e Thymeleaf**. O projeto inclui funcionalidades essenciais de autenticação e gerenciamento de sessões.

---

### 💻 Funcionalidades

* **Cadastro de Usuário:** Tela para criação de novas contas, armazenando os dados no banco de dados.
* **Login de Usuário:** Autenticação de usuários existentes com validação de credenciais.
* **Home:** Página interna acessível apenas por usuários logados.
* **Restrição de Acesso:** O sistema impede o acesso a páginas internas sem a devida autenticação.
* **Gerenciamento de Sessão:** Utiliza cookies para manter o usuário logado e controlar o acesso.
* **Logout:** Funcionalidade para encerrar a sessão do usuário.

---

### 🛠️ Tecnologias Utilizadas

* **Java**: Linguagem de programação principal.
* **Spring Boot**: Framework para o backend da aplicação.
* **Maven**: Gerenciador de dependências.
* **PostgreSQL**: Banco de dados para persistência de dados.
* **Spring Data JPA**: Para facilitar a interação com o banco de dados.
* **Thymeleaf**: Motor de template para renderizar as páginas HTML do frontend.

---

### 🚀 Como Rodar o Projeto

1.  **Clone o repositório:**
    ```bash
    git clone https://github.com/MiguelOlivieira/Login-e-Cadastro-com-SpringBoot.git
    ```

2.  **Configuração do Banco de Dados:**
    * Certifique-se de ter o **PostgreSQL** instalado e em execução.
    * Crie um banco de dados com o nome que preferir.
    * O Spring Boot se encarregará de criar a tabela de usuários (`usuarios`) automaticamente.

3.  **Atualize as Propriedades do Banco de Dados:**
    * Abra o arquivo `src/main/resources/application.properties`.
    * Atualize as configurações com as suas credenciais do PostgreSQL:
    ```DataBaseConfiguration
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/NOME DO BANCO");
        dataSource.setUsername("USERNAME");
        dataSource.setPassword("SENHA DO BANCO");

     return dataSource;
    ```

4.  **Execute a Aplicação:**
    * Você pode rodar o projeto usando sua IDE (como VS Code ou IntelliJ) ou via linha de comando com o Maven.
    ```bash
    mvn spring-boot:run
    ```

5.  **Acesse a Aplicação:**
    * Após a inicialização, a aplicação estará disponível em `http://localhost:8080`.
    * 1-  `http://localhost:8080/login`.
    * 2-  `http://localhost:8080/cadastro`.
    * 3-  `http://localhost:8080/sair`.
    * 4-  `http://localhost:8080`

---

Agredecimentos ao Tácio pelo guia.
