## Controle Financeiro — Projeto de Extensão

**Autor:** Antonio Matheus  
**Tipo:** Projeto de Extensão

---

### Descrição
O projeto **Controle Financeiro** é uma aplicação desenvolvida para resolver um problema de controle financeiro de uma empresa de pequeno porte, permitindo gerenciar dívidas e transações, controlar entradas e saídas financeiras e monitorar as obrigações da empresa.

O sistema possibilita:
- Listagem e registro de dívidas com descrição, valor e data de vencimento.
- Registro de transações financeiras (entradas e saídas) com descrição, valor e data.
- Cálculo do valor total das dívidas e transações, exibido diretamente no front-end.

### Termo de Autorização

O termo de autorização da empresa para desenvolvimento e utilização deste sistema
no contexto do Projeto de Extensão está disponível em:

- `docs_evidencias/termo_autorizacao/termo_autorizacao.jpg`

---

### Tecnologias Utilizadas
- **Back-end:** Java, Spring Boot, JPA
- **Banco de dados:** H2 (em memória, com persistência dos dados localmente)
- **Front-end:** HTML, CSS, JavaScript (interface básica)
- **Build:** Gerado JAR para execução local
- **Outras ferramentas:** NSSM para deploy local (opcional)

---

### Como Executar
1. Abrir o projeto em uma IDE compatível com Java/Spring Boot.
2. Executar a aplicação localmente usando o JAR ou direto pela IDE.
3. Acessar a aplicação pelo navegador ou por requisições HTTP (Postman) para visualizar dívidas, transações e valores totais.

### Observações
- O front-end é básico, apenas para exibição dos dados e consumo do back-end.
- Toda a lógica de negócio está concentrada no back-end, com métodos de cálculo de valores totais implementados na camada Service e expostos via Controller.
- Este projeto é de extensão obrigatório da faculdade, com entrega prevista para o dia 5 de dezembro.

### Evidências
- `docs_evidencias/termo_autorizacao` — termo de autorização da empresa
- `docs_evidencias/jar` — arquivo JAR para execução local
- `docs_evidencias/videosistema` — vídeos de demonstração do sistema funcionando
- `docs_evidencias/evidencias` — prints e registros da execução do projeto





