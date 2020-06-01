# Banco de Dados SQL em Java

Passo-a-passo de como conectar sua aplicação Java em um banco de dados SQL, realizar operações básicas de definição, inserção e busca de dados.

> Está disponível um exemplo para a conexão a um banco de dados MySQL, com a criação e inserção/atualização/remoção/consulta de dados de uma tabela `funcionário`.   
>
> **OBS**: Código foi escrito com IntelliJ IDEA, copie o código fonte de entrada  `com.clebersondev.Main` para usar em sua IDE preferida.



## Criando a conexão

1. Baixe o driver JDBC específico ao seu SGBD e importe o arquivo JAR disponível em projeto ([Como adicionar no NetBeans]()):

   - [PostgreSQL](https://jdbc.postgresql.org/download/postgresql-42.2.12.jar)
   - [MySQL](https://dev.mysql.com/downloads/connector/j/)
   - [Microsoft SQL Server](https://go.microsoft.com/fwlink/?linkid=2122433)
   - [Oracle SQL](https://download.oracle.com/otn-pub/otn_software/jdbc/ojdbc10.jar)

2. Faça uma importação completa das classes/interfaces/etc... que estão disponíveis no pacote `java.sql`:

   ``````java
   import java.sql.*;
   ``````

3. No método `main` da sua aplicação, copie o código abaixo e substitua os campos entre `<` `>`:

   ``````java
   String BD_URL = "<url-do-seu-banco-de-dados>";
   String BD_USUARIO = "<nome-de-usuario-do-seu-bd>";
   String BD_SENHA = "<senha-do-seu-usuario-na-bd>";
   
   Connection c = null;
   
   try {
   	c = DriverManager.getConnection(BD_URL, BD_USUARIO, BD_SENHA);
   } catch (Exception e) {
       e.printStackTrace();
       System.err.println(e.getClass().getName() + ": " + e.getMessage());
   	System.exit(0);
   }
   ``````

   A URL varia de acordo com o SGBD e banco de dados a ser usado, portanto substitua o campo `<nome-bd>` com o nome do banco de dados que você irá usar e copie todo URL específico a sua SGBD para a variável `BD_URL`:

   - PostgreSQL: `jdbc:postgresql://localhost:5432/<nome-bd>`
   - MySQL: `jdbc:mysql://localhost:3306/<nome-bd>`
   - Microsoft SQL Server: `jdbc:sqlserver://localhost:1433;databaseName=<nome-bd>`
   - Oracle SQL Server: `jdbc:oracle:thin:@<nome-bd>`

   Exemplo de uma conexão para um SGBD mySQL, com um banco de dados chamado `empresa`, de usuário `fernando` e senha `senhasegura`:

   ``````java
   String BD_URL = "jdbc:mysql://localhost:3306/empresa";
   String BD_USUARIO = "fernando";
   String BD_SENHA = "senhasegura";
   
   Connection c = null;
   
   try {
   	c = DriverManager.getConnection(BD_URL, BD_USUARIO, BD_SENHA);
   } catch (Exception e) {
       e.printStackTrace();
       System.err.println(e.getClass().getName() + ": " + e.getMessage());
   	System.exit(0);
   }
   ``````

Pronto! A partir de agora, iremos utilizar o objeto `c` (Classe: `Connection`) para acessarmos nosso banco de dados e fazer operações de definição, manipulação de dados e gerenciamento da nossa BD.



## Operações



### Criar uma nova tabela

```java
String sql = "CREATE TABLE tabela(...)";
Statement smt = c.createStatement();
smt.executeUpdate(sql);
```



### Inserir dados em uma tabela

```java
String sql = "INSERT INTO tabela VALUES(...)";
Statement smt = c.createStatement();
smt.executeUpdate(sql);
```



### Consultar dados

```java
String sql = "SELECT ... FROM tabela";
Statement smt = c.createStatement();
ResultSet rs = smt.executeQuery(sql);

// rs.next() é responsável por disponibilizar uma tupla por vez no resultado da consulta
while (rs.next()) { 
    // rs.next() Retornará false quando não houver mais dados para extrair
    rs.getString('<nome-atributo>');
    rs.getInt('<nome-atributo>');
    rs.getDouble('<nome-atributo>');
    rs.getDate('<nome-atributo>');
}
```

> **OBS**: Deu pra percerber que existe um método para obter o valor da coluna de uma tupla para cada tipo de dado disponível em Java (`getTipo()`). 
>
> Opte usar o mais adequado para o tipo definido na BD.



### Atualizar dados

```java
String sql = "UPDATE tabela SET antigoAtributo = novoAtributo... WHERE ...";
Statement smt = c.createStatement();
ResultSet rs = smt.executeQuery(sql);
```



### Remover dados

```java
String sql = "DELETE FROM tabela WHERE ..."; // NÃO ESQUEÇA O WHERE -_-
Statement smt = c.createStatement();
ResultSet rs = smt.executeQuery(sql);
```





## Tudo certo! 

Agora que já sabe como configurar a conexão de um banco de dados SQL à sua aplicação Java, e sabe como ocorre a execução de comandos ao banco, tudo que precisa fazer é usar seus conhecimentos de SQL para construir seu banco de dados da maneira que achar melhor.





## Dicas

- Utilize `smt.executeUpdate` quando fazer operações em que o resultado da consulta não importe (operações de definições de dados, como criação/modificação/remoção de tabela, exclusão de tuplas, etc...).
- Utilize `smt.executeQuery` quando quiser fazer operações onde o resultado importe (buscas por dados, na maioria).