package repository;

import model.ContaBancaria;
import model.Pessoa;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Banco {

    // 1. Geração Automática do Número da Conta consultando o BD
    private int gerarProximoNumeroConta() {
        String sql = "SELECT MAX(numero_conta) AS ultimo_numero FROM contas";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                int ultimo = rs.getInt("ultimo_numero");
                return ultimo > 0 ? ultimo + 1 : 1001; // Se o banco for novo, começa no 1001
            }
        } catch (SQLException e) {
            System.out.println(" Erro ao gerar número da conta: " + e.getMessage());
        }
        return 1001;
    }

    // 2. Método Auxiliar para converter os dados do SQL em um Objeto Java
    private ContaBancaria mapearConta(ResultSet rs) throws SQLException {
        Pessoa titular = new Pessoa(
                rs.getString("nome_titular"),
                rs.getString("cpf_titular"),
                rs.getLong("telefone_titular")
        );
        // Usa o nosso novo construtor que aceita o saldo!
        return new ContaBancaria(titular, rs.getInt("numero_conta"), rs.getBigDecimal("saldo"));
    }

    // =================================================================
    // MÉTODOS CRUD (CREATE, READ, UPDATE)
    // =================================================================

    public ContaBancaria abrirConta(Pessoa titular) {
        int novoNumero = gerarProximoNumeroConta();
        String sql = "INSERT INTO contas (numero_conta, nome_titular, cpf_titular, telefone_titular, saldo) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, novoNumero);
            stmt.setString(2, titular.getNome());
            stmt.setString(3, titular.getCpf());
            stmt.setLong(4, titular.getTelefone());
            stmt.setBigDecimal(5, BigDecimal.ZERO);

            stmt.executeUpdate(); // Executa a inserção no PostgreSQL

            return new ContaBancaria(titular, novoNumero);
        } catch (SQLException e) {
            System.out.println(" Erro ao salvar conta no banco: " + e.getMessage());
            return null;
        }
    }

    public ContaBancaria fazerLogin(int numeroConta, String cpf) {
        String sql = "SELECT * FROM contas WHERE numero_conta = ? AND cpf_titular = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, numeroConta);
            stmt.setString(2, cpf);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) return mapearConta(rs);
            }
        } catch (SQLException e) {
            System.out.println(" Erro de banco de dados no Login: " + e.getMessage());
        }
        return null; // Login falhou
    }

    public ContaBancaria buscarContaPorCpf(String cpf) {
        String sql = "SELECT * FROM contas WHERE cpf_titular = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cpf);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) return mapearConta(rs);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    public ContaBancaria buscarContaPorTelefone(long telefone) {
        String sql = "SELECT * FROM contas WHERE telefone_titular = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, telefone);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) return mapearConta(rs);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    public ContaBancaria buscarContaPorNumero(int numeroConta) {
        String sql = "SELECT * FROM contas WHERE numero_conta = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, numeroConta);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) return mapearConta(rs);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    public boolean isVazio() {
        String sql = "SELECT COUNT(*) AS total FROM contas";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) return rs.getInt("total") == 0;
        } catch (SQLException e) { e.printStackTrace(); }
        return true;
    }

    //  Atualiza o saldo da conta no banco após transferências e saques
    public void atualizarSaldo(ContaBancaria conta) {
        String sql = "UPDATE contas SET saldo = ? WHERE numero_conta = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setBigDecimal(1, conta.getSaldo());
            stmt.setInt(2, conta.getNumeroConta());
            stmt.executeUpdate(); // Salva saldo permanentemente

        } catch (SQLException e) {
            System.out.println(" Erro ao atualizar saldo no BD: " + e.getMessage());
        }
    }
}