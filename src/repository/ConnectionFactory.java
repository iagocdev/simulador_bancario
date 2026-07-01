package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    private static final String URL = "jdbc:postgresql://localhost:5432/banco_itau";

    // Capturando os valores das variáveis
    private static final String USER = System.getenv("DB_USER");
    private static final String PASSWORD = System.getenv("DB_PASSWORD");

    public static Connection getConnection() {
        // Validação de segurança variáveis
        if (USER == null || PASSWORD == null) {
            throw new RuntimeException(" Variáveis de ambiente DB_USER e DB_PASSWORD não configuradas!");
        }

        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(" Erro ao conectar com o banco de dados: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Connection conexao = getConnection();
        if (conexao != null) {
            System.out.println(" Conexão segura com PostgreSQL realizada com sucesso!");
            try {
                conexao.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}