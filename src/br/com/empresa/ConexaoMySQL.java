package br.com.empresa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class ConexaoMySQL {

    public static void main(String[] args) {
        Connection conexao = null;

        try {
            
            String url = "jdbc:mysql://127.0.0.1:3306/empresa"; 
            String usuario = "root"; 
            String senha = "112615rrr";

           
            Class.forName("com.mysql.cj.jdbc.Driver");

            
            conexao = DriverManager.getConnection(url, usuario, senha);
            System.out.println("✅ Conexão com o banco de dados realizada com sucesso!");

            
            Statement stmt = conexao.createStatement();

           
            String sql = "SELECT id, nome, salario FROM funcionario";
            ResultSet rs = stmt.executeQuery(sql);

            System.out.println("\n👨‍💼 Lista de Funcionários:");
            System.out.println("------------------------------------");

            
            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                double salario = rs.getDouble("salario");

                System.out.printf("ID: %d | Nome: %s | Salário: R$ %.2f%n", id, nome, salario);
            }

            rs.close();
            stmt.close();
            conexao.close();
            System.out.println("------------------------------------");
            System.out.println("🔒 Conexão encerrada com sucesso.");

        } catch (ClassNotFoundException e) {
            System.out.println("❌ Driver JDBC não encontrado.");
            e.printStackTrace();

        } catch (SQLException e) {
            System.out.println("❌ Erro ao conectar ou executar comandos no banco de dados.");
            e.printStackTrace();
        }
    }
}
