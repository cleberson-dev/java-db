package com.clebersondev;

import java.sql.*;

public class Main {
    public static void main(String[] args) {
        Connection c = null;
        String BD_URL = "jdbc:mysql://localhost:3306/empresa";
        String BD_USUARIO = "root";
        String BD_SENHA = "";

	try {
	  c = DriverManager.getConnection(BD_URL, BD_USUARIO, BD_SENHA);
          Statement smt = c.createStatement();
          String sql = "";

          // Removendo a tabela toda vez que o código executar (evita erros e duplicatas)
          sql = "DROP TABLE IF EXISTS funcionario";
          smt.executeUpdate(sql);

	  // Criar tabela (cria uma relação funcionario)
          sql = "CREATE TABLE funcionario(";
          sql += "nome VARCHAR(50) ";
          sql += ", sobrenome VARCHAR(50)";
          sql += ", email VARCHAR(50)";
          sql += ", telefone CHAR(14)";
          sql += ", salario DOUBLE";
          sql += ")";
          smt.executeUpdate(sql);



          // Inserir dados (adiciona 2 novos funcionários à tabela)
          sql = "INSERT INTO funcionario VALUES(";
          sql += "'Cleberson'";
          sql += ", 'Junior'";
          sql += ", 'cleberson.dev@gmail.com'";
          sql += ", '+5598982272020'";
          sql += ", 3000.00";
          sql += ")";
          smt.executeUpdate(sql);

          sql = "INSERT INTO funcionario VALUES(";
          sql += "'Fernanda'";
          sql += ", 'Silva'";
          sql += ", 'fernandinha@silva.com'";
          sql += ", '+5598970707070'";
          sql += ", 5200.00";
          sql += ")";
          smt.executeUpdate(sql);



          // Consultar dados (recupera e imprime dois funcionários)
          sql = "SELECT * FROM funcionario";
          ResultSet rs = smt.executeQuery(sql);
          System.out.println("Consulta:");
          while (rs.next()) {
            String nomeCompleto = rs.getString("nome") + rs.getString("sobrenome");
            double salario = rs.getDouble("salario");

            String resumoFormato = "%s, R$%.2f";
            System.out.println(String.format(resumoFormato, nomeCompleto, salario));
          }



          // Atualizar dados (aumenta o salario dos funcionarios em R$200,00
          sql = "UPDATE funcionario SET salario = salario + 200";
          smt.executeUpdate(sql);
          rs = smt.executeQuery("SELECT * FROM funcionario");
          System.out.println("Atualização:");
          while (rs.next()) {
            String nomeCompleto = rs.getString("nome") + rs.getString("sobrenome");
            double salario = rs.getDouble("salario");

            String resumoFormato = "%s, R$%.2f";
            System.out.println(String.format(resumoFormato, nomeCompleto, salario));
          }



          // Remover dados (remove o funcionário Cleberson)
          sql = "DELETE FROM funcionario WHERE email='cleberson.dev@gmail.com'";
          smt.executeUpdate(sql);
          System.out.println("Remoção:");
          rs = smt.executeQuery("SELECT * FROM funcionario");
          while (rs.next()) {
            String nomeCompleto = rs.getString("nome") + " " + rs.getString("sobrenome");
            double salario = rs.getDouble("salario");

            String resumoFormato = "%s, R$%.2f";
            System.out.println(String.format(resumoFormato, nomeCompleto, salario));
          }

          smt.close();
          c.close();
        } catch (Exception e) {
          e.printStackTrace();
          System.err.println(e.getClass().getName() + ": " + e.getMessage());
          System.exit(0);
        }
    }
}
