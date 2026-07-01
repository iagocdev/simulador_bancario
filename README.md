# 🏦 Simulador Bancário - Java Puro

Projeto acadêmico/pessoal desenvolvido em Java puro (Java SE) simulando um sistema de cadastros e operações bancárias do Banco Itaú.

##  Demonstração
* *

##  Como Executar

1. Clone este repositório:
   ```bash
   git clone https://github.com/iagocdev/simulador_bancario.git
   
2. Navegue até a pasta do projeto (onde estão os pacotes):
   ```bash
   cd simulador_bancario/src
   ```
3. Compile os arquivos Java:
   ```bash
   javac model/*.java repository/*.java view/*.java
   ```
4. Execute o sistema:
   ```bash
   java view.SistemaBancario
   ```

## Funcionalidades Principais
- [x] Abertura de contas bancárias com geração automática de número.
- [x] Sistema de Autenticação (Login protegido por Número da Conta e CPF).
- [x] Operações financeiras com precisão decimal garantida via `BigDecimal`.
- [x] Sistema de Transferência simulando PIX e TED (buscas por CPF, Telefone ou Número da Conta).
- [x] Geração de Extrato Bancário detalhado do histórico de transações.

##  Evolução do Código (Histórico de Aprendizado)
O objetivo principal deste repositório é registrar a minha evolução prática com a linguagem Java:
- **Fase 1:** Sistema simples no terminal controlado por loops e listas dinâmicas (`ArrayList<String>`).
- **Fase 2:** Evolução para Programação Orientada a Objetos (POO), aplicando encapsulamento de dados com a classe `Pessoa`.
- **Fase 3:** Implementação de conceitos de composição de objetos e regras de negócios com a classe `ContaBancaria` (operações de Saque e Depósito).
- **Fase 4:** Transferências entre contas, simulando PIX e TED de forma segura.
- **Fase 5:** Persistência de dados em Banco de Dados com PostgreSQL.

##  Arquitetura (Padrão MVC e SOLID)
O projeto foi refatorado para seguir boas práticas de separação de responsabilidades (SRP):
- **`model/`**: Entidades e regras de negócio puras e isoladas (`ContaBancaria`, `Pessoa`).
- **`repository/`**: Gerenciamento, armazenamento e busca dos dados (`Banco`).
- **`view/`**: Interface de interação com o usuário via terminal (`SistemaBancario`).

##  Tecnologias Utilizadas
- Java 17+ (JDK)
- Bibliotecas nativas: `java.util.Scanner`, `java.util.ArrayList`, `java.math.BigDecimal`
- PostgreSQL (JDBC para persistência de dados)